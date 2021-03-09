<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.ServletHelper"%>
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
        <script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery.form.js"></script>
	    <!-- 回跳 -->
	    <jsp:include page="../../pub/backPageHelper.jsp" flush="true"/> 
	<script type="text/javascript">
    
    var linkUrl = "<%=basePath %>/AMS/MessagePushManagerController/queryMessagePush.do";
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
    </script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 消息推送管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 消息推送查询</span><span class="divider">/</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/MessagePushManagerController/queryMessagePush.do" method="POST">
					<div class="row">
						<webTag:Text id="task_type" name="task_type"  value='${rmHelper.returnParams.task_type}' displayLable="类型代码" />
						<webTag:Text id="task_name" name="task_name" value='${rmHelper.returnParams.task_name}' displayLable="类型名称"/>
					</div>	
					
				    <div class="row" style="text-align:right;">
				      <button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>			       
					</div>
				</form>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<table class="table table-striped table-bordered bootstrap-datatable datatable ">
					<thead>
						<tr>
						    <th>操作</th>
							<th>序号</th>
							<th>类型代码</th>
							<th>类型名称</th>
							<th>任务状态</th>
							<th>任务标题</th>
							<th>任务内容</th>
							<th>任务内容相关号码</th>
							<th>任务提醒开始日期</th>
							<th>任务提醒失效时间</th>
							<th>任务接收人</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
							<tr>
							    <td>						
                                    <c:if test="${sm.proce_status=='0'}">
							             <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/AMS/MessagePushManagerController/messagePushModify.do?taskid=${sm.get("taskid")}'><i class="icon-pencil icon-white"></i>修改</a>
									     <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/AMS/MessagePushManagerController/messagePushInfo.do?taskid=${sm.get("taskid")}'><i class="icon-zoom-in icon-white"></i>明细</a>
							        </c:if>
							        <c:if test="${sm.proce_status=='1'}">
									    <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/AMS/MessagePushManagerController/messagePushInfo.do?taskid=${sm.get("taskid")}'><i class="icon-zoom-in icon-white"></i>明细</a>
						            </c:if> 
							    </td>     								
								<td>${number.index+1}</td>
								<td>${sm.task_type}</td>
								<td>${sm.task_name}</td>
								<c:if test="${sm.proce_status=='0'}">
								<td>未处理</td>
								</c:if>
								<c:if test="${sm.proce_status=='1'}">
								<td>已处理</td>
								</c:if>															
								<td>${sm.task_title}</td>
								<td>${sm.task_content}</td>
								<td>${sm.task_object_id}</td>
								<td>${sm.task_proce_date}</td>
								<td>${sm.task_failure}</td>
								<td>${sm.task_sales_id}</td>							
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			<div class="pagination pagination-centered">
				    <ul id="Pagination"></ul>
			</div>
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
