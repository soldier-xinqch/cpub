<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>增加法庭</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body class="yui3-skin-sam">
	<div class="bodyDiv">
		<!--主要区域开始-->
		<div id="vod_pub_main" class="pure-u-1">
			<div class="pure-u-1-3 left"style="height:40px;">
				<h2>新增法庭信息</h2>
			</div>
			<div class="clearfix"></div>
			<hr>
		</div>
		<input type="hidden" name="id" value="${courtRoom.id}" id="roomId"/>
		<div class="bodyForm">
			<form id="courtRoomForm" class="pure-form pure-form-aligned" action="addRoom" method="post" class="vod_main_form" name="frm"
					onsubmit="return validate_channel_info(this);">
			    <fieldset>
			        <div class="pure-control-group">
			            <label for="name">法院名称:</label>
			             <select id="state" class="pure-u-1-5" style="height: 36px;" name="orgId">
							<c:forEach var="orgName" items="${orgNames}" varStatus="status">
								<option value="${orgName.id}">${orgName.orgName}</option>
							</c:forEach>
						</select>
			            
			           <%--  <input type="text" class="pure-u-1-5" placeholder="法院名称" name="" value="${courtName }" id="court" />
						<input type="hidden" name="orgId" value="${courtIds }" id="courtId"/> --%>
			        </div>
			
			        <div class="pure-control-group">
			            <label >法庭名称:</label>
			            <input type="text" class="pure-u-1-5" placeholder="法庭名称" maxlength="16" name="roomName" id="roomName" />
			        </div>
			
			        <div class="pure-control-group">
			            <label >法庭类型:</label>
			            <input type="text" class="pure-u-1-5" placeholder="法庭类型" name="roomType" value="" maxlength="16" id="roomType" />
			        </div>
			
			        <div class="pure-control-group">
			            <label>直播流地址:</label>
			            <input type="text" class="pure-u-1-5" placeholder="直播流地址" name="wwwLiveUrl" maxlength="255" id="pubAccessUrl" value="">
			        </div>
			     	<div class="pure-control-group">
			            <label>法庭详细:</label>
			            <textarea name="roomMemo" class="pure-u-1-5" placeholder="请输入详细信息……" style="height: 100px;" onkeyup="checkLength(this);" id="caseText" ></textarea>  
						<br><small style="margin-left:200px;">*文字最大长度: 250.个字符 还剩: <span id="chLeft">250</span>.个字符</small> 
			        </div>
			      	<div class="pure-controls" style="margin-right: 80px;">
			        	<input type="button" class="pure-button pure-button-primary pure-u-1-8" value="保存" id="submitBtn"/>
			        	<input type="button" class="pure-button pure-button-primary pure-u-1-8" value="返回" 
			        		onclick="location.href='<c:url value='/courtPub/vod/roomList'/>';"/>
			       	</div>
			   		</fieldset>
			</form>
		</div>
		<!--主要区域结束-->
	</div>
<script type="text/javascript" charset="utf-8">
	<%@ include file="room.js" %> 
</script>
</body>
</html>