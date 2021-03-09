<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>中介公司核心系统</title>
<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp"%>
<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp"%>
<!-- 回跳、收缩及上跳 -->
<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp"
	flush="true" />
<link
	href="<%=basePath%>/resources/newtouch/demo/uploadify/uploadify.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>/resources/newtouch/demo/uploadify/uploadimg.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=basePath%>/resources/newtouch/demo/uploadify/swfobject.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>/resources/newtouch/demo/uploadify/jquery.form.js"
	type="text/javascript"></script>
</head>
<script type="text/javascript">
/* by zdd 20190606 */
</script>
<style>
	.style{
	 float: right;
	}

</style>
<body >
	<!-- <body onload="tiMesg()"> -->
	<div class="container-fluid" style="height: 400px;background-color: #e9ecee">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i>机构管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 营业执照历史记录</span>

		</div>
		<!-- 面包屑导航  end -->
		<div class="row-fluid" id="Shrinkcontent1" >
				<form id="fileForm" name='fileForm' method="post" class="form-horizontal alert alert-info fade in span12"
				enctype="multipart/form-data" action="<%=basePath%>/Branch/importbranchIMgPdf.do">

				<!-- 提示信息 -->
					<div id="dialog" title="提示信息" style="display: none">
						<center>
							<image id="dialog_img"></image>
							</br>
							<span>${rmHelper.msgStr}</span>
						</center>
					</div>
					<webTag:HiddenInputTag id="result_flag" name="result_flag"
						value="${rmHelper.successflag}" displayLable="msg状态" />
					<webTag:HiddenInputTag id="msg" name="msg"
						value="${rmHelper.msgStr}" displayLable="msg信息" />
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath%>' />
					<!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
					<webTag:HiddenInputTag name="flag" id="flag"
						value="${rmHelper.returnParams.flag}"></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="removeflag" id="removeflag"
						value="${removeflag}"></webTag:HiddenInputTag>
						
				<div class="overAuto row-fluid">
				<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}' />
					<table class='table table-striped table-bordered bootstrap-datatable datatable' style="background-color: #e9ecee">
					   <tr>
					       <th>机构名称</th>
					       <th>时间</th>
					       <th>文件名称</th>
					       <th>文件查看</th>
					     
					    </tr>
					   <c:if test="${not empty rmHelper.returnMsg.dataList}">
					   <c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
					    <tr>
					     
					       <td>${sm.branch_name}</td>
					       <td>${sm.c_up_tm}</td>
					       <td>${sm.fileName}</td>
						    <td>
						       <a class="btn btn-danger" href="<%=basePath %>/Branch/downImgList.do?u=${sm.licensepath}"><i class="icon-download-alt icon-white"></i>营业执照查看</a>
						        <c:if test="${sm.c_last_mark!=1}">
						        <a class="btn btn-danger" href="<%=basePath %>/Branch/upbusinessLicenseHis.do?id=${sm.id}&seq_id=${sm.seq_id}">删除</a>
						        </c:if>
						    </td>
						   
					   </tr>
					   </c:forEach>
					   </c:if>
					    <c:if test="${empty rmHelper.returnMsg.dataList}">
					     <tr><td colspan="4" style="text-align: center;"><i>暂无数据！</i></td></tr>
					    </c:if>
					</table>
				</div>
			<div class="style">
					<a class="btn btn-danger" href="<%=basePath %>/Manage/Branch/toQueryBranch.do"><i class="icon-share-alt icon-white"></i>返回</a>
				</div>
		<div class="zeoBottomH90"></div>
		
		<!-- 		底部高度填充块 结束-->
		</div>
	</form>
			<div class="zeoBottomH90"></div>
		</div>
	
</body>

</html>