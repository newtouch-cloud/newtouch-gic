<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>

<%
	//request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
	//request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "";
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
<script type="text/javascript">

  $(document).ready(function() {
		 $("#queryForm").validate({
			 rules:{
				 enddate:{// 终止日期  从 Date 非空
					 required:true
					 }
				 }
			 },
		  onkeyup:false
		 });
		 //校验样式效果,文本框获取焦点,隐藏该文本框相应报错信息
  		 $("#queryForm").find("input").each(function(){
          		$(this).click(function(){
          			var _this=$(this);
          			if(_this.hasClass("error")){
          				_this.removeClass("error");
          				var labelAR = _this.parent().find("label[class='error']");
          				labelAR.remove();
          			}
          		});
          	});
    });
		</script>
<script type="text/javascript">
		 $(function(){
			 	$("#st").delegate("td","click",function(){
					var str = $(this).text();
					$("#code").next("label[class='error']").remove();
					$("#code").val(str);
					$("#code").focus();
					$("#st tbody").empty();
				}); 
			 	 //鼠标移到到那一行改变颜色
			 	$("#st").delegate("tr","mouseover",function(){
					$(this).addClass("over");
				}); 
			 	 //鼠标移出到那一行改变颜色
				$("#st").delegate("tr","mouseout",function(){
					$(this).removeClass("over");
				});  
			 	$("#code").keyup(function(){
				var code = $("#code").val();
				if(code==null||code==""){
					$("#st tbody").empty();
					return;
				} 
			 	var emp_id; 
			 	var t_name = 'sales_id';//要查询的字段
			 	var table_name = 'smis_sales';//要查询的表名
			  	var reg = code+'%'; //定义匹配规则(从前至后匹配)
					$.ajax({
			     		url : "<%=basePath%>/Manage/FuncPanel/getSechInfo.do",
			     		type : "post",
			     		async : false,
			     		data : {
			     			"emp_id" : emp_id,
			     			"keyWord" : code,
			     			"t_name" : t_name,
			     			"table_name" : table_name,
			     			"reg" : reg
			     		},
			     		dataType:"json",
			     		success : function(data) {
			     			$("#st tbody").empty();
			     			var tbodyHtml="";
			     			var arr = new Array();
			     			$.each(data,function(index,comment){
			     				tbodyHtml+='<tr>'
		     								+'<td id="newTd">'+comment.seachMsg+'</td>'
			     							+'</tr>';
			     				arr[index] = comment.seachMsg;
			     			}); 
		     				$("#st tbody").append(tbodyHtml);
		     				if(arr.length == 1 && code == arr[0] ){  //当输入完整时去掉带出的那条数据
			     				$("#st tbody").empty();
			     			}
		     				if(tbodyHtml==""){
		     					$("#promptMessage").css({ display: "none" });
		     				}else{
			     				$("#promptMessage").css({ display: "block" });
		     				}
			     		}
					});
		 	}); 
		}); 
		</script>
</head>
<body style="height: 750px">
	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i> 协议管理 </span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>协议管理</span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>
				解除</span>
		</div>
		<!-- 面包屑导航  end -->

		<!--增加面板 start -->
		<div class="row-fluid">
			<form id="queryForm" name="queryForm"
				class="form-horizontal alert alert-info fade in span12"
				action="<%=basePath%>/organization/Protocol/upProReason.do"
				method="POST" enctype="multipart/form-data" autocomplete="off">
				<!-- 提示信息标签 -->
				<div id="dialog" title="提示信息" style="display: none">
					<center>
						<image id="dialog_img"></image>
						</br> <span>${rmHelper.msgStr}</span>
					</center>
				</div>
				<!-- 路径-->
				<webTag:HiddenInputTag id="path" name="path" value='<%=basePath%>' />
				<!-- value为后台返回的 true 或者false-->
				<webTag:HiddenInputTag id="result_flag" name="result_flag"
					value="${rmHelper.successflag}" displayLable="状态位" />
				<webTag:HiddenInputTag id="msg" name="msg"
					value="${rmHelper.msgStr}" displayLable="状态信息" />
				<!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				<webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				<webTag:HiddenInputTag name="seq_id" id="seq_id"
					value="${rmHelper.returnParams.seq_id}"></webTag:HiddenInputTag>
				<webTag:HiddenInputTag name="sign_type" id="sign_type" value="1"></webTag:HiddenInputTag>

				<fieldset>
					<div class="row" style="margin-left: -30px; margin-right: -40px">
						<webTag:Text id="agreement_no" name="agreement_no"
							value='${rmHelper.returnParams.agreement_no}' displayLable="协议号" />
						<webTag:Date id="enddate" name="enddate"
							value='${rmHelper.returnParams.enddate}' displayLable="提前终止日期"
							dateFormat="yyyy-MM-dd" isdisplay="true" />
					    <webTag:Text id="reason" name="reason"
							value='${rmHelper.returnParams.reason}' displayLable="提前终止原因" />	
					</div>

					<div class="row" style="text-align: right;">
						<button type="submit" class="btn btn-danger">
							<i class="icon-inbox icon-white"></i>保存
						</button>
						<a class="btn btn-danger"
							href="<%=basePath%>/organization/Protocol/goProtocolList.do"
							style='text-decoration: none;'><i
							class="icon-share-alt icon-white"></i>返回</a>
					</div>
					<!-- /.row -->
				</fieldset>
			</form>
		</div>
		<!-- 增加面板 end -->
	</div>
	<!-- 		底部高度填充块 -->
	<div class="zeoBottom"></div>
	<!-- 		底部高度填充块 结束-->
</body>
<script type="text/javascript">
    $(document).ready(function() {
    var a= $(window.parent.document).find("#sidebar").height();
    a=a+150;
	$(window.parent.document).find("#ffame").css("height",""+a+"px");
    });
    </script>
</html>
