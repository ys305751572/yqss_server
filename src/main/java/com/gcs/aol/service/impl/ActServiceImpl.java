package com.gcs.aol.service.impl;

import com.gcs.aol.dao.ActDao;
import com.gcs.aol.entity.ActEntity;
import com.gcs.aol.service.ActService;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
@Service
public class ActServiceImpl extends GenericManagerImpl<ActEntity,ActDao> implements ActService{

    @Autowired
    private ActDao dao;

    @Override
    public Page<ActEntity> findPage(final String mobile, int pagenum, int pagesize) {
        Specification<ActEntity> spec = new Specification<ActEntity>() {
            @Override
            public Predicate toPredicate(Root<ActEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(mobile)) {
                    list.add(criteriaBuilder.like(root.get("mobile").as(String.class),"%" + mobile + "%"));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec, new PageRequest(pagenum,pagesize, Sort.Direction.DESC,"id"));
    }
}
