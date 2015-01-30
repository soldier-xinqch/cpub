

	
function deleteVod(id){
	YUI().use('node', 'io-base','json-parse','panel','escape', function(Y){
	    //对话框按钮
	    var btnOk = {
	        value:'确定',
	        section:Y.WidgetStdMod.FOOTER,
	        action:function(ev){
	        	window.location = 'deleteUser?id='.concat(id);
	        	userStatus.hide();
	        }
	    };
	    
	    //对话框按钮
	    var btnCancel = {
	        value:'取消',
	        section:Y.WidgetStdMod.FOOTER,
	        action:function(ev){
	        	userStatus.hide();
	        }
	    };
	    
	    //对话框按钮
	    var btnDElCancel = {
	        value:'确定',
	        section:Y.WidgetStdMod.FOOTER,
	        action:function(ev){
	        	deleteUserStatus.hide();
	        }
	    };
		
	    var userStatus = new Y.Panel({
	        contentBox : Y.Node.create('<div id="dialog1" />'),
	        bodyContent: '<div class="promptBox icon-warn">[输入提示的详细信息……]</div>',
	        width      : 370,
	        zIndex     : 6,
	        centered   : true,
	        modal      : false, // modal behavior
	        render     : '.example',
	        visible    : false, // make visible explicitly with .show()
	        buttons    : [btnOk,btnCancel]
	    });
	    
	    var deleteUserStatus = new Y.Panel({
	        contentBox : Y.Node.create('<div id="dialog" />'),
	        bodyContent: '<div class="promptBox icon-warn"><p class="promptBoxMeggess">确定要删除此条案件信息吗？</p></div>',
	        width      : 370,
	        zIndex     : 6,
	        centered   : true,
	        modal      : false, // modal behavior
	        render     : '.example',
	        visible    : false, // make visible explicitly with .show()
	        buttons    : [btnDElCancel]
	    });
	    
		Y.io(CTX_PATH +"/verifyDeleteUser?id="+id+'&timeFlag='+Math.random(),{
			method: 'GET',
			sync : true, 
			on: {
				complete:function(id, response){
					var result=Y.JSON.parse(response.responseText);
					 if(result.code == 'SUCCESS'){
	                	//失败
						Y.one('#dialog1 .promptBox').setHTML('<p class="promptBoxMeggess">确定要删除此条用户信息吗？</p>');
	         			Y.one('#dialog1 .promptBox').set('className', 'promptBox icon-warn');
	         			userStatus.show();
					 }else{
						Y.one('#dialog .promptBox').setHTML('<p class="promptBoxMeggess">不能删除同法院的用户！</p>');
	         			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
	         			deleteUserStatus.show();
					 }
				}
			}
		});
	});
}
