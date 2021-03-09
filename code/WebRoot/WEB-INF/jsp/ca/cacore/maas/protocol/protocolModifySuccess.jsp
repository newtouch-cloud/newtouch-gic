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
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script >
		</script>	
				<script type="text/javascript" src="<%=basePath%>/compent/default/js/attachmentModify.js"></script>   
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>协议管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保险公司协议</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>维护</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="" enctype="multipart/form-data" method="POST">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>	
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag id="file_name" name="file_name" value="${rmHelper.returnParams.file_name}"/>
                    <webTag:HiddenInputTag id="file_id" name="file_id" value="${rmHelper.returnParams.file_id}"/>
                    <webTag:HiddenInputTag id="file_flag" name="file_flag" value="${rmHelper.returnParams.file_flag}"/>
                    <webTag:HiddenInputTag id="delete_file" name="delete_file" value=''/>
                    
					<fieldset>
					<div class="row">
<%-- 				  		<jsp:include page="branchTreeRequired.jsp" flush="true" /> --%>
				  		<webTag:Text id="branch_name" name="branch_name"  value='${rmHelper.returnParams.branch_name}' displayLable="使用机构" isdisplay="true" readonly="true" />
<%-- 					    <webTag:DynamicSelectTag src="insBranchSelect" name="ins_branch" id="ins_branch" displayLable="保险公司" value="${rmHelper.returnParams.ins_branch}" isdisplay="true" readonly="true"/> --%>
						<webTag:Text name="insBranch_name" id="insBranch_name" displayLable="保险公司" value="${rmHelper.returnParams.insBranch_name}" isdisplay="true" readonly="true"/>
						<webTag:Text id="ins_branchdept" name="ins_branchdept"  value='${rmHelper.returnParams.ins_branchdept}' displayLable="签约机构" isdisplay="true" readonly="true" />
					</div><!-- /.row -->
					
					<div class="row">                          
                        <webTag:Text id="agreement_no"  name="agreement_no"   value='${rmHelper.returnParams.agreement_no}'  displayLable="协议号" isdisplay="true" readonly="true" />
                        <webTag:Text id="contacts_name" name="contacts_name"  value='${rmHelper.returnParams.contacts_name}' displayLable="联系人:" readonly="true" />
                        <webTag:Text id="contacts_way"  name="contacts_way"   value='${rmHelper.returnParams.contacts_way}'  displayLable="联系方式:" readonly="true" />
					</div>
					
					<div class="row">                
						<webTag:Date id="startdate"  name="startdate"  value='${rmHelper.returnParams.startdate}'  displayLable="生效日期" isdisplay="true" readonly="true" />
						<webTag:Date id="enddate"    name="enddate"    value='${rmHelper.returnParams.enddate}'    displayLable="终止日期" isdisplay="true" readonly="true" />
						<webTag:Date id="dateofsign" name="dateofsign" value='${rmHelper.returnParams.dateofsign}' displayLable="协议签订日期" isdisplay="true" readonly="true" />
					</div><!-- /.row -->
					
					<div class="row">                
						<webTag:Text id="code" name="code" value='${rmHelper.returnParams.code}' displayLable="签订人工号" isdisplay="true" readonly="true" />
                        <webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="签订人姓名" isdisplay="true" readonly="true"/>
<%-- 					    <webTag:DynamicSelectTag src="protocolStatusSelect"  id="status" name="status"  value='${rmHelper.returnParams.status}'      displayLable="状态"  isdisplay="true" readonly="true" /> --%>
						<webTag:Text  id="status_name" name="status_name"  value='${rmHelper.returnParams.status_name}'      displayLable="状态"  isdisplay="true" readonly="true" />
					</div><!-- /.row -->
										    <webTag:AttachmentUpdateTag   id="attachment" name="attachment" value='${rmHelper.returnParams.file_name}'  basePath="<%=basePath %>" uploadLable="附件上传:" displayLable="附件下载:"/>
				    <div class="row" style="text-align:right;">
<!-- 			    		<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button> -->
					    <a class="btn btn-danger" href="<%=basePath %>/maas/Protocol/queryProtocolList.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
				  </fieldset>	
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
