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
			    <span><i class="icon-list icon-red"></i>培训立项审批明细</span>
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
							<span><i class="icon-ziliao icon-red mrl14"></i> 培训立项信息</span>
						</div>
			             <div class="row">
						    <webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' readonly="true" displayLable="所属机构代码" isdisplay="true"/>
						    <webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' readonly="true" displayLable="所属机构名称" isdisplay="true"/>
						    <webTag:Text id="project_id" name="project_id" value='${rmHelper.returnParams.project_id}' readonly="true" isdisplay="true" displayLable="立项编号"/>
						</div>
						<div class="row">
							<webTag:Text id="project_name" name="project_name" value='${rmHelper.returnParams.project_name}' readonly="true" isdisplay="true" displayLable="立项名称"/>
							<webTag:DynamicSelectTag src="trainingFormatSelect" name="training_format_code" id="training_format_code" displayLable="制式类型" value='${rmHelper.returnParams.training_format_code}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"/>
							<webTag:DynamicSelectTag src="trainingTypeSelect" name="training_type_code" id="training_type_code" displayLable="培训类型" value='${rmHelper.returnParams.training_type_code}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"/>
						</div>
						<div class="row">
							<webTag:Text id="training_item" name="training_item" value='${rmHelper.returnParams.training_item}' readonly="true" isdisplay="true" displayLable="培训项目"/>
							<webTag:DynamicSelectTag src="trainingPeriodsSelect" name="plan_periods" id="plan_periods" displayLable="培训期数" value='${rmHelper.returnParams.plan_periods}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"/>
							<webTag:Text id="training_day" name="training_day" value='${rmHelper.returnParams.training_day}' readonly="true" isdisplay="true" displayLable="培训天数（天）"/>
						</div>
						<div class="row">
							<webTag:Text id="plan_person_num" name="plan_person_num" value='${rmHelper.returnParams.plan_person_num}' readonly="true" isdisplay="true" displayLable="培训人数"/>
							<webTag:Text id="training_object" name="training_object" value='${rmHelper.returnParams.training_object}' readonly="true" isdisplay="true" displayLable="培训对象"/>
							<webTag:TextDoubleTag id="training_budget" name="training_budget" value='${rmHelper.returnParams.training_budget}' readonly="true" isdisplay="true" displayLable="培训经费预算（元）"/>
						</div>
						<div class="row">
							<webTag:DynamicSelectTag src="projectAppStatusSelect" name="status_code" id="status_code" displayLable="立项状态" value='${rmHelper.returnParams.status_code}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
							<c:if test="${rmHelper.returnParams.upload_time !=null }">
								<div class="control-group span4">
		                    		<label class="control-label" for="uploadify">附件下载:</label>
		                    		<div class="controls">
			                        	<a href='<%=basePath %>/TMS/upload/downloadAttachment.do?bus_id=${rmHelper.returnParams.project_id}&attachment_type=7' >${ rmHelper.returnParams.file_name}</a>
		                    		</div>
	                    		</div>
							</c:if>
							<c:if test="${rmHelper.returnParams.upload_time ==null }">
								<div class="control-group span4">
		                    		<label class="control-label" for="uploadify">附件下载:</label>
		                    		<div class="controls">
		                    			<label style="text-align:left;padding-top:5px;"  for="noattachment">未上传附件</label>
		                    		</div>
	                    		</div>
							</c:if>
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