<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>

<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>更改直播预告</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global.css'/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global_color.css'/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/add.css'/>" />
<script type='text/javascript'
	src='<c:url value="/public/js/time/WdatePicker.js"/>'></script>
	<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/public/ui-lightness/jquery-ui-1.9.2.custom.css"/>" />
<script type='text/javascript'
	src='<c:url value="/public/js/jquery-1.9.1.js"/>'></script>
<script type='text/javascript'
	src='<c:url value="/public/js/jquery-ui-1.9.2.custom.js"/>'></script>
<script type="text/javascript">
	var regex = /^\s*$/;
	function verify2() {
		document.getElementById("case_num").style.display = "none";
	}
	function verify1() {
		document.getElementById("case_name").style.display = "none";
	}
	function verifyNum() {
		var caseNumValue = document.getElementById("num").value;
		if (caseNumValue == null || regex.test(caseNumValue)) {
			document.getElementById("case_num").style.display = "block";
		}
	}
	function verifyName() {
		var caseNameValue = document.getElementById("name").value;
		if (caseNameValue == null || regex.test(caseNameValue)) {
			document.getElementById("case_name").style.display = "block";
		}
	}
	function validate_channel_info(frm) {
		var caseNumValue = document.getElementById("caseNums").value;
		var caseNameValue = document.getElementById("roomName").value;
		var startTime = document.getElementById("d242").value;
		var dateflag = testDate();
		if (frm.caseNums.value == "" || regex.test(caseNumValue)) {
			alert("案件不得为空！");
			return false;
		} else if (frm.roomName.value == "" || regex.test(caseNameValue)) {
			alert("法庭不能为空！");
			return false;
		} else if (frm.d242.value == "" || regex.test(startTime)) {
			alert("直播开庭时间不能为空");
			return false;
		}else if(false==dateflag){
			alert("开始时间不能大于或等于结束时间,请重新输入!!!");
			return false;
		}
		return true;
	}
	
	function  testDate(){
		var startDate = document.getElementById("d242").value;
		var endDate = document.getElementById("d241").value;
		var newStartDate = startDate.substring(11, 13);
		var newEndDate = endDate.substring(11, 13);
		var newStartDat1e = startDate.substring(14, 16);
		var newEndDat1e = endDate.substring(14, 16);
		if(newStartDate>newEndDate){
			return false;
		}
		if(newStartDate == newEndDate){
			if(newStartDat1e>newEndDat1e||newStartDat1e == newEndDat1e){
				return false;
			}
		}
		return true;
	}
	
	function changeTime(){
		var changeDate = document.getElementById("d242");
		changeDate.onfocus = function onfocus(event) {
			WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'d241\');}'})
			};
	}
	
	function dialogShowCase() {
		var caseId = "";
		var caseNum = "";
		var caseName = "";
		var caseTypeName = "";
		var registerTime = "";
		$("<div></div>").append($("<iframe  src='"
				+ '<c:url value='/courtPub/vod/caseList'/>'
				+ "'  width=\"880px;\" height=\"620px;\"  frameborder=\"0\">"+"</iframe>")).dialog(
						{
							autoOpen : true,
							draggable : true,
							resizable : false,
							modal : true,
							title : '选择案件',
							position : 'center',
							width : 898,
							height : 635,
							show : {
								effect : "blind",
								duration : 1000
							},
							hide : {
								effect : "explode",
								duration : 500
							},
							buttons : {
								"确认" : function() {
									caseId = document.getElementById("caseId").value;
									caseNum = document.getElementById("caseNums").value;
									caseName = document.getElementById("cname").value;
									caseTypeName = document.getElementById("caseTypeName").value;
									registerTime = document.getElementById("registerTime").value;
									
									$(this).dialog('close');
								},
								"取消" : function() {
									document.getElementById("caseId").value = caseId;
									document.getElementById("caseNums").value = caseNum;
									document.getElementById("cname").value = caseName;
									document.getElementById("caseTypeName").value = caseTypeName;
									document.getElementById("registerTime").value = registerTime;
									$(this).dialog('close');
								}
							},
							overlay : {
								opacity : 0.5,
								background : "black"
							},
							beforeClose : function(event) {
								if (document.getElementById("caseId").value != caseId) {
									document.getElementById("caseId").value = caseId;
									document.getElementById("caseNums").value = caseNum;
									document.getElementById("cname").value = caseName;
									document.getElementById("caseTypeName").value = caseTypeName;
									document.getElementById("registerTime").value = registerTime;
								}
							}
						});
	}
	
	
	function dialogShowRoom() {
		var roomId = "";
		var roomName = "";
		$("<div></div>").append($("<iframe  src='"
				+ '<c:url value='/courtPub/vod/rooms'/>'
				+ "'  width=\"880px;\" height=\"620px;\"  frameborder=\"0\">"+"</iframe>")).dialog(
						{
							autoOpen : true,
							draggable : true,
							resizable : false,
							modal : true,
							title : '选择法庭',
							position : 'center',
							width : 898,
							height : 610,
							show : {
								effect : "blind",
								duration : 1000
							},
							hide : {
								effect : "explode",
								duration : 500
							},
							buttons : {
								"确认" : function() {
									roomId = parent.document.getElementById("roomId").value;
									roomName = parent.document.getElementById("roomName").value;
									$(this).dialog('close');
								},
								"取消" : function() {
									document.getElementById("roomId").value = roomId;
									document.getElementById("roomName").value = roomName;
									$(this).dialog('close');
								}
							},
							overlay : {
								opacity : 0.5,
								background : "black"
							},
							beforeClose : function(event) {
								if (document.getElementById("roomName").value != roomName) {
									document.getElementById("roomId").value = roomId;
									document.getElementById("roomName").value = roomName;
								}
							}
						});
	}
	
	function dialogShowHeYi() {
		var heyiId = "";
		var heyiting = "";
		$("<div></div>").append($("<iframe  src='"
				+ '<c:url value='/courtPub/vod/selectHeYi'/>'
				+ "'  width=\"355px;\" height=\"500px;\"  frameborder=\"0\">"+"</iframe>")).dialog(
						{
							autoOpen : true,
							draggable : true,
							resizable : false,
							modal : true,
							title : '选择合议庭成员',
							position : 'center',
							width : 375,
							height : 550,
							show : {
								effect : "blind",
								duration : 1000
							},
							hide : {
								effect : "explode",
								duration : 500
							},
							buttons : {
								"确认" : function() {
									heyiId = parent.document.getElementById("collegialId").value;
									heyiting = parent.document.getElementById("heyiName").value;
									$(this).dialog('close');
								},
								"取消" : function() {
									document.getElementById("collegialId").value = heyiId;
									document.getElementById("heyiName").value = heyiting;
									$(this).dialog('close');
								}
							},
							overlay : {
								opacity : 0.5,
								background : "black"
							},
							beforeClose : function(event) {
								if (document.getElementById("heyiName").value != heyiting) {
									document.getElementById("collegialId").value = heyiId;
									document.getElementById("heyiName").value = heyiting;
								}
							}
						});
	}
</script>
<style type="text/css">
input {
	width: 244px;
}

textarea {
	resize: none;
	width: 300px;
	max-width: 300px;
	max-height: 100px;
	border: 1px solid #C0C0C0;
}
#push{
	width: 30px;	
}

</style>
</head>
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
</html>