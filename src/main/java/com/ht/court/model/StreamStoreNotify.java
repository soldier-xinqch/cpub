package com.ht.court.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StreamStoreNotify {

	private String court_number;
	private String stream_url;
	private String cmd;
	private Date startTime;
	private Date endTime;
	private int chunk_size;
	private Date chunkTime;
	private String start_time;
	private String end_time;
	private String chunk_time;
	
	/**
	 * 案号
	 * 
	 * @return
	 */
	public String getCourt_number() {
		return court_number;
	}
	public void setCourt_number(String court_number) {
		this.court_number = court_number;
	}
	
	
	
	
	
	/**
	 * 流地址
	 * 
	 * @return
	 */
	public String getStream_url() {
		return stream_url;
	}
	public void setStream_url(String stream_url) {
		this.stream_url = stream_url;
	}
	
	/**
	 * 命令，有三种命令
	 * store(存储)
	 * stop(停止)
	 * search(查询)
	 * 
	 * @return
	 */
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	/**
	 * 开始存储时间，使用24小时制
	 * 
	 * @return
	 */
	public String getStart_time() {
		return start_time == null ? formatDate(startTime) : start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	/**
	 * 结束存储时间，使用24小时制
	 * 
	 * @return
	 */
	public String getEnd_time() {
		return end_time == null ? formatDate(endTime) : end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	
	/**
	 * 存储的每块文件的大小，单位是M
	 * 
	 * @return
	 */
	public int getChunk_size() {
		return chunk_size;
	}
	public void setChunk_size(int chunk_size) {
		this.chunk_size = chunk_size;
	}
	
	
	/**
	 * 存储的每块的文件的时长，单位是min(分钟)
	 * @return
	 */
	public String getChunk_time() {
		return chunk_time == null ? formatDate(chunkTime) : chunk_time;
	}
	public void setChunk_time(String chunk_time) {
		this.chunk_time = chunk_time;
	}
	
	private String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date);
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getChunkTime() {
		return chunkTime;
	}
	public void setChunkTime(Date chunkTime) {
		this.chunkTime = chunkTime;
	}
	
	
}
