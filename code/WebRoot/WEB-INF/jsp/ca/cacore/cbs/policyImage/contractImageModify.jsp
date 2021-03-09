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
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs-1.7.2.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script >
		
		//提交表单
		function submitImage(){
			var flag = $('#flag').val();
			
			var length = $("form").has("img").length
 			if(length ==　0){  //不允许修改影像件时没有关联影像件进行保存,
 				alert("没有找到影像信息,不能进行保存操作");
 				return false;
 			}
			
			if(flag == ""){
				alert("未检测到修改操作");//没有改则不提交表单
				return false;
			}else{
				return true;
			}
			
		}
		
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 影像管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保单影像件查询</span><span class="divider">/</span>
				<span><i class="icon-list icon-red"></i> 保单影像修改</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/cbs/policyImage/modifyContractImage.do" method="POST">
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>
					<webTag:HiddenInputTag id="send_code" name="send_code" value='${rmHelper.returnParams.send_code}'/>
					<webTag:HiddenInputTag id="policy_id" name="policy_id" value='${rmHelper.returnParams.policy_id}'/>
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
					  	<jsp:include page="../policyImage/imageModify.jsp"></jsp:include>
				  	</div>	
				    <div class="row" style="text-align:right;">
				    		<button id="submitBtn" type="submit" onclick="return submitImage();" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
				    		<a  class="btn btn-danger" href="<%=basePath %>/cbs/policyImage/queryContractImage.do" ><i class="icon-share-alt icon-white"></i>返回</a>
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
