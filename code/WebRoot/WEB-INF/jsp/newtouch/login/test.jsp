<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.newtouch.core.quanxianguanli.pojo.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
User user = (User)session.getAttribute("CF_USER");

%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath %>/compent/xinzhi/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath %>/compent/xinzhi/assets/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=basePath %>/compent/xinzhi/assets/css/ace.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>/compent/xinzhi/css/xinzhi.css">
<script type="text/javascript" src="<%=basePath %>/compent/xinzhi/js/jquery-1.9.1.min.js"></script>
<script src="assets/js/ace-extra.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/compent/xinzhi/js/iframe.js"></script>
<script type="text/javascript" src="<%=basePath %>/compent/xinzhi/js/xinzhi.js"></script>
<title>首页</title>
</head>

<body>
<div id="header">
    <div class="header_t"> <a href="#"><img class="logo" src="img/logo.png" /></a>
        <div class="line"> <a href="#">首页</a>丨<a href="#">重置密码</a>丨<a href="#">退出</a> </div>
    </div>
    <div class="header_b">保险核心业务系统</div>
</div>
<div class="main">
    <div class="sidebar" id="sidebar"> 
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
        </script>
        <ul class="nav nav-list" id="ulmenu">
            <li> <a href="#" class="dropdown-toggle"> <i class="icon no1"></i> <span class="menu-text">产品管理 </span> <b class="arrow icon-angle-down"></b> </a>
                <ul class="submenu">
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 产品设置 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 产品定义 </a> </li>
                            <li> <a href="#"> <i></i> 产品维护 </a> </li>
                        </ul>
                    </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 产品组合设置 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 产品组合定义 </a> </li>
                            <li> <a href="#"> <i></i> 产品组合维护 </a> </li>
                        </ul>
                    </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 产品手续费率设置 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 产品手续费率设置 </a> </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li> <a href="#" class="dropdown-toggle"> <i class="icon no2"></i> <span class="menu-text">保单管理 </span> <b class="arrow icon-angle-down"></b> </a>
                <ul class="submenu">
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 新契约管理 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 投保单录入 </a> </li>
                            <li> <a href="#"> <i></i> 保单录入 </a> </li>
                            <li> <a href="#"> <i></i> 回执录入 </a> </li>
                            <li> <a href="#"> <i></i> 回执信息维护 </a> </li>
                            <li> <a href="#"> <i></i> 问题件录入 </a> </li>
                            <li> <a href="#"> <i></i> 投保单维护 </a> </li>
                            <li> <a href="#"> <i></i> 投保单状态变更 </a> </li>
                            <li> <a href="#"> <i></i> 投保单问题件管理 </a> </li>
                        </ul>
                    </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 保全管理 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 保全录入 </a> </li>
                            <li> <a href="#"> <i></i> 保全维护 </a> </li>
                            <li> <a href="#"> <i></i> 保单状态变更 </a> </li>
                            <li> <a href="#"> <i></i> 保单维护 </a> </li>
                            <li> <a href="#"> <i></i> 保全问题件管理 </a> </li>
                        </ul>
                    </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 理赔管理 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 理赔录入 </a> </li>
                            <li> <a href="#"> <i></i> 理赔维护 </a> </li>
                            <li> <a href="#"> <i></i> 理赔问题件管理 </a> </li>
                        </ul>
                    </li>
                    <li> <a href="#" class="dropdown-toggle" id="baodanfenpei"> <i class="icon-double-angle-right"></i> 保单分配 </a> </li>
                </ul>
            </li>
            <li> <a href="#" class="dropdown-toggle"> <i class="icon no3"></i> <span class="menu-text"> 中介人员管理 </span> <b class="arrow icon-angle-down"></b> </a>
                <ul class="submenu">
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 机构管理 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 机构变更轨迹 </a> </li>
                            <li> <a href="#"> <i></i> 机构管理 </a> </li>
                        </ul>
                    </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 薪酬管理 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 薪酬导入 </a> </li>
                            <li> <a href="#"> <i></i> 薪酬查询 </a> </li>
                        </ul>
                    </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 中介组织管理 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 组织维护管理 </a> </li>
                            <li> <a href="#"> <i></i> 组织状态变更历史查询 </a> </li>
                            <li> <a href="#"> <i></i> 组织异动管理 </a> </li>
                            <li> <a href="#"> <i></i> 主管任命管理 </a> </li>
                            <li> <a href="#"> <i></i> 团队拆分 </a> </li>
                        </ul>
                    </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 中介人员管理 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu">
                            <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 人员入司管理 <b class="arrow icon-angle-down"></b> </a>
                                <ul class="submenu" style="margin-left:15px;">
                                    <li> <a href="#"> <i></i> 入司录入 </a> </li>
                                </ul>
                            </li>
                            <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 人员信息管理 <b class="arrow icon-angle-down"></b> </a>
                                <ul class="submenu" style="margin-left:15px;">
                                    <li> <a href="#"> <i></i> 人员信息维护 </a> </li>
                                    <li> <a href="#"> <i></i> 人员信息修改轨迹 </a> </li>
                                </ul>
                            </li>
                            <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 人员职级变更管理 <b class="arrow icon-angle-down"></b> </a>
                                <ul class="submenu" style="margin-left:15px;">
                                    <li> <a href="#"> <i></i> 职级变更 </a> </li>
                                    <li> <a href="#"> <i></i> 职级变更轨迹 </a> </li>
                                </ul>
                            </li>
                            <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 人员解约管理 <b class="arrow icon-angle-down"></b> </a>
                                <ul class="submenu" style="margin-left:15px;">
                                    <li> <a href="#"> <i></i> 解约申请 </a> </li>
                                </ul>
                            </li>
                            <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 人员异动管理 <b class="arrow icon-angle-down"></b> </a>
                                <ul class="submenu" style="margin-left:15px;">
                                    <li> <a href="#"> <i></i> 人员异动 </a> </li>
                                    <li> <a href="#"> <i></i> 异动轨迹查询 </a> </li>
                                </ul>
                            </li>
                            <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 人员暂存管理 <b class="arrow icon-angle-down"></b> </a>
                                <ul class="submenu" style="margin-left:15px;">
                                    <li> <a href="#"> <i></i> 人员暂存查询 </a> </li>
                                </ul>
                            </li>
                            <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 团队异动管理 <b class="arrow icon-angle-down"></b> </a>
                                <ul class="submenu" style="margin-left:15px;">
                                    <li> <a href="#"> <i></i> 团队异动 </a> </li>
                                    <li> <a href="#"> <i></i> 异动轨迹查询 </a> </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li> <a href="#" class="dropdown-toggle"> <i class="icon no4"></i> <span class="menu-text">客户关系管理 </span> <b class="arrow icon-angle-down"></b> </a>
                <ul class="submenu">
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 客户添加及维护 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 客户查询 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 客户行为录入 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 客户行为查询 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 客户分析查询 </a> </li>
                </ul>
            </li>
            <li> <a href="#" class="dropdown-toggle"> <i class="icon no5"></i> <span class="menu-text">系统管理 </span> <b class="arrow icon-angle-down"></b> </a>
                <ul class="submenu">
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 审批管理 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 审批处理 </a> </li>
                        </ul>
                    </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 保险公司机构管理 </a> </li>
                </ul>
            </li>
            <li> <a href="#" class="dropdown-toggle"> <i class="icon no6"></i> <span class="menu-text">权限管理 </span> <b class="arrow icon-angle-down"></b> </a>
                <ul class="submenu">
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 角色管理 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 菜单管理 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 用户管理 </a> </li>
                </ul>
            </li>
            <li> <a href="#" class="dropdown-toggle"> <i class="icon no7"></i> <span class="menu-text">回访管理 </span> <b class="arrow icon-angle-down"></b> </a>
                <ul class="submenu">
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 保单回访录入 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 保单回访信息维护 </a> </li>
                </ul>
            </li>
            <li> <a href="#" class="dropdown-toggle"> <i class="icon no8"></i> <span class="menu-text">综合查询 </span> <b class="arrow icon-angle-down"></b> </a>
                <ul class="submenu">
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 投保单查询 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 保单查询 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 保单费用查询 <b class="arrow icon-angle-down"></b> </a>
                        <ul class="submenu" style="margin-left:15px;">
                            <li> <a href="#"> <i></i> 保单费用明细查询 </a> </li>
                            <li> <a href="#"> <i></i> 保单费用结算查询 </a> </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li> <a href="#" class="dropdown-toggle"> <i class="icon no9"></i> <span class="menu-text">影像管理 </span> <b class="arrow icon-angle-down"></b> </a>
                <ul class="submenu">
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 投保单影像件上传 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 投保单影像件查询 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 保单影像件上传 </a> </li>
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 保单影像件查询 </a> </li>
                </ul>
            </li>
            <li> <a href="#" class="dropdown-toggle"> <i class="icon no10"></i> <span class="menu-text">手续费参数管理 </span> <b class="arrow icon-angle-down"></b> </a>
                <ul class="submenu">
                    <li> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i> 手续费参数管理 </a> </li>
                </ul>
            </li>
        </ul>
        <div class="sidebar-collapse" id="sidebar-collapse"> <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i> </div>
    </div>
    <div class="page_tab">
    	<div class="page_tab_titles">
        	<ul>
            	<li class="page_tab_title visited">
                	<span class="title">首页</span>
                    <span class="close">x</span>
                </li>
            </ul>
        </div>
        <div class="page_tab_content">
        	<iframe class="iframe" name="mainIFrame" width="1158px" scrolling="no" onload="dyniframesize()" frameborder="0" src="html/tongji.html" allowtransparency="true"></iframe>
        </div>
        
    </div>
