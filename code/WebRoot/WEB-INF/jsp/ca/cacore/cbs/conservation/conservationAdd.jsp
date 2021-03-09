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
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 按钮返回控制 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#opflag").val("true");
			});
		</script>
		<script>
			  jQuery.validator.addMethod("getPoliInfo", function(value, element) {
				    $("#policy_code").removeClass("error");  
					$("label[for='policy_code'][class='error']").remove();
					$("label[for='insBranch_id'][class='error']").remove();
			     	var insBranch_id = $("#insBranch_id").val();
			     	var policy_code = $("#policy_code").val();
			     	if (!isUndefined(insBranch_id) && !isUndefined(policy_code)) {
			     		$.ajax({
			     			url : "<%=basePath %>/CBS/Conservation/getPoliInfo.do",
			     			type : "post",
			     			async : false,
			     			data : {
			     				"insBranch_id" : insBranch_id,
			     				"policy_code" : policy_code
			     			},
			     			success : function(data) {
			     				var data1 =data.split('#')[0];
			     				var data2 =data.split('#')[1];
			     				var obj1=eval(data1);
			     				var obj2=eval(data2);
			     				
			     				$.each(obj1,function(index,comment){
			     					var isSuccess =comment.isSuccess;
			     					if (isSuccess == "true") {
			     						var policy_id = comment.policy_id;
				     					var branch_id = comment.branch_id;
				     					var holder_id = comment.holder_id;
				     					var holder_name = comment.holder_name;
				     					var insurant_id = comment.insurant_id;
				     					var insurant_name = comment.insurant_name;
				     					var validate_date = comment.validate_date;
				     					
				     					$("#policy_id").val(policy_id);
				     					$("#branch_id").val(branch_id);
				     					$("#holder_id").val(holder_id);
				     					$("#holder_name").val(holder_name);
				     					$("#insurant_id").val(insurant_id);
				     					$("#insurant_name").val(insurant_name);
				     					$("#validate_date").val(validate_date);
				     				
				     					
				     					$("#flag").val("true");
				     					$("#insBranch_id").change(function(){
				     						$("#opflag").val("true");
				     						}
				     					);
				     					$("#policy_code").change(function(){
				     						$("#opflag").val("true");
				     						}
				     					);
				     				} else {
				     					// 先清空带出信息
				     					$("#policy_id").val("");
				     					$("#branch_id").val("");
				     					$("#holder_id").val("");
				     					$("#holder_name").val("");
				     					$("#insurant_id").val("");
				     					$("#insurant_name").val("");
				     					$("#validate_date").val("");
				     					$("#applicant_id").empty();
				     					$("#applicant_id").append("<option value=''>---请选择---</option>");
				     					
				     					$("#flag").val("false");
				     					$("#opflag").val("true");
				     				}
			     				});
			     				if($("#flag").val() == "true"){
			     					if($("#opflag").val() == "true"){
			     					    $("#applicant_id").empty();
			     						$("#applicant_id").append("<option value=''>---请选择---</option>");
					     				$.each(obj2,function(index,comment){
											var applicant_name=comment.name;
											var applicant_id=comment.id;
												$("#applicant_id").append("<option value='"+applicant_id+"'>"+applicant_name+"</option>");
										});
					     				$("#opflag").val("false");
			     					}
			     				}
			     			}
			     		});
			     	}
			     	if ($("#flag").val() == "false") {
			     		return false;
			     	} else {
			     		$("label:contains('输入保单号无效或不存在，请重新输入。')").remove();
			     		return true;
			     	}
			     }, "输入保单号无效或不存在，请重新输入。");
			  //校验日期的先后顺序正确性
		     jQuery.validator.addMethod("checkDateOrder",function(value,element,param){
		     	var application_Time=$("#"+param[0]).val();
		     	var validate_date=$("#"+param[1]).val();
		     	if(!isUndefined(application_Time)&&!isUndefined(validate_date)){
		     		if(validate_date>application_Time){
		     			return false;
		     		}
		     		$("label:contains('申请日期不能小于保单生效日期')").remove();
		     		return true;
		     	}
		     	return true;
		     },"申请日期不能小于保单生效日期");
			 function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		     }
			 $(document).ready(function() {
				 $("#queryForm").validate({
					 rules:{
						 insBranch_id:{
							 required:true,
							 getPoliInfo:[],
							 checkDateOrder : ["application_Time","validate_date"]
						 },
						 policy_code:{
				 			 required:true,
				 			getPoliInfo:[],
				 			 checkDateOrder : ["application_Time","validate_date"]
				 		 },
				 		applicant_id:{
	    					required:true
	    				 },
	    				 application_Time:{
	    					required:true,
	    					 checkDateOrder : ["application_Time","validate_date"]
	    				 },
	    				 conservationItem_code:{
	    					required:true
	    				 },
	    				 replace_applicant: {
	    					 maxlength : 7
	    				 },
	    				 remark: {
	    					 maxlength : 500
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
			    <span class=mrl14><i class="icon-list icon-red"></i> 保单管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保全管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保全录入</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12"  action="<%=basePath %>/CBS/Conservation/addConservation.do" method="POST">
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="opflag" id="opflag"></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="branch_id" id="branch_id" value='${rmHelper.returnParams.branch_id}'></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="holder_id" id="holder_id" value='${rmHelper.returnParams.holder_id}'></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="insurant_id" id="insurant_id" value='${rmHelper.returnParams.insurant_id}'></webTag:HiddenInputTag>
					<webTag:HiddenInputTag id="policy_id" name="policy_id" value='${rmHelper.returnParams.policy_id}'></webTag:HiddenInputTag>
					<webTag:HiddenInputTag id="validate_date" name="validate_date" value='${rmHelper.returnParams.validate_date}'></webTag:HiddenInputTag>
					<div class="row">
						<webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" value='${rmHelper.returnParams.insBranch_id}' displayLable="保险公司机构" isdisplay="true"/>
						<webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="保单号"  isdisplay="true"/>
						<webTag:Text id="holder_name" name="holder_name" value='${rmHelper.returnParams.holder_name}' displayLable="投保人:" readonly="true" />
					</div>
					
					<div class="row">
						<webTag:Text id="insurant_name" name="insurant_name" value='${rmHelper.returnParams.insurant_name}' displayLable="主被保人:" readonly="true" />
						<webTag:DynamicSelectTag src="conservationItemSelect" name="conservationItem_code" id="conservationItem_code" value='${rmHelper.returnParams.conservationItem_Code}' displayLable="保全项" isdisplay="true"/>
						<webTag:Date id="application_Time" name="application_Time" value='${rmHelper.returnParams.application_Time}' displayLable="申请日期" isdisplay="true"/>
					</div>
					
					<div class="row">
						<webTag:DynamicSelectTag src="applicantSelect" name="applicant_id" id="applicant_id" value='${rmHelper.returnParams.applicant_id}' displayLable="申请人" isdisplay="true"/>
						<webTag:Text id="replace_applicant" name="replace_applicant" value='${rmHelper.returnParams.replace_applicant}' displayLable="代办人:"/>
					</div>
					<div class="row">
						<webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' displayLable="备注:"/>
					</div>
				    <div class="row" style="text-align:right;">
  				         <button id="subButton" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
  				        <a id="backBtn" class="btn btn-danger" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/main"><i class="icon-share-alt icon-white"></i>返回</a>
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
