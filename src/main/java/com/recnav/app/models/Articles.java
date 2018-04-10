package com.recnav.app.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})

public class Articles extends BaseModels{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "article_id")
    private int articleId;

    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private ArticleCategories category;


    @ManyToOne
    @JoinColumn(name = "app_id" , nullable = false)
    private Apps app;

    /*@OneToMany(
            targetEntity = UserClicks.class,
            mappedBy = "article",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )*//*
    private Set<UserClicks> clicks = new HashSet<UserClicks>(0);*/

    public Articles() {

    }

    public Articles(int articleId, ArticleCategories category) {
        this.articleId = articleId;
        this.category = category;
    }

/*
    public Set<UserClicks> getClicks() {
        return clicks;
    }

    public void setClicks(Set<UserClicks> clicks) {
        this.clicks = clicks;
    }
*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public Apps getApp() {
        return app;
    }

    public void setApp(Apps app) {
        this.app = app;
    }

    public ArticleCategories getCategory() {
        return category;
    }

    public void setCategory(ArticleCategories category) {
        this.category = category;
    }

}
