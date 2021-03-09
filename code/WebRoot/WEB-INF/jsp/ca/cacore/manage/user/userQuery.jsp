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
			var linkUrl = "<%=basePath %>/User/queryUser.do";
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
				    document.form.action="<%=basePath %>/User/queryUser.do?fid="+strs;
				    alert("<%=basePath %>/UserCRUD/queryUser.do?fid="+strs);
				}
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>后台管理功能 <span class="divider">/</span></li>
					<li>用户管理</li>
				</ul>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/User/queryUser.do" method="POST">
					<div class="row">
					    <webTag:Text   id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码"/>	
						<webTag:DynamicSelectTag src="authTypeSelect" name="user_type" id="user_type" displayLable="用户类型" ></webTag:DynamicSelectTag>
						<webTag:Text   id="userName" name="userName" value='${rmHelper.returnParams.userName}' displayLable="用户名"/>
					</div><!-- /.row -->
					<div class="row">
					<webTag:Select id="status" name="status" value='${rmHelper.returnParams.status}' displayLable="状态">
							<webTag:Option id="status" name="status" value="1" displayLable="有效 "/>
							<webTag:Option id="status" name="status" value="0" displayLable="无效"/>
						</webTag:Select>
					</div><!-- /.row -->
					<div class="row" style="text-align:right;">
				    		<button type="submit" class="btn btn-mini btn-primary" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
				    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/manage/user/userAdd"><i class="icon-plus icon-white"></i>新增</a>
				    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/manage/user/userAdd">导出</a>
				    		<button type="reset" class="btn btn-mini btn-primary" >重置</button>
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
							<th>机构代码</th>
							<th>用户类型</th>
							<th>用户名</th>
							<th>姓名</th>
							<th>性别</th>
							<th>手机</th>
							<th>固定电话</th>
							<th>电子邮件</th>
							<th>员工代码</th>
							<th>办公电话</th>
							<th>办公地址</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}">
							<tr>
								<td>
									<a class="btn btn-mini btn-success" href='<%=basePath %>/User/userView.do?seq_id=${sm.seq_id}'>明细<i class="icon-zoom-in icon-white"></i></a><!-- View -->
									<a class="btn btn-mini btn-info" href='<%=basePath %>/User/userGoModify.do?seq_id=${sm.seq_id}'>修改<i class="icon-edit icon-white"></i></a><!-- Edit -->
									<a class="btn btn-mini btn-info" href='<%=basePath %>/User/queryUserAuths.do?seq_id=${sm.seq_id}'>权限<i class="icon-edit icon-white"></i></a><!-- Delete -->
								</td>
								<td>${sm.seq_id}</td>
								<td>${sm.branch_id}</td>
								<td>${sm.user_type}</td>
								<td>${sm.userName}</td>
								<td>${sm.name}</td>
								<td>${sm.sex_code}</td>
								<td>${sm.mobile}</td>
								<td>${sm.fixed_line}</td>
								<td>${sm.email}</td>
								<td>${sm.emp_id}</td>
								<td>${sm.job_tel}</td>
								<td>${sm.job_address}</td>
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