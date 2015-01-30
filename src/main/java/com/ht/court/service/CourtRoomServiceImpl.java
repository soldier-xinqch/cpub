package com.ht.court.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ht.court.mapper.CourtRoomMapper;
import com.ht.court.model.CourtCasePlan;
import com.ht.court.model.CourtRoom;
import com.ht.court.model.CourtRoomExample;
import com.hx.service.TpOrgService;
import com.hx.util.Page;


/**
 * 法庭信息服务实现。
 * 
 * @author xinqichao
 * @version 1.0 2014/03/23
 *
 */
@Transactional
@Service("courtRoomService")
public class CourtRoomServiceImpl implements CourtRoomService {
	
	private static final Logger logger = LoggerFactory.getLogger(CourtRoomServiceImpl.class);
	
	@Autowired
	private CourtRoomMapper mapper;
	@Autowired
	private TpOrgService tpOrgService;
	@Autowired
	private CourtCasePlanService courtCasePlanService;
	
	@Override
	public Page<CourtRoom> findByPage(int startIndex, int pageSize, Map<String,Object> queryParams, String... orderBys){
		CourtRoomExample qbe=new CourtRoomExample();
		qbe.createCriteria().andDelFlagEqualTo(false);
		List<CourtRoom> courtRoomList=mapper.selectByExample(qbe);
		Page<CourtRoom> page=new Page<CourtRoom>();
		page.setResults(courtRoomList);
		return page;
	}
	@Override
	public void create(CourtRoom courtRoom) {
		courtRoom.genUUIDIfNeeded();
		mapper.insert(courtRoom);
	}
	@Override
	public CourtRoom get(String id) {
		CourtRoomExample qbe=new CourtRoomExample();
		qbe.createCriteria().andIdEqualTo(id).andDelFlagEqualTo(false);
		List<CourtRoom> list=mapper.selectByExample(qbe);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	@Override
	public void update(CourtRoom courtRoom) {
		courtRoom.setUpTime();
		mapper.updateByPrimaryKey(courtRoom);
	}
	@Override
	public void delete(String id) {
		CourtRoom courtRoom=this.get(id);
		courtRoom.setDelFlag(true);
		courtRoom.setUpTime();
		this.update(courtRoom);
	}
	/**
	 * 通过排期表法庭ID得到法院列表
	 */
	@Override
	public List<CourtRoom> findByPlanRoomId(String courtId)   {
		List<CourtCasePlan> courtCasePlan;
		try {
			courtCasePlan = courtCasePlanService.findByCourtId(courtId);
			if(courtCasePlan.equals(null)){
				logger.debug("<<<<排期为空，没有要开放直播的法庭>>>>");
			}
			List<String> roomId = new ArrayList<String>();
			List<CourtRoom> courtRoom = mapper.selectRoomByPlanRoomId(roomId);
			return courtRoom;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public List<CourtRoom> findByOrgId(List<String> courtIds,int begin,int size)   {
		List<CourtRoom> courtRoom;
		try {
			courtRoom = mapper.selectByOrgId(courtIds, begin, size);
			return courtRoom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public CourtRoom getCourtRoomIdByCourtRoomName(String courtRoomName,String courtId)
			  {
		CourtRoomExample qbe = new CourtRoomExample();
		qbe.createCriteria().andDelFlagEqualTo(false).andRoomNameEqualTo(courtRoomName).andOrgIdEqualTo(courtId);
		List<CourtRoom> list=mapper.selectByExample(qbe);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public int getRoomNumByCourtId(List<String> courtIds)   {
		return mapper.getRoomNumByCourtId(courtIds);
	}

	@Override
	public Page<CourtRoom> getPage(int startIndex, int pageSize,List<String> courtIds)
			  {
		
		long time = System.currentTimeMillis();
		
		int totalCount = this.getRoomNumByCourtId(courtIds);
		logger.debug("列表总条数为： " + totalCount);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<CourtRoom> lis_dev = this.findByOrgId(courtIds, startIndex, pageSize);
		Page<CourtRoom> page = new Page<CourtRoom>(
				startIndex, pageSize, totalCount, lis_dev);
		
		System.out.println("Room 查询结果：" + (System.currentTimeMillis() - time));
		
		return page;
	}
}