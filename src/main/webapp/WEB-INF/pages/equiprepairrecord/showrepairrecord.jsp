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
	<table id="showForm" class="easyui-datagrid"  title="保养单列表" style="width:100%;height:420px">
	
	</table>
	
	<div id="tb" style="padding:5px;height:auto;display:none">
		<div>
	      	<select style="width:200px" name="areaid" id="areaid">
	      			<option value="-1">请选择区县</option>
					<c:forEach items="${allAreas}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
			</select>
	      	<select style="width:200px" name="sewageid" id="sewageid">
	      			<option value="">请选择水污水站</option>
			</select>
			
			<a id="searchbtn" href="#" class="easyui-linkbutton">查找</a>
			
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$(document).ready(function(){
	$("#areaid").change(function(){ 
		getSewageByareaId();
	});
	
	$("#showForm").datagrid({"onLoadSuccess":function(data){
            if (data.total == 0) {
                //添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                $("#showForm").datagrid('appendRow', { sewageName:'<div style="text-align:center;color:red">没有相关记录！</div>' });
                $("#showForm").datagrid('mergeCells', { index: 0, field: 'sewageName', colspan:6});
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else $("#showForm").closest('div.datagrid-wrap').find('div.datagrid-pager').show();
	}});
	
	
});

$('#showForm').datagrid({  
    //url:getContextPath()+'/warming/getequipmentabnormal.do',
    columns:[[
		{field:'sewagename',title:'所属污水站点',width:130,align:'center'},
		{field:'devicename',title:'设备名称',width:130,align:'center'},
		{field:'time',title:'日期',width:130,align:'center',formatter: function(value,row,index){
				if(typeof(row.time)!="undefined"){
					return new Date(row.time).format("yyyy-MM-dd");
			    }
			}},
		{field:'consumetime',title:'保养耗时',width:130,align:'center'},
		{field:'repairreason',title:'保养原因',width:130,align:'center'},
		{field:'repaircontent',title:'保养内容',width:130,align:'center'},
		{field:'consumematerial',title:'消耗材料',width:130,align:'center'},
		{field:'repairman',title:'维修人员',width:130,align:'center'},
		{field:'completetime',title:'完成时间',width:130,align:'center',formatter: function(value,row,index){
				if(typeof(row.time)!="undefined"){
					return new Date(row.time).format("yyyy-MM-dd");
			    }
			}}
    ]],
    fitColumns:true,
    idField:"id",
    pagination:true,//显示分页  
    pageSize:10,//分页大小  
    pageList:[10,15,20],//每页的个数  
    toolbar: '#tb',
});



function getSewageByareaId(){
	var areaId = $("#areaid").val();
	var postUrl = getContextPath()+"/monitor/ajaxgetsewagebyareaid.do";
	var data = {"areaid":areaId};
	
	$("#sewageid").empty();

  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
			$("#sewageid").append("<option value=''>请选择水污水站</option>");
	        $.each(data,function(key,item){
	        	var key = item.sewageid;
	        	var value= item.name;
	        	$("#sewageid").append("<option value='"+key+"'>"+value+"</option>");
	        });			
		}
	});
}

$("#searchbtn").click(function () {
/* 	if($("#sewageid").val() == -1 || $("#begintime").val() == "" || $("#endtime").val() == ""){
		$.messager.confirm('信息','请您输入查询条件','info');
		return ;
	} */

	var url = getContextPath()+"/equiprepairrecord/getrepairedrecord.do";
	
	$("#showForm").datagrid({
		"url":url,
		"queryParams":{
			sewageid:$("#sewageid").val(),
		}	
	});
	$("#showForm").datagrid("load");
});


/*以下高度高度自适应*/
$('#showForm').datagrid('resize', {
	//width:function(){return document.body.clientWidth;},
	//height:function(){return document.body.clientHeight;},
});

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>