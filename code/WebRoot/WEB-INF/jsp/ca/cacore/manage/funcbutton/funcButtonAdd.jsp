<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
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
			
        <script >
		$(document).ready(function(){
		$( "#dialog" ).dialog({
			autoOpen: false,
			width: 400,
			buttons: [
				{
					text: "确定",
					click: function() {
						
						$( this ).dialog( "close" );
					}
				},
				{
					text: "关闭",
					click: function() {
					    $("#menu_id").val("");
					    $("#menu_name").val("");
						$( this ).dialog( "close" );
					}
				}
			]
		});
		$( "#dialog-link" ).click(function( event ) {
			$( "#dialog" ).dialog( "open" );
			event.preventDefault();
		});
		});
		function openTree(){
		$( "#dialog" ).dialog( "open" );
		<!--window.showModalDialog("<%=basePath %>/redirect/redirect.do?linkUrl=ca/cacore/manage/funcbutton/funcmentTree","dialogHeight:350px;dialogWidth:450px;resizable:yes");
			$("#treeInfo").load("funcmentTree.jsp");
			$('#treeDialog').dialog('option', 'title', "菜单树");
			$('#treeDialog').dialog('open');-->
			
		}
		
		</script>
		<script >
       		var setting = {
			data: {
				key: {
					title:"t"
				},
				simpleData: {
					enable: true
				},
				  treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
		        treeNodeParentKey : "pId"        //在isSimpleData格式下，当前节点的父节点id属性  
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};
        var treeNodes ="";
		$.ajax({
					type : "POST",
					async: false,
					url : "<%=basePath %>/FuncMenu/funcMenuTree.do",
					dataType : "json", 
					error: function () {//请求失败处理函数  
			            alert('请求失败');  
			        },  
					success : function(data) {
						 treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes  
					}
				});  
		var log, className = "dark";
		function beforeClick(treeId, treeNode, clickFlag) {
			className = (className === "dark" ? "":"dark");
			return (treeNode.click != false);
		}
		function onClick(event, treeId, treeNode, clickFlag) {
			   //	alert(treeNode.id+","+treeNode.pId + ", " + treeNode.name);//点击直接返回节点对象treeNode
			  
			   if(treeNode.pId!=null){
			   		$("#menu_id").val(treeNode.id);
					$("#menu_name").val(treeNode.name);
			   }
		}		
	
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, treeNodes);
		});
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="row-fluid">
				<ul class="breadcrumb">
					<li>按钮管理功能 <span class="divider">/</span></li>
					<li>功能按钮添加</li>
				</ul>
			</div>
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/FuncButton/funcButtonAdd.do" method="POST">
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
					
					<!-- ui-dialog -->
					<div id="dialog" title="菜单树">
						<div class="row" style="text-align:center;">
							<div class="zTreeDemoBackground center">
								<ul id="treeDemo" class="ztree"></ul>
							</div>
						</div>
					</div>
					<div class="row">
						<webTag:HiddenInputTag id="menu_id" name="menu_id" value='${rmHelper.returnParams.menu_id}' displayLable="菜单代码"/>
						<webTag:Text id="button_id" name="button_id" value='${rmHelper.returnParams.button_id}' displayLable="按钮代码"/>
						<webTag:Text id="button_name" name="button_name" value='${rmHelper.returnParams.button_name}' displayLable="按钮名称"/>
					</div><!-- /.row -->
					<div class="row">
                         <webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' displayLable="备注"/>
					</div><!-- /.row -->
					<div class="row">
					</div><!-- /.row -->
				    <div class="row" style="text-align:right;">
					    		<button type="submit" class="btn btn-mini btn-primary"><i class="icon-search icon-white"></i>保存</button>
					    		<a class="btn btn-mini btn-primary" href="<%=basePath %>/FuncButton/queryFuncButton.do"><i class="icon-plus icon-white"></i>返回</a>
					</div><!-- /.row -->
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		 <div id="treeDialog" title="菜单树" align="center">
	     <div id="treeInfo"></div>
    </div>
	</body>
</html>
