<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%@page import="com.newtouch.organization.model.vo.IAgencyModel"%>
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
		<script type="text/javascript">
		 
		 function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
		 
		//验证 生效日期-终止日期  前后顺序正确性校验
		 jQuery.validator.addMethod("checkDateOrder1",function(value,element){
				var startdate=$("#startdate").val();
		     	var enddate=$("#enddate").val();
		     	if(!isUndefined(startdate)||!isUndefined(enddate)){
		     		var flag=false;
		     		if(!isUndefined(startdate)&&!isUndefined(enddate)&&(enddate>=value&&value>=startdate)){
		     			flag=true;
		     		}
		     		if(!isUndefined(startdate)&&isUndefined(enddate)&&(value>=startdate)){
		     			flag=true;
		     		}
		     		if(isUndefined(startdate)&&!isUndefined(enddate)&&(enddate>=value)){
		     			flag=true;
		     		}
		     	}
		     	if(!isUndefined(startdate)&&!isUndefined(enddate)){
		     		if(startdate>enddate){
		     			return false;
		     		}
		     	}
		     	$("label:contains('起始时间时间顺序有误')").remove();
		     	return true;
		    },"终止日期必须大于,等于生效日期");
	 	//验证 生效日期-终协议签订日期  前后顺序正确性校验
		jQuery.validator.addMethod("checkDateOrder2",function(value,element){
			debugger;
				var dateofsign=$("#dateofsign").val();
		     	var startdate=$("#startdate").val();
		     	if(!isUndefined(dateofsign)||!isUndefined(startdate)){
		     		var flag=false;
		     		if(!isUndefined(dateofsign)&&!isUndefined(startdate)&&(startdate>=value&&value>=dateofsign)){
		     			flag=true;
		     		}
		     		if(!isUndefined(dateofsign)&&isUndefined(startdate)&&(value>=dateofsign)){
		     			flag=true;
		     		}
		     		if(isUndefined(dateofsign)&&!isUndefined(startdate)&&(startdate>=value)){
		     			flag=true;
		     		}
		     	}
		     	if(!isUndefined(dateofsign)&&!isUndefined(startdate)){
		     		if(dateofsign>startdate){
		     			return false;
		     		}
		     	}
		     	$("label:contains('起始时间时间顺序有误')").remove();
		     	return true;
		    },"生效日期必须大于,等于签订日期");
	
   $(document).ready(function() {
		 $("#queryForm").validate({
			 rules:{
				 cpybranch_name:{//保险公司 非空
					 required:true,
				 },
				 branch_name:{//保险公司 非空
					 required:true,
				 },
				 startdate:{// 生效日期   从 Date 非空
					 required:true,
					 checkDateOrder1:[],
					  checkDateOrder2:[] 
				 },
				 enddate:{// 终止日期  从 Date 非空
					 required:true ,
					 checkDateOrder1:[] 
				 },
				 dateofsign:{//协议签订日期 Date 非空
					 required:true ,
				 }
			 },
		  onkeyup:false
		 });
   });
		 </script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 协议管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保险公司协议</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 新增</span>
			</div>
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/maas/Protocol/addProtocol.do" enctype="multipart/form-data" method="POST">
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
					<fieldset>
					<div class="row" >
				  		<jsp:include page="../../maas/protocol/protocolTree.jsp" flush="true"/>
				  		<webTag:Text id="status"  name="status"  value='保险专业代理委托合同' displayLable="协议类型" readonly="true" isdisplay="true"/>
						<jsp:include page="../../util/NewBranchTree.jsp" flush="true"/>

					</div>
					
					<div class="row" >
						<webTag:Date id="dateofsign"  name="dateofsign" value='${rmHelper.returnParams.dateofsign}'   displayLable="协议签订日期" dateFormat="yyyy-MM-dd" isdisplay="true"/>
						<webTag:Date id="startdate"   name="startdate"  value='${rmHelper.returnParams.startdate}'    displayLable="生效日期"    dateFormat="yyyy-MM-dd"   isdisplay="true"/>
						<webTag:Date id="enddate"     name="enddate"    value='${rmHelper.returnParams.enddate}'      displayLable="终止日期"    dateFormat="yyyy-MM-dd"   isdisplay="true"/>
					</div>
					
				    <div class="row" style="text-align:right;">
					    <button type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
					    <a class="btn btn-danger" href="<%=basePath %>/maas/Protocol/goProtocolList.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
				  </fieldset>	
				</form>
			</div>
		</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<div class="zeoBottomH90"></div>
		<div class="zeoBottomH90"></div>
		<div class="zeoBottomH90"></div>
		<!-- 底部高度填充块 结束-->
	</body>
</html>
