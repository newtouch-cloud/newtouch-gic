<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%@page import="com.newtouch.component.c11assistant.ServletHelper"%>

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
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
	<script >
		var linkUrl = "<%=basePath %>/maas/Protocol/queryProtocolList.do";
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
		
		function invalid(){
	 		return confirm("确定要注销该协议吗？");
		}
		function add(){
				location.href="<%=basePath %>/maas/Protocol/addProtocolView.do";
			}
		
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 协议管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保险公司协议</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 查询</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" >
				<div class="Shrinkcontent" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/maas/Protocol/queryProtocolList.do" method="POST">
						<div class="row" >
							<jsp:include page="../../maas/protocol/protocolTree.jsp" flush="true"/>
						</div>
	
				    <div class="row" style="text-align:right;">
				    	<button type="submit" class="btn btn-danger" onClick="defaultQuery()" ><i class="icon-search icon-white"></i>查询</button>
				    	<button name="resetting"  id="newreset"  type="button" class="btn btn-danger" >重置</button>
<!-- 				    	<button type="button" class="btn btn-danger" onClick="importInfo();" ><i class="icon-download-alt icon-white"></i>导出</button> -->
				    	<button type="button" class="btn btn-danger" onclick="add();"><i class="icon-plus icon-white"></i>新增</button> 
					</div>
				</form>
				</div>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid" >
				<table class="table table-striped table-bordered bootstrap-datatable datatable ">
					<thead>
						<tr>
							<th class="FixedTd">操作</th>
							<th class="FixedTd">序号</th>
							<th>保险公司</th>
							<th>保险公司协议号</th>
							<th>中介公司</th>
							<th>协议签订日期</th>
							<th>协议有效起期</th>
							<th>协议有效止期</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="cm" items="${rmHelper.returnMsg.dataList}" varStatus="index" >
							<tr>
								<td class="FixedTd">
									<a  class="btn btn-mini btn-dangerLight" href='<%=basePath %>/maas/Protocol/goProtocolModifyView.do?agreement_no=${cm.agreement_no}'><i class="icon-zoom-in icon-white"></i>明细</a>
									<c:if  test="${cm.status=='1'}">
									 	<a  class="btn btn-mini btn-dangerLight" href='<%=basePath %>/maas/Protocol/toModifyProtocol.do?agreement_no=${cm.agreement_no}'><i class="icon-pencil icon-white"></i>维护</a>
	                                	<a  class="btn btn-mini btn-dangerLight" onclick="return invalid()" href='<%=basePath %>/maas/Protocol/upProStatus.do?agreement_no=${cm.agreement_no}'><i class="icon-off icon-white"></i>注销</a><!-- Edit -->
	                                </c:if>
								</td>
								<td class="FixedTd">${index.index+1}</td>
								<td>${cm.ins_branchname}</td>
								<td>${cm.agreement_no}</td>
								<td>${cm.branch_name}</td>
								<td>${cm.dateofsign}</td>
								<td>${cm.startdate}</td>
								<td>${cm.enddate}</td>
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
	<script type="text/javascript">
       $(document).ready(function() {
       var a= $(window.parent.document).find("#sidebar").height();
	   $(window.parent.document).find("#ffame").css("height",""+a+"px");
       });
   </script>
</html>
