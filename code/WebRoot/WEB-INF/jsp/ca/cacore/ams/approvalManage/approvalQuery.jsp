<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@ page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@ page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@ page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@ page import="com.newtouch.component.c11assistant.ServletHelper"%>
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
		<script >
			//分页 
			var linkUrl = "<%=basePath %>/Ams/ApprovalManage/approvalQuery.do";
			$(document).ready(function(){
				
				linkUrl = linkUrl+"?<%=ServletHelper.getHttpRequestQueryString(request)%>&nowPage=__id__&";
	            $("#Pagination").pagination(${rmHelper.pageCount.allRows}
	            							,{
	            								items_per_page:${rmHelper.pageCount.rows4Page}
	            							   ,num_display_entries:5
	            							   ,ellipse_text:'...'
	            							   ,current_page:${rmHelper.pageCount.nowPage}-1
	            							   ,link_to:linkUrl
	            							   ,callback: defaultQuery
	            							 });
	        });
			function defaultQuery(){
				document.queryForm.submit;
			}
			function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		     }

			//校验日期的先后顺序正确性
		    jQuery.validator.addMethod("checkDateOrder",function(value,element,param){
		    	var give_date=$("#"+param[0]).val();
		     	var valid_date=$("#"+param[1]).val();
		     	if(!isUndefined(give_date)&&!isUndefined(valid_date)){
		     		if(give_date>=valid_date){
		     			return false;
		     		}
		     	}
		     	$("label:contains('"+param[2]+"')").remove();
		     	return true;
		    },jQuery.validator.format("{2}"));
		    $(document).ready(function() {
		    	$("#queryForm").validate({
		    		rules : {
		    			startDate:{
			    			checkDateOrder:["startDate","endDate","事项申请时间顺序有误"]
			    		},
			    		endDate:{
			    			checkDateOrder:["startDate","endDate","事项申请时间顺序有误"]
			    		}
		    		}
		    	});
		    });
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>审批处理</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/Ams/ApprovalManage/approvalQuery.do" method="POST">
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
						<div class="row">
							<jsp:include page="../../util/branchTree.jsp" flush="true"/>
						</div>
						<div class="row">
						    <webTag:DynamicSelectTag src="approvalItemSelect" name="approval_item_code" id="approval_item_code" value='${rmHelper.returnParams.approval_item_code}' displayLable="审批事项"/>
							<webTag:Date name="startDate" id="startDate" value='${rmHelper.returnParams.startDate}' displayLable="事项申请时间 自"></webTag:Date>
							<webTag:Date name="endDate" id="endDate" value='${rmHelper.returnParams.endDate}' displayLable="到"></webTag:Date>
						</div>
					    <div class="row" style="text-align:right;">
					    	<button type="submit" class="btn btn-danger"  onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
						</div>
				</form>
			</div>
			<!-- 查询面板 end -->
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
							<th>操作</th>
							<th>序号</th>
							<th>机构代码</th>
							<th>机构名称</th>
							<th>审批事项</th>
							<th>事项申请时间</th>
							<th>审批描述</th>
							<th>审批状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sa" items="${rmHelper.returnMsg.dataList}" varStatus="index">
							<tr>
							    <td>
							    <c:if test="${sa.approval_item== '01'}">
							    <a class="btn btn-mini btn-dangerLight" href='<%=basePath%>/ams/salesEnterAppr/salesApprChuFa.do?approval_id=${sa.approval_id}&flag=0&seq_id=${sa.seq_id}'><i class="icon-leaf icon-white"></i>入司审批</a> 
							    </c:if>
							    <c:if test="${sa.approval_item== '02'}">
							    <a class="btn btn-mini btn-dangerLight" href='<%=basePath%>/ams/approvalManage/salesDisApprChuFa.do?approval_id=${sa.approval_id}&flag=0&seq_id=${sa.seq_id}'><i class="icon-leaf icon-white"></i>解约审批</a> 
							    </c:if>
							    <c:if test="${sa.approval_item== '03'}">
							    <a class="btn btn-mini btn-dangerLight" href='<%=basePath%>/ams/approvalManage/dismissResumeApprChuFa.do?approval_id=${sa.approval_id}&flag=0&seq_id=${sa.seq_id}'><i class="icon-leaf icon-white"></i>解约恢复审批</a> 
							    </c:if>
							    <c:if test="${sa.approval_item== '04'}">
							    <a class="btn btn-mini btn-dangerLight" href='<%=basePath%>/ams/approvalManage/recruitBatchApprView.do?approval_id=${sa.approval_id}&flag=0&seq_id=${sa.seq_id}'><i class="icon-leaf icon-white"></i>招募批次审批</a> 
							    </c:if>
							    <c:if test="${sa.approval_item== '05'}">
							    <a class="btn btn-mini btn-dangerLight" href='<%=basePath%>/ams/approvalManage/trainProApprView.do?approval_id=${sa.approval_id}&flag=0&seq_id=${sa.seq_id}'><i class="icon-leaf icon-white"></i>培训立项审批</a> 
							    </c:if>
							    <c:if test="${sa.approval_item== '06'}">
							    <a class="btn btn-mini btn-dangerLight" href='<%=basePath%>/ams/approvalManage/trainCourseApprView.do?approval_id=${sa.approval_id}&flag=0&seq_id=${sa.seq_id}'><i class="icon-leaf icon-white"></i>培训班课程审批</a> 
							    </c:if>
							    <c:if test="${sa.approval_item== '07'}">
							    <a class="btn btn-mini btn-dangerLight" href='<%=basePath%>/ams/approvalManage/announcementApprView.do?approval_id=${sa.approval_id}&flag=0&seq_id=${sa.seq_id}'><i class="icon-leaf icon-white"></i>公告审批</a> 
							    </c:if>
							    <c:if test="${sa.approval_item== '08'}">
							    <a class="btn btn-mini btn-dangerLight" href='<%=basePath%>/ams/approvalManage/lawContractApprView.do?approval_id=${sa.approval_id}&flag=0&seq_id=${sa.seq_id}'><i class="icon-leaf icon-white"></i>法律合同审批</a> 
							    </c:if>
							    <c:if test="${sa.approval_item== '09'}">
							    <a class="btn btn-mini btn-dangerLight" href='<%=basePath%>/ams/certificateAppr/certificateApprChuFa.do?approval_id=${sa.approval_id}&flag=0&seq_id=${sa.seq_id}'><i class="icon-leaf icon-white"></i>人员证件审批</a> 
							    </c:if>
							    </td>
							    <td>${index.index+1}</td>
								<td>${sa.branch_id}</td>
								<td>${sa.branch_name}</td>
								<td>${sa.approval_item_name}</td>
								<td>${sa.application_time}</td>
								<td>${sa.remark}</td>
								<td>${sa.approval_status_name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		 	</div>
			<div class="pagination pagination-centered">
			    <ul id="Pagination"></ul>
			</div>
		</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 底部高度填充块 结束-->
	</body>
</html>