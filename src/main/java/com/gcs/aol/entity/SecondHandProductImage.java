package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 二手商品图片
 * @author yesong
 *
 */
@Entity
@Table(name="second_hand_images")
public class SecondHandProductImage extends IdAbstartEntity{

	private static final long serialVersionUID = -6271513679309128042L;

	@Column(name="second_hand_id")
	private Integer secondHandProductId;
	
	@Column(name="image_url")
	private String imageUrl;

	public Integer getSecondHandProductId() {
		return secondHandProductId;
	}

	public void setSecondHandProductId(Integer secondHandProductId) {
		this.secondHandProductId = secondHandProductId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
