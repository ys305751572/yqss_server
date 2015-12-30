package com.gcs.aol.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.Helper;
import com.gcs.aol.service.IHelperManager;
import com.gcs.aol.service.impl.HelperManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/helper/")
public class HelperController extends GenericEntityController<Helper, Helper, HelperManagerImpl>{

	public static final String HELP_LIST_PAGE = "management/aol/helperMgr/helper-list";
	public static final String HELP_DETAIL_PAGE = "management/aol/helperMgr/helper-detail";
	public static final String HELP_EDIT_PAGE = "management/aol/helperMgr/helper-edit";
	
	@Autowired
	private IHelperManager manager;
	
	@RequestMapping(value="listPage", method = RequestMethod.GET)
	public String listPage() {
		return HELP_LIST_PAGE;
	}
	
	@RequestMapping(value="editPage", method = RequestMethod.GET)
	public String editPage(@RequestParam(value="id",required = false) Integer id,Model model) {
		
		if(id != null) {
			Helper h = manager.queryByPK(id);
			model.addAttribute("helper", h);
		}
		return HELP_EDIT_PAGE;
	}
	
	@RequestMapping(value="detailPage", method = RequestMethod.GET)
	public String detailPage(Integer id,Model model) {
		
		Helper h = manager.queryByPK(id);
		model.addAttribute("helper", h);
		return HELP_DETAIL_PAGE;
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		Page<Helper> page = manager.query(null, pp);
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn delete(Integer id) {
		
		Helper h = manager.queryByPK(id);
		manager.delete(h);
		return new MsgJsonReturn(true, "删除成功");
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	public String edit(Helper helper) {
		manager.save(helper);
		return HELP_LIST_PAGE;
	}
}
