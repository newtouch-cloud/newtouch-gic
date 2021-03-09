<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>

<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
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
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户行为查询</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户行为明细</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="" method="POST">
					<webTag:HiddenInputTag   id="branch_level" name="branch_level" value='${rmHelper.returnParams.branch_level}' displayLable="&nbsp;"/>
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
					<fieldset>
					<div class="row">
						  <webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码" isdisplay="true" readonly="true"/>
						  <webTag:Text id="branch_name" name="branch_name"  value='${rmHelper.returnParams.branch_name}' displayLable="机构名称" isdisplay="true" readonly="true"/>
						  <webTag:Text id="customer_id" name="customer_id"  value='${rmHelper.returnParams.customer_id}' displayLable="客户代码" isdisplay="true" readonly="true"/>
					</div><!-- /.row -->
					<div class="row">
						  <webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="客户姓名" isdisplay="true" readonly="true"/>
						  <webTag:DynamicSelectTag src="customerTypeSelect" id="customer_type" name="customer_type"  value='${rmHelper.returnParams.customer_type}' displayLable="客户类型"  isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						  <webTag:DynamicSelectTag src="genderSelect" name="gender" id="gender" displayLable="性别" value='${rmHelper.returnParams.gender}'  isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
                    </div>
                    <div class="row">
						  <webTag:DynamicSelectTag src="custActionTypeSelect" id="action_type" name="action_type"  value='${rmHelper.returnParams.action_type}' displayLable="行为类型" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						  <webTag:Date id="action_time" name="action_time" value='${rmHelper.returnParams.action_time}' displayLable="行为日期:"  readonly="true" />
                    </div>
					<div class="row">
						<webTag:TextareaTag id="action_notes" name="action_notes" rows='6'  value='${rmHelper.returnParams.action_notes}' displayLable="行为描述" isdisplay="true" readonly="true" />
					</div><!-- /.row -->
				    <div class="row" style="text-align:right;">
					    <a class="btn btn-danger"  href="<%=basePath%>/CRM/CustomerAction/queryAll.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
				  </fieldset>	
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 底部高度填充块 结束-->
	</body>
</html>
