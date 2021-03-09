<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>

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
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 按钮返回控制 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
	    <script type="text/javascript">
	    $(function(){
	    	 $("#mainForm").validate({
	   			 onkeyup:false,
	   			 rules:{
	   				 insBranch_id:{
						 required:true
					 },
					 policy_code:{
						 required:true
					 },
					 branch_id:{
						 required:true 
					 },
					 bef_status:{
						 required:true
					 },
	   				 aft_status:{
	   					required:true,
	   					validateStatus:[]
	   				 }
	   			 }
	   		 });
	    });
	    //投保单状态：不能回退且保单进入正常有效状态不能变为首期待承保和待承保前撤件
	    //首期待承保 不能变为承保前撤件和保单进入正常有效状态
		jQuery.validator.addMethod("validateStatus",function(value,element){
		     	var bef_status=$("#bef_status").val();
		     	var aft_status=$("#aft_status").val();
		     	if(!isUndefined(bef_status) && !isUndefined(aft_status)){
		     		  //强制类型转换---字符串比较大小与数字不同
		     		  if(Number(bef_status) >= Number(aft_status) && bef_status!='53'){
		     			  //不可以回退的约束
			     		 return false;
			     	   }else if((bef_status !='14' || bef_status!='53') && (aft_status == '14' || aft_status == '53')){
			     		   //其他状态的投保单不可以变为保单进入正常有效状态和首期待承保
			     		   return false;
			     	   }else if(bef_status =='14' &&(aft_status =='53'||aft_status =='18')){
			     		   //首期带承保的投保单不可以变为保单进入正常有效状态和承保前撤件
			     		   return false;
			     	   }else if(bef_status =='53' && (aft_status =='22'||aft_status =='14')){
			     		   //保单进入正常有效状态的投保单不可以变为首期待承保和带承保前撤件
			     		   return false;
			     	   }else{
			     		   return true;
			     	   }
		     	}
		     	return true;
		     },"变更状态不对，不允许变更。");
	    
		  /*
	      *判断参数是否为未定义或null,如未定义或为null返回true
	      *@param paraValue 待处理字符串
	      *@return true或者false
	      */
	       function isUndefined(paraValue){
	        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
	        return false;
	        }
	    </script>
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount" id="mao">
			    <span class=mrl14><i class="icon-list icon-red"></i>保单管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>新契约管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>投保单状态变更</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>状态变更</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id= "mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/cbs/policyLifeManage/plfStatusModify.do" method="POST">
				   <fieldset>
				   <div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
				   <webTag:HiddenInputTag id="policy_id" name="policy_id" value="${rmHelper.returnParams.policy_id}" displayLable="保单号id"/>
					   <div class="row">
					      <webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" displayLable="保险公司机构" value="${rmHelper.returnParams.insBranch_id}" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
						  <webTag:Text id="send_code" name="send_code" value="${rmHelper.returnParams.send_code}" displayLable="所属投保单号"  isdisplay="true" readonly="true"/>
						 </div>
						<div class="row">
						  <webTag:Text id="branch_id" name="branch_id" value="${rmHelper.returnParams.branch_id}" displayLable="中介机构代码:"  readonly="true" />
						  <webTag:Text id="branch_name" name="branch_name" value="${rmHelper.returnParams.branch_name}" displayLable="机构名称:" readonly="true" />
						  <webTag:Text id="app_name" name="app_name" value="${rmHelper.returnParams.app_name}" displayLable="投保人:" readonly="true"  />
					    </div>
					    <div class="row">
					      <webTag:DynamicSelectTag src="policyStatusSelect" name="bef_status" id="bef_status" value="${rmHelper.returnParams.status}" displayLable="变更前状态" isdisplay="true" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					      <webTag:DynamicSelectTag src="policyStatusSelect" name="aft_status" id="aft_status" value="" parameter="6" isdisplay="true" displayLable="变更后状态"/>
					    </div>
					    </br>
						</br>
						</br>
						</br>
					    <div class="row" style="text-align:right;">
					    	<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
				    	    <a class="btn btn-danger" href="<%=basePath %>/cbs/policyLifeManage/queryPolicyLifeForStatus.do"><i class="icon-share-alt icon-white"></i>  返回</a>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</body>
</html>
