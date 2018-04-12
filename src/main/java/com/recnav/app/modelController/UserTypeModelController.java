package com.recnav.app.modelController;

import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.UserTypes;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserTypeModelController extends UserTypes{

    static Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    static Transaction transaction = session.getTransaction();



    public static UserTypes get(Integer id){
        return session.get(UserTypes.class, id);
    }




}
