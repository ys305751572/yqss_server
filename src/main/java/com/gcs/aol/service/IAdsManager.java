package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.Ads;
import com.gcs.sysmgr.service.GenericManager;

public interface IAdsManager extends GenericManager<Ads>{

	public Page<Ads> findAll(Ads ads,Integer currentPage,Integer pageSize);
}
