<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
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
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>基本法管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>基本法信息管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>新增</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/LMS/BasicLawsManager/addBasicLaws.do" method="POST">
					<!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					
					<fieldset>
						<div class="row">
							<webTag:DynamicSelectTag src="channelInfoSelect" name="dept_type" id="dept_type" displayLable="渠道类型" value='${rmHelper.returnParams.dept_type}'></webTag:DynamicSelectTag>
							<webTag:Text id="impmeansver_name" name="impmeansver_name" value='${rmHelper.returnParams.impmeansver_name}' displayLable="基本法名称" isdisplay="true"/>
	                        <webTag:Date id="start_date" name="start_date" value='${rmHelper.returnParams.start_date}' displayLable="基本法开始日期" isdisplay="true"/>
						</div><!-- /.row -->
						
						<div class="row">                          
	                        <webTag:Date id="end_date" name="end_date" value='${rmHelper.returnParams.end_date}' displayLable="基本法结束日期" isdisplay="true"/>
	                        <webTag:Date id="iss_date" name="iss_date" value='${rmHelper.returnParams.iss_date}' displayLable="颁布日期" isdisplay="true"/>
	                        <webTag:DynamicSelectTag src="YNStatusSelect" name="data_flag" id="data_flag" value='${rmHelper.returnParams.data_flag}' displayLable="是否实施"></webTag:DynamicSelectTag>
						</div>
						
					    <div class="row" style="text-align:right;">
				    		<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
				    		<a class="btn btn-danger" href="<%=basePath %>/LMS/BasicLawsManager/getAllBasicLawsInfo.do"><i class="icon-share-alt icon-white"></i>返回</a>
						</div><!-- /.row -->
					</fieldset>	
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
	</body>
</html>
