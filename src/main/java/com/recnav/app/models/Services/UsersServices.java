package com.recnav.app.models.Services;

import com.recnav.app.models.Users;

import java.util.ArrayList;
import java.util.List;

public interface UsersServices {

    public void addUsers(Users p);

    public void updateUsers(Users p);

    public List<Users> listUserss();

    public Users getUsersById(int id);

    public void removeUsers(int id);

    public Users getUserByKey(String key);

    public ArrayList<Users> getUsers();
}
