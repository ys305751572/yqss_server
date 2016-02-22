package com.gcs.aol.service;

import java.util.List;

import com.gcs.aol.entity.ProductStage;
import com.gcs.sysmgr.service.GenericManager;

public interface IProductStageManager extends GenericManager<ProductStage>{

	public List<ProductStage> findStagesByProductId(Integer productId);
}
