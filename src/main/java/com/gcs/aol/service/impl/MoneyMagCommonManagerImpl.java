package com.gcs.aol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.MoneyMagCommonDAO;
import com.gcs.aol.entity.MoneyMagCommon;
import com.gcs.aol.service.IMoneyMagCommonManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class MoneyMagCommonManagerImpl extends GenericManagerImpl<MoneyMagCommon, MoneyMagCommonDAO> implements IMoneyMagCommonManager{

	@Autowired
	private MoneyMagCommonDAO dao;

	@Override
	public MoneyMagCommon findByType(Integer type) {
		List<MoneyMagCommon> list = this.queryByProperty("type", type);
		return list != null && !list.isEmpty() ? list.get(0) : null;
	}
}
