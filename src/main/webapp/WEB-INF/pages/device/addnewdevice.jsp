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
	
	<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
	
	<link rel="stylesheet" href="<%=basePath %>css/jqueryvalidation/screen.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/jquery.validate.js"></script>
	
<body>
<div id="areaForm" class="easyui-panel" title="添加设备" style="width:auto;padding:30px 60px;height:auto">
<form id="submitform">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <th width="25%">所属区县</th>
							      <td width="75%">
							      	<select style="width:200px" name="areaid" id="areaid">
							      			<option value="-1">请选择区县</option>
											<c:forEach items="${allAreas}" var="item">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
									</select>
							      	<select style="width:200px" name="sewageid" id="sewageid">
							      			<option value="">请选择水污水站</option>
									</select>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">编号</th>
							      <td width="75%">
							      	<input id="number" name="number" type="text" style="width:50%"/>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">设备名称</th>
							      <td width="75%">
							      	<input id="devicename" name="devicename" type="text" style="width:50%"/>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">设备参数类型</th>
							      <td width="75%">
							      	<input id="devicetype" name="devicetype" type="text" style="width:50%"/>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">安装时间</th>
							      <td width="75%">
							      	<input id="setuptime" name="setuptime" type="text" style="width:50%" onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">上次保养时间</th>
							      <td width="75%">
							      	<input id="lastrepairtime" name="lastrepairtime" type="text" style="width:50%" onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">保养周期（天）</th>
							      <td width="75%">
							      	<input id="maintaincycleday" name="maintaincycleday" type="text" style="width:50%"/>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">保养周期（年）</th>
							      <td width="75%">
							      	<input id="limityears" name="limityears" type="text" style="width:50%"/>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%"></th>
							      <td width="75%">
										<span>
											<input class="btn" name="seachpoint" id="seachpoint" value="提交" type="submit" style="width:25%;height:30px" />
											<input class="btn" name="returnback" id="returnback" value="返回" type="button" style="width:25%;height:30px" onclick="javascript:history.go(-1)"/>
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
$(document).ready(function(){
	$("#areaid").change(function(){ 
		getSewageByareaId();
	});
	
	$.validator.setDefaults({
		submitHandler: function() {
			var data = serializeForm($("#submitform"));
			delete data["areaid"];
			delete data["seachpoint"];
			var postdata = JSON.stringify(data);
			
			
			var postUrl = getContextPath()+"/device/adddevicedoc.do";
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
			
		},

	});
	
	$("#submitform").validate({
		rules: {
			sewageid:"required",
			number: {
				required:true,
				digits:true
			},
			devicename: "required" ,
			devicetype: {
				required: true
			},
			setuptime: {
				required: true
			},
			lastrepairtime: {
				required: true
			},
			maintaincycleday: {
				required: true,
				digits:true
			},
			limityears: {
				required: true,
				digits:true
			}
		},
		messages: {
			sewageid:"请选择一个地区",
			number: {
				required: "请输入编号",
				digits:"必须输入整数"
			},
			devicename: "请输入设备名称" ,
			devicetype: {
				required: "请输入设备参数类型"
			},
			setuptime: {
				required: "请选择安装时间"
			},
			lastrepairtime: {
				required: "请选择上次保养时间"
			},
			maintaincycleday: {
				required: "请输入保养周期（天）",
				digits:"必须输入整数"
			},
			limityears:{
				required: "请输入保养周期（年）",
				digits:"必须输入整数"
			}
		}
	});	
	
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

function submitForm(){
/* 	if($("#areaForm").form('enableValidation').form('validate') == true){

		var data = serializeForm($("#area"));
		var postdata = JSON.stringify(data);
		
		var postUrl = getContextPath()+"/admin/addnewadmin.do";
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
	
	} */
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