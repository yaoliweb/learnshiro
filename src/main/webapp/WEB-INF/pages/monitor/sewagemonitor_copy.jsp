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
	
	<!-- <link rel="stylesheet" type="text/css" href="css/table_css.css" /> -->
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
	
	
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/monitor/sewagemonitor.js"></script>
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
					      <td width="30%">最后跟新时间：????</td>
					    </tr>
					    <tr>
					      <td width="100%" colspan="4"><label id="shortTitle"></label>微动力污水处理设施工艺流程图</td>
					    </tr>
					  </tbody>
					</table>
					<div style="float:left;width:50%">
							<table class="search-tab" style="margin:auto">
							  <tbody>
							    <tr>
							      <td width="50%">日处理水量</td>
							      <td width="40%"><label id="dayWater"></label></td>
							      <td width="10%">m3</td>
							    </tr>
							    <tr>
							      <td width="50%">日消减COD量</td>
							      <td width="40%"><label id="dayCod"></label></td>
							      <td width="10%">g</td>
							    </tr>
							    <tr>
							      <td width="50%">日消减NH3-N量</td>
							      <td width="40%"><label id="dayNh3n"></label></td>
							      <td width="10%">g</td>
							    </tr>
							    <tr>
							      <td width="50%">日消减总P量</td>
							      <td width="40%"><label id="dayP"></label></td>
							      <td width="10%">g</td>
							    </tr>
							  </tbody>
							</table>
						</div>
						
						<div style="float:left">
							<table class="search-tab" >
							  <tbody>
							    <tr>
							      <td width="35%">T</td>
							      <td width="45%"><label id="gaugeTempNowValue"></label></td>
							      <td width="20%">m3</td>
							    </tr>
							  </tbody>
							  <tbody>
							    <tr>
							      <td width="35%">PH</td>
							      <td width="45%"><label id="gaugePhNowValue"></label></td>
							      <td width="20%">m3</td>
							    </tr>
							  </tbody>

							  <tbody>
							    <tr>
							      <td width="35%">ORP</td>
							      <td width="45%"><label id="gaugeOrpNowValue"></label></td>
							      <td width="20%">m3</td>
							    </tr>
							  </tbody>
							  <tbody>
							    <tr>
							      <td width="35%">LS</td>
							      <td width="45%"><label id="gaugeLsNowValue"></label></td>
							      <td width="20%">m3</td>
							    </tr>
							  </tbody>
							  <tbody>
							    <tr>
							      <td width="35%">DO</td>
							      <td width="45%"><label id="gaugeDoNowValue"></label></td>
							      <td width="20%">mg/L</td>
							    </tr>
							  </tbody>
							</table>
						</div>
			</div>
		</div>			
		
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <td width="25%">人工格栅</td>
							      <td width="25%">上次确认格栅时间</td>
							      <td width="25%">当前状态</td>
							      <td width="25%">格栅间隔天数</td>
							    </tr>
							    <tr>
							      <td width="25%">?????</td>
							      <td width="25%">未选择</td>
							      <td width="25%"><label id="geShanState"></label></td>
							      <td width="25%"><label id="gratingdays"></label></td>
							    </tr>
							  </tbody>
					</table>			 	
			 </div>
        </div>
                
 		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <td width="25%">设备</td>
							      <td width="25%">设备当前控制方式</td>
							      <td width="25%">设备当前状态</td>
							      <td width="25%">设备报警操作</td>
							    </tr>
							    <tr>
							      <td width="25%"><label id="equipment1Name"></label></td>
							      <td width="25%"><label id="equipment1ControlStrategy"></label></td>
							      <td width="25%"><label id="equipment1NowState"></label></td>
							      <td width="25%">？？？？</td>
							    </tr>
							    <tr>
							      <td width="25%"><label id="equipment2Name"></label></td>
							      <td width="25%"><label id="equipment2ControlStrategy"></label></td>
							      <td width="25%"><label id="equipment2NowState"></label></td>
							      <td width="25%">？？？？</td>
							    </tr>
							    <tr>
							      <td width="25%"><label id="equipment3Name"></label></td>
							      <td width="25%"><label id="equipment3ControlStrategy"></label></td>
							      <td width="25%"><label id="equipment3NowState"></label></td>
							      <td width="25%">？？？？</td>
							    </tr>
							  </tbody>
					</table>				 	
			 </div>
        </div>
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <td width="25%">仪表传感器</td>
							      <td width="25%">仪表取值范围</td>
							      <td width="25%">仪表当前值</td>
							      <td width="25%">解除报警操作</td>
							    </tr>
							    <tr>
							      <td width="25%">T</td>
							      <td width="25%"><label id="gaugeTempRange"></label></td>
							      <td width="25%"><label id="detection1"></label></td>
							      <td width="25%">？？？？</td>
							    </tr>
							    <tr>
							      <td width="25%">PH(pH)</td>
							      <td width="25%"><label id="gaugePhRange"></label></td>
							      <td width="25%"><label id="detection2"></label></td>
							      <td width="25%">？？？？</td>
							    </tr>
							    <tr>
							      <td width="25%">ORP(mV)</td>
							      <td width="25%"><label id="gaugeOrpRange"></label></td>
							      <td width="25%"><label id="detection3"></label></td>
							      <td width="25%">？？？？</td>
							    </tr>
							    <tr>
							      <td width="25%">LS</td>
							      <td width="25%"><label id="gaugeLsRange"></label></td>
							      <td width="25%"><label id="detection4"></label></td>
							      <td width="25%">？？？？</td>
							    </tr>
							    <tr>
							      <td width="25%">DO(mg/L)</td>
							      <td width="25%"><label id="gaugeDoRange"></label></td>
							      <td width="25%"><label id="detection5"></label></td>
							      <td width="25%">？？？？</td>
							    </tr>
							    <tr>
							      <td width="25%">流量</td>
							      <td width="25%"><label id="gaugeFlowRange"></label></td>
							      <td width="25%"><label id="detection6"></label></td>
							      <td width="25%">？？？？</td>
							    </tr>
							  </tbody>
					</table>				 	
			 </div>
        </div>
	</div>
	
	
	
	
	
	
	
