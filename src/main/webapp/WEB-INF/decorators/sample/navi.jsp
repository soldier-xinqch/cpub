<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/include.jsp"%>
<%
String servletPath=request.getServletPath();
    String contextPath=request.getContextPath();
    String[] strArrays=servletPath.split("/");
    int len=strArrays.length;
    String result="";
    for(int i=1;i<len-1;i++){
    	result+="/" + strArrays[i];
    }
    if(len==2){
		result=servletPath;
    }
String upgradeClass=result.indexOf("/upg/upgrade")>=0 ? " tab_selected" :"";
String packageClass=result.indexOf("/upg/pkg")>=0 ? " tab_selected" :"";
String topoClass=result.indexOf("/topo/orgDev")>=0 ? " tab_selected" :"";
String sysClass=(result.indexOf("/sys/")>=0 || result.indexOf("/upg/init")>=0 || result.indexOf("/upg/devSoft")>=0)? 
		" tab_selected" :"";
%>
<div class="title_div">
	<div class="label_div<%=upgradeClass%>" id="softUpdate">
		<%if("".equals(upgradeClass)){%>
		<a href="<c:url value="/upg/upgrade/list"/>" style="color:#000000;text-decoration:none">
		软件升级</a>
		<%}else{%>软件升级<%}%>
	</div>
	<div class="label_div<%=packageClass%>" id="softPackage">
		<%if("".equals(packageClass)){%>
		<a href="<c:url value="/upg/pkg/list"/>" style="color:#000000;text-decoration:none">
		软件包管理</a>
		<%}else{%>软件包管理<%}%>
	</div>
	<div class="label_div<%=topoClass%>" id="orgDevice">
		<%if("".equals(topoClass)){%>
		<a href="<c:url value="/topo/orgDev/list"/>" style="color:#000000;text-decoration:none">
		组织与设备</a><%}else{%>组织与设备<%}%>
	</div>
	<div class="label_div<%=sysClass%>" id="sysSetUp">
		<%if("".equals(sysClass)){%>
		<a href="<c:url value="/upg/init/inputs"/>" style="color:#000000;text-decoration:none">
		系统设置</a>
		<%}else{%>系统设置<%}%>
	</div>
</div>