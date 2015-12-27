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

import com.gcs.aol.dao.SecondHandProductDAO;
import com.gcs.aol.entity.SecondHandProduct;
import com.gcs.aol.service.ISecondHandProductManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class SecondHandProductManagerImpl extends GenericManagerImpl<SecondHandProduct, SecondHandProductDAO> implements ISecondHandProductManager{

	@Autowired
	SecondHandProductDAO dao;
	
	@Override
	public Page<SecondHandProduct> findAll(SecondHandProduct shp, Integer currentPage, Integer pageSize) {
		Specification<SecondHandProduct> spec = buildSpecification(shp);
		return dao.findAll(spec, new PageRequest(currentPage, pageSize, Sort.Direction.DESC));
	}

	@Override
	public Specification<SecondHandProduct> buildSpecification(final SecondHandProduct shp) {
		return new Specification<SecondHandProduct>() {
			@Override
			public Predicate toPredicate(Root<SecondHandProduct> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(shp.getTitle() != null) {
					list.add(cb.like(root.get("root").as(String.class), "%" + shp.getTitle() + "%"));
				}
				
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
}
