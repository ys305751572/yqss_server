package com.gcs.aol.service;

import com.gcs.aol.entity.MoneyMagCommon;
import com.gcs.sysmgr.service.GenericManager;

public interface IMoneyMagCommonManager extends GenericManager<MoneyMagCommon>{

	public MoneyMagCommon findByType(Integer type);
}
