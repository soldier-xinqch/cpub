<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
<html>
<head>
<title>软件升级</title>
<script type="text/javascript">
$(function() {
	$( "#dialog" ).dialog({
		autoOpen: false,
		width: 600,
		height:420	
	});

	// Link to open the dialog
	$( "#dialog-link" ).click(function( event ) {
		$( "#dialog" ).dialog( "open" );
		event.preventDefault();
	});
	

});
</script>
</head>
<body>
	<fieldset class="fieldsetStyle">
		<!-- content -->
		<table class="tab" id="update_table">
			<tr class="tab_head">
				<td colspan="6" style="text-align: left;">软件升级列表</td>
				<td>
					<input type="button" value="全部升级" id="dialog-link"/>
				</td>
			</tr>
			<tr>
				<th width="5%">序号</th>
				<th width="20%">软件名称</th>
				<th width="10%">已升级/未升级</th>
				<th width="10%">最新版本</th>
				<th width="10%">软件大小</th>
				<th width="20%">发布日期</th>
				<th width="25%">升级</th>
			</tr>
			<tr bgcolor="#F4F8FB">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr bgcolor="#F8FAFC">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr bgcolor="#F4F8FB">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr bgcolor="#F8FAFC">
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<div class="page_div">
		<div class="num_div" style="background-color: #FBECDE">1</div>
		<div class="num_div">2</div>
		<div class="num_div">3</div>
		<div class="num_div">4</div>
		<div class="num_div" style="width: 40px;"> 下一页</div>
		</div>
	</fieldset>
	
<div id="dialog" title="Dialog Title" style="display: none;">
	<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
</div>
</body>
</html>