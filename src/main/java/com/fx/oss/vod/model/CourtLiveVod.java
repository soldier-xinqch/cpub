package com.fx.oss.vod.model;

import java.util.Date;

public class CourtLiveVod {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.ID
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.CASE_NUM
     *
     * @mbggenerated
     */
    private String caseNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.PARENT_COURT_ID
     *
     * @mbggenerated
     */
    private String parentCourtId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.COURT_ID
     *
     * @mbggenerated
     */
    private String courtId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.COURT_NAME
     *
     * @mbggenerated
     */
    private String courtName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.COURT_ROOM_ID
     *
     * @mbggenerated
     */
    private String courtRoomId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.COURT_ROOM_NAME
     *
     * @mbggenerated
     */
    private String courtRoomName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.JUDGE_ID
     *
     * @mbggenerated
     */
    private String judgeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.JUDGE_NAME
     *
     * @mbggenerated
     */
    private String judgeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.CASE_TYPE_ID
     *
     * @mbggenerated
     */
    private String caseTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.CASE_TYPE_NAME
     *
     * @mbggenerated
     */
    private String caseTypeName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.CASE_NAME
     *
     * @mbggenerated
     */
    private String caseName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.ACCUSER
     *
     * @mbggenerated
     */
    private String accuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.ACCUSER_LAWER
     *
     * @mbggenerated
     */
    private String accuserLawer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.ACCUSED
     *
     * @mbggenerated
     */
    private String accused;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.ACCUSERD_LAWER
     *
     * @mbggenerated
     */
    private String accuserdLawer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.REGISTER_TIME
     *
     * @mbggenerated
     */
    private Date registerTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.LIVE_URL
     *
     * @mbggenerated
     */
    private String liveUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.VOD_URL
     *
     * @mbggenerated
     */
    private String vodUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.VOD_FILE_PATH
     *
     * @mbggenerated
     */
    private String vodFilePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.HEAR_TIME
     *
     * @mbggenerated
     */
    private Date hearTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.TRAIL_STATUS
     *
     * @mbggenerated
     */
    private String trailStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.DEL_FLAG
     *
     * @mbggenerated
     */
    private Byte delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.CREATE_USER
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.CREATE_USER_ID
     *
     * @mbggenerated
     */
    private String createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.CREATE_TIME
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.UPDATE_USER
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.UPDATE_USER_ID
     *
     * @mbggenerated
     */
    private String updateUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.UPDATE_TIME
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_LIVE_VOD.ROOT_ORG_ID
     *
     * @mbggenerated
     */
    private String rootOrgId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.ID
     *
     * @return the value of COURT_LIVE_VOD.ID
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.ID
     *
     * @param id the value for COURT_LIVE_VOD.ID
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.CASE_NUM
     *
     * @return the value of COURT_LIVE_VOD.CASE_NUM
     *
     * @mbggenerated
     */
    public String getCaseNum() {
        return caseNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.CASE_NUM
     *
     * @param caseNum the value for COURT_LIVE_VOD.CASE_NUM
     *
     * @mbggenerated
     */
    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum == null ? null : caseNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.PARENT_COURT_ID
     *
     * @return the value of COURT_LIVE_VOD.PARENT_COURT_ID
     *
     * @mbggenerated
     */
    public String getParentCourtId() {
        return parentCourtId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.PARENT_COURT_ID
     *
     * @param parentCourtId the value for COURT_LIVE_VOD.PARENT_COURT_ID
     *
     * @mbggenerated
     */
    public void setParentCourtId(String parentCourtId) {
        this.parentCourtId = parentCourtId == null ? null : parentCourtId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.COURT_ID
     *
     * @return the value of COURT_LIVE_VOD.COURT_ID
     *
     * @mbggenerated
     */
    public String getCourtId() {
        return courtId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.COURT_ID
     *
     * @param courtId the value for COURT_LIVE_VOD.COURT_ID
     *
     * @mbggenerated
     */
    public void setCourtId(String courtId) {
        this.courtId = courtId == null ? null : courtId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.COURT_NAME
     *
     * @return the value of COURT_LIVE_VOD.COURT_NAME
     *
     * @mbggenerated
     */
    public String getCourtName() {
        return courtName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.COURT_NAME
     *
     * @param courtName the value for COURT_LIVE_VOD.COURT_NAME
     *
     * @mbggenerated
     */
    public void setCourtName(String courtName) {
        this.courtName = courtName == null ? null : courtName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.COURT_ROOM_ID
     *
     * @return the value of COURT_LIVE_VOD.COURT_ROOM_ID
     *
     * @mbggenerated
     */
    public String getCourtRoomId() {
        return courtRoomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.COURT_ROOM_ID
     *
     * @param courtRoomId the value for COURT_LIVE_VOD.COURT_ROOM_ID
     *
     * @mbggenerated
     */
    public void setCourtRoomId(String courtRoomId) {
        this.courtRoomId = courtRoomId == null ? null : courtRoomId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.COURT_ROOM_NAME
     *
     * @return the value of COURT_LIVE_VOD.COURT_ROOM_NAME
     *
     * @mbggenerated
     */
    public String getCourtRoomName() {
        return courtRoomName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.COURT_ROOM_NAME
     *
     * @param courtRoomName the value for COURT_LIVE_VOD.COURT_ROOM_NAME
     *
     * @mbggenerated
     */
    public void setCourtRoomName(String courtRoomName) {
        this.courtRoomName = courtRoomName == null ? null : courtRoomName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.JUDGE_ID
     *
     * @return the value of COURT_LIVE_VOD.JUDGE_ID
     *
     * @mbggenerated
     */
    public String getJudgeId() {
        return judgeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.JUDGE_ID
     *
     * @param judgeId the value for COURT_LIVE_VOD.JUDGE_ID
     *
     * @mbggenerated
     */
    public void setJudgeId(String judgeId) {
        this.judgeId = judgeId == null ? null : judgeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.JUDGE_NAME
     *
     * @return the value of COURT_LIVE_VOD.JUDGE_NAME
     *
     * @mbggenerated
     */
    public String getJudgeName() {
        return judgeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.JUDGE_NAME
     *
     * @param judgeName the value for COURT_LIVE_VOD.JUDGE_NAME
     *
     * @mbggenerated
     */
    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName == null ? null : judgeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.CASE_TYPE_ID
     *
     * @return the value of COURT_LIVE_VOD.CASE_TYPE_ID
     *
     * @mbggenerated
     */
    public String getCaseTypeId() {
        return caseTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.CASE_TYPE_ID
     *
     * @param caseTypeId the value for COURT_LIVE_VOD.CASE_TYPE_ID
     *
     * @mbggenerated
     */
    public void setCaseTypeId(String caseTypeId) {
        this.caseTypeId = caseTypeId == null ? null : caseTypeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.CASE_TYPE_NAME
     *
     * @return the value of COURT_LIVE_VOD.CASE_TYPE_NAME
     *
     * @mbggenerated
     */
    public String getCaseTypeName() {
        return caseTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.CASE_TYPE_NAME
     *
     * @param caseTypeName the value for COURT_LIVE_VOD.CASE_TYPE_NAME
     *
     * @mbggenerated
     */
    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName == null ? null : caseTypeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.CASE_NAME
     *
     * @return the value of COURT_LIVE_VOD.CASE_NAME
     *
     * @mbggenerated
     */
    public String getCaseName() {
        return caseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.CASE_NAME
     *
     * @param caseName the value for COURT_LIVE_VOD.CASE_NAME
     *
     * @mbggenerated
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName == null ? null : caseName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.ACCUSER
     *
     * @return the value of COURT_LIVE_VOD.ACCUSER
     *
     * @mbggenerated
     */
    public String getAccuser() {
        return accuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.ACCUSER
     *
     * @param accuser the value for COURT_LIVE_VOD.ACCUSER
     *
     * @mbggenerated
     */
    public void setAccuser(String accuser) {
        this.accuser = accuser == null ? null : accuser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.ACCUSER_LAWER
     *
     * @return the value of COURT_LIVE_VOD.ACCUSER_LAWER
     *
     * @mbggenerated
     */
    public String getAccuserLawer() {
        return accuserLawer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.ACCUSER_LAWER
     *
     * @param accuserLawer the value for COURT_LIVE_VOD.ACCUSER_LAWER
     *
     * @mbggenerated
     */
    public void setAccuserLawer(String accuserLawer) {
        this.accuserLawer = accuserLawer == null ? null : accuserLawer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.ACCUSED
     *
     * @return the value of COURT_LIVE_VOD.ACCUSED
     *
     * @mbggenerated
     */
    public String getAccused() {
        return accused;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.ACCUSED
     *
     * @param accused the value for COURT_LIVE_VOD.ACCUSED
     *
     * @mbggenerated
     */
    public void setAccused(String accused) {
        this.accused = accused == null ? null : accused.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.ACCUSERD_LAWER
     *
     * @return the value of COURT_LIVE_VOD.ACCUSERD_LAWER
     *
     * @mbggenerated
     */
    public String getAccuserdLawer() {
        return accuserdLawer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.ACCUSERD_LAWER
     *
     * @param accuserdLawer the value for COURT_LIVE_VOD.ACCUSERD_LAWER
     *
     * @mbggenerated
     */
    public void setAccuserdLawer(String accuserdLawer) {
        this.accuserdLawer = accuserdLawer == null ? null : accuserdLawer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.REGISTER_TIME
     *
     * @return the value of COURT_LIVE_VOD.REGISTER_TIME
     *
     * @mbggenerated
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.REGISTER_TIME
     *
     * @param registerTime the value for COURT_LIVE_VOD.REGISTER_TIME
     *
     * @mbggenerated
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.LIVE_URL
     *
     * @return the value of COURT_LIVE_VOD.LIVE_URL
     *
     * @mbggenerated
     */
    public String getLiveUrl() {
        return liveUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.LIVE_URL
     *
     * @param liveUrl the value for COURT_LIVE_VOD.LIVE_URL
     *
     * @mbggenerated
     */
    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl == null ? null : liveUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.VOD_URL
     *
     * @return the value of COURT_LIVE_VOD.VOD_URL
     *
     * @mbggenerated
     */
    public String getVodUrl() {
        return vodUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.VOD_URL
     *
     * @param vodUrl the value for COURT_LIVE_VOD.VOD_URL
     *
     * @mbggenerated
     */
    public void setVodUrl(String vodUrl) {
        this.vodUrl = vodUrl == null ? null : vodUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.VOD_FILE_PATH
     *
     * @return the value of COURT_LIVE_VOD.VOD_FILE_PATH
     *
     * @mbggenerated
     */
    public String getVodFilePath() {
        return vodFilePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.VOD_FILE_PATH
     *
     * @param vodFilePath the value for COURT_LIVE_VOD.VOD_FILE_PATH
     *
     * @mbggenerated
     */
    public void setVodFilePath(String vodFilePath) {
        this.vodFilePath = vodFilePath == null ? null : vodFilePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.HEAR_TIME
     *
     * @return the value of COURT_LIVE_VOD.HEAR_TIME
     *
     * @mbggenerated
     */
    public Date getHearTime() {
        return hearTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.HEAR_TIME
     *
     * @param hearTime the value for COURT_LIVE_VOD.HEAR_TIME
     *
     * @mbggenerated
     */
    public void setHearTime(Date hearTime) {
        this.hearTime = hearTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.TRAIL_STATUS
     *
     * @return the value of COURT_LIVE_VOD.TRAIL_STATUS
     *
     * @mbggenerated
     */
    public String getTrailStatus() {
        return trailStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.TRAIL_STATUS
     *
     * @param trailStatus the value for COURT_LIVE_VOD.TRAIL_STATUS
     *
     * @mbggenerated
     */
    public void setTrailStatus(String trailStatus) {
        this.trailStatus = trailStatus == null ? null : trailStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.DEL_FLAG
     *
     * @return the value of COURT_LIVE_VOD.DEL_FLAG
     *
     * @mbggenerated
     */
    public Byte getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.DEL_FLAG
     *
     * @param delFlag the value for COURT_LIVE_VOD.DEL_FLAG
     *
     * @mbggenerated
     */
    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.CREATE_USER
     *
     * @return the value of COURT_LIVE_VOD.CREATE_USER
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.CREATE_USER
     *
     * @param createUser the value for COURT_LIVE_VOD.CREATE_USER
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.CREATE_USER_ID
     *
     * @return the value of COURT_LIVE_VOD.CREATE_USER_ID
     *
     * @mbggenerated
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.CREATE_USER_ID
     *
     * @param createUserId the value for COURT_LIVE_VOD.CREATE_USER_ID
     *
     * @mbggenerated
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.CREATE_TIME
     *
     * @return the value of COURT_LIVE_VOD.CREATE_TIME
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.CREATE_TIME
     *
     * @param createTime the value for COURT_LIVE_VOD.CREATE_TIME
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.UPDATE_USER
     *
     * @return the value of COURT_LIVE_VOD.UPDATE_USER
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.UPDATE_USER
     *
     * @param updateUser the value for COURT_LIVE_VOD.UPDATE_USER
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.UPDATE_USER_ID
     *
     * @return the value of COURT_LIVE_VOD.UPDATE_USER_ID
     *
     * @mbggenerated
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.UPDATE_USER_ID
     *
     * @param updateUserId the value for COURT_LIVE_VOD.UPDATE_USER_ID
     *
     * @mbggenerated
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.UPDATE_TIME
     *
     * @return the value of COURT_LIVE_VOD.UPDATE_TIME
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.UPDATE_TIME
     *
     * @param updateTime the value for COURT_LIVE_VOD.UPDATE_TIME
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_LIVE_VOD.ROOT_ORG_ID
     *
     * @return the value of COURT_LIVE_VOD.ROOT_ORG_ID
     *
     * @mbggenerated
     */
    public String getRootOrgId() {
        return rootOrgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_LIVE_VOD.ROOT_ORG_ID
     *
     * @param rootOrgId the value for COURT_LIVE_VOD.ROOT_ORG_ID
     *
     * @mbggenerated
     */
    public void setRootOrgId(String rootOrgId) {
        this.rootOrgId = rootOrgId == null ? null : rootOrgId.trim();
    }
}