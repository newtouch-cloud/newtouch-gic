<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="../css/xinzhi.css">
<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../js/xinzhi.js"></script>
<script type="text/javascript" src="../js/iframe.js"></script>
<title>无标题文档</title>
</head>

<body class="tongji" onload="queryInfo();">
    <div class="page_right">
        <div class="toji_info">
            <div class="tl">
            	<div class="t_data">
                	<div class="data">
                    	<div class="t_title"><span class="t_t_ico"></span>统计数据</div>
                    		<img alt="" src="<%=basePath %>/AMS/statisticalDataController/statisticalDataYear.controller" style="margin: 0px 25px;width: 220px;height: 250px;">
                    </div>
                    <div class="checkin">
                    	<div class="t_title"><span class="t_t_ico"></span>统计数据</div>
                    	   <img alt="" src="<%=basePath %>/AMS/statisticalDataController/statisticalDataMonth.controller" style="margin: 0px 25px;width: 220px;height: 250px;">
                       <!--  <div class="toji_bill">
                        </div> -->
                    </div>
                </div>
            </div>
            <div class="tr">
            	<div class="t_info">
                	<div id="tab_control" class="tab_control">
                    	<div id="tab_titles" class="tab_titles" >
                        	<ul id="ul_titles">
                            	<li id="tab2" class="tab_title visited" onclick="select(this);" value="0" >待完成任务</li>
                            	<li id="tab3" class="tab_title" onclick="select(this);"  value="1" >已完成任务</li>
                            	<li id="tab1" class="tab_title" onclick="select(this);"  value="0" >信息公告</li>
                            </ul>                          
                        </div>
                        <div id="div_content" class="div_content">
                        
                           <div id="tab2_content" class="tab_content" style="height:260px;display:block;overflow:auto;">
                        	 <ul id="content2" class="information"></ul>
                          </div>
                           <div id="tab3_content" class="tab_content" style="height:260px;display:none;overflow:auto;">
                             <ul id="content3" class="information"></ul>
                           </div> 
                           <div id="tab1_content" class="tab_content" style="display:none;height:260px;overflow:auto;">
                             <ul id="content1" class="information"></ul>
                          </div>
                        </div>
                                                                    
                    </div>
                </div>
            </div>
        </div>
        <div class="bottom_menu">
        	<a class="no1" href="#" name="SMS/SalesInfo/toSalesInfo.do?pageName=salesInfo&channel_id=01">
            	<span class="icon"></span>
                <span class="title">中介人员查询</span>
            </a>
        </div>
    </div>
</body>
<script type="text/javascript">

function select(tab){
	
	/* var liList = $("#ul_titles").find("li");
	for(var i=0; i<liList.length; i++){
	   $(liList[i]).attr("style","display:none");
	}
	var divList =$("#div_content").find("div");
    for(var i=0; i<divList.length; i++){
	   divList[i].attr("style","display:none");
	} */
    $(tab).attr("style","display:block");
	$(tab.id+"_content").attr("style","display:block");
	
}

