<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<html lang="en">
	<head>
		<title>中介人员管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- The styles -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css" >
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="row-fluid">
					<div class="span12 center login-header"><h2>欢迎访问中介人员管理系统</h2></div>
				</div><!--/row-->
				<div class="row-fluid">
					<div class="well span5 center login-box">
						<div class="alert alert-info">请输入用户名和密码.</div>
						<form class="form-horizontal" method="post" action="<%=basePath %>/redirect.controller" id="form1">
<%-- 							<form class="form-horizontal" action="<%=basePath %>/framesjsp.do" method="post"> --%>
							<fieldset>
								<div class="input-prepend" title="用户名" data-rel="tooltip">
									<span class="add-on"><i class="icon-user"></i></span><input autofocus class="input-large span10" name="opt_no" id="opt_no" type="text" value="${opt_no}"/>
								</div>
								<div class="clearfix"></div>
								<div class="input-prepend" title="密码" data-rel="tooltip">
									<span class="add-on"><i class="icon-lock"></i></span><input class="input-large span10" name="opt_password" id="opt_password" type="password" value="${opt_password}"/>
								</div>
								<div class="clearfix"></div>
								<div class="input-prepend">
									<label class="remember" for="remember"><input type="checkbox" id="remember" />记住账号</label>
								</div>
								<div>
									<br><font color="red">${message}</font>
								</div>
								<div class="clearfix"></div>
                                <input type="hidden" id="linkUrl" name="linkUrl" value="newtouch/login/indexBef"/>
								<p class="center span5"><button class="btn btn-primary" >登陆</button></p>
                                
                
							</fieldset>
						</form>
					</div><!--/span-->
				</div><!--/row-->
			</div><!--/fluid-row-->
		</div><!--/.fluid-container-->
	</body>
</html>