package net.bookmanagement.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;

@Entity
@Table(name="book_tbl")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private int _bookId;
	
	@Column(name="book_name")
	private String _bookName;

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author_book_tbl",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> _authors;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category _category;
	

	public Book() {
	}
	
	public Book(String name, Set<Author> authors) {
		super();
		this._bookName = name;
		this._authors = authors;
	}
	
	public Book(String name, Author... authors) {
		this._bookName = name;
        this._authors = Stream.of(authors).collect(Collectors.toSet());
        this._authors.forEach(x -> x.get_books().add(this));
    }

	public Book(int id, String name, Set<Author> authors)  {
		super();
		this._bookId = id;
		this._bookName = name;
		this._authors = authors;
	}

	public int get_bookId() {
		return _bookId;
	}

	public void set_bookId(int _bookId) {
		this._bookId = _bookId;
	}

	public String get_bookName() {
		return _bookName;
	}

	public void set_bookName(String _bookName) {
		this._bookName = _bookName;
	}

	public Category get_category() {
		return _category;
	}

	public void set_category(Category _category) {
		this._category = _category;
	}

	public Set<Author> get_authors() {
		return _authors;
	}

	public void set_authors(Set<Author> _authors) {
		this._authors = _authors;
	}

}
