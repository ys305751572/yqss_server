package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 理财活期/定期基本配置
 */
@Entity
@Table(name = "tb_moneymanager")
public class MoneyMag extends IdAbstartEntity{

	private static final long serialVersionUID = -7462733472334222004L;

	@Column(name = "type")
	private Integer type;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "introduce")
	private String introduce;
	
	@Column(name = "year_yield")
	private String yearYield;
	
	@Column(name = "time_limit")
	private String timelimit; // 期限
	
	@Column(name = "create_date")
	private Long createDate;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getYearYield() {
		return yearYield;
	}

	public void setYearYield(String yearYield) {
		this.yearYield = yearYield;
	}

	public String getTimelimit() {
		return timelimit;
	}

	public void setTimelimit(String timelimit) {
		this.timelimit = timelimit;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
