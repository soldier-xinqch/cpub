<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>法庭列表</title>
<script type="text/javascript">
			//删除
			function deleteVod(id) {
				var r = window.confirm("确定要删除此法庭吗？");
				if (r) {
					var roomId = id;
					window.location = '<c:url value="/courtPub/vod/deleteRoom?id=' + roomId + '"/>';
				}
			}
		</script>
	</head>
<body>
<!-- 数据展示部分  -->
	<!--导航区域开始-->
	<div class="col-lg-12" style="margin: 15px 0px 10px 0px;">
		<ul id="menu">
				<li><a href="<c:url value='/courtPub/vod/pubBillboard'/>">直播预告</a></li>
				<li><a href="<c:url value='/courtPub/vod/pubList'/>">直播</a></li>
				<li><a href="<c:url value='/courtPub/vod/vodList'/>">点播</a></li>
				<li><a href="<c:url value='/courtPub/vod/list'/>">案件</a></li>
				<li><a href="<c:url value='/courtPub/vod/roomList'/>" style="background-color: #0099CC;">法庭</a></li>
				<li><a href="<c:url value='/courtPub/vod/userList'/>">用户管理</a></li>
			</ul>
	</div>
	<!--导航区域结束-->
<!-- 搜索部分 -->
	<div class="col-lg-12">
		<!-- 条件区域开始 -->
		<form action="<c:url value='/courtPub/vod/getRoom'/>" method="get">
					<div style="width: 1290px; height: 30px;">
				<div>
					<input type="button" value="增加" class="btn_add" 
				onclick="location.href='<c:url value='/courtPub/vod/toAddRoom'/>';" />
				</div>
				<div class="search_add">
					<select style="width: 150px; text-align: center; height: 25px;"	name="qp_orgId">
						<option value="请选择法院">请选择法院</option>
						<c:forEach var="orgName" items="${orgNames}" varStatus="status">
							<option value="${orgName.id}">${orgName.orgName}</option>
						</c:forEach>
					</select>&nbsp;&nbsp; 
				<input type="submit" value="查询" class="btn btn-info" style="height: 25px; font-size: 13px" />
				</div>
			</div>
		</form>
		<!--条件区域结束  -->
	</div>
	<!-- 搜索部分结束 -->
<!-- 分页部分  -->
	<div class="col-lg-12" style="height: 450px;">
	<!--数据展示区开始  -->
		<table id="datalist">
			<tr>
				<th style="width: 10%">所属法院</th>
				<th style="width: 12%">法庭名称</th>
				<th style="width: 32%">直播地址</th>
				<th style="width: 10%">法庭类型</th>
				<!-- <th style="width: 10%">案件庭审时间</th> -->
				<th style="width: 10%">操作管理</th>
			</tr>
			<c:forEach var="clvList" items="${lis_dev}" varStatus="status">
				<tr>
					<td>${clvList.courtName }</td>
					<td>${clvList.roomName }</td>
					<%--  <td><a href='<c:url value="/courtPub/vod/caseDesc"/>?id=${clvList.id}'>${clvList.wwwLiveUrl }</a></td> --%> 
					<td>${clvList.wwwLiveUrl }</td>
					<td>${clvList.roomType }</td>
					<td>
						<input	type="button" value="修改" class="btn_search" onclick="location.href='<c:url value='/courtPub/vod/toModifyRoom?id=${clvList.id}'/>';" /> 
						<input	type="button" value="删除" class="btn_search" onclick="deleteVod('${clvList.id}')" />
					</td>
			</c:forEach>
		</table>
		<div style="width:95%;">
		<!--分页-->
		<c:if test="${pagi.list != null }">
			<c:if test="${fn:length(pagi.list) != 0 }">
				<jsp:include page="/common/component/ajaxPager.jsp">
					<jsp:param name="actionUrl"
						value="${pageContext.request.contextPath }/courtPub/vod/roomList" />
					<jsp:param name="totalCount" value="${pagi.totalCount}" />
					<jsp:param name="pageSize" value="${pagi.pageSize}" />
					<jsp:param name="showPageNum" value="5" />
				</jsp:include>
			</c:if>
		</c:if>
		</div>
	</div>
</body>
</html>