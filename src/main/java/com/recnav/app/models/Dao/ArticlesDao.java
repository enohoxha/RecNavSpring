package com.recnav.app.models.Dao;

import com.recnav.app.models.Articles;

public interface ArticlesDao {

    public Articles getArticleById(Integer id);


    public void saveArticle(Articles articles);

}
