package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name="bank_type")
public class Bank extends IdAbstartEntity{

	private static final long serialVersionUID = 2301705321083119335L;

	@Column(name="bank_name")
	private String name;
	
	@Column(name="create_date")
	private Long createDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
