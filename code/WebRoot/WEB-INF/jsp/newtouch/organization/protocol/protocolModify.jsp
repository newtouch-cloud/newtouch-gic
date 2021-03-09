<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp" %>
		<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" %>
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/>
		<script >
		<%-- jQuery.validator.addMethod("branchInfo",function(value,element){
		     var code = $("#code").val();
			 $.ajax({
					 url:"<%=basePath %>/organization/protocol/getProtocolPersonInformation.do",
				 type:"post",
				 async: false,
				 dataType : "json",
				 data:{"code":code},
				 success:function(data){
					  $.each(data,function(index,comment){
						 var isSuccess = comment.success;
						 if("true"==isSuccess){
							 var code = comment.code;
							  var name = comment.name;
							 $("#code").val(code);
							 $("#name").val(name);
							 $("#flag").val("0");
						 }else{ 
						        //$("#code").val("");
						      // $("#name").val("");
							  if("false1"==isSuccess){
								 var code = comment.code;
								 var name = comment.name;
								 $("#code").val(code);
								 $("#name").val(name); 
							     $("#flag").val("1");
							  }else if("false2"==isSuccess){
								  $("#flag").val("2");
							  }
				         }
					  });
				}
			 });
			 
			 if($("#flag").val()=="1"&&""!=code){
		     		return false;
		        	}else {
		     		return true;
		        	}
	 			 },"签订人工号不存在,请重新输入");  --%>
		   
		  /*  jQuery.validator.addMethod("branchInfo1",function(value,element){
				  if($("#flag").val()=="2"){
			     		return false;
			        	}else{
			     		return true;
			        	}
		   },"输入的签订人代码所属签订人已有签订信息，请重新输入。"); */
		   
		 //验证 生效日期-终止日期  前后顺序正确性校验
			 jQuery.validator.addMethod("checkDate1",function(value,element){
		    	var startdate=$("#startdate").val();
				var enddate=$("#enddate").val();
				var flag = true;
		   		if(!isUndefined(startdate)&&!isUndefined(enddate)){
		   			if(startdate>enddate){
		   				flag=false;
		   			}
		   		}
		    	if(!flag){
		    		return false;
		    	}else{
		    		$("label:contains('日期输入顺序有误')").remove();
		    		return true;
		    	}
		   	},"日期输入顺序有误"); 
			//验证 生效日期-终协议签订日期  前后顺序正确性校验
			 jQuery.validator.addMethod("checkDate2",function(value,element){
		    	var startdate=$("#startdate").val();
				var dateofsign=$("#dateofsign").val();
				var flag = true;
		   		if(!isUndefined(startdate)&&!isUndefined(dateofsign)){
		   			if(dateofsign>startdate){
		   				flag=false;
		   			}
		   		}
		    	if(!flag){
		    		return false;
		    	}else{
		    		$("label:contains('日期输入顺序有误')").remove();
		    		return true;
		    	}
		   },"日期输入顺序有误"); 
			function isUndefined(paraValue){
		       if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		       return false;
		   }
			 
			 jQuery.validator.addMethod("checkHasCHN1", function(agreement_no, element) {
				 var reg = /[\u4E00-\u9FA5]/g;
				   if(reg.test(agreement_no)){
				    return false;
				   }else{
				    return true;
				   }
			}, "不能包含中文字符");
			
			function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
		   $(document).ready(function() {
				 $("#queryForm").validate({
					 rules:{
						 agreement_no:{//协议号 18 非空
							 required:true,
							 checkHasCHN1:[]
						 },
						 branch_name:{// 使用机构name 200
							 required:true
						 },
						 startdate:{// 生效日期   从 Date 非空
							 required:true,
							 checkDate1:[],
							 checkDate2:[]
						 },
						 enddate:{// 终止日期  从 Date 非空
							 required:true,
							 checkDate1:[]
						 },
						 dateofsign:{//协议签订日期 Date 非空
							 required:true,
							 checkDate2:[]
						 },
						 status:{//状态
							 required:true 
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
		<script type="text/javascript" src="<%=basePath%>/compent/default/js/attachmentModify.js"></script>   
	</head>
	<body style="height: 750px">
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 协议管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 协议管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 修改</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/organization/Protocol/modifyProtocol.do" enctype="multipart/form-data" method="POST">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				 <webTag:HiddenInputTag name="returnMsg" id="returnMsg" value="${rmHelper.returnParams.returnMsg}"></webTag:HiddenInputTag>
				 <webTag:HiddenInputTag name="removeflag" id="removeflag" value="${removeflag}"></webTag:HiddenInputTag>	
				 <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
				 <webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>	
				 <webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/> 
				 <webTag:HiddenInputTag name="seq_id" id="seq_id" value="${rmHelper.returnParams.seq_id}"></webTag:HiddenInputTag>
					

					<div class="row" style="margin-left:-20px;margin-right:-40px">    
					   <webTag:Text id="branch_name"  name="branch_name"   value='${rmHelper.returnParams.branch_name}'  displayLable="中介公司机构" isdisplay="true" readonly="true"/>
					   <webTag:Text id="branch_id"  name="branch_id"   value='${rmHelper.returnParams.branch_id}'  displayLable="中介公司机构" isdisplay="true" readonly="true"/>			
						<webTag:Text id="agreement_no"  name="agreement_no"   value='${rmHelper.returnParams.agreement_no}'  displayLable="协议号" isdisplay="true" readonly="true"/>
					</div>
				
					<div class="row" style="margin-left:-20px;margin-right:-40px">  
					<webTag:Date id="dateofsign" name="dateofsign" value='${rmHelper.returnParams.dateofsign}' displayLable="协议签订日期" isdisplay="true"/>         
						<webTag:Date id="startdate"  name="startdate"  value='${rmHelper.returnParams.startdate}'  displayLable="协议生效起期" isdisplay="true"/>
						<webTag:Date id="enddate"    name="enddate"    value='${rmHelper.returnParams.enddate}'    displayLable="协议生效止期" isdisplay="true"/>
					</div><!-- /.row -->
					
	                <div class="row" style="text-align:right;">  
				    <button id="submitBtn" type="submit" onclick="submitFormForFile('<%=basePath %>');" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
<!-- 			    		<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button> -->
					    <a class="btn btn-danger" href="<%=basePath %>/organization/Protocol/queryProtocolList.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
					</div><!-- /.row -->
				  </fieldset>	
				</form>
			</div>
			<!-- 数据区 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
	<script type="text/javascript">
    $(document).ready(function() {
    var a= $(window.parent.document).find("#sidebar").height();
	$(window.parent.document).find("#ffame").css("height",""+a+"px");
    });
</script>
</html>
