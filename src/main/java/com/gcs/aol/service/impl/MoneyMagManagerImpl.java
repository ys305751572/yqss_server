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
import com.gcs.aol.dao.MoneyMagDAO;
import com.gcs.aol.entity.MoneyMag;
import com.gcs.aol.service.IMoneyMagManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class MoneyMagManagerImpl extends GenericManagerImpl<MoneyMag, MoneyMagDAO> implements IMoneyMagManager{

	@Autowired
	private MoneyMagDAO dao;

	@Override
	public Page<MoneyMag> list(Integer currentPage, Integer pagesize) {
		Specification<MoneyMag> spec = new Specification<MoneyMag>() {

			@Override
			public Predicate toPredicate(Root<MoneyMag> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
		return dao.findAll(spec, new PageRequest(currentPage, pagesize, Sort.Direction.DESC, "id"));
	}

	@Override
	public MoneyMag findByUserId(Integer userId) {
		List<MoneyMag> list = this.queryByProperty("user.id", userId);
		return list != null && !list.isEmpty() ? list.get(0) : null;
	}
}
