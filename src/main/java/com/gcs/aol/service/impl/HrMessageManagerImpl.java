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

import com.gcs.aol.dao.HrMessageDAO;
import com.gcs.aol.entity.HrMessage;
import com.gcs.aol.service.IHrMessageManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class HrMessageManagerImpl extends GenericManagerImpl<HrMessage, HrMessageDAO> implements IHrMessageManager{

	@Autowired
	private HrMessageDAO dao;
	
	@Override
	public Page<HrMessage> findAll(HrMessage hr, Integer currentPage, Integer pageSize) {
		Specification<HrMessage> spec = buildSpecification(hr);
		return dao.findAll(spec, new PageRequest(currentPage, pageSize, Sort.Direction.DESC));
	}
	
	@Override
	public Specification<HrMessage> buildSpecification(final HrMessage msg) {
		return new Specification<HrMessage>() {
			@Override
			public Predicate toPredicate(Root<HrMessage> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				if(msg.getTitle() != null) {
					list.add(cb.like(root.get("title").as(String.class),"%" + msg.getTitle() + "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
}
