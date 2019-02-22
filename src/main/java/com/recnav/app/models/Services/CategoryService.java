package com.recnav.app.models.Services;

import com.recnav.app.models.ArticleCategories;

import java.util.ArrayList;

public interface CategoryService {

    public ArticleCategories getArticleByName(String name);

    public ArticleCategories addCategory(ArticleCategories articleCategories);

    public ArrayList<ArticleCategories> getAll();

    public ArticleCategories findOrAdd(ArticleCategories articleCategories, String name);

}
