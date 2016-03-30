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
	<!-- <link rel="stylesheet" type="text/css" href="css/table_css.css" /> -->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
	
	
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
	<%-- <script type="text/javascript" src="<%=basePath%>js/monitor/sewagemonitor.js"></script> --%>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	
	<script type="text/javascript" src="<%=basePath%>js/monitor/t.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/monitor/ph.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/monitor/orp.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/monitor/do.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/monitor/cod.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/monitor/an.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/monitor/ftu.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/monitor/tp.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/monitor/rh.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
	<script>
  $(function() {
    $( "#tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
    $( "#tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
  });
  </script>
<!--   <style>
  .ui-tabs-vertical { width: 99%; }
  .ui-tabs-vertical .ui-tabs-nav { padding: .2em .1em .2em .2em; float: left; width: 4em; }
  .ui-tabs-vertical .ui-tabs-nav li { clear: left; width: 100%; border-bottom-width: 1px !important; border-right-width: 0 !important; margin: 0 -1px .2em 0; }
  .ui-tabs-vertical .ui-tabs-nav li a { display:block; }
  .ui-tabs-vertical .ui-tabs-nav li.ui-tabs-active { padding-bottom: 0; padding-right: .1em; border-right-width: 1px; }
  .ui-tabs-vertical .ui-tabs-panel { padding: 1em; float: right; width: 85%;}
  </style> -->
	
<body>
	<div class="result-wrap">
		<div class="config-items">
            <div class="result-content" style="background-image:url('<%=basePath %>images/map.jpg');width:100%;height:404px">
					<table class="search-tab" width="100%">
					  <tbody>
					    <tr>
					      <td width="20%">
					      	<select style="width:200px" name="searchlist_areaid" id="searchlist_areaid">
					      			<option value="-1">请选择区县</option>
								<c:forEach items="${areaList}" var="item">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
					      </td>
					      <td width="20%">
					      	<select style="width:200px" name="searchlist_sewageid" id="searchlist_sewageid">
					      			<option value="-1">请选择水污水站</option>
							</select>
					      
					      </td>
					      <td width="30%">运行状态：<label id="runStateM"></label></td>
					      <td width="30%">最后更新时间：<label id="lastUpdatetime"></label></td>
					    </tr>
					    <tr>
					      <td width="100%" height="30px" colspan="4"><h6  style="text-align:center"><p id="shortTitle"></p>微动力污水处理设施工艺流程图</h6></td>
					    </tr>
					  </tbody>
					</table>
					<div style="float:left;width:45%">
							<table class="search-tab" style="margin:auto;background-color: #2ECCFA">
							  <tbody>
							    <tr>
							      <td width="50%">日处理水量</td>
							      <td width="25%"><label id="dayWater"></label></td>
							      <td width="25%">m3</td>
							    </tr>
							    <tr>
							      <td width="50%">日消减COD量</td>
							      <td width="25%"><label id="dayCod"></label></td>
							      <td width="25%">g</td>
							    </tr>
							    <tr>
							      <td width="50%">日消减NH3-N量</td>
							      <td width="25%"><label id="dayNh3n"></label></td>
							      <td width="25%">g</td>
							    </tr>
							    <tr>
							      <td width="50%">日消减总P量</td>
							      <td width="30%"><label id="dayP"></label></td>
							      <td width="25%">g</td>
							    </tr>
							  </tbody>
							</table>
						</div>
						
						<div style="float:left">
							<table class="search-tab" style="margin:auto;background-color: #2ECCFA">
							  <tbody>
							    <tr>
							      <td width="13%">T</td>
							      <td width="24%"><label id="detection1"></label></td>
							      <td width="13%">&#8451;</td>
							      
							      <td width="13%">AN</td>
							      <td width="22%"><label id="detection11"></label></td>
							      <td width="15%">mg/L</td>
							    </tr>
							  </tbody>
							  <tbody>
							    <tr>
							      <td width="">PH</td>
							      <td width=""><label id="detection2"></label></td>
							      <td width=""></td>
							      
							      <td width="">FTU</td>
							      <td width=""><label id="detection12"></label></td>
							      <td width="">FTU</td>
							    </tr>
							  </tbody>

							  <tbody>
							    <tr>
							      <td width="">ORP</td>
							      <td width=""><label id="detection3"></label></td>
							      <td width="">mV</td>
							      
							      <td width="">TP</td>
							      <td width=""><label id="detection13"></label></td>
							      <td width="">mg/L</td>
							    </tr>
							  </tbody>
							  <tbody>
							    <tr>
							      <td width="">DO</td>
							      <td width=""><label id="detection5"></label></td>
							      <td width="">mg/L</td>
							      
							      <td width="">RH</td>
							      <td width=""><label id="detection14"></label></td>
							      <td width=""></td>
							    </tr>
							  </tbody>
							  <tbody>
							    <tr>
							      <td width="">COD</td>
							      <td width=""><label id="detection10"></label></td>
							      <td width="">mg/L</td>
							    </tr>
							  </tbody>
							</table>
						</div>
			</div>
		</div>			
	</div>

<div id="tabs">
  <ul>
    <li><a href="#tabs-1">T</a></li>
    <li><a href="#tabs-2">PH</a></li>
    <li><a href="#tabs-3">ORP</a></li>
    <li><a href="#tabs-5">DO</a></li>
    <li><a href="#tabs-10">COD</a></li>
    <li><a href="#tabs-11">AN</a></li>
    <li><a href="#tabs-12">FTU</a></li>
    <li><a href="#tabs-13">TP</a></li>
    <li><a href="#tabs-14">RH</a></li>
<!--     <li><a href="#tabs-5">COD</a></li>
    <li><a href="#tabs-6">AN</a></li>
    <li><a href="#tabs-7">FTU</a></li>
    <li><a href="#tabs-8">TP</a></li>
	<li><a href="#tabs-9">RH</a></li>
	<li><a href="#tabs-10">Flow</a></li> -->
    
  </ul>
  <div id="tabs-1">
	<div id="chart1" style="float:left;height:300px;width:1000px">
	</div>  
  </div>
  <div id="tabs-2">
	<div id="chart2" style="float:left;height:300px;width:1000px">
	</div>  
  </div>
  <div id="tabs-3">
	<div id="chart3" style="float:left;height:300px;width:1000px">
	</div>  
  </div>
  <div id="tabs-5">
	<div id="chart5" style="float:left;height:300px;width:1000px">
	</div>  
  </div>
  <div id="tabs-10">
	<div id="chart10" style="float:left;height:300px;width:1000px">
	</div>  
  </div>
  <div id="tabs-11">
	<div id="chart11" style="float:left;height:300px;width:1000px">
	</div>  
  </div>
  <div id="tabs-12">
	<div id="chart12" style="float:left;height:300px;width:1000px">
	</div>  
  </div>
  <div id="tabs-13">
	<div id="chart13" style="float:left;height:300px;width:1000px">
	</div>  
  </div>
  <div id="tabs-14">
	<div id="chart14" style="float:left;height:300px;width:1000px">
	</div>  
  </div>


</div>
 	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
 	<%-- <script src="<%=basePath %>js/echarts/dist/echarts.js"></script> --%>
 	<script type="text/javascript">
//横轴坐标
window.xtokes = [];
//温度
window.mychart1;
//ph
window.mychart2;
//orp
window.mychart3;
//do
window.mychart5;


//flow
window.mychart10;
//flow
window.mychart11;

window.mychart12;
window.mychart13;
window.mychart14;



$(document).ready(function(){
	$("#searchlist_areaid").change(function(){ 
		getSewageByareaId();
	});
	
	$("#searchlist_sewageid").change(function(){
		getSewageRunInfoBySewageId();
		//ajaxGet5Info();
	});
	initxtoken();
	setLabels();
	chart1();
	chart2();
	chart3();
	chart5();
	chart10();
	chart11();
	chart12();
	chart13();
	chart14();	
});


//获取运行信息
function getSewageRunInfoBySewageId(){
	var areaId = $("#searchlist_areaid").val();
	var sewageId = $("#searchlist_sewageid").val();
	
	if(areaId == -1 && sewageId == -1){
		return;
	}	
	var postUrl = getContextPath() + "/monitor/ajaxgetsewageruninfobysewageid.do";
	var postdata = {"sewageid":sewageId};
	
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(postdata),
		success:function(data){
			var returndata = data;
			$("label").each(function(){
				//
    			var idname = $(this).attr("id");
    			if(idname == 'lastUpdatetime'){
    				var lastUpdatetimeValue = eval("returndata."+idname);
    				if(typeof lastUpdatetimeValue === 'undefined'){
    					$("#"+idname).html(eval("returndata.runStateM"));
    				}else{
    					$("#"+idname).html(new Date(eval("returndata."+idname)).format("yyyy-MM-dd hh:mm:ss"));
    				}
    			}else{
    				$("#"+idname).html(eval("returndata."+idname));
    			}
    			
			});
		}
		
	});
	
	//启动是获取折线图
	//设置
	ajaxGet5Info();
}

