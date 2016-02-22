package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.aol.dao.ProductDAO;
import com.gcs.aol.entity.Product;
import com.gcs.aol.entity.ProductImage;
import com.gcs.aol.service.IProductImageManager;
import com.gcs.aol.service.IProductManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class ProductManagerImpl extends GenericManagerImpl<Product,ProductDAO> implements IProductManager{

	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private IProductImageManager imageManager;
	
	@Override
	public void isList(Product product) {
	}

	@Override
	public Page<Product> findAll(Product product, Integer currentPage, Integer pageSize) {
		Specification<Product> spec = buildSpecification(product);
		return dao.findAll(spec, new PageRequest(currentPage, pageSize, Sort.Direction.DESC, "id"));
	}

	@Override
	public Specification<Product> buildSpecification(final Product p) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(p.getName() != null) {
					list.add(cb.like(root.get("name").as(String.class), "%" + p.getName() + "%"));
				}
				if(p.getIsList() != null) {
					list.add(cb.equal(root.get("isList").as(Integer.class), p.getIsList()));
				}
				if(p.getProductType() != null && p.getProductType().getId() != null) {
					list.add(cb.equal(root.get("productType").as(Integer.class), p.getProductType().getId()));
				}
				Predicate[] array = new Predicate[list.size()];
				return cb.and(list.toArray(array));
			}
		};
	}

	@Override
	public void deleteProduct(Product product) {
		
		delete(product);
//		List<ProductImage> list = product.getPicList();
//		for (ProductImage productImage : list) {
//			imageManager.delete(productImage);
//		}
	}
}
