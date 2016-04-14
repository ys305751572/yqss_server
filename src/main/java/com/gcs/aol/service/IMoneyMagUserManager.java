package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.MoneyMagUser;
import com.gcs.sysmgr.service.GenericManager;

public interface IMoneyMagUserManager extends GenericManager<MoneyMagUser>{
	
	public MoneyMagUser findByUserId(Integer userId);
	
	public Boolean isCertification(Integer userId);
	
	public Boolean isCorrectPwd(Integer userId,String password);
	
	public Page<MoneyMagUser> list(MoneyMagUser user,int currentPage,int pagesize);
}
