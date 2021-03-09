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
        <script >                        
            var linkUrl = "<%=basePath %>/CBS/PolicyLifeProblem/queryConservationProblem.do";  
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
		   function defaultQuery(){
				document.queryForm.submit;
			}
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>保单管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保全管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保全问题件管理</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CBS/PolicyLifeProblem/queryConservationProblem.do" method="POST">
					 <div class="row">
						<jsp:include page="../../util/branchTree.jsp" flush="true"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" value='${rmHelper.returnParams.insBranch_id}' displayLable="保险公司机构"/>
						<webTag:DynamicSelectTag src="problemTypeSelect" name="type_code" id="type_code" value='${rmHelper.returnParams.type_code}' displayLable="问题件类型"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="problemStatusSelect" name="status_code" id="status_code" value='${rmHelper.returnParams.status_code}' displayLable="问题件状态"/>
						<webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="保单号"/>
					</div>
				    <div class="row" style="text-align:right;">
				      	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					   	<a  class="btn btn-danger" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/cbs/policyLifeProblem/addConservationProblem&flag=2" ><i class="icon-plus icon-white"></i>添加</a>
					</div>
				</form>
			</div>
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
						    <th align="center" colspan="2">操作</th>
							<th>序号</th>
							<th>机构代码</th>
							<th>机构名称</th>
							<th>保险公司机构</th>
							<th>保单号</th>
							<th>问题件类型</th>
							<th>问题件业务来源</th>
							<th>问题件状态</th>
							<th>问题件说明</th>
							<th>问题件回馈说明</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="index">
							<tr>
								<td>
								   <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/CBS/PolicyLifeProblem/getClaimsConserProblemView.do?seq_id=${sm.seq_id}&flag=3'><i class="icon-pencil icon-white"></i>状态更新</a>
							    </td>
							    <td>
								   <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/CBS/PolicyLifeProblem/getClaimsConserProblemView.do?seq_id=${sm.seq_id}&flag=1'><i class="icon-zoom-in icon-white"></i>明细</a>
							    </td>
								<td>${index.index+1}</td>
								<td>${sm.branch_id}</td>
								<td>${sm.branch_name}</td>
								<td>${sm.insBranch_name}</td>
								<td>${sm.policy_code}</td>
								<td>${sm.type_name}</td>
								<td>${sm.origin_type_name}</td>
								<td>${sm.status_name}</td>
								<td>${sm.notes}</td>
								<td>${sm.return_notes}</td>
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
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>