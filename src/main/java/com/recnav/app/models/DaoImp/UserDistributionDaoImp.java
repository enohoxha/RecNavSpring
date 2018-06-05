package com.recnav.app.models.DaoImp;

import com.recnav.app.models.Dao.UserDistributionDao;
import com.recnav.app.models.UserDistribution;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDistributionDaoImp implements UserDistributionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UserDistribution> find(String key, String value) {
        return null;
    }

    @Override
    public UserDistribution save(UserDistribution ud) {
        Session session = sessionFactory.getCurrentSession();
        session.save(ud);
        return ud;
    }

    @Override
    public UserDistribution getLastInsertedItem() {
        Session session = sessionFactory.getCurrentSession();
        List results = session.createQuery("FROM UserDistribution  ORDER BY created DESC").list();
        if(results.isEmpty())
            return null;
        return (UserDistribution) results.get(0);
    }
}
