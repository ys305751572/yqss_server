package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.MoneyMag;
import com.gcs.sysmgr.service.GenericManager;

public interface IMoneyMagManager extends GenericManager<MoneyMag>{

	public Page<MoneyMag> list(Integer currentPage,Integer pagesize);
}
