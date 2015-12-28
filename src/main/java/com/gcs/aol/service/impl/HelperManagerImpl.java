package com.gcs.aol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.HelperDAO;
import com.gcs.aol.entity.Helper;
import com.gcs.aol.service.IHelperManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class HelperManagerImpl extends GenericManagerImpl<Helper, HelperDAO> implements IHelperManager{

	@SuppressWarnings("unused")
	@Autowired
	private HelperDAO dao;
}
