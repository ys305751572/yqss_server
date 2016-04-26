package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.gcs.aol.dao.MoneyMagTRDAO;
import com.gcs.aol.entity.MoneyMagDod;
import com.gcs.aol.entity.MoneyMagTR;
import com.gcs.aol.utils.DateUtil;
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

	@Autowired
	private MoneyMagTRDAO trDao;

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

	@Override
	public Map<String, String> h5Index(Integer userId) {

		List<MoneyMagTR> list = trDao.findByUserId(userId);
		double totalMoney = 0.0;
		double totalEarnings = 0.0;

		try {
			for (MoneyMagTR tr : list) {
				MoneyMagDod dod = tr.getDod();
				totalMoney += tr.getMoney();
				totalEarnings += earnings(dod,tr.getCreateDate());
			}
		} catch (Exception e) {

		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("totalMoney",String.valueOf(totalMoney));
		map.put("totalEarnings",String.valueOf(totalEarnings));
		return map;
	}

	public double earnings(MoneyMagDod dod,Long createDate) throws Exception {
		double yearYield = dod.getYearYield();
		double days = com.gcs.utils.DateUtil.getDistanceDays(System.currentTimeMillis(),createDate);
		return yearYield / 100.0 * days;
	}
}
