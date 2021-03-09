<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@ page import="com.newtouch.component.c11assistant.ServletHelper"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<script type="text/javascript">

	$(function(){
// 		//页面加载时获取的回跳参数
// 		var params=encodeURI(encodeURI('${rmHelper.returnParams.params}'));
// 		//从明细或修改页面跳回到主查询时自动拼接进来时的参数
// 		$("div > a").last().click(function(){
// 			var href = $(this).attr("href");
// 			//将查询参数拼接到请求超链接上
// 			params = params.replace(/!/g, "&");
// 			if(href!=null&&href.indexOf("'")==-1){
// 				//如果返回的请求地址中原来没有参数，那么要增加'?'
// 				if(href.indexOf("?")==-1){
// 					href+="?1=1"+params;
// 				}else{
// 					href+=params;
// 				}
// 			}else{
// 				var url=href.substring(0,href.lastIndexOf("'"));
// 				href=url+params+"'";
// 			}
// 			$(this).attr("href",href);
// 		});
// 		//从主查询中跳转到明细或修改页面，或者注销、删除时保证页码和查询参数保留，以便停留在操作前的页码
// 		$("td > a").click(function(){
// 			var url=$(this).attr("href");
// 			//jquery节点对象的增删
// 			var arr = new Array();
// 			arr[0] = $("form input");
// 			arr[1] = $("form select");
// 			var params = "";
// 			var separator = "!";
// 			//判断是否是注销和删除功能，如果是那么直接 用请求参数的方式拼接超链接
// 			if($(this).attr("id") == "other"){
// 				separator = "&";
// 			}
// 			for(var i=0;i<arr.length;i++){
// 				for(var j=0;j<arr[i].length;j++){
// 					var obj = arr[i].eq(j);
// 					if(obj.val()!=""&&obj.val()!=null){
// 						//排除掉主查询页面有类似注销删除操作的提示框的信息
// 						if(obj.attr("name")!="result_flag" && obj.attr("name")!="msg" && obj.attr("name")!="path"){
// 							params+=separator+obj.attr("name")+"="+obj.val();
// 						}
// 					}
// 				}
// 			}
// 			//如果返回的请求地址中原来没有参数，那么要增加'?'
// 			if(url.indexOf("?")==-1){
// 				url+="?1=1";
// 			}
// 			//判断是否是注销和删除功能，如果是那么直接 用请求参数的方式拼接超链接
// 			if($(this).attr("id") == "other"){
// 				url+=params+"&nowPage=${rmHelper.pageCount.nowPage-1}";
// 			}else{
// 				url+="&params="+params+"!nowPage=${rmHelper.pageCount.nowPage-1}";
// 			}
// 			url = encodeURI(encodeURI(url));
// 			$(this).attr("href",url);
// 		});
		
// 		//从明细或修改页面跳回到主查询时自动拼接进来时的参数
// 		$("#submitBtn").click(function(){
// 			var action = $('form').attr('action');
// 			//如果已经将回跳参数拼接上就不在继续拼接
// 			if(action.indexOf("params")==-1){
// 				//如果返回的请求地址中原来没有参数，那么要增加'?'
// 				if(action.indexOf("?")==-1){
// 					action+="?params="+params;
// 				}else{
// 					action+="&params="+params;
// 				}
// 			}
// 			$('form').attr("action",action);
// 		});
		
		/**
		 * 进入查询页面，当查询到的数据为空的时候，填充中间部分
		 */
		var size =0;
		if(${!empty rmHelper.returnMsg.dataList}){
			 size=${rmHelper.returnMsg.dataListSize}+0;
		}
		if(size == 0 && $(".pagination").length > 0){
			$(".overAuto").after("<div class='divForEmpty'></div>");
		}
		
		
		
		//子页面控制父页面高度开始
		//子页面加载时重新计算（适应）父页面页面高度
		var frameId = "#mainIframe";
		var frameW= 1060;//定义页面宽度
		var option = {
				FrameId : frameId,
				FrameW : frameW,
			};
		$(window).frameWH(option);//控制页面宽度
		
		//子页面加载时跳转到页面顶端
		var parentW = $(window.parent.document.documentElement);
		parentW.scrollTop(0);
		
		//伸缩 及功能按钮控制
		var Skb1 = $("#Shrinkbutton1");
		var ob1 = $("#Shrinkcontent1");
		var object1 = {
				FrameId : frameId,
				Obj : ob1,
		};
		Skb1.UpDown(object1); 
		
		var Skb2 = $("#Shrinkbutton2");
		var ob2 = $("#Shrinkcontent2");
		var object2 = {
				FrameId : frameId,
				Obj : ob2,
		};
		Skb2.UpDown(object2); 
		
		var Skb3 = $("#Shrinkbutton3");
		var ob3 = $("#Shrinkcontent3");
		var object3 = {
				FrameId : frameId,
				Obj : ob3,
		};
		Skb3.UpDown(object3); 
		
		var Skb4 = $("#Shrinkbutton4");
		var ob4 = $("#Shrinkcontent4");
		var object4 = {
				FrameId : frameId,
				Obj : ob4,
		};
		Skb4.UpDown(object4); 
		
		var Skb5 = $("#Shrinkbutton5");
		var ob5 = $("#Shrinkcontent5");
		var object5 = {
				FrameId : frameId,
				Obj : ob5,
		};
		Skb5.UpDown(object5); 
		
		var Skb6 = $("#Shrinkbutton6");
		var ob6 = $("#Shrinkcontent6");
		var object6 = {
				FrameId : frameId,
				Obj : ob6,
		};
		Skb6.UpDown(object6); 
		
		var Skb7 = $("#Shrinkbutton7");
		var ob7 = $("#Shrinkcontent7");
		var object7 = {
				FrameId : frameId,
				Obj : ob7,
		};
		Skb7.UpDown(object7); 
	    
		var Skb8 = $("#Shrinkbutton8");
		var ob8 = $("#Shrinkcontent8");
		var object8 = {
				FrameId : frameId,
				Obj : ob8,
		};
		Skb8.UpDown(object8);
		
		
	});
</script>