package com.recnav.app.modelController;

import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.Articles;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ArticleModelController{

    Session session;
    Transaction transaction;
    Articles articles;

    public ArticleModelController(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
        articles = new Articles();
    }






}
