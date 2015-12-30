package com.gcs.aol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gcs.aol.entity.Commons;
import com.gcs.aol.service.ICommonsManager;
import com.gcs.aol.service.impl.CommonsManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;

@RequestMapping("/management/commons")
@Controller
public class CommonsController extends GenericEntityController<Commons, Commons, CommonsManagerImpl>{

	public static final String COMMONS_EDIT_PAGE = "management/aol/commonMgr/common-edit";
	public static final String COMMONS_DETAIL_PAGE = "management/aol/commonMgr/common-detail";
	
	@Autowired
	private ICommonsManager manager;
	
	@RequestMapping(value="editPage", method = RequestMethod.GET)
	public String editPage(Model model) {
		
		Commons common = manager.queryAll().get(0);
		model.addAttribute("common", common);
		return COMMONS_EDIT_PAGE;
	}
	
	@RequestMapping(value="detailPage", method = RequestMethod.GET)
	public String detailPage(Model model) {
		
		Commons common = manager.queryAll().get(0);
		model.addAttribute("common", common);
		return COMMONS_DETAIL_PAGE;
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	public String edit(Commons commons,Model model) {
		
		manager.save(commons);
		model.addAttribute("common", commons);
		return COMMONS_DETAIL_PAGE;
	}
}
