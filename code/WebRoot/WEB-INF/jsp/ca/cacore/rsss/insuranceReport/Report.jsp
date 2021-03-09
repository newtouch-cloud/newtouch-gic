<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
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
		<script type="text/javascript" src="<%=basePath%>/compent/xinzhi/js/report.js"></script>
	</head>
	<body style="height: 750px">
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>报表管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>报表查询</span>
			</div>
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" enctype="multipart/form-data" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/RFM/Report/queryReport.do" method="POST">
					<!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
						<image id="dialog_img" >${rmHelper.msgStr}</image>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>

					
					<div class="row">
						<webTag:DynamicSelectTag src="reportSelect" name="reportType" id="reportType" value='${rmHelper.returnParams.reportType}' displayLable="报表类型" isdisplay="true"/>
<%-- 						<jsp:include page="branchTreeRequired.jsp" flush="true"/> --%>
						<jsp:include page="branchTree4Level2.jsp" flush="true"/>
					</div>
					
					<div class="row" id="ywbbcx">
						
						<webTag:MonthDate id="firstDate"   name="firstDate"   value='${rmHelper.returnParams.firstDate}'   displayLable="统计月份" isdisplay="true" />
					</div>
					
					<div class="row" id="jbqkb">
						<webTag:MonthDate id="firstDateJbb"   name="firstDateJbb"   value='${rmHelper.returnParams.firstDateJbb}'   displayLable="统计月份" isdisplay="true" />
					</div>
					
					
					
					<div class="row" id="date">
						<webTag:Date name="firstDate" id="firstDate" displayLable="统计日期 从" value='${rmHelper.returnParams.firstDate}'/>
						<webTag:Date name="secondDate" id="secondDate" displayLable="到" value='${rmHelper.returnParams.secondDate}'/>
					</div>
					
					<div class="row" id="statistic">
						<webTag:Text name="statistic_year" id="statistic_year" value='${rmHelper.returnParams.statistic_year}' displayLable="统计年份"></webTag:Text>
                        <webTag:DynamicSelectTag src="statisticMonthSelect" name="statistic_month" id="statistic_month" value='${rmHelper.returnParams.statistic_month}' displayLable="统计季度"></webTag:DynamicSelectTag>
					</div>
					
					<div class="row" style="text-align:right;">
				       	<button type="submit" class="btn btn-danger"  ><i class="icon-search icon-white"></i>查看报表</button>
<!-- 				    	<button type="button" class="btn btn-danger" onClick="exportInfo();" ><i class="icon-download icon-white"></i>导出报表</button> -->
					</div>
				</form>
				<!-- 导出传值form表单  start-->
				<form id="exportForm" action="<%=basePath %>/RFM/Report/exportReport.do" method="POST">
						<input type="hidden" id="b_id" name="branch_id" />
						<input type="hidden" id="s_year" name="statistic_year" />
						<input type="hidden" id="s_month" name="statistic_month" />
				</form>
			</div>
		</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 底部高度填充块 结束-->
	</body>
</html>
