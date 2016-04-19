package com.gcs.aol.dao;

import com.gcs.aol.entity.DQBao;
import com.gcs.aol.entity.HQBao;
import com.gcs.sysmgr.service.IBaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2016/4/19 0019.
 */
public interface DQBaoDao extends IBaseJpaRepository<DQBao> {

    @Query(value = "select a from DQBao a where a.id = ?1 ")
    public DQBao getById(Integer id);
}
