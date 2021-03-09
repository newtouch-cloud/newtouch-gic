<%@page import="com.newtouch.component.c11assistant.ServletHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
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
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>		
        <script >    
			var linkUrl = "<%=basePath %>/CRM/Customer/custQueryAna.do";
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
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户分析查询</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CRM/Customer/custQueryAna.do" method="POST">
					<!-- 2 3 4 机机构 -->
  					<div class="row">
							<jsp:include page="../../util/branchTree.jsp" flush="true"/>
							<webTag:DynamicSelectTag src="customerTypeSelect" id="customer_type" name="customer_type"  value='${rmHelper.returnParams.customer_type}' displayLable="客户类型" />
					</div>	
					<div class="row">
						<webTag:Text id="customer_id" name="customer_id" value='${rmHelper.returnParams.customer_id}' displayLable="客户代码"/>
                        <webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="客户姓名"/>
						<webTag:DynamicSelectTag src="genderSelect" name="gender" id="gender" displayLable="性别" value='${rmHelper.returnParams.gender}' ></webTag:DynamicSelectTag>                   
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="educationSelect" id="education2" name="education2" value='${rmHelper.returnParams.education2}' displayLable="教育程度"/>
						<webTag:DynamicSelectTag src="jobTypeSelect" id="job_type" name="job_type" value='${rmHelper.returnParams.job_type}' displayLable="职业类别"/>
						<webTag:DynamicSelectTag src="customerIncomTypeSelect" id="income_type" name="income_type" value='${rmHelper.returnParams.income_type}' displayLable="收入区间"/>
					</div>
				    <div class="row" style="text-align:right;">
				      		<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					</div><!-- /.row -->
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
							<th>客户类型</th>
							<th>客户代码</th>
							<th>客户姓名</th>
							<th>性别</th>
							<th>出生日期</th>
							<th>证件类型</th>
							<th>证件号码</th>
							<th>教育程度</th>
							<th>职业类别</th>
							<th>职业</th>
							<th>收入区间</th>
							<th>籍贯</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
							<tr>
								<td>
								<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/CRM/Customer/CustomerAnaView.do?customer_id=${sm.customer_id}'><i class="icon-zoom-in icon-white"></i>明细</a><!-- View -->
								</td>
								<td>${number.index+1}</td>
								<td>${sm.branch_id}</td>
								<td>${sm.branch_name}</td>
								<td>${sm.type_name}</td>
								<td>${sm.customer_id}</td>
								<td>${sm.name}</td>
								<td>${sm.gender_name}</td>
								<td>${sm.birthday}</td>
								<td>${sm.certi_type_name}</td>
								<td>${sm.certi_no}</td>
								<td>${sm.education_name}</td>
								<td>${sm.jobtype_name}</td>
								<td>${sm.job_code}</td>
								<td>${sm.incomtype_name}</td>
								<td>${sm.homeplace}</td>
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