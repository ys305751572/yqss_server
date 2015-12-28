package com.gcs.aol.service;

import org.springframework.data.domain.Page;

import com.gcs.aol.entity.HrMessage;
import com.gcs.sysmgr.service.GenericManager;

public interface IHrMessageManager extends GenericManager<HrMessage>{

	public Page<HrMessage> findAll(HrMessage hr,Integer currnetPage,Integer pageSize);
}
