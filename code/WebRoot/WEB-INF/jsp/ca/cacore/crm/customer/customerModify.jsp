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
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<!-- 附件修改操作js -->
		<script type="text/javascript" src="<%=basePath%>/compent/default/js/attachmentModify.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/ckeditor/ckeditor.js"></script>
		<script >
		var certi_no_flag =${rmHelper.returnParams.certi_no} ; //修改时接收后台传给页面的值
		$(function() {
//			表单校验
			$("#queryForm").validate({
    			rules : {
    				branch_id:{
    					required:true
    				},
    				branch_name:{
    					required:true
    				},
    				customer:{
    					required:true
    				},
    				member_id : {
    					checkMemberId : []
    				},
    				name : { //姓名
    					required : true,
    					maxlength :50
//     					,checkCustomerRepeate : []
    				},
    				title : { //称呼
    					maxlength :50
    				},
    				customer_type : {  //客户类型
    					required : true
    				},
    				gender : {  //性别
    					//required : true,
//     					checkCustomerRepeate : []
    				},
    				birthday : {  //生日
    					//required : true,
    					checkBirthday : []
//     					,checkCustomerRepeate : []
    				},
    				certi_type : { //证件类型
    					required : true,
    					checkCustomerRepeate : []
    				},
    				certi_no : {  //证件号码
    					required : true,
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
    				}
    			},
    			messages:{
    				fax:"请输入正确的传真电话",
    				telphone:"请输入正确的住宅电话",
    				job_tel:"请输入正确的办公电话"
			 	},
    			onkeyup:false,
    			
    		});
			//校验结束
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
		     	var member_flag = $('#member_flag').val(); //页面 加载时获取会员编号的值 用于提交表单时进行比较值是否改变
		     	var flag = false;
		     	if(member_id == member_flag){//如果更新界面带出的会员编号值未改变,则不进行校验
		     		return true;
		     	}
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
	        	var seq_id = $("#customer_seq_id").val();
// 	        	var name = $('#name').val();
// 		     	var birthday = $('#birthday').val();
// 		     	var gender = $('#gender').val();
	        	if(certi_type==""){
	        		return true; //如果证件类型为空时触发则不校验
	        	}
	        	var flag = false;
// 	        	if(certi_no != certi_no_flag){  //更改时如果修改的证件号码不等于修改带入的证件号码则需要进行比较
	        		// 	        		if (certi_type!="" && certi_no!="" && seq_id!=""  && name!="" && birthday!="" && gender!="") {
	        		if (certi_type!="" && certi_no!="" && seq_id!="") {	
			     		$.ajax({
			     			url : "<%=basePath %>/CRM/Customer/updateCheckCustomerIsRepeat.do",
			     			type : "post",
			     			async : false,
			     			data : {
// 			     				"certi_type" : certi_type,"certi_no" : certi_no,"seq_id" : seq_id,"name":name,"birthday":birthday,"gender":gender
			     				"certi_type" : certi_type,"certi_no" : certi_no,"seq_id" : seq_id
			     			},
			     			success : function(data) {
			     				if (data == "true") {
			     					flag=true;
			     				}
			     			}
			     		});
			     	}else{
			     		flag = true; //等待五要素输入后才进行校验
			     	}
// 	        	}else{
// 	        		flag=true; //表示身份证号码未改变
// 	        	}
	        	if (flag) {//如果证件类型和证件号码不存在，去掉校验
			     	$("label:contains('客户已存在，请重新录入。')").remove();
			     }
		     	return flag;
		     }, "客户已存在，请重新录入。");
	     
	     
	       //判断值为空函数
	       function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
	       
	       //将字符串型格式yyyy-MM-dd转换成日期类型
	       function changeStringToDate(date){
		     	return new Date(Date.parse(date.replace(/-/g,"/")));
		     }
	     
	   	//表单提交校验
	     	function checkSubmit(){
	     		if($('#queryForm').valid()){
	     			return true;
	     		}else{
	     			return false;
	     		}
	     	}
	     
		 </script>		     
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户信息维护</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户信息修改</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" onsubmit="return checkSubmit();" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CRM/Customer/modifySave.do"  enctype="multipart/form-data" method="POST">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>
					<%-- <webTag:HiddenInputTag id="customer_id" name="customer_id" value='${rmHelper.returnParams.customer_id}'/> --%>
				    <webTag:HiddenInputTag name="customer_seq_id" id="customer_seq_id" value='${rmHelper.returnParams.customer_seq_id}'></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag name="customercontact_seq_id" id="customercontact_seq_id" value='${rmHelper.returnParams.customercontact_seq_id}'></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag id="file_name" name="file_name" value="${rmHelper.returnParams.file_name}"/>
					<webTag:HiddenInputTag id="file_id" name="file_id" value="${rmHelper.returnParams.file_id}"/>
					<webTag:HiddenInputTag id="file_flag" name="file_flag" value="${rmHelper.returnParams.file_flag}"/>
				   	<webTag:HiddenInputTag id="delete_file" name="delete_file" value=''/>
				   	<webTag:HiddenInputTag   id="certi_no_hidden" name="certi_no_hidden" value='${rmHelper.returnParams.certi_no}'/>
				   	<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
				    
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户基本信息</span>
					</div>
					<div class="row">
						  <webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码" readonly="true" isdisplay="true"/>
						  <webTag:Text id="branch_name" name="branch_name"  value='${rmHelper.returnParams.branch_name}' displayLable="机构名称" readonly="true" isdisplay="true"/>
						  <webTag:Text id="customer_id" name="customer_id"  value='${rmHelper.returnParams.customer_id}' displayLable="客户代码" readonly="true" isdisplay="true"/>
					</div><!-- /.row -->
				<%-- 	<div class="row">
						  <webTag:Text  id="member_id" name="member_id" value='${rmHelper.returnParams.member_id}' displayLable="会员编号:" />
						  <webTag:HiddenInputTag name="member_flag" id="member_flag" value='${rmHelper.returnParams.member_id}'></webTag:HiddenInputTag>
					</div> --%>
					<div class="row">
						  <webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="客户姓名:"  />
						  <webTag:Text id="title" name="title"  value='${rmHelper.returnParams.title}' displayLable="称呼:" />
						  <webTag:DynamicSelectTag src="customerTypeSelect" id="customer_type" name="customer_type"  value='${rmHelper.returnParams.customer_type}' displayLable="客户类型:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true" />
					</div><!-- /.row -->
					<div class="row">
						  <webTag:DynamicSelectTag src="certiTypeSelect" id="certi_type" name="certi_type" value='${rmHelper.returnParams.certi_type}' displayLable="证件类型:" />
						  <webTag:Text  id="certi_no" name="certi_no" value='${rmHelper.returnParams.certi_no}' displayLable="证件号码:" />
						  <webTag:Date id="certi_validdate" name="certi_validdate" value='${rmHelper.returnParams.certi_validdate}' displayLable="证件有效期至:" />
					</div><!-- /.row -->
					
					<div class="row">                                    
                       	<webTag:DynamicSelectTag src="genderSelect" name="gender" id="gender" displayLable="性别:" value='${rmHelper.returnParams.gender}' />
                        <webTag:Date id="birthday" name="birthday"  value='${rmHelper.returnParams.birthday}' displayLable="出生日期:" />
                        <webTag:Text id="education" name="education"  value='${rmHelper.returnParams.education}' displayLable="学历:"/>
					</div><!-- /.row -->
					<div class="row">                                    
                        <webTag:DynamicSelectTag src="nationalitySelect" id="nationality" name="nationality" value='${rmHelper.returnParams.nationality}' displayLable="国籍:"/>
                        <webTag:DynamicSelectTag src="nationSelect" id="nation" name="nation" value='${rmHelper.returnParams.nation}' displayLable="民族:" />
                        <webTag:Text id="homeplace" name="homeplace"  value='${rmHelper.returnParams.homeplace}' displayLable="籍贯:"/>
					</div><!-- /.row -->

					<div class="row">                                    
                        <webTag:Text id="seat_address" name="seat_address" value='${rmHelper.returnParams.seat_address}' displayLable="户口所在地:"/>
                        <webTag:Text id="height" name="height" value='${rmHelper.returnParams.height}' displayLable="身高(cm):"/>
                        <webTag:Text id="weight" name="weight"  value='${rmHelper.returnParams.weight}' displayLable="体重(kg):"/>
					</div><!-- /.row -->
					
					<div class="row">                                    
                        <webTag:DynamicSelectTag src="politicalSelect" id="political" name="political" value='${rmHelper.returnParams.political}' displayLable="政治面貌:" />
                   		<webTag:DynamicSelectTag src="educationSelect" id="education2" name="education2" value='${rmHelper.returnParams.education2}' displayLable="教育程度:" />
                   		<webTag:DynamicSelectTag src="maritalSelect" id="marital_stat" name="marital_stat" value='${rmHelper.returnParams.marital_stat}' displayLable="婚姻状况:" />
                    </div><!-- /.row -->
					
					<div class="row">
						 <webTag:DynamicSelectTag src="healthSelect" id="health" name="health" value='${rmHelper.returnParams.health}' displayLable="健康状况:"/>                                    
						 <webTag:DynamicSelectTag src="jobTypeSelect" id="job_type" name="job_type" value='${rmHelper.returnParams.job_type}' displayLable="职业类别:"/> 
						 <webTag:Text id="job_code" name="job_code"  value='${rmHelper.returnParams.job_code}' displayLable="职业:"/>
					</div><!-- /.row -->
					
					<div class="row">                                    
                         <webTag:DynamicSelectTag src="customerIncomTypeSelect" id="income_type" name="income_type" value='${rmHelper.returnParams.income_type}' displayLable="收入区间:"/>
                         <webTag:Text id="bank_code" name="bank_code" value='${rmHelper.returnParams.bank_code}' displayLable="银行编码:"/>
						 <webTag:Text id="bank_account_no" name="bank_account_no"  value='${rmHelper.returnParams.bank_account_no}' displayLable="银行账号:"/>                                   
					</div><!-- /.row -->
					
					<div class="row">                                    
                        <webTag:Text id="bank_account_name" name="bank_account_name" value='${rmHelper.returnParams.bank_account_name}' displayLable="银行账户名: "/>
						<webTag:DynamicSelectTag src="YNStatusSelect" name="is_telmsgservice" id="is_telmsgservice" value='${rmHelper.returnParams.is_telmsgservice}' displayLable="是否接收短信服务:"/>
					</div><!-- /.row -->
					<div class="row">
						<webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' displayLable="备注:"/>
					</div><!-- /.row -->
					</fieldset>
										
					<!-- 客户联系信息 -->
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户联系信息</span>
					</div>
					<div class="row">
						<webTag:Text id="address" name="address" value='${rmHelper.returnParams.address}' displayLable="家庭地址:"/>
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
					</div><!-- /.row -->
				  </fieldset>	
				  <!-- 客户价值分析 -->
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户价值分析</span>
					</div>
					<div class="row">
						<!-- <label class="control-label" for="uploadify">客户价值分析:</label> -->
                   		<!-- <div style="width: 700px;margin-left:158px;">
                        	<input type="file" name="uploadify"id="uploadify" />
							<div id="fileQueue"></div>
                   		</div> -->
                   	</div>	
                   				<webTag:AttachmentUpdateTag   id="attachment" name="attachment" value="${rmHelper.returnParams.file_name}" basePath="<%=basePath %>" uploadLable="附件上传:" displayLable="附件下载:"/>				
                   	<div class="row" style="text-align:right;">
					    <button id="submitBtn" type="submit" onclick="submitFormForFile('<%=basePath %>');" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
					    <a class="btn btn-danger" id="backButton" href="<%=basePath %>/CRM/Customer/goQueryAndModify.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
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
