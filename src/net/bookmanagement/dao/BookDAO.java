package net.bookmanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.bookmanagement.model.Book;
import net.bookmanagement.utils.HibernateUtil;

public class BookDAO {
	public void saveBook(Book book) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the book object
            session.saveOrUpdate(book);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }
	
	public void updateBook(Book book) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // start a transaction
            transaction = session.beginTransaction();
            // save the book object
            session.update(book);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();
    }
	
	public void deleteBookById(int id) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			// start a transaction
			transaction = session.beginTransaction();
			//delete a book object
			Book book = session.get(Book.class, id);
			if (book != null) {
				session.delete(book);
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
	
	public Book getBookById(int id) {
		Transaction transaction = null;
		Book book = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			//Start a transaction
			transaction = session.beginTransaction();
			// get Book object by id
			book = session.get(Book.class, id);
			//commit transaction
			transaction.commit();
		
		} catch(Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
		}
		session.close();
		return book;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks(){
		Transaction transaction = null;
		 List <Book> listOfBook = null;
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 try {
			 transaction = session.beginTransaction();
			 
			 listOfBook = session.createQuery("from Book")
			            .getResultList();	 
			 transaction.commit();
			 
		 } catch ( Exception e) {
			 if ( transaction != null) {
				 transaction.rollback();
			 }
			 e.printStackTrace();
		 }
		 session.close();
		 return listOfBook;
	}
	
}

