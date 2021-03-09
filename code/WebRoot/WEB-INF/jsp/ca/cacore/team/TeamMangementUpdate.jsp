<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@ page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@ page import="com.newtouch.component.c11assistant.JspHelper"%>
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
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/> 	
		<jsp:include page="/WEB-INF/jsp/ca/cacore/manage/branch/province.jsp" flush="true"/> 
       
       <script type="text/javascript">
		 jQuery.validator.addMethod("checkteamnameRepeat",function(value,element){
			 var team_name = $("#team_name").val();
			 var branch_id = $("#branch_id").val();
			 debugger;
	     	$.ajax({
	     		url:"<%=basePath %>/team/teammangement/addCheckRepeat.do",
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
		
		$(document).ready(function() {
    		$("#queryForm").validate({
    			rules : {
    				team_id : {
    					required : true,
    				},
    				team_name : {
    					required : true,
    					maxlength :100,
    					checkteamnameRepeat: []
    				}
    				
    			},
    			onkeyup:false 
    		});
    	});  		
			
		</script>
		
	</head>
	<body style="height: 750px">
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span><i class="icon-list icon-red"></i>团队管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>团队管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>修改</span>
			</div>
			<!-- 面包屑导航  end -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/team/teammangement/Updateteam.do" method="POST">
			         <div id="dialog" title="提示信息" style="display:none">
				    	<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					 </div>
			         <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				     <webTag:HiddenInputTag name="flag" id="flag" value="${rmHelper.returnParams.flag}"></webTag:HiddenInputTag>
				     <webTag:HiddenInputTag name="removeflag" id="removeflag" value="${removeflag}"></webTag:HiddenInputTag>	 
                     
					 <webTag:HiddenInputTag id="branch_id" name="branch_id"  value="${rmHelper.returnMsg.dataTable.branch_id}"/>
					 <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					 <webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					 <webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>

						
						
						 <div class="row" >
						   <webTag:Text   id="team_id" name="team_id" value='${rmHelper.returnMsg.dataTable.team_id}' displayLable="团队/部门编码" readonly="true" isdisplay="true"/>
				           <webTag:Text   id="team_name" name="team_name" value='${rmHelper.returnMsg.dataTable.team_name}' displayLable="团队/部门名称"  isdisplay="true"/>
						 </div>
						 <div class="row" style="text-align:right;">
					      	<button id="submitBtn" type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-inbox icon-white"></i>保存</button>
                            <a id="backBtn" class="btn btn-danger" href="<%=basePath %>/team/teammangement/queryteam.do"><i class="icon-share-alt icon-white"></i>返回</a>
						</div><!-- /.row -->
						
					</form>
				
			</div>
		
				<div class="pagination pagination-centered">
				    <ul id="Pagination"></ul>
				</div>
			</div>
			<!-- 底部高度填充块 -->
			<div class="zeoBottomH90"></div>
			<!-- 底部高度填充块 结束-->
	</body>
	<script type="text/javascript">
       $(document).ready(function() {
       var a= $(window.parent.document).find("#sidebar").height();
       a=a+150;
	   $(window.parent.document).find("#ffame").css("height",""+a+"px");
       });
   </script>
</html>