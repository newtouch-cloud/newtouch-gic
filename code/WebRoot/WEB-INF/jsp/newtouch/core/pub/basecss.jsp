<%@page import="net.sf.json.JSONObject,java.util.*,com.newtouch.utils.stringutil.StringUtil"%>
<%@taglib uri="/jspTag" prefix="n"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 String jsonMapCss = (String)request.getAttribute("json");
	    JSONObject paramMapCss = new JSONObject();
	    if(!StringUtil.isNull(jsonMapCss))
	    	paramMapCss = JSONObject.fromObject(jsonMapCss);
	    if(paramMapCss.get("InitPageType") == null){
%>

<link rel="stylesheet" type="text/css" href="<%=path%>/compent/bootstrap/css/bootstrap.css" />
<%-- <link rel="stylesheet" type="text/css" href="<%=path%>/compent/grid/css/other-ff.css" /> --%>
<!--[if IE]>
<link rel="stylesheet" type="text/css" href="<%=path%>/compent/bootstrap/css/bootstrap-ie.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/compent/grid/css/other-ie.css" />
<![endif]-->
<!--[if !IE]>

<![endif]-->

<link rel="stylesheet" type="text/css" href="<%=path%>/compent/bootstrap/css/bootstrap-theme.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/compent/datepicker/skin/WdatePicker.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/compent/msg/css/com.newtouch.message.css" />
<%-- <link rel="stylesheet" type="text/css" href="<%=path%>/compent/grid/css/other.css" />
 --%><%} %>
