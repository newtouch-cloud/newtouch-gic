<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>新致金保通</title>
	<jsp:include page="/WEB-INF/jsp/newtouch/core/pub/jvbasecss.jsp" flush="true" />
	<jsp:include page="/WEB-INF/jsp/newtouch/core/pub/jvbasejs.jsp" flush="true" />
	<!-- 回跳 -->
 	<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true" />
 </head>
<style>
	.style{
	 float: right;
	}

</style>
<body >

	<div class="container-fluid" style="height: 400px;background-color: #e9ecee">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i> 人员管理 </span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>
				人员基本信息查询</span><span class="divider"></span> <span
				class="divider">/</span> <span><i class="icon-list icon-red"></i></i>高管信息批复文件上传</span>
		</div>
		<!-- 面包屑导航  end -->
	
	
	      <div class="row-fluid">
	     	 <form id="fileForm" name='fileForm' method="post" class="form-horizontal alert alert-info fade in span12"
				enctype="multipart/form-data" action="<%=basePath%>/Person/PersonManage/saveGleaderInfo.do?person_no=${rmHelper.returnParams.person_no}">
				<!-- 提示信息标签 -->
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
					
					<webTag:HiddenInputTag name="person_no" id="person_no" value="${rmHelper.returnParams.person_no}"></webTag:HiddenInputTag>
				
				<fieldset>
				<table class='table table-striped table-bordered bootstrap-datatable datatable' style="background-color: #e9ecee">
					   
						<tr>
						<td >信息显示</td>
						<td ><b>文件名称:</b><input type="text" id="inf" name="inf" value='${rmHelper.returnParams.filename}' readonly="readonly" style="text-align: center;"/></td>
						</tr>
						<tr>
						    <td width="50px"><i class="icon-file icon-red"></i> 请选择</td>
						 	<td st><input type="file" name="uploadfile" id="file" class="form-control" />
						 	<button type="button" class="form-control" id="upload" onclick="fileUpload();">上传</button></td>
						 	</td>
						 <td style="text-align: center;">
						 <input type="hidden" id="zzimg" name="zzimg" value=''/> 		  
						 </td>
						</tr>
						<tr>
						
					     </tr>
					</table>
				<div class="row" style="text-align:right; margin-right:40px">
	          	<a id="backButton" class="btn btn-danger"
						href="<%=basePath %>/Person/PersonManage/toQueryPersonInfo.do?pageName=personInfo"><i class="icon-share-alt icon-white"></i>返回</a>
	          </div>
			</fieldset>
	        </form>
	        
	      </div>
	</div>
	<div class="zeoBottomH90"></div> 
</body>
<script type="text/javascript">
$(document).ready(function() {
var a= $(window.parent.document).find("#sidebar").height();
	a=a+150;
	$(window.parent.document).find("#ffame").css("height",""+a+"px");
});
function fileUpload() {
	var file = document.getElementById("file").value;
	if (file == "") {
		alert("请选择上传文件！");
		return;
	}
	var fileType = file.substring(file.lastIndexOf(".")+1);
	debugger;
	if(fileType != "jpg" && fileType != "pdf"&&fileType != "JPG" && fileType != "PDF") {
		alert("上传的文件类型不正确！");
		return;
	}
	
	var imagSize =  document.getElementById("file").files[0].size;
	if(imagSize>1024*1024*2){
	  alert("文件大小必须在2M以内，现文件大小为："+imagSize/(1024*1024)+"M");
	  return ;
	}
	var oldAction = document.fileForm.action;
	document.fileForm.action = "<%=basePath%>/Person/PersonManage/uploadApprovalFile.do";
	console.log(document.fileForm.action);
	document.fileForm.submit();
	document.fileForm.action = oldAction;
	
	<%-- var formData = new FormData($("#fileForm")[0]);
	$.ajax({
		  type: "POST",
		  url: "<%=basePath%>/Person/PersonManage/uploadApprovalFile.do",
		  data: formData,
		  dataType:"text",
		  cache : false,
		  processData: false, 
		  contentType: false,
		  success: function(data){
			 alert(data); 
		  },
		  error: function(){
			  alert("上传失败!");
		  }
		}); --%>
  }

</script>
</html>