package com.zh.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.zh.utils.Pager;

public class BaseDao<T> {
	@Resource
	private HibernateTemplate hibernateTemplate;

	public Serializable save(T t) {

		return this.hibernateTemplate.save(t);
	}
	public void saveOrUpdate(T t) {

		this.hibernateTemplate.saveOrUpdate(t);
	}

	public void delete(T t) {
		this.hibernateTemplate.delete(t);
	}
	
	//批量删除
	public void delete(Collection<T> entities) {
		this.hibernateTemplate.deleteAll(entities);
	}
	
	//执行sql语句
	public void sqlQuery(String sql) {
		hibernateTemplate.execute(new HibernateCallback<T>() {
			
			@SuppressWarnings("deprecation")
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				session.createSQLQuery(sql).executeUpdate();
				return null;
			}
		});
		
	}

	public void update(T t) {
		this.hibernateTemplate.update(t);
	}

	public T load(Class<T> clazz, Serializable id) {
		return (T) this.hibernateTemplate.load(clazz, id);
	}

	public T get(Class<T> clazz, Serializable id) {
		// this.hibernateTemplate.get(clazz.getName(), id);
		return (T) this.hibernateTemplate.get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(Class<T> clazz) {
		return (List<T>) this.hibernateTemplate.find("from "+clazz.getName());
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		return (List<T>) this.hibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, String value){
		return (List<T>) this.hibernateTemplate.find(hql, value);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... objects){
		return (List<T>) this.hibernateTemplate.find(hql, objects);
	}

	
	//分页查找
	@SuppressWarnings("unchecked")
	public List<T> find(DetachedCriteria criteria, int firstResult, int maxResults) {

		return (List<T>) this.hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
	}
	

	
	public int count(Class<T> clazz,String where){
		Long number = (Long)this.hibernateTemplate.iterate("select count(*) from "+clazz.getName()+" "+where).next();
		
		return Integer.parseInt(number.toString());
	}
	@SuppressWarnings("unchecked")
	public List<T> find(DetachedCriteria criteria) {

		return (List<T>) this.hibernateTemplate.findByCriteria(criteria);
	}
	

}
