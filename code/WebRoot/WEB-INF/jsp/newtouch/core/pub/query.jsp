<%@taglib uri="/jspTag" prefix="n"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- jquery -->
<script type="text/javascript" src="<%=path%>/compent/jquery/jquery-1.9.1.min.js"></script>
<!-- 取值/赋值 -->
<script type="text/javascript" src="<%=path%>/compent/base.js"></script>
<script type="text/javascript" src="<%=path%>/compent/grid/com.newtouch.grid.js"></script>

<!-- ajax -->
<script type="text/javascript" src="<%=path%>/compent/ajax/com.newtouch.ajax.js"></script>
<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/newtouch/js/datepicker/WdatePicker.js"></script>
<!-- 表格样式 -->
<script type="text/javascript" src="<%=path%>/newtouch/js/tabs.style.js"></script>
<!-- CSS格式 -->
<link rel="stylesheet" type="text/css" href="<%=path %>/newtouch/default/css/chaxun.css" />

