<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@ page import="com.newtouch.component.c11assistant.ServletHelper"%>
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
	<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
	<script >
		var linkUrl = "<%=basePath %>/mass/InsBranchManage/insBranchQuery.do";
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
		function k(){
			location.href="<%=basePath %>/gozdd.do";
		}
	</script>
	<script type="text/javascript">
		jQuery.validator.addMethod("checkDateOrder",function(value,element){
			var first_Date=$("#createDateL").val();
			var second_Date=$("#createDateG").val();
			if(!isUndefined(first_Date)||!isUndefined(second_Date)){
				var flag=false;
				if(!isUndefined(first_Date)&&!isUndefined(second_Date)&&(second_Date>=value&&value>=first_Date)){
					flag=true;
				}
				if(!isUndefined(first_Date)&&isUndefined(second_Date)&&(value>=first_Date)){
					flag=true;
				}
				if(isUndefined(first_Date)&&!isUndefined(second_Date)&&(second_Date>=value)){
					flag=true;
				}
			}
			if(!isUndefined(first_Date)&&!isUndefined(second_Date)){
				if(first_Date>second_Date){
					return false;
				}
			}
			$("label:contains('起始时间时间顺序有误')").remove();
			return true;
		},"起始时间时间顺序有误");

		$(document).ready(function() {
			$("#queryForm").validate({
				rules:{
					createDateL:{
						checkDateOrder:[]
					},
					createDateG:{
						checkDateOrder:[]
					}
				},
				onkeyup:false
			});
		});
		function isUndefined(paraValue){
			if(paraValue==null||paraValue==undefined||paraValue=="") return true;
			return false;
		}
		function chongzhi(){
			this.location.href="<%=basePath %>/mass/InsBranchManage/toInsBranchQuery.do";
		}
		function addzgs(){
			this.location.href="<%=basePath %>/mass/InsBranchManage/toBranchAdd.do?branch_level=0";
		}
	</script>
