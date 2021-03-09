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
		
		
		
		
		
		
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script >
			var linkUrl = "<%=basePath %>/cbs/policyImage/QueryAddContractImage.do";
			$(document).ready(function(){
				linkUrl = linkUrl+"?<%=ServletHelper.getHttpRequestQueryString(request)%>&nowPage=__id__&";
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
			
		    
			function syncValue(){
				if($("#branch_name").val()==""||$("#branch_name").val()==null){
					$("#branch_id").val("");
				}
			}
			//机构选择结束
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 影像管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保单影像件上传</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/cbs/policyImage/QueryAddContractImage.do" method="POST">
					<div class="row"> <!-- 机构选择 -->
					    <jsp:include page="../../util/branchTree.jsp"  flush="true"/>
					</div>	
				    <div class="row">
					    <webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" displayLable="保险公司机构" value='${rmHelper.returnParams.insBranch_id}'/>
						<webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="保单号"/>
						<webTag:Text id="app_name" name="app_name" value='${rmHelper.returnParams.app_name}' displayLable="投保人姓名"/>
					</div>
					<div class="row">
					    <webTag:Text id="agent_name" name="agent_name" value='${rmHelper.returnParams.agent_name}' displayLable="中介人员姓名"/>
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
						    <th>操作</th>
							<th>序号</th>
							<th>机构代码</th>
							<th>机构名称</th>
							<th>保险公司</th>
							<th>保单号</th>
							<th>保单中介人员</th>
							<th>保单服务人员</th>
							<th>投保人姓名</th>
							<th>主被保人姓名</th>
							<th>投保日期</th>
							<th>影像上传日期</th>
							<th>保单状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cm" items="${rmHelper.returnMsg.dataList}" varStatus="index" >
							<tr>
							    <td>
						    		<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/cbs/policyImage/addContractImage.do?policy_id=${cm.policy_id}'><i class="icon-plus icon-white"></i>影像件上传</a>
							    </td>
							    <td>${index.index+1}</td>
								<td>${cm.branch_id}</td>
								<td>${cm.branch_name}</td>
								<td>${cm.insbranch_name}</td>
								<td>${cm.policy_code}</td>
								<td>${cm.agent_name}</td>
								<td>${cm.service_name}</td>
								<td>${cm.app_name}</td>
								<td>${cm.insurant_name}</td>
								<td>${cm.hold_date}</td>
								<td>${cm.scan_time}</td>
								<td>${cm.status_name}</td>
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
