package com.recnav.com.recnav.userSimulator;

import com.recnav.app.models.Articles;
import com.recnav.app.models.Services.ArticlesService;
import com.recnav.app.models.Services.UserClicksService;
import com.recnav.app.models.Services.UsersServices;
import com.recnav.app.models.UserClicks;
import com.recnav.app.models.Users;
import com.recnav.parser.Adapters.AlgorithmAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

@Component
public class UsersClickSimulator {

    @Autowired
    UserClicksService userClicksService;

    @Autowired
    ArticlesService articlesService;

    @Autowired
    UsersServices usersServices;

    @Scheduled(cron = "0 * * * * *")
    public void generateDummyClick() {
        ArrayList<Users> usersList = usersServices.getUsers();
        ArrayList<Articles> articles = articlesService.getArticles();
        if(usersList.size() > 0){
            Random random = new Random();
            int userId = random.nextInt(Math.abs(usersList.size()-1));

            UserClicks u = new UserClicks();
            u.setUser(usersList.get(userId));
            u.setArticle(articles.get(random.nextInt(articles.size()-1)));

            userClicksService.addUserClick(u);
        }

    }

}
