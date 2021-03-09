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
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >
		<!-- 校验失败样式 -->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/compent/newtouch/util/validation.css">
		
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jqueryui/jquery-1.9.1.js"></script>
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jquery-ui-1.10.3.custom.js"></script>
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
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
			
        <script >       
        var strs_order = new Array();  //接收被保人id+已有收益顺序,strs_order含有被保人及其对应的 已有受益顺序
        //异步请求：自动带出数据
	     $(function() {
	    	 
	    	   var  obj = window.dialogArguments  ;
			   $("#branch_id").val(obj.branch_id);//中介机构id
			   $("#policy_certi_no").val(obj.policy_certi_no);//
			   $("#policy_certi_type").val(obj.policy_certi_type);//
			   $("#age").val(obj.age_value);//
	           var  insurant_name_arr=$("#insurant_name_arr").val();
	           var  insurant_name_value=$("#insurant_name_value").val();
	           var  insurant_name_selected=$("#insurant_name_selected").val();
			   
			   var strs = new Array();
			   var strs_value = new Array();
			   var value_="";
			   var certi_no="";//证件号码
			   var certi_no_value="";
			   var certi_type="";
			   var certi_type_value="";
			   	$("#insurant_name").empty();
			   if(insurant_name_arr!=""){
			   		strs = insurant_name_value.split(",");
			   		strs_arr = insurant_name_arr.split(",");
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
	      			strs_order = obj.order.split(",");  //strs_order含有被保人及其对应的 已有受益顺序,->结构: ,被保人id:受益顺序,被保人id:受益顺序
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
		   			$("#insurant_name").attr("disabled", "disabled");
			   
			   
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
				 		}
				 	},
				 	messages:{
				 		name:"请输入正确的受益人姓名",
				 		name_tow:"请输入相同的受益人姓名",
				 		certi_no_tow:"请输入相同的证件号码"
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
	        });
	     
 	     
 	    function setDateAndAge(value){
	 		//获取输入身份证号码 
	       	var UUserCard = value; 
	       	//获取出生日期 
	       	$('#birthday').val(UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14)); 
	       	
	       	//获取年龄 
	       	var myDate = new Date(); 
	       	var month = myDate.getMonth() + 1; 
	       	var day = myDate.getDate();
	       	var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1; 
	       	if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) { 
	       	age++; 
	       	} 
	       	if(age==0){
	       		age=1;
	       	}
	       	$('#age').val(age);
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
					<div class="row">
					    <webTag:DynamicSelectTag src="beneficiaryTypeSelect" name="bene_type" id="bene_type" value='${rmHelper.returnParams.bene_type}' displayLable="受益性质" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					    				<webTag:Text id="bene_order" name="bene_order" value='${rmHelper.returnParams.bene_order}' displayLable="受益顺序" isdisplay="true"  readonly="true"/>
						<webTag:Text id="bene_rate" name="bene_rate" value='${rmHelper.returnParams.bene_rate}' displayLable="受益比例" isdisplay="true"  readonly="true"/>

					</div>
					<div class="row">
					    <webTag:DynamicSelectTag src="applicantRelation1Select" name="relation1" id="relation1" value='${rmHelper.returnParams.relation1}' displayLable="与投保人关系" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					    <webTag:DynamicSelectTag src="applicantRelation2Select" name="relation2" id="relation2" value='${rmHelper.returnParams.relation2}' displayLable="与被保人关系" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
						<webTag:Select id="insurant_name" name="insurant_name" value='${rmHelper.returnParams.insurant_name}' displayLable="被保人姓名" isdisplay="true" readonly="true" >
                        </webTag:Select> 
						<webTag:HiddenInputTag id="insurant_name_selected" name="insurant_name_selected" value='${rmHelper.returnParams.insurant_name}' displayLable="被保人姓名锁定" />
						<webTag:HiddenInputTag id="insurant_name_value" name="insurant_name_value" value='${rmHelper.returnParams.insurant_name_value}' displayLable="被保人姓名集合" />
						<webTag:HiddenInputTag id="insurant_name_arr" name="insurant_name_arr" value='${rmHelper.returnParams.insurant_name_arr}' displayLable="被保人姓名id数组" />
						<webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="受益人姓名" isdisplay="true" readonly="true"/>
						<webTag:Text id="name_tow" name="name_tow" value='${rmHelper.returnParams.name}' displayLable="受益人姓名（二录）" isdisplay="true" readonly="true"/>
					</div>
					<div class="row">
					    <webTag:DynamicSelectTag src="certiTypeSelect" name="certi_type" id="certi_type" value='${rmHelper.returnParams.certi_type}' displayLable="受益人证件类型" isdisplay="true"  onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
						<webTag:Text id="certi_no" name="certi_no" value='${rmHelper.returnParams.certi_no}' displayLable="受益人证件号码" onblur="customerInfo();" isdisplay="true"  readonly="true" />
						<webTag:Text id="certi_no_tow" name="certi_no_tow" value='${rmHelper.returnParams.certi_no}' displayLable="受益人证件号码（二录）" isdisplay="true"  readonly="true"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="genderSelect"  id="gender" name="gender" value='${rmHelper.returnParams.gender}' isdisplay="true" displayLable="性别" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:Date id="birthday" name="birthday" value='${rmHelper.returnParams.birthday}' displayLable="出生日期" isdisplay="true" iclass="required dateISO" readonly="readonly"/>
					    <webTag:DynamicSelectTag src="nationalitySelect"  id="nationality" name="nationality" value='${rmHelper.returnParams.nationality}'  displayLable="国籍:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
						<webTag:Date  id="certi_validDate" name="certi_validDate" value='${rmHelper.returnParams.certi_validdate}'  displayLable="证件有效期限至"  isdisplay="true"  readonly="readonly"/>
						<webTag:Text id="age" name="age" value='${rmHelper.returnParams.age}' displayLable="年龄" iclass="required" isdisplay="true" readonly="true"/>
						<webTag:DynamicSelectTag src="customerIncomTypeSelect"  id="income_type" name="income_type" value='${rmHelper.returnParams.income_type}'  displayLable="平均年收入（过去三年）:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="nationSelect" name="nation" id="nation" value='${rmHelper.returnParams.nation}' displayLable="民族" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
						<webTag:Text id="homeplace" name="homeplace" value='${rmHelper.returnParams.homeplace}' displayLable="籍贯:" readonly="true"/>
						<webTag:DynamicSelectTag src="maritalSelect" name="marital_stat" id="marital_stat" value='${rmHelper.returnParams.marital_stat}' displayLable="婚姻状况" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="politicalSelect"  id="political" name="political" value='${rmHelper.returnParams.political}'  displayLable="政治面貌:" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
                        <webTag:DynamicSelectTag src="educationSelect" name="education2" id="education2" value='${rmHelper.returnParams.education2}' displayLable="教育程度:" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
                        <webTag:DynamicSelectTag src="healthSelect" name="health" id="health" value='${rmHelper.returnParams.health}' displayLable="健康状况:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:Text id="height" name="height" value='${rmHelper.returnParams.height}' displayLable="身高:"  iclass="checkHeight" readonly="true"/>
						<webTag:Text id="weight" name="weight" value='${rmHelper.returnParams.weight}' displayLable="体重kg:"  iclass="checkWeight" readonly="true"/>
						<webTag:DynamicSelectTag  src="hignPolicySelect" id="is_telMsgService" name="is_telMsgService" value='${rmHelper.returnParams.is_telmsgservice}'  displayLable="是否接受手机短信服务:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
					</div>
					<div class="row">
						<webTag:Text id="mobile" name="mobile" value='${rmHelper.returnParams.mobile}' displayLable="移动电话:" iclass="mobilePhone" readonly="true"/>
						<webTag:Text id="telphone" name="telphone" value='${rmHelper.returnParams.telphone}' displayLable="住宅电话:"  iclass="telephone" readonly="true"/>
						<webTag:Text id="fax" name="fax" value='${rmHelper.returnParams.fax}' displayLable="传真电话:"  iclass="telephone" readonly="true"/>
					</div>
					<div class="row">
						<webTag:Text id="email" name="email" value='${rmHelper.returnParams.email}' displayLable="电子邮箱:" iclass="email" readonly="true"/>
						<webTag:Text id="address" name="address" value='${rmHelper.returnParams.address}' displayLable="家庭住址:" readonly="true"/>
						<webTag:Text id="zip" name="zip" value='${rmHelper.returnParams.zip}' displayLable="邮政编码:" iclass="checkPost" readonly="true"/>
					</div>
					<div class="row">
						<webTag:Text id="job_com" name="job_com" value='${rmHelper.returnParams.job_com}' displayLable="工作单位名称:" readonly="true"/>
						<webTag:Text id="job_code" name="job_code" value='${rmHelper.returnParams.job_code}' displayLable="职业代码:" readonly="true"/>
						<webTag:Text id="job_tel" name="job_tel" value='${rmHelper.returnParams.job_tel}' displayLable="办公电话:"  iclass="telephone" readonly="true"/>
					</div>
				</form>
									
				    <div class="row" style="text-align:right;">
					    		<button type="button" onclick="window.close()" class="btn btn-danger"><i class="icon-share-alt icon-white"></i>返回</button>
					</div><!-- /.row -->
			</div>
			
			
		</div>
	</body>
	
</html>