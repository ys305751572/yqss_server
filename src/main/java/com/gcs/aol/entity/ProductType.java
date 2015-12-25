package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 
 * @ClassName: ProductType
 * @Description: 商品类型
 * @author yesong
 * @date 2015年12月23日
 *
 */
@Entity
@Table(name="product_type")
public class ProductType extends IdAbstartEntity{
	
	private static final long serialVersionUID = -8011441773850583968L;

	@Column(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
