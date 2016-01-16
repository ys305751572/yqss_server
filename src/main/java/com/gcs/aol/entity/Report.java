package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 
 * @ClassName: Report
 * @Description: 举报
 * @author yesong
 * @date 2016年1月16日
 *
 */
@Entity
@Table(name="report")
public class Report extends IdAbstartEntity {

	private static final long serialVersionUID = -8467822356726947743L;

	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name="sh_id")
	private SecondHandProduct shp;
	
	@Column(name="content")
	private String content;
	
	@Column(name="create_date")
	private Long createDate;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public SecondHandProduct getShp() {
		return shp;
	}

	public void setShp(SecondHandProduct shp) {
		this.shp = shp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
}
