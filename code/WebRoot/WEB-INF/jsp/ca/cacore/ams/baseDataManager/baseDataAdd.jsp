<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html>
<head>
        <%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script type="text/javascript" src="<%=basePath%>/compent/default/js/attachmentModify.js"></script>
</head>
<script type="text/javascript">

 
jQuery.validator.addMethod("checkTypecode",function(value,element,param){
	var typecode=$("#typecode").val();
	if(typecode==""){
		alert("不能为空");
		return false;
	}
	$.ajax({ 
		type: "POST", 
		url: "<%=basePath %>/AMS/BaseDataManagerController/queryBaseDataTypecode.do", 
		async:false,
		data: {'typecode':typecode}, 
		success: function(result) { 
			if(typecode==result){
				return false;
			} 
			return true;
		} 
	}); 
  	return true;
  },"主类型已存在");

$(document).ready(function() {
		$("#mainForm").validate({
			rules : {
				typecode:{
					checkTypecode : ["typecode"]
				    }
				},
			onkeyup:false
		});
	}); 


 
//////////////////////////////////////////////////////////////////////////////////////////////
//增加一条校验 
function addOneBaseData(){
	var numdiv=$("#row").nextAll().length;
	var mainform=$("#mainForm").append("<div id='row"+(numdiv+1)+"' class='row'><webTag:Text id='code"+(numdiv+1)+"' name='code'  value='${rmHelper.returnParams.code}' displayLable='数据编码'/><webTag:Text id='name"+(numdiv+1)+"' name='name'  value='${rmHelper.returnParams.name}' displayLable='数据名称'/></div>");
}


</script>
<body>

<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>数据字典管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>新增</span><span class="divider">/</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/BaseDataManagerController/addBaseData.do"  enctype="multipart/form-data"  method="POST">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<image id="dialog_img" >${rmHelper.msgStr}</image>
					</div  >
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>					
					<!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag name="id" id="id" value="${rmHelper.returnParams.id}"></webTag:HiddenInputTag>
				    	
				    <div id="click" class="row" align="right">
					     <a id="addone" class="btn btn-danger" onclick="addOneBaseData();"><i class="icon-plus icon-white"></i>增加一条</a>
						 <button id="submitBtn" type="submit" onClick="defaultQuery();" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
			             <a class="btn btn-danger" href="<%=basePath %>/AMS/BaseDataManagerController/toQueryBaseData.do"><i class="icon-share-alt icon-white"></i>返回</a>
		 			</div>
						
					<div id="row" class="row">
					    <webTag:Text id="typecode" name="typecode"  value='${rmHelper.returnParams.typecode}' displayLable="类型编码" isdisplay="true"/>
					    <webTag:Text id="name" name="name"  value='${rmHelper.returnParams.name}' displayLable="类型名称" isdisplay="true"/>
					</div>					
					<div id="row1" class="row">
					    <webTag:Text id="code1" name="code"  value='${rmHelper.returnParams.code}' displayLable="数据编码" />
					    <webTag:Text id="name1" name="name"  value='${rmHelper.returnParams.name}' displayLable="数据名称" />
					</div>						   
					<div id="row2" class="row">
					    <webTag:Text id="code2" name="code"  value='${rmHelper.returnParams.code}' displayLable="数据编码" />
					    <webTag:Text id="name2" name="name"  value='${rmHelper.returnParams.name}' displayLable="数据名称" />
					</div>	                 
                   <div id="row3" class="row">
					    <webTag:Text id="code3" name="code"  value='${rmHelper.returnParams.code}' displayLable="数据编码" />
					    <webTag:Text id="name3" name="name"  value='${rmHelper.returnParams.name}' displayLable="数据名称"/>
					</div>	
					
				</form>
			</div>
			<!-- 增加面板 end -->			
        </div>
<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
<!-- 		底部高度填充块 结束-->



</body>
</html>