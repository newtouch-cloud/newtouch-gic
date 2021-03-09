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
		
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		
		<style>
		  /**去除jquery ui close按钮**/
		  .my-dialog .ui-dialog-titlebar-close{
		    display: none;
		  }
		</style> 
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 公告管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 公告审批查询</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 公告审批明细</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id= "mainForm" class="form-horizontal alert alert-info fade in span12" action="" method="POST">
					<fieldset>
						<div id="dialog" title="提示信息" style="display:none">
							<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
						</div>
					    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
						<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
						<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
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
							<span><i class="icon-ziliao icon-red mrl14"></i> 公告信息</span>
						</div>
						
			            <div class="row">
						    <webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' readonly="true" displayLable="公告的机构" isdisplay="true"/>
						    <webTag:Text id="announcement_theme" name="announcement_theme" value='${rmHelper.returnParams.announcement_theme}' readonly="true" displayLable="主题" isdisplay="true"/>
					     <webTag:DynamicSelectTag src="announcementTypeSelect" name="announcement_type_code" id="announcement_type_code" displayLable="标识" value='${rmHelper.returnParams.announcement_type_code}' isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
						</div>
						<div class="row">
							<webTag:Text id="start_time" name="start_time" value='${rmHelper.returnParams.start_time}' readonly="true" isdisplay="true" displayLable="公告开始生效时间"/>
							<webTag:Text id="end_time" name="end_time" value='${rmHelper.returnParams.end_time}' readonly="true" isdisplay="true" displayLable="到"/>
					       <webTag:DynamicSelectTag src="announcementMarkSelect" name="announcement_mark_code" id="announcement_mark_code" displayLable="标识" value='${rmHelper.returnParams.announcement_mark_code}' isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
						</div>
						<div class="row">
						   <webTag:DynamicSelectTag src="approvalStatusSelect" name="approval_status" id="approval_status" value='${rmHelper.returnParams.approval_status}' displayLable="审批状态:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
						</div>
							<webTag:AttachmentDownloadTag busId='${rmHelper.returnParams.announcement_id}'   attachmentType="20" fileName='${rmHelper.returnParams.file_name}' trueFlag='${rmHelper.returnParams.upload_time !=null}' basePath="<%=basePath %>" displayLable="附件下载:" noAttachment="未上传附件"/>
						<div class="row">
							<webTag:TextareaTag id="announcement_content" name="announcement_content" rows='5' value='${rmHelper.returnParams.announcement_content}' displayLable="内容:" readonly="true"/>
						</div>
					  <div class="row">
							<webTag:TextareaTag id="approval_views" name="approval_views" rows='5' value='${rmHelper.returnParams.approval_views}' displayLable="审批意见:" readonly="true"/>
						</div>
					   <div class="row" align="right">
			           		<a class="btn btn-danger" href="<%=basePath %>/AMS/Announcement/queryAnnouncementAppr.do?pageName=announcementAppr_Query" ><i class="icon-share-alt icon-white"></i>返回</a>
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