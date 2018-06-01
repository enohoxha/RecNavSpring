package com.recnav.app.models;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Table(name = "user_distribution")
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class UserDistribution extends BaseModels{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private ArticleCategories category;

    @Column
    private int clicks;

    @Column
    private double distribution;

    @Column
    private String type;

    @Column
    private Date from_date;

    @Column
    private Date too_date;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private Users user;

    public UserDistribution(ArticleCategories category, Users user, int click) {
        this.category = category;
        this.user = user;
        this.clicks = click;
    }

    public UserDistribution(){

    }

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

    public ArticleCategories getCategory() {
        return category;
    }

    public void setCategory(ArticleCategories category) {
        this.category = category;
    }

    public int getClick() {
        return clicks;
    }

    public void setClick(int click) {
        this.clicks = click;
    }

    public double getDistribution() {
        return distribution;
    }

    public void setDistribution(double distribution) {
        this.distribution = distribution;
    }

    public void addClick(){
        this.clicks += 1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getToo_date() {
        return too_date;
    }

    public void setToo_date(Date too_date) {
        this.too_date = too_date;
    }
}
