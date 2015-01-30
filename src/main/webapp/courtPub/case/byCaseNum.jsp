<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>案件查询</title>
<script type="text/javascript">
//删除
function deleteVod(id) {
		verify(id);
}
 function verify(id){
		$.ajax({
			type:"get",
			url:"<c:url value='/verifyCaseDelete?id="+id+"'/>",
			success : function(data) {  
			     if(false==data.data){
			    		alert("该案件已经排期，无法删除！");
			     }else{
		    		var r = window.confirm("确定要删除此条案件信息吗？");
					if (r) {
						document.getElementById("operate_result_info").innerHTML = "删除成功!";
						document.getElementById("operate_result_info").style.display = "block";
						window.location =  '<c:url value="/courtPub/vod/delete?id=' + id + '"/>';
					}
			     }
			     /*   alert("Data Saved: " + data.data); */
			   }  
		});
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
			<li><a href="<c:url value='/courtPub/vod/vodList'/>">点播</a></li>
			<li><a href="<c:url value='/courtPub/vod/list'/>"
				style="background-color: #0099CC;">案件</a></li>
			<li><a href="<c:url value='/courtPub/vod/roomList'/>">法庭</a></li>
			<li><a href="<c:url value='/courtPub/vod/userList'/>">用户管理</a></li>
		</ul>
	</div>
	<!--导航区域结束-->
	<!-- 搜索部分 -->
	<div class="col-lg-12" style="height: 450px;">
		<!-- 条件区域开始 -->
	<form action="<c:url value='/courtPub/vod/byCaseNum'/>" method="get">
			<div style="width: 1290px; height: 30px;">
				<div>
					<input type="button" value="增加" class="btn_add"
						onclick="location.href='<c:url value='/courtPub/vod/toAdd'/>';" />
				</div>
				<div class="search_add">
						<select id="selectCourt" name="qp_orgId">
							<option value="请选择法院">请选择法院</option>
							<c:forEach var="orgName" items="${orgNames}" varStatus="status">
								<option value="${orgName.id}">${orgName.orgName}</option>
							</c:forEach>
						</select> &nbsp;&nbsp;
						<input type="text" value="输入你要查询的案号……"
							name="qp_caseNum" class="text_search" id="caseText"
							onfocus="displayText(this.id);" /> &nbsp;&nbsp; 
							
						<select id="selectCasetype" name="qp_casetype">
							<option value="请选择案件类型">请选择案件类型</option>
							<option value="民事案件" >民事案件</option>  
						     <option value="刑事案件">刑事案件</option>
						     <option value="行政案件">行政案件</option>
						     <option value="审监案件">审监案件</option>
						     <option value="减刑假释">减刑假释</option>
						</select> &nbsp;&nbsp;
						开始时间:<input type="text" id="d242" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d241\');}'})"
							class="Wdate" style="width: 140px;" name="qp_startTime" readonly />
						&nbsp;&nbsp; 
						结束时间：<input type="text" id="d241" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d242\');}'})"
							class="Wdate" style="width: 140px;" name="qp_endTime" readonly />
						&nbsp;&nbsp; <input type="submit" value="查询" class="btn btn-info" style="height: 25px; font-size: 13px"/>					
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
				<td><fmt:formatDate value="${clvList.registerTime }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><input type="button" value="修改" class="btn_search"
					onclick="location.href='<c:url value='/courtPub/vod/toModify?id=${clvList.id}'/>';" />
					<input type="button" value="删除" class="btn_search"
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
</body>
</html>