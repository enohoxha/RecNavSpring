package com.recnav.app.models.Dao;

import com.recnav.app.models.Users;

import java.util.List;

public interface UsersDao {

    public void addUsers(Users p);

    public void updateUsers(Users p);

    public List<Users> listUserss();

    public Users getUsersById(int id);

    public void removeUsers(int id);

    public Users getUsersByKey(String key);

}
