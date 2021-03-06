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

import com.gcs.aol.dao.MessageDAO;
import com.gcs.aol.entity.Message;
import com.gcs.aol.service.IMessageManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.utils.DateUtils;

@Service
public class MessageManagerImpl extends GenericManagerImpl<Message, MessageDAO> implements IMessageManager{

	@Autowired
	private MessageDAO dao;
	
	private Long regTimeQ;
	
	private Long regTimeZ;
	
	@Override
	public Page<Message> findAll(Message msg, String regTimeQ, String regTimeZ, Integer currentPage, Integer pageSize) {
		this.regTimeQ = StringUtils.isNotBlank(regTimeQ) ? DateUtils.stringToLong(regTimeQ + " 00:00:00", "yyyy-MM-dd HH:mm:ss") : null;
		this.regTimeZ = StringUtils.isNotBlank(regTimeZ) ? DateUtils.stringToLong(regTimeZ + " 00:00:00", "yyyy-MM-dd HH:mm:ss") : null;
		Specification<Message> spec = buildSpecification(msg);
		return dao.findAll(spec, new PageRequest(currentPage, pageSize, Sort.Direction.DESC,"id"));
	}

	@Override
	public Specification<Message> buildSpecification(final Message msg) {
		return new Specification<Message>() {
			@Override
			public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> arg1, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				if(msg.getUser().getUserId() != null) {
					list.add(cb.equal(root.get("userId").as(Integer.class), msg.getUser().getUserId()));
				}
				if(regTimeQ != null ) {
					list.add(cb.gt(root.get("createDate").as(Long.class),regTimeQ));
				}
				if(regTimeZ != null) {
					list.add(cb.le(root.get("createDate").as(Long.class), regTimeZ));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
	}
}
