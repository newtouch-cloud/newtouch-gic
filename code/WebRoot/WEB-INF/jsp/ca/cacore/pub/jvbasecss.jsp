<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%
String pathcss = request.getContextPath();
String basePathcss = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathcss+"";
%>
<!--  收缩 -->
<link rel="stylesheet" href="<%=basePathcss %>/compent/charisma/css/bootstrap-cerulean-ZM.css" >
<link rel="stylesheet" type="text/css" href="<%=basePathcss%>/compent/charisma/css/xinzhijunyang.css">
<link rel="stylesheet" href="<%=basePathcss %>/compent/charisma/css/charisma-app.css"><!-- 标注1 -->

<!-- fram start -->
<link rel="stylesheet" href="<%=basePathcss %>/compent/charisma/css/bootstrap-responsive.min.css" >

<!-- 数据校验 -->
<link rel="stylesheet" type="text/css" href="<%=basePathcss%>/compent/newtouch/util/validation.css">
<!-- 弹出笑脸提示框 -->
<link rel="stylesheet" href="<%=basePathcss %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >