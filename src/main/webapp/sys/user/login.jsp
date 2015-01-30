<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
<%
String server=request.getServerName();
int serverPort=request.getServerPort();
String contextPath=request.getContextPath();
if(serverPort!=80){
	server+=":" + serverPort;
}
if(!"/".equals(contextPath)){
	contextPath+="/";
}
%>
<!doctype html>
<html>
<head>
<title>公网直播管理系统用户登录</title>
<meta name="decorator" content="none" />
<%@ include file="/common/ref/ref.jsp"%>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value="/public/css/login.css"/>"> --%>

<style type="text/css">
html {
	overflow: hidden;
}

body {
	margin: 0px;
	background-color: #2BB4D6;
	overflow: hidden;
}

.fieldset1 {
	text-align: center;
	padding: 0;
	border: 0;
	margin-top: 50px;
}

.fieldset2 {
	border: 0;
	margin-top: 110px;
}

.fieldset3 {
	border: 0;
	text-align: center;
	font-size: 14px;
}

.but11 {
	width: 93px;
	height: 27px;
	border: 0;
	background: url("resource/image/login/but_zc.gif");
	cursor: pointer;
	color: #ffffff;
}

.but12 {
	width: 93px;
	height: 27px;
	border: 0;
	background: url("resource/image/login/but_xz.gif");
	cursor: pointer;
	color: #ffffff;
}

.but13 {
	width: 93px;
	height: 27px;
	border: 0;
	background: url("resource/image/login/but_ax.gif");
	cursor: pointer;
	color: #ffffff;
}

#log {
	font-family: Arial;
}

#log .td2 {
	width: 230px;
	text-align: right;
	height: 80px;
	font-size: 20px;
	color: #A1A1A1;
	font-weight: bold;
}

#log .td1 {
	width: 230px;
	text-align: right;
	height: 25px;
	color: #333333;
	font-size: 12px;
}

#log .td3 {
	font-size: 12px;
	color: #333333;
}
</style>
<script>
	var errType='${errType}';
	$(document).ready(function(){
		if(''==errType){
			focusForm();
		}else if('pwdErr'==errType){
			$('#j_password').focus();
		}else{
			$('#j_username').focus();
		}
		$('#btnLogin').click(function(){
			if(canSubmit()){
				$('#theForm').submit();
			}
		});
		$('#btnClear').click(
			function(){
				clearForm('theForm');
				$('#lab1').html('');
				focusForm();
			}
		);
		$('#j_username').blur(function(){
			if(!isInputEmpty('j_username')){
				$('#lab1').html('');
			}
		});
		$('#j_password').blur(function(){
			if(!isInputEmpty('j_password')){
				$('#lab1').html('');
			}
		});
		
		$('#j_username').keypress(function(event){
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13' && canSubmit()){
				$('#theForm').submit();
				return false;
			}
		});
		
		$('#j_password').keypress(function(event){
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13' && canSubmit()){
				$('#theForm').submit();
				return false;
			}
		});
	});
	
	function canSubmit(){
		if(isInputEmpty('j_username')){
			$('#lab1').html('请输入用户名。');
			$('#j_username').focus();
			return false;
		}
		if(isInputEmpty('j_password')){
			$('#lab1').html('请输入密码。');
			$('#j_password').focus();
			return false;
		}
		uname=$('#j_username').val();
		pwd=$('#j_password').val();
		if(!validatePureStr(uname)){
			$('#lab1').html('请输入合法用户名。');
			return false;
		}
		if(!validatePureStr(pwd)){
			$('#lab1').html('请输入合法密码。');
			return false;
		}
		return true;
	}
	
	function focusForm(){
		$(document).find('form:visible').each(function(){
            $(this).find('input:visible:enabled:first').focus();
        });
	}
</script>
<%-- <link rel="shortcut icon" href="favicon.ico"  type="image/x-icon" />
<link rel="icon" href="http://<%=server%><%=contextPath%>favicon.ico" /> --%>
</head>
<body>
	<form action="<c:url value="/j_spring_security_check"/>" name="theForm"
		method="post" id="theForm">
		<fieldset class="fieldset1">
			<table width="900px" border="0" align="center" height="500px"
				background="../../public/images/login/bg.jpg">
				<tr>
					<td>
						<fieldset class="fieldset2" style="text-align: left;">
							<table id="log" >
								<tr>
									<td>&nbsp;&nbsp;</td>
									<td>&nbsp;&nbsp;</td>
								</tr>
								<tr>
									<td class="td2">用户登录</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="td1">用户名：</td>
									<td><input type="text" name="j_username" id="j_username"
										value="${userName}" style="width: 170px" maxlength="12"></td>
								</tr>
								<tr>
									<td class="td1">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
									<td><input type="password" name="j_password"
										style="width: 170px" id="j_password" maxlength="12"></td>
								</tr>
								<tr>
									<td class="td1">&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td class="td1">&nbsp;</td>
									<td><input type="button" class="but" value="登 录"
										id="btnLogin"> &nbsp;&nbsp;&nbsp; <input type="button"
										class="but" value="清 空" id="btnClear"></td>
								</tr>
								<tr>
									<td class="td1">&nbsp;</td>

									<td align="left" colspan="2"><label id="lab1"
										style="color: red">${MSG_ERROR}</label></td>
								</tr>
							</table>
						</fieldset> <br>
					<br>
					<br>
					<br>
						<fieldset class="fieldset3">
							<font color='#00539F'>版权所有：北京和兴宏图科技有限公司</font>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
