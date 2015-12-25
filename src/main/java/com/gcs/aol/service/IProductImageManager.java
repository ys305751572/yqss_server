package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.ProductImage;
import com.gcs.sysmgr.service.GenericManager;

public interface IProductImageManager extends GenericManager<ProductImage>{
	
	public List<ProductImage> findAllByProductId(Integer productId);
	
	public void insertProductImage(ProductImage image);
	
	public void delete(Integer imageId);
	
	public void insertProductImages(List<ProductImage> images);
}