//获取站点信息列表
function getSewageByareaId(){
	var areaId = $("#searchlist_areaid").val();
	var postUrl = getContextPath()+"/monitor/ajaxgetsewagebyareaid.do";
	var data = {"areaid":areaId};
	
	$("#searchlist_sewageid").empty();

  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
			$("#searchlist_sewageid").append("<option value='-1'>请选择水污水站</option>");
	        $.each(data,function(key,item){
	        	var key = item.sewageid;
	        	var value= item.name;
	        	$("#searchlist_sewageid").append("<option value='"+key+"'>"+value+"</option>");
	        });			
		}
	});
}

function ajaxGet5Info(){
	//var temp = [];
	var sewageid = $("#searchlist_sewageid").val();
	var postUrl = getContextPath()+"/monitor/ajaxget5info.do";
	var data = {"sewageid":sewageid};
	
  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
            var now = new Date();
            var len = now.getHours();
            var flag = false;
            
            
            //数据库中 00:00到00.59点表示1点的平均数据，一次类推
            for(var i = 1;i<= len;i++){
            	flag = false;
            	for(var j = 0; j < data.length; j++){
            		if(i == (data[j].hourname + 1)){ //时间是否相等
            			var hourname = data[j].hourname+1;
			        	var hourcount = data[j].hourcount;
		            	var de1 = data[j].detection1/hourcount;
			        	var de2 = data[j].detection2/hourcount;
			        	var de3 = data[j].detection3/hourcount;
			        	
			        	var de5 = data[j].detection5/hourcount;
			        	var de10 = data[j].detection10/hourcount;
			        	var de11 = data[j].detection11/hourcount;
			        	
			        	var de12 = data[j].detection12/hourcount;
			        	var de13 = data[j].detection13/hourcount;
			        	var de14 = data[j].detection14/hourcount;

					    mychart1.addData([
					        [
					            0,        // 系列索引
					            de1, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart2.addData([
					        [
					            0,        // 系列索引
					            de2, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart3.addData([
					        [
					            0,        // 系列索引
					            de3, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart5.addData([
					        [
					            0,        // 系列索引
					            de5, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart10.addData([
					        [
					            0,        // 系列索引
					            de10, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);	
					    mychart11.addData([
					        [
					            0,        // 系列索引
					            de11, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart12.addData([
					        [
					            0,        // 系列索引
					            de12, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart13.addData([
					        [
					            0,        // 系列索引
					            de13, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart14.addData([
					        [
					            0,        // 系列索引
					            de14, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    
					    flag = true;
					    break;
            		}
            	}
            	if(flag == false){
	 	         	mychart1.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart2.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]); 
	 	         	mychart3.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart5.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart10.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
				    mychart11.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
				    mychart12.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
				    mychart13.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
				    mychart14.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
            	}

            } 	        		
		}
	});
}


//将所有label标签全部设置成未选择
function setLabels(){
	$("label").html("0");
	$("label[id='runStateM']").html("未选择");
	$("label[id='lastUpdatetime']").html("未选择");
}

function initxtoken(){
     var now = new Date();
     var res = [];
     var len = now.getHours();
     while (len > 0) {
       	var hours = now.getHours();       //获取当前小时数(0-23)
           res.unshift(hours);
           now.setHours(now.getHours()-1);
           len = len - 1 ;
     }
     return res
}
function initxdata(){
   	var arr = [];
   	var now = new Date();
   	var len = now.getHours();
   	for(var i = 0;i<len ; i++){
   		arr.push("-");
   	}
  	return arr;
}
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

/* timeTicket = setInterval(function (){
	var areaId = $("#searchlist_areaid").val();
	var sewageid = $("#searchlist_sewageid").val();
	
	if(areaId != -1 && sewageid != -1){
		var postUrl = getContextPath()+"/monitor/ajaxgetlatestdetectiondata.do";
		var data = {"sewageid":areaId};
		
	  	$.ajax({
			type:"POST",
			url:postUrl,
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data),
			async:true,
			success:function(data){

			}
		});
	}
	}, 10000);//设置成10秒执行一次， */
    </script> 
</body>
</html>