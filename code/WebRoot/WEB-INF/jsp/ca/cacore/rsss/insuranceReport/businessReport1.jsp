<%@page import="com.newtouch.component.c11assistant.ServletHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
	int line = 12;

%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<!--  收缩 -->
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean-ZM.css" >
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >
		
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery.form.js"></script>
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		
		<!-- fram plugins start--> 
		<script type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/pagination/jquery.pagination.js"></script>
		<!--  收缩 -->
		<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
		<!-- 数据校验 -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/compent/newtouch/util/validation.css">
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/newtouch-validate.js"></script>
        <script >    
			var linkUrl = "<%=basePath %>/CRM/Customer/custQueryAna.do";
			$(document).ready(function(){
				linkUrl = linkUrl+"?nowPage=__id__&";
	            $("#Pagination").pagination(${rmHelper.pageCount.allRows}
	            							,{
	            								items_per_page:${rmHelper.pageCount.rows4Page}
	            							   ,num_display_entries:5
	            							   ,ellipse_text:'...'
	            							   ,current_page:${rmHelper.pageCount.nowPage}-1
	            							   ,link_to:linkUrl
	            							   ,callback: defaultQuery
	            							 });
//				子页面控制父页面高度开始
			  	var frameId = "#mainIframe";
				var frameW= 1060;//定义页面宽度
				var option = {
						FrameId : frameId,
						FrameW : frameW,
					};
				$(window).frameWH(option);//控制页面宽度
//		 		子页面控制父页面高度结束
				//伸缩 及功能按钮控制
//		 		var Skt1 = $("#Shrinktop1");
				var Skb1 = $("#Shrinkbutton1");
				var ob1 = $("#Shrinkcontent1");
				var object1 = {
						FrameId : frameId,
						Obj : ob1,
				};
				Skb1.UpDown(object1);
				
				//查询校验
				$("#queryForm").validate({
	    			rules : {
	    				branch_name : {
	    					required : true
	    				},
	    				statistic_year : {
	    					required : true
	    				},
	    				statistic_month : {
	    					required : true
	    				}
	    				
	    			},
	    			onkeyup:false
	    			
	    		});
	    		
	    		//校验样式效果,文本框获取焦点,隐藏该文本框相应报错信息
		   		 $("#queryForm").find("input").each(function(){
		           		$(this).click(function(){
		           			var _this=$(this);
		           			if(_this.hasClass("error")){
		           				_this.removeClass("error");
		           				var labelAR = _this.parent().find("label[class='error']");
		           				labelAR.remove();
		           			}
		           		});
		           	});

	        });
			
			
			//导出查询数据
			function exportInfo(){
				//获取查询条件的值
				var branch_id = $('#branch_id').val();
				var statistic_year = $('#statistic_year').val();
				var statistic_month = $('#statistic_month').val();
				if(branch_id == ''){
					alert('机构选择不能为空');
				}else if(statistic_year==''){
					alert('统计年份不能为空');
				}else if(statistic_month==''){
					alert('统计月份不能为空');
				}else{
					//为隐藏域赋值
					$('#b_id').val(branch_id);
					$('#s_year').val(statistic_year);
					$('#s_month').val(statistic_month);
					//提交表单
					$('#exportForm').submit();
				}
			}
		</script>
		
		
		
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 报表管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保险代理机构业务汇总表(一)</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/RFM/BusinessReport/queryReport1.do" method="POST">
					
							<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />					 
  					<div class="row">
							<jsp:include page="../../util/branchTree.jsp" flush="true"/>
							<webTag:DynamicSelectTag src="statisticYearSelect" name="statistic_year" id="statistic_year" value='${rmHelper.returnParams.statistic_year}' displayLable="统计年份" isdisplay="true"></webTag:DynamicSelectTag>
                        	<webTag:DynamicSelectTag src="statisticMonthSelect" name="statistic_month" id="statistic_month" value='${rmHelper.returnParams.statistic_month}' displayLable="统计季度" isdisplay="true"></webTag:DynamicSelectTag>
					</div>
					<div class="row" style="text-align:right;">
				      		<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
				      		<button type="button" class="btn btn-danger" onClick="exportInfo();" ><i class="icon-download icon-white"></i>导出</button>
					</div>
				</form>
				<form id="exportForm" action="<%=basePath %>/RFM/BusinessReport/exportReport1.do" method="POST">
						<input type="hidden" id="b_id" name="branch_id" />
						<input type="hidden" id="s_year" name="statistic_year" />
						<input type="hidden" id="s_month" name="statistic_month" />
				</form>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
						    <th rowspan="2" colspan="2"></th>
							<th rowspan="2" style="text-align:center;vertical-align:middle;">行次</th>
							<th colspan="2" style="text-align:center;">保单件数（件）</th>
							<th colspan="2" style="text-align:center;">保费金额</th>
							<th colspan="2" style="text-align:center;">未解付保费</th>
							<th colspan="2" style="text-align:center;">代理手续费</th>
							<th rowspan="2" style="text-align:center;vertical-align:middle;">备注</th>
						</tr>
						<tr>
							<th>本期</th>
							<th>累计</th>
							<th>本期</th>
							<th>累计</th>
							<th>本期</th>
							<th>累计</th>
							<th>本期</th>
							<th>累计</th>
						</tr>
						<tr>
							<th rowspan="12" style="text-align:center;vertical-align:middle;">财产保险类</th>
					    </tr>
					    <tr>
							<th style="text-align:left;width:40px;">企业财产保险</th>
							<td>1</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
						</tr>
						<tr>
							<th style="text-align:left;">机动车辆险</th>
							<td>2</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
					    </tr>
					    <tr>
							<th style="text-align:left;">家财险</th>
							<td>3</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
						</tr>
						<tr>
							<th style="text-align:left;">货运、船舶保险</th>
							<td>4</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
						</tr>
						<tr>
							<th style="text-align:left;">能源、核电站保险</th>
							<td>5</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
						</tr>
						<tr>
							<th style="text-align:left;">飞机、飞机责任和航天保险</th>
							<td>6</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
						</tr>
						<tr>
							<th style="text-align:left;">建筑、安装工程险</th>
							<td>7</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
						</tr>
						<tr>
							<th style="text-align:left;">责任、信用、保证和农业保险</th>
							<td>8</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
						</tr>
						<tr>
							<th style="text-align:left;">短期健康保险、意外险</th>
							<td>9</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
						</tr>
						<tr>
							<th style="text-align:left;">其他</th>
							<td>10</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
						</tr>
						<tr>
							<th style="text-align:left;">小计</th>
							<td>11</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td></td>
						</tr>
						<tr>
							<th rowspan="12" style="text-align:center;vertical-align:middle;">人身保险类</th>
					    </tr>
					    
					    <c:forEach var="cm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
						    <tr >
							    <th style="text-align:left;">${cm.product_name}</th>
								<td><%=line++ %></td>
								<td><fmt:parseNumber value="${cm.period_num}" pattern="0.00" type="number"/></td>
								<td><fmt:parseNumber value="${cm.total_num}" pattern="0.00" type="number"/></td>
								<td><fmt:parseNumber value="${cm.period_prem}" pattern="0.00" type="number"/></td>
								<td><fmt:parseNumber value="${cm.total_prem}" pattern="0.00" type="number"/></td>
								<td><fmt:parseNumber value="${cm.period_unreceived_prem}" pattern="0.00" type="number"/></td>
								<td><fmt:parseNumber value="${cm.total_unreceived_prem}" pattern="0.00" type="number"/></td>
								<td><fmt:parseNumber value="${cm.period_fee}" pattern="0.00" type="number"/></td>
								<td><fmt:parseNumber value="${cm.total_fee}" pattern="0.00" type="number"/></td>
								<td>${cm.remark}</td>
						    </tr>
						    <% if(line == 18){ %>
						    	<tr>
						    		<th style="text-align:left;">四、补充养老保险</th>
									<td><%=line++ %></td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td></td>
								</tr>
								<tr>	
						    		<th style="text-align:left;">五、补充医疗保险</th>
									<td><%=line++ %></td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td></td>
								</tr>
								<tr>	
									<th style="text-align:left;">六、其他</th>
									<td><%=line++ %></td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td>0</td>
									<td></td>	
								</tr>	
						   	<% 	} %>
						   	
						   	<% if(line == 23){ %>
						   		<tr>	
						   			<td></td>
							   		<th style="text-align:left;">总计</th>
									<td><%=line++ %></td>
									<td><fmt:parseNumber value="${cm.period_num}" pattern="0.00" type="number"/></td>
									<td><fmt:parseNumber value="${cm.total_num}" pattern="0.00" type="number"/></td>
									<td><fmt:parseNumber value="${cm.period_prem}" pattern="0.00" type="number"/></td>
									<td><fmt:parseNumber value="${cm.total_prem}" pattern="0.00" type="number"/></td>
									<td><fmt:parseNumber value="${cm.period_unreceived_prem}" pattern="0.00" type="number"/></td>
									<td><fmt:parseNumber value="${cm.total_unreceived_prem}" pattern="0.00" type="number"/></td>
									<td><fmt:parseNumber value="${cm.period_fee}" pattern="0.00" type="number"/></td>
									<td><fmt:parseNumber value="${cm.total_fee}" pattern="0.00" type="number"/></td>
									<td>${cm.remark}</td>
								</tr>	
						   	<% 		} %>
					    </c:forEach>
					</thead>
					
				</table>
			</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>