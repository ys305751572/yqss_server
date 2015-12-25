package com.gcs.aol.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name="product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product extends IdAbstartEntity{

	private static final long serialVersionUID = -558721063703874444L;

	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private double price;
	
	@Column(name="content")
	private String content;
	
	@Column(name="url")
	private String url;
	
	@Column(name="is_list")
	private Integer isList = 0;
	
	@Column(name="create_date")
	private Long createDate;

	@OneToOne
	@JoinColumn(name="product_type")
	private ProductType productType;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "product_id")
	private List<ProductImage> picList;
	
	public List<ProductImage> getPicList() {
		return picList;
	}

	public void setPicList(List<ProductImage> picList) {
		this.picList = picList;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIsList() {
		return isList;
	}

	public void setIsList(Integer isList) {
		this.isList = isList;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
