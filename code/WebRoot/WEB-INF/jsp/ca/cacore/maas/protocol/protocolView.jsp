<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%@page import="com.newtouch.organization.model.vo.IAgencyModel"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script type="text/javascript" src="<%=basePath%>/compent/default/js/attachmentModify.js"></script>   
	</head>
	<body>
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 协议管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保险公司协议</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 明细</span>
			</div>
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="" enctype="multipart/form-data" method="POST">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>	
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
					<fieldset>
					<div class="row" >
				  		<webTag:Text id="ins_branchname" name="ins_branchname" value='${rmHelper.returnParams.ins_branchname}' displayLable="保险公司机构:" readonly="true"/>
				  		<webTag:Text id="status"  name="status"  value='保险专业代理委托合同' displayLable="协议类型" readonly="true" isdisplay="true"/>
						<webTag:Date id="dateofsign" name="dateofsign" value='${rmHelper.returnParams.dateofsign}' displayLable="协议签订日期" isdisplay="true" readonly="true"/>
					</div>
					
					<div class="row">
						<webTag:Text id="branch_name"  name="branch_name"  value='${rmHelper.returnParams.branch_name}' displayLable="中介公司机构:" readonly="true" />
						<webTag:Date id="startdate"  name="startdate"  value='${rmHelper.returnParams.startdate}'  displayLable="生效日期" isdisplay="true" readonly="true"/>
						<webTag:Date id="enddate"    name="enddate"    value='${rmHelper.returnParams.enddate}'    displayLable="终止日期" isdisplay="true" readonly="true"/>
					</div><!-- /.row -->
					
					<div class="row">
                        <webTag:Text name="agreement_no" id="agreement_no"  value='${rmHelper.returnParams.agreement_no}' readonly="true" displayLable="协议号:" ></webTag:Text>
					</div>
					
				    <div class="row" style="text-align:right;">
					    <a class="btn btn-danger" href="<%=basePath %>/maas/Protocol/queryProtocolList.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
				  </fieldset>	
				</form>
			</div>
		</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<div class="zeoBottomH90"></div>
		<div class="zeoBottomH90"></div>
		<div class="zeoBottomH90"></div>
		<!-- 底部高度填充块 结束-->
	</body>
</html>
