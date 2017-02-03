package com.zh.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zh.dao.ClassDao;
import com.zh.entity.ClassAdmin;

@Service
public class ClassService {
	@Resource
	private ClassDao classDao;
	
public boolean save(ClassAdmin classAdmin){
		
		if(classDao.save(classAdmin)!=null)return true;
		return false;
	}
	public void delete(ClassAdmin classAdmin){
		classDao.delete(classAdmin);
	}
	
	public ClassAdmin get(String id){
		return classDao.get(ClassAdmin.class, id);
	}
	public List<ClassAdmin> find(){
		return classDao.find("from ClassAdmin");
	}
	public void update(ClassAdmin classAdmin) {
		classDao.update(classAdmin);
		
	}
	
}
