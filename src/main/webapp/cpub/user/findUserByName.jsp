<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户列表</title>
</head>
<body>
<!--导航区域开始-->
		<div class="pure-menu pure-menu-open pure-menu-horizontal menu" style="background-color: #6699ff;">
	       <ul id="menu">
			<li><a href="<c:url value='/courtPub/vod/pubBillboard'/>">&nbsp;&nbsp;&nbsp;&nbsp;直播预告&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/pubList'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;直播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/vodList'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/list'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/roomList'/>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法庭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/userList'/>" style="background-color: #0099CC;">&nbsp;&nbsp;&nbsp;&nbsp;用户管理&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
				<li><a href="<c:url value='/courtPub/statistics/home'/>">&nbsp;&nbsp;&nbsp;&nbsp;庭审统计&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		</ul>
	</div>
	<!--导航区域结束-->
	<!-- 搜索部分 -->
	<div class="pure-u-1">
		<form class="pure-form pure-form-aligned" action="findUserByName" method="get">
			<div class="pure-control-group pure-u-1-6" style="float: left;padding: 0px;">
         	 <select id="state"	name="qp_orgId" style="width: 200px;height: 34px;float: left;">
					<option value="请选择法院">请选择法院</option>
					<c:forEach var="orgName" items="${orgNames}" varStatus="status">
						<option value="${orgName.id}">${orgName.orgName}</option>
					</c:forEach>
			</select>
       		</div>
       		<div class="pure-control-group pure-u-1-6" style="float: left;">
       		   <input type="text" placeholder="输入你要查询的用户名" name="qp_userName" id="userText"  class="bodySearch" />
      		</div>
		 	<div class="pure-control-group pure-u-1-8" style="float: left;">
         		 <select class="bodySearch" name="qp_roles" style="width: 150px;text-align: center;height:34px;">
							 <option >请选择角色</option>  
						     <option value="ROLE_ADMIN" >法院管理员</option>  
						     <option value="ROLE_ADMIN_HEYI" >合议庭成员</option>  
				</select>
       		</div>
       		<button type="submit" class="pure-button pure-button-primary" style="float: left;height: 33px;" >查询</button>
			<div class="pure-u-2-24" style="float: right;">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="增加" class="pure-button pure-button-primary" style="height: 33px;" onclick="location.href='toAddUser';"  />
			</div>
		</form>
	</div>
	<!-- 搜索部分结束 -->
	<!-- 数据展示开始  -->
	<div class="pure-u-1 bodyData">
		<table id="datalist">
			<tr>
				<th>所属法院</th>
				<th>用户名</th>
				<th>授权日期</th>
				<th>角色</th>
				<th>真实姓名</th>
				<th>操作管理</th>
			</tr>
			<c:forEach var="clvList" items="${lis_dev}" varStatus="status">
				<tr>
					<td id="userCourt">${clvList.courtName }</td>
					<td>${clvList.userName }</td>
					<td><fmt:formatDate value="${clvList.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td id="userRoles" >
					<c:if test="${clvList.roles == 'ROLE_ADMIN'}">
								法院管理员
					</c:if>
					<c:if test="${clvList.roles == 'ROLE_ADMIN_HEYI'}">
								合议庭成员
					</c:if>
					</td>
					<td>${clvList.realName}</td>
					<td><input type="button" value="修改"  class="button-secondary pure-button"
						onclick="location.href='toModifyUser?id=${clvList.id}';" /> <input
						type="button" value="删除" class="button-error pure-button" 
						onclick="deleteVod('${clvList.id}')" /></td>
				</tr>
			</c:forEach>
		</table>
		<div style="width:95%;">
		<!--分页-->
		<c:if test="${pagi.list != null }">
			<c:if test="${fn:length(pagi.list) != 0 }">
				<jsp:include page="/common/component/ajaxPager.jsp">
					<jsp:param name="actionUrl"
						value="${pageContext.request.contextPath }/courtPub/vod/findUserByName" />
					<jsp:param name="totalCount" value="${pagi.totalCount}" />
					<jsp:param name="pageSize" value="${pagi.pageSize}" />
					<jsp:param name="showPageNum" value="5" />
					<jsp:param name="qp.name1" value="qp_orgId" />
				<jsp:param name="qp.name2" value="qp_userName" />
				<jsp:param name="qp.name3" value="qp_roles" />
				</jsp:include>
			</c:if>
		</c:if>
		</div>
	</div>
<script type="text/javascript">
	<%@ include file="dateList.js" %>
</script>
</body>
</html>