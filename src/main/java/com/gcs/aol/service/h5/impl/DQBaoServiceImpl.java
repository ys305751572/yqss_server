package com.gcs.aol.service.h5.impl;

import com.gcs.aol.dao.DQBaoDao;
import com.gcs.aol.dao.HQBaoDao;
import com.gcs.aol.entity.DQBao;
import com.gcs.aol.entity.HQBao;
import com.gcs.aol.service.h5.DQBaoService;
import com.gcs.aol.service.h5.HQBaoService;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;
import com.gcs.sysmgr.vo.PageParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/4/18 0018.
 */
@Service
public class DQBaoServiceImpl extends GenericManagerImpl<DQBao, DQBaoDao> implements DQBaoService {

    @Autowired
    private DQBaoDao dqBaoDao;

    @Override
    public DQBao getbyid(Integer id) {
        return dqBaoDao.getById(id);
    }

    @Override
    public Page<DQBao> query(DQBao entity, PageParameters pp) {
        return null;
    }

    @Override
    public Specification<DQBao> buildSpecification(DQBao hqBao) {
        return null;
    }

    @Override
    public DQBao insert(DQBao object) {
        return null;
    }

    @Override
    public DQBao save(DQBao object) {
        return null;
    }

    @Override
    public DQBao update(DQBao object) {
        return null;
    }

    @Override
    public void delete(DQBao object) {

    }
}
