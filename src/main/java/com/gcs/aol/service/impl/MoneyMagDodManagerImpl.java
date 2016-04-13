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

import com.gcs.aol.dao.MoneyMagDodDAO;
import com.gcs.aol.entity.MoneyMagDod;
import com.gcs.aol.service.IMoneyMagDodManager;
import com.gcs.aol.service.IMoneyMagTRManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.utils.NumberUtils;

@Service
public class MoneyMagDodManagerImpl extends GenericManagerImpl<MoneyMagDod, MoneyMagDodDAO> implements IMoneyMagDodManager{

	@Autowired
	private MoneyMagDodDAO dao;
	
	@Autowired
	private IMoneyMagTRManager manager;
	
	@Override
	public MoneyMagDod findDueOnDemandDetail() {
		MoneyMagDod entity = this.queryByProperty("type", 1).get(0);
		// 根据理财产品ID查询已转入金额总和
		Double totalMoney = manager.countMoneyByDodId(entity.getId());
		entity.setResidue(NumberUtils.format(entity.getMoneyLimit() - totalMoney));
		entity.setPercent(NumberUtils.format(totalMoney / entity.getMoneyLimit() * 100));
		return entity;
	}

	@Override
	public List<MoneyMagDod> findFixPeriodList() {
		List<MoneyMagDod> list = this.queryByProperty("type", 2);
		
		for (MoneyMagDod moneyMagDod : list) {
			Double totalMoney = manager.countMoneyByDodId(moneyMagDod.getId());
			moneyMagDod.setResidue(NumberUtils.format(moneyMagDod.getMoneyLimit() - totalMoney));
			moneyMagDod.setPercent(NumberUtils.format(totalMoney / moneyMagDod.getMoneyLimit() * 100));
		}
		return list;
	}

	@Override
	public MoneyMagDod findFixPeriodDetail(Integer id) {
		MoneyMagDod entity = dao.findOne(id);
		// 根据理财产品ID查询已转入金额总和
		Double totalMoney = manager.countMoneyByDodId(entity.getId());
		entity.setResidue(NumberUtils.format(entity.getMoneyLimit() - totalMoney));
		entity.setPercent(NumberUtils.format(totalMoney / entity.getMoneyLimit() * 100));
		return entity;
	}

	@Override
	public Page<MoneyMagDod> list(final MoneyMagDod dod, int currentPage, int pagesize) {
		Specification<MoneyMagDod> spec = new Specification<MoneyMagDod>() {

			@Override
			public Predicate toPredicate(Root<MoneyMagDod> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(dod.getType() != null) {
					list.add(cb.equal(root.get("type").as(Integer.class), dod.getType()));
				}
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
		return dao.findAll(spec, new PageRequest(currentPage, pagesize, Sort.Direction.DESC,"id"));
	}

	@Override
	public String saveDod(MoneyMagDod dod) {
		if(dod.getId() != null) {
			//编辑
			MoneyMagDod _dod = this.queryByProperty("type", 1).get(0);
			if(dod.getId() == _dod.getId() && dod.getType() == 2) {
				return "ERROR";
			}
			if(dod.getId() != _dod.getId() && dod.getType() == 1) {
				return "ERROR";
			}
			MoneyMagDod _d = this.queryByPK(dod.getId());
			_d.setMoneyLimit(dod.getMoneyLimit());
			_d.setMonth(dod.getMonth());
			_d.setPercent(dod.getPercent());
			_d.setResidue(dod.getResidue());
			_d.setType(dod.getType());
			_d.setYearYield(dod.getYearYield());
			this.save(_d);
		}
		else {
			// 新增
			if(dod.getType() == 1) {
				if(this.queryByProperty("type", 1).get(0) != null) {
					return "ERROR";
				}
				else {
					this.save(dod);
				}
			}
		}
		return "SUCCESS";
	}
}
