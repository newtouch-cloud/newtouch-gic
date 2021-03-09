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
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>   
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章制度管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章维护</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 明细</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm"  class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/RegulationManagerController/addRegulationBasic.do" method="POST">
				     <!-- 提示信息标签 -->
				   	<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
				   <fieldset>
					     <div class="row">
					    	  <webTag:Text id="regulation_id" name="regulation_id"  value='${rmHelper.returnParams.regulation_id}'  displayLable="规章编号"  isdisplay="true" readonly="true"/>
					    	  <webTag:Text id="regulation_name" name="regulation_name"  value='${rmHelper.returnParams.regulation_name}' displayLable="规章名称"  isdisplay="true" readonly="true"/>
                              <webTag:DynamicSelectTag src="regStatusTypeSelect" name="regulation_status_code" id="regulation_status_code" value='${rmHelper.returnParams.regulation_status_code}' displayLable="规章状态"  isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
                             <webTag:Text id="makers" name="makers"  value='${rmHelper.returnParams.makers}' displayLable="制定人"  isdisplay="true" readonly="true"/>    
					</div>
					         <webTag:AttachmentDownloadTag busId='${rmHelper.returnParams.regulation_id}' attachmentType="17"  fileName='${rmHelper.returnParams.file_name}'  trueFlag='${(rmHelper.returnParams.upload_time) !=null }' basePath="<%=basePath %>"  displayLable="附件下载:"  noAttachment="未上传附件"/>					        
					<div class="row">                                    
                             <webTag:TextareaTag id="regulation_description" name="regulation_description" value='${rmHelper.returnParams.regulation_description}' displayLable="规章描述:" rows="5" readonly="readonly"/>
					</div>
					<div class="row" align="right">
			        <a class="btn btn-danger" href="<%=basePath %>/AMS/RegulationManagerController/queryAll.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				  </fieldset>	
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
