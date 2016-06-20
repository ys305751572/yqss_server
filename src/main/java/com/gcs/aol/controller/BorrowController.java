package com.gcs.aol.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.constant.Constant;
import com.gcs.aol.entity.Borrow;
import com.gcs.aol.entity.BorrowRepayRecord;
import com.gcs.aol.entity.Message;
import com.gcs.aol.entity.Users;
import com.gcs.aol.service.IBorrowManager;
import com.gcs.aol.service.IBorrowRepayRecordManager;
import com.gcs.aol.service.IMessageManager;
import com.gcs.aol.service.IUsersManager;
import com.gcs.aol.service.impl.BorrowManagerImpl;
import com.gcs.aol.utils.YqssUtils;
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

	public final static String BORROW_LIST_PAGE = "management/aol/borrowMgr/borrow-list";
	public final static String BORROW_DETAIL_PAGE = "management/aol/borrowMgr/borrow-detail";
	public final static String BORROW_RECORD_LIST_PAGE = "management/aol/borrowMgr/borrow-record-list";
	
	@Autowired
	private IBorrowManager manager;
	
	@Autowired
	private IMessageManager messageManager;
	
	@Autowired
	private IUsersManager userManager;
	
	@Autowired
	private IBorrowRepayRecordManager borrowRepayRecordManager;
	
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
		b.setIsList(StringUtils.isNotBlank(paramMap.get("isList")) ? Integer.parseInt(paramMap.get("isList")) : null);

		Users users = new Users();
		users.setName(paramMap.get("name")); // 登陆用户信息
		users.setMobile(paramMap.get("mobile")); // 用户手机

		b.setUser(users);

		b.setUsername(paramMap.get("username")); // 借款人信息
		b.setSchoolName(paramMap.get("schoolName"));
		b.setPhone(paramMap.get("phone"));

		String regTimeQ = paramMap.get("regTimeQ");
		String regTimeZ = paramMap.get("regTimeZ");
		Page<Borrow> page = manager.findAll(b,regTimeQ,regTimeZ,pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value="agree", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public MsgJsonReturn agree(Borrow borrow) {
	
		Borrow _b = manager.queryByPK(borrow.getId()); 
//		Integer userId = _b.getUser().getUserId();
		
//		long count = manager.getCountByProperty("borrow_info", "user_id", userId);
//		if(count > 0) {
//			return new MsgJsonReturn(false,"改用户已经有过一次");
//		}
		_b.setIsList(1);
		_b.setRepayDate(YqssUtils.borrowResidueDate(_b.getMaxDay()));
		manager.save(_b);
		
		Message msg = new Message();
		msg.setCreateDate(System.currentTimeMillis());
		msg.setTitle(Constant.MSG_TITLE_AGREE);
		msg.setContent(Constant.MSG_CONTENT_AGREE);
		msg.setIsList(1);
		Users user = userManager.queryUniqueBy("userId", _b.getUser().getUserId());
		msg.setUser(user);
		messageManager.save(msg);
		
		return new MsgJsonReturn(true, "操作成功");
	}
	
	@RequestMapping(value="refuse", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public MsgJsonReturn refuse(Borrow borrow) {
	
		Borrow _b = manager.queryByPK(borrow.getId());
		_b.setIsList(2);
		
		Message msg = new Message();
		msg.setCreateDate(System.currentTimeMillis());
		msg.setTitle(Constant.MSG_TITLE_REFUSE);
		msg.setContent(Constant.MSG_CONTENT_REFUSE);
		msg.setIsList(1);
		Users user = userManager.queryUniqueBy("userId", _b.getUser().getUserId());
		msg.setUser(user);
		messageManager.save(msg);
		
		manager.save(_b);
		return new MsgJsonReturn(true, "操作成功");
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
		Borrow borrow = new Borrow();
		borrow.setUser(users);

		BorrowRepayRecord record = new BorrowRepayRecord();
		record.setBorrow(borrow);

		Page<BorrowRepayRecord> page = borrowRepayRecordManager.findPage(record,pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
}
