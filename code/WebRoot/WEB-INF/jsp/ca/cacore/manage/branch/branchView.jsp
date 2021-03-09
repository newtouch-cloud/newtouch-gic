<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%
	String path = request.getContextPath();
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
	<jsp:include page="../../manage/branch/province.jsp" flush="true"/>
	<script type="text/javascript">
		function downimg(){
			var path=$("#zzimg").val();
			if(path==null || path==""){
				alert("未上传营业执照，请上传后再进行查看！");
				return ;
			}

			location.href="<%=basePath %>/Branch/downImg.do?u="+path;
		}
	</script>
</head>
<body>
<div class="container-fluid" >
	<!-- 面包屑导航  start -->
	<div class="dreadcount">
		<span class=mrl14><i class="icon-list icon-red"></i>机构管理</span><span class="divider">/</span>
		<span><i class="icon-list icon-red"></i>明细查看</span>
	</div>
	<!-- 面包屑导航  end -->

	<!--增加面板 start -->
	<div class="row-fluid">
		<form class="form-horizontal alert alert-info fade in span12" action="" method="POST">
			<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
			<fieldset>

				<div class="row">
					<webTag:Text id="parent_branch_id" name="parent_branch_id" value='${rmHelper.returnParams.parent_branch_id}'  readonly="true" displayLable="上级组织代码" isdisplay="true"/>
					<webTag:Text id="parent_branch_name" name="parent_branch_name" value='${rmHelper.returnParams.parent_branch_name}' readonly="true" displayLable="上级组织名称" isdisplay="true"/>
					<webTag:Text id="branch_level_name" name="branch_level_name" value='${rmHelper.returnParams.branch_level_name}' readonly="true" displayLable="本组织层级" isdisplay="true"/>
				</div><!-- /.row -->

				<div class="row">
					<webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' readonly="true" displayLable="组织编码" isdisplay="true"/>
					<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' readonly="true" displayLable="组织名称" isdisplay="true"/>
					<webTag:Text id="branch_abbr" name="branch_abbr"  value='${rmHelper.returnParams.branch_abbr}' readonly="true" displayLable="组织简称" isdisplay="true"/>
				</div><!-- /.row -->

				<div class="row">
					<!-- 修改成立日期成 营业执照成立日期            by zdd 20190606   -->
					<%-- <webTag:Date id="found_date" name="found_date" value='${rmHelper.returnParams.found_date}' readonly="true" displayLable="成立日期" isdisplay="true"/> --%>

					<webTag:Text id="delegate" name="delegate" value='${rmHelper.returnParams.delegate}' readonly="true" displayLable="负责人:"/>

				</div>

				<!-- 省市县 -->
				<div class="row">
					<webTag:Text id="province" name="province" value='${rmHelper.returnParams.province}' readonly="true" displayLable="省：" />
					<webTag:Text id="city" name="city" value='${rmHelper.returnParams.city}' readonly="true" displayLable="市：" />
					<webTag:Text id="area" name="area" value='${rmHelper.returnParams.area}' readonly="true" displayLable="区/县：" />
				</div>

				<div class="row">
					<c:if  test="${rmHelper.returnParams.status=='0' }">
						<webTag:Text id="status" name="status" value='失效' readonly="true" displayLable="组织状态:" />
					</c:if>
					<c:if  test="${rmHelper.returnParams.status=='1' }">
						<webTag:Text id="status" name="status" value='启用' readonly="true" displayLable="组织状态:" />
					</c:if>
					<c:if  test="${rmHelper.returnParams.status=='2' }">
						<webTag:Text id="status" name="status" value='禁用' readonly="true" displayLable="组织状态:" />
					</c:if>
					<c:if  test="${rmHelper.returnParams.status=='0'}">
						<webTag:Text id="exittime" name="exittime" value='${rmHelper.returnParams.exittime }' readonly="true" displayLable="退出时间:" />
					</c:if>
					<webTag:Text id="channelcode" name="channelcode" value='${rmHelper.returnParams.channelcode}' readonly="true" displayLable="渠道代码信息:" />
					<webTag:Text id="unifiedSocialCreditNO" name="unifiedSocialCreditNO" value='${rmHelper.returnParams.unifiedSocialCreditNO}' readonly="true" displayLable="统一社会信用代码:" />


				</div><!-- /.row -->
				<%--<div class="row"">
                  <webTag:TextareaTag id="remark" name="remark" rows='3'   value="${rmHelper.returnParams.remark}" readonly="true" displayLable="备注:"  />
                  </div>--%>
				<div class="row">
					<%--<webTag:Date id="found_date" name="found_date" value='${rmHelper.returnParams.buslicensefounddate}' readonly="true" displayLable="营业执照成立日期" isdisplay="true"/>--%>
					<webTag:Text id="zip" name="zip" value='${rmHelper.returnParams.zip}' readonly="true" displayLable="邮政编码:" />
					<webTag:Text id="deatailedaddress" name="deatailedaddress" value='${rmHelper.returnParams.deatailedaddress}' readonly="true" displayLable="详细地址:" />
				</div>

				<div class="row">
					<webTag:Text id="telephone" name="telephone" value='${rmHelper.returnParams.telephone}' readonly="true" displayLable="电话号码:"/>
					<webTag:Text id="address" name="address" value='${rmHelper.returnParams.address}' readonly="true" displayLable="联系地址:"/>
					<webTag:Text id="email" name="email" value='${rmHelper.returnParams.email}' readonly="true" displayLable="电子邮件:" />
				</div><!-- /.row -->

				<div class="row">
					<webTag:Text id="fax" name="fax" value='${rmHelper.returnParams.fax}' readonly="true" displayLable="机构传真:"/>
					<webTag:Text id="permitcode" name="permitcode" value='${rmHelper.returnParams.permitcode}' readonly="true" displayLable="银保监许可证编码:"/>
					<webTag:Text id="permitarea" name="permitarea" value='${rmHelper.returnParams.permitarea}' readonly="true" displayLable="许可经营区域:"/>
				</div><!-- /.row -->

				<div class="row" style="text-align:right;">
					<input type="hidden" id="zzimg" name="zzimg" value='${rmHelper.returnParams.seq_id}'/>
					<%--<webTag:Button name="exportBranchInfo" id="exportBranchInfo" type="button" onClick="downimg();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="营业执照"/>--%>  <!--  by zhudongdong 20190606 -->
					<a class="btn btn-danger" href="<%=basePath %>/Manage/Branch/toQueryBranch.do"><i class="icon-share-alt icon-white"></i>返回</a>
				</div><!-- /.row -->
			</fieldset>
		</form>
	</div>
	<!-- 增加面板 end -->

</div>
</body>
</html>