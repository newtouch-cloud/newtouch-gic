<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		
		
		
		
		
	
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean-ZM.css" >
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css" >
		<link href="<%=basePath%>/resources/ca/cacore/uploadify/uploadimg.css" rel="stylesheet" type="text/css" />
		<!-- jQuery 1.7.2-->
		<script src="<%=basePath%>/resources/ca/cacore/uploadify/jquery-1.7.2.min.js" type="text/javascript"></script>
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		<!-- fram plugins start--> 
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
						<!--  收缩 -->
		<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>
		<script >
		$(document).ready(function() {
			
//		 		子页面控制父页面高度开始
			  	var frameId = "#mainIframe";
				var frameW= 1060;//定义页面宽度
				var option = {
						FrameId : frameId,
						FrameW : frameW,
					};
				$(window).frameWH(option);//控制页面宽度
//		 		子页面控制父页面高度结束
			
			var ei = $("#large");//获取预览图片Div
		    ei.hide();  //页面加载时即隐藏图层 
			$("#img1").live('click',function(){//img1是 显示 上传图片的缩略图
					//鼠标点击小图时的时候 给div加上 图片
				var file_id= this.alt;
				ei.css({}).html('<img id="img2" style="border:1px solid gray;" src="' + this.src + '" />').show();
			    
			});
			$("#img2").live('click',function(){   
			   	ei.hide();   //点击被放大的图片后进行隐藏
			});
					    
		});
		
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 影像管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保单影像件查询</span><span class="divider">/</span>
				<span><i class="icon-list icon-red"></i> 影像明细</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/cbs/policyImage/queryPolicyImage.do" method="POST">
					
				    <div class="row">
					    <webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" displayLable="保险公司机构" value='${rmHelper.returnParams.insBranch_id}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"/>
					    <webTag:DynamicSelectTag src="imageBusTypeSelect" name="bus_type" id="bus_type" displayLable="上传类型" value='${rmHelper.returnParams.bus_type}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"/>
                        <webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="所属保单号" readonly="true" isdisplay="true"/>
					</div>
					<div class="row">
					<webTag:Text id="branch_id"  name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码:" readonly="true"/>
					<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="机构名称:" readonly="true"/>
					<webTag:Text id="app_name" name="app_name" value='${rmHelper.returnParams.app_name}' displayLable="投保人:" readonly="true"/>
					</div>
					<div id="uploadimage" style="width:100%;"> 
						  <table style="width: 100%;height:200px;" class="table table-striped table-bordered bootstrap-datatable datatable">						  
							<tr>
								<td><i class="icon-th-large icon-red"></i> 影像件预览</td>
								<td height="22px" valign="top" width="80%">
									<ul id="chens" class="thumbnails">
									<!-- 判断保单号是否关联了影像 -->
									<c:choose> 
									   <c:when test="${rmHelper.returnMsg.dataListSize == 0}">
									   		<div style='margin-left:12px'><webTag:ReturnMsgTag id="msg" name="msg"  msg='没有相关联的影像信息!' /></div>
									   		
									   </c:when>
									   <c:when test="${rmHelper.returnMsg.dataList != null}">
									   		<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
									    	    <li class='thumbnail'>	
									    			<img id="img1" src="<%=basePath %>/${sm.file_path}" class="magnify" style="width:200px; height:150px" />						    		
									    	    </li>
									    	</c:forEach>
									   </c:when>
									</c:choose>
									</ul>						     
									<div class="show" id="large" ></div>						     
								</td>
							</tr>						
						</table>
					</div>
				    <div class="row" style="text-align:right;">
				    		<a  class="btn btn-danger"  href="javascript:history.go(-1);" ><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
			<!-- 查询面板 end -->
			 	
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
