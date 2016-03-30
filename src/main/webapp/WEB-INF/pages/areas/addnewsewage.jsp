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
<div id="submitForm" class="easyui-panel" title="添加污水站点" style="width:auto;padding:30px 60px;height:100%">
<form id="submit">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
<!-- 							    <tr>
							      <th width="25%">上级地区</th>
							      <td width="75%">
							      	<input id="loginusername" name="loginusername" class="easyui-textbox" data-options="required:true,validType:['mustnumber']" style="width:50%;height:30px">
							      </td>
							    </tr> -->
							    <tr>
							      <th width="20%">所属地区</th>
							      <td width="30%">
							      	<input id="areaid" name="areaid" style="width:100%;" data-options="required:true">
							      </td>
							      <!-- 已经在区县配置中设置 -->
							      <th width="20%"><!-- 管理员 --></th>
							      <td width="30%">
							      	<!-- 数据库乱了 -->
							      	<!-- <input id="adminid" name="adminid" style="width:100%;" data-options="required:true"> -->
							      	<!-- <input id="administratorid" name="administratorid" style="width:100%;" value="1" data-options="required:true">顾杨</input> -->
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">控制方式</th> 
							      <td width="30%">
							      	<select id="controlmethod" name="controlmethod" class="easyui-combobox" data-options="editable:false"  style="width:100%;">
									    <option value="1">一般控制</option>
									    <option value="2">无锡信捷XC1-16 PLC</option>
									    <option value="3">台达DVP PL</option>
									</select>
							      </td>
							     
							     
							      <th width="20%">控制ID</th>
							      <td width="30%">
							      	<input id="controlid" name="controlid" class="easyui-textbox" style="width:100%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">简称</th>
							      <td width="30%">
							      	<input id="shortTitle" name="shortTitle" class="easyui-textbox" data-options="required:true" style="width:100%;height:30px">
							      </td>
							      <th width="20%">名称</th>
							      <td width="30%">
							      	<input id="name" name="name" class="easyui-textbox" data-options="required:true" style="width:100%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">运营编号</th>
							      <td width="30%">
							      	<input id="operationnum" name="operationnum" class="easyui-textbox" data-options="required:true" style="width:100%;height:30px">
							      </td>
							      <th width="20%">地址</th>
							      <td width="30%">
							      	<input id="address" name="address" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">经度</th>
							      <td width="30%">
							      	<input id="coordinatey" name="coordinatey" class="easyui-textbox" data-options="required:true,validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">纬度</th>
							      <td width="30%">
							      	<input id="coordinatex" name="coordinatex" class="easyui-textbox" data-options="required:true,validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>



							    <tr>
							      <th width="20%">T(温度)上限</th>
							      <td width="30%">
							      	<input id="detection1ul" name="detection1ul" class="easyui-textbox" data-options="validType:['mustnumber']"  style="width:100%;height:30px">
							      </td>
							      <th width="20%">T(温度)下限</th>
							      <td width="30%">
							      	<input id="detection1dl" name="detection1dl" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">PH上限</th>
							      <td width="30%">
							      	<input id="detection2ul" name="detection2ul" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">PH下限</th>
							      <td width="30%">
							      	<input id="detection2dl" name="detection2dl" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">ORP上限</th>
							      <td width="30%">
							      	<input id="detection3ul" name="detection3ul" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">ORP下限</th>
							      <td width="30%">
							      	<input id="detection3dl" name="detection3dl" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>
<!-- 							    <tr>
							      <th width="20%">LS上限</th>
							      <td width="30%">
							      	<input id="detection4ul" name="detection4ul" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							      <th width="20%">LS下限</th>
							      <td width="30%">
							      	<input id="detection4dl" name="detection4dl" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							    </tr> -->
							    <tr>
							      <th width="20%">DO上限</th>
							      <td width="30%">
							      	<input id="detection5ul" name="detection5ul" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">DO下限</th>
							      <td width="30%">
							      	<input id="detection5dl" name="detection5dl" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>

<!-- 							    <tr>
							      <th width="20%">流量上限</th>
							      <td width="30%">
							      	<input id="detection5ul" name="detection5ul" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">流量下限</th>
							      <td width="30%">
							      	<input id="detection5dl" name="detection5dl" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr> -->

<!-- 							    <tr>
							      <th width="20%">液位计2上限</th>
							      <td width="30%">
							      	<input id="detection5ul" name="detection5ul" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							      <th width="20%">液位计2下限</th>
							      <td width="30%">
							      	<input id="detection5dl" name="detection5dl" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							    </tr> -->
							    
<!-- 							    <tr>
							      <th width="20%">瞬时流量(actureFlow)上限</th>
							      <td width="30%">
							      	<input id="detection5ul" name="detection5ul" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							      <th width="20%">瞬时流量(actureFlow)下限</th>
							      <td width="30%">
							      	<input id="detection5dl" name="detection5dl" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							    </tr> -->
							    
