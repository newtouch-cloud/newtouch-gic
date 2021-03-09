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
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>	
		
		
		<!-- uploadify start -->
		<link href="<%=basePath%>/resources/newtouch/demo/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/resources/newtouch/demo/uploadify/uploadimg.css" rel="stylesheet" type="text/css" />    	
    	<script src="<%=basePath%>/resources/newtouch/demo/uploadify/swfobject.js" type="text/javascript"></script>
    	<script src="<%=basePath%>/resources/newtouch/demo/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
		<!-- uploadify end-->	
	</head>
	
	<script>
			$(document).ready(function () {
				$("#uploadify").uploadify({ //上传
		            'swf': '<%=basePath %>/resources/newtouch/demo/uploadify/uploadify.swf', //swf 文件路径
		            'uploader': '<%=basePath %>/CRM/Customer/uploadClassResult.do',
		            'fileObjName': 'fileupload',
		            'folder': 'upload',//您想将文件保存到的路径
		            'queueID': 'fileQueue',//与下面的id对应
		            'queueSizeLimit': 20,
		            'width': 80,
		            'height' : 25,
		            'buttonClass': 'buttonCss',/*  btn btn-danger*/
		            'removeCompleted': true,
		            'fileTypeDesc': '支持格式:jpg/gif/jpeg/png/bmp/txt/doc/rar/zip/xls/xlsx/docx/pdm/pdb/pdf/ppt', //文件类型的说明   如果允许选择所有文件则需要删除此2项,否则js报错
			    	'fileTypeExts': '*.jpg;*.gif;*.jpeg;*.png;*.bmp;*.txt;*.doc;*.rar;*.zip;*.xls;*.docx;*.pdm;*.pdb;*.pdf;*.ppt,*.xlsx;',  //指定允许上传的文件类型
		            'method': 'post', //发送方式，默认post
		            'auto': true, //是否自动上传，设成true后，自动执行uploadify('upload','*') 方法
		            'multi': true,// 是否支持多文件上传
		            'progressData': 'speed', //文件上传时显示的数据，显示'speed' 速度，'percentage' 显示百分比
		            'simUploadLimit': 20,
		            'removeTimeout': '1',  //上传成功后进度条消失时间,秒
		            'requeueErrors': false, //若设置为True，那么在上传过程中因为出错导致上传失败的文件将被重新加入队列。
		            'fileSizeLimit': '2MB', //上传文件大小限制，默认单位是KB 限制2M
		            'buttonText': '导入',
		            'onFallback': function () { //无Flash时触发
		                alert('没有支持的Flash版本，请更新Flash版本!');
		            },
		            'onUploadSuccess': function (file, data, response) { // 每个文件文件上传成功时都会触发
		                try {
		                    var result = $.parseJSON(data);
		                    
		                    if (result.uploadMsg == 'success') {
		                      //上传完后导入到数据库
		                        $.post('<%=basePath %>/TMS/TrainingClass/importClassResult.do', {uploadFilename: result.uploadFilename}, function(data) {
		                        	var result = $.parseJSON(data);	
		                        	if (result.isSuccess == 'true') {
			                        	$('input[name="uploadFilename"]').val('');
			                        	<%--  if(confirm('数据导入成功，是否刷新页面显示导入内容？')){
				                       		 location.href = '<%=basePath %>/TMS/TrainingProject/queryTrainingPlanList.do';
				                       		} --%>
			                        	 var result_flag="true";
			                       		 var msg="数据导入成功，是否刷新页面显示导入内容？";
			                       		 var path=$("#path").val();
			                       		 //弹出消息
			                             dialogForUpload(result_flag,msg,path,"/TMS/TrainingClass/queryTrainingClassResultMana.do");  
			                        }else{
			                        	$('input[name="uploadFilename"]').val('');
			                        	alertInfo($('form'), 'error', '导入失败，请核实导入模板及模板参数是否正确');
			                        	$("#upMsg").text(result.msg);
			                        }
			                   
			                	});
		                    }else{
		                    	 alertInfo($('form'), 'error', '上传失败，请重新上传。');
		                    }
		                } catch (e) {}
		               /*  alertInfo($('form'), 'error', '操作失败。'); */
		            },
		            'onUploadError': function (file, errorCode, errorMsg, errorString) {
		                //alert(errorMsg);
		            },
		            'onSelectError': function (file) {
		            	
		            },
		            'onUploadStart': function (file) { //动态参数设置
		
		            }
		        });
		    }); 
		</script>	
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户查询</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户信息明细</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" method="POST">
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户基本信息</span>
					</div>
					<div class="row">
						  <webTag:Text id="customer_id" name="customer_id"  value='${rmHelper.returnParams.customer_id}' displayLable="客户代码" readonly="true" isdisplay="true" />
