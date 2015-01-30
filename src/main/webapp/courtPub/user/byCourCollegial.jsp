<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>合议庭成员</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta name="decorator" content="none" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global.css'/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global_color.css'/>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script type="text/javascript">
	function chooseCase() {
		var heyiting ="";
		var heyiId = "";
		 var checklist = document.getElementsByName ("HEYIName");
		 for(var i=0;i<checklist.length;i++)
		   {
		      var thisValue = checklist[i].checked;
		      if(thisValue){
	    
	    		  heyiting=heyiting+" "+checklist[i].value;
		    	  heyiId = heyiId+" "+ checklist[i].id; 
		      }
	      
		   } 
			parent.document.getElementById("heyiName").value = heyiting;
			parent.document.getElementById("collegialId").value = heyiId;
	} 
	var personValue ="";
	var personId ="";
	function choosePerson(id) {

		if(document.getElementById("heYiName").value == null || document.getElementById("heYiName").value == ""){

			personValue = window.parent.document.getElementById("heyiName").value;
			personId = window.parent.document.getElementById("collegialId").value;
			document.getElementById("heYiName").value=personValue;
	    	document.getElementById("heYiId").value=personId;
  	  	}
		
		if(document.getElementById(id).checked){

			var checkBoxValue = document.getElementById(id).value;
			var checkBoxId = id;
			
			personValue = checkBoxValue +" "+ personValue;
			personId = checkBoxId +" "+ personId;
			parent.document.getElementById("heyiName").value = personValue;
			parent.document.getElementById("collegialId").value = personId;		
		}else{
			if(personValue != null){
				var unCheckValue="";
				var unCheckId ="";
				var checkBoxId = id;
				var cutValue =new Array();
				cutValue = personValue.split(" ");
				var cutId = new Array();
				cutId=personId.split(" ");
				for(var i=0;i<cutId.length;i++){
					if(checkBoxId != cutId[i]){
						
						unCheckValue = cutValue[i]+" "+unCheckValue;
						unCheckId =  cutId[i]+" "+unCheckId;
					}
				}
				parent.document.getElementById("heyiName").value = unCheckValue;
				parent.document.getElementById("collegialId").value = unCheckId;
				personValue =unCheckValue;
				personId =unCheckId;
			}
			
		}
		
	}
	
	function selectAll(){
		var checklist = document.getElementsByTagName("input");
		if(document.getElementById("controlAll").checked){											
		   for(var i=0;i<checklist.length;i++){
		      checklist[i].checked = 1;
		   } 
		 }else{
		  for(var j=0;j<checklist.length;j++){
		     checklist[j].checked = 0;
		  }
		 }
		chooseCase();
	}
	
	window.onload = function() {
		var heYiId = window.parent.document.getElementById("collegialId").value;
		if(heYiId != null||heYiId != " "){
			var cutHeYiId = heYiId.split(" ");
			var checklist = document.getElementsByName("HEYIName");

			for (var j = 0; j < cutHeYiId.length; j++) {
				for (var i = 0; i < checklist.length; i++) {
					if (checklist[i] != cutHeYiId[j]) {
						var checkedbox = document.getElementById(cutHeYiId[j]);
						if(checkedbox != null){
							checkedbox.checked=1;
			
							
						}
						
					}
				}
			}
		}
	}
</script>

</head>
<body style="background-color: #E8F3F8; width: 350px;">
	<!-- 右侧区域开始 -->
	<form action="<c:url value='/courtPub/vod/selectHeYiByCourt'/>"
		method="get">
		<div class="search_add">
			<!-- 把选中的数据传回后台 -->
			<input type="hidden" name="qp_heYiId" value="" id="heYiId">
			<input type="hidden" name="qp_heYiName" value="" id="heYiName">
			<!-- 传回结束 -->
			<div style="float: left; padding: 5px 0px 5px 14px;">
				<select
					style="width: 150px; text-align: center; height: 25px; float: left;"
					name="qp_orgId">
					<option value="请选择法院">请选择法院</option>
					<c:forEach var="orgName" items="${orgNames}" varStatus="status">
						<option value="${orgName.id}">${orgName.orgName}</option>
					</c:forEach>
				</select> <input type="submit" style="margin-right: 130px;"
					class="btn_search" value="查询" />
			</div>
		</div>
		<table id="datalist" style="width: 350px; text-align: center;">
			<tr>
				<th style="width: 10%">全选<input type="checkbox" id="controlAll"
					name="controlAll" onclick="selectAll()" /></th>
				<th style="width: 27%">所属法院</th>
				<th style="width: 27%">合议庭成员</th>
				<!-- <th style="width: 32%">合议庭成员图片</th> -->
			</tr>
			<c:forEach var="clvList" items="${lis_dev}" varStatus="status">
				<tr>
					<td><input type="checkbox" id="${clvList.id}" name="HEYIName"
						value="${clvList.realName}"
						onclick="choosePerson('${clvList.id}');" /></td>
					<td>${clvList.courtName}</td>
					<td>${clvList.realName}</td>
					<%-- <td>${clvList.photo }</td> --%>
			</c:forEach>
		</table>
		<div style="width: 95%;">
			<!--分页-->
			<c:if test="${pagi.list != null }">
				<c:if test="${fn:length(pagi.list) != 0 }">
					<jsp:include page="/common/component/ajaxPager.jsp">
						<jsp:param name="actionUrl"
							value="${pageContext.request.contextPath }/courtPub/vod/selectHeYiByCourt" />
						<jsp:param name="totalCount" value="${pagi.totalCount}" />
						<jsp:param name="pageSize" value="${pagi.pageSize}" />
						<jsp:param name="showPageNum" value="5" />
						<jsp:param name="qp.name1" value="qp_orgId" />
					</jsp:include>
				</c:if>
			</c:if>
		</div>
	</form>
</body>
</html>