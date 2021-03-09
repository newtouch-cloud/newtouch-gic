	$(function() {
	
	});
	   //增加投保人信息\被保人\受益人
       function policyPeople_clone(reValue,type,name_value){
      
         	var policyPeople_json = "";  
         	var policyPeople_tr_id="";
			var bene_type_name="" ;//收益性质名称
			var bene_type_code="" ;//收益性质名称代码
			var bene_type_code_value="" ;//收益性质名称代码_后台使用
			var policyPeople_name_value="";
			var insurant_customer_id="";//受益人中的 被保人客户id
			var relation1_name="" ;
			var relation1_code="" ;
			var relation2_name="" ;
			var relation2_code="" ;
			var insurant_name="" ;//被保险人锁定值
			var insurant_name_arr="";//被保险人字符串数组
			var bene_rate="";//受益百分比
			var bene_order="";//受益顺序
            var _len = $("#"+name_value+"info tr").length;
            //policyPeople_info
			$.each(reValue,function(n,value) {  
					
				     var tr_id_value="";
		             var trs = ""; 
		            if(type=="update"){
						  policyPeople_tr_id=value.policy_tr_id;
		             	  tr_id_value = "<tr id="+value.policy_tr_id+">";
					}else{
						 tr_id_value= "<tr id="+name_value+""+_len+">";
					} 
					//投保人与主被保人的关系
					if(name_value=='policyPeople_'){
					
						relation2_name="<td >" + value.relation_name +"</td>"+"<td style='display:none' ><input type='hidden' name='"+name_value+"relation1_name' value='" + value.relation_name +"'/></td>";//与主被保人关系名称
					}
					//判断被保险人中的 与投保人关系
					 if(name_value=='policyLifeInsured_'){
		            	 relation1_code = "<td style='display:none' ><input type='hidden' name='"+name_value+"relation1' value='" + value.relation1_code +"'/></td>"+"<td style='display:none' ><input type='hidden' name='"+name_value+"relation1_name' value='" + value.relation1_name +"'/></td>";//与投保人关系代码
		            	 relation2_code = "<td style='display:none' ><input type='hidden' name='"+name_value+"relation2' value='" + value.relation2_code +"'/></td>"+"<td style='display:none' ><input type='hidden' name='"+name_value+"relation2_name' value='" + value.relation2_name +"'/></td>";//与主被保人关系代码
		            	 relation1_name ="<td >" + value.relation1_name +"</td>";//与投保人关系
		            	 relation2_name ="<td >" + value.relation2_name +"</td>";//与主被保人关系
					 }
		            //判断受益人
					 if(name_value=='policyLifeBeneficiary_'){
					    $("#policyLifeInsured_info tr").each(function(index,element){
							var name = $(element).find("td[id='policyPeople_name']").html();
							 if(name!=undefined){
								policyPeople_name_value = policyPeople_name_value+","+name;
							}
						});
					     relation1_name ="<td >" + value.relation1_name +"</td>"+"<td style='display:none' ><input type='hidden' name='"+name_value+"relation1_name' value='" + value.relation1_name +"'/></td>";//与投保人关系
					     relation1_code = "<td style='display:none' ><input type='hidden' name='"+name_value+"relation1' value='" + value.relation1_code +"'/></td>";//与投保人关系代码
					 	 relation2_name ="<td >" + value.relation2_name +"</td>"+"<td style='display:none' ><input type='hidden' name='"+name_value+"relation2_name' value='" + value.relation2_name +"'/></td>";//与投保人关系
		            	 relation2_code = "<td style='display:none' ><input type='hidden' name='"+name_value+"relation2' value='" + value.relation2_code +"'/></td>";//与主被保险人关系
					     bene_type_name ="<td >" + value.bene_type_name +"</td>"+"<td style='display:none' ><input type='hidden' name='"+name_value+"bene_type_name' value='" + value.bene_type_name +"'/></td>";//受益性质
		            	 bene_order = "<td id='bene_order'>" + value.bene_order +"</td>"+"<td style='display:none' ><input type='hidden' name='"+name_value+"bene_order' value='" + value.bene_order +"'/></td>";//收益顺序
		            	 bene_rate = "<td id='bene_rate'>" + value.bene_rate +"</td>"+"<td style='display:none' ><input type='hidden' name='"+name_value+"bene_rate' value='" + value.bene_rate +"'/></td>";//收益顺序
		            	 bene_type_code_value="<td style='display:none' ><input type='hidden' name='"+name_value+"bene_order' value='" + value.bene_order +"'/></td>";//收益循序_input值
		            	 bene_type_code_value="<td style='display:none' ><input type='hidden' name='"+name_value+"bene_type_code' value='" + value.bene_type_code +"'/></td>";//受益性质代码
		            	 bene_type_code = "<td id='bene_type' style='display:none'>" + value.bene_type_code +"</td>";//受益性质代码
		            	 insurant_name = "<td id='insurant_name' style='display:none'>" + value.insurant_name +"</td>";//被保人姓名
		            	 insurant_name_arr = "<td id='insurant_name_arr' style='display:none'>" + policyPeople_name_value +"</td>";//被保人姓名
		            	 insurant_customer_id= "<td style='display:none' ><input type='hidden' name='insurant_customer_id' value='" + value.insurant_customer_id +"'/></td>";//收益循序_input值
		            }
					trs +=tr_id_value+"<td><input type='checkbox' name='"+name_value+"Checkbox'  /></td>"+
		             "<td id='policyPeople_name'>"+value.name+"</td>"+
		             "<td >" + value.gender +"</td>"+//性别
		             "<td >" + value.certi_code +"</td>"+//证件类型
		             "<td id='policyPeople_certi_no'>" + value.certi_no +"</td>"+//证件号码
		             "<td id='policyPeople_address'>" + value.address +"</td>"+//家庭住址
		             "<td id='policyPeople_mobile'>" + value.mobile +"</td>"+
		             "<td id='policyPeople_email'>" + value.email +"</td>"+
		          	  relation1_name+//与投保人关系
		          	  relation2_name+//与主被保人关系
		             //"<td >" + value.relation_name +"</td>"+//与主被保险人关系text
		             bene_order+bene_rate+//收益顺序  收益比例
		             bene_type_name+bene_type_code+
		             insurant_name+insurant_name_arr+bene_type_code_value+insurant_customer_id+
		             relation1_code+//与投保人关系代码
		             relation2_code+//与主被保人关系代码
		             "<td id='certi_type' style='display:none'>" + value.certi_type +"</td>"+//证件类型
		             "<td style='display:none' ><input type='hidden' name='"+name_value+"relation_code' value='" + value.relation_code +"'/></td>"+//与主被保人关系
		             "<td style='display:none' ><input type='hidden' name='"+name_value+"customer_id' value='" + value.customer_id +"'/></td>"+//客户id
		             "<td style='display:none' ><input type='hidden' name='"+name_value+"age' value='" + value.age +"'/></td>"+//年龄
		             "<td style='display:none' ><input type='hidden' name='"+name_value+"gender_code' value='" + value.gender_code +"'/></td>"+//性别代码
		             "<td style='display:none' ><input type='hidden' name='"+name_value+"birthday' value='" + value.birthday +"'/></td>"+//出生日期
		             "<td style='display:none' ><input type='hidden' name='"+name_value+"insurant_name_value' value='" + value.insurant_name_value +"'/></td></tr>";//受益人中被保人客户id
		             policyPeople_json += trs;         
		           });  
		          //alert(policyPeople_json);
		           if(type=="update"){
		        	  $(policyPeople_json).replaceAll("#"+policyPeople_tr_id+"");
		           }else{
		        	 $("#"+name_value+"info").append(policyPeople_json);  
		           }
		           //投保人新增按钮变灰
		           if(name_value=="policyPeople_"){
		           		$("#policyPeople_button_id").attr('disabled',' disabled');
		           		$("#policyPeople_button_id").removeAttr('onclick');
		           }
		        var frameId = "#mainIframe";
				var frameW= 1060;//定义页面宽度
				var option = {
						FrameId : frameId,
						FrameW : frameW,
					};
				$(window).frameWH(option);//控制页面宽度
				$("#image_id").show();//按钮显示
       }
       
           //删除
       function policy_del(name_value){
    	   if($("input[name='"+name_value+"Checkbox']:checkbox:checked").size() >0){
	    	   if(confirm("是否删除?")){
	    		   var period_prem=0;//保费累加值
	  	         	$($("input[name='"+name_value+"Checkbox']:checkbox:checked")).each(function(){
	  					$(this).closest('tr').remove();
	  				})
	  				 if(name_value=="policyPeople_"){
	  		           		$("#policyPeople_button_id").attr('onclick',"policyPeople_add('policyPeople_')");
	  		           		$("#policyPeople_button_id").removeAttr('disabled');
	  		           		$("#image_id").show();//按钮显示
	  		        }
	  			
		  			if(name_value=="productLlife_"){
		  				 //保费累加
		  		        $("#productLlife_info tr").each(function(index,element){
		  						var prem = $(element).find("td[id='period_prem_id']").html();
		  						 if(prem!=null){
		  							period_prem+=parseFloat(prem);
		  						}
		  				});
		  					if(period_prem!=0){
		  						$("#period_prem").val(parseFloat(period_prem).toFixed(4));
		  					}else{
		  						$("#period_prem").val("");
		  					}
		  			}
	    	   }
  			}else{
  				alert("请选择要操作的数据");
  			}
  			
  			var frameId = "#mainIframe";
  			var frameW= 1060;//定义页面宽度
  			var option = {
  					FrameId : frameId,
  					FrameW : frameW,
  				};
  			$(window).frameWH(option);//控制页面宽度
			
       }
       
       	//险种行列增加
			 function product_clone(reValue,type,name_value){
	      		var period_prem=0;//保费累加值
	         	var policyPeople_json = "";  
	         	var policyPeople_tr_id="";//所选中的tr的id值
	         	var policyPeople_name_value="";//获取被保人数组
			    $("#policyLifeInsured_info tr").each(function(index,element){
					var name = $(element).find("td[id='policyPeople_name']").html();
					 if(name!=undefined){
						policyPeople_name_value = policyPeople_name_value+","+name;
					}
				});
	            var _len = $("#"+name_value+"info tr").length;
	            
	            //policyPeople_info
				$.each(reValue,function(n,value) {  
						
					    var tr_id_value="";
			            var trs = ""; 
			            if(type=="update"){
							  policyPeople_tr_id=value.policy_tr_id;
			             	  tr_id_value = "<tr id="+value.policy_tr_id+"><td>";
						}else{
							 tr_id_value= "<tr id="+name_value+""+_len+"><td>";
						} 
					
						trs +=tr_id_value+"<input type='checkbox' name='"+name_value+"Checkbox'  /></td>"+
			             "<td id='product_name'>"+value.product_name+"</td>"+
			             "<td id='coverage_time'>" + value.coverage_time +"/1/1</td>"+//保额、档次、份数 
			             "<td id='name'>" + value.insurant_name+"</td>"+//被保人姓名
			             "<td style='display:none' ><input type='hidden' name='"+name_value+"insurant_name' value='" + value.insurant_name +"'/></td>"+//
			             "<td id='charge_type_code'>" + value.charge_type_code +"</td>"+//缴费方式
			             "<td style='display:none' ><input type='hidden' name='"+name_value+"charge_type_name' value='" + value.charge_type_code +"'/></td>"+//产品代码
			             "<td id='charge_period_code'>" + value.charge_period_code +"</td>"+//缴费期限
			             "<td style='display:none' ><input type='hidden' name='"+name_value+"charge_period_name' value='" + value.charge_period_code +"'/></td>"+//产品代码
			             "<td id='period_prem_id'>" + value.period_prem +"</td>"+//保费
			             "<td id='product_seq_id' style='display:none'>" + value.seq_id +"</td>"+//产品seq_id
			             "<td id='product_id' style='display:none'>" + value.product_id +"</td>"+//产品id
			             "<td id='coverage_period_value' style='display:none'>" + value.coverage_period_value +"</td>"+//保障期限类型代码
			             "<td id='coverage_year' style='display:none'>" + value.coverage_year +"</td>"+//保障时间
			             "<td id='charge_period_value' style='display:none'>" + value.charge_period_value +"</td>"+//缴费期限类型代码
			             "<td id='charge_year' style='display:none'>" + value.charge_year +"</td>"+//保障年数
			             "<td id='charge_type_value' style='display:none'>" + value.charge_type_value +"</td>"+//缴费方式代码
			             "<td id='amount' style='display:none'>" + value.amount +"</td>"+//基本保险金额
			             "<td id='auth_payage' style='display:none'>" + value.auth_payage +"</td>"+//年金领取年龄
			             "<td id='auth_draw' style='display:none'>" + value.auth_draw +"</td>"+//年金领取方式
			             "<td id='auth_firstpay' style='display:none'>" + value.auth_firstpay +"</td>"+//首期领取金额
			             "<td id='dividend_choice' style='display:none'>" + value.dividend_choice +"</td>"+//红利领取方式
			             "<td id='ins_type' style='display:none'>" + value.ins_type +"</td>"+//主附险标志
			             "<td id='product_type' style='display:none'>" + value.product_type +"</td>"+//产品分类1
			             "<td id='product_type2' style='display:none'>" + value.product_type2 +"</td>"+//产品分类2
			             "<td id='product_type3' style='display:none'>" + value.product_type2 +"</td>"+//产品分类3
			             "<td id='product_type3' style='display:none'>" + value.product_type3 +"</td>"+//险种状态
			             "<td id='is_autoren' style='display:none'>" + value.is_autoren +"</td>"+//是否可续保
			             "<td id='renew' style='display:none'>" + value.renew +"</td>"+//险种是否可续保
			             "<td id='insurant_select' style='display:none'>" + value.insurant_select +"</td>"+//被保人姓名
			            // "<td id='insurant_name_arr' style='display:none'>" + policyPeople_name_value +"</td>"+//被保人锁定姓名
			        	 "<td style='display:none' ><input type='hidden' name='"+name_value+"product_id' value='" + value.product_id +"'/></td>"+//产品代码
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"customer_id' value='" + value.insurant_select +"'/></td>"+//人员代码
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"coverage_period' value='" + value.coverage_period_value +"'/></td>"+//保障期限类型代码
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"charge_year' value='" + value.charge_year +"'/></td>"+//保障年数
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"coverage_year' value='" + value.coverage_year +"'/></td>"+//缴费年龄或年限
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"charge_period' value='" + value.charge_period_value +"'/></td>"+//缴费期限类型代码
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"charge_type' value='" + value.charge_type_value +"'/></td>"+//缴费方式
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"amount' value='" + value.amount +"'/></td>"+//基本保险金额
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"period_prem' value='" + value.period_prem +"'/></td>"+//保费
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"auth_payage' value='" + value.auth_payage +"'/></td>"+//年金领取年龄
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"auth_draw' value='" + value.auth_draw +"'/></td>"+//年金领取方式
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"auth_firstpay' value='" + value.auth_firstpay +"'/></td>"+//首期领取金额
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"dividend_choice' value='" + value.dividend_choice +"'/></td>"+//红利领取方式
			              "<td style='display:none' ><input type='hidden' name='"+name_value+"is_autoren' value='" + value.is_autoren +"'/></td>"+//是否自动续保
			              "<td style='display:none' ><input type='hidden' name='product_seq_id' value='" + value.seq_id +"'/></td>"+//是否自动续保
			             "</tr>";//
			             policyPeople_json += trs;         
			           });  
			           //alert(policyPeople_json);
			           if(type=="update"){
			        	  $(policyPeople_json).replaceAll("#"+policyPeople_tr_id+"");
			           }else{
			        	 $("#"+name_value+"info").append(policyPeople_json);  
			           }
			           //保费累加
				        $("#productLlife_info tr").each(function(index,element){
								var prem = $(element).find("td[id='period_prem_id']").html();
								 if(prem!=null){
									 period_prem+=parseFloat(prem);
								}
							});
						 $("#period_prem").val(parseFloat(period_prem).toFixed(4));
						var frameId = "#mainIframe";
						var frameW= 1060;//定义页面宽度
						var option = {
								FrameId : frameId,
								FrameW : frameW,
							};
						$(window).frameWH(option);//控制页面宽度	
	       }
			//产品修改
				function  productLlife_update(type_){
					var path=$("#path").val();
				   var policyPeople_name_value="";
			       var policyPeople_name_value_str="";
			       var _json="";
			       var type="";
			       var insured_customer_id="";
					//获取被保人信息
					$("#policyLifeInsured_info tr").each(function(index,element){
						 var name = $(element).find("td[id='policyPeople_name']").html();
						 var customer_id = $(element).find("input[name='policyLifeInsured_customer_id']").val();
						 if(name!=undefined){
							policyPeople_name_value = policyPeople_name_value+","+name;
						  }
						   if(customer_id!=undefined){
							insured_customer_id = insured_customer_id+","+customer_id;
						 }
					});
					 var parents_id=$("input[name='productLlife_Checkbox']:checkbox:checked").parents("tr").attr("id");
					 if($("input[name='productLlife_Checkbox']:checkbox:checked").size() ==1){
					 	 $("#"+parents_id+"").each(function(index,value){
				         		 var _value;
								 var arr = new Array(); 
								 var td_value = $(this).children("td");
								 for(var i=0;i<td_value.length;i++){  
										
										if(i==0){
											_json="type_="+type_+"&insured_customer_id="+insured_customer_id+"&policyPeople_name_value="+policyPeople_name_value+"&policy_tr_id="+parents_id+"&"+"type="+type+"&"+td_value.eq(i+1).attr("id")+"="+td_value.eq(i+1).text();
										} else if(td_value.eq(i+1).attr("id")!=undefined) {
										
											_json=_json+"&"+td_value.eq(i+1).attr("id")+"="+td_value.eq(i+1).text();
										}
								 }  
							
							 });
						//alert(_json);
						var obj = new  Object(); 
						obj.beneficiary_arr="";
						obj.insbranch_id=$('#insBranch_id option:selected').val();//保险公司id
						var  url=path+"/cbs/policyLife/policyProductUpdate.do?"+encodeURI(encodeURI(_json));
						var reValue = window.showModalDialog(url,obj,"dialogWidth:1050px;resizable=no;status=no;dialogHeight:900px;dialogLeft:200px;dialogTop:10px;center:yes;help:yes;resizable");
						if(reValue!=null){
							var type="update";
							product_clone(reValue,type,'productLlife_');
						}
						
					 }else{
					 	alert("请选择要操作的数据");
					 }
					 
					 
				} 
			//修改、详细
		  function policyPeople_update(name_value,jsp_url,type_){
		       var path=$("#path").val();
		       var flag=true;
		       var period_prem=0;
		       var customer_id="";//客户id
		       var branch_id = $("#branch_id").val();//机构id
		       var policyPeople_name_value="";
		       var policyPeople_name_value_str="";
		       var relation1 ="";//与主被保人关系
		       var relation2 ="";//与投保人关系
		       var bene_order ="";//收益顺序
		       var bene_rate ="";//收益比例
		       var bene_type ="";//收益性质
		       var insured_name ="";//被保人名称
		       var insured_name_value ="";//被保人名称集合
		       var insured_customer_id="";//选中的被保人
		       var beneficiary_arr="";//类型集合
		       var relation2_arr="";//与主被保人关系集合
		       var policyPeople_relation1="";//投保人-与主被保人关系
		       var certi_no="";//被保人证件号码
		       var certi_type="";//被保人证件类型
		       var age_value="";//年龄 
		       var insurant_bene_order="";//受益人 序号+客户id数组 
		       //获取被保人信息
			     $("#policyLifeInsured_info tr").each(function(index,element){
					 var name = $(element).find("td[id='policyPeople_name']").html();
					 if(name!=undefined){
						policyPeople_name_value = policyPeople_name_value+","+name;
					  }
					policyPeople_name_value_str = "<td id='insurant_name_arr' style='display:none'>" + policyPeople_name_value +"</td>";//被保人姓名			
					$(policyPeople_name_value_str).replaceAll("#insurant_name_arr");
				});
		       var policyPeople_json="";
		       //获取选中的checkbox的tr的id
		       var parents_id=$("input[name='"+name_value+"Checkbox']:checkbox:checked").parents("tr").attr("id");
		     	 if($("input[name='"+name_value+"Checkbox']:checkbox:checked").size() ==1){
		         		 $("#"+parents_id+"").each(function(index,value){
			         		 var _value;
							 var arr = new Array(); 
							 var td_value = $(this).children("td");
							 var  branch_id= $("#branch_id").val();
							 td_value.each(function(t,element){
							    if(name_value=="policyPeople_"){
							    	//客户id
									 var v_=	$(element).find("input[name='policyPeople_customer_id']").val();
									 //与主被保人关系
									  var relation_code=	$(element).find("input[name='policyPeople_relation_code']").val();
									 //年龄
									  var age=	$(element).find("input[name='policyPeople_age']").val();
									 if(v_!=undefined){
										 customer_id=v_;
									 }
									 if(relation_code!=undefined){
										 relation1= relation_code;
									 }
									 if(age!=undefined){
										 age_value= age;
									 }
									 
							    }else if(name_value=="policyLifeInsured_"){
									 var v_=	$(element).find("input[name='policyLifeInsured_customer_id']").val();
									 //被保人-与投保人关系
									  var relation1_code=$(element).find("input[name='policyLifeInsured_relation1']").val();
									 //被保人-与主被保人关系
									 var relation2_code=$(element).find("input[name='policyLifeInsured_relation2']").val();
									 //年龄
									 var age=	$(element).find("input[name='policyLifeInsured_age']").val();
									 
									 if(v_!=undefined){
										 customer_id=v_;
									 }
									  if(relation1_code!=undefined){
										 relation1= relation1_code;
									 }
									  if(relation2_code!=undefined){
										 relation2= relation2_code
									 }
									 if(age!=undefined){
										 age_value= age;
									 }
									 //当被保险人是同一人时，被保险人资料的修改按钮应不可用
									 if(type_!="view"){
										 if(relation1_code=="1"){
										 	flag=false;
										 	alert("被保险人与投保人为同一人不能修改");
										 	return false;
										 }
									 }
							    }else if(name_value=="policyLifeBeneficiary_"){
									 var v_=	$(element).find("input[name='policyLifeBeneficiary_customer_id']").val();
									 //受益人-与投保人关系
									  var relation1_code=$(element).find("input[name='policyLifeBeneficiary_relation1']").val();
									 //收益人-与主被保人关系
									  var relation2_code=$(element).find("input[name='policyLifeBeneficiary_relation2']").val();
									 //收益顺序
									 var policyLifeBeneficiary_bene_order=	$(element).find("input[name='policyLifeBeneficiary_bene_order']").val();
									 //收益比例
									 var policyLifeBeneficiary_bene_rate=	$(element).find("input[name='policyLifeBeneficiary_bene_rate']").val();
									 //收益性质
									 var bene_type_code=	$(element).find("input[name='policyLifeBeneficiary_bene_type_code']").val();
									 //选中的被保险人
									 var policyLifeBeneficiary_insurant_name_value=	$(element).find("input[name='policyLifeBeneficiary_insurant_name_value']").val();
									 //年龄
									 var age=	$(element).find("input[name='policyLifeBeneficiary_age']").val();
									 
									 if(v_!=undefined){
										 customer_id=v_;
									 }
									 if(policyLifeBeneficiary_bene_order!=undefined){
										 bene_order=policyLifeBeneficiary_bene_order;
									 }
									 if(policyLifeBeneficiary_bene_rate!=undefined){
										 bene_rate=policyLifeBeneficiary_bene_rate;
									 }
									 if(relation1_code!=undefined){
										 relation1= relation1_code;
									 }
									  if(relation2_code!=undefined){
										 relation2= relation2_code;
									 }
									  if(bene_type_code!=undefined){
										 bene_type= bene_type_code;
									 }
									   if(policyLifeBeneficiary_insurant_name_value!=undefined){
										 insured_customer_id= policyLifeBeneficiary_insurant_name_value;
									 }
									 if(age!=undefined){
										 age_value= age;
									 }
			
							    }
							 });
						 });
						if(name_value=="policyPeople_"){
							policyPeople_json="type_="+type_+"&relation1="+relation1+"&policy_tr_id="+parents_id+"&flag=1"+"&customer_id="+customer_id+"&branch_id="+branch_id;
						}
		         	 	if(name_value=="policyLifeBeneficiary_"){
							policyPeople_json="type_="+type_+"&relation1="+relation1+"&policy_tr_id="+parents_id+"&flag=1"+"&customer_id="+customer_id+"&branch_id="+branch_id;
					    } 
		         	 	if(name_value=="policyLifeInsured_"){
							policyPeople_json="type_="+type_+"&relation2="+relation2+"&relation1="+relation1+"&policy_tr_id="+parents_id+"&flag=2"+"&customer_id="+customer_id+"&branch_id="+branch_id;
					    } 
		         	 	if(name_value=="policyLifeBeneficiary_"){
		         	 		//获取被保人的客户名称和id
							$("#policyLifeInsured_info tr").each(function(index,element){
								var name = $(element).find("td[id='policyPeople_name']").html();
								var customer_id = $(element).find("input[name='policyLifeInsured_customer_id']").val();
								 if(name!=undefined){
									insured_name = insured_name+","+name;
								}
								 if(customer_id!=undefined){
									insured_name_value = insured_name_value+","+customer_id;
								}
								var policy_certi_no = $(element).find("td[id='policyPeople_certi_no']").html();//身份证号
								var policy_certi_type = $(element).find("td[id='certi_type']").html();//证件类型
								 if(policy_certi_no!=undefined){
									certi_no = certi_no+","+policy_certi_no;
								}
								 if(policy_certi_type!=undefined){
									certi_type =certi_type+","+policy_certi_type;
								}
							});
							policyPeople_json="type_="+type_+"&insured_customer_id="+insured_customer_id+"&insured_name_value="+insured_name_value+"&insured_name="+insured_name+"&bene_order="+bene_order+"&bene_rate="+bene_rate+"&bene_type="+bene_type+"&relation2="+relation2+"&relation1="+relation1+"&policy_tr_id="+parents_id+"&flag=3"+"&customer_id="+customer_id+"&branch_id="+branch_id;
					    } 
		   			//获取没有选中的tr下的id
		   				$("#"+name_value+"info tr").each(function(index,element){
		   						var checked_tr=$(element).find("input[name='"+name_value+"Checkbox']").attr("checked");
		   							//与主被保人关系
			   					//alert(checked_tr+"--"+name_value);
			   					if(checked_tr!="checked"){
								   var relation2_code=$(element).find("input[name='"+name_value+"relation2']").val();
								    customer_id=$(element).find("input[name='policyLifeBeneficiary_insurant_name_value']").val();
				   					var certi_no_ = $(element).find("td[id='policyPeople_certi_no']").html();//身份证号
									var certi_type_ = $(element).find("td[id='certi_type']").html();//证件类型
			   					/*	if(certi_no_!=undefined&&certi_type_!=undefined){
			   							if(beneficiary_arr==""){
			   								//beneficiary_arr = certi_type_+":"+certi_no_;
			   								beneficiary_arr = "{\"customer_id\":\""+customer_id+"\",\"certi_no\":\""+certi_no+"\",\"certi_type\":\""+certi_type+"\"}";
			   							}else{
			   								//beneficiary_arr = beneficiary_arr+","+certi_type_+":"+certi_no_;
			   								beneficiary_arr =beneficiary_arr+","+"{\"customer_id\":\""+customer_id+"\",\"certi_no\":\""+certi_no+"\",\"certi_type\":\""+certi_type+"\"}";
			   							}
			   							 
			   						}*/
									if(index==1){
										if(certi_no!=undefined&&certi_type!=undefined&&customer_id!=undefined){
											 //beneficiary_arr = beneficiary_arr+","+certi_type+":"+certi_no;
											 beneficiary_arr = "{\"customer_id\":\""+customer_id+"\",\"certi_no_\":\""+certi_no_+"\",\"certi_type\":\""+certi_type_+"\"}";
										}
									}else if(index>1){
										beneficiary_arr =beneficiary_arr+","+"{\"customer_id\":\""+customer_id+"\",\"certi_no\":\""+certi_no_+"\",\"certi_type\":\""+certi_type_+"\"}";
									}
			   						if(relation2_code!=undefined){
			   							 relation2_arr = relation2_arr+","+relation2_code;
			   						}
			   					}
		   					
						});
		         		if(flag==false){
		         			return false;
		         		}
		         		if(beneficiary_arr!=""){
							beneficiary_arr ="["+beneficiary_arr+"]";
						}
		         		//
		         		//获取受益人 所有序号和客户id
						$("#policyLifeBeneficiary_info tr").each(function(index,element){
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
							 
						});
		         		var obj = new  Object();  
		   				obj.branch_id=$("#branch_id").val(); 					
		   				obj.customer_id=customer_id; 			
		   				obj.beneficiary_arr=beneficiary_arr; 			
		   				obj.relation2=relation2_arr; 
		   				obj.certi_no=certi_no;
		   				obj.certi_type=certi_type;
		   				obj.name=insured_name;			
		   				obj.age_value=age_value;			
		   				obj.policyPeople_age=age_value;			
		   				obj.bene_rate=bene_rate;
		   				obj.policyPeople_customer_id=$("input[name='policyPeople_customer_id']").val();
		   				obj.relation_code=$("input[name='policyPeople_relation_code']").val(); //投保人-与主被保人关系
		   				obj.order=insurant_bene_order;
		   				var	url=path+"/cbs/policyLife/customerView.do?"+encodeURI(encodeURI(policyPeople_json));
						var reValue = window.showModalDialog(url,obj,"dialogWidth:1050px;resizable=no;status=no;dialogHeight:900px;dialogLeft:200px;dialogTop:10px;center:yes;help:yes;resizable");
						var type="update";
						if(reValue!=null){
							policyPeople_clone(reValue,type,name_value);//投保人、被保人、受益人
						}
					    
					   
					}else{
						alert("请选择要操作的数据");
					}
		       		    	
		       }
				
			//保单保存
				function CBSPolicyLifeSave(type){
					var path=$("#path").val();
					var msg="";
					var flag=true;
					//获取请求参数
					var params=$("#params").val();
					
					//判断对应被保人的受益比例提交时的值必须是100  否则提示,
					//同时判断被保险人中必须有一个与住被保人关系是本人的被保人->即主被保人有且只有一个
					var relationFlag = 0;  //用于判断与主被保人关系  本人必须有一个且只有一个
					
					if($("#queryForm").valid()){  //再通过表单校验的情况下才执行if内的内容
						blockUI();
						
				 		//投保人不能为空
						var policyPeople_customer_id = $("input[name='policyPeople_customer_id']").val();
						if(policyPeople_customer_id==undefined){
							$.unblockUI();
							flag=false;
							alert("投保人信息不能为空");
							return false;
						}
						//被保险人不能为空
						var policyLifeInsured_customer_id = $("input[name='policyLifeInsured_customer_id']").val();
						if(policyLifeInsured_customer_id==undefined){
							$.unblockUI();
							flag=false;
							alert("被保人信息不能为空");
							return false;
						}
						//受益人
						var policyLifeBeneficiary_customer_id = $("input[name='policyLifeBeneficiary_customer_id']").val();
						if(policyLifeBeneficiary_customer_id==undefined){
							$.unblockUI();
							flag=false;
							alert("被保险人下未添加受益人，请核实。");
							return false;
						}
						//判断被保人下的受益人是否添加完全
						var beneficiary_arr="";
						var policyLifeInsured_bene_arr="";
						var policyLifeBeneficiary_count=0;
						//取得受益人下所有客户id  
						$("#policyLifeBeneficiary_info tr").each(function(index,element){
								var beneficiary_customer_id = $(element).find("input[name='insurant_customer_id']").val();
									 	if(index==1){
										   if(beneficiary_customer_id!=undefined){
												 //beneficiary_arr = beneficiary_arr+","+certi_type+":"+certi_no;
												 beneficiary_arr = beneficiary_customer_id;
											}
										}else if(index>1){
											beneficiary_arr =beneficiary_arr+","+beneficiary_customer_id;
										}
							});
						
						var bene_array = new Array(beneficiary_arr);
						//过滤去重复
						var policyLifeBeneficiary_array=bene_array.reverse().join(",").match( /([^,]+)(?!.*\1)/ig).reverse();
						//获取被保人客户id
						$("#policyLifeInsured_info tr").each(function(index,element){
							var customer_id = $(element).find("input[name='policyLifeInsured_customer_id']").val();
							 if(customer_id!=undefined){
								 	if(index==1){
									   if(customer_id!=undefined){
											 //beneficiary_arr = beneficiary_arr+","+certi_type+":"+certi_no;
											 policyLifeInsured_bene_arr =customer_id;
										}
									}else if(index>1){
										policyLifeInsured_bene_arr =policyLifeInsured_bene_arr+","+customer_id;
									}
							}
						});
						
						policyLifeInsured_bene_arr = policyLifeInsured_bene_arr.split(",");
						 //每一个受益人和被保人循环判断
						 $(policyLifeInsured_bene_arr).each(function(i,value){
					 		 var customer_id = value;
						 		$.each(policyLifeBeneficiary_array, function (index, e) { 
						   				if(e==customer_id){
						   					policyLifeBeneficiary_count=policyLifeBeneficiary_count+1; 
						   				}
					   			});
					    });
						 var policyLifeInsured_bene_length=policyLifeInsured_bene_arr.length;
							if(policyLifeBeneficiary_count!=policyLifeInsured_bene_length){
								$.unblockUI();
								alert("请为被保人添加相关受益人");
								return false;
							}
						
						$("#policyLifeInsured_info tr").each(function(index,element){ //循环被保人信息 当获取一个含有被保人的tr时 再进行遍历受益人信息进行比较
							var customer_id = $(element).find("input[name='policyLifeInsured_customer_id']").val();
							
							if(customer_id != undefined){
								var relation = $(element).find("input[name='policyLifeInsured_relation2']").val();
								
								if(relation == '5'){
									relationFlag++; 
								}
								var sum_bene_rate = 0.00;
								
								$("#policyLifeBeneficiary_info tr").each(function(i,e){ //遍历受益人信息,并获取受益人对应的被保人信息与上层被保人进行比较得出受益人的收益顺序及收益比例并进行校验合法性
									
									var insurant = $(e).find("input[name='policyLifeBeneficiary_insurant_name_value']").val(); //被保人id
									var bene_rate = $(e).find("input[name='policyLifeBeneficiary_bene_rate']").val(); //受益比例
										
									if(insurant!=undefined && bene_rate!=undefined){  //不为空
										
										if(insurant == customer_id){  //且受益人对应的被保人等于上层循环的被保人
											
											if(bene_rate!=undefined){  //判断受益比例是否找到->来到这步以后正常情况下都可以获取到正确的受益比例
												
												sum_bene_rate += parseFloat(bene_rate); //把收到的值转换为浮点型并与当前被保人收益比例进行相加
											}
										}
									}
									
								});
								
								if(sum_bene_rate != 100.00 ){  //通过循环得出当前被保人收益比例总值,如果不是100%则提出
									$.unblockUI();
									flag=false;
									msg="受益比例异常请核实";
									//alert("受益比例异常请核实");
									return false;
								}
							}
							/*msg="受益比例异常请核实1111111";
							flag=false;*/
							
						});
						if(flag==false){
							$.unblockUI();
							if(msg!=""){
								alert(msg);
							}
							return false;
						}
						//同时判断被保险人中必须有一个与住被保人关系是本人的被保人->即主被保人有且只有一个
						if(relationFlag == 0){
							$.unblockUI();
							alert('未定义主被保人,请核实。');
							return false;
						}else if(relationFlag > 1){
							$.unblockUI();
							alert('只能有一个主被保人,请核实。');
							return false;
						} 
						//产品
					 	var productLlife_product_id = $("input[name='productLlife_product_id']").val();
						if(productLlife_product_id==undefined){
							flag=false;
							$.unblockUI();
							alert("险种信息不能为空");
							return false;
						} 
						//判断被保人与险种的关系
							var lifeInsured_count=0; //记录险种中被保人客户id总数
							var policyLifeInsured_int=0;
							var productLlife_arr="";
							var policyLifeInsured_arr="";
							$("#productLlife_info tr").each(function(index,element){
								var customer_id_2 = $(element).find("input[name='productLlife_customer_id']").val();
									 	if(index==1){
										   if(customer_id_2!=undefined){
												 //beneficiary_arr = beneficiary_arr+","+certi_type+":"+certi_no;
												 productLlife_arr = customer_id_2;
											}
										}else if(index>1){
											productLlife_arr =productLlife_arr+","+customer_id_2;
										}
							});
							var v_Array = new Array(productLlife_arr);
							//险种客户过滤去重复
							var productLlife_array=v_Array.reverse().join(",").match( /([^,]+)(?!.*\1)/ig).reverse();

							//获取被保人客户id
							$("#policyLifeInsured_info tr").each(function(index,element){
								var customer_id = $(element).find("input[name='policyLifeInsured_customer_id']").val();
								 if(customer_id!=undefined){
									 	if(index==1){
										   if(customer_id!=undefined){
												 //beneficiary_arr = beneficiary_arr+","+certi_type+":"+certi_no;
												 policyLifeInsured_arr =customer_id;
											}
										}else if(index>1){
											policyLifeInsured_arr =policyLifeInsured_arr+","+customer_id;
										}
								}
							});
							
							 policyLifeInsured_arr = policyLifeInsured_arr.split(",");
							 //每一个险种和被保人循环判断
							 $(policyLifeInsured_arr).each(function(i,value){
						 		 var customer_id = value;
							 		$.each(productLlife_array, function (index, e) { 
							   				if(e==customer_id){
							   					lifeInsured_count=lifeInsured_count+1; 
							   				}
						   			});
						    });
						//var productLlife_length=productLlife_array.length;
						var policyLifeInsured_length=policyLifeInsured_arr.length;
						if(lifeInsured_count!=policyLifeInsured_length){
							$.unblockUI();
							alert("有未添加险种的被保人");
							return false;
						}
						//影像信息
						/*var length = $("form").has("img").length
						if(length <=　0){
							alert("投保单影像件没有上传，请稍后上传，谢谢。");
						}*/
						//保存
						if(type=="add"){
							document.getElementById("queryForm").action=path+"/cbs/policyLife/policyLifeSave.do?params="+params;
							document.getElementById("queryForm").submit();
						}else if(type=="update"){
							document.getElementById("queryForm").action=path+"/cbs/policyLife/policyLifeUpdate.do?params="+params;
							document.getElementById("queryForm").submit();
						}
						else if(type=="contractLifeSave"){
							document.getElementById("queryForm").action=path+"/cbs/contractLife/contractLifeSave.do?params="+params;
							document.getElementById("queryForm").submit();
						}
						else if(type=="contractLifeUpdate"){
							document.getElementById("queryForm").action=path+"/cbs/contractLife/contractLifeUpdate.do?params="+params;
							document.getElementById("queryForm").submit();
						}
						
					 }
					
				}
				
				
				function blockUI(){
					var path=$("#path").val();
					   $.blockUI({
							  message: '<image src="../../compent/charisma/img/loadingpage.gif">请等待...</image>', 
						  css: {
						      border:'none',                   // 无边界
						      width:"150px",                     // 中间框框的宽度
						      top:"10%",                        // 高居中
						      left:"45%",                      // 左居中        
						      padding: '15px',
						      opacity: .9    
						 }
						});
					   
					}