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
<base target="_self"/>
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
		<%-- <script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jquery-ui-1.10.3.custom.js"></script> --%>
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		
		<!-- fram plugins start--> 
		<script type="text/javascript" src="<%=basePath %>/compent/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/ajax/newtouch.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/newtouch-validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
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
         var strs_order = new Array();  //接收被保人id+已有收益顺序,strs_order含有被保人及其对应的 已有受益顺序
        //异步请求：自动带出数据
	     $(function() {
	    	 var certi_no_value= $('#certi_no').val();
	    	 var certi_code_value = $('#certi_type option:selected').val();//证件类型代码
	    	 if(certi_code_value=="11"){
	    		 //修改时只有在正确长度的身份证号码下才会进行获取生日和年龄并置灰
	 				 // setDateAndAge(certi_no_value); //获取生日及年龄
	 	 			  $('#birthday').attr({"readonly":true,"onclick":null});
	 	 			  $('#age').attr("readonly",true);
	 			   
	    	 }
	    	 
	    	   var  obj = window.dialogArguments  ;
	    	   
			   $("#branch_id").val(obj.branch_id);//中介机构id
			   $("#policy_certi_no").val(obj.policy_certi_no);//
			   $("#policy_certi_type").val(obj.policy_certi_type);//
			   $("#relation_code").val(obj.relation_code);//投保人-与主被保人关系
	 	       $("#customer_id").val(obj.customer_id);//客户id	
	 	       $("#age").val(obj.age_value);//
	 	       $("#bene_rate").val(obj.bene_rate);//
	 	       strs_order =obj.order.split(",");
	           var  insurant_name_arr=$("#insurant_name_arr").val();//后台返回
	           var  insurant_name_value=$("#insurant_name_value").val();//后台返回
	           var  insurant_name_selected=$("#insurant_name_selected").val();//后台返回
			   
			   var strs = new Array();
			   var strs_value = new Array();
			   var value_="";
			   var certi_no="";//证件号码
			   var certi_no_value="";
			   var certi_type="";
			   var certi_type_value="";
			   var certi_no_add=obj.certi_no;//投保页面传的证件号码
			   	$("#insurant_name").empty();
			   if(insurant_name_arr!=""){
			   		strs = insurant_name_value.split(",");
			   		strs_arr = insurant_name_arr.split(",");
			   		certi_no_add = certi_no_add.split(",");
			   		certi_type = obj.certi_type.split(",");//证件类型
			   		
			   		$.each(strs, function (index, value) { 
			   			$.each(strs_arr, function (i, e) { 
					   		if(index==i){
				   				if(e!=""){
				   					value_=e;
				   				}
				   				return false;
			   				}
			   			});
			   			$.each(certi_no_add, function (index_value, e) { 
					   		if(index==index_value){
				   				if(e!=""){
				   					certi_no_value=e;
				   				}
				   				return false;
			   				}
			   			});
			   			$.each(certi_type, function (index_value, e) { 
					   		if(index==index_value){
				   				if(e!=""){
				   					certi_type_value=e;
				   				}
				   				return false;
			   				}
			   			});
		   				if(value!=""){
		   					if(insurant_name_selected==value_){
		   						$("#insurant_name").append("<option selected value='"+value_+"' type='"+certi_type_value+"' no='"+certi_no_value+"' order='"+$('#bene_order').val()+"'>"+value+"</option>");
		   					}else{
		   						$("#insurant_name").append("<option value='"+value_+"' type='"+certi_type_value+"' no='"+certi_no_value+"'>"+value+"</option>");
		   					}
		   				}
		   				
		   			});
			   }else{
				  // alert("--2-");
	      			strs = obj.name.split(",");
	      			strs_value = obj.value.split(",");
	      			//strs_order = obj.order.split(",");  //strs_order含有被保人及其对应的 已有受益顺序,->结构: ,被保人id:受益顺序,被保人id:受益顺序
	      			certi_no = obj.certi_no.split(",");//证件号码
	      			certi_type = obj.certi_type.split(",");//证件类型
				   	$.each(strs, function (index, value) { 
					   	$.each(strs_value, function (index_value, e) { 
					   		if(index==index_value){
				   				if(e!=""){
				   					value_=e;
				   				}
				   				return false;
			   				}
			   			});
					   	$.each(certi_no, function (index_value, e) { 
					   		if(index==index_value){
				   				if(e!=""){
				   					certi_no_value=e;
				   				}
				   				return false;
			   				}
			   			});
					   	$.each(certi_type, function (index_value, e) { 
					   		if(index==index_value){
				   				if(e!=""){
				   					certi_type_value=e;
				   				}
				   				return false;
			   				}
			   			});
			   				if(value!=""){
			   					$("#insurant_name").append("<option value='"+value_+"' no='"+certi_no_value+"' type='"+certi_type_value+"'>"+value+"</option>");
			   				}
		   			});
	      			
	      			
			   }
			   
			   
			   //收益顺序
     			//alert(strs_order+"----=-=-");
			    //alert(insurant_name_arr);
     			var insurant_name = $('#insurant_name').val();  //被保人id
     			if(insurant_name_arr==""){
     				var order= $("#insurant_name option:selected").attr("order");
     				 //alert(order);
     				if(order!=undefined){
		    			 $("#bene_order").val(order);
		    			 $("#insurant_name option:selected").attr("order",order);
			    	}else{
		    				for(var i = 0 ;i<strs_order.length;i++){
		   			   		var insurant_bene_order = strs_order[i].split(":");  //分割出被保人id及其受益顺序
		   			   		if(insurant_bene_order[0]==""){
		   			   			$("#bene_order").val(1);
		   			   		    $("#insurant_name option:selected").attr("order",1);
		   			   		}else{
		   			   			if(insurant_name==insurant_bene_order[0]){
		   	 			   			//alert(insurant_bene_order[0]+"--insurant_name:"+insurant_name+"--insurant_bene_order[1]:"+insurant_bene_order[1]);
		   	 			   		    $("#bene_order").val(parseInt(insurant_bene_order[1])+1);
		   	 			   	        $("#insurant_name option:selected").attr("order",parseInt(insurant_bene_order[1])+1);
		   	 		   				/* if(bene_order ==insurant_bene_order[1]){   //如果节目被保人id等于传过来的被保人id 且 受益顺序等于传过来的受益顺序 则表示重复
		   	 		   					alert(insurant_bene_order[1]);
		   	 		   				} */
		   	 	   				}
		   			   		}
	    			 }
    	    			/* var insurant_bene_order = strs_order[i].split(":");  //分割出被保人id及其受益顺序
    			   		if(insurant_bene_order[0]==""){
    						 $("#bene_order").val(1);	
    						 $("#insurant_name option:selected").attr("order",1);
    		   				/* if(bene_order ==insurant_bene_order[1]){   //如果节目被保人id等于传过来的被保人id 且 受益顺序等于传过来的受益顺序 则表示重复
    		   					alert(insurant_bene_order[1]);
    		   					return false;  //如果有相同的受益人顺序则return false;
    		   				} 
    	   				}else{
    	   					$("#bene_order").val(parseInt(insurant_bene_order[1])+1);
    	   					//$("#insurant_name option:selected").attr("order",parseInt(insurant_bene_order[1])+1);
    	   				} */
    	    		}
     			}
			   	
			  //投保人 onchang 序号
			     $('#insurant_name').change(function(){ 
			    	 var insurant_name = $('#insurant_name').val();  //被保人id
			    	 var order= $("#insurant_name option:selected").attr("order");
			    	 /* if(order==undefined){
			    		 $("#bene_order").val(1);	
						 $("#insurant_name option:selected").attr("order",1);
			    	 }else{ */
			    		
			    		 if(order!=undefined){
			    			 $("#bene_order").val(order);
			    		 }else{
			    			 for(var i = 0 ;i<strs_order.length;i++){
				 	    			var insurant_bene_order = strs_order[i].split(":");  //分割出被保人id及其受益顺序
				 	    			   // alert(insurant_name+"-+-"+insurant_bene_order[0]+"--"+insurant_bene_order[1]);
				 	    			//alert(insurant_bene_order[1]=="");
				 			   		if(insurant_name==insurant_bene_order[0]){
				 			   			//alert(insurant_bene_order[0]+"--insurant_name:"+insurant_name+"--insurant_bene_order[1]:"+insurant_bene_order[1]);
				 			   		    $("#bene_order").val(parseInt(insurant_bene_order[1])+1);
				 			   		    $("#insurant_name option:selected").attr("order",parseInt(insurant_bene_order[1])+1);
				 		   				/* if(bene_order ==insurant_bene_order[1]){   //如果节目被保人id等于传过来的被保人id 且 受益顺序等于传过来的受益顺序 则表示重复
				 		   					alert(insurant_bene_order[1]);
				 		   				} */
				 	   				}  
				 			   	    order= $("#insurant_name option:selected").attr("order");
				 			   		if(order==undefined){
				 	   					$("#bene_order").val(parseInt(1));
				 	   				    $("#insurant_name option:selected").attr("order",1);
				 	   				}   
				 			   		
				 	    		}
			    		 }
			    		 
			    		 
			    	// }
				  }) 
			
			   $("#queryForm").validate({  //为queryForm表单配置validate
	        		onkeyup:false,
	        		rules : {
	        			name:{   //姓名长度
							 maxlength:16
						 },
						 name_tow:{ //二录姓名需要与投保人相同
							equalTo:'#name'
						 },
						 certi_no:{
							 maxlength:50
						 },
				 		 certi_no_tow:{ //二录证件号码需一样
				 			equalTo:'#certi_no'
				 		 },
				 		 address:{ //地址长度
				 			 maxlength:50
				 		 },
				 		 job_com:{ //单位长度
				 			maxlength:50
				 		 },
				 		 job_code:{ //职业长度
				 			maxlength:25
				 		 },
				 		 homeplace:{ //籍贯长度
				 			maxlength:50
				 		},
				 		job_code:{ //长度
				 			maxlength:50
				 		}
				 	},
				 	messages:{
				 		name:"请输入正确的受益人姓名",
				 		name_tow:"请输入相同的受益人姓名",
				 		bene_order:"请输入正确的数字",
				 		certi_no_tow:"请输入相同的证件号码"
				 	}
				 });
				//校验样式效果,文本框获取焦点,异常相应报错信息
			/* 	$("#queryForm").find("input").each(function(){
	        		$(this).click(function(){
	        			var _this=$(this);
	        			if(_this.hasClass("error")){
	        				_this.removeClass("error");
	        				var labelAR = _this.parents().find("label[class='error']");
	        				labelAR.remove();
	        			}
	        		});
	        	}); */
	        	
	        	//绑定onblur事件
				$("#name").change(function(){
					customerAjax();
				});
				$("#certi_no").change(function(){
					customerAjax();
				});
				$("#certi_type").change(function(){
					customerAjax();
				});
				$("#gender").change(function(){
					customerAjax();
				});
				//if(policy_tr_id==""){
					$('#relation1').bind("change", relation1Ajax);
				//}
				
	        });
        
			function custom_focus(element){
				var birthday=$("#birthday").val();
				var birthday_tow=$("#birthday_tow").val();
				if(birthday_tow==""){
					$("#birthday_tow").val(birthday);
				}else{
					if(birthday_tow!=birthday){
						$("#birthday_tow").val(birthday);
						customerAjax();
					}
					
				}
				if(birthday!=""){
					 setAge(birthday);
				 }
				WdatePicker({el:element})
			}
        
	     $(function() {
	    	//受益人为同一人时，受益人中受益性质、受益顺序、受益比例可以修改。
   			var flag=$('#flag').val();
   			var relation1=$('#relation1').val();
	   		if(flag=="3"){
	   			if(relation1=="1"){
	   			 $("#relation1").attr("disabled","disabled");
	   			 $("#relation2").attr("disabled","disabled");
	   			 $("#insurant_name").attr("disabled","disabled");
	   			 $("#certi_no").attr("readOnly","readOnly");
				  $("#certi_no_tow").attr("readOnly","readOnly");
				  $("#name").attr("readOnly","readOnly");
				  $("#name_tow").attr("readOnly","readOnly");
				  $("#homeplace").attr("readOnly","readOnly");
				  $("#height").attr("readOnly","readOnly");
				  $("#weight").attr("readOnly","readOnly");
				  $("#mobile").attr("readOnly","readOnly");
				  $("#telphone").attr("readOnly","readOnly");
				  $("#fax").attr("readOnly","readOnly");
				  $("#email").attr("readOnly","readOnly");
				  $("#address").attr("readOnly","readOnly");
				  $("#zip").attr("readOnly","readOnly");
				  $("#job_com").attr("readOnly","readOnly");
				  $("#job_code").attr("readOnly","readOnly");
				  $("#job_tel").attr("readOnly","readOnly");
				  $("#birthday").attr("readOnly","readOnly");
				  $("#nationality").attr("disabled","disabled");
				  $("#nation").attr("disabled","disabled");
				  $("#gender").attr("disabled","disabled");
				  $("#homeplace").attr("readOnly","readOnly");
				  $("#certi_validDate").attr("readOnly","readOnly");
				  	$("#income_type").attr("disabled","disabled");
	        		
	        		$("#health").attr("disabled","disabled");
	        		$("#marital_stat").attr("disabled","disabled");
	        		$("#political").attr("disabled","disabled");
	        		$("#education2").attr("disabled","disabled");
	        		$("#is_telMsgService").attr("disabled","disabled");
	        		$("#certi_type").attr("disabled","disabled");
	        		//$("#job_type").attr("disabled","disabled");
	        		$("#age").attr("readOnly","readOnly");
	   			}
	   		}
	     });
	 	jQuery.validator.addMethod("checkCertiValidDate",function(value,element){
			 var birthday = $('#birthday').val();
			 if(Date.parse(birthday)-Date.parse(value)>0){
				 return false
			 }
			 return true;
		 }, "证件有效期限大于出生日期");
		
		  
	      function customerAjax(){
			 var certi_no = $("#certi_no").val();
			 var certi_type = $("#certi_type").val();
			 var name = $("#name").val();
			 var gender = $("#gender").val();
			 var birthday = $("#birthday").val();
			 if(birthday!=""){
				 setAge(birthday);
			 }
			 if(certi_no!=""&&certi_type!=""&&name!=""&&gender!=""&&birthday!=""){
				 $.ajax({
						 url:"<%=basePath %>/cbs/commonAsyn/getCustomerInfo.do",
						 type:"post",
						 async: false,
						 dataType : "json", 
						 data:{"certi_no":certi_no,"certi_type":certi_type,"name":name,"gender":gender,"birthday":birthday},
						 success:function(data){
							 $.each(data,function(index,comment){
							   var success = comment.success;
							   if("true"==success){
									 var gender = comment.gender;
									 var birthday = comment.birthday;
									 var nationality = comment.nationality;
									 var nation = comment.nation;
									 var homeplace = comment.homeplace;
									 var marital_stat = comment.marital_stat;
									 var political = comment.political;
									 var education2 = comment.education2;
									 var health = comment.health;
									 var height = comment.height;
									 var weight = comment.weight;
									 var mobile = comment.mobile;
									 var telphone = comment.telphone;
									 var fax = comment.fax;
									 var email = comment.email;
									 var address = comment.address;
									 var zip = comment.zip;
									 var job_com = comment.job_com;
									// var job_type = comment.job_type;
									 var job_code = comment.job_code;
									 var job_tel = comment.job_tel;
									 var is_telMsgService = comment.is_telMsgService;
									 var certi_validDate = comment.certi_validDate;
									 var income_type = comment.income_type;
									 var name = comment.name;
									 
									 $("#gender").val(gender);
									 $("#birthday").val(birthday);
									 $("#nationality").val(nationality);
									 $("#nation").val(nation);
									 $("#homeplace").val(homeplace);
									 $("#marital_stat").val(marital_stat);
									 $("#political").val(political);
									 $("#education2").val(education2);
									 $("#health").val(health);
									 $("#height").val(height);
									 $("#weight").val(weight);
									 $("#mobile").val(mobile);
									 $("#telphone").val(telphone);
									 $("#fax").val(fax);
									 $("#email").val(email);
									 $("#address").val(address);
									 $("#zip").val(zip);
									 $("#job_com").val(job_com);
									// $("#job_type").val(job_type);
									 $("#job_code").val(job_code);
									 $("#job_tel").val(job_tel);
									 $("#is_telMsgService").val(is_telMsgService);
									 $("#certi_validDate").val(certi_validDate);
									 $("#income_type").val(income_type);
									 $("#name").val(name);
									 $("#name_tow").val(name);
								 }else{
									 $("#nationality").val("");
									 $("#nation").val("");
									 $("#homeplace").val("");
									 $("#marital_stat").val("");
									 $("#political").val("");
									 $("#education2").val("");
									 $("#health").val("");
									 $("#height").val("");
									 $("#weight").val("");
									 $("#mobile").val("");
									 $("#telphone").val("");
									 $("#fax").val("");
									 $("#email").val("");
									 $("#address").val("");
									 $("#zip").val("");
									 $("#job_com").val("");
									// $("#job_type").val("");
									 $("#job_code").val("");
									 $("#job_tel").val("");
									 $("#is_telMsgService").val("");
									 $("#certi_validDate").val("");
									 $("#income_type").val("");
									 $("#flag").val("false2");
								 }
								 
							 });
						   }
					   });
			 }
			 /* if(certi_type == '11'){   //如果证件是身份证则执行提取生日及年龄
				 	$('#certi_no').attr("class","input-medium required idCardNo customerInfo");
				 }else{
					 $('#certi_no').attr("class","input-medium required");
			  } */
			 if(certi_type == '11'){   //如果证件是身份证则执行提取生日及年龄
				  if($('#certi_no').hasClass("error")){
					  $('#certi_no').attr("class","input-medium required idCardNo customerInfo error");
				  }else{
					  $('#certi_no').attr("class","input-medium required idCardNo customerInfo");
				  }
				 }else{
					 if($('#certi_no').hasClass("error")){
						 $('#certi_no').attr("class","input-medium required error");
					  }else{
						  $('#certi_no').attr("class","input-medium required");
					  }
					 
			  }
		}
        
	      function relation1Ajax(){
        	var relation1 = $('#relation1 option:selected').val();//
        	if(relation1=="1"){
        		var branch_id = $('#branch_id').val();//机构id
        		var customer_id = $('#customer_id').val();//客户id
				$.ajax({
						type : "POST",
						async: false,
						url : "<%=basePath %>/cbs/commonAsyn/getAjaxCustomerInfo.do?branch_id="+branch_id+"&customer_id="+customer_id,
						dataType : "json", 
						error: function () {//请求失败处理函数  
				            alert('请求失败');  
				        },  
						success : function(data) {
							$.each(data,function(indx,comment){
		                          var customer_id=comment.customer_id;
	                              var certi_no=comment.certi_no;
	                              var birthday=comment.birthday;
	                              var nationality=comment.nationality;
	                              var nation=comment.nation;
								  var height=comment.height;
								  var weight=comment.weight;
								  var telphone=comment.telphone;
								  var mobile=comment.mobile;
								  var fax=comment.fax;
								  var email=comment.email;
								  var address=comment.address;
								  var zip=comment.zip;
								  var job_com=comment.job_com;
								  var job_code=comment.job_code;
								  var job_tel=comment.job_tel;
								  var name=comment.name;
								  var gender = comment.gender;
								  var marital_stat = comment.marital_stat;
								  var political = comment.political;
								  var education2 = comment.education2;
								  var is_telmsgservice = comment.is_telmsgservice;
								  var income_type= comment.income_type;
								  var certi_type = comment.certi_type;
								  var homeplace = comment.homeplace;
								  var health = comment.health;
								 // var job_type = comment.job_type;
								  var certi_validdate = comment.certi_validdate;
								  var relation_code=$("#relation_code").val();
								  /*  if(relation_code=="0"||relation_code=="2"){
									  $("#relation2").val("0");//
								  }else{
									  $("#relation2").val("5");//
								  }
								   $("#relation2").attr("disabled","disabled"); */
								  $("#relation_code").val(relation_code);//被保人证件号码
								  $("#certi_no").val(certi_no);//被保人证件号码
								  $("#certi_no").attr("readOnly","readOnly");
								  $("#certi_no_tow").val(certi_no);//被保人证件号码(二录)
								  $("#certi_no_tow").attr("readOnly","readOnly");
								  $("#name").val(name);//被保人姓名
								  $("#name").attr("readOnly","readOnly");
								  $("#name_tow").val(name);//
								  $("#name_tow").attr("readOnly","readOnly");
								  $("#homeplace").val(homeplace);//籍贯
								  $("#homeplace").attr("readOnly","readOnly");
								  $("#height").val(height);//身高
								  $("#height").attr("readOnly","readOnly");
								  $("#weight").val(weight);//体重kg
								  $("#weight").attr("readOnly","readOnly");
								  $("#mobile").val(mobile);//移动电话
								  $("#mobile").attr("readOnly","readOnly");
								  $("#telphone").val(telphone);// 住宅电话
								  $("#telphone").attr("readOnly","readOnly");
								  $("#fax").val(fax);// 传真电话
								  $("#fax").attr("readOnly","readOnly");
								  $("#email").val(email);// 
								  $("#email").attr("readOnly","readOnly");
								  $("#address").val(address);// 
								  $("#address").attr("readOnly","readOnly");
								  $("#zip").val(zip);// 邮政编码
								  $("#zip").attr("readOnly","readOnly");
								  $("#job_com").val(job_com);//工作单位名称
								  $("#job_com").attr("readOnly","readOnly");
								  $("#job_code").val(job_code);//职业
								  $("#job_code").attr("readOnly","readOnly");
								  $("#job_tel").val(job_tel);//办公电话
								  $("#job_tel").attr("readOnly","readOnly");
								  $("#birthday").val(birthday);//出生日期
								  $("#birthday").attr("readOnly","readOnly");
								  $("#nationality").val(nationality);//国籍
								  $("#nationality").attr("disabled","disabled");
								  $("#nation").val(nation);//民族
								  $("#nation").attr("disabled","disabled");
								  $("#gender").val(gender);//性别
								  $("#gender").attr("disabled","disabled");
								  $("#marital_stat").val(marital_stat);//婚姻
								  $("#political").val(political);//政治
								  $("#education2").val(education2);//教育
								  $("#is_telmsgservice").val(is_telmsgservice);//是否接受短信服务
								  $("#income_type").val(income_type);//收入范围
								  $("#political").val(political);//政治面貌
								  $("#education2").val(education2);//教育
								  $("#is_telMsgService").val(is_telmsgservice);//是否接受短信服务
								  $("#certi_type").val(certi_type);//证件类型
								  $("#homeplace").val(homeplace);//家庭住址
								  $("#homeplace").attr("readOnly","readOnly");
								  $("#health").val(health);//健康
								 // $("#job_type").val(job_type);//工作类型
								  $("#certi_validDate").val(certi_validdate);//证件有效日期至
								  $("#certi_validDate").attr("readOnly","readOnly");
								  	$("#income_type").attr("disabled","disabled");
					        		
					        		$("#health").attr("disabled","disabled");
					        		$("#marital_stat").attr("disabled","disabled");
					        		$("#political").attr("disabled","disabled");
					        		$("#education2").attr("disabled","disabled");
					        		$("#is_telMsgService").attr("disabled","disabled");
					        		$("#certi_type").attr("disabled","disabled");
					        		//$("#job_type").attr("disabled","disabled");
					        		var    obj    =    window.dialogArguments  ;
					        		$("#age").val(obj.policyPeople_age);//
					        		$("#age").attr("readOnly","readOnly");
					        		//setDateAndAge(certi_no);//
	                        });
						}
					}); 
				
        		
        	
        	}else{
        	//清空所有数据4
				  $("#certi_no").val("");//被保人证件号码
				  $("#certi_no").removeAttr("readOnly");
				  $("#certi_no_tow").val("");//被保人证件号码(二录)
				  $("#certi_no_tow").removeAttr("readOnly");
				  $("#name").val("");//被保人姓名
				  $("#name").removeAttr("readOnly");
				  $("#name_tow").val("");//
				  $("#name_tow").removeAttr("readOnly");
				  $("#homeplace").val("");//籍贯
				  $("#homeplace").removeAttr("readOnly");
				  $("#height").val("");//身高
				  $("#height").removeAttr("readOnly");
				  $("#weight").val("");//体重kg
				  $("#weight").removeAttr("readOnly");
				  $("#mobile").val("");//移动电话
				  $("#mobile").removeAttr("readOnly");
				  $("#telphone").val("");// 住宅电话
				  $("#telphone").removeAttr("readOnly");
				  $("#fax").val("");// 传真电话
				  $("#fax").removeAttr("readOnly");
				  $("#email").val("");// 
				  $("#email").removeAttr("readOnly");
				  $("#address").val("");// 
				  $("#address").removeAttr("readOnly");
				  $("#zip").val("");// 邮政编码
				  $("#zip").removeAttr("readOnly");
				  $("#job_com").val("");//工作单位名称
				  $("#job_com").removeAttr("readOnly");
				  $("#job_code").val("");//职业
				  $("#job_code").removeAttr("readOnly");
				  $("#job_tel").val("");//办公电话
				  $("#job_tel").removeAttr("readOnly");
				  $("#birthday").val("");//出生日期
				  $("#birthday").removeAttr("readOnly");
				  $("#birthday").removeAttr("disabled");
				  $("#nationality").val("");//国籍
				  $("#nationality").removeAttr("disabled");
				  $("#nation").val("");//民族
				  $("#nation").removeAttr("disabled");
				  $("#gender").val("");//性别
				  $("#gender").removeAttr("disabled");
				  $("#marital_stat").val("");//婚姻
				  $("#marital_stat").removeAttr("disabled");
				  $("#political").val("");//政治
				  $("#political").removeAttr("disabled");
				  $("#education2").val("");//教育
				  $("#education2").removeAttr("disabled");
				  $("#is_telMsgService").val("");//是否接受短信服务
				  $("#is_telMsgService").removeAttr("disabled");
				  $("#income_type").val("");//收入范围
				  $("#income_type").removeAttr("disabled");
				  $("#political").val("");//政治面貌
				  $("#political").removeAttr("disabled");
				  $("#certi_type").val("");//证件类型
				  $("#certi_type").removeAttr("disabled");
				  $("#homeplace").val("");//家庭住址
				  $("#homeplace").removeAttr("disabled");
				  $("#health").val("");//健康
				  $("#health").removeAttr("disabled");
				 // $("#job_type").val("");//工作类型
				 // $("#job_type").removeAttr("disabled");
				  $("#certi_validDate").val("");//证件有效期限至
				  $("#certi_validDate").removeAttr("readOnly");
				  $("#age").val("");//年龄
				  $("#age").removeAttr("readOnly");
				  $("#relation2").val("");//年龄
				  $("#relation2").removeAttr("disabled");
        	}
        }
          //保存和修改方法
 		 function policySave(){
        	
 			var  obj = window.dialogArguments;
			var relation1_code = $('#relation1 option:selected').val();//与投保人关系代码
			var relation2_code = $('#relation2 option:selected').val();//与主被保险人关系代码
			var certi_code_value = $('#certi_type option:selected').val();//证件类型代码
			var certi_no = $("#certi_no").val();//证件号码
			var policy_certi_no = $("#policy_certi_no").val();//投保人-证件号码
			var policy_certi_type = $("#policy_certi_type").val();//投保人-证件类型
			var insurant_certi_type= $("#insurant_name").find("option:selected").attr("type"); 
			var insurant_certi_no=$("#insurant_name").find("option:selected").attr("no"); 
			var insurant_value=$("#insurant_name").find("option:selected").attr("value"); //被保人客户id
			var bene_type_name = $('#bene_type option:selected').text();//受益人
			var bene_type_code = $('#bene_type option:selected').val();//受益人代码
			var flag=true;
			//受益性质 为身故收益人
			if(bene_type_code=="2"){
				if(certi_code_value==insurant_certi_type&&certi_no==insurant_certi_no){
						alert("受益性质为身故受益人时，被保人与受益人不能同一人。");
						return false;
				}
			}
			
			
			//当修改的受益人证件类型和证件号码与选中的被保人证件类型和证件号码相同时，此时受益人与被保人关系必须为“本人”
			if(certi_code_value==insurant_certi_type&&certi_no==insurant_certi_no){
				if(relation2_code!="5"){
					alert("受益人与被保人关系必须为本人");
					return false;
				}
				if(relation1_code==1){
					if(relation2_code!="5"){
						alert("受益人与被保人关系必须为本人");
						return false;
					}
				}
			}
			//被保人姓名为投保人时，与投保人关系 -无关、同一人、不同人时
			
			var strs_arr= obj.policyPeople_customer_id;
			if(strs_arr==insurant_value){
					//与投保人关系为无关  与被保险人关系为无关或者不确定
					if(relation1_code=="0"){
						if(relation2_code!="0"){
							flag=false;
							alert("被保人关系须为无关或者不确定");
							return false;
						}
					}
					if(relation1_code=="2"){
						if(relation2_code=="0"||relation2_code=="5"){
							flag=false;
							alert("被保人关系不能为本人和无关或者不确定");
							return false;
						}
					}
			}
			if(flag==false){
				return false;
			}
			//与被保险人关系是本人 被保人和收益人必须为相同
			if(relation2_code=="5"){
				if(certi_code_value!=insurant_certi_type||certi_no!=insurant_certi_no){
					alert("受益人与被保人必须相同");
					return false;
				}
				
			}
			
				var flag=true;
				 
				 var beneficiary_arr=obj.beneficiary_arr;
				//var str = '[{"customer_id":"24","certi_no":"1","certi_type":"3"}]';
				var str=beneficiary_arr.toString();
				 if(beneficiary_arr!=""){
				  var beneficiary = jQuery.parseJSON(str);
				  //判断被保人是否重复添加
				 	 $(beneficiary).each(function(){
				 		 var customer_id_arr = this.customer_id;
				 		 var certi_no_arr = this.certi_no;
				 		 var certi_type_arr = this.certi_type;
				 		 if(customer_id_arr==insurant_value&&certi_code_value==certi_type_arr&&certi_no==certi_no_arr){
				 			flag=false;
				 			 alert("该受益人已经添加了，请重新输入。");
				 			 return false;
				 		 }
				    });
				 }
				 
				if(flag==false){
					return false;
				}
				
 			if($("#queryForm").valid()){  //再通过表单校验的情况下才执行if内的内容
 				var branch_id = $("#branch_id").val();//
 	 			var name = $("#name").val();//投保人名称
 				var insurant_name = $('#insurant_name option:selected').text();//被保人姓名
 				var insurant_name_value = $('#insurant_name option:selected').val();//被保人姓名代码
 				var relation1_name = $('#relation1 option:selected').text();//与投保人关系
 				var relation2_name = $('#relation2 option:selected').text();//与主被保险人关系名称
 				var certi_code = $('#certi_type option:selected').text();//证件类型
 				var certi_code_value = $('#certi_type option:selected').val();//证件类型代码
 				var gender = $('#gender option:selected').text();//性别
 				var gender_code = $('#gender option:selected').val();//性别代码
 				var birthday = $('#birthday').val();//出生日期
 				var nationality = $('#nationality option:selected').val();//国籍
 				var nation = $('#nation option:selected').val();//民族
 				var homeplace = $("#homeplace").val();//籍贯
 				var marital_stat = $('#marital_stat option:selected').val();//婚姻状况
 				var political = $('#political option:selected').val();//政治面貌
 				var education2 = $('#education2 option:selected').val();//教育程度
 				var health = $('#health option:selected').val();//健康状态
 				var height = $("#height").val();//身高
 				var weight = $("#weight").val();//体重
 				var mobile = $("#mobile").val();//移动电话
 				var telphone = $("#telphone").val();//住宅电话
 				var fax = $("#fax").val();//传真
 				var email = $("#email").val();//电子邮件
 				var address = $("#address").val();//家庭地址
 				var zip = $("#zip").val();//邮编
 				var job_com = $("#job_com").val();//工作单位
 				//var job_type = $("#job_type").val();//职业类型
 				var job_code = $("#job_code").val();//职业
 				var job_tel = $("#job_tel").val();//工作电话
 				var policy_tr_id = $("#policy_tr_id").val();//trid
 				var customer_id = $("#customer_id").val();//客户id
 				var bene_order = $("#bene_order").val();//受益顺序
 				var bene_rate = $("#bene_rate").val();//受益百分比
 				var income_type =$("#income_type").val();//收入
 				var certi_validDate =$("#certi_validDate").val();//证件有效期
 				var is_telMsgService =$("#is_telMsgService").val();//是否接受短信服务
 				var age =$("#age").val();//年龄
 				
 				var obj_ ="name="+name+""+   
 				 "&certi_type=" +certi_code_value+""+
 				 "&certi_no=" +certi_no+""+
 				 "&gender=" +gender_code+""+
 				 "&birthday=" +birthday+""+
 				 "&nationality=" +nationality+""+
 				 "&nation=" +nation+""+
 				 "&homeplace=" +homeplace+""+
 				 "&marital_stat=" +marital_stat+""+
 				 "&political=" +political+""+
 				 "&education2=" +education2+""+
 				 "&health=" +health+""+
 				 "&height=" +height+""+
 				 "&weight=" +weight+""+
 				 "&mobile=" +mobile+""+
 				 "&telphone=" +telphone+""+
 				 "&fax=" +fax+""+
 				 "&email=" +email+""+
 				 "&address=" +address+""+
 				 "&zip=" +zip+""+
 				 "&job_com=" +job_com+""+
 				 "&job_code=" +job_code+""+
 				 "&branch_id=" +branch_id+""+
 				 "&policy_tr_id="+policy_tr_id+
 				 "&customer_id="+customer_id+
 				 "&bene_order="+bene_order+
 				 "&bene_rate="+bene_rate+
 				 "&job_tel=" +job_tel+""+
 				 "&income_type="+income_type+""+
 				 "&certi_validDate="+certi_validDate+""+
 				 "&is_telMsgService="+is_telMsgService;
 				 var flag = $("#flag").val();
 				 var url ;
 					 url = "<%=basePath %>/cbs/policyLife/policyLifeSaveInfo.do?"+encodeURI(encodeURI(obj_));
 				 $.ajax({
 						type : "POST",
 						async: false,
 						url : url,
 						dataType : "json", 
 						error: function () {
 				            alert('请求失败');  
 				        },  
 						success : function(data) {
 							var obj =[{"name":name,
 								"relation1_code":relation1_code,
 								"relation1_name":relation1_name,
 								"relation2_code":relation2_code,
 								"relation2_name":relation2_name,
 								"bene_type_name":bene_type_name,
 								"bene_type_code":bene_type_code,
 								"certi_code":certi_code,
 								"certi_type":certi_code_value,
 								"certi_no":certi_no,
 								"gender":gender,
 								"gender_code":gender_code,
 								"birthday":birthday,
 								"nationality":nationality,
 								"nation":nation,
 								"homeplace":homeplace,
 								"marital_stat":marital_stat,
 								"political":political,
 								"education2":education2,
 								"health":health,
 								"height":height,
 								"weight":weight,
 								"mobile":mobile,
 								"telphone":telphone,
 								"fax":fax,
 								"email":email,
 								"address":address,
 								"zip":zip,
 								"job_com":job_com,
 								"job_code":job_code,
 								"job_tel":job_tel,
 								"customer_id":data,
 								"bene_order":bene_order,
 								"bene_rate":bene_rate,
 								"insurant_name_value":insurant_name_value,
 								"age":age,
 								"policy_tr_id":policy_tr_id,
 								"insurant_customer_id":insurant_value
 							}];
 	                        window.returnValue=obj //将值返回主页面
 						    window.close();
						}
					});  
 			}
 		}
          
 		jQuery.validator.addMethod("customerInfo",function(value,element){
			var certi_no = $("#certi_no").val();
			 var certi_type = $("#certi_type").val();
			 if(certi_no!="" && certi_type!="" ){  //证件号码及证件类型为空时不进行ajax带值
				 if(certi_type == '11'){   //如果证件是身份证则执行提取生日及年龄
	        			if($('#certi_no').hasClass("error")){
	        				$('#certi_no').attr("class","input-medium required idCardNo customerInfo error");
	        			}else{
	        				$('#certi_no').attr("class","input-medium required idCardNo customerInfo");
	        			}
		 				
		 			   if(certi_no.length >= 15 && !isNaN(certi_no.substring(0,15))){ //只有在输入正确长度的身份证号码下才会进行获取生日和年龄并置灰
		 				  setDateAndAge(certi_no); //获取生日及年龄
		 	 			  $('#birthday').attr({"readonly":true,"onclick":null});
		 	 			  $('#age').attr("readonly",true);
		 			   }
					   
					 }else{ //如果证件不是身份证则取消日期生日及年龄的置灰并修改证件号码的class验证方式
						$('#birthday').attr({"readonly":false,"onclick":"WdatePicker()"});
			 			$('#age').attr("readonly",false);
			 			if($('#certi_no').hasClass("error")){
			 				$('#certi_no').attr("class","input-medium required error");
			 			}else{
			 				$('#certi_no').attr("class","input-medium required");
			 			}
			 			
					}
			 
				 
			 }
		    return true
		   },"");
 		
 	/*   //校验受益人的受益顺序,暂时判断为对应被保人的受益顺序不能重复,便于修改
	 	  jQuery.validator.addMethod("checkBeneOrder",function(value,element){
	 	    	var bene_order =  value;   //受益人顺序
	 	    	var insurant_name = $('#insurant_name').val();  //被保人id
	 	    	if(strs_order.length > 0){   //必须是此数组大于0时表示已有受益人顺序
	 	    		for(var i = 0 ;i<strs_order.length;i++){
	 	    			var insurant_bene_order = strs_order[i].split(":");  //分割出被保人id及其受益顺序
	 			   		if(insurant_name==insurant_bene_order[0]){
	 		   				if(bene_order ==insurant_bene_order[1]){   //如果节目被保人id等于传过来的被保人id 且 受益顺序等于传过来的受益顺序 则表示重复
	 		   					return false;  //如果有相同的受益人顺序则return false;
	 		   				}
	 	   				}
	 	    		}
	 	    		
	 	    	}  
	 	    	
	 	   		return true;  //除了相应被保人下的收益顺序已经存在的情况下return false 其他情况都return true;
		
	 	    },"受益顺序重复"); */
 	  
	 	 function setAge(value){
		 		//获取输入身份证号码 
		       	var UUserCard = value; 
		       	//获取出生日期 
		       
		       	var myDate = new Date();
		       	//获取年龄 
	       		var month = myDate.getMonth() + 1; 
		       	var day = myDate.getDate();
		       	var age = myDate.getFullYear() - UUserCard.substring(0, 4) - 1; 
		       	//alert(UUserCard.substring(0, 4));
		       	if (UUserCard.substring(5, 7) < month || UUserCard.substring(5, 7) == month && UUserCard.substring(5, 7) <= day) { 
		       		age++; 
		       	} 
		       	
		      	//校验 
		        var now = new Date();
			   	 var date = value+' 23:59:59';
			   	 if(Date.parse(value)-Date.parse(now)<=0){
			   		if(age==0){
			       		age=1;
			       	}
			   		$('#age').val(age);
			   	 }else{
			   		$('#age').val();
			   	 }
		 }	 
	 	 
 	    function setDateAndAge(value){
	 		//获取输入身份证号码 
	       	var UUserCard = value; 
	 		var length = UUserCard.length;
	       	//获取出生日期 
	       
	       	var myDate = new Date();
	       	//获取年龄 
	       	if(length>15){
	       		//18位截取生日
	       		$('#birthday').val(UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14)); 
	       		var month = myDate.getMonth() + 1; 
		       	var day = myDate.getDate();
		       	var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1; 
		       	if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) { 
		       	age++; 
		       	} 
		       	$('#age').val(age);
		    	
	       	}else{
	       		//15位截取生日
	       		var birthday = '19'+UUserCard.substring(6,8)+'-'+UUserCard.substring(8,10)+'-'+UUserCard.substring(10,12)
	       		$('#birthday').val(birthday);
	       		var brith=new Date(Date.parse(birthday));
	       		
	       		var thisYear=myDate.getFullYear();
	       		var thisMonth=myDate.getMonth()+1;
	       		var thisDay=myDate.getDate();
	       		brithy=brith.getFullYear();
	       		brithm=brith.getMonth();
	       		brithd=brith.getDate();
	       		if(thisMonth-brithm<0)
	            {
	       			$('#age').val(thisYear-brithy-1);
	            }
	            else
	            {
	                   if(thisDay-brithd>=0)
	                   {
	                		$('#age').val(thisYear-brithy);
	                   }
	                   else
	                   {
	                		$('#age').val(thisYear-brithy-1);
	                   }
	            }
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
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/cbs/policyLife/policyLifeSaveInfo.do" method="POST">
						<webTag:HiddenInputTag id="customer_id" name="customer_id" value='${rmHelper.returnParams.customer_id}' displayLable="客户id" />
						<webTag:HiddenInputTag id="policy_tr_id" name="policy_tr_id" value='${rmHelper.returnParams.policy_tr_id}' displayLable="投保人id" />
					    <webTag:HiddenInputTag id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码" />
				        <webTag:HiddenInputTag id="flag" name="flag" value='${rmHelper.returnParams.flag}' displayLable="操作标志" />
				        <webTag:HiddenInputTag id="policy_certi_no" name="policy_certi_no" value='${rmHelper.returnParams.policy_certi_no}' displayLable="投保人-证件号码" />
				        <webTag:HiddenInputTag id="policy_certi_type" name="policy_certi_type" value='${rmHelper.returnParams.policy_certi_type}' displayLable="投保人-证件号码类型" />
						<webTag:HiddenInputTag id="birthday_tow" name="birthday_tow" value='${rmHelper.returnParams.birthday}' displayLable="日期" />
					
					<div class="row">
					    <webTag:DynamicSelectTag src="beneficiaryTypeSelect" name="bene_type" id="bene_type" value='${rmHelper.returnParams.bene_type}' displayLable="受益性质" isdisplay="true" iclass="required"/>
						<webTag:Text id="bene_order" name="bene_order" value='${rmHelper.returnParams.bene_order}' displayLable="受益顺序" isdisplay="true" readonly="true" iclass="required checkBeneOrder isNum"/>
						<webTag:Text id="bene_rate" name="bene_rate" value='${rmHelper.returnParams.bene_rate}' displayLable="受益比例" isdisplay="true" iclass="required checkPercent"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="applicantRelation1Select" name="relation1" id="relation1" value='${rmHelper.returnParams.relation1}' displayLable="与投保人关系" isdisplay="true" iclass="required"/>
					    <webTag:DynamicSelectTag src="applicantRelation2Select" name="relation2" id="relation2" value='${rmHelper.returnParams.relation2}' displayLable="与被保险人关系" isdisplay="true" iclass="required"/>
					</div>
					<div class="row">
						<webTag:Select id="insurant_name" name="insurant_name" value='${rmHelper.returnParams.insurant_name}' displayLable="被保人姓名" isdisplay="true" iclass="required">
                        </webTag:Select> 
						<webTag:HiddenInputTag id="insurant_name_selected" name="insurant_name_selected" value='${rmHelper.returnParams.insurant_name}' displayLable="被保人姓名锁定" />
						<webTag:HiddenInputTag id="insurant_name_value" name="insurant_name_value" value='${rmHelper.returnParams.insurant_name_value}' displayLable="被保人姓名集合" />
						<webTag:HiddenInputTag id="insurant_name_arr" name="insurant_name_arr" value='${rmHelper.returnParams.insurant_name_arr}' displayLable="被保人姓名id数组" />
						<webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="受益人姓名" isdisplay="true" iclass="required"/>
						<webTag:Text id="name_tow" name="name_tow" value='${rmHelper.returnParams.name}' displayLable="受益人姓名（二录）" isdisplay="true"  iclass="required"/>
					</div>
					
					<div class="row">
					    <webTag:DynamicSelectTag src="certiTypeSelect" name="certi_type" id="certi_type" value='${rmHelper.returnParams.certi_type}' displayLable="受益人证件类型" isdisplay="true"  iclass="required customerInfo"></webTag:DynamicSelectTag>
						<webTag:Text id="certi_no" name="certi_no" value='${rmHelper.returnParams.certi_no}' displayLable="受益人证件号码"  isdisplay="true"  iclass="required customerInfo"/>
						<webTag:Text id="certi_no_tow" name="certi_no_tow" value='${rmHelper.returnParams.certi_no}' displayLable="受益人证件号码（二录）" isdisplay="true"  iclass="required"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="genderSelect"  id="gender" name="gender" value='${rmHelper.returnParams.gender}'  displayLable="性别" isdisplay="true" iclass="required"/>
						<webTag:DateChangeTag  id="birthday" name="birthday" value='${rmHelper.returnParams.birthday}'  displayLable="出生日期" isdisplay="true" iclass="required dateISO checkBirthday"/>
					    <webTag:DynamicSelectTag src="nationalitySelect"  id="nationality" name="nationality" value='${rmHelper.returnParams.nationality}'  displayLable="国籍:"/>
					</div>
					<div class="row">
						<webTag:Date  id="certi_validDate" name="certi_validDate" value='${rmHelper.returnParams.certi_validdate}'  displayLable="证件有效期限至"  isdisplay="true" iclass="required dateISO checkCertiValidDate"/>
						<webTag:Text id="age" name="age" value='${rmHelper.returnParams.age}' displayLable="年龄" isdisplay="true" iclass="required" readonly="true"/>
						<webTag:DynamicSelectTag src="customerIncomTypeSelect"  id="income_type" name="income_type" value='${rmHelper.returnParams.income_type}'  displayLable="平均年收入（过去三年）:"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="nationSelect" name="nation" id="nation" value='${rmHelper.returnParams.nation}' displayLable="民族" isdisplay="true" iclass="required"></webTag:DynamicSelectTag>
						<webTag:Text id="homeplace" name="homeplace" value='${rmHelper.returnParams.homeplace}' displayLable="籍贯:"/>
						<webTag:DynamicSelectTag src="maritalSelect" name="marital_stat" id="marital_stat" value='${rmHelper.returnParams.marital_stat}' displayLable="婚姻状况" isdisplay="true" iclass="required"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="politicalSelect"  id="political" name="political" value='${rmHelper.returnParams.political}'  displayLable="政治面貌" isdisplay="true" iclass="required"/>
                        <webTag:DynamicSelectTag src="educationSelect" name="education2" id="education2" value='${rmHelper.returnParams.education2}' displayLable="教育程度" isdisplay="true" iclass="required"></webTag:DynamicSelectTag>
                        <webTag:DynamicSelectTag src="healthSelect" name="health" id="health" value='${rmHelper.returnParams.health}' displayLable="健康状况:"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:Text id="height" name="height" value='${rmHelper.returnParams.height}' displayLable="身高cm:"  iclass="checkHeight"/>
						<webTag:Text id="weight" name="weight" value='${rmHelper.returnParams.weight}' displayLable="体重kg:"  iclass="checkWeight"/>
						<webTag:DynamicSelectTag  src="hignPolicySelect" id="is_telMsgService" name="is_telMsgService" value='${rmHelper.returnParams.is_telmsgservice}'  displayLable="是否接受手机短信服务:" />
					</div>
					<div class="row">
						<webTag:Text id="mobile" name="mobile" value='${rmHelper.returnParams.mobile}' displayLable="移动电话:" iclass="mobilePhone"/>
						<webTag:Text id="telphone" name="telphone" value='${rmHelper.returnParams.telphone}' displayLable="住宅电话:"  iclass="telephone"/>
						<webTag:Text id="fax" name="fax" value='${rmHelper.returnParams.fax}' displayLable="传真电话:"  iclass="telephone"/>
					</div>
					<div class="row">
						<webTag:Text id="email" name="email" value='${rmHelper.returnParams.email}' displayLable="电子邮箱:" iclass="email"/>
						<webTag:Text id="address" name="address" value='${rmHelper.returnParams.address}' displayLable="家庭地址:"/>
						<webTag:Text id="zip" name="zip" value='${rmHelper.returnParams.zip}' displayLable="邮政编码:" iclass="checkPost"/>
					</div>
					<div class="row">
						<webTag:Text id="job_com" name="job_com" value='${rmHelper.returnParams.job_com}' displayLable="工作单位名称:"/>
						<webTag:Text id="job_code" name="job_code" value='${rmHelper.returnParams.job_code}' displayLable="职业代码:"/>
						<webTag:Text id="job_tel" name="job_tel" value='${rmHelper.returnParams.job_tel}' displayLable="办公电话:"  iclass="telephone"/>
					</div>
					
				</form>
									
				    <div class="row" style="text-align:right;">
					    		<button type="button"  onclick="policySave()" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
					    		<button type="button" onclick="window.close()" class="btn btn-danger"><i class="icon-share-alt icon-white"></i>返回</button>
					</div><!-- /.row -->
			</div>
			
			
		</div>
	</body>
	
</html>