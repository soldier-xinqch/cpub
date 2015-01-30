package com.fx.oss.vod.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fx.oss.utils.Page;
import com.fx.oss.vod.model.CourtLiveVod;
import com.fx.oss.vod.model.CourtLiveVodWithBLOBs;


public interface CourtLiveVodService {

	public CourtLiveVodWithBLOBs getCourtLiveVodById(String id) throws SQLException;
	
	public void deleteCourtLiveVodById(String id)throws SQLException;
	
	public  List<CourtLiveVodWithBLOBs>  findAll() throws SQLException;
	
	public void createCourtLiveVod(CourtLiveVodWithBLOBs courtLiveVodWithBLOBs) throws SQLException;
	
	public void updateCourtLiveVod(CourtLiveVodWithBLOBs courtLiveVodWithBLOBs) throws SQLException;
	
	public CourtLiveVodWithBLOBs findByCaseName(String caseName) throws SQLException;
	
	public int findTotalPage() throws SQLException;
	
	List<CourtLiveVodWithBLOBs>  findLiveList()throws SQLException;
	
	List<CourtLiveVodWithBLOBs> getMetaDevByPage(int begin, int size)throws SQLException;

	int getAllMetaDevCount() throws SQLException;
	
	List<CourtLiveVodWithBLOBs> findCourtLiveVodByCaseNum(String caseNum,int begin,int size) throws SQLException;

	Page<CourtLiveVodWithBLOBs> getPage(HttpServletRequest request) throws SQLException;
	
	Page<CourtLiveVodWithBLOBs> findByPage(HttpServletRequest request) throws SQLException;
}
