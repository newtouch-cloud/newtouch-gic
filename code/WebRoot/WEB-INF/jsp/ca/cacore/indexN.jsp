<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.newtouch.core.quanxianguanli.pojo.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
User user = (User)session.getAttribute("CF_USER");
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<link rel="shortcut icon" href="<%=basePath %>/resources/ca/cacore/img/jv.png">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="CACore">
		<meta name="author" content="Newtouch">
		
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href='<%=basePath %>/compent/charisma/css/opa-icons.css' >
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<script type="text/javascript" src="<%=basePath %>/compent/charisma/js/bootstrap-dropdown.js"></script><!-- custom dropdown library -->
		<script type="text/javascript" src="<%=basePath %>/compent/charisma/js/bootstrap-tab.js"></script><!-- library for creating tabs -->
		<!-- fram start -->
		
		<!-- fram plugins start-->
		<link rel="stylesheet" type="text/css" href="<%=basePath %>/compent/easyui/bootstrap/easyui.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/ztree/zTreeStyle.css" type="text/css"><!-- ztree -->
		<script type="text/javascript" src="<%=basePath %>/compent/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/ztree/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang_main_menu.js"></script>
		<!-- fram plugins end! -->
		<SCRIPT type="text/javascript">
			var index = 0;
	        function addPanel(title,url){
	            index++;
	            $('#contentTabs').tabs('add',{
	                title: title,
	                content: createFrame(url),
					closable: true
	            });
	        }
	        
	        function createFrame(url){
	            return "<iframe height='99%' width='100%' src='"+url+"' frameBorder=0 noresize='noresize'></iframe>";
	        }
	        
	        //树形控件展现设置
			var zTreeMainSetting = {
					data: {
		                simpleData: {enable:true, idKey: "id", pIdKey: "pid", rootPId: "" , nameKey:"name"
		                }
					},
					callback: {
						beforeClick: function(treeId, treeNode) {
							var title = treeNode.name;
							var url = treeNode.aurl;
							var isExisit = $('#contentTabs').tabs('exists', title);
			            	if (isExisit) {
			            		$('#contentTabs').tabs('select', title);
			            	}else{
			            		if(url){
			            			var url = treeNode.open_type=="outweb"?url:url;
			            			addPanel(title,url);
			            		}
			            	}
						}
					}
				};
			$(document).ready(function(){
				var treeObj = $.fn.zTree.init($("#mainMenuTree"), zTreeMainSetting, ${json}.retList);
				var nodes = treeObj.getNodes();
				if(nodes.length > 0){
					treeObj.expandNode(nodes[0], true, false, true);
				}
			});
		</SCRIPT>
	</head>
	<body style="padding-top:40px" class="easyui-layout">
		<!-- 布局:上方横幅 -->
		<div region="north" border="false" style="height:50px;background:#B3DFDA;overflow:hidden">
			<!-- 导航条 -->
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container-fluid">
						<!-- .btn-navbar is used as the toggle for collapsed navbar content -->
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</a>
						<!-- 公司LOGO -->
						<a class="brand" href="#"><img class="logo" src="<%=basePath %>/compent/login/images/logo_login.png" /></a>
						<!-- 公司LOGO end -->
						
						<!-- 用户控件 -->
						<div class="btn-group pull-right" >
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon-user"></i><span class="hidden-phone"> admin</span>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="<%=basePath %>"><i class="icon-home"></i> 退出</a></li>
							</ul>
						</div>
						<!-- 用户控件 end -->
					</div>
				</div>
			</div>
			<!-- 导航条 end -->
		</div>
		<!-- 布局:上方横幅  end-->
		
		<!-- 布局:左方菜单 start-->
		<div region="west" split="true" title="<%=user.getOptName() %>，您好" style="width:200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="核心业务系统菜单" data-options="selected:true">
					<div class="content_wrap">
						<div class="zTreeDemoBackground left">
							<ul id="mainMenuTree" class="ztree"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 布局:左方菜单  end-->
		
		<!-- 布局:右侧操作区 start-->
		<div region="center" border="true" style="border-left:0px;border-right:0px;overflow:hidden;">		
			<div class="easyui-layout" id="subWrap" fit="true" style="width:100%;height:100%;">
				<div region="center" border="true" split="true" style="">
					<div id="contentTabs" class="easyui-tabs" fit="true" >
						<div title="主页" data-options="closable:false" style="padding:10px">
							welcome
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 布局:右侧操作区  end-->
		
		<!-- 布局:下方横幅  end-->
		<div region="south" border="false" style="height:30px;">
			<footer>
				<p class="pull-left">&copy; <a href="#" target="_blank">cinda</a> 2014</p>
				<p class="pull-right">Powered by: <a href="#">Newtouch</a></p>
			</footer>
		</div>
		<!-- 布局:左方横幅  end-->
	</body>
</html>