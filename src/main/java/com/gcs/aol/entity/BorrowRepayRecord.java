package com.gcs.aol.entity;

import javax.persistence.*;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name = "repay_record")
public class BorrowRepayRecord extends IdAbstartEntity{

	private static final long serialVersionUID = -1153436278054287161L;

	@ManyToOne
	@JoinColumn(name = "borrow_info_id")
	private Borrow borrow;
	
	@Column(name = "money")
	private Double money;
	
	@Column(name = "create_date")
	private Long createDate;

	public Borrow getBorrow() {
		return borrow;
	}

	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
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
