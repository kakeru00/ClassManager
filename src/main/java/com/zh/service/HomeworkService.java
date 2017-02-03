package com.zh.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.zh.dao.HomeworkDao;
import com.zh.entity.Homework;

@Service
public class HomeworkService {

	@Resource
	HomeworkDao homeworkDao;
	public void save(Homework homework) {
		homeworkDao.save(homework);
		
	}
	public List<Homework> find(String hql) {
		
		return homeworkDao.find(hql);
	}
	public List<Homework> find(DetachedCriteria criteria) {
		
		return homeworkDao.find(criteria);
	}
	public Homework  get(int id) {
		return homeworkDao.get(Homework.class, id);
	}
	public Homework  load(int id) {
		return homeworkDao.load(Homework.class, id);
	}
	public void delete(Homework homework) {
		homeworkDao.delete(homework);
	}
	public void update(Homework homework) {
		homeworkDao.update(homework);		
	}
}
