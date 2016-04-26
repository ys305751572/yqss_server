package com.gcs.aol.dao;

import com.gcs.aol.entity.MoneyMagTR;
import com.gcs.sysmgr.service.IBaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoneyMagTRDAO extends IBaseJpaRepository<MoneyMagTR>{

    @Query("select a from MoneyMagTR a where a.user.userId = ?1 and a.status = 1")
    public List<MoneyMagTR> findByUserId(Integer userId);
}
