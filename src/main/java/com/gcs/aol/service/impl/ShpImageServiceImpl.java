package com.gcs.aol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.ShpImageDAO;
import com.gcs.aol.entity.SecondHandProductImage;
import com.gcs.aol.service.IShpImageService;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class ShpImageServiceImpl extends GenericManagerImpl<SecondHandProductImage, ShpImageDAO> implements IShpImageService{

	@SuppressWarnings("unused")
	@Autowired
	private ShpImageDAO dao;
}
