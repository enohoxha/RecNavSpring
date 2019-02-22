package com.recnav.app.models.Dao;

import com.recnav.app.models.ArticleCategories;
import com.recnav.app.models.Articles;

import java.util.ArrayList;

public interface CategoriesDao {

    public ArticleCategories getArticleByName(String name);

    public ArticleCategories addCategory(ArticleCategories articleCategories);

    public ArrayList<ArticleCategories> getAllCategories();

}
