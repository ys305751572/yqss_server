package com.gcs.aol.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.MoneyMagUser;
import com.gcs.aol.entity.Users;
import com.gcs.aol.service.IMoneyMagUserManager;
import com.gcs.aol.service.impl.MoneyMagUserManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping(value = "/management/muser")
public class MoneyMagUserController extends GenericEntityController<MoneyMagUser, MoneyMagUser, MoneyMagUserManagerImpl>{

	@Autowired
	private IMoneyMagUserManager moneyMagUserManager;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "management/aol/muserMgr/list";
	}
	
	@RequestMapping(value="list", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		MoneyMagUser muser = new MoneyMagUser();
		
		Users users = new Users();
		if(StringUtils.isNotBlank(paramMap.get("mobile"))) {
			users.setMobile(paramMap.get("mobile"));
		}
		if(StringUtils.isNotBlank(paramMap.get("name"))) {
			users.setName(paramMap.get("name"));
		}
		muser.setUser(users);
		
		Page<MoneyMagUser> page = moneyMagUserManager.list(muser, pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
}
