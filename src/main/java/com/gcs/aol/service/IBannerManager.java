package com.gcs.aol.service;

import com.gcs.aol.entity.Banner;
import com.gcs.sysmgr.service.GenericManager;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/4/22.
 */
public interface IBannerManager extends GenericManager<Banner>{

    public Page<Banner> findPage(Banner banner, int currentPage, int pageSize);
}
