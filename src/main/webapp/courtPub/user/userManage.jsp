<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户列表</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global.css'/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global_color.css'/>" />
<script type='text/javascript'
	src='<c:url value="/public/js/time/WdatePicker.js"/>'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script type="text/javascript">
	//删除
	function deleteVod(id) {
		var r = window.confirm("确定要删除此用户吗？");
		if (r) {

			document.getElementById("operate_result_info").innerHTML = "删除成功!";
			document.getElementById("operate_result_info").style.display = "block";
			window.location = 'deleteUser?id='.concat(id);
		}
	}
	function displayText(id) {
		document.getElementById(id).value = "";
	}
	window.onload = function() {
		var warning = document.getElementById("warning").value;
		var privilFlag = warning;
		/* alert(warning+"DFSDFDSFDSFSDFSDF"); */
		if("2" == privilFlag){
			alert("不允许修改或删除同一法院的用户！");
		}else if("3" == privilFlag){
			alert("您没有权限修改或删除用户，维护用户请联系高院管理员");
		 }
	};	
		 
</script>
</head>
<body>
	<!--启用操作的操作提示-->
	<div id="operate_result_info" class="operate_success">
		<img onclick="this.parentNode.style.display='none';" /> 删除成功！
	</div>
	<!--主要区域开始-->
	<div id="main" >
		<!--导航区域开始-->
		<div id="navi">
			<ul id="menu">
				<li><a href="<c:url value='/courtPub/vod/pubBillboard'/>">直播预告</a></li>
				<li><a href="<c:url value='/courtPub/vod/pubList'/>">直播</a></li>
				<li><a href="<c:url value='/courtPub/vod/vodList'/>">点播</a></li>
				<li><a href="<c:url value='/courtPub/vod/list'/>">案件</a></li>
				<li><a href="<c:url value='/courtPub/vod/roomList'/>" >法庭</a></li>
				<li><a href="<c:url value='/courtPub/vod/userList'/>" style="background-color: #0099CC;">用户管理</a></li>
			</ul>
		</div>
	<!--导航区域结束-->
	
	<div style="width: 1290px;">
		<form action="findUserByName" method="get">
			<div style="width: 100%; height: 30px;">
				<div style="float: left; height: 30px;">

					<select style="width: 150px; text-align: center; height: 25px;"
						name="qp_orgId">
						<option value="请选择法院">请选择法院</option>
						<c:forEach var="orgName" items="${orgNames}" varStatus="status">
							<option value="${orgName.id}">${orgName.orgName}</option>
						</c:forEach>
					</select>&nbsp;&nbsp; 
					<input type="text" value="输入你要查询的用户名" name="qp_userName"
						id="userText" onfocus="displayText(this.id)" class="text_search" />&nbsp;
					 <select style="width: 150px;text-align: center;height:25px;" name="qp_roles">  
						     <option  >请选择角色</option>  
						     <option value="ROLE_ADMIN" >法院管理员</option>  
						     <option value="ROLE_ADMIN_HEYI" >合议庭成员</option>  
						</select>  
					&nbsp;
					<input type="submit" value="查询" class="btn_search" />
				</div>

				<div>
					<input type="hidden" value="${courtName }" id="courtName"/>
					<input type="hidden" value="${warning}" id ="warning" />
					<input type="button" value="增加" class="btn_add"
						onclick="location.href='toAddUser';" />
				</div>

			</div>
		</form>
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
					<td><input type="button" value="修改" class="btn_search"
						onclick="location.href='toModifyUser?id=${clvList.id}';" /> <input
						type="button" value="删除" class="btn_search"
						onclick="deleteVod('${clvList.id}')" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div style="width:95%;">
	<!--分页-->
	<c:if test="${pagi.list != null }">
		<c:if test="${fn:length(pagi.list) != 0 }">
			<jsp:include page="/common/component/ajaxPager.jsp">
				<jsp:param name="actionUrl"
					value="${pageContext.request.contextPath }/courtPub/vod/userList" />
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