
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.ca.cacore.manage.model.bo.UserModel"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "";
	UserModel user = (UserModel)session.getAttribute("user");
	String portraitPath = basePath+"/"+user.getPortraitPath(); //获取用户头像地址
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="CACore">
		<meta name="author" content="Newtouch">

		<link rel="shortcut icon" href="<%=basePath%>/resources/ca/cacore/img/jv.png">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/charisma/css/xinzhijunyang.css">
		<script type="text/javascript" src="<%=basePath%>/compent/jquery/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/compent/charisma/js/xinzhijunyang_main_menu.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
		//      控制页面高度开始
				var frameId = "#mainIframe";
				$(window).frameWH(frameId);
		//      控制页面高度结束
		
				var myDate = new Date();
				var Year = myDate.getFullYear() + "年"; //获取完整的年份(4位,1970-????)
				var Moon = myDate.getMonth() + 1 + "月"; //获取当前月份(0-11,0代表1月)
				var Day = myDate.getDate() + "日"; //获取当前日(1-31)
				var Week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
				var ThisDay = Week[myDate.getDay()]; //获取当前星期X(0-6,0代表星期天)
				$("#year").text(Year);
				$("#moon").text(Moon);
				$("#day").text(Day);
				$("#week").text(ThisDay);
			});
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				var emp_id='<%=user.getEmp_id()%>';
				$.ajax({
		     		url : "<%=basePath %>/Manage/FuncPanel/getFuncPanelConfInfo.do",
		     		type : "post",
		     		async : false,
		     		data : {
		     			"emp_id" : emp_id
		     		},
		     		dataType:"json",
		     		success : function(data) {
		     			//右上方
		     			var rightTop=data[0].rightTop;
		     			var rightTopHtml="";
		     			$.each(rightTop,function(index,comment){
		     				rightTopHtml+='<div class="'+comment.style+'">'
										+'<a href="<%=basePath%>'+comment.link_url+'"><span></span>'+comment.func_name+'</a>'
										+'</div>';
						});
		     			$("#rightTop div").append(rightTopHtml);
		     			
		     			//右下方左侧
		     			var rightBottomLeft=data[1].rightBottomLeft;
		     			var rightBottomLeftHtml="";
		     			$.each(rightBottomLeft,function(index,comment){
		     				rightBottomLeftHtml+='<div id="'+comment.style+'" class="functionIcon">'
												+'<a href="<%=basePath%>'+comment.link_url+'"><span></span>'+comment.func_name+'</a>'
												+'</div>';
						});
		     			$(".rightBottomleft div").append(rightBottomLeftHtml);
		     			
		     			//右下方右侧
		     			var rightBottomRight=data[2].rightBottomRight;
		     			var rightBottomRightHtml="";
		     			$.each(rightBottomRight,function(index,comment){
		     				rightBottomRightHtml+='<div id="'+comment.style+'" class="functionIcon">'
										        +'<a><span></span>'+comment.func_name+'</a>'
												+'</div>';
						});
		     			$(".rightBottomright div").append(rightBottomRightHtml);
		     		}
		     	});
				//控制圆形Span显示隐藏方法开始
				var spanArray=$("#rightTop").find("span");
				$(window).spanShow(spanArray);
				//控制圆形Span显示隐藏方法结束
			});
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				$.ajax({
		     		url : "<%=basePath %>/AMS/Announcement/getNoticeInfo.do",
		     		type : "post",
		     		async : false,
		     		dataType:"json",
		     		success : 
		     			function(data) 
		     			{
		     			  var announcement_theme=data[0].announcement_theme;
		     			  var start_time=data[0].start_time;
 				     			var tbodyHtml="";			
 				     			var theme="";
 				     			$.each(data,function(index,comment){
 				     				if(comment.announcement_theme.length>10)
 				     					{
 				     						theme=comment.announcement_theme.substr(0,6)+"...";
 				     					}
 				     				else
 				     					{
 				     						theme=comment.announcement_theme;
 				     					}
 				     			
 				     				tbodyHtml+='<tr><td width="105px">'
			     								+'<a href="<%=basePath %>/AMS/Announcement/viewAnnouncement.do?pageName=announcement_View&seq_id='+comment.seq_id+'" >'+theme+'</a>'
 				     							+'</td><td>'+comment.start_time+'</td></tr>';
 				     			});
 				     			$("#notice").append(tbodyHtml);
		     		 	}
		     			
				});
			});
		
		</script>
		<script type="text/javascript">
// 			$(function(){
// 				$("#st").delegate("td","click",function(){
// // 					alert($(this).text());
// // 					alert($(this).children().val());
// 					window.location.href=$(this).children().val();
// 				});
				
// 				$("#keyWord").bind("input", function() {
<%-- 					var emp_id ='<%=user.getEmp_id()%>'; --%>
// 					var keyWord = $("#keyWord").val();
// 					if(keyWord==null||keyWord==""){
// 						$("#st tbody").empty();
// 						return;
// 					}
// 					alert(keyWord+","+emp_id);
// 					var handler = function(){
// 						$.ajax({
<%-- 				     		url : "<%=basePath %>/Manage/FuncPanel/getMenuInfo.do", --%>
// 				     		type : "post",
// 				     		async : false,
// 				     		data : {
// 				     			"emp_id" : emp_id,
// 				     			"keyWord" : keyWord
// 				     		},
// 				     		dataType:"json",
// 				     		success : function(data) {
// 				     			$("#st tbody").empty();
// 				     			var tbodyHtml="";
// 				     			$.each(data,function(index,comment){
// 				     				tbodyHtml+='<tr>'
<%-- 			     								+'<td id="newTd">'+comment.menu_name+'<input id="url" type="hidden" name="url" value="<%=basePath %>/'+comment.menu_url+'"></input></td>' --%>
// 				     							+'</tr>';
// 				     			});
// 			     				$("#st tbody").append(tbodyHtml);
// 			     				if(tbodyHtml==""){
// 			     					$("#promptMessage").css({ display: "none" });
// 			     				}else{
// 				     				$("#promptMessage").css({ display: "block" });
				     				
