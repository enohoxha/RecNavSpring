package com.recnav.app.database;

import com.recnav.app.models.*;
import com.recnav.app.Observers.ModelObserver;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration()
                    .configure()
                    .setInterceptor(new ModelObserver())
                    .addAnnotatedClass(Apps.class)
                    .addAnnotatedClass(AuthData.class)
                    .addAnnotatedClass(Articles.class)
                    .addAnnotatedClass(ArticleCategories.class)
                    .addAnnotatedClass(UserTypes.class)
                    .addAnnotatedClass(CountryDistribution.class)
                    .addAnnotatedClass(Users.class)
                    .addAnnotatedClass(UserClicks.class)
                    .buildSessionFactory();
        }
        catch (Exception ex ) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }


}
