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

ReturnMsg returnMsg = (ReturnMsg)request.getAttribute("returnMsg");
if(returnMsg==null){returnMsg = new ReturnMsg();}
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
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>权限管理系统菜单 <span class="divider">/</span></li>
					<li>角色管理 <span class="divider">/</span></li>
					<li>明细查看</li>
				</ul>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/Role/roleAdd.do" method="POST">
				<!-- 提示信息标签 -->
				<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
				<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
					<fieldset>
						<div class="row">
							<webTag:Text   id="role_type" name="role_type" value='${rmHelper.returnParams.role_type}' readonly="true" displayLable="角色类型"/>
						  	<webTag:Text   id="role_name" name="role_name" value='${rmHelper.returnParams.role_name}' readonly="true" displayLable="角色名称"/>
						  	<webTag:Text   id="role_id" name="role_id" value='${rmHelper.returnParams.role_id}' readonly="true" displayLable="角色代码"/>
						</div><!-- /.row -->
						
						<div class="row">
							<webTag:DynamicSelectTag src="statusSelect" name="status" id="status" displayLable="状态" value='${rmHelper.returnParams.status}' readonly="true"></webTag:DynamicSelectTag>
							<webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' readonly="true" displayLable="备注"/>
						</div><!-- /.row -->
						<div class="row">
							<webTag:Text   id="createUser" name="createUser" value='${rmHelper.returnParams.createUser}' readonly="true" displayLable="创建人"/>
							<webTag:Date   id="createDate" name="createDate" value='${rmHelper.returnParams.createDate}' readonly="true" displayLable="创建时间"/>
						</div>
						<div class="row">
							<webTag:Text   id="modifyUser" name="modifyUser" value='${rmHelper.returnParams.modifyUser}' readonly="true" displayLable="最后修改人"/>
							<webTag:Date   id="modifyDate" name="modifyDate" value='${rmHelper.returnParams.modifyDate}' readonly="true" displayLable="最后修改时间"/>
						</div>
					    <div class="row" style="text-align:right;">
					    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/Role/roleSelect.do"><i class="icon-plus icon-white"></i>保存</a>
					    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/Role/roleSelect.do"><i class="icon-plus icon-white"></i>返回</a>
						</div><!-- /.row -->
					</fieldset>
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
	</body>
</html>
