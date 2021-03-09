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
			    <span class=mrl14><i class="icon-list icon-red"></i> 子基本法管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 子基本法信息管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 子基本法修改</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/LMS/SubLawsManager/modifySubLaws.do" method="POST">
					<webTag:HiddenInputTag name="serno" id="serno" value='${rmHelper.returnParams.serno}'></webTag:HiddenInputTag>
					<!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					
					<fieldset>
					<ul id="myTab1" class="nav nav-tabs">
				        <li class="active"><a href="#" onclick="javascript:goUrl(1);">基本信息</a></li>
				        <li class="disabled"><a href="<%=basePath %>/LMS/SubLawsManager/getRankList.do?serno=${rmHelper.returnParams.serno}&impmeans_name=${rmHelper.returnParams.impmeans_name}">职级信息</a></li>
				        <li class="disabled"><a href="#" onclick="javascript:goUrl(3);">参数信息</a></li>
				        <li class="disabled"><a href="#" onclick="javascript:goUrl(4);">考核公式信息</a></li>
      				</ul>
					<div class="row">
						<webTag:Text id="basiclaw_no" name="basiclaw_no" value='${rmHelper.returnParams.basiclaw_no}' displayLable="基本法代码" isdisplay="true" readonly="true"/>
						<webTag:Text id="impmeans_no" name="impmeans_no" value='${rmHelper.returnParams.impmeans_no}' displayLable="子基本法代码" isdisplay="true" readonly="true"/>
						<webTag:Text id="impmeans_name" name="impmeans_name" value='${rmHelper.returnParams.impmeans_name}' displayLable="子基本法名称" isdisplay="true"/>
					</div><!-- /.row -->
					
					<div class="row">                          
                        <webTag:Date id="start_date" name="start_date" value='${rmHelper.returnParams.start_date}' displayLable="基本法开始日期" isdisplay="true"/>
                        <webTag:Date id="end_date" name="end_date" value='${rmHelper.returnParams.end_date}' displayLable="基本法结束日期" isdisplay="true"/>
                        <webTag:DynamicSelectTag src="YNStatusSelect" name="data_flag" id="data_flag" value='${rmHelper.returnParams.data_flag}' displayLable="是否实施"></webTag:DynamicSelectTag>
					</div>
					<div class="row">    
					    <webTag:TextareaTag rows ='5' id="memo" name="memo" value='${rmHelper.returnParams.memo}' displayLable="说明信息:"/>
					</div>
					</fieldset>	
				    <div class="row" style="text-align:right;">
			    		<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
			    		<a class="btn btn-danger" href="<%=basePath %>/LMS/SubLawsManager/getSubLawsList.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