function queryInfo(){
	   $.ajax({
	 		url : "<%=basePath %>/AMS/Announcement/queryAnnounce.do",
	 		type : "post",
	 		async : false,
	 		success : function(data) {
	 			var flag=data.substring(1, data.indexOf('}'));
	 			var isSuccess =flag.split(':')[1];	 			
	 			if(isSuccess=="true"){		 				
	 				var str = data.substring(data.indexOf('}')+1);
		 			var info = str.split(":");
		 			$("#content1").empty();
		 			for(var i=0;i<info.length;i++){
		 				var ary = info[i].split(",");
		 				var v = ary[2];
		 				$("#content1").append("<li class='no"+(i+1)+"'><a href='<%=basePath %>/AMS/Announcement/viewAnnouncement.do?pageName=announcement_View&seq_id="+v+"'>"+ary[0]+"</a><span class='date'>"+ary[1]+"</span></li>");
		 			}	
	 				$("#content1").append("<li  style='text-align:right'><a style='text-decoration:none' href='<%=basePath %>/AMS/Announcement/queryAnnouncement.do?pageName=announcement_Query2' >更多>>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</a></li>");
				}else{
					$("#content1").remove();
					$("#tab1_content").text("没有信息公告");
				}	 			
	 		}
	 	});		   
	
	   var status2=$("#tab2").val();
	   $.ajax({
	 		url : "<%=basePath %>/AMS/MessagePushManagerController/queryMessagePushStatus.do",
	 		type : "post",
	 		async : false,
	 		data : {"status": status2},
	 		success : function(data) {
	 			var flag=data.substring(1, data.indexOf('}'));
	 			var isSuccess =flag.split(':')[1];	 			
	 			if(isSuccess=="true"){ 				
	 				var str = data.substring(data.indexOf('}')+1);
		 			var info = str.split(":");		 			
		 			$("#content2").empty();
		 			for(var i=0;i<info.length;i++){
		 				var ary = info[i].split("|");
		 				$("#content2").append("<li id='ttt' class='no"+(i+1)%6+"'><a href='<%=basePath %>/AMS/MessagePushManagerController/toDealMessagePush.do?taskid="+ary[2]+"'>"+ary[0]+"</a><span class='date'>"+ary[1]+"</span></li>");
		 			}		 			
				}else{
					$("#content2").remove();
					$("#tab2_content").text("没有任务");
				}
	 			
	 		}
	 	});		   
	   var status3=$("#tab3").val();
	   $.ajax({
	 		url : "<%=basePath %>/AMS/MessagePushManagerController/queryMessagePushStatus.do",
	 		type : "post",
	 		async : false,
	 		data : {"status": status3},
	 		success : function(data) {
	 			var flag=data.substring(1, data.indexOf('}'));
	 			var isSuccess =flag.split(':')[1];	 			
	 			if(isSuccess=="true"){		 				
	 				var str = data.substring(data.indexOf('}')+1);
		 			var info = str.split(":");
		 			$("#content3").empty();
		 			for(var i=0;i<info.length;i++){
		 				var ary = info[i].split("|");
		 				$("#content3").append("<li class='no"+(i+1)+"'><a href='<%=basePath %>/AMS/MessagePushManagerController/toInfoMessagePush.do?taskid="+ary[2]+"'>"+ary[0]+"</a><span class='date'>"+ary[1]+"</span></li>");
		 			}	
				}else{
					$("#content3").remove();
					$("#tab3_content").text("没有任务");
				}	 			
	 		}
	 	});		
	
}
	
	
$(function(){
	tabControl();    //选项卡
	pageTab(); //页面选项卡
	pageSkip(); //页面跳转
});
function pageSkip(){
	var aPageTabTitleLength = 0;
	var parentDoc = window.parent.document
	$('.bottom_menu').find('a').click(function(){
		var link = $(this).attr('name');
		var aPageTabTitleIdLength = $('.page_tab_titles',parentDoc).find('#t_' + $(this).attr('class')).length; 
			if(aPageTabTitleIdLength==0){
				var titleArray = $(".page_tab_titles",parentDoc).find("li");
				var titleWidth = 0;
				for(var i=0;i < titleArray.length;i++){
					titleWidth += $(titleArray[i]).width();
				}
				
				$('.page_tab_title',parentDoc).removeClass('visited');
				$('.page_tab_content',parentDoc).hide();
				$('.page_tab_titles ul',parentDoc).append(
					'<li class="page_tab_title visited" id="t_'+ $(this).attr('class') +'">'
						+ '<span class="title">'
							+ $(this).text() 
						+'</span>'
						+ '<span class="close">x</span>'
					+ '</li>'
				);
				$('.page_tab',parentDoc).append(
					'<div class="page_tab_content">'
						+ '<iframe class="iframe" name="mainIFrame" width="1158px" onload="dyniframesize()" frameborder="0" src="'
							+ link
						+'" allowtransparency="true"></iframe>'
					+ '</div>'
				);
				
				aPageTabTitleLength = $('.page_tab_title',parentDoc).length;
				pageTab();
				
				try{
					dyniframesize();
				}catch(e){
					
				}
			}else{
				$('.page_tab_title',parentDoc).removeClass('visited');
				$('#t_' + link,parentDoc).addClass('visited');
				$('.page_tab_content',parentDoc).hide();
				$('.page_tab_content',parentDoc).eq($('#t_' + $(this).attr('class')).index()).show();
			}
		
		$('.page_tab_title .close',parentDoc).click(function(event){
			event.stopPropagation(); // 阻止冒泡
			aPageTabTitleLength = $('.page_tab_title',parentDoc).length;
			var oIndex = $(this).parents('.page_tab_title',parentDoc).index();
			//首页tab禁止删除
			if(oIndex == 0){
				return;
			}
			if(aPageTabTitleLength > 1){
				$(this).parents('.page_tab',parentDoc).find('.page_tab_content').eq(oIndex).remove();
				$('.page_tab_content',parentDoc).eq(0).show();
				$(this).parents('.page_tab_title',parentDoc).remove();
				$('.page_tab_title',parentDoc).removeClass('visited');
				$('.page_tab_title',parentDoc).eq(0).addClass('visited');
			}
			try{
				dyniframesize();
			}catch(e){
				
			}
		});
		
	});
}
</script>
</html>
