package com.recnav.app.models;

import javax.persistence.*;

@Entity(name = "user_distribution")
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class UserDistribution extends BaseModels{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String country;

    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private ArticleCategories category;

    @Column
    private int click;

    @Column
    private int distribution;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArticleCategories getCategory() {
        return category;
    }

    public void setCategory(ArticleCategories category) {
        this.category = category;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getDistribution() {
        return distribution;
    }

    public void setDistribution(int distribution) {
        this.distribution = distribution;
    }
}
