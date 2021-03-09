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
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp" %>
		<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" %>
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/>
		<script >
		</script>	
				<script type="text/javascript" src="<%=basePath%>/compent/default/js/attachmentModify.js"></script>   
	</head>
	<body style="height: 750px">
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>协议管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>协议管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>明细</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
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
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag id="file_name" name="file_name" value="${rmHelper.returnParams.file_name}"/>
                    <webTag:HiddenInputTag id="file_id" name="file_id" value="${rmHelper.returnParams.file_id}"/>
                    <webTag:HiddenInputTag id="file_flag" name="file_flag" value="${rmHelper.returnParams.file_flag}"/>
                    <webTag:HiddenInputTag id="delete_file" name="delete_file" value=''/>
                    
					<fieldset>
					<div class="row" >
				  		<webTag:Text  iclass="branch_name" id="branch_name"  name="branch_name"  value='${rmHelper.returnParams.branch_name}' displayLable="归属机构" isdisplay="true" readonly="true"/>
						<webTag:Text  iclass="branch_id" id="branch_id"  name="branch_id"  value='${rmHelper.returnParams.branch_id}' displayLable="归属机构编码" isdisplay="true" readonly="true"/>
						<webTag:Text id="agreement_no"  name="agreement_no"   value='${rmHelper.returnParams.agreement_no}'  displayLable="协议号" isdisplay="true" readonly="true" />
				    </div>
				
					<div class="row" >
						<webTag:Date id="dateofsign" name="dateofsign" value='${rmHelper.returnParams.dateofsign}' displayLable="协议签订日期" isdisplay="true" readonly="true" />
					    <webTag:Date id="startdate"  name="startdate"  value='${rmHelper.returnParams.startdate}'  displayLable="生效日期" isdisplay="true" readonly="true" />
						<webTag:Date id="enddate"    name="enddate"    value='${rmHelper.returnParams.enddate}'    displayLable="终止日期" isdisplay="true" readonly="true" />

					</div>					
					<div class="row">                
<%-- 					    <webTag:DynamicSelectTag src="protocolStatusSelect"  id="status" name="status"  value='${rmHelper.returnParams.status}'      displayLable="状态"  isdisplay="true" readonly="true" /> --%>
					<!--<webTag:Text  id="status_name" name="status_name"  value='${rmHelper.returnParams.status_name}'      displayLable="状态"  isdisplay="true" readonly="true" />  -->	
					</div><!-- /.row -->
				    <div class="row" style="text-align:right;">

					    <a class="btn btn-danger" href="<%=basePath %>/organization/Protocol/queryProtocolList.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
				  </fieldset>	
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
