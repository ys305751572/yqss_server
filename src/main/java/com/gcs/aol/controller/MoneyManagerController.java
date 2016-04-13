package com.gcs.aol.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.MoneyMag;
import com.gcs.aol.service.IMoneyMagManager;
import com.gcs.aol.service.impl.MoneyMagManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping(value = "/management/moneymgr")
public class MoneyManagerController extends GenericEntityController<MoneyMag, MoneyMag, MoneyMagManagerImpl>{
	
	@Autowired
	private IMoneyMagManager moneyMagManager;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "management/aol/moneymgr/list";
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public JSONResponse list(@RequestBody JSONParam[] params) {
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		Page<MoneyMag> page = moneyMagManager.list(pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value = "/add")
	public String add(Integer id,Model model) {
		
		if(id != null) {
			model.addAttribute("msg", moneyMagManager.queryByPK(id));
		}
		return "management/aol/moneymgr/add";
	}
	
	@RequestMapping(value = "/save")
	public String save(MoneyMag msg) {
		
		if(msg.getId() != null) {
			MoneyMag _msg = moneyMagManager.queryByPK(msg.getId());
			_msg.setIntroduce(msg.getIntroduce());
			_msg.setName(msg.getName());
			_msg.setTimelimit(msg.getTimelimit());
			_msg.setYearYield(msg.getYearYield());
			moneyMagManager.save(_msg);
		}
		else {
			moneyMagManager.save(msg);
		}
		return "management/aol/moneymgr/list";
	}
}
