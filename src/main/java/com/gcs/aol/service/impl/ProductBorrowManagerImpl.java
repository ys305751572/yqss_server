package com.gcs.aol.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.gcs.aol.dao.ProductBorrowDAO;
import com.gcs.aol.entity.ProductBorrow;
import com.gcs.aol.service.IProductBorrowManger;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.utils.DateUtils;

@Service
public class ProductBorrowManagerImpl extends GenericManagerImpl<ProductBorrow, ProductBorrowDAO> implements IProductBorrowManger{

	@Autowired
	private ProductBorrowDAO dao;
	
	private Long retimeQ;
	
	private Long retimeZ;
	
	@Override
	public Page<ProductBorrow> findAll(ProductBorrow pb, String retimeQ, String retimeZ, Integer currentPage,
			Integer pageSize) {
		
		this.retimeQ = StringUtils.isNotBlank(retimeQ) ? DateUtils.stringToLong(retimeQ + " 00:00:00", "yyyy-MM-dd HH:mm:ss") : null;
		this.retimeZ = StringUtils.isNotBlank(retimeZ) ? DateUtils.stringToLong(retimeZ + " 00:00:00", "yyyy-MM-dd HH:mm:ss") : null;
		
		Specification<ProductBorrow> spec = this.buildSpecification(pb);
		return dao.findAll(spec, new PageRequest(currentPage,pageSize,Sort.Direction.DESC,"id"));
	}

	@Override
	public Specification<ProductBorrow> buildSpecification(final ProductBorrow pb) {
		return new Specification<ProductBorrow>() {
			@Override
			public Predicate toPredicate(Root<ProductBorrow> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(pb.getUser().getUserId() != null) {
					list.add(cb.equal(root.get("userId").as(Integer.class), pb.getUser().getUserId()));
				}
				if(retimeQ != null) {
					list.add(cb.ge(root.get("createDate").as(Long.class), retimeQ));
				}
				if(retimeZ != null) {
					list.add(cb.le(root.get("createDate").as(Long.class), retimeZ));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
}
