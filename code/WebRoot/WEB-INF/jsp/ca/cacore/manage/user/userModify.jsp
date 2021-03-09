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
		<script >
		 window.onload = function()
	     {
	         CKEDITOR.replace( 'editor01' );
	     };
		 </script>		     
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>后台管理功能 <span class="divider">/</span></li>
					<li>用户管理<span class="divider">/</span></li>
					<li>修改</li>
				</ul>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/User/userModify.do" method="POST">
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
					<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
					<webTag:HiddenInputTag   id="createUser" name="createUser" value='${rmHelper.returnParams.createUser}'/>
					
					<fieldset>
					 <jsp:include page="../../util/branchTree.jsp" flush="true"/> 
					<div class="row">
					   <webTag:Text   id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构id" readonly="true"/>
					   <webTag:DynamicSelectTag src="authTypeSelect" name="user_type" id="user_type" displayLable="用户类型" value='${rmHelper.returnParams.user_type}'></webTag:DynamicSelectTag>
					   <webTag:Text   id="userName" name="userName" value='${rmHelper.returnParams.userName}' displayLable="用户名" readonly="true"/>	
					</div><!-- /.row -->
						<div class="row">
							<webTag:PasswordTag id="password" name="password" value='${rmHelper.returnParams.password}' displayLable="登陆密码"></webTag:PasswordTag>
						   <webTag:Text   id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="姓名"/>
						  <webTag:DynamicSelectTag src="genderSelect" name="sex_code" id="sex_code" displayLable="性别" value='${rmHelper.returnParams.sex_code}'></webTag:DynamicSelectTag>
						</div><!-- /.row -->
						<div class="row">
						   <webTag:Text   id="mobile" name="mobile" value='${rmHelper.returnParams.mobile}' displayLable="手机"/>
						   <webTag:Text   id="fixed_line" name="fixed_line" value='${rmHelper.returnParams.fixed_line}' displayLable="固定电话"/>
						   <webTag:Text   id="email" name="email" value='${rmHelper.returnParams.email}' displayLable="电子邮件"/>
						</div>
						<div class="row">
						  <webTag:Text   id="emp_id" name="emp_id" value='${rmHelper.returnParams.emp_id}' displayLable="员工代码"/>
						  <webTag:Text   id="job_tel" name="job_tel" value='${rmHelper.returnParams.job_tel}' displayLable="办公电话"/>
						  <webTag:Text   id="job_address" name="job_address" value='${rmHelper.returnParams.job_address}' displayLable="办公地址"/>
						</div>
						<div class="row">
						  <webTag:Text   id="remark" name="remark" value='${rmHelper.returnParams.remark}' displayLable="备注"/>
						</div>
					    <div class="row" style="text-align:right;">
					    		<button type="submit" class="btn btn-mini btn-primary"><i class="icon-search icon-white"></i>保存</button>
					    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/User/queryUser.do">返回</a>
						</div><!-- /.row -->
					</fieldset>
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
	</body>
</html>
