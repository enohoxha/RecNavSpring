package com.recnav.app.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user_types")
@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class UserTypes extends BaseModels{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type_name")
    private String type;

    @OneToMany(
            targetEntity = Users.class,
            mappedBy = "userType",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Users> users = new HashSet<Users>(0);

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }
}
