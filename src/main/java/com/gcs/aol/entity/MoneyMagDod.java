package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gcs.sysmgr.entity.IdAbstartEntity;

import java.util.Calendar;

/**
 * 
 * @ClassName: MoneyMagDod
 * @Description: 理财详情表
 * @author Administrator
 * @date 2016年4月11日
   *
 */

@Entity
@Table(name = "tb_moneymanager_due_on_demand")
public class MoneyMagDod extends IdAbstartEntity{

	
	private static final long serialVersionUID = -6173302544488513906L;

	@Column(name = "month")
	private Integer month;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "year_yield")
	private Double yearYield;
	
	@Column(name = "money_limit")
	private Double moneyLimit;
	
	@Column(name = "create_date")
	private Long createDate;

	@Column(name = "content")
	private String content;

	@Transient
	private Double residue;
	
	@Transient
	private Integer percent;

	@Transient
	private Double residue2;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getResidue2() {
		try {
			residue2 = (getMoneyLimit() == null ? 0 : getMoneyLimit())  - (getResidue() == null ? 0 : getResidue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return residue2;
	}

	public void setResidue2(Double residue2) {
		this.residue2 = residue2;
	}

	public Double getResidue() {
		return residue;
	}

	public void setResidue(Double residue) {
		this.residue = residue;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getYearYield() {
		return yearYield;
	}

	public void setYearYield(Double yearYield) {
		this.yearYield = yearYield;
	}

	public Double getMoneyLimit() {
		return moneyLimit;
	}

	public void setMoneyLimit(Double moneyLimit) {
		this.moneyLimit = moneyLimit;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
