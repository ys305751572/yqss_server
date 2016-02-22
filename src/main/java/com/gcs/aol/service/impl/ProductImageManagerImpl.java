package com.gcs.aol.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcs.aol.dao.ProductImageDAO;
import com.gcs.aol.entity.ProductImage;
import com.gcs.aol.service.IProductImageManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class ProductImageManagerImpl extends GenericManagerImpl<ProductImage, ProductImageDAO> implements IProductImageManager{

	@Autowired
	private ProductImageDAO dao;
	
	@Override
	public List<ProductImage> findAllByProductId(Integer productId) {
		return queryByProperty("productId", productId);
	}

	@Override
	public void insertProductImage(ProductImage image) {
	}

	@Override
	public void delete(Integer imageId) {
		
	}

	@Override
	public void insertProductImages(List<ProductImage> images) {
		
		if(images != null && images.size() > 0) {
			for (ProductImage productImage : images) {
				save(productImage);
			}
		}
	}
}
