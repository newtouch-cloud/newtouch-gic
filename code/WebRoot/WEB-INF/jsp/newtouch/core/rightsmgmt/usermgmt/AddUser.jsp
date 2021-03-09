<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>新致金保通</title>
	<jsp:include page="/WEB-INF/jsp/newtouch/core/pub/jvbasecss.jsp" flush="true" />
	<jsp:include page="/WEB-INF/jsp/newtouch/core/pub/jvbasejs.jsp" flush="true" />
	<!-- 回跳 -->
 	<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true" />
 </head>

<script type="text/javascript">


	jQuery.validator.addMethod("checkEmail",function(value,element){
	var emailTest = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	var email = $("#opt_mail").val();
	var flag = true;
	if(email!=""&&!emailTest.test(email)){
		/* alert("邮箱格式错误");
		$("#opt_mail").val("");
		$("#opt_mail").focus(); */
		flag=false;
	}
	if(!flag){
    	return false;
    }else{
    	$("label:contains('邮箱格式错误')").remove();
    	return true;
    }
   	},"邮箱格式错误"); 
	jQuery.validator.addMethod("checkPassword",function(value,element,param){
   	 var opt_password=$("#opt_password").val();
		 var opt_conf_password=$("#opt_conf_password").val();
    	if(opt_password!="" && opt_conf_password!=""){
    		if(opt_password!=opt_conf_password){
    			return false;
    		}
    	}
    	$("label:contains('两次密码不一致，请重新输入。')").remove();
    	return true;
    },"两次密码不一致，请重新输入。");
	
	

$(document).ready(function() {
	$("#queryForm").validate({
		rules : {
			branch_name : {
				required : true,
				maxlength :100,
				
			},
			opt_sex : {
				required : true,
				maxlength :10,
			},
			opt_password : {
				required : true,
				minlength : 6,
				maxlength :25
			},
			opt_conf_password :
				{
					required:true,
					minlength: 6,
					maxlength :25,
					checkPassword:[]
				},
				opt_type :
				{
					required:true,
					maxlength:10
				},
			opt_no: {
				required : true,
				maxlength :25
			},
			opt_name: {
				required : true,
				maxlength :25
			},
			opt_mail : {
				required : false,
				maxlength :30,
				checkEmail : []
			}
			
		},
		onkeyup:false
	});
});  		
	 		

</script>
<body  >

	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i>权限管理</span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>
				用户管理</span><span class="divider"></span> <span><i
				class="icon-list icon-red"></i>添加</span>
		</div>
		<!-- 面包屑导航  end -->
	
	
	      <div class="row-fluid">
	         <form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/doUserMgmtAddUser.do" method="POST">
	         		<%-- <webTag:ReturnMsgTag id="msg" name="msg" successflag='${msg.successflag}' msg='${msg.msgStr}' />
	         		 --%>
	         		 <!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>	
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag name="seq_id" id="seq_id" value="${rmHelper.returnParams.seq_id}"></webTag:HiddenInputTag>
	         		<%-- <div id="dialog" title="提示信息" style="display: none">
						<center>
							<image id="dialog_img"></image>
						</br><span>${rmHelper.returnMsg.msgStr}</span></center>
					</div> --%>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.returnMsg.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.returnMsg.msgStr}"    displayLable="msg信息"/>
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag" value="${rmHelper.returnParams.flag}"></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="removeflag" id="removeflag" value="${removeflag}"></webTag:HiddenInputTag>	
					
				 
					
				<fieldset>
				<div class="row" >
					<jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree.jsp" flush="true" />
					<webTag:Text id="opt_no" name="opt_no" value='${rmHelper.returnParams.opt_no}' displayLable="用户代码" onblur="getInfo()"
						 isdisplay="true"></webTag:Text>
					<webTag:Text id="opt_name" name="opt_name" value='${rmHelper.returnParams.opt_name}'
						displayLable="用户姓名"  isdisplay="true"></webTag:Text>	
				</div>
				
				<div class="row">
					
					<webTag:Select name="opt_sex" id="opt_sex" value='${rmHelper.returnParams.opt_sex}' displayLable="用户性别" isdisplay="true" >
						<webTag:Option value="1" displayLable="男"></webTag:Option>
						<webTag:Option value="2" displayLable="女"></webTag:Option>
					</webTag:Select>
					<webTag:PasswordTag id="opt_password" name="opt_password" value='${rmHelper.returnParams.opt_password}' 
					displayLable="用户密码" isdisplay="true"></webTag:PasswordTag>
					<webTag:PasswordTag id="opt_conf_password" name="opt_conf_password" value='${rmHelper.returnParams.opt_password}'
						displayLable="确认密码" isdisplay="true"></webTag:PasswordTag>
				</div>
				
				<div class="row" >
					<webTag:Select name="opt_type" id="opt_type" value='${rmHelper.returnParams.opt_type}' displayLable="用户类型" isdisplay="true">
						<webTag:Option value="1" displayLable="系统管理员"></webTag:Option>
						<webTag:Option value="2" displayLable="普通用户"></webTag:Option>
					</webTag:Select>
					<webTag:Text id="opt_mail" name="opt_mail" value='${rmHelper.returnParams.opt_mail}' displayLable="电子邮件:" 
						  onblur="checkEmail()"></webTag:Text>
					<webTag:Text id="opt_phone" name="opt_phone" value='${rmHelper.returnParams.opt_phone}' displayLable="手机号码:" ></webTag:Text>
				</div>
				<div class="row" style="text-align:right; margin-right:40px">
	            <span>
	            <button type="submit"  class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button></span>
	          	<a id="backButton" class="btn btn-danger"
						href="<%=basePath %>/goUserMgmtQueryPage.do"><i class="icon-share-alt icon-white"></i>返回</a>
	          </div>
			</fieldset>
	          
	          
	        </form>
	        
	      </div>
	</div>
	<div class="zeoBottomH90"></div>
</body>
<script type="text/javascript">
$(document).ready(function() {
var a= $(window.parent.document).find("#sidebar").height();
	a=a+150;
	$(window.parent.document).find("#ffame").css("height",""+a+"px");
});

function getInfo(){
	var opt_no = document.getElementById("opt_no").value;
	$.ajax({
		  type: "GET",
		  url: "<%=basePath%>/getInfo.do",
		  data: {"opt_no":opt_no
			  },
		  dataType:"json",
		  success: function(data){
			  document.getElementById("branch_id").value = data.branch_id;
			  document.getElementById("branch_name").value = data.branch_name;
			  document.getElementById("opt_name").value = data.opt_name;
			  document.getElementById("opt_sex").value = data.opt_sex;
			  document.getElementById("opt_mail").value = data.opt_mail;
			  document.getElementById("opt_phone").value = data.opt_phone; 
		  },
		});
	}
</script>
</html>