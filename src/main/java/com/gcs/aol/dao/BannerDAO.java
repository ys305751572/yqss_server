package com.gcs.aol.dao;

import com.gcs.aol.entity.Banner;
import com.gcs.sysmgr.service.IBaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 */
public interface BannerDAO extends IBaseJpaRepository<Banner>{

//    @Query("select a from Banner a where a.isList = ?1 ")
//    public List<Banner> findTitleBuyIsList(Integer isList);
}
