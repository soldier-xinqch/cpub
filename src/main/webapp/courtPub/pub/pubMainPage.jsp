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
<title>庭审案件直播安排</title>
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

a{
	font-size: 16px;
	font-style:inherit;
	font-variant: small-caps;
}
a.active{
	color:red;
}
a:hover{
	font-weight: bold;
} 
</style>
<script type="text/javascript">
	
	 function verify(storeFlag){
			var casePlanId = '<c:out value='${courtCasePlan.id}' default='0'/>';
			$.ajax({
				type:"get",
				url:"<c:url value='/verifyPauseAndGo?storeFlag="+storeFlag+"&casePlanId="+casePlanId+"&timestamp="+Math.random()+"'/>",
				success : function(data) {  
					/* alert(data.data+"12345"); */
				     if(false==data.data){
				        /*   alert("停止成功！"); */
				          alert("操作成功！您将退回直播列表中……");
				          window.location =  '<c:url value="/courtPub/vod/pubList"/>';
				      }else{
				    	  alert("继续播放！");
				    	  toPause();	
				      } 
				       /*   alert("Data Saved: " + data.data);   */
				       }  
			});

		}
	function toPause(){
		document.getElementById("pubUrl").value = "";
	}
	function toLive(){
		var pubUrl = document.getElementById("pubUrl").value;
		live(pubUrl);
	}
	 
	 window.onload = function() {
		/*  live('<c:out value='${wwwLiveUrl}' default='0'/>'); */
		 showLocale();
		 toLive();
		 };	

	/*************************************************
	 Function:       live    
	 Description:    视频直播
	 Author:         Huangdj             
	 *************************************************/
	function live(liveaddress) {
		var c = liveaddress.lastIndexOf("/");
		var file = liveaddress.substring(c + 1);
		/*  alert(file); */
		var live = liveaddress.substring(0, c);
	/* 	alert(live); */
		jwplayer('mediaspace0').setup({
			'flashplayer': '../../public/player.swf',
			'file' : file,
			'streamer' : live,
			 'autostart':true, 
			'controlbar' : 'bottom',
			'width' : '95%',
			'height' : '95%'
		});
	}  
		 
		 
		 
		 
</script>
</head>
<div class="col-lg-12" style="text-align: left;margin: 15px 0px 10px 0px;" >
	<span id="add_title" style="margin-left: 80px;display:inline-block;">庭审案件直播安排</span>
	<div class="col-lg-3" style="float: right;text-align: right;">
	<input type="button" name="" class="btn_return" value="返回直播列表" onclick="location.href='<c:url value='/courtPub/vod/pubList'/>';"/>
	</div>
	<HR style="width=100%;border: 1px solid black;margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;"/>
</div>
<div class="col-lg-9" style="margin-bottom: 20px;">
<div id="mediaspace0" >
	<script type="text/javascript">
	 
	 window.onload = function() {
		/*  live('<c:out value='${wwwLiveUrl}' default='0'/>'); */
		 toLive();
		 };	

	/*************************************************
	 Function:       live    
	 Description:    视频直播
	 Author:         Huangdj             
	 *************************************************/
	function live(liveaddress) {
		var c = liveaddress.lastIndexOf("/");
		var file = liveaddress.substring(c + 1);
		/*  alert(file); */
		var live = liveaddress.substring(0, c);
	/* 	alert(live); */
		jwplayer('mediaspace0').setup({
			'flashplayer': '../../public/player.swf',
			'file' : file,
			'streamer' : live,
			 'autostart':true, 
			'controlbar' : 'bottom',
			'width' : '95%',
			'height' : '80%'
		});
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
			<tr>
			<td align="left" valign="top"  colspan="2">&nbsp;&nbsp;&nbsp;
			
			</td>
			<td align="right">
			</td>
			</tr>
		</table>
	</fieldset>
	<div class="col-lg-12" style="float: inherit;margin-top: 10px;">
	<input type="button" id="storeGo" name="storeGo" value="继续"  class="btn btn-primary" onclick="verify('TOGO')"/>&nbsp;
	<input type="button" id="storePausexiu" name="storePausexiu" value="休庭"  class="btn btn-primary"onclick="verify('PAUSEXIU')"/>&nbsp;
	<input type="button" id="storePause" name="storePause" value="闭庭"   class="btn btn-warning" onclick="verify('PAUSE')"/>&nbsp;
</div>
</div>
<!--主要区域结束-->
</html>