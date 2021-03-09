
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%
String path = request.getContextPath();
String pathjs = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>中介公司核心系统</title>
		
		
		
		
		
		
		<!--  收缩 -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean-ZM.css" >
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/newtouch/util/validation.css">
		
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
		<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>	
		<!-- 保存完成提示框 -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >
		<script type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jqueryui/ui/jquery-ui.js"></script>
        
        <script type="text/javascript" src="<%=pathjs%>/compent/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=pathjs%>/compent/base.js"></script>
        <script type="text/javascript" src="<%=pathjs%>/compent/pagination/com.newtouch.pagination.js"></script>
        <script type="text/javascript" src="<%=pathjs%>/compent/grid/com.newtouch.grid.js"></script>
		<script type="text/javascript" src="<%=pathjs%>/compent/msg/com.newtouch.message.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
		<script type="text/javascript" src="<%=pathjs%>/compent/util.js"></script>
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if IE]>
		<script type="text/javascript" src="<%=pathjs%>/compent/html5shiv/html5shiv.js"></script>
		<script type="text/javascript" src="<%=pathjs%>/compent/bootstrap/js/respond.min.js"></script>
		<![endif]-->
		
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >
     	<!-- 保存完成提示框 -->
       	<script type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jqueryui/ui/jquery-ui.js"></script>
		<style>
		  .my-dialog .ui-dialog-titlebar-close{
		    display: none;
		  }
		 </style> 
		<script type="text/javascript">
			$(document).ready(function() {
				var frameId = "#mainIframe";
				var frameW= 1060;//定义页面宽度
				var option = {
						FrameId : frameId,
						FrameW : frameW,
					};
				$(window).frameWH(option);//控制页面宽度
				$("#subButton").gobackTop();  
				$("#backButton").gobackTop();  
				//伸缩 及功能按钮控制
				
				$('#opt_name').blur("blur",function () //失去焦点时触发的事件,判断是不是用户名与登录用户名一致
			            { 
							      if ($('#opt_name').val() == 
								          $('#opt_name_login').val()) {
			                      $('#error').html('<font color="red">对不起,不能修改自己的用户密码!</font>');
			               }else {
			                      $('#error').html('');
			               } 
			             });
			});
				
		</script>
		<script>
			/*
		      *判断参数是否为未定义或null,如未定义或为null返回true
		      *@param paraValue 待处理字符串
		      *@return true或者false
		      */
		     function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		     }
			 
			 function toBack(){
				 document.getElementById("mainForm").action="<%=basePath %>/AMS/userMgmtController/toLogin.controller";
				  document.getElementById("mainForm").submit();
			 }
			 
			 function save(){
				 var opt_password=$("#opt_password").val();
				 var opt_conf_password=$("#opt_conf_password").val(); 
			 }
			 
			//校验两次密码是否输入一直
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
			//校验
				$(document).ready(function(){
					var validator = $("#mainForm").validate({
		    			rules : {
		    				opt_password : {
		    					required:true,
		    					checkPassword:[],
		    					checkRepeatPassWord:[]
					           },
					       opt_conf_password : {
			    					required:true,
			    					checkPassword:[]
						           }
		    			},
		    			messages:{
		    			}
		    		});
					$("#subButton").click(function(){
						if(validator.form()){
							document.getElementById("#mainForm").submit();
						}
					});
				});
				//校验样式效果,文本框获取焦点,隐藏该文本框相应报错信息
		   		 $("#mainForm").find("input").each(function(){
		           		$(this).click(function(){
		           			var _this=$(this);
		           			if(_this.hasClass("error")){
		           				_this.removeClass("error");
		           				var labelAR = _this.parent().find("label[class='error']");
		           				labelAR.remove();
		           			}
		           		});
		           	});
		</script>
		<script>
			$(document).ready(function() {
				  $(function() {
			     	    //dialog 信息
			     	    var flag=$("#result_flag").val();
			     	    $("#dialog").dialog({
			     	        autoOpen:false,
			     	        buttons:[{
			     	            text:"确定",
			     	            click:function(){
			     	                	$("#dialog").dialog("close");
			     	                	if(flag=="true"){
			     	                		window.parent.document.getElementById("exitBtn").click(); 
			     	                	}
			     	                }
			     	            }
			     	            ],
			     	            position:"top",//弹出位置
			     	            modal:true,
			     	            width:345, //窗口宽度
			     	            height:280,
			     	            dialogClass: "my-dialog",
			     	            closeText:false,
			     	            drag:function(){
			     	                
			     	            }

			     	        });
			     		 var result_flag=$("#result_flag").val();
			     		 var msg=$("#msg").val();
			     		 var path=$("#path").val();
			     		var src_cg=path+"/compent/charisma/img/checkmark.png";
			     		var src_sb=path+"/compent/charisma/img/cross.png";
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
				  
				  
				  
				  $("#mainForm").validate({
		    			rules : {
		    				opt_password : {
		    					required : true,
		    					maxlength : 128
		    				},
		    				opt_conf_password : {
		    					required : true,
		    					maxlength : 128
		    				}
		    			},
		    			onkeyup:false 
		    		});
	          });
			
			
			 /*
		      *判断参数是否为未定义或null,如未定义或为null返回true
		      *@param paraValue 待处理字符串
		      *@return true或者false
		      */
		     function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		     }
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>密码重置 </span>
			</div>
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/ModifyUserPassword.do" method="POST">
					<!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.returnMsg.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag name="opt_no1" id="opt_no1"  value='${opt_no1}'/>
					<%-- <div class="row">
						<webTag:Text id="opt_no" name="opt_no" value='' displayLable="用户工号:"  />
						<div style="padding-left: 70px;padding-top: 8px;"  id='error'></div>
					</div>
					<div class="row">
						<webTag:Text id="opt_name" name="opt_name" value='' displayLable="用户姓名:"  />
						<div style="padding-left: 70px;padding-top: 8px;"  id='error'></div>
					</div> --%>
					<div class="row">
						<webTag:PasswordTag id="opt_password" name="opt_password" value='' displayLable="新密码:"/> <%-- ${rmHelper.returnParams.opt_password} --%>
						<div style="padding-left: 70px;padding-top: 8px;"  id='error_p'></div>
					</div>
					<div class="row">
						<webTag:PasswordTag id="opt_conf_password" name="opt_conf_password" value='' displayLable="新密码确认:" /><%-- ${rmHelper.returnParams.opt_conf_password} --%>
						<div style="padding-left: 70px;padding-top: 8px;"  id='error_c'></div>
					</div>
					<div class="row" style="text-align:right;">
				       <button id="subButton" type="submit" class="btn btn-danger" onclick="save()"><i class="icon-inbox icon-white" ></i>保存</button>
				       <!-- </span> <span><button id="fanhui" type="button" name="goUserMgmtQueryPage.do" class="btn btn-danger"><i class="icon-inbox icon-white" ></i>返回</button> </span> -->
					   <a id="backButton" class="btn btn-danger"
						href="<%=basePath %>/goUserMgmtQueryPage.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
