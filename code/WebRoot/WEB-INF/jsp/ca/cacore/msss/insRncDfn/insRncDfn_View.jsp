<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/> 
		<script>
		
			
			
			 
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>保险公司管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>产品管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>产品信息明细</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 数据区 start -->
			<div class="row-fluid">
			<div class="Shrinkcontent" id="Shrinkcontent1">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/msss/InsRncDfn/modifyInsRncDfn.do" method="POST">
				     <div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
					
					<div class="row" >
						<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="保险公司机构选择" isdisplay="true" readonly="true"/>
						<webTag:Text id="classCode" name="classCode" value='${rmHelper.returnParams.classCode}' displayLable="险类编码" isdisplay="true" readonly="true"/>
						<webTag:Text id="className" name="className" value='${rmHelper.returnParams.className}' displayLable="险类名称" isdisplay="true" readonly="true"/>
					</div>
					
					<%-- <div class="row" >
						<webTag:Text id="product_code" name="product_code" value='${rmHelper.returnParams.product_code}' displayLable="产品编码" isdisplay="true" readonly="true"/>
						<webTag:Text id="product_name" name="product_name" value='${rmHelper.returnParams.product_name}' displayLable="产品名称" isdisplay="true" readonly="true"/>
					</div> --%>
					<div class="row" >
						<webTag:Text id="riskName"  name="riskName"  value='${rmHelper.returnParams.riskName}' displayLable="险种名称" isdisplay="true" readonly="true"/> <!-- by zdd02 -->
						<webTag:Text id="riskCode"  name="riskCode"  value='${rmHelper.returnParams.riskCode}' displayLable="险种编号" isdisplay="true" readonly="true"/><!-- by zdd02 -->
					<webTag:Text id="bjtypename"  name="bjtypename"  value='${rmHelper.returnParams.bjtypename}' displayLable="保监分类" isdisplay="true" readonly="true"/><!-- by zddxiu -->
					</div>
					<div class="row" style="text-align:right;">
					   <a id="backButton" class="btn btn-danger" href="<%=basePath %>/msss/InsRncDfn/toQueryInsRncDfnList.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
				</div>
			</div>
		</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<div class="zeoBottom"></div>
	    <div class="zeoBottom"></div>
		<!-- 底部高度填充块 结束-->
	</body>
</html>
