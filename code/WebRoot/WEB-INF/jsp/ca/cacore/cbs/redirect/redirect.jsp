<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.ca.cacore.manage.model.bo.UserModel"%>
<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
	String url = (String)request.getAttribute("url");
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<link href="<%=basePath%>/resources/ca/cacore/uploadify/uploadimg.css" rel="stylesheet" type="text/css" />
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs-1.7.2.jsp" %>
		<!-- 按钮返回控制 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		
		<script type="text/javascript">
		$(document).ready(function(){
			window.open('<%=url%>')
	    });
		</script>
		
	</head>
	
	<body>
		
	</body>
</html>
