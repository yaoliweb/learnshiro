<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	//设置无缓存
	response.addHeader("Progma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Cache-Control", "must-revalidate");
%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript">
	function screenAdapter() {
		/* document.getElementById('footer').style.top= document.documentElement.scrollTop+document.documentElement.clientHeight- document.getElementById('footer').offsetHeight+"px"; */
		document.getElementById('navigator').style.height = document.documentElement.clientHeight - 150 + "px";
		document.getElementById('main').style.height = document.documentElement.clientHeight - 150 + "px";
		/*document.getElementById('main').style.width=window.screen.width-230+"px";*/
	}

	window.onscroll = function() {
		screenAdapter();
	};
	window.onresize = function() {
		screenAdapter();
	};
	window.onload = function() {
		screenAdapter();
	};
</script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<div id="logo"></div>
			<table style="border:1px">
				<tr style="height:38px;">
					<td></td>
					<td><!-- <br />时间 --></td>
				</tr>
				<tr style="height:50px;">
					<td width="450px" align="center" 
						style="vertical-align:middle;font-size:18px;align:left;text-shadow:Red;font-family:'黑体';">江苏商达智慧型农村水环境<br />治理监管综合系统</td>
					<td>
						<%-- <img src="<%=basePath%>images/sdnum_1.jpg"> --%>
						
						<a href="http://www.sunda.zj.cn/" style="cursor:pointer" target="blank"><img src="<%=basePath%>images/sdnum_1.jpg"></a>
						<a href="<%=basePath%>common/seehelp.do" style="cursor:pointer" target="MainFrame"><img src="<%=basePath%>images/sdnum_2.jpg"></a>
						<a href="<%=basePath%>common/contactus.do" style="cursor:pointer" target="MainFrame"><img src="<%=basePath%>images/sdnum_3.jpg"></a>
						<a href="<%=basePath%>system/outlogin.do" style="cursor:pointer"><img src="<%=basePath%>images/sdnum_4.jpg"></a>   	
					</td>
				</tr>
			</table>
		</div>


		</div>
		<div id="navigator">
			<iframe src="<%=basePath%>system/gotomenus.do"></iframe>
		</div>

		<div id="main">
			<iframe name="MainFrame" src="<%=basePath%>monitor/intomapmonitor.do" ></iframe>
		</div>

		<!-- <div id="footer">Copyright © 2009-2011 All Rights Reserved Powered By Nan Lei</div> -->
	</div>
</body>
</html>