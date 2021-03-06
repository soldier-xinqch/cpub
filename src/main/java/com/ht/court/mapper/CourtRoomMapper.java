package com.ht.court.mapper;

import com.ht.court.model.CourtRoom;
import com.ht.court.model.CourtRoomExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CourtRoomMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    int countByExample(CourtRoomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    int deleteByExample(CourtRoomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    int insert(CourtRoom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    int insertSelective(CourtRoom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    List<CourtRoom> selectByExample(CourtRoomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    CourtRoom selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CourtRoom record, @Param("example") CourtRoomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CourtRoom record, @Param("example") CourtRoomExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CourtRoom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURT_ROOM
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CourtRoom record);
    /**
     *  通过排期中Court_room_id 来查询列表
     * @param list
     * @return
     */
    List<CourtRoom> selectRoomByPlanRoomId(@Param("list") List<String> list);
    /**
     * 通过OrgID来得到法庭列表
     * @param orgId
     * @return
     */
    List<CourtRoom>  selectByOrgId(@Param("list") List<String> list,@Param("begin")int begin, @Param("size")int size);
    /**
     *  根据法院Id获取法庭列表
     * @param list
     * @return
     */
    int getRoomNumByCourtId(@Param("list") List<String> list);
}