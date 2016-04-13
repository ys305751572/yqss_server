package com.gcs.aol.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.MoneyMagDod;
import com.gcs.aol.service.IMoneyMagDodManager;
import com.gcs.aol.service.impl.MoneyMagDodManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping(value = "/management/dod")
public class MoneyMagDodController extends GenericEntityController<MoneyMagDod, MoneyMagDod, MoneyMagDodManagerImpl>{

	@Autowired
	private IMoneyMagDodManager moneyMagDodManager;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "management/aol/dodMgr/list";
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public JSONResponse list(@RequestBody JSONParam[] params) {
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		MoneyMagDod dod = new MoneyMagDod();
		if(StringUtils.isNotBlank(paramMap.get("type"))) {
			dod.setType(Integer.parseInt(paramMap.get("type")));
		}
		Page<MoneyMagDod> page = moneyMagDodManager.list(dod,pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value = "/add")
	public String add(Integer id,Model model) {
		if(id != null) {
			MoneyMagDod dod = moneyMagDodManager.queryByPK(id);
			model.addAttribute("dod", dod);
		}
		return "management/aol/dodMgr/add";
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public MsgJsonReturn save(MoneyMagDod dod) {
		String result = moneyMagDodManager.saveDod(dod);
		if("ERROR".equals(result)) {
			return new MsgJsonReturn(false, "请至少保留一条活期宝数据");
			
		}
		return new MsgJsonReturn(true, "保存成功");
	}
}
