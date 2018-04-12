package com.recnav.app.modelController;

import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.UserClicks;
import com.recnav.app.models.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserClickModelController {


    Session session;
    Transaction transaction;
    UserClicks userClicks;

    private String userKey;
    private int articleId;

    public UserClickModelController() {
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public UserClickModelController(UserClicks userClicks) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
        this.userClicks = userClicks;
    }

    public void save(){
        try{
            session.persist(userClicks);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void commitTransaction(){
        transaction.commit();
    }

}
