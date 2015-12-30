package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name="commons")
public class Commons extends IdAbstartEntity{

	private static final long serialVersionUID = 5431537259071904589L;

	@Column(name="qq")
	private String qq;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="rate")
	private Double rate;

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}
