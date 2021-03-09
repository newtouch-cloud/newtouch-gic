<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ServletHelper"%>

<%
	String path = request.getContextPath();
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
		<script >
			var linkUrl = "<%=basePath %>/CBS/Conservation/queryConservationsList.do";
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
		    //分页回调函数  
			function defaultQuery(){
				document.queryForm.submit;
			}
			
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			   <span class=mrl14><i class="icon-list icon-red"></i> 保单管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保全管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保全维护</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CBS/Conservation/queryConservationsList.do" method="POST">
				    <webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
				    <div class="row">
					    <webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" displayLable="保险公司机构" value='${rmHelper.returnParams.insBranch_id}'/>
						<webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="保单号"/>
					</div>
				    <div class="row" style="text-align:right;">
				    		<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					</div>
				</form>
			</div>
			<!-- 查询面板 end -->
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid" id="queryListDiv">
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
						    <th>操作</th>
							<th>序号</th>
							<th>保险公司</th>
							<th>保单号</th>
							<th>投保人姓名</th>
							<th>主被保人姓名</th>
							<th>申请人姓名</th>
							<th>申请日期</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cm" items="${rmHelper.returnMsg.dataList}" varStatus="index" >
							<tr>
							    <td>
							    	<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/CBS/Conservation/goModifyConservationsPage.do?seq_id=${cm.seq_id}'><i class="icon-pencil icon-white"></i>修改</a><!-- Edit -->
									<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/CBS/Conservation/viewConservations.do?seq_id=${cm.seq_id}'><i class="icon-zoom-in icon-white"></i>明细</a><!-- View -->
							    </td>
							    <td>${index.index+1}</td>
								<td>${cm.insBranch_name}</td>
								<td>${cm.policy_code}</td>
								<td>${cm.holder_name}</td>
								<td>${cm.insurant_name}</td>
								<td>${cm.applicant_name}</td>
								<td>${cm.application_time}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		<!-- 分页 -->
		<div class="pagination pagination-centered">
		    <ul id="Pagination"></ul>
		</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
