<%@page import="net.sf.json.JSONObject,java.util.*,com.newtouch.utils.stringutil.StringUtil"%>
<%
	String pathjs = request.getContextPath();
	String basepathjs = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ pathjs + "/";
	String jsonMap = (String)request.getAttribute("json");
    JSONObject paramMap = new JSONObject();
    if(!StringUtil.isNull(jsonMap))
	   paramMap = JSONObject.fromObject(jsonMap);
    if(paramMap.get("InitPageType") == null){
%>
<script type="text/javascript" src="<%=pathjs%>/compent/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=pathjs%>/compent/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=pathjs%>/compent/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=pathjs%>/compent/base.js"></script>
<script type="text/javascript" src="<%=pathjs%>/compent/pagination/com.newtouch.pagination.js"></script>
<script type="text/javascript" src="<%=pathjs%>/compent/grid/com.newtouch.grid.js"></script>
<script type="text/javascript" src="<%=pathjs%>/compent/msg/com.newtouch.message.js"></script>
<script type="text/javascript" src="<%=pathjs%>/compent/util.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if IE]>
<script type="text/javascript" src="<%=pathjs%>/compent/html5shiv/html5shiv.js"></script>
<script type="text/javascript" src="<%=pathjs%>/compent/bootstrap/js/respond.min.js"></script>
<![endif]-->
<%} else{
 %>
 <script type="text/javascript" src="<%=pathjs%>/compent/init.js"></script>
 <% 
}%>

