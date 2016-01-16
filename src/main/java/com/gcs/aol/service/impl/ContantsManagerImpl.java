package com.gcs.aol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.ContantsDAO;
import com.gcs.aol.entity.Contants;
import com.gcs.aol.service.IContantsManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class ContantsManagerImpl extends GenericManagerImpl<Contants, ContantsDAO> implements IContantsManager{

	@SuppressWarnings("unused")
	@Autowired
	private ContantsDAO dao;
}
