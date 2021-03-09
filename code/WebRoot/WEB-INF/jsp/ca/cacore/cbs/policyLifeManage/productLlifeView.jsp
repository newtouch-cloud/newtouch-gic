<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
<base target="_self"></base>
	<head>
		<title>新致金保通</title>
		<link rel="shortcut icon" href="<%=basePath %>/resources/ca/cacore/img/jv.png">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="CACore">
		<meta name="author" content="Newtouch">
	
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean-ZM.css" >
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >
		<!-- 校验失败样式 -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/compent/newtouch/util/validation.css">
		
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jqueryui/jquery-1.9.1.js"></script>
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jquery-ui-1.10.3.custom.js"></script>
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		
		<!-- fram plugins start--> 
		<script type="text/javascript" src="<%=basePath %>/compent/pagination/jquery.pagination.js"></script>
		
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/newtouch-validate.js"></script>
		<script >
		$(function() {
			$("#queryForm").validate({
				 onkeyup:false,
				 rules:{
					 period_prem_tow:{
						 equalTo:'#period_prem'
					 }
				 },
				 	messages:{
				 		period_prem:"请输入正确的保费",
				 		period_prem_tow:"请输入相同的保费",
				 	}
			 });
			//校验样式效果,文本框获取焦点,异常相应报错信息
			$("#queryForm").find("input").each(function(){
        		$(this).click(function(){
        			var _this=$(this);
        			if(_this.hasClass("error")){
        				_this.removeClass("error");
        				var labelAR = _this.parents().find("label[class='error']");
        				labelAR.remove();
        			}
        		});
        	});
			
			
			 var renew =  $("#renew").val();
			 var is_autoRen =  $("#is_autoRen").val();
			 $("#is_autoren").empty();
			 if(renew=="Y"){
			 	 if(is_autoRen=="1"){
			 		$("#is_autoren").append("<option value='1' selected>是</option>");
			 		$("#is_autoren").append("<option value='0'>否</option>");
			 	 
			 	 }else{
			 		 $("#is_autoren").append("<option value='0' selected>否</option>");
			 	 	 $("#is_autoren").append("<option value='1' >是</option>");
			 	 }
			 	 
			 }else if(renew=="N"){
			 	 $("#is_autoren").append("<option value='0' selected>否</option>");
			 }else if(renew==""){
				 if(is_autoRen=="1"){
				 		$("#is_autoren").append("<option value='1' selected>是</option>");
				 		$("#is_autoren").append("<option value='0'>否</option>");
				 	 
				 	 }else{
				 		 $("#is_autoren").append("<option value='0' selected>否</option>");
				 	 	 $("#is_autoren").append("<option value='1' >是</option>");
				 	 }
			 }
			 
			 $("#is_autoren").attr("disabled", "disabled");
			 var  insurant_name_arr=$("#insurant_name_arr").val();
		       var  insurant_name_value=$("#insurant_name_id").val();
		       var  insurant_name_selected=$("#insurant_name_selected").val();
			   var    obj    =    window.dialogArguments  ;
			   var strs = new Array();
			   var strs_arr = new Array();
			   var value_="";
			   	$("#insurant_name").empty();
			   if(insurant_name_arr!=""){
			   			strs = insurant_name_arr.split(",");
				   		strs_arr = insurant_name_value.split(",");
				   		$.each(strs, function (index, value) { 
				   			$.each(strs_arr, function (i, e) { 
						   		if(index==i){
					   				if(e!=""){
					   					value_=e;
					   				}
					   				return false;
				   				}
				   			});
			   				if(value!=""){
			   					if(insurant_name_selected==value_){
			   						$("#insurant_name").append("<option selected value='"+value_+"'>"+value+"</option>");
			   					}else{
			   						$("#insurant_name").append("<option value='"+value_+"'>"+value+"</option>");
			   					}
			   				}
			   			});
			   }else{
	      			strs = obj.name.split(",");
	      			strs_value = obj.value.split(",");
				   	$.each(strs, function (index, value) { 
					   	$.each(strs_value, function (index_value, e) { 
						   		if(index==index_value){
					   				if(e!=""){
					   					value_=e;
					   				}
					   				return false;
				   				}
				   			});
		   				if(value!=""){
			   					$("#insurant_name").append("<option value='"+value_+"'>"+value+"</option>");
			   				}
		   			});
			   }
			   
			    $("#insurant_name").attr("disabled", "disabled");
	     });
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/cbs/policyLife/policyLifeProductSaveInfo.do" method="POST">
				    <webTag:HiddenInputTag id="policy_tr_id" name="policy_tr_id" value='${rmHelper.returnParams.policy_tr_id}' displayLable="投保人id" />
				    <webTag:HiddenInputTag id="flag" name="flag" value='${rmHelper.returnParams.flag}'/>
					<webTag:HiddenInputTag id="ins_type" name="ins_type" value='${rmHelper.returnParams.ins_type}' displayLable="主附险标志"/>
					<webTag:HiddenInputTag id="product_type" name="product_type" value='${rmHelper.returnParams.product_type}' displayLable="产品分类1"/>
					<webTag:HiddenInputTag id="product_type2" name="product_type2" value='${rmHelper.returnParams.product_type2}' displayLable="产品分类2"/>
					<webTag:HiddenInputTag id="product_type3" name="product_type3" value='${rmHelper.returnParams.product_type3}' displayLable="产品分类3"/>
					<webTag:HiddenInputTag id="status" name="status" value='${rmHelper.returnParams.status}' displayLable="险种状态"/>
					<webTag:HiddenInputTag id="renew" name="renew" value='${rmHelper.returnParams.renew}' displayLable="是否可续保"/>
					<div class="row"> 
						<webTag:Text id="product_id" name="product_id" value='${rmHelper.returnParams.product_id}' displayLable="险种代码" isdisplay="true" iclass="required productInfo"  readonly="true"/>
						<webTag:Text id="product_name" name="product_name" value='${rmHelper.returnParams.product_name}' displayLable="险种名称" readonly="true" isdisplay="true"   />
						<webTag:Select id="insurant_name" name="insurant_name" value='${rmHelper.returnParams.insurant_name}' displayLable="被保人姓名" isdisplay="true" iclass="required">
                        </webTag:Select> 
                        <webTag:HiddenInputTag id="insurant_name_selected" name="insurant_name_selected" value='${rmHelper.returnParams.insurant_name_selected}' displayLable="被保人姓名锁定" />
						<webTag:HiddenInputTag id="insurant_name_arr" name="insurant_name_arr" value='${rmHelper.returnParams.insurant_name_arr}' displayLable="被保人姓名数组" />
						<webTag:HiddenInputTag id="insurant_name_id" name="insurant_name_id" value='${rmHelper.returnParams.insurant_name}' displayLable="被保人姓名id集合" />
					</div>
					<div class="row-fluid">
						<ul class="breadcrumb">
							<li>险种相关因素</li>
						</ul>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="libCoveragePeriodSelect" name="coverage_period" id="coverage_period" value='${rmHelper.returnParams.coverage_period}' displayLable="保障期限类型"  isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
						<webTag:Text id="coverage_year" name="coverage_year" value='${rmHelper.returnParams.coverage_year}' displayLable="保障时间" isdisplay="true" iclass="required"  readonly="true"/>
					</div>
					<div class="row"> 
						<webTag:DynamicSelectTag src="libChargePeriodSelect"  id="charge_period" name="charge_period" value='${rmHelper.returnParams.charge_period}'  displayLable="缴费期限类型"  isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:Text id="charge_year" name="charge_year" value='${rmHelper.returnParams.charge_year}' displayLable="缴费年龄或年限" isdisplay="true" iclass="required"  readonly="true"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="libChargeTypeSelect" name="charge_type_code" id="charge_type_code" value='${rmHelper.returnParams.charge_type}' displayLable="缴费方式" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
						<webTag:Text id="amount" name="amount" value='${rmHelper.returnParams.amount}' displayLable="基本保险金额" isdisplay="true" iclass="required checkPolicyMeny"  readonly="true"/>
						<webTag:Text id="period_prem" name="period_prem" value='${rmHelper.returnParams.period_prem}' displayLable="保费" isdisplay="true" iclass="required checkPolicyMeny"  readonly="true"/>
					</div>
					<div class="row">
						<webTag:Text id="period_prem_tow" name="period_prem_tow" value='${rmHelper.returnParams.period_prem}' displayLable="保费二录"  isdisplay="true" iclass="required"  readonly="true"/>
						<webTag:Text id="auth_payage" name="auth_payage" value='${rmHelper.returnParams.auth_payAge}' displayLable="年金领取年龄:" iclass="checkAge"  readonly="true"/>
						<webTag:DynamicSelectTag src="productAuthTypeSelect" name="auth_draw" id="auth_draw" value='${rmHelper.returnParams.auth_draw}' displayLable="年金领取方式:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:Text id="auth_firstpay" name="auth_firstpay" value='${rmHelper.returnParams.auth_firstPay}' displayLable="首期领取金额:" iclass="checkMeny"  readonly="true"/>
						<webTag:DynamicSelectTag src="productDividendTypeSelect" name="dividend_choice" id="dividend_choice" value='${rmHelper.returnParams.dividend_choice}' displayLable="红利领取方式:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
						<webTag:Select id="is_autoren" name="is_autoren" value='${rmHelper.returnParams.is_autoren}' displayLable="是否自动续保" isdisplay="true" iclass="required"> </webTag:Select> 
						<webTag:HiddenInputTag id="is_autoRen" name="is_autoRen" value='${rmHelper.returnParams.is_autoRen}' displayLable="首期领取金额"/>
					</div>
				    <div class="row" style="text-align:right;">
					    		<button type="button" onclick="window.close()"  class="btn btn-danger"><i class="icon-share-alt icon-white"></i>返回</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>