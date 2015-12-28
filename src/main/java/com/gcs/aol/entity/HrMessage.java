package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

@Entity
@Table(name="hr_message")
public class HrMessage extends IdAbstartEntity{

	private static final long serialVersionUID = -4822181679029152168L;

	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="salary_min")
	private Integer salaryMin;
	
	@Column(name="salary_max")
	private Integer salaryMax;
	
	@Column(name="tel")
	private String tel ;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="company")
	private String company;
	
	@Column(name="address")
	private String address;
	
	@Column(name="position")
	private String position;
	
	@Column(name="counts")
	private Integer counts;
	
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

	public Integer getSalaryMin() {
		return salaryMin;
	}

	public void setSalaryMin(Integer salaryMin) {
		this.salaryMin = salaryMin;
	}

	public Integer getSalaryMax() {
		return salaryMax;
	}

	public void setSalaryMax(Integer salaryMax) {
		this.salaryMax = salaryMax;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
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
