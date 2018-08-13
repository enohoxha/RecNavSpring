package com.recnav.app.models.DaoImp;

import com.recnav.app.models.Articles;
import com.recnav.app.models.Dao.ArticlesDao;
import com.recnav.app.models.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ArticlesDaoImp implements ArticlesDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Articles getArticleById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Articles articles = session.byNaturalId(Articles.class)
                .using("articleId", id).load();

        return articles;
    }


    @Override
    public void saveArticle(Articles articles) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(articles);
    }

    @Override
    public ArrayList<Articles> getArticles() {
        Session session = sessionFactory.getCurrentSession();
        ArrayList<Articles> articles = (ArrayList<Articles>) session.createQuery("from Articles")
                .list();
        return articles;
    }

    @Override
    public ArrayList<Articles> getArticlesByCategory(int categoryId)
    {
        Session session = sessionFactory.getCurrentSession();
        ArrayList<Articles> articles = (ArrayList<Articles>) session.createQuery("from Articles where category_id = " + categoryId).setMaxResults(20)
                .list();
        return articles;
    }
}