<%-- 						  <webTag:Text  id="member_id" name="member_id" value='${rmHelper.returnParams.member_id}' displayLable="会员编号:" readonly="true"/>
 --%>					</div><!-- /.row -->
					<div class="row">
						  <webTag:Text id="name" name="name" value='${rmHelper.returnParams.name}' displayLable="客户姓名:" readonly="true"  />
						  <webTag:Text id="title" name="title"  value='${rmHelper.returnParams.title}' displayLable="称呼:" readonly="true"/>
						  <webTag:DynamicSelectTag src="customerTypeSelect" id="customer_type" name="customer_type"  value='${rmHelper.returnParams.customer_type}' displayLable="客户类型:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
					</div><!-- /.row -->
					<div class="row">
						  <webTag:DynamicSelectTag src="certiTypeSelect" id="certi_type" name="certi_type" value='${rmHelper.returnParams.certi_type}' displayLable="证件类型:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
						  <webTag:Text  id="certi_no" name="certi_no" value='${rmHelper.returnParams.certi_no}' displayLable="证件号码:" readonly="true" />
						  <webTag:Date id="certi_validdate" name="certi_validdate" value='${rmHelper.returnParams.certi_validdate}' displayLable="证件有效期至:" readonly="true" />
					</div><!-- /.row -->
					
					<div class="row">                                    
                       	<webTag:DynamicSelectTag src="genderSelect" name="gender" id="gender" displayLable="性别:" value='${rmHelper.returnParams.gender}' onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
                        <webTag:Date id="birthday" name="birthday"  value='${rmHelper.returnParams.birthday}' displayLable="出生日期:" readonly="true" />
                        <webTag:Text id="education" name="education"  value='${rmHelper.returnParams.education}' readonly="true" displayLable="学历:"/>
					</div><!-- /.row -->
					<div class="row">                                    
                        <webTag:DynamicSelectTag src="nationalitySelect" id="nationality" name="nationality" value='${rmHelper.returnParams.nationality}' displayLable="国籍:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
                        <webTag:DynamicSelectTag src="nationSelect" id="nation" name="nation" value='${rmHelper.returnParams.nation}' displayLable="民族:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
                        <webTag:Text id="homeplace" name="homeplace"  value='${rmHelper.returnParams.homeplace}' readonly="true" displayLable="籍贯:"/>
					</div><!-- /.row -->

					<div class="row">                                    
                        <webTag:Text id="seat_address" name="seat_address" value='${rmHelper.returnParams.seat_address}' readonly="true" displayLable="户口所在地:"/>
                        <webTag:Text id="height" name="height" value='${rmHelper.returnParams.height}' readonly="true" displayLable="身高(cm):"/>
                        <webTag:Text id="weight" name="weight"  value='${rmHelper.returnParams.weight}' readonly="true" displayLable="体重(kg):"/>
					</div><!-- /.row -->
					
					<div class="row">                                    
                        <webTag:DynamicSelectTag src="politicalSelect" id="political" name="political" value='${rmHelper.returnParams.political}' displayLable="政治面貌:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
                   		<webTag:DynamicSelectTag src="educationSelect" id="education2" name="education2" value='${rmHelper.returnParams.education2}' displayLable="教育程度:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
                   		<webTag:DynamicSelectTag src="maritalSelect" id="marital_stat" name="marital_stat" value='${rmHelper.returnParams.marital_stat}' displayLable="婚姻状况:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly" />
                    </div><!-- /.row -->
					
					<div class="row">
						 <webTag:DynamicSelectTag src="healthSelect" id="health" name="health" value='${rmHelper.returnParams.health}' displayLable="健康状况:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>                                    
						 <webTag:DynamicSelectTag src="jobTypeSelect" id="job_type" name="job_type" value='${rmHelper.returnParams.job_type}' displayLable="职业类别:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/> 
						 <webTag:Text id="job_code" name="job_code"  value='${rmHelper.returnParams.job_code}' readonly="true" displayLable="职业:"/>
					</div><!-- /.row -->
					
					<div class="row">                                    
                         <webTag:DynamicSelectTag src="customerIncomTypeSelect" id="income_type" name="income_type" value='${rmHelper.returnParams.income_type}' displayLable="收入区间:" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
                         <webTag:Text id="bank_code" name="bank_code" value='${rmHelper.returnParams.bank_code}' readonly="true" displayLable="银行编码:"/>
						 <webTag:Text id="bank_account_no" name="bank_account_no"  value='${rmHelper.returnParams.bank_account_no}' readonly="true" displayLable="银行账号 :"/>                                   
					</div><!-- /.row -->
					
					<div class="row">                                    
                        <webTag:Text id="bank_account_name" name="bank_account_name" value='${rmHelper.returnParams.bank_account_name}' readonly="true" displayLable="银行账户名: "/>
						<webTag:DynamicSelectTag src="YNStatusSelect" name="is_telmsgservice" id="is_telmsgservice" value='${rmHelper.returnParams.is_telmsgservice}' displayLable="是否接收短信服务 :" onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();" readonly="readonly"/>
					</div><!-- /.row -->
					<div class="row">
						<webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' readonly="true" displayLable="备注:"/>
					</div><!-- /.row -->
					</fieldset>
					
					
					<!-- 客户联系信息 -->
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户联系信息</span>
					</div>
					<div class="overAuto row-fluid">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						<thead>
							<tr>
								<th>序号</th>
								<th>机构代码</th>
								<th>机构名称</th>
								<th>家庭地址</th>
								<th>邮政编码</th>
								<th>移动电话</th>
								<th>传真电话</th>
								<th>住宅电话</th>
								<th>办公电话</th>
								<th>电子邮箱</th>
								<th>工作单位名称</th>
								<th>变更时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="sm" items="${rmHelper.returnParams.listCont}" varStatus="number">
								<tr>
									<td>${number.index+1}</td>
									<td>${sm.branch_id}</td>
									<td>${sm.branch_name}</td>
									<td>${sm.address}</td>
									<td>${sm.zip}</td>
									<td>${sm.mobile}</td>
									<td>${sm.fax}</td>
									<td>${sm.telphone}</td>
									<td>${sm.job_tel}</td>
									<td>${sm.email}</td>
									<td>${sm.job_com}</td>
									<td>${sm.modifydate}</td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
					</div>
				  </fieldset>	
				  
				  
				  
				  <!-- 承保信息 -->
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 承保信息</span>
					</div>
					<div class="overAuto row-fluid">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						<thead>
							<tr>
								<th>序号</th>
								<th>承保险种</th>
								<th>起保时间</th>
								<th>终保时间</th>
								<th>保单号</th>
								<th>保费（元）</th>
								<th>在保状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="a" items="${rmHelper.returnParams.listLas}" varStatus="number">
								<tr>
									<td>${number.index+1 }</td>
									<td>${a.riskname }</td>
									<td>${a.startdate }</td>
									<td>${a.enddate }</td>
									<td>${a.policyno }</td>
									<td>${a.netpremium }</td>
									<td>${a.status}</td>
								</tr>
							</c:forEach>
							<tr>
								<td>合计保费</td>
								<td colspan="6">${sum2}</td>
							</tr>
						</tbody>
						</table>
					</div>
				    <div class="row" style="text-align:right;">
