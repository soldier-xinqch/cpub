<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加法庭</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/public/css/global.css"/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/public/css/global_color.css"/>" />
<link type="text/css" rel="stylesheet" media="all"
	href="<c:url value="/public/css/add.css"/>" />
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
function validate_channel_info(frm)  
{  
	 var roomName = document.getElementById("roomName").value;
	 var roomType = document.getElementById("roomType").value;
	 var pubAccessUrl = document.getElementById("pubAccessUrl").value;
	 var ajaxData = verify();
	 var testSpace =  regxSpace();
	 if(frm.roomName.value==""||regex.test(roomName)){
		alert("法庭名称不得为空！");
        return false;  
    }else if(frm.roomType.value==""||regex.test(roomType)){
			alert("法庭类型不能为空！");
			return false;
		}else if(frm.pubAccessUrl.value==""||regex.test(pubAccessUrl)){
			alert("直播链接地址不能为空");
			return false;
		}else if(ajaxData==false){
			alert("法庭名称不能重复！");
			return false; 
		}else if(testSpace == true){
			 alert("直播地址不能有空格,请您检查后重新输入！");
			 return false;
		}
	
    return true;  
} 
function verify(){
	var roomNamed = document.getElementById("roomName").value; 
	var ajaxData = null;
	$.ajax({
		type:"get",
		async: false,
		url:"<c:url value='/verifyCourtRoom?roomName="+roomNamed+"'/>",
		success : function(data) {  
			ajaxData =data.data;
		     if(false==data.data){
		        	 showWarning();
		         } 
		       }  
	});
	return ajaxData;
}
//显示
function showWarning(){
	document.getElementById("resultMessage").style.display="";
}
//隐藏
function verify2() {
	 document.getElementById("resultMessage").style.display="none";
}

window.onload = function() {
	checkLength(caseText);
	 };	

function checkLength(which) { 
	var maxChars = 250; 
	if (which.value.length > maxChars) 
	which.value = which.value.substring(0,maxChars); 
	var curr = maxChars - which.value.length; 
	document.getElementById("chLeft").innerHTML = curr.toString(); 
	}  
function displayText(id){
	document.getElementById(id).value="";
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
	 
	 function regxSpace(){
		 var reg =/\s/;
		 var pubUrl = document.getElementById("pubAccessUrl").value;
		 if(reg.test(pubUrl)){
			 alert("直播地址不能有空格,请您检查后重新输入！");
			 return true;
		 }
		 
	 }
</script>
</head>
<body>
	<!--主要区域开始-->
	<div id="vod_pub_main">
		<div id="head_title" style="width: 100%; height: 55px;">
			<span id="add_title"
				style="width: 300px; height: 51px; position: absolute; left: 100px; top: 20px;">新增法庭</span><br />
			<br /> <br />
			<hr>
		</div>
		<form action="addRoom" method="post" class="vod_main_form" name="frm"
			onsubmit="return validate_channel_info(this);">
			<table id="addPub" style="position: relative;">
				<tr>
					<td align="right" width="200">法院名称:</td>
					<td align="left" width="300"><input type="text"
						name="" value="${courtName }" id="court"  readonly />
						<input type="hidden" name="orgId" value="${courtIds }" id="courtId"/>
						</td>
					<td width="100" style="text-align: left;">
					<a href="#" onclick="dialogShow();">选择法院</a>
					
					<!-- <a href="javascript:var a=window.open('courtTree.jsp','_blank','height=450px,width=350px,
							menubar=no,titlebar=no,scrollbar=no,toolbar=no,status=no,location=no,resizable=no')">选择法院</a> -->
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 42%">法庭名称:</td>
					<td align="left" style="width: 28%"><input type="text" maxlength="16"
						name="roomName" id="roomName" onblur="verify()"
						onfocus="verify2()" /></td>
					<td align="left">
						<div id="resultMessage" class="resultMessage"
							style="display: none;">* 法庭名称不合法</div>
					</td>
				</tr>
				<tr>
					<td align="right">法庭类型:</td>
					<td align="left"><input type="text" name="roomType" value="" maxlength="16"
						id="roomType" /></td>
					<td></td>
				</tr>
				<tr>
					<td align="right">直播地址:</td>
					<td align="left"><input type="text" name="wwwLiveUrl" maxlength="255"
						id="pubAccessUrl" value="" class="width300" onblur="regxSpace();"></td>
					<td></td>
				</tr>
				<tr>
					<td align="right" valign="top">法庭描述:</td>
					<td align="left">
							<textarea name="roomMemo" style="height: 100px;" onkeyup="checkLength(this);" id="caseText" onfocus="displayText(this.id);">请输入详细描述信息……</textarea>  
							<small>*文字最大长度: 250.个字符 还剩: <span id="chLeft">250</span>.个字符</small>  
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="center" colspan=3><input type="submit" name=""
						class="btn_save" value="保存">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name="" value="返回" class="btn_save"
						onclick="location.href='<c:url value='/courtPub/vod/roomList'/>';"></td>

				</tr>
			</table>
		</form>
	</div>

	<!--主要区域结束-->
</body>
</html>