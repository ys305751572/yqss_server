package com.gcs.aol.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.gcs.aol.entity.Product;
import com.gcs.aol.entity.ProductImage;
import com.gcs.aol.entity.ProductType;
import com.gcs.aol.service.IProductImageManager;
import com.gcs.aol.service.IProductManager;
import com.gcs.aol.service.IProductTypeManager;
import com.gcs.aol.service.impl.ProductManagerImpl;
import com.gcs.aol.utils.CommonUtils;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.Attach;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@Controller
@RequestMapping("/management/product/")
public class ProductController extends GenericEntityController<Product, Product, ProductManagerImpl>{

	public static final String PRODUCT_LIST_PAGE = "management/aol/productMgr/product-list";
	public static final String PRODUCT_EDIT_PAGE = "management/aol/productMgr/product-edit";
	public static final String PRODUCT_DETAIL_PAGE = "management/aol/productMgr/product-detail";
	
	@Autowired
	private IProductManager manager;
	
	@Autowired
	private IProductImageManager imageManager;
	
	@Autowired
	private IProductTypeManager typeManager;
	
	@RequestMapping(value = "listPage", method = RequestMethod.GET)
	public String listPage() {
		return PRODUCT_LIST_PAGE;
	}
	
	@RequestMapping(value = "editPage", method = RequestMethod.GET)
	public String editPage(@RequestParam(value="id", required = false) Integer id,Model model) {
		
		if(id != null) {
			Product product = manager.queryByPK(id);
			product.setPrice(product.getPrice());
			model.addAttribute("product", product); 
		}
		// 商品类型
		Map<String,List<ProductType>> map = new HashMap<String,List<ProductType>>();
		List<ProductType> typeList = typeManager.queryAll();
		map.put("list", typeList);
		model.addAttribute("map", map);
		return PRODUCT_EDIT_PAGE;
	}
	
	@RequestMapping(value = "detailPage", method = RequestMethod.GET)
	public String detailPage(Integer id,Model model) {
		
		Product product = manager.queryByPK(id);
		model.addAttribute("product", product);
		return PRODUCT_DETAIL_PAGE;
	}
	
	/**
	 * 
     * @Title: deleteProduct
     * @Description: 删除商品
     * @param @param id
     * @param @return    参数
     * @return MsgJsonReturn    返回类型
     * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn deleteProduct(Integer id) {
		
		try{
			manager.deleteByPK(id);
		}catch (Exception e) {
			e.printStackTrace();
			return new MsgJsonReturn(false,"删除失败");
		}
		return new MsgJsonReturn(true,"删除成功");
	}
	
	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestBody JSONParam[] params) {
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		Product product = new Product();
		product.setIsList(paramMap.get("isList") != null ? Integer.parseInt(paramMap.get("isList")) : null);
		product.setName(paramMap.get("name"));
		
		ProductType type = new ProductType();
		type.setId(paramMap.get("productType") != null ? Integer.parseInt(paramMap.get("productType")) : null);
		product.setProductType(type);
		
		Page<Product> page = manager.findAll(product, pp.getStart(), pp.getLength());
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	/**
	 * 
     * @Title: isList
     * @Description: 更新
     * @param @return    参数
     * @return MsgJsonReturn    返回类型
     * @throws
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn update(Integer id,String name,Double price,String url,Integer productType, MultipartHttpServletRequest fileRequest, HttpServletRequest request,HttpServletResponse resp) {
		
		Product product = new Product();
		if(id != null && StringUtils.isNotBlank(id.toString())) {
			product = manager.queryByPK(id);
		}
		product.setName(name);
		product.setUrl(url);
		product.setPrice(price);
		
		ProductType pt = new ProductType();
		pt.setId(productType);
		product.setProductType(pt);
		
		// 保存商品信息 
		manager.save(product);
		// 保存商品图片
		saveProductImage(product.getId(),fileRequest,request);
		return new MsgJsonReturn(true, "编辑成功");
	}
	
	/**
	 * 
     * @Title: saveProductImage
     * @Description: 保存图片
     * @param @param fileRequest    参数
     * @return void    返回类型
     * @throws
	 */
	private void saveProductImage(Integer productId,MultipartHttpServletRequest fileRequest,HttpServletRequest request) {
		Iterator<String> fileNames = fileRequest.getFileNames();
		String webRoot = request.getServletContext().getRealPath("");
		
		List<ProductImage> imageList = new ArrayList<ProductImage>();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile file = fileRequest.getFile(fileName);
			Attach attach = CommonUtils.uploadAttach(file, webRoot,"/upload/pi", null);
			if(attach != null) {
				ProductImage image = new ProductImage();
				image.setImageUrl(attach.getBak1()); // 完整路径
				image.setProductId(productId);
				imageList.add(image);
			}
		}
		
		// 批量保存图片
		imageManager.insertProductImages(imageList);
	}

	/**
	 * 
     * @Title: publish
     * @Description: 商品发布，下架
     * @param @param product
     * @param @return    参数
     * @return MsgJsonReturn    返回类型
     * @throws
	 */
	@RequestMapping(value = "publish", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn publish(Product product) {
		
		Product _p = manager.queryByPK(product.getId());
		_p.setIsList(product.getIsList());
		
		manager.save(_p);
		return new MsgJsonReturn(true, product.getIsList() == 1 ? "发布成功":"下架成功");
	}
	
	
	/**
	 * 
     * @Title: deleteImage
     * @Description: 删除图片
     * @param @param id
     * @param @return    参数
     * @return MsgJsonReturn    返回类型
     * @throws
	 */
	@RequestMapping(value = "image/delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn deleteImage(Integer id) {
		
		try{
			imageManager.deleteByPK(id);
		} catch (Exception e) {
			e.printStackTrace();
			new MsgJsonReturn(false, "删除失败");
		}
		return new MsgJsonReturn(true,"删除成功");
	}
}