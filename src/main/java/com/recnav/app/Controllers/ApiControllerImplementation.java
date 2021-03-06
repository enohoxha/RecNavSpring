package com.recnav.app.Controllers;


import com.google.gson.Gson;
import com.recnav.app.ResponseModels.Response;
import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.*;
import com.recnav.app.models.RequestModels.UserClickRequest;
import com.recnav.app.models.ResponseModels.SimilarityModel;
import com.recnav.app.models.Services.*;
import com.recnav.app.routes.ApiController;
import com.recnav.parser.RecommendationAlgorithm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Component
public class ApiControllerImplementation implements ApiController {

    private Response response;

    @Autowired
    private UsersServices usersServices;

    @Autowired
    private Auth authData;

    @Autowired
    private UserClicksService userClicksService;

    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RecNavContentBasedService recNavContentBasedService;

    @Autowired
    private CountryDistributionService countryDistributionService;

    @Autowired
    private CollaborativeFilteringService collaborativeFilteringService;

    @Autowired
    private RecommendationAlgorithm recommendationAlgorithm;

    public ApiControllerImplementation() {
        response = new Response();
    }

    public Response addNewApp() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Apps app = new Apps();
        app.setName("test");
        app.setUrl("test");
        session.persist(app);
        transaction.commit();

        this.response.setType(Response.SUCCESS);
        this.response.setCode(Response.ALL_GOOD);
        this.response.setMessageKey("message");
        this.response.setMessageText("App Added with success");


