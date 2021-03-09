<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@ page import="com.newtouch.component.c11assistant.ServletHelper"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head> 
		<title>新致金保通</title>
		<jsp:include page="../pub/jvbasecss.jsp" flush="true"/>
        <jsp:include page="../pub/jvbasejs.jsp" flush="true"/>
        <script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery.form.js"></script>
	    <!-- 回跳 -->
	    <jsp:include page="../pub/backPageHelper.jsp" flush="true"/> 
	    <!-- 职级联动 -->
	    <jsp:include page="../pub/jvrank.jsp" flush="true"/>
		
		 <script type="text/javascript"> 
       			var linkUrl = "<%=basePath %>/report/personal/queryreport.do";
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
		</script> 
		<script >
			function defaultQuery(){
				document.queryForm.submit;
			}
			function exportSalesInfo(){
			    $('#queryForm').attr("action","<%=basePath %>/Report/DownloadExcel.do"); //设置action指向导出
			    $('#queryForm').submit();
			    $('#queryForm').attr("action","<%=basePath %>/report/personal/queryreport.do"); //设置action执向查询
			 }
		</script>
	</head>
	<body style="height: 750px">
		<div class="container-fluid">
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>报表管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>保监报表</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>上报保监会报表（人身险）</span>
			</div>
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<div class="Shrinkcontent" id="Shrinkcontent1">
					<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/report/propertyReport/queryreport.do" method="POST">
						<div class="row">
						    <webTag:DynamicSelectTag src="" name="" id="" displayLable="期次" value=''/>
						    <webTag:DynamicSelectTag src="" name="" id="" displayLable="保险公司选择" value=''/>
						</div>
					    <div class="row" style="text-align:right;">
					      	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
                            <webTag:Button name="propertyReport.do" type="button" onClick="exportSalesInfo();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导出"/>
						</div><!-- /.row -->
					</form>
				</div>
			</div>
			<!-- 查询面板 end -->
			<!-- 查询结果 start -->
			
			
			
			
			<!-- 查询结果 end -->
			<div class="pagination pagination-centered">
				    <ul id="Pagination"></ul>
				</div>
		</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 底部高度填充块 结束-->
	</body>
</html>
