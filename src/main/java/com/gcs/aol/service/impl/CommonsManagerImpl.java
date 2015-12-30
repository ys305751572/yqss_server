package com.gcs.aol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.CommonsDAO;
import com.gcs.aol.entity.Commons;
import com.gcs.aol.service.ICommonsManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class CommonsManagerImpl extends GenericManagerImpl<Commons, CommonsDAO> implements ICommonsManager{

	@SuppressWarnings("unused")
	@Autowired
	private CommonsDAO dao;
}
