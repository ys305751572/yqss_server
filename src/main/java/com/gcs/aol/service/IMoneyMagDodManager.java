package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.MoneyMagDod;
import com.gcs.sysmgr.service.GenericManager;

public interface IMoneyMagDodManager extends GenericManager<MoneyMagDod>{

	public MoneyMagDod findFixPeriodDetail();
	
	public List<MoneyMagDod> findDueOnDemandList();
	
	public MoneyMagDod findDueOnDemandDetail(Integer id);
	
}
