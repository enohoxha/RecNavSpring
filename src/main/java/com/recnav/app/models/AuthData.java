package com.recnav.app.models;

import javax.persistence.*;

@Entity
@Table(name = "auth_data")
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})


public class AuthData extends BaseModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "secret_key")
    private String secretKey;

    @ManyToOne
    @JoinColumn(name="app_id", nullable=false)
    private Apps app;


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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Apps getApp() {
        return app;
    }

    public void setApp(Apps app) {
        this.app = app;
    }
}
