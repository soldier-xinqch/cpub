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
<title>庭审案件详细信息</title>
</head>
<body>
	<!--主要区域开始-->
	<div id="vod_pub_main" class="pure-u-1">
		<div class="pure-u-1-3 left"style="height:40px;">
			<h2>庭审案件信息详细</h2>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<form id="courtCaseForm" class="pure-form pure-form-aligned" action="modify" method="post" class="main_form" name="frm"  onsubmit="return validate_channel_info(this);">
		 <fieldset class="pure-u-1-2 left" style="margin-top: 20px;">
	        <div class="pure-control-group marginDivLeft">
	            <label for="name">法院名称:</label>
	            <input id="num" class="pure-u-1-2" placeholder="案号" type="text" name="caseNum" maxlength="50" value="${courtName }" readonly/>
	        </div>
	
	        <div class="pure-control-group marginDivLeft">
	            <label >案号:</label>
	            <input id="num" class="pure-u-1-2" placeholder="案号" type="text" name="caseNum" maxlength="50" value="${courtCase.caseNum}" readonly/>
	        </div>
	
	        <div class="pure-control-group marginDivLeft">
	            <label >直播预告信息:</label>
	            <input type="text" class="pure-u-1-2" placeholder="直播预告信息"  id="name" name="caseName" maxlength="75"  value="${courtCase.caseName }" readonly/>
	        </div>
	        <div class="pure-control-group marginDivLeft">
	            <label>案件类型:</label>
	            <input type="text" class="pure-u-1-2" placeholder="直播预告信息"  id="name" name="caseName" maxlength="75"  value="${courtCase.caseTypeName }" readonly/>
	        </div>
	        
	        <div class="pure-control-group marginDivLeft">
	            <label >立案时间:</label>
	          <input type="text" id="d242" value="<fmt:formatDate value="${courtCase.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:310px;height:34px;" name="registerTimed" value=""  readonly/>
	        </div>
	     	<div class="pure-control-group marginDivLeft">
	            <label>案件详细:</label>
	            <textarea id="caseDescText" class="pure-u-1-2"  placeholder="请输入详细信息……" name="caseDesc" style="height: 100px;resize: none;" readonly>${courtCase.caseDesc }</textarea>
	        </div>
		</fieldset>
		
		
		<fieldset class="pure-u-1-2 right" style="margin-top: 20px;">
	        <div class="pure-control-group marginDivRight">
	            <label for="name">原告:</label>
	            <input type="text" placeholder="原告" class="pure-u-1-2" name="accuser"  value="${courtCase.accuser}"  id="" maxlength="200" readonly/>
	        </div>
	
	        <div class="pure-control-group marginDivRight">
	            <label >被告:</label>
	          	<input type="text" placeholder="被告" class="pure-u-1-2" name="accused"  value="${courtCase.accused}" id="" maxlength="200" readonly/>
	        </div>
	
	        <div class="pure-control-group marginDivRight">
	            <label >原告律师:</label>
	            <input type="text" placeholder="原告律师" class="pure-u-1-2" name="accuserLawer" value="${courtCase.accuserLawer }" id="" maxlength="200" readonly/>
	        </div>
	        <div class="pure-control-group marginDivRight">
	            <label>被告律师:</label>
	            <input type="text" placeholder="被告律师" class="pure-u-1-2" name="accusedLawer" value="${courtCase.accusedLawer }" id="" maxlength="200" readonly/>
	        </div>
	        <div class="pure-control-group marginDivRight" style="height: 26px;">
	        </div>
	     	<div class="pure-control-group marginDivRight">
	            <label>法庭详细:</label>
	          <textarea id="CasePartyText" placeholder="请输入详细信息……" class="pure-u-1-2" name="party" style="height: 100px;resize: none;" readonly>${courtCase.party }</textarea>
	        </div>
		</fieldset>
	</form>
	<div class="pure-u-1-1 pure-controls" style="margin-bottom: 20px;">
       	<input type="button" class="pure-button pure-button-primary pure-u-1-8" value="返回" style="float: right;margin-right: 90px;"
       		onclick="location.href='<c:url value='/courtPub/vod/list'/>';"/>
    </div>
</body>
</html>