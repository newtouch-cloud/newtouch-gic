<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
    <%@ page import="com.newtouch.core.quanxianguanli.pojo.User"%>
        <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "";
	User user = (User) session.getAttribute("CF_USER");
%>
            <html lang="en">

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                <link rel="icon" type="image/png" href="<%=basePath%>/compent/login/images/logo.png">
                <link href="<%=basePath%>/compent/xinzhi/assets/css/bootstrap.min.css" rel="stylesheet" />
                <link rel="stylesheet" href="<%=basePath%>/compent/xinzhi/assets/css/font-awesome.min.css" />
                <link rel="stylesheet" href="<%=basePath%>/compent/xinzhi/assets/css/ace.min.css" />
                <link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/xinzhi/css/xinzhi.css">
                <script type="text/javascript" src="<%=basePath%>/compent/xinzhi/js/jquery-1.9.1.min.js"></script>
                <script src="<%=basePath%>/compent/xinzhi/assets/js/ace-extra.min.js"></script>
                <script type="text/javascript" src="<%=basePath%>/compent/xinzhi/js/iframe.js"></script>
                <script type="text/javascript" src="<%=basePath%>/compent/xinzhi/js/xinzhi.js"></script>
                <title>首页</title>
                <style>
                    .header_t {
                        background: #FFFFFF;
                        /*background: url(<%=basePath%>/compent/login/images/top.png) no-repeat;*/
                    }
                </style>
                <script type="text/javascript">
                    $(document).ready(function() {
                        mimatishi();
                        
						var img1 = new Image(); 
						// 为Image对象添加图片加载成功的处理方法 
						img1.onload = function() { 
						    //alert("图像加载成功"); 
							$("#hezi").attr('src',img1.src);
						}; 
						// 为Image对象添加图片加载失败的处理方法z 
						img1.onerror = function() { 
						    //alert("图像加载失败"); 
							$("#hezi").attr('src','<%=basePath%>/compent/xinzhi/img/ufc-banner.gif');
						} 
						// 开始加载图片 
						img1.src = 'https://ufc.newtouch.com/xz-data-admin/ufc-banner.gif';
                        
                    });

                    function mimatishi() {
                        $.ajax({
                            url: "<%=basePath%>/AMS/userMgmtController/mimaDaoQiTiShi.do",
                            type: "post",
                            async: false,
                            data: {},
                            success: function(data) {
                                if (data != null && data > -1) {
                                    $("#mimatishi").html("您的密码还有" + data + "天到期，请尽快修改。丨");
                                } else {
                                    $("#mimatishi").hide();
                                }
                            }
                        });
                    }
                </script>
            </head>

            <body>
                <div class="main">

                    <div id="header">
                        <img class="logo" src="<%=basePath%>/compent/xinzhi/img/logo_home@2x.png" style="width: 14.9%;height: 96px;margin: 0px">
                        <div class="header_t" style="float: right;position: relative">

                            <a href="#" style="color: #333333; line-height: 58px; font-size: 14px; margin-left: 14px;">
                                <%--<img class="logo" src="<%=basePath%>/compent/xinzhi/img/logo_home@2x.png" style="width: 200px;height: 96px;margin: 0px">--%>
                                欢迎您，<a id="name" style="size: 14px;color: #0c0c0c"><%=user.getOptName() %></a>
					<!--<img class="logo" src="<%=basePath%>/compent/login/images/top.png" /> 中介服务有限公司-->
				            </a>
				            
                            <div class="line" id="linenew" >
                            	<!-- <span id="code"></span><a id="PingCode" style="color: #333333">访问地址</a> 丨 -->
                            	<%-- <span id="code"></span><img class="sy" src="<%=basePath%>/compent/xinzhi/img/icon_mima@2x.png" /></a> <a name="<%=basePath%>/compent/xinzhi/img/bgimage.jpg" id="QRCode" style="color: #333333">静态二维码</a> 丨 --%>
                                <span id="mimatishi"></span><img class="sy" src="<%=basePath%>/compent/xinzhi/img/icon_mima@2x.png" /></a> <a href="#goUserMdfPassPage.do" name="<%=basePath%>/goUserMdfPassPage.do" id="mdfpass" style="color: #333333">修改密码</a> 丨
                                <img class="sy" src="<%=basePath%>/compent/xinzhi/img/icon_tuichu@2x.png" /><a href="runloginOut2.controller" style="color: #333333;margin-left:4px">退出</a>
                            </div>
                            
                            	<script type="text/javascript">
	                            /*	var url = 'https://ufc.newtouch.com/xz-data-admin/ufc-banner.png';
	                            		var ajaxTimeoutTest = $.ajax({
	                            		  type:'get',
	                            		  url: url,
	                            		  timeout: 3000,
	                            		  dataType: 'json',
	                            		  async: true,
	                            		  complete: function (XMLHttpRequest,status) {  
	                            			  alert(url);
	                            			  alert(status);
											if(status!="error"){
												$("#hezi").attr("src",url)	;
											}
	                            		  }
	                            		});*/
	                             		 // 定义一个Image对象 
		
                            	</script>
                            <img id="hezi" class="code" src="<%=basePath%>/compent/xinzhi/img/ufc-banner.gif" style="float:right;margin-right: 23px;margin-top: 2px" >
                            <div id="test" style="border:1px solid #E7E4F3;height:190px;width:165px;position: absolute;z-index:1;right:275px;display:none;background:white;text-align:center">
           						<span id="closeCode" class="close-code close" style="position:absolute;right:-16px;top:-7px;opacity:1">&times;</span>
                            	<img src="<%=basePath%>/compent/xinzhi/img/code.png" style="height:160px">
                            	<span style="font-size:15px">扫码注册免费用1年</span>
                            	<script type="text/javascript">
                            	    var test=document.getElementById("test");
                            	    var hezi=document.getElementById("hezi");
                            		var closeCode=document.getElementById("closeCode");
                            	    closeCode.onclick=function() {
                            	        test.style.display="none";
                            	    };
                            	    hezi.onclick=function(){
                            	        test.style.display="block";
                            			
                            	    }
                            	
                            	</script>
                            </div>
                            
                        </div>
                        <!-- <div class="header_b"></div> -->
                    </div>
                    <div class="sidebar" id="sidebar" style="width: 15%;background-color: #272175;">
                        <script type="text/javascript">
                            try {
                                ace.settings.check('sidebar', 'fixed')
                            } catch (e) {}
                        </script>
                        <ul class="nav nav-list" id="ulmenu">
                        </ul>
                        <div class="sidebar-collapse" id="sidebar-collapse">
                            <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
                        </div>
                    </div>
                    <div class="page_tab" id='page_tab_one'>
                    
                    	<div class="page_tab_titles" style="background-color: #E7E4F3">
                                <ul>
                                    <li class="page_tab_title visited" id="index"><a class="brand" href="#"></a> <span class="title">首页</span> <span class="close">&times;</span></li>
                                </ul>
                        </div>
                        <%--<div class="page_tab_titles" style="height: 36px;background-color: #E7E4F3">
                            <ul>
                                <li class="page_tab_title visited" id="index"><a class="brand" href="#"></a> <span class="title">首页</span> <span class="close">&times;</span></li>
                            </ul>
                        </div>--%>
                        <!-- <div class="page_tab_content">
				<div
					style="background-image: url('./compent/xinzhi/img/bgimage.jpg'); background-size: 100% 100%; height: 660px; margin-top: 35px;">
				</div>
			</div> -->
                        <div class="page_tab_content">
                            <iframe class="iframe  iframe-bg" id="mainIFrame" name="mainIFrame" width="100%" scrolling="no" onload="dyniframesize()" frameborder="0" src="<%=basePath %>/tobgimage.do" allowtransparency="true">

				</iframe>
                        </div>
                    </div>
                    <script type="text/javascript">
                        window.jQuery || document.write("<script src='CACore/compent/xinzhi/assets/js/jquery-2.0.3.min.js'>" + "<" +
                            "script>");
                    </script>
                    <script type="text/javascript">
                        if ("ontouchend" in document) document.write(
                            "<script src='CACore/compent/xinzhi/assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
                    </script>
                    <script src="<%=basePath%>/compent/xinzhi/assets/js/ace.min.js"></script>
            </body>
            <script type="text/javascript">
                $(function() {
                    loadMenu(${json});
                    tabControl(); //选项卡
                    pageTab(); //页面选项卡
                    lineSkip(); //页面跳转
                    goMdfPass(); //跳转到密码修改页面
                    goQRCode(); //跳转到动态二维码
                    var str = document.body.clientWidth;
                    var height = (1 - 240 / str) * str;
                    //$('#page_tab_one').css('width',height+'px');
                });

                function loadMenu(data) {
                    var jsonData = data.retList;
                    var ul = $("#ulmenu");
                    $.each(jsonData, function(i, value) {
                        var pid = value.pid;
                        var aurl = value.aurl;
                        var name = value.name;
                        var id = value.id;
                        var str = '';
                        if (pid == "1") {
                            var pLi = ul.find("li[id = '" + id + "']");
                            if (pLi[0] == undefined) {
                                ul.append("<li id = '" + id + "'></li>");
                                pLi = ul.find("li[id = '" + id + "']");
                            }
                            
                            if (aurl.length == 0) {
                               if (name == "系统管理") {
                                    str =
                                       '<a href="#" class="dropdown-toggle"> <i class="icon no1"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                } else if (name == "") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no3"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                } else if (name == "收支管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no2"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                } else if (name == "保单管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no4"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                } else if (name == "报表管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no5"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                } else if (name == "") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no6"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "分单管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no7"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "结算管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no8"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "人员管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no9"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "保险公司管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no10"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "机构管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no12"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "协议管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no11"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "单证管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no13"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "收入管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no14"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "支出管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no15"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "客户关系管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no16"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else if (name == "团队管理") {
                                    str =
                                        '<a href="#" class="dropdown-toggle"> <i class="icon no17"></i> <span class="menu-text">' +
                                        name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }else{
                                	  str =
                                          '<a href="#" class="dropdown-toggle"> <i class="icon no1"></i> <span class="menu-text">' +
                                          name + '</span> <b class="arrow icon-angle-down"></b> </a>';
                                }
                            } else {
                                str = '<a href="#" name = "' + aurl + '" id = "' + id +
                                    '" class="dropdown-toggle"> <i class="icon no1"></i> <span class="menu-text">' + name +
                                    '</span> <b class="arrow icon-angle-down"></b> </a>';
                            }

                            $(pLi[0]).append(str);
                            findChildNode(jsonData, id, pLi[0]);
                        }
                    });
                }

                function findChildNode(data, id, obj) {
                    var ul_1 = "<ul class='submenu'></ul>";
                    $(obj).append(ul_1);
                    for (var i in data) {
                        if (id == data[i]["pid"]) {
                            l_id = data[i]["id"];
                            aurl = data[i]["aurl"];
                            name = data[i]["name"];
                            var ul = $(obj).find("ul");
                            if (aurl == "") {
                                $(ul[0]).append('<li id = "' + l_id +
                                    '"> <a href="#" class="dropdown-toggle"> <i class="icon-double-angle-right"></i>' + name +
                                    '<b class="arrow icon-angle-down"></b> </a></li>');
                                var next_li = $(ul[0]).find("li[id = '" + l_id + "']");
                                findChildNode(data, l_id, next_li);
                            } else {
                                $(ul[0]).attr("style", "margin-left:15px;");
                                $(ul[0]).append('<li> <a href="#" name = "' + aurl + '" id = "' + l_id + '"> <i></i>' + name +
                                    '</a></li>');
                            }
                        }
                    }
                }

                function goMdfPass() {
                    var aPageTabTitleLength = 0;

                    $('.line #mdfpass').click(function() {
                        var aPageTabTitleIdLength = $('.page_tab_titles').find('#t_' + $(this).attr('id')).length;
                        var link = $(this).attr('name');
                        if (aPageTabTitleIdLength == 0) {
                            var titleArray = $(".page_tab_titles").find("li");
                            var titleWidth = 0;
                            for (var i = 0; i < titleArray.length; i++) {
                                titleWidth += $(titleArray[i]).width();
                            }
                          
                            $('.page_tab_title').removeClass('visited');
                            $('.page_tab_content').hide();
                            $('.page_tab_titles ul').append(
                                '<li class="page_tab_title visited" id="t_' + $(this).attr('id') + '">' +
                                '<span class="title">' +
                                $(this).text() +
                                '</span>' +
                                '<span class="close">x</span>' +
                                '</li>'
                            );

                            $('.page_tab').append(
                                '<div class="page_tab_content">' +
                                '<iframe class="iframe" name="mainIFrame" width="100%" onload="dyniframesize()" frameborder="0" src="' +
                                link +
                                '" allowtransparency="true"></iframe>' +
                                '</div>'
                            );

                            aPageTabTitleLength = $('.page_tab_title').length;
                            pageTab();
                            titleWidth += $('#t_' + $(this).attr('id')).width();
                            try {
                                dyniframesize();
                            } catch (e) {

                            }
                        } else {

                            $('.page_tab_title').removeClass('visited');
                            $('#t_' + $(this).attr('id')).addClass('visited');
                            $('.page_tab_content').hide();
                            $('.page_tab_content').eq($('#t_' + $(this).attr('id')).index()).show();
                            try {
                                dyniframesize();
                            } catch (e) {

                            }
                        }


                        $('.page_tab_title .close').click(function(event) {
                            event.stopPropagation(); // 阻止冒泡
                            aPageTabTitleLength = $('.page_tab_title').length;
                            var oIndex = $(this).parents('.page_tab_title').index();
                            //首页tab禁止删除
                            if (oIndex == 0) {
                                return;
                            }
                            if (aPageTabTitleLength > 1) {
                                $(this).parents('.page_tab').find('.page_tab_content').eq(oIndex).remove();
                                $('.page_tab_content').eq(0).show();
                                $(this).parents('.page_tab_title').remove();
                                $('.page_tab_title').removeClass('visited');
                                $('.page_tab_title').eq(0).addClass('visited');
                            }
                            try {
                                dyniframesize();
                            } catch (e) {

                            }
                        });

                    });
                }
                
                function goQRCode() {
                    var aPageTabTitleLength = 0;

                    $('.line #QRCode').click(function() {
                        var aPageTabTitleIdLength = $('.page_tab_titles').find('#t_' + $(this).attr('id')).length;
                        var link = $(this).attr('name');
                        if (aPageTabTitleIdLength == 0) {
                            var titleArray = $(".page_tab_titles").find("li");
                            var titleWidth = 0;
                            for (var i = 0; i < titleArray.length; i++) {
                                titleWidth += $(titleArray[i]).width();
                            }
                          
                            $('.page_tab_title').removeClass('visited');
                            $('.page_tab_content').hide();
                            $('.page_tab_titles ul').append(
                                '<li class="page_tab_title visited" id="t_' + $(this).attr('id') + '">' +
                                '<span class="title">' +
                                $(this).text() +
                                '</span>' +
                                '<span class="close">x</span>' +
                                '</li>'
                            );

                            $('.page_tab').append(
                                '<div class="page_tab_content">' +
                                '<iframe class="iframe" name="mainIFrame" width="100%" height="660px" onload="dyniframesize()" frameborder="0" src="' +
                                link +
                                '" allowtransparency="true"></iframe>' +
                                '</div>'
                            );

                            aPageTabTitleLength = $('.page_tab_title').length;
                            pageTab();
                            titleWidth += $('#t_' + $(this).attr('id')).width();
                            try {
                                dyniframesize();
                            } catch (e) {

                            }
                        } else {

                            $('.page_tab_title').removeClass('visited');
                            $('#t_' + $(this).attr('id')).addClass('visited');
                            $('.page_tab_content').hide();
                            $('.page_tab_content').eq($('#t_' + $(this).attr('id')).index()).show();
                            try {
                                dyniframesize();
                            } catch (e) {

                            }
                        }


                        $('.page_tab_title .close').click(function(event) {
                            event.stopPropagation(); // 阻止冒泡
                            aPageTabTitleLength = $('.page_tab_title').length;
                            var oIndex = $(this).parents('.page_tab_title').index();
                            //首页tab禁止删除
                            if (oIndex == 0) {
                                return;
                            }
                            if (aPageTabTitleLength > 1) {
                                $(this).parents('.page_tab').find('.page_tab_content').eq(oIndex).remove();
                                $('.page_tab_content').eq(0).show();
                                $(this).parents('.page_tab_title').remove();
                                $('.page_tab_title').removeClass('visited');
                                $('.page_tab_title').eq(0).addClass('visited');
                            }
                            try {
                                dyniframesize();
                            } catch (e) {

                            }
                        });

                    });
                }
                
                
            </script>
            <!--  
<style>
	html{
		background:url(<%=basePath%>/compent/login/images/beijing.png)  repeat;
	}-->
            </style>

            </html>