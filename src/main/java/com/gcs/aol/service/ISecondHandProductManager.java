package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.SecondHandProduct;
import com.gcs.sysmgr.service.GenericManager;

public interface ISecondHandProductManager extends GenericManager<SecondHandProduct>{

	public Page<SecondHandProduct> findAll(SecondHandProduct shp,Integer currentPage,Integer pageSize);
}
