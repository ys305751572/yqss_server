package com.gcs.aol.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gcs.aol.entity.Ads;
import com.gcs.aol.entity.Product;
import com.gcs.aol.entity.SecondHandProduct;
import com.gcs.aol.service.IAdsManager;
import com.gcs.aol.service.IProductManager;
import com.gcs.aol.service.ISecondHandProductManager;
import com.gcs.aol.service.impl.AdsManagerImpl;
import com.gcs.aol.utils.CommonUtils;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.Attach;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

/**
 * 广告controller
 * @author yesong
 *
 */
@Controller
@RequestMapping("/management/ads/")
public class AdsController extends GenericEntityController<Ads, Ads, AdsManagerImpl>{

	public final static String ADS_LIST_PAGE = "management/aol/adsMgr/ads-list";
	public final static String ADS_EDIT_PAGE = "management/aol/adsMgr/ads-edit";
	public final static String ADS_DETAIL_PAGE = "management/aol/adsMgr/ads-detail";
	
	@Autowired
	private IAdsManager manager;
	
	@Autowired
	private IProductManager productManager;
	
	@Autowired
	private ISecondHandProductManager secondManager;
	
	@RequestMapping(value="listPage", method = RequestMethod.GET)
	public String listPage() {
		return ADS_LIST_PAGE;
	}
	
	@RequestMapping(value="editPage", method = RequestMethod.GET)
	public String editPage(@RequestParam(value="id",required = false) Integer id,Model model) {
		
		if(id != null) {
			Ads ads = manager.queryByPK(id);
			model.addAttribute("ads", ads);
		}
		
		
		List<Product> productList = productManager.queryAll();
		List<SecondHandProduct> secondList = secondManager.queryAll();
		
		Map<String, List> map =  new HashMap<String,List>() ;
		map.put("product", productList);
		map.put("second", secondList);
		
		model.addAttribute("pro", map);
		return ADS_EDIT_PAGE;
	}
	
	@RequestMapping(value="detailPage", method = RequestMethod.GET)
	public String detailPage(Integer id,Model model) {
		Ads ads = manager.queryByPK(id);
		model.addAttribute("ads", ads);
		
		List<Product> productList = productManager.queryAll();
		List<SecondHandProduct> secondList = secondManager.queryAll();
		
		Map<String, List> map =  new HashMap<String,List>() ;
		map.put("product", productList);
		map.put("second", secondList);
		
		model.addAttribute("pro", map);
		
		return ADS_DETAIL_PAGE;
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		Ads ads = new Ads();
		ads.setTitle(paramMap.get("title"));
		ads.setIsList(StringUtils.isNotBlank(paramMap.get("isList")) ? Integer.parseInt(paramMap.get("isList")) : null);
		ads.setType(StringUtils.isNotBlank(paramMap.get("type"))? Integer.parseInt(paramMap.get("type")) : null);
		
		Page<Ads> page = manager.findAll(ads, pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	/**
	 * 发布/下架
	 * @param ads
	 * @return
	 */
	@RequestMapping(value="publish", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn isList(Ads ads) {
		
		Ads _ads = manager.queryByPK(ads.getId());
		_ads.setIsList(ads.getIsList());
		
		try{
			manager.save(_ads);
		} catch (Exception e) {
			e.printStackTrace();
			return new MsgJsonReturn(false, ads.getIsList() == 1 ? "发布失败": "下架失败");
		}
		
		return new MsgJsonReturn(true, ads.getIsList() == 1 ? "发布成功": "下架成功");
	}
	
	/**
	 * 删除广告
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn delete(Integer id) {
		
		Ads ads = manager.queryByPK(id);
		manager.delete(ads);
		return new MsgJsonReturn(true, "删除成功");
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn edit(Integer id,String title,String productId,Integer type,String content ,MultipartHttpServletRequest fileRequest,HttpServletRequest request) {
		
		Ads ads = new Ads();
		if(id != null) {
			ads = manager.queryByPK(id);
		}
		ads.setTitle(title);
		ads.setProductId(productId);
		ads.setType(type);
		ads.setContent(content);
		ads.setIsList(0);
		
		saveImages(ads,fileRequest,request);
		manager.save(ads);
		return new MsgJsonReturn(true,id == null ? "新增成功":"编辑成功");
	}

	private void saveImages(Ads ads, MultipartHttpServletRequest fileRequest,HttpServletRequest request) {
		Iterator<String> fileNames = fileRequest.getFileNames();
		String webRoot = request.getServletContext().getRealPath("");
		
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile file = fileRequest.getFile(fileName);
			Attach attach = CommonUtils.uploadAttach(file, webRoot,"/upload/pi", null);
			if(attach != null) {
				ads.setImageUrl(attach.getBak1());
			}
		}
	}
}
