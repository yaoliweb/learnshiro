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
	<!-- <link rel="stylesheet" type="text/css" href="../demo.css"> -->
	
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui_customize_validation.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />

<body>
<div id="areaForm" class="easyui-panel" title="添加区县" style="width:auto;padding:30px 60px;height:700px">
<form id="area">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <th width="25%">地区名称</th>
							      <td width="75%">
							      	<input id="name" name="name" class="easyui-textbox" data-options="required:true" style="width:50%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">管理员</th>
							      <td width="75%">
							      	<input id="adminId" name="adminId" style="width:50%;" data-options="required:true">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">地区维度</th>
							      <td width="75%">
							      	<input id="coordinatex" name="coordinatex" class="easyui-textbox" data-options="required:true,validType:['mustnumber']" style="width:50%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">地区经度</th>
							      <td width="75%">
							      	<input id="coordinatey" name="coordinatey" class="easyui-textbox" data-options="required:true,validType:['mustnumber']" style="width:50%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">简介</th>
							      <td width="75%">
							      	<textarea id="introduce" name="introduce" rows="15"  style="width:500px;"></textarea>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%"></th>
							      <td width="75%">
										<span>
											<a href="#" class="easyui-linkbutton" style="width:50%;height:30px"  onclick="submitForm();">提交</a>
											<a href="#" class="easyui-linkbutton" style="width:50%;height:30px"  onclick="javascript:history.go(-1)">返回</a>
										</span>							      	
							      </td>
							    </tr>
							  </tbody>
					</table>			 	
			 </div>
        </div>
</form>
</div>
</body>
</html>

<script type="text/javascript">

$('#adminId').combobox({
    url:getContextPath()+'/admin/getalladmins.do',
    valueField:'id',
    textField:'name',
    editable:false
});

function submitForm(){
	if($("#areaForm").form('enableValidation').form('validate') == true){

		var data = serializeForm($("#area"));
		var postdata = JSON.stringify(data);
		var postUrl = getContextPath()+"/areas/addnewarea.do";
		$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:postdata,
		success:function(data){
			if(data.key == "pass"){
				$.messager.alert("提示","你已经添加成功","info");
			}
		}
		
	});
	
	}
}
function serializeForm(form)
{
    var obj = {};
    $.each(form.serializeArray(), function (index)
    {
        if (obj[this['name']])
        {
            obj[this['name']] = obj[this['name']] + ',' + this['value'];
        }
        else
        {
            obj[this['name']] = this['value'];
        }
    }
    );
    return obj;
}
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>