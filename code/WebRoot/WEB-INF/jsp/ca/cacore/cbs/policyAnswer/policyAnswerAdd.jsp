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
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
	    <script >
	    $(function() {
			 $("#mainForm").validate({
				 rules:{
					 insBranch_id:{
						 required:true,
						 branchInfo:[],
						 branchInfo1:[],
						 branchInfo2:[]
					 },
					 policy_code:{
						 required:true,
						 branchInfo:[],
						 branchInfo1:[],
						 branchInfo2:[]
					 },
					 answer_type_code:{
						 required:true
					 },
					 is_success:{
						 required:true 
					 },
					 answer_notes :{
						 maxlength : 2000
					 }
				 },
			  onkeyup:false,
			 });
	     });
	     
	   jQuery.validator.addMethod("branchInfo",function(value,element){
		    $("#policy_code").removeClass("error");  
			$("label[for='policy_code'][class='error']").remove();
			$("label[for='insBranch_id'][class='error']").remove();
		     var insBranch_id = $("#insBranch_id").val();
			 var policy_code = $("#policy_code").val();
			 if(insBranch_id!="" && policy_code!=""){
				 $.ajax({
					 url:"<%=basePath %>/policyAnswer/getContractMasterInfo.do",
					 type:"post",
					 dataType: "json",
					 async: false,
					 data:{"insBranch_id":insBranch_id,"policy_code":policy_code},
					 success:function(data){
						 $.each(data,function(index,comment){
							 var isSuccess = comment.success;
							 if("true"==isSuccess){
								 var branch_id = comment.branch_id;
								 var branch_name = comment.branch_name;
								 var app_name = comment.app_name;
								 var policy_id = comment.policy_id;
								 $("#branch_id").val(branch_id);
								 $("#branch_name").val(branch_name);
								 $("#app_name").val(app_name);
								 $("#policy_id").val(policy_id);
								 $("#flag").val("0");
							 }else if("false1"==isSuccess){
							      $("#branch_id").val("");
							      $("#branch_name").val("");
								  $("#app_name").val("");
								  $("#flag").val("1");
							  }else if("false2"==isSuccess){
								  $("#branch_id").val("");
							      $("#branch_name").val("");
								  $("#app_name").val("");
								  $("#flag").val("2");
							  }else{
								  $("#branch_id").val("");
							      $("#branch_name").val("");
								  $("#app_name").val("");
								  $("#flag").val("3");
							  }
						 });
					   }
				   });
			 }
			  if($("#flag").val()=="1"){
	     		return false;
	        	}else{
	     		return true;
	        	}
	   },"保单号在保险公司不存在");
	   
	   jQuery.validator.addMethod("branchInfo1",function(value,element){
			  if($("#flag").val()=="2"){
		     		return false;
		        	}else{
		     		return true;
		        	}
	   },"保单已经得到回访");
	   
	   jQuery.validator.addMethod("branchInfo2",function(value,element){
			  if($("#flag").val()=="3"){
		     		return false;
		        	}else{
		     		return true;
		        	}
	   },"保单状态必须是有效状态");
	   
     </script>		     
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>回访管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保单回访录入</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id= "mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/policyAnswer/addPolicyAnswer.do" method="POST">
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag id="flag" name="flag" value='${rmHelper.returnParams.flag}'/>
					<webTag:HiddenInputTag id="policy_id" name="policy_id" value='${rmHelper.returnParams.policy_id}'/>
					<fieldset>
						<div class="row">
						    <webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" displayLable="保险公司机构" isdisplay="true" value='${rmHelper.returnParams.insBranch_id}'/>
						    <webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' isdisplay="true" displayLable="所属保单号"/>
						</div>
						<div class="row">
							<webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码:" readonly="true"/>
							<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="机构名称:" readonly="true"/>
							<webTag:Text id="app_name" name="app_name" value='${rmHelper.returnParams.app_name}' displayLable="投保人姓名:" readonly="true"/>
						</div>
						<div class="row">
						    <webTag:DynamicSelectTag src="answerTypeSelect" name="answer_type_code" id="answer_type_code" displayLable="回访方式" isdisplay="true" value='${rmHelper.returnParams.answer_type_code}'/>
						    <webTag:DynamicSelectTag src="answerSuccessSelect" name="is_success" id="is_success" displayLable="回访是否成功" isdisplay="true" value='${rmHelper.returnParams.is_success}'/>
						</div>
						<div class="row">
							<webTag:TextareaTag id="answer_notes" name="answer_notes" rows='3' value='${rmHelper.returnParams.answer_notes}' displayLable="回访情况说明:"/>
						</div>
					    <div class="row" style="text-align:right;">
				    		<button type="submit" id="policyLifeSave" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
							<a id="viewBack" class="btn btn-danger" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/main"><i class="icon-share-alt icon-white"></i>  返回</a>
						</div>
					</fieldset>
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
	</body>
</html>
