package com.gcs.aol.service.h5;

import com.gcs.aol.entity.HQBao;
import com.gcs.aol.entity.MoneyMag;
import com.gcs.aol.entity.MoneyMagDod;
import com.gcs.aol.service.h5.impl.HQBaoServiceImpl;
import com.gcs.aol.service.impl.MoneyMagManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.service.GenericManager;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;


/**
 * Created by Administrator on 2016/4/18 0018 下午 4:40.
 */
public interface HQBaoService extends GenericManager<HQBao> {

    //根据id查询对应活期宝
    public HQBao getbyid(Integer id);
}