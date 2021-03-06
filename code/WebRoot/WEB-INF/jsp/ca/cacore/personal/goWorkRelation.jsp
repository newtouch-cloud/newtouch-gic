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

		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp" flush="true" />
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" flush="true" />
        <script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery.form.js"></script>
	    <!-- 回跳 -->
	    <jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true" />

<script>
		<!--重置-->
		
		   
			var linkUrl = "<%=basePath %>/BSReport/queryWorkRelation.do";
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
			
			$(document).ready(function() {
				 $("#queryForm").validate({
					 rules:{
						 branch_id:{// 中介公司 非空
							 required:true ,
						 },
					 },
				  onkeyup:false
					 });
		     });
			
			function defaultQuery(){
				document.queryForm.submit;
			}
			
			function fomrReset(){
			    document.getElementById("queryForm").reset();
			}

			function exportWorkRelation(){
			    $('#queryForm').attr("action","<%=basePath %>/BSReport/exportWorkRelation.do"); //设置action指向导出
			    $('#queryForm').submit();
			    $('#queryForm').attr("action","<%=basePath %>/BSReport/queryWorkRelation.do"); //设置action执向查询
			 }
		</script>


</head>
<body>
	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>报表管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>人员情况类报表</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>人员情况分析表-用工性质</span>
			</div>
		<!-- 面包屑导航  end -->

		<!-- 查询面板 start -->
		<div class="row-fluid" id="Shrinkcontent1">
			<form id="queryForm" name="queryForm"
				class="form-horizontal alert alert-info fade in span12"
				action="<%=basePath %>/BSReport/queryWorkRelation.do"
				method="POST" autocomplete="off">
				 <!-- 提示信息 -->
			    <div id="dialog" title="提示信息" style="display:none">
			    	<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
				</div>
			    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
				<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
				<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
				
				<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
				 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
			    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
			    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${returnHepler.successflag}" displayLable="msg状态"/>
				
				<div class="row">
					<jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree.jsp" flush="true"/>
					<webTag:Date name="entry_date1" id="entry_date1"
						value='${rmHelper.returnParams.entry_date1}' displayLable="入职时间从"></webTag:Date>
					<webTag:Date name="entry_date2" id="entry_date2"
						value='${rmHelper.returnParams.entry_date2}' displayLable="至"></webTag:Date>
				</div>
				<div class="row">
					<webTag:Date name="end_date1" id="end_date1"
						value='${rmHelper.returnParams.end_date1}' displayLable="离职时间从"></webTag:Date>
					<webTag:Date name="end_date2" id="end_date2"
						value='${rmHelper.returnParams.end_date2}' displayLable="至"></webTag:Date>
					<webTag:Date name="sys_date1" id="sys_date1"
						value='${rmHelper.returnParams.sys_date1}' displayLable="系统时间从"></webTag:Date>
				</div>
				<div class="row">
					<webTag:Date name="sys_date2" id="sys_date2"
						value='${rmHelper.returnParams.sys_date2}' displayLable="至"></webTag:Date>
				</div>
				<div class="row" style="text-align: right;">
					<button type="button" onClick="exportWorkRelation();" class="btn btn-danger">导出</button>
					<button type="submit" class="btn btn-danger"
						onClick="defaultQuery();">
						<i class="icon-search icon-white"></i>查询
					</button>
					<button name="resetting" id="newreset" type="button"
						class="btn btn-danger" onclick="fomrReset();">重置</button>
				</div>
			</form>
		</div>

		<!-- 查询面板 end -->

		<!-- 查询结果 start -->
		<div class="overAuto row-fluid" id="fixeTD">
			<table 
				class="table table-striped table-bordered bootstrap-datatable datatable ">
				<thead>
					<tr>
						<th class="FixedTd">序号</th>
						<th>公司</th>
						<th>A1</th>
						<th>A2</th>
						<th>B1</th>
						<th>C1</th>
						<th>C2</th>
						<th>C3</th>
						<th>C4</th>
						<th>C5</th>
						<th>C6</th>
						<th>C7</th>
						<th>汇总</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="workRelation" items="${rmHelper.returnMsg.dataList}"
						varStatus="index">
						<tr>
							<td class="FixedTd">${index.index+1}</td>
							<td>${workRelation.branch_name}</td>
							<td>${workRelation.numA1}</td>
							<td>${workRelation.numA2}</td>
							<td>${workRelation.numB1}</td>
							<td>${workRelation.numC1}</td>
							<td>${workRelation.numC2}</td>
							<td>${workRelation.numC3}</td>
							<td>${workRelation.numC4}</td>
							<td>${workRelation.numC5}</td>
							<td>${workRelation.numC6}</td>
							<td>${workRelation.numC7}</td>
							<td>${workRelation.num}</td>
						</tr>
					</c:forEach>
					<tr>
						<td class="FixedTd">${rmHelper.returnMsg.dataList.size()+1}</td>
						<td>汇总</td>
						<td>${workRelation.numA1==null?0:workRelation.numA1}</td>
						<td>${workRelation.numA2==null?0:workRelation.numA2}</td>
						<td>${workRelation.numB1==null?0:workRelation.numB1}</td>
						<td>${workRelation.numC1==null?0:workRelation.numC1}</td>
						<td>${workRelation.numC2==null?0:workRelation.numC2}</td>
						<td>${workRelation.numC3==null?0:workRelation.numC3}</td>
						<td>${workRelation.numC4==null?0:workRelation.numC4}</td>
						<td>${workRelation.numC5==null?0:workRelation.numC5}</td>
						<td>${workRelation.numC6==null?0:workRelation.numC6}</td>
						<td>${workRelation.numC7==null?0:workRelation.numC7}</td>
						<td>${workRelation.num==null?0:workRelation.num}</td>
					</tr>
				</tbody>
				
			</table>
		</div>
		<!-- <div class="pagination pagination-centered">
			<ul id="Pagination"></ul>
		</div> -->
	</div>
	<!-- 底部高度填充块 -->
	<div class="zeoBottomH90"></div>
	<!-- 底部高度填充块 结束-->
</body>
</html>