<!-- <input type="button" name="show" id="show" value="增加数据"></input> -->

	<div id="chart1" style="float:left;height:300px;width:900px">
	
	</div>
	<div id="chart2" style="float:left;height:300px;width:900px">
	
	</div>
	<div id="chart3" style="float:left;height:300px;width:900px">
	
	</div>


	<div class="result-wrap">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <td width="25%">曝气机</td>
							      <td width="25%">污水泵</td>
							      <td width="25%">回流泵</td>
							      <td width="25%">？？？</td>
							    </tr>
							    <tr>
							      <td width="25%">日处理水量</td>
							      <td width="25%"></td>
							      <td width="25%">m3</td>
							      <td width="25%">m3</td>
							    </tr>
							    <tr>
							      <th width="50%" colspan="2"><input class="btn" name="seachpoint" id="seachpoint" value="提交" type="button"></th>
							      <td width="50%" colspan="2"></td>
							    </tr>
							  </tbody>
					</table>			 	
			 </div>
        </div>
	</div>



 	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
 	<%-- <script src="<%=basePath %>js/echarts/dist/echarts.js"></script> --%>
 	<script type="text/javascript">
window.mychart1;

window.mychart2;

window.mychart3;

$(document).ready(function(){
	$("#searchlist_areaid").change(function(){ 
		getSewageByareaId();
	});
	
	$("#searchlist_sewageid").change(function(){
		getSewageRunInfoBySewageId();
	});
	setLabels();
	chart1();
	chart2();
	chart3();
});

//将所有label标签全部设置成未选择
function setLabels(){
	$("label").html("未选择");
}

