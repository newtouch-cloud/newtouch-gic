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
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script >
			var linkUrl = "<%=basePath %>/Role/roleSelect.do";
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
		
				     
			 	var url = "<%=basePath%>uploadify";
				var fileId = new Array();
				function file2submit(){
					var strs="";
				 	for(var i=0;i<fileId.length;i++){
				 		if(i==fileId.length-1){
				 			strs=strs+fileId[i];
				 		}else{
				 			strs=strs+fileId[i]+"@";	
				 		}
				 	}
				    document.form.action="<%=basePath %>/Sample/sampleSelect.do?fid="+strs;
				    alert("<%=basePath %>/Sample/sampleSelect.do?fid="+strs);
				}
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>权限管理系统菜单 <span class="divider">/</span></li>
					<li>角色管理 <span class="divider">/</span></li>
					<li>角色查看</li>
				</ul>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/Role/roleSelect.do" method="POST">
					<div class="row">
						<webTag:DynamicSelectTag src="authTypeSelect" name="role_type" id="role_type" displayLable="角色类型" ></webTag:DynamicSelectTag>
						<webTag:Text   id="role_name" name="role_name" value='${rmHelper.returnParams.role_name}' displayLable="角色名称"/>
						<webTag:Text   id="role_id" name="role_id" value='${rmHelper.returnParams.role_id}' displayLable="角色代码"/>
					</div><!-- /.row -->
					<div class="row">
						<webTag:Select id="status" name="status" value='${rmHelper.returnParams.status}' displayLable="状态">
								<webTag:Option value="0" displayLable="无效"/>
								<webTag:Option value="1" displayLable="有效"/>
						</webTag:Select>
					</div><!-- /.row -->
				    <div class="row" style="text-align:right;">
				    		<button type="submit" class="btn btn-mini btn-primary" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
				    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/manage/role/role_Add"><i class="icon-plus icon-white"></i>新增</a>
				    		<button type="submit" class="btn btn-mini btn-primary" onClick="#" ><i class="icon-search icon-white"></i>导出</button>
				    		<button type="submit" class="btn btn-mini btn-primary" onClick="#" ><i class="icon-search icon-white"></i>重置</button>
					</div><!-- /.row -->
					
				</form>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="row-fluid">
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
							<th>操作</th>
							<th>序号</th>
							<th>角色类型</th>
							<th>角色名称</th>
							<th>角色代码</th>
							<th>状态</th>
						
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
							<tr>
								<td>
									<a class="btn btn-mini btn-success" href='<%=basePath %>/Role/roleView.do?seq_id=${sm.seq_id}'>明细<i class="icon-zoom-in icon-white"></i></a><!-- View -->
									<a class="btn btn-mini btn-info" href='<%=basePath %>/Role/roleGoModify.do?seq_id=${sm.seq_id}'>修改<i class="icon-edit icon-white"></i></a><!-- Edit -->
									<a class="btn btn-mini btn-danger" href='<%=basePath %>/Role/rolePrivilege.do?seq_id=${sm.role_id}'>权限<i class="icon-edit icon-white"></i></a><!-- Delete -->
								</td>
								<td>${number.index+1}</td>
								<td>${sm.role_type}</td>
								<td>${sm.role_name}</td>
								<td>${sm.role_id}</td>
								<td>${sm.status}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="pagination pagination-centered">
				    <ul id="Pagination"></ul>
				</div>
		</div>
	</body>
</html>
