package com.recnav.app.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class Auth {

    @Autowired
    private SessionFactory sessionFactory;

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

    @Transactional
    public AuthData getCurrentUser() {

        AuthData authData = null;
        Session session = null;
        try{

            session = sessionFactory.getCurrentSession();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println();
        try {

            authData = (AuthData) session.createQuery("from AuthData where id = :i")
                    .setParameter("i", this.getUserId())
                    .getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return authData;
    }

}
