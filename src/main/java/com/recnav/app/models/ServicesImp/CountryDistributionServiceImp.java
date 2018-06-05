package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.CountryDistribution;
import com.recnav.app.models.Dao.CountryDistributionDao;
import com.recnav.app.models.Services.CountryDistributionService;
import com.recnav.app.models.UserClicks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountryDistributionServiceImp implements CountryDistributionService {

    @Autowired
    CountryDistributionDao countryDistributionDao;

    private HashMap<String, CountryDistribution> countryDistributions;
    private Map<String, Integer> countrys;



    public CountryDistributionServiceImp() {
        countryDistributions = new HashMap<>();
        countrys = new HashMap<>();
    }

    @Override
    @Transactional
    public void addCountryDistribution(CountryDistribution p) {
        this.countryDistributionDao.addCountryDistribution(p);
    }

    @Override
    public void updateCountryDistribution(CountryDistribution p) {

    }

    @Override
    public List<CountryDistribution> listCountryDistribution() {
        return null;
    }

    @Override
    public CountryDistribution getCountryDistributionById(int id) {
        return null;
    }

    @Override
    public void removeCountryDistribution(int id) {

    }

    @Override
    public CountryDistribution getCountryDistributionByKey(String key) {
        return null;
    }

    @Override
    public void calculateClicksPerLocation(List<UserClicks> userClicks) {

    }



    @Override

    public void calculateDistributions(List<UserClicks> userClicks){
        for (int i = 0; i < userClicks.size(); i++) {
            String bucketKey = userClicks.get(i).getUser().getCountry() + userClicks.get(i).getArticle().getCategory().getName();
            if(!countryDistributions.containsKey(bucketKey)){
                countryDistributions.put(bucketKey, new CountryDistribution(userClicks.get(i).getUser().getCountry(), userClicks.get(i).getArticle().getCategory(), 1));
            } else {
                countryDistributions.get(bucketKey).addClick();
            }

            if(countrys.containsKey(userClicks.get(i).getUser().getCountry())){
                countrys.put(userClicks.get(i).getUser().getCountry(), countrys.get(userClicks.get(i).getUser().getCountry()) + 1);
            } else {
                countrys.put(userClicks.get(i).getUser().getCountry(), 1);
            }
        }
    }

    @Transactional
    @Override
    public String getLastInsertedItemDate() {

        CountryDistribution cd = countryDistributionDao.getLastItem();
        if(cd != null){
            return cd.getCreated().toString();
        }
        return null;
    }

    @Override
    public void cleanValues() {


    }

    @Override
    @Transactional
    public void saveDistributions(Date start, Date end, String type) {
        for (CountryDistribution countryDistribution : countryDistributions.values()){
            //System.out.println((double) countryDistribution.getClick() / countrys.get(countryDistribution.getCountry()));
            countryDistribution.setDistribution((double) countryDistribution.getClick() / countrys.get(countryDistribution.getCountry()));
            countryDistribution.setFrom(start);
            countryDistribution.setToo(end);
            countryDistribution.setType(type);
            this.addCountryDistribution(countryDistribution);
        }
    }

}
