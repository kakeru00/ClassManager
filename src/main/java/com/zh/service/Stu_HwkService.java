package com.zh.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.zh.dao.Stu_HwkDao;
import com.zh.entity.Student_Homework;

@Service
public class Stu_HwkService {
	@Resource
	private Stu_HwkDao stu_hwkDao;
	

	public void save(Student_Homework s_h) {
		stu_hwkDao.save(s_h);
	}
	public void saveOrUpdate(Student_Homework s_h) {

		stu_hwkDao.saveOrUpdate(s_h);
	}
	
	public List<Student_Homework> find(DetachedCriteria criteria){
		return stu_hwkDao.find(criteria);
	}
}
