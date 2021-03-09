<%@page import="com.newtouch.component.c11assistant.ServletHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
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
		//分页
		var linkUrl = "<%=basePath %>/AMS/RegulationStatusController/queryAction.do";
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
		   
		   //隐含带值 
		   function commitCheckbox(){
				$('#regulation_id_hidden').val($('#regulation_id').val());//规章编号
				$('#regulation_name_hidden').val($('#regulation_name_hidden').val());//规章名称
				$('#makers_hidden').val($('#makers').val());//制定人 
				$('#firstDate_hidden').val($('#firstDate').val());//
				$('#secondDate_hidden').val($('#secondDate').val());//
				if($("input[name='seq_id']:checkbox:checked").size() ==0){//如果没有勾选
					 alert("请选择要操作的数据");
				}else{
					document.getElementById("commitForm").submit();
				}
			}
			
	        </script>
	        <script >
			//checkbox  全选反选代码 jquery1.9.1版本
			$(function(){  
				$('#checkall').click(function(){  
  		 	 		var checkedOfAll=$("#checkall").prop("checked");   
   			 		$("input[name='seq_id']").prop("checked", checkedOfAll);        
				}); 
			});  
			</script>
		
		
		
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章制度管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 规章制度发布</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/RegulationStatusController/queryAction.do" method="POST">
  					<div id="dialog" title="提示信息" style="display:none">
					     <center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.isSuccessflag()}" displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.getMsgStr()}" displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag id="regulation_status_code" name="regulation_status_code" value='0' displayLable="规章状态"/>
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
						    <button type="button" class="btn btn-danger" onClick="commitCheckbox();" class="btn btn-mini btn-primary" >发布</button>									  
				    </div><!-- /.row -->
				</form>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
						<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<form id="commitForm" name="commitForm"  action="<%=basePath %>/AMS/RegulationStatusController/updateRegulationStatus.do" method="POST">
					<!-- 隐含值域 -->
					<webTag:HiddenInputTag   id="regulation_id_hidden" name="regulation_id" value='${rmHelper.returnParams.regulation_id}' />
					<webTag:HiddenInputTag   id="regulation_name_hidden" name="regulation_name" value='${rmHelper.returnParams.regulation_name}' />
					<webTag:HiddenInputTag   id="makers_hidden" name="makers" value='${rmHelper.returnParams.makers}' />
					<webTag:HiddenInputTag   id="firstDate_hidden" name="firstDate" value='${rmHelper.returnParams.firstDate}'/>
					<webTag:HiddenInputTag   id="secondDate_hidden" name="secondDate" value='${rmHelper.returnParams.secondDate}'/>
					<webTag:HiddenInputTag  id="regulation_status_code" name="regulation_status_code" value='0' displayLable="规章状态"/>
				  <table class="table table-striped table-bordered bootstrap-datatable datatable">
						<thead>
							<tr>
							    <th><input type="checkbox" name="checkall" value="ddd" id="checkall" />全选</th>
								<th>序号</th>
								<th>规章编号</th>
								<th>规章名称</th>
								<th>规章状态</th>
								<th>制定人</th>
								<th>制定时间</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="index" >
							<tr id="tr"+index>
								<td>
									<input type="checkbox" id="check" name="seq_id" value="${sm.seq_id}">
								</td>
									<td>${index.index+1}</td>
									<td>${sm.regulation_id}</td>
									<td>${sm.regulation_name}</td>
									<td>${sm.regulation_status_name}</td>
									<td>${sm.makers}</td>
									<td>${sm.make_time}</td>
								</tr>
							</c:forEach>
						</tbody>
				  </table>
			</form>
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