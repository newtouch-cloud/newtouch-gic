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
	    <!-- 附件修改操作js -->
		<script type="text/javascript" src="<%=basePath%>/compent/default/js/attachmentModify.js"></script>
		<script >
		$(document).ready(function() {
			$("#queryForm").validate({
	    			rules : {
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
	    			/* messages:{
	    				regulation_id:"不能超过20个字符或10个汉字",
	    				regulation_name:"不能超过100个字符或50个汉字",
	    				regulation_description:"不能超过1000个字母或500个汉字",
	    				makers:"不能超过50个字母或25个汉字"
				 	}, */
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
	        
	       //判断值为空函数
	       function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
	     
	     //表达提交校验
	     	
		 </script>		     
		
			     
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章制度管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章维护</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章制度修改</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm"  class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/RegulationManagerController/modifyRegulation.do" enctype="multipart/form-data" method="POST">
				     <!-- 提示信息标签 -->
				   	<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
				    <webTag:HiddenInputTag id="seq_id" name="seq_id" value="${rmHelper.returnParams.seq_id}" displayLable="机构代码"/>
				    <webTag:HiddenInputTag id="regulation_id" name="regulation_id" value="${rmHelper.returnParams.regulation_id}" displayLable="规章制度编号"/>
				   	<webTag:HiddenInputTag id="file_name" name="file_name" value="${rmHelper.returnParams.file_name}"/>
					<webTag:HiddenInputTag id="file_id" name="file_id" value="${rmHelper.returnParams.file_id}"/>
					<webTag:HiddenInputTag id="file_flag" name="file_flag" value="${rmHelper.returnParams.file_flag}"/>
				   	<webTag:HiddenInputTag id="delete_file" name="delete_file" value=''/>
					<fieldset>
					     <div class="row">
					    	<webTag:Text id="regulation_id" name="regulation_id"  value='${rmHelper.returnParams.regulation_id}'  displayLable="规章编号"  isdisplay="true" readonly="true"/>
					    	<webTag:Text id="regulation_name" name="regulation_name"  value='${rmHelper.returnParams.regulation_name}' displayLable="规章名称"  isdisplay="true"/>
                            <webTag:DynamicSelectTag src="regStatusTypeSelect" name="regulation_status_code" id="regulation_status_code" displayLable="规章状态" value="0" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
                             <webTag:Text id="makers" name="makers"  value='${rmHelper.returnParams.makers}' displayLable="制定人"  isdisplay="true"/>
                             <webTag:Date id="make_time" name="make_time"  value='${rmHelper.returnParams.make_time}' displayLable="制定时间" isdisplay="true"/>
					</div>
					         <webTag:AttachmentUpdateTag   id="attachment" name="attachment" value="${rmHelper.returnParams.file_name}" basePath="<%=basePath %>" uploadLable="附件上传:" displayLable="附件下载:"/>				
					<div class="row">                                    
                             <webTag:TextareaTag id="regulation_description" name="regulation_description" value='${rmHelper.returnParams.regulation_description}' displayLable="规章描述:" rows="5"/>
					</div>
					</fieldset>
					<div class="row" align="right">
						<button id="submitBtn" type="submit" onclick="submitFormForFile('<%=basePath %>');" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
			            <a class="btn btn-danger" href="<%=basePath %>/AMS/RegulationManagerController/queryAll.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
