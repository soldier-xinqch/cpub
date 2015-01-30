<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@page import="org.slf4j.Logger" %>
<%@page import="org.slf4j.LoggerFactory" %>
<%@ page isErrorPage="true" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/include.jsp"%>
<!doctype html>
<html>
	<head>
		<title>服务消息 — 错误消息</title>
                <%@ include file="/common/ref/ref.jsp"%>
	</head>
	<body>
		<h2 style="color: red;">出现异常啦！请联系管理员……</h2>
            <div style="color:red">
                系统错误：<%= exception.getMessage()%>
            </div>
            <div><span class="blue"><a href="#" onclick="$('#errorStack').toggle();">查看错误堆栈信息</a></span></div>
            <div id="errorStack" style="display: none">
                <%
                Logger logger = LoggerFactory.getLogger(System.class);
                StringWriter sw=null;
                PrintWriter pw =null;
                try{
                    sw = new StringWriter();
                    pw = new PrintWriter(sw);
                    exception.printStackTrace(pw);
                    out.print(sw);
                    logger.error("Error",exception);
                }catch(Exception x){
                    try{
                        if(sw!=null)
                            sw.close();
                    }catch(Exception x1){
                        
                    }
                    try{
                        if(pw!=null)
                            pw.close();
                    }catch(Exception x1){
                        
                    }
                }
                exception.printStackTrace();

                %>
            </div>
	</body>
</html>