<%-- 					    		<a class="btn btn-danger" href="<%=basePath%>/CRM/Customer/customerQuery.do"><i class="icon-share-alt icon-white"></i>返回</a>
 --%>					</div><!-- /.row -->
				  </fieldset>	
				  
				  
				   <!-- 理赔信息 -->
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 理赔信息</span>
					</div>
					<div class="overAuto row-fluid">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						<thead>
							<tr>
								<th>序号</th>
								<th>保单号</th>
								<th>赔案号</th>
								<th>出险日期</th>
								<th>已决金额</th>
								<th>未决金额</th>
								<th>赔案状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="b" items="${rmHelper.returnParams.listinf}" varStatus="number">
								<tr>
									<td>${number.index+1}</td>
									<td>${b.policyno }</td>
									<td>${b.claims_no }</td>
									<td>${b.event_date }</td>
									<td>${b.determined_money }</td>
									<td>${b.undetermined_money }</td>
									<td>${b.claims_status_name }</td>
								</tr>
							</c:forEach>
							<tr>
								<td>合计赔付</td>
								<td colspan="6">${sum1}</td>
								
							</tr>
						</tbody>
						</table>
					</div>
				  </fieldset>	
				  
				  
				  
				   <!-- 接触信息 -->
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 接触信息</span>
					</div>
					<div class="overAuto row-fluid">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						<thead>
							<tr>
								<th>序号</th>
								<th>日期</th>
								<th>客户代码</th>
								<th>姓名</th>
								<th>内容</th>							
							</tr>
						</thead>
						<tbody>
							<c:forEach var="c" items="${rmHelper.returnParams.listJieChu}" varStatus="number">
								<tr>
									<td>${number.index+1 }</td>
									<td>${c.action_time }</td>
									<td>${c.customer_id }</td>
									<td>${c.name }</td>
									<td>${c.action_notes}</td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
					</div>
				  </fieldset>	
				  
				  
				   <!-- 客户价值分析 -->
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户价值分析</span>
					</div>
					<div class="row">
						<!-- <label class="control-label" for="uploadify">客户价值分析:</label> -->
                   		<!-- <div style="width: 700px;margin-left:158px;">
                        	<input type="file" name="uploadify"id="uploadify" />
							<div id="fileQueue"></div>
                   		</div> -->
                   		<webTag:AttachmentDownloadTag busId='${rmHelper.returnParams.customer_id}'   attachmentType="30" fileName='${rmHelper.returnParams.file_name}' trueFlag='${rmHelper.returnParams.file_name !=null}' basePath="<%=basePath %>" displayLable="附件下载:" noAttachment="未上传附件"/>
                   	</div>	
				    <div class="row" style="text-align:right;">
					    <a class="btn btn-danger" href="<%=basePath %>/CRM/Customer/customerQuery.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
				  </fieldset>
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
