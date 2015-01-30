<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增用户</title>
</head>
<body>
	<!--主要区域开始-->
	<div id="vod_pub_main" class="pure-u-1">
		<div class="pure-u-1-3 left"style="height:40px;">
			<h2>新增用户信息</h2>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="bodyForm">
		<form id="courtUserForm" class="pure-form pure-form-aligned" action="addUser" enctype="multipart/form-data" method="post" class="main_form" name="frm"  onsubmit="return validate_channel_info(this);">
			<!-- 隐藏域  -->
			<input type="hidden" id="userId" value="" />
			<!-- 隐藏域  -->
				<fieldset>
			        <div class="pure-control-group">
			            <label for="name">法院名称:</label>
			             <select id="courtName" class="pure-u-1-5" style="height: 34px;" name="courtId">
							<c:forEach var="orgName" items="${orgNames}" varStatus="status">
								<option value="${orgName.id}">${orgName.orgName}</option>
							</c:forEach>
						</select>
			        </div>
			
			        <div class="pure-control-group">
			            <label >用户名:</label>
			            <input type="text" class="pure-u-1-5" placeholder="输入用户名" name="userName" value="" id="userName" onblur="verify()" maxlength="11" />
			        </div>
			
			        <div class="pure-control-group">
			            <label >密码:</label>
			            <input type="password" class="pure-u-1-5" placeholder="输入用户密码" name="userPassword" value="" id="passWord"  maxlength="11" />
			        </div>
			        <div class="pure-control-group">
			            <label>真实姓名:</label>
			            <input type="text" class="pure-u-1-5" placeholder="用户真实姓名" name="realName" value="" id="realName" maxlength="7" />
			        </div>
			     	<div class="pure-control-group " id="localUser" >
			            <label>角色:</label>
			            <input type="text" class="pure-u-1-5" value="合议庭成员" name="roles" id="roles" readonly="readonly"/> 
			        </div>
			        <div class="pure-control-group disPlayDiv" id="inferiorUser">
			            <label>角色:</label>
			           <select class="pure-u-1-5" style="text-align: center;height:38px;" name="roles" id="role"  >  
						     <option value="ROLE_ADMIN" >法院管理员</option>  
						     <option value="ROLE_ADMIN_HEYI" >合议庭成员</option>  
						</select>  
			        </div>
			        <div class="pure-control-group">
			            <label>上传人员头像:</label>
			            <input type="file" class="pure-u-1-5"  id="personPhoto" name="photo"  value=""  maxlength="200"/><br/>
					   	<span id="chLeft" style="font-size:11px;margin-left: 45px;"> 这里上传人员图片（可选）</span> 
			        </div>
			      	<div class="pure-controls" style="margin-right: 80px;">
			        	<input type="button" class="pure-button pure-button-primary pure-u-1-8" value="保存" id="submitBtn" />
			        	<input type="button" class="pure-button pure-button-primary pure-u-1-8" value="返回" 
			        		onclick="location.href='<c:url value='/courtPub/vod/userList'/>';"/>
			       	</div>
			   		</fieldset>	
					
		</form>
	</div>	
<script type="text/javascript">
	<%@ include file="user.js" %>
</script>	
</body>
</html>