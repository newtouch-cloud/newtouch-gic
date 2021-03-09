<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
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
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>回访管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保单回访信息维护</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保单回访明细</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id= "mainForm" class="form-horizontal alert alert-info fade in span12" action="" method="POST">
					<fieldset>
						<div class="row">
						    <webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" displayLable="保险公司机构" isdisplay="true" value='${rmHelper.returnParams.insBranch_id}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						    <webTag:Text id="policy_id" name="policy_id" value='${rmHelper.returnParams.policy_id}' displayLable="所属保单号" isdisplay="true" readonly="true"/>
						</div>
						<div class="row">
							<webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码:" readonly="true"/>
							<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="机构名称:" readonly="true"/>
							<webTag:Text id="app_name" name="app_name" value='${rmHelper.returnParams.app_name}' displayLable="投保人姓名:" readonly="true"/>
						</div>
						<div class="row">
					        <webTag:DynamicSelectTag src="answerTypeSelect" name="answer_type_code" id="answer_type_code" displayLable="回访方式" isdisplay="true" value='${rmHelper.returnParams.answer_type}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					        <webTag:DynamicSelectTag src="answerSuccessSelect" name="is_success" id="is_success" displayLable="回访是否成功" isdisplay="true" value='${rmHelper.returnParams.is_success}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					    </div>
						<div class="row">
							<webTag:TextareaTag id="answer_notes" name="answer_notes" rows='3' value='${rmHelper.returnParams.answer_notes}' displayLable="回访情况说明:" readonly="true"/>
						</div>
					    <div class="row" style="text-align:right;">
				    	   <a id="viewBack" class="btn btn-danger" href="<%=basePath %>/policyAnswer/queryPolicyAnswerInfo.do"><i class="icon-share-alt icon-white"></i>  返回</a>
						</div><!-- /.row -->
					</fieldset>
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
	  <div class="zeoBottomH90"></div>
	</body>
</html>
