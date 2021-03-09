<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>

<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp" %>
		<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" %>
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/>

		
		
		<!-- uploadify start -->
		<link href="<%=basePath%>/compent/WebUploader/webuploader.css"  rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/compent/WebUploader/bootstrap.css" 	rel="stylesheet" type="text/css" />
    	<script src="<%=basePath%>/compent/WebUploader/webuploader.js" 	type="text/javascript"></script>
		<!-- uploadify end-->	
	
	</head>
	<body>
		<div class="container-fluid">
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 协议管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 保险协议导入</span>
			    
			</div>
			<!-- 面包屑导航  end -->
			
			
			<div class="row-fluid" id="Shrinkcontent1">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="" method="POST">
				<!-- 提示信息标签 -->
			   	<div id="dialog" title="提示信息" style="display:none">
				<image id="dialog_img" ><span id="dialog_msg"></span></image>
				</div>
				<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
				<webTag:HiddenInputTag id="protocol_category" name="protocol_category" value='1'/>
				<div class="overAuto row-fluid">
					<table class='table table-striped table-bordered bootstrap-datatable datatable'>
						<tr>
							<td width="50px"><i class="icon-file icon-red"></i> 请选择</td>
							<td width="100px"><a id="downloadBtn" class="btn btn-danger" data-toggle="dropdown"  onclick="downloadfile();" >
								<i class="icon-download icon-white"></i>模板下载</a>
							</td>
						
							<td width="100px">
								<div id="pickerdaoru"><i class="icon-file icon-white"></i>选择文件</div>
							</td>
							<td width="100px">
								<button class="btn btn-danger" type="button" id="ctlBtndaoru"><i class="icon-upload icon-white"></i>开始导入</button>
							</td>
							<td rowspan="2">
									<div id="thelistdaoru" class="uploader-list"></div>
							</td>
						</tr>

						<tr>
							<td><i class="icon-exclamation-sign icon-red"></i> 导入消息</td>
							<td colspan="3">
								<textarea id="upMsg" rows="15" name="upMsg" style="width: 600px;" readonly="readonly"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="5" style="vertical-align: middle; text-align: right;"><a class="btn btn-danger" href="<%=basePath %>/organization/Protocol/goProtocolList.do"><i class="icon-share-alt icon-white"></i>返回</a></td>
						</tr>
					</table>
				</div>
				<!-- /.row -->
			</form>
			</div>
			<!-- 查询面板 end -->
		</div>
		<!-- 底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 底部高度填充块 结束-->
	</body>
	<script type="text/javascript">
	
			var uploaderdaoru = WebUploader.create({
				auto: false,
			    // swf文件路径
			    swf: '<%=basePath%>/compent/WebUploader/Uploader.swf',

			    // 文件接收服务端。
			    server: '<%=basePath%>/organization/Protocol/uploadBx.do',

			    /*  //设置上传图片大小 */
			    fileSingleSizeLimit: 5*1024*1024,
			   /*  //上传数量限制 */
			    // 选择文件的按钮。可选。
			    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
			    pick: '#pickerdaoru',
			    fileNumLimit:1, 
			    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
			    resize: false,
			    accept: {  
			        extensions: 'xls',  
			    }, 
			});
			
			//当有文件被添加进队列的时候
			uploaderdaoru.on( 'fileQueued', function( file ) {
				file.type="0";
			    $("#thelistdaoru").append( '<div id="' + file.id + '" class="item">' +
			        '<h4 id="'+file.id+'" class="info">' + file.name + '</h4>'+
			        '<span onclick="delFiledaoru(\''+file.id+','+file.name+','+3+'\')">删除</span>' +
			         
			    '</div>' );
			    file.name=file.name;//将参数放置到文件名上   file.name.replace(',','')+','+3+','+vm.addassessrec.assessid+','+vm.addassessrec.rankid
			});

			//文件上传过程中创建进度条实时显示。
			uploaderdaoru.on( 'uploadProgress', function( file, percentage ) {
			    var $li = $( '#'+file.id ),
			        $percent = $li.find('.progress .progress-bar');

			    // 避免重复创建
			    if ( !$percent.length ) {
			        $percent = $('<div class="progress progress-striped active">' +
			          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
			          '</div>' +
			        '</div>').appendTo( $li ).find('.progress-bar');
			    }

			    $li.find('p.state').text('上传中');

			    $percent.css( 'width', percentage * 100 + '%' );
			});

			uploaderdaoru.on( 'uploadSuccess', function( file , response) {
				$( '#'+file.id ).find('p.state').text('已上传');
				console.log(response);
				var obj = jQuery.parseJSON(response._raw);
				console.log(obj.ret);
				console.log(obj.uploadMsg);
				debugger;
				if(response.ret=='00000'){
					uploaderdaoru.removeFile(file.id , true);
					 vm.daoruflag=true;
					$('#upMsg').val("上传成功");
				}else{
					uploaderdaoru.removeFile(file.id , true);
					$('#upMsg').val(response.uploadMsg);
			   
				}
			    
			});

			uploaderdaoru.on( 'uploadError', function( file ) {
			    $( '#'+file.id ).find('p.state').text('上传出错');
			});

			uploaderdaoru.on( 'uploadComplete', function( file ) {
			    $( '#'+file.id ).find('.progress').fadeOut();
			});
			uploaderdaoru.on('error', function (type) {
				
			    if (type == "Q_EXCEED_NUM_LIMIT") {
			    	$('#upMsg').val("最多支持1个文件");
			     } else if (type == "F_DUPLICATE") {
			    	 $('#upMsg').val("文件已存在队列中");
			     }else if (type == "Q_TYPE_DENIED") {
			    	 $('#upMsg').val("文件类型不满足");
			     }else if (type == "F_EXCEED_SIZE") {
			    	 $('#upMsg').val("文件大小不能超过5M");
			     }else if (type == "Q_TYPE_DENIED") {
			    	 $('#upMsg').val("请上传JPG、PNG、GIF、BMP格式文件");
			     } else if (type == "Q_EXCEED_SIZE_LIMIT") {
			    	 $('#upMsg').val("文件大小不能超过5M");
			     }else {
			    	 $('#upMsg').val("上传出错！请检查后重新上传！错误代码"+type);
			     }
			});



			//点击按钮
			$("#ctlBtndaoru").on( 'click', function() {
				  console.log("上传...");  
				  uploaderdaoru.upload();  
			});
			

			//删除附件
			function delFiledaoru(id){
				var fileid = id.split(',')[0];
				var filename = id.split(',')[1];
				var filetype = id.split(',')[2];
				 $("#"+fileid).hide();
				uploaderdaoru.removeFile(fileid, true);
			}
			
			
			function downloadfile() {
				window.location.href= "../../download/protocolBxInfo.xls";
			}
			
		</script>
</html>
