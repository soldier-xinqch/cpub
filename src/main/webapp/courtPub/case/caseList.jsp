<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>选择案件</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="decorator" content="none" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global.css'/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global_color.css'/>" />
<script type='text/javascript'
	src='<c:url value="/public/js/time/WdatePicker.js"/>'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script type="text/javascript"> 
    var clickFlag="";
	function chooseCase(caseId,caseNum,caseName,caseTypeName,registerTime) {
		/* if (confirm("确认选择此案件么？")) { */
			/* window.opener.document.getElementById("caseId").value = caseId; */
			parent.document.getElementById("caseId").value = caseId;
			parent.document.getElementById("caseNums").value = caseNum;
			parent.document.getElementById("cname").value = caseName;
			parent.document.getElementById("caseTypeName").value = caseTypeName;
			parent.document.getElementById("registerTime").value = registerTime;
			choosed(caseId);
			clickFlag = caseId;
		/* } */
	}
	
	function choosed(id){
		var bgcolor = "#E8F3F8";
		if(clickFlag == ""){
			clickFlag = id;
		}
		document.getElementById(clickFlag).style.background = "";
		document.getElementById(id).style.background = bgcolor;
	}
	
	 function displayText(id){
		 document.getElementById(id).value="";
	 }
	
</script>  

</head>
<body style="background-color: #E8F3F8;overflow-y:scroll;">
	<!-- 右侧区域开始 -->
		<form class="pure-form pure-form-aligned" action="<c:url value='/courtPub/vod/byCaseNum'/>" method="get">
			<div class="pure-control-group pure-u-1-5" style="float: left;">
         	 <select id="state"	name="qp_orgId" style="width: 150px;height: 28px;">
					<option value="请选择法院">请选择法院</option>
					<c:forEach var="orgName" items="${orgNames}" varStatus="status">
						<option value="${orgName.id}">${orgName.orgName}</option>
					</c:forEach>
			</select>
       		</div>
       		<div class="pure-control-group pure-u-1-6" style="float: left;margin-right: 20px;">
	           <input id="caseText" type="text" placeholder="输入你要查询的案号……" name="qp_caseNum"  class="bodySearch" style="width: 150px;height: 28px;">
      		</div>
      		 <div class="pure-control-group pure-u-1-6" style="float: left;margin-right: 0px;">
         		 <select id="selectCasetype" class="bodySearch" name="qp_casetype" style="width:130px;height: 28px;">
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
								class="Wdate" style="width: 90px; height: 28px;" name="qp_startTime" readonly />
				&nbsp;结束时间：<input type="text" id="d241" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d242\');}'})"
					class="Wdate" style="width: 90px;height: 28px;" name="qp_endTime" readonly />
    				&nbsp;
    		</div>
    
       	<button type="submit" class="pure-button pure-button-primary" style="float: left;height: 33px;" >查询</button>
		</form>
	</div>	
	<div class="clearfix"></div>
	<table id="datalist" style="width: 850px; ">
			<tr>
				<th style="width: 10%">所属法院</th>
				<th style="width: 14%">案号</th>
				<th style="width: 27%">案件名称</th>
				<th style="width: 6%">案件类型</th>
				<th style="width: 12%">立案时间</th>
				<th style="width: 5%">操作</th>
			</tr>
			<c:forEach var="clvList" items="${lis_dev}" varStatus="status">
				<tr id="${clvList.id}" style="background:#fff;" class="bgNormal">
					<td>${clvList.courtName }</td>
					<td>${clvList.caseNum }</td>
					<td id="caseName" style="font-size: 11px;" >${clvList.caseName }</td>
					<td>${clvList.caseTypeName }</td>
					<td><fmt:formatDate value="${clvList.registerTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><input type="button" class="btn_search" name="" value="选择"
						id="" onclick="chooseCase('${clvList.id}','${clvList.caseNum }','${clvList.caseName }','${clvList.caseTypeName }','<fmt:formatDate value="${clvList.registerTime }" type="both" />')" /></td>
				</tr>
			</c:forEach>
		</table>
		<div style="width:95%;">
		<!--分页-->
		<c:if test="${pagi.list != null }">
			<c:if test="${fn:length(pagi.list) != 0 }">
				<jsp:include page="/common/component/ajaxPager.jsp">
					<jsp:param name="actionUrl"
						value="${pageContext.request.contextPath }/courtPub/vod/caseList" />
					<jsp:param name="totalCount" value="${pagi.totalCount}" />
					<jsp:param name="pageSize" value="${pagi.pageSize}" />
					<jsp:param name="showPageNum" value="5" />
					<jsp:param name="qp.name1" value="caseNum" />
					<jsp:param name="qp.name2" value="startTime" />
					<jsp:param name="qp.name3" value="endTime" />
				</jsp:include>
			</c:if>
		</c:if>
		</div>
</body>
</html>