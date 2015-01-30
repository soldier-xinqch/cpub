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
<title>更改直播预告</title>
</head>
<body>
	<!--主要区域开始-->
	<div id="vod_pub_main" class="pure-u-1">
		<div class="pure-u-1-3 left" style="height:40px;">
			<h2>更改直播预告信息</h2>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<form id="courtPlanForm" class="pure-form pure-form-aligned" action="modifyBillboard" method="post" class="main_form" name="frm"  onsubmit="return validate_channel_info(this);">
		<!-- 隐藏表单开始 -->
			 <input	type="hidden" name="caseId" value="${courtCase.id }" id="caseId" />
			 <input type="hidden" name="courtId" value="${courtIds }" />
			 <input type="hidden" name="courtRoomId"	value="${courtCasePlan.courtRoomId }" id="roomId" />
			 <input type="hidden" name="pubAccessUrl" value="${courtCasePlan.pubAccessUrl }" />
			 <input type="hidden" name="id" value="${planId }" />
			 <input type="hidden" name="createUserId" value="${courtCasePlan.createUserId }" />
			 <input type="hidden" name="collegialId" value="${courtCasePlan.collegialId }" id ="collegialId" />
			 <input type="hidden" name="createUser" value="${courtCasePlan.createUser }" />
			 <input type="hidden" name="trialLevel" value="${courtCasePlan.trialLevel }" />							
			 <input type="hidden" name="createTimed" value="<fmt:formatDate value="${courtCasePlan.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
		<!-- 隐藏表单结束 -->
		<div class="pure-u-1" >
		<div class="pure-u-1-3 left" >&nbsp;</div>
		<div class="width460 left">
			 <fieldset class="pure-u-1 left" style="margin-top: 20px;">
			  <table>
				 <tr>
					<td align="right" width="150px" >案件信息：<br><br><br><br><br><br><br><br></td>
					<td width=330px;>	
					<table class="pure-table pure-table-bordered addAndUpdatPlanTable" style="float: right;">
							<tr>
								<td align="right" width="96px">案号：</td>
								<td width="244px"><input type="text" class="width244" name="caseNum" value="${courtCase.caseNum }" id="caseNums" readonly /></td>
							</tr>
							<tr>
								<td align="right">立案时间：</td>
								<td width="244px"><input type="text" class="width244" name="registerTime" value="<fmt:formatDate value="${courtCase.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" id="registerTime" readonly /></td>
							</tr>
							<tr>
								<td align="right">案件名称：</td>
								<td width="244px"><input type="text" class="width244" name="caseName"
									value="${courtCase.caseName }" id="cname" readonly /></td>
							</tr>
							<tr>
								<td align="right">案件类型：</td>
								<td width="244px"><input type="text" class="width244" name="caseTypeName"
									value="${courtCase.caseTypeName }" id="caseTypeName" readonly /></td>
							</tr>
						</table>
						</td>
					</tr>
				 </table>
				<table class="pure-table pure-table-horizontal planTable" >
							<tr>
								<td align="right"  class="width150">开庭地点：</td>
								<td width="328px" align="left"><input type="text" name="courtRoomName"
								class="width328"
								value="${courtCasePlan.courtRoomName }" id="roomName" readonly /></td>
							</tr>
							<tr>
								<td align="right"  class="width150">合议庭成员：</td>
								<td width="328px" align="left"><input type="text" name="heyiName"   class="width328"    
								value="${collegialName }" id="heyiName" readonly /></td>
							</tr>
							<tr>
								<td align="right"  class="width150">直播开庭时间：</td>
								<td width="328px"><input type="text" id="d242"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d241\');}',minDate: '%y-%M-{%d+2} 07:00:00'})"
								class="Wdate" style="width: 328px; height: 30px; float: left;"
								name="planStartTimed"
								value="<fmt:formatDate value="${courtCasePlan.planStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								readonly /></td>
							</tr>
							<tr>
								<td align="right"  class="width150">计划闭庭时间：</td>
								<td width="328px"><input type="text" id="d241"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d242\');}'})"
								class="Wdate" style="width: 328px; height: 30px; float: left;"
								name="planEndTimed"
								value="<fmt:formatDate value="${courtCasePlan.planEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								readonly /></td>
							</tr>
							<tr>
								<td align="right"  class="width150">直播至最高院：</td>
								<td width="328px" align="left" valign="middle" >
									<input type="checkbox" id="push" name="allowLiveFlag" value="2" style="vertical-align:middle;"/>
									<span style="color: gray;"> 是否将庭审直播推送到最高院</span>
								</td>
							</tr>
				</table>
				<label style="float:left;text-align: center; font-size: 13px;color: red;margin-left: 120px;">注意： 点击灰色部分进行选择</label>
			</fieldset>
			</div>
		</div>
		<div style="width:100%;margin-bottom: 20px;">
        	<input type="button" class="pure-button pure-button-primary pure-u-1-8" value="保存" id="submitBtn"/>
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<input type="button" class="pure-button pure-button-primary pure-u-1-8" value="返回" 
        		onclick="location.href='<c:url value='/courtPub/vod/pubBillboard'/>';" />
       	</div>
	</form>
<script type="text/javascript" charset="utf-8">
	<%@ include file="planAddAndUpdate.js" %>
</script> 
</body>
</html>















<%-- 

<body>
	<!--主要区域开始-->
	<div id="add_bill_main">
		<div id="head_title" style="width: 100%; height: 90px;">
			<span id="add_title"
				style="width: 300px; height: 51px; position: absolute; left: 100px; top: 20px;" onclick="changeTime();">更改直播预告信息</span><br />
			<br /> <br />
			<hr>
		</div>
		<form action="modifyBillboard" method="post" class="main_form"
			name="frm" onsubmit="return validate_channel_info(this);">
			<div style="width: 41%; height: 500px;">
				<div
					style="text-align: center; width: 130px; height: 30px; float: left;">案件信息：</div>
				<div style="float: left;">
					<table id="plan_case_table">
						<tr>
							<td align="right">案号：</td>
							<td width="244px"><input type="text" name="caseNum"
								value="${courtCase.caseNum }" id="caseNums" readonly /> <input
								type="hidden" name="caseId" value="${courtCase.id }" id="caseId" />
								<input type="hidden" name="courtId" value="${courtIds }" /></td>
						</tr>
						<tr>
							<td align="right">案件名称：</td>
							<td width="244px"><input type="text" name="caseName"
								value="${courtCase.caseName }" id="cname" readonly /></td>
						</tr>
						<tr>
							<td align="right">案件类型：</td>
							<td width="244px"><input type="text" name="caseTypeName"
								value="${courtCase.caseTypeName }" id="caseTypeName" readonly /></td>
						</tr>
						<tr>
							<td align="right">立案时间：</td>
							<td width="244px"><input type="text" name="registerTime"
								value="<fmt:formatDate value="${courtCase.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								id="registerTime" readonly /></td>
						</tr>
					</table>
				</div>
				<div
					style="line-height: 30px; width: 85px; _width:65px;height: 30px; float: right;">
					<a href="#" onclick="dialogShowCase();">选择案件</a>
				</div>
				<div style="width: 350px; height: 300px; float: left;">
					<table id="plan_table">
						<tr>
							<td align="center" width="100px">开庭地点：</td>
							<td width="300px"><input type="text" name="courtRoomName"
								style="width: 320px; float: left;"
								value="${courtCasePlan.courtRoomName }" id="roomName" readonly />
								<input type="hidden" name="courtRoomId"
								value="${courtCasePlan.courtRoomId }" id="roomId"
								style="width: 380px;" readonly />
								<input type="hidden" name="pubAccessUrl"
								value="${courtCasePlan.pubAccessUrl }">
								</td>
							<td align="center"><a href="#" onclick="dialogShowRoom();">选择法庭</a>
									 <input type="hidden" name="id" value="${planId }">
								<input type="hidden" name="createUserId"
								value="${courtCasePlan.createUserId }"></td>
						</tr>
						<tr>
							<td align="center" width="100px"> 合议庭成员：&nbsp;&nbsp;</td>
							<td width="300px"><input type="text" name="heyiName"       
								style="width: 320px;_width:325px;float: left;" value="${collegialName }" id="heyiName" readonly />
								<input type="hidden" name="collegialId" value="${courtCasePlan.collegialId }" id ="collegialId" />
								 </td>
								<td align="center"><a href="#" onclick="dialogShowHeYi();">选择人员</a></td>
						</tr>
						<tr>
						<!-- onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d241\');}',minDate: '%y-%M-{%d+3}'})" -->
							<td align="left" id="changeFlag">直播开庭时间：</td>
							<td width="260px"><input type="text" id="d242"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d241\');}',minDate: '%y-%M-{%d+2} 07:00:00'})"
								class="Wdate" style="width: 320px; height: 30px; float: left;"
								name="planStartTimed"
								value="<fmt:formatDate value="${courtCasePlan.planStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								readonly /></td>
							<td><input type="hidden" name="createUser"
								value="${courtCasePlan.createUser }">
								<input type="hidden" name="trialLevel"
								value="${courtCasePlan.trialLevel }">
								</td>
								
						</tr>
						<tr>
							<td align="left">预计结束时间：</td>
							<td width="260px"><input type="text" id="d241"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d242\');}'})"
								class="Wdate" style="width: 320px; height: 30px; float: left;"
								name="planEndTimed"
								value="<fmt:formatDate value="${courtCasePlan.planEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								readonly /></td>
							<td width="50px"><input type="hidden" name="createTimed"
								value="<fmt:formatDate value="${courtCasePlan.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
							</td>
						</tr>
						<tr>
							<td align="left">直播至最高院：</td>
							<td width="260px" align="left" valign="middle" >
									
								<c:if test="${courtCasePlan.allowLiveFlag =='2'}">
								<input type="checkbox" id="push" name="allowLiveFlag" value="2" style="vertical-align:middle;" checked/>
								</c:if>
								<c:if test="${courtCasePlan.allowLiveFlag =='1'}">
								<input type="checkbox" id="push" name="allowLiveFlag" value="2" style="vertical-align:middle;"/>
								</c:if>
								<span style="color: gray;"> 是否将庭审直播推送到最高院</span>
							</td>
							<td width="50px"></td>
						</tr>
						
						<tr>
							<td align="center" colspan="3"><input type="submit"
								value="保存" name="Submit" class="btn_save" /> <input
								type="button" value="返回" class="btn_save" id="save_checking"
								onclick="location.href='<c:url value='/courtPub/vod/pubBillboard'/>';" /></td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
	<!--主要区域结束-->
</body>
</html> --%>