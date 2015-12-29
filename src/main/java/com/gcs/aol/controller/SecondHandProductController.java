package com.gcs.aol.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.SecondHandProduct;
import com.gcs.aol.entity.SecondHandProductImage;
import com.gcs.aol.service.ISecondHandProductManager;
import com.gcs.aol.service.IShpImageService;
import com.gcs.aol.service.impl.SecondHandProductManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

/**
 * 二手商品controller
 * @author yesong
 *
 */
@Controller
@RequestMapping("/management/second/")
public class SecondHandProductController extends GenericEntityController<SecondHandProduct, SecondHandProduct, SecondHandProductManagerImpl>{

	public static final String SECOND_LIST_PAGE = "management/aol/secondMgr/shp-list";
	public static final String SECOND_DETAIL_PAGE = "management/aol/secondMgr/shp-detail";
	
	@Autowired
	private ISecondHandProductManager manager;
	
	@Autowired
	private IShpImageService imageService;
	
	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping(value="listPage", method = RequestMethod.GET)
	public String listPage() {
		return SECOND_LIST_PAGE;
	}
	
	/**
	 * 详情页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="detailPage", method = RequestMethod.GET)
	public String detailPage(Integer id,Model model) {
		
		SecondHandProduct shp = manager.queryByPK(id);
		model.addAttribute("shp", shp);
		return SECOND_DETAIL_PAGE;
	}
	
	/**
	 * 查询列表（翻页）
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] param) {
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(param);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		SecondHandProduct shp = new SecondHandProduct();
		shp.setTitle(paramMap.get("title"));
		
		Page<SecondHandProduct> page = manager.findAll(shp, pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	/**
	 * 删除二手商品
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn delete(Integer id){
		
		SecondHandProduct shp = manager.queryByPK(id);
		List<SecondHandProductImage> list = shp.getPicList();
		for (SecondHandProductImage secondHandProductImage : list) {
			imageService.delete(secondHandProductImage);
		}
		manager.delete(shp);
		return new MsgJsonReturn(true,"删除成功");
	}
}
