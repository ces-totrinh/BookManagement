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

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "author_book_tbl",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> _authors;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category _category;

	public Book() {
	}
	
	public Book(String name, Category category, Set<Author> authors) {
		super();
		this._bookName = name;
		this._authors = authors;
		this._category = category;
	}
	
	public Book(String name, Category category, Author... authors ) {
		this._bookName = name;
        this._authors = Stream.of(authors).collect(Collectors.toSet());
        this._authors.forEach(x -> x.get_books().add(this));
        this._category = category;
    }

	public Book(int id, String name, Category category, Set<Author> authors)  {
		super();
		this._bookId = id;
		this._bookName = name;
		this._category = category;
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
	
    public String getAuthorNames(Set<Author> authors) {		
		String names = "";
        for (Author author : authors){ 
            names += author.get_authName() + ", ";
        }
		return names.substring(0, names.length()-2);     
    }
}
