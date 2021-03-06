package com.ht.court.model;

import com.hx.HxModel;

public class CourtRoom extends HxModel {
 
	private static final long serialVersionUID = -3779481233637514365L;
	
	private String courtName;
	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_ROOM.ROOM_NAME
     *
     * @mbggenerated
     */
    private String roomName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_ROOM.ROOM_TYPE
     *
     * @mbggenerated
     */
    private String roomType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_ROOM.WWW_LIVE_URL
     *
     * @mbggenerated
     */
    private String wwwLiveUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURT_ROOM.ROOM_MEMO
     *
     * @mbggenerated
     */
    private String roomMemo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_ROOM.ROOM_NAME
     *
     * @return the value of COURT_ROOM.ROOM_NAME
     *
     * @mbggenerated
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_ROOM.ROOM_NAME
     *
     * @param roomName the value for COURT_ROOM.ROOM_NAME
     *
     * @mbggenerated
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_ROOM.ROOM_TYPE
     *
     * @return the value of COURT_ROOM.ROOM_TYPE
     *
     * @mbggenerated
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_ROOM.ROOM_TYPE
     *
     * @param roomType the value for COURT_ROOM.ROOM_TYPE
     *
     * @mbggenerated
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_ROOM.WWW_LIVE_URL
     *
     * @return the value of COURT_ROOM.WWW_LIVE_URL
     *
     * @mbggenerated
     */
    public String getWwwLiveUrl() {
        return wwwLiveUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_ROOM.WWW_LIVE_URL
     *
     * @param wwwLiveUrl the value for COURT_ROOM.WWW_LIVE_URL
     *
     * @mbggenerated
     */
    public void setWwwLiveUrl(String wwwLiveUrl) {
        this.wwwLiveUrl = wwwLiveUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURT_ROOM.ROOM_MEMO
     *
     * @return the value of COURT_ROOM.ROOM_MEMO
     *
     * @mbggenerated
     */
    public String getRoomMemo() {
        return roomMemo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURT_ROOM.ROOM_MEMO
     *
     * @param roomMemo the value for COURT_ROOM.ROOM_MEMO
     *
     * @mbggenerated
     */
    public void setRoomMemo(String roomMemo) {
        this.roomMemo = roomMemo;
    }

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
}