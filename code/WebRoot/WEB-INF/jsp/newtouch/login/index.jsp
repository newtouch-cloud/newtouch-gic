<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.quanxianguanli.pojo.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
User user = (User)session.getAttribute("CF_USER");

%>
<html lang="en">
	<head>
		<title>中介管理平台</title>
		<link rel="shortcut icon" href="<%=basePath %>/resources/touch/jvsmis/img/touch.png"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
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
	            return "<iframe height='99%' width='99%' src='"+url+"?funcName="+url+"' frameBorder=0 noresize='noresize'></iframe>";
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
			var zNodes = [{ id:30, pid:0, name:"业务数据处理",aurl:""},
			              { id:3003, pid:30, name:"计算申请",aurl:"goJiSuanXinZengPage.do"},
			              { id:3004, pid:30, name:"批处理查询",aurl:"goJiSuanShenQingPage.do"},
			              
			              { id:40, pid:0, name:"权限管理",aurl:""},
			              { id:4001, pid:40, name:"角色管理",aurl:"goRoleMgmtQueryPage.do"},
			              { id:4002, pid:40, name:"菜单管理",aurl:"goMenuMgmtQueryPage.do"},
			              { id:4003, pid:40, name:"用户管理",aurl:"goUserMgmtQueryPage.do"},
			              
			              { id:150, pid:0, name:"元数据管理",aurl:""},
			              { id:15001, pid:150, name:"参数设置",aurl:"goCanShuSheZhi.do"},
			              { id:15004, pid:150, name:"数据项维护",aurl:""},
			              { id:15005, pid:150, name:"实体维护",aurl:""},
			              
			              { id:99, pid:0, name:"垫底",aurl:""},
			              { id:9901, pid:99, name:"垫底",aurl:""}];
			//zNodes = ${json}.retList;
			$(document).ready(function(){
				var treeObj = $.fn.zTree.init($("#mainMenuTree"), zTreeMainSetting, zNodes);
				var nodes = treeObj.getNodes();
				if(nodes.length > 0){
					treeObj.expandNode(nodes[0], true, false, true);
				}
// 				$.fn.zTree.init($("#deptTree"), zTreeDeptSetting);
				$("#deptTree").load("<%=basePath %>/depttreejsp.do?funcName=depttreejsp.do");
// 				var deptNodes = deptObj.getNodes();
// 				if(deptNodes.length > 0){
// 					deptObj.expandNode(deptNodes[0], true, false, true);
// 				}
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
						<a class="brand" href="#"><span>PoSM</span></a>
						<!-- 公司LOGO end -->
						
						<!-- 系统切换控件 -->
						<!-- <div class="btn-group pull-right" >
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon-tint"></i><span class="hidden-phone"> 系统</span>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a data-value="classic" href="#"><i class="icon-blank"></i> 中介人员管理系统</a></li>
								<li><a data-value="cerulean" href="#"><i class="icon-blank"></i> 代理人门店系统</a></li>
								<li><a data-value="cyborg" href="#"><i class="icon-blank"></i> 后台管理系统</a></li>
							</ul>
						</div> -->
						<!-- 系统切换控件 end -->
						
						<!-- 皮肤控件 -->
						<!-- <div class="btn-group pull-right" >
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon-tint"></i><span class="hidden-phone"> 换肤</span>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" id="themes">
								<li><a data-value="classic" href="#"><i class="icon-blank"></i> 经典 Classic</a></li>
								<li><a data-value="cerulean" href="#"><i class="icon-blank"></i> 蔚蓝 Cerulean</a></li>
								<li><a data-value="cyborg" href="#"><i class="icon-blank"></i> 赛博格 Cyborg</a></li>
								<li><a data-value="redy" href="#"><i class="icon-blank"></i> 粉红 Redy</a></li>
								<li><a data-value="journal" href="#"><i class="icon-blank"></i> 书刊 Journal</a></li>
								<li><a data-value="simplex" href="#"><i class="icon-blank"></i> 简单 Simplex</a></li>
								<li><a data-value="slate" href="#"><i class="icon-blank"></i> 石板 Slate</a></li>
								<li><a data-value="united" href="#"><i class="icon-blank"></i> 橙色 United</a></li>
							</ul>
						</div> -->
						<!-- 皮肤控件 end -->
						
						<!-- 用户控件 -->
						<div class="btn-group pull-right" >
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								<i class="icon-user"></i><span class="hidden-phone"> </span>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<!-- <li><a href="#"><i class="icon-user"></i> 用户中心</a></li>
								<li><a href="#"><i class="icon icon-black icon-key"></i> 密码修改</a></li>
								<li><a href="#"><i class="icon-book"></i> 帮助培训</a></li> -->
								<li><a href="#"><i class="icon-wrench"></i> 系统设置</a></li>
								<li class="divider"></li>
								<li><a href="<%=basePath %>/runloginOut.controller"><i class="icon-home"></i> 退出</a></li>
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
				<div title="组织结构">
					<div class="content_wrap">
						<div class="zTreeDemoBackground left">
							<div id="deptTree" class="ztree"></div>
						</div>
					</div>
				</div>
				<div title="销管系统菜单" data-options="selected:true">
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
							Welcome to CreditEase PoSM
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 布局:右侧操作区  end-->
		
		<!-- 布局:下方横幅  end-->
		<div region="south" border="false" style="height:30px;">
			<footer>
				<p class="pull-left">&copy; <a href="#" target="_blank">CreditEase</a> 2013</p>
				<p class="pull-right">Powered by: <a href="#">Newtouch</a></p>
			</footer>
		</div>
		<!-- 布局:左方横幅  end-->
	</body>
</html>