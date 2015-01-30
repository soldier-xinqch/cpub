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
<title>添加直播预告</title>
</head>
<body>
	<!--主要区域开始-->
	<div id="vod_pub_main" class="pure-u-1">
		<div class="pure-u-1-3 left" style="height:40px;">
			<h2>新增直播预告信息</h2>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<form id="courtPlanForm" class="pure-form pure-form-aligned" action="addBillboard" method="post" class="main_form" name="frm"  onsubmit="return validate_channel_info(this);">
		<!-- 隐藏表单开始 -->
			<input type="hidden" name="caseId" value="" id="caseId" /> 
			<input type="hidden" name="courtId"	value="${courtIds }" />
			<input type="hidden" name="courtRoomId" value="" id="roomId" />
			<input type="hidden" name="collegialId" value="" id="collegialId" />
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
								<td width="244px"><input type="text" class="width244" name="caseNum" value="" id="caseNums" readonly /></td>
							</tr>
							<tr>
								<td align="right">立案时间：</td>
								<td width="244px"><input type="text" class="width244" name="registerTime" value="" id="registerTime" readonly /></td>
							</tr>
							<tr>
								<td align="right">案件名称：</td>
								<td width="244px"><input type="text" class="width244" name="caseName"
									value="" id="cname" readonly /></td>
							</tr>
							<tr>
								<td align="right">案件类型：</td>
								<td width="244px"><input type="text" class="width244" name="caseTypeName"
									value="" id="caseTypeName" readonly /></td>
							</tr>
						</table>
					</td>
				</tr>
			 </table>
				<table class="pure-table pure-table-horizontal planTable" >
							<tr>
								<td align="right" class="width150">开庭地点：</td>
								<td width="328px"><input type="text" name="courtRoomName"
								class="width328" value="" id="roomName" readonly /> </td>
							</tr>
							<tr>
								<td align="right" class="width150">合议庭成员：</td>
								<td width="328px"><input type="text" name="heyiName" class="width328"
								value="" id="heyiName" readonly /> </td>
							</tr>
							<tr>
								<td align="right" class="width150">直播开庭时间：</td>
								<td width="328px"><input type="text" id="d242"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d241\');}',minDate: '%y-%M-{%d+2} 07:00:00'})"
								class="Wdate" style="width: 328px; height: 30px; float: left;"
								name="planStartTimed" value="" readonly /></td>
							</tr>
							<tr>
								<td align="right" class="width150">计划闭庭时间：</td>
								<td width="328px"><input type="text" id="d241"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'d242\');}'})"
								class="Wdate" style="width: 328px; height: 30px; float: left;"
								name="planEndTimed" value="" readonly /></td>
							</tr>
							<tr>
								<td align="right" class="width150">直播至最高院：</td>
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