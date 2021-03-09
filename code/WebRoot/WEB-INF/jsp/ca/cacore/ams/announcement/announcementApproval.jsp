<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%
String path = request.getContextPath();
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
     	
     	<script type="text/javascript">
	     	//页面加载时获取的回跳参数
			var params=encodeURI(encodeURI('${rmHelper.returnParams.params}'));
			$(document).ready(function() {
				//当审批不通过时必须填写审批意见
				$("#subButtonNOPass").click(function(){
					var approval_views;
					if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
						approval_views= $.trim($("#approval_views").text());
					}else{
						approval_views= $.trim($("#approval_views").val());
					}
					if(approval_views==""||approval_views==null){
						alert("请填写审批意见");
						return;
					}
					var action=$("#mainForm").attr("action");
					//判断是否是第一次点击审批不通过按钮
					if(action!=null&&action.indexOf("?")==-1){
						action+="?flag=0&params="+params;
					}else{
						//如果不是，截取拼接之前原始的action，否则会出现两个flag 
						action=action.substring(0,action.lastIndexOf("?"));
						//再次拼接action
						action+="?flag=0&params="+params;
					}
					$("#mainForm").attr("action",action);
					$("#mainForm").submit();
				});
				//审批通过时必须进行的操作
				$("#subButtonPass").click(function(){
					var action=$("#mainForm").attr("action");
					if(action!=null && action.indexOf("?")==-1){
						action+="?flag=1&params="+params;
					}else{
						action=action.substring(0,action.lastIndexOf("?"));
						action+="?flag=1&params="+params;
					}
					$("#mainForm").attr("action",action);
					$("#mainForm").submit();
				});
				//校验审批意见的长度
				$("#mainForm").validate({
					rules : {
						approval_views : {
							maxlength : 2000
						}
					}
				});
				
			});
		</script>	     
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批处理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>公告审批</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id= "mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/ams/approvalManage/announcementAppr.do" method="POST">
					<fieldset>
						<div id="dialog" title="提示信息" style="display:none">
							<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
						</div>
					    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
						<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
						<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
						<webTag:HiddenInputTag id="flag" name="flag" value=""    displayLable="异步请求结果标志"/>
						<webTag:HiddenInputTag id="approval_id" name="approval_id" value='${rmHelper.returnParams.approval_id}'/>
						<webTag:HiddenInputTag id="apprBranchId" name="apprBranchId" value='${rmHelper.returnParams.apprBranchId}' />
						<webTag:HiddenInputTag id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}' />
						
			       <div class="row">
						<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="公告的机构" isdisplay="true" readonly="true"/>
					   	<webTag:Text id="announcement_theme" name="announcement_theme"  value='${rmHelper.returnParams.announcement_theme}' displayLable="主题" isdisplay="true"  readonly="true"/>
					     <webTag:DynamicSelectTag src="announcementTypeSelect" name="announcement_type_code" id="announcement_type_code" displayLable="标识" value='${rmHelper.returnParams.announcement_type_code}' isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					<div  class="row">    
					     <webTag:Text id="start_time" name="start_time" value='${rmHelper.returnParams.start_time}' readonly="true" isdisplay="true" displayLable="公告开始生效时间"/>
						 <webTag:Text id="end_time" name="end_time" value='${rmHelper.returnParams.end_time}' readonly="true" isdisplay="true" displayLable="到"/>
					       <webTag:DynamicSelectTag src="announcementMarkSelect" name="announcement_mark_code" id="announcement_mark_code" displayLable="标识" value='${rmHelper.returnParams.announcement_mark_code}' isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>	
					<div class="row">
					       <webTag:DynamicSelectTag src="approvalStatusSelect" name="approval_status" id="approval_status" displayLable="审批状态" value='${rmHelper.returnParams.approval_status}' isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
						<webTag:AttachmentDownloadTag busId='${rmHelper.returnParams.announcement_id}'   attachmentType="20" fileName='${rmHelper.returnParams.file_name}' trueFlag='${rmHelper.returnParams.upload_time !=null}' basePath="<%=basePath %>" displayLable="附件下载:" noAttachment="未上传附件"/>
					<div  class="row">
					       <webTag:TextareaTag id="announcement_content" name="announcement_content" value='${rmHelper.returnParams.announcement_content}' displayLable="内容:" rows="5"   readonly="true"/>
                    </div>
						<div class="row">
							<webTag:TextareaTag id="approval_views" name="approval_views" rows='5' value='${rmHelper.returnParams.approval_views}' displayLable="审批意见:"/>
					    </div>
					<div class="row" align="right">
				    <c:if test='${rmHelper.returnParams.approval_status=="0"}'>
							<button id="subButtonPass" type="button" class="btn btn-danger"><i class="icon-ok-circle icon-white"></i>审批通过</button>
							<button id="subButtonNOPass" type="button" class="btn btn-danger"><i class="icon-remove-circle icon-white"></i>审批不通过</button>
						</c:if>
			            <a class="btn btn-danger" href="<%=basePath %>/Ams/ApprovalManage/approvalQuery.do" ><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
					</fieldset>
				</form>
			</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>