package net.bookmanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.bookmanagement.model.Category;
import net.bookmanagement.utils.HibernateUtil;

public class CategoryDAO {
	
	public void saveCategory(Category category) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }
	
	public void updateCategory(Category category) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }
	
	public void deleteCategoryById(int id) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			Category category = session.get(Category.class, id);
			if (category != null) {
				session.delete(category);
			}
			transaction.commit();
		} catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		session.close();
	}
	
	public Category getCategoryById(int id) {
		Transaction transaction = null;
		Category category = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			transaction = session.beginTransaction();
			category = session.get(Category.class, id);
			transaction.commit();
		
		} catch(Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
		}
		session.close();
		return category;
		
	}
	
	public Category getCategoryByName(String name) {
		Transaction transaction = null;
		Category category = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			transaction = session.beginTransaction();
			category = (Category) session.createQuery("from Category where category_name=:name").setParameter("name", name).uniqueResult();
			transaction.commit();
		
		} catch(Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
		}
		session.close();
		return category;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories(){
		Transaction transaction = null;
		 List <Category> listOfCategory = null;
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 try {
			 transaction = session.beginTransaction();
			 
			 listOfCategory = session.createQuery("from Category").getResultList();
			 
			 transaction.commit();
			 
		 } catch ( Exception e) {
			 if ( transaction != null) {
				 transaction.rollback();
			 }
			 e.printStackTrace();
		 }
		 session.close();
		 return listOfCategory;
	}
	
}


