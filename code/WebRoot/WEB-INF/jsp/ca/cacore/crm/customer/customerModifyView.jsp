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
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户信息维护 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户信息明细</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="" method="POST">
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    
					<fieldset>
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户基本信息</span>
					</div>
					<div class="row">
					     <%--  <webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' displayLable="机构代码" readonly="true" isdisplay="true"/>
						  <webTag:Text id="branch_name" name="branch_name"  value='${rmHelper.returnParams.branch_name}' displayLable="机构名称" readonly="true" isdisplay="true"/>
		 --%>			
		            	  <webTag:Text id="customer_id" name="customer_id"  value='${rmHelper.returnParams.customer_id}' displayLable="客户代码" readonly="true" isdisplay="true"/>
<%-- 						  <webTag:Text  id="member_id" name="member_id" value='${rmHelper.returnParams.member_id}' displayLable="会员编号:" readonly="true" />
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
					    <a class="btn btn-danger" id="backButton" href="<%=basePath %>/CRM/Customer/goQueryAndModify.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
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
