package com.recnav.app.models.Dao;

import com.recnav.app.models.Articles;

import java.util.ArrayList;

public interface ArticlesDao {

    public Articles getArticleById(Integer id);


    public void saveArticle(Articles articles);

    ArrayList<Articles> getArticles();

    public ArrayList<Articles> getArticlesByCategory(int categoryId);
}
