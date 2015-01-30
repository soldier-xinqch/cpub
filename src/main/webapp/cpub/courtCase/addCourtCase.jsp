<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/include/include.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>添加案件</title>
</head>
<body>
	<!--主要区域开始-->
	<div id="vod_pub_main" class="pure-u-1">
		<div class="pure-u-1-3 left" style="height:40px;">
			<h2>新增案件信息</h2>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<!-- 隐藏表单开始 -->
		<input type="hidden" name="courtId" value="${courtIds }" id="courtId"/>
		<input type="hidden"  value="" id="caseId"/>
	<!-- 隐藏表单结束 -->
	<form id="courtCaseForm" class="pure-form pure-form-aligned" action="add" method="post" class="main_form" name="frm"  onsubmit="return validate_channel_info(this);">
		 <fieldset class="pure-u-1-2 left" style="margin-top: 20px;">
	        <div class="pure-control-group marginDivLeft">
	            <label for="name">法院名称:</label>
	             <select id="state" class="pure-u-1-2" style="height: 34px;text-align: left;" name="courtId">
					<!-- <option value="请选择法院">请选择法院</option> -->
					<c:forEach var="orgName" items="${orgNames}" varStatus="status">
						<option value="${orgName.id}">${orgName.orgName}</option>
					</c:forEach>
				</select>
	        </div>
	
	        <div class="pure-control-group marginDivLeft">
	            <label >案号:</label>
	            <input id="num" class="pure-u-1-2" placeholder="案号" type="text" name="caseNum" maxlength="50" value="" />
	        </div>
	
	        <div class="pure-control-group marginDivLeft">
	            <label >直播预告信息:</label>
	            <input type="text" class="pure-u-1-2" placeholder="直播预告信息"  id="name" name="caseName" maxlength="75" />
	        </div>
	        <div class="pure-control-group marginDivLeft">
	            <label>案件类型:</label>
	            <select style="width: 310px;text-align: left;height:34px;" name="caseTypeName" id="caseTypeName">
					 <option value="请选择案件类型">请选择案件类型</option>
					 <option value="民事案件" >民事案件</option>  
				     <option value="刑事案件">刑事案件</option>
				     <option value="行政案件">行政案件</option>
				     <option value="审监案件">审监案件</option>
				     <option value="减刑假释">减刑假释</option>
				</select>
	        </div>
	        
	        <div class="pure-control-group marginDivLeft">
	            <label >立案时间:</label>
	          <input type="text" id="d242"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:310px;height:34px;" name="registerTimed" value=""  readonly/>
	        </div>
	     	<div class="pure-control-group marginDivLeft">
	            <label>案件详细:</label>
	            <textarea id="caseDescText" class="pure-u-1-2"  placeholder="请输入案件的详细信息……" name="caseDesc" style="height: 100px;resize: none;"></textarea>
	        </div>
		</fieldset>
		
		
		<fieldset class="pure-u-1-2 right" style="margin-top: 20px;">
	        <div class="pure-control-group marginDivRight">
	            <label for="name">原告:</label>
	            <input type="text" placeholder="原告" class="pure-u-1-2" name="accuser" value="" id="" maxlength="200"/>
	        </div>
	
	        <div class="pure-control-group marginDivRight">
	            <label >被告:</label>
	          	<input type="text" placeholder="被告" class="pure-u-1-2" name="accused" value="" id="" maxlength="200"/>
	        </div>
	
	        <div class="pure-control-group marginDivRight">
	            <label >原告律师:</label>
	            <input type="text" placeholder="原告律师" class="pure-u-1-2" name="accuserLawer" value="" id="" maxlength="200"/>
	        </div>
	        <div class="pure-control-group marginDivRight">
	            <label>被告律师:</label>
	            <input type="text" placeholder="被告律师" class="pure-u-1-2" name="accusedLawer" value="" id="" maxlength="200"/>
	        </div>
	        <div class="pure-control-group marginDivRight" style="height: 26px;">
	        
	        </div>
	     	<div class="pure-control-group marginDivRight">
	            <label>当事人详细:</label>
	          <textarea id="CasePartyText" placeholder="请输入当事人的详细信息……" class="pure-u-1-2" name="party" style="height: 100px;resize: none;"></textarea>
	        </div>
		</fieldset>
		<div class="pure-u-8-24">&nbsp;</div>
		<div style="width:100%;margin-bottom: 20px;text-align: center;">
        	<input type="button" class="pure-button pure-button-primary pure-u-1-8" value="保存" id="submitBtn" />
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<input type="button" class="pure-button pure-button-primary pure-u-1-8" value="返回" 
        		onclick="location.href='<c:url value='/courtPub/vod/list'/>';"/>
       	</div>
	</form>
		
<script type="text/javascript" charset="utf-8">
	<%@ include file="case.js" %>
</script>
</body>
</html>
