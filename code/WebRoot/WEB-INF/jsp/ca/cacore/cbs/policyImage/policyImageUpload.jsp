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
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean-ZM.css" >
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
 		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		
		<!-- jQuery 1.7.2 -->
		<script src="<%=basePath%>/resources/ca/cacore/uploadify/jquery-1.7.2.min.js" type="text/javascript"></script>
		<!-- uploadify start -->
		<link href="<%=basePath%>/resources/ca/cacore/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resources/ca/cacore/uploadify/uploadimg.css" rel="stylesheet" type="text/css" />
    	<script src="<%=basePath%>/resources/ca/cacore/uploadify/swfobject.js" type="text/javascript"></script>
    	<script src="<%=basePath%>/resources/ca/cacore/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
    	<!-- uploadify end -->
    			
		<!-- jQuery.validate start-->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/compent/newtouch/util/validation.css">	
	    <script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/newtouch-validate.js"></script>
		<!-- jQuery.validate end  -->
		<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>


		<script >

		$(window).unload(function(){ //解决ie调试报错 “__flash__removeCallback”未定义 问题
			$('#uploadimage').remove();
		});
		
		$(function() {
			
//		 		子页面控制父页面高度开始
			  	var frameId = "#mainIframe";
				var frameW= 1060;//定义页面宽度
				var option = {
						FrameId : frameId,
						FrameW : frameW,
					};
				$(window).frameWH(option);//控制页面宽度
//		 		子页面控制父页面高度结束

			
			$("#queryForm").validate({  //为queryForm表单配置validate
        		onkeyup:false,
        		rules : {
    				p_policy_code:{
    			        checkPolicyId : []
    				},
    				c_policy_code:{
    			        checkPolicyId : []
    				}
    			}
        	});
			
			//校验样式效果,文本框获取焦点,异常相应报错信息
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
			
			//对上传类型的onchange事件
			$("#type").change(function(){
				checkPolicyType()
			});
			
		});	
			jQuery.validator.addMethod("checkPolicyId", function(value, element, param)  {
				var flag = false;
				var insBranch_id = $('#insBranch_id').val();
				var type = $('#type').val();
				if(insBranch_id !="" && type!="" && value != ""){
					$.ajax({
			     		url:"<%=basePath %>/CBS/policyImage/validateBranchAndPolicy.do",
			     		type:"post",
			     		async: false,
			     		data:{"policy_code":value,"insBranch_id":$('#insBranch_id').val(),"type":$('#type').val()},
			     		success:function(data){
			     			if(data!=''){
			     				//拼接字符串,投保单号码^保单id^机构编号^机构名称^投保人姓名
			     				var info= new Array(); 
			     				info = data.split("^");
			     			 	$('#send_code').val(info[0]);
			     				$('#policy_id').val(info[1]);
			     				$('#branch_id').val(info[2]);
			     				$('#branch_name').val(info[3]);
			     				$('#app_name').val(info[4]);
			     				flag=true;
			     			}else{
			     				$('#send_code').val("");
			     				$('#policy_id').val("");
			     				$('#branch_id').val("");
			     				$('#branch_name').val("");
			     				$('#app_name').val("");
			     			}
			     		}
			     	});
			     	return flag;
				}
	        	
		     	return true;
		     }, "输入的所属保单号错误，请重新输入");
			
			
		
		//校验上传类型->必输项,并且会控制其他文本框的显示及值的预览
		function checkPolicyType(){
			var type = $('#type').val();
			var c_policy_code = $('#c_policy_code');  //保单号
			var p_policy_code = $('#p_policy_code');  //投保单号
			$('#branch_id').val(''); //清空各值
			$('#branch_name').val(''); 
			$('#app_name').val(''); 
			c_policy_code.val(""); //清空保单号的值
			p_policy_code.val(""); //清空投保单号的值
			if(type=='contract'){ //选择保单影像件上传
				$('#bus_type').val('2'); //表示影像件是保单
				c_policy_code.attr("readonly",false);   
				p_policy_code.attr("readonly",true);
				c_policy_code.parent().parent().children('label').append("<font>(<font color=red>*</font>):</font>"); //添加必填红星
				c_policy_code.rules("add","required"); //添加必选校验
				p_policy_code.rules("remove","required");  //删除必选校验
				p_policy_code.parent().parent().children('label').children('font').remove(); //删除必填红星
				
			}else if(type=='policy'){
				$('#bus_type').val('1'); //表示影像件是投保单
				p_policy_code.attr("readonly",false);
				c_policy_code.attr("readonly",true);
				p_policy_code.rules("add","required");  //添加必选校验
				c_policy_code.rules("remove","required"); //删除必选校验
				p_policy_code.parent().parent().children('label').append("<font>(<font color=red>*</font>):</font>"); //添加必填红星
				c_policy_code.parent().parent().children('label').children('font').remove(); //删除必填红星
			}else{
				$('#bus_type').val(''); 
				c_policy_code.val(""); //清空保单号的值
				p_policy_code.val(""); //清空投保单号的值
				p_policy_code.attr("readonly",false);
				c_policy_code.attr("readonly",false);
				p_policy_code.rules("remove","required");  //删除必选校验
				c_policy_code.rules("remove","required");  //删除必选校验 
				p_policy_code.parent().parent().children('label').children('font').remove(); //删除必填红点
				c_policy_code.parent().parent().children('label').children('font').remove(); //删除必填红点
			}
	     	
	     };
	     
	     //次js函数未被使用,预留中 //返回时校验是否有影响没有进行处理
	     function checkImage(){
	 		
	 		var length = $("form").has("img").length
	 		if(length>0){
	 			var boo= confirm("返回将删除页面未关联影像,是否确定?");
	 			if(boo){
	 				
	 				var file_id = "";
	 				var index = 0;
	 				var flag= false;
	 				$("img").each(function(){
	 					file_id+= this.alt+",";
	 				
	 				});
	 				
	 				$.ajax({
	 		     		url : "<%=basePath %>/CBS/policyImage/deletePolicyImageForBack.do",
	 		     		type : "post",
	 		     		async : false,
	 		     		data : {"file_id": file_id},
	 		     		success : function(data) {
	 		     				if(data =="1"){		//1表示删除成功
	 		     				 flag = true;
	 		     				}
	 		     			}
	 		     	});
	 		      if(flag){//如果正常删除则跳转界面
	 		    	  location.href="<%=basePath%>/redirect/redirect.do?linkUrl=ca/cacore/main"; 
	 		      }else{   //若未正常删除则提示出错,并让手动删除
	 		    	  alert("未关联影像删除出错,请手动删除");
	 		      }			
	 			}
	 			
	 		 }else{
	 			 location.href="<%=basePath%>/redirect/redirect.do?linkUrl=ca/cacore/main"; 
	 			}
	 	}	
		
	//保存时校验是否有相应的影像信息
	function checkSubmit(){
		//表达提交校验
     		if($('#queryForm').valid()){
     			var length = $("form").has("img").length
     			if(length >　0){
     				return true;
     			}else{
     				alert("没有找到影像信息,不能进行保存操作");
     				return false;
     			}
     			return true;
     		}else{
     			return false;
     		}
		
	}
     </script>	
        
	</head>
	<body>
	   
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			   <span class=mrl14><i class="icon-list icon-red"></i> 影像管理</span><span class="divider">/</span>
			   <span><i class="icon-list icon-red"></i> 保单影像件上传</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id= "queryForm" onsubmit="return checkSubmit();" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CBS/policyImage/concernPolicyImage.do" method="POST">
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
					<webTag:HiddenInputTag id="send_code" name="send_code" value='${rmHelper.returnParams.send_code}'/>
					<webTag:HiddenInputTag id="policy_id" name="policy_id" value='${rmHelper.returnParams.policy_id}'/>
					<webTag:HiddenInputTag id="bus_type" name="bus_type" />
					<fieldset>
						<div class="row">
							<webTag:DynamicSelectTag  src="insBranchSelect" name="insBranch_id" id="insBranch_id" value='${returnHelper.returnParams.insBranch_id}' displayLable="保险公司机构" isdisplay="true" iclass="required"></webTag:DynamicSelectTag>
							<webTag:Select id="type" name="type" value='${rmHelper.returnParams.bank_code}' displayLable="上传类型" isdisplay="true" iclass="required">
                            	<webTag:Option  value="policy" displayLable="投保单影像件上传"/>
                            	<webTag:Option  value="contract" displayLable="保单影像件上传"/>
							   	
                        	</webTag:Select>  
                        	<webTag:Text id="p_policy_code" name="p_policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="所属投保单号"/>
						</div>
						<div class="row">
							<webTag:Text id="c_policy_code" name="c_policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="所属保单号"/>
							<webTag:Text id="branch_id"  name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码:" readonly="true"/>
							<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="机构名称:" readonly="true"/>							
						</div>
						<div class="row">
							<webTag:Text id="app_name" name="app_name" value='${rmHelper.returnParams.app_name}' displayLable="投保人:" readonly="true"/>
						</div>
						
						<div id="uploadimage" style="width:100%;">
							<jsp:include page="../policyImage/imageUpload.jsp"></jsp:include>
						</div>
						
					    <div class="row" style="text-align:right;margin-top:10px;">
				    		<button type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
							<a class="btn btn-danger" href="<%=basePath%>/redirect/redirect.do?linkUrl=ca/cacore/main"><i class="icon-share-alt icon-white"></i>返回</a>
						</div>
					</fieldset>
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>

