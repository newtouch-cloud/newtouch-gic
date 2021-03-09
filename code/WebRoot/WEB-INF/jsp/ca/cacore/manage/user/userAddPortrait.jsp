<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.ca.cacore.manage.model.bo.UserModel"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
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
	UserModel user = (UserModel)session.getAttribute("user");
	String portraitPath = basePath+"/"+user.getPortraitPath(); //获取用户头像地址
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		
		<!-- uploadify start -->
		<link href="<%=basePath%>/resources/ca/cacore/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resources/ca/cacore/uploadify/uploadimg.css" rel="stylesheet" type="text/css" />
    	<script src="<%=basePath%>/resources/ca/cacore/uploadify/swfobject.js" type="text/javascript"></script>
    	<script src="<%=basePath%>/resources/ca/cacore/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
    	<!-- uploadify end -->
		
		<!-- jcrop图片剪裁 -->
		<link rel="stylesheet" href="<%=basePath %>/compent/jcrop/css/jquery.Jcrop.min.css" >
		<script src="<%=basePath%>/compent/jcrop/js/jquery.Jcrop.min.js" type="text/javascript"></script>
		<script src="<%=basePath%>/compent/jcrop/js/jquery.color.js" type="text/javascript"></script>
		
		<style type="text/css">

		.title{
		font-size: 24px;
		color:#999;
		line-height: 1;
		padding-bottom: 11px;
		border-bottom:solid 2px #F2F2F2;		
				
			}
		.avatar{
		height: 24px;
		line-height:25px;
		padding-top: 20px;
		zoom: 1;
		}	
		
		.avatar-current{
		float: left;
		width: 500px;
		font-size: 14px;
		}
		
		.avatar-setting{
		float: left;
		width: 85px;
		font-size: 14px;
		}
		.avatar-file{
		float: left;
		width: 116px;
		}
		.area{
		padding-left:500px;
		}
		.clear{ clear:both; float:none;}
	</style>
	<script type="text/javascript">
		var index = 0;
		jQuery(function($){
			$("#butt").gobackTop(); //保存时跳回顶部
//			//上传图片
			$("#uploadifynew").uploadify({ //上传
					'swf'	:'<%=basePath %>/resources/ca/cacore/uploadify/uploadify.swf', //swf 文件路径
			    	'uploader' :'<%=basePath %>/User/uploadPortraitFile.do',
			    	'script' : '<%=basePath %>/User/uploadPortraitFile.do',//后台处理的请求,对应Servlet
			    	'fileObjName' : 'fileupload',
			    	'folder' : 'upload',//您想将文件保存到的路径
			    	'queueID' : 'fileQueue',//与下面的id对应
			    	'queueSizeLimit' : 20,
			    	'width' : 90,
			    	'height' : 24,
			    	'buttonClass' : 'buttonCss',
			    	'removeCompleted' : true,
			    	'fileTypeDesc': 'Images Files', //文件类型的说明
			    	'fileTypeExts': '*.gif;*.png;*.jpg',  //指定允许上传的文件类型
			        'method': 'post', //发送方式，默认post
			        'auto' : true, //是否自动上传，设成true后，自动执行uploadify('upload','*') 方法
			    	'multi' : true,// 是否支持多文件上传
			    	'progressData':'percentage', //文件上传时显示的数据，显示'speed' 速度，'percentage' 显示百分比
			    	'simUploadLimit' : 20,  
			    	'removeTimeout' : '1',  //上传成功后进度条消失时间,秒
			    	'requeueErrors': false, //若设置为True，那么在上传过程中因为出错导致上传失败的文件将被重新加入队列。
			    	'fileSizeLimit': '500000KB', //上传文件大小限制，默认单位是KB
			    	'buttonText' : '选择图片',
			    	'onFallback' : function(){ //无Flash时触发
				    	alert('没有支持的Flash版本，请更新Flash版本!');
				    },
				    'onUploadSuccess' : function(file,data,response){ // 每个文件文件上传成功时都会触发
				    	
	                        var result = $.parseJSON(data);
	                        if (result.uploadMsg == 'success') {
	                        	if(index == 0){ //第一次进行上传
	                        		$('#target').attr("src","<%=basePath %>/"+result.portraitPath);
	                        		$('#portraitName').val(result.portraitName);
	                        	}else{ //第index 次上传
	                        		removeOne();
	                        		jQuery("#showimg").append("<img id='target'  src='<%=basePath %>/"+result.portraitPath+"' width='300' height='350' onload='initImage()'/>");
	                        		$('#portraitName').val(result.portraitName);
	                        	}
	                        	
	                        	index++;
	                        	
	                        	return;
	                        }
	                    
				    	
					},
					'onUploadError' : function(file,errorCode,errorMsg,errorString){
						//alert(errorMsg);
					},
					'onSelectError' : function(file){
						alert(file.name + '文件出错！');
					},
					'onUploadStart' : function(file) { //动态参数设置
						
			        }
				});
			
	  });
		
		function initImage(){
			var jcrop_api;
		    $('#target').Jcrop({
		      onChange:   showCoords,
		      onSelect:   showCoords,
		      onRelease:  clearCoords
		    },function(){
		      jcrop_api = this;
		    });

		    $('#coords').on('change','input',function(e){
		      var x1 = $('#x1').val(),
		          x2 = $('#x2').val(),
		          y1 = $('#y1').val(),
		          y2 = $('#y2').val();
		      jcrop_api.setSelect([x1,y1,x2,y2]);
		    });


		  // Simple event handler, called from onChange and onSelect
		  // event handlers, as per the Jcrop invocation above
		  function showCoords(c){
		    $('#x1').val(c.x);
		    $('#y1').val(c.y);
		    $('#x2').val(c.x2);
		    $('#y2').val(c.y2);
		    $('#w').val(c.w);
		    $('#h').val(c.h);
		    $('#butt').removeAttr('disabled');  //取消置灰保存按钮效果
		  };

		  function clearCoords(){
			  $('#value input').val('');
			  $('#butt').attr('disabled',' disabled');//设置置灰保存按钮
		  };

		}
		
		//上传新的头像时删除已经显示的图片
		function removeOne(){
			//remove target 页面就可以有多个剪裁界面
			$('#target').remove();
			$('.jcrop-holder').remove(); //实现删除界面上前一个上传的裁剪图片
			
		}
		
		//校验提交数据
		function checkSubmit(){
			var x=$('#x1').val();
		    var y=$('#y1').val();
		    var w=$('#w').val();
		    var h=$('#h').val();
		    var portraitName=$('#portraitName').val();//因为设置了按钮置灰校验,提交时不对数据为空进行校验
		    //使用ajax方式把图片裁剪参数传到后台
		    $.ajax({
     			url : "<%=basePath %>/User/toTailorPortrait.do",
     			type : "post",
     			async : false,
     			data : {
     				"x" : x,
     				"y" : y,
     				"w" : w,
     				"h" : h,
     				"portraitName" : portraitName
     			},
     			success : function(data) {
     				 var result = $.parseJSON(data);
     				 if (result.uploadMsg == 'success') {
     					$('#previewp').removeAttr("src");
                   		$('#previewp').attr("src","<%=basePath %>/"+result.portraitPath);
                   		$('#butt').attr('disabled',' disabled');//设置置灰保存按钮
                   		removeOne();
                   		jQuery("#showimg").append("<img id='target'  src='' width='300' height='350' onload='initImage()'/>");
                   	  	alertInfo($('form'), 'success', '头像保存成功');
                     }else{
                    	alertInfo($('form'), 'error', '头像保存失败，请重试');
                     }
     			}
     		});
		    
		}
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 我的资料 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 头像设置</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm"  class="" action="<%=basePath %>/User/toTailorPortrait.do" method="POST">
					<!-- 提示信息标签 -->
					<webTag:ReturnMsgTag id="msg" name="msg" successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />		
				    
					<fieldset>
					<div class="title" align="left" >头像设置</div>
					<div class="avatar">
						<p class="avatar-current">当前头像</p>
						<p class="avatar-setting">设置新头像</p>
						<div class="avatar-file">
							<input type="file"  name="uploadifynew" id="uploadifynew"/>
				 		  <!-- 显示上传进度条的div <div id="fileQueue"></div> -->
						</div>
							<button id="butt" type="button" onclick="checkSubmit();" disabled="disabled" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存头像</button>
						
					</div>
					
					<!-- 当前头像显示区域 -->
					<div class="clear">
						<div class="PictureBK">
						  <c:if test="${user.portraitPath != null}">
							  <img id="previewp" src="<%=portraitPath %>"  width="100" height="130" alt="Preview" />
						  </c:if>
						  <c:if test="${user.portraitPath == null}">
							  <img id="previewp" src="<%=basePath%>/compent/charisma/img/xinzhijunyang/portrait.PNG" width="100" height="130" alt="Preview"/>
						  </c:if>		
						</div>
						  
					
					</div>
					  
					<!-- 图片裁剪区域 -->
					<div class="area"  id="showimg">
						<img id="target" alt="" src="" width="300" height="350" onload="initImage();"/>
						   
						  <div class="description"></div>
						  <div id="value">
						  	<input type="hidden" size="4" id="x1" name="x" />
						    <input type="hidden" size="4" id="y1" name="y" />
						    <input type="hidden" size="4" id="x2" name="x2" />
						    <input type="hidden" size="4" id="y2" name="y2" />
						    <input type="hidden" size="4" id="w" name="w" />
						    <input type="hidden" size="4" id="h" name="h" />
						  </div>
							<input type="hidden" name="portraitName" value="" id="portraitName"/> 
						    
					 </div>
				  </fieldset>
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
