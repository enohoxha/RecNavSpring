package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.*;
import com.recnav.app.models.Dao.UserDistributionDao;
import com.recnav.app.models.DaoImp.CategoriesDaoImp;
import com.recnav.app.models.DaoImp.UserDaoImp;
import com.recnav.app.models.DaoImp.UserDistributionDaoImp;
import com.recnav.app.models.Services.UserDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserDistributionServiceImp implements UserDistributionService{

    HashMap<Integer, Users> usersHashMap;
    HashMap<Integer, Integer> usersClick;
    HashMap<Integer, ArticleCategories> articleCategoriesHashMap;
    HashMap<String, UserDistribution> userDistributionHashMap;

    @Autowired
    UserDistributionDaoImp userDistributionDao;

    @Autowired
    CategoriesDaoImp categoriesDaoImp;

    @Autowired
    UserDaoImp userDaoImp;

    public UserDistributionServiceImp() {

        this.usersHashMap = new HashMap<>();
        this.usersClick = new HashMap<>();
        this.articleCategoriesHashMap = new HashMap<>();
        this.userDistributionHashMap = new HashMap<>();


    }

    @Override
    public List<UserDistribution> find(String key, String value) {
        return null;
    }

    @Override
    @Transactional
    public UserDistribution save(UserDistribution ud) {
        this.userDistributionDao.save(ud);
        return ud;
    }

    @Override
    @Transactional
    public void calculateDistributions(List<UserClicks> userClicks) {
        ArrayList<Users> users = userDaoImp.getAllUsers();
        ArrayList<ArticleCategories> articleCategories = categoriesDaoImp.getAllCategories();


        for (UserClicks u: userClicks) {
            if(usersClick.containsKey(u.getUser().getId())){
                usersClick.put(u.getUser().getId(), usersClick.get(u.getUser().getId()) + 1);
            }else{
                usersClick.put(u.getUser().getId(), 1);
            }
            usersHashMap.put(u.getUser().getId(), u.getUser());
        }

        for (ArticleCategories ac: articleCategories){
            articleCategoriesHashMap.put(ac.getId(), ac);
        }



        for (UserClicks uc: userClicks){
            if(userDistributionHashMap.containsKey(uc.getUser().getId() + ":" + uc.getArticle().getCategory().getId())){
                userDistributionHashMap.get(
                        uc.getUser().getId() + ":" + uc.getArticle().getCategory().getId()
                ).addClick();
            } else{
                userDistributionHashMap.put(uc.getUser().getId() + ":" + uc.getArticle().getCategory().getId(),
                        new UserDistribution(uc.getArticle().getCategory(), uc.getUser(), 1));
            }
        }
    }

    @Override
    @Transactional
    public void saveDistributions(Date startDate, Date endDate, String type) {
        for (UserDistribution userDistribution : userDistributionHashMap.values()){

            userDistribution.setType(type);
            userDistribution.setFrom_date(startDate);
            userDistribution.setToo_date(endDate);

            if(userDistribution.getClick() > usersClick.get(userDistribution.getUser().getId())){
                System.out.println(userDistribution.getClick() +" > "+ usersClick.get(userDistribution.getUser().getId()));
            }
            userDistribution.setDistribution((double) userDistribution.getClick() / usersClick.get(userDistribution.getUser().getId()));

            this.save(userDistribution);
        }
    }

    @Override
    @Transactional
    public String getLastInsertedItemDate() {
        UserDistribution userDistribution = userDistributionDao.getLastInsertedItem();

        if(userDistribution != null){

            return userDistribution.getCreated().toString();
        }
        return null;
    }
}
