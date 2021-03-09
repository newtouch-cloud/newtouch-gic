<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>
<%@page import="com.newtouch.component.c11assistant.ServletHelper"%>

<%
	//request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
	//request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>新致金保通</title>
<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp"%>
<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp"%>
<!-- 回跳、收缩及上跳 -->
<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp"
	flush="true" />
<script>
		var linkUrl = "<%=basePath%>/organization/Protocol/queryProtocolList.do";
		$(document).ready(function(){
			linkUrl = linkUrl+"?<%=ServletHelper.getHttpRequestQueryString(request)%>&nowPage=__id__&";
            $("#Pagination").pagination(${rmHelper.pageCount.allRows}
            							,{
            								items_per_page:${rmHelper.pageCount.rows4Page}
            							   ,num_display_entries:5
            							   ,ellipse_text:'...'
            							   ,current_page:${rmHelper.pageCount.nowPage}-1
            							   ,link_to:linkUrl
            							   ,callback: defaultQuery
            							 });
        });
		
		function invalid(){
	 		return confirm("确定要注销该信息吗？");
		}
		
		function defaultQuery(){
			document.queryForm.submit;
		}
		function add(){
				location.href="<%=basePath%>/redirect/redirect.do?linkUrl=newtouch/organization/protocol/protocolAdd";
			}
		
		//查询条件中 开始日期 自   到  前后顺序正确性校验
		 jQuery.validator.addMethod("checkDate1",function(value,element){
	
    	var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		var flag = true;
   		if(!isUndefined(startdate)&&!isUndefined(enddate)){
   			if(startdate>enddate){
   				flag=false;
   			}
   		}
    	if(!flag){
    		return false;
    	}else{
    		$("label:contains('日期输入顺序有误')").remove();
    		return true;
    	}
   	},"日期输入顺序有误"); 
		function isUndefined(paraValue){
	        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
	        return false;
	    }
			   $(document).ready(function() {
					 $("#queryForm").validate({
						 rules:{
							 startdate:{// 生效日期   从 Date 非空
								 checkDate1:[]
							 },
							 enddate:{// 终止日期  从 Date 非空
								 checkDate1:[]
							 },
						 },
					  onkeyup:false
						 });
			     });
			 //校验样式效果,文本框获取焦点,隐藏该文本框相应报错信息
		   		 $("#queryForm").find("input").each(function(){
		           		$(this).click(function(){
		           			var _this=$(this);
		           			if(_this.hasClass("error")){
		           				_this.removeClass("error");
		           				var labelAR = _this.parent().find("label[class='error']");
		           				labelAR.remove();
		           			}
		           		});
		           	});
		   	//导出
		   		function importInfo(){
		   	               $('#queryForm').attr("action","<%=basePath%>/organization/Protocol/exportProtocol.do"); //设置action指向导出
		   	               $('#queryForm').submit();
		   	               $('#queryForm').attr("action","<%=basePath%>/organization/Protocol/queryProtocolList.do"); //设置action指向查询
		   	            }
		   	  
		   		//导出功能
		   		function exportBtn(){
		   		//	var endDate = $("#endDate").val();
		   		//	if(endDate =="" || endDate.length <= 0){
		   		//		alert("导出数据日期不能为空");
		   		//		return false;//	
		   		//	}
		   		  $('#queryForm').attr("action","<%=basePath%>/organization/Protocol/doExpProtocol.do"); //设置action指向导出
		   	               $('#queryForm').submit();
		   	               $('#queryForm').attr("action","<%=basePath%>/organization/Protocol/queryProtocolList.do"); 
		   			//	?endDate="+endDate;
		   		};
		   		
		   		var protocol_category;
		   		function importBtn(){
		   			debugger;
		   			protocol_category = $("#protocol_category").val();
		   			if(protocol_category == undefined || protocol_category ==""){
		   				alert("导入数据时需要选择导入协议类型！");
		   				return;
		   			}
		   			location.href="<%=basePath%>/organization/Protocol/goImpProtocol.do?protocol_category="+protocol_category;
		   		}
		</script>
