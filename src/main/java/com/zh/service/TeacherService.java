package com.zh.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.zh.dao.TeacherDao;
import com.zh.entity.Teacher;


@Service
public class TeacherService {
	@Resource
	private TeacherDao teacherDao;
	
	public boolean save(Teacher teacher){
		
		if(teacherDao.save(teacher)!=null)return true;
		return false;
	}
	
	public boolean save(List<Teacher> teachers){
		for(Teacher teacher : teachers){
			if(teacherDao.save(teacher)==null)return false;
		}
		
		return true;
	}
	
	public void delete(Teacher teacher){
		teacherDao.delete(teacher);
	}
	
	public Teacher get(int id){
		return teacherDao.get(Teacher.class, id);
	}

	public List<Teacher> find(DetachedCriteria criteria) {
		return teacherDao.find(criteria);
	}

	public void update(Teacher teacher) {
		teacherDao.update(teacher);
	}
}
