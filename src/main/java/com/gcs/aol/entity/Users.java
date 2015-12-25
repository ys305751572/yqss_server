package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 
 * @ClassName: Users
 * @Description: 用户
 * @author yesong
 * @date 2015年12月22日
 *
 */
@Entity
@Table(name = "users_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Users extends IdAbstartEntity{
	
	private static final long serialVersionUID = -8507931874096227631L;
	
	@Column(name="user_id")
	private Integer userId;
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name="name")
	private String name;
	@Column(name="gender")
	private Integer gender;
	@Column(name="head_url")
	private String headUrl;
	
	@Column(name="id_card")
	private String idCard;
	@Column(name="school_name")
	private String schoolName;
	
	@Column(name="address")
	private String address;
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "create_date")
	private Long createDate;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
