<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
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
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批明细查询</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>入司审批明细</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 添加信息数据区 start -->
			<div class="row-fluid">
				<form class="form-horizontal alert alert-info fade in span12" action="" method="POST">
				    <!-- 提示信息标签 -->
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
				    <fieldset>
				    <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 审批信息</span>
					</div>
				    <div class="row">
						<webTag:Text id="application_time" name="application_time" value='${rmHelper.returnParams.application_time}' displayLable="审批申请日期:" readonly="true"></webTag:Text>
						<webTag:Text id="approval_time" name="approval_time" value='${rmHelper.returnParams.approval_time}' displayLable="审批日期:" readonly="true"></webTag:Text>
						<webTag:DynamicSelectTag src="approvalStatusSelect" name="approval_status" id="approval_status" value='${rmHelper.returnParams.approval_status}' displayLable="审批状态:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 基本信息</span>
					</div>
				    <div class="row">
						<webTag:Text id="sales_id" name="sales_id" value='${rmHelper.returnParams.sales_id}' displayLable="人员代码" readonly="true" isdisplay="true"></webTag:Text>
						<webTag:DynamicSelectTag src="salesStatusSelect" name="salesStatus" id="salesStatus" value='${rmHelper.returnParams.sales_status}' displayLable="人员状态" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:Text id="sales_name" name="sales_name" value='${rmHelper.returnParams.sales_name}' displayLable="人员姓名" readonly="true" isdisplay="true"></webTag:Text>
						<webTag:DynamicSelectTag src="genderSelect" name="sex" id="sex" value='${rmHelper.returnParams.sex}' displayLable="性别" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
						<webTag:Date id="birthday" name="birthday" value='${rmHelper.returnParams.birthday}' displayLable="出生日期" readonly="true" isdisplay="true"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="certiTypeSelect" name="certi_type" id="certi_type" value='${rmHelper.returnParams.certi_type}' displayLable="证件类型" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
						<webTag:Text id="certi_no" name="certi_no" value='${rmHelper.returnParams.certi_no}' displayLable="证件号码" readonly="true" isdisplay="true"/>
					 	<webTag:DynamicSelectTag src="nationSelect" name="nation" id="nation" value='${rmHelper.returnParams.nation}' displayLable="民族" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="provinceSelect" name="province" id="province" value='${rmHelper.returnParams.province}' displayLable="籍贯省" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
						<webTag:DynamicSelectTag src="citySelect" name="city" id="city" value='${rmHelper.returnParams.city}' displayLable="籍贯市" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
					 	<webTag:Text id="domicile" name="domicile" value='${rmHelper.returnParams.domicile}' displayLable="户口所在地" readonly="true" isdisplay="true"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="politicalSelect" name="political" id="political" value='${rmHelper.returnParams.political}' displayLable="政治面貌" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
						<webTag:DynamicSelectTag src="maritalSelect" name="marital_stat" id="marital_stat" value='${rmHelper.returnParams.marital_stat}' displayLable="婚姻状况" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
					 	<webTag:DynamicSelectTag src="healthSelect" name="health" id="health" value='${rmHelper.returnParams.health}' displayLable="健康状况:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="educationSelect" name="education" id="education" value='${rmHelper.returnParams.education}' displayLable="教育程度" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
						<webTag:DynamicSelectTag src="contractTypeSelect" name="contract_type" id="contract_type" value='${rmHelper.returnParams.contract_type}' displayLable="合同类型" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 行政信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton2"></div>
						</div>
					</div>
				    <div class="Shrinkcontent" id="Shrinkcontent2">	
						<div class="row">
							<webTag:Text id="team_id" name="team_id" value='${rmHelper.returnParams.team_id}' displayLable="团队代码" readonly="true" isdisplay="true"/>
						 	<webTag:Text id="team_name" name="team_name" value='${rmHelper.returnParams.team_name}' displayLable="团队名称:" readonly="true"/>
						 	<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="所属机构名称:" readonly="true"/>
						</div>
						<div class="row">
							<webTag:Text id="recommend_id" name="recommend_id" value='${rmHelper.returnParams.recommend_id}' displayLable="推荐人代码:" readonly="true"></webTag:Text>
							<webTag:Text id="recommend_name" name="recommend_name" value='${rmHelper.returnParams.recommend_name}' displayLable="推荐人姓名:" readonly="true"></webTag:Text>
							<webTag:DynamicSelectTag src="YNStatusSelect" name="is_resigned" id="is_resigned" value='${rmHelper.returnParams.is_resigned}' displayLable="是否再次签约:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
						</div>
						<div class="row">
							<webTag:DynamicSelectTag src="channelPostSelect" name="post_code" id="post_code" value='${rmHelper.returnParams.post_code}' displayLable="当前职位" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"></webTag:DynamicSelectTag>
							<webTag:Text name="rank_name" id="rank_name" value='${rmHelper.returnParams.rank_name}' displayLable="当前职级" readonly="true" isdisplay="true"></webTag:Text>
							<webTag:HiddenInputTag name="rank_id" id="rank_id" value='${rmHelper.returnParams.rank_id}'/>
							<webTag:DynamicSelectTag src="rankSeriesSelect" name="rankSeries_code" id="rankSeries_code" value='${rmHelper.returnParams.rankSeries_code}' displayLable="当前职级序列:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
						</div>
						<div class="row">
							<webTag:Date id="probation_date" name="probation_date" value='${rmHelper.returnParams.probation_date}' displayLable="入司日期" readonly="true" isdisplay="true"/>
							<webTag:Date id="assess_start_date" name="assess_start_date" value='${rmHelper.returnParams.assess_start_date}' displayLable="开始考核日期" readonly="true" isdisplay="true"/>
							<webTag:Date id="full_memeber_date" name="full_memeber_date" value='${rmHelper.returnParams.full_memeber_date}' displayLable="转正日期:" readonly="true"/>
						</div>
						<div class="row">
							<webTag:Date id="duty_date" name="duty_date" value='${rmHelper.returnParams.duty_date}' displayLable="任职日期:" readonly="true"/>
							<webTag:Date id="predismissal_date" name="predismissal_date" value='${rmHelper.returnParams.predismissal_date}' displayLable="解约日期:" readonly="true"/>
						</div>
					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 证件信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton3"></div>
						</div>
					</div>
				    <div class="Shrinkcontent" id="Shrinkcontent3">	
						<div class="row">
							<webTag:Text id="certificate_id" name="certificate_id" value='${rmHelper.returnParams.certificate_id}' displayLable="资格证号码" readonly="true" isdisplay="true"/>
							<webTag:Date id="cer_give_date" name="cer_give_date" value='${rmHelper.returnParams.cer_give_date}' displayLable="发证日期:" readonly="true"/>
							<webTag:Date id="cer_valid_date" name="cer_valid_date" value='${rmHelper.returnParams.cer_valid_date}' displayLable="有效截止日期:" readonly="true"/>
						</div>
						
					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 联系信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton4"></div>
						</div>
					</div>
				    <div class="Shrinkcontent" id="Shrinkcontent4">	
						<div class="row">
							<webTag:Text id="home_address" name="home_address" value='${rmHelper.returnParams.home_address}' displayLable="居住住址" readonly="true" isdisplay="true"></webTag:Text>
							<webTag:Text id="home_zipcode" name="home_zipcode" value='${rmHelper.returnParams.home_zipcode}' displayLable="邮政编码" readonly="true" isdisplay="true"/>
							<webTag:Text id="mobile" name="mobile" value='${rmHelper.returnParams.mobile}' displayLable="手机" readonly="true" isdisplay="true"></webTag:Text>
						</div>
						<div class="row">
							<webTag:Text id="fixed_line" name="fixed_line" value='${rmHelper.returnParams.fixed_line}' displayLable="固定电话:" readonly="true"/>
						 	<webTag:Text id="email" name="email" value='${rmHelper.returnParams.email}' displayLable="电子邮件:" readonly="true"/>
						</div>
					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 教育信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton5"></div>
						</div>
					</div>
				    <div class="Shrinkcontent" id="Shrinkcontent5">	
						<div class="row">
							<webTag:Text id="graduate_school" name="graduate_school" value='${rmHelper.returnParams.graduate_school}' displayLable="毕业院校:" readonly="true"></webTag:Text>
							<webTag:Text id="degree" name="degree" value='${rmHelper.returnParams.degree}' displayLable="学位:" readonly="true"/>
						 	<webTag:Text id="major" name="major" value='${rmHelper.returnParams.major}' displayLable="专业:" readonly="true"/>
						</div>
					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 从业信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton6"></div>
						</div>
					</div>
				    <div class="Shrinkcontent" id="Shrinkcontent6">	
					    <div class="row">
							<webTag:DynamicSelectTag src="jobTypeSelect" name="old_job" id="old_job" value='${rmHelper.returnParams.old_job}' displayLable="原职业:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
							<webTag:Text id="old_company" name="old_company" value='${rmHelper.returnParams.old_company}' displayLable="原工作单位:" readonly="true"/>
						 	<webTag:Text id="work_experience" name="work_experience" value='${rmHelper.returnParams.work_experience}' displayLable="工作经验:" readonly="true"/>
					    </div>
					    <div class="row">
							<webTag:Date id="work_date" name="work_date" value='${rmHelper.returnParams.work_date}' displayLable="工作时间:" readonly="true"/>
							<webTag:Text id="biz_years" name="biz_years" value='${rmHelper.returnParams.biz_years}' displayLable="保险从业年数:" readonly="true"/>
						 	<webTag:DynamicSelectTag src="YNStatusSelect" name="is_samevocation" id="is_samevocation" value='${rmHelper.returnParams.is_samevocation}' displayLable="是否同业:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					    </div>
				    </div>
				    <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 银行账户信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton7"></div>
						</div>
					</div>
				    <div class="Shrinkcontent" id="Shrinkcontent7">
					    <div class="row">
					    	<webTag:Text id="bank_code" name="bank_code" value='${rmHelper.returnParams.bank_code}' displayLable="开户行" readonly="true" isdisplay="true"/>
						 	<webTag:Text id="bank_account_no" name="bank_account_no" value='${rmHelper.returnParams.bank_account_no}' displayLable="银行账户" readonly="true" isdisplay="true"/>
						 	<webTag:Text id="bank_account_name" name="bank_account_name" value='${rmHelper.returnParams.bank_account_name}' displayLable="银行开户人" readonly="true" isdisplay="true"/>
					    </div>
				    </div>
				    <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 审批意见</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton8"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent8">
						<div class="row">
							<webTag:TextareaTag id="approval_views" name="approval_views"  rows='5'  value='${rmHelper.returnParams.approval_views}' displayLable="审批意见:" readonly="true"/>
						</div>
					</div>
					</fieldset>
					<div class="row" align="right">
			        <a class="btn btn-danger" href="<%=basePath %>/Ams/ApprovalManage/approvalViewQuery.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
		<!--底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!--底部高度填充块 结束-->
	</body>
</html>
