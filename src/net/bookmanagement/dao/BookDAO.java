package net.bookmanagement.dao;

import java.util.ArrayList;
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
			transaction = session.beginTransaction();
			session.saveOrUpdate(book);
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
			transaction = session.beginTransaction();
			session.update(book);
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
			transaction = session.beginTransaction();
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
		try {
			transaction = session.beginTransaction();
			book = session.get(Book.class, id);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		session.close();
		return book;

	}

	@SuppressWarnings("unchecked")
	public Boolean checkBookExisted(String bookName, String categoryName, List<String> authorNames) {
		Transaction transaction = null;
		Boolean checkExisted = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();

			String conditionOfAuthor = "";
			for (String n : authorNames) {
				conditionOfAuthor += "and author_name LIKE '%" + n + "%'";
			}

			System.out.print(conditionOfAuthor.substring(3));
			List<Book> book = session
					.createQuery("" + "from Book b " + "JOIN FETCH b._authors a " + "JOIN FETCH b._category c "
							+ "where book_name=:bookName " + "and category_name=:categoryName " + "and ("
							+ conditionOfAuthor.substring(3) + ")")
					.setParameter("bookName", bookName).setParameter("categoryName", categoryName).getResultList();

			if (book.size() > 0)
				checkExisted = true;
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		session.close();
		return checkExisted;

	}

	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks() {
		Transaction transaction = null;
		List<Book> listOfBook = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();

			listOfBook = session.createQuery("from Book").getResultList();
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		session.close();
		return listOfBook;
	}

}