// 			     				}
// 				     		}
// 						});
// 		 		    };
// 		 		    var timer = setTimeout( handler , 0);
// 				});
				
// 				immediately();
// 			});
			
// 			function judgePosition(){
// 				if(document.activeElement.id !="newTd"){
// 					$("#st tbody").empty();
// 				}
// 			}
			
// 			function immediately(){ 
// 				var element = document.getElementById("keyWord");
// 				if(navigator.userAgent.indexOf("MSIE")>0) { //IE
// 					element.onpropertychange = webChange; 
// 				}else{ 
// 					element.addEventListener("input",webChange,false);
// 				}
// 			}
// 			function webChange(){ 
<%-- 				var emp_id ='<%=user.getEmp_id()%>'; --%>
// 				var keyWord = $("#keyWord").val();
// 				if(keyWord==null||keyWord==""){
// 					$("#st tbody").empty();
// 					return;
// 				}
// // 				alert(keyWord+","+emp_id);
// 				var handler = function(){
// 					$.ajax({
<%-- 			     		url : "<%=basePath %>/Manage/FuncPanel/getMenuInfo.do", --%>
// 			     		type : "post",
// 			     		async : false,
// 			     		data : {
// 			     			"emp_id" : emp_id,
// 			     			"keyWord" : keyWord
// 			     		},
// 			     		dataType:"json",
// 			     		success : function(data) {
// 			     			$("#st tbody").empty();
// 			     			var tbodyHtml="";
// 			     			$.each(data,function(index,comment){
// 			     				tbodyHtml+='<tr>'
<%-- 		     								+'<td id="newTd">'+comment.menu_name+'<input id="url" type="hidden" name="url" value="<%=basePath %>/'+comment.menu_url+'"></input></td>' --%>
// 			     							+'</tr>';
// 			     			});
// 		     				$("#st tbody").append(tbodyHtml);
// 		     				if(tbodyHtml==""){
// 		     					$("#promptMessage").css({ display: "none" });
// 		     				}else{
// 			     				$("#promptMessage").css({ display: "block" });
		     				
// 		     				}
// 			     		}
// 					});
// 	 		    };
// 	 		    var timer = setTimeout( handler , 0);
// 			} 
		</script>   
		
	</head>
	<body>
		<div class="countWarp">
			<div class="leftCount">
				<div class="userblock">
					<div class="PictureBK">
					<c:if test="${user.portraitPath != null}">
						<a href="<%=basePath%>/User/addUserPortrait.do" ><img id="userPicture" src="<%=portraitPath%>" border="0"/></a>
					</c:if>
					<c:if test="${user.portraitPath == null}">
						<a href="<%=basePath%>/User/addUserPortrait.do" ><img id="userPicture" src="<%=basePath%>/compent/charisma/img/xinzhijunyang/portrait.PNG" border="0"/></a>
					</c:if>	
					</div>
					<div class="DateBlock">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td id="year"></td>
								<td id="moon"></td>
								<td id="day"></td>
							</tr>
							<tr>
								<td colspan="3" id="week"></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="messageBlock">
					<h3>信息公告</h3>
<!-- 					<form id="searchForm" name="searchForm" action="http://www.baidu.com/" method="post"> -->
<!-- 					    <input id="keyWord" type="text" autocomplete="off" style="width:110px;" maxlength="100" name="keyWord" onblur="judgePosition();"></input> -->
<!-- 					    <span class="btn_wr"> -->
<!-- 					        <input id="su1" type="submit" onmouseout="" onmousedown="" value="搜索"></input> -->
<!-- 					    </span> -->
<!--     					<div id="promptMessage" class="s-ps-sug" style="width: 110px; top: 31px;"> -->
<!--         				<table id="st" cellspacing="0" cellpadding="2"> -->
<!--             				<tbody> -->
            					
<!-- 				            </tbody> -->
<!--         				</table> -->
<!--     					</div> -->
<!-- 					</form> -->
					<dl>
					<dt>
						<table cellspacing="0" cellpadding="2" style="width: 200px; top: 31px;">
            				<tbody id="notice">
            					
				            </tbody>
        				</table>						
					</dt>
					</dl>
					<a href="<%=basePath %>/AMS/Announcement/queryAnnouncement2.do?pageName=announcement_Query2">更多>></a>
				</div>
			</div>
			<div class="rightCount">
				<div class="rightTop" id="rightTop">
					<h3>常用功能区</h3>
					<div>
						
					</div>
				</div>
				<div class="rightBottom">
					<div class="rightBottomleft">
						<h3>功能查询区</h3>
						<div>
						
						</div>
					</div>
					<div class="rightBottomright">
						<h3>个人助理区</h3>
						<div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="zeoBottom"></div>
	</body>
</html>