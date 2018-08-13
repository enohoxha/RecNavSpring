package com.recnav.app.models.Services;

import com.recnav.app.models.ArticleCategories;
import com.recnav.app.models.Articles;

import java.util.ArrayList;

public interface ArticlesService {


    public Articles getArticleById(Integer id);

    public void saveArticle(Articles articles);

    ArrayList<Articles> getArticles();

    public ArrayList<Articles> getArticlesByCategory(int categoryId);
}
