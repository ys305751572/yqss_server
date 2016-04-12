package com.gcs.aol.service.impl;

import org.springframework.stereotype.Service;
import com.gcs.aol.dao.MoneyMagDAO;
import com.gcs.aol.entity.MoneyMag;
import com.gcs.aol.service.IMoneyMagManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class MoneyMagManagerImpl extends GenericManagerImpl<MoneyMag, MoneyMagDAO> implements IMoneyMagManager{

	private MoneyMagDAO dao;
}
