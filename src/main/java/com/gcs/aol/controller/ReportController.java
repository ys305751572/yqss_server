package com.gcs.aol.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.Ads;
import com.gcs.aol.entity.Report;
import com.gcs.aol.service.IReportManager;
import com.gcs.aol.service.impl.ReportManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@RequestMapping("/management/report/")
@Controller
public class ReportController extends GenericEntityController<Report, Report, ReportManagerImpl>{

	public static final String REPORT_LIST_PAGE = "management/aol/reportMgr/report-list";
	
	@Autowired
	private IReportManager manager;
	/**
	 * 
     * @Title: listPage
     * @Description: 列表页面
     * @param @return    参数
     * @return String    返回类型
     * @throws
	 */
	@RequestMapping(value="listPage", method = RequestMethod.GET)
	public String listPage() {
		return REPORT_LIST_PAGE;
	}
	
	/**
	 * 
     * @Title: findAll
     * @Description: 列表页面查询
     * @param @param params
     * @param @return    参数
     * @return JSONResponse    返回类型
     * @throws
	 */
	@RequestMapping(value="findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		Report report = new Report();
		report.setContent(paramMap.get("content"));
		
		Page<Report> page = manager.findAll(report, pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn delete(Integer id) {
		
		Report report = manager.queryByPK(id);
		manager.delete(report);
		return new MsgJsonReturn(true,"删除成功");
	}
}