</head>
<body>
<div class="container-fluid" >
	<div class="dreadcount">
		<span class=mrl14><i class="icon-list icon-red"></i> 保险公司管理 </span><span class="divider">/</span>
		<span><i class="icon-list icon-red"></i> 保险公司管理</span>
		<div class="slideUp_Down" id="Shrinkbutton1"></div>
	</div>


	<div class="row-fluid">
		<div class="Shrinkcontent" id="Shrinkcontent1">
			<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/mass/InsBranchManage/insBranchQuery.do" method="POST" autocomplete="off">
				<!-- 提示信息 -->
				<div id="dialog" title="提示信息" style="display:none">
					<%-- 							<image id="dialog_img" >${rmHelper1.msgStr}</image> --%>
					<center><image id="dialog_img"></image></br><span>${rmHelper1.msgStr}</span></center>
				</div>
				<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper1.successflag}"   displayLable="msg状态"/>
				<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper1.msgStr}"    displayLable="msg信息"/>
				<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>

				<div class="row">
					<jsp:include page="../../util/cpyBranchTree.jsp" flush="true"/>
					<webTag:Text id="branch_nameA" name="branch_nameA"  value='${rmHelper.returnParams.branch_nameA}' displayLable="保险公司名称"/>
					<%-- <webTag:Date id="createDateL" name="createDateL" value='${rmHelper.returnParams.createDateL}' displayLable="成立日期"  /> --%>
				</div>
				<%-- <div class="row">
                    <webTag:Date id="createDateG" name="createDateG" value='${rmHelper.returnParams.createDateG}' displayLable="至"  />
                </div> --%>

				<div class="row" style="text-align:right;">
					<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					<button name="resetting"  id="newreset"  type="button" class="btn btn-danger" onclick="chongzhi();">重置</button>	<!-- by zdd02 -->
                    <button  type="button" class="btn btn-danger" onclick="addzgs();">新增</button> <!-- zddxiu -->

				</div>
			</form>
		</div>
	</div>

	<div class="overAuto row-fluid">
		<table class="table table-striped table-bordered bootstrap-datatable datatable">
			<thead>
			<tr>

				<th>序号</th>
				<th>保险公司代码</th>
				<th>保险公司名称</th>
				<th>成立日期</th>
				<th>本级等级</th>
				<th>上级公司代码</th>
				<th>上级公司名称</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}"  varStatus="index">
				<tr>
					<td>${index.index+1}</td>
					<td>${sm.branch_id}</td>
					<td>${sm.branch_name}</td>
					<td>${sm.found_date}</td>
					<td>${sm.branch_level}</td>
					<td>${sm.branch_parentid}</td>
					<td>${sm.branch_parentname}</td>
					<td>
						<c:if test="${sm.status == 1}">
							<%-- <c:if test="${sm.branch_level == 5}">
                                <a id="modifyButton" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/sms/InsBranchManage/getInsBranch.do?seq_id=${sm.seq_id}&insBranch_id=${sm.insBranch_id}'><i class="icon-pencil icon-white"></i>修改</a>
                                <a id="viewButton" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/sms/InsBranchManage/insBranchView.do?seq_id=${sm.seq_id}'><i class="icon-zoom-in icon-white"></i>明细</a>
                            </c:if>
                            <c:if test="${sm.branch_level < 5 && sm.branch_level >0}">
                                  <a id="modifyButton" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/sms/InsBranchManage/getInsBranch.do?seq_id=${sm.seq_id}&insBranch_id=${sm.insBranch_id}'><i class="icon-pencil icon-white"></i>修改</a>
                                  <a id="viewButton" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/sms/InsBranchManage/insBranchView.do?seq_id=${sm.seq_id}'><i class="icon-zoom-in icon-white"></i>明细</a>
                                  <a id="addButton" class="btn btn-mini btn-dangerLight"" href='<%=basePath %>/sms/InsBranchManage/toBranchAdd.do?seq_id=${sm.seq_id}&branch_level=${sm.branch_level}&bleng_branchid=${sm.bleng_branchid}&bleng_branchname=${sm.bleng_branchname}&branch_sort=${sm.branch_sort}'><i class="icon-zoom-in icon-white"></i>新增下级</a>
                            </c:if>
                            <c:if test="${sm.branch_level ==0}">
                                  <a id="addButton" class="btn btn-mini btn-dangerLight"" href='<%=basePath %>/sms/InsBranchManage/toBranchAdd.do?seq_id=${sm.seq_id}&branch_level=${sm.branch_level}&bleng_branchid=${sm.bleng_branchid}&bleng_branchname=${sm.bleng_branchname}&branch_sort=${sm.branch_sort}'><i class="icon-zoom-in icon-white"></i>新增下级</a>
                            </c:if> --%><!-- zdd0724 -->
							<a id="modifyButton" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/mass/InsBranchManage/getInsBranch.do?seq_id=${sm.seq_id}&insBranch_id=${sm.insBranch_id}&branch_parentid=${sm.branch_parentid}&cpy_branch_level=${sm.branch_level}&bleng_branchid=${sm.bleng_branchid}'><i class="icon-pencil icon-white"></i>修改</a>
							<a id="viewButton" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/mass/InsBranchManage/insBranchView.do?seq_id=${sm.seq_id}&branch_parentid=${sm.branch_parentid}&cpy_branch_level=${sm.branch_level}'><i class="icon-zoom-in icon-white"></i>明细</a>
							<a id="addButton" class="btn btn-mini btn-dangerLight"" href='<%=basePath %>/mass/InsBranchManage/toBranchAdd.do?seq_id=${sm.seq_id}&branch_level=${sm.branch_level}&bleng_branchid=${sm.bleng_branchid}&bleng_branchname=${sm.bleng_branchname}&branch_sort=${sm.branch_sort}'><i class="icon-zoom-in icon-white"></i>新增下级</a>
						</c:if>
						<c:if test="${sm.status == 0}">
							<a id="modifyButton" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/mass/InsBranchManage/getInsBranch.do?seq_id=${sm.seq_id}&insBranch_id=${sm.insBranch_id}&branch_parentid=${sm.branch_parentid}&cpy_branch_level=${sm.branch_level}'><i class="icon-pencil icon-white"></i>修改</a>
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
<div class="zeoBottomH90"></div>
</body>
</html>