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
						  <webTag:Text id="customer_id" name="customer_id"  value='${rmHelper.returnParams.customer_id}' displayLable="客户代码" readonly="true" isdisplay="true"/>
<%-- 						  <webTag:Text  id="member_id" name="member_id" value='${rmHelper.returnParams.member_id}' displayLable="会员编号:" readonly="true" />
 --%>					</div><!-- /.row -->
					<div class="row">
						  <webTag:Text id="company_name"     name="company_name"     value='${rmHelper.returnParams.company_name}' displayLable="单位名称:" readonly="true"  />
						  <webTag:Text id="company_address"  name="company_address"  value='${rmHelper.returnParams.company_address}' displayLable="地址:" readonly="true"/>
						  <webTag:Text id="company_telphone" name="company_telphone" value='${rmHelper.returnParams.company_telphone}' displayLable="电话:" readonly="true"  />
					</div><!-- /.row -->
					<div class="row">
						  <webTag:Text id="company_fax"      name="company_fax"      value='${rmHelper.returnParams.company_fax}' displayLable="传真:" readonly="true"  />
						  <webTag:Text  id="company_mobile"  name="company_mobile"   value='${rmHelper.returnParams.company_mobile}' displayLable="联系方式:" readonly="true" />
						  <webTag:Text id="company_postcode" name="company_postcode" value='${rmHelper.returnParams.company_postcode}' displayLable="邮编:" readonly="true"  />
					</div><!-- /.row -->
					
					<div class="row">                                    
						  <webTag:Text id="corporation_represen" name="corporation_represen" value='${rmHelper.returnParams.corporation_represen}' displayLable="法人代表:" readonly="true"  />
						  <webTag:Text id="company_url"          name="company_url"          value='${rmHelper.returnParams.company_url}' displayLable="公司网址:" readonly="true"  />
                          <webTag:Text id="company_mail"         name="company_mail"         value='${rmHelper.returnParams.company_mail}' readonly="true" displayLable="电子邮件:"/>
					</div><!-- /.row -->
					<div class="row">                                    
						  <webTag:Text id="company_industry"            name="company_industry"             value='${rmHelper.returnParams.company_industry}' displayLable="行业性质:" readonly="true"  />
						  <webTag:Text id="corporation_represen_qq"     name="corporation_represen_qq"      value='${rmHelper.returnParams.corporation_represen_qq}' displayLable="qq:" readonly="true"  />
                          <webTag:Text id="corporation_represen_wechat" name="corporation_represen_wechat"  value='${rmHelper.returnParams.corporation_represen_wechat}' readonly="true" displayLable="微信:"/>
					</div><!-- /.row -->

					<div class="row">
						<webTag:TextareaTag id="company_remark" name="company_remark" rows='3' value='${rmHelper.returnParams.company_remark}' readonly="true" displayLable="备注:"/>
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
								<th>联系人</th>
								<th>联系电话</th>
								<th>qq</th>
								<th>微信</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="sm" items="${rmHelper.returnParams.listCont}" varStatus="number">
								<tr>
									<td>${number.index+1}</td>
									<td>${sm.branch_id}</td>
									<td>${sm.branch_name}</td>
									<td>${sm.corporation_contact_person}</td>
									<td>${sm.corporation_contact_mobile}</td>
									<td>${sm.corporation_contact_qq}</td>
									<td>${sm.corporation_contact_wechat}</td>
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
