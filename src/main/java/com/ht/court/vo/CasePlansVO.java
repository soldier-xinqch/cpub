package com.ht.court.vo;

import java.io.Serializable;
import java.util.List;

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
public class CasePlansVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CourtCaseWithBLOBs courtCase;
	private List<CourtCasePlan> casePlans;
	
	public CasePlansVO(CourtCaseWithBLOBs courtCase,List<CourtCasePlan> casePlans){
		this.courtCase=courtCase;
		this.casePlans=casePlans;
	}
	
	public CasePlansVO(){
		
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
	 * 案件排期列表。
	 * 
	 * @return
	 */
	public List<CourtCasePlan> getCasePlans() {
		return casePlans;
	}

	public void setCasePlans(List<CourtCasePlan> casePlans) {
		this.casePlans = casePlans;
	}
}
