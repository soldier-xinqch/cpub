/*判断输入框是否为空*/
function isInputEmpty(inputId){
    if($.trim($('#' + inputId).val()).length==0) {
        return true;
    }else{
        return false;
    }
}

/*判断输入框是否为数字*/
function isNumInput(inputId){
    if(!isInputEmpty(inputId)){
        if($('#'+inputId).val()==parseFloat($('#'+inputId).val())){
            return true;
        }else{
            return false;
        }
    }else{//
        return true;
    }
}

function validateNumInput(inputId,fieldName,errElmPrefix){
    result=isNumInput(inputId);
    if(!result){
        $('#' + inputId).focus();
        showWarnMsg(errElmPrefix,fieldName+'只能输入数字');
    }
    return result;
}

/*当blur事件发生时，如果输入不是数字，显示错误提示，并且将焦点聚焦在输入空间上*/
function numberBlurEvent(inputId,fieldName,errElmPrefix){
    $("#"+inputId).blur(
        function(){
            if(!isNumInput(inputId)){
                $('#' + inputId).focus();
                showWarnMsg(errElmPrefix,fieldName+'只能输入数字');
            }
        }
    );
}

/*当blur事件发生时，如果为空，显示错误提示，并且将焦点聚焦在输入控件上*/
function notEmptyBlur(inputId,fieldName,errElmPrefix){
    if(typeof $('#' + errElmPrefix + 'Div')!='undefined' || $('#' + errElmPrefix + 'Div')!=null){
        $('#' + errElmPrefix + 'Div').hide();
    }
    if(isInputEmpty(inputId)){
        $('#' + inputId).focus();
        if(typeof $('#' + errElmPrefix + 'Div')!='undefined' || $('#' + errElmPrefix + 'Div')!=null){
             showWarnMsg(errElmPrefix,fieldName+'不允许为空，请输入');
        }
        return false;
    }
    return true;
}

function validateNotEmpty(inputId,fieldName,errElmPrefix){
    return notEmptyBlur(inputId,fieldName,errElmPrefix);
}

/*非空输入校验事件，当blur的时候发生*/
function notEmptyBlurEvent(inputId,fieldName,errElmPrefix){
    $("#"+inputId).blur(
        function(){
            notEmptyBlur(inputId,fieldName,errElmPrefix);
        }
    );
}

/*输入定长校验事件，当keyup的时候发生，inputId，输入项id，fieldName，字段中文名,len字段长度,warnElemPrefix错误消息元素前缀*/
function fixedLenKeyupEvent(inputId,fieldName,len,warnElemPrefix){
    $("#"+inputId).keyup(
        function(){
            validateFixedLen(inputId,fieldName,len,warnElemPrefix);
        }
    );
}

function validateFixedLen(inputId,fieldName,len,warnElemPrefix){
    if(len<$("#"+inputId).val().length){
            $("#"+inputId).val($("#"+inputId).val().substring(0,len));
            showLenWarnMsg(warnElemPrefix,fieldName,len);
            return false;
    }else{
        return true;
    }
}

function showLenWarnMsg(warnElemPrefix,fieldName,len){
    $('#' + warnElemPrefix + 'Span').html(fieldName+"不能超过"+len+"个字符长度");
    $('#' + warnElemPrefix + 'Div').fadeIn().delay(3000).fadeOut('slow');
}

function showWarnMsg(errElmPrefix,errMsg){
    $('#' + errElmPrefix + 'Span').html(errMsg);
    $('#' + errElmPrefix + 'Div').fadeIn().delay(3000).fadeOut('slow');
}

function showOkMsg(okElmPrefix,okMsg){
    $('#' + okElmPrefix + 'Span').html(okMsg);
    $('#' + okElmPrefix + 'Div').fadeIn().delay(3000).fadeOut('slow');
}

/*将表单复位*/
function resetForm(formId){
    $('#'+formId).each(function(){
        this.reset();
    });
}

