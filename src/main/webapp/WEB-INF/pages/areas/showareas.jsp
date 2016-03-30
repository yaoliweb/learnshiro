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

	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/icon.css">
	
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
	
<body>
	<table id="showAreas" class="easyui-datagrid"  title="地区列表" style="width:100%;height:420px">
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			<a id="deleteUser" href="#" class="easyui-linkbutton">删选中地区</a>
			<a id="addUser" href="<%=basePath %>areas/gotoaddnewarea.do" class="easyui-linkbutton">增加区县</a>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$('#showAreas').datagrid({  
    url:getContextPath()+'/areas/getareas.do',
    columns:[[
		{field:'id',title:'id',width:130,hidden:true},
		{field:'select',checkbox:true},
		{field:'name',title:'区县名称',width:130,align:'center'},
		{field:'adminName',title:'负责人',width:130,align:'center'},
		{field:'adminTelephone',title:'联系电话',width:130,align:'center'}
    ]],
    fitColumns:true,
    idField:"id",
    pagination:true,//显示分页  
    pageSize:10,//分页大小  
    pageList:[10,15,20],//每页的个数  
    toolbar: '#tb',
});
$("#deleteUser").click(function () {
     var selectIds = []; 
     var rows =  $('#showAreas').datagrid('getChecked');
     $.each(rows,function(index,data){
     	selectIds.push(data.id);
     });
     
     var postUrl = getContextPath()+"/areas/deleteseletedareas.do";
     
     var data = {"selectedIds":selectIds};
     
     $.messager.confirm('消息','确实删除所选的区域?',function(r){
    	if (r){
			     $.ajax({
						type : "POST",
						url : postUrl,
						dataType : "json",
						contentType : "application/json;charset=utf-8",
						data : JSON.stringify(data),
						async : false,
						success : function(returndata) {
/* 							if(returndata.key == "pass"){
								//重新加载数据
								$("#showAreas").datagrid("reload");
							}else if(returndata.key == "error"){
								$.messager.comfirm("消息","该地区不能删除，因为有污水站点与它绑定！","info");
							} */
							if(returndata.key == "pass"){
								var message = "";
								if(returndata.data.length > 0){
									message = "地区：";
									for(var i=0;i< returndata.data.length;i++){
									     $.each(rows,function(index,data){
     										if(returndata.data[i] == data.id){
     											message = message + data.name + " ";
     										}
     									 });									
									}
									message = message + "不能被删除，因为有污水站点与它绑定！";
								}
								if(rows.length > returndata.data.length){
									message = "删除成功！"+message;
								}
								$.messager.alert("提示",message,"info");
								//重新加载数据
								$("#showAreas").datagrid("reload");
							}
						}
				}); 
    		}
	});
     

});
$("#resetPwd").click(function () {
        alert("resetPwd我被点到了！");
});
/*以下高度高度自适应*/
$('#showUsers').datagrid('resize', {

});

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>