package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.Contants;
import com.gcs.sysmgr.service.GenericManager;

public interface IContantsManager extends GenericManager<Contants>{

	public List<Contants> queryContants(Integer userId);
}
