package com.gcs.aol.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name = "tb_moneymanager_common")
public class MoneyMagCommon extends IdAbstartEntity{

	private static final long serialVersionUID = -1471096814020236716L;

	private String content;
	
	private Integer type;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}