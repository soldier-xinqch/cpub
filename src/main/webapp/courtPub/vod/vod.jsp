<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>点播</title>
<script type="text/javascript">
			//删除
			function deleteVod(id) {
				var r = window.confirm("确定要删除此条直播吗？");
				if (r) {
					window.location =  '<c:url value="/courtPub/vod/deleteLive?qp_trailStatus=闭庭&id=' + id + '"/>';
				}
			}
			 function displayText(id){
				document.getElementById(id).value="";
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
				<li><a href="<c:url value='/courtPub/vod/vodList'/>" style="background-color: #0099CC;">点播</a></li>
				<li><a href="<c:url value='/courtPub/vod/list'/>">案件</a></li>
				<li><a href="<c:url value='/courtPub/vod/roomList'/>">法庭</a></li>
				<li><a href="<c:url value='/courtPub/vod/userList'/>">用户管理</a></li>
		</ul>
		<div style="clear:both"></div>
	</div>
	<div style="clear:both"></div>
	<!--导航区域结束-->
	<!-- 搜索部分 -->
	<div class="col-lg-12">
		<!-- 条件区域开始 -->
		<form action="<c:url value='/courtPub/vod/caseAndPlan'/>" method="get">
			<div style="width: 1290px; height: 30px;">
				<div class="search_add">
					<input type="hidden" name="trailStatus" value="闭庭"  />
				<select style="width: 150px; text-align: center; height: 25px;"	name="qp_orgId">
						<option value="请选择法院">请选择法院</option>
						<c:forEach var="orgName" items="${orgNames}" varStatus="status">
							<option value="${orgName.id}">${orgName.orgName}</option>
						</c:forEach>
					</select>&nbsp;&nbsp; 
					<input type="text" value="输入你要查询的案号……" name="qp_caseNum"
					id="caseText"	onfocus="displayText(this.id)" 
						class="text_search" /> <input type="hidden"
						value="${trailStatus}" name="qp_trailStatus" id="trailStatus" />
					&nbsp;&nbsp;开始时间:<input type="text"
						id="d242"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d241\');}'})"
						class="Wdate" style="width: 200px;" name="qp_startTime" 
						readonly /> &nbsp;&nbsp; 结束时间：<input type="text" id="d241"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d242\');}'})"
						class="Wdate" style="width: 200px;" name="qp_endTime"
						readonly /> &nbsp;&nbsp; <input type="submit" value="查询"
						class="btn btn-info" style="height: 25px; font-size: 13px"/>	
				</div>
			</div>
		</form>
		<div style="clear:both"></div>
		<!--条件区域结束  -->
	</div>
	<div style="clear:both"></div>
	<!-- 搜索部分结束 -->
	<!-- 分页部分  -->
	<div class="col-lg-12" style="height:auto;zoom:1;overflow: hidden;style="height: 450px;"">
	<!--数据展示区开始  -->
		<table id="datalist">
				<tr>
					<th style="width: 10%">所属法院</th>
					<th style="width: 17%">案号</th>
					<th style="width: 31%">直播预告信息</th>
					<th style="width: 10%">开庭地点</th>
					<th style="width: 12%">开庭时间</th>
					<th style="width: 8%">推送最高院</th>
					<th style="width: 10%">操作管理</th>
				</tr>
				<c:forEach var="clvList" items="${lis_dev}" varStatus="status">
					<tr>
						<td>${clvList.courtName }</td>
						<td>${clvList.caseNum }</td>
						<td>${clvList.caseName }</td>
						<td>${clvList.courtRoomName }</td>
						<td><fmt:formatDate value="${clvList.planStartTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<%-- <td><fmt:formatDate value="${clvList.planEndTime }"
								type="both" /></td> --%>
						<c:if test="${clvList.allowLiveFlag =='2'}">
						<td align="center">是</td>
						</c:if>
						<c:if test="${clvList.allowLiveFlag =='1'}">
						<td align="center">否</td>
						</c:if>
						<td><input type="button" value="播放" class="btn_search"
							onclick="location.href='<c:url value='/courtPub/vod/toVod?id=${clvList.id}'/>';" />
							<c:if test="${operateFlag == true}">
								<input type="button" value="删除" class="vod_search"
								onclick="deleteVod('${clvList.id}')" />
							</c:if>
							<c:if test="${operateFlag == false}">
								<input type="button" value="删除" class="vod_disable"
								onclick="deleteVod('${clvList.id}')"  disabled="disabled"/>
							</c:if>		
						</td>
						
					</tr>
				</c:forEach>
			</table>
			<div style="clear:both"></div>
			<div style="clear:both;">
			<!--分页-->
			<c:if test="${pagi.list != null }">
				<c:if test="${fn:length(pagi.list) != 0 }">
					<jsp:include page="/common/component/ajaxPager.jsp">
						<jsp:param name="actionUrl"
							value="${pageContext.request.contextPath }/courtPub/vod/vodList/${courtIds}" />
						<jsp:param name="totalCount" value="${pagi.totalCount}" />
						<jsp:param name="pageSize" value="${pagi.pageSize}" />
						<jsp:param name="showPageNum" value="5" />
					</jsp:include>
				</c:if>
			</c:if>
			<div style="clear:both"></div>
		</div>
		<div style="clear:both"></div>
	</div>
	<div style="margin-top:50px;clear:both"></div>
</body>
</html>