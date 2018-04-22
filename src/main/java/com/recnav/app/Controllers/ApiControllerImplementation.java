package com.recnav.app.Controllers;


import com.recnav.app.ResponseModels.Response;
import com.recnav.app.database.HibernateUtil;
import com.recnav.app.modelController.*;
import com.recnav.app.models.*;
import com.recnav.app.routes.ApiController;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Component
public class ApiControllerImplementation implements ApiController {
    private Response response;


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
                ArticleCategoriesModelController articleController = new ArticleCategoriesModelController();
                ArticleCategories articleCategories = articleController.findOrAdd(item.getCategory(), categoryName);
                articleController.commitTransaction();

                AuthData auth = Auth.getInstance().getCurrentUser();

                ArticleModelController article = new ArticleModelController();
                item.setCategory(articleCategories);

                item.setApp(auth.getApp());
                article.insertArticle(item);
                article.commitTransaction();

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


        AuthData auth = Auth.getInstance().getCurrentUser();
        UserModelController userModelController = new UserModelController();
        items.forEach(users->{
            if(userModelController.find(users.getUserKey()) == null){
                userModelController.setUser(users);
                users.setApp(auth.getApp());
                userModelController.save();
            }

        });
        userModelController.commitTransaction();


        this.response.setType(Response.SUCCESS);
        this.response.setCode(Response.ALL_GOOD);
        this.response.setMessageKey("message");
        this.response.setMessageText("New User has been registered");

        return response;
    }

    @Override
    public Response recordClick(@RequestBody UserClickModelController users) {

        try{
            ArticleModelController articleModelController= new ArticleModelController();
            Articles articles = articleModelController.find(users.getArticleId());
            articleModelController.commitTransaction();


            UserModelController userModelController = new UserModelController();
            Users users1 = userModelController.find(users.getUserKey());

            if(users1 != null){
                userModelController.commitTransaction();

                UserClicks userClicks = new UserClicks();
                userClicks.setArticle(articles);
                userClicks.setUser(users1);

                users = new UserClickModelController(userClicks);
                users.save();
                users.commitTransaction();

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
}