function clearForm(formId,clrHidden) {
    $form=$('#' + formId);
    if(clrHidden){
        $form.find('input:text, input:password, input:file, input:hidden, select, textarea').val('');
    }else{
        $form.find('input:text, input:password, input:file, select, textarea').val('');
    }
    $form.find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
}

/*循环方式将hash中的字段进行数字验证事件绑定*/
function bindNumBlurEvents(aHash,warnElemPrefix){
    $.each(aHash, function(aKey,aVal){
        numberBlurEvent(aKey,aVal,warnElemPrefix);
    });
}

/*循环方式将hash中的字段进行长度校验事件绑定*/
function bindFixedLenKeyupEvents(aHash,warnElemPrefix){
    $.each(aHash, function(aKey,aVal){
        fixedLenKeyupEvent(aKey,aVal[0],aVal[1],warnElemPrefix);
    });
}

function isFormInputsEmpty(formId){
    $inputs = $('#'+ formId+ ' :input');
    isEmpty=true;
    $inputs.each(
        function(){
            if($(this).attr('type')=='text'){
                if(!isInputEmpty($(this).attr('id'))){
                    isEmpty=false;
                }
            }
        }
    );
    return isEmpty;
}

function isFormFullInputs(formId){
    $inputs = $('#'+ formId+ ' :input');
    isFull=true;
    $inputs.each(
        function(){
            if($(this).attr('type')=='text'){
                isFull=isFull && !isInputEmpty($(this).attr('id'));
                
            }
        }
    );
    return isFull;
}

//是否正整数判断
function isPositiveInt(inputId){
    if(!isInputEmpty(inputId)){
        if(isNumInput(inputId)){
            anVal=$('#' + inputId).val();
            return anVal.indexOf(".")<0 && anVal.indexOf("-")<0;
        }else{
            return false;
        }
    }else{
        return true;
    }
}

//正整数校验
function validatePositiveInt(inputId,fieldName,errElmPrefix){
    result=isPositiveInt(inputId);
    if(!result){
        $('#' + inputId).focus();
        showWarnMsg(errElmPrefix,fieldName+'只能输入正整数');
    }
    return result;
}

//正整数校验事件绑定
function positiveIntBlurEvent(inputId,fieldName,errElmPrefix){
    $("#"+inputId).blur(
        function(){
            validatePositiveInt(inputId,fieldName,errElmPrefix);
        }
    );
}

function showAjaxMsgBar(msgText){
    $('#ajaxMsgBar').html(msgText);
    $('#ajaxMsgBar').fadeIn().delay(2000).fadeOut('slow');
}

function sleep(ms){
    var dt=new Date();
    dt.setTime(dt.getTime()+ms);
    while(new Date().getTime()<dt.getTime());
}

Date.prototype.format = function(format)
{
    var o = {
    "M+" : this.getMonth()+1, //month
    "d+" : this.getDate(),    //day
    "h+" : this.getHours(),   //hour
    "m+" : this.getMinutes(), //minute
    "s+" : this.getSeconds(), //second
    "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
    "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
    (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,
    RegExp.$1.length==1 ? o[k] :
    ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}

function showTime(spanId) //显示时间的方法  
{
	now=new Date();
	dt=now.format("yyyy年M月d日 hh:mm:ss");
	dts=dt.split(' ');
	document.getElementById(spanId).innerHTML=dts[0] + " " + getChiTimePeriod(now) + " " + dts[1]
		+' 星期'+'日一二三四五六'.charAt(now.getDay());
}

//获取中文时间段名称
function getChiTimePeriod(aDate){
	now = aDate;
	hour = now.getHours();
	if(hour < 6){return ("凌晨")}
	else if (hour < 9){return ("早上")}
	else if (hour < 12){return ("上午")}
	else if (hour < 14){return ("中午")}
	else if (hour < 17){return ("下午")}
	else if (hour < 19){return ("傍晚")}
	else if (hour < 22){return ("晚上")}
	else {return ("夜里")}
}

function validatePureStr(aStr){
	return aStr.match(/^\w+$/);
}
