package com.gcs.aol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.BankDAO;
import com.gcs.aol.entity.Bank;
import com.gcs.aol.service.IBankManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class BankManagerImpl extends GenericManagerImpl<Bank, BankDAO> implements IBankManager{

	@SuppressWarnings("unused")
	@Autowired
	private BankDAO dao;
}
