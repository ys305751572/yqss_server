package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.Product;
import com.gcs.sysmgr.service.GenericManager;

public interface IProductManager extends GenericManager<Product>{

	public void isList(Product product);
	
	public Page<Product> findAll(Product product,Integer currentPage,Integer pageSize);
	
	public void deleteProduct(Product product);
}
