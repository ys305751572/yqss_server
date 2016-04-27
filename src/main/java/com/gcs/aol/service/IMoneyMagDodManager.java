package com.gcs.aol.service;

import java.util.List;
import java.util.Map;

import com.gcs.utils.Result;
import org.springframework.data.domain.Page;

import com.gcs.aol.entity.MoneyMagDod;
import com.gcs.sysmgr.service.GenericManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IMoneyMagDodManager extends GenericManager<MoneyMagDod>{

	public MoneyMagDod findDueOnDemandDetail();
	
	public List<MoneyMagDod> findFixPeriodList();
	
	public MoneyMagDod findFixPeriodDetail(Integer id);
	
	public Page<MoneyMagDod> list(MoneyMagDod dod,int currentPage,int pagesize);
	
	public String saveDod(MoneyMagDod dod);

	/**
	 *
	 * @param dodId 理财产品ID
	 * @param type 支付类型 0:支付宝 1.微信
     * @return
     */
	public Map<String,Object> payConfig(HttpServletRequest request, HttpServletResponse response, Integer dodId, Integer type);

}
