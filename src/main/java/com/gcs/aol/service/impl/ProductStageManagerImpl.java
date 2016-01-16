package com.gcs.aol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.ProductStageDAO;
import com.gcs.aol.entity.ProductStage;
import com.gcs.aol.service.IProductStageManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class ProductStageManagerImpl extends GenericManagerImpl<ProductStage, ProductStageDAO> implements IProductStageManager{

	@SuppressWarnings("unused")
	@Autowired
	private ProductStageDAO dao;
}
