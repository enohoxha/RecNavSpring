package com.recnav.app.models;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")

@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class Users extends BaseModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "surname")
    private String lastName;

    @Column(name = "name")
    private String firstName;

    private String country;

    @ManyToOne
    @JoinColumn(name = "user_type_id" , nullable = false)
    private UserTypes userType;

    @ManyToOne
    @JoinColumn(name = "app_id" , nullable = false)
    private Apps app;

    @Column(name = "user_key")
    private String userKey;

    @OneToMany(
            targetEntity = UserClicks.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserClicks> clicks = new HashSet<UserClicks>(0);


    public Set<UserClicks> getClicks() {
        return clicks;
    }

    public void setClicks(Set<UserClicks> clicks) {
        this.clicks = clicks;
    }

    public Apps getApp() {
        return app;
    }

    public void setApp(Apps app) {
        this.app = app;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }


}
