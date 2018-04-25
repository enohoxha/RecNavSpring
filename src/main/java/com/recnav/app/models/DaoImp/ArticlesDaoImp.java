package com.recnav.app.models.DaoImp;

import com.recnav.app.models.Articles;
import com.recnav.app.models.Dao.ArticlesDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
