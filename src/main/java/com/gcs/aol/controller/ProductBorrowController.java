package com.gcs.aol.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.constant.Constant;
import com.gcs.aol.entity.BorrowRepayRecord;
import com.gcs.aol.entity.Message;
import com.gcs.aol.entity.ProductBorrow;
import com.gcs.aol.entity.ProductBorrowRepayRecord;
import com.gcs.aol.entity.Users;
import com.gcs.aol.service.IMessageManager;
import com.gcs.aol.service.IProductBorrowManger;
import com.gcs.aol.service.IProductBorrowRepayRecordManager;
import com.gcs.aol.service.IUsersManager;
import com.gcs.aol.service.impl.ProductBorrowManagerImpl;
import com.gcs.aol.utils.YqssUtils;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@RequestMapping("/management/pb/")
@Controller
public class ProductBorrowController extends GenericEntityController<ProductBorrow, ProductBorrow, ProductBorrowManagerImpl>{

	public final static String PB_LIST_PAGE = "management/aol/pbMgr/pb-list";
	public final static String PB_DETAIL_PAGE = "management/aol/pbMgr/pb-detail";
	public final static String BORROW_RECORD_LIST_PAGE = "management/aol/pbMgr/pb-record-list";
	
	@Autowired
	private IProductBorrowManger manager;
	
	@Autowired
	private IUsersManager userManager;
	
	@Autowired
	private IMessageManager messageManager;
	
	@Autowired
	private IProductBorrowRepayRecordManager productBorrowRepayRecordManager;
	
	@RequestMapping(value="listPage", method = RequestMethod.GET)
	public String listPage() {
		return PB_LIST_PAGE;
	}
	
	@RequestMapping(value="detailPage", method = RequestMethod.GET)
	public String detailPage(Integer id,Model model) {
		
		ProductBorrow pb = manager.queryByPK(id);
		model.addAttribute("pb", pb);
		return PB_DETAIL_PAGE;
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		ProductBorrow pb = new ProductBorrow();
		pb.getUser().setUserId(paramMap.get("userId") == null ? null : Integer.parseInt(paramMap.get("userId")));
		pb.getUser().setName(paramMap.get("name"));
		pb.getUser().setMobile(paramMap.get("mobile"));
		pb.setUsername(paramMap.get("username"));
		pb.setPhone(paramMap.get("phone"));

		pb.setSchoolName(paramMap.get("schoolName"));
		String regTimeQ = paramMap.get("regTimeQ");
		String regTimeZ = paramMap.get("regTimeZ");
		
		Page<ProductBorrow> page = manager.findAll(pb, regTimeQ, regTimeZ, pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value="agree", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn agree(Integer id) {
		
		ProductBorrow _pb = manager.queryByPK(id);
		_pb.setIsList(1);
		_pb.setNextDate(YqssUtils.nextResidueDay(new Date(_pb.getCreateDate())));
		
		manager.save(_pb);
		
		Message msg = new Message();
		msg.setCreateDate(System.currentTimeMillis());
		msg.setTitle(Constant.MSG_TITLE_AGREE);
		msg.setContent(Constant.MSG_CONTENT_AGREE);
		msg.setIsList(1);
		Users user = userManager.queryUniqueBy("userId", _pb.getUser().getUserId());
		msg.setUser(user);
		messageManager.save(msg);
		
		return new MsgJsonReturn(true,"操作成功");
	}
	
	@RequestMapping(value="refuse", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn refuse(Integer id) {
		
		ProductBorrow _pb = manager.queryByPK(id);
		_pb.setIsList(1);
		
		manager.save(_pb);
		
		Message msg = new Message();
		msg.setCreateDate(System.currentTimeMillis());
		msg.setTitle(Constant.MSG_TITLE_REFUSE);
		msg.setContent(Constant.MSG_CONTENT_REFUSE);
		msg.setIsList(1);
		Users user = userManager.queryUniqueBy("userId", _pb.getUser().getUserId());
		msg.setUser(user);
		messageManager.save(msg);
		
		return new MsgJsonReturn(true,"操作成功");
	}
	
	@RequestMapping(value = "record/index")
	public String recordIndex(Integer borrowInfoId,Model model){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("borrowInfoId", borrowInfoId);
		model.addAttribute("map", map);
		return BORROW_RECORD_LIST_PAGE;
	} 
	
	@RequestMapping(value = "record/list")
	@ResponseBody
	public JSONResponse findRecordPageByBorrowInfoId(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);

		String name = paramMap.get("name");
		Users users = new Users();
		users.setName(name);
		ProductBorrow pb = new ProductBorrow();
		pb.setUser(users);

		ProductBorrowRepayRecord record = new ProductBorrowRepayRecord();
		record.setProductBorrow(pb);

		Page<ProductBorrowRepayRecord> page = productBorrowRepayRecordManager.findPage(record,pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
}
