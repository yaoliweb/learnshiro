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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />	
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.datagrid.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.linkbutton.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.panel.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.resizable.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.form.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui_customize_validation.js"></script>
	

<body>
<div id="userForm" class="easyui-panel" title="更新密码" style="width:100%;padding:30px 60px;">
<form id="users">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <th width="25%">原密码:</th>
							      <td width="75%">
							      	<input id="loginpwd" type="password" data-options="required:true,validType:['checkpwdright','length[4,11]']"  class="easyui-textbox" style="width:50%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">输入新密码:</th>
							      <td width="75%">
							      	<input id="loginuserpwd" name="loginuserpwd" type="password" data-options="required:true,validType:['length[4,11]']"  class="easyui-textbox" style="width:50%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">确认新密码:</th>
							      <td width="75%">
							      	<input id="reloginuserpwd" type="password" data-options="required:true,validType:['length[4,11]','equaltopwd']" class="easyui-textbox" style="width:50%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%"></th>
							      <td width="75%">
									<span>
										<a href="#" class="easyui-linkbutton" style="width:40%;height:30px"  onclick="submitForm();">提交</a>
										<a href="#" class="easyui-linkbutton" style="width:40%;height:30px"  onclick="javascript:history.go(-1)">返回</a>
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
function submitForm(){
	if($("#userForm").form('enableValidation').form('validate') == true){
		var data = serializeForm($("#users"));
		var postdata = JSON.stringify(data);
		
		var postUrl = getContextPath()+"/users/updatepassword.do";
		$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:postdata,
		success:function(data){
			if(data.key == "pass"){
				$.messager.alert("提示","修改成功","info");
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