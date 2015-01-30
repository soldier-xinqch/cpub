package com.ht.court.service;

import java.io.IOException;

import com.ht.court.model.CourtCasePlan;

public interface PauseAndGoService {

	public void toPause(String id);
	
	public void toGo(String id);
	
	public void pushToPause(CourtCasePlan courtCasePlan) throws IOException;
	
	public void pushToGo(CourtCasePlan courtCasePlan) throws IOException;
}
