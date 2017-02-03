package com.zh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "t_teacher")
@Entity
public class Teacher {
	private int id;
	private String name;
	private String password;
	private String email;
	private boolean status = false;
	private ClassAdmin classAdmin;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name="classid")
	public ClassAdmin getClassAdmin() {
		return classAdmin;
	}
	public void setClassAdmin(ClassAdmin classAdmin) {
		this.classAdmin = classAdmin;
	}
	
	
}
