package com.gcs.aol.service;

import com.gcs.aol.entity.Banner;
import com.gcs.sysmgr.service.GenericManager;
import com.sun.org.apache.xpath.internal.operations.String;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 */
public interface IBannerManager extends GenericManager<Banner>{

    public Page<Banner> findPage(Banner banner, int currentPage, int pageSize);

    public List<Banner> findTitleBuyIsList(Integer isList);
}
