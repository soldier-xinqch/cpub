package com.ht.court.model;

import java.util.Date;

public class VodPermitFlag extends CourtCaseWithBLOBs{

	private static final long serialVersionUID = 1L;

	private boolean vodPlayFlag;
	
	private Date beginTime;
	
	private Date endTime;

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isVodPlayFlag() {
		return vodPlayFlag;
	}

	public void setVodPlayFlag(boolean vodPlayFlag) {
		this.vodPlayFlag = vodPlayFlag;
	}
	
}
