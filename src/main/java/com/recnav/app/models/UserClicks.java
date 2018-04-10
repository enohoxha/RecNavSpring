package com.recnav.app.models;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;

@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class UserClicks extends BaseModels{

    private Articles article;
    private Users user;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Articles getArticle() {
        return article;
    }

    public void setArticle(Articles article) {
        this.article = article;
    }
}
