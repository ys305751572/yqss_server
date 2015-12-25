package com.gcs.sysmgr.service.impl;

import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcs.sysmgr.dao.AttachDAO;
import com.gcs.sysmgr.entity.Attach;
import com.gcs.sysmgr.service.IAttachManager;
import com.gcs.sysmgr.service.impl.GenericManagerImpl;

@Service
public class AttachManagerImpl extends GenericManagerImpl<Attach, AttachDAO> implements IAttachManager {
	@Autowired
	AttachDAO attachDAO;
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
}
