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
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
        <script >    
        $(document).ready(function() {
			//表单校验
			$("#queryForm").validate({
	    			rules : {
	    				branch_name : {  //机构名称
	    					required : true,
	    					checkSubmit : []
	    				}
	    			},
	    			onkeyup:false,
	    			onfocusout : false
			});
			//校验样式效果,文本框获取焦点,隐藏该文本框相应报错信息
	   		 $("#queryForm").find("input").each(function(){
	           		$(this).click(function(){
	           			var _this=$(this);
	           			if(_this.hasClass("error")){
	           				_this.removeClass("error");
	           				var labelAR = _this.parent().find("label[class='error']");
	           				labelAR.remove();
	           			}
	           		});
	           	});	
		    
		});
        
			var linkUrl = "<%=basePath %>/CRM/Customer/customerQuery.do";
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
			
			//设置查询条件:机构选择是必输项，除去机构选择，其它查询条件中必须还有一个必输项，才能查询出客户信息。
			jQuery.validator.addMethod("checkSubmit",function(value,element){
				var name = $('#name').val();
				var member_id = $('#member_id').val();
				var certi_type = $('#certi_type').val();
				var certi_no = $('#certi_no').val();
				var birthday = $('#birthday').val();
				var mobile = $('#mobile').val();
				
				if(name != "" || member_id != "" || certi_type != "" || certi_no != "" || birthday != "" || mobile != ""){
					return true;
				}else{
					alert("查询条件必须是机构选择及任意另外最少一个查询条件");
					return false;
				}
	     	},"");
		     	
		function importInfo(){
		          $('#queryForm').attr("action","<%=basePath %>/CRM/Customer/daochuCustomer.do"); //设置action指向导出
		          $('#queryForm').submit();
		          $('#queryForm').attr("action","<%=basePath %>/CRM/Customer/customerQuery.do"); //设置action执向查询
		       }
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户查询</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CRM/Customer/customerQuery.do" method="POST">
  					<div class="row">
						<jsp:include page="../../util/branchTreeRequired.jsp" flush="true"/>
						<webTag:Text  id="policyno" name="policyno" value='${rmHelper.returnParams.policyno}' displayLable="保（批）单号" />
						<webTag:Text  id="car_no" name="car_no" value='${rmHelper.returnParams.car_no}' displayLable="车牌号" />
					</div>	
					<div class="row">
						<webTag:Text id="customer_id" name="customer_id" value='${rmHelper.returnParams.customer_id }' displayLable="客户代码"/>
						<webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="客户姓名"/>   
						<webTag:Text id="mobile" name="mobile" value='${rmHelper.returnParams.mobile}' displayLable="移动电话"/> 
					</div>
					<div class="row">
						<webTag:Text id="claimno" name="claimno" value='${rmHelper.returnParams.claimno}' displayLable="赔案号"/>
						<webTag:Text id="insurCount" name="insurCount" value='${rmHelper.returnParams.insurCount}' displayLable="出险次数"/>
					</div>
				    <div class="row" style="text-align:right;">
				      		<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
<!-- 				      		<button type="button" class="btn btn-danger" onClick="importInfo();" ><i class="icon-download-alt icon-white"></i>导出</button> -->
					        <webTag:Button name="exportCustomer.do" type="button" onClick="importInfo();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导出"/>
					</div><!-- /.row -->
					
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
							<th>客户类型</th>
							<th>客户代码</th>
							<th>客户姓名</th>
							<th>性别</th>
							<th>出生日期</th>
							<th>证件类型</th>
							<th>证件号码</th>
							<th>教育程度</th>
							<th>婚姻状况</th>
							<th>家庭地址</th>
							<th>移动电话</th>
							<th>电子邮箱</th>
							<th>职业</th>
							<th>法人代表</th>
							<th>单位名称</th>
							<th>地址</th>
							<th>电话</th>
							<th>传真</th>
							<th>公司网址</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
							<tr>
								<td>
									<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/CRM/Customer/CustomerQueryView1.do?customer_id=${sm.customer_id}&customer_type=${sm.customer_type}&branch_id=${sm.branch_id}'><i class="icon-zoom-in icon-white"></i>明细</a><!-- View -->
									<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/CRM/Customer/queryCustomerById.do?customer_id=${sm.customer_id}'><i class="icon-plus icon-white"></i>接触录入</a><!-- View -->
								</td>
								<td>${number.index+1}</td>
								<td>${sm.branch_id}</td>
								<td>${sm.branch_name}</td>
								<td>${sm.type_name}</td>
								<td>${sm.customer_id}</td>
								<td>${sm.name}</td>
								<td>${sm.gender_name}</td>
								<td>${sm.birthday}</td>
								<td>${sm.certi_type_name}</td>
								<td>${sm.certi_no}</td>
								<td>${sm.education_name}</td>
								<td>${sm.marital_stat_name}</td>
								<td>${sm.address}</td>
								<td>${sm.mobile}</td>
								<td>${sm.email}</td>
								<td>${sm.job_code}</td>
								
								<td>${sm.corporation_represen}</td>
								<td>${sm.company_name}</td>
								<td>${sm.company_address}</td>
								<td>${sm.company_telphone}</td>
								<td>${sm.company_fax}</td>
								<td>${sm.company_url}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="pagination pagination-centered">
			    <ul id="Pagination"></ul>
			</div>
		</div>
		<!-- 查询结果 end -->
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>