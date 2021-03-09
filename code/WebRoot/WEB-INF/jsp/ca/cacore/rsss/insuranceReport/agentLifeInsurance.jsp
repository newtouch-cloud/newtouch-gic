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
	   <%--  <jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvrank.jsp" flush="true"/> --%>
		
		<script type="text/javascript"> 
       			var linkUrl = "<%=basePath %>/rsss/Report/queryAgentLifeInsurance.do";
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
			function exportAgentLifeInfo(){
			    $('#queryForm').attr("action","<%=basePath %>/rsss/Report/exportAgentLifeInsurance.do"); //设置action指向导出
			    $('#queryForm').submit();
			    $('#queryForm').attr("action","<%=basePath %>/rsss/Report/queryAgentLifeInsurance.do"); //设置action执向查询
			 }
		</script>
	</head>
	<body style="height: 750px">
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>报表管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保监报表</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>代理人身险公司业务表</span>
			</div>
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<div class="Shrinkcontent" id="Shrinkcontent1">
					<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/rsss/Report/queryAgentLifeInsurance.do" method="POST" autocomplete="off">
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
						    <jsp:include page="../../util/paymentbillTree.jsp" flush="true"/>
							<jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree.jsp" flush="true"/>
							<div class="control-group span4">
								<label class="control-label" for="">填报口径</label>
								<div class='controls'>
									<select class="input-medium null" id="sign_path" name="sign_path" value='${rmHelper.returnParams.sign_path}'>
										<option value="0" ${rmHelper.returnParams.sign_path == '0'?"selected":""}>签单口径</option>
										<option value="1" ${rmHelper.returnParams.sign_path == '1'?"selected":""}>结算口径</option>
									</select>
								</div>
							</div>
						    <%-- <jsp:include page="/WEB-INF/jsp/ca/cacore/util/paymentbillTree.jsp" flush="true"/> --%>
						</div>
						<%-- <div class="row">
							<webTag:Select name="sign_path" id="sign_path" value='${rmHelper.returnParams.sign_path}' displayLable="填报口径">
								<webTag:Option value="0" displayLable="签单口径"></webTag:Option>
								<webTag:Option value="1" displayLable="结算口径"></webTag:Option>
							</webTag:Select>
						</div> --%>
						<div class="row">
							<div class="control-group span4">
								<label class="control-label" for="term">期次</label>
								<div class='controls'>
									<input type="text" style=" width: 180px;" name="term" id="term" class="inp-date"  onfocus="WdatePicker({skin:'default',dateFmt:'yyyy/MM',readOnly:false})" value="${rmHelper.returnParams.term}"/>
								</div>
							</div>

						</div>
					    <div class="row" style="text-align:right;">
					    	<button type="button" onClick="exportAgentLifeInfo();" class="btn btn-danger">导出</button>
					      	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					      	<button name="resetting" id="newreset" type="button" class="btn btn-danger" onclick="fomrReset();">重置</button>
						</div>
					</form>
				</div>
			</div>
			<!-- 查询面板 end -->
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid" id="fixeTD">
			<table 
				class="table table-striped table-bordered bootstrap-datatable datatable ">
				<thead>
					<tr>
						<th rowspan="3" style="text-align:center; vertical-align: middle;">险种名称</th>
						<th class="FixedTd" rowspan="3" style="text-align:center; vertical-align: middle;">行数</th>
						<th colspan="2" style="text-align:center;">新单保障对象数量</th>
						<th colspan="2" style="text-align:center;">新单保障额度/赔偿限额</th>
						<th colspan="2" style="text-align:center;">新保单保费金额</th>
						<th colspan="2" style="text-align:center;">续期保单金额</th>
						<th colspan="2" style="text-align:center;">应付保费</th>
						<th colspan="2" style="text-align:center;">新单代理佣金</th>
						<th colspan="2" style="text-align:center;">续期代理佣金</th>
						<th colspan="8" style="text-align:center;">自营网络平台渠道</th>
						<th colspan="8" style="text-align:center;">第三方网络平台渠道</th>
					</tr>
					<tr>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">本期</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">累计</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">本期</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">累计</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">本期</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">累计</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">本期</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">累计</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">本期</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">累计</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">本期</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">累计</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">本期</th>
						<th rowspan="2" style="text-align:center; vertical-align: middle;">累计</th>
						<th colspan="2" style="text-align:center;">新单保费金额</th>
						<th colspan="2" style="text-align:center;">续期保单金额 </th>
						<th colspan="2" style="text-align:center;">新单代理佣金</th>
						<th colspan="2" style="text-align:center;">续期代理佣金</th>
						<th colspan="2" style="text-align:center;">新单保费金额</th>
						<th colspan="2" style="text-align:center;">续期保单金额 </th>
						<th colspan="2" style="text-align:center;">新单代理佣金</th>
						<th colspan="2" style="text-align:center;">续期代理佣金</th>
					</tr>
					<tr>
						<th>本期</th>
						<th>累计</th>
						<th>本期</th>
						<th>累计</th>
						<th>本期</th>
						<th>累计</th>
						<th>本期</th>
						<th>累计</th>
						<th>本期</th>
						<th>累计</th>
						<th>本期</th>
						<th>累计</th>
						<th>本期</th>
						<th>累计</th>
						<th>本期</th>
						<th>累计</th>
					</tr>
				</thead>
				<tbody>
				
		
					<tr>
						<td><b>一、寿险小计</b></td>
						<td>1</td>
						<td>${agentLife0.total_policy_count}</td>
						<td>${agentLife0.total_policycount_sum}</td>
						<td>${agentLife0.total_amount}</td>
						<td>${agentLife0.total_amount_sum}</td>
						<td>${agentLife0.total_newpolfee}</td>
						<td>${agentLife0.total_newpolicyfee_sum}</td>
						<td>${agentLife0.total_renewfee}</td>
						<td>${agentLife0.total_renewalfee_sum}</td>
						<td>${agentLife0.total_permium}</td>
						<td>${agentLife0.total_premium_sum}</td>
						<td>${agentLife0.total_newagent}</td>
						<td>${agentLife0.total_new_agcommiss_sum}</td>
						<td>${agentLife0.total_renewagent}</td>
						<td>${agentLife0.total_renew_agcommiss_sum}</td>
						<td>${agentLife0.total_autonewpolfee}</td>
						<td>${agentLife0.total_autonewfee_sum}</td>
						<td>${agentLife0.total_auto_renewfee}</td>
						<td>${agentLife0.total_autorenewfee_sum}</td>
						<td>${agentLife0.total_newagcommiss}</td>
						<td>${agentLife0.total_autonew_agcommiss_sum}</td>
						<td>${agentLife0.total_renewplofee}</td>
						<td>${agentLife0.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife0.total_tdnewplofee}</td>
						<td>${agentLife0.total_tdnewfee_sum}</td>
						<td>${agentLife0.total_tdrenewfee}</td>
						<td>${agentLife0.total_tdrenew_sum}</td>
						<td>${agentLife0.total_newploagcommiss}</td>
						<td>${agentLife0.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife0.total_renewagcomiss}</td>
						<td>${agentLife0.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>（一）普通寿险</td>
						<td>2</td>
						<td>${agentLife1.total_policy_count}</td>
						<td>${agentLife1.total_policycount_sum}</td>
						<td>${agentLife1.total_amount}</td>
						<td>${agentLife1.total_amount_sum}</td>
						<td>${agentLife1.total_newpolfee}</td>
						<td>${agentLife1.total_newpolicyfee_sum}</td>
						<td>${agentLife1.total_renewfee}</td>
						<td>${agentLife1.total_renewalfee_sum}</td>
						<td>${agentLife1.total_permium}</td>
						<td>${agentLife1.total_premium_sum}</td>
						<td>${agentLife1.total_newagent}</td>
						<td>${agentLife1.total_new_agcommiss_sum}</td>
						<td>${agentLife1.total_renewagent}</td>
						<td>${agentLife1.total_renew_agcommiss_sum}</td>
						<td>${agentLife1.total_autonewpolfee}</td>
						<td>${agentLife1.total_autonewfee_sum}</td>
						<td>${agentLife1.total_auto_renewfee}</td>
						<td>${agentLife1.total_autorenewfee_sum}</td>
						<td>${agentLife1.total_newagcommiss}</td>
						<td>${agentLife1.total_autonew_agcommiss_sum}</td>
						<td>${agentLife1.total_renewplofee}</td>
						<td>${agentLife1.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife1.total_tdnewplofee}</td>
						<td>${agentLife1.total_tdnewfee_sum}</td>
						<td>${agentLife1.total_tdrenewfee}</td>
						<td>${agentLife1.total_tdrenew_sum}</td>
						<td>${agentLife1.total_newploagcommiss}</td>
						<td>${agentLife1.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife1.total_renewagcomiss}</td>
						<td>${agentLife1.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 1、定期寿险</td>
						<td>3</td>
						<td>${agentLife11.total_policy_count}</td>
						<td>${agentLife11.total_policycount_sum}</td>
						<td>${agentLife11.total_amount}</td>
						<td>${agentLife11.total_amount_sum}</td>
						<td>${agentLife11.total_newpolfee}</td>
						<td>${agentLife11.total_newpolicyfee_sum}</td>
						<td>${agentLife11.total_renewfee}</td>
						<td>${agentLife11.total_renewalfee_sum}</td>
						<td>${agentLife11.total_permium}</td>
						<td>${agentLife11.total_premium_sum}</td>
						<td>${agentLife11.total_newagent}</td>
						<td>${agentLife11.total_new_agcommiss_sum}</td>
						<td>${agentLife11.total_renewagent}</td>
						<td>${agentLife11.total_renew_agcommiss_sum}</td>
						<td>${agentLife11.total_autonewpolfee}</td>
						<td>${agentLife11.total_autonewfee_sum}</td>
						<td>${agentLife11.total_auto_renewfee}</td>
						<td>${agentLife11.total_autorenewfee_sum}</td>
						<td>${agentLife11.total_newagcommiss}</td>
						<td>${agentLife11.total_autonew_agcommiss_sum}</td>
						<td>${agentLife11.total_renewplofee}</td>
						<td>${agentLife11.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife11.total_tdnewplofee}</td>
						<td>${agentLife11.total_tdnewfee_sum}</td>
						<td>${agentLife11.total_tdrenewfee}</td>
						<td>${agentLife11.total_tdrenew_sum}</td>
						<td>${agentLife11.total_newploagcommiss}</td>
						<td>${agentLife11.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife11.total_renewagcomiss}</td>
						<td>${agentLife11.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 2、两全寿险</td>
						<td>4</td>
						<td>${agentLife12.total_policy_count}</td>
						<td>${agentLife12.total_policycount_sum}</td>
						<td>${agentLife12.total_amount}</td>
						<td>${agentLife12.total_amount_sum}</td>
						<td>${agentLife12.total_newpolfee}</td>
						<td>${agentLife12.total_newpolicyfee_sum}</td>
						<td>${agentLife12.total_renewfee}</td>
						<td>${agentLife12.total_renewalfee_sum}</td>
						<td>${agentLife12.total_permium}</td>
						<td>${agentLife12.total_premium_sum}</td>
						<td>${agentLife12.total_newagent}</td>
						<td>${agentLife12.total_new_agcommiss_sum}</td>
						<td>${agentLife12.total_renewagent}</td>
						<td>${agentLife12.total_renew_agcommiss_sum}</td>
						<td>${agentLife12.total_autonewpolfee}</td>
						<td>${agentLife12.total_autonewfee_sum}</td>
						<td>${agentLife12.total_auto_renewfee}</td>
						<td>${agentLife12.total_autorenewfee_sum}</td>
						<td>${agentLife12.total_newagcommiss}</td>
						<td>${agentLife12.total_autonew_agcommiss_sum}</td>
						<td>${agentLife12.total_renewplofee}</td>
						<td>${agentLife12.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife12.total_tdnewplofee}</td>
						<td>${agentLife12.total_tdnewfee_sum}</td>
						<td>${agentLife12.total_tdrenewfee}</td>
						<td>${agentLife12.total_tdrenew_sum}</td>
						<td>${agentLife12.total_newploagcommiss}</td>
						<td>${agentLife12.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife12.total_renewagcomiss}</td>
						<td>${agentLife12.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 3、终身寿险</td>
						<td>5</td>
						<td>${agentLife13.total_policy_count}</td>
						<td>${agentLife13.total_policycount_sum}</td>
						<td>${agentLife13.total_amount}</td>
						<td>${agentLife13.total_amount_sum}</td>
						<td>${agentLife13.total_newpolfee}</td>
						<td>${agentLife13.total_newpolicyfee_sum}</td>
						<td>${agentLife13.total_renewfee}</td>
						<td>${agentLife13.total_renewalfee_sum}</td>
						<td>${agentLife13.total_permium}</td>
						<td>${agentLife13.total_premium_sum}</td>
						<td>${agentLife13.total_newagent}</td>
						<td>${agentLife13.total_new_agcommiss_sum}</td>
						<td>${agentLife13.total_renewagent}</td>
						<td>${agentLife13.total_renew_agcommiss_sum}</td>
						<td>${agentLife13.total_autonewpolfee}</td>
						<td>${agentLife13.total_autonewfee_sum}</td>
						<td>${agentLife13.total_auto_renewfee}</td>
						<td>${agentLife13.total_autorenewfee_sum}</td>
						<td>${agentLife13.total_newagcommiss}</td>
						<td>${agentLife13.total_autonew_agcommiss_sum}</td>
						<td>${agentLife13.total_renewplofee}</td>
						<td>${agentLife13.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife13.total_tdnewplofee}</td>
						<td>${agentLife13.total_tdnewfee_sum}</td>
						<td>${agentLife13.total_tdrenewfee}</td>
						<td>${agentLife13.total_tdrenew_sum}</td>
						<td>${agentLife13.total_newploagcommiss}</td>
						<td>${agentLife13.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife13.total_renewagcomiss}</td>
						<td>${agentLife13.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 4、年金寿险</td>
						<td>6</td>
						<td>${agentLife14.total_policy_count}</td>
						<td>${agentLife14.total_policycount_sum}</td>
						<td>${agentLife14.total_amount}</td>
						<td>${agentLife14.total_amount_sum}</td>
						<td>${agentLife14.total_newpolfee}</td>
						<td>${agentLife14.total_newpolicyfee_sum}</td>
						<td>${agentLife14.total_renewfee}</td>
						<td>${agentLife14.total_renewalfee_sum}</td>
						<td>${agentLife14.total_permium}</td>
						<td>${agentLife14.total_premium_sum}</td>
						<td>${agentLife14.total_newagent}</td>
						<td>${agentLife14.total_new_agcommiss_sum}</td>
						<td>${agentLife14.total_renewagent}</td>
						<td>${agentLife14.total_renew_agcommiss_sum}</td>
						<td>${agentLife14.total_autonewpolfee}</td>
						<td>${agentLife14.total_autonewfee_sum}</td>
						<td>${agentLife14.total_auto_renewfee}</td>
						<td>${agentLife14.total_autorenewfee_sum}</td>
						<td>${agentLife14.total_newagcommiss}</td>
						<td>${agentLife14.total_autonew_agcommiss_sum}</td>
						<td>${agentLife14.total_renewplofee}</td>
						<td>${agentLife14.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife14.total_tdnewplofee}</td>
						<td>${agentLife14.total_tdnewfee_sum}</td>
						<td>${agentLife14.total_tdrenewfee}</td>
						<td>${agentLife14.total_tdrenew_sum}</td>
						<td>${agentLife14.total_newploagcommiss}</td>
						<td>${agentLife14.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife14.total_renewagcomiss}</td>
						<td>${agentLife14.total_tdrenew_agcommiss_sum}</td>
					</tr>
					
					<%-- <c:forEach var="agentLife" items="${list1}"
						varStatus="index">
						<tr>
							<td>${agentLife.risk_name}</td>
							<td class="FixedTd">${index.index+3}</td>
							<td>${agentLife.total_newpolfee}</td>
							<td>${agentLife.total_renewfee}</td>
							<td>${agentLife.total_permium}</td>
							<td>${agentLife.total_newagent}</td>
							<td>${agentLife.total_renewagent}</td>
							<td>${agentLife.total_autonewpolfee}</td>
							<td>${agentLife.total_auto_renewfee}</td>
							<td>${agentLife.total_newagcommiss}</td>
							<td>${agentLife.total_renewplofee}</td>
							<td>${agentLife.total_tdnewplofee}</td>
							<td>${agentLife.total_tdrenewfee}</td>
							<td>${agentLife.total_newploagcommiss}</td>
							<td>${agentLife.total_renewagcomiss}</td>
						</tr>
					</c:forEach> --%>
					
					<tr>
						<td>（二）分红寿险</td>
						<%-- <td>${list1.size()+list2.size()+4}</td> --%>
						<td>7</td>
						<td>${agentLife2.total_policy_count}</td>
						<td>${agentLife2.total_policycount_sum}</td>
						<td>${agentLife2.total_amount}</td>
						<td>${agentLife2.total_amount_sum}</td>
						<td>${agentLife2.total_newpolfee}</td>
						<td>${agentLife2.total_newpolicyfee_sum}</td>
						<td>${agentLife2.total_renewfee}</td>
						<td>${agentLife2.total_renewalfee_sum}</td>
						<td>${agentLife2.total_permium}</td>
						<td>${agentLife2.total_premium_sum}</td>
						<td>${agentLife2.total_newagent}</td>
						<td>${agentLife2.total_new_agcommiss_sum}</td>
						<td>${agentLife2.total_renewagent}</td>
						<td>${agentLife2.total_renew_agcommiss_sum}</td>
						<td>${agentLife2.total_autonewpolfee}</td>
						<td>${agentLife2.total_autonewfee_sum}</td>
						<td>${agentLife2.total_auto_renewfee}</td>
						<td>${agentLife2.total_autorenewfee_sum}</td>
						<td>${agentLife2.total_newagcommiss}</td>
						<td>${agentLife2.total_autonew_agcommiss_sum}</td>
						<td>${agentLife2.total_renewplofee}</td>
						<td>${agentLife2.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife2.total_tdnewplofee}</td>
						<td>${agentLife2.total_tdnewfee_sum}</td>
						<td>${agentLife2.total_tdrenewfee}</td>
						<td>${agentLife2.total_tdrenew_sum}</td>
						<td>${agentLife2.total_newploagcommiss}</td>
						<td>${agentLife2.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife2.total_renewagcomiss}</td>
						<td>${agentLife2.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 1、定期寿险</td>
						<td>8</td>
						<td>${agentLife21.total_policy_count}</td>
						<td>${agentLife21.total_policycount_sum}</td>
						<td>${agentLife21.total_amount}</td>
						<td>${agentLife21.total_amount_sum}</td>
						<td>${agentLife21.total_newpolfee}</td>
						<td>${agentLife21.total_newpolicyfee_sum}</td>
						<td>${agentLife21.total_renewfee}</td>
						<td>${agentLife21.total_renewalfee_sum}</td>
						<td>${agentLife21.total_permium}</td>
						<td>${agentLife21.total_premium_sum}</td>
						<td>${agentLife21.total_newagent}</td>
						<td>${agentLife21.total_new_agcommiss_sum}</td>
						<td>${agentLife21.total_renewagent}</td>
						<td>${agentLife21.total_renew_agcommiss_sum}</td>
						<td>${agentLife21.total_autonewpolfee}</td>
						<td>${agentLife21.total_autonewfee_sum}</td>
						<td>${agentLife21.total_auto_renewfee}</td>
						<td>${agentLife21.total_autorenewfee_sum}</td>
						<td>${agentLife21.total_newagcommiss}</td>
						<td>${agentLife21.total_autonew_agcommiss_sum}</td>
						<td>${agentLife21.total_renewplofee}</td>
						<td>${agentLife21.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife21.total_tdnewplofee}</td>
						<td>${agentLife21.total_tdnewfee_sum}</td>
						<td>${agentLife21.total_tdrenewfee}</td>
						<td>${agentLife21.total_tdrenew_sum}</td>
						<td>${agentLife21.total_newploagcommiss}</td>
						<td>${agentLife21.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife21.total_renewagcomiss}</td>
						<td>${agentLife21.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 2、两全寿险</td>
						<td>9</td>
						<td>${agentLife22.total_policy_count}</td>
						<td>${agentLife22.total_policycount_sum}</td>
						<td>${agentLife22.total_amount}</td>
						<td>${agentLife22.total_amount_sum}</td>
						<td>${agentLife22.total_newpolfee}</td>
						<td>${agentLife22.total_newpolicyfee_sum}</td>
						<td>${agentLife22.total_renewfee}</td>
						<td>${agentLife22.total_renewalfee_sum}</td>
						<td>${agentLife22.total_permium}</td>
						<td>${agentLife22.total_premium_sum}</td>
						<td>${agentLife22.total_newagent}</td>
						<td>${agentLife22.total_new_agcommiss_sum}</td>
						<td>${agentLife22.total_renewagent}</td>
						<td>${agentLife22.total_renew_agcommiss_sum}</td>
						<td>${agentLife22.total_autonewpolfee}</td>
						<td>${agentLife22.total_autonewfee_sum}</td>
						<td>${agentLife22.total_auto_renewfee}</td>
						<td>${agentLife22.total_autorenewfee_sum}</td>
						<td>${agentLife22.total_newagcommiss}</td>
						<td>${agentLife22.total_autonew_agcommiss_sum}</td>
						<td>${agentLife22.total_renewplofee}</td>
						<td>${agentLife22.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife22.total_tdnewplofee}</td>
						<td>${agentLife22.total_tdnewfee_sum}</td>
						<td>${agentLife22.total_tdrenewfee}</td>
						<td>${agentLife22.total_tdrenew_sum}</td>
						<td>${agentLife22.total_newploagcommiss}</td>
						<td>${agentLife22.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife22.total_renewagcomiss}</td>
						<td>${agentLife22.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 3、终身寿险</td>
						<td>10</td>
						<td>${agentLife23.total_policy_count}</td>
						<td>${agentLife23.total_policycount_sum}</td>
						<td>${agentLife23.total_amount}</td>
						<td>${agentLife23.total_amount_sum}</td>
						<td>${agentLife23.total_newpolfee}</td>
						<td>${agentLife23.total_newpolicyfee_sum}</td>
						<td>${agentLife23.total_renewfee}</td>
						<td>${agentLife23.total_renewalfee_sum}</td>
						<td>${agentLife23.total_permium}</td>
						<td>${agentLife23.total_premium_sum}</td>
						<td>${agentLife23.total_newagent}</td>
						<td>${agentLife23.total_new_agcommiss_sum}</td>
						<td>${agentLife23.total_renewagent}</td>
						<td>${agentLife23.total_renew_agcommiss_sum}</td>
						<td>${agentLife23.total_autonewpolfee}</td>
						<td>${agentLife23.total_autonewfee_sum}</td>
						<td>${agentLife23.total_auto_renewfee}</td>
						<td>${agentLife23.total_autorenewfee_sum}</td>
						<td>${agentLife23.total_newagcommiss}</td>
						<td>${agentLife23.total_autonew_agcommiss_sum}</td>
						<td>${agentLife23.total_renewplofee}</td>
						<td>${agentLife23.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife23.total_tdnewplofee}</td>
						<td>${agentLife23.total_tdnewfee_sum}</td>
						<td>${agentLife23.total_tdrenewfee}</td>
						<td>${agentLife23.total_tdrenew_sum}</td>
						<td>${agentLife23.total_newploagcommiss}</td>
						<td>${agentLife23.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife23.total_renewagcomiss}</td>
						<td>${agentLife23.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 4、年金寿险</td>
						<td>11</td>
						<td>${agentLife24.total_policy_count}</td>
						<td>${agentLife24.total_policycount_sum}</td>
						<td>${agentLife24.total_amount}</td>
						<td>${agentLife24.total_amount_sum}</td>
						<td>${agentLife24.total_newpolfee}</td>
						<td>${agentLife24.total_newpolicyfee_sum}</td>
						<td>${agentLife24.total_renewfee}</td>
						<td>${agentLife24.total_renewalfee_sum}</td>
						<td>${agentLife24.total_permium}</td>
						<td>${agentLife24.total_premium_sum}</td>
						<td>${agentLife24.total_newagent}</td>
						<td>${agentLife24.total_new_agcommiss_sum}</td>
						<td>${agentLife24.total_renewagent}</td>
						<td>${agentLife24.total_renew_agcommiss_sum}</td>
						<td>${agentLife24.total_autonewpolfee}</td>
						<td>${agentLife24.total_autonewfee_sum}</td>
						<td>${agentLife24.total_auto_renewfee}</td>
						<td>${agentLife24.total_autorenewfee_sum}</td>
						<td>${agentLife24.total_newagcommiss}</td>
						<td>${agentLife24.total_autonew_agcommiss_sum}</td>
						<td>${agentLife24.total_renewplofee}</td>
						<td>${agentLife24.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife24.total_tdnewplofee}</td>
						<td>${agentLife24.total_tdnewfee_sum}</td>
						<td>${agentLife24.total_tdrenewfee}</td>
						<td>${agentLife24.total_tdrenew_sum}</td>
						<td>${agentLife24.total_newploagcommiss}</td>
						<td>${agentLife24.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife24.total_renewagcomiss}</td>
						<td>${agentLife24.total_tdrenew_agcommiss_sum}</td>
					</tr>
					
					<%-- <c:forEach var="agentLife" items="${list2}"
						varStatus="index">
						<tr>
							<td>${agentLife.risk_name}</td>
							<td class="FixedTd">${index.index+8}</td>
							<td>${agentLife.total_newpolfee}</td>
							<td>${agentLife.total_renewfee}</td>
							<td>${agentLife.total_permium}</td>
							<td>${agentLife.total_newagent}</td>
							<td>${agentLife.total_renewagent}</td>
							<td>${agentLife.total_autonewpolfee}</td>
							<td>${agentLife.total_auto_renewfee}</td>
							<td>${agentLife.total_newagcommiss}</td>
							<td>${agentLife.total_renewplofee}</td>
							<td>${agentLife.total_tdnewplofee}</td>
							<td>${agentLife.total_tdrenewfee}</td>
							<td>${agentLife.total_newploagcommiss}</td>
							<td>${agentLife.total_renewagcomiss}</td>
						</tr>
					</c:forEach> --%>
					<tr>
						<td>（三）投资连结产品</td>
						<%-- <td>${list1.size()+list2.size()+4}</td> --%>
						<td>12</td>
						<td>${agentLife3.total_policy_count}</td>
						<td>${agentLife3.total_policycount_sum}</td>
						<td>${agentLife3.total_amount}</td>
						<td>${agentLife3.total_amount_sum}</td>
						<td>${agentLife3.total_newpolfee}</td>
						<td>${agentLife3.total_newpolicyfee_sum}</td>
						<td>${agentLife3.total_renewfee}</td>
						<td>${agentLife3.total_renewalfee_sum}</td>
						<td>${agentLife3.total_permium}</td>
						<td>${agentLife3.total_premium_sum}</td>
						<td>${agentLife3.total_newagent}</td>
						<td>${agentLife3.total_new_agcommiss_sum}</td>
						<td>${agentLife3.total_renewagent}</td>
						<td>${agentLife3.total_renew_agcommiss_sum}</td>
						<td>${agentLife3.total_autonewpolfee}</td>
						<td>${agentLife3.total_autonewfee_sum}</td>
						<td>${agentLife3.total_auto_renewfee}</td>
						<td>${agentLife3.total_autorenewfee_sum}</td>
						<td>${agentLife3.total_newagcommiss}</td>
						<td>${agentLife3.total_autonew_agcommiss_sum}</td>
						<td>${agentLife3.total_renewplofee}</td>
						<td>${agentLife3.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife3.total_tdnewplofee}</td>
						<td>${agentLife3.total_tdnewfee_sum}</td>
						<td>${agentLife3.total_tdrenewfee}</td>
						<td>${agentLife3.total_tdrenew_sum}</td>
						<td>${agentLife3.total_newploagcommiss}</td>
						<td>${agentLife3.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife3.total_renewagcomiss}</td>
						<td>${agentLife3.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 其中：年金保险</td>
						<td>13</td>
						<td>${agentLife31.total_policy_count}</td>
						<td>${agentLife31.total_policycount_sum}</td>
						<td>${agentLife31.total_amount}</td>
						<td>${agentLife31.total_amount_sum}</td>
						<td>${agentLife31.total_newpolfee}</td>
						<td>${agentLife31.total_newpolicyfee_sum}</td>
						<td>${agentLife31.total_renewfee}</td>
						<td>${agentLife31.total_renewalfee_sum}</td>
						<td>${agentLife31.total_permium}</td>
						<td>${agentLife31.total_premium_sum}</td>
						<td>${agentLife31.total_newagent}</td>
						<td>${agentLife31.total_new_agcommiss_sum}</td>
						<td>${agentLife31.total_renewagent}</td>
						<td>${agentLife31.total_renew_agcommiss_sum}</td>
						<td>${agentLife31.total_autonewpolfee}</td>
						<td>${agentLife31.total_autonewfee_sum}</td>
						<td>${agentLife31.total_auto_renewfee}</td>
						<td>${agentLife31.total_autorenewfee_sum}</td>
						<td>${agentLife31.total_newagcommiss}</td>
						<td>${agentLife31.total_autonew_agcommiss_sum}</td>
						<td>${agentLife31.total_renewplofee}</td>
						<td>${agentLife31.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife31.total_tdnewplofee}</td>
						<td>${agentLife31.total_tdnewfee_sum}</td>
						<td>${agentLife31.total_tdrenewfee}</td>
						<td>${agentLife31.total_tdrenew_sum}</td>
						<td>${agentLife31.total_newploagcommiss}</td>
						<td>${agentLife31.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife31.total_renewagcomiss}</td>
						<td>${agentLife31.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<%-- <c:forEach var="agentLife" items="${list3}"
						varStatus="index">
						<tr>
							<td>${agentLife.risk_name}</td>
							<td class="FixedTd">${index.index+12}</td>
							<td>${agentLife.total_newpolfee}</td>
							<td>${agentLife.total_renewfee}</td>
							<td>${agentLife.total_permium}</td>
							<td>${agentLife.total_newagent}</td>
							<td>${agentLife.total_renewagent}</td>
							<td>${agentLife.total_autonewpolfee}</td>
							<td>${agentLife.total_auto_renewfee}</td>
							<td>${agentLife.total_newagcommiss}</td>
							<td>${agentLife.total_renewplofee}</td>
							<td>${agentLife.total_tdnewplofee}</td>
							<td>${agentLife.total_tdrenewfee}</td>
							<td>${agentLife.total_newploagcommiss}</td>
							<td>${agentLife.total_renewagcomiss}</td>
						</tr>
					</c:forEach> --%>
					
					<tr>
						<td>（四）万能寿险</td>
						<%-- <td>${list1.size()+list2.size()+list3.size()+5}</td> --%>
						<td>14</td>
						<td>${agentLife4.total_policy_count}</td>
						<td>${agentLife4.total_policycount_sum}</td>
						<td>${agentLife4.total_amount}</td>
						<td>${agentLife4.total_amount_sum}</td>
						<td>${agentLife4.total_newpolfee}</td>
						<td>${agentLife4.total_newpolicyfee_sum}</td>
						<td>${agentLife4.total_renewfee}</td>
						<td>${agentLife4.total_renewalfee_sum}</td>
						<td>${agentLife4.total_permium}</td>
						<td>${agentLife4.total_premium_sum}</td>
						<td>${agentLife4.total_newagent}</td>
						<td>${agentLife4.total_new_agcommiss_sum}</td>
						<td>${agentLife4.total_renewagent}</td>
						<td>${agentLife4.total_renew_agcommiss_sum}</td>
						<td>${agentLife4.total_autonewpolfee}</td>
						<td>${agentLife4.total_autonewfee_sum}</td>
						<td>${agentLife4.total_auto_renewfee}</td>
						<td>${agentLife4.total_autorenewfee_sum}</td>
						<td>${agentLife4.total_newagcommiss}</td>
						<td>${agentLife4.total_autonew_agcommiss_sum}</td>
						<td>${agentLife4.total_renewplofee}</td>
						<td>${agentLife4.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife4.total_tdnewplofee}</td>
						<td>${agentLife4.total_tdnewfee_sum}</td>
						<td>${agentLife4.total_tdrenewfee}</td>
						<td>${agentLife4.total_tdrenew_sum}</td>
						<td>${agentLife4.total_newploagcommiss}</td>
						<td>${agentLife4.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife4.total_renewagcomiss}</td>
						<td>${agentLife4.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 其中：年金保险</td>
						<td>15</td>
						<td>${agentLife41.total_policy_count}</td>
						<td>${agentLife41.total_policycount_sum}</td>
						<td>${agentLife41.total_amount}</td>
						<td>${agentLife41.total_amount_sum}</td>
						<td>${agentLife41.total_newpolfee}</td>
						<td>${agentLife41.total_newpolicyfee_sum}</td>
						<td>${agentLife41.total_renewfee}</td>
						<td>${agentLife41.total_renewalfee_sum}</td>
						<td>${agentLife41.total_permium}</td>
						<td>${agentLife41.total_premium_sum}</td>
						<td>${agentLife41.total_newagent}</td>
						<td>${agentLife41.total_new_agcommiss_sum}</td>
						<td>${agentLife41.total_renewagent}</td>
						<td>${agentLife41.total_renew_agcommiss_sum}</td>
						<td>${agentLife41.total_autonewpolfee}</td>
						<td>${agentLife41.total_autonewfee_sum}</td>
						<td>${agentLife41.total_auto_renewfee}</td>
						<td>${agentLife41.total_autorenewfee_sum}</td>
						<td>${agentLife41.total_newagcommiss}</td>
						<td>${agentLife41.total_autonew_agcommiss_sum}</td>
						<td>${agentLife41.total_renewplofee}</td>
						<td>${agentLife41.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife41.total_tdnewplofee}</td>
						<td>${agentLife41.total_tdnewfee_sum}</td>
						<td>${agentLife41.total_tdrenewfee}</td>
						<td>${agentLife41.total_tdrenew_sum}</td>
						<td>${agentLife41.total_newploagcommiss}</td>
						<td>${agentLife41.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife41.total_renewagcomiss}</td>
						<td>${agentLife41.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<%-- <c:forEach var="agentLife" items="${list4}"
						varStatus="index">
						<tr>
							<td>${agentLife.risk_name}</td>
							<td class="FixedTd">${index.index+12}</td>
							<td>${agentLife.total_newpolfee}</td>
							<td>${agentLife.total_renewfee}</td>
							<td>${agentLife.total_permium}</td>
							<td>${agentLife.total_newagent}</td>
							<td>${agentLife.total_renewagent}</td>
							<td>${agentLife.total_autonewpolfee}</td>
							<td>${agentLife.total_auto_renewfee}</td>
							<td>${agentLife.total_newagcommiss}</td>
							<td>${agentLife.total_renewplofee}</td>
							<td>${agentLife.total_tdnewplofee}</td>
							<td>${agentLife.total_tdrenewfee}</td>
							<td>${agentLife.total_newploagcommiss}</td>
							<td>${agentLife.total_renewagcomiss}</td>
						</tr>
					</c:forEach> --%>
					
					<tr>
						<td><b>二、意外伤害险小计</b></td>
						<%-- <td>${6+list1.size()+list2.size()+list3.size()+list4.size()}</td> --%>
						<td>16</td>
						<td>${agentLife5.total_policy_count}</td>
						<td>${agentLife5.total_policycount_sum}</td>
						<td>${agentLife5.total_amount}</td>
						<td>${agentLife5.total_amount_sum}</td>
						<td>${agentLife5.total_newpolfee}</td>
						<td>${agentLife5.total_newpolicyfee_sum}</td>
						<td>${agentLife5.total_renewfee}</td>
						<td>${agentLife5.total_renewalfee_sum}</td>
						<td>${agentLife5.total_permium}</td>
						<td>${agentLife5.total_premium_sum}</td>
						<td>${agentLife5.total_newagent}</td>
						<td>${agentLife5.total_new_agcommiss_sum}</td>
						<td>${agentLife5.total_renewagent}</td>
						<td>${agentLife5.total_renew_agcommiss_sum}</td>
						<td>${agentLife5.total_autonewpolfee}</td>
						<td>${agentLife5.total_autonewfee_sum}</td>
						<td>${agentLife5.total_auto_renewfee}</td>
						<td>${agentLife5.total_autorenewfee_sum}</td>
						<td>${agentLife5.total_newagcommiss}</td>
						<td>${agentLife5.total_autonew_agcommiss_sum}</td>
						<td>${agentLife5.total_renewplofee}</td>
						<td>${agentLife5.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife5.total_tdnewplofee}</td>
						<td>${agentLife5.total_tdnewfee_sum}</td>
						<td>${agentLife5.total_tdrenewfee}</td>
						<td>${agentLife5.total_tdrenew_sum}</td>
						<td>${agentLife5.total_newploagcommiss}</td>
						<td>${agentLife5.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife5.total_renewagcomiss}</td>
						<td>${agentLife5.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 1、一年期以内业务</td>
						<td>17</td>
						<td>${agentLife51.total_policy_count}</td>
						<td>${agentLife51.total_policycount_sum}</td>
						<td>${agentLife51.total_amount}</td>
						<td>${agentLife51.total_amount_sum}</td>
						<td>${agentLife51.total_newpolfee}</td>
						<td>${agentLife51.total_newpolicyfee_sum}</td>
						<td>${agentLife51.total_renewfee}</td>
						<td>${agentLife51.total_renewalfee_sum}</td>
						<td>${agentLife51.total_permium}</td>
						<td>${agentLife51.total_premium_sum}</td>
						<td>${agentLife51.total_newagent}</td>
						<td>${agentLife51.total_new_agcommiss_sum}</td>
						<td>${agentLife51.total_renewagent}</td>
						<td>${agentLife51.total_renew_agcommiss_sum}</td>
						<td>${agentLife51.total_autonewpolfee}</td>
						<td>${agentLife51.total_autonewfee_sum}</td>
						<td>${agentLife51.total_auto_renewfee}</td>
						<td>${agentLife51.total_autorenewfee_sum}</td>
						<td>${agentLife51.total_newagcommiss}</td>
						<td>${agentLife51.total_autonew_agcommiss_sum}</td>
						<td>${agentLife51.total_renewplofee}</td>
						<td>${agentLife51.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife51.total_tdnewplofee}</td>
						<td>${agentLife51.total_tdnewfee_sum}</td>
						<td>${agentLife51.total_tdrenewfee}</td>
						<td>${agentLife51.total_tdrenew_sum}</td>
						<td>${agentLife51.total_newploagcommiss}</td>
						<td>${agentLife51.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife51.total_renewagcomiss}</td>
						<td>${agentLife51.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 2、一年期业务</td>
						<td>18</td>
						<td>${agentLife52.total_policy_count}</td>
						<td>${agentLife52.total_policycount_sum}</td>
						<td>${agentLife52.total_amount}</td>
						<td>${agentLife52.total_amount_sum}</td>
						<td>${agentLife52.total_newpolfee}</td>
						<td>${agentLife52.total_newpolicyfee_sum}</td>
						<td>${agentLife52.total_renewfee}</td>
						<td>${agentLife52.total_renewalfee_sum}</td>
						<td>${agentLife52.total_permium}</td>
						<td>${agentLife52.total_premium_sum}</td>
						<td>${agentLife52.total_newagent}</td>
						<td>${agentLife52.total_new_agcommiss_sum}</td>
						<td>${agentLife52.total_renewagent}</td>
						<td>${agentLife52.total_renew_agcommiss_sum}</td>
						<td>${agentLife52.total_autonewpolfee}</td>
						<td>${agentLife52.total_autonewfee_sum}</td>
						<td>${agentLife52.total_auto_renewfee}</td>
						<td>${agentLife52.total_autorenewfee_sum}</td>
						<td>${agentLife52.total_newagcommiss}</td>
						<td>${agentLife52.total_autonew_agcommiss_sum}</td>
						<td>${agentLife52.total_renewplofee}</td>
						<td>${agentLife52.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife52.total_tdnewplofee}</td>
						<td>${agentLife52.total_tdnewfee_sum}</td>
						<td>${agentLife52.total_tdrenewfee}</td>
						<td>${agentLife52.total_tdrenew_sum}</td>
						<td>${agentLife52.total_newploagcommiss}</td>
						<td>${agentLife52.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife52.total_renewagcomiss}</td>
						<td>${agentLife52.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 3、一年期以上业务</td>
						<td>19</td>
						<td>${agentLife53.total_policy_count}</td>
						<td>${agentLife53.total_policycount_sum}</td>
						<td>${agentLife53.total_amount}</td>
						<td>${agentLife53.total_amount_sum}</td>
						<td>${agentLife53.total_newpolfee}</td>
						<td>${agentLife53.total_newpolicyfee_sum}</td>
						<td>${agentLife53.total_renewfee}</td>
						<td>${agentLife53.total_renewalfee_sum}</td>
						<td>${agentLife53.total_permium}</td>
						<td>${agentLife53.total_premium_sum}</td>
						<td>${agentLife53.total_newagent}</td>
						<td>${agentLife53.total_new_agcommiss_sum}</td>
						<td>${agentLife53.total_renewagent}</td>
						<td>${agentLife53.total_renew_agcommiss_sum}</td>
						<td>${agentLife53.total_autonewpolfee}</td>
						<td>${agentLife53.total_autonewfee_sum}</td>
						<td>${agentLife53.total_auto_renewfee}</td>
						<td>${agentLife53.total_autorenewfee_sum}</td>
						<td>${agentLife53.total_newagcommiss}</td>
						<td>${agentLife53.total_autonew_agcommiss_sum}</td>
						<td>${agentLife53.total_renewplofee}</td>
						<td>${agentLife53.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife53.total_tdnewplofee}</td>
						<td>${agentLife53.total_tdnewfee_sum}</td>
						<td>${agentLife53.total_tdrenewfee}</td>
						<td>${agentLife53.total_tdrenew_sum}</td>
						<td>${agentLife53.total_newploagcommiss}</td>
						<td>${agentLife53.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife53.total_renewagcomiss}</td>
						<td>${agentLife53.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<%-- <c:forEach var="agentLife" items="${list5}"
						varStatus="index">
						<tr>
							<td>${agentLife.risk_name}</td>
							<td class="FixedTd">${index.index+17}</td>
							<td>${agentLife.total_newpolfee}</td>
							<td>${agentLife.total_renewfee}</td>
							<td>${agentLife.total_permium}</td>
							<td>${agentLife.total_newagent}</td>
							<td>${agentLife.total_renewagent}</td>
							<td>${agentLife.total_autonewpolfee}</td>
							<td>${agentLife.total_auto_renewfee}</td>
							<td>${agentLife.total_newagcommiss}</td>
							<td>${agentLife.total_renewplofee}</td>
							<td>${agentLife.total_tdnewplofee}</td>
							<td>${agentLife.total_tdrenewfee}</td>
							<td>${agentLife.total_newploagcommiss}</td>
							<td>${agentLife.total_renewagcomiss}</td>
						</tr>
					</c:forEach> --%>
				
					<tr>
						<td><b>三、健康险小计</b></td>
						<%-- <td>${7+list1.size()+list2.size()+list3.size()+list4.size()+list5.size()}</td> --%>
						<td>20</td>
						<td>${agentLife6.total_policy_count}</td>
						<td>${agentLife6.total_policycount_sum}</td>
						<td>${agentLife6.total_amount}</td>
						<td>${agentLife6.total_amount_sum}</td>
						<td>${agentLife6.total_newpolfee}</td>
						<td>${agentLife6.total_newpolicyfee_sum}</td>
						<td>${agentLife6.total_renewfee}</td>
						<td>${agentLife6.total_renewalfee_sum}</td>
						<td>${agentLife6.total_permium}</td>
						<td>${agentLife6.total_premium_sum}</td>
						<td>${agentLife6.total_newagent}</td>
						<td>${agentLife6.total_new_agcommiss_sum}</td>
						<td>${agentLife6.total_renewagent}</td>
						<td>${agentLife6.total_renew_agcommiss_sum}</td>
						<td>${agentLife6.total_autonewpolfee}</td>
						<td>${agentLife6.total_autonewfee_sum}</td>
						<td>${agentLife6.total_auto_renewfee}</td>
						<td>${agentLife6.total_autorenewfee_sum}</td>
						<td>${agentLife6.total_newagcommiss}</td>
						<td>${agentLife6.total_autonew_agcommiss_sum}</td>
						<td>${agentLife6.total_renewplofee}</td>
						<td>${agentLife6.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife6.total_tdnewplofee}</td>
						<td>${agentLife6.total_tdnewfee_sum}</td>
						<td>${agentLife6.total_tdrenewfee}</td>
						<td>${agentLife6.total_tdrenew_sum}</td>
						<td>${agentLife6.total_newploagcommiss}</td>
						<td>${agentLife6.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife6.total_renewagcomiss}</td>
						<td>${agentLife6.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 1、短期健康险</td>
						<td>21</td>
						<td>${agentLife61.total_policy_count}</td>
						<td>${agentLife61.total_policycount_sum}</td>
						<td>${agentLife61.total_amount}</td>
						<td>${agentLife61.total_amount_sum}</td>
						<td>${agentLife61.total_newpolfee}</td>
						<td>${agentLife61.total_newpolicyfee_sum}</td>
						<td>${agentLife61.total_renewfee}</td>
						<td>${agentLife61.total_renewalfee_sum}</td>
						<td>${agentLife61.total_permium}</td>
						<td>${agentLife61.total_premium_sum}</td>
						<td>${agentLife61.total_newagent}</td>
						<td>${agentLife61.total_new_agcommiss_sum}</td>
						<td>${agentLife61.total_renewagent}</td>
						<td>${agentLife61.total_renew_agcommiss_sum}</td>
						<td>${agentLife61.total_autonewpolfee}</td>
						<td>${agentLife61.total_autonewfee_sum}</td>
						<td>${agentLife61.total_auto_renewfee}</td>
						<td>${agentLife61.total_autorenewfee_sum}</td>
						<td>${agentLife61.total_newagcommiss}</td>
						<td>${agentLife61.total_autonew_agcommiss_sum}</td>
						<td>${agentLife61.total_renewplofee}</td>
						<td>${agentLife61.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife61.total_tdnewplofee}</td>
						<td>${agentLife61.total_tdnewfee_sum}</td>
						<td>${agentLife61.total_tdrenewfee}</td>
						<td>${agentLife61.total_tdrenew_sum}</td>
						<td>${agentLife61.total_newploagcommiss}</td>
						<td>${agentLife61.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife61.total_renewagcomiss}</td>
						<td>${agentLife61.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp; 2、长期健康险</td>
						<td>22</td>
						<td>${agentLife62.total_policy_count}</td>
						<td>${agentLife62.total_policycount_sum}</td>
						<td>${agentLife62.total_amount}</td>
						<td>${agentLife62.total_amount_sum}</td>
						<td>${agentLife62.total_newpolfee}</td>
						<td>${agentLife62.total_newpolicyfee_sum}</td>
						<td>${agentLife62.total_renewfee}</td>
						<td>${agentLife62.total_renewalfee_sum}</td>
						<td>${agentLife62.total_permium}</td>
						<td>${agentLife62.total_premium_sum}</td>
						<td>${agentLife62.total_newagent}</td>
						<td>${agentLife62.total_new_agcommiss_sum}</td>
						<td>${agentLife62.total_renewagent}</td>
						<td>${agentLife62.total_renew_agcommiss_sum}</td>
						<td>${agentLife62.total_autonewpolfee}</td>
						<td>${agentLife62.total_autonewfee_sum}</td>
						<td>${agentLife62.total_auto_renewfee}</td>
						<td>${agentLife62.total_autorenewfee_sum}</td>
						<td>${agentLife62.total_newagcommiss}</td>
						<td>${agentLife62.total_autonew_agcommiss_sum}</td>
						<td>${agentLife62.total_renewplofee}</td>
						<td>${agentLife62.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife62.total_tdnewplofee}</td>
						<td>${agentLife62.total_tdnewfee_sum}</td>
						<td>${agentLife62.total_tdrenewfee}</td>
						<td>${agentLife62.total_tdrenew_sum}</td>
						<td>${agentLife62.total_newploagcommiss}</td>
						<td>${agentLife62.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife62.total_renewagcomiss}</td>
						<td>${agentLife62.total_tdrenew_agcommiss_sum}</td>
					</tr>
					<%-- <c:forEach var="agentLife" items="${list6}"
						varStatus="index">
						<tr>
							<td>${agentLife.risk_name}</td>
							<td class="FixedTd">${index.index+21}</td>
							<td>${agentLife.total_newpolfee}</td>
							<td>${agentLife.total_renewfee}</td>
							<td>${agentLife.total_permium}</td>
							<td>${agentLife.total_newagent}</td>
							<td>${agentLife.total_renewagent}</td>
							<td>${agentLife.total_autonewpolfee}</td>
							<td>${agentLife.total_auto_renewfee}</td>
							<td>${agentLife.total_newagcommiss}</td>
							<td>${agentLife.total_renewplofee}</td>
							<td>${agentLife.total_tdnewplofee}</td>
							<td>${agentLife.total_tdrenewfee}</td>
							<td>${agentLife.total_newploagcommiss}</td>
							<td>${agentLife.total_renewagcomiss}</td>
						</tr>
					</c:forEach> --%>	
					
					<tr>
						<td><b>四、其他</b></td>
						<%-- <td>${8+list1.size()+list2.size()+list3.size()+list4.size()+list5.size()+list6.size()}</td> --%>
						<td>23</td>
						<td>${agentLife7.total_policy_count}</td>
						<td>${agentLife7.total_policycount_sum}</td>
						<td>${agentLife7.total_amount}</td>
						<td>${agentLife7.total_amount_sum}</td>
						<td>${agentLife7.total_newpolfee}</td>
						<td>${agentLife7.total_newpolicyfee_sum}</td>
						<td>${agentLife7.total_renewfee}</td>
						<td>${agentLife7.total_renewalfee_sum}</td>
						<td>${agentLife7.total_permium}</td>
						<td>${agentLife7.total_premium_sum}</td>
						<td>${agentLife7.total_newagent}</td>
						<td>${agentLife7.total_new_agcommiss_sum}</td>
						<td>${agentLife7.total_renewagent}</td>
						<td>${agentLife7.total_renew_agcommiss_sum}</td>
						<td>${agentLife7.total_autonewpolfee}</td>
						<td>${agentLife7.total_autonewfee_sum}</td>
						<td>${agentLife7.total_auto_renewfee}</td>
						<td>${agentLife7.total_autorenewfee_sum}</td>
						<td>${agentLife7.total_newagcommiss}</td>
						<td>${agentLife7.total_autonew_agcommiss_sum}</td>
						<td>${agentLife7.total_renewplofee}</td>
						<td>${agentLife7.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife7.total_tdnewplofee}</td>
						<td>${agentLife7.total_tdnewfee_sum}</td>
						<td>${agentLife7.total_tdrenewfee}</td>
						<td>${agentLife7.total_tdrenew_sum}</td>
						<td>${agentLife7.total_newploagcommiss}</td>
						<td>${agentLife7.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife7.total_renewagcomiss}</td>
						<td>${agentLife7.total_tdrenew_agcommiss_sum}</td>
					</tr>

					<tr>
						<td>总计</td>
						<%-- <td>${9+list1.size()+list2.size()+list3.size()+list4.size()+list5.size()+list6.size()}</td> --%>
						<td>24</td>
						<td>${agentLife8.total_policy_count}</td>
						<td>${agentLife8.total_policycount_sum}</td>
						<td>${agentLife8.total_amount}</td>
						<td>${agentLife8.total_amount_sum}</td>
						<td>${agentLife8.total_newpolfee}</td>
						<td>${agentLife8.total_newpolicyfee_sum}</td>
						<td>${agentLife8.total_renewfee}</td>
						<td>${agentLife8.total_renewalfee_sum}</td>
						<td>${agentLife8.total_permium}</td>
						<td>${agentLife8.total_premium_sum}</td>
						<td>${agentLife8.total_newagent}</td>
						<td>${agentLife8.total_new_agcommiss_sum}</td>
						<td>${agentLife8.total_renewagent}</td>
						<td>${agentLife8.total_renew_agcommiss_sum}</td>
						<td>${agentLife8.total_autonewpolfee}</td>
						<td>${agentLife8.total_autonewfee_sum}</td>
						<td>${agentLife8.total_auto_renewfee}</td>
						<td>${agentLife8.total_autorenewfee_sum}</td>
						<td>${agentLife8.total_newagcommiss}</td>
						<td>${agentLife8.total_autonew_agcommiss_sum}</td>
						<td>${agentLife8.total_renewplofee}</td>
						<td>${agentLife8.total_autorenew_agcommiss_sum}</td>
						<td>${agentLife8.total_tdnewplofee}</td>
						<td>${agentLife8.total_tdnewfee_sum}</td>
						<td>${agentLife8.total_tdrenewfee}</td>
						<td>${agentLife8.total_tdrenew_sum}</td>
						<td>${agentLife8.total_newploagcommiss}</td>
						<td>${agentLife8.total_tdnew_agcommiss_sum}</td>
						<td>${agentLife8.total_renewagcomiss}</td>
						<td>${agentLife8.total_tdrenew_agcommiss_sum}</td>
					</tr>
				</tbody>
			</table>
		</div>

			
		<!-- 查询结果 end -->
		<!-- <div class="pagination pagination-centered">
			<ul id="Pagination"></ul>
		</div> -->
		</div>
	<!-- 底部高度填充块 -->
	<div class="zeoBottomH90"></div>
	<!-- 底部高度填充块 结束-->
</body>
</html>
