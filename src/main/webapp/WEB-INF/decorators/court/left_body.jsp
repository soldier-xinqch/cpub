<%-- <%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>法院选择</title>
		<link rel="stylesheet" type="text/css" href="css/main1.css">
		<script src="css/uup.js" type="text/javascript"></script>
		<link rel="stylesheet" href="<c:url value="/public/css/zTreeStyle35/zTreeStyle.css"/>"
			type="text/css">
		<script type="text/javascript" src="<c:url value="/public/js/zTree35/jquery-1.4.4.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/public/js/zTree35/jquery.ztree.core-3.5.min.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/public/js/courtSelect.js"/>"></script>
   		<SCRIPT type="text/javascript">
   	   <%--   		var CTX_PATH='<%=request.getContextPath()%>';
		<%if(request.getServerPort()==80){%>
			var HOST_PORT='<%=request.getScheme() + "://" + request.getServerName()%>';
		<%}else{%>
			var HOST_PORT='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()%>';
		<%}%>
        //选择组织机构节点
        function onClick(event, treeId, treeNode, clickFlag){
          /* alert('todo'); */
         /*    alert(getSelectedNode().orgName);
            alert(getSelectedNode().id); */
            var id = (getSelectedNode().id);
          /*   window.location.href="list"+id; */
          _getNodeLoadUrl();
          window.location.href="roomList?courtId="+id;
        }
        
        function OnRightClick(){
                    
        }
                
        function beforeAsync(){
            //alert('开始异步加载...');
            
        }
        
        function onAsyncSuccess(){
        	getZTree().expandAll(true);
        	var seleNode = getSelectedNode();
            if(!seleNode){
            	var currNode = getZTree().getNodeByParam("id", '<%=request.getParameter("courtId") %>', null);
            	if(!currNode){
            		/* alert("currNode is null"); */
            		var rootNode = getZTree().getNodeByParam("parentId", null, null);
            		if(rootNode){
                        getZTree().selectNode(rootNode);
                        seleNode=rootNode;
                    }
            	} else {
            		/* alert("currNode is not null"); */
            		getZTree().selectNode(currNode);
            		seleNode=currNode;
            	}
            } else {
            	/* alert("有选中的。"); */
            }
        	
        }
        
        function onAsyncError(){
            alert('加载错误...');
        }
        
        function getZTree(){
            var zTree = $.fn.zTree.getZTreeObj("zTree");
            return zTree;
        }
        
        //获取节点
        function getSelectedNode(){
            var nodes = getZTree().getSelectedNodes();
            if(!nodes){
                return;
            }
            var node=nodes[0];
            return node;
        }
        
        var setting = {
                async:{
                    enable: true,
                    url: _getNodeLoadUrl(),
                    autoParam:["id"]
                },
                callback: {
                    onClick: onClick,
                    onRightClick: OnRightClick,
                    beforeAsync: beforeAsync,
                    onAsyncSuccess: onAsyncSuccess,
                    onAsyncError: onAsyncError
                },
                data:{
                    keep:{
                        leaf:false,
                        parent:false
                    },
                    key:{
                        checked:"checked",
                        children:"children",
                        name:"orgName",
                        title:"",
                        url:"ssssurl"
                    },
                    simpleData:{
                        enable:true,
                        idKey:"id",
                        pIdKey:"parentId",
                        rootPId:null
                    }
                }
        };
        
        $(document).ready(function(){
            $.fn.zTree.init($("#zTree"), setting);
        });
    function _getNodeLoadUrl(){
        	var urlTreeLoad=CTX_PATH+'/hava/sys/org/tree?treeType=LOCAL_ORG&expandAll=true';
        	<% 
        		String courtId = request.getParameter("courtId");
        	   	courtId = (courtId == null ? "" : courtId);
        	%>
            var urlTreeLoad=CTX_PATH+'/hava/sys/org/treeDevelop?treeType=LOCAL_ORG&courtId='+ '<%=courtId%>';
        	/* alert(urlTreeLoad); */
            return urlTreeLoad; --%>
        }
    </SCRIPT>
	</head>
	<body bgcolor="#e2ecf9">
		<form method="post" target="#">
			<input type="hidden" id="courtId" name="courtId" />
			<!-- 法院ID -->
			<input type="hidden" id="courtName" name="courtName" />
			<!-- 法院名称 -->
			<input type="hidden" id="courtIp" name="courtIp" />
			<!-- 法院ip -->
			<input type="hidden" id="localCourtId" name="localCourtId"
				value="${localCourtId}" />
			  
			
			<table border="1" bordercolor="#BECBDA" cellspacing="0"
				cellpadding="0"
				style="width: 90%; height: 90%; position: relative; top: 4%; padding: 5px; border-collapse: collapse; margin-left: 10px;">
				<tr>
					<th style="height: 25px; background-color: #D1DAEB">
						法院信息
					</th>
				</tr>
				<tr>
					<td style="width: 90%; height: 90%;">
						
							<div style="width: 100%; height: 100%; overflow-y: scroll">
								<ul id="zTree" class="ztree"></ul>
							</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
