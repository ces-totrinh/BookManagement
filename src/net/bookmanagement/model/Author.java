package net.bookmanagement.model;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "author_tbl")
public class Author {

	public Author() {
	}

	public Author(String name) {
		super();
		this._authName = name;

	}

	public Author(int id, String name) {
		super();
		this._authId = id;
		this._authName = name;
	}

	public int get_authId() {
		return _authId;
	}

	public void set_authId(int _authId) {
		this._authId = _authId;
	}

	public String get_authName() {
		return _authName;
	}

	public void set_authName(String _authName) {
		this._authName = _authName;
	}

	public Set<Book> get_books() {
		return _books;
	}

	public void set_books(Set<Book> _books) {
		this._books = _books;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private int _authId;

	@Column(name = "author_name")
	private String _authName;

	@ManyToMany(mappedBy = "_authors")
	private Set<Book> _books = new HashSet<>();

}
