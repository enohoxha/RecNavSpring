package com.recnav.app.modelController;

import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.Articles;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ArticleModelController{

    Session session;
    Transaction transaction;

    public ArticleModelController(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
    }

    public void insertArticle(Articles article){
        try{
            session.save(article);
        } catch (Exception e){

            e.printStackTrace();
        }
    }

    public void commitTransaction(){

        transaction.commit();
    }

    public Articles find(Integer id){
        return session.get(Articles.class, id);
    }


}
