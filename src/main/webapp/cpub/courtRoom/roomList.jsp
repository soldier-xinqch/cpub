<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>法庭列表</title>
</head>
<body>
	<!--导航区域开始-->
	<div class="pure-menu pure-menu-open pure-menu-horizontal menu" style="background-color: #6699ff;">
	       <ul id="menu">
			<li><a href="<c:url value='/courtPub/vod/pubBillboard'/>">&nbsp;&nbsp;&nbsp;&nbsp;直播预告&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/pubList'/>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;直播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/vodList'/>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/list'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/roomList'/>" style="background-color: #0099CC;"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法庭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/userList'/>">&nbsp;&nbsp;&nbsp;&nbsp;用户管理&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/statistics/home'/>">&nbsp;&nbsp;&nbsp;&nbsp;庭审统计&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		</ul>
	</div>
	<!--导航区域结束-->
	<!-- 搜索部分 -->
	<div class="pure-u-1">
		<form class="pure-form pure-form-aligned" action="<c:url value='/courtPub/vod/getRoom'/>" method="get">
			<div class="pure-control-group pure-u-1-6" style="float: left;padding: 0px;">
         	 <select id="state" style="width: 200px; text-align: center; height: 34px;float: left;"	name="qp_orgId">
					<option value="请选择法院">请选择法院</option>
					<c:forEach var="orgName" items="${orgNames}" varStatus="status">
						<option value="${orgName.id}">${orgName.orgName}</option>
					</c:forEach>
			</select>
       		</div>
       		<button type="submit" class="pure-button pure-button-primary" style="float: left;height: 33px;" >查询</button>
			<div class="pure-u-2-24" style="float: right;">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="增加" class="pure-button pure-button-primary" style="height: 33px;" onclick="location.href='<c:url value='/courtPub/vod/toAddRoom'/>';" />
			</div>
		</form>
	</div>
	<div class="clearfix"></div>
	<!-- 搜索部分结束 -->
	<!-- 数据展示开始  -->
	<div class="pure-u-1 bodyData">
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
					<td>${clvList.courtName }
					<input type="hidden" value="${clvList.id }" id="roomId">
					</td>
					<td>${clvList.roomName }</td>
					<%--  <td><a href='<c:url value="/courtPub/vod/caseDesc"/>?id=${clvList.id}'>${clvList.wwwLiveUrl }</a></td> --%> 
					<td>${clvList.wwwLiveUrl }</td>
					<td>${clvList.roomType }</td>
					<td>
						<input	type="button" value="修改" class="button-secondary pure-button" onclick="location.href='<c:url value='/courtPub/vod/toModifyRoom?id=${clvList.id}'/>';" /> 
						<input	type="button" value="删除" class="button-error pure-button"  onclick="deleteVod('${clvList.id}')"  />
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
	<!-- 数据展示结束 -->
<script type="text/javascript">
//删除
	<%@ include file="dateList.js" %>
</script>
</body>
</html>