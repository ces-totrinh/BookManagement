package net.bookmanagement.servlet;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

import net.bookmanagement.dao.AuthorDAO;
import net.bookmanagement.dao.BookDAO;
import net.bookmanagement.dao.CategoryDAO;
import net.bookmanagement.model.Author;
import net.bookmanagement.model.Book;
import net.bookmanagement.model.Category;

@WebServlet("/")
public class BookServlet extends HttpServlet {

	public void init() {
		bookDao = new BookDAO();
		categoryDao = new CategoryDAO();
		authorDao = new AuthorDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/book-new":
				showNewForm(request, response);
				break;
			case "/book-insert":
				insertBook(request, response);
				break;
			case "/book-delete":
				deleteBook(request, response);
				break;
			case "/book-edit":
				showEditForm(request, response);
				break;
			case "/book-update":
				updateBook(request, response);
				break;
			case "/book-list":
				getAllBooks(request, response);
				break;
			default:
				redirectToHome(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("book-form.jsp");
		rd.forward(request, response);
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String authorList = request.getParameter("author");
		String categoryName = request.getParameter("category");

		List<String> authorNames = new ArrayList<String>(Arrays.asList(authorList.split(", ")));

		if (!bookDao.checkBookExisted(name, categoryName, authorNames)) {

			Set<Author> authors = new HashSet<>();

			for (String n : authorNames) {
				// get author object if it existed
				Author author = authorDao.getAuthorByName(n);
				if (author == null) {
					author = new Author(n);
				}
				authors.add(author);
			}
			// get category object if it existed
			Category category = categoryDao.getCategoryByName(categoryName);
			if (category == null)
				category = new Category(categoryName);
			Book book = new Book(name, category, authors);

			bookDao.saveBook(book);
		}
		response.sendRedirect("book-list");

	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		bookDao.deleteBookById(id);
		response.sendRedirect("book-list");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book book = bookDao.getBookById(id);
		request.setAttribute("book", book);
		RequestDispatcher rd = request.getRequestDispatcher("book-form.jsp");
		rd.forward(request, response);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String authorList = request.getParameter("author");
		String categoryName = request.getParameter("category");
		List<String> authorNames = new ArrayList<String>(Arrays.asList(authorList.split(", ")));

		Set<Author> authors = new HashSet<>();

		for (String n : authorNames) {
			Author author = authorDao.getAuthorByName(n);
			if (author == null) {
				author = new Author(n);
			}
			authors.add(author);
		}

		Category category = categoryDao.getCategoryByName(categoryName);
		if (category == null) {
			category = new Category(categoryName);
		}

		Book book = new Book(id, name, category, authors);
		bookDao.updateBook(book);
		response.sendRedirect("book-list");
	}

	private void getAllBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> listOfBooks = bookDao.getAllBooks();
		request.setAttribute("bookList", listOfBooks);
		request.getRequestDispatcher("book-list.jsp").forward(request, response);
	}

	private void redirectToHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	private static final long serialVersionUID = 1L;

	private AuthorDAO authorDao;
	private BookDAO bookDao;
	private CategoryDAO categoryDao;

}
