package com.springboot.blogimetro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name= "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	@Size(min=4)
	private String password;
	
	@Column(name = "nivelacesso")
	private String nivelacesso;

	public User(String name, String email, String password,String nivelacesso) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.nivelacesso = nivelacesso;
	}

	public User() {
		super();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNivelacesso() {
		return nivelacesso;
	}

	public void setNivelacesso(String nivelacesso) {
		this.nivelacesso = nivelacesso;
	}
	
	

}
