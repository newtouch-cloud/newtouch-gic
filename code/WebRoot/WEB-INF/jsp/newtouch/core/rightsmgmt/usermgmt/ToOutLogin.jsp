<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%
String path = request.getContextPath();
String pathjs = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<!--  收缩 -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean-ZM.css" >
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/newtouch/util/validation.css">
		
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>	
		<!-- 保存完成提示框 -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >
		<script type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jqueryui/ui/jquery-ui.js"></script>
        
        <script type="text/javascript" src="<%=pathjs%>/compent/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=pathjs%>/compent/base.js"></script>
        <script type="text/javascript" src="<%=pathjs%>/compent/pagination/com.newtouch.pagination.js"></script>
        <script type="text/javascript" src="<%=pathjs%>/compent/grid/com.newtouch.grid.js"></script>
		<script type="text/javascript" src="<%=pathjs%>/compent/msg/com.newtouch.message.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
		<script type="text/javascript" src="<%=pathjs%>/compent/util.js"></script>
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if IE]>
		<script type="text/javascript" src="<%=pathjs%>/compent/html5shiv/html5shiv.js"></script>
		<script type="text/javascript" src="<%=pathjs%>/compent/bootstrap/js/respond.min.js"></script>
		<![endif]-->
		
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >
     	<!-- 保存完成提示框 -->
       	<script type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jqueryui/ui/jquery-ui.js"></script>
		<style>
		  .my-dialog .ui-dialog-titlebar-close{
		    display: none;
		  }
		 </style> 
		<script type="text/javascript">
			$(document).ready(function() {
				alert("抱歉,您的账号已在其他地方登陆。");
				location.href="<%=basePath%>/runloginOut2.controller";
			  });
		</script>
	 
	</head>
	<body>
	</body>
</html>