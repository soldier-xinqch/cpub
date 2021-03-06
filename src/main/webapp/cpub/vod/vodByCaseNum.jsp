<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>点播</title>
</head>
<body>
	<!--导航区域开始-->
	<div class="pure-menu pure-menu-open pure-menu-horizontal menu" style="background-color: #6699ff;">
	       <ul id="menu">
			<li><a href="<c:url value='/courtPub/vod/pubBillboard'/>">&nbsp;&nbsp;&nbsp;&nbsp;直播预告&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/pubList'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;直播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/vodList'/>" style="background-color: #0099CC;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/list'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/roomList'/>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法庭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/userList'/>">&nbsp;&nbsp;&nbsp;&nbsp;用户管理&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
				<li><a href="<c:url value='/courtPub/statistics/home'/>">&nbsp;&nbsp;&nbsp;&nbsp;庭审统计&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		</ul>
	</div>
	<!--导航区域结束-->
	<!-- 搜索部分 -->
	<div class="pure-u-1">
		<form class="pure-form pure-form-aligned" action="<c:url value='/courtPub/vod/caseAndPlan'/>" method="get">
			<!-- 隐藏域开始 -->
				<input type="hidden" name="trailStatus" value="闭庭"  />
				<input type="hidden" value="${trailStatus}" name="qp_trailStatus" id="trailStatus" />
			<!-- 隐藏域结束 -->
			<div class="pure-control-group pure-u-1-6" style="float: left;padding: 0px;">
         	 <select id="state" style="width: 200px; text-align: center; height: 34px;float: left;"	name="qp_orgId">
					<option value="请选择法院">请选择法院</option>
					<c:forEach var="orgName" items="${orgNames}" varStatus="status">
						<option value="${orgName.id}">${orgName.orgName}</option>
					</c:forEach>
			</select>
       		</div>
       		<div class="pure-control-group pure-u-1-6" style="float: left;">
       			<input id="caseText" type="text" placeholder="输入你要查询的案号……" name="qp_caseNum"  class="bodySearch" />
      		</div>
			<div class="pure-1-3" style="float: left;">
	    		&nbsp;开始时间:<input type="text" id="d242" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d241\');}'})"
								class="Wdate" style="width: 140px; height: 34px;" name="qp_startTime" readonly />
				&nbsp;结束时间：<input type="text" id="d241" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d242\');}'})"
					class="Wdate" style="width: 140px;height: 34px;" name="qp_endTime" readonly />
    				&nbsp;
    		</div>
    		<button type="submit" class="pure-button pure-button-primary" style="float: left;height: 33px;" >查询</button>
		</form>
	</div>
	<!-- 搜索部分结束 -->
	<!-- 数据展示开始  -->
	<div class="pure-u-1 bodyData">
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
						<td><input type="button" value="播放" class="button-success pure-button"
							onclick="location.href='<c:url value='/courtPub/vod/toVod?id=${clvList.id}'/>';" />
							<c:if test="${operateFlag == true}">
								<input type="button" value="删除" class="button-error pure-button" 
								onclick="deleteVod('${clvList.id}')" />
							</c:if>
							<c:if test="${operateFlag == false}">
								<input type="button" value="删除" class="button-error pure-button" 
								onclick="deleteVod('${clvList.id}')"  disabled="disabled"/>
							</c:if>		
						</td>
						
					</tr>
				</c:forEach>
			</table>
			<div style="width:95%;">
			<!--分页-->
			<c:if test="${pagi.list != null }">
				<c:if test="${fn:length(pagi.list) != 0 }">
					<jsp:include page="/common/component/ajaxPager.jsp">
						<jsp:param name="actionUrl"
							value="${pageContext.request.contextPath }/courtPub/vod/caseAndPlan" />
						<jsp:param name="totalCount" value="${pagi.totalCount}" />
						<jsp:param name="pageSize" value="${pagi.pageSize}" />
						<jsp:param name="showPageNum" value="5" />
						<jsp:param name="qp.name1" value="caseNum" />
						<jsp:param name="qp.name2" value="startTime" />
						<jsp:param name="qp.name3" value="endTime" />
						<jsp:param name="qp.name4" value="trailStatus" />
						<jsp:param name="qp.name5" value="qp_orgId" />
					</jsp:include>
				</c:if>
			</c:if>
			</div>
	</div>
<script type="text/javascript">
	<%@ include file="vod.js" %>
</script>
</body>
</html>