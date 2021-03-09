<%@page import="com.newtouch.component.c11assistant.ServletHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
	int line = 12;

%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<!--  收缩 -->
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean-ZM.css" >
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >
		
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery.form.js"></script>
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		
		<!-- fram plugins start--> 
		<script type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/pagination/jquery.pagination.js"></script>
		<!--  收缩 -->
		<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
		<!-- 数据校验 -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/compent/newtouch/util/validation.css">
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/newtouch-validate.js"></script>
        <script >    
			        $(document).ready(function() {
					    $("#mainForm").validate({
							 rules:{
								 reportType:{
									 required:true
								 },
								 branch_name : {  //机构名称
				    					required : true
				    			 },
				    			 firstDateJbb:{
								required:true
							    }
							 },
							 onkeyup:false
						 });
				     });
				   
			
				function isUndefined(paraValue){
			     if(paraValue==null||paraValue==undefined||paraValue=="") return true;
			     return false;
			 }
				function defaultQuery(){
					document.queryForm.submit;
				}
			
			//导出查询数据
			function exportInfo(){
				//获取查询条件的值
				var firstDateJbb = $('#firstDateJbb').val();
				var branch_id= $('#branch_id').val();
					//为隐藏域赋值
					$('#firstDateJbb').val(firstDateJbb);
					$('#branch_id').val(branch_id);
					//提交表单
					$('#exportForm').submit();
			}
			
			  function importInfo(){
				//获取查询条件的值
					var branch_id = $('#branch_id').val();
				
						$('#exportForm').submit();
			   	} 
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>报表管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保险代理机构业务汇总表</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="mainForm" name="mainForm" enctype="multipart/form-data" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/RFM/Report/queryReport.do" method="POST">
					<!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
						<image id="dialog_img" >${rmHelper.msgStr}</image>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag name="reportType" id="reportType" value='YWHZB' displayLable="报表类型" />
					
					<div class="row" >
						<webTag:Text name="reportType1" id="reportType1" value='保险代理机构业务汇总表' displayLable="报表类型" isdisplay="true" readonly="true"/>
					</div>
					
					<div class="row" id="ywbbcx">
<%-- 						<jsp:include page="branchTreeRequired.jsp" flush="true"/> --%>
						<jsp:include page="branchTree4Level2.jsp" flush="true"/>
						<webTag:MonthDate id="firstDateJbb"   name="firstDateJbb"   value='${firstDateJbb}'   displayLable="统计月份" isdisplay="true" />
					</div>
					
					<div class="row" style="text-align:right;">
				       	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查看报表</button>
				    	<button type="button" class="btn btn-danger" onClick="exportInfo();" ><i class="icon-download icon-white"></i>导出报表</button>
						<a class="btn btn-danger" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/rfm/insuranceReport/Report"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
				<form id="exportForm" action="<%=basePath %>/rfm/BusinessReport/exportBusinessReport.do" method="POST">
				    <webTag:HiddenInputTag id="firstDateJbb" name="firstDateJbb"  value='${firstDateJbb}'></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag id="branch_id" name="branch_id"  value='${rmHelper.returnParams.branch_id}'></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag id="branch_name" name="branch_name"  value='${rmHelper.returnParams.branch_name}'></webTag:HiddenInputTag>
				</form>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
							<th rowspan="2" style="text-align:center;vertical-align:middle;">行次</th>
							<th rowspan="2" style="text-align:center;vertical-align:middle;">分支机构(个)</th>
							<th colspan="2" style="text-align:center;vertical-align:middle;">公司员工人数(人)</th>
							<th colspan="2" style="text-align:center;">保单件数（件）</th>
							<th colspan="2" style="text-align:center;">保费金额</th>
							<th colspan="2" style="text-align:center;">未解付保费</th>
							<th colspan="2" style="text-align:center;">代理手续费</th>
							<th colspan="2" style="text-align:center;vertical-align:middle;">利润</th>
							<th rowspan="2" style="text-align:center;vertical-align:middle;">注册资本</th>
							<th rowspan="2" style="text-align:center;vertical-align:middle;">资产总额</th>
						</tr>
						<tr>
							<th>现有人数</th>
							<th>同比(+/-)</th>
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
					<tbody id="commission_info">
						 <c:forEach var="oa" items="${rmHelper.returnMsg.dataList}" varStatus="index">
							   		<tr>	
								   		<td>${index.index+1}</td>
										<td>${oa.branch_name}</td>
										<td>${oa.person_num}</td>
										<td>${oa.person_com}</td>
										<td>${oa.period_num}</td>
										<td>${oa.total_num}</td>
										<td>${oa.period_prem}</td>
										<td>${oa.total_prem}</td>
										<td>0</td>
										<td>0</td>
										<td>${oa.period_fee}</td>
										<td>${oa.total_fee}</td>
										<td>0</td>
										<td>0</td>
										<td>0</td>
										<td>0</td>
									</tr>	
					    </c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>