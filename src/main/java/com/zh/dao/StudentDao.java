package com.zh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zh.entity.Student;
@Repository
public class StudentDao extends BaseDao<Student>{
	
	public List<Student> find(){
		
		return this.find(Student.class);	
	}
	
	
}
