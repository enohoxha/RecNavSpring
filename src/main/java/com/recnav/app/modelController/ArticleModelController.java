package com.recnav.app.modelController;

import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.Articles;
import com.recnav.app.models.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
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
        Articles articles;
        try{
            articles = (Articles) session.createQuery("from Articles where articleId = :key")
                    .setParameter("key", id)
                    .getSingleResult();

        } catch (NoResultException e){
            articles = null;
        }
        return articles;
    }


}
