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
		
		   
			var linkUrl = "<%=basePath %>/rsss/Report/queryBasicInformation.do";
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
			

			function exportBasicInfo(){
			    $('#queryForm').attr("action","<%=basePath %>/rsss/Report/exportBasicInformation.do"); //设置action指向导出
			    $('#queryForm').submit();
			    $('#queryForm').attr("action","<%=basePath %>/rsss/Report/queryBasicInformation.do"); //设置action执向查询
			 }
		</script>


</head>
<body>
	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>报表管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保监报表</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>基本情况表</span>
			</div>
		<!-- 面包屑导航  end -->

		<!-- 查询面板 start -->
		<div class="row-fluid" id="Shrinkcontent1">
			<form id="queryForm" name="queryForm"
				class="form-horizontal alert alert-info fade in span12"
				action="<%=basePath %>/rsss/Report/queryBasicInformation.do"
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
					<%-- <jsp:include page="/WEB-INF/jsp/ca/cacore/util/paymentbillTree.jsp" flush="true"/> --%>
					<jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree.jsp" flush="true"/>
					<div class="control-group span4">
						<label class="control-label" for="term">期次</label>
						<div class='controls'> 
						   	<input type="text" style=" width: 180px;" name="term" id="term" class="inp-date"  onfocus="WdatePicker({skin:'default',dateFmt:'yyyy/MM',readOnly:false})" value="${rmHelper.returnParams.term}"/>
						</div>
					</div>
					<%-- <webTag:Date name="entry_date1" id="entry_date1"
						value='${rmHelper.returnParams.entry_date1}' displayLable="入职时间从"></webTag:Date>
				    <webTag:Date name="entry_date2" id="entry_date2"
						value='${rmHelper.returnParams.entry_date2}' displayLable="至"></webTag:Date> --%>
				</div>
				<%-- <div class="row" >
					<webTag:Date name="end_date1" id="end_date1"
						value='${rmHelper.returnParams.end_date1}' displayLable="离职时间从"></webTag:Date>
					<webTag:Date name="end_date2" id="end_date2"
						value='${rmHelper.returnParams.end_date2}' displayLable="至"></webTag:Date>
				    <webTag:Date name="sys_date1" id="sys_date1"
						value='${rmHelper.returnParams.sys_date1}' displayLable="系统时间从"></webTag:Date>
				</div> --%>
				<%-- <div class="row">
				    <webTag:Date name="sys_date2" id="sys_date2"
						value='${rmHelper.returnParams.sys_date2}' displayLable="至"></webTag:Date>
				</div> --%>
				
				<div class="row" style="text-align: right;">
					<button type="button" onClick="exportBasicInfo();" class="btn btn-danger">导出</button>
					<button type="submit" class="btn btn-danger" onClick="defaultQuery();"><i class="icon-search icon-white"></i>查询</button>
					<button name="resetting" id="newreset" type="button" class="btn btn-danger" onclick="fomrReset();">重置</button>
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
						<th>基本情况</th>
						<th class="FixedTd">行数</th>
						<th>数值</th>
						<!-- <th>公司现有人数</th>
						<th>同比(+/-)</th>
						<th>总持证数</th>
						<th>高级管理人员人数</th>
						<th>高级管理人员持证数</th>
						<th>业务人员人数</th>
						<th>业务人员持证数</th>
						<th>非业务人员人数</th>
						<th>非业务人员持证数</th>
						<th>保单件数</th> -->
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>公司现有人数（1）</td>
						<td>1</td>
						<td>${basicInfomation.numbers}</td>
					</tr>
					<tr>
						<td>同比（+/-）（2）</td>
						<td>2</td>
						<td>${basicInfomation.compared}</td>
					</tr>
					<tr>
						<td>总持证数（3）</td>
						<td>3</td>
						<td>${basicInfomation.is_practice}</td>
					</tr>
					<tr>
						<td>高级管理人员人数（4）</td>
						<td>4</td>
						<td>${basicInfomation.gleaderNum}</td>
					</tr>
					<tr>
						<td>高级管理人员持证数（5）</td>
						<td>5</td>
						<td>${basicInfomation.gleaderIsPracticeNum}</td>
					</tr>
					<tr>
						<td>业务人员人数（6）</td>
						<td>6</td>
						<td>${basicInfomation.businessNum}</td>
					</tr>
					<tr>
						<td>业务人员持证数（7）</td>
						<td>7</td>
						<td>${basicInfomation.businessIsPracticeNum}</td>
					</tr>
					<tr>
						<td>非业务人员人数（8）</td>
						<td>8</td>
						<td>${basicInfomation.noBusinessNum}</td>
					</tr>
					<tr>
						<td>非业务人员持证数（9）</td>
						<td>9</td>
						<td>${basicInfomation.noBusinessIsPracticeNum}</td>
					</tr>
					<tr>
						<td>保单件数（件）</td>
						<td>10</td>
						<td>${basicInfomation.policyNum}</td>
					</tr>
					<%-- <c:forEach var="basicInfo" items="${rmHelper.returnMsg.dataList}"
						varStatus="index">
						<tr>
							<td>${basicInfo.baseinfo}</td>
							<td class="FixedTd">${index.index+1}</td>
							<td>${basicInfo.baseinfo_value}</td>
						</tr>
					</c:forEach> --%>
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
