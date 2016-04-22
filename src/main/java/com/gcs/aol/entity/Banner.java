package com.gcs.aol.entity;

import com.gcs.sysmgr.entity.IdAbstartEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/4/22.
 */
@Entity
@Table(name = "tb_banner")
public class Banner extends IdAbstartEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "path")
    private String path ;

    @Column(name = "is_list")
    private Integer isList;

    @Column(name = "create_date")
    private Long createDate;

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getIsList() {
        return isList;
    }

    public void setIsList(Integer isList) {
        this.isList = isList;
    }
}
