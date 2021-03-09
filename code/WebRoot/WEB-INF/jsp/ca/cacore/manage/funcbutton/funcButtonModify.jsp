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
		<script >
			 window.onload = function()
		     {
		         CKEDITOR.replace( 'editor01' );
		     };
		 </script>		     
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>按钮管理功能<span class="divider">/</span></li>
					<li>修改功能按钮</li>
				</ul>
			</div>
			
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/FuncButton/funcButtonModify.do" method="POST">
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
					<div class="row">
						<webTag:HiddenInputTag name="seq_id" id="seq_id" value='${rmHelper.returnParams.seq_id}'/>
					</div><!-- /.row -->
		
					<div class="row">
						<webTag:Text id="menu_id" name="menu_id" value='${rmHelper.returnParams.menu_id}' displayLable="菜单代码"/>
						<webTag:Text id="button_id" name="button_id" value='${rmHelper.returnParams.button_id}' displayLable="按钮代码"/>
						<webTag:Text id="button_name" name="button_name" value='${rmHelper.returnParams.button_name}' displayLable="按钮名称"/>
					</div><!-- /.row -->
					
					<div class="row">
						<webTag:DynamicSelectTag src="statusSelect" name="status" id="status" displayLable="状态" value='${rmHelper.returnParams.status}'></webTag:DynamicSelectTag>
						<webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' displayLable="备注"/>
					</div><!-- /.row -->
					
				    <div class="row" style="text-align:right;">
				       <button type="submit" class="btn btn-mini btn-primary"><i class="icon-search icon-white"></i>保存</button>
					   <a class="btn btn-mini btn-primary" href="<%=basePath %>/FuncButton/queryFuncButton.do"><i class="icon-plus icon-white"></i>返回</a>
					</div><!-- /.row -->
					
				</form>
			</div>
		</div>
	</body>
</html>
