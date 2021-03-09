<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>

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
		<!-- 按钮返回控制 -->
		<jsp:include page="../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../pub/backPageHelper.jsp" flush="true"/>	
		<jsp:include page="../manage/branch/province.jsp" flush="true"/> 
		<script type="text/javascript" >
		 jQuery.validator.addMethod("checkteamnameRepeat",function(value,element){
			 var branch_id = $("#branch_id").val();
			 var team_name = $("#team_name").val();
	     	$.ajax({
	     		url:"<%=basePath%>/team/teammangement/addCheckRepeat.do",
	     		type:"post",
	     		async: false,
	     		data:{"team_name":team_name,"branch_id":branch_id},
	     		success:function(data){
	     			var str=data.substring(1,data.lastIndexOf('}'));
     				var isSuccess=str.split(',')[0].split(':')[1];
     				if(isSuccess=="true"){
     					$("#flag").val("true");
     				}else{
     					$("#flag").val("false");
     				}
	     		}
	     	});
	     	if($("#flag").val()=="true"){
	     		return true;
	     	}else{
	     		return false;
	     	}
	     },"团队名称重复，请重新输入。");	
		 
		/*  jQuery.validator.addMethod("checkDateFormat",function(value,element){
	        	if(!isUndefined(value)){
	        		
			        
			        if(dateNumber2>=dateNumber1){
			        	return true;
			        }else{
			        	return false;
			        }
			        
	        	}else{
	        		return false;
	        	}
		     },"日期不正确，请核实。"); */
		 
		$(document).ready(function() {
    		$("#queryForm").validate({
    			rules : {
    				branch_name : {
    					required : true,
    				},
    				team_name : {
    					required : true,
    					maxlength :100,
    					checkteamnameRepeat: []
    				},
    				team_type : {
    					required : true,
    				},
    				found_date : {
    					required : true,
    					minlength :10,
    				}
    			},
    			onkeyup:false 
    		});
    	});  		
			
		</script>
		
	</head>
	<body style="height: 750px">
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span><i class="icon-list icon-red"></i>团队管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>团队管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>新增</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12"  action="<%=basePath %>/team/teammangement/addTeam.do" method="POST">
				 
				 <!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
				    	<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					 </div>
					
				 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				 <webTag:HiddenInputTag name="flag" id="flag" value="${rmHelper.returnParams.flag}"></webTag:HiddenInputTag>
				 <webTag:HiddenInputTag name="removeflag" id="removeflag" value="${removeflag}"></webTag:HiddenInputTag>	
				 <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
				 <webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>	
				 <webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/> 
				   <fieldset>
				   <div class="row" >
					    <jsp:include page="../util/NewBranchTree.jsp" flush="true" />
						<webTag:DynamicSelectTag src="teamTypeSelect" name="team_type" id="team_type" displayLable="新增团队/部门" value='${rmHelper.returnParams.team_type}' isdisplay="true"/>
				        <webTag:Text id="team_name" name="team_name" value='${rmHelper.returnParams.team_name}' displayLable="团队/部门名称"  isdisplay="true"/>
				   </div>
					<div class="row" >
					    
						<%-- <webTag:Date id="found_date" name="found_date"  value="${rmHelper.returnParams.found_date}" displayLable="创建时间" isdisplay="true" onClick="WdatePicker({skin:'whyGreen',minDate:'%y-%M-{%d-4}',maxDate:'%y-%M-{%d+3}'} )/>   --%>  
				     <%--      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                     创建时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="found_date" name="found_date" style="height: 20px;width: 195px;border-radius:18px;" value="${rmHelper.returnParams.found_date}" isdisplay="true" onfocus="WdatePicker({skin:'default',minDate:'%y-%M-{%d-4}',maxDate:'%y-%M-{%d+3}'} )"  />  --%> 
					<div class='control-group span4'>    
						<label class='control-label' for='found_date'>创建时间(<font color="red">*</font>):</label>    
						<div class='controls'><input type='text' class='input-medium null' iclass='' onClick="WdatePicker({skin:'default',minDate:'%y-%M-{%d-4}',maxDate:'%y-%M-{%d+3}'} )" name='found_date' id='found_date' value=''  ></div></div>           
					</div>
					
					
					</div>
					
					
					<div class="row" style="text-align: right;">
						<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
						<a id="backBtn" class="btn btn-danger" href="<%=basePath%>/Team/toQueryTeam.do" style='text-decoration:none;'><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</fieldset>
				</form>
			</div>
			<!--  数据区 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
	<script type="text/javascript">
       $(document).ready(function() {
       var a= $(window.parent.document).find("#sidebar").height();
       a=a+150;
	   $(window.parent.document).find("#ffame").css("height",""+a+"px");
       });
   </script>
</html>
