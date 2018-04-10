package com.recnav.app.models;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

@AttributeOverrides({
        @AttributeOverride( name="created", column = @Column(name="created_at") ),
        @AttributeOverride( name="updated", column = @Column(name="updated_at") )
})
public class UserTypes extends BaseModels{
    private int id;
    private String type;
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
