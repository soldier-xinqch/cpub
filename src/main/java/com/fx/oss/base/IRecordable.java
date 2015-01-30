package com.fx.oss.base;

import java.util.Date;

/**
 * 可记录接口。
 * <p>
 * 记录编辑痕迹。
 * 
 * @author huangwy
 * @version 1.0 2013/6/11
 */
public interface IRecordable {
	public void setCreateTime(Date date);
	public Date getCreateTime();
	public void setUpdateTime(Date date);
	public Date getUpdateTime();
	public String getCreateBy();
	public void setCreateBy(String createdBy);
	public String getLastUpdator();
	public void setLastUpdator(String updator);
}
