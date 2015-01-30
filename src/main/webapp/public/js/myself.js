
function showLocale()
{
	var str;
	var objD = new Date();
	var yy = objD.getYear();
		if(yy<1900) yy = yy+1900;
	var MM = objD.getMonth()+1;
		if(MM<10) MM = '0' + MM;
	var dd = objD.getDate();
		if(dd<10) dd = '0' + dd;
	var hh = objD.getHours();
		if(hh<10) hh = '0' + hh;
	var mm = objD.getMinutes();
		if(mm<10) mm = '0' + mm;
	var ss = objD.getSeconds();
		if(ss<10) ss = '0' + ss;
	var ww = objD.getDay();
		if ( ww==0 ) colorhead="<font color=\"#FF0000\">";
		if ( ww > 0 && ww < 6 ) colorhead="<font color=\"#373737\">";
		if ( ww==6 ) colorhead="<font color=\"#008000\">";
		if (ww==0) ww="星期日";
		if (ww==1) ww="星期一";
		if (ww==2) ww="星期二";
		if (ww==3) ww="星期三";
		if (ww==4) ww="星期四";
		if (ww==5) ww="星期五";
		if (ww==6) ww="星期六";
		str =  "当前时间："+ yy + "年" + MM + "月" + dd + "日" + hh + ":" + mm + ":" + ss + " " + ww;
		document.getElementById("nowTime").innerHTML =str;
		window.setTimeout("showLocale()", 1000);
}