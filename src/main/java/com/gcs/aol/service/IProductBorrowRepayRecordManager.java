package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.ProductBorrow;
import org.springframework.data.domain.Page;
import com.gcs.aol.entity.ProductBorrowRepayRecord;
import com.gcs.sysmgr.service.GenericManager;

public interface IProductBorrowRepayRecordManager extends GenericManager<ProductBorrowRepayRecord>{

	public List<ProductBorrowRepayRecord> findByBorrowInfoId(Integer borrowInfoId);
	
	public Page<ProductBorrowRepayRecord> findPage(ProductBorrowRepayRecord record, Integer currentPage, Integer pageSize);
}
