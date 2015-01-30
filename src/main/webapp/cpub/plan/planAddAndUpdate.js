
YUI().use('node', 'io-base','json-parse','panel','escape', function(Y){
	
	// 选择案件 —-----------------------------------------------------------------------
	Y.one("#caseNums").on("click",function(){
		vodStatus.show();
	});
	Y.one("#registerTime").on("click",function(){
		vodStatus.show();
	});
	Y.one("#caseTypeName").on("click",function(){
		vodStatus.show();
	});
	Y.one("#cname").on("click",function(){
		vodStatus.show();
	});
	
    //对话框按钮
    var btnOk = {
        value:'确定',
        section:Y.WidgetStdMod.FOOTER,
        action:function(ev){
        	vodStatus.hide();
        }
    };
    
    //对话框按钮
    var btnCancel = {
        value:'取消',
        section:Y.WidgetStdMod.FOOTER,
        action:function(ev){
        	vodStatus.hide();
        }
    };
    
    var vodStatus = new Y.Panel({
        contentBox : Y.Node.create('<div id="checkCase" style="width: 100%;height: 500px;font-size: 12px;"><iframe src="caseList" style="width: 99%;height: 460px;font-size: 12px;"> </iframe> </div>'),
        //bodyContent: '<div class="promptBox icon-warn">[输入提示的详细信息……]</div>',
    	headerContent: '案件选择',
    	srcNode   	: '#checkCase',
        width      : 900,
        zIndex     : 6,
        centered   : true,
        modal      : false, // modal behavior
        render     : '.example',
        visible    : false, // make visible explicitly with .show()
        buttons    : [btnOk,btnCancel]
    });
 // 选择案件 —-----------------------------------------------------------------------

 // 选择法庭 —-----------------------------------------------------------------------
    Y.one("#roomName").on("click",function(){
    	roomStatus.show();
	});
	
    //对话框按钮
    var btnOk = {
        value:'确定',
        section:Y.WidgetStdMod.FOOTER,
        action:function(ev){
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
        contentBox : Y.Node.create('<div id="checkRoom" style="width: 100%;height: 500px;font-size: 12px;"><iframe src="rooms" style="width: 99%;height: 460px;font-size: 12px;"> </iframe></div>'),
        //bodyContent: '<div class="promptBox icon-warn">[输入提示的详细信息……]</div>',
    	headerContent: '开庭地点选择（法庭）',
    	srcNode   	: '#checkRoom',
        width      : 900,
        zIndex     : 6,
        centered   : true,
        modal      : false, // modal behavior
        render     : '.example',
        visible    : false, // make visible explicitly with .show()
        buttons    : [btnOk,btnCancel]
    });
 // 选择人员 —-----------------------------------------------------------------------
    
    // 选择法庭 —-----------------------------------------------------------------------
    Y.one("#heyiName").on("click",function(){
    	heYiStatus.show();
	});
	
    //对话框按钮
    var btnOk = {
        value:'确定',
        section:Y.WidgetStdMod.FOOTER,
        action:function(ev){
        	heYiStatus.hide();
        }
    };
    
    //对话框按钮
    var btnCancel = {
        value:'取消',
        section:Y.WidgetStdMod.FOOTER,
        action:function(ev){
        	heYiStatus.hide();
        }
    };
    
    var heYiStatus = new Y.Panel({
        contentBox : Y.Node.create('<div id="checkHeYi" style="width: 100%;height: 500px;font-size: 12px;"><iframe src="selectHeYi" style="width: 99%;height: 460px;font-size: 12px;"> </iframe></div>'),
        //bodyContent: '<div class="promptBox icon-warn">[输入提示的详细信息……]</div>',
    	headerContent: '合议庭成员选择',
    	srcNode   	: '#checkHeYi',
        width      : 390,
        zIndex     : 6,
        centered   : true,
        modal      : false, // modal behavior
        render     : '.example',
        visible    : false, // make visible explicitly with .show()
        buttons    : [btnOk,btnCancel]
    });
 // 选择人员 —-----------------------------------------------------------------------
    
    
    //对话框按钮
    var btnCancel = {
        value:'确定',
        section:Y.WidgetStdMod.FOOTER,
        action:function(ev){
        	planStatus.hide();
        }
    };
	
    var planStatus = new Y.Panel({
        contentBox : Y.Node.create('<div id="dialog" />'),
        bodyContent: '<div class="promptBox icon-warn">[输入提示的详细信息……]</div>',
        width      : 300,
        zIndex     : 6,
        centered   : true,
        modal      : false, // modal behavior
        render     : '.example',
        visible    : false, // make visible explicitly with .show()
        buttons    : [btnCancel]
    });
    
	Y.one("#submitBtn").on("click",function(){
		var caseNums = Y.one('#caseNums').get('value');
		var roomName = Y.one('#roomName').get('value');
		var d242 = Y.one('#d242').get('value');
		var isSubmit = true;
		var dateflag = testDate();
		if("" == caseNums || null==caseNums){
			Y.one('#dialog .promptBox').setHTML('案件信息不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			planStatus.show();
 			isSubmit = false;
		}else if("" == roomName || null==roomName){
			Y.one('#dialog .promptBox').setHTML('开庭地点不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			planStatus.show();
 			isSubmit = false;
		}else if("" == d242 || null==d242){
			Y.one('#dialog .promptBox').setHTML('开始时间不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			planStatus.show();
 			isSubmit = false;
		}else if(false==dateflag){
			Y.one('#dialog .promptBox').setHTML('开始时间不能等于结束时间');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			planStatus.show();
 			isSubmit = false;
		}
		
		//表单提交
		if(isSubmit){
			var form = Y.one("#courtPlanForm");
			form.submit();
		}
	});
	
	function  testDate(){
		var startDate = Y.one('#d242').get('value');
		var endDate = Y.one('#d241').get('value');
		var newStartDate = startDate.substring(11, 13);
		var newEndDate = endDate.substring(11, 13);
		var newStartDat1e = startDate.substring(14, 16);
		var newEndDat1e = endDate.substring(14, 16);
		if(""!= endDate){
		if(newStartDate>newEndDate){
			return false;
		}
			if(newStartDate == newEndDate){
				if(newStartDat1e>newEndDat1e||newStartDat1e == newEndDat1e){
					return false;
				}
			}
		}
		return true;
	}
});
