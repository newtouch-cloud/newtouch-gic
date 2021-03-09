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
		<title>新致金保通</title>
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
		 
		
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<%-- <jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>  --%>
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
				 document.getElementById("mainForm").target="_parent";
				 document.getElementById("mainForm").submit();
			 }
			 
			
			 
			//校验日期的先后顺序正确性
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
			
		       //检查密码是否与原密码一致 
				jQuery.validator.addMethod("checkRepeatPassWord",function(value,element){
					 var flag = true;
					 var opt_password=$("#opt_password").val();
					 var opt_no=$("#opt_no").val();
					 if(!isUndefined(opt_password) &&  !isUndefined(opt_no)){
						 $.ajax({
					     		url : "<%=basePath %>/AMS/userMgmtController/checkRepeatPassWord.controller",
					     		type : "post",
					     		async : false,
					     		dataType : "json",
					     		data : {"opt_password": opt_password,"opt_no":opt_no},
					     		success : function(data) {
					     			if("2"==data){
										flag=true;
				                    }else{
				                 	   flag=false;
				                    }
					     		}
					     	});
					     return flag;
					 }else{
				      		return true;
				      	}
				},"新密码不能与原密码一致，请重新输入。");
				//检查密码是否与原密码一致 
				jQuery.validator.addMethod("checkoldPassWord",function(value,element){
					 var flag = true;
					 var old_password=$("#old_password").val();
					
					 var opt_no=$("#opt_no").val();
					
					 if(!isUndefined(old_password) &&  !isUndefined(opt_no)){
						 
						 $.ajax({
							
					     		url : "<%=basePath %>/AMS/userMgmtController/checkRepeatPassWord.controller",
					     		type : "post",
					     		async : false,
					     		dataType : "json",
					     		data : {"opt_password": old_password,"opt_no":opt_no},
					     		success : function(data) {
					     			if("1"==data){
										flag=true;
				                    }else{
				                 	   flag=false;
				                    }
					     		}
					     	});
					     return flag;
					 }else{
				      		return true;
				      	}
				},"旧密码不正确，请重新输入。");
			 
			//校验
				$(document).ready(function(){
					var validator = $("#mainForm").validate({
		    			rules : {
		    				old_password : {
		    					required : true,
		    					checkoldPassWord:[],
		    					
					           },
		    				opt_password : {
		    					required:true,
		    					maxlength :25,
		    					minlength:6,
		    					checkPassword:[],
		    					checkRepeatPassWord:[]
					           },
					       opt_conf_password : {
			    					required:true,
			    					maxlength :25,
			    					minlength:6,
			    					checkPassword:[]
						           }
		    			},
		    			 onkeyup:false
					 });
		    		});
				$(document).ready(function(){
					
					  $("#dialog").dialog({
			     	        autoOpen:false,
			     	        buttons:[{
			     	            text:"关闭",
			     	            click:function(){
			     	            	
			     	                	$("#dialog").dialog("close");
			     	                	toBack();
			     	                }
			     	            }
			     	            ],
			     	            position:"top",//弹出位置
			     	            modal:true,
			     	            width:400, //窗口宽度
			    	            height:330,
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
				
					
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>修改密码 </span>
			</div>
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/userMgmtController/mdfUserPass.controller" method="POST">
					<!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag name="opt_no" id="opt_no"  value='${rmHelper.returnParams.opt_no}'/>
					<div class="row">
						<webTag:Text id="opt_name" name="opt_name" value='${rmHelper.returnParams.opt_name}' displayLable="用户姓名:" readonly="true" />
					</div>
					<div class="row">
						<webTag:PasswordTag id="old_password" name="old_password" value='' displayLable="用户旧密码" isdisplay="true"/>
					</div>
					<div class="row">
						<webTag:PasswordTag id="opt_password" name="opt_password" value='' displayLable="用户新密码" isdisplay="true"/>
					</div>
					
					<div class="row">
						<webTag:PasswordTag id="opt_conf_password" name="opt_conf_password" value='' displayLable="确认密码" isdisplay="true"/>
					</div>
					
					<div class="row" style="text-align:right;">
				       <button id="subButton" type="submit" class="btn btn-danger" ><i class="icon-inbox icon-white"></i>保存</button>
                       <a  class="btn btn-danger" href="#" id="close1" onclik="closewin()">关闭</a> 
					</div>
				</form>
			</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
	<script type="text/javascript">
	$(document).ready(function() {
			var a= $(window.parent.document).find("#sidebar").height();
				a=a+150;
				$(window.parent.document).find("#ffame").css("height",""+a+"px");
				
				
			});
	 $("#close1").click(function(){
		$('.page_tab_title .close',parentDoc).click();
		});
	 var parentDoc = window.parent.document;
	 $('.page_tab_title .close',parentDoc).click(function(event){
		 	
			event.stopPropagation(); // 阻止冒泡
			aPageTabTitleLength = $('.page_tab_title',parentDoc).length;
			var oIndex = $(this).parents('.page_tab_title',parentDoc).index();
			//首页tab禁止删除
			if(oIndex == 0){
				return;
			}
			if(aPageTabTitleLength > 1){
				$(this).parents('.page_tab',parentDoc).find('.page_tab_content').eq(oIndex).remove();
				$('.page_tab_content',parentDoc).eq(0).show();
				$(this).parents('.page_tab_title',parentDoc).remove();
				$('.page_tab_title',parentDoc).removeClass('visited');
				$('.page_tab_title',parentDoc).eq(0).addClass('visited');
			}
			try{
				dyniframesize();
			}catch(e){
				
			}
		});
	
	</script>
</html>
