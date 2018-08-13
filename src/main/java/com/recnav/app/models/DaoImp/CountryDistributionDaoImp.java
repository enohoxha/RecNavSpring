package com.recnav.app.models.DaoImp;

import com.recnav.app.models.CountryDistribution;
import com.recnav.app.models.Dao.CountryDistributionDao;
import com.recnav.app.models.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class CountryDistributionDaoImp implements CountryDistributionDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addCountryDistribution(CountryDistribution p) {
        Session session = sessionFactory.getCurrentSession();
        if(p.getType() == "short_time"){
            session.saveOrUpdate(p);
        } else {
            session.save(p);
        }
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
        if(results.isEmpty())
            return null;
        return (CountryDistribution) results.get(0);
    }

    @Override
    public List get(HashMap values, String type) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from CountryDistribution where ";
        for (Object value : values.keySet()) {
            hql += value + " = '" + values.get(value) +"'";
            hql += " and ";
        }
        hql = hql.substring(0, hql.length() - 4);
        if(type == "rec"){
            hql += "ORDER BY from_date DESC";
        }
        List results = session.createQuery( hql).list();
        if(results.isEmpty())
            return null;
        return results;

    }

    @Override
    public void deleteAll(String type) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete CountryDistribution where type = '" + type + "'").executeUpdate();
    }
}
