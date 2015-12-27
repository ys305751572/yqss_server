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

import com.gcs.aol.dao.AdsDAO;
import com.gcs.aol.entity.Ads;
import com.gcs.aol.service.IAdsManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class AdsManagerImpl extends GenericManagerImpl<Ads, AdsDAO> implements IAdsManager{

	@Autowired
	private AdsDAO dao;
	
	@Override
	public Page<Ads> findAll(Ads ads, Integer currentPage, Integer pageSize) {
		Specification<Ads> spec = buildSpecification(ads);
		return dao.findAll(spec, new PageRequest(currentPage, pageSize, Sort.Direction.DESC));
	}

	@Override
	public Specification<Ads> buildSpecification(final Ads ads) {
		return new Specification<Ads>() {
			@Override
			public Predicate toPredicate(Root<Ads> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(ads.getTitle() != null) {
					list.add(cb.like(root.get("title").as(String.class), "%" + ads.getTitle() + "%"));
				} 
				if(ads.getIsList() != null) {
					list.add(cb.equal(root.get("isList").as(Integer.class), ads.getIsList()));
				}
				if(ads.getType() != null) {
					list.add(cb.equal(root.get("type").as(Integer.class), ads.getType()));
				}
				
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
}
