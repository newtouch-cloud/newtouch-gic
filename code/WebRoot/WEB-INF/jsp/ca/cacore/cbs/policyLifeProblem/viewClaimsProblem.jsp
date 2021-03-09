<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
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
		<link href="<%=basePath%>/resources/ca/cacore/uploadify/uploadimg.css" rel="stylesheet" type="text/css" />
	    <%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs-1.7.2.jsp" %>
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script>
			 $(document).ready(function() {
				 
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
			 })
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>保单管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>理赔管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>理赔问题件管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>理赔问题件明细</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/PolicyLifeProblem/modifyPolicyLifeProblem.do" method="POST">
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
					<div class="row">
						<webTag:HiddenInputTag name="seq_id" id="seq_id" value='${rmHelper.returnParams.seq_id}'/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" value='${rmHelper.returnParams.insBranch_id}' displayLable="保险公司机构" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"/>
						<webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' readonly="true" displayLable="所属保单号" isdisplay="true"/>
					</div>
					<div class="row">
						<webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' readonly="true" displayLable="机构代码:"/>
						<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' readonly="true" displayLable="机构名称:"/>
						<webTag:Text id="app_name" name="app_name" value='${rmHelper.returnParams.app_name}' readonly="true" displayLable="投保人:"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="problemTypeSelect" name="type_code" id="type_code" value='${rmHelper.returnParams.type_code}' displayLable="问题件类型" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:DynamicSelectTag src="problemOriginTypeSelect" name="origin_type_code" id="origin_type_code" value='${rmHelper.returnParams.origin_type_code}' displayLable="问题件业务来源" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:DynamicSelectTag src="problemStatusSelect" name="status_code" id="status_code" value='${rmHelper.returnParams.status_code}' displayLable="问题件状态" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
						<webTag:TextareaTag id="notes" name="notes" rows='3' value='${rmHelper.returnParams.notes}' displayLable="问题件说明:" readonly="true"/>
					</div>
					<div class="row">
						<webTag:TextareaTag id="return_notes" name="return_notes" rows='3' value='${rmHelper.returnParams.return_notes}' readonly="true" displayLable="问题件回馈说明:"/>
					</div>
			   <!-- 影像预览 -->
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
					   <a id="viewBack" class="btn btn-danger" href="<%=basePath %>/CBS/PolicyLifeProblem/queryClaimsProblem.do"><i class="icon-share-alt icon-white"></i>  返回</a>
					</div>
					
				</form>
			</div>
			<!--  数据区 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
