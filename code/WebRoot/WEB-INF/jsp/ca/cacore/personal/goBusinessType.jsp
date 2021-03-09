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
	    <!-- 职级联动 -->
	    <jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvrank.jsp" flush="true"/>
		
		 <script type="text/javascript"> 
       			var linkUrl = "<%=basePath %>/bussiness/queryBusinessType.do";
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
			
			function exportBusinessType(){
			    $('#queryForm').attr("action","<%=basePath %>/bussiness/exportBusinessType.do"); //设置action指向导出
			    $('#queryForm').submit();
			    $('#queryForm').attr("action","<%=basePath %>/bussiness/queryBusinessType.do"); //设置action指向查询
			 }
		</script>
	</head>
	<body style="height: 750px">
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>报表管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>业绩报表</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>业务类型报表</span>
			</div>
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<!-- <div class="Shrinkcontent" id="Shrinkcontent1"> -->
					<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/bussiness/queryBusinessType.do" method="POST" autocomplete="off">
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
						</div>
						<%-- <div class="row">
							<div class="control-group span4">
								<label class="control-label" for="">填报口径</label>
								<div class='controls'> 
								<select class="input-medium null" id="sign_path" name="sign_path" value='${rmHelper.returnParams.sign_path}' >
									<option value="0" ${rmHelper.returnParams.sign_path == '0'?"selected":""}>签单口径</option>
									<option value="1" ${rmHelper.returnParams.sign_path == '1'?"selected":""}>结算口径</option>
								</select>
								</div>
							</div>
						</div> --%>
					    <div class="row" style="text-align:right;">
					    	<button type="button" onClick="exportBusinessType();" class="btn btn-danger">导出</button>
					      	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					      	<button name="resetting" id="newreset" type="button" class="btn btn-danger" onclick="fomrReset();">重置</button>
						</div><!-- /.row -->
					</form>
				<!-- </div> -->
			</div>
			<!-- 查询面板 end -->
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid" id="fixeTD">
			<table 
				class="table table-striped table-bordered bootstrap-datatable datatable ">
				<thead>
					<tr>
						<th class="FixedTd" rowspan="2">序号</th>
						<th rowspan="2">分公司名称</th>
						<th rowspan="2">业务类型</th>
						<th colspan="4" style="text-align:center;">核单口径-本月</th>
						<th colspan="4" style="text-align:center;">核单口径-本年</th>
					</tr>
					<tr>
						<th>保单数量</th>
						<th>代理保费</th>
						<th>跟单手续费</th>
						<th>财务已结手续费</th>
						<th>保单数量</th>
						<th>代理保费</th>
						<th>跟单手续费</th>
						<th>财务已结手续费</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bussinessType" items="${rmHelper.returnMsg.dataList}"
						varStatus="index">
						<tr>
							<td class="FixedTd">${index.index+1}</td>
							<td>${bussinessType.branch_name}</td>
							<td>${bussinessType.business_flag}</td>
							<td>${bussinessType.u_month_count}</td>
							<td>${bussinessType.u_month_premium}</td>
							<td>${bussinessType.u_month_fee}</td>
							<td>${bussinessType.u_month_paidfee}</td>
							<td>${bussinessType.u_year_count}</td>
							<td>${bussinessType.u_year_premium}</td>
							<td>${bussinessType.u_year_fee}</td>
							<td>${bussinessType.u_year_paidfee}</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</div>

			
		<!-- 查询结果 end -->
		<div class="pagination pagination-centered">
			<ul id="Pagination"></ul>
		</div>
		</div>
	<!-- 底部高度填充块 -->
	<div class="zeoBottomH90"></div>
	<!-- 底部高度填充块 结束-->
</body>
</html>
