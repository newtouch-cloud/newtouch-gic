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

ReturnMsg returnMsg = (ReturnMsg)request.getAttribute("returnMsg");
if(returnMsg==null){returnMsg = new ReturnMsg();}
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
						//在此拼接action
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
		<div class="container-fluid" >
			 <!-- 面包屑导航  start -->
			 <div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批处理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>解约恢复审批</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- start -->
			<div class="row-fluid">
			        <form class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/ams/approvalManage/dismissResumeAppr.do" method="POST" id="mainForm">
			        <!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
			        
			        <webTag:HiddenInputTag name="approval_id" id="approval_id" value='${ rmHelper.returnParams.approval_id}'/>
					<webTag:HiddenInputTag name="seq_id" id="seq_id" value='${ rmHelper.returnParams.seq_id}'/>
					<webTag:HiddenInputTag name="channel_id" id="channel_id" value='${ rmHelper.returnParams.channel_id}'/>
					<webTag:HiddenInputTag name="sales_status" id="sales_status" value='${ rmHelper.returnParams.sales_status}'/>
					<div class="row">
						<webTag:Text id="sales_id" name="sales_id" value='${rmHelper.returnParams.sales_id}' readonly="true" displayLable="人员代码" isdisplay="true"/>
					    <webTag:Text id="sales_name" name="sales_name" value='${rmHelper.returnParams.sales_name}' readonly="true" displayLable="人员姓名" isdisplay="true"/> 
					    <webTag:Text name="rank_name" id="rank_name" value='${rmHelper.returnParams.rank_name}' displayLable="当前职级" readonly="true" isdisplay="true"></webTag:Text>
						<webTag:HiddenInputTag name="rank_id" id="rank_id" value='${rmHelper.returnParams.rank_id}'/>
						<webTag:HiddenInputTag name="basic_version_id" id="basic_version_id" value='${rmHelper.returnParams.basic_version_id}'/>
					</div>
					<div class="row">
						<webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="所属机构代码" readonly="true" isdisplay="true"/>
					    <webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="所属机构名称:" readonly="true"/>
					</div>
					<div class="row">
					     <webTag:Text id="team_id" name="team_id" value='${rmHelper.returnParams.team_id}' displayLable="团队代码" readonly="true" isdisplay="true"/>
						 <webTag:Text id="team_name" name="team_name" value='${rmHelper.returnParams.team_name}' displayLable="团队名称:" readonly="true"/>
					     <webTag:DynamicSelectTag src="channelSelect" name="team_lvl" id="team_lvl" value='${rmHelper.returnParams.team_lvl}' displayLable="团队级别:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
			        <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i>  恢复信息</span>
					</div>
					<div class="row">
					    <webTag:DynamicSelectTag src="salesStatusSelect" name="status" id="status" displayLable="离职恢复后状态" value='1'  onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"/>
					</div>
					<div class="row">
					     <webTag:TextareaTag id="resume_description"  rows="5" name="resume_description" value='${rmHelper.returnParams.resume_description}' displayLable="恢复原因:" readonly="true"/>
					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 审批意见</span>
					</div>
					<div class="row">
						<webTag:TextareaTag id="approval_views" name="approval_views"  rows='5'  value='${rmHelper.returnParams.approval_views}' displayLable="审批意见:"/>
					</div>
					<div class="row" style="text-align:right;">
						<c:if test='${rmHelper.returnParams.approval_status=="0"}'>
					    	<button id="subButtonPass" type="button" class="btn btn-danger"><i class="icon-ok-circle icon-white"></i>审批通过</button>
							<button id="subButtonNOPass" type="button" class="btn btn-danger"><i class="icon-remove-circle icon-white"></i>审批不通过</button>
					    </c:if>
					    <a class="btn btn-danger" href="<%=basePath %>/Ams/ApprovalManage/approvalQuery.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
		    </div>
		    <!-- end结束 -->
		</div>
	</body>
</html>