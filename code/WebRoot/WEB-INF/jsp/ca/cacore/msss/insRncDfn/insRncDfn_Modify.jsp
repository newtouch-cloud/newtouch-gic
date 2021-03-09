<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
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
	<script>


		$(document).ready(function(){
			var baonameid = $("#baonameid").val();
			var baoname = $("#baoname").val();
			var nameid= baonameid+","+baoname;
			//加载保险公司
			$.ajax({
				type: "GET",
				url: "<%=basePath %>/msss/InsRncDfn/getBaoxianCP.do",
				async : true,
				dataType: "json",
				success: function (respData) {

					positionList = respData.retList;
					var arr = [];
					arr.push('<option value="">---请选择---</option>')
					if(respData.retList){
						for (var i = 0; i < positionList.length; i++) {
							/* var s=item.id+","+item.name; */

							var item = positionList[i];

							if(baonameid==item.id){
								arr.push('<option selected="selected" value="' + item.id+','+item.name + '">' + item.name + '</option>');
							}else{
								arr.push('<option value="' + item.id+','+item.name + '">' + item.name + '</option>');
							}
						}
					}
					$("#branch_id").html(arr.join('\n'));
				},
				error: function (request, status, errorTrown) {
					alert('保险公司加载失败', {icon: 5});
				}
			});
			$("#branch_id").change(function () {

				var branch_id =  $("#branch_id").val();
				var abitype = $("#abitype").val();
				/*  alert("更改值"+ branch_id ); */
				if(branch_id ==null){

					/*  alert(arr[0]); */
				}else{
					var arr= branch_id.split(",");

					$.ajax({
						type: "POST",
						url: "<%=basePath %>/msss/InsRncDfn/getBaoxianType.do",
						async : true,
						data:{"branch_id":arr[0]},
						dataType: "json",
						success: function (respData) {

							positionList = respData.retList;
							var arr = [];
							arr.push('<option value="">---请选择---</option>')
							if(respData.retList){
								for (var i = 0; i < positionList.length; i++) {

									var item = positionList[i];
									if(abitype==item.bjtype){
										arr.push('<option selected="selected" value="' + item.bjtype+ '">' + item.bjtypename + '</option>');
									}else{
										arr.push('<option value="' + item.bjtype+ '">' + item.bjtypename + '</option>');
									}
								}
							}
							$("#bjtype").html(arr.join('\n'));
						},
						error: function (request, status, errorTrown) {
							alert('保险公司加载失败', {icon: 5});
						}
					});
				}
			});

		});
	</script>
	<style>
		.style2{
			float: right;
		}
		.input-medium null{
			width:150px;
			margin-right: 100px
		}
		.style1{
			float:right;
			margin-top: -5px;
			height: 5px;
			width: 48px;
		}

	</style>
