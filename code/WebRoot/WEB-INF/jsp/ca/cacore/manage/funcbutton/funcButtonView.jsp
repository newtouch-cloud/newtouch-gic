<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
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
		<script >
			 window.onload = function()
		     {
		         CKEDITOR.replace( 'editor01' );
		     };
		 </script>	
        
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>按钮管理功能 <span class="divider">/</span></li>
					<li>功能按钮明细查看</li>
				</ul>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/FuncButtonCRUD/funcButtonView.do" method="POST">
		
					<div class="row">
						<webTag:Text id="menu_id" name="menu_id" value='${rmHelper.returnParams.menu_id}' readonly="true" displayLable="菜单代码" />
						<webTag:Text id="button_id" name="button_id" value='${rmHelper.returnParams.button_id}' readonly="true" displayLable="按钮代码"/>
						<webTag:Text id="button_name" name="button_name" value='${rmHelper.returnParams.button_name}' readonly="true" displayLable="按钮名称"/>
					</div><!-- /.row -->
					
					<div class="row">
                        <webTag:DynamicSelectTag src="statusSelect" name="status" id="status" displayLable="状态" value='${rmHelper.returnParams.status}' readOnly="true"></webTag:DynamicSelectTag>
                        <webTag:Text id="createDate" name="createDate" value='${rmHelper.returnParams.createDate}' readonly="true" displayLable="创建时间"/>
                        <webTag:Text id="createUser" name="createUser" value='${rmHelper.returnParams.createUser}' readonly="true" displayLable="创建人"/>
					</div><!-- /.row -->
					
					<div class="row">
					   <webTag:Text id="modifyUser" name="modifyUser" value='${rmHelper.returnParams.modifyUser}' readonly="true" displayLable="最后修改人"/>
					   <webTag:Text id="modifyDate" name="modifyDate" value='${rmHelper.returnParams.modifyDate}' readonly="true" displayLable="最后修改时间"/>
					   <webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' displayLable="备注"/>
					</div>
					
				    <div class="row" style="text-align:right;">
					    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/FuncButton/queryFuncButton.do"><i class="icon-plus icon-white"></i>返回</a>
					</div><!-- /.row -->
					
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
	</body>
</html>
