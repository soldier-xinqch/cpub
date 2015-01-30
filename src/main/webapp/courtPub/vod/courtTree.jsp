<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>法院选择</title>
<meta name="decorator" content="none" />
<link rel="stylesheet"
	href="<c:url value="/public/css/zTreeStyle35/zTreeStyle.css"/>"
	type="text/css">
<script type="text/javascript"
	src="<c:url value="/public/js/zTree35/jquery-1.4.4.min.js"/>"></script>
<script type='text/javascript'
	src='<c:url value="/public/js/jquery-ui-1.9.2.custom.js"/>'></script>
<script type="text/javascript"
	src="<c:url value="/public/js/zTree35/jquery.ztree.core-3.5.min.js"/>"></script>
<SCRIPT type="text/javascript">
   		var CTX_PATH='<%=request.getContextPath()%>';
		<%if(request.getServerPort()==80){%>
			var HOST_PORT='<%=request.getScheme() + "://" + request.getServerName()%>';
		<%}else{%>
			var HOST_PORT='<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()%>';
		<%}%>
        //选择组织机构节点
        function onClick(event, treeId, treeNode, clickFlag){
          /*   alert(getSelectedNode().orgName);
            alert(getSelectedNode().id); 
            var id = (getSelectedNode().id);*/
            var courtName=(getSelectedNode().orgName);
            var courtId = (getSelectedNode().id);
            parent.document.getElementById("court").value = courtName;
            parent.document.getElementById("courtId").value = courtId;
            /* if(confirm("确认选择该法院么？")) {
                 var courtName=(getSelectedNode().orgName);
                 var courtId = (getSelectedNode().id);
                 parent.document.getElementById("court").value = courtName;
                 parent.document.getElementById("courtId").value = courtId;
		         window.close();
	             
          		} */
        }
        
        function OnRightClick(){
                    
        }
                
        function beforeAsync(){
            //alert('开始异步加载...');
            
        }
        
        function onAsyncSuccess(){
        	${courtId}
        	
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
                        url:"url"
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
            var urlTreeLoad=CTX_PATH+'/courtPub/vod/courtTree?treeType=LOCAL_ORG';
            return urlTreeLoad;
        }
      /*   function check() {
      	    if(confirm("确认选择这条做为回复吗？")) {
      	    	 alert(getSelectedNode().orgName);
                 alert(getSelectedNode().id);
                 var courtName=(getSelectedNode().orgName);
		                window.opener.document.getElementById("court").value = courtName;
		        window.close();
          }
      } */
    </SCRIPT>
</head>
<body style="bgcolor:#e2ecf9;width:250px;" >
		<table border="1" bordercolor="#BECBDA" cellspacing="0"cellpadding="0"
			style="width:240px; height: 90%;background-color:#e2ecf9; padding: 5px; border-collapse: collapse;	">
			<tr>
				<th style="height: 25px; background-color: #D1DAEB">法院列表</th>
			</tr>
			<tr>
				<td style="width: 240px; height: 90%;">

					<div style="width: 240px; height: 400px; overflow-y: scroll">
						<ul id="zTree" class="ztree"></ul>
					</div>
				</td>
			</tr>
		</table>
</body>
</html>
