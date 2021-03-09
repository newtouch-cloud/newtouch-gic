<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		
		
		
		
		
	
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css" ">
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
		<script type="text/javascript">
		//异步请求获取分配前服务人员代码,判断是否存在
		function queryServiceId(){
			 var bef_service_id = $("#bef_service_id").val();
			 if(bef_service_id!=""){//如果分配前服务人员代码不为空
				 $.ajax({
			     		url : "<%=basePath %>/CBS/ContractAllot/queryServiceId.do",
			     		type : "post",
			     		async : false,
			     		data : {
			     			"bef_service_id": $("#bef_service_id").val(),//分配前服务人员代码
			     			"insBranch_id": $("#insBranch_id").val()//保险公司机构代码
			     		},
			     		success : function(data) {
			     			var str = data.substring(1, data.lastIndexOf('}'));
			     			var isSuccess = str.split(',')[0].split(':')[1];
			     			if (isSuccess == "true") {
			     				$("#flag").val("true");
			     			} else {
			     				$("#flag").val("false");
			      			} 
			     		}
			     	});
			 }else{
				 $("#bef_service_name").val("");//分配前服务人员姓名
	  			 $("#branch_id").val("");//机构代码
	  			 $("#branch_name").val("");//机构名称
	  			 $("tr[name='tr']").remove();//清空保单列表
	  			 $("#flag").val("false");//将标志位置为false
			 }
     	}
     	//异步请求获取保单号,判断是否存在
		function queryPCode(){
			$.ajax({
	     		url : "<%=basePath %>/CBS/ContractAllot/queryPCode.do",
	     		type : "post",
	     		async : false,
	     		data : {
	     			"bef_service_id": $("#bef_service_id").val(),//分配前服务人员代码
	     			"bef_policy_code": $("#bef_policy_code").val(),////所属保单号(页面分配前信息中)
	     			"insBranch_id": $("#insBranch_id").val()//保险公司机构代码
	     		},
	     		success : function(data) {
	     			var str = data.substring(1, data.lastIndexOf('}'));
	     			var isSuccess = str.split(',')[0].split(':')[1];
	     			if (isSuccess == "true") {
	     				$("#flag").val("true");
	     			} else {
	     				var isfalse=str.split(',')[1].split(':')[1];
	     				$("#flag").val("false");
	     				if(isfalse=="0"){
	     					$("#flag").val("false0");
		      			}else if(isfalse=="1"){
		     				$("#flag").val("false1");
		      			}
	     			}
	     		}
	     	});
     	}
     	//异步请求获得分配后服务人员姓名(根据分配后服务人员代码) 
     	function queryAftSName(){
			$.ajax({
	     		url : "<%=basePath %>/CBS/ContractAllot/queryAftSName.do",
	     		type : "post",
	     		async : false,
	     		data : {
	     			"aft_service_id": $("#aft_service_id").val(),//分配后服务人员代码
	     			"bef_service_id": $("#bef_service_id").val()//分配前服务人员代码
	     		},
	     		success : function(data) {
	     			var str = data.substring(1, data.lastIndexOf('}'));
	     			var isSuccess = str.split(',')[0].split(':')[1];
	     			if (isSuccess == "true") {
	     				var aft_service_name=str.split(',')[1].split(':')[1];//分配后服务人员姓名
		 				$("#aft_service_name").val(aft_service_name);//分配后服务人员姓名
	     				$("#flag").val("true");
	     			}else {
	     				var isfalse=str.split(',')[1].split(':')[1];
	 	     			// 清空不可输入框的数据
	 	     			$("#aft_service_name").val("");
	 	     			$("#flag").val("false");
		     			if(isfalse=="0"){//分配后服务人员代码不存在,返回失败0
		     				// 清空不可输入框的数据
		     				$("#aft_service_name").val("");
		     				$("#flag").val("false0");
		      			}else if(isfalse=="1"){//分配后服务人员状态为无效,返回失败1
		      				$("#aft_service_name").val("");
		     				$("#flag").val("false1");
		      			}else if(isfalse=="2"){//分配后服务人员状态为离司,返回失败2
		      				$("#aft_service_name").val("");
		     				$("#flag").val("false2");
		      			}else if(isfalse=="3"){//分配前后服务人员代码相同,返回失败3
		      				$("#aft_service_name").val("");
		     				$("#flag").val("false3");
		      			}else if(isfalse=="4"){//分配后人员职级不为中介人员,返回失败4
		      				$("#aft_service_name").val("");
		     				$("#flag").val("false4");
		      			}
	     			}
	     		}
	     	});
     	}
     	//异步获取保单列表(根据分配前服务人员)
	     function queryPolicyList(){
	    	 var bef_service_id = $("#bef_service_id").val();//分配前服务人员代码
			 $("#t").empty();
			 if(bef_service_id!=""){//分配前服务人员代码不为空
			 $.ajax({
				url : "<%=basePath %>/CBS/ContractAllot/queryPolicyList.do", 
	     		type : "post",
	     		async : false,
	     		data : {
	     			"bef_service_id" :  $("#bef_service_id").val()//分配前服务人员代码
	     			,"insBranch_id" :  $("#insBranch_id").val()//保险公司机构代码
	     		},
	     		dataType:"json",
	     		success : function(data) {
	     			//清空带出的信息
	     			$("#bef_service_name").val("");//分配前服务人员姓名
	 				$("#branch_id").val("");//机构代码
	 				$("#branch_name").val("");//机构名称
	 				$("#bef_policy_code").val("");//所属保单号(页面分配前信息中)
	 				$("#bef_holder_name").val("");//投保人(页面分配前信息中)
		     		$("tr[name='tr']").remove();//保单table下的tr
	     			$.each(data,function(index,comment){
	     				if(index==0){//index为0时增加一个body
							$("#t").after("<tbody id='tb'></tbody>");
	     				}
	     				var policy_id=comment.policy_id;//保单id
						var bef_service_name=comment.bef_service_name;//分配前服务人员姓名
	     				var branch_id=comment.branch_id;//机构代码
	     				var branch_name=comment.branch_name;//机构名称
	     				var insBranch_name=comment.insBranch_name;//保险公司机构名称
						var policy_code=comment.policy_code;//保单号
	     				var holder_name=comment.holder_name;//投保人姓名
	     				var product_name = comment.product_name;//险种
	     				var amount = comment.amount;//保额
						var period_prem=comment.period_prem;//保费
	     				var validate_date=comment.validate_date;//保单生效日期
	     				$("#bef_service_name_hidden").val(bef_service_name);//给分配前服务人员姓名的隐藏域赋值
	     				$("#branch_id_hidden").val(branch_id);//给机构代码的隐藏域赋值
	     				$("#branch_name_hidden").val(branch_name);//给机构名称的隐藏域赋值
	     				//遍历一条数据加一个tr(将保单id的值给 checkbox)
						$("#tb").append("<tr name='tr'>"
			+"<td>"+"<input type='checkbox' validate='required:true' name='policy_id' id='check' value='"+policy_id+"'/>"+"</td>"
							+"<td>"+insBranch_name+"</td>"//保险公司名称
							+"<td>"+policy_code+"</td>"//保单号
							+"<td>"+holder_name+"</td>"//投保人
							+"<td>"+product_name+"</td>"//险种
							+"<td>"+amount+"</td>"//保额
							+"<td>"+period_prem+"</td>"//保费
							+"<td>"+validate_date+"</td>"//保单生效日期
							+"</tr>");
	     			});
	     			$("#bef_service_name").val($("#bef_service_name_hidden").val());//隐藏域的值赋给分配前服务人员姓名
	     			$("#branch_id").val($("#branch_id_hidden").val());//隐藏域的值赋给机构代码
     				$("#branch_name").val($("#branch_name_hidden").val());//隐藏域的值赋给机构名称
				 }
			 });
			 }else{//清空不可输入框的信息
	 				$("#bef_service_name").val("");
	  				$("#branch_id").val("");
	  				$("#branch_name").val("");
	  				$("tr[name='tr']").remove();
	  				$("#flag").val("false");
	 			}
	 	}
     	//获取保单信息(根据保单号)
	     function queryServiceByPolicyCode(){
	    	 var bef_service_id = $("#bef_service_id").val();//分配前服务人员代码
	    	 var bef_policy_code= $("#bef_policy_code").val();////所属保单号(页面分配前信息中)
			 $("#t").empty();
			 if(bef_policy_code!=""){//如果所属保单号不为空
			 $.ajax({
				url : "<%=basePath %>/CBS/ContractAllot/queryServiceByPolicyCode.do", 
	     		type : "post",
	     		async : false,
	     		data : {
	     			"bef_service_id" :  $("#bef_service_id").val()//分配前服务人员代码
	     			,"bef_policy_code": $("#bef_policy_code").val()//所属保单号(页面分配前信息中)
	     			,"insBranch_id" :  $("#insBranch_id").val()//保险公司机构代码
	     			,"branch_id" :  $("#branch_id").val()//机构代码
	     		},
	     		dataType:"json",
	     		success : function(data) {
	     			//清空带出的信息
	     			$("#bef_service_name").val("");//分配前服务人员姓名
	 				$("#branch_id").val("");//机构代码
	 				$("#branch_name").val("");//机构名称
	 				$("#bef_holder_name").val("");//投保人(页面分配前信息中)
		     		$("tr[name='tr']").remove();
	     			$.each(data,function(index,comment){
	     				if(index==0){
							$("#t").after("<tbody id='tb'></tbody>");
	     				}
	     				var policy_id=comment.policy_id;//保单id
	     				var bef_service_id=comment.bef_service_id;//分配前服务人员代码
						var bef_service_name=comment.bef_service_name;//分配前服务人员姓名
	     				var branch_id=comment.branch_id;//机构代码
	     				var branch_name=comment.branch_name;//机构名称
	     				var bef_holder_name=comment.bef_holder_name;//投保人(页面分配前信息中)
	     				var insBranch_name=comment.insBranch_name;//保险公司机构名称
						var policy_code=comment.policy_code;//保单号
	     				var holder_name=comment.holder_name;//投保人
	     				var product_name = comment.product_name;//险种
	     				var amount = comment.amount;//保额
						var period_prem=comment.period_prem;//保费
	     				var validate_date=comment.validate_date;//保单生效日期
	     				if($("#bef_service_id").val()==""){//如果分配前服务人员代码为空
	     					$("#bef_service_id").attr("readonly",true); //将分配前服务人员代码置灰
		     				$("#bef_service_id").attr("disabled","disabled");
	     				}
	     				$("#policy_id").val(policy_id);//保单id
	     				$("#bef_service_id").val(bef_service_id);//分配前服务人员代码
	     				
	     				$("#bef_service_name").val(bef_service_name);//分配前服务人员姓名
	     				$("#branch_id").val(branch_id);//机构代码
	     				$("#branch_name").val(branch_name);//机构名称
	     				$("#bef_holder_name").val(bef_holder_name);//投保人(页面分配前信息中)
						$("#tb").append("<tr name='tr'>"
		+"<td>"+"<input type='checkbox' validate='required:true'   name='policy_id' id='check' value='"+policy_id+"'/>"+"</td>"
							+"<td>"+insBranch_name+"</td>"//保险公司机构名称
							+"<td>"+policy_code+"</td>"//保单号
							+"<td>"+holder_name+"</td>"//投保人
							+"<td>"+product_name+"</td>"//险种
							+"<td>"+amount+"</td>"//保额
							+"<td>"+period_prem+"</td>"//保费
							+"<td>"+validate_date+"</td>"//保单生效日期
							+"</tr>");
	     				});
				 	}
				 });
				 }else{
					 //清空带出的信息
				 	$("#bef_service_id").val("");//分配前服务人员代码
	 				$("#bef_service_name").val("");//分配前服务人员姓名
	  				$("#branch_id").val("");//机构代码
	  				$("#branch_name").val("");//机构名称
	  				$("#bef_holder_name").val("");//投保人(页面分配前信息中)
	  				$("tr[name='tr']").remove();
	  				$("#flag").val("false");
	 			}
			 
	 	}
	 	jQuery.validator.addMethod("getServiceId", function(value, element, param)  {
	 		queryServiceId();//异步请求获取分配前服务人员代码,判断是否存在
	     	if ($("#flag").val()!= "false") {//存在
	     		queryPolicyList();//异步获取保单列表(根据分配前服务人员)
	     		return true;
	     	} else {//不存在
	     		//清空带出的信息
	     		$("#bef_service_name").val("");
 				$("#branch_id").val("");
 				$("#branch_name").val("");
	     		$("tr[name='tr']").remove();
	     		return false;
	     	}
	     }, "输入的分配前服务人员代码错误，请重新输入");
	     jQuery.validator.addMethod("getPolicyCode0", function(value, element, param)  {
	    	 queryPCode();//异步请求获取保单号,判断是否存在
	     	if ($("#flag").val()!= "false0") {
	     		queryServiceByPolicyCode();//获取保单信息(根据保单号)
	     		return true;
	     	} else {//清空带出的信息
	     		$("#bef_holder_name").val("");
	     		$("tr[name='tr']").remove();
	     		return false;
	     	}
	     }, "输入的所属保单号错误，请重新输入");
	     jQuery.validator.addMethod("getPolicyCode1", function(value, element, param)  {
	    	 queryPCode();//异步请求获取保单号,判断是否存在
	     	if ($("#flag").val()!= "false1") {
	     		queryServiceByPolicyCode();//获取保单信息(根据保单号)
	     		return true;
	     	} else {//清空带出的信息
	     		$("#bef_service_id").val("");
 				$("#bef_service_name").val("");
  				$("#branch_id").val("");
  				$("#branch_name").val("");
  				$("#bef_holder_name").val("");
  				$("tr[name='tr']").remove();
	     		return false;
	     	}
	     }, "输入的所属保单无效，请重新输入");
	     jQuery.validator.addMethod("getAftServiceId0", function(value, element) {
	     		queryAftSName();//异步请求获得分配后服务人员姓名(根据分配后服务人员代码) 
		     	if ($("#flag").val()!= "false0") {
		     		return true;
		     	} else {
		     		return false;
		     	}
		     }, "输入的分配后服务人员代码错误，请重新输入");
	     jQuery.validator.addMethod("getAftServiceId1", function(value, element) {
	     		queryAftSName();//异步请求获得分配后服务人员姓名(根据分配后服务人员代码) 
		     	if ($("#flag").val()!= "false1") {
		     		return true;
		     	} else {
		     		return false;
		     	}
		     }, "分配后的服务人员处于待入司状态，不满足条件，请重新录入");
	     jQuery.validator.addMethod("getAftServiceId2", function(value, element) {
	     		queryAftSName();//异步请求获得分配后服务人员姓名(根据分配后服务人员代码) 
		     	if ($("#flag").val()!= "false2") {
		     		return true;
		     	} else {
		     		return false;
		     	}
		     }, "分配后的服务人员处于离司状态，不满足条件，请重新录入");
	     jQuery.validator.addMethod("getAftServiceId3", function(value, element) {
	     		queryAftSName();//异步请求获得分配后服务人员姓名(根据分配后服务人员代码) 
		     	if ($("#flag").val()!= "false3") {
		     		return true;
		     	} else {
		     		return false;
		     	}
		     }, "分配前和分配后的服务人员为同一人,请重新录入");
	     jQuery.validator.addMethod("getAftServiceId4", function(value, element) {
	     		queryAftSName();//异步请求获得分配后服务人员姓名(根据分配后服务人员代码) 
		     	if ($("#flag").val()!= "false4") {
		     		return true;
		     	} else {
		     		return false;
		     	}
		     }, "分配后的服务人员应为中介人员,请重新录入");
	     $(document).ready(function() {
	    		$("#beforeForm").validate({
	    			rules : {
	    				bef_service_id : {//分配前服务人员代码
	    					maxlength : 15,
	    					getServiceId : []
	    				},
	    				bef_policy_code:{//所属保单号(页面分配前信息中)
	    					maxlength : 12,
	    					getPolicyCode0: [],
	    					getPolicyCode1: []
	    				}
	    			},
	    			onkeyup:false
	    		});
	    	});
	     $(document).ready(function() {
	    		$("#afterForm").validate({
	    			rules : {
	    				aft_service_id : {//分配后服务人员代码
	    					required : true,
	    					maxlength : 15,
	    					getAftServiceId0: [],
	    					getAftServiceId1: [],
	    					getAftServiceId2: [],
	    					getAftServiceId3: [],
	    					getAftServiceId4: []
	    				}
	    			},
	    			onkeyup:false
	    		});
	    	});
	     
	     function commitForm(){//点击保存时的onclick事件
	    	 $('#bef_service_id_hidden').val($('#bef_service_id').val());//分配前服务人员代码
	    	 $('#branch_id_hidden2').val($('#branch_id').val());//机构代码
	    	 $('#insBranch_id_hidden').val($('#insBranch_id').val());//保险公司机构
			 if($("input[name='policy_id']:checkbox:checked").size() ==0){//如果没有勾选保单
				 alert("请选择要分配的保单");
			 }else{
				 $('#afterForm').submit(); 
			 }
	     }
		 </script>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>保单管理菜单 <span class="divider">/</span></li>
					<li>保单续期管理 <span class="divider">/</span></li>
					<li>保单分配</li>
				</ul>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--  分配信息面板 start -->
			<div class="row-fluid">
				<form id="beforeForm" name="beforeForm" class="form-horizontal alert alert-info fade in span12" method="POST">
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
					<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnMsg.dataTable.seq_id}'/>
					<webTag:HiddenInputTag  id="policy_id" name="policy_id" value='${rmHelper.returnMsg.dataTable.policy_id}'/><!-- 保单id -->
					<webTag:HiddenInputTag   id="insBranch_name" name="insBranch_name" value='${rmHelper.returnMsg.dataTable.insBranch_name}'/><!-- 保险公司机构名称 -->
					<webTag:HiddenInputTag  id="holder_id" name="holder_id" value='${rmHelper.returnMsg.dataTable.holder_id}'/><!-- 投保人id -->
					<!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <!--  分配前信息 start -->
					<div class="row">
						 分配前信息
					</div>
					<fieldset>
						<div class="row">
							<webTag:Text id="bef_service_id" name="bef_service_id"	value='${rmHelper.returnMsg.dataTable.bef_service_id}'  displayLable="分配前服务人员代码<font color=red>*</font>:" />
							<webTag:Text id="bef_service_name" name="bef_service_name"	value='${rmHelper.returnMsg.dataTable.bef_service_name}'  readonly="true"	displayLable="分配前服务人员姓名:" />
							<webTag:HiddenInputTag  id="bef_service_name_hidden" name="bef_service_name_hidden" value='${rmHelper.returnMsg.dataTable.bef_service_name}'/>
						</div><!-- /.row -->
						<div class="row">
							<webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnMsg.dataTable.branch_id}'  readonly="true" displayLable="机构代码:" />
							<webTag:HiddenInputTag  id="branch_id_hidden" name="branch_id_hidden" value='${rmHelper.returnMsg.dataTable.branch_id}'/>
							<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnMsg.dataTable.branch_name}'  readonly="true" displayLable="机构名称:" />
							<webTag:HiddenInputTag  id="branch_name_hidden" name="branch_name_hidden" value='${rmHelper.returnMsg.dataTable.branch_name}'/>
						</div><!-- /.row -->
						<div class="row">
							<webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" value='${returnHelper.returnParam.insBranch_id}' displayLable="保险公司机构<font color=red>*</font>:"></webTag:DynamicSelectTag>
							<webTag:Text id="bef_policy_code" name="bef_policy_code" value='${rmHelper.returnMsg.dataTable.bef_policy_code}'     displayLable="所属保单号<font color=red>*</font>:" />
							<webTag:Text id="bef_holder_name" name="bef_holder_name" value='${rmHelper.returnMsg.dataTable.bef_holder_name}' readonly="true" displayLable="投保人:" />
						</div><!-- /.row -->
					</fieldset>
				</form>
				<!--  分配前信息 end -->
				<div class="row-fluid">
				<!-- 保单信息 start -->
			 	<form id="afterForm" name="afterForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CBS/ContractAllot/updatePolicyService.do" method="POST">
					<webTag:HiddenInputTag   id="bef_service_id_hidden" name="bef_service_id_hidden" value='${rmHelper.returnMsg.dataTable.bef_service_id}' />
					<webTag:HiddenInputTag   id="branch_id_hidden2" name="branch_id_hidden2" value='${rmHelper.returnMsg.dataTable.branch_id}' />
					<webTag:HiddenInputTag   id="insBranch_id_hidden" name="insBranch_id_hidden" value='${rmHelper.returnMsg.dataTable.insBranch_id}' />
					<div class="row">
						保单信息
					</div>
					<div class="row-fluid">
						<table  class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr >
									<th>选择</th>
									<th>保险公司</th>
									<th>保单号</th>
									<th>投保人</th>
									<th>险种</th>
									<th>保额</th>
									<th>保费</th>
									<th>保单生效日期</th>
								</tr>
							</thead>
							<tbody id="t">
							</tbody>
						</table>
					</div>
					<div class="pagination pagination-centered">
				    	<ul id="Pagination"></ul>
					</div>
					<!-- 保单信息 end -->
					<!--  分配后信息 start -->
					<div class="row">
						分配后信息
					</div>
					<div class="row">
					<webTag:Text id="aft_service_id" name="aft_service_id"  value='${rmHelper.returnMsg.dataTable.aft_service_id}'
						displayLable="分配后服务人员代码<font color=red>*</font>:" />
					<webTag:Text id="aft_service_name" name="aft_service_name"   value='${rmHelper.returnMsg.dataTable.aft_service_name}'  readonly="true"  
						displayLable="分配后服务人员姓名<font color=red>*</font>:" />
					</div>
					<div class="row" style="text-align: right;">
					<button type="button" onclick="commitForm();" class="btn btn-mini btn-primary" ">保存</button>
					<a class="btn btn-mini btn-primary" href="<%=basePath %>/CBS/ContractAllot/queryPolicy.do">返回</a>
					</div>
				</div>
			</div>
			<!--  分配后信息 end -->
			<!--  分配信息面板 end -->
		</div>
	</body>
</html>
