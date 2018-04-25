package com.recnav.app.models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "article_categories")
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class ArticleCategories extends BaseModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NaturalId
    private String name;

    @OneToMany(
            targetEntity = Articles.class,
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Articles> articles = new HashSet<Articles>(0);


    public ArticleCategories() {

    }

    public ArticleCategories(String name) {
        this.name = name;
    }

    public Set<Articles> getArticles() {
        return articles;
    }

    public void setArticles(Set<Articles> articles) {
        this.articles = articles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
