package com.zh.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")//静态化 hibernate某查找方法用到
@Entity
@Table(name="t_student")
public class Student implements Serializable{
	
	
	private String id;
	private String name;
	private String password = "student";
	private String email;
	private String photo;
	private String phone;
	private boolean status = false;
	private boolean access = false;
	private boolean firstTag = false;
	private ClassAdmin classAdmin;
	
	public Student(String id, String name, String password, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	public Student( String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}
	public Student() {
	}
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable=false,columnDefinition="varchar(15)")
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public boolean isAccess() {
		return access;
	}
	public void setAccess(boolean access) {
		this.access = access;
	}
	
	@ManyToOne
	@JoinColumn(name="classid")
	public ClassAdmin getClassAdmin() {
		return classAdmin;
	}
	public void setClassAdmin(ClassAdmin classAdmin) {
		this.classAdmin = classAdmin;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isFirstTag() {
		return firstTag;
	}
	public void setFirstTag(boolean firstTag) {
		this.firstTag = firstTag;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
