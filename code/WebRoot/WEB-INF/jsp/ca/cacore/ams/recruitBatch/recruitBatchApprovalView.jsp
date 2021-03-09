<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
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
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批处理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>招募批次审批明细</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id= "mainForm" class="form-horizontal alert alert-info fade in span12" action="" method="POST">
					<fieldset>
						<webTag:HiddenInputTag id="flag" name="flag" value=""    displayLable="异步请求结果标志"/>
						<div class="dreadcount">
							<span><i class="icon-ziliao icon-red mrl14"></i> 审批信息</span>
						</div>
						<div class="row">
						    <webTag:Text id="application_time" name="application_time" value='${rmHelper.returnParams.application_time}' readonly="true" displayLable="审批申请时间:" />
						    <webTag:Text id="approval_time" name="approval_time" value='${rmHelper.returnParams.approval_time}' readonly="true" displayLable="审批时间:" />
						    <webTag:DynamicSelectTag src="approvalStatusSelect" name="approval_status" id="approval_status" value='${rmHelper.returnParams.approval_status}' displayLable="审批状态:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
						</div>
						<div class="dreadcount">
							<span><i class="icon-ziliao icon-red mrl14"></i> 招募批次信息</span>
						</div>
						
			            <div class="row">
						    <webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' readonly="true" displayLable="所属机构代码" isdisplay="true"/>
						    <webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' readonly="true" displayLable="所属机构名称" isdisplay="true"/>
						    <webTag:Text id="batch_id" name="batch_id" value='${rmHelper.returnParams.batch_id}' readonly="true" isdisplay="true" displayLable="批次号"/>
						</div>
						<div class="row">
							<webTag:Text id="batch_name" name="batch_name" value='${rmHelper.returnParams.batch_name}' readonly="true" isdisplay="true" displayLable="批次名称"/>
							<webTag:Text id="start_time" name="start_time" value='${rmHelper.returnParams.start_time}' readonly="true" isdisplay="true" displayLable="开始时间"/>
							<webTag:Text id="end_time" name="end_time" value='${rmHelper.returnParams.end_time}' readonly="true" isdisplay="true" displayLable="结束时间"/>
						</div>
						<div class="row">
							<webTag:Text id="plan_recruitNum" name="plan_recruitNum" value='${rmHelper.returnParams.plan_recruitNum}' isdisplay="true" displayLable="计划招聘人数" readonly="true"/>
							<webTag:DynamicSelectTag src="recruitBatchStatusSelect" id="batch_status" name="batch_status" value='${rmHelper.returnParams.batch_status}' isdisplay="true" displayLable="批次状态"  onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						</div>
						<div class="row">
							<webTag:TextareaTag id="remark" name="remark" rows='5' value='${rmHelper.returnParams.remark}' displayLable="备注:" readonly="true"/>
						</div>
						<div class="row">
							<webTag:TextareaTag id="approval_views" name="approval_views" rows='5' value='${rmHelper.returnParams.approval_views}' displayLable="审批意见:" readonly="true"/>
						</div>
					   <div class="row" align="right">
			           		<a class="btn btn-danger" href="<%=basePath %>/Ams/ApprovalManage/approvalViewQuery.do" ><i class="icon-share-alt icon-white"></i>返回</a>
					   </div>
					</fieldset>
				</form>
			</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>