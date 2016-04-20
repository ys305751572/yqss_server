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

import com.gcs.aol.dao.MoneyMagUserDAO;
import com.gcs.aol.entity.MoneyMagUser;
import com.gcs.aol.service.IMoneyMagUserManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class MoneyMagUserManagerImpl extends GenericManagerImpl<MoneyMagUser, MoneyMagUserDAO> implements IMoneyMagUserManager{

	@Autowired
	private MoneyMagUserDAO dao;
	
	@Override
	public MoneyMagUser findByUserId(Integer userId) {
		List<MoneyMagUser> list = this.queryByProperty("user.userId", userId);
		return list != null && !list.isEmpty() ? list.get(0) : null;
	}

	/**
	 * 
     * @Title: isCertification
     * @Description: 是否认证
     * @param @param userId
     * @param @return    参数
     * @return Boolean    返回类型
     * @throws
	 */
	@Override
	public Boolean isCertification(Integer userId) {
		
		MoneyMagUser user = findByUserId(userId);
		if(user != null) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean isCorrectPwd(Integer userId, String password) {
		MoneyMagUser user = findByUserId(userId);
		if(user != null && StringUtils.isNotBlank(user.getTradingPassword())) {
			if(password.equals(user.getTradingPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Page<MoneyMagUser> list(final MoneyMagUser user, int currentPage, int pagesize) {
		Specification<MoneyMagUser> spec = new Specification<MoneyMagUser>() {
			@Override
			public Predicate toPredicate(Root<MoneyMagUser> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(user.getUser().getMobile() != null) {
					list.add(cb.like(root.get("user").get("mobile").as(String.class), "%" + user.getUser().getMobile() + "%"));
				}
				if(user.getUser().getName() != null) {
					list.add(cb.like(root.get("user").get("name").as(String.class), "%" + user.getUser().getName() + "%"));
				}
				return cb.and(list.toArray(new Predicate[list.size()]));
			}
		};
		return dao.findAll(spec, new PageRequest(currentPage, pagesize, Sort.Direction.DESC, "id"));
	}
}
