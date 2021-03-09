$(function() {
    //回滚
    $("#policyLifeSave").gobackTop();
    //校验
	$("#queryForm").validate({
			 onkeyup:false,
			 rules:{
				 send_code:{
					 maxlength:20 
				 },
				 bank_accName:{
					 maxlength:16
				 },
				 bank_account:{
					 maxlength:32
				 },
				 bank_account_tow:{
					 equalTo:'#bank_account'
				 },
				 period_prem_tow:{
					 equalTo:'#period_prem'
				 }
			 },
			 messages:{
				 bank_account_tow:"请输入相同的银行账号",
				 period_prem_tow:"请输入相同的首期保费合计"
			 	}
		 
		 });
		 
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
});

//判断保存完成后 true跳转到二次保存，false跳转到修改页面
	 var result_flag =$("#result_flag").val();
		if(result_flag=="false"){
			$("#policyPeople_button_id").hide(); 
			$("#policyPeople_del").hide(); 
			$("#policylife_button_insured_add").hide(); 
			$("#policylife_insured_del").hide(); 
			$("#policyLife_button_beneficiary_add").hide(); 
			$("#policyLife_beneficiary_del").hide(); 
			$("#productLlife_button_add").hide(); 
			$("#productLlife_del").hide(); 
			$("#policyLifeSave").hide();//保存按钮隐藏
			$("#uploadimage").hide();//上传隐藏
		}else{
			$("#policyLifeUpdate").hide();//修改按钮隐藏
			$("#image_id").hide();//按钮隐藏
		}
		
