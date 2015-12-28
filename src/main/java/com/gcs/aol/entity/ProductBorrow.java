package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name="buy_borrow_info")
public class ProductBorrow extends IdAbstartEntity{

	private static final long serialVersionUID = 8542774779402763105L;

	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="period")
	private Integer period;
	
	@Column(name="username")
	private String username;
	
	@Column(name="id_card")
	private String idCard;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="school_name")
	private String schoolName;
	
	@Column(name="address")
	private String address;

	@Column(name="is_list")
	private Integer isList;
	
	public Integer getIsList() {
		return isList;
	}

	public void setIsList(Integer isList) {
		this.isList = isList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
