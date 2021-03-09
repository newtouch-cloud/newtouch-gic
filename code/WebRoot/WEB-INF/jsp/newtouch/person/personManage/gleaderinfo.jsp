<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
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

<body  >

	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i> 人员管理 </span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>
				人员基本信息查询</span><span class="divider">/</span> 
				<span><i class="icon-list icon-red"></i>高管信息</span>
		</div>
		<!-- 面包屑导航  end -->
	
	
	      <div class="row-fluid">
	     	 <form id="fileForm" name='fileForm' method="post" class="form-horizontal alert alert-info fade in span12"
				enctype="multipart/form-data" action="<%=basePath%>/Person/PersonManage/saveGleaderInfo.do?person_no=${rmHelper.returnParams.person_no}" autocomplete="off">
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
				
				<div class="row" >
					<webTag:Select name="gleader" id="gleader" value='${rmHelper.returnParams.gleader}' displayLable="是否高管">
						<webTag:Option value="1" displayLable="是"></webTag:Option>
						<webTag:Option value="0" displayLable="否"></webTag:Option>
					</webTag:Select>
					<webTag:Date name="approval_time" id="approval_time"
						value='${rmHelper.returnParams.approval_time}' displayLable="高管批复时间:"></webTag:Date>
					<webTag:Text name="approval_file" id="approval_file"
						value='${rmHelper.returnParams.approval_file}' displayLable="高管批复文号:" />	
				</div>
				
				<div class="row">
					<webTag:Date name="employment_term" id="employment_term"
						value='${rmHelper.returnParams.employment_term}' displayLable="高管聘期:"></webTag:Date>
					<webTag:Date name="gvalid_time" id="gvalid_time"
						value='${rmHelper.returnParams.gvalid_time}' displayLable="有效期 :"></webTag:Date>
				</div>
				<%-- <div class="row">
					<div class="control-group span4"><label class="control-label" for="upload_approval_file">上传批复文件:</label>
						<input class="input-medium null" type="file" id="file" name="uploadfile" value="${rmHelper.returnParams.upload_approval_file}"/> 
					</div>
            		<button type="button" value="上传"  onclick="fileUpload();">上传</button> 
				</div> --%>
				<div class="row" style="text-align:right; margin-right:40px">
				<a class="btn btn-danger"  href='<%=basePath %>/Person/PersonManage/uploadFilePage.do?person_no=${rmHelper.returnParams.person_no}' style='text-decoration:none;'><i class="icon-off icon-white"></i>上传批复文件</a>
	            <span><button type="button"  class="btn btn-danger" onclick="saveData();"><i class="icon-inbox icon-white"></i>保存</button></span>
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
  
function saveData(){
	<%-- var person_no = document.getElementById("person_no").value;
	var gleader = document.getElementById("gleader").value;
	var approval_time = document.getElementById("approval_time").value;
	var approval_file = document.getElementById("approval_file").value;
	var employment_term = document.getElementById("employment_term").value;
	var gvalid_time = document.getElementById("gvalid_time").value;
	$.ajax({
		  type: "POST",
		  url: "<%=basePath%>/Person/PersonManage/saveGleaderInfo.do",
		  data: {"person_no":person_no,
			  "gleader": gleader,
			  "approval_time": approval_time,
			  "approval_file": approval_file,
			  "employment_term": employment_term,
			  "gvalid_time": gvalid_time
			  },
		  dataType:"text",
		  success: function(data){
			 alert(data); 
		  },
		  error: function(){
			  alert("保存失败!");
		  }
		}); --%>
	$("#fileForm").submit();
}
</script>
</html>