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
		//数据校验
		$(document).ready(function() {
			$("#queryForm").validate({
	    			rules : {
	    				regulation_id : {   // 规章编号
	    					required : true,
	    					maxlength :20,
	    					checkRegulationIdUnique:[]
	    				},
	    				regulation_name : { //规章名称
	    					required : true,
	    					maxlength :200
	    				},
	    				regulation_description :　{   //规章描述
	    					maxlength :500
	    				},
	    				makers : {  //制定人
	    					required : true,
	    					maxlength :25
	    				},
	    				make_time : { //制定时间
	    					required : true
	    				}
	    			},
	    			onkeyup:false
			});
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
		
		 // (检查规章编号是否重复 )根据规章编号异步请求相应信息 
	     jQuery.validator.addMethod("checkRegulationIdUnique",function(value,element){
         var regulation_id=$('#regulation_id').val();
	          var flag=true;
	          if(regulation_id!=""){
	               $.ajax({
	                   url:"<%=basePath %>/AMS/RegulationManagerController/checkRegulationIdUnique.do",
	                   type:"post",
	                   async: false,
	                   data:{"regulation_id": regulation_id},
	                   //true表示制度编号存在，false表示不存在 
	                   success:function(data){
	                		if (data == "false") {
		     					flag=false;
		     					//$("#regulation_id").val("");
		     				}
		     			}
		     		});
		     	}else{
		     		flag = true;//如果规章编号为空时触发则不校验
		     	}
		     	return flag;
	     }, "输入的规章编号已存在，请重新录入!");
	    //判断值为空函数
	    function isUndefined(paraValue){
		      if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		      return false;
	   }
	</script>		     
		
			     
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章制度管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章维护</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章制度新增</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm"  class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/RegulationManagerController/addRegulationBasic.do" enctype="multipart/form-data" method="POST">
				    <!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
				    <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <fieldset>
					     <div class="row">
					    	<webTag:Text id="regulation_id" name="regulation_id"  value='${rmHelper.returnParams.regulation_id}' displayLable="规章编号"  isdisplay="true"/>
							<webTag:Text id="regulation_name" name="regulation_name" value='${rmHelper.returnParams.regulation_name}' displayLable="规章名称" isdisplay="true"/>
                            <webTag:DynamicSelectTag src="regStatusTypeSelect" name="regulation_status_code" id="regulation_status_code" displayLable="规章状态" value="0" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					
					<div class="row">
                             <webTag:Text id="makers" name="makers"  value='${rmHelper.returnParams.makers}' displayLable="制定人"  isdisplay="true"/>
                             <webTag:Date id="make_time" name="make_time"  value='${rmHelper.returnParams.make_time}' displayLable="制定时间" isdisplay="true"/>
				    </div>
				    <div class="row">
				             <webTag:AttachmentUploadTag   id="attachment" name="attachment"  displayLable="附件上传:"/>
					</div>
					<div class="row">                                
                             <webTag:TextareaTag id="regulation_description" name="regulation_description" value='${rmHelper.returnParams.regulation_description}' displayLable="规章描述:" rows="5"/>    
					</div>			
					<div class="row" align="right">
					<button type="submit" id="submitBtn" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
			        <a class="btn btn-danger" href="<%=basePath %>/AMS/RegulationManagerController/queryAll.do"><i class="icon-share-alt icon-white"></i>返回</a>
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