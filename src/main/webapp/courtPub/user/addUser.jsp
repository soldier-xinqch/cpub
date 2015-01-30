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
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global.css'/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value='/public/css/global_color.css'/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/public/ui-lightness/jquery-ui-1.9.2.custom.css"/>" />
<script type='text/javascript'
	src='<c:url value="/public/js/jquery-1.9.1.js"/>'></script>
<script type='text/javascript'
	src='<c:url value="/public/js/jquery-ui-1.9.2.custom.js"/>'></script>
<style type="text/css">
input {
	width: 300px;
}

textarea {
	resize: none;
	width: 300px;
	max-width: 300px;
	max-height: 100px;
	border: 1px solid #C0C0C0;
}
</style>
<script type="text/javascript">
var regex = /^\s*$/;
var regexName =/^[A-Za-z0-9]+$/;
function validate_channel_info(frm)  
{  
	 var court = document.getElementById("court").value;
	 var userName = document.getElementById("userName").value;
	 var passWord = document.getElementById("passWord").value;
	 var ajaxData = verify();
	 if(frm.court.value==""||regex.test(court)){
		alert("所属法院不能为空！");
        return false;  
     }else if(frm.userName.value==""||regex.test(userName)){
			alert("用户名不能为空！");
			return false;
	 }else if(frm.passWord.value==""||regex.test(passWord)){
			alert("密码不能为空！");
			return false;
	 }else if(ajaxData==false){
		 alert("请输入正确的用户名，十二个字符以内的英文或字母的组合");
			return false;
	 }
    return true;  
} 
function regexUserName(){
	var userName = document.getElementById("userName").value;
	if(regexName.test(userName)){
		return true;
	}else{
		return false;
	}
	
}

function verify(){
	var userName = document.getElementById("userName").value; 
	var ajaxData = regexUserName();
	if(frm.userName.value==""||regex.test(userName)){
		alert("用户名不能为空！");
		return false;
 	}else{	
 		if(false == ajaxData){
			alert("请输入正确的用户名，十二个字符以内的英文或字母的组合");
		}
 	}
	if(true == ajaxData){
		$.ajax({
			type:"get",
		    async: false,
			url:"<c:url value='/verifyUserName?userName="+userName+"'/>",
			success : function(data) {  
				ajaxData =data.data;
			     if(false==data.data){
			        	alert("用户名重复，请核对后重新输入！");
			         } 
			     /*   alert("Data Saved: " + data.data);  */
			       }  
	});
	}
return ajaxData;
}


	function dialogShow(){
		 var courtName ='${courtName }';
		 var courtId = '${courtIds}';
	 $("<iframe id='chooseCourt' src='" + '<c:url value='/courtPub/vod/courtTree.jsp'/>' + "'style="+"width:220px;"+"/>")
	 .dialog({
		 autoOpen: true,
		 draggable:true,
		 resizable:false,
		 modal:true,
		 title:'选择法院',
		 position:'center',
		 width:270,
		 height:555,
		 show: {
		 effect: "blind",
		 duration: 1000
		 },
		 hide: {
		 effect: "explode",
		 duration: 500
		 },
		 buttons: {
		      "确认": function() {  
		    	  courtName = document.getElementById("court").value;
		    	  courtId = document.getElementById("courtId").value;
		    	  $(this).dialog('close');
		    	  } ,
		      "取消": function() {
		    	  document.getElementById("court").value = '${courtName }';
		          document.getElementById("courtId").value = '${courtIds}';
		    	  $(this).dialog('close');
		       }
		   },
		 overlay: {
	         opacity: 0.5,
	         background: "black"
       	 },
       	 beforeClose: function(event){
       		 if(document.getElementById("courtId").value !=courtId || 
       				 document.getElementById("court").value != courtName){
       			 document.getElementById("court").value = '${courtName }';
		          document.getElementById("courtId").value = '${courtIds}';
       		 }
       	 }
		}); 
	 } 
</script>
</head>
<body>
	<div id="vod_pub_main">
		<div id="head_title" style="width:100%;height: 55px;">
			<span id="add_title"
				style="width: 300px; height: 51px; position: absolute; left: 100px; top: 20px;">新增用户信息</span><br />
			<br /> <br />
			<hr>
		</div>
	<form action="addUser" enctype="multipart/form-data" method="post" class="main_form" name="frm"  onsubmit="return validate_channel_info(this);">
		<div style="width:39%;height:400px;">
			<table id="addPub" style="width: 520px;">
				<tr>
					<td align="right">所属法院：</td>
					<td align="left"><input type="text" name="courtName" value="" id="court"  readonly="readonly"/></td>
					<td width="60px" align="left" valign="top">
						<a href="#" onclick="dialogShow();">选择法院</a>
					<!-- 	
						<a href="javascript:var a=window.open('courtTree.jsp','_blank','height=450px,width=350px,
							menubar=no,titlebar=no,scrollbar=no,toolbar=no,status=no,location=no,resizable=no')">选择法院</a> -->
						<input type="hidden" name="courtId" value="" id="courtId"  />
					</td>
				</tr>
				<tr>
					<td align="right">用户名：</td>
					<td align="left"><input type="text" name="userName" value="" id="userName" onblur="verify()" maxlength="11" /></td>
					<td></td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td align="left"><input type="password" name="userPassword" value="" id="passWord"  maxlength="11" /></td>
					<td></td>
				</tr>
				<tr>
					<td align="right">真实姓名：</td>
					<td align="left"><input type="text" name="realName" value="" id="" maxlength="7" /></td>
					<td></td>
				</tr>
				<tr>
					<td align="right" valign="top">角色：</td>
					<td align="left">
					<%-- <c:if test="${operateFlag == true}"></c:if> 
					    <select style="width: 150px;text-align: center;height:25px;" name="roles">  
						     <option value="ROLE_ADMIN" >法院管理员</option>  
						     <option value="ROLE_ADMIN_HEYI" >合议庭成员</option>  
						</select>  
					--%>
					<input type="text" value="合议庭成员" name="roles" readonly="readonly"/> 
					<!-- 	<textarea id="" name="roles" class=""></textarea> -->
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="right" valign="top">人员图片：</td>
					<td align="left">
					   <input type="file" id="personPhoto" name="photo"  value=""  maxlength="200"/><br/>
					   <span id="chLeft" style="font-size:10px;"> 这里上传人员图片（可选）</span>
					</td>
					<td align="right" valign="top">
					</td>
				</tr>
				<tr>
					<td align="center" colspan=3>
					<input type="submit" name="" class="btn_save" value="保存">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" name="" value="返回" class="btn_save"
						onclick="location.href='<c:url value='/courtPub/vod/userList'/>';"></td>
				</tr>
			</table>
		</div>
		</form>
	</div>
	<!--主要区域结束-->
</body>
</html>