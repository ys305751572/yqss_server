package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.BorrowDAO;
import com.gcs.aol.entity.Borrow;
import com.gcs.aol.service.IBorrowManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.utils.DateUtils;

@Service
public class BorrowManagerImpl extends GenericManagerImpl<Borrow, BorrowDAO> implements IBorrowManager{

	@Autowired
	private BorrowDAO dao;
	
	private Long regTimeQ;
	
	private Long regTimeZ;
	
	@Override
	public Page<Borrow> findAll(Borrow borrow,String regTimeQ,String regTimeZ, Integer currentPage, Integer pageSize) {
		this.regTimeQ = StringUtils.isNotBlank(regTimeQ) ? DateUtils.stringToLong(regTimeQ + " 00:00:00", "yyyy-MM-dd HH:mm:ss") : null;
		this.regTimeZ = StringUtils.isNotBlank(regTimeZ) ? DateUtils.stringToLong(regTimeZ + " 00:00:00", "yyyy-MM-dd HH:mm:ss") : null;
		Specification<Borrow> spec = buildSpecification(borrow);
		return dao.findAll(spec, new PageRequest(currentPage, pageSize, Sort.Direction.DESC,"id"));
	}

	@Override
	public Specification<Borrow> buildSpecification(final Borrow borrow) {
		return new Specification<Borrow>() {
			@Override
			public Predicate toPredicate(Root<Borrow> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(borrow.getUser().getUserId() != null) {
					list.add(cb.equal(root.get("userId").as(Integer.class), borrow.getUser().getUserId()));
				}
				
				if(borrow.getIsList() != null) {
					list.add(cb.equal(root.get("isList").as(Integer.class), borrow.getIsList()));
				}
				if(StringUtils.isNotBlank(borrow.getSchoolName())) {
					list.add(cb.like(root.get("schoolName").as(String.class),"%" + borrow.getSchoolName() + "%"));
				}

				if(StringUtils.isNotBlank(borrow.getUser().getName())) {
					list.add(cb.like(root.get("user").get("name").as(String.class),"%" + borrow.getUser().getName() + "%"));
				}
				if(StringUtils.isNotBlank(borrow.getUsername())) {
					list.add(cb.like(root.get("username").as(String.class),"%" + borrow.getUsername()+ "%"));
				}
				if(StringUtils.isNotBlank(borrow.getUser().getMobile())) {
					list.add(cb.like(root.get("user").get("mobile").as(String.class),"%" + borrow.getUser().getMobile() + "%"));
				}
				if(StringUtils.isNotBlank(borrow.getPhone())) {
					list.add(cb.like(root.get("phone").as(String.class),"%" + borrow.getPhone() + "%"));
				}
				if(regTimeQ != null ) {
					list.add(cb.gt(root.get("createDate").as(Long.class),regTimeQ));
				}
				if(regTimeZ != null) {
					list.add(cb.le(root.get("createDate").as(Long.class), regTimeZ));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}

	@Override
	public Integer count(Integer userId) {
		
		
		return null;
	}
}
