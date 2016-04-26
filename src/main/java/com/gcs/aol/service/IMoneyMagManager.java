package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.MoneyMag;
import com.gcs.sysmgr.service.GenericManager;

import java.util.Map;

public interface IMoneyMagManager extends GenericManager<MoneyMag>{

	public Page<MoneyMag> list(Integer currentPage,Integer pagesize);

	public MoneyMag findByUserId(Integer userId);

	public Map<String,String> h5Index(Integer userId);
}
