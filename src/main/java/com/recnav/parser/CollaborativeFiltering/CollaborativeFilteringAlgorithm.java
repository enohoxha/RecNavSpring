package com.recnav.parser.CollaborativeFiltering;

import com.recnav.app.models.*;
import com.recnav.app.models.Services.*;
import com.recnav.parser.CollaborativeFiltering.KMeans.KMens;
import com.recnav.parser.CollaborativeFiltering.Models.ClusterModel;
import com.recnav.parser.CollaborativeFiltering.Models.UserModel;
import com.recnav.parser.Helpers.StopWatch;
import com.recnav.parser.contract.AlgorithmContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class CollaborativeFilteringAlgorithm implements AlgorithmContract {

    @Autowired
    private UserClicksService userClicks;

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private CollaborativeFilteringService collaborativeFilteringService;

    @Autowired
    private RecNavContentBasedService recNavContentBasedService;

    @Autowired
    private StopWatch stopWatch;

    private ArrayList<Articles> articles;
    private HashMap<String, Double> contentBasedCoefficients;
    private HashMap<Integer, Double> fractionalMembershipHash;
    private HashMap<String, Double> indicator;
    private HashMap<Integer, Integer> userCluster;

    public CollaborativeFilteringAlgorithm() {
    }

    @Override
    public void startProcess() throws Exception
    {
        System.out.println(Thread.currentThread().getName());
        System.out.println("********************************** Starting Collaborative Filtering ****************************");


        HashMap<Integer, int[]> usersSet = new HashMap<>();
        ArrayList<Users> users = this.usersServices.getUsers();
        List<UserClicks> userClicks = this.userClicks.getUserClicks();
        List<Integer> clicks = new ArrayList<>();
        ClusterModel[] clusterModels;

        //Loop all users and find his clicks set
        for (Users u: users) {
            for (UserClicks userClick: userClicks) {

                if(u.getId() == userClick.getUser().getId()){
                    clicks.add(userClick.getArticle().getId());
                }
            }

            if (clicks.size() > 0){
                if(!usersSet.containsKey(u.getId())) {

                    usersSet.put(u.getId(), clicks.stream().mapToInt(i->i).toArray());
                }
            }

            clicks.clear();

        }
        KMens kMens = new KMens(24 , usersSet);
        clusterModels = kMens.startClusteringData();

        this.calculateRecommendations(clusterModels, users);

    }

    /**
     * Fill db with recomandions
     * @param clusterModels
     * @param users
     */
    private void calculateRecommendations(ClusterModel[] clusterModels, ArrayList<Users> users)
    {
        articles = articlesService.getArticles();
        ArrayList<CollaborativeFiltering> collaborativeFilterings = new ArrayList<>();
        double coefficient = 0;
        double contentBasedCoefficient = 0;

        HashMap<String , Double> data = new HashMap<>();
        List<RecNavContentBased> recNavContentBased = recNavContentBasedService.get(data, "get");

        stopWatch.start();
        this.initHashMaps(recNavContentBased, clusterModels);
        stopWatch.stop();

        System.out.println("Init: " + stopWatch.getTime());
        stopWatch.clean();
        stopWatch.start();
        for (Users u: users) {
            if(userCluster.containsKey(u.getId())){
                for (Articles a: articles) {
                    contentBasedCoefficient = this.getContentBasedCoefficient(u.getId(), a.getCategory().getId());
                    coefficient = this.calculateCoefficient(u, a);
                    double value = contentBasedCoefficient * coefficient;
                    if(value > 0){
                        CollaborativeFiltering collaborativeFiltering = new CollaborativeFiltering();
                        collaborativeFiltering.setUsers(u);
                        collaborativeFiltering.setArticle(a);
                        collaborativeFiltering.setCoefficient(value);
                        collaborativeFilterings.add(collaborativeFiltering);
                    }

                }
            }

        }
        stopWatch.stop();
        System.out.println("Time: " + stopWatch.getTime());
        // save data
        collaborativeFilteringService.saveAll(collaborativeFilterings);
    }

    /**
     * Optimization
     * @param recNavContentBased
     * @param clusterModels
     */
    private void initHashMaps(List<RecNavContentBased> recNavContentBased, ClusterModel[] clusterModels)
    {
        this.contentBasedCoefficients = new HashMap<String, Double>();
        this.contentBasedCoefficients = new HashMap<>();
        this.fractionalMembershipHash = new HashMap<>();
        this.indicator = new HashMap<>();
        this.userCluster = new HashMap<>();

        for (RecNavContentBased i : recNavContentBased)
            contentBasedCoefficients.put(i.getUsers().getId() + ":" + i.getCategory().getId(), i.getCoefficient());

        for (ClusterModel c : clusterModels) {
            for (Map.Entry<Integer, UserModel> entry : c.getUsers().entrySet()) {
                fractionalMembershipHash.put(entry.getValue().getUserId(), entry.getValue().getFractionalMembership());
                userCluster.put(entry.getValue().getUserId(), c.getClusterId());
            }
        }

        double sum = 0;
        for (Articles a: articles) {
            for (ClusterModel c : clusterModels) {
                for (Map.Entry<Integer, UserModel> entry : c.getUsers().entrySet()) {
                    int[] clicks = entry.getValue().getClicks();
                    for (int i = 0; i < clicks.length; i++){
                        if(clicks[i] == a.getId()){
                            sum += 1;
                        }
                    }
                }
                indicator.put(c.getClusterId() + ":" + a.getArticleId(), sum);
                sum = 0;
            }
        }


    }

    /**
     * Get content based value
     * @param id
     * @param id1
     * @return
     */
    private double getContentBasedCoefficient(int id, int id1)
    {
        return contentBasedCoefficients.get(id + ":" + id1);
    }

    private double calculateCoefficient(Users u, Articles a)
    {


        double fractionalMembership = this.getFractionalMembership(u);

        double indicatior = this.getIndicatorFromCluster(a, u);

        return (double) fractionalMembership * indicatior;
    }



    private double getIndicatorFromCluster(Articles a, Users u)
    {
        return indicator.get(userCluster.get(u.getId()) + ":" + a.getArticleId());
    }

    private double getFractionalMembership(Users u)
    {
        return this.fractionalMembershipHash.get(u.getId());
    }


}
