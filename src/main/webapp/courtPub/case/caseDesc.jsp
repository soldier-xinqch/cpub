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
<style type="text/css">
.textInput{
	width: 300px;
}
</style>
</head>
<body>
	<!--主要区域开始-->
	<div class="col-lg-12" style="text-align: left;margin: 15px 0px 10px 0px;">
			<span id="add_title" style="margin-left: 80px;display:inline-block;">庭审案件信息详细</span>
			<HR style="width=100%;border: 1px solid black;margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;"/>
	</div>
	
	<div class="col-lg-12" >
			<table id="add_table">
				<tr>
					<td align="right" width="200">法院信息:</td>
					<td align="left" width="300"><input type="text"  name="courtName"
						value="${courtName }" class="form-control textInput" disabled /></td>

					<td width="80"></td>

					<td align="right" width="130">原告:</td>
					<td align="left" width="400"><input type="text" name="accuser"
						value="${courtCase.accuser}" class="form-control textInput" disabled /></td>
				</tr>
				<tr>
					<td align="right">案号:</td>
					<td align="left"><input type="text" name="caseNum" value="${courtCase.caseNum}"
						class="form-control textInput" disabled /></td>
					<td></td>
					<td align="right">被告:</td>
					<td align="left"><input type="text" name="accused" value="${courtCase.accused}"
						class="form-control textInput" disabled /></td>
				</tr>
				<tr>
					<td align="right">直播预告信息:</td>
					<td align="left"><input type="text" name="caseName" value="${courtCase.caseName }"
						class="form-control textInput" disabled /></td>
					<td></td>
					<td align="right">原告律师:</td>
					<td align="left"><input type="text" name="accuserLawer" value="${courtCase.accuserLawer }"
						class="form-control textInput" disabled  /></td>
				</tr>
				<tr>
					<td align="right">案件类型:</td>
					<td align="left"><input type="text" name="caseTypeName" value="${courtCase.caseTypeName }"
						class="form-control textInput" disabled /></td>
					<td width="80px"></td>
					<td align="right">被告律师:</td>
					<td align="left"><input type="text" name="accusedLawer" value="${courtCase.accusedLawer }"
						class="form-control textInput" disabled /></td>
				</tr>
				<tr>
					<td align="right">立案时间:</td>
					<td align="left"><input type="text" class="form-control textInput" style="width: 300px;height:30px;" name="registerTime" value="<fmt:formatDate value="${courtCase.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"  disabled/>
					</td>
					<td width="80px" colspan="3"></td>
				</tr>
				<tr>
					<td align="right" valign="top">案件详细：</td>
					<td align="left">
						<div class="input_info_high">
							<textarea class="form-control textInput" name="caseDesc" disabled >${courtCase.caseDesc }</textarea>
						</div>
					</td>
					<td></td>
					<td align="right" valign="top">当事人信息:</td>
					<td align="left">
						<div class="input_info_high">
							<textarea class="form-control textInput" name="party" disabled >${courtCase.party }</textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan=2 align="right"></td>
					<td></td>
					<td></td>
					<td align="center">
					<div>
					<input type="button" value="返回" class="btn_save" 
						onclick="location.href='<c:url value='/courtPub/vod/list'/>';" />
					</div>
					</td>
				</tr>
			</table>
	</div>
	<!--主要区域结束-->
</body>
</html>