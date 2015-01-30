<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>庭审案件点播</title>
<style type="text/css">
fieldset{
    width:100%;
    color:#333; 
    border:#06c dashed 1px;
} 
legend {
    color:#06c;
    font-weight:800; 
    font-size:16px;
} 
textarea {
	resize: none;
	width: 100%;
	height:80px;
	max-width: 300px;
	max-height: 100px;
	border: 1px solid #C0C0C0;
}
</style>
</head>
<body>
<div class="col-lg-12" style="text-align: left;margin: 15px 0px 10px 0px;" >
	<span id="add_title" style="margin-left: 80px;display:inline-block;">庭审案件点播</span>
	<div class="col-lg-3" style="float: right;text-align: right;">
		<input type="button" name="" class="btn_return" value="返回点播列表" onclick="location.href='<c:url value="/courtPub/vod/vodList"/>'"/>
		<input type="hidden" name="vodList" value="${vodUrl }" id="vodUrl"  />
	</div>
	<HR style="width=100%;border: 1px solid black;margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;"/>
</div>
<div class="col-lg-9" style="margin-bottom: 20px;">
<div id="mediaspace0" >
<script type="text/javascript">
window.onload = function() {
	showLocale();
	 live();
	 desc();
	 };	
/*************************************************
Function:       live    
Description:    视频直播
Author:         Huangdj             
*************************************************/
function live() {
	 var rtmpUrl = '<c:out value='${rtmpUrl}' default='0'/>';
	/*  alert(rtmpUrl+"点播rtmp流"); */
	jwplayer('mediaspace0').setup({
		'flashplayer' : '../../public/player.swf',
		//'file' : file,
		//'streamer' : live,
		"playlist":[
		           <%
		           List<String> vodUrl = (List<String>)request.getAttribute("vodUrl");
		           //PrintWriter write = response.getWriter();
		           for(int i=0;i<vodUrl.size();i++){
		        	   int c = vodUrl.get(i).lastIndexOf("streams/");
		   			   String d =  vodUrl.get(i).substring(c+7, vodUrl.get(i).length());
		   			   String rtmpUrl = vodUrl.get(i).substring(0, c+7);
		        	   String vodFiles =d;
		        	   if(i==vodUrl.size()){
		        		   //vodFiles=vodUrl.get(i).substring(0, vodUrl.get(i).length());
		        		   %>
				        	 {file:'<%=vodFiles%>'}
				        	 <%
		        	   } else {
		        		   %>
				        	 {file:'<%=vodFiles%>'},
				        	 <%
		        	   }
		        	   //write.println("{file:'"+vodFiles+"'},");d
		           }
		          
		           %>
		            //{file:'/2014/4/6/(2013)西安铁路中院法检赔字第12号/DarkKnight'},
		            //{file:'/2014/4/6/(2013)西安铁路中院法检赔字第12号/IronMan'},
		            //{file:'/2014/4/6/(2013)西安铁路中院法检赔字第12号/on2_flash8_w_audio'}
		            ],
		//"playlist.position":"right",
		//"playlist.size":200,
		"provider": "rtmp", 
		"streamer": rtmpUrl,
		'repeat' :'list',
		'autostart' : true,
		'controlbar' : 'bottom',
		 //aspectratio: "12:10",
		'width' : '95%',
		'height':'80%'
	});
}
	 
function desc(){
	var caseDesc;
	if(navigator.appName.indexOf("Explorer") > -1){
		caseDesc = document.getElementById("desc").innerText;
	}else{
		caseDesc = document.getElementById("desc").textContent;
	}
	if(caseDesc != null){
		if(caseDesc.length<84){
			/* document.getElementById("desc").style="height:90px;border:1px solid;overflow-y:none;"; */
			document.getElementById("desc").style.cssText = 'border:1px solid #E8F3F8;height:90px;';
		}
		/* alert(caseDesc.length); */
	}
} 
	
</script>
	</div>
</div>
<div class="col-lg-3" style="text-align: left;padding: 0px 0px 0px 0px;margin-bottom: 20px;">
<input type="hidden" value="${wwwLiveUrl}" id="pubUrl" />
	<fieldset>
		<legend>案件信息</legend>
		<table id="pub_vod_table"  >
			<tr>
			<td align="left" width="65px">案号：</td>
			<td align="left">${courtCase.caseNum }</td>
			</tr>
			<tr>
			<td align="left">案件名称：</td>
			<td align="left">${courtCase.caseName }</td>
			</tr>
			<tr>
			<td align="left">案件类型：</td>
			<td align="left">${courtCase.caseTypeName }</td>
			</tr>
			<tr>
			<td align="right">立案时间：</td>
			<td align="left"><fmt:formatDate value="${courtCase.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
				<tr>
			<td align="left">开庭时间：</td>
			<td align="left"><fmt:formatDate value="${courtCasePlan.planStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
				<tr>
			<td align="left">闭庭时间：</td>
			<td align="left"><fmt:formatDate value="${courtCasePlan.planEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
			<td align="left">原告：</td>
			<td align="left">${courtCase.accuser }</td>
			</tr>
			<tr>
			<td align="left">原告律师：</td>
			<td align="left">${courtCase.accuserLawer }</td>
			</tr>
				<tr>
			<td align="left">被告：</td>
			<td align="left">${courtCase.accused }</td>
			</tr>
				<tr>
			<td align="left">被告律师：</td>
			<td align="left">${courtCase.accusedLawer }</td>
			</tr>
			<tr>
			<td align="left" valign="top" >案件详细：</td>
			<td align="left">
				<div style="height: 90px;overflow-y:scroll;">${courtCase.caseDesc }
					<!-- <textarea name="" disabled></textarea> -->
				</div>
			</td>
			</tr>
		</table>
	</fieldset>
</div>
</body>
</html>