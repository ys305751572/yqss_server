package com.gcs.aol.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.gcs.aol.entity.Message;
import com.gcs.sysmgr.service.GenericManager;

@Service
public interface IMessageManager extends GenericManager<Message>{

	public Page<Message> findAll(Message msg,String regTimeQ,String regTimeZ,Integer currentPage,Integer pageSize);
}
