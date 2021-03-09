<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="com.newtouch.core.quanxianguanli.pojo.User"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "";
	User user = (User)session.getAttribute("CF_USER");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>新致金保通</title>
<link rel="shortcut icon"
	href="<%=basePath%>/resources/ca/cacore/img/jv.png">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="CACore">
<meta name="author" content="Newtouch">

<!-- fram start -->
<script type="text/javascript"
	src="<%=basePath%>/compent/jquery/jquery-1.9.1.min.js"></script>
<!-- jQuery -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
<script type="text/javascript" src="<%=basePath%>/compent/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang_main_menu.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var jsonData = ${json}.retList; 
		var menuTree = $("#menuBlock");
		var str = "";
		for(var oneRow in jsonData){
			var jsonValue = jsonData[oneRow];
			var ul = menuTree.find("ul[id='"+jsonValue["pid"]+"']");
			if(jsonValue["aurl"] == "" && ul[0] == undefined){
				str = "<li><a>"+jsonValue["name"]+"</a><ul class='treeNode hasKind' id='"+jsonValue["id"]+"' ></ul></li>";
			} else if(jsonValue["aurl"] == "" && ul[0] != undefined){
				str = "<li><a>"+jsonValue["name"]+"</a><ul class='treeNode' id='"+jsonValue["id"]+"' ></ul></li>";
			} else {
				str = "<li><a class='hasurl' id='"+jsonValue["aurl"]+"'>"+jsonValue["name"]+"</a></li>";
			}
			if(ul[0] == undefined){
				menuTree.find("ul[id='treeF']").append(str);
				continue;
			}
			ul.append(str);
		}
		var meinMenuB = $("#meinMenuB");
		var menuBlock = $("#menuBlock");
		var meinIfram = $("#mainIframe");
		var kinderTree= $("#treeF");
		var mainpage= $("#mainpage");
		var hasKind = $("[class='treeNode hasKind']");
		var treeNode = $("[class='treeNode']");
		var option = {
			MeinMenuB : meinMenuB,
			MenuBlock : menuBlock,
			MeinIfram : meinIfram,
			KinderTree : kinderTree,
			Mainpage :   mainpage
		};
		$("#bottom").mainMenu(option);
		$("#treeF").mainTree();
		hasKind.each(function(i, value){
			$(value).mainTree();
		});
		treeNode.each(function(i, value){
			$(value).mainTree();
		});
	});
</script>
<!-- new fram end -->
</head>
<body>
	<div class="main">
		<div class="top">
			<div class="topMiddle">
				<div class="topLogo"></div>
				<div class="topLink">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td><a id="mainpage"  name="<%=basePath%>/redirect/redirect.do?linkUrl=ca/cacore/main">首页</a></td>
							<td valign="top">|</td>
							<td><a target="mainIframe" href="<%=basePath%>/goUserMdfPassPage.do">修改密码</a></td>
							<td valign="top">|</td>
							<td><a id="exitBtn" href="<%=basePath%>">退出</a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="topNameBK">
			<div class="topNameMiddle">
				<div class="topName"></div>
				<div class="userName">
					<table cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td>登录姓名：</td>
							<td id="name"><%=user.getOptName() %></td>
							<td>所属机构：</td>
							<td id="adresse"><%=user.getDeptName() %></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="warp">
		  <div class="iframewarp">																				
     		  <iframe id="mainIframe" name="mainIframe" src="<%=basePath%>/redirect/redirect.do?linkUrl=ca/cacore/main" width="960px" frameborder="0" scrolling="no"></iframe>
		  </div>
		</div>
	</div>
	<div class="bottom" id="bottom">
		<div class="bottomMiddle">
			<div class="bottomMenuLink">
				<a id="meinMenuB">主菜单</a>
			</div>
			<div class="bottomMenu" id="menuBlock">
				<ul id="treeF">

				</ul>
			</div>

		</div>
	</div>
</body>
</html>