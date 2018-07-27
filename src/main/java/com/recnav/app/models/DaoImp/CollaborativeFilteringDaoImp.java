package com.recnav.app.models.DaoImp;

import com.recnav.app.models.CollaborativeFiltering;
import com.recnav.app.models.Dao.CollaborativeFilteringDao;
import com.recnav.app.models.RecNavContentBased;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class CollaborativeFilteringDaoImp implements CollaborativeFilteringDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(CollaborativeFiltering collaborativeFiltering)
    {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(collaborativeFiltering);
    }

    @Override
    public List get(HashMap values, String type) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from CollaborativeFiltering where ";
        if(type.equals("bigger")){
            for (Object value : values.keySet()) {
                hql += value + " > " + values.get(value);
                hql += " and ";
            }
        } else if(type.equals("top")){
            hql = "from CollaborativeFiltering order By coefficient desc and";
        }
        else {

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
    public void saveAll(ArrayList<CollaborativeFiltering> collaborativeFiltering) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from CollaborativeFiltering").executeUpdate();
        int i = 1;
        for (CollaborativeFiltering r: collaborativeFiltering) {

                session.save(r);
                if( i % 25000 == 0 ) {
                    session.flush();
                    session.clear();
                }
                i++;

        }
    }
}
