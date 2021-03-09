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
		
		
		
		
		
	
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean-ZM.css" >
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >
		<!-- 校验失败样式 -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/compent/newtouch/util/validation.css">
		
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jqueryui/jquery-1.9.1.js"></script>
	    <script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>
		<%-- <script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jquery-ui-1.10.3.custom.js"></script> --%>
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
			<script type="text/javascript">
	$(document).ready(function() {
//	 		子页面控制父页面高度开始
	  	var frameId = "#mainIframe";
		var frameW= 1060;//定义页面宽度
		var option = {
				FrameId : frameId,
				FrameW : frameW,
			};
		$(window).frameWH(option);//控制页面宽度
	    
	});
	</script>	
		<script >
		 var charge_type_code_hidden,charge_period_code_hidden,coverage_period_code_hidden;
		 $(function() {
			$("#queryForm").validate({
				 onkeyup:false,
				 rules:{
					 product_id_tow:{ //二录姓名需要与投保人相同
						 equalTo:'#product_id'
					 },
					 period_prem_tow:{
						 equalTo:'#period_prem'
					 }
				
				 },
				 	messages:{
				 		period_prem:"请输入正确的保费 ",
				 		period_prem_tow:"请输入相同的保费",
				 		coverage_year:"请输入正确的保险期间",
				 		product_id_tow:"请输入相同的险种代码",
				 		charge_year:"请输入正确的缴费年龄或年限"
				 	}
			 });
			//校验样式效果,文本框获取焦点,异常相应报错信息
			/* $("#queryForm").find("input").each(function(){
        		$(this).click(function(){
        			var _this=$(this);
        			if(_this.hasClass("error")){
        				_this.removeClass("error");
        				var labelAR = _this.parents().find("label[class='error']");
        				labelAR.remove();
        			}
        		});
        	}); */
			
			$(document).ready(function() {
				 
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
				 
				
				 /* else{
				 	$("#is_autoren").append("<option >---请选择---</option>");
				 } */
				 var coverage_period_value = $('#coverage_period option:selected').val();//保障期限类型
				 var charge_period_value = $('#charge_period option:selected').val();//缴费期限类型代码
				 if(coverage_period_value=="1"){
				 	$("#coverage_year").attr("readOnly","readOnly");
				 }
				 
				 if(charge_period_value=="1"){
				 	$("#charge_year").attr("readOnly","readOnly");
				 }
				//先加载 险种 ajax查询方法
				  ajaxPro();
			 
			});
			 
			 var  insurant_name_arr=$("#insurant_name_arr").val();
		     var  insurant_name_value=$("#insurant_name_id").val();
		     var  insurant_name_selected=$("#insurant_name_selected").val();
			 var    obj    =    window.dialogArguments  ;
			   //$("#send_code").val(obj.send_code);//投保单号
			  // $("#branch_id").val(obj.branch_id);//中介机构id
			  // $("#insbranch_id").val(obj.insbranch_id);//保险机构id
			  // $("#money_id").val(obj.money_id);//币种
			  // $("#hold_date").val(obj.hold_date);//投保日期
			  // $("#high_policy").val(obj.high_policy);//是否高额件
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
			   //当保障期限类型、缴费期限类型、缴费方式的下拉选值改变时，同步它的值到存储变量中
			   //页面加载的时候给储存变量赋值
			   charge_type_code_hidden=$("#charge_type_code").val();
			   charge_period_code_hidden=$("#charge_period").val();
			   coverage_period_code_hidden=$("#coverage_period").val();
			   $("#charge_type_code").change(function(){
				   charge_type_code_hidden=$("#charge_type_code").val();
			   });
			   $("#charge_period").change(function(){
				   charge_period_code_hidden=$("#charge_period").val();
			   });
			   $("#coverage_period").change(function(){
				   coverage_period_code_hidden=$("#coverage_period").val();
			   });
			 //根据保险公司机构和险种代码过滤保障期限类型、缴费期限类型、缴费方式的下拉选
			   $("#product_id").blur(function(){
				  
				   ajaxPro();
			   });
			 
	     });
		 //
		 function ajaxPro(){
			   var    obj    =    window.dialogArguments  ;
			   var product_id = $("#product_id").val();
			   var insBranch_id=obj.insbranch_id;
			   if(isUndefined(product_id)||isUndefined(insBranch_id)){
					return;					   
			   }
			//请求缴费方式
			   $.ajax({
		   			 url:"<%=basePath %>/CBS/PolicyLife/getChargeType.do",
		   			 type:"post",
		   			 async: false,
		   			 data:{"product_id":product_id,"insBranch_id":insBranch_id},
		   			 dataType:"json",
		   			 success:function(data){
		   				 if(data=="[]"){
		   					 return ;
		   				 }
		   				 $("#charge_type_code").empty();
						 $("#charge_type_code").append("<option value=''>---请选择---</option>");
						 $.each(data,function(index,value){
							var charge_type_code=value.code;
							var charge_type_name=value.name;
							if(charge_type_code==charge_type_code_hidden){
								$("#charge_type_code").append("<option value='"+charge_type_code+"' selected='selected'>"+charge_type_name+"</option>");
							}else{
								$("#charge_type_code").append("<option value='"+charge_type_code+"'>"+charge_type_name+"</option>");
							}
						});
		   			 }
			   });
			   //请求缴费期限类型
			   $.ajax({
		   			 url:"<%=basePath %>/CBS/PolicyLife/getCharge_Period.do",
		   			 type:"post",
		   			 async: false,
		   			 data:{"product_id":product_id,"insBranch_id":insBranch_id},
		   			 dataType:"json",
		   			 success:function(data){
		   				if(data=="[]"){
		   					 return;
		   				 }
		   				 $("#charge_period").empty();
						 $("#charge_period").append("<option value=''>---请选择---</option>");
						$.each(data,function(index,comment){
							var charge_period_code=comment.code;
							var charge_period_name=comment.name;
							if(charge_period_code==charge_period_code_hidden){
								$("#charge_period").append("<option value='"+charge_period_code+"' selected='selected'>"+charge_period_name+"</option>");
							}else{
								$("#charge_period").append("<option value='"+charge_period_code+"'>"+charge_period_name+"</option>");
							}
						});
		   			 }
			   });
			   //请求保障期限类型
			   $.ajax({
		   			 url:"<%=basePath %>/CBS/PolicyLife/getCoveragePeriod.do",
		   			 type:"post",
		   			 async: false,
		   			 data:{"product_id":product_id,"insBranch_id":insBranch_id},
		   			 dataType:"json",
		   			 success:function(data){
		   				if(data=="[]"){
		   					 return false;
		   				 }
		   				 $("#coverage_period").empty();
						 $("#coverage_period").append("<option value=''>---请选择---</option>");
						$.each(data,function(index,comment){
							var coverage_period_code=comment.code;
							var coverage_period_name=comment.name;
							if(coverage_period_code==coverage_period_code_hidden){
								$("#coverage_period").append("<option value='"+coverage_period_code+"' selected='selected'>"+coverage_period_name+"</option>");
							}else{
								$("#coverage_period").append("<option value='"+coverage_period_code+"'>"+coverage_period_name+"</option>");
							}
						});
		   			 }
			   });
		 }
		/*
	      *判断参数是否为未定义或null,如未定义或为null返回true
	      *@param paraValue 待处理字符串
	      *@return true或者false
	      */
	     function isUndefined(paraValue){
	        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
	        return false;
	     }
		//产品ajax查询
        jQuery.validator.addMethod("productInfo",function(value,element){
   		 var product_id = $("#product_id").val();
   		 var obj = window.dialogArguments ;
   		 var insBranch_id=obj.insbranch_id;
   		 $.ajax({
   			 url:"<%=basePath %>/cbs/commonAsyn/getProductInfo.do",
   			 type:"post",
   			 dataType : "json",
   			 async: false,
   			 data:{"product_id":product_id,"insBranch_id":insBranch_id},
   			 success:function(data){
   				 $.each(data,function(index,comment){
   					var success = comment.success;
   				    if("true"==success){
   					 var product_name = comment.product_name;
   					 var ins_type = comment.ins_type;
   					 var product_type = comment.product_type;
   					 var product_type2 = comment.product_type2;
   					 var product_type3 = comment.product_type3;
   					 var status = comment.status;
   					 var renew = comment.renew;
   					 var insuredFlag = comment.insuredFlag;
   					 $("#product_name").val(product_name);
   					 $("#ins_type").val(ins_type);
   					 $("#product_type").val(product_type);
   					 $("#product_type2").val(product_type2);
   					 $("#product_type3").val(product_type3);
   					 $("#status").val(status);
   					 $("#renew").val(renew);
   					 $("#insuredFlag").val(insuredFlag);
   					 $("#is_autoren").empty();
   					 if(renew=="Y"){
   					 	 $("#is_autoren").append("<option value='1'>是</option>");
   					 	 $("#is_autoren").append("<option value='0'>否</option>");
   					 }else if(renew=="N"){
   					 	 $("#is_autoren").append("<option value='0'>否</option>");
   					 }else if(renew==""){
   						$("#is_autoren").append("<option value='1'>是</option>");
  					 	 $("#is_autoren").append("<option value='0'>否</option>");
   					 }
   					 $("#flag").val("0");
   				    }else{
   					 $("#product_name").val("");
   					 $("#ins_type").val("");
   					 $("#product_type").val("");
   					 $("#product_type2").val("");
   					 $("#product_type3").val("");
   					 //清空储存变量的值
   					 charge_period_code_hidden = "";
   					 charge_period_code_hidden = "";
   					 coverage_period_code_hidden = "";
   					 $("#status").val("");
   					 $("#renew").val("");
   					  $("#flag").val("1");
   				     }
   				 });
   			  }
   		   });
   		  if($("#flag").val()=="1"){
   	     		return false;
   	        }else{
   	     		return true;
   	         }
   	   },"险种不存在"); 
        
   	   //保障期限类型 控制事件
        jQuery.validator.addMethod("coverage_period",function(value,element){
        	//1为保终身
   		  if(value=="1"){
   		  	$("#coverage_year").val("99");
   		  	$("#coverage_year").attr("readOnly","readOnly");
   		  }else if(value=="0"){
     		  	$("#coverage_year").val("99");
       		  	$("#coverage_year").attr("readOnly","readOnly");
       		}else{
    	   		$('#coverage_year').removeAttr("readonly");
       	  }
   		  return true;
   	   },"");
        //缴费期限类型 控制事件
         jQuery.validator.addMethod("charge_period",function(value,element){
        	//1为保终身
   		  if(value=="1"){
   		  	$("#charge_year").val("1");
   		  	$("#charge_year").attr("readOnly","readOnly");
   		  }else if(value=="0"){
    		  	$("#charge_year").val("99");
    		  	$("#charge_year").attr("readOnly","readOnly");
    	  }else{
    		  	$('#charge_year').removeAttr("readonly");
    	  }
   		  return true;
   	   },"");
        
        //险种是否存在重复校验
         jQuery.validator.addMethod("productInfo_repeat",function(value,element){
        	 $("#product_id").removeClass("error");  
			 $("label[for='product_id'][class='error']").remove();
        	 var product_id = $("#product_id").val();//产品代码
        	 var insuredFlag = $("#insuredFlag").val();//是否允许多被保人 m允许 s不允许 
     		 var insurant_select = $('#insurant_name option:selected').val();//被保人姓名代码
        	 var  obj = window.dialogArguments  ;
			 var arr=obj.beneficiary_arr.split(",");
			 if(arr!=""){
				 if(obj.beneficiary_arr.length>1){
					   for(var g = 0 ;g<arr.length;g++){
					   		var beneficiary_ =  arr[g].split(":");  //分割
					   			if(insurant_select==beneficiary_[0]){
			 		   				if(product_id ==beneficiary_[1]){   //
			 		   					return false;  //
			 		   				}
			 	   				}
	 	    			}
				 }
			 }
   		  return true;
   	   },"被保险人此险种信息已添加，请重新输入。");
        
         jQuery.validator.addMethod("productInfo_repeat_tow",function(value,element){
        	 $("#product_id").removeClass("error");  
			 $("label[for='product_id'][class='error']").remove();
        	 var product_id = $("#product_id").val();//产品代码
        	 var insuredFlag = $("#insuredFlag").val();//是否允许多被保人 m允许 s不允许 
     		 var insurant_select = $('#insurant_name option:selected').val();//被保人姓名代码
        	 var  obj = window.dialogArguments  ;
			 var arr=obj.beneficiary_arr.split(",");
			 if(arr!=""){
				 if(obj.beneficiary_arr.length>1){
					   for(var g = 0 ;g<arr.length;g++){
					   		var beneficiary_ =  arr[g].split(":");  //分割
					   		 if(insuredFlag=="S"){
			 		   				if(product_id ==beneficiary_[1]){   //
			 		   					return false;  //
			 		   				}
					   		}
		 			   		
	 	    			}
				 }
			 }
   		  return true;
   	   },"该险种为单一被保人，请重新输入。");
        
        
 		function policySave(){
 		 //首期领取金额小于基本保险金额
 		 var auth_firstpay = $("#auth_firstpay").val();//首期领取金额
 		 var amount = $('#amount').val();//基本保险金额
 		 if(eval(auth_firstpay)>eval(amount)){
 		 	alert("首期领取金额应小于基本保险金额");
 		 	return false;
 		 }
 		 
 		 var product_id = $("#product_id").val();//产品代码
 		 var insurant_select = $('#insurant_name option:selected').val();//被保人姓名代码
 		 var is_autoren = $('#is_autoren option:selected').val();//是否自动续保
 		/*  var  obj = window.dialogArguments  ;
			 var arr=obj.beneficiary_arr.split(",");
			 if(arr!=""){
				 if(obj.beneficiary_arr.length>1){
					   for(var g = 0 ;g<arr.length;g++){
					   		var beneficiary_ =  arr[g].split(":");  //分割
		 			   		if(insurant_select==beneficiary_[0]){
		 		   				if(product_id ==beneficiary_[1]){   //
		 		   					alert("被保险人此险种信息已添加，请重新输入");
		 		   					return false;  //
		 		   				}
		 	   				}
	 	    			}
				 }
			 } */
 			if($("#queryForm").valid()){  //再通过表单校验的情况下才执行if内的内容
		 		var insurant_name = $('#insurant_name option:selected').text();//被保人姓名
				var product_name = $("#product_name").val();//产品名称
				var name = $('#name option:selected').text();//被保人
				var coverage_period_code = $('#coverage_period option:selected').text();//保障期限类型
				var coverage_period_value = $('#coverage_period option:selected').val();//保障期限类型
				var coverage_year = $("#coverage_year").val();//
				var charge_period_code = $('#charge_period option:selected').text();//缴费期限类型
				var charge_period_value = $('#charge_period option:selected').val();//缴费期限类型代码
				var charge_year = $("#charge_year").val();//保障年数
				var charge_type_code = $('#charge_type_code option:selected').text();//缴费方式
				var charge_type_value = $('#charge_type_code option:selected').val();//缴费方式代码
				
				var period_prem = $('#period_prem').val();//保费
				var policy_tr_id = $("#policy_tr_id").val();//trid
				var auth_payage = $("#auth_payage").val();//年金领取年龄
				var auth_draw = $('#auth_draw option:selected').val();//年金领取方式
				
				var dividend_choice = $('#dividend_choice option:selected').val();//红利领取方式
				var ins_type = $("#ins_type").val();//主附险标志
				var product_type = $("#product_type").val();//产品分类1
				var product_type2 = $("#product_type2").val();//产品分类2
				var product_type3 = $("#product_type3").val();//产品分类3
				var status = $("#status").val();//险种状态
				var seq_id = $("#seq_id").val();//险种id
				//var is_autoren = $("#is_autoren").val();//是否可续保
				//alert(is_autoren);
				var renew = $("#renew").val();//险种-是否可续保
				var obj =[{"product_id":product_id,
								"insurant_name":insurant_name,
								"product_name":product_name,
								"name":name,
								"amount":amount,
								"coverage_period_code":coverage_period_code,
								"coverage_period_value":coverage_period_value,
								"coverage_year":coverage_year,
								"charge_period_code":charge_period_code,
								"charge_period_value":charge_period_value,
								"coverage_year":coverage_year,
								"charge_period_code":charge_period_code,
								"charge_period_value":charge_period_value,
								"charge_year":charge_year,
								"charge_type_code":charge_type_code,
								"charge_type_value":charge_type_value,
								"coverage_time":amount,
								"period_prem":period_prem,
								"auth_payage":auth_payage,
								"auth_draw":auth_draw,
								"auth_firstpay":auth_firstpay,
								"dividend_choice":dividend_choice,
								"is_autoren":is_autoren,
								"ins_type":ins_type,
								"product_type":product_type,
								"product_type2":product_type2,
								"product_type3":product_type3,
								"status":status,
								"is_autoren":is_autoren,
								"insurant_select":insurant_select,
								"renew":renew,
								"seq_id":seq_id,
								"policy_tr_id":policy_tr_id
							}];
						window.returnValue=obj //把这个数组返回到父页面
						window.close();
 			}
 		}
	      
		
		</script>
		
	
	</head>
	
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<!-- 面包屑导航  end -->
			
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
					<webTag:HiddenInputTag id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}' displayLable="产品id"/>
					<webTag:HiddenInputTag id="insuredFlag" name="insuredFlag" value='${rmHelper.returnParams.insuredFlag}' displayLable="是否允许多被保人"/>
					<div class="row"> 
						<webTag:Text id="product_id" name="product_id" value='${rmHelper.returnParams.product_id}' displayLable="险种代码" isdisplay="true" iclass="required productInfo productInfo_repeat productInfo_repeat_tow"/>
						<webTag:Text id="product_id_tow" name="product_id_tow" value='${rmHelper.returnParams.product_id}' displayLable="险种代码二录" isdisplay="true" iclass="required productInfo"/>
                        <webTag:HiddenInputTag id="insurant_name_selected" name="insurant_name_selected" value='${rmHelper.returnParams.insurant_name_selected}' displayLable="被保人姓名锁定" />
						<webTag:HiddenInputTag id="insurant_name_arr" name="insurant_name_arr" value='${rmHelper.returnParams.insurant_name_arr}' displayLable="被保人姓名数组" />
						<webTag:HiddenInputTag id="insurant_name_id" name="insurant_name_id" value='${rmHelper.returnParams.insurant_name}' displayLable="被保人姓名id集合" />
					</div>
					<div class="row"> 
						<webTag:Text id="product_name" name="product_name" value='${rmHelper.returnParams.product_name}' displayLable="险种名称" readonly="true" isdisplay="true" iclass="required"/>
						<webTag:Select id="insurant_name" name="insurant_name" value='${rmHelper.returnParams.insurant_name}' displayLable="被保险人" isdisplay="true" iclass="required productInfo_repeat productInfo_repeat_tow">
                        </webTag:Select> 
					</div>
				<div class="row-fluid">
					<ul class="breadcrumb">
						<li>险种相关因素</li>
					</ul>
				</div>
					<div class="row">
						<webTag:DynamicSelectTag src="libCoveragePeriodSelect" name="coverage_period" id="coverage_period" value='${rmHelper.returnParams.coverage_period}' displayLable="保障期限类型"  isdisplay="true" iclass="required coverage_period"></webTag:DynamicSelectTag>
						<webTag:Text id="coverage_year" name="coverage_year" value='${rmHelper.returnParams.coverage_year}' displayLable="保险期间" isdisplay="true" iclass="required checkAge"/>
					</div>
					<div class="row"> 
						<webTag:DynamicSelectTag src="libChargePeriodSelect"  id="charge_period" name="charge_period" value='${rmHelper.returnParams.charge_period}'  displayLable="缴费期限类型"  isdisplay="true" iclass="required charge_period"/>
						<webTag:Text id="charge_year" name="charge_year" value='${rmHelper.returnParams.charge_year}' displayLable="缴费年龄或年限" isdisplay="true" iclass="required checkAge"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="libChargeTypeSelect" name="charge_type_code" id="charge_type_code" value='${rmHelper.returnParams.charge_type}' displayLable="缴费方式" isdisplay="true" iclass="required"></webTag:DynamicSelectTag>
						<webTag:Text id="amount" name="amount" value='${rmHelper.returnParams.amount}' displayLable="基本保险金额" isdisplay="true" iclass="required checkPolicyMeny"/>
						<webTag:Text id="period_prem" name="period_prem" value='${rmHelper.returnParams.period_prem}' displayLable="保费" isdisplay="true" iclass="required checkPolicyMeny " />
					</div>
					<div class="row">
						<webTag:Text id="period_prem_tow" name="period_prem_tow" value='${rmHelper.returnParams.period_prem}' displayLable="保费二录"  isdisplay="true" iclass="required"/>
						<webTag:Text id="auth_payage" name="auth_payage" value='${rmHelper.returnParams.auth_payAge}' displayLable="年金领取年龄:" iclass="checkAge"/>
						<webTag:DynamicSelectTag src="productAuthTypeSelect" name="auth_draw" id="auth_draw" value='${rmHelper.returnParams.auth_draw}' displayLable="年金领取方式:"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:Text id="auth_firstpay" name="auth_firstpay" value='${rmHelper.returnParams.auth_firstPay}' displayLable="首期领取金额:" iclass="checkMeny"/>
						<webTag:DynamicSelectTag src="productDividendTypeSelect" name="dividend_choice" id="dividend_choice" value='${rmHelper.returnParams.dividend_choice}' displayLable="红利领取方式:"></webTag:DynamicSelectTag>
						
						<webTag:Select id="is_autoren" name="is_autoren" value='${rmHelper.returnParams.is_autoren}' displayLable="是否自动续保" isdisplay="true" iclass="required">
                        </webTag:Select> 

						<webTag:HiddenInputTag id="is_autoRen" name="is_autoRen" value='${rmHelper.returnParams.is_autoRen}' displayLable="首期领取金额:"/>

                        
					</div>
				</form>
									
				    <div class="row" style="text-align:right;">
					    		<button type="button"  onclick="policySave()"  class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
					    		<button type="button" onclick="window.close()"  class="btn btn-danger"><i class="icon-share-alt icon-white"></i>返回</button>
					</div><!-- /.row -->
			</div>
			
			
		</div>
	</body>
	
</html>