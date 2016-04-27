package com.gcs.aol.dao;

import com.gcs.aol.entity.MoneyMagTR;
import com.gcs.sysmgr.service.IBaseJpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MoneyMagTRDAO extends IBaseJpaRepository<MoneyMagTR>{

    @Query("select a from MoneyMagTR a where a.user.userId = ?1 and a.status = 1")
    public List<MoneyMagTR> findByUserId(Integer userId);

    @Query("select a from MoneyMagTR a where a.sn = ?1")
    public MoneyMagTR findBySn(String sn);

    @Modifying
    @Transactional
    @Query("update MoneyMagTR a set a.status = 1 where a.sn = ?1")
    public void modifyTrStatus(String sn);
}
