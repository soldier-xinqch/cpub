package com.fx.oss.vod.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.oss.utils.Page;
import com.fx.oss.utils.PaginateUtil;
import com.fx.oss.vod.controller.CourtLiveVodController;
import com.fx.oss.vod.model.CourtLiveVodExample;
import com.fx.oss.vod.model.CourtLiveVodWithBLOBs;
import com.fx.oss.vod.persistence.CourtLiveVodMapper;

@Service("courtLiveVodService")
public class CourtLiveVodServiceImpl implements CourtLiveVodService {

	private static Logger logger = LoggerFactory
			.getLogger(CourtLiveVodServiceImpl.class);

	@Autowired
	private CourtLiveVodMapper courtLiveVodMapper;

	/*
	 * 由ID来获得一个直点播信息
	 */
	@Override
	public CourtLiveVodWithBLOBs getCourtLiveVodById(String id) {
		CourtLiveVodWithBLOBs courtLiveVodWithBLOBs = courtLiveVodMapper
				.selectByPrimaryKey(id);
		return courtLiveVodWithBLOBs;
	}
	
	/*
	 * 由ID来删除信息
	 */
	@Override
	public void deleteCourtLiveVodById(String id) {
		courtLiveVodMapper.deleteByPrimaryKey(id);
	}
	
	/*
	 *查询所有记录
	 */
	@Override
	public List<CourtLiveVodWithBLOBs> findAll() throws SQLException {
		CourtLiveVodExample clve = new CourtLiveVodExample();
		List<CourtLiveVodWithBLOBs> lvList = courtLiveVodMapper
				.selectByExampleWithBLOBs(clve);
		return lvList;
	}

	/*
	 * 新增一条记录
	 */
	@Override
	public void createCourtLiveVod(CourtLiveVodWithBLOBs courtLiveVodWithBLOBs)
			throws SQLException {
		courtLiveVodMapper.insert(courtLiveVodWithBLOBs);
	}

	/*
	 * 由ID来更改一条记录
	 */
	@Override
	public void updateCourtLiveVod(CourtLiveVodWithBLOBs courtLiveVodWithBLOBs)
			throws SQLException {
		courtLiveVodMapper.updateByPrimaryKey(courtLiveVodWithBLOBs);
	}

	/*
	 * 条件查询-根据案号来查找列表
	 */
	@Override
	public CourtLiveVodWithBLOBs findByCaseName(String caseName)
			throws SQLException {
		CourtLiveVodExample clve = new CourtLiveVodExample();
		clve.createCriteria().andCaseNumEqualTo(caseName);
		List<CourtLiveVodWithBLOBs> lvList = courtLiveVodMapper
				.selectByExampleWithBLOBs(clve);
		return lvList.get(0);
	}

	/**
	 *  返回总条数
	 */
	@Override
	public int findTotalPage() throws SQLException {
		int totalPage = courtLiveVodMapper.selectTotalPage();
		return totalPage;
	}

	/*
	 * 根据分页获得直点播列表
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public Page<CourtLiveVodWithBLOBs> getPage(HttpServletRequest request)throws SQLException {
		int totalCount = this.getAllMetaDevCount();
		logger.debug("列表总条数为： " + totalCount);
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 10);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<CourtLiveVodWithBLOBs> lis_dev = this.getMetaDevByPage(startIndex,
				pageSize);
		// for(int i = 0 ; i < lis_dev.size() ; i++){//向设备中添加软件信息
		// CourtLiveVodWithBLOBs dev = lis_dev.get(i);
		// List<CourtLiveVodWithBLOBs> lis = this.getListSoft(dev);
		// dev.setSoftList(lis);
		// }
		Page<CourtLiveVodWithBLOBs> page = new Page<CourtLiveVodWithBLOBs>(
				startIndex, pageSize, totalCount, lis_dev);
		return page;
	}

	/*
	 * 获取数据的条数 
	 * @return
	 */
	@Override
	public int getAllMetaDevCount() throws SQLException{
		return courtLiveVodMapper.getAllMetaDevCount();
	}

	/*
	 * 根据分页获得所有设备信息
	 * 
	 * @param begin
	 * @param size
	 * @return
	 */
	@Override
	public List<CourtLiveVodWithBLOBs> getMetaDevByPage(int begin, int size)throws SQLException {
		return courtLiveVodMapper.getMetaDevByPage(begin, size);
	}

	/*
	 * 条件查询返回直点播信息的列表+分页机制
	 */
	@Override
	public List<CourtLiveVodWithBLOBs> findCourtLiveVodByCaseNum(
			String caseNum, int begin, int size)throws SQLException {
		return courtLiveVodMapper.getCourtLiveVodByCaseNum(caseNum, begin, size);
	}

	/*
	 * 条件查询 -返回页面信息
	 */
	@Override
	public Page<CourtLiveVodWithBLOBs> findByPage(HttpServletRequest request) throws SQLException{
		int totalCount = this.getAllMetaDevCount();
		logger.debug("列表总条数为： " + totalCount);
		String caseNum = request.getParameter("caseNum");
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 10);
		logger.debug("获取分页后的列表，从第" + startIndex + "条起的共" + pageSize + "条记录");
		List<CourtLiveVodWithBLOBs> lis_dev = this.findCourtLiveVodByCaseNum(caseNum, startIndex, pageSize);
		// for(int i = 0 ; i < lis_dev.size() ; i++){//向设备中添加软件信息
		// CourtLiveVodWithBLOBs dev = lis_dev.get(i);
		// List<CourtLiveVodWithBLOBs> lis = this.getListSoft(dev);
		// dev.setSoftList(lis);
		// }
		Page<CourtLiveVodWithBLOBs> page = new Page<CourtLiveVodWithBLOBs>(
				startIndex, pageSize, totalCount, lis_dev);
		return page;
	}
	
	/*
	 * 查询直播列表
	 */
	@Override
	public List<CourtLiveVodWithBLOBs> findLiveList() throws SQLException {
		return courtLiveVodMapper.selectLiveList();
	}

}
