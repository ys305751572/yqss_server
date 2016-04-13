package com.gcs.aol.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.MoneyMagTR;
import com.gcs.aol.service.IMoneyMagTRManager;
import com.gcs.aol.service.impl.MoneyMagTRManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@RequestMapping(value = "/management/tr")
@Controller
public class MoneyMagTRController extends GenericEntityController<MoneyMagTR, MoneyMagTR, MoneyMagTRManagerImpl>{

	@Autowired
	private IMoneyMagTRManager moneyMagTRManager;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "management/aol/trMgr/list";
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public JSONResponse list(@RequestBody JSONParam[] params) {
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		Integer id = null;
		if(paramMap.get("id") != null) {
			id = Integer.parseInt(paramMap.get("id"));
		}
		Page<MoneyMagTR> page = moneyMagTRManager.list(paramMap.get("mobile"),paramMap.get("name"), id ,pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
}
