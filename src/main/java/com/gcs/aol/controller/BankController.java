package com.gcs.aol.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.Bank;
import com.gcs.aol.service.IBankManager;
import com.gcs.aol.service.impl.BankManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@RequestMapping("/management/bank/")
public class BankController extends GenericEntityController<Bank, Bank,BankManagerImpl>{

	public static final String BANK_LIST_PAGE = "management/aol/bankMgr/bank-list";
	public static final String BANK_EDIT_PAGE = "management/aol/bankMgr/bank-edit";
	
	@Autowired
	private IBankManager manager;
	
	@RequestMapping(value="listPage", method = RequestMethod.GET)
	public String listPage() {
		return BANK_LIST_PAGE;
	}
	
	public String editPage(@RequestParam(value="id", required=false) Integer id,Model model) {
		
		if(id != null) {
			Bank bank = manager.queryByPK(id);
			model.addAttribute("bank", bank);
		}
		return BANK_EDIT_PAGE;
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.POST)
	public JSONResponse findAll(@RequestParam JSONParam[] params) {
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		Page<Bank> page = manager.query(null, pp);
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn edit(Bank bank) {
		
		manager.save(bank);
		return new MsgJsonReturn(true, bank.getId() == null ? "新增成功": "编辑成功");
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn delete(Integer id) {
		
		manager.deleteByPK(id);
		return new MsgJsonReturn(true, "删除成功");
	}
}
