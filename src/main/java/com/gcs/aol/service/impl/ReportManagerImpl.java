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

import com.gcs.aol.dao.ReportDAO;
import com.gcs.aol.entity.Report;
import com.gcs.aol.service.IReportManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class ReportManagerImpl extends GenericManagerImpl<Report, ReportDAO> implements IReportManager{

	@SuppressWarnings("unused")
	@Autowired
	private ReportDAO dao;

	@Override
	public Page<Report> findAll(Report report, Integer currnetPage, Integer pageSize) {
		Specification<Report> spec = buildSpecification(report);
		return dao.findAll(spec, new PageRequest(currnetPage, pageSize, Sort.Direction.DESC, "id"));
	}

	@Override
	public Specification<Report> buildSpecification(final Report entity) {
		return new Specification<Report>() {
			@Override
			public Predicate toPredicate(Root<Report> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(entity.getContent())) {
					list.add(cb.like(root.get("content").as(String.class),"%" + entity.getContent() + "%"));
				}
				Predicate[] array = new Predicate[list.size()];
				return cb.and(list.toArray(array));
			}
		};
	}
}
