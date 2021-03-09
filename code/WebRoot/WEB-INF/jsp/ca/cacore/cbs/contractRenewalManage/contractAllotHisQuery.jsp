<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css" ">
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		<!-- fram plugins start--> 
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
		<script >
			var linkUrl = "<%=basePath %>/CBS/ContractAllotHis/queryContList.do";
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
	        });
			function defaultQuery(){
				//document.queryForm.action=document.queryForm.action+"projectAction_queryPro";
				document.queryForm.submit;
			}
			 	var url = "<%=basePath%>uploadify";
				var fileId = new Array();
				
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>保单管理菜单<span class="divider">/</span></li>
					<li>保单续期管理<span class="divider">/</span></li>
					<li>保单分配轨迹查询</li>
				</ul>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CBS/ContractAllotHis/queryContList.do" method="POST">
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
					<div class="row">
						<jsp:include page="../../util/branchTree.jsp" flush="true"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" value='${rmHelper.returnParams.insBranch_id}'   displayLable="保险公司机构"></webTag:DynamicSelectTag>
					    <webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="保单号"/>
						<webTag:Text id="holder_name" name="holder_name" value='${rmHelper.returnParams.holder_name}' displayLable="投保人姓名"/>
					</div>
					<div class="row">
					     <webTag:Text id="bef_service_name" name="bef_service_name" value='${rmHelper.returnParams.bef_service_name}' displayLable="分配前服务人员姓名"/>
						 <webTag:Text id="aft_service_name" name="aft_service_name" value='${rmHelper.returnParams.aft_service_name}' displayLable="分配后服务人员姓名"/>
					</div>
				    <div class="row" style="text-align:right;">
				    		<button type="submit" class="btn btn-mini btn-primary" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					</div><!-- /.row -->
				</form>
			</div>
			<!-- 查询面板 end -->
			<!-- 查询结果 start -->
			<div class="row-fluid">
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
							<th>序列号</th>
							<th>机构代码</th>
							<th>机构名称</th>
							<th>保险公司</th>
							<th>产品代码</th>
							<th>产品名称</th>
							<th>保单号</th>
							<th>保单中介人员</th>
							<th>分配前服务人员</th>
							<th>分配后服务人员</th>
							<th>分配时间</th>
							<th>投保人姓名</th>
							<th>主被保人姓名</th>
							<th>投保日期</th>
							<th>保单生效日期</th>
							<th>保单状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="stm"  varStatus="index" items="${rmHelper.returnMsg.dataList}"  >
							<tr>
							    <td>${index.index + 1}</td>
								<td>${stm.branch_id}</td>
								<td>${stm.branch_name}</td>
								<td>${stm.insBranch_name}</td>
								<td>${stm.product_id}</td>
								<td>${stm.product_name}</td>
								<td>${stm.policy_code}</td>
								<td>${stm.agent_name}</td>
								<td>${stm.bef_service_name}</td>
								<td>${stm.aft_service_name}</td>
								<td>${stm.modifyDate}</td>
								<td>${stm.holder_name}</td>
								<td>${stm.insurant_name}</td>
								<td>${stm.hold_date}</td>
								<td>${stm.validate_date}</td>
								<td>${stm.policy_status_name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		<div class="pagination pagination-centered">
		    <ul id="Pagination"></ul>
		</div>
		</div>
	</body>
</html>
