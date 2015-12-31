package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name="message")
public class Message extends IdAbstartEntity{

	private static final long serialVersionUID = 2391835447158239779L;

	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	private Users user = new Users();
	
	@Column(name="is_list")
	private Integer isList;
	
	@Column(name="create_date")
	private Long createDate;

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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Integer getIsList() {
		return isList;
	}

	public void setIsList(Integer isList) {
		this.isList = isList;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
