package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.ArticleCategories;
import com.recnav.app.models.Dao.CategoriesDao;
import com.recnav.app.models.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoriesDao categoriesDao;

    @Override
    @Transactional
    public ArticleCategories getArticleByName(String name) {
        return categoriesDao.getArticleByName(name);
    }

    @Override
    @Transactional
    public ArticleCategories addCategory(ArticleCategories articleCategories) {
        return categoriesDao.addCategory(articleCategories);
    }

    @Override
    @Transactional
    public ArticleCategories findOrAdd(ArticleCategories articleCategories, String name){
        if(this.getArticleByName(name) != null){
            return this.getArticleByName(name);
        } else {
            return this.addCategory(articleCategories);
        }
    }

}
