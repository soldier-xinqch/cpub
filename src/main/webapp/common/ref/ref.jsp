<%@ include file="/common/include/include.jsp"%>
<%
request.setAttribute("upCtx", request.getContextPath());
%>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<c:url value="/public/css/body.css"/>">
<link href="<c:url value="/public/css/custom-theme/jquery-ui-1.10.3.custom.css"/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value="/public/js/jquery-1.9.1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/public/js/main.js"/>"></script>
<script>
     var CTX_PATH='<%=request.getContextPath()%>';
</script>
