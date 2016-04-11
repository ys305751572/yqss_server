package com.gcs.aol.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.gcs.aol.entity.MoneyMag;
import com.gcs.aol.service.impl.MoneyMagManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;

@RequestMapping(value = "/moneymag")
public class MoneyMagController extends GenericEntityController<MoneyMag, MoneyMag, MoneyMagManagerImpl>{

	@RequestMapping(value = "moneymag/index")
	public String moneymagIndex() {
		return null;
	}
	
	
}
