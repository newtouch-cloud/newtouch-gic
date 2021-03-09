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
	    <%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs-1.7.2.jsp" %>
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script >
		 $(function() {
			 $("#mainForm").validate({
				 rules:{
					 insBranch_id:{
						 required:true
					 },
					 send_code:{
						 required:true,
					 },
					 branch_id:{
						 required:true 
					 },
					 ack_customer_date:{
						 required:true,
						 validateHoldDate:[],
						 validateDateOrder:[]
					 },
					 ack_branch_date:{
						 required:true ,
						 validateDateOrder:[]
					 },
					 ack_notes:{
						 maxlength : 2000
					 }
				 },
			  onkeyup:false
			 });
	     });
	     
	   jQuery.validator.addMethod("validateHoldDate",function(value,element){
		    var ack_customer_date = $("#ack_customer_date").val();
		    var hold_date = $("#hold_date").val();
		    if(ack_customer_date!=""){
		    	  if(ack_customer_date < hold_date ){
			     		return false;
			        	}else{
			     		return true;
			        	}
		    }else{
		    	return true;
		    }
	   },"客户签收日期不能小于投保日期");
	   
	    jQuery.validator.addMethod("validateDateOrder",function(value,element,param){
		     	var c_date=$("#ack_customer_date").val();
		     	var b_date=$("#ack_branch_date").val();
		     	if(c_date !="" &&b_date !=""){
		     		 if(c_date > b_date){
			     			return false;
			     		}
		     	}
		     	return true;
		     },"公司签收回执日期不能小于客户签收回执日期");
	    
     </script>		     
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>保单管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>新契约管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保单回执信息维护</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保单回执修改</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id= "mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/cbs/policyLifeAck/modifyPolicyLifeAck.do" method="POST">
					<fieldset>
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag id="flag" name="flag" value='${rmHelper.returnParams.flag}'/>
					<webTag:HiddenInputTag id="hold_date" name="hold_date" value='${rmHelper.returnParams.hold_date}'/>
					<webTag:HiddenInputTag id="policy_id" name="policy_id" value='${rmHelper.returnParams.policy_id}'/>
					<webTag:HiddenInputTag id="send_code" name="send_code" value='${rmHelper.returnParams.send_code}' displayLable="投保单号码"/>
					<webTag:HiddenInputTag id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
					<div class="row">
						    <webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" displayLable="保险公司机构" isdisplay="true" value='${rmHelper.returnParams.insBranch_id}'  onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						    <webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="所属保单号" isdisplay="true" readonly="true"/>
						</div>
						<div class="row">
							<webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码:" readonly="true"/>
							<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="机构名称:" readonly="true"/>
							<webTag:Text id="app_name" name="app_name" value='${rmHelper.returnParams.app_name}' displayLable="投保人姓名:" readonly="true"/>
						</div>
						<div class="row">
						    <webTag:Date id="ack_customer_date" name="ack_customer_date" value='${rmHelper.returnParams.ack_customer_date}' isdisplay="true" displayLable="客户签收回执日期"/>
							<webTag:Date id="ack_branch_date" name="ack_branch_date" value='${rmHelper.returnParams.ack_branch_date}' isdisplay="true" displayLable="公司签收回执日期" />
						</div>
						<div class="row">
							<webTag:TextareaTag id="ack_notes" name="ack_notes" rows='3' value='${rmHelper.returnParams.ack_notes}' displayLable="回执情况说明:"/>
						</div>
					    <div id="uploadimage" style="width:100%;">
							<jsp:include page="../policyImage/imageUpload.jsp"></jsp:include>
					    </div>
					    <div class="row" style="text-align:right;">
				    		 <button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
						     <a id="viewBack" class="btn btn-danger" href="<%=basePath %>/cbs/policyLifeAck/queryPolicyLifeAckList.do"><i class="icon-share-alt icon-white"></i>  返回</a>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