        return this.response;


    }

    @Override
    public Response addArticles(@RequestBody List <Articles>items) {
        items.forEach(item->{
            try{
                String categoryName = item.getCategory().getName();

                ArticleCategories articleCategories = categoryService.findOrAdd(item.getCategory(), categoryName);

                AuthData auth = this.authData.getCurrentUser();


                item.setCategory(articleCategories);
                item.setApp(auth.getApp());

                this.articlesService.saveArticle(item);

                this.response.setType(Response.SUCCESS);
                this.response.setCode(Response.ALL_GOOD);
                this.response.setMessageKey("message");
                this.response.setMessageText("New Articles has been imported");
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        return this.response;
    }


    @Override
    public Response registerUser(@RequestBody List<Users> items) {


        AuthData auth = authData.getCurrentUser();



        items.forEach(users->{

            Users user = usersServices.getUserByKey(users.getUserKey());

            if(user == null){

                Users u = new Users();
                u.setCountry(users.getCountry());
                u.setFirstName(users.getFirstName());
                u.setLastName(users.getLastName());
                u.setUserKey(users.getUserKey());
                u.setApp(auth.getApp());
                u.setUserType(users.getUserType());
                usersServices.addUsers(u);


                this.response.setType(Response.SUCCESS);
                this.response.setCode(Response.ALL_GOOD);
                this.response.setMessageKey("message");
                this.response.setMessageText("New User has been registered");
            } else{

                this.response.setType(Response.SUCCESS);
                this.response.setCode(Response.ALL_GOOD);
                this.response.setMessageKey("message");
                this.response.setMessageText("User Exist on our servers");
            }

        });

        return response;
    }

    @Override

    public Response recordClick(@RequestBody UserClickRequest users) {


        try{
            Articles articles = articlesService.getArticleById(users.getArticleId());


            Users users1 = usersServices.getUserByKey(users.getUserKey());

            if(users1 != null && articles != null){

                UserClicks userClicks = new UserClicks();
                userClicks.setArticle(articles);
                userClicks.setUser(users1);
                userClicksService.addUserClick(userClicks);

                this.response.setType(Response.SUCCESS);
                this.response.setCode(Response.ALL_GOOD);
                this.response.setMessageKey("message");
                this.response.setMessageText("Registered click action");

            } else {
                this.response.setType(Response.ERROR);
                this.response.setCode(Response.USER_DO_NOT_EXIST);
                this.response.setMessageKey("message");
                this.response.setMessageText("Please register user");
            }

            return response;

        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response getNearestCategories(@RequestParam("uuid") String userId) {

        HashMap<String , Double> data = new HashMap<>();

        List<RecNavContentBased> recNavContentBaseds = recNavContentBasedService.get(data, "top");
        List<SimilarityModel> response = new LinkedList<>();

        for (RecNavContentBased r : recNavContentBaseds) {

            if(r.getUsers().getUserKey().equals(userId)){
                SimilarityModel similarityModel = new SimilarityModel();
                similarityModel.setCategoryId(r.getCategory().getId());
                similarityModel.setCoefficent(r.getCoefficient());
                similarityModel.setType("SINGLE_USER_TYPE");
                similarityModel.setUserId(r.getUsers().getId());
                response.add(similarityModel);
            }
        }

        this.response.setType(Response.SUCCESS);
        this.response.setCode(Response.ALL_GOOD);
        this.response.setMessageKey("message");
        this.response.setMessageObject(response);

        return this.response;
    }

    @Override
    public Response getCountryDistributions() {
        HashMap<String, String> hashMap = new HashMap<>();
        List<CountryDistribution> countryDistributions = this.countryDistributionService.get(hashMap, "");

        this.response.setType(Response.SUCCESS);
        this.response.setCode(Response.ALL_GOOD);
        this.response.setMessageKey("message");
        this.response.setMessageObject(countryDistributions);

        return this.response;
    }

    @Override
    public Response getRecommendations(@RequestParam("uuid") String userId)
    {
        ArrayList rec = new ArrayList();
        HashMap<String , Double> data = new HashMap<>();
        Users u = usersServices.getUserByKey(userId);
        // if user is not registerd no rec found
        if (u == null){
            this.response.setType(Response.SUCCESS);
            this.response.setCode(Response.ALL_GOOD);
            this.response.setMessageKey("message");
            this.response.setMessageObject(null);
            return response;
        }
        data.put("user_id", (double) u.getId());
        List <CollaborativeFiltering> recList = collaborativeFilteringService.get(data, "rec");

        // if user dont exist in clusters of cf
        if (recList == null){

            List<RecNavContentBased> recNavContentBaseds = recNavContentBasedService.get(data, "rec");
            List<Articles> articles = null;

            // if user don't exist in contentBased
            if (recNavContentBaseds == null){

                HashMap<String , String> data2 = new HashMap<>();
                data2.put("country", u.getCountry());
                List <CountryDistribution> countryDistributions = countryDistributionService.get(data2, "rec");
                double maxValue = Integer.MIN_VALUE;
                int maxId = Integer.MIN_VALUE;
                int i = 1;

                for (CountryDistribution c: countryDistributions) {
                    if (i == 5){
                        break;
                    }
                    if(maxValue < c.getDistribution()){
                        maxValue = c.getDistribution();
                        maxId = c.getCategory().getId();
                    }
                    i++;
                }
                articles = articlesService.getArticlesByCategory(maxId);
            } else {
                articles = articlesService.getArticlesByCategory(recNavContentBaseds.get(0).getCategory().getId());
            }

            for (Articles a: articles) {
                rec.add(a.getArticleId());
            }
        } else{
            for (CollaborativeFiltering c: recList) {
                if (rec.size() > 20){
                    break;
                }
                rec.add(c.getArticles().getArticleId());
            }
        }

        this.response.setType(Response.SUCCESS);
        this.response.setCode(Response.ALL_GOOD);
        this.response.setMessageKey("message");
        this.response.setMessageObject(rec);

        return this.response;
    }

    @Override
    public Response runCollaborativeFiltering()
    {
        try {
            recommendationAlgorithm.startCollaborativeFiltering();
            this.response.setType(Response.SUCCESS);
            this.response.setCode(Response.ALL_GOOD);
            this.response.setMessageKey("message");
            this.response.setMessageText("Success");

        } catch (Exception e) {
            this.response.setType(Response.SUCCESS);
            this.response.setCode(Response.ALL_GOOD);
            this.response.setMessageKey("message");
            this.response.setMessageText(e.getMessage());
            e.printStackTrace();
        }
        return this.response;
    }


    @Override
    public Response runContentBased()
    {
        try {
            recommendationAlgorithm.reportCurrentTime();
            this.response.setType(Response.SUCCESS);
            this.response.setCode(Response.ALL_GOOD);
            this.response.setMessageKey("message");
            this.response.setMessageText("Success");

        } catch (Exception e) {
            this.response.setType(Response.SUCCESS);
            this.response.setCode(Response.ALL_GOOD);
            this.response.setMessageKey("message");
            this.response.setMessageText(e.getMessage());
            e.printStackTrace();
        }
        return this.response;
    }


}
