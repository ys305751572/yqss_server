package com.gcs.aol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.aol.dao.ProductTypeDAO;
import com.gcs.aol.entity.ProductType;
import com.gcs.aol.service.IProductTypeManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

/**
 * 
 * @ClassName: ProductTypeManagerImpl
 * @Description: 商品类型
 * @author yesong 
 * @date 2015年12月23日
 *
 */
@Service
public class ProductTypeManagerImpl extends GenericManagerImpl<ProductType, ProductTypeDAO> implements IProductTypeManager{

	@Autowired
	private ProductTypeDAO dao;
}
