package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.ArticleCategories;
import com.recnav.app.models.Articles;
import com.recnav.app.models.Dao.ArticlesDao;
import com.recnav.app.models.Services.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticlesServiceImp implements ArticlesService {

    @Autowired
    private ArticlesDao articlesDao;

    @Transactional
    @Override
    public Articles getArticleById(Integer id) {
        return articlesDao.getArticleById(id);
    }

    @Transactional
    @Override
    public void saveArticle(Articles articles) {
        articlesDao.saveArticle(articles);
    }


}
