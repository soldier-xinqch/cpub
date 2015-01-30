package com.fx.oss.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 抽象实体。
 * <p>
 * 用作所有实体的父类。
 * 
 * @author huangwy
 * @version 1.0 2013/6/11
 * 
 */
public abstract class AbstractModel implements IPersistable,IRecordable,Serializable{

	private Long id;
	private Date createTime;
	private Date updateTime;
	private String lastUpdator;
	private String createBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getLastUpdator() {
		return lastUpdator;
	}

	public void setLastUpdator(String lastUpdator) {
		this.lastUpdator = lastUpdator;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	

}
