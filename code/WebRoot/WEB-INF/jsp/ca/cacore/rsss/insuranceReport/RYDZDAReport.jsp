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
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/> 
		<script>
				$(document).ready(function() {
				    $("#mainForm").validate({
						 rules:{
							 branch_name : {  //机构名称
			    					required : true
			    			 }
						 },
						 onkeyup:false
					 });
			     });
			   
		
		 function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
				function defaultQuery(){
				document.queryForm.submit;
			}
		
		//导出查询数据
		function exportInfo(){
			//获取查询条件的值
			var branch_id = $('#branch_id').val();
				//为隐藏域赋值
				$('#branch_id').val(branch_id);
				//提交表单
				$('#exportForm').submit();
			
		}
		</script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>报表管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保险代理机构人员电子档案</span><span class="divider">/</span>
			</div>
			<!-- 数据区 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" enctype="multipart/form-data" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/RFM/Report/queryReport.do" method="POST">
					<!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
						<image id="dialog_img" >${rmHelper.msgStr}</image>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag id="reportType" name="reportType" value='RYDZDA'/>

					
					<div class="row">
						<webTag:Text  name="reportType1" id="reportType1" value='保险代理机构人员电子档案' displayLable="报表类型" isdisplay="true" readonly="true"/>
<%-- 						<jsp:include page="branchTreeRequired.jsp" flush="true"/> --%>
						<jsp:include page="branchTree4Level2.jsp" flush="true"/>
					</div>
					
					
					<div class="row" style="text-align:right;">
				       	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查看报表</button>
				    	<button type="button" class="btn btn-danger" onClick="exportInfo();" ><i class="icon-download icon-white"></i>导出报表</button>
						<a class="btn btn-danger" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/rfm/insuranceReport/Report"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
				<!-- 导出传值form表单  start-->
				<form id="exportForm" action="<%=basePath %>/rfm/InsAgenPersonReport/exportPersonReport.do" method="POST">
		            <webTag:HiddenInputTag id="branch_id" name="branch_id"  value='${rmHelper.returnParams.branch_id }'></webTag:HiddenInputTag>
		            <webTag:HiddenInputTag id="branch_name" name="branch_name"  value='${rmHelper.returnParams.branch_name }'></webTag:HiddenInputTag>
				</form>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
							<th>序号</th>
							<th>员工编号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>身份证号</th>
							<th>职务</th>
							<th>劳动合同号或代理合同号</th>
							<th>劳动合同号或代理合同号签订时间</th>
							<th>资格证号</th>
							<th>资格证获取时间</th>
							<th>执业证号</th>
							<th>离司时间</th>
						</tr>
					</thead>
					<tbody>
					    <c:forEach var="oa" items="${rmHelper.returnMsg.dataList}" varStatus="index" >
							   		<tr>	
										<td>${index.index+1}</td>
												<td>${oa.sales_id}</td>
												<td>${oa.sales_name}</td>
												<td>${oa.gender}</td>
												<td>${oa.iden_card}</td>
												<td>${oa.post}</td>
												<td>${oa.labour_code}</td>
												<td>${oa.labour_code_time}</td>
												<td>${oa.certificate}</td>
												<td>${oa.certificate_date}</td>
												<td>${oa.certificate_no}</td>
												<td>${oa.department_time}</td>
									</tr>	
					    </c:forEach>
					</tbody>
				</table>
			</div>
			
		</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 底部高度填充块 结束-->
	</body>
</html>
