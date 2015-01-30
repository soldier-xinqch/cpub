<%@ page language="java" import="java.util.*,java.text.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>更改法庭</title>
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
	 var planStartTime = document.getElementById("d241").value;
	 var planEndTime = document.getElementById("d242").value;
	 var pubAccessUrl = document.getElementById("pubAccessUrl").value;
	 var ajaxData = verify();
	 var testSpace = regxSpace();
	 if(frm.d241.value==""||regex.test(planStartTime)){
		alert("法庭名称不得为空！");
        return false;  
    }else if(frm.d242.value==""||regex.test(planEndTime)){
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
	var roomNamed = document.getElementById("d241").value; 
	$.ajax({
		type:"get",
		url:"<c:url value='/verifyCourtRoom?roomName="+roomNamed+"'/>",
		success : function(data) {  
		     if(false==data.data){
		        	 showWarning();
		         } 
		        /*  alert("Data Saved: " + data.data); */  
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

window.onload = function(){
	checkpointFoutNum();
};
	 
function checkLength(which) { 
	var maxChars = 250; 
	if (which.value.length > maxChars) 
	which.value = which.value.substring(0,maxChars); 
	var curr = maxChars - which.value.length; 
	document.getElementById("chLeft").innerHTML = curr.toString(); 
	}  
function checkpointFoutNum(){
	var maxChars = 250; 
	var caseText = document.getElementById('caseText');
	
	caseText.value = caseText.value.substring(0,maxChars); 
	var curr = maxChars - caseText.value.length; 
	document.getElementById("chLeft").innerHTML = curr.toString(); 
} 
	
function showWarning(){
	document.getElementById("resultMessage").style.display="";
}

function verify2() {
	document.getElementById("resultMessage").style.display="none";
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
	<!--主要区域开始-->
	<div id="vod_pub_main">
		<div id="head_title" style="width:100%;height: 55px;">
			<span id="add_title"
				style="width: 300px; height: 51px; position: absolute; left: 100px; top: 20px;">更改法庭</span><br />
			<br /> <br />
			<hr>
		</div>
		<form action="modifyRoom" method="post" class="vod_main_form" name="frm"  onsubmit="return validate_channel_info(this);">
			<input type="hidden" name="id" value="${courtRoom.id}" />
			<table id="addPub" style="position: relative;">
			    <tr>
					<td align="right" width="200">法院名称:</td>
					<td align="left" width="300"><input type="text"
						name="" value="${courtName }" id="court"  readonly />
						<input type="hidden" name="orgId" value="${courtIds }" id="courtId"/>
						</td>
					<td width="100" style="text-align: left;">
					<a href="#" onclick="dialogShow();">选择法院</a>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 42%">法庭名称:</td>
					<td align="left" >
					<input type="text" name="roomName" value="${courtRoom.roomName}" id="d241" maxlength="16" onblur="verify()" onfocus="verify2()"/> 
					</td>
						<td align="left">
						<div id="resultMessage" class="resultMessage" style="display: none;">
							* 法庭名称重复 
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">法庭类型:</td>
					<td align="left">
					<input type="text" name="roomType" value="${courtRoom.roomType}" id="d242" maxlength="16"/>
					</td>
					<td>
						<input type="hidden" name="createTime" value="${courtRoom.createTime}" /> 
					</td>
				</tr>
				<tr>
					<td align="right">直播地址:</td>
					<td align="left"><input type="text" name="wwwLiveUrl" id="pubAccessUrl" maxlength="200"	value="${courtRoom.wwwLiveUrl}" class="width300"  onblur="regxSpace();"></td>
					<td>
						<input type="hidden" name="createUser" value="${courtRoom.createUser}" /> 
					</td>
				</tr>
				<tr>
					<td align="right" valign="top">法庭描述:</td>
					<td align="left">
						<textarea name="roomMemo" style="height: 100px;" onkeyup="checkLength(this);" id="caseText" onfocus="displayText(this.id);">${courtRoom.roomMemo}</textarea>  
						<br/><small>*文字最大长度: 250.个字符 还剩: <span id="chLeft">250</span>.个字符</small>  
					</td>
					<td>
						<input type="hidden" name="createUserId" value="${courtRoom.createUserId}" /> 
					</td>
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