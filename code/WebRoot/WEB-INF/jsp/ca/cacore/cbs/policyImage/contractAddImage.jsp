<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs-1.7.2.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script >
		$(document).ready(function() {
			var params=encodeURI(encodeURI('${rmHelper.returnParams.params}'));
			 //dialog 信息
       	    $("#dialog").dialog({
       	        autoOpen:false,
       	        buttons:[{
       	            text:"是",
       	            click:function(){
		       	            var href = "<%=basePath %>/cbs/policyImage/QueryAddContractImage.do";
		       	 			//将查询参数拼接到请求超链接上
		       	 			params = params.replace(/!/g, "&");
		       	 			if(href!=null&&href.indexOf("'")==-1){
		       	 				//如果返回的请求地址中原来没有参数，那么要增加'?'
		       	 				if(href.indexOf("?")==-1){
		       	 					href+="?1=1"+params;
		       	 				}else{
		       	 					href+=params;
		       	 				}
		       	 			}else{
		       	 				var url=href.substring(0,href.lastIndexOf("'"));
		       	 				href=url+params+"'";
		       	 			}
		       	            location.href=href;
		       	            $("#dialog").dialog("close");
       	                }
       	            },
       				{
       					text: "否",
       					click: function() {
       						$('#submitBtn').attr('disabled',' disabled');//设置置灰保存按钮
       						$('#uploadifynew-button').remove();
       						$( this ).dialog( "close" );
       					}
       				}
       	            ],
       	            position:"top",//弹出位置
       	            modal:true,
       	            width:500, //窗口宽度
       	            height:280,
       	            dialogClass: "my-dialog",
       	            closeText:false,
       	            drag:function(){
       	                
       	            }

       	        });
       		 var result_flag=$("#result_flag").val();
       		 var msg=$("#msg").val();
       		 var path=$("#path").val();
       		 if(msg!=""){
        		 var src_cg=path+"/compent/charisma/img/xiao.png";
        		 var src_sb=path+"/compent/charisma/img/ku.png";
        		 if(result_flag=="true"){
         	    	$("#dialog_img").attr("src",src_cg);
         	    	 $("#dialog").dialog("open");
         	     }else if(result_flag=="false"){
         	    	$("#dialog_img").attr("src",src_sb);
         	    	$("#dialog").dialog("open");
         	     }
       		 }	 
			
			
		});
		
		//保存时校验是否有相应的影像信息
		function checkSubmit(){
			//表达提交校验
	     			var length = $("form").has("img").length
	     			if(length >　0){
	     				return true;
	     			}else{
	     				alert("没有找到影像信息,不能进行保存操作");
	     				return false;
	     			}
		}
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 影像管理</span><span class="divider">/</span>
				<span><i class="icon-list icon-red"></i> 保单影像件上传</span>
			</div>
			<!-- 面包屑导航  end -->
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" onsubmit="return checkSubmit();" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CBS/policyImage/concernAddContractImage.do" method="POST">
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>
					<webTag:HiddenInputTag id="send_code" name="send_code" value='${rmHelper.returnParams.send_code}'/>
					<webTag:HiddenInputTag id="policy_id" name="policy_id" value='${rmHelper.returnParams.policy_id}'/>
				    <div class="row">
					    <webTag:DynamicSelectTag src="insBranchSelect" name="insBranch_id" id="insBranch_id" displayLable="保险公司机构" value='${rmHelper.returnParams.insBranch_id}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"/>
					    <webTag:DynamicSelectTag src="imageBusTypeSelect" name="bus_type" id="bus_type" displayLable="上传类型" value='${rmHelper.returnParams.bus_type}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" isdisplay="true"/>
                        <webTag:Text id="policy_code" name="policy_code" value='${rmHelper.returnParams.policy_code}' displayLable="所属保单号" readonly="true" isdisplay="true"/>
					</div>
					<div class="row">
						<webTag:Text id="branch_id"  name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码:" readonly="true"/>
						<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="机构名称:" readonly="true"/>
						<webTag:Text id="app_name" name="app_name" value='${rmHelper.returnParams.app_name}' displayLable="投保人:" readonly="true"/>
					</div>
					<div id="uploadimage" style="width:100%;"> 
					  	<jsp:include page="../policyImage/imageAdd.jsp"></jsp:include>
				  	</div>	
				    <div class="row" style="text-align:right;">
				    		<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
				    		<a class="btn btn-danger" href="<%=basePath %>/cbs/policyImage/QueryAddContractImage.do" ><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				</form>
			</div>
			<!-- 查询面板 end -->
			 
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
