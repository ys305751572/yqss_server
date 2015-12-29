package com.gcs.aol.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 二手商品
 * @author yesong
 *
 */
@Entity
@Table(name="second_hand")
public class SecondHandProduct extends IdAbstartEntity{

	private static final long serialVersionUID = -8611446086016123179L;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="price")
	private double price;
	
	@OneToOne
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	public Users user;
	
	@OneToMany
	@JoinColumn(name="second_hand_id")
	public List<SecondHandProductImage> picList;
	
	@Column(name="create_date")
	private Long createDate;
	
	public List<SecondHandProductImage> getPicList() {
		return picList;
	}

	public void setPicList(List<SecondHandProductImage> picList) {
		this.picList = picList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
