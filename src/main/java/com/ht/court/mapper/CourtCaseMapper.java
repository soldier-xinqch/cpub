package com.ht.court.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ht.court.model.CourtCase;
import com.ht.court.model.CourtCaseExample;
import com.ht.court.model.CourtCaseWithBLOBs;

public interface CourtCaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int countByExample(CourtCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int deleteByExample(CourtCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int insert(CourtCaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int insertSelective(CourtCaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    List<CourtCaseWithBLOBs> selectByExampleWithBLOBs(CourtCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    List<CourtCaseWithBLOBs> selectByExample(CourtCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    CourtCaseWithBLOBs selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CourtCaseWithBLOBs record, @Param("example") CourtCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") CourtCaseWithBLOBs record, @Param("example") CourtCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CourtCase record, @Param("example") CourtCaseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CourtCaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(CourtCaseWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_CASE
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CourtCase record);
    
    /**
     * 案件列表分页查询
     * @param begin
     * @param size
     * @return List<CourtLiveVodWithBLOBs>
     */
	List<CourtCaseWithBLOBs> getMetaDevByPage(@Param("begin")int begin, @Param("size")int size);

	/**
	 * 获得列表总数
	 * @return
	 */
	int getAllMetaDevCount();
	/**
	 * 直播列表
	 * @return
	 */
	int getTotalNumByCourtId(@Param("list") List<String> list);
	/**
	 *  根据案号，开始时间，结束时间以及法院的ID来获取到案件总数
	 * @param list
	 * @param caseNum
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	//TODO
	int getTotalNumByCaseNum(@Param("list") List<String> list,@Param("caseNum") String caseNum,@Param("caseType") String caseType,@Param("startTime") Date startTime,@Param("endTime") Date endTime);
	/**
	 *  根据法院ID分页显示案件信息
	 * @param list
	 * @param begin
	 * @param size
	 * @return
	 */
	List<CourtCaseWithBLOBs> getCaseByCourtId(@Param("list") List<String> list,@Param("begin")int begin, @Param("size")int size);
	/**
	 * 获得直播案件列表
	 * @return
	 */
	List<CourtCaseWithBLOBs> selectLiveList();
	/**
	 * 
	 * @param list
	 * @param begin
	 * @param size
	 * @return
	 */
	List<CourtCaseWithBLOBs> getCourtCaseById(@Param("list") List<String> list,@Param("begin")int begin, @Param("size")int size);
	/**
	 *   条件查询分页显示
	 * @param list
	 * @param caseNum
	 * @param begin
	 * @param size
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	//TODO
	List<CourtCaseWithBLOBs> findCourtCaseByCaseNum
		(@Param("list") List<String> list,@Param("caseNum") String caseNum,@Param("caseType") String caseType,@Param("begin")int begin, @Param("size")int size
					,@Param("startTime") Date startTime,@Param("endTime") Date endTime);
	/**
	 * 根据条件查询案件。
	 * 
	 * @param map 查询条件。
	 * @return 返回案件列表。
	 */
	List<CourtCaseWithBLOBs> searchByQueryConditions(Map<String,Object> map);
}