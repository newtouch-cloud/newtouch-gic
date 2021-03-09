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
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp" flush="true"/>
        <jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" flush="true"/>
        <script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery.form.js"></script>
	    <!-- 回跳 -->
	    <jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/> 
	    <!-- 职级联动 -->
	    
		<script >
		<!--重置-->
		
		   
			var linkUrl = "<%=basePath %>/personalPerson/sysbranchcommission.do";
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
			
			function exportSalesInfo(){
			    $('#queryForm').attr("action","<%=basePath %>/personalPerson/downloadsysbranchcommission.do"); //设置action指向导出
			    $('#queryForm').submit();
			    $('#queryForm').attr("action","<%=basePath %>/personalPerson/sysbranchcommission.do"); //设置action执向查询
			 }
		</script>
		
		
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 报表管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 中介公司报表查询</span><span class="divider"></span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
		<div class="row-fluid" id="Shrinkcontent1">
			<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath%>/personalPerson/sysbranchcommission.do" method="POST" autocomplete="off">
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
			    <webTag:HiddenInputTag name="person_flag" id="person_flag" value='1'></webTag:HiddenInputTag>
			    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${returnHepler.successflag}" displayLable="msg状态"/>
				<div class="row" >
					<jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree.jsp" flush="true"/>
					<webTag:Date name="term_date" id="term_date" value='${rmHelper.returnParams.term_date}' displayLable="日期"></webTag:Date>
				</div>
				<div class="row" style="text-align: right;">
					<button type="submit" class="btn btn-danger" >
						<i class="icon-search icon-white"></i>查询
					</button>
					<button name="resetting" id="newreset" type="button" class="btn btn-danger">重置</button>
					<webTag:Button name="propertyReport.do" type="button" onClick="exportSalesInfo();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导出"/>
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
						<th rowspan="2">公司名称</th>
						<th colspan="6" style="text-align:center;">本月新保</th>
						<th colspan="6" style="text-align:center;">本年新保</th>
						<th colspan="4" style="text-align:center;">本月续期</th>
						<th colspan="4" style="text-align:center;">本年续期</th>
						<th colspan="4" style="text-align:center;">本月合计</th>
						<th colspan="4" style="text-align:center;">本年合计</th>
					</tr>
					<tr>
						<th >承保数量</th>
						<th >保险金额</th>
						<th >保费</th>
						<th >新单手续费</th>
						<th >保费同期（+/-)</th>
						<th >新单费同期（+/-)</th>
						
						<th >承保数量</th>
						<th >保险金额</th>
						<th >保费</th>
						<th >新单手续费</th>
						<th >保费同期（+/-)</th>
						<th >续期手续费（+/-)</th>
						
						<th >承保数量</th>
						<th >保险金额</th>
						<th >保费</th>
						<th >续期手续费</th>
						
						<th >承保数量</th>
						<th >保险金额</th>
						<th >保费</th>
						<th >续期手续费</th>
						
						<th >承保数量</th>
						<th >保险金额</th>
						<th >保费</th>
						<th >手续费合计</th>
						
						<th >承保数量</th>
						<th >保险金额</th>
						<th >保费</th>
						<th >手续费合计</th>
						
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="person" items="${rmHelper.returnMsg.dataList}" varStatus="index">
						<tr>
							
							<td>${person.branch_name}</td>
							<td>${person.g_month_count}</td>
							<td>${person.g_month_amount}</td>
							<td>${person.g_month_premium}</td>
							<td>${person.g_month_fee}</td>
							<td>${person.newg_month_premium}</td>
							<td>${person.newg_month_fee}</td>
							
							<td>${person.g_year_count}</td>
							<td>${person.g_year_amount}</td>
							<td>${person.g_year_premium}</td>
							<td>${person.g_year_fee}</td>
							<td>${person.newg_year_premium}</td>
							<td>${person.newg_year_fee}</td>
							
							<td>${person.u_month_count}</td>
							<td>${person.u_month_amount}</td>
							<td>${person.u_month_premium}</td>
							<td>${person.u_month_fee}</td>
							
							<td>${person.u_year_count}</td>
							<td>${person.u_year_amount}</td>
							<td>${person.u_year_premium}</td>
							<td>${person.u_year_fee}</td>
							
							<td>${person.all_month_count}</td>
							<td>${person.all_month_amount}</td>
							<td>${person.all_month_premium}</td>
							<td>${person.all_month_fee}</td>
							
							<td>${person.all_year_count}</td>
							<td>${person.all_year_amount}</td>
							<td>${person.all_year_premium}</td>
							<td>${person.all_year_fee}</td>
							
							<%-- <td>${person.all_month_count==0?"":person.all_month_count}</td>
							<td>${person.all_month_amount==0?"":person.all_month_amount}</td>
							<td>${person.all_month_premium==0?"":person.all_month_premium}</td>
							<td>${person.all_month_fee==0?"":person.all_month_fee}</td>
							
							<td>${person.all_year_count==0?"":person.all_year_count}</td>
							<td>${person.all_year_amount==0?"":person.all_year_amount}</td>
							<td>${person.all_year_premium==0?"":person.all_year_premium}</td>
							<td>${person.all_year_fee==0?"":person.all_year_fee}</td> --%>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- 查询结果 end -->
			<!-- <div class="pagination pagination-centered">
			    <ul id="Pagination"></ul>
			</div> -->
		</div>
		<!--底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!--底部高度填充块 结束-->
	</body>
</html>
