package com.ht.court.model;

import java.util.List;

public class VodFileUrls extends CourtCasePlan {
	
	private static final long serialVersionUID = 5349034429444549193L;
	private List<String> vodFileUrls;

	public List<String> getVodFileUrls() {
		return vodFileUrls;
	}

	public void setVodFileUrls(List<String> vodFileUrls) {
		this.vodFileUrls = vodFileUrls;
	}
}