</div>
<script type="text/javascript">
	window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
</script>
<script type="text/javascript">
	if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
</script>
<script src="assets/js/ace.min.js"></script>
</body>
<script type="text/javascript">
	$(function(){loadMenu(${json});});
	
	function loadMenu(data){
		alert(data);
		var jsonData = data.retList;
		var ul = $("#ulmenu");
		$.each(jsonData,function(i,value){
			var pid = $(value).pid;
			var aurl = $(value).aurl;
			var name = $(value).name;
			var id = $(value).id;
			var firstLi = ul.find("li[id = '"+pid+"']");
			var firstUl = $(firstLi).find("ul").get(0);
			var str = '';
			if(pid == "1"){
				var pLi = ul.find("li[id = '"+id+"']");
				if(pLi[0] == undefined)
					ul.append("<li id = '"+id+"'></li>");
				if(aurl.length == 0)
					str = '<a href="#" class="dropdown-toggle"> <i class="icon no1"></i> <span class="menu-text">'+name+'</span> <b class="arrow icon-angle-down"></b> </a>';
				else
					str = '<a href="'+aurl+'" class="dropdown-toggle"> <i class="icon no1"></i> <span class="menu-text">'+name+'</span> <b class="arrow icon-angle-down"></b> </a>';
					
				$(firstLi).append(str);	
				
			}else{
				if(firstLi[0] == undefined){
					var p_id = findPid(jsonData,pid);
					if(p_id == "1"){
						ul.append('<li id = "'+pid+'"><a href="#" class="dropdown-toggle"> <i class="icon no1"></i> <span class="menu-text">'+name+'</span> <b class="arrow icon-angle-down"></b> </a></li>');
					}
					if(firstUl == undefined ){
						if(aurl != ""){
							$(firstLi).append("<ul class='submenu'style='margin-left:15px;'></ul>");
							$(firstUl).append('<li id="'+id+'"> <a href="'+aurl+'"> <i></i>' +name+ '</a> </li>');
						}else{
							$(firstLi).append("<ul class='submenu'></ul>");
							$(firstUl).append('<li id="'+id+'"> <a href="'+aurl+'" class="dropdown-toggle"> <i class="icon-double-angle-right"></i>' +name+ '</a> </li>');
						}
					}else{
						if(aurl != ""){
							$(firstUl).append('<li id="'+id+'"> <a href="'+aurl+'"> <i></i>' +name+ '</a> </li>');
						}else{
							$(firstUl).append('<li id="'+id+'"> <a href="'+aurl+'" class="dropdown-toggle"> <i class="icon-double-angle-right"></i>' +name+ '</a> </li>');
						}
					}
				}else{
					if(firstUl == undefined ){
						if(aurl != ""){
							$(firstLi).append("<ul class='submenu'style='margin-left:15px;'></ul>");
							$(firstUl).append('<li id="'+id+'"> <a href="'+aurl+'"> <i></i>' +name+ '</a> </li>');
						}else{
							$(firstLi).append("<ul class='submenu'></ul>");
							$(firstUl).append('<li id="'+id+'"> <a href="'+aurl+'" class="dropdown-toggle"> <i class="icon-double-angle-right"></i>' +name+ '</a> </li>');
						}
					}else{
						if(aurl != ""){
							$(firstUl).append('<li id="'+id+'"> <a href="'+aurl+'"> <i></i>' +name+ '</a> </li>');
						}else{
							$(firstUl).append('<li id="'+id+'"> <a href="'+aurl+'" class="dropdown-toggle"> <i class="icon-double-angle-right"></i>' +name+ '</a> </li>');
						}
					}
				}
			}
		})
	}
	
	function findPid(data,id){
		var pid = "";
		for(var i in data){
			if(id == data[i]["id"]){
				pid = data[i]["pid"];
			}
		}
		return pid;
	}
</script>
</html>
