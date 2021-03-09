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
		<link href="<%=basePath%>/resources/ca/cacore/uploadify/uploadimg.css" rel="stylesheet" type="text/css" />
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs-1.7.2.jsp" %>
		<!--按钮返回控制 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		
		<script>
			 jQuery.validator.addMethod("queryBranch", function(value, element) {
				    $("#send_code").removeClass("error");  
					$("label[for='send_code'][class='error']").remove();
					$("label[for='insBranch_id'][class='error']").remove();
			     	var insBranch_id = $("#insBranch_id").val();
			     	var send_code = $("#send_code").val();
			     	if (!isUndefined(insBranch_id) && !isUndefined(send_code)) {
			     		$.ajax({
			     			url : "<%=basePath %>/PolicyLifeProblem/getInfo.do",
			     			type : "post",
			     			dataType : "json", 
			     			async : false,
			     			data : {
			     				"insBranch_id" : insBranch_id,
			     				"send_code" : send_code
			     			},
			     			success : function(data) {
			     				$.each(data,function(index,comment){
			     					var isSuccess =comment.isSuccess;
			     					if (isSuccess == "true") {
				     					var branch_id = comment.branch_id;
				     					var branch_name = comment.branch_name;
				     					var app_name = comment.app_name;
				     					var policy_id = comment.policy_id;
				     					$("#branch_id").val(branch_id);
				     					$("#branch_name").val(branch_name);
				     					if(app_name=="null"){
				     						$("#app_name").val("");
				     					}else{
				     						$("#app_name").val(app_name);
				     					}
				     					$("#policy_id").val(policy_id);
				     					$("#flag").val("true");
				     				}else if(isSuccess == "false1") {
				     					// 先清空带出信息
				     					$("#branch_id").val("");
				     					$("#branch_name").val("");
				     					$("#app_name").val("");
				     					$("#flag").val("false1");
				     				}else{
				     				    // 先清空带出信息
				     					$("#branch_id").val("");
				     					$("#branch_name").val("");
				     					$("#app_name").val("");
				     					$("#flag").val("false2");
				     				}
			     				});
			     			}
			     		});
			     	}
			     	if ($("#flag").val() == "false1") {
			     		return false;
			     	} else {
			     		return true;
			     	}
			     }, "输入的所属投保单号不存在，请重新输入。");
			 
			  jQuery.validator.addMethod("queryBranch2",function(value,element){
					  if($("#flag").val()=="false2"){
				     		return false;
				        	}else{
				     		return true;
				        	}
			   },"输入的所属投保单不能是无效投保单，请重新输入。");
			 
			 function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		     }
			 
			 $(document).ready(function() {
				 $("#queryForm").validate({
					 rules:{
						 insBranch_id:{
							 required:true,
							 queryBranch:[],
							 queryBranch2:[]
						 },
						 send_code:{
				 			 required:true,
				 			 queryBranch:[],
				 			 queryBranch2:[]
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
		    					required:true,
		    					maxlength : 2000
		    				 },
	    				 return_notes:{
		    					maxlength : 2000
		    				 }
					 },
					 messages:{
				 			type_code:{
				 				required:"问题件类型不能为空"
				 			},
				 			origin_type:{
				 				required:"问题件业务来源不能为空"
				 			},
				 			status:{
				 				required:"问题件状态不能为空"
				 			},
				 			notes:{
				 				required:"问题件说明不能为空"
				 			}
				 	},
					 onkeyup:false
				 })
			 });
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>保单管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>新契约管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>投保单问题件管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>投保单问题件录入</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12"  action="<%=basePath %>/PolicyLifeProblem/addPolicyLifeProblem.do?flag=1" method="POST">
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="policy_id" id="policy_id" value='${rmHelper.returnParams.insBranch_id}' displayLable="保单id"/>
					<div class="row">
						<webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" value='${rmHelper.returnParams.insBranch_id}' displayLable="保险公司机构" isdisplay="true"/>
						<webTag:Text id="send_code" name="send_code" value='' displayLable="所属投保单号"  isdisplay="true"/>
					</div>
					
					<div class="row">
						<webTag:Text id="branch_id" name="branch_id" value='' displayLable="机构代码:" readonly="true"/>
						<webTag:Text id="branch_name" name="branch_name" value='' displayLable="机构名称:" readonly="true"/>
						<webTag:Text id="app_name" name="app_name" value='' displayLable="投保人:" readonly="true" />
					</div>
					
					<div class="row">
					    <webTag:DynamicSelectTag src="problemTypeSelect" name="type_code" id="type_code" value='${rmHelper.returnParams.type_code}' displayLable="问题件类型" isdisplay="true"/>
						<webTag:DynamicSelectTag src="problemOriginTypeSelect" name="origin_type" id="origin_type" value='1' displayLable="问题件业务来源" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:DynamicSelectTag src="problemStatusSelect" name="status" id="status" value='' displayLable="问题件状态" isdisplay="true"/>
					</div>
					
					<div class="row">
						<webTag:TextareaTag id="notes" name="notes" rows='3' value='' displayLable="问题件说明" isdisplay="true"/>
					</div>
					
					<div class="row">
						<webTag:TextareaTag id="return_notes" name="return_notes" value='' rows='3' displayLable="问题件回馈说明:"/>
					</div>
					  <!-- 可伸缩区域样式结束 -->	
				    <div id="uploadimage" style="width:100%;">
					   <jsp:include page="../policyImage/imageUpload.jsp"></jsp:include>
					</div>
				    <div class="row" style="text-align:right;">
  				         <button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
					     <a class="btn btn-danger" href='<%=basePath %>/CBS/PolicyLifeProblem/queryPolicyLifeProblem.do'><i class="icon-share-alt icon-white"></i>返回</a>
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
