package net.bookmanagement.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;

@Entity()
@Table(name="category_tbl")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private int _categId;
	
	@Column(name="category_name")
	private String _categName;
	
	@OneToMany(mappedBy = "_category", cascade = CascadeType.ALL)
    private Set<Book> _books;
	
	public Category() {
	}
	
	public Category(String name) {
		super();
		this._categName = name;
	}
	
	public Category(int id, String name) {
		super();
		this._categId = id;
		this._categName = name;
	}
	
	public Category(String name, Book... books) {
		super();
		this._categName = name;
		this._books = Stream.of(books).collect(Collectors.toSet());
        this._books.forEach(x -> x.set_category(this));
	}

	public int get_categId() {
		return _categId;
	}

	public void set_categId(int _categId) {
		this._categId = _categId;
	}

	public String get_categName() {
		return _categName;
	}

	public void set_categName(String _categName) {
		this._categName = _categName;
	}

	public Set<Book> get_books() {
		return _books;
	}

	public void set_books(Set<Book> _books) {
		this._books = _books;
	}
	
}
