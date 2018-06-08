package com.recnav.app.models.DaoImp;

import com.recnav.app.models.CountryDistribution;
import com.recnav.app.models.Dao.RecNavContentBasedDao;
import com.recnav.app.models.RecNavContentBased;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RecNavContentBasedDaoImp implements RecNavContentBasedDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(RecNavContentBased recNavContentBasedDao) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(recNavContentBasedDao);
    }

    @Override
    public List get(HashMap values, String type) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from RecNavContentBased where ";

        if(type.equals("bigger")){
            for (Object value : values.keySet()) {
                hql += value + " > " + values.get(value);
                hql += " and ";
            }
        } else {
            for (Object value : values.keySet()) {
                hql += value + " = " + values.get(value);
                hql += " and ";
            }

        }
        hql = hql.substring(0, hql.length() - 4);
        List results = session.createQuery(hql).list();
        if(results.isEmpty())
            return null;
        return results;

    }

    @Override
    public void saveAll(ArrayList<RecNavContentBased> recNavContentBaseds) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from RecNavContentBased").executeUpdate();
        for (RecNavContentBased r: recNavContentBaseds) {
            session.save(r);
        }
        session.flush();
    }
}
