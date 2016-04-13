package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.ProductBorrowRepayRecordDAO;
import com.gcs.aol.entity.ProductBorrowRepayRecord;
import com.gcs.aol.service.IProductBorrowRepayRecordManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class ProductBorrowRepayRecordManagerImpl extends GenericManagerImpl<ProductBorrowRepayRecord, ProductBorrowRepayRecordDAO> implements IProductBorrowRepayRecordManager{

	@Autowired
	private ProductBorrowRepayRecordDAO dao;
	
	@Override
	public List<ProductBorrowRepayRecord> findByBorrowInfoId(Integer borrowInfoId) {
		return this.queryByProperty("borrowInfoId", borrowInfoId);
	}

	@Override
	public Page<ProductBorrowRepayRecord> findPage(final Integer borrowInfoId, Integer currentPage, Integer pageSize) {
		Specification<ProductBorrowRepayRecord> spec = new Specification<ProductBorrowRepayRecord>() {

			@Override
			public Predicate toPredicate(Root<ProductBorrowRepayRecord> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(borrowInfoId != null) {
					list.add(cb.equal(root.get("borrow_info_id").as(Integer.class), borrowInfoId));
				}
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
		return dao.findAll(spec, new PageRequest(currentPage, pageSize, Sort.Direction.DESC, "id"));
	}

}
