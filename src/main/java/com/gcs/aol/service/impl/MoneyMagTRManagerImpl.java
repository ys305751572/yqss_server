package com.gcs.aol.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gcs.aol.dao.MoneyMagTRDAO;
import com.gcs.aol.entity.MoneyMagTR;
import com.gcs.aol.service.IMoneyMagTRManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class MoneyMagTRManagerImpl extends GenericManagerImpl<MoneyMagTR, MoneyMagTRDAO> implements IMoneyMagTRManager{

	public MoneyMagTRDAO dao;
	
	@Override
	public List<MoneyMagTR> findByDodId(Integer dodId) {
		return this.queryByProperty("dod.id", dodId);
	}

	@Override
	public Double countMoneyByDodId(Integer dodId) {
		List<MoneyMagTR> list = findByDodId(dodId);
		double totalMoney = 0.0;

		if(list != null && !list.isEmpty()) {
			for (MoneyMagTR moneyMagTR : list) {
				totalMoney += moneyMagTR.getMoney();
			}
		}
		return totalMoney;
	}

}
