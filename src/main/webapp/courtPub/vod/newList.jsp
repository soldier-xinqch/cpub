<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<html>
	<head>
		<title>法庭列表</title>
		<meta http-equiv="content-type" content="text/html;charset=" utf-8" />
		<link type="text/css" rel="stylesheet" media="all" href="../../public/css/global.css" />
		<link type="text/css" rel="stylesheet" media="all" href="../../public/css/global_color.css" />
		<link type="text/css" rel="stylesheet" media="all" href="../../public/css/cssgrids.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<script type="text/javascript">
			//删除
			function deleteVod(id) {
				var r = window.confirm("确定要此法庭吗？");
				if (r) {
					document.getElementById("operate_result_info").innerHTML = "删除成功!";
					document.getElementById("operate_result_info").style.display = "block";
					window.location = 'deleteRoom?id='.concat(id);
				}
			}
		</script>
	</head>
<body>
	<!--启用操作的操作提示-->
	<div id="operate_result_info" class="operate_success">
		<img src="images/close.png"
			onclick="this.parentNode.style.display='none';" /> 删除成功！
	</div> 
	<!--主要区域开始-->
	<!--导航区域开始   -->
	
	<!--导航区域结束   -->
	<!-- 右侧区域开始 -->
	<form action="byCaseNum" method="post">
		<%-- <input type="hidden" name="courtId" value="${courtId }" id="court" /> --%>
		<div>
			<input type="button" value="增加" class="btn_add" onclick="location.href='toAddRoom?courtId=${courtIds}';" />
		</div>
		<div class="search_add">
			<input type="button" value="查看案件" class="btn_add" style="float: right;"  onclick="location.href='list${courtIds}';"/>
		</div>
		<table id="datalist">
			<tr>
				<th style="width: 16%">法庭名称</th>
				<th style="width: 32%">直播地址</th>
				<th style="width: 10%">法庭类型</th>
				<!-- <th style="width: 10%">案件庭审时间</th> -->
				<th style="width: 16%">操作管理</th>
			</tr>
			<c:forEach var="clvList" items="${courtRoom}" varStatus="status">
				<tr>
					<td>${clvList.roomName }</td>
					<%--  <td><a href='<c:url value="/courtPub/vod/caseDesc"/>?id=${clvList.id}'>${clvList.wwwLiveUrl }</a></td> --%> 
					<td>${clvList.wwwLiveUrl }</td>
					<td>${clvList.roomType }</td>
					<td>
						<input	type="button" value="修改" class="btn_search" onclick="location.href='toModifyRoom?id=${clvList.id}';" /> 
						<input	type="button" value="删除" class="btn_search" onclick="deleteVod('${clvList.id}')" />
					</td>
			</c:forEach>
		</table>
	</form>


</body>
</html>