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
		<script>
		  $(document).ready(function() {
		     	var policy_id = $("#policy_id").val();
		     	if (!isUndefined(policy_id)) {
		     		$.ajax({
		     			url : "<%=basePath %>/CBS/Conservation/getApplyInfo.do",
		     			type : "post",
		     			async : false,
		     			data : {
		     				"policy_id" : policy_id
		     			},
		     			success : function(data) {
		     				var obj=eval(data);
		     				$.each(obj,function(index,comment){
		     						var applicant_name=comment.name;
									var applicant_id=comment.id;
									if($("#apply_id_tmp").val() == applicant_id){
										$("#applicant_id").append("<option value='"+applicant_id+"' selected='selected'>"+applicant_name+"</option>");
									}else{
										$("#applicant_id").append("<option value='"+applicant_id+"'>"+applicant_name+"</option>");
									} 
			     				}); 
		     				}
		     			});
		     		}
		  });
		//校验日期的先后顺序正确性
	     jQuery.validator.addMethod("checkDateOrder",function(value,element, param){
	           var insBranch_id=$("#"+param[0]).val();
	           var policy_code=$("#"+param[1]).val();
	           var application_time=$("#"+param[2]).val();
			var flag;
	              if(!isUndefined(policy_code) && !isUndefined(insBranch_id) && !isUndefined(application_time)){
	                  $.ajax({
	                      url:"<%=basePath %>/CBS/Conservation/checkDateOrder.do",
	                      type:"post",
	                      async: false,
	                      data:{"insBranch_id": insBranch_id,"policy_code":policy_code,"application_time":application_time},
	                      success:function(data){
	                          if(data=="true"){
								flag=true;
	                          }else{
	                       	   flag=false;
	                          }
	                      }
	                  });
	                  if(flag){
	                      $("label:contains('申请时间不能小于保单生效日期')").remove();
	                      return true;
	                  }else{
	                      return false;
	                  }
	              }else{
	                  return true;
	              }
		     	
		     },"申请时间不能小于保单生效日期");
		 function isUndefined(paraValue){
	        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
	        return false;
	     }
		 $(document).ready(function() {
			 $("#queryForm").validate({
				 rules:{
  				applicant_id:{
  					required:true
  				 }, 
  				application_time:{
 					required:true,
 					checkDateOrder:["insBranch_id","policy_code","application_time"]
  				 },
  				conservationItem_code:{
  					required:true
  				 },
  				replace_applicant: {
  					maxlength : 7
				 },
				 remark : {
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
			    <span><i class="icon-list icon-red"></i> 保全维护</span><span class="divider">/</span>
			     <span><i class="icon-list icon-red"></i> 保全信息修改</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12"  action="<%=basePath %>/CBS/Conservation/modifyConservation.do" method="POST">
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value='${rmHelper.successflag}'   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value='${rmHelper.msgStr}'    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="seq_id" id="seq_id" value='${rmHelper.returnParams.seq_id}'></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="policy_id" id="policy_id" value='${rmHelper.returnParams.policy_id}'></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="apply_id_tmp" id="apply_id_tmp" value='${rmHelper.returnParams.applicant_id}'></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="insBranch_id" id="insBranch_id" value='${rmHelper.returnParams.insBranch_id}'></webTag:HiddenInputTag>
					<div class="row">
						<webTag:Text  name="insBranch_name" id="insBranch_name" value='${rmHelper.returnParams.insBranch_name}' displayLable="保险公司机构" isdisplay="true" readonly="true"/>
						<webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="保单号"  isdisplay="true" readonly="true"/>
						<webTag:Text id="holder_name" name="holder_name" value='${rmHelper.returnParams.holder_name}' displayLable="投保人:" readonly="true" />
					</div>
					
					<div class="row">
						<webTag:Text id="insurant_name" name="insurant_name" value='${rmHelper.returnParams.insurant_name}' displayLable="主被保人:" readonly="true" />
						<webTag:DynamicSelectTag src="conservationItemSelect" name="conservationItem_code" id="conservationItem_code" value='${rmHelper.returnParams.conservationItem_code}' displayLable="保全项" isdisplay="true"/>
						<webTag:DynamicSelectTag src="applicantSelect" name="applicant_id" id="applicant_id" value='${rmHelper.returnParams.applicant_id}' displayLable="申请人" isdisplay="true"/>
					</div>
					
					<div class="row">
						<webTag:Date id="application_time" name="application_time" value='${rmHelper.returnParams.application_time}' displayLable="申请日期" isdisplay="true"/>
						<webTag:Text id="replace_applicant" name="replace_applicant" value='${rmHelper.returnParams.replace_applicant}' displayLable="代办人:"/>
					</div>
					
					<div class="row">
						<webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' displayLable="备注:"/>
					</div>
					
				    <div class="row" style="text-align:right;">
  				         <button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
  				         <a id="backBtn" class="btn btn-danger" href="<%=basePath %>/CBS/Conservation/queryConservationsList.do"><i class="icon-share-alt icon-white"></i>返回</a>
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
