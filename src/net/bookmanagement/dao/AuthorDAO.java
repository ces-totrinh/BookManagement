package net.bookmanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.bookmanagement.model.Author;
import net.bookmanagement.utils.HibernateUtil;

public class AuthorDAO {

	public void saveAuthor(Author author) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }
	
	public void updateAuthor(Author author) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }
	
	public void deleteAuthorById(int id) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			Author author = session.get(Author.class, id);
			if (author != null) {
				session.delete(author);
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
	
	public Author getAuthorById(int id) {
		Transaction transaction = null;
		Author author = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			transaction = session.beginTransaction();
			author = session.get(Author.class, id);
			transaction.commit();
		
		} catch(Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
		}
		session.close();
		return author;
		
	}
	
	public Author getAuthorByName(String name) {
		Transaction transaction = null;
		Author author = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			transaction = session.beginTransaction();
			author = (Author) session.createQuery("from Author where author_name=:name").setParameter("name", name).uniqueResult();
			 
			//commit transaction
			transaction.commit();
		
		} catch(Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
		}
		session.close();
		return author;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> getAllAuthor(){
		Transaction transaction = null;
		 List <Author> listOfAuthor = null;
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 try {
			 transaction = session.beginTransaction();
			 
			 listOfAuthor = session.createQuery("from Author").getResultList();
			 
			 transaction.commit();
			 
		 } catch ( Exception e) {
			 if ( transaction != null) {
				 transaction.rollback();
			 }
			 e.printStackTrace();
		 }
		 session.close();
		 return listOfAuthor;
	}
	
}

