package com.gcs.aol.service.h5;

import com.gcs.aol.entity.DQBao;
import com.gcs.aol.entity.HQBao;
import com.gcs.sysmgr.service.GenericManager;


/**
 * Created by Administrator on 2016/4/18 0018 下午 4:40.
 */
public interface DQBaoService extends GenericManager<DQBao> {

    //根据id查询对应活期宝
    public DQBao getbyid(Integer id);
}