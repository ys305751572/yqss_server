package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name = "buy_repay_record")
public class ProductBorrowRepayRecord extends IdAbstartEntity{

	private static final long serialVersionUID = -3554633858193104732L;

	@Column(name = "borrow_info_id")
	private Integer borrow_info_id;
	
	@Column(name = "money")
	private Double money;
	
	@Column(name = "create_date")
	private Long createDate;

	public Integer getBorrow_info_id() {
		return borrow_info_id;
	}

	public void setBorrow_info_id(Integer borrow_info_id) {
		this.borrow_info_id = borrow_info_id;
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
