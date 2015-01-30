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
			'width' : '100%',
			'height' : '98%'
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
</head>
<body>
	<!--主要区域开始-->
	<div id="vod_pub_main" class="pure-u-1">
		<div class="pure-u-1-3 left"style="height:40px;">
			<h2>庭审案件直播安排</h2>
		</div>
		<div class="pure-u-4-24" style="float: right;text-align: right;margin-top: 20px;">
			<input type="button" name="" class="btn_return" value="返回直播列表" onclick="location.href='<c:url value='/courtPub/vod/pubList'/>';"/>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="pure-u-16-24" style="float: left;margin: 20px 0px 20px 16px;height: 460px;">
		<div id="mediaspace0">
			<script type="text/javascript">
			 
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
					'width' : '100%',
					'height' : '98%'
				});
			}  
			</script>
		</div>
	</div>
	<div class="pure-u-7-24" style="text-align: left;float:right;padding: 0px 0px 0px 0px;margin: 20px 10px 0px 0px;">
	<input type="hidden" value="${wwwLiveUrl}" id="pubUrl" />
	<fieldset>
		<legend>案件信息</legend>
		<table id="pub_vod_table"  >
			<tr>
			<td align="left" width="75px">案号:</td>
			<td align="left">${courtCase.caseNum }</td>
			</tr>
			<tr>
			<td align="left">案件名称:</td>
			<td align="left">${courtCase.caseName }</td>
			</tr>
			<tr>
			<td align="left">案件类型:</td>
			<td align="left">${courtCase.caseTypeName }</td>
			</tr>
			<tr>
			<td align="left">立案时间:</td>
			<td align="left"><fmt:formatDate value="${courtCase.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
				<tr>
			<td align="left">开庭时间:</td>
			<td align="left"><fmt:formatDate value="${courtCasePlan.planStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
				<tr>
			<td align="left">闭庭时间:</td>
			<td align="left"><fmt:formatDate value="${courtCasePlan.planEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
			<td align="left">原告:</td>
			<td align="left">${courtCase.accuser }</td>
			</tr>
			<tr>
			<td align="left">原告律师:</td>
			<td align="left">${courtCase.accuserLawer }</td>
			</tr>
				<tr>
			<td align="left">被告:</td>
			<td align="left">${courtCase.accused }</td>
			</tr>
				<tr>
			<td align="left">被告律师:</td>
			<td align="left">${courtCase.accusedLawer }</td>
			</tr>
			<tr>
			<td align="left" valign="top" >案件详细:</td>
			<td align="left">
				<div style="height: 90px;overflow-y:scroll;" id="desc">${courtCase.caseDesc }
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
	<div class="col-lg-12" style="float: inherit;margin: 10px 0px 10px 0px;">
	<input type="button" id="storeGo" name="storeGo" value="继续"  class="btn btn-primary" onclick="verify('TOGO')"/>&nbsp;
	<input type="button" id="storePausexiu" name="storePausexiu" value="休庭"  class="btn btn-primary"onclick="verify('PAUSEXIU')"/>&nbsp;
	<input type="button" id="storePause" name="storePause" value="闭庭"   class="btn btn-warning" onclick="verify('PAUSE')"/>&nbsp;
		</div>
		</div>
	<div class="clearfix"></div>
</body>
</html>