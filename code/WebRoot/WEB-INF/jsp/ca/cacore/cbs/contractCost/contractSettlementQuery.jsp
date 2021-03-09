<%@page import="com.newtouch.component.c11assistant.ServletHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>  
		<script type="text/javascript">
			var linkUrl = "<%=basePath %>/CBS/contractCost/querycontractSettlement.do";
			$(document).ready(function() {
				//分页
				linkUrl =  linkUrl+"?<%=ServletHelper.getHttpRequestQueryString(request)%>&nowPage=__id__&";
				 $("#Pagination").pagination(${rmHelper.pageCount.allRows}
					,{
						items_per_page:${rmHelper.pageCount.rows4Page}
					   ,num_display_entries:5
					   ,ellipse_text:'...'
					   ,current_page:${rmHelper.pageCount.nowPage}-1
					   ,link_to:linkUrl
					   ,callback: defaultQuery
					 });
	            //校验
	            $("#queryForm").validate({  //为queryForm表单配置validate
	        		onkeyup:false,
	        		messages : {
	        			cred_id:"请输入合理的凭证号"
	    			}
	        	});
				
				//校验样式效果,文本框获取焦点,异常相应报错信息
				$("#queryForm").find("input").each(function(){
	        		$(this).click(function(){
	        			var _this=$(this);
	        			if(_this.hasClass("error")){
	        				_this.removeClass("error");
	        				var labelAR = _this.parents().find("label[class='error']");
	        				labelAR.remove();
	        			}
	        		});
	        	});
	        });
		      
			function defaultQuery(){ //分页的回调函数
				document.queryForm.submit;
			}
				
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>综合查询</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保单费用查询</span><span class="divider">/</span>
				<span><i class="icon-list icon-red"></i> 保单费用结算查询</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CBS/contractCost/querycontractSettlement.do" method="POST">
				    <div class="row">
						
					</div>
				    <div class="row">
					    <jsp:include page="../../util/branchTree.jsp"  flush="true"/>
						<webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" displayLable="保险公司机构" value='${rmHelper.returnParams.insBranch_id}'/>
						<webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="保单号"/>
						
					</div>
					<div class="row">
						<webTag:Text id="app_name" name="app_name" value='${rmHelper.returnParams.app_name}' displayLable="投保人姓名"/>
					 	<webTag:Text id="agent_name" name="agent_name" value='${rmHelper.returnParams.agent_name}' displayLable="中介人员姓名"/>
					 	<webTag:Text id="cred_id" name="cred_id" value='${rmHelper.returnParams.cred_id}' iclass="isNum" displayLable="记账凭证号"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="feeStatusSelect" name="fee_status" id="fee_status" displayLable="费用处理状态" value='${rmHelper.returnParams.fee_status}'/>
					</div>
				    <div class="row" style="text-align:right;">
				    		<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					</div>
				</form>
			</div>
			<!-- 查询面板 end -->
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
							<th>序号</th>
							<th>机构代码</th>
							<th>机构名称</th>
							<th>保险公司</th>
							<th>产品代码</th>
							<th>产品名称</th>
							<th>产品分类1</th>
							<th>产品分类2</th>
							<th>产品分类3</th>
							<th>保单号</th>
							<th>保单中介人员</th>
							<th>保单服务人员</th>
							<th>投保人姓名</th>
							<th>被保人姓名</th>
							<th>主附险标志</th>
							<th>保险期限类型</th>
							<th>保障期限类型</th>
							<th>中介方式</th>
							<th>缴费方式</th>
							<th>保单年度</th>
							<th>缴费次数</th>
							<th>费用业务类型</th>	
							<th>本期应缴保费</th>
							<th>本期实缴保费</th>
							<th>付款方式</th>
							<th>投保货币</th>
							<th>投保日期</th>
							<th>应缴日期</th>
							<th>应收回销日期</th>
							<th>是否已生成记账凭证</th>
							<th>记账凭证号</th>
							<th>记账凭证号生成时间</th>
							<th>费用处理状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cm" items="${rmHelper.returnMsg.dataList}" varStatus="index" >
							<tr>
							    <td>${index.index+1}</td>
								<td>${cm.branch_id}</td>
								<td>${cm.branch_name}</td>
								<td>${cm.insBranch_name}</td>
								<td>${cm.product_id}</td>
								<td>${cm.product_name}</td>
								<td>${cm.product_type1_name}</td>
								<td>${cm.product_type2_name}</td>
								<td>${cm.product_type3_name}</td>
								<td>${cm.policy_code}</td>
								<td>${cm.agent_name}</td>
								<td>${cm.service_name}</td>
								<td>${cm.app_name}</td>
								<td>${cm.insurant_name}</td>
								<td>${cm.ins_type_name}</td>
								<td>${cm.periodtype_name}</td>
								<td>${cm.coverage_period_name}</td>
								<td>${cm.sell_way_name}</td>
								<td>${cm.charge_type_name}</td>
								<td>${cm.coverage_year}</td>
								<td>${cm.policy_period}</td>
								<td>${cm.feetype_name}</td>
								<td>${cm.period_prem}</td>
								<td>${cm.actual_prem}</td>
								<td>${cm.pay_mode_name}</td>
								<td>${cm.money_id}</td>
								<td>${cm.hold_date}</td>
								<td>${cm.due_time}</td>
								<td>${cm.received_date}</td>
								<td>${cm.posted}</td>
								<td>${cm.cred_id}</td>
								<td>${cm.post_time}</td>
								<td>${cm.status_name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		 </div>
		<div class="pagination pagination-centered">
		    <ul id="Pagination"></ul>
		</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
