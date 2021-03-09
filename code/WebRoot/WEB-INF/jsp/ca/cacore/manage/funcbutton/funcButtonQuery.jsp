<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@ page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
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
			var linkUrl = "<%=basePath %>/FuncButton/queryFuncButton.do";
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
				document.queryForm.submit;
			}
			 window.onload = function()
				     {
				         CKEDITOR.replace( 'editor01' );
				     };
				     
			 	var url = "<%=basePath%>uploadify";
				var fileId = new Array();
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>按钮管理功能 <span class="divider">/</span></li>
					<li>按钮功能信息CRUD</li>
				</ul>
			</div>
			
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/FuncButton/queryFuncButton.do" method="POST">
					<div class="row">
						<webTag:Text id="menu_name" name="menu_name" value='${rmHelper.returnParams.menu_id}' displayLable="菜单名称"/>
						<webTag:Text id="button_id" name="button_id" value='${rmHelper.returnParams.button_id}' displayLable="按钮代码"/>
						<webTag:DynamicSelectTag src="statusSelect" name="status" id="status" displayLable="状态" value='${rmHelper.returnParams.status}'></webTag:DynamicSelectTag>
					</div><!-- /.row -->
					
				    <div class="row" style="text-align:right;">
				      		<button type="submit" class="btn btn-mini btn-primary" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
							<a class="btn btn-mini btn-primary" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/manage/funcbutton/funcButtonAdd"><i class="icon-plus icon-white"></i>新增</a>
					</div>
					
				</form>
			</div>
			
			<!-- 查询结果 start -->
			<div class="row-fluid">
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
						    <th>操作</th>
							<th>序列号</th>
							<th>菜单名称</th>
							<th>按钮代码</th>
							<th>按钮名称</th>
							<th>状态</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="index">
							<tr>
								<td>
								<a class="btn btn-mini btn-success" href='<%=basePath %>/FuncButton/funcButtonView.do?seq_id=${sm.seq_id}'><i class="icon-zoom-in icon-white"></i>详细</a>
								<a class="btn btn-mini btn-info" href='<%=basePath %>/FuncButton/funcButtonGoModify.do?seq_id=${sm.seq_id}'><i class="icon-edit icon-white"></i>修改</a>
								<a class="btn btn-mini btn-danger" href='<%=basePath %>/FuncButton/funcButtonDelete.do?seq_id=${sm.seq_id}'><i class="icon-edit icon-white"></i>删除</a>
								</td>
								
								<td>${index.index + 1}</td>
								<td>${sm.menu_name}</td>
								<td>${sm.button_id}</td>
								<td>${sm.button_name}</td>
								<td>${sm.status}</td>
								<td>${sm.remark}</td>
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
	</body>
</html>