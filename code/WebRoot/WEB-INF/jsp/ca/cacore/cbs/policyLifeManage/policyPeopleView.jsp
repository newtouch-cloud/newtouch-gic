<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
	ReturnMsgHelper returnHepler = (ReturnMsgHelper)request.getAttribute("returnHepler");
	if(returnHepler==null){ returnHepler = new ReturnMsgHelper(request);}

%>
<!DOCTYPE html>
<html lang="en">
<base target="_self"></base>
	<head>
		<title>新致金保通</title>
		
		
		
		
		
	
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean-ZM.css" >
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/jquery-ui-1.10.3.custom.css" >
		
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jqueryui/jquery-1.9.1.js"></script>
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/webstyle/js/jquery-ui-1.10.3.custom.js"></script>
		<script language="javascript" type="text/javascript" src="<%=basePath %>/compent/datepicker/WdatePicker.js"></script>
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		
		<!-- fram plugins start--> 
		<script type="text/javascript" src="<%=basePath %>/compent/pagination/jquery.pagination.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/default/js/base.js"></script>
		<script >
		$(function() {
			//修改时带出生日及年龄
	         var    obj    =    window.dialogArguments  ;
			 $("#age").val(obj.age_value);//
		  });   
		       
	     function setDateAndAge(value){
	 		//获取输入身份证号码 
	       	var UUserCard = value; 
	       	//获取出生日期 
	       	$('#birthday').val(UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14)); 
	       	
	       	//获取年龄 
	       	var myDate = new Date(); 
	       	var month = myDate.getMonth() + 1; 
	       	var day = myDate.getDate();
	       	var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1; 
	       	if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) { 
	       	age++; 
	       	} 
	       	$('#age').val(age);
	 	  }	 
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<!-- 面包屑导航  end -->
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/FuncButton/funcButtonAdd.do" method="POST">
				 <webTag:HiddenInputTag id="policy_tr_id" name="policy_tr_id" value='${rmHelper.returnParams.policy_tr_id}' displayLable="投保人id" />
					<div class="row">
						<webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="投保人姓名" isdisplay="true" readonly="true"/>
						<webTag:Text id="name_tow" name="name_tow" value='${rmHelper.returnParams.name}' displayLable="投保人姓名（二录）" isdisplay="true" readonly="true"/>
						<webTag:DynamicSelectTag src="applicantRelation1Select" name="relation" id="relation" value='${rmHelper.returnParams.relation}' displayLable="与主被保险人关系" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
					    <webTag:DynamicSelectTag src="certiTypeSelect" name="certi_type" id="certi_type" value='${rmHelper.returnParams.certi_type}' isdisplay="true" displayLable="投保人证件类型" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:Text id="certi_no" name="certi_no" value='${rmHelper.returnParams.certi_no}' displayLable="投保人证件号码" isdisplay="true" readonly="true" />
						<webTag:Text id="certi_no_tow" name="certi_no" value='${rmHelper.returnParams.certi_no}' displayLable="投保人证件号码（二录）" isdisplay="true" readonly="true"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="genderSelect"  id="gender" name="gender" value='${rmHelper.returnParams.gender}' isdisplay="true"  displayLable="性别" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:Date id="birthday" name="birthday" value='${rmHelper.returnParams.birthday}' displayLable="出生日期" readonly="true" isdisplay="true"/>
						<webTag:DynamicSelectTag src="nationalitySelect"  id="nationality" name="nationality" value='${rmHelper.returnParams.nationality}'  displayLable="国籍:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
						<webTag:Date id="certi_validDate" name="certi_validDate" value='${rmHelper.returnParams.certi_validdate}' displayLable="证件有效期限至" isdisplay="true" readonly="true" />
						<webTag:Text id="age" name="age" value='${rmHelper.returnParams.age}' displayLable="年龄" isdisplay="true" readonly="true"/>
					    <webTag:DynamicSelectTag src="customerIncomTypeSelect"  id="income_type" name="income_type" value='${rmHelper.returnParams.income_type}'  displayLable="平均年收入（过去三年）:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
						<webTag:DynamicSelectTag src="nationSelect"  id="nation" name="nation" value='${rmHelper.returnParams.nation}'  displayLable="民族" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						<webTag:Text id="homeplace" name="homeplace" value='${rmHelper.returnParams.homeplace}' displayLable="籍贯:" readonly="true"/>
						<webTag:DynamicSelectTag src="maritalSelect" name="marital_stat" id="marital_stat" value='${rmHelper.returnParams.marital_stat}' displayLable="婚姻状况" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
					    <webTag:DynamicSelectTag src="politicalSelect"  id="political" name="political" value='${rmHelper.returnParams.political}'  displayLable="政治面貌" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
                        <webTag:DynamicSelectTag src="educationSelect" name="education2" id="education2" value='${rmHelper.returnParams.education2}' displayLable="教育程度" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
                        <webTag:DynamicSelectTag src="healthSelect" name="health" id="health" value='${rmHelper.returnParams.health}' displayLable="健康状况:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div>
					<div class="row">
						<webTag:Text id="height" name="height" value='${rmHelper.returnParams.height}' displayLable="身高:" readonly="true"/>
						<webTag:Text id="weight" name="weight" value='${rmHelper.returnParams.weight}' displayLable="体重kg:" readonly="true"/>
						<webTag:DynamicSelectTag  src="hignPolicySelect" id="is_telMsgService" name="is_telMsgService" value='${rmHelper.returnParams.is_telmsgservice}'  displayLable="是否接受手机短信服务:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"></webTag:DynamicSelectTag>
					</div>
					<div class="row">
						<webTag:Text id="mobile" name="mobile" value='${rmHelper.returnParams.mobile}' displayLable="移动电话:" readonly="true"/>
						<webTag:Text id="telphone" name="telphone" value='${rmHelper.returnParams.telphone}' displayLable="住宅电话:" readonly="true"/>
						<webTag:Text id="fax" name="fax" value='${rmHelper.returnParams.fax}' displayLable="传真电话:" readonly="true"/>
					</div>
					<div class="row">
						<webTag:Text id="email" name="email" value='${rmHelper.returnParams.email}' displayLable="电子邮箱:" readonly="true"/>
						<webTag:Text id="address" name="address" value='${rmHelper.returnParams.address}' displayLable="家庭住址:" readonly="true"/>
						<webTag:Text id="zip" name="zip" value='${rmHelper.returnParams.zip}' displayLable="邮政编码:" readonly="true"/>
					</div>
					<div class="row">
						<webTag:Text id="job_com" name="job_com" value='${rmHelper.returnParams.job_com}' displayLable="工作单位名称:" readonly="true"/>
						<webTag:Text id="job_code" name="job_code" value='${rmHelper.returnParams.job_code}' displayLable="职业代码:" readonly="true"/>
						<webTag:Text id="job_tel" name="job_tel" value='${rmHelper.returnParams.job_tel}' displayLable="办公电话:" readonly="true"/>
					</div>
				 <div class="row" style="text-align:right;">
				   <button type="button" onclick="window.close()" class="btn btn-danger"><i class="icon-share-alt icon-white"></i>返回</button>
				 </div>
				</form>
			</div>
		</div>
	</body>
</html>