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
		
		   
			var linkUrl = "<%=basePath %>/BSReport/queryLaborStatistics.do";
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

			function exportLaborStatistics(){
			    $('#queryForm').attr("action","<%=basePath %>/BSReport/exportLaborStatistics.do"); //设置action指向导出
			    $('#queryForm').submit();
			    $('#queryForm').attr("action","<%=basePath %>/BSReport/queryLaborStatistics.do"); //设置action执向查询
			 }
		</script>


</head>
<body>
	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>报表管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>人员情况类报表</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>劳动统计报表</span>
			</div>
		<!-- 面包屑导航  end -->

		<!-- 查询面板 start -->
		<div class="row-fluid" id="Shrinkcontent1">
			<form id="queryForm" name="queryForm"
				class="form-horizontal alert alert-info fade in span12"
				action="<%=basePath %>/BSReport/queryLaborStatistics.do"
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
					<webTag:Select name="work_nature" id="work_nature"
						value='${rmHelper.returnParams.work_nature}' displayLable="合同类型">
						<webTag:Option value="正式员工" displayLable="正式员工"></webTag:Option>
						<webTag:Option value="试用期员工" displayLable="试用期员工"></webTag:Option>
						<webTag:Option value="个人代理" displayLable="个人代理"></webTag:Option>
						<webTag:Option value="退休返聘" displayLable="退休返聘"></webTag:Option>
						<webTag:Option value="劳务派遣" displayLable="劳务派遣"></webTag:Option>
						<webTag:Option value="实习员工" displayLable="实习员工"></webTag:Option>
						<webTag:Option value="专职委派" displayLable="专职委派"></webTag:Option>
						<webTag:Option value="兼职委派" displayLable="兼职委派"></webTag:Option>
					</webTag:Select>
					<webTag:Select name="work_relation" id="work_relation"
						value='${rmHelper.returnParams.work_relation}' displayLable="用工性质">
						<webTag:Option value="A1" displayLable="A1"></webTag:Option>
						<webTag:Option value="A2" displayLable="A2"></webTag:Option>
						<webTag:Option value="B1" displayLable="B1"></webTag:Option>
						<webTag:Option value="C1" displayLable="C1"></webTag:Option>
						<webTag:Option value="C2" displayLable="C2"></webTag:Option>
						<webTag:Option value="C3" displayLable="C3"></webTag:Option>
						<webTag:Option value="C4" displayLable="C4"></webTag:Option>
						<webTag:Option value="C5" displayLable="C5"></webTag:Option>
						<webTag:Option value="C6" displayLable="C6"></webTag:Option>
						<webTag:Option value="C7" displayLable="C7"></webTag:Option>
					</webTag:Select>
					
					<%-- <div class="control-group span4">
						<label class="control-label" for="work_nature">合同类型</label>
						<div class='controls'> 
						<select class="input-medium null" id="work_nature" name="work_nature">
							<option value="">---请选择---</option>
							<c:forEach var="work_nature" items="${work_naturelist}">
								<c:if test="${rmHelper.returnParams.work_nature == work_nature.enum_code}">
							 		<option selected="selected" value="${work_nature.enum_code}" >${work_nature.enum_name}</option>
							 	</c:if>
							 	<c:if test="${rmHelper.returnParams.work_nature != work_nature.enum_code}">
							 		<option value="${work_nature.enum_code}" >${work_nature.enum_name}</option>
							 	</c:if>
                         	</c:forEach>
						</select>
						</div>
					</div> --%>
					<%-- <div class="control-group span4">
						<label class="control-label" for="work_relation">用工性质</label>
						<div class='controls'> 
						<select class="input-medium null" id="work_relation" name="work_relation">
							<option value="">---请选择---</option>
							<c:forEach var="work_relation" items="${work_relationList}">
								<c:if test="${rmHelper.returnParams.work_relation == work_relation.enum_code}">
							 		<option selected="selected" value="${work_relation.enum_code}" >${work_relation.enum_name}</option>
							 	</c:if>
							 	<c:if test="${rmHelper.returnParams.work_relation != work_relation.enum_code}">
							 		<option value="${work_relation.enum_code}" >${work_relation.enum_name}</option>
							 	</c:if>
                         	</c:forEach>
						</select>
						</div>
					</div> --%>
				</div>
				<div class="row">
				<webTag:Date name="entry_date1" id="entry_date1"
						value='${rmHelper.returnParams.entry_date1}' displayLable="入职时间从"></webTag:Date>
					<webTag:Date name="entry_date2" id="entry_date2"
						value='${rmHelper.returnParams.entry_date2}' displayLable="至"></webTag:Date>
					<webTag:Date name="end_date1" id="end_date1"
						value='${rmHelper.returnParams.end_date1}' displayLable="离职时间从"></webTag:Date>			
				</div>
				<div class="row">
					<webTag:Date name="end_date2" id="end_date2"
						value='${rmHelper.returnParams.end_date2}' displayLable="至"></webTag:Date>
					<webTag:Date name="sys_date1" id="sys_date1"
						value='${rmHelper.returnParams.sys_date1}' displayLable="系统时间从"></webTag:Date>
					<webTag:Date name="sys_date2" id="sys_date2"
						value='${rmHelper.returnParams.sys_date2}' displayLable="至"></webTag:Date>
				</div>
				<div class="row" style="text-align: right;">
					<button type="button" onClick="exportLaborStatistics();" class="btn btn-danger">导出</button>
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
						<th>总人数</th>
						<th>女性</th>
						<th>党员</th>
						<th>35岁及以下</th>
						<th>男60岁及以上</th>
						<th>女55岁及以上</th>
						<th>大学本科及以上</th>
						<th>持证人数</th>
						<th>未持证人数</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="laborStatistics" items="${rmHelper.returnMsg.dataList}"
						varStatus="index">
						<tr>
							<%-- <td class="FixedTd">${index.index+1}</td> --%>
							<td>${laborStatistics.numbers}</td>
							<td>${laborStatistics.womannum}</td>
							<td>${laborStatistics.partynum}</td>
							<td>${laborStatistics.age1}</td>
							<td>${laborStatistics.age2}</td>
							<td>${laborStatistics.age3}</td>
							<td>${laborStatistics.education}</td>
							<td>${laborStatistics.is_practice}</td>
							<td>${laborStatistics.no_practice}</td>
						</tr>
					</c:forEach>
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
