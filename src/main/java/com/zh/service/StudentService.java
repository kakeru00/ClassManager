package com.zh.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.zh.dao.StudentDao;
import com.zh.entity.ClassAdmin;
import com.zh.entity.Student;
import com.zh.utils.Pager;

@Service
public class StudentService {
	
	@Resource
	private StudentDao studentDao;
	
	public boolean save(Student student){
		
		if(studentDao.save(student)!=null)return true;
		return false;
	}
	
	public boolean save(List<Student> students){
		for(Student student : students){
			if(studentDao.save(student)==null)return false;
		}
		
		return true;
	}
	
	public void delete(Student student){
		studentDao.delete(student);
	}
	
	public Student get(String id){
		return studentDao.get(Student.class, id);
	}
	public List<Student> find(DetachedCriteria criteria){
		

		return studentDao.find(criteria);
	}
	
	public List<Student> findByPage(DetachedCriteria criteria,int firstResult,int maxResults){

		return studentDao.find(criteria,firstResult,maxResults);
		
	}
	
/*	public List<Student> find(DetachedCriteria criteria){
		return null;
	}*/
	
	public List<Student> find(String hql){
		return studentDao.find(hql);
	}
	
	public List<Student> find(String hql, Object... objects){
		return studentDao.find(hql,objects);
	}
	
	public List<Student> find(String column, String value){
		return studentDao.find("from Student where "+column+" like '%"+value+"%'");
	}
	
	public List<Student> find(DetachedCriteria criteria,int firstResult, int maxResults){
		return studentDao.find(criteria, firstResult, maxResults);
	}
	
	public void deleteAll(){
		studentDao.sqlQuery("truncate table t_student");
	}
	
	public void update(Student student){
		studentDao.update(student);
	}
	
	public int count(ClassAdmin classAdmin){
		return studentDao.count(Student.class,"where classId = '"+classAdmin.getClassId()+"'");
	}
}
