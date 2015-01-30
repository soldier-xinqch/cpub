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
<style type="text/css">
.textInput{
	width: 300px;
}
</style>
</head>
<body>

<div class="col-lg-12" style="text-align: left;margin: 15px 0px 10px 0px;">
			<span id="add_title" style="margin-left: 80px;display:inline-block;">新增案件信息</span>
			<HR style="width=100%;border: 1px solid black;margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;"/>
</div>
<div class="col-lg-12" >
	<!--主要区域开始-->
<form action="add" method="post" class="main_form" name="frm"  onsubmit="return validate_channel_info(this);">
			<input type="hidden" name="courtId" value="${courtIds }" id="courtId"/>
			<table id="add_table">
				<tr>
					<td align="right" width="200">法院名称:</td>
					<td align="left" width="300"><input type="text" class="form-control"
						name="" value="${courtName }" id="court"  readonly /></td>
					<td align="left" width="100" >
					<a href="#" onclick="dialogShow();">选择法院</a>
					</td>
					<td align="right" width="130">原告:</td>
					<td align="left" width="400">
					<input type="text" class="form-control textInput" name="accuser" value="" id="" maxlength="200"/>
					
					<!-- <textarea name="accuser"
							style="height: 30px;"></textarea>   -->
					</td>
				</tr>
				<tr>
					<td align="right">案号:</td>
					<td align="left"><input id="num" class="form-control" type="text" name="caseNum" maxlength="50"
						value="" onblur="verifyNum()" onfocus="verify2()" /></td>
					<td>
						<!--提示信息开始-->
						<div id="case_num">* 请输入案号</div> 
						<div id="resultMessage" class="resultMessage" style="display: none;">
							* 案号重复 
						</div>
						<!--提示信息结束-->
					</td>
					<td align="right">被告:</td>
					<td align="left">
					<input type="text" class="form-control textInput" name="accused" value="" id="" maxlength="200"/>
					<!-- <textarea name="accused"
							style="height: 30px;"></textarea> -->
					</td>
				</tr>
				<tr>
					<td align="right">直播预告信息:</td>
					<td align="left"><input type="text" class="form-control" id="name" name="caseName" maxlength="75"
						onblur="verifyName()" onfocus="verify1()"></td>
					<td>
						<!--提示信息开始-->
						<div id="case_name">* 请输入直播预告信息</div> 
						<!--提示信息结束-->
					</td>
					<td align="right">原告律师:</td>
					<td align="left">
					<input type="text" class="form-control textInput" name="accuserLawer" value="" id="" maxlength="200"/>
					<!-- <textarea name="accuserLawer" 
							style="height: 30px;"></textarea> -->
					</td>
				</tr>
				<tr>
					<td align="right">案件类型:</td>
					<td align="left">
					<select class="form-control" style="width: 100px;text-align: center;height:30px;" name="caseTypeName">  
						     <option value="民事案件" >民事案件</option>  
						     <option value="刑事案件">刑事案件</option>
						     <option value="行政案件">行政案件</option>
						     <option value="审监案件">审监案件</option>
						     <option value="减刑假释">减刑假释</option>
					</select>  
					</td>
					<td width="80px"></td>
					<td align="right">被告律师:</td>
					<td align="left">
					<input type="text" class="form-control textInput" name="accusedLawer" value="" id="" maxlength="200"/>
					<!-- <textarea name="accusedLawer"
							style="height: 30px;"></textarea> --></td>
				</tr>
				<tr>
					<td align="right">立案时间:</td>
					<td align="left"><input type="text" id="d242"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						class="Wdate" style="width: 300px;height:30px;" name="registerTimed" value=""  readonly/>
					</td>
					<td width="80px" colspan="3"></td>
				</tr>
				<tr>
					<td align="right" valign="top">案件详细:</td>
					<td align="left">
						<div class="input_info_high">
							<textarea id="caseDescText" class="form-control" name="caseDesc" style="height: 100px;resize: none;" onfocus="displayText(this.id);">请输入详细信息……</textarea>
						</div>
					</td>
					<td></td>
					<td align="right" valign="top">当事人信息:</td>
					<td align="left">
						<div class="input_info_high">
							<textarea id="CasePartyText" class="form-control" name="party" style="height: 100px;resize: none;" onfocus="displayText(this.id);">请输入当事人的详细信息……</textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan=2 align="right"><input type="submit" value="保存"
						name="Submit" class="btn_save" /></td>
					<td></td>
					<td colspan=2 align="left"><input type="button" value="返回"
						class="btn_save" id="save_checking"
						onclick="location.href='<c:url value='/courtPub/vod/list'/>';" /></td>
				</tr>
			</table>
		</form>
	<!--主要区域结束-->

</div>
</body>
</html>





<script type="text/javascript">
	var regex = /^\s*$/;
	function verify2() {
		document.getElementById("case_num").style.display = "none";
		document.getElementById("resultMessage").style.display="none";
	}
	function verify1() {
		document.getElementById("case_name").style.display = "none";
	}
	function verifyNum() {
		var caseNumValue = document.getElementById("num").value;
		if (caseNumValue == null || regex.test(caseNumValue)) {
			document.getElementById("case_num").style.display = "block";
		}else{
			 verify();
		}
	}
	function verifyName() {
		var caseNameValue = document.getElementById("name").value;
		if (caseNameValue == null || regex.test(caseNameValue)) {
			document.getElementById("case_name").style.display = "block";
		}
	}	
	 
	 function validate_channel_info(frm)  
	 {  
	 	 var caseNumValue = document.getElementById("num").value;
	 		var caseNameValue = document.getElementById("name").value;
	 		var courtName = document.getElementById("court").value;
	 		var d242 =document.getElementById("d242").value;
	 		var ajaxData = verify();
	 		if(frm.num.value==""||regex.test(caseNumValue)){
	 			alert("案号不得为空！");
	         return false;  
	     	}else if(frm.name.value==""||regex.test(caseNameValue)){
	 			alert("直播预告信息不能为空！");
	 			return false;
	 		}else if(frm.court.value==""||regex.test(courtName)){
	 			alert("法院名称不能为空！");
	 			return false;
	 		}else if(frm.d242.value==""||regex.test(d242)){
	 			alert("立案时间不能为空！");
	 			return false;
	 		}else if(ajaxData==false){
				alert("案号不能重复！");
				return false;
			}
	     return true;  
	 }  
	 
	 function verify(){
			var caseNum = document.getElementById("num").value; 
			var ajaxData = null;
			$.ajax({
				type:"get",
			    async: false,
				url:"<c:url value='/verifyCase?caseNum="+caseNum+"'/>",
				success : function(data) {  
					ajaxData =data.data;
				     if(false==data.data){
				        	alert("案号重复，请核对后重新输入！");
				         } 
				     /*   alert("Data Saved: " + data.data);  */
				       }  
			});
		return ajaxData;
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
			 title:'选择案件',
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
