<%@taglib uri="/jspTag" prefix="n"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="<%=path%>/newtouch/js/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/newtouch/js/ajax.js"></script>
<script type="text/javascript" src="<%=path%>/newtouch/js/dataAutomaticFilling.js"></script>
<script type="text/javascript" src="<%=path%>/newtouch/js/datepicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/newtouch/default/css/newtouch.css" />
<script type="text/javascript" src="<%=path%>/newtouch/js/tabs/com.newtouch.editRows.js"></script>
<script type="text/javascript">
$(function() {
	if($(".innerblock_warp").length>0){
		$(".innerblock_warp").table_style();
	}
});
</script>