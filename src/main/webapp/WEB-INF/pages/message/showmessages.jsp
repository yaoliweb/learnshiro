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
	<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
	
	
<body>
	<table id="showForm" class="easyui-datagrid" title="已发送短信列表" style="width:100%;height:420px">
	</table>
</body>
</html>

<script type="text/javascript">
$('#showForm').datagrid({  
    url:getContextPath()+'/message/getmessages.do',
    columns:[[
		{field:'messageid',title:'id',width:130,hidden:true},
		{field:'tel',title:'发送号码',width:130,align:'center'},
		{field:'sendtime',title:'发送时间',width:150,align:'center',formatter: function(value,row,index){
				if(typeof(row.testingtime)!="undefined"){
					return new Date(row.testingtime).format("yyyy-MM-dd hh:mm:ss");
			    }
			}},
		{field:'abnormaltype',title:'故障类型',width:130,align:'center',formatter: function(value,row,index){
				if(row.abnormaltype == 1){
					return "设备故障";
				}else if(row.abnormaltype == 2){
					return "水质异常";
				}
			}},
		{field:'messagedetail',title:'短信内容',width:600,align:'center'}
    ]],
    //fitColumns:true,
    idField:"messageid",
    pagination:true,//显示分页  
    pageSize:10,//分页大小  
    pageList:[10,15,20],//每页的个数  
    toolbar: '#tb',
});


$("#deleteRecord").click(function () {
     var selectIds = []; 
     var rows =  $('#showForm').datagrid('getChecked');
     $.each(rows,function(index,data){
     	selectIds.push(data.id);
     });
     
     var postUrl = getContextPath()+"/message/getmessages.do";
     
     var data = {"selectedIds":selectIds};
     
     $.messager.confirm('消息','确定删除所选选择的管理员?',function(r){
    	if (r){
			     $.ajax({
						type : "POST",
						url : postUrl,
						dataType : "json",
						contentType : "application/json;charset=utf-8",
						data : JSON.stringify(data),
						async : false,
						success : function(returndata) {
							if(returndata.key == "pass"){
								$.messager.confirm("消息","删除成功","Info");
								$("#showForm").datagrid("reload");
							}
						}
				}); 
    		}
	});
});

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>