</head>
<body>
	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i> 协议管理 </span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>
				协议管理</span><span class="divider">/</span> <span><i
				class="icon-list icon-red"></i> 查询</span>
			<div class="slideUp_Down" id="Shrinkbutton1"></div>
		</div>
		<!-- 面包屑导航  end -->

		<!-- 查询面板 start -->
		<div class="row-fluid" id="Shrinkcontent1">
			<form id="queryForm" name="queryForm"
				class="form-horizontal alert alert-info fade in span12"
				action="<%=basePath%>/organization/Protocol/queryProtocolList.do"
				method="POST" autocomplete="off">
				<div class="row">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display: none">
						<center>
							<image id="dialog_img"></image>
							</br> <span>${rmHelper.msgStr}</span>
						</center>
					</div>

					<!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
					<webTag:HiddenInputTag name="flag" id="flag"
						value="${rmHelper.returnParams.flag}"></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="removeflag" id="removeflag"
						value="${removeflag}"></webTag:HiddenInputTag>
					<webTag:HiddenInputTag id="result_flag" name="result_flag"
						value="${rmHelper.successflag}" displayLable="状态位" />
					<webTag:HiddenInputTag id="msg" name="msg"
						value="${rmHelper.msgStr}" displayLable="状态信息" />
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath%>' />
					<div class="row">
						<jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree.jsp"
							flush="true" />
						<webTag:Date id="dateofsign1" name="dateofsign1"
							value='${rmHelper.returnParams.dateofsign1}'
							dateFormat="yyyy-MM-dd" displayLable="签订日期从" />
						<webTag:Date id="dateofsign2" name="dateofsign2"
							value='${rmHelper.returnParams.dateofsign2}'
							dateFormat="yyyy-MM-dd" displayLable="至" />
					</div>
					<div class="row">
						<jsp:include page="/WEB-INF/jsp/ca/cacore/util/protocolTree.jsp"
							flush="true" />
						<webTag:Date id="enddate1" name="enddate1"
							value='${rmHelper.returnParams.enddate1}' dateFormat="yyyy-MM-dd"
							displayLable="终止日期从" />
						<webTag:Date id="enddate2" name="enddate2"
							value='${rmHelper.returnParams.enddate2}' dateFormat="yyyy-MM-dd"
							displayLable="至" />

					</div>
					<div class="row">
						<div class="control-group span4">
							<label class="control-label" for="status">协议状态</label>
							<div class='controls'>
								<select class="input-medium null" id="status" name="status">
									<option value="">---请选择---</option>
									<c:forEach var="status" items="${listStatus}">
										<c:if test="${rmHelper.returnParams.status == status.code}">
											<option selected="selected" value="${status.code}">${status.name}</option>
										</c:if>
										<c:if test="${rmHelper.returnParams.status != status.code}">
											<option value="${status.code}">${status.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<!-- <div class="control-group span4">    
							<label class="control-label" for="status">协议状态</label> <div class="controls"> 
							<select id="status"   name="status" class="input-medium null">
							</select>
							</div></div>
							 <div class="control-group span4">    
							<label class="control-label" for="sign_type">签订类型</label> <div class="controls"> 
						 	<select id="sign_type"   name="sign_type" class="input-medium null">
							</select>
							</div></div>-->
						<div class="control-group span4">
							<label class="control-label" for="work_nature">签订类型</label>
							<div class='controls'>
								<select class="input-medium null" id="sign_type"
									name="sign_type">
									<option value="">---请选择---</option>
									<c:forEach var="singType" items="${listSignType}">
										<c:if
											test="${rmHelper.returnParams.sign_type == singType.code}">
											<option selected="selected" value="${singType.code}">${singType.name}</option>
										</c:if>
										<c:if
											test="${rmHelper.returnParams.sign_type != singType.code}">
											<option value="${singType.code}">${singType.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="control-group span4">
							<label class="control-label" for="work_nature">一月内到期</label>
							<div class='controls'>
								<select class="input-medium null" id="isOverdue"
									name="isOverdue">
									<option value="">---请选择---</option>
									<option value='1'
										<c:if test="${rmHelper.returnParams.isOverdue=='1'}"> selected="selected"</c:if>>是</option>
									<option value='0'
										<c:if test="${rmHelper.returnParams.isOverdue=='0'}"> selected="selected"</c:if>>否</option>
								</select>
							</div>
						</div>
						<!-- <webTag:Select id="isOverdue"
							value='${rmHelper.returnParams.isOverdue}' name="isOverdue"
							displayLable="一月内到期">
							<option value='1'>是</option>
							<option value='0'>否</option>
							<c:if test="${rmHelper.returnParams.isOverdue=='1'}">
								<option selected="selected" value='1'>是</option>
							</c:if>
							<c:if test="${rmHelper.returnParams.isOverdue=='0'}">
								<option selected="selected" value='0'>否</option>
							</c:if>
						</webTag:Select> -->
					</div>
					<div class="row">
						<webTag:Text id="agreement_no" name="agreement_no"
							value='${rmHelper.returnParams.agreement_no}' displayLable="协议号" />
						<!-- 	<div class="control-group span4">    
							<label class="control-label" for="protocol_category">协议类型</label> <div class="controls"> 
							<select  id="protocol_category" name="protocol_category" class="input-medium null"></select>
							</div></div> -->
						<div class="control-group span4">
							<label class="control-label" for="protocol_category">协议类型</label>
							<div class='controls'>
								<select class="input-medium null" id="protocol_category"
									name="protocol_category">
									<option value="">---请选择---</option>
									<c:forEach var="pro" items="${listPro}">
										<c:if
											test="${rmHelper.returnParams.protocol_category == pro.code}">
											<option selected="selected" value="${pro.code}">${pro.name}</option>
										</c:if>
										<c:if
											test="${rmHelper.returnParams.protocol_category != pro.code}">
											<option value="${pro.code}">${pro.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="text-align: right;">
						<button type="submit" class="btn btn-danger" onClick="">
							<i class="icon-search icon-white"></i>查询
						</button>
						<!-- 				    	<button type="button" class="btn btn-danger" onClick="importInfo();" ><i class="icon-download-alt icon-white"></i>导出</button> -->
						<!-- <webTag:Button name="exportCustomer.do" type="button" onClick="importInfo();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导出"/> -->
						<button name="resetting" id="newreset" type="button"
							class="btn btn-danger">重置</button>
						<button type="button" class="btn btn-danger" onclick="add();">
							<i class="icon-plus icon-white"></i>新增
						</button>
						<button type="button" class="btn btn-danger"
							onclick="exportBtn();">导出</button>
						<button type="button" class="btn btn-danger"
							onclick="importBtn();">导入</button>

					</div>
			</form>
		</div>
		<!-- 查询面板 end -->

		<!-- 查询结果 start -->
		<div class="overAuto row-fluid" id="fixeTD">
			<table
				class="table table-striped table-bordered bootstrap-datatable datatable ">
				<thead>
					<tr>
						<th class="FixedTd">序号</th>
						<th class="FixedTd">操作</th>
						
						<th>协议类型</th>
						<th>合同类别</th>
						<th>所属机构名称</th>
						<th>甲方</th>
						<th>乙方</th>
						<th>丙方</th>
						<th>协议对象</th>
						<th>协议号</th>
						<th>协议签订日期</th>
						<th>协议生效起期</th>
						<th>协议生效止期</th>
						<th>签订类型</th>
						<th>保险公司</th>
						<th>协议状态</th>
						<th>合同金额</th>
						<th>代理企业类型</th>
						<th>代理社会统一信用代码</th>
						<th>汽车信息咨询</th>
						<th>代理开户行</th>
						<th>代理银行账号</th>
						<th>代理联系人</th>
						<th>代理电话</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cm" items="${rmHelper.returnMsg.dataList}"
						varStatus="index">
						<tr>
						<td class="FixedTd">${index.index+1}</td>
						<td class="FixedTd"><a class="btn btn-mini btn-dangerLight"
								href='<%=basePath %>/organization/Protocol/toModifyProtocol.do?seq_id=${cm.seq_id}'
								style='text-decoration: none;'><i
									class="icon-pencil icon-white"></i>修改</a> <a
								class="btn btn-mini btn-dangerLight"
								href='<%=basePath %>/organization/Protocol/goProtocolModifyView.do?seq_id=${cm.seq_id}'
								style='text-decoration: none;'><i
									class="icon-zoom-in icon-white"></i>明细</a> <c:if
									test="${cm.status!='提前终止'}">
									<a class="btn btn-mini btn-dangerLight"
										href='<%=basePath %>/organization/Protocol/upProStatus.do?seq_id=${cm.seq_id}'
										style='text-decoration: none;'><i
										class="icon-off icon-white"></i>解除</a>
									<!-- Edit -->
								</c:if> 
								<a class="btn btn-mini btn-dangerLight"
										href='<%=basePath %>/organization/Protocol/upSignType.do?seq_id=${cm.seq_id}'
										style='text-decoration: none;'><i
										class="icon-pencil icon-white"></i>续签</a>
								<a class="btn btn-mini btn-dangerLight"
								href='<%=basePath %>/organization/Protocol/goImpProtocolPdf.do?seq_id=${cm.seq_id}'
								style='text-decoration: none;'><i
									class="icon-off icon-white"></i>导入合同附件</a> <!-- Edit --></td>
							
							<td>${cm.protocol_category}</td>
							<td>${cm.contract_type}</td>
							<td>${cm.branch_name}</td>
							<td>${cm.party_a}</td>
							<td>${cm.party_b}</td>
							<td>${cm.party_c}</td>
							<td>${cm.ins_branch}</td>
							<td>${cm.agreement_no}</td>
							<td>${cm.dateofsign}</td>
							<td>${cm.startdate}</td>
							<td>${cm.enddate}</td>
							<td>${cm.sign_type}</td>
							<td>${cm.ins_branchname}</td>
							<td>${cm.status}</td>
							<td>${cm.amount}</td>
							<td>${cm.enterprise_type}</td>
							<td>${cm.credit_code}</td>
							<td>${cm.isconsult}</td>
							<td>${cm.bank_name}</td>
							<td>${cm.bank_code}</td>
							<td>${cm.party_b_name}</td>
							<td>${cm.party_b_phone}</td>
							<td>${cm.remarks}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination pagination-centered">
			<ul id="Pagination"></ul>
		</div>
	</div>
	<!-- 		底部高度填充块 -->
	<div class="zeoBottomH90"></div>
	<!-- 		底部高度填充块 结束-->
</body>

<!--   <script type="text/javascript">
    $(document).ready(function(){
    	//加载合同类型，协议类型
    	$.ajax({
            type: "GET",
            url: "/CACore/organization/Protocol/treeList.do",
            async : true,
            dataType: "json",
            success: function (respData) {
                positionList = respData.retList;
                var arr = [];
                arr.push('<option value="">---请选择---</option>')
                if(respData.retList){
                    for (var i = 0; i < positionList.length; i++) {
                        var item = positionList[i];
                        arr.push('<option value="' + item.proCode + '">' + item.proName + '</option>');
                    }
                }
                $("#protocol_category").html(arr.join('\n'))
            },
            error: function (request, status, errorTrown) {
                alert('协议类型加载失败', {icon: 5});
            }
        });
    	
    	 //加载协议状态数据
    	 $.ajax({
             type: "POST",
             url: "/CACore/organization/Protocol/proStatus.do",
             async : true,
             dataType: "json",
             success: function (respData) {
                 positionList = respData.retList;
                 var arr = [];
                 arr.push('<option value="">---请选择---</option>')
                 if(respData.retList){
                     for (var i = 0; i < positionList.length; i++) {
                         var item = positionList[i];
                         arr.push('<option value="' + item.code + '">' + item.name + '</option>');
                     }
                 }
                 $("#status").html(arr.join('\n'))
             },
             error: function (request, status, errorTrown) {
                 alert('协议状态加载失败', {icon: 5});
             }
         });
    	 //加载签订类型数据
    	 $.ajax({
             type: "POST",
             url: "/CACore/organization/Protocol/proSignType.do",
             async : true,
             dataType: "json",
             success: function (respData) {
                 positionList = respData.retList;
                 var arr = [];
                 arr.push('<option value="">---请选择---</option>')
                 if(respData.retList){
                     for (var i = 0; i < positionList.length; i++) {
                         var item = positionList[i];
                         arr.push('<option value="' + item.code + '">' + item.name + '</option>');
                     }
                 }
                 $("#sign_type").html(arr.join('\n'))
             },
             error: function (request, status, errorTrown) {
                 alert('签订类型加载失败', {icon: 5});
             }
         });
    	 
    });
</script> -->
</html>
