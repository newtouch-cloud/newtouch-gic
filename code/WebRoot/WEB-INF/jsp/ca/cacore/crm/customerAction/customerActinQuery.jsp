<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>

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
		<style type="text/css">
			.tdclass{
				 width:10px;
				 text-overflow : ellipsis; 
				 white-space : nowrap; 
				 overflow : hidden;
			}
		</style>
		
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
	           				var labelAR = _this.parents().find("label[class='error']");
	           				labelAR.remove();
	           			}
	           		});
	           	});
		    
		});
        
			var linkUrl = "<%=basePath %>/CRM/CustomerAction/queryAll.do";
			$(document).ready(function(){
				
				linkUrl = linkUrl+"?nowPage=__id__&";
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
				var customer_name = $('#customer_name').val();
				var customer_id = $('#customer_id').val();
				var customer_type = $('#customer_type').val();
				var gender = $('#gender').val();
				var action_type = $('#action_type').val();
				
				if(customer_name != "" || customer_id != "" || customer_type != "" || gender != "" || action_type != ""){
					return true;
				}else{
					alert("查询条件必须是机构选择及任意另外最少一个查询条件");
					return false;
				}
	     	},"");
		  </script>
		
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户行为查询</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm"  class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CRM/CustomerAction/queryAll.do" method="POST">
					<div class="row">
							<jsp:include page="../../util/branchTreeRequired.jsp" flush="true"/>
							<webTag:DynamicSelectTag src="customerTypeSelect" id="customer_type" name="customer_type"  value='${rmHelper.returnParams.customer_type}' displayLable="客户类型"/>
			  				<webTag:Text id="customer_id" name="customer_id"  value='${rmHelper.returnParams.customer_id}' displayLable="客户代码"/>
					</div>	
					 <div class="row">
                        <webTag:Text id="customer_name" name="customer_name" value='${rmHelper.returnParams.customer_name}' displayLable="客户姓名"/>
                        <webTag:DynamicSelectTag src="genderSelect" name="gender" id="gender" displayLable="性别" value='${rmHelper.returnParams.gender}' />
						<webTag:DynamicSelectTag src="custActionTypeSelect" id="action_type" name="action_type"  value='${rmHelper.returnParams.action_type}' displayLable="行为类型"/>
					 
					</div>
					
				    <div class="row" style="text-align:right;">
				      		<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
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
							<th>行为类型</th>
							<th>行为时间</th>
							<th>行为描述</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
							<tr>
							    <td><a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/CRM/CustomerAction/goActionDetail.do?customer_id=${sm.customer_id}&seq_id=${sm.seq_id}'><i class="icon-zoom-in icon-white"></i>明细</a><!-- View --></td>
								<td>${number.index+1}</td>
								<td>${sm.branch_id}</td>
								<td>${sm.branch_name}</td>
								<td>${sm.customer_type}</td>
								<td>${sm.customer_id}</td>
								<td>${sm.name}</td>
								<td>${sm.gender}</td>
								<td>${sm.action_type}</td>
								<td>${sm.action_time}</td>
								<td class="tdclass">${sm.action_notes}</td>
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