package com.gcs.aol.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.Users;
import com.gcs.aol.service.IUsersManager;
import com.gcs.aol.service.impl.UsersManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.DateUtils;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/users/")
public class UsersController extends GenericEntityController<Users, Users, UsersManagerImpl>{

	public static final String USER_LIST_PAGE = "management/aol/usersMgr/list";
	public static final String USER_DETAIL_PAGE = "management/aol/usersMgr/users-detail";

	@Autowired
	private IUsersManager manager;
	
	@RequestMapping(value = "listPage", method = RequestMethod.GET)
	public String listPage() {
		return USER_LIST_PAGE;
	}
	
	/**
	 * 
     * @Title: detailPage
     * @Description: 详情页面
     * @param @param user
     * @param @return    参数
     * @return String    返回类型
     * @throws
	 */
	@RequestMapping(value = "detailPage", method = RequestMethod.GET)
	public String detailPage(Integer id,Model model) {
		Users _user = manager.queryByPK(id);
		model.addAttribute("users", _user);
		return USER_DETAIL_PAGE;
	}
	
	/**
	 * 
     * @Title: findAll
     * @Description: 用户列表 翻页
     * @param @return    参数
     * @return JSONResponse    返回类型
     * @throws
	 */
	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		Users users = new Users();
		users.setName(paramMap.get("name"));
		users.setGender(StringUtils.isNotBlank(paramMap.get("gender")) ? Integer.parseInt(paramMap.get("gender")): null);
		
		Long regTimeQ = StringUtils.isNotBlank(paramMap.get("regTimeQ")) ? DateUtils.stringToLong(paramMap.get("regTimeQ") + " 00:00:00", "yyyy-MM-dd HH:mm:ss") : null;
		Long regTimeZ = StringUtils.isNotBlank(paramMap.get("regTimeZ")) ? DateUtils.stringToLong(paramMap.get("regTimeZ") + " 00:00:00", "yyyy-MM-dd HH:mm:ss") : null;
		Page<Users> page = manager.findAll(users,regTimeQ,regTimeZ, pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(),page.getTotalElements(),pp.getSEcho(),page.getContent()));
	}
}
