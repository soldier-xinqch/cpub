<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="org.springframework.web.util.WebUtils"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.fx.oss.utils.WUtils"%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/include.jsp"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<style type="text/css">
.page_div {
	
	height: 40px;
	text-align: center;
	color: #333333;
	float: right;
	padding-top: 10px;
	padding-left: 2.5%;
	margin-bottom: 20px;
	/*  border:1px solid red; */
}

.num_div {
	width: 24px;
	height: 24px;
	background-color: #FFFFFF;
	border: 1px solid #797979;
	text-align: center;
	float: left;
	margin-right: 8px;
	white-space: nowrap;
	font-size: 13px;
	padding-top: 1px;
	cursor: pointer;
	/* border:1px solid red; */
}
</style>
<%
	System.out.println("aaaaaaa" +request.getRequestURI());
%>
<%
	String actionUrl=WUtils.getParam(request, "actionUrl", request.getContextPath()+request.getRequestURI());
String totalCount=request.getParameter("totalCount");
String pageSize=WUtils.getParam(request,"pageSize","12");
String showPageNum=WUtils.getParam(request,"showPageNum","5");
boolean oneMorePage=true;
if(pageSize!=null && totalCount!=null){
    try{
        int intTotalCount=Integer.parseInt(totalCount);
        int intPageSize=Integer.parseInt(pageSize);
        oneMorePage=intTotalCount>intPageSize;
    }catch(Exception x){
           
    }
}


Map<String,Object> qpDefs=WebUtils.getParametersStartingWith(request, "qp_");
/* for(Map.Entry<String,Object> entry:qpDefs.entrySet()){
    System.out.println("@@@@@@key=" + entry.getKey());
    System.out.println("@@@@@@val=" + entry.getValue());
} */
//System.out.println("qpdefs=" + qpDefs);
Map<String,String> qps=new HashMap<String,String>();
if(qpDefs!=null && !qpDefs.isEmpty()){
    for(Map.Entry<String,Object> entry:qpDefs.entrySet()){
        String paramName=entry.getKey();
        
        Object obj=entry.getValue();
        
        if(obj instanceof String[]){
            String[] strs=(String[])obj;
            obj=strs[0];
        }
        //String[] values=(String[])entry.getValue();
        String paramVal=(String)obj;
        if(paramVal!=null && !"".equals(paramVal.trim())){
            //paramVal=values[0];
            System.out.println("aaaa=" + paramName + "bbbb=" + paramVal);
            qps.put("qp_" + paramName, paramVal);
        }
    }
}

pageContext.setAttribute("totalCount", totalCount);
pageContext.setAttribute("actionUrl", actionUrl);
pageContext.setAttribute("pageSize", pageSize);
pageContext.setAttribute("showPageNum", showPageNum);
pageContext.setAttribute("qps", qps);

//System.out.println("qps=" + qps);
%>
<script type="text/javascript">
	function clickA(id) {
		var a = document.getElementById(id);
		//a.click();
		//alert(a.click());
	}
</script>
<pg:pager url="${actionUrl}" items="${totalCount}" index="center"
	maxPageItems="${pageSize}" maxIndexPages="${showPageNum}"
	isOffset="true" export="offset,currentPageNumber=pageNumber"
	scope="request">
	<c:forEach items="${qps}" var="qp">
		<pg:param name="${qp.key}" value="${qp.value}" />
	</c:forEach>
	<pg:param name="pager.pageSize" value="${pageSize}" />

	<div class="page_div">

		<pg:prev export="prevPageUrl=pageUrl">
			<div class="num_div" style="width: 40px;" onclick="clickA('a上一页')">
				<a href="<%=prevPageUrl%>" class="raw_text" id="a上一页"> 上一页</a>
			</div>
		</pg:prev>
		<%
			if (oneMorePage) {
		%>
		<pg:pages>
			<%
				if (pageNumber == currentPageNumber) {
			%>
			<div class="num_div" style="background-color: #FBECDE"><%=pageNumber%></div>
			<%
				} else {
			%>
			<div class="num_div" onclick="clickA('a<%=pageNumber%>')">
				<a href="<%=pageUrl%>" class="raw_text" id="a<%=pageNumber%>"><%=pageNumber%></a>
			</div>
			<%
				}
			%>
		</pg:pages>
		<%
			}
		%>
		<pg:next export="nextPageUrl=pageUrl">
			<div class="num_div" style="width: 40px;" onclick="clickA('a下一页')">
				<a href="<%=nextPageUrl%>" class="raw_text" id="a下一页"> 下一页</a>
			</div>
		</pg:next>
	</div>
</pg:pager>

