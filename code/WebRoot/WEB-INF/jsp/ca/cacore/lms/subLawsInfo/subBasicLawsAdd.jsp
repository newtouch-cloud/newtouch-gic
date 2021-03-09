<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
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
		<script>
		 function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		     }
		//选择基本法带出开始日期结束日期
		jQuery.validator.addMethod("getLawsInfo", function(value, element) {
			     	var basiclaw_no = $("#basiclaw_no").val();
			     	var flag = true;
			     	if (!isUndefined(basiclaw_no)) {
			     		$.ajax({
			     			url : "<%=basePath %>/LMS/SubLawsManager/getLawsInfo.do",
			     			type : "post",
			     			async : false,
			     			data : {
			     				"basiclaw_no" : basiclaw_no
			     			},
			     			success : function(data) {
			     				var obj1=eval(data);
			     				$.each(obj1,function(index,comment){
			     					var isSuccess =comment.isSuccess;
			     					if (isSuccess == "true") {
			     						var start_date = comment.start_date;
				     					var end_date = comment.end_date;
				     					$("#start_date").val(start_date);
				     					$("#end_date").val(end_date);
				     					flag = true;
				     				} else {
				     					// 先清空带出信息
				     					$("#start_date").val("");
				     					$("#end_date").val("");
				     					flag = false;
				     				}
			     				});
			     			}
			     		});
			     	}
			     	if (!flag) {
			     		return false;
			     	} else {
			     		$("label:contains('基本法名称无效或不存在，请重新输入。')").remove();
			     		return true;
			     	}
			     }, "基本法名称无效或不存在，请重新输入。");
		  $(document).ready(function() {
			 $("#mainForm").validate({
				 rules:{
					 basiclaw_no : {
						 required: true,
						 getLawsInfo : ["basiclaw_no"]
					 }
				 },
				 onkeyup:false
			 })
		  });
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 子基本法管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 子基本法信息管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 子基本法新增</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/LMS/SubLawsManager/addSubLows.do" method="POST">
					<!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					
					<fieldset>
						<div class="row">
							<webTag:DynamicSelectTag src="channelInfoSelect" name="dept_type" id="dept_type" displayLable="渠道类型" value='${rmHelper.returnParams.dept_type}' isdisplay="true"></webTag:DynamicSelectTag>
						</div><!-- /.row -->
						<div class="row">
							<webTag:Text id="basiclaw_no" name="basiclaw_no" value='${rmHelper.returnParams.basiclaw_no}' displayLable="基本法名称:"  isdisplay="true"/>
	                        <webTag:Date id="start_date" name="start_date" value='${rmHelper.returnParams.start_date}' displayLable="基本法开始日期:" readonly="true"/>
	                        <webTag:Date id="end_date" name="end_date" value='${rmHelper.returnParams.end_date}' displayLable="基本法结束日期"  readonly="true"/>
						</div><!-- /.row -->
						<div class="row"> 
							<webTag:Text id="impmeans_name" name="impmeans_name" value='${rmHelper.returnParams.impmeans_name}' displayLable="子基本法名称" isdisplay="true"/>
							<webTag:Date id="sub_start_date" name="sub_start_date" value='${rmHelper.returnParams.sub_start_date}' displayLable="子基本法开始日期" isdisplay="true"/>
	                        <webTag:Date id="sub_end_date" name="sub_end_date" value='${rmHelper.returnParams.sub_end_date}' displayLable="子基本法结束日期" isdisplay="true"/>
						</div>
						<div class="row"> 
							<webTag:TextareaTag id="memo" name="memo" value='${rmHelper.returnParams.memo}' displayLable="说明信息" isdisplay="true" rows='5'/>
						</div>
					    <div class="row" style="text-align:right;">
				    		<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
				    		<a class="btn btn-danger" href="<%=basePath %>/LMS/SubLawsManager/getSubLawsList.do"><i class="icon-share-alt icon-white"></i>返回</a>
						</div><!-- /.row -->
					</fieldset>	
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
