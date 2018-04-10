package com.recnav.app.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "apps")
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class Apps extends BaseModels  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;

    @OneToMany(
            targetEntity = AuthData.class,
            mappedBy = "app",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private Set<AuthData> authDataSet;

   /* private Set<Users> users = new HashSet<Users>(0);*/

    public Apps() {
    }

    public Apps(int id, String name, String url, Set<AuthData> authDataSet, Set<Users> users) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.authDataSet = authDataSet;
       // this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<AuthData> getAuthDataSet() {
        return authDataSet;
    }

    public void setAuthDataSet(Set<AuthData> authDataSet) {
        this.authDataSet = authDataSet;
    }
/*
    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }*/
}
