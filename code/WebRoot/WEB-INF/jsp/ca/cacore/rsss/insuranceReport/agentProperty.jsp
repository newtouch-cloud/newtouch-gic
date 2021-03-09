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
       			var linkUrl = "<%=basePath %>/rsss/Report/queryAgentProperty.do";
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
			
			function exportAgentInfo(){
			    $('#queryForm').attr("action","<%=basePath %>/rsss/Report/exportAgentProperty.do"); //设置action指向导出
			    $('#queryForm').submit();
			    $('#queryForm').attr("action","<%=basePath %>/rsss/Report/queryAgentProperty.do"); //设置action指向查询
			 }
		</script>
	</head>
	<body style="height: 750px">
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>报表管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保监报表</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>代理产险公司业务表</span>
			</div>
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<div class="Shrinkcontent" id="Shrinkcontent1">
					<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/rsss/Report/queryAgentProperty.do" method="POST" autocomplete="off">
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
						    <jsp:include page="../../util/paymentbillTree.jsp" flush="true"/>
							<jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree.jsp" flush="true"/>
								<div class="control-group span4">
									<label class="control-label" for="">填报口径</label>
									<div class='controls'>
										<select class="input-medium null" id="sign_path" name="sign_path" value='${rmHelper.returnParams.sign_path}' >
											<option value="0" ${rmHelper.returnParams.sign_path == '0'?"selected":""}>签单口径</option>
											<option value="1" ${rmHelper.returnParams.sign_path == '1'?"selected":""}>结算口径</option>
										</select>
									</div>
						</div>
						<div class="row">
							<%-- <webTag:Select name="sign_path" id="sign_path" value='${rmHelper.returnParams.sign_path}' displayLable="填报口径">
								<webTag:Option value="0" displayLable="签单口径"></webTag:Option>
								<webTag:Option value="1" displayLable="结算口径"></webTag:Option>
							</webTag:Select> --%>
								<div class="control-group span4">
									<label class="control-label" for="term">期次</label>
									<div class='controls'>
										<input type="text" style=" width: 180px;" name="term" id="term" class="inp-date"  onfocus="WdatePicker({skin:'default',dateFmt:'yyyy/MM',readOnly:false})" value="${rmHelper.returnParams.term}"/>
									</div>
								</div>

							</div>
						</div>
					    <div class="row" style="text-align:right;">
					    	<button type="button" onClick="exportAgentInfo();" class="btn btn-danger">导出</button>
					      	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					      	<button name="resetting" id="newreset" type="button" class="btn btn-danger" onclick="fomrReset();">重置</button>
						</div><!-- /.row -->
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
						<th colspan="2" style="text-align:center;">业务笔数（保单件数）</th>
						<th colspan="2" style="text-align:center;">保障额度/赔偿限额</th>
						<th colspan="2" style="text-align:center;">保费金额</th>
						<th colspan="2" style="text-align:center;">应付保费</th>
						<th colspan="2" style="text-align:center;">代理佣金</th>
						<th colspan="4" style="text-align:center;">自营网络平台渠道</th>
						<th colspan="4" style="text-align:center;">第三方网络平台渠道</th>
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
						<th colspan="2" style="text-align:center;">保费金额</th>
						<th colspan="2" style="text-align:center;">代理佣金</th>
						<th colspan="2" style="text-align:center;">保费金额</th>
						<th colspan="2" style="text-align:center;">代理佣金</th>
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
					</tr>
				</thead>
				<tbody>
					<%-- <c:forEach var="agentProperty" items="${list}"
						varStatus="index">
						<tr>
							<td>${agentProperty.risk_name}</td>
							<td class="FixedTd">${index.index+1}</td>
							<td>${agentProperty.premium}</td>
							<td>${agentProperty.premium_payable}</td>
							<td>${agentProperty.agentcommission}</td>
							<td>${agentProperty.autotrophy_premium}</td>
							<td>${agentProperty.autotrophy_agentcommission}</td>
							<td>${agentProperty.thirdparty_premium}</td>
							<td>${agentProperty.thirdparty_agentcommission}</td>
							<td>${agentProperty.crt_date}</td>
						</tr>
					</c:forEach> --%>
					<tr>
						<td>1、企业财产保险</td>
						<td class="FixedTd">1</td>
						<td>${agentProperty1.total_policy_count}</td>
						<td>${agentProperty1.total_policycount_sum}</td>
						<td>${agentProperty1.total_amount}</td>
						<td>${agentProperty1.total_amount_sum}</td>
						<td>${agentProperty1.total_permium}</td>
						<td>${agentProperty1.total_permium_sum}</td>
						<td>${agentProperty1.total_ppayable}</td>
						<td>${agentProperty1.total_ppayable_sum}</td>
						<td>${agentProperty1.total_agcommiss}</td>
						<td>${agentProperty1.total_agcommiss_sum}</td>
						<td>${agentProperty1.total_autoprem}</td>
						<td>${agentProperty1.total_autoprem_sum}</td>
						<td>${agentProperty1.total_autoagcommiss}</td>
						<td>${agentProperty1.total_autoagcommiss_sum}</td>
						<td>${agentProperty1.total_trdprem}</td>
						<td>${agentProperty1.total_trdprem_sum}</td>
						<td>${agentProperty1.total_trdagcommiss}</td>
						<td>${agentProperty1.total_trdagcommiss_sum}</td>
					</tr> 
					<tr>
						<td>2、家庭财产保险</td>
						<td class="FixedTd">2</td>
						<td>${agentProperty2.total_policy_count}</td>
						<td>${agentProperty2.total_policycount_sum}</td>
						<td>${agentProperty2.total_amount}</td>
						<td>${agentProperty2.total_amount_sum}</td>
						<td>${agentProperty2.total_permium}</td>
						<td>${agentProperty2.total_permium_sum}</td>
						<td>${agentProperty2.total_ppayable}</td>
						<td>${agentProperty2.total_ppayable_sum}</td>
						<td>${agentProperty2.total_agcommiss}</td>
						<td>${agentProperty2.total_agcommiss_sum}</td>
						<td>${agentProperty2.total_autoprem}</td>
						<td>${agentProperty2.total_autoprem_sum}</td>
						<td>${agentProperty2.total_autoagcommiss}</td>
						<td>${agentProperty2.total_autoagcommiss_sum}</td>
						<td>${agentProperty2.total_trdprem}</td>
						<td>${agentProperty2.total_trdprem_sum}</td>
						<td>${agentProperty2.total_trdagcommiss}</td>
						<td>${agentProperty2.total_trdagcommiss_sum}</td>
					</tr> 
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp;其中：投资型家财险</td>
						<td class="FixedTd">3</td>
						<td>${agentProperty21.total_policy_count}</td>
						<td>${agentProperty21.total_policycount_sum}</td>
						<td>${agentProperty21.total_amount}</td>
						<td>${agentProperty21.total_amount_sum}</td>
						<td>${agentProperty21.total_permium}</td>
						<td>${agentProperty21.total_permium_sum}</td>
						<td>${agentProperty21.total_ppayable}</td>
						<td>${agentProperty21.total_ppayable_sum}</td>
						<td>${agentProperty21.total_agcommiss}</td>
						<td>${agentProperty21.total_agcommiss_sum}</td>
						<td>${agentProperty21.total_autoprem}</td>
						<td>${agentProperty21.total_autoprem_sum}</td>
						<td>${agentProperty21.total_autoagcommiss}</td>
						<td>${agentProperty21.total_autoagcommiss_sum}</td>
						<td>${agentProperty21.total_trdprem}</td>
						<td>${agentProperty21.total_trdprem_sum}</td>
						<td>${agentProperty21.total_trdagcommiss}</td>
						<td>${agentProperty21.total_trdagcommiss_sum}</td>						
					</tr> 
					<tr>
						<td>3、机动车辆保险</td>
						<td class="FixedTd">4</td>
						<td>${agentProperty3.total_policy_count}</td>
						<td>${agentProperty3.total_policycount_sum}</td>
						<td>${agentProperty3.total_amount}</td>
						<td>${agentProperty3.total_amount_sum}</td>
						<td>${agentProperty3.total_permium}</td>
						<td>${agentProperty3.total_permium_sum}</td>
						<td>${agentProperty3.total_ppayable}</td>
						<td>${agentProperty3.total_ppayable_sum}</td>
						<td>${agentProperty3.total_agcommiss}</td>
						<td>${agentProperty3.total_agcommiss_sum}</td>
						<td>${agentProperty3.total_autoprem}</td>
						<td>${agentProperty3.total_autoprem_sum}</td>
						<td>${agentProperty3.total_autoagcommiss}</td>
						<td>${agentProperty3.total_autoagcommiss_sum}</td>
						<td>${agentProperty3.total_trdprem}</td>
						<td>${agentProperty3.total_trdprem_sum}</td>
						<td>${agentProperty3.total_trdagcommiss}</td>
						<td>${agentProperty3.total_trdagcommiss_sum}</td>
					</tr> 
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp;其中：交强险</td>
						<td>5</td>
						<td>${agentProperty31.total_policy_count}</td>
						<td>${agentProperty31.total_policycount_sum}</td>
						<td>${agentProperty31.total_amount}</td>
						<td>${agentProperty31.total_amount_sum}</td>
						<td>${agentProperty31.total_permium}</td>
						<td>${agentProperty31.total_permium_sum}</td>
						<td>${agentProperty31.total_ppayable}</td>
						<td>${agentProperty31.total_ppayable_sum}</td>
						<td>${agentProperty31.total_agcommiss}</td>
						<td>${agentProperty31.total_agcommiss_sum}</td>
						<td>${agentProperty31.total_autoprem}</td>
						<td>${agentProperty31.total_autoprem_sum}</td>
						<td>${agentProperty31.total_autoagcommiss}</td>
						<td>${agentProperty31.total_autoagcommiss_sum}</td>
						<td>${agentProperty31.total_trdprem}</td>
						<td>${agentProperty31.total_trdprem_sum}</td>
						<td>${agentProperty31.total_trdagcommiss}</td>
						<td>${agentProperty31.total_trdagcommiss_sum}</td>
					</tr> 
					<tr>
						<td>4、工程保险</td>
						<td>6</td>
						<td>${agentProperty4.total_policy_count}</td>
						<td>${agentProperty4.total_policycount_sum}</td>
						<td>${agentProperty4.total_amount}</td>
						<td>${agentProperty4.total_amount_sum}</td>
						<td>${agentProperty4.total_permium}</td>
						<td>${agentProperty4.total_permium_sum}</td>
						<td>${agentProperty4.total_ppayable}</td>
						<td>${agentProperty4.total_ppayable_sum}</td>
						<td>${agentProperty4.total_agcommiss}</td>
						<td>${agentProperty4.total_agcommiss_sum}</td>
						<td>${agentProperty4.total_autoprem}</td>
						<td>${agentProperty4.total_autoprem_sum}</td>
						<td>${agentProperty4.total_autoagcommiss}</td>
						<td>${agentProperty4.total_autoagcommiss_sum}</td>
						<td>${agentProperty4.total_trdprem}</td>
						<td>${agentProperty4.total_trdprem_sum}</td>
						<td>${agentProperty4.total_trdagcommiss}</td>
						<td>${agentProperty4.total_trdagcommiss_sum}</td>
					</tr> 
					<tr>
						<td>5、责任保险</td>
						<td>7</td>
						<td>${agentProperty5.total_policy_count}</td>
						<td>${agentProperty5.total_policycount_sum}</td>
						<td>${agentProperty5.total_amount}</td>
						<td>${agentProperty5.total_amount_sum}</td>
						<td>${agentProperty5.total_permium}</td>
						<td>${agentProperty5.total_permium_sum}</td>
						<td>${agentProperty5.total_ppayable}</td>
						<td>${agentProperty5.total_ppayable_sum}</td>
						<td>${agentProperty5.total_agcommiss}</td>
						<td>${agentProperty5.total_agcommiss_sum}</td>
						<td>${agentProperty5.total_autoprem}</td>
						<td>${agentProperty5.total_autoprem_sum}</td>
						<td>${agentProperty5.total_autoagcommiss}</td>
						<td>${agentProperty5.total_autoagcommiss_sum}</td>
						<td>${agentProperty5.total_trdprem}</td>
						<td>${agentProperty5.total_trdprem_sum}</td>
						<td>${agentProperty5.total_trdagcommiss}</td>
						<td>${agentProperty5.total_trdagcommiss_sum}</td>
					</tr> 
					<tr>
						<td>6、信用保险</td>
						<td>8</td>
						<td>${agentProperty6.total_policy_count}</td>
						<td>${agentProperty6.total_policycount_sum}</td>
						<td>${agentProperty6.total_amount}</td>
						<td>${agentProperty6.total_amount_sum}</td>
						<td>${agentProperty6.total_permium}</td>
						<td>${agentProperty6.total_permium_sum}</td>
						<td>${agentProperty6.total_ppayable}</td>
						<td>${agentProperty6.total_ppayable_sum}</td>
						<td>${agentProperty6.total_agcommiss}</td>
						<td>${agentProperty6.total_agcommiss_sum}</td>
						<td>${agentProperty6.total_autoprem}</td>
						<td>${agentProperty6.total_autoprem_sum}</td>
						<td>${agentProperty6.total_autoagcommiss}</td>
						<td>${agentProperty6.total_autoagcommiss_sum}</td>
						<td>${agentProperty6.total_trdprem}</td>
						<td>${agentProperty6.total_trdprem_sum}</td>
						<td>${agentProperty6.total_trdagcommiss}</td>
						<td>${agentProperty6.total_trdagcommiss_sum}</td>
					</tr> 
					<tr>
						<td>7、保证保险</td>
						<td>9</td>
						<td>${agentProperty7.total_policy_count}</td>
						<td>${agentProperty7.total_policycount_sum}</td>
						<td>${agentProperty7.total_amount}</td>
						<td>${agentProperty7.total_amount_sum}</td>
						<td>${agentProperty7.total_permium}</td>
						<td>${agentProperty7.total_permium_sum}</td>
						<td>${agentProperty7.total_ppayable}</td>
						<td>${agentProperty7.total_ppayable_sum}</td>
						<td>${agentProperty7.total_agcommiss}</td>
						<td>${agentProperty7.total_agcommiss_sum}</td>
						<td>${agentProperty7.total_autoprem}</td>
						<td>${agentProperty7.total_autoprem_sum}</td>
						<td>${agentProperty7.total_autoagcommiss}</td>
						<td>${agentProperty7.total_autoagcommiss_sum}</td>
						<td>${agentProperty7.total_trdprem}</td>
						<td>${agentProperty7.total_trdprem_sum}</td>
						<td>${agentProperty7.total_trdagcommiss}</td>
						<td>${agentProperty7.total_trdagcommiss_sum}</td>

					</tr> 
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp;其中：机动车辆消费贷款保证保险</td>
						<td>10</td>
						<td>${agentProperty71.total_policy_count}</td>
						<td>${agentProperty71.total_policycount_sum}</td>
						<td>${agentProperty71.total_amount}</td>
						<td>${agentProperty71.total_amount_sum}</td>
						<td>${agentProperty71.total_permium}</td>
						<td>${agentProperty71.total_permium_sum}</td>
						<td>${agentProperty71.total_ppayable}</td>
						<td>${agentProperty71.total_ppayable_sum}</td>
						<td>${agentProperty71.total_agcommiss}</td>
						<td>${agentProperty71.total_agcommiss_sum}</td>
						<td>${agentProperty71.total_autoprem}</td>
						<td>${agentProperty71.total_autoprem_sum}</td>
						<td>${agentProperty71.total_autoagcommiss}</td>
						<td>${agentProperty71.total_autoagcommiss_sum}</td>
						<td>${agentProperty71.total_trdprem}</td>
						<td>${agentProperty71.total_trdprem_sum}</td>
						<td>${agentProperty71.total_trdagcommiss}</td>
						<td>${agentProperty71.total_trdagcommiss_sum}</td>
					</tr> 
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp;其中：个人贷款抵押房屋保证保险</td>
						<td>11</td>
						<td>${agentProperty72.total_policy_count}</td>
						<td>${agentProperty72.total_policycount_sum}</td>
						<td>${agentProperty72.total_amount}</td>
						<td>${agentProperty72.total_amount_sum}</td>
						<td>${agentProperty72.total_permium}</td>
						<td>${agentProperty72.total_permium_sum}</td>
						<td>${agentProperty72.total_ppayable}</td>
						<td>${agentProperty72.total_ppayable_sum}</td>
						<td>${agentProperty72.total_agcommiss}</td>
						<td>${agentProperty72.total_agcommiss_sum}</td>
						<td>${agentProperty72.total_autoprem}</td>
						<td>${agentProperty72.total_autoprem_sum}</td>
						<td>${agentProperty72.total_autoagcommiss}</td>
						<td>${agentProperty72.total_autoagcommiss_sum}</td>
						<td>${agentProperty72.total_trdprem}</td>
						<td>${agentProperty72.total_trdprem_sum}</td>
						<td>${agentProperty72.total_trdagcommiss}</td>
						<td>${agentProperty72.total_trdagcommiss_sum}</td>
					</tr> 
					<tr>
						<td>8、船舶保险</td>
						<td>12</td>
						<td>${agentProperty8.total_policy_count}</td>
						<td>${agentProperty8.total_policycount_sum}</td>
						<td>${agentProperty8.total_amount}</td>
						<td>${agentProperty8.total_amount_sum}</td>
						<td>${agentProperty8.total_permium}</td>
						<td>${agentProperty8.total_permium_sum}</td>
						<td>${agentProperty8.total_ppayable}</td>
						<td>${agentProperty8.total_ppayable_sum}</td>
						<td>${agentProperty8.total_agcommiss}</td>
						<td>${agentProperty8.total_agcommiss_sum}</td>
						<td>${agentProperty8.total_autoprem}</td>
						<td>${agentProperty8.total_autoprem_sum}</td>
						<td>${agentProperty8.total_autoagcommiss}</td>
						<td>${agentProperty8.total_autoagcommiss_sum}</td>
						<td>${agentProperty8.total_trdprem}</td>
						<td>${agentProperty8.total_trdprem_sum}</td>
						<td>${agentProperty8.total_trdagcommiss}</td>
						<td>${agentProperty8.total_trdagcommiss_sum}</td>
					</tr>
					<tr>
						<td>9、货物运输保险</td>
						<td>13</td>
						<td>${agentProperty9.total_policy_count}</td>
						<td>${agentProperty9.total_policycount_sum}</td>
						<td>${agentProperty9.total_amount}</td>
						<td>${agentProperty9.total_amount_sum}</td>
						<td>${agentProperty9.total_permium}</td>
						<td>${agentProperty9.total_permium_sum}</td>
						<td>${agentProperty9.total_ppayable}</td>
						<td>${agentProperty9.total_ppayable_sum}</td>
						<td>${agentProperty9.total_agcommiss}</td>
						<td>${agentProperty9.total_agcommiss_sum}</td>
						<td>${agentProperty9.total_autoprem}</td>
						<td>${agentProperty9.total_autoprem_sum}</td>
						<td>${agentProperty9.total_autoagcommiss}</td>
						<td>${agentProperty9.total_autoagcommiss_sum}</td>
						<td>${agentProperty9.total_trdprem}</td>
						<td>${agentProperty9.total_trdprem_sum}</td>
						<td>${agentProperty9.total_trdagcommiss}</td>
						<td>${agentProperty9.total_trdagcommiss_sum}</td>
					</tr>
					<tr>
						<td>10、特殊风险保险</td>
						<td>14</td>
						<td>${agentProperty10.total_policy_count}</td>
						<td>${agentProperty10.total_policycount_sum}</td>
						<td>${agentProperty10.total_amount}</td>
						<td>${agentProperty10.total_amount_sum}</td>
						<td>${agentProperty10.total_permium}</td>
						<td>${agentProperty10.total_permium_sum}</td>
						<td>${agentProperty10.total_ppayable}</td>
						<td>${agentProperty10.total_ppayable_sum}</td>
						<td>${agentProperty10.total_agcommiss}</td>
						<td>${agentProperty10.total_agcommiss_sum}</td>
						<td>${agentProperty10.total_autoprem}</td>
						<td>${agentProperty10.total_autoprem_sum}</td>
						<td>${agentProperty10.total_autoagcommiss}</td>
						<td>${agentProperty10.total_autoagcommiss_sum}</td>
						<td>${agentProperty10.total_trdprem}</td>
						<td>${agentProperty10.total_trdprem_sum}</td>
						<td>${agentProperty10.total_trdagcommiss}</td>
						<td>${agentProperty10.total_trdagcommiss_sum}</td>
					</tr>
					<tr>
						<td>11、农业保险</td>
						<td>15</td>
						<td>${agentProperty11.total_policy_count}</td>
						<td>${agentProperty11.total_policycount_sum}</td>
						<td>${agentProperty11.total_amount}</td>
						<td>${agentProperty11.total_amount_sum}</td>
						<td>${agentProperty11.total_permium}</td>
						<td>${agentProperty11.total_permium_sum}</td>
						<td>${agentProperty11.total_ppayable}</td>
						<td>${agentProperty11.total_ppayable_sum}</td>
						<td>${agentProperty11.total_agcommiss}</td>
						<td>${agentProperty11.total_agcommiss_sum}</td>
						<td>${agentProperty11.total_autoprem}</td>
						<td>${agentProperty11.total_autoprem_sum}</td>
						<td>${agentProperty11.total_autoagcommiss}</td>
						<td>${agentProperty11.total_autoagcommiss_sum}</td>
						<td>${agentProperty11.total_trdprem}</td>
						<td>${agentProperty11.total_trdprem_sum}</td>
						<td>${agentProperty11.total_trdagcommiss}</td>
						<td>${agentProperty11.total_trdagcommiss_sum}</td>
					</tr>
					<tr>
						<td>12、健康险</td>
						<td>16</td>
						<td>${agentProperty12.total_policy_count}</td>
						<td>${agentProperty12.total_policycount_sum}</td>
						<td>${agentProperty12.total_amount}</td>
						<td>${agentProperty12.total_amount_sum}</td>
						<td>${agentProperty12.total_permium}</td>
						<td>${agentProperty12.total_permium_sum}</td>
						<td>${agentProperty12.total_ppayable}</td>
						<td>${agentProperty12.total_ppayable_sum}</td>
						<td>${agentProperty12.total_agcommiss}</td>
						<td>${agentProperty12.total_agcommiss_sum}</td>
						<td>${agentProperty12.total_autoprem}</td>
						<td>${agentProperty12.total_autoprem_sum}</td>
						<td>${agentProperty12.total_autoagcommiss}</td>
						<td>${agentProperty12.total_autoagcommiss_sum}</td>
						<td>${agentProperty12.total_trdprem}</td>
						<td>${agentProperty12.total_trdprem_sum}</td>
						<td>${agentProperty12.total_trdagcommiss}</td>
						<td>${agentProperty12.total_trdagcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp;其中：投资型健康险</td>
						<td>17</td>
						<td>${agentProperty121.total_policy_count}</td>
						<td>${agentProperty121.total_policycount_sum}</td>
						<td>${agentProperty121.total_amount}</td>
						<td>${agentProperty121.total_amount_sum}</td>
						<td>${agentProperty121.total_permium}</td>
						<td>${agentProperty121.total_permium_sum}</td>
						<td>${agentProperty121.total_ppayable}</td>
						<td>${agentProperty121.total_ppayable_sum}</td>
						<td>${agentProperty121.total_agcommiss}</td>
						<td>${agentProperty121.total_agcommiss_sum}</td>
						<td>${agentProperty121.total_autoprem}</td>
						<td>${agentProperty121.total_autoprem_sum}</td>
						<td>${agentProperty121.total_autoagcommiss}</td>
						<td>${agentProperty121.total_autoagcommiss_sum}</td>
						<td>${agentProperty121.total_trdprem}</td>
						<td>${agentProperty121.total_trdprem_sum}</td>
						<td>${agentProperty121.total_trdagcommiss}</td>
						<td>${agentProperty121.total_trdagcommiss_sum}</td>
					</tr>
					<tr>
						<td>13、意外伤害保险</td>
						<td>18</td>
						<td>${agentProperty13.total_policy_count}</td>
						<td>${agentProperty13.total_policycount_sum}</td>
						<td>${agentProperty13.total_amount}</td>
						<td>${agentProperty13.total_amount_sum}</td>
						<td>${agentProperty13.total_permium}</td>
						<td>${agentProperty13.total_permium_sum}</td>
						<td>${agentProperty13.total_ppayable}</td>
						<td>${agentProperty13.total_ppayable_sum}</td>
						<td>${agentProperty13.total_agcommiss}</td>
						<td>${agentProperty13.total_agcommiss_sum}</td>
						<td>${agentProperty13.total_autoprem}</td>
						<td>${agentProperty13.total_autoprem_sum}</td>
						<td>${agentProperty13.total_autoagcommiss}</td>
						<td>${agentProperty13.total_autoagcommiss_sum}</td>
						<td>${agentProperty13.total_trdprem}</td>
						<td>${agentProperty13.total_trdprem_sum}</td>
						<td>${agentProperty13.total_trdagcommiss}</td>
						<td>${agentProperty13.total_trdagcommiss_sum}</td>
					</tr>
					<tr>
						<td>&nbsp; &nbsp; &nbsp; &nbsp;其中：投资型意外险</td>
						<td>19</td>
						<td>${agentProperty131.total_policy_count}</td>
						<td>${agentProperty131.total_policycount_sum}</td>
						<td>${agentProperty131.total_amount}</td>
						<td>${agentProperty131.total_amount_sum}</td>
						<td>${agentProperty131.total_permium}</td>
						<td>${agentProperty131.total_permium_sum}</td>
						<td>${agentProperty131.total_ppayable}</td>
						<td>${agentProperty131.total_ppayable_sum}</td>
						<td>${agentProperty131.total_agcommiss}</td>
						<td>${agentProperty131.total_agcommiss_sum}</td>
						<td>${agentProperty131.total_autoprem}</td>
						<td>${agentProperty131.total_autoprem_sum}</td>
						<td>${agentProperty131.total_autoagcommiss}</td>
						<td>${agentProperty131.total_autoagcommiss_sum}</td>
						<td>${agentProperty131.total_trdprem}</td>
						<td>${agentProperty131.total_trdprem_sum}</td>
						<td>${agentProperty131.total_trdagcommiss}</td>
						<td>${agentProperty131.total_trdagcommiss_sum}</td>
					</tr>
					<tr>
						<td>14、其他险</td>
						<td>20</td>
						<td>${agentProperty14.total_policy_count}</td>
						<td>${agentProperty14.total_policycount_sum}</td>
						<td>${agentProperty14.total_amount}</td>
						<td>${agentProperty14.total_amount_sum}</td>
						<td>${agentProperty14.total_permium}</td>
						<td>${agentProperty14.total_permium_sum}</td>
						<td>${agentProperty14.total_ppayable}</td>
						<td>${agentProperty14.total_ppayable_sum}</td>
						<td>${agentProperty14.total_agcommiss}</td>
						<td>${agentProperty14.total_agcommiss_sum}</td>
						<td>${agentProperty14.total_autoprem}</td>
						<td>${agentProperty14.total_autoprem_sum}</td>
						<td>${agentProperty14.total_autoagcommiss}</td>
						<td>${agentProperty14.total_autoagcommiss_sum}</td>
						<td>${agentProperty14.total_trdprem}</td>
						<td>${agentProperty14.total_trdprem_sum}</td>
						<td>${agentProperty14.total_trdagcommiss}</td>
						<td>${agentProperty14.total_trdagcommiss_sum}</td>
					</tr>
					<tr>
						<td>总计</td>
						<td>21</td>
						<td>${agentProperty.total_policy_count}</td>
						<td>${agentProperty.total_policycount_sum}</td>
						<td>${agentProperty.total_amount}</td>
						<td>${agentProperty.total_amount_sum}</td>
						<td>${agentProperty.total_permium}</td>
						<td>${agentProperty.total_permium_sum}</td>
						<td>${agentProperty.total_ppayable}</td>
						<td>${agentProperty.total_ppayable_sum}</td>
						<td>${agentProperty.total_agcommiss}</td>
						<td>${agentProperty.total_agcommiss_sum}</td>
						<td>${agentProperty.total_autoprem}</td>
						<td>${agentProperty.total_autoprem_sum}</td>
						<td>${agentProperty.total_autoagcommiss}</td>
						<td>${agentProperty.total_autoagcommiss_sum}</td>
						<td>${agentProperty.total_trdprem}</td>
						<td>${agentProperty.total_trdprem_sum}</td>
						<td>${agentProperty.total_trdagcommiss}</td>
						<td>${agentProperty.total_trdagcommiss_sum}</td>
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
