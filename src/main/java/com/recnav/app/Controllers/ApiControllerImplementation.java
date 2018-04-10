package com.recnav.app.Controllers;


import com.recnav.app.ResponseModels.Response;
import com.recnav.app.database.HibernateUtil;
import com.recnav.app.modelController.ArticleCategoriesModelController;
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
                articleController.findOrAdd(item.getCategory(),categoryName);
                articleController.commitTransaction();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
        return null;
    }


}
