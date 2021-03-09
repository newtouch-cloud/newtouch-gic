<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<div class='form-group'>
	<label class='col-sm-1 control-label' for='dept_name'>机构选择</label>
	<div class='col-sm-10'>
		<input type='text' class='form-control' name='dept_name' readonly id='dept_name' onclick='getOrgTree();' value='${rmHelper.returnParams.branch_name}'/>
		<input type="hidden" id="dept_no" name="dept_no" value='${rmHelper.returnParams.branch_id}'/>
	</div>
</div>
<script type="text/javascript">
	function getOrgTree(){
		var feature="dialogWidth:500px;dialogHeight:500px;status:no;help:no;location=no"; 
	    var someValue = window.showModalDialog(encodeURI("<%=basePath %>/Branch/queryBranchTreePage4WF.do"),null,feature); 
		if(someValue != null){
			$("#dept_no").val(someValue.split("#")[0]);
			$("#dept_name").val(someValue.split("#")[1]);
		}
	}
	
    function handler(){
		var element = document.getElementById("dept_name");
		var evt = getEvent();
		var currKey=evt.keyCode||evt.which||evt.charCode;
		if (currKey == 8) {
			evt.returnValue = false;
		}
    }
    
    //获得event对象
	function getEvent() {
		if (document.all) {
			return window.event;//如果是ie
		}
		func = getEvent.caller;
		while (func != null) {
			var arg0 = func.arguments[0];
			if (arg0) {
				if ((arg0.constructor == Event || arg0.constructor == MouseEvent)
						|| (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
					return arg0;
				}
			}
			func = func.caller;
		}
		return null;
	}

	$(function() {
		//加载时判断输入框是否有值，有的话去掉搜索背景
		if($("#dept_name").val()!=""&&$("#dept_name").val()!=null){
			$("#dept_name").removeClass("back_image");
		}
		//动态改变机构选择文本框的搜索背景
		$("#dept_name").bind("blur focus", function() {
			var branch_name = $("#dept_name").val();
			if(branch_name!=""&&branch_name!=null){
				$("#dept_name").removeClass("back_image");
			}else{
				$("#dept_name").addClass("back_image");
				$("#dept_no").val("");
			}
		});
		
		var element = document.getElementById("dept_name");
		//区分ie和firefox
		if (document.all) {
// 			alert("IE");
			element.attachEvent("onkeydown", handler);
		} else {//如果是Firefox
// 			alert("firefox"); 
			element.addEventListener("keydown", handler, true);
		}
	});
</script>
<style type="text/css">
  	/*机构输入框的样式*/
	#branch_name {
	    background-color: #fff;
	    border:rgba(82,168,236,0.8) solid 1px;
		cursor: pointer;
	}
	/*机构输入框的背景样式*/
	.back_image {
		background-repeat: no-repeat;
	    background-position: right center;
		background-image: url("<%=basePath %>/compent/charisma/img/search.png");
	}
  
</style>