package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.Borrow;
import org.springframework.data.domain.Page;

import com.gcs.aol.entity.BorrowRepayRecord;
import com.gcs.sysmgr.service.GenericManager;

public interface IBorrowRepayRecordManager extends GenericManager<BorrowRepayRecord>{

	public List<BorrowRepayRecord> findByBorrowInfoId(Integer borrowInfoId);
	
	public Page<BorrowRepayRecord> findPage(BorrowRepayRecord record, Integer currentPage, Integer pageSize);
}
