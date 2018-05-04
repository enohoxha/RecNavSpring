package com.recnav.app.models.DaoImp;

import com.recnav.app.models.CountryDistribution;
import com.recnav.app.models.Dao.CountryDistributionDao;
import com.recnav.app.models.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDistributionDaoImp implements CountryDistributionDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addCountryDistribution(CountryDistribution p) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(p);
    }

    @Override
    public void updateCountryDistribution(CountryDistribution p) {

    }

    @Override
    public List<CountryDistribution> listCountryDistribution() {
        return null;
    }

    @Override
    public CountryDistribution getCountryDistributionById(int id) {
        return null;
    }

    @Override
    public void removeCountryDistribution(int id) {

    }

    @Override
    public CountryDistribution getCountryDistributionByKey(String key) {
        return null;
    }

    @Override
    public CountryDistribution getLastItem() {
        Session session = sessionFactory.getCurrentSession();
        List results = session.createQuery("FROM CountryDistribution  ORDER BY created DESC").list();
        System.out.println("asdasdasdad");
        return (CountryDistribution) results.get(0);
    }
}
