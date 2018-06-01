package com.recnav.app.models.ServicesImp;

import com.recnav.app.models.Dao.UsersDao;
import com.recnav.app.models.Services.UsersServices;
import com.recnav.app.models.Users;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImp implements UsersServices {
    @Autowired
    private UsersDao personDAO;

    @Transactional
    @Override
    public void addUsers(Users p) {
        personDAO.addUsers(p);
    }

    @Override
    public void updateUsers(Users p) {
        personDAO.updateUsers(p);
    }

    @Override
    public List<Users> listUserss() {
        return personDAO.listUserss();
    }

    @Transactional
    @Override
    public Users getUsersById(int id) {
        return personDAO.getUsersById(id);
    }

    @Override
    public void removeUsers(int id) {
        personDAO.removeUsers(id);
    }

    @Transactional
    @Override
    public Users getUserByKey(String key) {
        return personDAO.getUsersByKey(key);
    }

    @Transactional
    @Override
    public ArrayList getUsers() {
        ArrayList<Users> users = personDAO.getAllUsers();
        return users;
    }
}
