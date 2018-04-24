package com.recnav.app.models.Services;

import com.recnav.app.models.Articles;

public interface ArticlesService {


    public Articles getArticleById(Integer id);

    public void saveArticle(Articles articles);

}
