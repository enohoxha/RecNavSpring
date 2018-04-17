package com.recnav.app.modelController;

import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.UserTypes;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserTypeModelController extends UserTypes{

    private Session session;
    private Transaction transaction;

    public UserTypeModelController() {
        session  = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.getTransaction();
    }

    public UserTypes get(Integer id){
        return session.get(UserTypes.class, id);
    }




}
