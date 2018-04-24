package com.recnav.app.models.DaoImp;

import com.recnav.app.models.Dao.UsersDao;
import com.recnav.app.models.Users;
import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UsersDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUsers(Users p) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(p);
    }

    @Override
    public void updateUsers(Users p) {

    }

    @Override
    public List<Users> listUserss() {
        return null;
    }

    @Override
    public Users getUsersById(int id) {
        return null;
    }

    @Override
    public void removeUsers(int id) {

    }

    @Override
    public Users getUsersByKey(String key) {
        Session session = sessionFactory.getCurrentSession();
        Users users = session.byNaturalId(Users.class)
                .using("userKey", key)
                .load();
        return users;
    }
}
