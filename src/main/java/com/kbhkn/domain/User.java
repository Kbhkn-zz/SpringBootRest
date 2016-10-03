package com.kbhkn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by kbhkn on 20.09.2016 16:38.
 */
@Entity
@Table(name = "USER")
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "id_generator_user", strategy = "increment")
	@GeneratedValue(generator = "id_generator_user")
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "passowrd", length=80)
	private String password;

	@Column(name = "role")
	private String role;

	public User() {
	}

	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
