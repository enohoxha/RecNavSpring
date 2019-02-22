package com.recnav.app.models.DaoImp;

import com.recnav.app.models.Dao.UserClicksDao;
import com.recnav.app.models.UserClicks;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
    public List<UserClicks> getUserClicksDateRange(Date start, Date end) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from UserClicks uc where uc.created BETWEEN :start AND  :end ")
                .setParameter("start", start)
                .setParameter("end", end);

        List<UserClicks> userClicks = query.list();
        return userClicks;
    }
}
