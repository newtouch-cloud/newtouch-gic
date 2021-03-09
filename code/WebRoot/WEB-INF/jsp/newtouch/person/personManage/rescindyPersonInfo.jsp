<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>新致金保通</title>
<jsp:include page="/WEB-INF/jsp/newtouch/core/pub/jvbasecss.jsp"
	flush="true" />
	<jsp:include page="/WEB-INF/jsp/newtouch/core/pub/jvbasejs.jsp" flush="true" />
	<!-- 回跳 -->
	<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true" />
</head>
<script type="text/javascript">
	function downloadPic(){
		//var person_no = document.getElementById("person_no").value;
		var url = document.getElementById("upload_approval_file").value;
		if(url == null || url == ""){
			alert("文件不存在,请先上传");
			return;
		}
		window.location.href="<%=basePath %>/Person/PersonManage/downloadPic.do?url="+url;
		<%-- $.ajax({
		 	 type: "GET",
			  url: "<%=basePath%>/Person/PersonManage/downloadPic.do",
			  data: {
				  "url": url
			  },
			  dataType:"text",
			  success: function(data){
				 alert(data); 
			 },
		 	 error: function(){
			 	 alert("下载失败");
		 	 }
			}); --%>
	}
</script>
<body>

	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcou	nt">
			<span class=mrl14><i class="icon-list icon-red"></i> 人员管理 </span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>
				人员基本信息查询</span><span class="divider"></span> <span><i
				class="icon-list icon-red"></i>修改</span>
		</div>
		<!-- 面包屑导航  end -->

		<!-- 添加信息数据区 start -->
		<div class="row-fluid">
			<form class="form-horizontal alert alert-info fade in span12" id="form"
				action="<%=basePath%>/Person/PersonManage/rescindPersonInfo.do" method="POST" autocomplete="off">
				<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper1.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper1.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper1.msgStr}" displayLable="状态信息"/>	
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag name="seq_id" id="seq_id" value="${rmHelper1.returnParams.seq_id}"></webTag:HiddenInputTag>
					<webTag:ReturnMsgTag id="msg" name="msg"
						successflag='${rmHelper1.successflag}' msg='${rmHelper1.msgStr}' />
				<fieldset>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 个人基本信息</span>
					</div>
					<div class="row">
						<webTag:Text id="person_no" name="person_no"
							value='${rmHelper.returnParams.person_no}' displayLable="员工编码"
							readonly="true" isdisplay="false"></webTag:Text>
						<webTag:Text id="person_name" name="person_name"
							value='${rmHelper.returnParams.person_name}' displayLable="员工姓名"
							readonly="true" isdisplay="false"></webTag:Text>
						<webTag:Text id="sex" name="sex"
							value='${rmHelper.returnParams.sex}' displayLable="性别:"
							readonly="true" ></webTag:Text>
						
						<%-- <webTag:Text id="person_type" name="person_type"
								value='${rmHelper.returnParams.person_type}'
								displayLable="员工类型" readonly="true" isdisplay="false"/>
						 --%>
					</div>
					<div class="row">
						<webTag:Date id="birthday" name="birthday"
							value='${rmHelper.returnParams.birthday}' displayLable="出生日期:"
							readonly="true" />
						<webTag:Text id="age" name="age" value='${rmHelper.returnParams.age}'
								displayLable="年龄:"  readonly="true" ></webTag:Text>
						<%--<webTag:Text id="person_status" name="person_status" value='${rmHelper.returnParams.person_status}'
								displayLable="员工状态"  isdisplay="false"></webTag:Text>--%>
						<%--<webTag:Select name="person_status" id="person_status"
									   value='${rmHelper.returnParams.person_status}' displayLable="员工状态">
							<webTag:Option value="1" displayLable="在职"></webTag:Option>
							<webTag:Option value="2" displayLable="离职"></webTag:Option>
						</webTag:Select>--%>
					<div class="control-group span4">
								<label class="control-label" for="person_status">员工状态</label>
								<%--<label class="control-label" for="person_status">员工状态</label>
						<div class='controls'>
							<select class="input-medium null" id="person_status" name="person_status">
								<option value="">---请选择---</option>
								<c:forEach var="person_status" items="${person_statusList}">
									<c:if test="${rmHelper.returnParams.person_status == person_status.enum_code}">
										<option selected="selected" value="${person_status.enum_code}" >${person_status.enum_name}</option>
									</c:if>
									<c:if test="${rmHelper.returnParams.person_status != person_status.enum_code}">
										<option value="${person_status.enum_code}" >${person_status.enum_name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>--%>
						<div class='controls'>
							<select class="input-medium null" name="person_status" id="person_status">
								<option value="">请选择</option>
								<option value="0" <c:if test="${rmHelper.returnParams.person_status=='不在职'}">selected</c:if>>不在职</option>
								<option value="1" <c:if test="${rmHelper.returnParams.person_status=='在职'}">selected</c:if>>在职</option>
								<option value="2" <c:if test="${rmHelper.returnParams.person_status=='离职'}">selected</c:if>>离职</option>
								<option value="3" <c:if test="${rmHelper.returnParams.person_status=='离退休'}">selected</c:if>>离退休</option>
								<option value="4" <c:if test="${rmHelper.returnParams.person_status=='返聘'}">selected</c:if>>返聘</option>
							</select>
						</div>
					</div>
				</div>
				    <div class="row">
						<webTag:Date name="end_date" id="end_date"
						value='${rmHelper.returnParams.end_date}' displayLable="离职时间:" ></webTag:Date>
						<%-- <webTag:Text id="work_relation" name="work_relation" value='${rmHelper.returnParams.work_relation}'
								displayLable="用工类型" readonly="true" isdisplay="false"></webTag:Text> --%>
						<webTag:Text id="work_nature" name="work_nature" value='${rmHelper.returnParams.work_nature}'
								displayLable="合同类型:"  readonly="true" ></webTag:Text>
						<webTag:Text name="ismarrid" id="ismarrid"
							value='${rmHelper.returnParams.ismarrid}' displayLable="婚姻状态:"
							readonly="true" ></webTag:Text>
				    </div>
				    <div class="row">
				    	<webTag:Text name="national" id="national"
							value='${rmHelper.returnParams.national}' displayLable="民族:"
							readonly="true" />
						<webTag:Text id="political" name="political"
							value='${rmHelper.returnParams.political}' displayLable="政治面貌:"
							readonly="true" />
						<webTag:Text name="card_type" id="card_type"
							value='${rmHelper.returnParams.card_type}' displayLable="户口类型:"
							 readonly="true" />
				    </div>
				    <div class="row">
				    	<%--<webTag:Text name="education" id="education"
							value='${rmHelper.returnParams.education}' displayLable="学历:"
									 readonly="true" ></webTag:Text>--%>
						<!-- 照片 -->
						<webTag:Text id="idcard" name="idcard"
							value='${rmHelper.returnParams.idcard}' displayLable="身份证号:"
							readonly="true" />
						<webTag:Text name="home_address" id="home_address"
							value='${rmHelper.returnParams.home_address}' displayLable="通信地址:"
							 readonly="true"></webTag:Text>
							<webTag:Text id="phone" name="phone"
										 value='${rmHelper.returnParams.phone}' displayLable="移动电话:"
										 readonly="true"  />
				    </div>

				    <%-- <div class="row">
						<webTag:Text name="birth_address" id="birth_address"
							value='${rmHelper.returnParams.birth_address}' displayLable="出生地:"
							readonly="true" />
						<webTag:Text name="idcard_adress" id="idcard_adress"
							value='${rmHelper.returnParams.idcard_adress}'
							displayLable="户口所在地:"  readonly="true" />
					</div>
				    
					<div class="row">
						<webTag:Text id="phone" name="phone"
							value='${rmHelper.returnParams.phone}' displayLable="手机号:"
							readonly="true" ></webTag:Text>
						
							<webTag:Text id="email" name="email"
							value='${rmHelper.returnParams.email}' displayLable="邮箱:"
							readonly="true"></webTag:Text>	
					</div>
		
					<div class="row">
						
						<webTag:Text name="social_security_no" id="social_security_no"
							value='${rmHelper.returnParams.social_security_no}'
							displayLable="社会保障号:" readonly="true"></webTag:Text>
						<webTag:Text name="health" id="health"
							value='${rmHelper.returnParams.health}' displayLable="健康状况:"
							readonly="true" ></webTag:Text>
					</div>
					<div class="row">
						
						<webTag:Text name="ismarrid" id="ismarrid"
							value='${rmHelper.returnParams.ismarrid}' displayLable="婚姻状况:"
							readonly="true" ></webTag:Text>
						<webTag:Text name="technology_no" id="technology_no"
							value='${rmHelper.returnParams.technology_no}'
							displayLable="专业技术证书:"  readonly="true"></webTag:Text>
						<webTag:Text name="work_date" id="work_date"
							value='${rmHelper.returnParams.work_date}' displayLable="参加工作时间:"
							 readonly="true"></webTag:Text>
                        <c:if test="${rmHelper.returnParams.audit_status=='0'}">
					</div>
					<div class="row">
                        <webTag:Text name="audit_status" id="audit_status"
							value='未审核' displayLable="审核状态:"
							isdisplay="false" readonly="true"></webTag:Text>
                         </c:if> 
                        <c:if test="${rmHelper.returnParams.audit_status=='1'}">
                        <webTag:Text name="audit_status" id="audit_status"
							value='已审核' displayLable="审核状态:"
							isdisplay="false" readonly="true"></webTag:Text>
                         </c:if> 	 
					</div> --%>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 教育信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton5"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent5">
						<table
								class="table table-striped table-bordered bootstrap-datatable datatable ">
							<thead>
							<tr>
								<th>毕业院校</th>
								<th>专业</th>
								<th>学历</th>
								<th>毕业年份</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach var="education"
									   items="${education.returnMsg.dataList}">
								<tr>
									<td>${education.address}</td>
									<td>${education.major}</td>
									<td>
										<select name="education" id="education" readonly="true" >
											<option value="" style="display: none">未选择</option>
											<option value="1" style="display:none" <c:if test="${rmHelper.returnParams.education=='文盲'}">selected</c:if>>文盲</option>
											<option value="2" style="display:none" <c:if test="${rmHelper.returnParams.education=='小学'}">selected</c:if>>小学</option>
											<option value="3" style="display:none" <c:if test="${rmHelper.returnParams.education=='初中'}">selected</c:if>>初中</option>
											<option value="4" style="display:none" <c:if test="${rmHelper.returnParams.education=='高中'}">selected</c:if>>高中</option>
											<option value="5" style="display:none" <c:if test="${rmHelper.returnParams.education=='中专'}">selected</c:if>>中专</option>
											<option value="6" style="display:none" <c:if test="${rmHelper.returnParams.education=='中技'}">selected</c:if>>中技</option>
											<option value="7" style="display:none" <c:if test="${rmHelper.returnParams.education=='大专'}">selected</c:if>>大专</option>
											<option value="8" style="display:none" <c:if test="${rmHelper.returnParams.education=='本科'}">selected</c:if>>本科</option>
											<option value="9" style="display:none" <c:if test="${rmHelper.returnParams.education=='研究生及以上'}">selected</c:if>>研究生及以上</option>
											<option value="10" style="display:none" <c:if test="${rmHelper.returnParams.education=='博士'}">selected</c:if>>博士</option>
											<option value="11" style="display:none" <c:if test="${rmHelper.returnParams.education=='博士后'}">selected</c:if>>博士后</option>
										</select>
									</td>
									<td>${education.graduation_date}</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>

					</div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 执业信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton3"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent3">
						<div class="row">
							<webTag:Text id="practice_no" name="practice_no"
										 value='${rmHelper.returnParams.practice_no}'
										 displayLable="执业证编号:" readonly="true"/>
							<webTag:Date id="practice_startdate" name="practice_startdate"
										 value='${rmHelper.returnParams.practice_startdate}'
										 displayLable="执业证有效期开始:" readonly="true"></webTag:Date>
							<webTag:Date id="practice_enddate" name="practice_enddate"
										 value='${rmHelper.returnParams.practice_enddate}'
										 displayLable="执业证有效期截止:" readonly="true"></webTag:Date>

						</div>
					<div id="uploadimage" style="width: 100%;"></div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 行政信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton1"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent1">
						<div class="row">
							<webTag:Text id="branch_name" name="branch_name"
								value='${rmHelper.returnParams.branch_name}'
								displayLable="所属机构" readonly="true" isdisplay="false"/>
							<webTag:Text id="team_name" name="team_name"
								value='${rmHelper.returnParams.team_name}' displayLable="所在团队"
								readonly="true" isdisplay="false"/>
							<webTag:Text id="entry_date" name="entry_date"
								value='${rmHelper.returnParams.entry_date}' displayLable="入职时间:" readonly="true"></webTag:Text>
							<%-- <webTag:Text name="work_nature" id="work_nature"
								value='${rmHelper.returnParams.work_nature}' displayLable="用工类型"
								readonly="true" isdisplay="false"></webTag:Text>	 --%>
						</div>
						<div class="row">
							<webTag:Text id="entry_source" name="entry_source"
								value='${rmHelper.returnParams.entry_source}'
								displayLable="入职来源:" readonly="true" />
							<%-- <webTag:Text id="ontrial_date" name="ontrial_date"
								value='${rmHelper.returnParams.ontrial_date}' displayLable="试用期:" readonly="true"></webTag:Text> --%>
						</div>

					</div>

						</div>


				</fieldset>
				<div class="row" align="right">

					<button type="button"  class="btn btn-danger" onclick="saveData();"><i class="icon-inbox icon-white"></i>保存</button></span>
					<%-- <a id="saveButton" class="btn btn-danger"
						href="<%=basePath %>/Person/PersonManage/updatePerson.do?pageName=personInfo"><i
						class="icon-share-alt icon-white"></i>保存</a> --%>
					<a id="backButton" class="btn btn-danger"
						href="<%=basePath %>/Person/PersonManage/toQueryPersonInfo.do?pageName=personInfo"><i
						class="icon-share-alt icon-white"></i>返回</a> 
				</div>
			</form>
		</div>
		<!-- 数据区 end -->
	</div>
	<!-- 		底部高度填充块 -->
	<div class="zeoBottomH90"></div>
	<!-- 		底部高度填充块 结束-->
</body>
</html>
<script type="text/javascript">
	function saveData(){
	$("#form").submit();
} 

<%-- function saveData(){
	var birthday = document.getElementById("birthday").value;
	var education = document.getElementById("education").value;
	var org_id = document.getElementById("org_id").value;
	var org_name = document.getElementById("org_name").value;
	var belong_opt_no = document.getElementById("belong_opt_no").value;
	var belong_opt_name = document.getElementById("belong_opt_name").value;
	var belong_idcard = document.getElementById("belong_idcard").value;
	var technology_no = document.getElementById("technology_no").value;
	var practice_type = document.getElementById("practice_type").value;
	var practice_status = document.getElementById("practice_status").value;
	var practice_no = document.getElementById("practice_no").value;
	var practice_startdate = document.getElementById("practice_startdate").value;
	var practice_enddate = document.getElementById("practice_enddate").value;
	var practice_area = document.getElementById("practice_area").value;
	var channel_type = document.getElementById("channel_type").value;
	var business_scope = document.getElementById("business_scope").value
	var person_no = document.getElementById("person_no").value;
	var political = document.getElementById("political").value;
	var content_code = document.getElementById("content_code").value;
	var political_startdate = "";
	var political_joindate = "";
	var political_company = "";
	var party_org = "";
	var political_fee = "";
	if(political != null && political == "党员"){
		var political_startdate = document.getElementById("political_startdate").value;
		var political_joindate = document.getElementById("political_joindate").value;
		var political_company = document.getElementById("political_company").value;
		var party_org = document.getElementById("party_org").value;
		var political_fee = document.getElementById("political_fee").value;
	}
	/* var gleader = document.getElementById("gleader").value;
	var approval_time = document.getElementById("approval_time").value;
	var approval_file = document.getElementById("approval_file").value;
	var employment_term = document.getElementById("employment_term").value; */

	$.ajax({
		  type: "POST",
		  url: "<%=basePath%>/Person/PersonManage/savePersonInfo.do",
		  data: {
			  "education": education,
			  "org_id": org_id,
			  "org_name": org_name,
			  "belong_opt_no": belong_opt_no,
			  "belong_opt_name": belong_opt_name,
			  "belong_idcard": belong_idcard,
			  "technology_no": technology_no,
			  "practice_type": practice_type,
			  "practice_status": practice_status,
			  "practice_no": practice_no,
			  "practice_startdate": practice_startdate,
			  "practice_enddate": practice_enddate,
			  "practice_area": practice_area,
			  "channel_type": channel_type,
			  "business_scope": business_scope,
			  "political_startdate": political_startdate,
			  "political_joindate": political_joindate,
			  "political_company": political_company,
			  "party_org": party_org,
			  "political_fee": political_fee,
			  "person_no": person_no,
			  "content_code": content_code
			 /*  "gleader": gleader,
			  "approval_time": approval_time,
			  "approval_file": approval_file,
			  "employment_term": employment_term */
			  },
		  dataType:"text",
		  success: function(data){
			 alert(data); 
		  },
		  error: function(){
			  alert("保存失败!");
		  }
		});
} --%>
</script>