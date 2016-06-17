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

import com.gcs.aol.dao.MoneyMagTRDAO;
import com.gcs.aol.entity.MoneyMagDod;
import com.gcs.aol.entity.MoneyMagTR;
import com.gcs.aol.entity.Users;
import com.gcs.aol.service.IMoneyMagTRManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class MoneyMagTRManagerImpl extends GenericManagerImpl<MoneyMagTR, MoneyMagTRDAO> implements IMoneyMagTRManager{

	@Autowired
	public MoneyMagTRDAO dao;
	
	@Override
	public List<MoneyMagTR> findByDodId(Integer dodId) {
		return this.queryByProperty("dod.id", dodId);
	}

	@Override
	public Double countMoneyByDodId(Integer dodId) {
		Double d = dao.haveBuy(dodId);
		return d == null ? 0 : d.doubleValue();
	}

	@Override
	public void createMoneyMagTR(Integer userId, Integer dodId, Double money) {
		MoneyMagTR tr = new MoneyMagTR();
		
		Users user = new Users();
		user.setId(userId);
		
		MoneyMagDod dod = new MoneyMagDod();
		dod.setId(dodId);
		
		tr.setUser(user);
		tr.setDod(dod);
		
		this.save(tr);
	}

	@Override
	public Page<MoneyMagTR> list(final String mobile,final String name,final Integer id, int currentPgae, int pagesize) {
		Specification<MoneyMagTR> spec = new Specification<MoneyMagTR>() {
			@Override
			public Predicate toPredicate(Root<MoneyMagTR> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(name)) {
					list.add(cb.like(root.get("user").get("name").as(String.class), "%" + name + "%"));
				}
				if(StringUtils.isNotBlank(mobile)) {
					list.add(cb.like(root.get("user").get("mobile").as(String.class), "%" + mobile + "%"));
				}
				if(id != null) {
					list.add(cb.equal(root.get("dod").get("id").as(Integer.class), id));
				}
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
		return dao.findAll(spec, new PageRequest(currentPgae, pagesize, Sort.Direction.DESC, "id"));
	}

	@Override
	public MoneyMagTR findBySn(String sn) {
		return dao.findBySn(sn);
	}

	@Override
	public void modifyTrStatus(String sn) {
		MoneyMagTR tr = findBySn(sn);
		if(tr.getStatus() != null || tr.getStatus() == 0) {
			dao.modifyTrStatus(sn);
		}
	}
}
