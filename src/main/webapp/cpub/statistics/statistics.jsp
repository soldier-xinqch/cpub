<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>庭审统计</title>
<style type="text/css">
#mychart {
	margin: 15px 20px 20px 20px;
	width: 90%;
	max-width: 800px;
	height: 440px;
	float: left;
}

#planTotal {
	margin: 15px 20px 20px 20px;
	width: 380px;
	height: 300px;
	float: right;
}

#totalRecord {
	width: 380px;
	height: 110px;
	float: right;
	border: 5px solid #2BB4D6;
}
</style>
</head>
<body>
	<!--导航区域开始-->
	<div class="pure-menu pure-menu-open pure-menu-horizontal menu"
		style="background-color: #6699ff;">
		<ul id="menu">
			<li><a href="<c:url value='/courtPub/vod/pubBillboard'/>">&nbsp;&nbsp;&nbsp;&nbsp;直播预告&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/pubList'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;直播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/vodList'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/list'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/roomList'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法庭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/userList'/>">&nbsp;&nbsp;&nbsp;&nbsp;用户管理&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/statistics/home'/>"
				style="background-color: #0099CC;">&nbsp;&nbsp;&nbsp;&nbsp;庭审统计&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		</ul>
	</div>
	<!--导航区域结束-->
	<!-- 搜索部分 -->
	<div class="pure-u-1">
		<form class="pure-form pure-form-aligned"
			action="<c:url value='/courtPub/vod/caseAndPlan'/>" method="get">
			<div class="pure-control-group pure-u-3-5">
				<select id="state"
					style="width: 200px; text-align: center; height: 34px; float: left;"
					name="qp_orgId">
					<option value="请选择法院">请选择法院</option>
					<c:forEach var="orgName" items="${orgNames}" varStatus="status">
						<option value="${orgName.id}">${orgName.orgName}</option>
					</c:forEach>
				</select> &nbsp;开始时间:<input type="text" id="d242"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d241\');}'})"
					class="Wdate" style="width: 140px; height: 34px;"
					name="qp_startTime" readonly /> &nbsp;结束时间：<input type="text"
					id="d241"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d242\');}'})"
					class="Wdate" style="width: 140px; height: 34px;" name="qp_endTime"
					readonly /> &nbsp;
			</div>
			<button type="submit" class="pure-button pure-button-primary">查询</button>
		</form>
	</div>
	<!-- 搜索部分结束 -->
	<!-- 数据展示开始  -->
	<div class="pure-u-1 bodyData">
		<div id="mychart"></div>
		<div id="planTotal"></div>
		<!--  总计  -->
		<div id="totalRecord">
			<table>
				<tr>
					<td>庭审直播总计:</td>
					<td>数值</td>
					<td>庭审直播总计:</td>
					<td>数值</td>
				</tr>
				<tr>
					<td>庭审点播总计：</td>
				</tr>
				<tr>
					<td>庭审案件总计：</td>
				</tr>
			</table>
		</div>
		<div>
			<label>注意：当天的统计数据需要在第二天查看或者在庭审静止时查看！！！</label>
		</div>
	</div>
<script type="text/javascript">
<%@ include file="statistics.js" %>
</script>
</body>
</html>
