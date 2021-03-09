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
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i> 人员管理 </span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>
				人员基本信息查询</span><span class="divider"></span> <span><i
				class="icon-list icon-red"></i>明细</span>
		</div>
		<!-- 面包屑导航  end -->

		<!-- 添加信息数据区 start -->
		<div class="row-fluid">
			<form class="form-horizontal alert alert-info fade in span12"
				action="" method="POST">
				<!-- 提示信息标签 -->
				<webTag:ReturnMsgTag id="msg" name="msg"
					successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}' />
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
								displayLable="年龄:" readonly="true" ></webTag:Text>
						<webTag:Text id="person_status" name="person_status" value='${rmHelper.returnParams.person_status}'
								displayLable="员工状态" readonly="true" isdisplay="false"></webTag:Text>
				    </div>
				    <div class="row">
						<webTag:Date name="end_date" id="end_date"
						value='${rmHelper.returnParams.end_date}' displayLable="离职时间:" readonly="true" ></webTag:Date>
						<%-- <webTag:Text id="work_relation" name="work_relation" value='${rmHelper.returnParams.work_relation}'
								displayLable="用工类型" readonly="true" isdisplay="false" ></webTag:Text> --%>
						<webTag:Text id="work_nature" name="work_nature" value='${rmHelper.returnParams.work_nature}'
								displayLable="合同类型:" readonly="true" ></webTag:Text>
						<div class="row">
							<webTag:Text name="home_address" id="home_address"
										 value='${rmHelper.returnParams.home_address}' displayLable="通信地址:"
										 readonly="true"></webTag:Text>
						</div>
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
							readonly="true"></webTag:Text>--%>
						<!-- 照片 -->
						<webTag:Text id="idcard" name="idcard"
							value='${rmHelper.returnParams.idcard}' displayLable="身份证号:"
							readonly="true" />
						<webTag:Text name="ismarrid" id="ismarrid"
									 value='${rmHelper.returnParams.ismarrid}' displayLable="婚否:"
									 readonly="true" ></webTag:Text>
						<webTag:Text id="phone" name="phone"
									 value='${rmHelper.returnParams.phone}' displayLable="移动电话:"
									 readonly="true"  />
				    </div>

				    <%--<div class="row">
						<webTag:Text name="home_address" id="home_address"
							value='${rmHelper.returnParams.home_address}' displayLable="通信地址:"
							 readonly="true"></webTag:Text>
				    </div>--%>
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
									<td>${rmHelper.returnParams.education}</td>
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
										 displayLable="执业证编号:" readonly="true" />
							<webTag:Text id="practice_startdate" name="practice_startdate"
										 value='${rmHelper.returnParams.practice_startdate}'
										 displayLable="执业证有效期开始:" readonly="true" />
							<webTag:Text id="practice_enddate" name="practice_enddate"
										 value='${rmHelper.returnParams.practice_enddate}'
										 displayLable="执业证有效期截止:" readonly="true"  />
						</div>

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
								value='${rmHelper.returnParams.team_name}' displayLable="所在部门"
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
					<%-- </c:if> --%>
					<%-- <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 展业信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton4"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent4">
						<div class="row">
							<webTag:Text id="microshop_id" name="microshop_id"
								value='${rmHelper.returnParams.microshop_id}'
								displayLable="微店账号:" readonly="true" />
							<webTag:Text id="shopkeeper_name" name="shopkeeper_name"
								value='${rmHelper.returnParams.shopkeeper_name}'
								displayLable="微店主姓名:" readonly="true" />
							<webTag:Text id="increase_type" name="increase_type"
								value='${rmHelper.returnParams.increase_type}'
								displayLable="增员方式:" readonly="true" />
							
						</div>
						<div class="row">
							
							<webTag:Text id="channel_code" name="channel_code"
								value='${rmHelper.returnParams.channel_code}' displayLable="渠道码:"
								readonly="true" />

						</div>
					</div> --%>

					<%--<div class="dreadcount">
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
									<!-- <th>学年</th> -->
									<th>学历</th>
									<th>入学年份</th>
									<th>毕业年份</th>
									<!-- <th>学位</th> -->
									<th>是否最高学位</th>
									<th>专业类别</th>
									<th>教育类型</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="education"
									items="${education.returnMsg.dataList}">
									<tr>
										<td>${education.address}</td>
										<td>${education.major}</td>
										&lt;%&ndash; <td>${education.year}</td> &ndash;%&gt;
										<td>${education.degree}</td>
										<td>${education.start_date}</td>
										<td>${education.graduation_date}</td>
										&lt;%&ndash; <td>${education.degree_type}</td> &ndash;%&gt;
										
										<td>${education.ishigh_degree}</td>
										
										<td>${education.major_type}</td>
										<td>${education.education_type}</td>

									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>--%>
					<%-- <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 个人从业经历</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton6"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent6">
						<table
							class="table table-striped table-bordered bootstrap-datatable datatable ">
							<thead>
								<tr>
									<th>工作单位</th>
									<th>部门</th>
									<th>职务</th>
									<th>工作单位起始日期</th>
									<th>离职日期</th>
									<th>证明人</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="work" items="${work.returnMsg.dataList}">
									<tr>
										<td>${work.address}</td>
										<td>${work.work_position}</td>
										<td>${work.major}</td>
										<td>${work.start_date}</td>
										<td>${work.graduation_date}</td>
										<td>${work.approve_person}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div> --%>
					<%-- <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 专业技术职务</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton7"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent7">
						<table
							class="table table-striped table-bordered bootstrap-datatable datatable ">
							<thead>
								<tr>
									<th>资格证证书名</th>
									<th>获取途径</th>
									<th>资格证获取时间</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="license" items="${license.returnMsg.dataList}">
									<tr>
										<td>${license.license_name}</td>
										<td>${license.license_channel}</td>
										<td>${license.license_startdate}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div> --%>
					<%-- <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i>家庭成员信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton8"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent8">
						<table
							class="table table-striped table-bordered bootstrap-datatable datatable ">
							<thead>
								<tr>
									<th>家庭成员姓名</th>
									<th>家庭成员性别</th>
									<th>家庭成员生日</th>
									<th>与本人关系</th>
									<th>政治面貌</th>
									<th>公司以及职务</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="family" items="${family.returnParams.family}">
									<tr>
										<td>${family.family_name}</td>
										<td>${family.family_sex}</td>
										<td>${family.family_birthday}</td>
										<td>${family.family_relation}</td>
										<td>${family.family_political}</td>
										<td>${family.family_position}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div> --%>


					<%-- <c:if test='${rmHelper.returnParams.political=="党员"}'> --%>

					<%-- </c:if> --%>
					<%-- <c:if test='${rmHelper.returnParams.person_status=="0"}'>
						<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i>离职信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton11"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent11">
							<div class="row">
								<webTag:Text id="end_date" name="end_date"
									value='${rmHelper.returnParams.end_date}' displayLable="离职时间:"
									readonly="true"  />
								<webTag:Text id="reason" name="reason"
									value='${rmHelper.returnParams.reason}' displayLable="离职原因:"
									readonly="true"  />
								<webTag:Text id="apply_date" name="apply_date"
									value='${rmHelper.returnParams.apply_date}' displayLable="申报时间:"
									readonly="true" />
							</div>
							<div class="row">
							
								<webTag:Text id="check_date" name="check_date"
									value='${rmHelper.returnParams.check_date}' displayLable="审核时间:"
									readonly="true"  />

								<webTag:Text id="status" name="status"
									value='${rmHelper.returnParams.status}' displayLable="办理状态:"
									readonly="true" isdisplay="false" />
								<webTag:Text id="practice_no" name="practice_no"
									value='${rmHelper.returnParams.practice_no}'
									displayLable="展业编号" readonly="true"  />

							</div>
						</div>
					</c:if> --%>

				</fieldset>
				<div class="row" align="right">
					<!-- <a id="backButton" class="btn btn-danger"
						href="javascript:history.back(-1)"><i
						class="icon-share-alt icon-white"></i>返回</a> -->
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
