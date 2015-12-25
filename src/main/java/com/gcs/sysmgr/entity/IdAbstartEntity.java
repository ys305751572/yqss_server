package com.gcs.sysmgr.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class IdAbstartEntity implements Serializable{

	
	private static final long serialVersionUID = -4296775453239391919L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
