package com.ht.court.service;

import java.util.List;
import java.util.Map;

import com.ht.court.model.CourtRoom;
import com.hx.util.Page;

/**
 * 法庭信息服务接口。
 * 
 * @author xinqichao
 * @version 1.0 2014/03/23
 *
 */
public interface CourtRoomService {
	
	/**
	 * 分页获取法庭信息。
	 *
	 * @return 返回分页记录。
	 */
	public Page<CourtRoom> findByPage(int startIndex, int pageSize, Map<String,Object> queryParams, String... orderBys);
	
	/**
	 * 新建法庭信息。
	 * 
	 * @param courtRoom 新增的法庭信息对象。
	 */
	public void create(CourtRoom courtRoom);
	
	/**
	 * 根据ID获取法庭信息实体。
	 * 
	 * @param id 法庭信息 ID。
	 */
	public CourtRoom get(String id);
	
	/**
	 * 更新法庭信息。
	 * 
	 * @param courtRoom 变更的法庭信息对象。
	 */
	public void update(CourtRoom courtRoom);
	
	/**
	 * 根据ID删除法庭信息实体。
	 * 
	 * 
	 * @param id 法庭信息 ID。
	 */
	public void delete(String id);
	
	/**
	 * 通过排期表中的法庭ID来查询法庭信息
	 * @param roomId
	 * @return
	 */
	public List<CourtRoom> findByPlanRoomId(String courtId)  ;
	
	/**
	 * 通过法院ID来获取法庭列表
	 * @param OrgId
	 * @return
	 * @ 
	 */
	public List<CourtRoom> findByOrgId(List<String> courtIds,int begin,int size)  ;
	
	/**
	 * 通过法院ID和法庭名称查询法庭信息
	 * 
	 * @param courtRoomName
	 * @param courtId
	 * @return
	 */
	public CourtRoom getCourtRoomIdByCourtRoomName(String courtRoomName,String courtId)  ;
	/**
	 * 通过法院ID来获取该法院所有的法庭数
	 * @param courtId
	 * @return
	 * @ 
	 */
	public int getRoomNumByCourtId(List<String> courtIds)  ;
	/**
	 * 法庭分页机制
	 * @param request
	 * @return
	 * @ 
	 */
	Page<CourtRoom> getPage(int startIndex, int pageSize,List<String> courtIds)  ;
}