<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%@ page import="com.newtouch.component.c11assistant.ServletHelper"%>
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
			var linkUrl = "<%=basePath %>/Branch/queryAllBranchStatusHis.do";
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
			    <span class=mrl14><i class="icon-list icon-red"></i>中介人员管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>机构管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>机构变更轨迹</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<div class="Shrinkcontent" id="Shrinkcontent1">
					<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/Branch/queryAllBranchStatusHis.do" method="POST">
						<div class="row">
							<jsp:include page="../../util/branchTree.jsp" flush="true"/>
	                        <webTag:DynamicSelectTag src="statusSelect" name="status" id="status" displayLable="机构状态" value='${rmHelper.returnParams.status}'></webTag:DynamicSelectTag>
						</div>
					    <div class="row" style="text-align:right;">
					      	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
						</div><!-- /.row -->
					</form>
				</div>
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
							<th>变更前状态</th>
							<th>变更后状态</th>									
							<th>操作人</th>
							<th>操作时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
							<tr>
								<td>${number.index+1}</td>
								<td>${sm.branch_id}</td>
								<td>${sm.branch_name}</td>								
								<td>${sm.bef_stat_code}</td>
								<td>${sm.aft_stat_code}</td>																							
								<td>${sm.modifyUser}</td>
								<td>${sm.modifyDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<!-- 查询结果 end -->
				<div class="pagination pagination-centered">
				    <ul id="Pagination"></ul>
				</div>
			</div>
			<!-- 底部高度填充块 -->
			<div class="zeoBottomH90"></div>
			<!-- 底部高度填充块 结束-->
	</body>
</html>