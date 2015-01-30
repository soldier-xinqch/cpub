<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%>
<!-- head -->
<table class="top_tab" border="0">
	<tr>
		<td width="15%">
		</td>
		<td class="toptitle_td">
		</td>
		<td class="topInfo_td">
		 		<span id="timeSpan"></span>  &nbsp;&nbsp;&nbsp;
		 		<img src="<c:url value="/public/images/body/user.png"/>">&nbsp;<sec:authentication property="principal.username"/>  &nbsp;&nbsp;&nbsp;
		 		<img src="<c:url value="/public/images/body/exit.png"/>">&nbsp;<font class="exit_font"><a href="<c:url value="/sys/user/logout"/>" style="color:#E6ECFA;text-decoration:none">退出</a></font>
		</td>
	</tr>
</table>
<script type="text/javascript">
<!--
showTime('timeSpan');
setInterval("showTime('timeSpan')", "1000");
//-->
</script>
