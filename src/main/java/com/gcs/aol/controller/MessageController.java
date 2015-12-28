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

import com.gcs.aol.entity.Message;
import com.gcs.aol.service.IMessageManager;
import com.gcs.aol.service.impl.MessageManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@RequestMapping("/management/msg/")
@Controller
public class MessageController extends GenericEntityController<Message, Message, MessageManagerImpl>{

	public final static String MSG_LIST_PAGE = "management/aol/msgMgr/msg-list";
	public final static String MSG_DETAIL_PAGE = "management/aol/msgMgr/msg-detail";
	
	@Autowired
	private IMessageManager manager;
	
	@RequestMapping(value="listPage", method = RequestMethod.POST)
	public String listPage() {
		return MSG_LIST_PAGE;
	}
	
	@RequestMapping(value="detailPage", method = RequestMethod.POST)
	public String detailPage(Integer id,Model model) {
		
		Message msg = manager.queryByPK(id);
		model.addAttribute("msg", msg);
		return MSG_DETAIL_PAGE;
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		Message msg = new Message();
		msg.getUser().setUserId(paramMap.get("userId") != null ? Integer.parseInt(paramMap.get("userId")) : null);
		
		String regTimeQ = paramMap.get("regTimeQ");
		String regTimeZ = paramMap.get("regTimeZ");
		Page<Message> page = manager.findAll(msg,regTimeQ,regTimeZ,pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn delete(Integer id) {
		
		manager.deleteByPK(id);
		return new MsgJsonReturn(true, "删除成功");
	}
}
