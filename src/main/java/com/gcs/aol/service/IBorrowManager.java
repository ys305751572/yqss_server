package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.Borrow;
import com.gcs.sysmgr.service.GenericManager;

public interface IBorrowManager extends GenericManager<Borrow>{

	public Page<Borrow> findAll(Borrow borrow,String regTimeQ,String regTimeZ,Integer currentPage,Integer pageSize);
	
	public Integer count(Integer userId) ; 
}
