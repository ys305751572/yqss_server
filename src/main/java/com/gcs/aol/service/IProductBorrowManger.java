package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.ProductBorrow;
import com.gcs.sysmgr.service.GenericManager;

/**
 * 
 * @ClassName: IProductBorrowManger
 * @Description: 商品购买
 * @author yesong
 * @date 2015年12月28日
 *
 */
public interface IProductBorrowManger extends GenericManager<ProductBorrow>{

	public Page<ProductBorrow> findAll(ProductBorrow pb,String retimeQ,String retimeZ,Integer currentPage,Integer pageSize);
}
