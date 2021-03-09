<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<div class='control-group span4'>
	<label class='control-label' for='branch_name'>机构选择(<font color=red>*</font>):</label>
	<div class='controls'>
		<input type='text' class='input-medium' name='branch_name' id='branch_name' onblur='syncValue();' onclick='getOrgTree();' value='${rmHelper.returnParams.branch_name}'/>
		<input type="hidden" id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}'/>
	</div>
</div>
<script type="text/javascript">
	function getOrgTree(){
		var feature="dialogWidth:500px;dialogHeight:500px;status:no;help:no;location=no"; 
	    var someValue = window.showModalDialog(encodeURI("<%=basePath %>/Branch/queryBranchTreePage.do"),null,feature); 
		if(someValue != null){
			$("#branch_id").val(someValue.split("#")[0]);
			$("#branch_name").val(someValue.split("#")[1]);
		}
	}
	function syncValue(){
		if($("#branch_name").val()==""||$("#branch_name").val()==null){
			$("#branch_id").val("");
		}
	}
</script>