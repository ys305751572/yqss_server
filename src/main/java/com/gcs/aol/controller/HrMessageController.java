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
import com.gcs.aol.entity.HrMessage;
import com.gcs.aol.service.IHrMessageManager;
import com.gcs.aol.service.impl.HrMessageManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/hr/")
public class HrMessageController extends GenericEntityController<HrMessage, HrMessage, HrMessageManagerImpl>{

	public final static String HR_LIST_PAGE = "management/aol/hrMgr/hr-list";
	public final static String HR_DETAIL_PAGE = "management/aol/hrMgr/hr-detail";
	public final static String HR_EDIT_PAGE = "management/aol/hrMgr/hr-edit";
	
	@Autowired
	private IHrMessageManager manager;
	
	@RequestMapping(value="listPage", method = RequestMethod.GET)
	public String listPage() {
		return HR_LIST_PAGE;
	}
	
	@RequestMapping(value="editPage", method = RequestMethod.GET)
	public String editPage(@RequestParam(value="id", required = false) Integer id,Model model) {
		
		if(id != null) {
			HrMessage hr = manager.queryByPK(id);
			model.addAttribute("hr", hr);
		}
		return HR_EDIT_PAGE;
	}
	
	@RequestMapping(value="detailPage", method = RequestMethod.GET)
	public String detailPage(Integer id,Model model) {
		
		HrMessage hr = manager.queryByPK(id);
		model.addAttribute("hr", hr);
		return HR_DETAIL_PAGE;
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		HrMessage hr = new HrMessage();
		hr.setTitle(paramMap.get("title"));
		
		Page<HrMessage> page = manager.findAll(hr,pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value="isList", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn isList(HrMessage hr) {
		
		HrMessage _hr = manager.queryByPK(hr.getId());
		_hr.setIsList(hr.getIsList());
		
		manager.save(_hr);
		return new MsgJsonReturn(true, hr.getIsList() == 1 ? "发布成功": "下架成功");
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	public String edit(HrMessage hr) {
		
		manager.save(hr);
		return HR_LIST_PAGE;
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn delete(Integer id) {
		
		HrMessage _hr = manager.queryByPK(id);
		manager.delete(_hr);
		return new MsgJsonReturn(true, "删除成功");
	}
}
