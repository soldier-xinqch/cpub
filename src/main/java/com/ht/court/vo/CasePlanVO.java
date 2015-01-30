package com.ht.court.vo;

import java.io.Serializable;

import com.ht.court.model.CourtCasePlan;
import com.ht.court.model.CourtCaseWithBLOBs;

/**
 * 案件和排期值对象。
 * <p>
 * 将案件和排期实体组合作为值传递。
 * 
 * @author huangweiyong
 * @version 2.0.0.0 2014/4/9
 * 
 */
public class CasePlanVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CourtCaseWithBLOBs courtCase;
	private CourtCasePlan casePlan;
	
	public CasePlanVO(CourtCaseWithBLOBs courtCase,CourtCasePlan casePlan){
		this.courtCase=courtCase;
		this.casePlan=casePlan;
	}
	
	public CasePlanVO(){
		
	}

	/**
	 * 案件对象。
	 * 
	 * @return
	 */
	public CourtCaseWithBLOBs getCourtCase() {
		return courtCase;
	}

	public void setCourtCase(CourtCaseWithBLOBs courtCase) {
		this.courtCase = courtCase;
	}

	/**
	 * 案件排期对象。
	 * 
	 * @return
	 */
	public CourtCasePlan getCasePlan() {
		return casePlan;
	}

	public void setCasePlan(CourtCasePlan casePlan) {
		this.casePlan = casePlan;
	}

}