//获取运行信息
function getSewageRunInfoBySewageId(){
	var areaId = $("#searchlist_areaid").val();
	var sewageId = $("#searchlist_sewageid").val();
	
	if(areaId == -1 && sewageId == -1){
		return;
	}	
	var postUrl = getContextPath() + "/monitor/ajaxgetsewageruninfobysewageid.do";
	var postdata = {"sewageId":sewageId};
	
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(postdata),
		success:function(data){
			var returndata = data;
			$("label").each(function(){
    			var idname = $(this).attr("id");
    			$("#"+idname).html(eval("returndata."+idname));
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




function chart1(){
	 	//var myChart;
	 	//var mychart1;
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        // 使用
        	require(
            [
                'echarts',
                //'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                mychart1 = ec.init(document.getElementById('chart1')); 
                
                var option = {
								    title : {
								        text: 'T、PH实时曲线图（每分钟更新）'//,
								        //subtext: '纯属虚构'
								    },
								    legend: {
								        data:['T','PH']
								    },
								    //calculable : true,
								    xAxis : [
								        {
								        	//xAxisIndex 为0 
								            type : 'category',
								            boundaryGap : false,
								            data :(function (){
								                var now = new Date();
								                var res = [];
								                var len = 5;
								                while (len--) {
								                	var hours = now.getHours();       //获取当前小时数(0-23)
													var mins = now.getMinutes(); 
								                    //res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
								                    res.unshift(hours+":"+mins);
								                    now = new Date(now - 60000);
								                }
								                return res;								            	
								            })()
								        }
								        
								    ],
								    yAxis : [
								        {
								         	//yAxisIndex=0
								            type : 'value',
								            scale: true,
								            name : 'T',
								            boundaryGap: [0.2, 0.2],
								            min:10,
								            max:40,
								            splitNumber:10
								        },
								        {
								        	//yAxisIndex=1
								            type : 'value',
								            scale: true,
								            name : 'PH',
								            boundaryGap: [0.2, 0.2],
								            min:6,
								            max:11,
								            splitNumber:5
								        }
								    ],
								    series : [
								        {
								            name:'T',
								            type:'line',
								            xAxisIndex: 0,//非常重要
								            yAxisIndex: 0,
 								            data:(function(){
 								            	var arr = [];
								            	arr.push("-");
								            	arr.push("-");
								            	arr.push("-");
								            	arr.push("-");
								            	arr.push("-");
								            	return arr;
												//return ajaxGet5Temp();
								            })()
								        },
								        {
								            name:'PH',
								            type:'line',
								            xAxisIndex: 0,//非常重要
								            yAxisIndex: 1,
 								            data:(function(){
								            	var arr = [];
								            	arr.push("-");
								            	arr.push("-");
								            	arr.push("-");
								            	arr.push("-");
								            	arr.push("-");
								            	return arr;
								            })() 
								        }
								    ]
						};
                // 为echarts对象加载数据 
                mychart1.setOption(option); 
            }
        );//end require
        
/* 	var lastData;
	var axisData;
	timeTicket = setInterval(function (){

		var areaId = $("#searchlist_areaid").val();
		var sewageid = $("#searchlist_sewageid").val();
		
		if(areaId != -1 && sewageid != -1){
			var postUrl = getContextPath()+"/monitor/ajaxgetlatestdetectiondata.do";
			var data = {"sewageId":areaId};
			
		  	$.ajax({
				type:"POST",
				url:postUrl,
				dataType:"json",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify(data),
				async:true,
				success:function(data){
		    	// 动态数据接口 addData
		    		var de1 = data.detection1;
		    		var de2 = data.detection2;
		    		var now = new Date();
		    		var hours = now.getHours();
		    		var mins = now.getMinutes();
		    		axisData = hours+":"+mins;
				    mychart1.addData([
					        [
					            0,        // 系列索引
					            de1, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ],
					        [
					            1,        // 系列索引
					            de2, // 新增数据
					            false,    // 新增数据是否从队列头部插入
					            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					            axisData  // 坐标轴标签
					        ]
				    ]);
				}
			});
		}
		}, 10000);//设置成10秒执行一次， */
}

	var lastData;
	var axisData;
	timeTicket = setInterval(function (){

		var areaId = $("#searchlist_areaid").val();
		var sewageid = $("#searchlist_sewageid").val();
		
		if(areaId != -1 && sewageid != -1){
			var postUrl = getContextPath()+"/monitor/ajaxgetlatestdetectiondata.do";
			var data = {"sewageId":areaId};
			
		  	$.ajax({
				type:"POST",
				url:postUrl,
				dataType:"json",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify(data),
				async:true,
				success:function(data){
		    	// 动态数据接口 addData
		    		var de1 = data.detection1;
		    		var de2 = data.detection2;
		    		var de3 = data.detection3;
		    		var de4 = data.detection4;
		    		var de5 = data.detection5;
		    		var now = new Date();
		    		var hours = now.getHours();
		    		var mins = now.getMinutes();
		    		axisData = hours+":"+mins;
				    mychart1.addData([
					        [
					            0,        // 系列索引
					            de1, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ],
					        [
					            1,        // 系列索引
					            de2, // 新增数据
					            false,    // 新增数据是否从队列头部插入
					            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					            axisData  // 坐标轴标签
					        ]
				    ]);
					mychart2.addData([
					        [
					            0,        // 系列索引
					            de4, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false,     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					            axisData
					        ],
				        	[
					            1,        // 系列索引
					            de5, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false,     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					            axisData
					        ]
				    ]);
					mychart3.addData([
					        [
					            0,        // 系列索引
					            de3, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false,     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					            axisData
					        ]
				    ]);
				}
			});
		}
		}, 10000);//设置成10秒执行一次，



function ajaxGet5Info(){
	var temp = [];
	var sewageid = $("#searchlist_sewageid").val();
	var postUrl = getContextPath()+"/monitor/ajaxget5info.do";
	var data = {"sewageId":sewageid};
	
  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
	        $.each(data,function(key,item){
	        	var de1 = item.detection1;
	        	var de2 = item.detection2;
	        	var de3 = item.detection3;
	        	var de4 = item.detection4;
	        	var de5 = item.detection5;
	        	//temp.push(de1);
			    mychart1.addData([
			        [
			            0,        // 系列索引
			            de1, // 新增数据
			            false,     // 新增数据是否从队列头部插入
			            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
			        ],
			        [
			            1,        // 系列索引
			            de2, // 新增数据
			            false,    // 新增数据是否从队列头部插入
			            false//,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
			            //axisData  // 坐标轴标签
				     ]
			    ]);
 				mychart2.addData([
			        [
			            0,        // 系列索引
			            de4, // 新增数据
			            false,     // 新增数据是否从队列头部插入
			            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
			        ],
			        [
			            1,        // 系列索引
			            de5, // 新增数据
			            false,    // 新增数据是否从队列头部插入
			            false//,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
			            //axisData  // 坐标轴标签
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
	        });			
		}
	});
	//return temp;
}



function chart2(){
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        // 使用
        	require(
            [
                'echarts',
                'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                mychart2 = ec.init(document.getElementById('chart2')); 
                var option = {
							    title : {
							        text: 'LS、DO实时曲线图（每分钟更新）'
							    },
							    legend: {
							        data:['LS','DO']
							    },
							    xAxis : [
							        {
							            type : 'category',
							            boundaryGap : false,
							            data : (function (){
								                var now = new Date();
								                var res = [];
								                var len = 5;
								                while (len--) {
								                	var hours = now.getHours();       //获取当前小时数(0-23)
													var mins = now.getMinutes(); 
								                    res.unshift(hours+":"+mins);
								                    now = new Date(now - 60000);
								                }
								                return res;								            	
								        })()
							        }
							    ],
							    yAxis : [
							        {
							            type : 'value',
							            name : 'LS',
							            scale:true,
							            min:0,
							            max:2,
							            splitNumber:2
							        },
							        {
							            type : 'value',
							            name : 'DO',
							            scale: true,
							            boundaryGap: [0.2, 0.2],
							            min:1,
							            max:11,
							            splitNumber:5
							        }
							    ],
							    series : [
							        {
							            name:'LS',
							            type:'line',
							            xAxisIndex: 0,//非常重要
								        yAxisIndex: 0,
							            data:(function(){
							            	var arr = [];
							            	arr.push('-');
							            	arr.push('-');
							            	arr.push('-');
							            	arr.push('-');
							            	arr.push('-');
							            	return arr;
							            })()
							        },
							        {
							            name:'DO',
							            type:'line',
							            xAxisIndex: 0,//非常重要
								        yAxisIndex: 1,
							            data:(function(){
							            	var arr = [];
							            	arr.push('-');
							            	arr.push('-');
							            	arr.push('-');
							            	arr.push('-');
							            	arr.push('-');
							            	return arr;
							            })()
							        }
							    ]
						};
                // 为echarts对象加载数据 
                mychart2.setOption(option); 
            }
        );
}



function chart3(){
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        // 使用
        	require(
            [
                'echarts',
                'echarts/chart/line'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                mychart3 = ec.init(document.getElementById('chart3')); 
                var option = {
							    title : {
							        text: 'ORP实时曲线图（每分钟更新）'
							    },
							    legend: {
							        data:['ORP']
							    },
							    xAxis : [
							        {
							            type : 'category',
							            boundaryGap : false,
							            data : (function (){
								                var now = new Date();
								                var res = [];
								                var len = 5;
								                while (len--) {
								                	var hours = now.getHours();       //获取当前小时数(0-23)
													var mins = now.getMinutes(); 
								                    res.unshift(hours+":"+mins);
								                    now = new Date(now - 60000);
								                }
								                return res;								            	
								        })()
							        }
							    ],
							    yAxis : [
							        {
							            type : 'value',
							            name : 'LS',
							            scale:true,
							            min:-1000,
							            max:1000,
							            splitNumber:10
							        }
							    ],
							    series : [
							        {
							            name:'ORP',
							            type:'line',
							            data:(function(){
							            	var arr = [];
							            	arr.push('-');
							            	arr.push('-');
							            	arr.push('-');
							            	arr.push('-');
							            	arr.push('-');
							            	return arr;
							            })()
							        }
							    ]
						};
                // 为echarts对象加载数据 
                mychart3.setOption(option); 
            }
        );
}

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
    </script> 
</body>
</html>