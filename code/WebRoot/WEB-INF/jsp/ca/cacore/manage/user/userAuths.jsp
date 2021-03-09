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
			function save(){
				var checks= document.getElementsByName("role");
				var checkNum=0;
				for(var i=0;i<checks.length;i++){
					if(checks[i].checked==true){checkNum=checkNum+1;}
				}
				if(checkNum==0){alert("请为用户选择权限！")}
				else{document.forms[0].submit();}
			}
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>后台管理功能 <span class="divider">/</span></li>
					<li>用户管理<span class="divider">/</span></li>
					<li>明细</li>
				</ul>
			</div>
			<!-- 面包屑导航  end -->
			<form class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/User/updateUserAuths.do" method="POST">
			<!-- 数据区 start -->
			<div class="row-fluid">
					<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id }'/>
					<fieldset>
					<jsp:include page="../../util/branchTree.jsp" flush="true"/> 
					<div class="row">
					   <webTag:Text   id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id }' readonly="true" displayLable="机构id"/>
					   <webTag:DynamicSelectTag src="authTypeSelect" name="user_type" id="user_type" value='${rmHelper.returnParams.user_type }' displayLable="用户类型"  ></webTag:DynamicSelectTag>
					   <webTag:Text id="username" name="username" value='${rmHelper.returnParams.userName}' readonly="true" displayLable="用户名"/>	
					</div>
						<div class="row">
							<webTag:PasswordTag id="password" name="password" value='${rmHelper.returnParams.password}'  displayLable="登陆密码"></webTag:PasswordTag>
						   <webTag:Text   id="name" name="name" value='${rmHelper.returnParams.name }' displayLable="姓名"/>
						   <webTag:DynamicSelectTag src="genderSelect" name="sex_code" id="sex_code" displayLable="性别" value='${rmHelper.returnParams.sex_code }'></webTag:DynamicSelectTag>
						</div><!-- /.row -->
						<div class="row">
						   <webTag:Text   id="mobile" name="mobile" value='${rmHelper.returnParams.mobile }' displayLable="手机"/>
						   <webTag:Text   id="fixed_line" name="fixed_line" value='${rmHelper.returnParams.fixed_line }' displayLable="固定电话"/>
						   <webTag:Text   id="email" name="email" value='${rmHelper.returnParams.email }' displayLable="电子邮件"/>
						</div>
						<div class="row">
						  <webTag:Text   id="emp_id" name="emp_id" value='${rmHelper.returnParams.emp_id }' displayLable="员工代码"/>
						  <webTag:Text   id="job_tel" name="job_tel" value='${rmHelper.returnParams.job_tel }' displayLable="办公电话"/>
						  <webTag:Text   id="job_address" name="job_address" value='${ rmHelper.returnParams.job_address }' displayLable="办公地址"/>
						</div>
						<div class="row">
						<webTag:Text   id="remark" name="remark" value='${rmHelper.returnParams.remark }' displayLable="备注"/>
						</div>
						
					</fieldset>
			</div>
			<div class="row-fluid">
				<!-- 这个是列表 -->
						<div class="row-fluid">
							<table class="table table-striped table-bordered bootstrap-datatable datatable">
								<thead>
									<tr>
										<th>选择</th>
										<th>序号</th>
										<th>角色代码</th>
										<th>角色名称</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="data" items="${rmHelper.returnMsg.dataList}" varStatus="status">
									<tr>
										
										<td>
											<c:choose>
												<c:when test="${ data.roleSelected=='true' }"><input type="checkbox" name="role" value="${ data.role_id}" checked/></c:when>
												<c:when test="${ data.roleSelected=='false' }"><input type="checkbox" name="role" value="${ data.role_id}"/></c:when>
											</c:choose>
										</td>
										<td>${status.index }</td>
										<td>${ data.role_id }</td>
										<td>${ data.role_name }</td>
										<td>${ data.remark }</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					    <div class="row" style="text-align:right;">
					    		<button type="button" class="btn btn-mini btn-primary" onclick="save()"><i class="icon-search icon-white"></i>保存</button>
<%-- 					    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/User/updateUserAuths.do">保存</a> --%>
					    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/User/queryUser.do">返回</a>
						</div>
			</div>
			</form>
			<!-- 数据区 end -->
		</div>
	</body>
</html>
