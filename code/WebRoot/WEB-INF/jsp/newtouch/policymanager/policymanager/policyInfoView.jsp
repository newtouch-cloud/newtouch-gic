<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<jsp:include page="/WEB-INF/jsp/newtouch/core/pub/jvbasecss.jsp" flush="true"/>
        <jsp:include page="/WEB-INF/jsp/newtouch/core/pub/jvbasejs.jsp" flush="true"/>
	    <!-- 回跳 -->
	    <jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/> 
	</head>
	<body>
	
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
				<span class=mrl14><i class="icon-list icon-red"></i>保单管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保单信息查询</span><span class="divider"></span>
				<span><i class="icon-list icon-red"></i>明细</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 添加信息数据区 start -->
			<div class="row-fluid">
				<form class="form-horizontal alert alert-info fade in span12" action="" method="POST">
				    <!-- 提示信息标签 -->
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
				    <fieldset>
				    <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 保单明细</span>
					</div>
					<div class="row">
						<webTag:Text id="province" name="province"
							value='${rmHelper.returnParams.province}' displayLable="省份"
							readonly="true" isdisplay="true"></webTag:Text>
						<webTag:Text id="city" name="city"
							value='${rmHelper.returnParams.city}'
							displayLable="市级名称" readonly="true" isdisplay="true"></webTag:Text>
						<webTag:Text id="area" name="area"
							value='${rmHelper.returnParams.area}' displayLable="区县名称"
							readonly="true" isdisplay="true"></webTag:Text>
					</div>
					<div class="row">
						<webTag:Text id="branch_name" name="branch_name"
							value='${rmHelper.returnParams.branch_name}' displayLable="机构名称"
							readonly="true" isdisplay="true"></webTag:Text>
						<webTag:Date id="branch_id" name="branch_id"
							value='${rmHelper.returnParams.branch_id}' displayLable="机构代码"
							readonly="true" isdisplay="true" />
						<webTag:Text id="person_name" name="person_name"
							value='${rmHelper.returnParams.person_name}' displayLable="归属人姓名"
							readonly="true" isdisplay="true"></webTag:Text>
					</div>
					<div class="row">
						<webTag:Text id="person_no" name="person_no"
							value='${rmHelper.returnParams.person_no}' displayLable="归属人工号"
							readonly="true" isdisplay="true" />
						<webTag:Text name="statistics_type" id="statistics_type"
							value='${rmHelper.returnParams.statistics_type}' displayLable="统计口径"
							isdisplay="true" readonly="true"></webTag:Text>
					</div>
					<div class="row">
						<webTag:Text name="channel_type" id="channel_type"
							value='${rmHelper.returnParams.channel_type}'
							displayLable="渠道类型" isdisplay="true" readonly="true" />
						<webTag:Text name="channel_no" id="channel_no"
							value='${rmHelper.returnParams.channel_no}' displayLable="渠道码"
							isdisplay="true" readonly="true" />
						<webTag:Text name="insurance_type_name" id="insurance_type_name"
							value='${rmHelper.returnParams.insurance_type_name}' displayLable="险种类别"
							isdisplay="true" readonly="true" />
					</div>
					<div class="row">
						<webTag:Text name="insurance_name" id="insurance_name"
							value='${rmHelper.returnParams.insurance_name}'
							displayLable="险种名称" readonly="true"></webTag:Text>
						<webTag:Text name="product_name" id="product_name"
							value='${rmHelper.returnParams.product_name}' displayLable="产品名称"
							readonly="true" isdisplay="true"></webTag:Text>
						<webTag:Text name="sign_date" id="sign_date"
							value='${rmHelper.returnParams.sign_date}' displayLable="签单日期"
							readonly="true" isdisplay="true"></webTag:Text>
					</div>
					<div class="row">
						<webTag:Text name="accomplish_date" id="accomplish_date"
							value='${rmHelper.returnParams.accomplish_date}' displayLable="成单日期"
							isdisplay="true" readonly="true"></webTag:Text>
						<webTag:Text name="statistics_date" id="statistics_date"
							value='${rmHelper.returnParams.statistics_date}' displayLable="统计日期"
							isdisplay="true" readonly="true"></webTag:Text>
						<webTag:Text id="insure_no" name="insure_no"
							value='${rmHelper.returnParams.insure_no}' displayLable="投保单号:"
							readonly="true" />
					</div>


					<div class="row">
						<webTag:Text id="policy_no" name="policy_no"
							value='${rmHelper.returnParams.policy_no}' displayLable="保单号:"
							readonly="true" />
						<webTag:Text id="applicant_name" name="applicant_name"
							value='${rmHelper.returnParams.applicant_name}' displayLable="投保人:"
							readonly="true"></webTag:Text>
						<webTag:Text id="insured_name" name="insured_name"
							value='${rmHelper.returnParams.insured_name}'
							displayLable="被保险人:" readonly="true" />
					</div>
					<div class="row">
						<webTag:Text id="premium" name="premium"
							value='${rmHelper.returnParams.premium}'
							displayLable="保费:" readonly="true" />
						<webTag:Text id="LPN" name="LPN"
							value='${rmHelper.returnParams.LPN}' displayLable="车牌号:"
							readonly="true"></webTag:Text>
						<webTag:Text id="rack_no" name="rack_no"
							value='${rmHelper.returnParams.rack_no}' displayLable="车架号:"
							readonly="true" />
					</div>
					<div class="row">
						<webTag:Text id="repair_coding" name="repair_coding" 
                              value='${rmHelper.returnParams.repair_coding}'
								displayLable="推荐维修码:" readonly="true"></webTag:Text>
						<webTag:Text name="check_date" id="check_date"
							value='${rmHelper.returnParams.check_date}'
							displayLable="核保日期" readonly="true" isdisplay="true"></webTag:Text>
						<webTag:Text name="begin_date" id="begin_date"
							value='${rmHelper.returnParams.begin_date}' displayLable="起保日期"
							readonly="true" isdisplay="true"></webTag:Text>
					</div>
					<div class="row">
						<webTag:Text name="end_date" id="end_date"
							value='${rmHelper.returnParams.end_date}' displayLable="终保日期"
							readonly="true" isdisplay="true"></webTag:Text>
						<webTag:Text name="approval_date" id="approval_date"
							value='${rmHelper.returnParams.approval_date}' displayLable="批单日期"
							readonly="true" isdisplay="true"></webTag:Text>
						<webTag:Text id="service_charge" name="service_charge"
							value='${rmHelper.returnParams.service_charge}'
							displayLable="中介公司跟单手续费比例" readonly="true" isdisplay="true" />
					</div>
					<div class="row">
						<webTag:Text id="order_pay" name="order_pay"
							value='${rmHelper.returnParams.order_pay}'
							displayLable="中介公司跟单手续费" readonly="true" isdisplay="true" />
						<webTag:Text id="clean_charge" name="clean_charge"
							value='${rmHelper.returnParams.clean_charge}'
							displayLable="已结算手续费金额:" readonly="true" />
						<webTag:Text id="wei_rate" name="wei_rate"
							value='${rmHelper.returnParams.wei_rate}'
							displayLable="代理人佣金比例" readonly="true" isdisplay="true" />
					</div>

					<div class="row">
						<webTag:Text id="commission"
							name="commission"
							value='${rmHelper.returnParams.commission}'
							displayLable="代理人佣金:" readonly="true" />
						<webTag:Text id="cash"
							name="cash"
							value='${rmHelper.returnParams.cash}'
							displayLable="提现金额:" readonly="true" />
					</div>
					</fieldset>
					<div class="row" align="right">
			        	<a id="backButton" class="btn btn-danger" href="<%=basePath %>/PolicyManager/goPolicyList.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>