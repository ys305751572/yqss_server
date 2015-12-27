package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 借款
 * @author yesong
 *
 */
@Entity
@Table(name="borrow_info")
public class Borrow extends IdAbstartEntity{

	private static final long serialVersionUID = 6729759147384579516L;

	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="limit_money")
	private Integer limitMoney;
	
	@Column(name="max_day")
	private Integer maxDay;
	
	@Column(name="type")
	private Integer type;
	
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
	
	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="repay_date")
	private String repayDate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLimitMoney() {
		return limitMoney;
	}

	public void setLimitMoney(Integer limitMoney) {
		this.limitMoney = limitMoney;
	}

	public Integer getMaxDay() {
		return maxDay;
	}

	public void setMaxDay(Integer maxDay) {
		this.maxDay = maxDay;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}
}
