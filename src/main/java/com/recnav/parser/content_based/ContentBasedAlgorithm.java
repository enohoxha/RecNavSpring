package com.recnav.parser.content_based;


import com.recnav.app.models.*;
import com.recnav.app.models.Services.*;
import com.recnav.parser.content_based.Helpers.Distributions;
import com.recnav.parser.contract.AlgorithmContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
public class ContentBasedAlgorithm implements AlgorithmContract {

    private ArrayList<RecNavContentBased> recNavContentBase; //p0(category =  ci | click)

    private HashMap<String, CountryDistribution> shortTimeDistributions;
    private HashMap<String, UserDistribution> countryCategoryLong;

    private HashMap<String, CountryDistribution> longTimeBetweenDadesCategoryDistribution;

    private HashMap<String, UserDistribution> userDateMap;

    private final static int VIRTUAL_CLICKS = 10;

    @Autowired
    private Distributions contentBasedAlgorithmImplementation;

    @Autowired
    private CountryDistributionService countryDistributionService;

    @Autowired
    private UserDistributionService userDistributionService;

    @Autowired
    private RecNavContentBasedService recNavContentBasedService;

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserClicksService userClicksService;

    public ContentBasedAlgorithm() {
    }


    @Override
    public void startProcess() throws ParseException {
        this.recNavContentBase = new ArrayList();
        contentBasedAlgorithmImplementation.calculateLongTimeDistribution();
        this.contentBasedAlgorithmImplementation.calculateShortTimeDistribution();

        System.out.println("Start calculateCoefficients algorithm");
        this.getAllShortTImeDistribution();
        this.calculateCoefficients();

        System.out.println("End calculateCoefficients algorithm");
    }

    /**
     * Similarity
     * <p>
     * short time country distribution * ({ ϵ[t](total click month <= t) * (user distribution[t] / long time country distribution[t]) } + G )
     * __________________________________________________________________________________________________________________________________________
     * <p>
     * (ϵ[t](total click month <= t)) + G
     *
     *
     * Careful queries are expensive
     * search todo
     */

    private void calculateCoefficients() {

        ArrayList<Users> users = usersServices.getUsers();
        ArrayList<ArticleCategories> articleCategories = categoryService.getAll();

        for (Users user : users) {

            double NtG = this.getTotalClicks(user) + VIRTUAL_CLICKS; // total click + G (last part)

            for (ArticleCategories articleCategorie : articleCategories) {

                double p0catci = this.getShortTimeCountryDistribution(user.getCountry(),
                        articleCategorie.getName());// short time country distribution

                if(NtG > 0){

                    RecNavContentBased recNavContentBased = new RecNavContentBased();
                    recNavContentBased.setUsers(user);
                    recNavContentBased.setCategory(articleCategorie);

                    double value = this.getUserPreference(user, articleCategorie) + VIRTUAL_CLICKS;
                    double similarityCoefficient = (p0catci * value) / NtG;

                    if(similarityCoefficient == 0){
                        System.out.println();
                    } else if (similarityCoefficient > 1){
                        System.out.println();
                    }
                    recNavContentBased.setCoefficient(similarityCoefficient);
                    this.recNavContentBase.add(recNavContentBased);
                }

            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        recNavContentBasedService.saveAll(this.recNavContentBase);


    }

    private double getShortTimeCountryDistribution(String country, String name) {
        if(shortTimeDistributions.containsKey(name + ":" + country)){
            return shortTimeDistributions.get(name + ":" + country).getDistribution();
        }
        return 0;
    }

    private void getAllShortTImeDistribution(){
        this.shortTimeDistributions = new HashMap<>();
        this.longTimeBetweenDadesCategoryDistribution = new HashMap<>();
        this.countryCategoryLong = new HashMap<>();
        this.userDateMap = new HashMap<>();

        HashMap<String, String> data = new HashMap<>();
        HashMap<String, String> longTime = new HashMap<>();
        HashMap<String, String> longTimeUsers = new HashMap<>();

        data.put("type", "short_time");
        longTime.put("type", "long_time");
        longTimeUsers.put("type", "long_time_user");

        List<CountryDistribution> shortTimeDistributions = countryDistributionService.get(data, "test");
        List<UserDistribution> longTimeDistributions = userDistributionService.get(longTimeUsers, "test");

        List<UserDistribution> userDateList = userDistributionService.get(longTimeUsers, "test");

        for (CountryDistribution c: shortTimeDistributions) {
            this.shortTimeDistributions.put(c.getCategory().getName() + ":" + c.getCountry(), c);
        }

        for (UserDistribution u: longTimeDistributions) {
            this.countryCategoryLong.put(u.getUser().getCountry() + ":" + u.getCategory().getName() + ":" + u.getFrom_date() + ":" + u.getToo_date(), u);
        }

        for (UserDistribution u: userDateList) {
            this.userDateMap.put(u.getUser().getId() + ":" +u.getToo_date() + ":" + u.getFrom_date(), u);
        }



    }



    private double getTotalClick(Users u, Date from_date, Date too_date){
        if(this.userDateMap.containsKey(u.getId() + ":" + too_date + ":" + from_date)){
            return this.userDateMap.get(u.getId() + ":" + too_date + ":" + from_date).getClick();
        }
        return 0;
    }

    private double getTotalDistribution(Users u, String category, Date from_date, Date too_date){
        if(countryCategoryLong.containsKey(u.getCountry() + ":"+ category + ":" + from_date + ":" + too_date)){
            return this.countryCategoryLong.get(u.getCountry() + ":"+ category + ":" + from_date + ":" + too_date).getDistribution();
        }
        return 0;
    }



    private double getUserPreference(Users user, ArticleCategories articleCategorie){

        HashMap<String, String > val = new HashMap<>();
        val.put("user.userKey", user.getUserKey());
        val.put("category.name", articleCategorie.getName());
        val.put("type", "long_time_user");
        List <UserDistribution> userDistributions = userDistributionService.get(val, "test"); //optimize

        double returnVal = 0;

        if(userDistributions != null){
            int sum = 0;
            for (int i = 0; i < userDistributions.size(); i++){

                double nt = this.getTotalClick(user, userDistributions.get(i).getFrom_date(), userDistributions.get(i).getToo_date());
                double ptcatciclick = userDistributions.get(i).getDistribution();
                double ptcatci = this.getTotalDistribution(userDistributions.get(i).getUser(), userDistributions.get(i).getCategory().getName(), userDistributions.get(i).getFrom_date(), userDistributions.get(i).getToo_date());
                double value = 0;
                if(ptcatciclick > 1 || ptcatci > 1){
                    throw new IllegalArgumentException("Wtf propabilitetu > 0 ?");
                }
                if (ptcatci != 0){
                    value = (
                            ptcatciclick / ptcatci
                    );
                }
                returnVal += nt * value;
                sum += nt;
            }

        }


        return returnVal;

    }


    public double getTotalClicks(Users user) {

        int  sum = 0;

        for (UserDistribution value : userDateMap.values()) {
            if (value.getUser().getId() == user.getId()){
                sum += value.getClick();
            }
        }
        return sum;
    }
}
