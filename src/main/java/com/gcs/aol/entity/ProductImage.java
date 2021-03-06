package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 
 * @ClassName: ProductImage
 * @Description: 商品图片
 * @author yesong
 * @date 2015年12月23日
     *
 */
@Entity
@Table(name = "product_images")
public class ProductImage extends IdAbstartEntity{

	private static final long serialVersionUID = -1861598837623329337L;

	@ManyToOne(optional = true,fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="height")
	private Double height;
	
	@Column(name="width")
	private Double width;
	
	@Column(name="small_url")
	private String smallUrl;
	
	@Column(name="create_date")
	private Long createDate;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
