<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>案件查询</title>
</head>
<body>
	<!--导航区域开始-->
	<div class="pure-menu pure-menu-open pure-menu-horizontal menu" style="background-color: #6699ff;">
	       <ul id="menu">
			<li><a href="<c:url value='/courtPub/vod/pubBillboard'/>">&nbsp;&nbsp;&nbsp;&nbsp;直播预告&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/pubList'/>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;直播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/vodList'/>" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点播&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/list'/>" style="background-color: #0099CC;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;案件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/roomList'/>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;法庭&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/vod/userList'/>">&nbsp;&nbsp;&nbsp;&nbsp;用户管理&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="<c:url value='/courtPub/statistics/home'/>">&nbsp;&nbsp;&nbsp;&nbsp;庭审统计&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		</ul>
	</div>
	<!--导航区域结束-->
	<!-- 搜索部分 -->
	<div class="pure-u-1">
		<form class="pure-form pure-form-aligned" action="<c:url value='/courtPub/vod/byCaseNum'/>" method="get">
			<div class="pure-control-group pure-u-1-5" style="float: left;padding: 0px;">
         	 <select id="state"	name="qp_orgId" style="width: 200px;height: 34px;float: left;">
					<option value="请选择法院">请选择法院</option>
					<c:forEach var="orgName" items="${orgNames}" varStatus="status">
						<option value="${orgName.id}">${orgName.orgName}</option>
					</c:forEach>
			</select>
       		</div>
       		<div class="pure-control-group pure-u-1-6" style="float: left;">
	           <input id="caseText" type="text" placeholder="输入你要查询的案号……" name="qp_caseNum"  class="bodySearch">
      		</div>
      		 <div class="pure-control-group pure-u-1-8" style="float: left;">
         		 <select id="selectCasetype" class="bodySearch" name="qp_casetype" style="width:160px;">
							<option value="请选择案件类型">请选择案件类型</option>
							<option value="民事案件" >民事案件</option>  
						     <option value="刑事案件">刑事案件</option>
						     <option value="行政案件">行政案件</option>
						     <option value="审监案件">审监案件</option>
						     <option value="减刑假释">减刑假释</option>
				</select>
       		</div>
    		<div class="pure-1-3" style="float: left;">
	    		&nbsp;开始时间:<input type="text" id="d242" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d241\');}'})"
								class="Wdate" style="width: 140px; height: 34px;" name="qp_startTime" readonly />
				&nbsp;结束时间：<input type="text" id="d241" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d242\');}'})"
					class="Wdate" style="width: 140px;height: 34px;" name="qp_endTime" readonly />
    				&nbsp;
    		</div>
    
       	<button type="submit" class="pure-button pure-button-primary" style="float: left;height: 33px;" >查询</button>
			<div class="pure-u-2-24" style="float: right;">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="增加" class="pure-button pure-button-primary" style="height: 33px;" onclick="location.href='<c:url value='/courtPub/vod/toAdd'/>';" />
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
				<th style="width: 12%">案号</th>
				<th style="width: 27%">直播预告信息</th>
				<th style="width: 7%">案件类型</th>
				<th style="width: 10%">立案时间</th>
				<th style="width: 7%">操作管理</th>
			</tr>
			<c:forEach var="clvList" items="${lis_dev}" varStatus="status">
				<tr>
					<td>${clvList.courtName }</td>
					<td>${clvList.caseNum }</td>
					<td><a
						href='<c:url value="/courtPub/vod/caseDesc"/>?id=${clvList.id}'>${clvList.caseName }</a></td>
					<td>${clvList.caseTypeName }</td>
					<td><fmt:formatDate value="${clvList.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><input type="button" value="修改" class="button-secondary pure-button"
						onclick="location.href='<c:url value='/courtPub/vod/toModify?id=${clvList.id}'/>';" />
						<input type="button" value="删除" class="button-error pure-button" id="deleteCase"
						onclick="deleteVod('${clvList.id}')" /></td>
			</c:forEach>
		</table>
		<div style="width:95%;">
	<!--分页-->
	<c:if test="${pagi.list != null }">
		<c:if test="${fn:length(pagi.list) != 0 }">
			<jsp:include page="/common/component/ajaxPager.jsp">
				<jsp:param name="actionUrl"
					value="${pageContext.request.contextPath }/courtPub/vod/byCaseNum" />
				<jsp:param name="totalCount" value="${pagi.totalCount}" />
				<jsp:param name="pageSize" value="${pagi.pageSize}" />
				<jsp:param name="showPageNum" value="5" />
				<jsp:param name="qp.name1" value="caseNum" />
				<jsp:param name="qp.name2" value="startTime" />
				<jsp:param name="qp.name3" value="endTime" />
				<jsp:param name="qp.name4" value="qp_orgId" />
				<jsp:param name="qp.name5" value="qp_casetype"/>
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