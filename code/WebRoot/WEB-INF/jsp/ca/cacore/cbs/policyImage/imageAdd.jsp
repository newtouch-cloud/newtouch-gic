<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>

		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		
		<!-- uploadify start -->
		<link href="<%=basePath%>/resources/ca/cacore/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resources/ca/cacore/uploadify/uploadimg.css" rel="stylesheet" type="text/css" />    	
    	<script src="<%=basePath%>/resources/ca/cacore/uploadify/swfobject.js" type="text/javascript"></script>
    	<script src="<%=basePath%>/resources/ca/cacore/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
		<!-- uploadify end-->

		<script >
		var ei;
		$(function() {
			
			ei = $("#large");//获取预览图片Div
		    ei.hide();  //页面加载时即隐藏图层 
			$("#img1").live('click',function(){//img1是 显示 上传图片的缩略图
					//鼠标点击小图时的时候 给div加上 图片
				var file_id= this.alt;
				ei.css({}).html('<img id="img2" style="border:1px solid gray;" src="' + this.src + '" />').show();
			    
			});
			$("#img2").live('click',function(){   
			   	ei.hide();   //点击被放大的图片后进行隐藏
			});
			
			$("#img1").live('hover',function(e){//鼠标移动到图片那是把删除按钮弄出来
		    	 var file_id= this.alt;
		         $("#"+file_id).slideDown(1000); 
			});
			
		    var index=0; //用于自动加载影像的id
			$("#uploadifynew").uploadify({ //上传
				'swf'	:'<%=basePath %>/resources/ca/cacore/uploadify/uploadify.swf', //swf 文件路径
		    	'uploader' : '<%=basePath %>/PolicyImageUploadServlet',
		    	'script' : '<%=basePath %>/PolicyImageUploadServlet',//后台处理的请求,对应Servlet
		    	'fileObjName' : 'fileupload',
		    	'folder' : 'upload',//您想将文件保存到的路径
		    	'queueID' : 'fileQueue',//与下面的id对应
		    	'queueSizeLimit' : 20,
		    	'width' : 110,
		    	'height' : 22,
		    	'buttonClass' : 'buttonCss',
		    	'removeCompleted' : true,
		    	'fileTypeDesc': 'Images Files', //文件类型的说明
		    	'fileTypeExts': '*.gif;*.png;*.jpg',  //指定允许上传的文件类型
		        'method': 'post', //发送方式，默认post
		        'auto' : true, //是否自动上传，设成true后，自动执行uploadify('upload','*') 方法
		    	'multi' : true,// 是否支持多文件上传
		    	'progressData':'speed', //文件上传时显示的数据，显示'speed' 速度，'percentage' 显示百分比
		    	'simUploadLimit' : 20,  
		    	'removeTimeout' : '1',  //上传成功后进度条消失时间,秒
		    	'requeueErrors': false, //若设置为True，那么在上传过程中因为出错导致上传失败的文件将被重新加入队列。
		    	'fileSizeLimit': '500000KB', //上传文件大小限制，默认单位是KB
		    	'buttonText' : '多附件上传',
		    	'onFallback' : function(){ //无Flash时触发
			    	alert('没有支持的Flash版本，请更新Flash版本!');
			    },
			    'onUploadSuccess' : function(file,data,response){ // 每个文件文件上传成功时都会触发
			    	var rdata= new Array(); 
			    	rdata = data.split("^");
			    	var img1=index++;
			    	var img2=index++;
			    	if(rdata[0]!=""){  //为空时表示上传可能出错,不再添加图片和隐藏域
			    		jQuery("#chens").append("<li id='imgli' class='thumbnail'><img id='"+img1+"' alt='"+rdata[1]+"' src='<%=basePath %>/"+ rdata[0]+"' class='magnify' style='width: 200px;height:150px' /> " 
						    	+"<div id='"+rdata[1]+"' class='well gallery-controls' style='margin-top:-1px;width:38px;margin-left:73px;display:none;'><p><a class='btn' title='删除此影像' href='javascript:void(0)' onclick='deleteImage("+rdata[1]+");' ><i class='icon-remove'></i></a></p></div>"
						    	+"<input  type='hidden' name='file_id'  value='"+rdata[1]+"' /></li> ");
			    	
			    		$("#"+img1).live('click',function(e){//img1是 显示 上传图片的缩略图
							//鼠标点击小图时的时候 给div加上 图片
								var file_id= this.alt;
			    				//动态获取页面滚动条位置,用于预览图片
			    				
						     	ei.css({top: window.parent.document.documentElement.scrollTop+20,left:window.parent.document.documentElement.scrollLeft+250}).html('<img id="'+img2+'" style="border:1px solid gray;" src="' + this.src + '" />').show();
						    	
						    });
						    $("#"+img2).live('click',function(e){   
						   		ei.hide();   //点击被放大的图片后进行隐藏
						    });
						    
						    $("#"+img1).live('hover',function(e){//鼠标移动到图片那是把删除按钮弄出来
						    	 var file_id= this.alt;
						         $("#"+file_id).slideDown(1000); 
							}); 
			    		$(window.parent.document).find("#mainIframe").height(document.body.offsetHeight);
			    	
			    	}else{
			    		alert(file.name+"上传出错");
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
		
		
		/*
		 点击删除超链接删除影像
		*/
  	 function deleteImage(file_id){
			var boo= confirm("是否确认删除此影像?");
			if(boo){
				$('#'+file_id).parent().remove();
				ei.hide();//隐藏预览界面
				$.ajax({
		     		url : "<%=basePath %>/CBS/policyImage/deletePolicyImage.do",
		     		type : "post",
		     		async : false,
		     		data : {"file_id": file_id},
		     		success : function(data) {
		     			//暂时不提示
		     		}
		     	});
			}
		}
	
     </script>	
		
			<table style="width: 100%;height:200px;" class="table table-striped table-bordered bootstrap-datatable datatable">
			   <tr>
			     <td><i class="icon-film icon-red"></i> 影像件</td>
			     <td height="22px">
			     	<input type="file"  name="uploadifynew" id="uploadifynew"/>
				 	<div id="fileQueue"></div>
			     </td>
			   </tr>
			   <tr>
			     <td><i class="icon-th-large icon-red"></i> 影像件预览</td>
			     <td valign="top" width="80%">
			        <ul id="chens" class="thumbnails">
			        	<c:choose> 
						   <c:when test="${rmHelper.returnMsg.dataList != null}">
						   		<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
						    	    <li class='thumbnail'>	
						    			<img id="img1" alt="${sm.file_id}" src="<%=basePath %>/${sm.file_path}" class="magnify" style="width: 200px;height:150px" />						    		
						    	    </li>
						    	</c:forEach>
						   </c:when>
						</c:choose>
			        </ul>
					 <div class="show" id="large" ></div>						     
			     </td>
			   </tr>						
			</table>
	