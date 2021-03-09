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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../../core/pub/basejs.jsp"%>


<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp"
	flush="true" />
<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" flush="true" />
<!-- 回跳 -->
<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp"
	flush="true" />
	
<%-- <link href="<%=basePath%>/compent/WebUploader/combo.select.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>/compent/WebUploader/jquery.combo.select.js" type="text/javascript"></script> --%>
<!-- 职级联动 -->
<style type="text/css">

</style>
<script>
		/* $(document).ready(function () {
				var data = ${superiorList};
				alert(data.length);
				if (data != null) {
                    if (data.length != 0) {
                    	$("#dataList datalist").remove();
						$("#dataList").append("<datalist id='superiorList'>");
                		/* $("#superiorList").append("<option value=''>--请选择--</option>"); 
                		for (var i = 0; i < data.length; i++) {
                    		$("#superiorList").append("<option data-id=" + data[i].superior_no + ">" +data[i].superior_no+"-"+ data[i].superior_name + "</option>");
                		}
                		$("#dataList").append("</datalist>");
                    }else{
                    	$("#dataList datalist").remove();	
                    }
                }else{
                	$("#dataList datalist").remove();	
                }
		}); */
			
			function fomrReset(){
			    document.getElementById("queryForm").reset();
			}
			 		
			function saveData(){
				/* var superiorList = ${superiorList};
				superiorList = JSON.stringify(superiorList);
				var superior_name1 = $("#superior_name").val();
				for (var i = 0; i < superiorList.length; i++) {
					var superior_name1 = superiorList[i].superior_name;
					if(superior_name1 != superior_name2){
						alert("该上级不存在");
						return;
					}
				} */
				$("#queryForm").submit();
			} 
			
			function inputSelect(){
				var input_select=$("#superior_name").val();
			     var option_length=$("option").length;
			     if(option_length>0){
			    	 var option_value = '';
				     for(var i=0;i<option_length;i++){
				         var option=$("option").eq(i).val();
				         if(input_select==option){
				        	 option_value=$("option").eq(i).attr('data-id');
				             break;
				         }
				     }
				     console.log(input_select,option_length,option_value);
				     document.getElementById("superior_no").value=option_value;
			     }
			     
			}
			
			
</script>


</head>
<body>
	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i> 用户管理 </span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>
				选择上级</span><span class="divider"></span>
			<div class="slideUp_Down" id="Shrinkbutton1"></div>
		</div>
		<!-- 面包屑导航  end -->

		<!-- 查询面板 start -->
		<div class="row-fluid" id="Shrinkcontent1">
			<form id="queryForm" name="queryForm"
				class="form-horizontal alert alert-info fade in span12"
				action="<%=basePath %>/goSaveSuperior.do"
				method="POST">
				<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>	
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag name="seq_id" id="seq_id" value="${rmHelper.returnParams.seq_id}"></webTag:HiddenInputTag>
				<div class="row" >
					<webTag:Text name="opt_no" id="opt_no"
						value='${rmHelper.returnParams.opt_no}' displayLable="用户代码" readonly="true"/>
					<webTag:Text name="opt_name" id="opt_name"
						value='${rmHelper.returnParams.opt_name}' displayLable="用户姓名" readonly="true"/>
					<div class="control-group" id = "dataList">
						<label class="control-label" for="superior_no ">选择上级</label>
						<input class="input-medium null" type="text" list="superiorList" id="superior_name" name="superior_name" value="${rmHelper.returnParams.superior_name}" onchange="inputSelect()"   autocomplete="off">
						<input class="input-medium null" type="hidden"  id="superior_no" name="superior_no" value="${rmHelper.returnParams.superior_no}">
						<datalist id="superiorList">
							<c:forEach var="superior" items="${superiorList}">
							 	<option data-id="${superior.superior_no}">${superior.superior_no}---${superior.superior_name}</option>
                         	</c:forEach>
						</datalist>
						
						<%-- <div class='controls'> 
							<select class="input-medium null" id="select" name="superior_no" value="${rmHelper.returnParams.superior_no}">
								<option value="">---请选择---</option>
								<c:forEach var="superior" items="${superiorList}">
							 		<option ${rmHelper.returnParams.superior_no == superior.superior_no ? "selected" : ""} value="${superior.superior_no}" >${superior.superior_name}</option>
                         		</c:forEach><!--  easyui-combobox-->
							</select>
						</div> --%>
					</div>
				</div>
				<div class="row">
          			<jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree5.jsp" flush="true" />
          		</div>
				<div class="col-md-12 text-center panel-body" style="text-align:center">
            		<span><button type="button"  class="btn btn-default btn-xs" onclick="saveData();">保存</button></span>
            		<a id="backButton"  class="btn btn-default btn-xs" href="<%=basePath %>/goUserMgmtQueryPage.do">返回</a>
          		</div>
			</form>
		</div>
		
		
		<div class="overAuto row-fluid" id="fixeTD">
			<table 
				class="table table-striped table-bordered bootstrap-datatable datatable ">
				<thead>
					<tr>
						<th class="FixedTd">序号</th>
						<th class="hidden">序列</th>
						<th>上级工号</th>
						<th>上级名称</th>
						<th class="FixedTd">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="superior1" items="${superiorList1}"
						varStatus="index">
						<tr>
							<td class="FixedTd">${index.index+1}</td>
							<td class="hidden">${superior1.superior_id}</td>
							<td>${superior1.superior_no}</td>
							<td>${superior1.superior_name}</td>
							<td class="FixedTd">
								<a id="subButton" class="btn btn-mini btn-dangerLight" href="<%=basePath %>/deleteSuperior.do?opt_no=${rmHelper.returnParams.opt_no}&opt_name=${rmHelper.returnParams.opt_name}&superior_id=${superior1.superior_id}">
								<i class="icon-pencil icon-white"></i>删除</a>
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
	
	<!-- 底部高度填充块 -->
	<div class="zeoBottomH90"></div>
	<!-- 底部高度填充块 结束-->
</body>
</html>