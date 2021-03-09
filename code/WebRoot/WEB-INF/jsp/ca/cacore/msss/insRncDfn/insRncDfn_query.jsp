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
	<title>中介人员管理系统</title>
	<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
	<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
	<!-- 回跳 -->
	<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>

	<script >

		$(document).ready(function() {
			$("#queryForm").validate({
				rules : {
				},
				onkeyup:false
			});
		});


		<%-- 	var linkUrl = "<%=basePath %>/PMS/InsRncDfn/queryInsRncDfnList.do";
			$(document).ready(function(){
				linkUrl = linkUrl+"?<%=ServletHelper.getHttpRequestQueryString(request)%>nowPage=__id__&";
				 $("#Pagination").pagination(${rmHelper.pageCount.allRows}
					,{
						items_per_page:${rmHelper.pageCount.rows4Page}
					   ,num_display_entries:5
					   ,ellipse_text:'...'
					   ,current_page:${rmHelper.pageCount.nowPage}-1
					   ,link_to:linkUrl
					   ,callback: defaultQuery
					 });
	        }); --%>
		var linkUrl = "<%=basePath %>/msss/InsRncDfn/queryInsRncDfnList.do";
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


		function invalid(){
			return confirm("确定要注销该产品吗？");

		}


		function defaultQuery(){
			document.queryForm.submit;
		}

		var url = "<%=basePath%>uploadify";
		var fileId = new Array();
		/*  by zdd 20190619 start */
		function exportSalesInfo(){

			$("#queryForm").attr("action","<%=basePath %>/msss/InsRncDfn/exportInfo.do");//设置action执向导出
			$("#queryForm").submit();
			$("#queryForm").attr("action","<%=basePath %>/msss/InsRncDfn/queryInsRncDfnList.do"); //设置action执向查询
		}
		function importRedirect(){
			location.href="<%=basePath %>/msss/InsRncDfn/importRedirect.do?linkUrl=/ca/cacore/msss/insRncDfn/insRncDfn_Import";
		}
		function chongzhi(){

			this.location.href="<%=basePath %>/msss/InsRncDfn/toQueryInsRncDfnList.do";
			/* location.reload(); */
		}
		/*  by zdd 20190619 end  */  /* zdd20190722 */
		function xinxzeng(){
			this.location.href="<%=basePath %>/redirect/redirect2.do?linkUrl=ca/cacore/msss/insRncDfn/insRncDfn_Add";
		}
	</script>
	<script>

	</script>
</head>
<body>
<div class="container-fluid">
	<!-- 按钮 -->

	<!-- 面包屑导航  start -->
	<div class="dreadcount">
		<span class=mrl14><i class="icon-list icon-red"></i> 保险公司管理</span><span class="divider">/</span>
		<span><i class="icon-list icon-red"></i> 产品管理</span>
		<div class="slideUp_Down" id="Shrinkbutton1"></div>
	</div>
	<!-- 面包屑导航  end -->

	<!-- 查询面板 start -->
	<div class="row-fluid" id="Shrinkcontent1">
		<div class="row-fluid">
			<form id="queryForm" name="queryForm" style="min-width:740px;" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/msss/InsRncDfn/queryInsRncDfnList.do" method="POST">
				<div class="row" >
					<%-- <jsp:include page="../../sams/protocol/protocolTree.jsp" flush="true"/> --%>
					<%-- <jsp:include page="../../util/cpyBranchTree.jsp" flush="true"/> --%><!-- zddxiu -->
					<jsp:include page="/WEB-INF/jsp/ca/cacore/util/cpyBranchTree.jsp" flush="true"/>
					<webTag:Text   id="riskName" name="riskName" value='${rmHelper.returnParams.riskName}' displayLable="险种名称"/>
					<webTag:Text   id="riskCode" name="riskCode" value='${rmHelper.returnParams.riskCode}' displayLable="险种编码"/>
				</div>

				<!-- /.row -->
				<div class="row" style="text-align: right;">
					<%-- <a id="addButton" class="btn btn-danger" href='<%=basePath %>/redirect/redirect2.do?linkUrl=ca/cacore/pms/insRncDfn/insRncDfn_Add'><i class="icon-plus icon-white"></i>新增</a> --%>
					<webTag:Button name="exportBranchInfo" type="button" onClick="xinxzeng();" classCss="btn btn-danger" iClassCss="icon-plus icon-white" value="新增"/>  <!--  by zdd02 20190616 -->
					<%--				    <webTag:Button name="exportBranchInfo" type="button" onClick="importRedirect();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="批量导入"/>  <!--  by zdd02 20190616 -->--%>
					<webTag:Button name="exportBranchInfo" type="button" onClick="exportSalesInfo();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导出"/>  <!--  by zdd02 20190616 -->
					<button type="submit" class="btn btn-danger"
							onClick="defaultQuery();">
						<i class="icon-search icon-white"></i>查询
					</button>
					<button name="resetting"  id="newreset"  type="button" class="btn btn-danger"  onclick="chongzhi()">重置</button>
				</div>
				<!-- /.row -->
			</form>
		</div>
	</div>

	<!-- 查询面板 end -->

	<!-- 查询结果 start -->
	<div class="overAuto row-fluid">
		<table class="table table-striped table-bordered bootstrap-datatable datatable">
			<thead>
			<tr>
				<th>序号</th>
				<th>保险公司名称</th>
				<th>险类名称</th>
				<th>险类代码</th>
				<!-- 	<th>产品名称</th> -->
				<th>险种名称</th>
				<th>险种编号</th>
				<th>产品所属保险公司</th>
				<th>保监分类</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="index">
				<tr>
					<td>${index.index+1}</td>

					<td>${sm.branch_name}</td>
					<td>${sm.className}</td>
					<td>${sm.classCode}</td>
						<%-- <td>${sm.product_name}</td> --%>
					<td>${sm.riskName }</td>
					<td>${sm.riskCode }</td>
					<td>${sm.parname}</td>
					<td>${sm.bjtypename}</td>  <!-- zddxiu -->
					<td>
						<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/msss/InsRncDfn/getInsRncDfnByID.do?seq_id=${sm.seq_id}'><i class="icon-pencil icon-white"></i>明细</a>
						<c:if  test="${sm.status=='1'}">
							<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/msss/InsRncDfn/getInsRncDfn.do?seq_id=${sm.seq_id}'><i class="icon-pencil icon-white"></i>修改</a>
							<a id="other" class="btn btn-mini btn-dangerLight" onclick="return invalid()" href='<%=basePath %>/msss/InsRncDfn/updateStatus.do?seq_id=${sm.seq_id}&status=0'><i class="icon-off icon-white"></i>注销</a>
						</c:if>
						<c:if  test="${sm.status=='0' }">
							<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/msss/InsRncDfn/updateStatus.do?seq_id=${sm.seq_id}&status=1'><i class="icon-pencil icon-white"></i>复效</a>
						</c:if>
					</td>
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
