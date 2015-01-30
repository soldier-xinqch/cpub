
YUI().use('node', 'io-base','json-parse','io-form','transition',
		'panel', "datatable",'datatype-number',"datasource-get","datasource-jsonschema", 
		"datatable-datasource", 'array-extras', 'querystring-stringify','escape', function(Y){
	
	
	Y.one("#roomName").on("blur",function(){
		var roomName = Y.one("#roomName").get("value");
		if("" == roomName || null == roomName){
			Y.one('#dialog .promptBox').setHTML('法庭名称不能为空');
			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
			    /* classnames and images provided in the CSS are:
			        .icon-bubble --信息
			        .icon-error --错误
			        .icon-info --信息提示
			        .icon-question --警示
			        .icon-warn -- 警告
			        .icon-success -- 成功
			        .icon-none --空
			     */
			//roomStatus.set('headerContent',"<div class='prompBoxHeader'><p>法庭操作提示</p><div/>");
			roomStatus.show();
			//alert("法庭名称不能为空！");
		}else{
			checkRoomName();
		}
	});
	
	 //对话框确定按钮
    var btnConfirm = {
        value:'确定',
        section:Y.WidgetStdMod.FOOTER,
        action:function(ev){
            //执行删除操作
        	var planId = panelDelete.get('planId');
            delInfo(planId);
            panelDelete.hide();
        }
    };
    //对话框按钮
    var btnCancel = {
        value:'确定',
        section:Y.WidgetStdMod.FOOTER,
        action:function(ev){
        	roomStatus.hide();
        }
    };
	
    var roomStatus = new Y.Panel({
        contentBox : Y.Node.create('<div id="dialog" />'),
        bodyContent: '<div class="promptBox icon-warn">法庭名称不能为空!</div>',
        width      : 270,
        zIndex     : 6,
        centered   : true,
        modal      : false, // modal behavior
        render     : '.example',
        visible    : false, // make visible explicitly with .show()
        buttons    : [btnCancel]
    });
    
	function checkRoomName(){
		var checkName = "";
		var roomNamed = Y.one('#roomName').get('value');
		var roomId = Y.one("#roomId").get("value");
		Y.io(CTX_PATH +"/verifyCourtRoom?roomName="+roomNamed+'&roomId='+roomId+'&timeFlag='+Math.random(),{
			method: 'GET',
			sync : true, 
			on: {
				complete:function(id, response){
					var result=Y.JSON.parse(response.responseText);
					 if(result.code != 'SUCCESS'){
	                	//失败
	                	Y.one('#dialog .promptBox').setHTML('法庭名称不能重复');
	         			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
	         			roomStatus.show();
	         			checkName=true;
					 }else{
						checkName=false;
					 }
				}
			}
		});
		return checkName;
	}
	Y.one("#submitBtn").on("click",function(){
		var roomName = Y.one('#roomName').get('value');
		var pubUrl = Y.one('#pubAccessUrl').get('value');
		var checkName = checkRoomName();
		var isSubmit = true;
		if("" == roomName || null==roomName){
			Y.one('#dialog .promptBox').setHTML('法庭名称不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			roomStatus.show();
 			isSubmit = false;
		}else if("" == pubUrl || null==pubUrl){
			Y.one('#dialog .promptBox').setHTML('直播流地址不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			roomStatus.show();
 			isSubmit = false;
		}else if(checkName){
			alert("dfdf"+ checkName);
			Y.one('#dialog .promptBox').setHTML('法庭名称不能重复');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			roomStatus.show();
 			isSubmit = false;
		}
		
		//表单提交
		if(isSubmit){
			var form = Y.one("#courtRoomForm");
			form.submit();
		}
	});
});
function checkLength(which) { 
	var maxChars = 250; 
	if (which.value.length > maxChars) 
	which.value = which.value.substring(0,maxChars); 
	var curr = maxChars - which.value.length; 
	document.getElementById("chLeft").innerHTML = curr.toString(); 
	}  
