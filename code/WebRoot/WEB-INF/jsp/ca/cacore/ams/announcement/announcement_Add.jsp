<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

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
		
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
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
	    //校验日期的先后顺序正确性
	       jQuery.validator.addMethod("checkDateOrder",function(value,element,param){
		     	var start_time=$("#start_time").val();
		     	var end_time=$("#end_time").val();
		     	if(!isUndefined(start_time)&&!isUndefined(end_time)){
		     		if(start_time>=end_time){
		     			return false;
		     		}
		     		$("label:contains('时间顺序有误')").remove();
		     		return true;
		     	}
		     	return true;
		     },"时间顺序有误");
	   
     
        
     $(document).ready(function() {
    	$('#announcement_type_code').val("1");
 		$("#mainForm").validate({
 			rules : {
 				    branch_name:{//机构名称
 				    	required : true
 				    },
 				   announcement_theme : {//主题
 					required : true,
 					maxlength :200
 				},
 				announcement_type_code:{//类型
 					required : true
 				},
 				start_time : {//开始时间
					required : true,
					checkDateOrder : [ "start_time", "end_time" ]
				},
				end_time : {//结束时间
					required : true,
					checkDateOrder : [ "start_time", "end_time" ]
				},
 				approval_status: {//状态
 					required : true
 				},
 				announcement_mark_code:{//标识
 					required : true
 				},
 				announcement_content:{
 					maxlength:498
 					}
 				},
 			onkeyup:false,
 			messages:{
 				announcement_content:{
 					maxlength : "输入的内容不能大于500个字符"
				}
 			}
 		});
 	});
        </script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>公告管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>公告维护</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>公告录入 </span>
			</div>
			<!-- 面包屑导航  end -->
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/Announcement/addAnnouncement.do" method="POST" enctype="multipart/form-data">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div  >
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.getMsgStr()}" displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}' displayLable="机构代码"/>
				    <webTag:HiddenInputTag id="announcement_id" name="announcement_id" value='${rmHelper.returnParams.announcement_id}' displayLable="公告编号"/>
					<!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
					<div class="row">
						<jsp:include page="../../util/branchTreeRequired.jsp" flush="true"/>
						<webTag:Text id="announcement_theme" name="announcement_theme"  value='${rmHelper.returnParams.announcement_theme}' displayLable="主题" isdisplay="true"/>
					    <webTag:DynamicSelectTag src="announcementTypeSelect" name="announcement_type_code" id="announcement_type_code" value='${rmHelper.returnParams.announcement_type_code}' displayLable="类型"  isdisplay="true"  onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					<div  class="row">
					   <webTag:Date id="start_time" name="start_time"  value='${rmHelper.returnParams.start_time}' displayLable="公告生效时间 从" isdisplay="true"/>
                        <webTag:Date id="end_time" name="end_time"  value='${rmHelper.returnParams.end_time}' displayLable="到" isdisplay="true"/>
					    <webTag:DynamicSelectTag src="announcementMarkSelect" name="announcement_mark_code" id="announcement_mark_code"  value='${rmHelper.returnParams.announcement_mark_code}'  displayLable="标识"  isdisplay="true"  ></webTag:DynamicSelectTag>
					</div>	
					<div  class="row">    
					    <%--   <webTag:DynamicSelectTag src="approvalStatusSelect" name="approval_status" id="approval_status" displayLable="审批状态" value="0" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag> --%>
					      <webTag:AttachmentUploadTag   id="attachment" name="attachment"  displayLable="附件上传:" />
					</div>	                                                                                                                    
					<div  class="row">
					       <webTag:TextareaTag id="announcement_content" name="announcement_content" value='${rmHelper.returnParams.announcement_content}' displayLable="内容:" rows="5"/>
                    </div>
					<div class="row" align="right">
					     <button type="submit" id="submitBtn" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
			             <a class="btn btn-danger" href="<%=basePath %>/AMS/Announcement/queryAnnouncement.do?pageName=announcement_Query"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
