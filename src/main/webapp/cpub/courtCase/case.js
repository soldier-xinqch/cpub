
YUI().use('node', 'io-base','json-parse','io-form','transition',
		'panel', "datatable",'datatype-number',"datasource-get","datasource-jsonschema", 
		"datatable-datasource", 'array-extras', 'querystring-stringify','escape', function(Y){
	
//	Y.one("#d242").on("blur",function(){
//		var registerTimed = Y.one("#d242").get("value");
//		if("" == registerTimed || null == registerTimed){
//			Y.one('#dialog .promptBox').setHTML('<p class="promptBoxMeggess">立案时间不能为空</p>');
//			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
//			caseStatus.show();
//		}
//	});
	
	Y.one("#caseTypeName").on("blur",function(){
		var caseTypeName = Y.one("#caseTypeName").get("value");
		if("请选择案件类型" == caseTypeName){
			Y.one('#dialog .promptBox').setHTML('<p class="promptBoxMeggess">案件类型不能为空</p>');
			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
			caseStatus.show();
		}
	});
	
	Y.one("#name").on("blur",function(){
		var caseName = Y.one("#name").get("value");
		if("" == caseName || null == caseName){
			Y.one('#dialog .promptBox').setHTML('<p class="promptBoxMeggess">预告名称不能为空</p>');
			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
			caseStatus.show();
		}
	});
	
	Y.one("#caseDescText").on("blur",function(){
		var caseDescText = Y.one("#caseDescText").get("value");
		if("" == caseDescText || null == caseDescText){
			Y.one('#dialog .promptBox').setHTML('<p class="promptBoxMeggess">案件详细名称不能为空</p>');
			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
			caseStatus.show();
		}
	});
	
	
	Y.one("#num").on("blur",function(){
		var caseNum = Y.one("#num").get("value");
		if("" == caseNum || null == caseNum){
			Y.one('#dialog .promptBox').setHTML('<p class="promptBoxMeggess">案号名称不能为空</p>');
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
			caseStatus.show();
			//alert("法庭名称不能为空！");
		}else{
			checkCaseNum();
		}
	});
	
    //对话框按钮
    var btnCancel = {
        value:'确定',
        section:Y.WidgetStdMod.FOOTER,
        action:function(ev){
        	caseStatus.hide();
        }
    };
	
    var caseStatus = new Y.Panel({
        contentBox : Y.Node.create('<div id="dialog" />'),
        bodyContent: '<div class="promptBox icon-warn">[输入提示的详细信息……]</div>',
        width      : 270,
        zIndex     : 6,
        centered   : true,
        modal      : false, // modal behavior
        render     : '.example',
        visible    : false, // make visible explicitly with .show()
        buttons    : [btnCancel]
    });
    
	function checkCaseNum(){
		var checkCase ="";
		var caseNum = Y.one('#num').get('value');
		var caseId = Y.one("#caseId").get("value");
		Y.io(CTX_PATH +"/verifyCase?caseNum="+caseNum+'&caseId='+caseId+'&timeFlag='+Math.random(),{
			method: 'GET',
			sync : true, 
			on: {
				complete:function(id, response){
					var result=Y.JSON.parse(response.responseText);
					 if(result.code != 'SUCCESS'){
	                	//失败
	                	Y.one('#dialog .promptBox').setHTML('<p class="promptBoxMeggess">案号不能重复</p>');
	         			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
	         			caseStatus.show();
	         			checkCase = true;
					 }else{
						 checkCase = false;
					 }
				}
			}
		});
		return checkCase;
	}
	
	Y.one("#submitBtn").on("click",function(){
		var num = Y.one('#num').get('value');
		var name = Y.one('#name').get('value');
		var caseTypeName = Y.one('#caseTypeName').get('value');
		var d242 = Y.one('#d242').get('value');
		var caseDescText = Y.one('#caseDescText').get('value');
		var checkName = checkCaseNum();
		var isSubmit = true;
		if("" == num || null==num){
			Y.one('#dialog .promptBox').setHTML('案号不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			caseStatus.show();
 			isSubmit = false;
		}else if("" == name || null==name){
			Y.one('#dialog .promptBox').setHTML('案件名称不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			caseStatus.show();
 			isSubmit = false;
		}else if("" == caseTypeName || null==caseTypeName){
			Y.one('#dialog .promptBox').setHTML('案件类型不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			caseStatus.show();
 			isSubmit = false;
		}else if("" == d242 || null==d242){
			Y.one('#dialog .promptBox').setHTML('立案时间不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			caseStatus.show();
 			isSubmit = false;
		}else if("" == caseDescText || null==caseDescText){
			Y.one('#dialog .promptBox').setHTML('案件详细不能为空');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			caseStatus.show();
 			isSubmit = false;
		}else if(checkName){
			alert("dfdf"+ checkName);
			Y.one('#dialog .promptBox').setHTML('案号不能重复');
 			Y.one('#dialog .promptBox').set('className', 'promptBox icon-warn');
 			caseStatus.show();
 			isSubmit = false;
		}
		
		//表单提交
		if(isSubmit){
			var form = Y.one("#courtCaseForm");
			form.submit();
		}
	});
});