</head>
<body>
<div class="container-fluid">
	<div class="dreadcount">
		<span class=mrl14><i class="icon-list icon-red"></i>保险公司管理 </span><span class="divider">/</span>
		<span><i class="icon-list icon-red"></i>产品管理 </span><span class="divider">/</span>
		<span><i class="icon-list icon-red"></i>修改产品信息</span>
		<div class="slideUp_Down" id="Shrinkbutton1"></div>
	</div>
	<!-- 数据区 start -->
	<div class="row-fluid">
		<div class="Shrinkcontent" id="Shrinkcontent1">
			<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/msss/InsRncDfn/modifyInsRncDfn.do" method="POST">
				<div id="dialog" title="提示信息" style="display:none">
					<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
				</div>
				<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
				<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
				<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
				<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
				<webTag:HiddenInputTag   id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}'/>
				<webTag:HiddenInputTag id="abitype" name="abitype" value='${rmHelper.returnParams.bjtype}'/>
				<webTag:HiddenInputTag id="baonameid" name="baonameid" value='${rmHelper.returnParams.branch_id}'/>
				<webTag:HiddenInputTag id="baoname" name="baoname" value='${rmHelper.returnParams.branch_name}'/>
				<div class="row" >
					<%-- <jsp:include page="/WEB-INF/jsp/ca/cacore/util/cpyBranchTree.jsp" flush="true"/> --%>
					<webTag:Text  name='branch_name' id="branch_name"  value='${rmHelper.returnParams.branch_name}' displayLable="保险公司机构选择"   readonly="true"/>
					<webTag:Text id="classCode" name="classCode" value='${rmHelper.returnParams.classCode}' displayLable="险类编码" isdisplay="true" />
					<webTag:Text id="className" name="className" value='${rmHelper.returnParams.className}' displayLable="险类名称" isdisplay="true" />
				</div>

				<%-- <div class="row" >
                    <webTag:Text id="product_code" name="product_code" value='${rmHelper.returnParams.product_code}' displayLable="产品编码" isdisplay="true" />
                    <webTag:Text id="product_name" name="product_name" value='${rmHelper.returnParams.product_name}' displayLable="产品名称" isdisplay="true" />
                </div> --%>
				<div class="row" >
					<webTag:Text id="riskName"  name="riskName"  value='${rmHelper.returnParams.riskName}' displayLable="险种名称" isdisplay="true" />
					<webTag:Text id="riskCode"  name="riskCode"  value='${rmHelper.returnParams.riskCode}' displayLable="险种编号" isdisplay="true" />
					<%-- <webTag:Text id="bjtypename"  name="bjtypename"  value='${rmHelper.returnParams.bjtypename}' displayLable="保监分类" isdisplay="true"/><!-- by zddxiu --> --%>
					<webTag:Select    id="bjtype"  name="bjtype"  displayLable="保监统计分类" isdisplay="true">
						<%-- <c:forEach var="model" items="${models}">
                            <c:if test="${rmHelper.returnParams.bjtype==model.bjtype}">
                             <option selected="selected" value='${model.bjtype}'>${model.bjtypename}</option>
                            </c:if>
                             <c:if test="${rmHelper.returnParams.bjtype!=model.bjtype}">
                             <option  value='${model.bjtype}'>${model.bjtypename}</option>
                            </c:if>
                        </c:forEach>   --%>
					</webTag:Select>
				</div>

				<div class="row" style="text-align:right;">
					<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
					<a id="backButton" class="btn btn-danger" href="<%=basePath %>/msss/InsRncDfn/toQueryInsRncDfnList.do"><i class="icon-share-alt icon-white"></i>返回</a>
				</div>

			</form>
		</div>
	</div>
</div>
<!-- 底部高度填充块 -->
<div class="zeoBottomH90"></div>
<div class="zeoBottom"></div>
<div class="zeoBottom"></div>
<!-- 底部高度填充块 结束-->
<script type="text/javascript">

	$(document).ready(function(){
		var baonameid = $("#baonameid").val();
		var baoname = $("#baoname").val();
		var nameid= baonameid+","+baoname;
		var abitype = $("#abitype").val();
		/*  alert("更改值"+ branch_id ); */
		if(baonameid ==null){
			/*  alert(arr[0]); */
		}else{
			var arr= baonameid.split(",");

			$.ajax({
				type: "POST",
				url: "<%=basePath %>/msss/InsRncDfn/getBaoxianType.do",
				async : true,
				data:{"branch_id":arr[0]},
				dataType: "json",
				success: function (respData) {

					positionList = respData.retList;
					var arr = [];
					arr.push('<option value="">---请选择---</option>')
					if(respData.retList){
						for (var i = 0; i < positionList.length; i++) {

							var item = positionList[i];
							if(abitype==item.bjtype){
								arr.push('<option selected="selected" value="' +abitype+ '">' + item.bjtypename + '</option>');
							}else{
								arr.push('<option value="' + item.bjtype+ '">' + item.bjtypename + '</option>');
							}
						}
					}
					$("#bjtype").html(arr.join('\n'));
				},
				error: function (request, status, errorTrown) {
					alert('保险公司加载失败', {icon: 5});
				}
			});
		}
	});
</script>
</body>
</html>
