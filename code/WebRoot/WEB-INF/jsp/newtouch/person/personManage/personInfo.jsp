<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp"
	flush="true" />
<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" flush="true" />
<!-- 回跳 -->
<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp"
	flush="true" />
<!-- 职级联动 -->
<style type="text/css">


</style>
<script>
		<!--重置-->
		
		   
			var linkUrl = "<%=basePath %>/Person/PersonManage/QueryPersonInfo.do";
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
			//查询条件中 开始日期 自   到  前后顺序正确性校验
			jQuery.validator.addMethod("checkDate1",function(value,element){
	    	var startdate=$("#entry_date").val();
			var enddate=$("#end_date").val();
			var flag = true;
	   		if(!isUndefined(startdate)&&!isUndefined(enddate)){
	   			if(startdate>enddate){
	   				flag=false;
	   			}
	   		}
	    	if(!flag){
	    		return false;
	    	}else{
	    		$("label:contains('起期必须小于、等于止期')").remove();
	    		return true;
	    	}
	   	},"起期必须小于、等于止期"); 
			function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
			$(document).ready(function() {
						 $("#queryForm").validate({
							 rules:{
								 entry_date:{// 生效日期   从 Date 非空
									 checkDate1:[]
								 },
								 end_date:{// 终止日期  从 Date 非空
									 checkDate1:[]
								 },
								 branch_name : {
										
										maxlength :100,
										
									}
							 },
						  onkeyup:false
							 });
						 
				     });

			function importPersonInfo(){
				window.location.href="<%=basePath%>/Person/PersonManage/importPersonInfoPage.do";
			}
			function enterPersonInfo(){
				window.location.href="<%=basePath%>/Person/PersonManage/enterPersonInfo.do";
			}
			function exportPersonInfo(){
				console.log(document.queryForm.action);
				var oldAction = document.queryForm.action;
				document.queryForm.action = "<%=basePath%>/Person/PersonManage/exportPersonInfo.do";
				console.log(document.queryForm.action);
				document.queryForm.submit();
				document.queryForm.action = oldAction;
			}
			
			function fomrReset(){
			    document.getElementById("queryForm").reset();
			}
			 		
			<!--跳转添加党员信息-->
			function addForm(){
				$("#queryForm").attr("action","<%=basePath %>/Person/PersonManage/addPartyInfo.do?pageName=addPartyInfo");//设置action执行重置
				$("#queryForm").submit();
				$("#queryForm").attr("action","<%=basePath %>/Person/PersonManage/QueryPersonInfo.do"); //设置action执行查询
				$("#found_date").val("");
			}
		</script>


