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
			    <span><i class="icon-list icon-red"></i>审批明细查询</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>法律合同审批明细</span>
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
							<span><i class="icon-ziliao icon-red mrl14"></i> 法律合同信息</span>
						</div>
					<div class="row">
						<webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="审批机构代码" isdisplay="true" readonly="true"/>
						<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="审批机构名称" isdisplay="true" readonly="true"/>
						<webTag:DynamicSelectTag src="approvalStatusSelect" name="approval_status" id="approval_status" value='${rmHelper.returnParams.approval_status}' displayLable="审批状态:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
					</div>
					<div class="row">  
						<webTag:Text id="legal_contracts_id" name="legal_contracts_id"  value='${rmHelper.returnParams.legal_contracts_id}' displayLable="法律合同编号" isdisplay="true" readonly="true"/>
						<webTag:Text id="legal_contracts_name" name="legal_contracts_name"  value='${rmHelper.returnParams.legal_contracts_name}' displayLable="法律合同名称" isdisplay="true" readonly="true"/>                                  
					</div>
					<div class="row">
					     <webTag:Date id="formulate_date" name="formulate_date"  value='${rmHelper.returnParams.formulate_date}' displayLable="制定日期" isdisplay="true" readonly="true"/>
					     <webTag:Text id="formulate_person" name="formulate_person"  value='${rmHelper.returnParams.formulate_person}' displayLable="制定人" isdisplay="true" readonly="true"/>
					</div>
					<div class="row">
							<webTag:TextareaTag id="remark" name="remark" rows='5' value='${rmHelper.returnParams.remark}' displayLable="备注:" readonly="true"/>
					</div>
				    <webTag:AttachmentDownloadTag busId='${rmHelper.returnParams.legal_contracts_id}'   attachmentType="15" fileName='${rmHelper.returnParams.file_name}' trueFlag='${rmHelper.returnParams.upload_time !=null}' basePath="<%=basePath %>" displayLable="附件:" noAttachment="未上传附件"/>
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