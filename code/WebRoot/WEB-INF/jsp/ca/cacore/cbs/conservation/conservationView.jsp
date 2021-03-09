<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>

<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 按钮返回控制 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 保单管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保全管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保全维护</span><span class="divider">/</span>
			     <span><i class="icon-list icon-red"></i> 保全信息明细</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12"  action="<%=basePath %>/CBS/Claims/addClaims.do" method="POST">
					<webTag:HiddenInputTag name="seq_id" id="seq_id" value='${rmHelper.returnParams.seq_id}'></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="event_id" id="event_id" value='${rmHelper.returnParams.event_id}'></webTag:HiddenInputTag>
					<div class="row">
						<webTag:Text  name="insBranch_name" id="insBranch_name" value='${rmHelper.returnParams.insBranch_name}' displayLable="保险公司机构" isdisplay="true" readonly="true"/>
						<webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="保单号"  isdisplay="true" readonly="true"/>
						<webTag:Text id="holder_name" name="holder_name" value='${rmHelper.returnParams.holder_name}' displayLable="投保人:" readonly="true" />
					</div>
					
					<div class="row">
						<webTag:Text id="insurant_name" name="insurant_name" value='${rmHelper.returnParams.insurant_name}' displayLable="主被保人:" readonly="true" />
						<webTag:Text id="event_name" name="event_name" value='${rmHelper.returnParams.applicant_name}' displayLable="申请人" readonly="true" isdisplay="true" />
						<webTag:Date id="event_date" name="event_date" value='${rmHelper.returnParams.application_time}' displayLable="申请日期" readonly="true" isdisplay="true" />
					</div>
					
					<div class="row">
						<webTag:Text id="conservationItem_name" name="conservationItem_name" value='${rmHelper.returnParams.conservationItem_name}' displayLable="保全项" readonly="true" isdisplay="true" />
						<webTag:Text id="replace_applicant" name="replace_applicant" value='${rmHelper.returnParams.replace_applicant}' displayLable="代办人:" readonly="true"/>
					</div>
					
					<div class="row">
						<webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' displayLable="备注:" readonly="true"/>
					</div>
					
				    <div class="row" style="text-align:right;">
  				         <a id="backBtn" class="btn btn-danger" href="<%=basePath %>/CBS/Conservation/queryConservationsList.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
			<!--  数据区 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
