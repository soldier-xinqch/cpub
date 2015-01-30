	
function deleteVod(id){
	YUI().use('node', 'io-base','json-parse','panel','escape', function(Y){
		
	    //对话框按钮
	    var btnOk = {
	        value:'确定',
	        section:Y.WidgetStdMod.FOOTER,
	        action:function(ev){
	        	window.location = '<c:url value="/courtPub/vod/deleteRoom?id=' + id + '"/>';
	        	roomStatus.hide();
	        }
	    };
	    
	    //对话框按钮
	    var btnCancel = {
	        value:'取消',
	        section:Y.WidgetStdMod.FOOTER,
	        action:function(ev){
	        	roomStatus.hide();
	        }
	    };
	    
	    var roomStatus = new Y.Panel({
	        contentBox : Y.Node.create('<div id="dialog" />'),
	        bodyContent: '<div class="promptBox icon-warn"><p class="promptBoxMeggess">确定要删除此法庭吗？</p></div>',
	        width      : 370,
	        zIndex     : 6,
	        centered   : true,
	        modal      : false, // modal behavior
	        render     : '.example',
	        visible    : false, // make visible explicitly with .show()
	        buttons    : [btnOk,btnCancel]
	    });
	    
		roomStatus.show();
	});
}
