package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name="product_stage")
public class ProductStage extends IdAbstartEntity{

	private static final long serialVersionUID = -942158695863001172L;

	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="stage")
	private Integer stage;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}
}
