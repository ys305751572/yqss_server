package com.gcs.aol.entity;

import com.gcs.sysmgr.entity.IdAbstartEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/4/18 0018.
 */
@Entity
@Table(name = "tb_moneymanager_dq")
public class DQBao extends IdAbstartEntity {

    private static final long serialVersionUID = -7462733472334222004L;

    @Column(name = "money")
    private double money;

    @Column(name = "limit")
    private Integer month;

    @Column(name = "rate")
    private double rate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
