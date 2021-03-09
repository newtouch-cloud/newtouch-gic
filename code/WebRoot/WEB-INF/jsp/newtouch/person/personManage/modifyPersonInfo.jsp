<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
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
				class="icon-list icon-red"></i>修改</span>
		</div>
		<!-- 面包屑导航  end -->

		<!-- 添加信息数据区 start -->
		<div class="row-fluid">
			<form class="form-horizontal alert alert-info fade in span12" id="form"
				action="<%=basePath%>/Person/PersonManage/savePersonInfo.do" method="POST" autocomplete="off">
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
						<webTag:Text id="person_status" name="person_status" value='${rmHelper.returnParams.person_status}'
								displayLable="员工状态" readonly="true" isdisplay="false"></webTag:Text>
				    </div>
				    <div class="row">
						<%--<webTag:Date name="end_date" id="end_date"
						value='${rmHelper.returnParams.end_date}' displayLable="离职时间:" readonly="true" ></webTag:Date>--%>
						<%-- <webTag:Text id="work_relation" name="work_relation" value='${rmHelper.returnParams.work_relation}'
								displayLable="用工类型" readonly="true" isdisplay="false"></webTag:Text> --%>
						<webTag:Text id="phone" name="phone"
							value='${rmHelper.returnParams.phone}' displayLable="移动电话:"
							readonly="true"  />
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
                                     readonly="true"></webTag:Text>--%>
						<!-- 照片 -->
						<webTag:Text id="idcard" name="idcard"
							value='${rmHelper.returnParams.idcard}' displayLable="身份证号:"
							readonly="true" />
						<webTag:Text name="home_address" id="home_address"
							value='${rmHelper.returnParams.home_address}' displayLable="通信地址:"
							 readonly="true"></webTag:Text>
				    </div>
				   <%-- <div class="row">
				    	<webTag:Text id="phone" name="phone"
							value='${rmHelper.returnParams.phone}' displayLable="移动电话:"
							readonly="true"  />

						
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
								<!-- <th>学年</th> -->
								<th>学历</th>
								<%--<th>入学年份</th>--%>
								<th>毕业年份</th>
								<!-- <th>学位</th> -->
								<%--<th>是否最高学位</th>
								<th>专业类别</th>
								<th>教育类型</th>--%>
							</tr>
							</thead>
							<tbody>
							<c:forEach var="education"
									   items="${education.returnMsg.dataList}">
								<tr>
									<td>${education.address}</td>
									<td>${education.major}</td>
										<%-- <td>${education.year}</td> --%>
									<%--<td>${rmHelper.returnParams.education}</td>--%><%--学历之前--%>
									<td readonly="true">
										<%--普通的select标签--%>
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
									<%--<td>${education.start_date}</td>--%>
									<td>${education.graduation_date}</td>
										<%-- <td>${education.degree_type}</td> --%>

									<%--<td>${education.ishigh_degree}</td>

									<td>${education.major_type}</td>
									<td>${education.education_type}</td>--%>

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
										 displayLable="执业证编号:" />
							<webTag:Date id="practice_startdate" name="practice_startdate"
										 value='${rmHelper.returnParams.practice_startdate}'
										 displayLable="执业证有效期开始:"></webTag:Date>
							<webTag:Date id="practice_enddate" name="practice_enddate"
										 value='${rmHelper.returnParams.practice_enddate}'
										 displayLable="执业证有效期截止:"></webTag:Date>

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
						<%-- <div class="row">
							
							<webTag:Text id="formal_date" name="formal_date"
								value='${rmHelper.returnParams.formal_date}'
								displayLable="转正日期:" readonly="true" />
							<webTag:Text id="end_date" name="end_date"
								value='${rmHelper.returnParams.end_date}' displayLable="离职时间:" readonly="true"></webTag:Text>
							<webTag:Text name="superior_position_name" id="superior_position_name"
								value='${rmHelper.returnParams.superior_position_name}'
								displayLable="上级岗位名称:" readonly="true" ></webTag:Text>
						</div> --%>
						<%-- <div class="row">
							
							<webTag:Text id="position_id" name="position_id"
								value='${rmHelper.returnParams.position_id}'
								displayLable="岗位编码:" readonly="true"></webTag:Text>
							<webTag:Text id="position" name="position"
								value='${rmHelper.returnParams.position}' displayLable="岗位名称:"
								readonly="true" />
							<c:if test="${rmHelper.returnParams.ischarge=='是'}">
								<webTag:Text id="ischarge" name="ischarge" value='是'
									displayLable="是否为负责人职位:" readonly="true"></webTag:Text>
							</c:if>
							<c:if test="${rmHelper.returnParams.ischarge=='否'}">
								<webTag:Text id="ischarge" name="ischarge" value='否'
									displayLable="是否为负责人职位:" readonly="true"></webTag:Text>
							</c:if>
						</div> --%>
						
					</div>
                    <%-- <div class="dreadcount">
                      <span><i class="icon-ziliao icon-red mrl14"></i> 岗位信息</span>
                      <div class="Shrinktop">
                        <div class="slideUp_Down" id="Shrinkbutton1"></div>
                      </div>
                    </div>  
                    <div class="Shrinkcontent" id="Shrinkcontent5">
            <table
              class="table table-striped table-bordered bootstrap-datatable datatable ">
              <thead>
                <tr>
                  <th>岗位名称</th>
                  <th>岗位编码</th>
                  <th>上级岗位名称</th>
                  <th>是否为负责人职位</th>
                  <th>任职类型</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="position"
                  items="${positionInfo.returnMsg.dataList}">
                  <tr>
                    <td>${position.position}</td>
                    <td>${position.position_id}</td>
                    <td>${position.superior_position_name}</td>
                    <td>${position.ischarge}</td>
                    <td>${position.profession_type}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>

          </div> --%>
					<%-- <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 财险归属机构</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton2"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent2">
						<div class="row">

							<webTag:Text name="org_id" id="org_id"
								value='${rmHelper.returnParams.org_id}' displayLable="归属财险机构编码:"></webTag:Text>
							<webTag:Text name="org_name" id="org_name"
								value='${rmHelper.returnParams.org_name}' displayLable="归属财险机构名称 :"></webTag:Text>
							<webTag:Text id="belong_opt_no" name="belong_opt_no"
								value='${rmHelper.returnParams.belong_opt_no}' displayLable="归属人工号:"/>
						</div>
						<div class="row">
							<webTag:Text id="belong_opt_name" name="belong_opt_name" value='${rmHelper.returnParams.belong_opt_name}'
								displayLable="归属人姓名:" />
							<webTag:Text id="belong_idcard" name="belong_idcard" value='${rmHelper.returnParams.belong_idcard}'
								displayLable="归属人身份证号:" />
						</div>
					</div> --%>
					<%-- <c:if test='${rmHelper.returnParams.person_type=="个人代理"}'> --%>
						<%--<div class="dreadcount">
							<span><i class="icon-ziliao icon-red mrl14"></i> 执业信息</span>
							<div class="Shrinktop">
								<div class="slideUp_Down" id="Shrinkbutton3"></div>
							</div>
						</div>
						<div class="Shrinkcontent" id="Shrinkcontent3">
							<div class="row">
								<webTag:Text id="practice_no" name="practice_no"
									value='${rmHelper.returnParams.practice_no}'
									displayLable="执业证编号:" />
								<webTag:Date id="practice_startdate" name="practice_startdate"
									value='${rmHelper.returnParams.practice_startdate}'
									displayLable="执业证有效期开始:"></webTag:Date>
								<webTag:Date id="practice_enddate" name="practice_enddate"
									value='${rmHelper.returnParams.practice_enddate}'
									displayLable="执业证有效期截止:"></webTag:Date>

							</div>

						</div>--%>
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

					<%-- <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i>相关事项说明</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton9"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent9">
					
						<div class="row">
					<div class="control-group span4">
						<label class="control-label" for="work_relation">相关事项说明:</label>
						<div class='controls'> 
						<select class="input-medium null" id="content_code" name="content_code">
							<option value="">---请选择---</option>
							<c:forEach var="emp_note" items="${empNoteList}">
								<c:if test="${rmHelper.returnParams.content_code == emp_note.enum_code}">
							 		<option selected="selected" value="${emp_note.enum_code}" >${emp_note.enum_name}</option>
							 	</c:if>
							 	<c:if test="${rmHelper.returnParams.content_code != emp_note.enum_code}">
							 		<option value="${emp_note.enum_code}" >${emp_note.enum_name}</option>
							 	</c:if>
                         	</c:forEach>
						</select>
						</div>
					</div>
						<table
							class="table table-striped table-bordered bootstrap-datatable datatable ">

							<tbody>
								<c:if test="${empty note.returnMsg.dataList}">
									<td>无</td>
								</c:if>
								<c:forEach var="note" items="${note.returnMsg.dataList}">
									<tr>
										<td>${note.content_code}</td>
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
					
					<!-- <div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i>高管信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton9"></div>
						</div>
					</div> -->
					<%-- <div class="Shrinkcontent" id="Shrinkcontent11">
						<div class="row" >
							<webTag:Select name="gleader" id="gleader" value='${rmHelper.returnParams.gleader}' displayLable="是否高管:">
							<webTag:Option value="1" displayLable="是"></webTag:Option>
							<webTag:Option value="0" displayLable="否"></webTag:Option>
							</webTag:Select>
							<webTag:Date name="approval_time" id="approval_time"
							value='${rmHelper.returnParams.approval_time}' displayLable="高管批复时间:"></webTag:Date>
							<webTag:Text name="approval_file" id="approval_file"
							value='${rmHelper.returnParams.approval_file}' displayLable="高管批复文号:" />	
						</div>
				
						<div class="row">
							<webTag:Date name="employment_term" id="employment_term"
								value='${rmHelper.returnParams.employment_term}' displayLable="高管聘期:"></webTag:Date>
							<div class="control-group span4"><label class="control-label" for="upload_approval_file">上传批复文件:</label>
								<input class="input-medium null" type="file" id="file" name="uploadfile" value="${rmHelper.returnParams.upload_approval_file}"/> 
							</div>
            				<button type="button" value="上传"  onclick="fileUpload();">上传</button> 
						</div>
						<div class="row">
            				<webTag:Date name="gvalid_time" id="gvalid_time"
							value='${rmHelper.returnParams.gvalid_time}' displayLable="有效期 :"></webTag:Date>
						</div>
					</div> --%>
					<%-- <div class="Shrinkcontent" id="Shrinkcontent9">
						<table
							class="table table-striped table-bordered bootstrap-datatable datatable ">
							<thead>
								<tr>
									<th>是否高管</th>
									<th>高管批复时间</th>
									<th>高管批复文号</th>
									<th>高管聘期</th>
									<th>下载批复文件</th>
									<th>有效期至</th>
								</tr>
							</thead>
							<tbody>
								<tr>			
									<td>											
										<c:if test="${rmHelper.returnParams.gleader=='1'}">是</c:if>
										<c:if test="${rmHelper.returnParams.gleader=='0'}">否</c:if>
									</td>
									<td>${rmHelper.returnParams.approval_time}</td>
									<td>${rmHelper.returnParams.approval_file}</td>
									<td>${rmHelper.returnParams.employment_term}</td>
									<td><c:if test="${rmHelper.returnParams.gleader=='1'}"><a href="javaScript:void();" onclick="downloadPic();">下载</a></c:if>
									<input id="upload_approval_file" type="hidden" value='${rmHelper.returnParams.upload_approval_file}'/>
									<td>${rmHelper.returnParams.gvalid_time}</td>
								</tr>
							</tbody>
						</table>

					</div> --%>
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