//校验顶部数据有空值时不能 点击添加投保人及被保人 进行警告提示
		function checkTopInfo(){
			var send_code = $("#send_code").val(); //投保单号
			var insBranch_id = $("#insBranch_id").val();  //保险公司机构
			var agent_id = $("#agent_id").val();   //保单中介人员代码
			var agent_name = $("#agent_name").val();  //保单中介人员名称
			var branch_id = $("#branch_id").val();    //中介机构代码
			var branch_name = $("#branch_name").val();    //中介机构名称
			var high_policy = $("#high_policy").val();   //是否高额件
			var hold_date = $("#hold_date").val();  //投保日期
			
			if(insBranch_id == ""){
				alert("保险公司机构不能为空，不能进行此操作。");
				return false;
			}
			if(send_code == ""){
				alert("投保单号为空,不能进行此操作。");
				return false;
			}
			if(agent_id == ""){
				alert("保单中介人员代码为空,不能进行此操作。");
				return false;
			}
			if(agent_name == ""){
				alert("保单中介人员名称,请核实。");
				return false;
			}
			if(branch_id == ""){
				alert("中介机构代码为空,请核实。");
				return false;
			}
			if(branch_name == ""){
				alert("中介机构名称为空,不能进行此操作。");
				return false;
			}
			if(high_policy == ""){
				alert("是否高额件为空,不能进行此操作。");
				return false;
			}
			if(hold_date == ""){
				alert("投保日期为空,不能进行此操作。");
				return false;
			}
			
			return true;
		}
		
		function checkInsurantName(){
			//获取被保人的客户名称和id
			var boo = false;
			$("#policyLifeInsured_info tr").each(function(index,element){
				var name = $(element).find("td[id='policyPeople_name']").html();
				 if(name!=undefined){
					 boo = true;
				}
			});
			
			return boo;
		}
		
		 //中介人员ajax
		jQuery.validator.addMethod("branchInfo",function(value,element){
			 var agent_id = $("#agent_id").val();
			 var path = $("#path").val();
			 $.ajax({
				 url:path+"/cbs/commonAsyn/getSalesInfo.do",
				 type:"post",
				 async: false,
				 dataType : "json", 
				 data:{"agent_id":agent_id},
				 success:function(data){
					 $.each(data,function(index,comment){
						 var success = comment.success;
						 if("true"==success){
							 var agent_name= comment.sales_name;
							 var branch_id = comment.branch_id;
							 var branch_name = comment.branch_name;
							 $("#branch_id").val(branch_id);
							 $("#branch_name").val(branch_name);
							 $("#agent_name").val(agent_name);
							 $("#flag").val("0");
						 }else if("false1"==success){
							  $("#branch_id").val("");
						      $("#branch_name").val("");
							  $("#agent_name").val("");
							  $("#flag").val("1");
						  }else{
							  $("#branch_id").val("");
						      $("#branch_name").val("");
							  $("#agent_name").val("");
							  $("#flag").val("2");
						  }
						 
					  });
				   }
			   });
			  if($("#flag").val()=="1"){
		     		return false;
		        	}else{
		     		return true;
		        	}
		   },"输入的中介人员状态必须为在职，请重新输入。");
	      
		    //中介人员状态校验
		    jQuery.validator.addMethod("branchInfo2",function(value,element){
		    	       if($("#flag").val()=="2"){
			     		return false;
			        	}else{
			     		return true;
			        	}
			   },"输入的中介人员代码不存在,请重新输入。");
		  //投保单ajax重复校验
		  jQuery.validator.addMethod("validateSendCode",function(value,element){
			     var send_code = $("#send_code").val();
				 var insBranch_id = $("#insBranch_id").val();
				 var path = $("#path").val();
				 if(send_code!=""){
		   			$("#file_name").val(send_code+".jpg");
				  }
				 if(send_code!="" && insBranch_id!= ""){
					 $.ajax({
						 url:path+"/cbs/commonAsyn/validateSendCode.do",
						 type:"post",
						 async: false,
						 data:{"send_code":send_code,"insBranch_id":insBranch_id},
						 success:function(data){
							 //alert(data);
							 if("true"==data){
								 $("#send_code_repeat").val("true");
							 }else{
								  $("#send_code_repeat").val("false");
							  }
						   }
					   });
				 }
				
				  if($("#send_code_repeat").val()=="false"){
			     		return false;
			        	}else{
			     		return true;
			        	}
			   },"投保单号重复。");
		  
		  
		// 保单  根据投保单号和保险公司机构异步请求：提示投保单号的信息
			jQuery.validator.addMethod("getPolicyLifeBySendCode",function(value,element){
				$("#send_code").removeClass("error");  
				$("label[for='send_code'][class='error']").remove();
				$("label[for='insBranch_id'][class='error']").remove();
				
				var path=$("#path").val();
				var send_code = $('#send_code').val();
	   		 	var insBranch = $("#insBranch_id").val();
	   		 	var result = "";//变量决定是跳转还是提示
	   		 	if(send_code!=""&&insBranch!=""){
	   		    //异步请求：如果投保单状态不为首期带承保则返回false否则返回true
	   		 	 $.ajax({
					 url:path+"/cbs/contractLife/ajaxPolicyLife.do",
					 type:"post",
					 async: false,
					 data:{"send_code":send_code,"insBranch_id":insBranch},
					 success:function(data){
						 if("false1"==data){
							 //投保单号不存在
							 $("#result_flag").val("false1");
						 }else if("false2"==data){
							 $("#result_flag").val("false2");//状态不为首期带承保
						 }else{
							 //返回true 查询该投保单的信息给保单
							  document.getElementById("queryForm").action=path+"/cbs/contractLife/getPolicyLifeInfoBySend_code.do?send_code="+send_code+"&insBranch_id="+insBranch+"";
					 	      document.getElementById("queryForm").submit();
						  }
					   }
				   });
	   		 	}
	   		 	var  result_flag = $("#result_flag").val();
	   		 	if(result_flag == "false2"){
	   		 		return false;
	   		 	}else{
	   		 		return true;
	   		 	}
			  },"投保单状态必须为首期待承保，请重新输入。");
			
			
			//保单ajax重复校验
			  jQuery.validator.addMethod("validatePolicyCode",function(value,element){
				     var path=$("#path").val();
				     var policy_code = $("#policy_code").val();
					 var insBranch_id = $("#insBranch_id").val();
					 if(policy_code!="" && insBranch_id!= ""){
						 $.ajax({
							 url:path+"/cbs/commonAsyn/validatePolicyCode.do",
							 type:"post",
							 async: false,
							 data:{"policy_code":policy_code,"insBranch_id":insBranch_id},
							 success:function(data){
								 if("true"==data){
									 $("#policy_code_repeat").val("true");
								 }else{
									  $("#policy_code_repeat").val("false");
								  }
							   }
						   });
					 }
					
					  if($("#policy_code_repeat").val()=="false"){
				     		return false;
				        	}else{
				     		return true;
				        	}
				   },"保单号重复");
			  
			   //投保单不存在的提示
				jQuery.validator.addMethod("validatePLF",function(value,element){
				     	var result_flag=$("#result_flag").val();
				     	if(result_flag=="false1"){
				     		return false;
				     	}
				     	return true;
				     },"投保单在保险公司不存在，请重新输入。");
		        
		        //验证投保日期和保单生效日期
				jQuery.validator.addMethod("checkDateOrder",function(value,element){
				     	var hold_date=$("#hold_date").val();
				     	var validate_date=$("#validate_date").val();
				     	if(hold_date!=""&&validate_date!=""){
				     		 if(hold_date > validate_date){
					     			return false;
					     		}
				     	}
				     	return true;
				     },"保单生效日期不能小于投保日期");