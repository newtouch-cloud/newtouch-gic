<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@ page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@ page import="com.newtouch.component.c11assistant.ServletHelper"%>
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
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳、收缩及上跳 -->
	<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/> 	
        <script type="text/javascript"> 
    	var linkUrl = "<%=basePath %>/team/teammangement/queryteam.do";
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
		   <script type="text/javascript"> 	
			 
			jQuery.validator.addMethod("checkDateOrder",function(value,element){
				
				var first_Date=$("#found_date").val();
		     	var second_Date=$("#recall_date").val();
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
		     	$("label:contains('起期必须小于、等于止期，请重新选择')").remove();
		     	return true;
		    },"起期必须小于、等于止期，请重新选择");
			
			function defaultQuery(){
				document.queryForm.submit;
			}
			
		  	function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
		  	
			$(document).ready(function() {
				
				$("#queryForm").validate({
					rules:{
						found_date:{
							checkDateOrder:[]
						},
						recall_date:{
							checkDateOrder:[]
						}
						
					},
					onkeyup:false
				});
			});
			
			
			function invalid(){
		 		return confirm("确定要注销该团队吗？");
			}
		
	</script>
	
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span><i class="icon-list icon-red"></i>团队管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>团队管理</span><span class="divider">/</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<div class="Shrinkcontent" id="Shrinkcontent1">
					<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/team/teammangement/queryteam.do" method="POST">
						<!-- 提示信息 -->
					    <div id="dialog" title="提示信息" style="display:none">
							<center><image id="dialog_img"></image></br><span>${returnHepler.msgStr}</span></center>
						</div>
					    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${returnHepler.successflag}"   displayLable="msg状态"/>
						<webTag:HiddenInputTag id="msg" name="msg" value="${returnHepler.msgStr}"    displayLable="msg信息"/>
						<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
						<div class="row">
						    <jsp:include page="../util/NewBranchTree.jsp" flush="true"/>
						     <webTag:Text  id="team_name" name="team_name" value='${rmHelper.returnParams.team_name}' displayLable="团队/部门名称" />
						     <webTag:DynamicSelectTag src="statusSelect" name="status" id="status" displayLable="团队/部门状态" value='${rmHelper.returnParams.status}'/>
	                    </div>
						<div class="row"  >
						    <webTag:Date id="found_date" name="found_date"  value="${rmHelper.returnParams.found_date}" displayLable="成立日期" />
						    <webTag:Date id="recall_date" name="recall_date"  value="${rmHelper.returnParams.recall_date}" displayLable="至"/>
						    
						    <webTag:DynamicSelectTag src="teamTypeSelect" name="team_type" id="team_type" displayLable="类别" value='${rmHelper.returnParams.team_type}' />
						    
						</div>
						
					    <div class="row" style="text-align:right;">
					      	<button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
                            <a id="backBtn" class="btn btn-danger" href="<%=basePath%>/team/teammangement/keepTeamMangement.do" style='text-decoration:none;'><i class="icon-plus icon-white"></i>新增</a>
						    <button name="resetting"  id="newreset"  type="button" class="btn btn-danger" >重置</button>
						</div><!-- /.row -->
					</form>
				</div>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid" >
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
							<th>序号</th>
							<th>归属机构编码</th>
							<th>归属机构名称</th>
							<th>团队/部门编码</th>
							<th>团队/部门名称</th>
							<th>类型</th>
							<th>建立日期</th>
							<!-- <th>注销日期</th> -->
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
							<tr>
								<td>${number.index+1}</td>
								<td>${sm.branch_id}</td>
								<td>${sm.branch_name}</td>
								<td>${sm.team_id}</td>
								<td>${sm.team_name}</td>							
								<td>
								    <c:if  test="${sm.team_type=='T'}">
								团队
									</c:if>
									<c:if  test="${sm.team_type=='D'}">
								部门
									</c:if>
								</td>
								
								<td>${sm.found_date}</td>
								<!--<td>${sm.recall_date}</td> -->
								<td>
									<c:if  test="${sm.status=='1'}">
								有效
									</c:if>
									<c:if  test="${sm.status=='0'}">
								无效
									</c:if>
								</td>
								
								<td>
									<c:if  test="${sm.status=='1'}">
									    <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/team/teammangement/SelectOneteam.do?team_id=${sm.team_id}' style='text-decoration:none;'><i class="icon-pencil icon-white"></i>修改</a><!-- Edit -->
										<!-- <a id="other" class="btn btn-mini btn-dangerLight" onclick="return invalid()" href='<%=basePath %>/team/teammangement/logoutteam.do?team_id=${sm.team_id}'><i class="icon-off icon-white"></i>注销</a>-->  
									</c:if>
									<c:if  test="${sm.status=='0'}">
								
									</c:if>
									
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<!-- 查询结果 end -->
				<div class="pagination pagination-centered">
				    <ul id="Pagination"></ul>
				</div>
			</div>
			<!-- 底部高度填充块 -->
			<div class="zeoBottomH90"></div>
			<!-- 底部高度填充块 结束-->
	</body>
	<script type="text/javascript">
       $(document).ready(function() {
       var a= $(window.parent.document).find("#sidebar").height();
       a=a+150;
	   $(window.parent.document).find("#ffame").css("height",""+a+"px");
       });
   </script>
</html>