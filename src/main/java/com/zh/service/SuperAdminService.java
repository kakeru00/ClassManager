package com.zh.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zh.dao.SuperAdminDao;
import com.zh.entity.SuperAdmin;

@Service
public class SuperAdminService {
	
	@Resource
	private SuperAdminDao superAdminDao;
	
	public SuperAdmin get(String id){
		return superAdminDao.get(SuperAdmin.class, id);
	}
}
