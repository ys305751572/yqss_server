package com.gcs.aol.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.MoneyMagUserDAO;
import com.gcs.aol.entity.MoneyMagUser;
import com.gcs.aol.service.IMoneyMagUserManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class MoneyMagUserManagerImpl extends GenericManagerImpl<MoneyMagUser, MoneyMagUserDAO> implements IMoneyMagUserManager{

	
	private MoneyMagUserDAO dao;
	
	@Override
	public MoneyMagUser findByUserId(Integer userId) {
		List<MoneyMagUser> list = this.queryByProperty("user.id", userId);
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
}
