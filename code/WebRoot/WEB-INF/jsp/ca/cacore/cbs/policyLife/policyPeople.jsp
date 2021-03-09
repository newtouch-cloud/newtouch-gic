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
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jquery-ui-1.10.3.custom.js"></script>
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		
		<!-- fram plugins start--> 
		<script type="text/javascript" src="<%=basePath %>/compent/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/ajax/newtouch.js"></script>
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
		$(function() {
			//修改时带出生日及年龄
			 var certi_no_value= $('#certi_no').val();
			 var certi_code_value = $('#certi_type option:selected').val();//证件类型代码
	    	 if(certi_code_value=="11"){
	    		 //修改时只有在正确长度的身份证号码下才会进行获取生日和年龄并置灰
	 				 // setDateAndAge(certi_no_value); //获取生日及年龄
	 	 			  $('#birthday').attr({"readonly":true,"onclick":null});
	 	 			  
	 	 			  $('#age').attr("readonly",true);
	 			   
	    	 }
			
			 var    obj    =    window.dialogArguments  ;
			 $("#branch_id").val(obj.branch_id);//中介机构id
			 $("#customer_id").val(obj.customer_id);//客户id
			 $("#age").val(obj.age_value);//
			 $("#age").val(obj.age_value);//
	        
			 $("#queryForm").validate({
	 				onkeyup:false,
					 rules:{
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
				 		job_code:{ //籍贯长度
				 			maxlength:50
				 		}
				 	},
				 	messages:{
				 		name:"请输入正确的投保人姓名",
				 		name_tow:"请输入相同的投保人姓名",
				 		certi_no_tow:"请输入相同的证件号码"
				 	}
				 });
				//校验样式效果,文本框获取焦点,异常相应报错信息
				/*  $("#queryForm").find("input").each(function(){
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
				//$('#certi_no').attr({"onblur":"customerAjax()"});
				//$('#certi_type').attr({"onblur":"customerAjax()"});
				//$('#name').attr({"change":"customerAjax()"});
				//$('#gender').attr({"onblur":"customerAjax()"});
				//$('#birthday').attr({"onblur":"customerAjax()"});
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
				//$('#birthday').attr({"onblur":"birthdayAdd()"});
			// $("#birthday").bind(function(){
					 
				//});
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
	 	
		       
		function customerAjax(){
			//$("#birthday").attr("onclick","WdatePicker();");
			 var certi_no = $("#certi_no").val();
			 var certi_type = $("#certi_type").val();
			 var name = $("#name").val();
			 var gender = $("#gender").val();
			 var birthday = $("#birthday").val();
			 
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
									 //var job_type = comment.job_type;
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
									 //$("#job_type").val(job_type);
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
		
		jQuery.validator.addMethod("checkCertiValidDate",function(value,element){
			 var birthday = $('#birthday').val();
			 if(Date.parse(birthday)-Date.parse(value)>0){
				 return false
			 }
			 return true;
		 }, "证件有效期限大于出生日期");
		
		
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

		 
      function policyPeopleSave(){

    	  if($("#queryForm").valid()){  //再通过表单校验的情况下才执行if内的内容
    		  var name = $("#name").val();//
  			var relation_name = $('#relation option:selected').text();//
  			var relation_code = $('#relation option:selected').val();//
  			var certi_code = $('#certi_type option:selected').text();//证件类型
  			var certi_code_value = $('#certi_type option:selected').val();//证件类型代码
  			var certi_no = $("#certi_no").val();//证件号码
  			var gender = $('#gender option:selected').text();//性别
  			var gender_code = $('#gender option:selected').val();//性别代码
  			var birthday = $('#birthday').val();//出生日期
  			var nationality = $('#nationality option:selected').val();//国籍
  			var nation = $('#nation option:selected').val();//民族
  			var homeplace = $("#homeplace").val();//家庭住址
  			var marital_stat = $('#marital_stat option:selected').val();//婚姻状况
  			var political = $('#political option:selected').val();//政治
  			var education2 = $('#education2 option:selected').val();//教育程度
  			var health = $('#health option:selected').val();//健康状态
  			var height = $("#height").val();//身高
  			var weight = $("#weight").val();//体重
  			var mobile = $("#mobile").val();//移动电话
  			var telphone = $("#telphone").val();//住宅电话
  			var fax = $("#fax").val();//传真
  			var email = $("#email").val();//邮箱
  			var address = $("#address").val();//家庭住址
  			var zip = $("#zip").val();//邮编
  			var job_com = $("#job_com").val();//工作单位
  			var job_type = $("#job_type").val();//职业类型
  			var job_code = $("#job_code").val();//职业
  			var job_tel = $("#job_tel").val();//工作电话
  			var policy_tr_id = $("#policy_tr_id").val();//trid
  			var branch_id = $("#branch_id").val();//机构
  			var customer_id = $("#customer_id").val();//客户号
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
  			 "&job_type=" +job_type+""+
  			 "&job_code=" +job_code+""+
  			 "&branch_id=" +branch_id+""+
  			 "&customer_id="+customer_id+
  			 "&policy_tr_id="+policy_tr_id+
  			 "&job_tel=" +job_tel+""+
  			 "&income_type="+income_type+""+
  			 "&certi_validDate="+certi_validDate+""+
  			 "&is_telMsgService="+is_telMsgService;
  			 var flag = $("#flag").val();
  			 var url = "<%=basePath %>/cbs/policyLife/policyLifeSaveInfo.do?"+encodeURI(encodeURI(obj_));
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
  							"relation_code":relation_code,
  							"relation_name":relation_name,
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
  							"job_type":job_type,
  							"job_code":job_code,
  							"job_tel":job_tel,
  							"job_tel":job_tel,
  							"customer_id":data,
  							"age":age,
  							"policy_tr_id":policy_tr_id
  						}];
                           window.returnValue=obj //将值返回主页面
  						 window.close();
						}
					}); 
    	  }
 		}
		</script>
		<base target="_self"/>
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
						<webTag:HiddenInputTag id="flag" name="flag" value='${rmHelper.returnParams.flag}' displayLable="状态标志位" />
					    <webTag:HiddenInputTag id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码" />
					    <webTag:HiddenInputTag id="birthday_tow" name="birthday_tow" value='${rmHelper.returnParams.birthday}' displayLable="日期" />
					<div class="row">
						<webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="投保人姓名" isdisplay="true" iclass="required"/>
						<webTag:Text id="name_tow" name="name_tow" value='${rmHelper.returnParams.name}' displayLable="投保人姓名（二录）" isdisplay="true" iclass="required"/>
					    <webTag:DynamicSelectTag src="applicantRelation1Select" name="relation" id="relation" value='${rmHelper.returnParams.relation}' displayLable="与主被保险人关系" isdisplay="true" iclass="required"/>
					</div>
					<div class="row">
					    <webTag:DynamicSelectTag src="certiTypeSelect" name="certi_type" id="certi_type" value='${rmHelper.returnParams.certi_type}' displayLable="证件类型" isdisplay="true" iclass="required customerInfo" ></webTag:DynamicSelectTag>
						<webTag:Text id="certi_no" name="certi_no" value='${rmHelper.returnParams.certi_no}' displayLable="投保人证件号码" iclass="required customerInfo" isdisplay="true"/>
						<webTag:Text id="certi_no_tow" name="certi_no_tow" value='${rmHelper.returnParams.certi_no}' displayLable="投保人证件号码（二录）" isdisplay="true" iclass="required"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="genderSelect"  id="gender" name="gender" value='${rmHelper.returnParams.gender}'  displayLable="性别" isdisplay="true" iclass="required"/>
						<%--  <input type="text" id="birthday" name="birthday" class="required dateISO checkBirthday test"  onFocus="custom_focus(this)" value='${rmHelper.returnParams.birthday}'/>--%>						
						<%-- <webTag:Date id="birthday" name="birthday" value='${rmHelper.returnParams.birthday}' displayLable="出生日期" isdisplay="true" iclass="required dateISO checkBirthday test"/> --%>
						<webTag:DateChangeTag  id="birthday" name="birthday" value='${rmHelper.returnParams.birthday}'  displayLable="出生日期" isdisplay="true" iclass="required dateISO checkBirthday"/>
						<webTag:DynamicSelectTag src="nationalitySelect"  id="nationality" name="nationality" value='${rmHelper.returnParams.nationality}'  displayLable="国籍:"/>
					</div>
					<div class="row">					
						<webTag:Date  id="certi_validDate" name="certi_validDate" value='${rmHelper.returnParams.certi_validdate}'  displayLable="证件有效期限至" isdisplay="true" iclass="required dateISO checkCertiValidDate"/>
						<webTag:Text id="age" name="age" value='${rmHelper.returnParams.age}' displayLable="年龄" iclass="checkAge required" isdisplay="true"/>
						<webTag:DynamicSelectTag src="customerIncomTypeSelect"  id="income_type" name="income_type" value='${rmHelper.returnParams.income_type}'  displayLable="平均年收入（过去三年）:"/>
					</div>
					
					<div class="row">
						<webTag:DynamicSelectTag src="nationSelect" name="nation" id="nation" value='${rmHelper.returnParams.nation}' displayLable="民族" isdisplay="true" iclass="required"></webTag:DynamicSelectTag>
						<webTag:Text id="homeplace" name="homeplace" value='${rmHelper.returnParams.homeplace}' displayLable="籍贯:"/>
						<webTag:DynamicSelectTag src="maritalSelect" name="marital_stat" id="marital_stat" value='${rmHelper.returnParams.marital_stat}' displayLable="婚姻状况"  isdisplay="true" iclass="required"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="politicalSelect"  id="political" name="political" value='${rmHelper.returnParams.political}'  displayLable="政治面貌"  isdisplay="true" iclass="required"/>
                        <webTag:DynamicSelectTag src="educationSelect" name="education2" id="education2" value='${rmHelper.returnParams.education2}' displayLable="教育程度"  isdisplay="true" iclass="required"></webTag:DynamicSelectTag>
                        <webTag:DynamicSelectTag src="healthSelect" name="health" id="health" value='${rmHelper.returnParams.health}' displayLable="健康状况:"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:Text id="height" name="height" value='${rmHelper.returnParams.height}' displayLable="身高cm:" iclass="checkHeight"/>
						<webTag:Text id="weight" name="weight" value='${rmHelper.returnParams.weight}' displayLable="体重kg:" iclass="checkWeight" />
						<webTag:DynamicSelectTag  src="hignPolicySelect" id="is_telMsgService" name="is_telMsgService" value='${rmHelper.returnParams.is_telmsgservice}'  displayLable="是否接受手机短信服务:" />
					</div>
					<div class="row">
						<webTag:Text id="mobile" name="mobile" value='${rmHelper.returnParams.mobile}' displayLable="移动电话:" iclass="mobilePhone"/>
						<webTag:Text id="telphone" name="telphone" value='${rmHelper.returnParams.telphone}' displayLable="住宅电话:" iclass="telephone"/>
						<webTag:Text id="fax" name="fax" value='${rmHelper.returnParams.fax}' displayLable="传真电话:" iclass="telephone"/>
					</div>
					<div class="row">
						<webTag:Text id="email" name="email" value='${rmHelper.returnParams.email}' displayLable="电子邮箱:" iclass="email"/>
						<webTag:Text id="address" name="address" value='${rmHelper.returnParams.address}' displayLable="家庭地址:"/>
						<webTag:Text id="zip" name="zip" value='${rmHelper.returnParams.zip}' displayLable="邮政编码:" iclass="checkPost"/>
					</div>
					<div class="row">
						<webTag:Text id="job_com" name="job_com" value='${rmHelper.returnParams.job_com}' displayLable="工作单位名称:"/>
						<webTag:Text id="job_code" name="job_code" value='${rmHelper.returnParams.job_code}' displayLable="职业代码:"/>
						<webTag:Text id="job_tel" name="job_tel" value='${rmHelper.returnParams.job_tel}' displayLable="办公电话:" iclass="telephone"/>
					</div>
				  <div class="row" style="text-align:right;">
					    		<button type="button" id="policyPeopleSaveId" onclick="policyPeopleSave()"  class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
					    		<button type="button" onclick="window.close()"  class="btn btn-danger"><i class="icon-share-alt icon-white"></i>返回</button>
				</div><!-- /.row -->
				</form>
			</div>
			
			
		</div>
	</body>
	
</html>