<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
String pathjs = request.getContextPath();
String basePathjs = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathjs+"";
%>
<!-- fram start  -->
<script type="text/javascript" src="<%=basePathjs %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
<script type="text/javascript" src="<%=basePathjs %>/compent/base.js"></script><!-- jQuery -->
<!--  收缩 -->
<script type="text/javascript" src="<%=basePathjs%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>
<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="<%=basePathjs %>/compent/html5shiv/html5shiv.js"></script>
<![endif]-->
<!-- fram plugins start-->
<script type="text/javascript" src="<%=basePathjs %>/compent/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/pagination/jquery.pagination.js"></script>
<!-- fileupload start-->

<!-- 保存完成提示框 -->
<script type="text/javascript" src="<%=basePathjs %>/compent/webstyle/js/jqueryui/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/common/dialog.js"></script>

<style>
	.my-dialog .ui-dialog-titlebar-close{
	display: none;
	}
</style> 

<!-- 数据校验 -->
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/jquery.metadata.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/newtouch-validate.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/idHelper.js"></script>