<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<!-- 回跳 -->
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/> 
       	<script type="text/javascript">
			var linkUrl = "<%=basePath %>/DMS/Querydocmanagent.do";
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
		     	$("label:contains('起始时间时间顺序有误')").remove();
		     	return true;
		    },"起期必须小于、等于止期");
			
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
		 		return confirm("确定要注销该机构吗？");
			}
			
			function valid(){
			 		return confirm("确定要恢复该机构吗？");
			}
			
			/* byzdd 20190606 start */
			function daoru(){
				
				location.href="<%=basePath %>/DMS/toImportdocmanagent.do";
			}
           
            function daochu(){
            	$("#queryForm").attr("action","<%=basePath %>/DMS/exportdocmanagent.do");//设置action执向导出
    			$("#queryForm").submit();
    			$("#queryForm").attr("action","<%=basePath %>/DMS/Querydocmanagent.do"); //设置action执向查询
             <%--   var branch_id =$("#branch_id").val();
               var applay_cpybranchname=$("#applay_cpybranchname").val();
               var document_idcard=$("#document_idcard").val();
               var receiptdate=$("#receiptdate").val();
               var receiptdate1=$("#receiptdate1").val();
               var applyuser_name=$("#applyuser_name").val();
               var return_time=$("#return_time").val();
               var return_time1=$("#return_time1").val();
               var returnuser_name=$("#returnuser_name").val();
               var s="?branch_id="+branch_id+"&applay_cpybranchname="+applay_cpybranchname+"&document_idcard="+document_idcard+"&receiptdate="+receiptdate;
                var  s1=s+"&receiptdate1="+receiptdate1+"&applyuser_name="+applyuser_name+"&return_time="+return_time+"&return_time1="+return_time1+"&returnuser_name="+returnuser_name;
            	alert(s1);
                this.location.href="<%=basePath %>/DMS/exportdocmanagent.do?branch_id="+branch_id+"&applay_cpybranchname="+applay_cpybranchname+"&document_idcard="+document_idcard+"&receiptdate="+receiptdate+"&receiptdate1="+receiptdate1+"&applyuser_name="+applyuser_name+"&return_time="+return_time+"&return_time1="+return_time1+"&returnuser_name="+returnuser_name;
            	 /* location.reload(); */ --%>
            }
           function chongzhi(){
        	   location.href="<%=basePath %>/DMS/docmanagent.do";
           }
           
			/* byzdd 20190606 end */
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>单证管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 单证管理</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<div class="Shrinkcontent" id="Shrinkcontent1">
					<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/DMS/Querydocmanagent.do" method="POST" autocomplete="off">
						<!-- 提示信息 -->
					    <div id="dialog" title="提示信息" style="display:none">
							<center><image id="dialog_img"></image></br><span>${returnHepler.msgStr}</span></center>
						</div>
					    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${returnHepler.successflag}"   displayLable="msg状态"/>
						<webTag:HiddenInputTag id="msg" name="msg" value="${returnHepler.msgStr}"    displayLable="msg信息"/>
						<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
						<div id="div1" class="row">
							<jsp:include page="../../util/NewBranchTree.jsp" flush="true"/>
							 <input type="hidden" id="ccode" name="ccode" value='${rmHelper.returnParams.ccode}'/>
						<webTag:Text  id="ccodename" name="ccodename" value='${rmHelper.returnParams.ccodename}' displayLable="保险公司" />
						<webTag:Text  id="document_idcard" name="document_idcard" value='${rmHelper.returnParams.document_idcard}' displayLable="单证识别码" />
					</div>	
					<div class="row">
						<webTag:Date id="receiptdate" name="receiptdate"  value="${rmHelper.returnParams.receiptdate}" displayLable="领用时间段" />
						<webTag:Date id="receiptdate1" name="receiptdate1"  value="${rmHelper.returnParams.receiptdate1}" displayLable="至" />
						
						<webTag:Text id="applyuser_name" name="applyuser_name" value='${rmHelper.returnParams.applyuser_name}' displayLable="领用人"/> 
					</div>
					<div class="row">
					<webTag:Date id="return_time" name="return_time"  value="${rmHelper.returnParams.return_time}" displayLable="交回时间段" />  
						<webTag:Date id="return_time1" name="return_time1"  value="${rmHelper.returnParams.return_time1}" displayLable="至" />  
						<webTag:Text id="returnuser_name" name="returnuser_name" value='${rmHelper.returnParams.returnuser_name}' displayLable="交回人"/>
					</div>
					<div>
					
					  <webTag:Text  id="applyuser_id" name="applyuser_id" value='${rmHelper.returnParams.applyuser_id}' displayLable="领用人代码" />
					  <webTag:Text  id="returnuser_code" name="returnuser_code" value='${rmHelper.returnParams.returnuser_code}' displayLable="交回人代码" />
					</div>
				    <div class="row" style="text-align:right;">
				      		
<!-- 				      		<button type="button" class="btn btn-danger" onClick="importInfo();" ><i class="icon-download-alt icon-white"></i>导出</button> -->
					        <webTag:Button name="exportCustomer.do" type="button" onClick="daochu();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导出"/>
					        <webTag:Button name="exportCustomer.do" type="button" onClick="daoru();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导入"/>
					       <button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
					         <button type="button" class="btn btn-danger"  onclick="chongzhi();">重置</button>
					</div><!-- /.row -->
					
				</form>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr >
						<th rowspan="2" >序号</th>
						<th colspan="10" style="text-align: center;">领用</th> 
						<th colspan="12" style="text-align: center;">交回</th>
						<th rowspan="2">备注</th>
						</tr>
						<tr>
							
							<th>领用日期</th>
							<th>领用人/使用人代码</th>
							<th>领用人/使用人姓名</th>
							<th>领用机构</th>
							<th>保险公司</th>
							<th>单证识别码</th>
							<th>单证名称</th>
							<th>单证流水号（起）</th>
							<th>单证流水号（止）</th>
							<th>数量</th>
							
							<th>交回时间</th>
							<th>交回人代码</th>
							<th>交回人姓名</th>
							<th>交回机构</th>
							<th>保险公司</th>
							<th>单证识别码</th>
							<th>单证流水号（起）</th>
							<th>单证流水号（止）</th>
							<th>数量</th>
							<th>其中：已使用</th>
							<th>其中：作废</th>
							<th>其中：未使用址</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
							<tr>
								
								<td>${number.index+1}</td>
								<td>${sm.receiptdate}</td>
								
								<td>${sm.applyuser_id}</td>
								<td>${sm.applyuser_name}</td>
								<td>${sm.apply_sysbranchname}</td>
								<td>${sm.applay_cpybranchname}</td>
								<td>${sm.document_idcard}</td>
								<td>${sm.document_name}</td>
								<td>${sm.document_serial_beg}</td>
								<td>${sm.document_serial_end}</td>
								<td>${sm.applay_num}</td>
								
								<td>${sm.return_time}</td>
								<td>${sm.returnuser_code}</td>
								<td>${sm.returnuser_name}</td>
								<td>${sm.return_sysbranchname}</td>
								
								<td>${sm.return_cpybranchname}</td>
								<td>${sm.redocument_idcard}</td>
								<td>${sm.redocument_serial_beg}</td>
								<td>${sm.redocument_serial_end}</td>
								<td>${sm.return_num}</td>
								<td>${sm.user_number}</td>
								<td>${sm.obsolete_number}</td>
								<td>${sm.not_used_number}</td>
								<td>${sm.remarks}</td>
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