package com.gcs.aol.entity;

import javax.persistence.*;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name = "buy_repay_record")
public class ProductBorrowRepayRecord extends IdAbstartEntity{

	private static final long serialVersionUID = -3554633858193104732L;

	@ManyToOne
	@JoinColumn(name = "borrow_info_id")
	private ProductBorrow productBorrow;
	
	@Column(name = "money")
	private Double money;
	
	@Column(name = "create_date")
	private Long createDate;

	public ProductBorrow getProductBorrow() {
		return productBorrow;
	}

	public void setProductBorrow(ProductBorrow productBorrow) {
		this.productBorrow = productBorrow;
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
