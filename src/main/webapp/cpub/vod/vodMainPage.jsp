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
</head>
<body>
	<!--主要区域开始-->
	<div id="vod_pub_main" class="pure-u-1">
		<div class="pure-u-1-3 left"style="height:40px;">
			<h2>庭审案件点播</h2>
		</div>
		<div class="pure-u-4-24" style="float: right;text-align: right;margin-top: 20px;">
			<input type="button" name="" class="btn_return" value="返回点播列表" onclick="location.href='<c:url value="/courtPub/vod/vodList"/>'"/>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>

<div class="pure-u-16-24" style="float: left;margin: 20px 0px 20px 16px;height: 400px;">
<div id="mediaspace0" >
<script type="text/javascript">
window.onload = function() {
	showLocale();
	 live();
	 desc();
	 };	
/*************************************************
Function:       live    
Description:    视频点播
Author:         Huangdj             
*************************************************/
function live() {
	 var rtmpUrl = '<c:out value='${rtmpUrl}' default='0'/>';
    jwplayer('mediaspace0').setup({
		'flashplayer' : '../../public/player.swf',
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
		        	   //write.println("{file:'"+vodFiles+"'},");
		           }
		          
		           %>
		            ],
		//"playlist.position":"right",
		//"playlist.size":200,
		"provider": "rtmp", 
		"streamer": rtmpUrl,
		'repeat' :'list',
		'autostart' : true,
		'controlbar' : 'bottom',
		'aspectratio': "12:11",
		'width' : '100%',
		'height':'98%'
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
	<div class="pure-u-7-24" style="text-align: left;float:right;padding: 0px 0px 0px 0px;margin: 20px 10px 20px 0px;">
	<fieldset>
		<legend>案件信息</legend>
		<table id="pub_vod_table"  >
			<tr>
			<td align="left" width="85px">案号：</td>
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
			<td align="left">立案时间：</td>
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
				<div style="height: 90px;overflow-y:scroll;" id="desc">${courtCase.caseDesc }
					<!-- <textarea name="" disabled></textarea> -->
				</div>
			</td>
			</tr>
		</table>
	</fieldset>
	</div>
	<div class="clearfix"></div>
</body>
</html>