<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/include/include.jsp"%>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
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
<decorator:usePage id="currentPage"/>
<!doctype html>
<html>
<head>
<title>升级服务 - <decorator:title default="欢迎您！"/></title>
<%@ include file="/common/ref/ref.jsp"%>
<decorator:head/>
<link rel="shortcut icon" href="favicon.ico"  type="image/x-icon" />
<link rel="icon" href="http://<%=server%><%=contextPath%>favicon.ico" />
</head>
<body bgcolor="#989CAC">
<!-- head -->
<page:applyDecorator name="sampleHeader"/>
<!-- body -->
<div class="bodyStyle_div">
	<page:applyDecorator name="upNaviBar"/>
	<decorator:body/>
</div>
</body>
</html>
