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
			    			 firstDateJbb:{
		 					required:true
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
			var firstDateJbb = $('#firstDateJbb').val();
			$('#firstDateJbb').val(firstDateJbb);
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
			    <span><i class="icon-list icon-red"></i>基本情况表</span>
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
					<webTag:HiddenInputTag id="reportType" name="reportType" value='JBQKB'/>
					
					<div class="row" >
						<webTag:Text name="reportType1" id="reportType1" value='基本情况表' displayLable="报表类型" isdisplay="true" readonly="true"/>
					    <webTag:MonthDate id="firstDateJbb"   name="firstDateJbb"   value='${firstDateJbb}'   displayLable="统计月份" isdisplay="true" />
					</div>
					
					<div class="row" style="text-align:right;">
				       	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查看报表</button>
				    	<button type="button" class="btn btn-danger" onClick="exportInfo();" ><i class="icon-download icon-white"></i>导出报表</button>
						<a class="btn btn-danger" href="<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/rfm/insuranceReport/Report"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
				<!-- 导出传值form表单  start-->
				<form id="exportForm" action="<%=basePath%>/rfm/BasicSituation/exportBasicSituation.do" method="POST">
				    <webTag:HiddenInputTag id="firstDateJbb" name="firstDateJbb"  value='${firstDateJbb }'></webTag:HiddenInputTag>
				</form>
			</div>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
							<th>序号</th>
							<th>基本情况</th>
							<th>行次</th>
							<th>数值</th>
						</tr>
					</thead>
					<tbody id="commission_info">
						 <c:forEach var="oa" items="${rmHelper.returnMsg.dataList}" varStatus="index">
							   		<tr>	
								   		<td>${index.index+1}</td>
										<td>${oa.basic_station}</td>
										<td>${oa.line}</td>
										<td>${oa.num}</td>
									</tr>
					    </c:forEach>
					</tbody>
				</table>
			</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 底部高度填充块 结束-->
	</body>
</html>
