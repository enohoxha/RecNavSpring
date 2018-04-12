package com.recnav.app.modelController;

import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.UserTypes;
import com.recnav.app.models.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;

public class UserModelController{

    Session session;
    Transaction transaction;
    private Integer userTypeId;
    private Users users;

    public UserModelController(Users users) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();
        this.users = users;
    }

    public UserModelController() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();

    }


    public void save(){
        System.out.println("fdfdfdfdfdfdddf"+users.getUserType().getType());
        UserTypes userTypes = UserTypeModelController.get(users.getUserType().getId());
        users.setUserType(userTypes);
        session.save(users);
    }


    public void commitTransaction(){
        transaction.commit();
        session.close();
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }


    public Users find(String userKey){

        Users user;
        try{
            user = (Users) session.createQuery("from users where userKey = :key")
                    .setParameter("key", userKey)
                    .getSingleResult();

        } catch (NoResultException e){
            user = null;
        }


        return user;
    }
}
