package com.recnav.app.modelController;

import com.recnav.app.database.HibernateUtil;
import com.recnav.app.models.ArticleCategories;
import com.recnav.app.models.Articles;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArticleCategoriesModelController {
    Session session;
    Transaction transaction;
    List<ArticleCategories>  categories;
    CriteriaBuilder builder;
    CriteriaQuery<ArticleCategories> query;

    public ArticleCategoriesModelController(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.beginTransaction();

        builder = session.getCriteriaBuilder();
        query = builder.createQuery(ArticleCategories.class);
    }

    public void findByName(String name){
        try{
            /*Root<ArticleCategories> root= query.from(ArticleCategories.class);
            query.select(root).where(builder.equal(root.get("name"), name));
*/
             categories = session
                    .createQuery("from ArticleCategories where name = :n")
                    .setParameter("n", name)
                    .getResultList();

        } catch (Exception e){
            e.printStackTrace();


        }

    }

    public int findOrAdd(ArticleCategories categories, String name){
        this.findByName(name);
        try{
            if(this.categories.size() == 0){
                int categoryId = (Integer) session.save(categories);
                return categoryId;
            } else {
                return this.categories.get(0).getId();
            }
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }


    public void commitTransaction(){
        transaction.commit();
    }
}
