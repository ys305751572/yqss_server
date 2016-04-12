package com.gcs.aol.service;

import com.gcs.aol.entity.MoneyMagUser;
import com.gcs.sysmgr.service.GenericManager;

public interface IMoneyMagUserManager extends GenericManager<MoneyMagUser>{
	
	public MoneyMagUser findByUserId(Integer userId);
	
	public Boolean isCertification(Integer userId);
	
	public Boolean isCorrectPwd(Integer userId,String password);
	
}
