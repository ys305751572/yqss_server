package com.gcs.aol.service;

import com.gcs.aol.entity.ActEntity;
import com.gcs.sysmgr.service.GenericManager;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2016/5/27.
 */
public interface ActService extends GenericManager<ActEntity>{

    Page<ActEntity> findPage(String mobil,int pagenum,int pagesize);
}
