package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name="ads")
public class Ads extends IdAbstartEntity{

	private static final long serialVersionUID = 2372940252935505980L;

	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="product_id")
	private String productId;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="link_url")
	private String linkUrl;
	
	@Column(name="type")
	private Integer type;

	@Column(name="is_list")
	private Integer isList;
	
	@Column(name="create_date")
	private Long createDate = System.currentTimeMillis();
	
	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Integer getIsList() {
		return isList;
	}

	public void setIsList(Integer isList) {
		this.isList = isList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
