<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";

ReturnMsg returnMsg = (ReturnMsg)request.getAttribute("returnMsg");
if(returnMsg==null){returnMsg = new ReturnMsg();}
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
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>通用前台功能 <span class="divider">/</span></li>
					<li>通用CRUD <span class="divider">/</span></li>
					<li>查看</li>
				</ul>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/FuncMenu/addFuncMenu.do" method="POST" id="formID">
					   <!-- 提示信息标签 -->
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
					<fieldset>
						<div class="row">
						 <webTag:Text   id="menu_id" name="menu_id"  value='${rmHelper.returnParams.menu_id}' displayLable="菜单代码"/>
						   <webTag:Text   id="menu_name" name="menu_name"  value='${rmHelper.returnParams.menu_name}' displayLable="菜单名称"/> 
						<webTag:DynamicSelectTag src="authTypeSelect" name="menu_type" id="menu_type" displayLable="菜单类型" value='${rmHelper.returnParams.menu_type}'></webTag:DynamicSelectTag>
						</div><!-- /.row -->
						
						<div class="row">
							<webTag:DynamicSelectTag src="funcMenuURITypeSelect" name="menu_uritype" id="menu_uritype" displayLable="菜单访问类型" value='${rmHelper.returnParams.menu_uritype}'></webTag:DynamicSelectTag>
							 <webTag:Text   id="menu_uri" name="menu_uri"  value='${rmHelper.returnParams.menu_uri}' displayLable="菜单访问路径"/>
						    <webTag:DynamicSelectTag src="funcMenuOpenTypeSelect" name="menu_opentype" id="menu_opentype" displayLable="菜单打开方式"  value='${rmHelper.returnParams.menu_opentype}'></webTag:DynamicSelectTag>
						</div><!-- /.row -->
						
						<div class="row">
						      <webTag:Text   id="menu_dispath" name="menu_dispath"  value='${rmHelper.returnParams.menu_dispath}' displayLable="菜单显示路径"/>
						      <webTag:Text   id="menu_disorder" name="menu_disorder"  value='${rmHelper.returnParams.menu_disorder}' displayLable="菜单显示顺序"/>
						</div><!-- /.row -->
						
						<div class="row">
						<webTag:TextareaTag name="remark" rows="4" id="remark" displayLable="备注" value='${rmHelper.returnParams.remark}'></webTag:TextareaTag>
	                    </div><!-- /.row -->
					    <div class="row" style="text-align:right;">
					    		<button type="submit" class="btn btn-mini btn-primary"><i class="icon-search icon-white"></i>保存</button>
					    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/FuncMenu/queryFuncMenu.do"><i class="icon-plus icon-white"></i>返回</a>
						</div><!-- /.row -->
					</fieldset>
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
	</body>
</html>
