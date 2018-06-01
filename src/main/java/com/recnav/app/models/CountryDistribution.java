package com.recnav.app.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "country_distribution")
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class CountryDistribution extends BaseModels{
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
    private double distribution;

    @Column
    private String type;

    @Column
    private Date from_date;

    @Column
    private Date too_date;

    public CountryDistribution(String country, ArticleCategories category, int click) {
        this.country = country;
        this.category = category;
        this.click = click;
    }

    public CountryDistribution() {

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

    public double getDistribution() {
        return distribution;
    }

    public void setDistribution(double distribution) {
        this.distribution = distribution;
    }

    public void addClick(){
        this.click += 1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getFrom() {
        return from_date;
    }

    public void setFrom(Date from) {
        this.from_date = from;
    }

    public Date getToo() {
        return too_date;
    }

    public void setToo(Date too) {
        this.too_date = too;
    }


}
