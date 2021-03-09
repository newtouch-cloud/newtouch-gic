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
	<head>
		<title>新致金保通</title>
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script >
		var branch_id =${rmHelper.returnParams.branch_id} ;
		  $(function() {
		    	 
//				修改成功后返回界面清空行为类型行为日期及行为描述的内容
				  //清空行为类型
	    		$("#queryForm").validate({
	    			rules : {
	    				action_time : {
	    					required : true
	    				},
	    				action_notes : {
	    					required : true,
	    					maxlength :2000
	    				},
	    				customer_id :{
	    					required : true,	
	    				}
	    			//	customer_id : {
	    				//	required : true,
	    					//checkCustomerId : []
	    				//}
	    			},
	    			onkeyup:false
	    			
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
		   		initDate();
		   		saveHead();
	    	});
	     
	  <%--    jQuery.validator.addMethod("checkCustomerId",function(value,element,param){
	    	 var flag = false;
	    	 if(value != ""){
	    		 $.ajax({
	    			 url:"<%=basePath %>/CRM/Customer/queryCustomerById.do",
	    			 type:"post",
	    			 dataType : "json",
	    			 async:false,
	    			 data:{"customer_id":value},
	    			 success :　function(data){
	    				 $.each(data,function(index,comment){
	    					 var success = comment.success;
		    				 
	    					 if(success == "true"){
		    					 $('#customer_id').val(comment.customer_id);
		    					 $('#name').val(comment.name);
		    					 
		    					 flag = true;
		    				 }
	    					 
	    				 });
	    				
	    				 
	    			 }
	    		 });
	    	 }
	    	 return flag;
	     },"输入的客户代码不存在"); --%>
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
	   
	     	function initDate(){
	    		var actionTime=$("#action_time");
	    		var date=new Date();
	    		var year=date.getFullYear();
	    		var month=date.getMonth()+1;
	    		var day=date.getDate();
	    		if(month <= 9){
	    			month="0"+month;
	    		}
	    		if(day <= 9){
	    			day="0"+day;
	    		}	
	    		var action_time=year+"-"+month+"-"+day;
	    		actionTime.val(action_time);
	    	}
	     	function saveHead(){
	     		var ss=$('#result_flag').val();
	     		if(ss=='true'){
	     			$('#butt').hide();
	     		}
	     	}
	     	
		</script>		     
		
			     
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户查询</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 接触录入</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" onsubmit="return checkSubmit();" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CRM/Customer/updateCustomerJieChu.do" method="POST">
				<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${savaHide}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>
					<webTag:HiddenInputTag id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}'/>
					<!-- value为后台返回的 true 或者false-->
					    
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 接触录入</span>
					</div>
					<div class="row">
						 <webTag:Text  id="customer_id" name="customer_id" value='${rmHelper.returnParams.customer_id}' displayLable="客户代码" isdisplay="true" readonly="true"/>
						  <webTag:Text  id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="客户姓名"  readonly="true"/>
						  <webTag:Text  id="corporation_represen" name="corporation_represen" value='${rmHelper.returnParams.corporation_represen}' displayLable="法人代表"  readonly="true"/>
					</div><!-- /.row -->
					<div class="row">
						  <webTag:Date  id="action_time" name="action_time" value='' displayLable="日期" readonly="true"/>
					</div><!-- /.row -->
					
					<div class="row">
						 <webTag:TextareaTag name="action_notes" id="action_notes" rows="3"  value='${rmHelper.returnParams.action_notes}' displayLable="内容" ></webTag:TextareaTag>
					</div>
					<div class="row" style="text-align:right;">
					    		<button id="butt" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
					    		<a class="btn btn-danger" href="<%=basePath %>/CRM/Customer/customerQuery.do"><i class="icon-share-alt icon-white"></i>返回</a>
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
