package com.zh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name="t_homework")
public class Homework implements Serializable{

	
	private int id;
	private String title;
	private String content;
	private String attach;
	private Date publish = new Date();
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date conclude;
	private boolean status = true;
	private ClassAdmin classAdmin;
	private Student author;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	@ManyToOne
	@JoinColumn(name="classid")
	public ClassAdmin getClassAdmin() {
		return classAdmin;
	}
	public void setClassAdmin(ClassAdmin classAdmin) {
		this.classAdmin = classAdmin;
	}

	
	public Date getConclude() {
		return conclude;
	}
	public void setConclude(Date conclude) {
		this.conclude = conclude;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@ManyToOne
	@JoinColumn(name="authorid")
	public Student getAuthor() {
		return author;
	}
	public void setAuthor(Student author) {
		this.author = author;
	}
	public Date getPublish() {
		return publish;
	}
	public void setPublish(Date publish) {
		this.publish = publish;
	}
	
}
