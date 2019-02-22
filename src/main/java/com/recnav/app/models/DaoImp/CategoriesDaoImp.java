package com.recnav.app.models.DaoImp;

import com.recnav.app.models.ArticleCategories;
import com.recnav.app.models.Dao.CategoriesDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CategoriesDaoImp implements CategoriesDao {

    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public ArticleCategories getArticleByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        ArticleCategories articleCategories = session.byNaturalId(ArticleCategories.class)
                .using("name", name).load();

        return articleCategories;
    }

    @Override
    public ArticleCategories addCategory(ArticleCategories articleCategories) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(articleCategories);
        return articleCategories;
    }

    @Override
    public ArrayList<ArticleCategories> getAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        ArrayList<ArticleCategories> users = (ArrayList<ArticleCategories>) session.createQuery("from ArticleCategories")
                .list();
        return users;
    }
}
