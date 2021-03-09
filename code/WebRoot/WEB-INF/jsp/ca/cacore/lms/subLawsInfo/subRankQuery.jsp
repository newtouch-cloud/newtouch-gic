<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
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
			function defaultQuery(){
				document.queryForm.submit;
			}
			function delBasicLaws(){
		 		return confirm("确定要删除该基本法吗？");
			}
			//分页
			var linkUrl = "<%=basePath %>/LMS/SubLawsManager/getSubLawsList.do";
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
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 子基本法管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 子基本法信息维护</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/LMS/SubLawsManager/getSubLawsList.do" method="POST">
					<div id="dialog" title="提示信息" style="display:none">
<%-- 						<image id="dialog_img" >${rmHelper1.msgStr}</image> --%>
						<center><image id="dialog_img"></image></br><span>${rmHelper1.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper1.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper1.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<ul id="myTab1" class="nav nav-tabs">
				        <li class="disabled"><a href="<%=basePath %>/LMS/SubLawsManager/goSublawForMd.do?serno=${rmHelper.returnParams.serno}">基本信息</a></li>
				        <li class="active"><a href="#" onclick="javascript:goUrl(2);">职级信息</a></li>
				        <li class="disabled"><a href="#" onclick="javascript:goUrl(3);">参数信息</a></li>
				        <li class="disabled"><a href="#" onclick="javascript:goUrl(4);">考核公式信息</a></li>
      				</ul>
					<div class="row">
						<webTag:DynamicSelectTag src="channelInfoSelect" name="dept_type" id="dept_type" displayLable="渠道类型" value='${rmHelper.returnParams.dept_type}' isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
						<webTag:Text name="impmeansver_name" id="impmeansver_name" value='${rmHelper.returnParams.impmeansver_name}' displayLable="基本法名称" readonly="true"/>
						<webTag:Text name="impmeans_name" id="impmeans_name" value='${rmHelper.returnParams.impmeans_name}' displayLable="子基本名称" readonly="true"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="channelInfoSelect" name="rank_class_no" id="rank_class_no" displayLable="职级序列" value='${rmHelper.returnParams.rank_class_no}' isdisplay="true"></webTag:DynamicSelectTag>
						<webTag:Text name="rank_no" id="rank_no" value='${rmHelper.returnParams.rank_no}' displayLable="职级代码"/>
						<webTag:Text name="rank_name" id="rank_name" value='${rmHelper.returnParams.rank_name}' displayLable="职级名称"/>
					</div>
				    <div class="row" style="text-align:right;">
				    	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
				    	<a class="btn btn-danger"href="<%=basePath%>/redirect/redirect.do?linkUrl=ca/cacore/lms/subLawsInfo/subBasicLawsAdd"><i class="icon-plus icon-white"></i>新增</a>
					</div>
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
							<th>子基本法代码/基本法代码</th>
							<th>子基本法代码/基本法代码</th>
							<th>职级代码</th>
							<th>职级名称</th>
							<th>职级序列</th>
							<th>职级类型</th>
							<th>职级级别</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="index">
							<tr>
								<td class="FixedTd">
									<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/LMS/SubLawsManager/goSublawForMd.do?serno=${sm.serno}'><i class="icon-pencil icon-white"></i>修改</a><!-- Edit -->
									<a id="other" class="btn btn-mini btn-dangerLight" onclick="return delBasicLaws()" href='<%=basePath%>/LMS/SubLawsManager/delSubLaws.do?serno=${sm.serno}'><i class="icon-off icon-white"></i>删除</a>
								</td>
								<td class="FixedTd">${index.index+1}</td>
								<td>${sm.impmeans_no}</td>
								<td>${sm.impmeans_name}</td>
								<td>${sm.rank_no}</td>
								<td>${sm.rank_name}</td>
								<td>${sm.rank_class_name}</td>
								<td>${sm.rank_class_type}</td>
								<td>${sm.level_name}</td>
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
