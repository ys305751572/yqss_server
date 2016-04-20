package com.gcs.aol.entity;

import com.gcs.sysmgr.entity.IdAbstartEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/4/18 0018.
 */
@Entity
@Table(name = "tb_moneymanager_hq")
public class HQBao extends IdAbstartEntity {

    private static final long serialVersionUID = -7462733472334222004L;

    @Column(name = "money")
    private double money;

    @Column(name = "income")
    private double earning;

    @Column(name = "userId")
    private Integer userId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getEarning() {
        return earning;
    }

    public void setEarning(double earning) {
        this.earning = earning;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
