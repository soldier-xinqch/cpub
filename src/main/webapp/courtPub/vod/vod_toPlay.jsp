
<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ include file="/common/include/include.jsp"%>
<%@ include file="/common/include/include_sec_tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>播放</title>
<meta http-equiv="content-type" content="text/html;charset=" utf-8" />
<link type="text/css" rel="stylesheet" media="all"
	href="../../public/css/global.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="../../public/css/global_color.css" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type='text/javascript'
	src='<c:url value="/public/js/jwplayer.js"/>'></script>

</head>
<body>
	<!--主要区域开始-->
	<div id="toMain">
		<div id="event" style="text-align: center;">${caseName}</div>
			<!-- START OF THE PLAYER EMBEDDING TO COPY-PASTE -->
			<object id="player" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" name="player" width="100%" height="80%"> 
				<param name="movie" value="player.swf" /> 
				<param name="allowfullscreen" value="true" /> 
				<param name="allowscriptaccess" value="always" /> 
				<param name="flashvars" value="file=../../public/video.mp4&image=preview.jpg" /> 
				<embed 
					type="application/x-shockwave-flash"
					id="player2"
					name="player2"
					src="../../public/player.swf" 
					width="628" 
					height="400"
					allowscriptaccess="always" 
					allowfullscreen="true"
					flashvars="file=../../public/video.mp4&image=../../public/preview.jpg" 
				/> 
			</object> 
			<!-- END OF THE PLAYER EMBEDDING -->
		
<!-- 	<div id='mediaspace0'style="position:relitive; bottom£º10px; float:bottom;">
	</div>
	<script type='text/javascript'>
		window.onload = function(){
			live("rtmp://localhost/oflaDemo/stream1394532338270");
		}
		function live(liveaddress){
			var c = liveaddress.lastIndexOf("/");
			var file = liveaddress.substring(c+1);
			var live = liveaddress.substring(0,c);
			alert(file + ":" + live);
			jwplayer('mediaspace0').setup({
					'flashplayer': '../../public/player.swf',
					'file': file,
					'streamer': live,
					'controlbar': 'bottom',
					'width': '70%',
					'height': '66%'
					});
		}
	</script> -->
	</div>
	<!--主要区域结束-->
	<div id="footer">
		<p>[www.hxht.com]</p>
		<p>北京和兴宏图有限公司</p>
	</div>

</body>
</html>