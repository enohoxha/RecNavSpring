package com.recnav.app.models.DaoImp;

import com.recnav.app.models.Dao.UserClicksDao;
import com.recnav.app.models.UserClicks;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserClicksDaoImp implements UserClicksDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUserClick(UserClicks userClicks) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(userClicks);
    }

    @Override
    public List<UserClicks> getUserClicks() {
        Session session = sessionFactory.getCurrentSession();

        List<UserClicks> userClicks = session.createQuery("from UserClicks").list();
        return userClicks;
    }

    @Override
    public List<UserClicks> getUserClicksDateRange(String start, String end) {
        Session session = sessionFactory.getCurrentSession();

        List<UserClicks> userClicks= session.createQuery("from UserClicks where created BETWEEN :start AND  :end")
        .setParameter("start", start)
        .setParameter("end", end).list();
        return userClicks;
    }
}
