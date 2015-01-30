
YUI().use('node', 'io-base','json-parse','io-form','transition',
		'panel', "datatable",'datatype-number',"datasource-get","datasource-jsonschema", 
		"datatable-datasource", 'array-extras', 'querystring-stringify','escape', function(Y){
	 var roles =  Y.one("#role");
	 roles.set("name"," ");
	Y.one("#courtName").on("blur",function(){
		var courtId = Y.one('#courtName').get('value');
		Y.io(CTX_PATH +"/verifyChooseUser?courtId="+courtId+'&timeFlag='+Math.random(),{
			method: 'GET',
			sync : true, 
			on: {
				complete:function(id, response){
					var result=Y.JSON.parse(response.responseText);
					 if(result.code != 'SUCCESS'){
						 //只能创建合议庭用户
						 var inferiorUser =  Y.one("#inferiorUser");
						 var localUser =  Y.one("#localUser");
						 var roles =  Y.one("#roles");
						 roles.set("name"," ");
						 var roles =  Y.one("#role");
						 roles.set("name","roles");
						 localUser.addClass('disPlayDiv');
						 inferiorUser.removeClass('disPlayDiv');
					 }else{
						 //可以创建管理员用户和合议庭用户
						 var inferiorUser =  Y.one("#inferiorUser");
						 var localUser =  Y.one("#localUser");
						 var roles =  Y.one("#role");
						 roles.set("name"," ");
						 var roles =  Y.one("#roles");
						 roles.set("name","roles");
						 inferiorUser.addClass('disPlayDiv');
						 localUser.removeClass('disPlayDiv');
					 }
				}
			}
		});
	});
	
	
	
	Y.one("#userName").on("blur",function(){
		chooseUserRolse();
	});
	
	   //对话框按钮
    var btnCancel = {
        value:'确定',
        section:Y.WidgetStdMod.FOOTER,
        action:function(ev){
        	userStatus.hide();
        }
    };
	
    var userStatus = new Y.Panel({
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
	
	function chooseUserRolse(){
		var checkName = "";
		var userName = Y.one('#userName').get('value');
		var userId = Y.one("#userId").get("value");
		Y.io(CTX_PATH +"/verifyUserName?userName="+userName+'&userId='+userId+'&timeFlag='+Math.random(),{
			method: 'GET',
			sync : true, 
			on: {
				complete:function(id, response){
					var result=Y.JSON.parse(response.responseText);
					 if(result.code != 'SUCCESS'){
						 //只能创建合议庭用户
						Y.one('#dialog .promptBox').setHTML('用户名称不能重复');
	         			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
	         			userStatus.show();
	         			checkName= true;
					 }else{
						checkName = false;
					 }
				}
			}
		});
		return checkName;
	}

	
	Y.one("#submitBtn").on("click",function(){
		var state = Y.one('#courtName').get('value');
		var userName = Y.one('#userName').get('value');
		var passWord = Y.one('#passWord').get('value');
		var realName = Y.one('#realName').get('value');
		var roles = Y.one('#roles').get('value');
		var checkName = chooseUserRolse();
		var isSubmit = true;
		if("" == state || null==state){
			Y.one('#dialog .promptBox').setHTML('法庭名称不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			userStatus.show();
 			isSubmit = false;
		}else if("" == userName || null==userName){
			Y.one('#dialog .promptBox').setHTML('用户名不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			userStatus.show();
 			isSubmit = false;
		}else if("" == passWord || null==passWord){
			Y.one('#dialog .promptBox').setHTML('用户密码不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			userStatus.show();
 			isSubmit = false;
		}else if("" == realName || null==realName){
			Y.one('#dialog .promptBox').setHTML('真实名称不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			userStatus.show();
 			isSubmit = false;
		}else if("" == roles || null==roles){
			Y.one('#dialog .promptBox').setHTML('角色不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			userStatus.show();
 			isSubmit = false;
		}else if(checkName){
			alert("dfdf"+ checkName);
			Y.one('#dialog .promptBox').setHTML('用户名不能重复');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			userStatus.show();
 			isSubmit = false;
		}
		
		//表单提交
		if(isSubmit){
			var form = Y.one("#courtUserForm");
			form.submit();
		}
	});
});
