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

import com.gcs.aol.dao.ProductStageDAO;
import com.gcs.aol.entity.Product;
import com.gcs.aol.entity.ProductStage;
import com.gcs.aol.service.IProductStageManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class ProductStageManagerImpl extends GenericManagerImpl<ProductStage, ProductStageDAO> implements IProductStageManager{

	@Autowired
	private ProductStageDAO dao;

	@Override
	public List<ProductStage> findStagesByProductId(Integer productId) {
		
		ProductStage ps = new ProductStage();
		Product p = new Product();
		p.setId(productId);
		ps.setProduct(p);
		Specification<ProductStage> sepc = buildSpecification(ps);
		return dao.findAll(sepc);
	}

	@Override
	public Specification<ProductStage> buildSpecification(final ProductStage entity) {
		return new Specification<ProductStage>() {

			@Override
			public Predicate toPredicate(Root<ProductStage> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(entity != null && entity.getProduct().getId() != null) {
					list.add(cb.equal(root.get(root.getModel().getSingularAttribute("product", Product.class)), entity.getProduct().getId()));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
	
	
}
