<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>法庭列表</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="decorator" content="none" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global.css'/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global_color.css'/>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script type="text/javascript">
var clickFlag="";
function chooseCase(roomId, roomName) {
	parent.document.getElementById("roomId").value = roomId;
	parent.document.getElementById("roomName").value = roomName;
	choosed(roomId);
	clickFlag = roomId;
}

function choosed(id){
	var bgcolor = "#E8F3F8";
	if(clickFlag == ""){
		clickFlag = id;
	}
	document.getElementById(clickFlag).style.background = "";
	document.getElementById(id).style.background = bgcolor;
}
</script>
</head>
<body style="background-color: #E8F3F8;overflow-y: hidden;">
	<!-- 条件区域开始 -->
	<form action="<c:url value='/courtPub/vod/getRoom'/>" method="get">
			<div class="search_add">
				<div id="outPage">
				<input type="hidden" value="outRoom" name="qp_outRoom" />
				<select style="width: 150px; text-align: center; height: 25px;"
					name="qp_orgId">
					<option value="请选择法院">请选择法院</option>
					<c:forEach var="orgName" items="${orgNames}" varStatus="status">
						<option value="${orgName.id}">${orgName.orgName}</option>
					</c:forEach>
				</select>&nbsp;&nbsp; <input type="submit" value="查询" class="btn_search" />
				</div>
		</div>
	</form>
	<!--条件区域结束  -->
	<table id="datalist" style="width: 850px;">
		<tr>
			<th style="width: 12%">所属法院</th>
			<th style="width: 16%">法庭名称</th>
			<th style="width: 32%">直播地址</th>
			<th style="width: 8%">法庭类型</th>
			<th style="width: 6%">操作管理</th>
		</tr>
		<c:forEach var="clvList" items="${lis_dev}" varStatus="status">
			<tr id="${clvList.id}" style="background: #fff;">
				<td>${clvList.courtName }</td>
				<td>${clvList.roomName }</td>
				<td>${clvList.wwwLiveUrl }</td>
				<td>${clvList.roomType }</td>
				<td><input type="button" value="选择" class="btn_search"
					onclick="chooseCase('${clvList.id}','${clvList.roomName }')" /></td>
		</c:forEach>
	</table>
	<div style="width: 95%;">
		<!--分页-->
		<c:if test="${pagi.list != null }">
			<c:if test="${fn:length(pagi.list) != 0 }">
				<jsp:include page="/common/component/ajaxPager.jsp">
					<jsp:param name="actionUrl"
						value="${pageContext.request.contextPath }/courtPub/vod/rooms" />
					<jsp:param name="totalCount" value="${pagi.totalCount}" />
					<jsp:param name="pageSize" value="${pagi.pageSize}" />
					<jsp:param name="showPageNum" value="5" />
				</jsp:include>
			</c:if>
		</c:if>
	</div>
</body>
</html>