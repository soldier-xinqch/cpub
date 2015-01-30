

	
function deleteVod(id){
	YUI().use('node', 'io-base','json-parse','panel','escape', function(Y){
	    //对话框按钮
	    var btnOk = {
	        value:'确定',
	        section:Y.WidgetStdMod.FOOTER,
	        action:function(ev){
	        	window.location =  '<c:url value="/courtPub/vod/delete?id=' + id + '"/>';
	        	caseStatus.hide();
	        }
	    };
	    
	    //对话框按钮
	    var btnCancel = {
	        value:'取消',
	        section:Y.WidgetStdMod.FOOTER,
	        action:function(ev){
	        	caseStatus.hide();
	        }
	    };
	    
	    //对话框按钮
	    var btnDElCancel = {
	        value:'确定',
	        section:Y.WidgetStdMod.FOOTER,
	        action:function(ev){
	        	deleteCaseStatus.hide();
	        }
	    };
		
	    var caseStatus = new Y.Panel({
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
	    
	    var deleteCaseStatus = new Y.Panel({
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
	    
		Y.io(CTX_PATH +"/verifyCaseDelete?id="+id+'&timeFlag='+Math.random(),{
			method: 'GET',
			sync : true, 
			on: {
				complete:function(id, response){
					var result=Y.JSON.parse(response.responseText);
					 if(result.code == 'SUCCESS'){
	                	//失败
						Y.one('#dialog1 .promptBox').setHTML('<p class="promptBoxMeggess">确定要删除此条案件信息吗？</p>');
	         			Y.one('#dialog1 .promptBox').set('className', 'promptBox icon-warn');
	         			caseStatus.show();
					 }else{
						Y.one('#dialog .promptBox').setHTML('<p class="promptBoxMeggess">案件已经添加预告不能删除</p>');
	         			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
	         			deleteCaseStatus.show();
					 }
				}
			}
		});
	});
}
