<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@ page import="com.newtouch.core.returnmsg.ReturnMsg"%>
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
	</head>
	<body>
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 保险公司管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保险公司管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保险公司机构明细</span>
			</div>
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="" method="POST">
					<div class="row">
						<webTag:Text id="branch_parentid" name="branch_parentid" value='${rmHelper.returnParams.branch_parentid}' displayLable="上级保险公司代码" isdisplay="true" readonly="true"/>
						<webTag:Text id="branch_parentname" name="branch_parentname" value='${rmHelper.returnParams.branch_parentname}' displayLable="上级保险公司名称" isdisplay="true" readonly="true"/>
						<webTag:Text id="branch_sortname" name="branch_sortname" value='${rmHelper.returnParams.branch_sortname}' displayLable="保险公司分类" isdisplay="true" readonly="true"/> <!-- zddxiu -->						
					</div>
					<div class="row">
					<webTag:Text id="branch_level" name="branch_level" value='${rmHelper.returnParams.branch_level}' displayLable="本公司等级" isdisplay="true" readonly="true"/>
						<webTag:Text id="branch_no" name="branch_no" value='${rmHelper.returnParams.branch_no}' displayLable="保险公司代码" isdisplay="true" readonly="true"/>
						<webTag:Text id="branch_cpyname" name="branch_cpyname" value='${rmHelper.returnParams.branch_cpyname}' displayLable="保险公司名称" isdisplay="true" readonly="true"/>
						
						
					</div>
					<div class="row">
					<webTag:Text id="insBranch_abbr" name="insBranch_abbr" value='${rmHelper.returnParams.branch_abbr}' displayLable="保险公司简称"  readonly="true"/>
					    <webTag:Text id="sales_org_name" name="sales_org_name" value='${rmHelper.returnParams.sales_org_name}' displayLable="所属中介公司机构" readonly="true"/>
						<webTag:Text id="statusA" name="statusA" value='${rmHelper.returnParams.statusA}' displayLable="状态"  isdisplay="true"  readonly="true"/>
						
						
					</div>
					<div class="row">
						<webTag:Text id="province" name="province" value='${rmHelper.returnParams.province}' displayLable="省"   readonly="true"/>
						<webTag:Text id="city" name="city" value='${rmHelper.returnParams.city}' displayLable="市"  readonly="true"/>
						<webTag:Text id="area" name="area" value='${rmHelper.returnParams.area}' displayLable="县"    readonly="true"/>
					</div>
					<div class="row">
					<webTag:Text id="found_date" name="found_date" value='${rmHelper.returnParams.found_date}' displayLable="成立日期" readonly="true"/><!-- isdisplay="true" -->
					   <webTag:Text id="delegate" name="delegate" value='${rmHelper.returnParams.delegate}' displayLable="法人代表:" readonly="true"/>
						<webTag:Text id="address" name="address" value='${rmHelper.returnParams.address}' displayLable="联系地址:" readonly="true"/>
						
						
					</div>
					<div class="row">
					<webTag:Text id="zip" name="zip" value='${rmHelper.returnParams.zip}' displayLable="邮政编码:" readonly="true"/>
					    <webTag:Text id="fax" name="fax" value='${rmHelper.returnParams.fax}' displayLable="公司传真:" readonly="true"/>
						<webTag:Text id="telephone" name="telephone" value='${rmHelper.returnParams.telephone}' displayLable="联系电话:" readonly="true"/>
                       
                        
                    </div>


					
					<div class="row" style="text-align:right;">
					  <a id="backButton" class="btn btn-danger" href="<%=basePath %>/mass/InsBranchManage/toInsBranchQuery.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
