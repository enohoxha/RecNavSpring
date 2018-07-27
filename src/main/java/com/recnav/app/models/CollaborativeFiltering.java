package com.recnav.app.models;

import javax.persistence.*;

@Entity
@Table(name = "collaborative_filtering")
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class CollaborativeFiltering extends BaseModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "article_id" , nullable = false)
    private Articles articles;

    @Column(name = "coefficient")
    private double coefficient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Articles getCategory() {
        return articles;
    }

    public void setArticle(Articles articles) {
        this.articles = articles;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
