<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html>
<head>
        <%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script type="text/javascript" src="<%=basePath%>/compent/default/js/attachmentModify.js"></script>
</head>
<script type="text/javascript">


/*
 *判断参数是否为未定义或null,如未定义或为null返回true
 *@param paraValue 待处理字符串
 *@return true或者false
 */
function isUndefined(paraValue){
   if(paraValue==null||paraValue==undefined||paraValue=="") return true;
   return false;
}
//校验日期的先后顺序正确性
jQuery.validator.addMethod("checkDateOrder",function(value,element,param){
  	var task_proce_date=$("#task_proce_date").val();
  	var task_failure=$("#task_failure").val();
  	if(!isUndefined(task_proce_date)&&!isUndefined(task_failure)){
  		if(task_proce_date>=task_failure){
  			return false;
  		}
  		$("label:contains('时间顺序有误')").remove();
  		return true;
  	}
  	return true;
  },"时间顺序有误");
$(document).ready(function() {
		$("#mainForm").validate({
			rules : {
				task_type:{
				    required : true
				},
				task_name : {
					required : true,
				},
				proce_status:{
					required : true
				},
				task_title : {
				    required : true,
			    },
			    task_proce_date : {
				    required : true,
				    checkDateOrder : [ "task_proce_date", "task_failure" ]
			    },
			    task_failure: {
					required : true
					checkDateOrder : [ "task_proce_date", "task_failure" ]
				},
				task_content:{//标识
					required : true
				},
				task_object_id:{
					required : true
				}
				task_sales_id:{
					required : true
				}
			},
			onkeyup:false,
			messages:{
				task_content:{
					maxlength : "输入的内容不能大于500个字符"
			    }
			}
		});
	});

</script>
<body>

<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>消息推送管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>消息推送查询</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>修改</span><span class="divider">/</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/MessagePushManagerController/saveMessagePushModify.do"  enctype="multipart/form-data"  method="POST">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<image id="dialog_img" >${rmHelper.msgStr}</image>
					</div  >
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>					
					<!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag name="taskid" id="taskid" value="${rmHelper.returnParams.taskid}"></webTag:HiddenInputTag>					
					<div  class="row">
					    <webTag:Text id="task_type" name="task_type"  value='${rmHelper.returnParams.task_type}' displayLable="类型代码" readonly="true" isdisplay="true"/>
					    <webTag:Text id="task_name" name="task_name"  value='${rmHelper.returnParams.task_name}' displayLable="类型名称" readonly="true" isdisplay="true"/>
					    <c:if test="${rmHelper.returnParams.proce_status=='0'}">
					       <webTag:Text id="proce_status" name="proce_status"  value='未处理' displayLable="任务状态" readonly="true" isdisplay="true"/> 
					    </c:if>				    
					    <c:if test="${rmHelper.returnParams.proce_status=='1'}">
					       <webTag:Text id="proce_status" name="proce_status"  value='已处理' displayLable="任务状态" readonly="true" isdisplay="true"/> 
					    </c:if>
					</div>	
					
					<div  class="row">    
                        <webTag:Text id="task_title" name="task_title"  value='${rmHelper.returnParams.task_title}' displayLable="任务标题" isdisplay="true"/>
					    <webTag:Date id="task_proce_date" name="task_proce_date"  value='${rmHelper.returnParams.task_proce_date}' displayLable="任务提醒开始日期" isdisplay="true"/>
					    <webTag:Date id="task_failure" name="task_failure"  value='${rmHelper.returnParams.task_failure}' displayLable="任务提醒失效日期" isdisplay="true"/> 
					</div>	
					   
					<div  class="row">
					    <webTag:Text id="task_content" name="task_content"  value='${rmHelper.returnParams.task_content}' displayLable="任务内容" isdisplay="true"/>
					    <webTag:Text id="task_object_id" name="task_object_id"  value='${rmHelper.returnParams.task_object_id}' displayLable="任务相关号码" isdisplay="true"/>
					    <webTag:Text id="task_sales_id" name="task_sales_id"  value='${rmHelper.returnParams.task_sales_id}' displayLable="任务接收人" isdisplay="true"/> 
                    </div>
					<div class="row" align="right">
						 <button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
			             <a class="btn btn-danger" href="<%=basePath %>/AMS/MessagePushManagerController/queryMessagePush.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
			<!-- 增加面板 end -->			
        </div>
<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
<!-- 		底部高度填充块 结束-->



</body>
</html>