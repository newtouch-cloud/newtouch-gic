$(function(){
     
 })	  
			//添加投保人信息
			function policyPeople_add(){
				var path = $("#path").val();
				if(checkTopInfo()){  //页面头部必填项资料不能为空
					var type="add";
					var obj = new  Object();  
	   				obj.type=type; 
	   				obj.branch_id=$("#branch_id").val(); 
					var reValue = window.showModalDialog(path+"/redirect/redirect.do?linkUrl=ca/cacore/cbs/policyLife/policyPeople",obj,"dialogWidth:1050px;resizable=no;status=no;dialogHeight:900px;dialogLeft:200px;dialogTop:10px;center:yes;help:yes;resizable");
					if(reValue!=null){
						policyPeople_clone(reValue,type,'policyPeople_');
					}
				}
				
			}
		
			//添加被保险人信息
			function policylife_insured_add(){
				var path = $("#path").val();
				var beneficiary_arr="";//证件类型和号码集合
				var certi_no="";//身份证号
				var certi_type="";//证件类型
				var relation_code="";//投保人-与主被保人关系
				var relation2="";//被保人-与主被保人关系
				var policyPeople_age="";//被保人年龄
				var policyPeople_name="";//投保人姓名
				//投保人不能为空
				var policyPeople_customer_id = $("input[name='policyPeople_customer_id']").val();
				if(policyPeople_customer_id==undefined){
					alert("投保人信息不能为空");
					return false;
				}
			//获取投保人的身份证号、身份证类型、与主被保人关系
					$("#policyPeople_info tr").each(function(index,element){
						var name = $(element).find("td[id='policyPeople_name']").html();//身份证号
						var policy_certi_no = $(element).find("td[id='policyPeople_certi_no']").html();//身份证号
						var policy_certi_type = $(element).find("td[id='certi_type']").html();//证件类型
						var policyPeople_relation_code = $(element).find("input[name='policyPeople_relation_code']").val();//与主被保人关系
						var age=	$(element).find("input[name='policyPeople_age']").val();
						if(name!=undefined){
							policyPeople_name = name;
						}
						if(age!=undefined){
							 policyPeople_age = age;
						}
						 if(policy_certi_no!=undefined){
							certi_no = policy_certi_no;
						}
						 if(policy_certi_type!=undefined){
							certi_type =policy_certi_type;
						}
						 if(policyPeople_relation_code!=undefined){
							relation_code =policyPeople_relation_code;
						}
					});
				//获取被保人的	身份证号、身份证类型 判断是否存在重复
					$("#policyLifeInsured_info tr").each(function(index,element){
						var certi_no = $(element).find("td[id='policyPeople_certi_no']").html();//身份证号
						var certi_type = $(element).find("td[id='certi_type']").html();//证件类型
						var policyLifeInsured_relation2 = $(element).find("input[name='policyLifeInsured_relation2']").val();//与主被保人关系
						var age=	$(element).find("input[name='policyPeople_age']").val();
						 if(certi_no!=undefined&&certi_type!=undefined){
							 if(beneficiary_arr==""){
								 beneficiary_arr = certi_type+":"+certi_no;
							 }else{
								 beneficiary_arr = beneficiary_arr+","+certi_type+":"+certi_no;
							 }
							 
						}
						 if(policyLifeInsured_relation2!=undefined){
							 relation2 = relation2+","+policyLifeInsured_relation2;
						}
						
					});
				if(checkTopInfo()){  //页面头部必填项资料不能为空
					var type="add";
					var obj = new  Object();  
	   				obj.type=type; 
	   				obj.branch_id=$("#branch_id").val();
	   				obj.certi_no=certi_no;
	   				obj.certi_type=certi_type;
	   				obj.relation_code=relation_code;
	   				obj.beneficiary_arr=beneficiary_arr;
	   				obj.relation2=relation2;
	   				obj.policyPeople_age=policyPeople_age;
	   				obj.customer_id=$("input[name='policyPeople_customer_id']").val(); 
	   				obj.policyPeople_name=policyPeople_name; 
	   				obj.policyPeople_gender_code=$("input[name='policyPeople_gender_code']").val(); //获取投保人性别代码
	   				obj.policyPeople_birthday=$("input[name='policyPeople_birthday']").val(); //获取投保人出生日期
					var reValue = window.showModalDialog(path+"/redirect/redirect.do?linkUrl=ca/cacore/cbs/policyLife/policyLifeInsured",obj,"dialogWidth:1050px;resizable=no;status=no;dialogHeight:900px;dialogLeft:200px;dialogTop:10px;center:yes;help:yes;resizable");
					if(reValue!=null){
						policyPeople_clone(reValue,type,'policyLifeInsured_');
					}
				}
				
			}
			
			//添加受益人
			function policyLife_beneficiary_add(name_value){
				var path = $("#path").val();
				if(checkInsurantName()){
					var type="add";
					var obj = new  Object();  
					var policyPeople_name="";
					var policyPeople_name_value="";
					var certi_no="";//被保人身份号码
					var certi_type="";//被保人证件类型
					var policy_certi_no="";//投保人证件号码
					var policy_certi_type="";//投保人证件类型
					var beneficiary_arr="";//所有受益人证件类型和证件号码数组
					var insurant_bene_order="";  //被保人收益顺序
					var relation_code="";//投保人-与主被保人关系
					var policyPeople_age="";//投保人-年龄
					//获取被保人的信息
					var length= $('#policyLifeInsured_info tr').size();
					if(length==0){
						alert("请先添加被保险人");
						return false;
					}
					//获取投保人的身份证号、身份证类型、与主被保人关系
					$("#policyPeople_info tr").each(function(index,element){
						var certi_no_ = $(element).find("td[id='policyPeople_certi_no']").html();//身份证号
						var certi_type_ = $(element).find("td[id='certi_type']").html();//证件类型
						 if(certi_type_!=undefined){
							policy_certi_type =certi_type_;
						}
						 if(certi_no_!=undefined){
							policy_certi_no =certi_no_;
						}
						var policyPeople_relation_code = $(element).find("input[name='policyPeople_relation_code']").val();//与主被保人关系
						if(policyPeople_relation_code!=undefined){
							relation_code =policyPeople_relation_code;
						}
						var age=	$(element).find("input[name='policyPeople_age']").val();
						if(age!=undefined){
							 policyPeople_age = age;
						}
						
					});
					
					//获取被保人的客户名称、客户id、身份证号、身份证类型
					$("#policyLifeInsured_info tr").each(function(index,element){
						var name = $(element).find("td[id='policyPeople_name']").html();
						var policy_no = $(element).find("td[id='policyPeople_certi_no']").html();//身份证号
						var policy_certi_type = $(element).find("td[id='certi_type']").html();//证件类型
						var customer_id = $(element).find("input[name='policyLifeInsured_customer_id']").val();
						 if(name!=undefined){
							policyPeople_name = policyPeople_name+","+name;
						}
						 if(customer_id!=undefined){
							policyPeople_name_value = policyPeople_name_value+","+customer_id;
						}
						 if(policy_no!=undefined){
							certi_no = certi_no+","+policy_no;
						}
						 if(policy_certi_type!=undefined){
							certi_type =certi_type+","+policy_certi_type;
						}
						
					}); 
					
					
					//获取受益人、身份证号、身份证类型
					var beneficiary_str="";
					$("#policyLifeBeneficiary_info tr").each(function(index,element){
						var customer_id = $(element).find("input[name='policyLifeBeneficiary_insurant_name_value']").val();
						var order = $(element).find("td[id='bene_order']").html();
						//var order = $(element).find("input[name='policyLifeBeneficiary__bene_order']").val();  //顺序					
						var insurant = $(element).find("input[name='policyLifeBeneficiary_insurant_name_value']").val(); //被保人id
						//alert(insurant+':'+order);
						if(insurant!=undefined && order!=undefined){
							if(insurant_bene_order==""){
								insurant_bene_order = insurant+":"+order;
							}else{
							    insurant_bene_order = insurant_bene_order+","+insurant+":"+order;
							}
						}
						
						var certi_no = $(element).find("td[id='policyPeople_certi_no']").html();//身份证号
						var certi_type = $(element).find("td[id='certi_type']").html();//证件类型
							if(index==1){
								if(certi_no!=undefined&&certi_type!=undefined&&customer_id!=undefined){
									 //beneficiary_arr = beneficiary_arr+","+certi_type+":"+certi_no;
									 beneficiary_arr = "{\"customer_id\":\""+customer_id+"\",\"certi_no\":\""+certi_no+"\",\"certi_type\":\""+certi_type+"\"}";
								}
							}else if(index>1){
								beneficiary_arr =beneficiary_arr+","+"{\"customer_id\":\""+customer_id+"\",\"certi_no\":\""+certi_no+"\",\"certi_type\":\""+certi_type+"\"}";
							}
						 
					});
					if(beneficiary_arr!=""){
						beneficiary_arr ="["+beneficiary_arr+"]";
					}
					
					obj.type=type; 
	   				obj.branch_id=$("#branch_id").val();
	   				obj.name=policyPeople_name; 
	   				obj.value=policyPeople_name_value;
	   				obj.order=insurant_bene_order;
	   				obj.certi_no=certi_no;
	   				obj.certi_type=certi_type;
	   				obj.policy_certi_type=policy_certi_type;
	   				obj.policyPeople_customer_id=$("input[name='policyPeople_customer_id']").val();;
	   				obj.policy_certi_no=certi_no;
	   				obj.beneficiary_arr=beneficiary_arr;
	   				obj.relation_code=relation_code;
	   				obj.policyPeople_age=policyPeople_age;
	   				obj.customer_id=$("input[name='policyPeople_customer_id']").val();
					var reValue = window.showModalDialog(path+"/redirect/redirect.do?linkUrl=ca/cacore/cbs/policyLife/policyLifeBeneficiary",obj,"dialogWidth:1050px;resizable=no;status=no;dialogHeight:900px;dialogLeft:200px;dialogTop:10px;center:yes;help:yes;resizable");
					if(reValue!=null){
						policyPeople_clone(reValue,type,name_value);
					}
				}else{
					alert("未找到被保人信息,不能进行此操作。");
				}
				
			}
			
			//添加险种
			function productLlife_add(){
				var path = $("#path").val();
				if(checkInsurantName()){
					var beneficiary_arr="";//产品id和被保人id集合
					var type="add";
					var policyPeople_name="";  //被保人名称
					var policyPeople_name_value="";//被保人id
					var send_code = $("#send_code").val();//投保人号
					var branch_id = $("#branch_id").val();//中介机构id
					var hold_date = $("#hold_date").val();//投保日期
					var insbranch_id = $('#insBranch_id option:selected').val();//保险机构id
					var high_policy = $('#high_policy option:selected').val();//是否高额件
					var money_id = $("#money_id").val();//币种
					$("#policyLifeInsured_info tr").each(function(index,element){
						var name = $(element).find("td[id='policyPeople_name']").html();
						var customer_id = $(element).find("input[name='policyLifeInsured_customer_id']").val();
						 if(name!=undefined){
							policyPeople_name = policyPeople_name+","+name;
						}
						 if(customer_id!=undefined){
							policyPeople_name_value = policyPeople_name_value+","+customer_id;
						}
					});
					//获取险种中被保人和险种的集合
					$("#productLlife_info tr").each(function(index,element){
						var product_id = $(element).find("td[id='product_id']").html();//产品id
						var insurant_select = $(element).find("td[id='insurant_select']").html();//被保人id
						 if(product_id!=undefined&&insurant_select!=undefined){
							 beneficiary_arr = beneficiary_arr+","+insurant_select+":"+product_id;
						}
						
					});
					
					
					var obj = new  Object();  
	   				obj.name=policyPeople_name; 
	   				obj.value=policyPeople_name_value; 
	   				obj.send_code= send_code;
	   				obj.branch_id= branch_id;
	   				obj.insbranch_id= insbranch_id;
	   				obj.money_id= money_id;
	   				obj.hold_date= hold_date;
	   				obj.high_policy= high_policy;
	   				obj.beneficiary_arr= beneficiary_arr;
	   				
					var reValue = window.showModalDialog(path+"/redirect/redirect.do?linkUrl=ca/cacore/cbs/policyLife/productLlife",obj,"dialogWidth:1050px;resizable=no;status=no;dialogHeight:470px;dialogLeft:200px;dialogTop:100px;center:yes;help:yes;resizable");
					if(reValue!=null){
						product_clone(reValue,type,'productLlife_');
					}
				}else{
					alert("未找到被保人信息,不能进行此操作。");
				}
				
			}
			
		
	