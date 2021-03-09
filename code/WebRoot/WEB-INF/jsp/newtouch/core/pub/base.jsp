<%@taglib uri="/jspTag" prefix="n"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="<%=path%>/compent/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/compent/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%=path%>/compent/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/compent/base.js"></script>
<script type="text/javascript" src="<%=path%>/compent/pagination/com.newtouch.pagination.js"></script>
<script type="text/javascript" src="<%=path%>/compent/grid/com.newtouch.grid.js"></script>

<link rel="stylesheet" type="text/css" href="<%=path%>/compent/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/compent/bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/compent/datepicker/skin/WdatePicker.css" />

