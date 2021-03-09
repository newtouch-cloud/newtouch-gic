<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
	
		<%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		
		
       <script >
			//分页
			var linkUrl = "<%=basePath %>/AMS/RegulationManagerController/queryAll.do";
			$(document).ready(function(){
				linkUrl = linkUrl+"?<%=ServletHelper.getHttpRequestQueryString(request)%>&pageName=salesInfo&nowPage=__id__&";
	            $("#Pagination").pagination(${rmHelper.pageCount.getAllRows()}
	            							,{
	            								items_per_page:${rmHelper.pageCount.getRows4Page()}
	            							   ,num_display_entries:5
	            							   ,ellipse_text:'...'
	            							   ,current_page:${rmHelper.pageCount.getNowPage()}-1
	            							   ,link_to:linkUrl
	            							   ,callback: defaultQuery
	            							 });
	        });
			
			function defaultQuery(){
				document.queryForm.submit;
			}
			
			//查询条件中 规章制定自   到  前后顺序正确性校验
			jQuery.validator.addMethod("checkDateOrder",function(value,element){
				var first_Date=$("#firstDate").val();
		     	var second_Date=$("#secondDate").val();
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
		     		if(first_Date>=second_Date){
		     			return false;
		     		}
		     	}
		     	$("label:contains('日期顺序有误')").remove();
		     	return true;
		    },"日期顺序有误");
			 //判断值为空函数
			function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
		    //校验
		   window.onload = function() {
			   $("#queryForm").validate({
					rules:{
						firstDate:{
							checkDateOrder:[]
						},
						secondDate:{
							checkDateOrder:[]
						}
					},
					onkeyup:false
				});
		   }
		</script>
		
		
		
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章制度管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章维护</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/RegulationManagerController/queryAll.do" method="POST">
  					<div class="row">
						<webTag:Text id="regulation_id" name="regulation_id"  value='${rmHelper.returnParams.regulation_id}' displayLable="规章编号" />
						<webTag:Text id="regulation_name" name="regulation_name" value='${rmHelper.returnParams.regulation_name}' displayLable="规章名称"/>
                        <webTag:Text id="makers" name="makers" value='${rmHelper.returnParams.makers}' displayLable="制定人"/>
					</div>	
					<div class="row">
                        <webTag:Date id="firstDate" name="firstDate"  value='${rmHelper.returnParams.firstDate}' displayLable="制定时间自"/>
                        <webTag:Date id="secondDate" name="secondDate"  value='${rmHelper.returnParams.secondDate}' displayLable="至"/>
					</div>
				    <div class="row" style="text-align:right;">
				      		<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					       <a class="btn btn-danger" href="<%=basePath %>/AMS/RegulationManagerController/goAddRegulationBasic.do"><i class="icon-plus icon-white"></i>新增</a>
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
							<th>规章编号</th>
							<th>规章名称</th>
							<th>规章状态</th>
							<th>制定人</th>
							<th>制定时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
							<tr>
								<td>	
                                     <!--如果状态为已发态就显示明细按钮如果状态为待发布就显示明细和修改按钮-->						
                                    <c:if test="${sm.regulation_status_code=='0'}">
							             <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/AMS/RegulationManagerController/toModifyRegulation.do?regulation_id=${sm.get("regulation_id")}'><i class="icon-pencil icon-white"></i>修改</a>
									     <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/AMS/RegulationManagerController/viewRegulationBasic.do?regulation_id=${sm.get("regulation_id")}'><i class="icon-zoom-in icon-white"></i>明细</a>
							        </c:if>
							        <c:if test="${sm.regulation_status_code=='1'}">
									    <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/AMS/RegulationManagerController/viewRegulationBasic.do?regulation_id=${sm.get("regulation_id")}'><i class="icon-zoom-in icon-white"></i>明细</a>
						            </c:if> 
							    </td>        
								<td>${number.index+1}</td>
								<td>${sm.regulation_id}</td>
								<td>${sm.regulation_name}</td>
								<td>${sm.regulation_status_name}</td>
								<td>${sm.makers}</td>
								<td>${sm.make_time}</td>
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