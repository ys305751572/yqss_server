package com.gcs.aol.service.h5.impl;

import com.gcs.aol.dao.HQBaoDao;
import com.gcs.aol.dao.MoneyMagDAO;
import com.gcs.aol.entity.HQBao;
import com.gcs.aol.entity.MoneyMag;
import com.gcs.aol.service.h5.HQBaoService;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.sysmgr.vo.PageParameters;
import org.hibernate.annotations.AccessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18 0018.
 */
@Service
public class HQBaoServiceImpl extends GenericManagerImpl<HQBao, HQBaoDao> implements HQBaoService {

    @Autowired
    private HQBaoDao hqBaoDao;

    @Override
    public HQBao getbyid(Integer id) {
        return hqBaoDao.getById(id);
    }

    @Override
    public Page<HQBao> query(HQBao entity, PageParameters pp) {
        return null;
    }

    @Override
    public Specification<HQBao> buildSpecification(HQBao hqBao) {
        return null;
    }

    @Override
    public HQBao insert(HQBao object) {
        return null;
    }

    @Override
    public HQBao save(HQBao object) {
        return null;
    }

    @Override
    public HQBao update(HQBao object) {
        return null;
    }

    @Override
    public void delete(HQBao object) {

    }
}
