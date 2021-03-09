<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
<script type="text/javascript">
$(function (){
	var vForm = $("#form1");
	vForm.append("<input type='hidden' id='opt_no' name='opt_no' value='${CF_USER.optID}' />");
	vForm.append("<input type='hidden' id='funcName' name='funcName' value='redirect.do' />");
	vForm[0].submit();
});
</script>
</head>
<body>
<form action="<%=basePath%>redirect.do" method="post" id="form1">
</form>
</body>
</html>