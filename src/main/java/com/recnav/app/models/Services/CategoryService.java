package com.recnav.app.models.Services;

import com.recnav.app.models.ArticleCategories;

public interface CategoryService {

    public ArticleCategories getArticleByName(String name);

    public ArticleCategories addCategory(ArticleCategories articleCategories);

    public ArticleCategories findOrAdd(ArticleCategories articleCategories, String name);

}
