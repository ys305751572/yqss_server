package com.gcs.aol.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gcs.sysmgr.entity.IdAbstartEntity;

/**
 * 
 * @ClassName: MoneyMagUser
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2016年4月11日
 *
 */
@Entity
@Table(name = "tb_moneymanager_user")
public class MoneyMagUser extends IdAbstartEntity{

	
	private static final long serialVersionUID = 2291631635371767466L;

	@OneToOne
	@JoinColumn(name = "user_id")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name = "dod_id")
	private MoneyMagDod dod;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "name")
	private String tradingPassword;
	
	@Column(name = "id_card")
	private String idCard;
	
	@Column(name = "bank_card")
	private String bankCard;
	
	@Column(name = "create_date")
	private Long createDate;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public MoneyMagDod getDod() {
		return dod;
	}

	public void setDod(MoneyMagDod dod) {
		this.dod = dod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTradingPassword() {
		return tradingPassword;
	}

	public void setTradingPassword(String tradingPassword) {
		this.tradingPassword = tradingPassword;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	
	
}
