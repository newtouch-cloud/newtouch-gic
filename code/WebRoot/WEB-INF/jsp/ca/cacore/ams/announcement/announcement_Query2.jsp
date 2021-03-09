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
			var linkUrl = "<%=basePath %>/AMS/Announcement/queryAnnouncement.do";
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
			function goBack(){
				document.queryForm.action="<%=basePath %>/compent/xinzhi/html/tongji.jsp";
			}
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 系统消息</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 公告查询</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/Announcement/queryAnnouncement.do?pageName=announcement_Query2" method="POST">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>				 
					<div class="row">
						<jsp:include page="../../util/branchTree.jsp" flush="true"/>
						<webTag:Text id="announcement_theme" name="announcement_theme" value='${rmHelper.returnParams.announcement_theme}' displayLable="主题" />
					    <webTag:DynamicSelectTag src="announcementMarkSelect" name="announcement_mark_code" id="announcement_mark_code" value='${rmHelper.returnParams.announcement_mark_code}' displayLable="标识"></webTag:DynamicSelectTag>
<%-- 						<webTag:DynamicSelectTag src="announcementTypeSelect" name="announcement_type_code" id="announcement_type_code" value='${rmHelper.returnParams.announcement_type_code}' displayLable="类型"></webTag:DynamicSelectTag> --%>
					</div>
					<div class="row" style="text-align:right;">
					    <button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
<!--                         <button class="btn btn-danger"   onclick="window.history.go(-1);return false;"><i class="icon-share-alt icon-white"></i>返回</button> -->
                        <button class="btn btn-danger"   onclick="goBack();"><i class="icon-share-alt icon-white"></i>返回</button>
					</div><!-- /.row -->
				</form>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
							<th >操作</th>
							<th>序号</th>
							<th>公告的机构代码</th>
							<th>公告的机构名称</th>
							<th>主题</th>
							<th>类型</th>
							<th>标识</th>
						<!-- 	<th>审批状态</th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rb" items="${rmHelper.returnMsg.dataList}" varStatus="index" >
							<tr>
							<td>
								    <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/AMS/Announcement/viewAnnouncement.do?seq_id=${rb.seq_id}&pageName=announcement_View'><i class="icon-zoom-in icon-white"></i>明细</a>
								</td>
							    <td>${index.index+1}</td>
								<td>${rb.branch_id}</td>
								<td>${rb.branch_name}</td>
								<td>${rb.announcement_theme}</td>
								<td>${rb.announcement_type_name}</td>
								<td>${rb.announcement_mark_name}</td>
							<%-- 	<td>${rb.approval_status_name}</td> --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="pagination pagination-centered">
			<ul id="Pagination"></ul>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>