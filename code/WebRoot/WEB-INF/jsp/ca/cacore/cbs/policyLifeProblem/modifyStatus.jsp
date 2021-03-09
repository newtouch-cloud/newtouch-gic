<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>

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
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs-1.7.2.jsp" %>
		<!-- 按钮返回控制 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script>
			 $(document).ready(function() {
				 $("#queryForm").validate({
		    			rules:{
		    				 insBranch_id:{
								 required:true
							 },
							 send_code:{
					 			 required:true
					 		 },
					 		 type_code:{
		    					required:true
		    				 },
		    				 origin_type:{
		    					required:true
		    				 },
		    				 status:{
		    					required:true
		    				 },
		    				 notes:{
			    					required:true  
			    				 }
		    			},
				 		messages:{
				 			status_code:{
				 				required:"问题件状态不能为空"
				 			}
				 		},
		    			onkeyup:false
				 });
			 })
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount" id="policyLifeDiv">
			    <span class=mrl14><i class="icon-list icon-red"></i>保单管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>新契约管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>投保单问题件管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>投保单问题件状态更新</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/PolicyLifeProblem/modifyStatus.do?flag=1" method="POST">
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag id="flag" name="flag" value='${rmHelper.returnParams.flag}' displayLable="标志"/>
					<div class="row">
						<webTag:HiddenInputTag name="seq_id" id="seq_id" value='${rmHelper.returnParams.seq_id}'/>
					</div>
					
					<div class="row">
						<webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" value='${rmHelper.returnParams.insBranch_id}' displayLable="保险公司机构" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"/>
						<webTag:Text id="send_code" name="send_code" value='${rmHelper.returnParams.send_code}' readonly="true" displayLable="所属投保单号" isdisplay="true"/>
					</div>
					<div class="row">
						<webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' readonly="true" displayLable="机构代码:"/>
						<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' readonly="true" displayLable="机构名称:"/>
						<webTag:Text id="app_name" name="app_name" value='${rmHelper.returnParams.app_name}' readonly="true" displayLable="投保人:"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="problemTypeSelect" name="type_code" id="type_code" isdisplay="true" value='${rmHelper.returnParams.type_code}' displayLable="问题件类型" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:DynamicSelectTag src="problemOriginTypeSelect" name="origin_type_code" id="origin_type_code" value='${rmHelper.returnParams.origin_type_code}' displayLable="问题件业务来源" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:DynamicSelectTag src="problemStatusSelect" name="status_code" id="status_code" value='${rmHelper.returnParams.status_code}' displayLable="问题件状态" isdisplay="true"/>
					</div>
					<div class="row">
						<webTag:TextareaTag id="notes" name="notes" rows='3' value='${rmHelper.returnParams.notes}' displayLable="问题件说明:" readonly="true"/>
					</div>
					<div class="row">
						<webTag:TextareaTag id="return_notes" name="return_notes" rows='3' value='${rmHelper.returnParams.return_notes}' displayLable="问题件回馈说明:" readonly="true"/>
					</div>
				    <div class="row" style="text-align:right;">
				          <button type="submit" id="submitBtn" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
					      <a id = "policyLifeProblemBack" class="btn btn-danger" href='<%=basePath %>/CBS/PolicyLifeProblem/queryPolicyLifeProblem.do'><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
			<!--  数据区 end -->
		</div>
	</body>
</html>
