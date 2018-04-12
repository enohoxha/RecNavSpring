package com.recnav.app.models;

import com.recnav.app.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Auth {

    private static Auth instance = null;

    private int userId;

    public Auth() {

    }

    public static void setInstance(Auth instance) {
        Auth.instance = instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public static Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
            System.out.println("Creating new instance");
        }
        return instance;
    }

    public AuthData getCurrentUser() {

        AuthData authData = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        try {

            authData = (AuthData) session.createQuery("from AuthData where id = :i")
                    .setParameter("i", this.getUserId())
                    .getSingleResult();
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            if(session!=null){
                session.close();
            }

        }

        return authData;
    }

}