<!-- 							    <tr>
							      <th width="20%">总流量(sumFlow)上限</th>
							      <td width="30%">
							      	<input id="detection5ul" name="detection5ul" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							      <th width="20%">总流量(sumFlow)下限</th>
							      <td width="30%">
							      	<input id="detection5dl" name="detection5dl" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							    </tr> -->
							    
							    <tr>
							      <th width="20%">COD(化学需氧量)上限</th>
							      <td width="30%">
							      	<input id="detection10ul" name="detection10ul" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">COD(化学需氧量)下限</th>
							      <td width="30%">
							      	<input id="detection10dl" name="detection10dl" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>

							    <tr>
							      <th width="20%">AN(氨氮)上限</th>
							      <td width="30%">
							      	<input id="detection11ul" name="detection11ul" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">AN(氨氮)下限</th>
							      <td width="30%">
							      	<input id="detection11dl" name="detection11dl" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>
							    
							    <tr>
							      <th width="20%">FTU(浊度)上限</th>
							      <td width="30%">
							      	<input id="detection12ul" name="detection12ul" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">FTU(浊度)下限</th>
							      <td width="30%">
							      	<input id="detection12dl" name="detection12dl" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>
							    
							    <tr>
							      <th width="20%">TP(总磷)上限</th>
							      <td width="30%">
							      	<input id="detection13ul" name="detection13ul" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">TP(总磷)下限</th>
							      <td width="30%">
							      	<input id="detection13dl" name="detection13dl" class="easyui-textbox" data-options="validType:['mustnumber']"  style="width:100%;height:30px">
							      </td>
							    </tr>
							    
							    <tr>
							      <th width="20%">RH上限</th>
							      <td width="30%">
							      	<input id="detection14ul" name="detection14ul" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">RH下限</th>
							      <td width="30%">
							      	<input id="detection14dl" name="detection14dl" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>



							    <tr>
							      <th width="20%">风机运行时间设置</th>
							      <td width="30%">
							      	<input id="runtimeperiod1" name="runtimeperiod1" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">风机停止时间设置</th>
							      <td width="30%">
							      	<input id="stoptimeperiod1" name="stoptimeperiod1" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>
							    
							    <tr>
							      <th width="20%">混合液回流泵运行时间设置</th>
							      <td width="30%">
							      	<input id="runtimeperiod2" name="runtimeperiod2" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							      <th width="20%">混合液回流泵停止时间设置</th>
							      <td width="30%">
							      	<input id="stoptimeperiod2" name="stoptimeperiod2" class="easyui-textbox" data-options="validType:['mustnumber']" style="width:100%;height:30px">
							      </td>
							    </tr>
							    							    
							    <tr>
							      <th width="20%">污泥回流泵运行时间设置</th>
							      <td width="30%">
							      	<input id="runtimeperiod3" name="runtimeperiod3" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							      <th width="20%">污泥回流泵停止时间设置</th>
							      <td width="30%">
							      	<input id="stoptimeperiod3" name="stoptimeperiod3" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							    </tr>
							    
							    <tr>
							      <th width="20%">电磁阀1运行时间设置</th>
							      <td width="30%">
							      	<input id="runtimeperiod4" name="runtimeperiod4" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							      <th width="20%">电磁阀1停止时间设置</th>
							      <td width="30%">
							      	<input id="stoptimeperiod4" name="stoptimeperiod4" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							    </tr>
							    
							    <tr>
							      <th width="20%">电磁阀2运行时间设置</th>
							      <td width="30%">
							      	<input id="runtimeperiod5" name="runtimeperiod5" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							      <th width="20%">电磁阀2停止时间设置</th>
							      <td width="30%">
							      	<input id="stoptimeperiod5" name="stoptimeperiod5" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							    </tr>
							    
							 	<tr>
							      <th width="20%">电磁阀3运行时间设置</th>
							      <td width="30%">
							      	<input id="runtimeperiod6" name="runtimeperiod6" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							      <th width="20%">电磁阀3停止时间设置</th>
							      <td width="30%">
							      	<input id="stoptimeperiod6" name="stoptimeperiod6" class="easyui-textbox"  style="width:100%;height:30px">
							      </td>
							    </tr>
							    
							    <tr>
							      <th width="20%"></th>
							      <td width="70%" colspan="3">
										<span>
										    <a href="#" class="easyui-linkbutton" style="width:200px;height:30px"  onclick="submitForm();">提交</a>
											<a href="#" class="easyui-linkbutton" style="width:200px;height:30px"  onclick="javascript:history.go(-1)">返回</a>
										 </span>	
							      </td>
							  </tbody>
					</table>			 	
			 </div>
        </div>



		
</form>
</div>
</body>
</html>

<script type="text/javascript">
$('#areaid').combobox({
    url:getContextPath()+'/areas/getallareas.do',
    valueField:'id',
    textField:'name',
    editable:false
});

function submitForm(){
	if($("#submitForm").form('enableValidation').form('validate') == true){
		var data = serializeForm($("#submit"));
		var postdata = JSON.stringify(data);
		
		var postUrl = getContextPath()+"/monitor/addnewsewage.do";
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