package com.gcs.aol.dao;

import com.gcs.aol.entity.HQBao;
import com.gcs.sysmgr.service.IBaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/4/19 0019.
 */
public interface HQBaoDao extends IBaseJpaRepository<HQBao> {

    @Query("select a from HQBao a where a.id = ?1 ")
    public HQBao getById(Integer id);
}
