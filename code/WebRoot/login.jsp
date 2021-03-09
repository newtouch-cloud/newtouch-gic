<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Random" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg" %>
<%@page import="com.newtouch.component.c11assistant.StringHelper" %>
<%@page import="com.newtouch.component.c11assistant.JspHelper" %>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper" %>
<%@ page import="com.newtouch.component.c11assistant.ServletHelper" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "";
%>
<%
    Random r = new Random();//为了避免ie中验证码的缓存
%>
<html lang="en">
<head>
    <title>新致金保通</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="icon" type="image/png" href="<%=basePath %>/compent/login/images/logo.png">
    <!-- The styles -->
    <link rel="stylesheet" href="<%=basePath %>/compent/login/css/login.css">
    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
    <%
        String pathjs = request.getContextPath();
        String basePathjs = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + pathjs + "";
    %>
    <script type="text/javascript" src="<%=basePathjs %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->

    <script type="text/javascript">
        function change(imgObj) {
            imgObj.src = "<%=basePath %>/sendValidateCode.controller?date=" + new Date().getTime();
        }

        $(document).ready(function () {
            var msginfo = $("#msginfo").val();
            if (msginfo == "1") {
                $("#messageNew").show();
            } else {
                $("#messageNew").hide();
            }
        });

        function toUpdatePass() {

            document.getElementById("loginForm").action = "<%=basePath %>/AMS/userMgmtController/toModifyPassWordPage.controller";
            document.getElementById("loginForm").submit();
        }
    </script>
</head>
<body>
<div class="login">
    <form method="post" action="<%=basePath %>/redirect.controller" id="loginForm" autocomplete="off">
        <webTag:HiddenInputTag id="msginfo" name="msginfo" value="${msginfo}"/>
        <div class="logo_name"></div>
        <div class="logo"><img src="<%=basePath %>/compent/login/images/logo_login.png"/></div>
        <!--  <div class="logo_name">金保通</div> -->
        <%--  <div class="div_input_text">用户名</div>--%>
        <div class="div_input div_inputUser">
            <input type="input" name="opt_no" id="opt_no" placeholder="请输入用户名" class="user" value="${opt_no}"/>
        </div>
        <%--<div class="div_input_text">登录密码</div>--%>
        <div class="div_input div_inputPass">
            <input type="password" id="opt_password" name="opt_password" placeholder="请输入密码" class="pass"
                   value="${opt_password}"/>
        </div>
        <%-- <div class="div_input_text">验证码</div>--%>
        <div class="div_input div_inputValidateCode">
            <input type="text" id="validateCode" name="validateCode" placeholder="请输入验证码" class="code"
                   value="${validateCode}"/>
            <img src="<%=basePath %>/sendValidateCode.controller?a=<%=r.nextDouble()%>" class="yanzhengma" alt="验证码"
                 title="点击更换" onclick="change(this);"/>
        </div>
        <input type="hidden" id="linkUrl" name="linkUrl" value="newtouch/login/indexBef"/>
        <a href="#">
            <button class="btns">登录</button>
        </a>
        <div class="message">${message}</div>
        <div class="messageNew" id="messageNew">用户密码已过期，请<a href="#" id="mdfpass" onclick="toUpdatePass()">修改密码</a>
        </div>
        <p class="over_i"><i></i>联系我们</p>
    </form>
</div>
</body>
</html>