<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<link rel="shortcut icon" href="<%=basePath %>/resources/touch/jvsmis/img/touch.png">
		
		
		<meta name="description" content="JVSmis">
		
	
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		
		<!-- fram plugins start--> 
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/pagination/jquery.pagination.js"></script>
		
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
		<!-- fram ckeditor start-->
		<script type="text/javascript" src="<%=basePath %>/compent/ckeditor/ckeditor.js"></script>
		<!-- fileupload start-->
		<script src="<%=basePath %>/resources/ca/cacore/fileupload/js/jquery.ui.widget.js"></script>
		<script src="<%=basePath %>/resources/ca/cacore/fileupload/js/jquery.iframe-transport.js"></script>
		<script src="<%=basePath %>/resources/ca/cacore/fileupload/js/jquery.fileupload.js"></script>
		<script src="<%=basePath %>/resources/ca/cacore/fileupload/js/jquery.tmpl.min.js"></script>
		<script src="<%=basePath %>/resources/ca/cacore/fileupload/js/fileupload.js"></script>
		
		<script >
			var linkUrl = "<%=basePath %>/FuncMenu/queryFuncMenu.do";
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
			 window.onload = function()
				     {
				         CKEDITOR.replace( 'editor01' );
				     };
				     
			 	var url = "<%=basePath%>uploadify";
				var fileId = new Array();
		</script>
		<script>
	
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>通用前台功能 <span class="divider">/</span></li>
					<li>通用CRUD</li>
				</ul>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/FuncMenu/queryFuncMenu.do" method="POST">
				
					<div class="row">
					    <webTag:Text   id="menu_id" name="menu_id"  value='${rmHelper.returnParams.menu_id}' displayLable="菜单代码"/>
						<webTag:Text   id="menu_name" name="menu_name"  value='${rmHelper.returnParams.menu_name}' displayLable="菜单名称"/>
						<webTag:DynamicSelectTag src="authTypeSelect" name="menu_type" id="menu_type" displayLable="菜单类型"></webTag:DynamicSelectTag>
					</div><!-- /.row -->

				    <div class="row" style="text-align:right;">
				    		<button type="submit" class="btn btn-mini btn-primary" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
				    		<button type="reset" class="btn btn-mini btn-primary"><i class="icon-search icon-white"></i>重置</button>
							<a class="btn btn-mini btn-primary" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/manage/funcmenu/funcmenuAdd"><i class="icon-plus icon-white"></i>新增</a>
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
							<th>菜单代码</th>
							<th>菜单名称</th>
							<th>菜单类型</th>
							<th>菜单访问类型</th>
							<th>菜单访问路径</th>
							<th>菜单打开方式</th>
							<th>菜单显示路径</th>
							<th>状态</th>
							
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}">
							<tr>
								<td>
									<a class="btn btn-mini btn-success" href='<%=basePath %>/FuncMenu/viewFuncMenu.do?seq_id=${sm.seq_id}'><i class="icon-zoom-in icon-white"></i></a><!-- View -->
									<a class="btn btn-mini btn-info" href='<%=basePath %>/FuncMenu/goModifyFuncMenu.do?seq_id=${sm.seq_id}'><i class="icon-edit icon-white"></i></a><!-- Edit -->
									<a class="btn btn-mini btn-danger" href='<%=basePath %>/FuncMenu/deleteFuncMenu.do?seq_id=${sm.seq_id}'><i class="icon-trash icon-white"></i></a><!-- Delete -->
								</td>
								<td>${sm.seq_id}</td>
								<td>${sm.menu_id}</td>
								<td>${sm.menu_name}</td>
								<td>${sm.menu_type}</td>
								<td>${sm.menu_uritype}</td>
								<td>${sm.menu_uri}</td>
								<td>${sm.menu_opentype}</td>
								<td>${sm.menu_dispath}</td>
								<td>${sm.status}</td>
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
