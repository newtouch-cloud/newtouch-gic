<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<div class='control-group span4'>
	<label class='control-label' for='branch_name'>机构选择(<font color="red">*</font>):</label>
	<div class='controls'>
<%-- 		<input type='text' class='input-medium back_image' name='branch_name' id='branch_name' onblur='syncValue();' onclick='getOrgTree();' value='${rmHelper.returnParams.branch_name}'/> --%>
			<input type='text' class='input-medium back_image' name='branch_name' readonly id='branch_name' onclick='getOrgTree();' value='${rmHelper.returnParams.branch_name}'/>
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
// 	function syncValue(){
// 		if($("#branch_name").val()==""||$("#branch_name").val()==null){
// 			$("#branch_id").val("");
// 		}
// 	}
function handler(){
		var element = document.getElementById("branch_name");
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
		if($("#branch_name").val()!=""&&$("#branch_name").val()!=null){
			$("#branch_name").removeClass("back_image");
		}
		//动态改变机构选择文本框的搜索背景
		$("#branch_name").bind("blur focus", function() {
			var branch_name = $("#branch_name").val();
			if(branch_name!=""&&branch_name!=null){
				$("#branch_name").removeClass("back_image");
			}else{
				$("#branch_name").addClass("back_image");
				$("#branch_id").val("");
			}
		});
		
		var element = document.getElementById("branch_name");
		//区分ie和firefox
		if (document.all) {
			element.attachEvent("onkeydown", handler);
		} else {//如果是Firefox
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