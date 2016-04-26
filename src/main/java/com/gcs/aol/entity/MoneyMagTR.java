package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 理财购买记录表
 */
@Entity
@Table(name = "tb_moneymanager_trading_record")
public class MoneyMagTR extends IdAbstartEntity{
	
	private static final long serialVersionUID = 288973837195710936L;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "user_id")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name = "dod_id")
	private MoneyMagDod dod;
	
	@Column(name = "money")
	private Double money;

	@Column(name = "earnings")
	private Double earnings;
	
	@Column(name = "pay_way")
	private String payWay;
	
	@Column(name = "create_date")
	private Long createDate;

	@Column(name = "status")
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public MoneyMagDod getDod() {
		return dod;
	}

	public void setDod(MoneyMagDod dod) {
		this.dod = dod;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Double getEarnings() {
		return earnings;
	}

	public void setEarnings(Double earnings) {
		this.earnings = earnings;
	}
}
