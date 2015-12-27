package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.gcs.aol.dao.BorrowDAO;
import com.gcs.aol.entity.Borrow;
import com.gcs.aol.service.IBorrowManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

public class BorrowManagerImpl extends GenericManagerImpl<Borrow, BorrowDAO> implements IBorrowManager{

	@Override
	public Page<Borrow> findAll(Borrow borrow, Integer currentPage, Integer pageSize) {
		return null;
	}

	@Override
	public Specification<Borrow> buildSpecification(final Borrow borrow) {
		return new Specification<Borrow>() {
			@Override
			public Predicate toPredicate(Root<Borrow> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(borrow.getUserId() != null) {
					
				}
				return null;
			}
		};
	}
}
