package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.gcs.aol.entity.Borrow;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.BorrowRepayRecordDAO;
import com.gcs.aol.entity.BorrowRepayRecord;
import com.gcs.aol.service.IBorrowRepayRecordManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class BorrowRepayRecordManagerImpl extends GenericManagerImpl<BorrowRepayRecord, BorrowRepayRecordDAO> implements IBorrowRepayRecordManager{

	@Autowired
	private BorrowRepayRecordDAO dao;
	
	@Override
	public List<BorrowRepayRecord> findByBorrowInfoId(Integer id) {
		return this.queryByProperty("borrowInfoId", id);
	}

	@Override
	public Page<BorrowRepayRecord> findPage(final BorrowRepayRecord record, Integer currentPage, Integer pageSize) {
		Specification<BorrowRepayRecord> spec = new Specification<BorrowRepayRecord>() {

			@Override
			public Predicate toPredicate(Root<BorrowRepayRecord> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
//				if(borrowInfoId != null) {
//					list.add(cb.equal(root.get("borrowInfoId").as(Integer.class), borrowInfoId));
//				}
				if(StringUtils.isNotBlank(record.getBorrow().getUser().getName())) {
					list.add(cb.like(root.get("borrow").get("user").get("name").as(String.class),"%" + record.getBorrow().getUser().getName() + "%"));
				}
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
		return dao.findAll(spec, new PageRequest(currentPage, pageSize, Sort.Direction.DESC, "id"));
	}
}
