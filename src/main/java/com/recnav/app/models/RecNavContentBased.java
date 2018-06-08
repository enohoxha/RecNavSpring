package com.recnav.app.models;

import javax.persistence.*;

@Entity
@Table(name = "rec_nav_content_based")
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class RecNavContentBased extends BaseModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private ArticleCategories category;

    @Column(name = "rec_coefficient")
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

    public ArticleCategories getCategory() {
        return category;
    }

    public void setCategory(ArticleCategories category) {
        this.category = category;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
