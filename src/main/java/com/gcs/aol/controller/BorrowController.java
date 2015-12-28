package com.gcs.aol.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.Borrow;
import com.gcs.aol.service.IBorrowManager;
import com.gcs.aol.service.impl.BorrowManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/borrow/")
public class BorrowController extends GenericEntityController<Borrow, Borrow, BorrowManagerImpl>{

	public final static String BORROW_LIST_PAGE = "managememnt/aol/borrowMgr/borrow-list";
	public final static String BORROW_DETAIL_PAGE = "management/aol/borrowMgr/borrow-detail";
	
	@Autowired
	private IBorrowManager manager;
	
	@RequestMapping(value="listPage", method = RequestMethod.GET)
	public String listPage() {
		return BORROW_LIST_PAGE;
	}
	
	@RequestMapping(value="detailPage", method = RequestMethod.GET)
	public String detailPage(Integer id,Model model) {
		
		Borrow b = manager.queryByPK(id);
		model.addAttribute("borrow",b);
		return BORROW_DETAIL_PAGE;
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		Borrow b = new Borrow();
		b.getUser().setUserId(paramMap.get("userId") != null ? Integer.parseInt(paramMap.get("userId")) : null);
		
		String regTimeQ = paramMap.get("regTimeQ");
		String regTimeZ = paramMap.get("regTimeZ");
		Page<Borrow> page = manager.findAll(b,regTimeQ,regTimeZ,pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value="agree", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn agree(Borrow borrow) {
	
		Borrow _b = manager.queryByPK(borrow.getId());
		_b.setIsList(borrow.getIsList());
		
		manager.save(_b);
		return new MsgJsonReturn(true, "操作成功");
	}
	
	@RequestMapping(value="refuse", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn refuse(Borrow borrow) {
	
		Borrow _b = manager.queryByPK(borrow.getId());
		_b.setIsList(borrow.getIsList());
		
		manager.save(_b);
		return new MsgJsonReturn(true, "操作成功");
	}
}
