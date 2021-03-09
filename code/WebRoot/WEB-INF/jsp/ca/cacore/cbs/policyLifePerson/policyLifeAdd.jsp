<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>

<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>

<!DOCTYPE html>
<html lang="en">
<base target="_self">
	<head>
		<title>新致金保通</title>
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs-1.7.2.jsp" %>
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jquery-ui-1.10.3.custom.js"></script>
		 <!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/> 
		<!-- 遮盖js -->
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.blockUI.js"></script>
		<script type="text/javascript" src="<%=basePath%>/compent/default/js/policyLife.js"></script>
		<script type="text/javascript" src="<%=basePath%>/compent/default/js/policyLifeShowDialog.js"></script>
		<script type="text/javascript" src="<%=basePath%>/compent/default/js/policyLifeValidate.js"></script>
		
		<style>
		  /**去除jquery ui close按钮**/
		  .my-dialog .ui-dialog-titlebar-close{
		    display: none;
		  }
		 </style> 
           
        <script>
    	$(document).ready(function() {
//	 		子页面控制父页面高度开始
	  	var frameId = "#mainIframe";
		var frameW= 1060;//定义页面宽度
		var option = {
				FrameId : frameId,
				FrameW : frameW,
			};
		$(window).frameWH(option);//控制页面宽度
// 		子页面控制父页面高度结束
		//伸缩 及功能按钮控制
		var Skb1 = $("#Shrinkbutton1");
		var ob1 = $("#Shrinkcontent1");
		var object1 = {
				FrameId : frameId,
				Obj : ob1,
		};
		Skb1.UpDown(object1);
	    
	    var Skb2 = $("#Shrinkbutton2");
	    var ob2 = $("#Shrinkcontent2");
	    var object2 = {
	    		FrameId : frameId,
			    Obj : ob2,
	    };
	    Skb2.UpDown(object2);
	
	 	var Skb3 = $("#Shrinkbutton3");
	    var ob3 = $("#Shrinkcontent3");
	    var object3 = {
	    		FrameId : frameId,
			    Obj : ob3,
	    };
	    Skb3.UpDown(object3);
	    
	    
	    var Skb4 = $("#Shrinkbutton4");
	    var ob4 = $("#Shrinkcontent4");
	    var object4 = {
	    		FrameId : frameId,
			    Obj : ob4,
	    };
	    Skb4.UpDown(object4);
	    
	    var Skb5 = $("#Shrinkbutton5");
	    var ob5 = $("#Shrinkcontent5");
	    var object5 = {
	    		FrameId : frameId,
			    Obj : ob5,
	    };
	    Skb5.UpDown(object5);
	    
	    });
        
   	</script>  
    	<script type="text/javascript">
    	 $(window).unload(function(){
         	
 			$('#uploadimage').remove();
 	     }); 
         
          $(function() {
         	    var path=$("#path").val();
         	    var policy_id=$("#policy_id").val();
         	    //dialog 信息
         	    $("#dialog").dialog({
         	        autoOpen:false,
         	        buttons:[{
         	            text:"是",
         	            click:function(){
         		            	document.getElementById("queryForm").action=path+"/cbs/policyImage/viewPolicyImageForAdd.do?policy_id="+policy_id;
         						document.getElementById("queryForm").submit();
         	                	$("#dialog").dialog("close");
         	                }
         	            },
         				{
         					text: "否",
         					click: function() {
         						$( this ).dialog( "close" );
         					}
         				}
         	            ],
         	            position:"top",//弹出位置
         	            modal:true,
         	            width:500, //窗口宽度
         	            height:280,
         	            dialogClass: "my-dialog",
         	            closeText:false,
         	            drag:function(){
         	                
         	            }

         	        });
         		/*  var result_flag=$("#result_flag").val();
         		 var path=$("#path").val();
          		 var src_cg=path+"/compent/charisma/img/xiao.png";
          		 var src_sb=path+"/compent/charisma/img/ku.png";
          		 if(result_flag=="true"){
           	    	$("img").attr("src",src_cg);
           	    	 $("#dialog").dialog("open");
           	     }else if(result_flag=="false"){
           	    	$("img").attr("src",src_sb);
           	    	$("#dialog").dialog("open");
           	     } */
          		 var result_flag=$("#result_flag").val();
         		 var msg=$("#msg").val();
         		 var path=$("#path").val();
         		 var src_cg=path+"/compent/charisma/img/xiao.png";
         		 var src_sb=path+"/compent/charisma/img/ku.png";
         		 if(msg!=""){
         			if(result_flag=="true"){
             	    	$("#dialog_img").attr("src",src_cg);
             	    	 $("#dialog").dialog("open");
             	     }else if(result_flag=="false"){
             	    	$("#dialog_img").attr("src",src_sb);
             	    	$("#dialog").dialog("open");
             	    	// $("#dialog_false").dialog("open");
             	     }
         		 }
         	     
         	});
    	</script>
	</head>
	<base target="_self">
	<body style="overflow: hidden;" id="qy_name">
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 保单管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 新契约管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 投保单录入</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm"   class="form-horizontal alert alert-info fade in span12" method="POST">
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag id="flag" name="flag" value='${rmHelper.returnParams.flag}'/>
					<webTag:HiddenInputTag id="msg" name="msg" value='${rmHelper.successflag}'/>
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.returnParams.result_flag}" displayLable="修改或者添加"/>
					<webTag:HiddenInputTag id="policy_id" name="policy_id" value="${rmHelper.returnParams.policy_id}" displayLable="保单id"/>
					<webTag:HiddenInputTag id="send_code_repeat" name="send_code_repeat" value='${rmHelper.returnParams.send_code_repeat}' displayLable="投保单号是否重复"/>
					<div class="row">
						<webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" displayLable="保险公司机构" value="${rmHelper.returnParams.insBranch_id}"  isdisplay="true" iclass="required validateSendCode"/>
						<webTag:Text id="send_code" name="send_code" value="${rmHelper.returnParams.send_code}" displayLable="投保单号"  isdisplay="true" iclass="required validateSendCode isNum_policy"/>
					</div>
					<div class="row">
						<webTag:Text id="agent_id" name="agent_id" value="${rmHelper.returnParams.agent_id}" displayLable="保单中介人员代码"  isdisplay="true"  iclass="required branchInfo branchInfo2 isNum" />
						<webTag:Text id="agent_name" name="agent_name" value="${rmHelper.returnParams.agent_name}" displayLable="保单中介人员名称" readonly="true" isdisplay="true"  iclass="required"/>
						<webTag:Text id="branch_id" name="branch_id" value="${rmHelper.returnParams.branch_id}" displayLable="中介机构代码"  readonly="true" isdisplay="true" iclass="required"/>
					</div>
					<div class="row">
						<webTag:Text id="branch_name" name="branch_name" value="${rmHelper.returnParams.branch_name}" displayLable="中介机构名称" readonly="true"  iclass="required" isdisplay="true"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="hignPolicySelect" name="high_policy" id="high_policy" displayLable="高额件" value="${rmHelper.returnParams.high_policy}" isdisplay="true" iclass="required" />
						<webTag:Date id="hold_date" name="hold_date" value="${rmHelper.returnParams.hold_date}" displayLable="投保日期" isdisplay="true" iclass="required"/>
					</div>
					<br>
				<div class="dreadcount">
				    <span class=mrl14><i class="icon-list icon-red"></i> 投保人资料</span>
					<div class="Shrinktop">
						<table id="productLlife_button">
							<tr>
								<td><button type="button"  id="policyPeople_button_id" class="btn btn-mini btn-dangerLight"  onClick="policyPeople_add('policyPeople_');" ><i class="icon-plus icon-white"></i>添加投保人 </button></td>
								<td><button type="button" class="btn btn-mini btn-dangerLight" onClick="policyPeople_update('policyPeople_','policyPeople','');"> <i class="icon-pencil icon-white"></i>修改投保人</button></td>
								<td><button type="button" id="productLlife_del" class="btn btn-mini btn-dangerLight" onClick="policy_del('policyPeople_');" > <i class="icon-remove icon-white"></i>删除投保人 </button></td>
								<td><button type="button" class="btn btn-mini btn-dangerLight" onClick="policyPeople_update('policyPeople_','policyPeople','view');"> <i class="icon-search icon-white"></i>查看投保人 </button></td>
								<td id="image_id"></td>
							</tr>
						</table>
						<div class="slideUp_Down" id="Shrinkbutton1"></div>
					</div>
				</div>
				
				<!-- 可伸缩区域样式 -->
				<div class="Shrinkcontent overAuto row-fluid" id="Shrinkcontent1" >
					<table   id="policyPeople_info" class="table table-striped table-bordered bootstrap-datatable datatable" cellspacing="0">
						<tr>                
		                    <td>选择</td>
		                    <td>姓名</td>
		                    <td>性别</td>
		                    <td>证件类型</td>
		                    <td>证件号码</td>
		                    <td>家庭地址</td>
		                    <td>移动电话</td>
		                    <td>电子邮箱</td>
		                    <td>与主被保人关系</td>
	                  	</tr>
                  		<c:forEach items="${rmHelper.returnParams.holderListView}" var="holder" varStatus="index">
		                  	<tr id="policyPeople_${index.index + 1}">
			                  	<td><input type="checkbox" name="policyPeople_Checkbox" ></td>
			                  	<td>${holder.name}</td>
			                  	<td>${holder.gender}</td>
			                  	<td>${holder.certi_type}</td>
			                  	<td>${holder.certi_no}</td>
			                  	<td>${holder.address}</td>
			                  	<td>${holder.mobile}</td>
			                  	<td>${holder.email}</td>
			                  	<td>${rmHelper.returnParams.relation_name}</td>
			                  	<td style='display:none' ><input type='hidden' name='policyPeople_relation_code' value="${rmHelper.returnParams.relation_id}"/></td>
					            <td style='display:none' ><input type='hidden' name='policyPeople_customer_id' value='${holder.customer_id}'/></td>
		                     	<td style='display:none' ><input type='hidden' name='policyPeople_age' value='${holder.age}'/></td>
		                  	</tr>
	                  	</c:forEach>
					</table>
				</div>
			 <!-- 可伸缩区域样式结束 -->
				
				<div class="dreadcount">
				    <span class=mrl14><i class="icon-list icon-red"></i> 被保险人资料</span>
					<div class="Shrinktop">
						<table id="productLlife_button">
							<tr>
								<td><button type="button" id="productLlife_button_add" class="btn btn-mini btn-dangerLight" onClick="policylife_insured_add();"><i class="icon-plus icon-white"></i>添加被保险人 </button></td>
								<td><button type="button" class="btn btn-mini btn-dangerLight" onClick="policyPeople_update('policyLifeInsured_','policyLifeInsured','');" > <i class="icon-pencil icon-white"></i>修改被保险人</button></td>
								<td><button type="button" id="productLlife_del" class="btn btn-mini btn-dangerLight" onClick="policy_del('policyLifeInsured_');"> <i class="icon-remove icon-white"></i>删除被保险人 </button></td>
								<td><button type="button" class="btn btn-mini btn-dangerLight" onClick="policyPeople_update('policyLifeInsured_','policyLifeInsured','view');"> <i class="icon-search icon-white"></i>查看被保险人 </button></td>
							</tr>
						</table>
						<div class="slideUp_Down" id="Shrinkbutton2"></div>
					</div>
				</div>
				
				<!-- 可伸缩区域样式 -->
				<div class="Shrinkcontent overAuto row-fluid" id="Shrinkcontent2" >
					<table   id="policyLifeInsured_info" class="table table-striped table-bordered bootstrap-datatable datatable" cellspacing="0">
						<tr>                
		                    <td>选择</td>
		                    <td>姓名</td>
		                    <td>性别</td>
		                    <td>证件类型</td>
		                    <td>证件号码</td>
		                    <td>家庭地址</td>
		                    <td>移动电话</td>
		                    <td>电子邮箱</td>
		                    <td>与投保人关系</td>
		                    <td>与主被保人关系</td>
	                  	</tr>
	                  	<c:forEach items="${rmHelper.returnParams.insurantListView}" var="insurant" varStatus="index">
	                  	<tr id="policyLifeInsured_${index.index + 1}">
	                  		<td><input type="checkbox" name="policyLifeInsured_Checkbox"></td>
	                  	    <td id="policyPeople_name">${insurant.name}</td>
	                  	    <td>${insurant.gender}</td>
	                  	    <td>${insurant.certi_type}</td>
	                  	    <td>${insurant.certi_no}</td>
	                  	    <td>${insurant.address}</td>
	                     	<td>${insurant.mobile}</td>
	                    	<td>${insurant.email}</td>
	                    	<td>${insurant.relation1_name}</td>
	                    	<td>${insurant.relation2_name}</td>
			                <td style='display:none' ><input type='hidden' name='policyLifeInsured_customer_id' value='${insurant.customer_id}'/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeInsured_relation1' value="${insurant.relation1}"/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeInsured_relation2' value="${insurant.relation2}"/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeInsured_age' value='${insurant.age}'/></td>
	                  	</tr>
	                  	</c:forEach>
	                </table>  	
			      </div>
				 <!-- 可伸缩区域样式结束 -->
					
				<div class="dreadcount">
				    <span class=mrl14><i class="icon-list icon-red"></i> 受益人资料</span>
					<div class="Shrinktop">
						<table id="productLlife_button">
							<tr>
								<td><button type="button" id="productLlife_button_add" class="btn btn-mini btn-dangerLight" onClick="policyLife_beneficiary_add('policyLifeBeneficiary_');"><i class="icon-plus icon-white"></i>添加受益人 </button></td>
								<td><button type="button" class="btn btn-mini btn-dangerLight" onClick="policyPeople_update('policyLifeBeneficiary_','policyLifeBeneficiary','');"> <i class="icon-pencil icon-white"></i>修改受益人</button></td>
								<td><button type="button" id="productLlife_del" class="btn btn-mini btn-dangerLight" onClick="policy_del('policyLifeBeneficiary_');"> <i class="icon-remove icon-white"></i>删除受益人 </button></td>
								<td><button type="button" class="btn btn-mini btn-dangerLight" onClick="policyPeople_update('policyLifeBeneficiary_','policyLifeBeneficiary','view');"> <i class="icon-search icon-white"></i>查看受益人 </button></td>
							</tr>
						</table>
						<div class="slideUp_Down" id="Shrinkbutton3"></div>
					</div>
				</div>
					
			<!-- 可伸缩区域样式 -->
			<div class="Shrinkcontent overAuto row-fluid" id="Shrinkcontent3" >	
					<table   id="policyLifeBeneficiary_info" class="table table-striped table-bordered bootstrap-datatable datatable" cellspacing="0">
						<tr>                
		                    <td>选择</td>
		                    <td>受益人姓名</td>
		                    <td>性别</td>
		                    <td>证件类型</td>
		                    <td>证件号码</td>
		                    <td>家庭地址</td>
		                    <td>移动电话</td>
		                    <td>电子邮箱</td>
		                    <td>与投保人关系</td>
		                    <td>与被保人关系</td>
		                    <td>受益顺序</td>
		                    <td>受益比例</td>
		                    <td>受益性质</td>
	                  	</tr>
	                  	<c:forEach items="${rmHelper.returnParams.beneficiaryListView}" var="beneficiary" varStatus="index">
	                  	<tr id="policyLifeBeneficiary_${index.index + 1}">
	                  		<td><input type="checkbox" name="policyLifeBeneficiary_Checkbox"></td>
	                  	    <td>${beneficiary.name}</td>
	                  	    <td>${beneficiary.gender}</td>
	                  	    <td>${beneficiary.certi_type}</td>
	                  	    <td>${beneficiary.certi_no}</td>
	                  	    <td>${beneficiary.address}</td>
	                     	<td>${beneficiary.mobile}</td>
	                    	<td>${beneficiary.email}</td>
	                     	<td>${beneficiary.relation1_name}</td>
	                    	<td>${beneficiary.relation2_name}</td>
	                    	<td>${beneficiary.bene_order}</td>
	                    	<td>${beneficiary.bene_rate}</td>
	                    	<td>${beneficiary.bene_type_name}</td>
	                        <td style='display:none' ><input type='hidden' name='policyLifeBeneficiary_customer_id' value='${beneficiary.customer_id}'/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeInsured_relation2' value="${beneficiary.relation2}"/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeBeneficiary_relation1' value="${beneficiary.relation1}"/></td>
                            <td style='display:none' ><input type='hidden' name='policyLifeBeneficiary_relation2' value="${beneficiary.relation2}"/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeBeneficiary_insurant_name_value' value='${beneficiary.insurant_id}'/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeBeneficiary_bene_type_name' value="${beneficiary.bene_type_name}"/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeBeneficiary_bene_type_code' value='${beneficiary.bene_type}'/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeBeneficiary_bene_order' value="${beneficiary.bene_order}"/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeBeneficiary_bene_rate' value="${beneficiary.bene_rate}"/></td>
	                  	    <td style='display:none' ><input type='hidden' name="policyLifeBeneficiary_relation2_name" value="${beneficiary.relation2_name}"/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeBeneficiary_relation1_name' value="${beneficiary.relation1_name}"/></td>
	                  	    <td style='display:none' ><input type='hidden' name='policyLifeBeneficiary_age' value='${beneficiary.age}'/></td>
	                  		</tr>
	                  	</c:forEach>
	                  	</table>
	                  </div>
				   <!-- 可伸缩区域样式结束 -->
	                  	
				<div class="dreadcount">
				    <span class=mrl14><i class="icon-list icon-red"></i> 险种资料</span>
					<div class="Shrinktop">
						<table id="productLlife_button">
							<tr>
								<td><button type="button" id="productLlife_button_add" class="btn btn-mini btn-dangerLight" onClick="productLlife_add();"><i class="icon-plus icon-white"></i>添加险种 </button></td>
								<td><button type="button" class="btn btn-mini btn-dangerLight" onClick="productLlife_update();"> <i class="icon-pencil icon-white"></i>修改险种</button></td>
								<td><button type="button" id="productLlife_del" class="btn btn-mini btn-dangerLight" onClick="policy_del('productLlife_');"> <i class="icon-remove icon-white"></i>删除险种</button></td>
								<td><button type="button" class="btn btn-mini btn-dangerLight" onClick="productLlife_update('view');"> <i class="icon-search icon-white"></i>查看险种</button></td>
							</tr>
						</table>
						<div class="slideUp_Down" id="Shrinkbutton4"></div>
					</div>
				</div>
				
				<!-- 可伸缩区域样式 -->
				<div class="Shrinkcontent overAuto row-fluid" id="Shrinkcontent4" >
					<table   id="productLlife_info" class="table table-striped table-bordered bootstrap-datatable datatable" cellspacing="0">
						<tr>                
		                    <td>选择</td>
		                    <td>险种名称</td>
		                    <td>保额/档次/份数</td>
		                    <td>被保险人</td>
		                    <td>缴费方式</td>
		                    <td>缴费期限</td>
		                    <td>缴保费</td>
	                  	</tr>
	                  	<c:forEach items="${rmHelper.returnParams.productListView}" var="product" varStatus="index">
	                    	<tr id="productLlife_${index.index + 1}">
	                  	  	 <td><input type="checkbox" name="productLlife_Checkbox"></td>
	                  	     <td id="product_name">${product.product_name}</td>
	                  	     <td id='coverage_time'>${product.ap}</td>
	                  	     <td id="name">${product.insurant_name}</td>
	                  	     <td>${product.charge_type_name}</td>
	                  	     <td id='charge_period'>${product.charge_period_name}</td>
	                    	 <td id='period_prem_id'>${product.period_prem}</td>
	                    	 <td id='product_seq_id' style='display:none' >${product.seq_id}</td>
	                    	 <td style='display:none' ><input type='hidden' name='productLlife_product_id' value='${product.product_id}'/></td>
	                  	     <td style='display:none' ><input type='hidden' name='productLlife_coverage_period' value="${product.coverage_period}"/></td>
	                  	     <td style='display:none' ><input type='hidden' name='productLlife_charge_year' value="${product.charge_year}"/></td>
	                  	     <td style='display:none' ><input type='hidden' name='productLlife_charge_period' value='${product.charge_period}'/></td>
	                  	     <td style='display:none' ><input type='hidden' name='productLlife_coverage_year' value="${product.coverage_year}"/></td>
	                  	     <td style='display:none' ><input type='hidden' name='productLlife_charge_type' value="${product.charge_type}"/></td>
	                         <td style='display:none' ><input type='hidden' name='productLlife_amount' value="${product.amount}"></td>
	                  	     <td style='display:none' ><input type='hidden' name='productLlife_period_prem' value="${product.period_prem}"/></td>
	                  	     <td style='display:none' ><input type='hidden' name='productLlife_customer_id' value="${product.insurant_id}"/></td>
	                         <td style='display:none' ><input type='hidden' name='productLlife_auth_payage' value="${product.auth_payAge}"></td>
	                  	     <td style='display:none' ><input type='hidden' name='productLlife_auth_draw' value="${product.auth_draw}"/></td>
	                    	 <td style='display:none' ><input type='hidden' name='productLlife_auth_firstpay' value="${product.auth_firstPay}"/></td>
	                         <td style='display:none' ><input type='hidden' name='productLlife_dividend_choice' value="${product.dividend_choice}"></td>
	                  	     <td style='display:none' ><input type='hidden' name='productLlife_is_autoren' value="${product.is_autoRen}"/></td>
	                  	     <td style='display:none' ><input type='hidden' name='productLlife_customer_id' value="${product.insurant_id}"/></td>
	                    	 <td id='insurant_select' style='display:none'>${product.insurant_id}</td>
	                    	 <td id='product_id' style='display:none'>${product.product_id}</td>
	                    	 <td id='charge_year' style='display:none'>${product.charge_year}</td>
	                    	 <td id='coverage_year' style='display:none'>${product.coverage_year}</td>
	                    	 <td id='amount' style='display:none'>${product.amount}</td>
	                    	 <td id='auth_payage' style='display:none'>${product.auth_payAge}</td>
	                    	 <td id='auth_firstpay' style='display:none'>${product.auth_firstPay}</td>
	                    	 <td id='coverage_period_value' style='display:none'>${product.coverage_period}</td>
	                    	 <td id='charge_period' style='display:none'>${product.charge_period}</td>
	                    	 <td id='charge_type_value' style='display:none'>${product.charge_type}</td>
	                    	 <td id='auth_draw' style='display:none'>${product.auth_draw}</td>
	                    	 <td id='dividend_choice' style='display:none'>${product.dividend_choice}</td>
	                    	 <td id='is_autoren' style='display:none'>${product.is_autoRen}</td>
	                    	 <td id='insured_customer_id' style='display:none'>${product.insurant_id}</td>
	                    	 <td style='display:none' ><input type='hidden' name='product_seq_id' value="${product.seq_id}"/></td>
	                    	</tr>
	                  	</c:forEach>
	                  	</table>
	             </div>
				<!-- 按钮放置在标题内的样式 -->
				<div class="dreadcount">
				    <span class=mrl14><i class="icon-list icon-red"></i> 缴费信息</span>
					<div class="Shrinktop">
						<div class="slideUp_Down" id="Shrinkbutton5"></div>
					</div>
				</div>
				<!-- 按钮放置在标题内的样式结束 -->
				<!-- 可伸缩区域样式 -->
				<div class="Shrinkcontent" id="Shrinkcontent5" >
					<div class="row">
						<webTag:Text id="period_prem" name="period_prem" value="${rmHelper.returnParams.period_prem}" displayLable="首期保费合计" readonly="true" isdisplay="true"  iclass="required"/>
						<webTag:Text id="period_prem_tow" name="period_prem_tow" value="" displayLable="首期保费合计确认"  isdisplay="true" iclass="required"/>
						<webTag:DynamicSelectTag src="payModeSelect" name="pay_mode" id="pay_mode" displayLable="首期付款方式" value="${3}" isdisplay="true" iclass="required"  onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="payModeSelect" name="pay_mode_next" id="pay_mode_next" displayLable="续期付款方式:" value="${3}" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:Text id="bank_code" name="bank_code" value="${rmHelper.returnParams.bank_code}"  displayLable="开户银行" isdisplay="true" iclass="required"/>
                        <webTag:DynamicSelectTag src="bankAccountTypeSelect" name="account_type" id="account_type" displayLable="账号类型" value="${rmHelper.returnParams.account_type}" isdisplay="true" iclass="required" />
					</div>
					<div class="row">
						<webTag:Text id="bank_accName" name="bank_accName" value="${rmHelper.returnParams.bank_accName}" displayLable="银行开户人" isdisplay="true" iclass="required" />
						<webTag:Text id="money_id_name" name="money_id_name" value='人民币' displayLable="币种" readonly="true" isdisplay="true" iclass="required" />
						<webTag:HiddenInputTag id="money_id" name="money_id" value='1' displayLable="币种"/>
						<webTag:Text id="bank_account" name="bank_account" value="${rmHelper.returnParams.bank_account}" displayLable="银行账号" isdisplay="true" iclass="required" />
					</div>
					<div class="row">
						<webTag:Text id="bank_account_tow" name="bank_account_tow" value="${rmHelper.returnParams.bank_account}" displayLable="银行账号确认" isdisplay="true" iclass="required" />
						<webTag:DynamicSelectTag src="policyLifeOverdueSelect" name="overdue_manage" id="overdue_manage" displayLable="保费逾期未付选择" value="${rmHelper.returnParams.overdue_manage}" isdisplay="true" iclass="required" />
					</div>
				</div>
				
					<%--  <!-- 可伸缩区域样式结束 -->	
					 <div id="uploadimage" style="width:100%;">
					     <jsp:include page="../policyImage/imageUpload.jsp"></jsp:include>
					  	 <webTag:Text id="file_name" name="file_name" value="" displayLable="拷贝文件的名字" isdisplay="true" readonly="true"/>
					 </div>  --%>
					 <div class="row" style="text-align:right;">
					     <button type="button" id="policyLifeSave" onclick="CBSPolicyLifeSave('add');" class="btn btn-danger"><i class="icon-inbox icon-white"></i>提交</button>
						 <button type="button" style="display:none" id="policyLifeU" onclick="CBSPolicyLifeSave('update')" class="btn btn-Gray"><i class="icon-edit icon-white"></i>修改</button>
					     <a id="viewBack" class="btn btn-danger" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/main"><i class="icon-share-alt icon-white"></i>  返回</a>
					</div>
				
				</form>
				</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
		 <script>  
   			 window.scrollTo(0,document.body.scrollHeight);  
   		 </script>  
	</body>
	
</html>