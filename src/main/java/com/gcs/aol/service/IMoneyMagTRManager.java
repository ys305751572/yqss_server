package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.MoneyMagTR;
import com.gcs.sysmgr.service.GenericManager;

public interface IMoneyMagTRManager extends GenericManager<MoneyMagTR>{

	public List<MoneyMagTR> findByDodId(Integer dodId);
	
	public Double countMoneyByDodId(Integer dodId);
}
