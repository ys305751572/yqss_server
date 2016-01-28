package com.gcs.aol.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.ContantsDAO;
import com.gcs.aol.entity.Contants;
import com.gcs.aol.entity.Users;
import com.gcs.aol.service.IContantsManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class ContantsManagerImpl extends GenericManagerImpl<Contants, ContantsDAO> implements IContantsManager{

	@SuppressWarnings("unused")
	@Autowired
	private ContantsDAO dao;

	@Override
	public List<Contants> queryContants(Integer userId) {
		
		Users user = new Users();
		user.setUserId(userId);
		String sql = "select c.user,c.contantsName,c.mobile from contants c where c.user = " + user;
		
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query = em.createQuery(sql);
		List list = query.getResultList();
		
		for (Object object : list) {
			Object[] objs = (Object[]) object;
			Users _user = (Users) objs[0];
			String contantsName = (String) objs[1];
			String mobile = (String) objs[2];
			
		}
		return null;
	}
}
