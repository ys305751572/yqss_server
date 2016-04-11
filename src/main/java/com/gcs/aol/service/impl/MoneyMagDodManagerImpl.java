package com.gcs.aol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcs.aol.dao.MoneyMagDodDAO;
import com.gcs.aol.entity.MoneyMagDod;
import com.gcs.aol.service.IMoneyMagDodManager;
import com.gcs.aol.service.IMoneyMagTRManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.utils.NumberUtils;

public class MoneyMagDodManagerImpl extends GenericManagerImpl<MoneyMagDod, MoneyMagDodDAO> implements IMoneyMagDodManager{

	@Autowired
	private MoneyMagDodDAO dao;
	
	@Autowired
	private IMoneyMagTRManager manager;
	
	@Override
	public MoneyMagDod findFixPeriodDetail() {
		MoneyMagDod entity = this.queryByProperty("type", 2).get(0);
		// 根据理财产品ID查询已转入金额总和
		Double totalMoney = manager.countMoneyByDodId(entity.getId());
		entity.setResidue(NumberUtils.format(entity.getMoneyLimit() - totalMoney));
		entity.setPercent(NumberUtils.format(totalMoney / entity.getMoneyLimit() * 100));
		return entity;
	}

	@Override
	public List<MoneyMagDod> findDueOnDemandList() {
		List<MoneyMagDod> list = this.queryByProperty("type", 1);
		
		for (MoneyMagDod moneyMagDod : list) {
			Double totalMoney = manager.countMoneyByDodId(moneyMagDod.getId());
			moneyMagDod.setResidue(NumberUtils.format(moneyMagDod.getMoneyLimit() - totalMoney));
			moneyMagDod.setPercent(NumberUtils.format(totalMoney / moneyMagDod.getMoneyLimit() * 100));
		}
		return list;
	}

	@Override
	public MoneyMagDod findDueOnDemandDetail(Integer id) {
		MoneyMagDod entity = dao.findOne(id);
		// 根据理财产品ID查询已转入金额总和
		Double totalMoney = manager.countMoneyByDodId(entity.getId());
		entity.setResidue(NumberUtils.format(entity.getMoneyLimit() - totalMoney));
		entity.setPercent(NumberUtils.format(totalMoney / entity.getMoneyLimit() * 100));
		return entity;
	}
}
