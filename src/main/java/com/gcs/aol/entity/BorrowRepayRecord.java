package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name = "repay_record")
public class BorrowRepayRecord extends IdAbstartEntity{

	private static final long serialVersionUID = -1153436278054287161L;

	@Column(name = "borrow_info_id")
	private Integer borrowInfoId;
	
	@Column(name = "money")
	private Double money;
	
	@Column(name = "create_date")
	private Long createDate;

	public Integer getBorrowInfoId() {
		return borrowInfoId;
	}

	public void setBorrowInfoId(Integer borrowInfoId) {
		this.borrowInfoId = borrowInfoId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	
}
