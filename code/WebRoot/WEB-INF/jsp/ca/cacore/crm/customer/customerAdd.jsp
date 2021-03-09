<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
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
	<head >
		<title>新致金保通</title>
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#personBaseInfo").hide();//个人基本信息
				$("#personContactInfo").hide();//个人联系信息
				$("#corporationBaseInfo").hide();//法人基本信息
				$("#corporationContactInfo").hide();//法人联系信息
				$("#customerValueInfo").hide();//客户价值分析
			});
			
			
		$(document).ready(function() {
			$("#queryForm").validate({
	    			rules : {
	    				branch_name : {  //机构名称
	    					required : true,
	    					checkBranchName : []
	    				},
	    				name : { //姓名
	    					//required : true,
	    					maxlength :50
// 	    					,checkCustomerRepeate : []
	    				},
	    				member_id :　{
	    					checkMemberId : []
	    				}
	    				,
	    				title : { //称呼
	    					maxlength :50
	    				},
	    				customer_type : {  //客户类型
	    					required : true
	    				//	customerTypeChange:[] 
	    				},
	    				gender : {  //性别
	    					//required : true,
// 	    					checkCustomerRepeate : []
	    				},
	    				birthday : {  //生日
	    					//required : true,
	    					checkBirthday : []
// 	    					,checkCustomerRepeate : []
	    				},
	    				certi_type : { //证件类型
	    					//required : true,
	    					checkCustomerRepeate : []
	    				},
	    				certi_no : {  //证件号码
	    					//required : true,
	    					checkIdCard : [],
	    					checkCustomerRepeate : []
	    				},
	    				certi_validdate : {  //证件有效期
	    					//required : true
	    				},
	    				education : {   //学历
	    					maxlength :5
	    				},
	    				nationality : { //国籍
	    					maxlength :5
	    				},
	    				nation : {  //民族
	    					//required : true
	    				},
	    				homeplace : {  //籍贯
	    					maxlength :50
	    				},
	    				seat_address : { //户口所在地
	    					maxlength :50
	    				},
	    				height : {  //身高
	    					checkHeight : []
	    				},
	    				weight : {  //体重
	    					checkWeight : []
	    				},
	    				political : { //政治面貌
	    					//required : true
	    				},
	    				education2 : {  //教育程度
	    					//required : true
	    				},
	    				marital_stat : {  //婚姻状况
	    					//required : true
	    				},
	    				job_code : { //职业名称
	    					maxlength :25
	    				},
	    				bank_code : { //银行编码
	    					maxlength :10
	    				},
	    				bank_account_no : { //银行账号
	    					maxlength :32,
	    					checkNum : []
	    				},
	    				bank_account_name : { //账户名
	    					maxlength :32
	    				},
	    				remark : { //备注
	    					maxlength :500
	    				},
	    				address : { //家庭地址
	    					maxlength :50
	    				},
	    				zip : {  //邮政编码
	    					checkPost : []
	    				},
	    				mobile : { //移动电话
	    					mobilePhone : []
	    				},
	    				fax : {  //传真
	    					telephone : []
	    				},
	    				telphone : { //住宅电话
	    					telephone : []
	    				},
	    				email : {//邮箱
	    					email : []
	    				},
	    				job_com : {//工作单位名称
	    					maxlength :50
	    				},
	    				job_tel : {//办公电话
	    					telephone : []
	    				},
	    				 company_name:{
	    					maxlength :60
	    				},
	    				company_address:{
	    					maxlength :60
	    				},
	    				company_telphone:{
	    					telephone : []
	    				},
	    				company_fax:{
	    					telephone : []
	    				},
	    				company_mobile:{
	    					mobilePhone : []
	    				},
	    				company_postcode:{
	    					checkPost : []
	    				},
	    				corporation_represen:{
	    					maxlength :60
	    				},
	    				company_url:{
	    					maxlength :60
	    				},
	    				company_mail:{
	    					email : []
	    				},
	    				company_industry:{
	    					maxlength :60
	    				},
	    				corporation_represen_wechat:{
	    					maxlength :60
	    				},
	    				company_remark:{
	    					maxlength :500
	    				},
	    				corporation_represen_qq:{
	    					number:true
	    				},
	    				corporation_contact_person:{
	    					maxlength :60,
	    					required:true
	    				},
	    				corporation_contact_mobile:{
	    					mobilePhone : [],
	    					required:true
	    				},
	    				corporation_contact_qq:{
	    					number:true
	    				},
	    				corporation_contact_wechat:{
	    					maxlength :60
	    				} 
	    			},
	    			messages:{
	    				fax:"请输入正确的传真电话",
	    				telphone:"请输入正确的住宅电话",
	    				job_tel:"请输入正确的办公电话",
	    				email:"请输入正确的电子邮箱"
				 	},
	    			onkeyup:false,
			});
			//校验样式效果,文本框获取焦点,隐藏该文本框相应报错信息
	   		 $("#queryForm").find("input").each(function(){
	           		$(this).click(function(){
	           			var _this=$(this);
	           			if(_this.hasClass("error")){
	           				_this.removeClass("error");
	           				var labelAR = _this.parent().find("label[class='error']");
	           				labelAR.remove();
	           			}
	           		});
	           	});	
		});
	
		  $(function() {
			     $("#customer_type").change(
			    	function(){
			    		customerTypeChange();
			    	}
			     );
		    });
		
		function customerTypeChange(){
			var customer_type=$("#customer_type").val();
			if(customer_type!="" && customer_type!=null){
				if("0"==customer_type){//个人
					 $("#personBaseInfo").show();//个人基本信息
					 $("#personContactInfo").show();//个人联系信息
					 $("#customerValueInfo").show();//客户价值分析 
					 $("#corporationBaseInfo").hide();//法人基本信息
					 $("#corporationContactInfo").hide();//法人联系信息
				}else if("1"==customer_type){//法人
					$("#corporationBaseInfo").show();//法人基本信息
					$("#corporationContactInfo").show();//法人联系信息
					$("#customerValueInfo").show();//客户价值分析 
					$("#personBaseInfo").hide();//个人基本信息
					$("#personContactInfo").hide();//个人联系信息
				}
			}
		}
		
		
		 //验证身份证
	     jQuery.validator.addMethod("checkIdCard", function(value, element,param) {
	    	//如果证件类型为身份证（value=11）那么验证身份证号的正确性
	    	var certi_type = $("#certi_type").val();
	    	
	     	if(certi_type!="11"){
	     		return true;
	     	}
	    	var idcard = $("#certi_no").val();
	     	if(isUndefined(idcard)){
	     		return true;
	     	}
	     	var regex1 = /^[1-9][0-7]\d{4}((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))\d{3}(\d|X|x)?$/;
	     	var regex2;
	     	/*身份号码位数及格式检验*/
	     	switch (idcard.length) {
	     	  case 15:
	     		if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
	     			regex2= /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
	     		} else {
	     			regex2 = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
	     		}
	     		if(regex2.test(idcard)){
	     			$("#birthday").val(getBirthdayFromCnId(idcard));
	     			$("#gender").val(getGenderFromCnId(idcard)=="M"?1:2);
	     			return true;
	     		}
	     		else{
	     			$("#birthday").val("");
	     			return false;
	     		}
	     		break; 
	     	  case 18:
	     	 	 if(regex1.test(idcard)){
	     			var S = (parseInt(idcard[0]) + parseInt(idcard[10])) * 7 + (parseInt(idcard[1]) + parseInt(idcard[11])) * 9 + (parseInt(idcard[2]) + parseInt(idcard[12])) * 10 + (parseInt(idcard[3]) + parseInt(idcard[13])) * 5 + (parseInt(idcard[4]) + parseInt(idcard[14])) * 8 + (parseInt(idcard[5]) + parseInt(idcard[15])) * 4 + (parseInt(idcard[6]) + parseInt(idcard[16])) * 2 + parseInt(idcard[7]) * 1 + parseInt(idcard[8]) * 6 + parseInt(idcard[9]) * 3;
	     			var Y = S % 11;
	     			var M = "F";
	     			var JYM = "10X98765432";
	     			M = JYM.substr(Y, 1);
	     			/*判断校验位*/
	     			if (M == idcard[17].toUpperCase()) {
	     				$("#birthday").val(getBirthdayFromCnId(idcard));
	     				$("#gender").val(getGenderFromCnId(idcard)=="M"?1:2);
	     				return true;
	     			} else {
	     				$("#birthday").val("");
	     				return false;
	     			}
	     		}else{
	     			$("#birthday").val("");
	     			return false;
	     		}
	     		break;
	     	  default:
	     		 $("#birthday").val("");
	     		 return false;
	     	}
	     }, jQuery.validator.format("请输入合法的身份证"));
	     

	  		// 异步请求会员编号是否存在
	        jQuery.validator.addMethod("checkMemberId",function(value,element){
		     	var member_id = $('#member_id').val();
		     	var flag = false;
		     	if (member_id!="") {
		     		$.ajax({
		     			url : "<%=basePath %>/CRM/Customer/checkMemberId.do",
		     			type : "post",
		     			async : false,
		     			data : {"member_id" : member_id},
		     			success : function(data) {
		     				if (data == "true") {
		     					flag=true;
		     				}
		     			}
		     		});
		     	}else{
		     		flag = true;//如果会员编号为空时触发则不校验
		     	}
		     	return flag;
		     }, "会员编号已存在");
	     
	     // 异步请求客户是否存在
	        jQuery.validator.addMethod("checkCustomerRepeate",function(value,element){
	        	var certi_type = $("#certi_type").val();
		     	var certi_no = $("#certi_no").val();
// 		     	var name = $('#name').val();
// 		     	var birthday = $('#birthday').val();
// 		     	var gender = $('#gender').val();
		     	var flag = false;
		     	if(certi_type==""){
		     		return true;//如果证件类型为空时触发则不校验
		     	}
// 		     	if (certi_type!="" && certi_no!="" && name!="" && birthday!="" && gender!="") {
				if (certi_type!="" && certi_no!="") {
		     		$.ajax({
		     			url : "<%=basePath %>/CRM/Customer/checkCustomerIsRepeat.do",
		     			type : "post",
		     			async : false,
		     			data : {
// 		     				"certi_type" : certi_type,"certi_no" : certi_no,"name":name,"birthday":birthday,"gender":gender
		     				"certi_type" : certi_type,"certi_no" : certi_no
		     			},
		     			success : function(data) {
		     				if (data == "true") {
		     					flag=true;
		     				}
		     			}
		     		});
		     	}else{
		     		flag = true;
		     	}
		     	return flag;
		     }, "该客户已存在，请重新录入。");
	     
	        jQuery.validator.addMethod("checkBranchName",function(value,element){
	        	var branch_name = $("#branch_name").val();
		     	if(branch_name=='总公司'){
		     		return false;
		     	}
		     	return true;
		     }, "不能再总公司下添加客户");
		
	        
	       //判断值为空函数
	       function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
	     
	     //表达提交校验
	       var index = 2;
			function addTR(){
				$(this).parents("tr button:gt(0)").remove();
				$('tbody').append("<tr> "
						+"<td><button  onclick='cutTR(this)' id='box"+index+"'  name='box_list'  class='btn btn-mini btn-danger'><i class='icon-remove icon-white'></i>删除</button> </td>"
						+"<td style='display: none'><input type='text' id='index"+index+"'  name='yitiao' class='"+index+"'/></td>"
						+"<td><input style='width:100px;' type='text' id='corporation_contact_person"+index+"' name='corporation_contact_person"+index+"' value='' ></td>"
						+"<td><input style='width:100px;' type='text' id='corporation_contact_mobile"+index+"' name='corporation_contact_mobile"+index+"' value='' ></td>"
						+"<td><input style='width:100px;'  type='text' id='corporation_contact_qq"+index+"'     name='corporation_contact_qq"+index+"'     value='' ></td>"
						+"<td><input style='width:100px;'  type='text' id='corporation_contact_wechat"+index+"' name='corporation_contact_wechat"+index+"' value='' ></td>"
						+"<td><a class='btn btn-mini btn-dangerLight' href='#' onclick='addTR()' ><i class='icon-plus icon-white'></i>新增</a></td>"
						+"</tr>");
				
				//添加行时动态添加校验
			    $("#corporation_contact_person"+index).rules("add",{maxlength :60,required:true }); 
				$("#corporation_contact_mobile"+index).rules("add",{mobilePhone : [],required:true  }); 
				$("#corporation_contact_qq"+index).rules("add",{number:true }); 
				$("#corporation_contact_wechat"+index).rules("add",{maxlength :60  }); 

				index++; 
			}
			//删除一行
			function cutTR(i)
			{
				var id=i.id;
				$("#"+id).parents("tr").remove();	
			}
			
			 //把信息按条拼成字符串并提交
			 function sub(){
				 var customer_type=$("#customer_type").val();
					if(customer_type!="" && customer_type!=null){
						if("1"==customer_type){//法人
						 	 $("input[name='yitiao']").each(function(i,val){
						 		var flag=$(this).attr("class");
						 		var corporation_contact_person=$("#corporation_contact_person"+flag).val();
						 		var corporation_contact_mobile=$("#corporation_contact_mobile"+flag).val();
						 		var corporation_contact_qq=$("#corporation_contact_qq"+flag).val();
						 		var corporation_contact_wechat=$("#corporation_contact_wechat"+flag).val();
					 			$("#index"+flag).val(corporation_contact_person+"#"+corporation_contact_mobile+"#"+corporation_contact_qq+"#"+corporation_contact_wechat);
						 	});
						}
					}
				$("#queryForm").submit();
			}
		 </script>		     
		
			     
	</head>
	<body style="height: 750px">
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户添加</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm"  class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CRM/Customer/addCustomerAndContact.do" method="POST" enctype="multipart/form-data">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>				    
					<fieldset >
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户基本信息</span>
					</div>
					<div class="row">
						 <jsp:include page="../../util/branchTreeRequired.jsp"  flush="true"/>
						  <webTag:DynamicSelectTag src="customerTypeSelect" id="customer_type" name="customer_type"  value='${rmHelper.returnParams.customer_type}' displayLable="客户类型" isdisplay="true" />
					</div>
					</fieldset>
					
					<fieldset id="personBaseInfo">
					<!-- <div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 个人基本信息</span>
					</div> -->
					<div class="row">
						  <webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="客户姓名:"  />
						  <webTag:Text id="title" name="title"  value='${rmHelper.returnParams.title}' displayLable="称呼:" />
                          <webTag:Date id="birthday" name="birthday"  value='${rmHelper.returnParams.birthday}' displayLable="出生日期:" />
					</div>
					 
					<div class="row"> 
                        <webTag:DynamicSelectTag src="certiTypeSelect" id="certi_type" name="certi_type" value='${rmHelper.returnParams.certi_type}' displayLable="证件类型:" />
                        <webTag:Text  id="certi_no" name="certi_no" value='${rmHelper.returnParams.certi_no}' displayLable="证件号码:" />
                        <webTag:Date id="certi_validdate" name="certi_validdate" value='${rmHelper.returnParams.certi_validdate}' displayLable="证件有效期至:" />    
					</div>
					
					<div class="row"> 
						<webTag:DynamicSelectTag src="genderSelect" name="gender" id="gender" displayLable="性别:"  value='${rmHelper.returnParams.gender}' />
                        <webTag:Text id="education" name="education"  value='${rmHelper.returnParams.education}' displayLable="学历:"/>
						<webTag:DynamicSelectTag src="nationalitySelect" id="nationality" name="nationality" value='${rmHelper.returnParams.nationality}' displayLable="国籍:"/>                                  
					</div>
					
					<div class="row">  
                        <webTag:DynamicSelectTag src="nationSelect" id="nation" name="nation" value='${rmHelper.returnParams.nation}' displayLable="民族:" />
                        <webTag:Text id="homeplace" name="homeplace"  value='${rmHelper.returnParams.homeplace}' displayLable="籍贯:"/>
						<webTag:Text id="seat_address" name="seat_address" value='${rmHelper.returnParams.seat_address}' displayLable="户口所在地:"/>                              
					</div>
					
					<div class="row">      
                        <webTag:Text id="height" name="height" value='${rmHelper.returnParams.height}' displayLable="身高(cm):"/>
                        <webTag:Text id="weight" name="weight"  value='${rmHelper.returnParams.weight}' displayLable="体重(kg):"/>
						<webTag:DynamicSelectTag src="politicalSelect" id="political" name="political" value='${rmHelper.returnParams.political}' displayLable="政治面貌:" />
                    </div>
					
					<div class="row">
						 <webTag:DynamicSelectTag src="educationSelect" id="education2" name="education2" value='${rmHelper.returnParams.education2}' displayLable="教育程度:" />
						 <webTag:DynamicSelectTag src="maritalSelect" id="marital_stat" name="marital_stat" value='${rmHelper.returnParams.marital_stat}' displayLable="婚姻状况:" />
						<webTag:DynamicSelectTag src="healthSelect" id="health" name="health" value='${rmHelper.returnParams.health}' displayLable="健康状况:"/>                                
					</div>
					
					<div class="row">    
                        <webTag:DynamicSelectTag src="jobTypeSelect" id="job_type" name="job_type" value='${rmHelper.returnParams.job_type}' displayLable="职业类别:"/>                                    
					 	<webTag:Text id="job_code" name="job_code"  value='${rmHelper.returnParams.job_code}' displayLable="职业:"/>
						<webTag:DynamicSelectTag src="customerIncomTypeSelect" id="income_type" name="income_type" value='${rmHelper.returnParams.income_type}' displayLable="收入区间:"/>                          
					</div>
					
					<div class="row">     
                        <webTag:Text id="bank_code" name="bank_code" value='${rmHelper.returnParams.bank_code}' displayLable="银行编码:"/>
						<webTag:Text id="bank_account_no" name="bank_account_no"  value='${rmHelper.returnParams.bank_account_no}' displayLable="银行账号:"/>
						<webTag:Text id="bank_account_name" name="bank_account_name" value='${rmHelper.returnParams.bank_account_name}' displayLable="银行账户名:"/>   
					 </div>
					
					<div class="row">
						<webTag:DynamicSelectTag src="YNStatusSelect" name="is_telmsgservice" id="is_telmsgservice" value='${rmHelper.returnParams.is_telmsgservice}' displayLable="是否接收短信服务:"></webTag:DynamicSelectTag>
					</div>
					
					<div class="row">     
						<webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' displayLable="备注:"/>
					 </div>
					</fieldset>
					
					<fieldset id="corporationBaseInfo">
					<!-- <div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 法人基本信息</span>
					</div> -->
					<div class="row">
						  <webTag:Text id="company_name"     name="company_name"      value='${rmHelper.returnParams.company_name}' displayLable="单位名称:"  />
						  <webTag:Text id="company_address"  name="company_address"   value='${rmHelper.returnParams.company_address}' displayLable="地址:" />
                          <webTag:Text id="company_telphone" name="company_telphone"  value='${rmHelper.returnParams.company_telphone}' displayLable="电话:" />
					</div>
					 
					<div class="row"> 
                         <webTag:Text  id="company_fax"      name="company_fax"       value='${rmHelper.returnParams.company_fax}' displayLable="传真:" />    
                         <webTag:Text  id="company_mobile"   name="company_mobile"    value='${rmHelper.returnParams.company_mobile}' displayLable="联系方式:" />
                         <webTag:Text  id="company_postcode" name="company_postcode"  value='${rmHelper.returnParams.company_postcode}' displayLable="邮编:" />    
					</div>
					
					<div class="row"> 
                         <webTag:Text id="corporation_represen" name="corporation_represen"  value='${rmHelper.returnParams.corporation_represen}' displayLable="法人代表:"/>
                         <webTag:Text id="company_url"          name="company_url"           value='${rmHelper.returnParams.company_url}' displayLable="公司网址:"/>
                         <webTag:Text id="company_mail"         name="company_mail"          value='${rmHelper.returnParams.company_mail}' displayLable="电子邮件:"/>
					</div>
					
					<div class="row">  
                        <webTag:Text id="company_industry"            name="company_industry"            value='${rmHelper.returnParams.company_industry}' displayLable="行业性质:"/>
                        <webTag:Text id="corporation_represen_qq"     name="corporation_represen_qq"     value='${rmHelper.returnParams.corporation_represen_qq}' displayLable="qq:"/>
						<webTag:Text id="corporation_represen_wechat" name="corporation_represen_wechat" value='${rmHelper.returnParams.corporation_represen_wechat}' displayLable="微信:"/>                              
					</div>

					<div class="row">     
						<webTag:TextareaTag id="company_remark" name="company_remark" rows='3' value='${rmHelper.returnParams.company_remark}' displayLable="备注:"/>
					 </div>
					</fieldset>
					
					<fieldset id="personContactInfo">
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户联系信息</span>
					</div>
					<div class="row">
						<webTag:Text id="address" name="address" value=' ${rmHelper.returnParams.address}' displayLable="家庭地址:"/>
                        <webTag:Text id="zip" name="zip" value='${rmHelper.returnParams.zip}' displayLable="邮政编码:"/>
					</div><!-- /.row -->
					
					<div class="row">                
						<webTag:Text id="mobile" name="mobile" value='${rmHelper.returnParams.mobile}' displayLable="移动电话:"/>
                        <webTag:Text id="fax" name="fax" value='${rmHelper.returnParams.fax}' displayLable="传真电话:"/>
                        <webTag:Text id="telphone" name="telphone" value='${rmHelper.returnParams.telphone}' displayLable="住宅电话:"/>
					</div><!-- /.row -->
					
					
					<div class="row">                
						<webTag:Text id="email" name="email" value='${rmHelper.returnParams.email}' displayLable="电子邮箱:"/>
                        <webTag:Text id="job_com" name="job_com" value='${rmHelper.returnParams.job_com}' displayLable="工作单位名称:"/>
                        <webTag:Text id="job_tel" name="job_tel" value='${rmHelper.returnParams.job_tel}' displayLable="办公电话:"/>
					</div>
					</fieldset>
					
					<fieldset id="corporationContactInfo">
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i>  客户联系信息</span>
					</div>
					<%-- <div class="row">                
						<webTag:Text id="corporation_contact_person" name="corporation_contact_person"   value='${rmHelper.returnParams.corporation_contact_person}' displayLable="联系人:"/>
                        <webTag:Text id="corporation_contact_mobile" name="corporation_contact_mobile"   value='${rmHelper.returnParams.corporation_contact_mobile}' displayLable="联系电话:"/>
                        <webTag:Text id="corporation_contact_qq"     name="corporation_contact_qq"       value='${rmHelper.returnParams.corporation_contact_qq}' displayLable="qq:"/>
					</div>
					<div class="row">                
						<webTag:Text id="corporation_contact_wechat" name="corporation_contact_wechat"   value='${rmHelper.returnParams.corporation_contact_wechat}' displayLable="微信:"/>
					</div> --%>
					
					<div class="overAuto row-fluid">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th></th>
									<th>联系人</th>
									<th>联系电话</th>
									<th>qq</th>
									<th>微信</th>
									<th></th>
								</tr>
							</thead>
							<tbody >
								<tr>
									<td><button style="display:none" onclick="cutTR(this)" id="box1"  name="box_list"  class="btn btn-mini btn-danger"><i class="icon-remove icon-white"></i>删除</button> </td>
									<td style="display: none"><input type="text" id="index1" name="yitiao" class="1"/></td>
									<td><input style="width:100px;" type="text" id="corporation_contact_person1" name="corporation_contact_person" value="" ></td>
									<td><input style="width:100px;" type="text" id="corporation_contact_mobile1" name="corporation_contact_mobile" value="" ></td>
								    <td><input style="width:100px;" type="text" id="corporation_contact_qq1"     name="corporation_contact_qq"     value="" ></td>
									<td><input style="width:100px;" type="text" id="corporation_contact_wechat1" name="corporation_contact_wechat" value="" ></td>
								    <td><a class="btn btn-mini btn-dangerLight" href="#" onclick="addTR()" ><i class="icon-plus icon-white"></i>新增</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</fieldset>
					
					
					<fieldset id="customerValueInfo">
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户价值分析</span>
					</div>
					<div class="row">                
						 <webTag:AttachmentUploadTag   id="attachment" name="attachment"  displayLable="附件上传:" />
					</div> 
				    <div class="row" style="text-align:right;">
					    		<button id="butt" type="submit" class="btn btn-danger" onclick="sub();"><i class="icon-inbox icon-white"></i>保存</button>
					    		<%-- <a class="btn btn-danger"  href="<%=basePath%>/CRM/Customer/toAddAndModifyCustomer.do"><i class="icon-share-alt icon-white"></i>返回</a> --%>
					</div>
				  </fieldset>
				  	
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