</head>
<body>
	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i> 人员管理 </span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>
				人员基本信息查询</span><span class="divider"></span>
			<div class="slideUp_Down" id="Shrinkbutton1"></div>
		</div>
		<!-- 面包屑导航  end -->

		<!-- 查询面板 start -->
		<div class="row-fluid" id="Shrinkcontent1">
			<form id="queryForm" name="queryForm"
				class="form-horizontal alert alert-info fade in span12"
				action="<%=basePath %>/Person/PersonManage/QueryPersonInfo.do?pageName=personInfo"
				method="POST" autocomplete="off">
				<webTag:ReturnMsgTag id="msg" name="msg"
					successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
				<div class="row" >
					<jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree.jsp"
						flush="true" />
					<webTag:Text name="person_no" id="person_no"
						value='${rmHelper.returnParams.person_no}' displayLable="员工编码" />
					<webTag:Text name="person_name" id="person_name"
						value='${rmHelper.returnParams.person_name}' displayLable="员工姓名" />
				</div>
				<div class="row">
					<webTag:Text name="idcard" id="idcard"
						value='${rmHelper.returnParams.idcard}' displayLable="身份证号码" />
					
					<%--<div class="control-group span4">
						<label class="control-label" for="branch_level">机构层级</label>
						<div class='controls'> 
						<select class="input-medium null" id="branch_level_code" name="branch_level_code">
							<option value="">---请选择---</option>
							<c:forEach var="branchLevel" items="${msg_branchLevels}">
								<c:if test="${rmHelper.returnParams.branch_level_code == branchLevel.branch_level_code}">
							 		<option selected="selected" value="${branchLevel.branch_level_code}" >${branchLevel.branch_level_name}</option>
							 	</c:if>
							 	<c:if test="${rmHelper.returnParams.branch_level_code != branchLevel.branch_level_code}">
							 		<option value="${branchLevel.branch_level_code}" >${branchLevel.branch_level_name}</option>
							 	</c:if>
                         	</c:forEach>
						</select>
						</div>
					</div>--%>
					<webTag:Select name="work_nature" id="work_nature"
						value='${rmHelper.returnParams.work_nature}' displayLable="合同类型">
						<webTag:Option value="1" displayLable="正式合同"></webTag:Option>
						<webTag:Option value="2" displayLable="试用期员工"></webTag:Option>
						<webTag:Option value="3" displayLable="个人代理"></webTag:Option>
						<webTag:Option value="4" displayLable="退休返聘"></webTag:Option>
						<webTag:Option value="5" displayLable="劳务派遣"></webTag:Option>
						<webTag:Option value="6" displayLable="实习员工"></webTag:Option>
						<webTag:Option value="7" displayLable="专职委派"></webTag:Option>
						<webTag:Option value="8" displayLable="兼职委派"></webTag:Option>
					</webTag:Select>
					<div class="control-group span4">
						<label class="control-label" for="person_status">员工状态</label>
						<div class='controls'>
							<select class="input-medium null" id="person_status" name="person_status">
								<option value="">---请选择---</option>
								<c:forEach var="person_status" items="${person_statusList}">
									<c:if test="${rmHelper.returnParams.person_status == person_status.enum_code}">
										<option selected="selected" value="${person_status.enum_code}" >${person_status.enum_name}</option>
									</c:if>
									<c:if test="${rmHelper.returnParams.person_status != person_status.enum_code}">
										<option value="${person_status.enum_code}" >${person_status.enum_name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</div>

				</div>
				
				<%-- <div class="row">

				</div>--%>
				<div class="row">
					<%--<webTag:Text name="education" id="education"
								 value='${rmHelper.returnParams.education}' displayLable="学历" />--%>
						<webTag:DynamicSelectTag src="educationSelect" name="education" id="education" value='${rmHelper.returnParams.education}' displayLable="学历" isdisplay="true"></webTag:DynamicSelectTag>
						<webTag:Date name="entry_date1" id="entry_date1"
									 value='${rmHelper.returnParams.entry_date1}' displayLable="入职日期从"></webTag:Date>
					<%--<webTag:Select name="is_practice" id="is_practice"
						value='${rmHelper.returnParams.is_practice}' displayLable="是否执业">
						<webTag:Option value="1" displayLable="是"></webTag:Option>
						<webTag:Option value="0" displayLable="否"></webTag:Option>
					</webTag:Select>--%>
					<%--<div class="control-group span4">
						<label class="control-label" for="person_status">员工状态</label>
						<div class='controls'> 
						<select class="input-medium null" id="person_status" name="person_status">
							<option value="">---请选择---</option>
							<c:forEach var="person_status" items="${person_statusList}">
								<c:if test="${rmHelper.returnParams.person_status == person_status.enum_code}">
							 		<option selected="selected" value="${person_status.enum_code}" >${person_status.enum_name}</option>
							 	</c:if>
							 	<c:if test="${rmHelper.returnParams.person_status != person_status.enum_code}">
							 		<option value="${person_status.enum_code}" >${person_status.enum_name}</option>
							 	</c:if>
                         	</c:forEach>
						</select>
						</div>

					</div>--%>
						<webTag:Date name="entry_date2" id="entry_date2"
									 value='${rmHelper.returnParams.entry_date2}' displayLable="至"></webTag:Date>
				</div>
				<div class="row">


					<%--<webTag:Date name="end_date1" id="end_date1"
						value='${rmHelper.returnParams.end_date1}' displayLable="离职时间从"></webTag:Date>--%>
				</div>
				<div class="row">
					<webTag:Date name="end_date1" id="end_date1"
								 value='${rmHelper.returnParams.end_date1}' displayLable="离职时间从"></webTag:Date>
					<webTag:Date name="end_date2" id="end_date2"
						value='${rmHelper.returnParams.end_date2}' displayLable="至"></webTag:Date>
					<%--<webTag:Date name="retire_time" id="retire_time"
						value='${rmHelper.returnParams.retire_time}' displayLable="退休时间"></webTag:Date>--%>
				</div>
				<div class="row" style="text-align: right;">
					<%--<button type="button" onClick="importPersonInfo();" class="btn btn-danger">导入</button>--%>
					<button type="button" onClick="exportPersonInfo();" class="btn btn-danger">导出</button>
					<button type="submit" class="btn btn-danger"
						onClick="defaultQuery();">
						<i class="icon-search icon-white"></i>查询
					</button>
					<button type="button" onClick="enterPersonInfo();" class="btn btn-danger">
						<i class="icon-pencil icon-white"></i>新增
					</button>
<%-- 					<webTag:Ahref path='<%=basePath%>' name="enterPersonInfo" value="新增" iclass_css="icon-plus icon-white"  class_css="btn btn-danger"  href="/Person/PersonManage/enterPersonInfo.do"/>--%>	
					<button name="resetting" id="newreset" type="button"
						class="btn btn-danger" onclick="fomrReset();">重置</button>
					<!-- <button name="add" type="button" onClick="addForm();" class="btn btn-danger" >添加党员</button> -->

				</div>
				<!-- /.row -->
			</form>
		</div>

		<!-- 查询面板 end -->

		<!-- 查询结果 start -->
		<div class="overAuto row-fluid" id="fixeTD">
			<table 
				class="table table-striped table-bordered bootstrap-datatable datatable ">
				<thead>
					<tr>
						<th class="FixedTd">序号</th>
						<th>操作</th>
						<th>归属机构</th>
						<th>所在团队</th>
						<%--<th>岗位名称</th>--%>
						<th>员工编码</th>
						<th>员工姓名</th>
						<th>性别</th>
						<th>民族</th>
						<th>身份证号</th>
						<th>手机号</th>
						<th>合同类型</th>
						<th>执业证编号</th>
						<th>有效期开始</th>
						<th>有效期截至</th>
						<th>入职日期</th>
						<th>离职时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="person" items="${rmHelper.returnMsg.dataList}"
						varStatus="index">
						<tr>
							<td class="FixedTd">${index.index+1}</td>
							<td>
								<a id="viewButton" class="btn btn-mini btn-dangerLight"
								href='<%=basePath %>/Person/PersonManage/PersonInfoView.do?person_no=${person.person_no}'>
								<i class="icon-zoom-in icon-white"></i>明细</a>
									<%-- <c:if test="${rmHelper.returnParams.person_class=='1'}">
										<a id="viewButton1" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/Person/PersonManage/GleaderInfoView.do?person_no=${person.person_no}'>
										<i class="icon-zoom-in icon-white"></i>高管信息</a>
									</c:if> --%>
								<%--<c:if test="${flag=='1'}">
								<a id="viewButton1" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/Person/PersonManage/GleaderInfoView.do?person_no=${person.person_no}'>
										<i class="icon-zoom-in icon-white"></i>高管信息</a>
								</c:if>--%>
								<c:if test="${person.person_status!='离职'}">
								<a id="subButton" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/Person/PersonManage/modifyPersonInfo.do?person_no=${person.person_no}&pageName=modifyPersonInfo'>
								<i class="icon-pencil icon-white"></i>修改</a>
								<a id="subButton" class="btn btn-mini btn-dangerLight" href='<%=basePath %>/Person/PersonManage/Rescind.do?person_no=${person.person_no}&pageName=rescindyPersonInfo'>
								<i class="icon-pencil icon-white"></i>解约</a>
								</c:if>

							</td>
							<td>${person.branch_name}</td>
							<td>${person.team_name}</td>
							<td>${person.person_no}</td>
							<td>${person.person_name}</td>
							<td>${person.sex}</td>
							<td>${person.national}</td>
							<td>${person.idcard}</td>
							<td>${person.phone}</td>
							<td>${person.work_nature_name}</td>
							<td>${person.practice_no}</td>
							<td>${person.practice_startdate}</td>
							<td>${person.practice_enddate}</td>
							<td>${person.entry_date}</td>
							<td>${person.end_date}</td>
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
