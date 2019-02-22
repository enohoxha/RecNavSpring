package com.recnav.app.models;

import javax.persistence.*;


@Entity
@Table(name = "user_clicks")
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class UserClicks extends BaseModels{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "article_id" , nullable = false)
    private Articles article;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private Users user;

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

    public boolean isSameRegion(UserClicks userClicks){

        if(this.user.getCountry().equals(userClicks.getUser().getCountry())){
            return true;
        }
        return false;
    }

    public boolean isSameCategory(UserClicks userClicks){
        if(this.article.getCategory().getName().equals(userClicks.getArticle().getCategory().getName() )){
            return  true;
        }
        return false;
    }
}
