package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
		
		ProductImage image = new ProductImage();
		image.setProductId(productId);
		Specification<ProductImage> spec = buildSpecification(image);
		List<ProductImage> list = dao.findAll(spec);
		return list;
	}
	
	@Override
	public Specification<ProductImage> buildSpecification(final ProductImage image) {
		return new Specification<ProductImage>() {
			@Override
			public Predicate toPredicate(Root<ProductImage> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(image.getProductId() != null) {
					list.add(cb.equal(root.get("productId").as(Integer.class), image.getProductId()));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
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
