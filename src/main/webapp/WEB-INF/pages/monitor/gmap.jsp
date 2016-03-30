<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	//设置无缓存
	response.addHeader("Progma","no-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires",0);
	response.setHeader("Pragma", "no-cache");   
	response.setHeader("Cache-Control", "no-store");  
	response.setHeader("Cache-Control", "must-revalidate");
%>

<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<!-- 必须添加这个 -->
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lro9rUlcpaFKtWd1F19DuPPs"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/map/gmap.js"></script>
	
	
	<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
<body>
	<div class="search-content">
		<form action="#" method="post">
			<table class="search-tab">
				<tr>
					<!-- <th width="70">关键字:</th> -->
					<td><input class="common-text" placeholder="请输入地点简称或者运营号" name="keywords" id="keywords" value="" id="" type="text">
					</td>
					<td><input class="btn" name="seachpoint" id="seachpoint" value="搜索" type="button">
					</td>
					<td><select style="width:200px" name="searchlist_areaid" id="searchlist_areaid">
						<c:forEach items="${allAreas}" var="item">
							<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select>
					</td>
					<td><input class="btn" name="seachlist" id="seachlist" value="选定" type="button">
					
					<input type="checkbox" id="showtruemap" name="showtruemap"><!-- 展示真实的页面 -->
					<input class="btn" name="zhuapai" id="zhuapai" value="图像抓拍" type="button" onclick="javascript:window.open('http://115.236.191.50:9090/SWS/pages/Country/Media/atuoLoginPicture.jsp?dbname=SWS_COUNTRY_TEST&username=admin')">
					<label id="abnormalrate">设备故障率0.0%</label>
					
					<label id="waterrate">水质异常率0.0%</label>
				</tr>
			</table>
		</form>
	</div>
<div id="allmap">

</div>

</body>
</html>