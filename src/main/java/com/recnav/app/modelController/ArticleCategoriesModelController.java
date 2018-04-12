package com.recnav.app.modelController;

import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.ArticleCategories;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ArticleCategoriesModelController {
    Session session;
    Transaction transaction;
    List<ArticleCategories>  categories;

    public ArticleCategoriesModelController(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();

    }

    public void findByName(String name){
        try{
             categories = session
                    .createQuery("from ArticleCategories where name = :n")
                    .setParameter("n", name)
                    .getResultList();

        } catch (Exception e){
            e.printStackTrace();

        }

    }

    public ArticleCategories findOrAdd(ArticleCategories categories, String name){
        this.findByName(name);
        try{
            if(this.categories.size() == 0){
                int categoryId = (Integer) session.save(categories);
                return categories;
            } else {
                return this.categories.get(0);
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public void commitTransaction(){
        transaction.commit();
        session.close();
    }
}
