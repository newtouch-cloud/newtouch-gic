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
			    <span><i class="icon-list icon-red"></i>培训班课程审批明细</span>
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
							<span><i class="icon-ziliao icon-red mrl14"></i> 培训课程信息</span>
						</div>
			             <div class="row">
						    <webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' readonly="true" displayLable="所属机构代码:"/>
						    <webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' readonly="true" displayLable="所属机构名称:"/>
						    <webTag:Text id="course_id" name="course_id" value='${rmHelper.returnParams.course_id}' readonly="true" isdisplay="true" displayLable="课程编号"/>
						</div>
						<div class="row">
							<webTag:Text id="course_name" name="course_name" value='${rmHelper.returnParams.course_name}' readonly="true" isdisplay="true" displayLable="课程名称"/>
							<webTag:DynamicSelectTag src="courseTypeSelect"  id="course_type_code" name="course_type_code" value='${rmHelper.returnParams.course_type_code}' isdisplay="true" displayLable="课程类型" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
							<webTag:DynamicSelectTag src="courseLevelSelect" id="course_level_code" name="course_level_code" value='${rmHelper.returnParams.course_level_code}' isdisplay="true" displayLable="课程级别" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						</div>
						<div class="row">
							<webTag:Text id="course_period" name="course_period" value='${rmHelper.returnParams.course_period}' readonly="true" isdisplay="true" displayLable="课程时长"/>
							<webTag:DynamicSelectTag src="trainingItemSelect" id="training_item_code" name="training_item_code" value='${rmHelper.returnParams.training_item_code}' isdisplay="true" displayLable="培训项目" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
							<webTag:Text id="teacher_id" name="teacher_id" value='${rmHelper.returnParams.teacher_id}' readonly="true" isdisplay="true" displayLable="讲师编号" />
						</div>
						<div class="row">
							<webTag:Text id="teacher_name" name="teacher_name" value='${rmHelper.returnParams.teacher_name}' isdisplay="true" displayLable="讲师姓名" readonly="true"/>
							<webTag:DynamicSelectTag src="courseStatusSelect" id="course_status" name="course_status" value='${rmHelper.returnParams.course_status_code}' isdisplay="true" displayLable="课程状态"  onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
							<webTag:DynamicSelectTag src="hignPolicySelect" id="is_preseted" name="is_preseted" value='${rmHelper.returnParams.is_preseted}' isdisplay="true" displayLable="是否为预置课程" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						</div>
						<div class="row">
						    <webTag:Text id="upload_time" name="upload_time" value='${rmHelper.returnParams.upload_time}' displayLable="资料上传时间" isdisplay="true" readonly="true"/>
							<c:if test="${rmHelper.returnParams.upload_time !=null }">
								<div class="control-group span4">
		                    		<label class="control-label" for="uploadify">资料下载:</label>
		                    		<div class="controls">
			                        	<a href='<%=basePath %>/TMS/upload/downloadAttachment.do?bus_id=${rmHelper.returnParams.course_id}&attachment_type=9' >${rmHelper.returnParams.file_name}</a>
		                    		</div>
	                    		</div>
							</c:if>
							<c:if test="${rmHelper.returnParams.upload_time ==null }">
								<div class="control-group span4">
		                    		<label class="control-label" for="uploadify">资料下载:</label>
		                    		<div class="controls">
		                    			<label style="text-align:left;padding-top:5px;"  for="noattachment">未上传资料</label>
		                    		</div>
	                    		</div>
							</c:if>
						</div>
						<div class="row">
							<webTag:TextareaTag id="course_introduction" name="course_introduction" rows='5' value='${rmHelper.returnParams.course_introduction}' displayLable="课程介绍:" readonly="true"/>
						</div>
						<div class="row">
							<webTag:TextareaTag id="approval_views" name="approval_views" rows='5' value='${rmHelper.returnParams.approval_views}' displayLable="审批意见:" readonly="true" />
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