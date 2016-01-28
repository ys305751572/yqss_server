package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 
 * @ClassName: Contants
 * @Description: 用户联系人
 * @author yesong
 * @date 2016年1月16日
     *
 */
@Entity
@Table(name="contants")
public class Contants extends IdAbstartEntity{

	private static final long serialVersionUID = 5310909153440434281L;

	@Column(name="user_id")
	private Integer userId; 
	
	@Column(name="contants_name")
	private String contantName;
	
	@Column(name="mobile")
	private String mobile;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContantName() {
		return contantName;
	}

	public void setContantName(String contantName) {
		this.contantName = contantName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
