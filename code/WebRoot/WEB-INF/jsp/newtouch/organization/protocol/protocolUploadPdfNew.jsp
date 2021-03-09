<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
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
<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp"%>
<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp"%>
<!-- 回跳、收缩及上跳 -->
<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp"
	flush="true" />
<link
	href="<%=basePath%>/resources/newtouch/demo/uploadify/uploadify.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=basePath%>/resources/newtouch/demo/uploadify/uploadimg.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=basePath%>/resources/newtouch/demo/uploadify/swfobject.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>/resources/newtouch/demo/uploadify/jquery.form.js"
	type="text/javascript"></script>
</head>
<script type="text/javascript">
/* by zdd 20190606 */
</script>
<style>
	.style{
	 float: right;
	}

</style>
<body >
	<!-- <body onload="tiMesg()"> -->
	<div class="container-fluid" style="height: 400px;background-color: #e9ecee">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i>协议管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 协议合同上传</span>

		</div>
		<!-- 面包屑导航  end -->
		<div class="row-fluid" id="Shrinkcontent1" >
				<form id="fileForm" name='fileForm' method="post" class="form-horizontal alert alert-info fade in span12"
				enctype="multipart/form-data" action="<%=basePath%>/organization/Protocol/impProtocolPdf.do">

				<!-- 提示信息 -->
					<div id="dialog" title="提示信息" style="display: none">
						<center>
							<image id="dialog_img"></image>
							</br>
							<span>${rmHelper.msgStr}</span>
						</center>
					</div>
					<webTag:HiddenInputTag id="result_flag" name="result_flag"
						value="${rmHelper.successflag}" displayLable="msg状态" />
					<webTag:HiddenInputTag id="msg" name="msg"
						value="${rmHelper.msgStr}" displayLable="msg信息" />
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath%>' />
					<!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
					<webTag:HiddenInputTag name="flag" id="flag"
						value="${rmHelper.returnParams.flag}"></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="removeflag" id="removeflag"
						value="${removeflag}"></webTag:HiddenInputTag>
						
				<div class="overAuto row-fluid">
				<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
					<table class='table table-striped table-bordered bootstrap-datatable datatable' style="background-color: #e9ecee">
					   
						<tr>
						<td>信息显示</td>
						<td width="30px"><b>协议号:&nbsp;&nbsp;</b><input type="text" id="agreement_no" name="agreement_no" value='${rmHelper.returnParams.agreement_no}' readonly="readonly" style="text-align: center;"/></td>
                          
                          
                   
						  <td width="30px"><b>文件名称:</b><input type="text" id="inf" name="inf" value='${rmHelper.returnParams.picture_pathName}' readonly="readonly" style="text-align: center;"/></td>
					       
					     

						
						</tr>
						<tr>
						    <td width="50px"><i class="icon-file icon-red"></i> 请选择</td>
						 	<td st><input type="file" name="file" id="file" class="form-control" />
						 	<button type="button" class="form-control" id="upload" onclick="fileUpload();">上传</button></td>
						 	</td>
						 <td style="text-align: center;">
						 <input type="hidden" id="zzimg" name="zzimg" value='${rmHelper.returnParams.picture_path}'/>
						 <!-- <button type="button" class="button" id="upload" onclick="downimg();" >协议合同查看</button> -->
			    		  
						 </td>
						</tr>
						<tr>
						
					     </tr>
					</table>
				</div>
				<div class="style">
				 <webTag:Button name="exportBranchInfo" type="button" onClick="downimg();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="协议合同下载"/> 
					<a class="btn btn-danger" href="<%=basePath %>/organization/Protocol/goProtocolList.do"><i class="icon-share-alt icon-white"></i>返回</a>
				</div>
		<div class="zeoBottomH90"></div>
		
		<!-- 		底部高度填充块 结束-->
		</div>
			</form>
			<div class="zeoBottomH90"></div>
		</div>
	
</body>

<script type="text/javascript">
	/* $(function (){
    	jsonToPage(${json});
    }); */
	
	
	
	function fileUpload() {
		var file = document.getElementById("file").value;
		if (file == "") {
			alert("请选择上传文件！");
			return;
		}
		var fileType = file.substring(file.lastIndexOf(".")+1);
		if(fileType != "jpg" && fileType != "pdf"&&fileType != "JPG" && fileType != "PDF") {
			alert("上传的文件类型不正确！");
			return;
		}
		
		var imagSize =  document.getElementById("file").files[0].size;
		if(imagSize>1024*1024*2){
		  alert("文件大小必须在2M以内，现文件大小为："+imagSize/(1024*1024)+"M");
		  return ;
		}
		var url = $("#fileForm").attr("action");
		$("#fileForm").submit();
	  }
    function downimg(){
		var fileName=$("#zzimg").val();
		if(path==null || path==""){
			alert("未上传协议合同，请上传后再进行查看！");
			return ;
		}
			window.location.href="<%=basePath%>/organization/Protocol/downImg.do?fileName="+fileName;
	}
</script>
	
</html>