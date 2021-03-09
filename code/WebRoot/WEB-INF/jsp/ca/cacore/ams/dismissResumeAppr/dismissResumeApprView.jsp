<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批明细查询</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>解约恢复审批明细</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="" method="POST">
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 审批信息</span>
					</div>
					<div class="row">
					    <webTag:Text id="application_time" name="application_time" value='${rmHelper.returnParams.application_time}' displayLable="审批申请日期:" readonly="true"/>
						<webTag:Date id="approval_time" name="approval_time" value='${rmHelper.returnParams.approval_time}' displayLable="审批日期:" readonly="true"/>
					    <webTag:DynamicSelectTag src="approvalStatusSelect" name="approval_status" id="approval_status" value='${rmHelper.returnParams.approval_status}' displayLable="审批状态:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 基本信息</span>
					</div>
					<div class="row">
					    <webTag:Text id="sales_id" name="sales_id" value='${rmHelper.returnParams.sales_id}' displayLable="人员代码" readonly="true" isdisplay="true"/>
					 	<webTag:Text id="sales_name" name="sales_name" value='${rmHelper.returnParams.sales_name}' displayLable="人员姓名" readonly="true" isdisplay="true"/>
						<webTag:Text name="rank_name" id="rank_name" value='${rmHelper.returnParams.rank_name}' displayLable="职级" readonly="true" isdisplay="true"></webTag:Text>
						<webTag:HiddenInputTag name="rank_id" id="rank_id" value='${rmHelper.returnParams.rank_id}'/>
					</div>
					<div class="row">
					    <webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="所属机构代码" readonly="true" isdisplay="true"/>
					    <webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="所属机构名称:" readonly="true"/>
					</div>
					<div class="row">
					    <webTag:Text id="team_id" name="team_id" value='${rmHelper.returnParams.team_id}' displayLable="团队代码" readonly="true" isdisplay="true"/>
					    <webTag:Text id="team_name" name="team_name" value='${rmHelper.returnParams.team_name}' displayLable="团队名称:" readonly="true"/>
					    <webTag:DynamicSelectTag src="channelSelect" id="team_lvl" name="team_lvl" value='${rmHelper.returnParams.team_lvl}' displayLable="团队级别:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 恢复信息</span>
					</div>
				    <div class="row">
					    <webTag:DynamicSelectTag src="salesStatusSelect" name="status" id="status" value='${rmHelper.returnParams.status}' displayLable="离职恢复后状态" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
					    <webTag:TextareaTag id="resume_description" name="resume_description"  rows="5" value='${rmHelper.returnParams.resume_description}' displayLable="恢复原因:" readonly="true"/>
					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 审批意见</span>
					</div>
					<div class="row">
						<webTag:TextareaTag id="approval_views" name="approval_views"  rows='5'  value='${rmHelper.returnParams.approval_views}' displayLable="审批意见:" readonly="true"/>
					</div>
					<div class="row" align="right">
					  <a class="btn btn-danger" href="<%=basePath %>/Ams/ApprovalManage/approvalViewQuery.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
		 </div>
	</body>
</html>
