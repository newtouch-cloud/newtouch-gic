<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
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
        <script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery.form.js"></script>
	    <!-- 回跳 -->
	    <jsp:include page="../../pub/backPageHelper.jsp" flush="true"/> 
	    
		<script >
			var linkUrl = "<%=basePath %>/AMS/BaseDataManagerController/queryBaseData.do";
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
			    <span class=mrl14><i class="icon-list icon-red"></i> 系统 管理  </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 数据字典管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 查询 </span><span class="divider">/</span>
			
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/BaseDataManagerController/queryBaseData.do" method="POST">
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
				
						<div class="row">
							<webTag:Text name="typecode" id="typecode" value='${rmHelper.returnParams.typecode}' displayLable="类型编码"/>
							<webTag:Date id="created" name="created"  value='${rmHelper.returnParams.created}' displayLable="创建日期"/> 
						</div><!-- /.row -->
						
				    <div class="row" style="text-align:right;">
				    	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					    <a id="addButton" class="btn btn-danger" href='<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/ams/baseDataManager/baseDataAdd'><i class="icon-plus icon-white"></i>新增</a>
					</div><!-- /.row -->
				</form>
			</div>
			
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid" id="fixeTD">
				<table class="table table-striped table-bordered bootstrap-datatable datatable ">
					<thead>
						<tr>
							<th class="FixedTd">操作</th>
							<th class="FixedTd">序号</th>
							<th>类型编码</th>
							<th>类型名称</th>
							<th>创建时间</th>
							<th>修改时间</th>
							<th>创建人</th>
							<th></th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="index">
							<tr>
								<td class="FixedTd">							
									<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/AMS/BaseDataManagerController/modifyBaseData.do?typecode=${sm.typecode}'><i class="icon-pencil icon-white"></i>修改</a>
									<a class="btn btn-mini btn-dangerLight" onclick="confirm('确定删除！！')"href='<%=basePath %>/AMS/BaseDataManagerController/deleteBaseData.do?typecode=${sm.typecode}'><i class="icon-zoom-in icon-white"></i>删除 </a>
								</td>
								<td class="FixedTd">${index.index+1}</td>
								<td>${sm.typecode}</td>
								<td>${sm.name}</td>
								<td>${sm.created}</td>
								<td>${sm.updated}</td>
								<td>${sm.createdby}</td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="pagination pagination-centered">
			    <ul id="Pagination"></ul>
			</div>
		</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 底部高度填充块 结束-->
	</body>
</html>
