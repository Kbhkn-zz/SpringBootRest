package com.kbhkn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "id_generator", strategy = "increment")
	@GeneratedValue(generator = "id_generator")
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	public Customer() {
		super();
	}

	public Customer(long id, String name, String lastName, String email) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Customer [id=%s, name=%s, lastName=%s, email=%s]", id, name, lastName, email);
	}
}
