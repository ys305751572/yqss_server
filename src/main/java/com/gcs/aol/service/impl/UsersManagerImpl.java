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

import com.gcs.aol.dao.UsersDAO;
import com.gcs.aol.entity.Users;
import com.gcs.aol.service.IUsersManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

/**
 * 
 * @ClassName: UsersManagerImpl
 * @Description: 用户
 * @author yesong
 * @date 2015年12月22日
     *
 */
@Service
public class UsersManagerImpl extends GenericManagerImpl<Users, UsersDAO> implements IUsersManager{

	@Autowired
	private UsersDAO dao;
	
	private Long regTimeQ;
	private Long regTimeZ;
	
	@Override
	public Page<Users> findAll(Users user,Long regTimeQ,Long regTimeZ ,Integer currentPage, Integer pageSize) {
		Specification<Users> spec = buildSpecification(user);
		this.regTimeQ = regTimeQ;
		this.regTimeZ = regTimeZ;
		return dao.findAll(spec, new PageRequest(currentPage, pageSize, Sort.Direction.DESC, "id"));
	}
	
	@Override
	public Specification<Users> buildSpecification(final Users d) {
		return new Specification<Users>() {
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(regTimeQ != null ) {
					list.add(cb.gt(root.get("createDate").as(Long.class),regTimeQ));
				}
				if(regTimeZ != null) {
					list.add(cb.le(root.get("createDate").as(Long.class), regTimeZ));
				}
				if(d.getName() != null) {
					list.add(cb.like(root.get("name").as(String.class), "%" + d.getName() + "%"));
				}
				if(d.getMobile() != null) {
					list.add(cb.like(root.get("mobile").as(String.class), "%" + d.getMobile() + "%"));
				}
				if(d.getGender() != null) {
					list.add(cb.equal(root.get("gender").as(Integer.class), d.getGender()));
				}
			    Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));  
			}
		};
	}
}
