package com.gcs.aol.service.impl;

import com.gcs.aol.dao.BannerDAO;
import com.gcs.aol.entity.Banner;
import com.gcs.aol.service.IBannerManager;
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
 * Created by Administrator on 2016/4/22.
 */
@Service
public class BannerManagerImpl extends GenericManagerImpl<Banner,BannerDAO> implements IBannerManager{

    @Autowired
    private BannerDAO dao;

    @Override
    public List<Banner> findTitleBuyIsList(Integer isList) {

        List<Banner> list = this.queryTop("id",false,5);
        return list;
//        return dao.findTitleBuyIsList(isList);
    }


    @Override
    public Page<Banner> findPage(final Banner banner, int currentPage, int pageSize) {
        Specification<Banner> spec = new Specification<Banner>() {
            @Override
            public Predicate toPredicate(Root<Banner> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(banner.getTitle())) {
                    list.add(cb.like(root.get("title").as(String.class),"%" + banner.getTitle() + "%"));
                }

                if(banner.getIsList() != null) {
                    list.add(cb.equal(root.get("isList").as(Integer.class),banner.getIsList()));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(spec,new PageRequest(currentPage,pageSize, Sort.Direction.DESC,"id"));
    }



}
