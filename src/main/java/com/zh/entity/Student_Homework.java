package com.zh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="t_student_homework")
public class Student_Homework implements Serializable{
	
	private Student student;
	private Homework homework;
	private String status;
	private Date date = new Date();
	private String path;
	public Student_Homework(){}
	
	public Student_Homework(Student student, Homework homework){
		this.student = student;
		this.homework = homework;
	}
	@Id
	@ManyToOne
	@JoinColumn(name="studentid")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@Id
	@ManyToOne
	@JoinColumn(name="homeworkid")
	public Homework getHomework() {
		return homework;
	}
	public void setHomework(Homework homework) {
		this.homework = homework;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	


	
	
	
}
