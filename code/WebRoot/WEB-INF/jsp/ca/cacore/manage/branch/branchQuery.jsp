<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@ page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@ page import="com.newtouch.component.c11assistant.ServletHelper"%>
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
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/> 
       	<script type="text/javascript">
			var linkUrl = "<%=basePath %>/Branch/queryBranch.do";
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
			
			jQuery.validator.addMethod("checkDateOrder",function(value,element){
				var first_Date=$("#found_date").val();
		     	var second_Date=$("#recall_date").val();
		     	if(!isUndefined(first_Date)||!isUndefined(second_Date)){
		     		var flag=false;
		     		if(!isUndefined(first_Date)&&!isUndefined(second_Date)&&(second_Date>=value&&value>=first_Date)){
		     			flag=true;
		     		}
		     		if(!isUndefined(first_Date)&&isUndefined(second_Date)&&(value>=first_Date)){
		     			flag=true;
		     		}
		     		if(isUndefined(first_Date)&&!isUndefined(second_Date)&&(second_Date>=value)){
		     			flag=true;
		     		}
		     	}
		     	if(!isUndefined(first_Date)&&!isUndefined(second_Date)){
		     		if(first_Date>second_Date){
		     			return false;
		     		}
		     	}
		     	$("label:contains('起始时间时间顺序有误')").remove();
		     	return true;
		    },"起期必须小于、等于止期");
			
			function defaultQuery(){
				document.queryForm.submit;
			}
			
		  	function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
		  	
			$(document).ready(function() {
				$("#queryForm").validate({
					rules:{
						found_date:{
							checkDateOrder:[]
						},
						recall_date:{
							checkDateOrder:[]
						}
					},
					onkeyup:false
				});
			});
			
			function invalid(){
		 		return confirm("确定要注销该机构吗？");
			}
			
			function valid(){
			 		return confirm("确定要恢复该机构吗？");
			}
			
			
           
			/* byzdd 20190606 end */
		</script>
		<script type="text/javascript">
		function exportSalesInfo(){
			$("#queryForm").attr("action","<%=basePath %>/Branch/exportBranchInfo.do");//设置action执向导出
			$("#queryForm").submit();
			$("#queryForm").attr("action","<%=basePath %>/Branch/queryBranch.do"); //设置action执向查询
		}
		/* byzdd 20190606 start */
		function importRedirect(){
			location.reload();
			location.href="<%=basePath %>/Branch/importexcel.do";
		}
       
        function chongzhi(){

        	 this.location.href="<%=basePath %>/Manage/Branch/toQueryBranch.do";
        	 /* location.reload(); */
        }
        function yingyezhizhao(){
        	var seq_id=$("#seq_id1").val();
        	var branch_nam = $("#branch_name2").val();
        	var licensepath=$("#licensepath1").val(); 
        	/* alert(seq_id+">>"+branch_nam+">>"+licensepath); */
        	/* alert($("#seq_id1").val()+">>>"+$("#branch_name1").val()+">>>"+$("#licensepath1").val()); */
        	$("#seq_id").val(seq_id);
        	$("#branch_name").val(branch_nam);
        	$("#licensepath").val(licensepath); 
        	$("#queryForm").attr("action","<%=basePath %>/Branch/importRedirect.do");//设置action执向导出
			$("#queryForm").submit();
			$("#queryForm").attr("action","<%=basePath %>/Branch/queryBranch.do"); //设置action执向查询 
        }
		</script>
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 机构管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 机构管理</span>
			    <div class="slideUp_Down" id="Shrinkbutton1"></div>
			</div>
			<!-- 面包屑导航  end -->
			
			<!-- 查询面板 start -->
			<div class="row-fluid">
				<div class="Shrinkcontent" id="Shrinkcontent1">
					<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/Branch/queryBranch.do" method="POST" autocomplete="off">
						<!-- 提示信息 -->
					    <div id="dialog" title="提示信息" style="display:none">
							<center><image id="dialog_img"></image></br><span>${returnHepler.msgStr}</span></center>
						</div>
					    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${returnHepler.successflag}"   displayLable="msg状态"/>
						<webTag:HiddenInputTag id="msg" name="msg" value="${returnHepler.msgStr}"    displayLable="msg信息"/>
						<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
						<div id="div1" class="row">
							 <jsp:include page="../../util/NewBranchTreejg.jsp" flush="true"/> 
							
							<!-- 查询条件修改   by 孙豪 -->
							<%-- <webTag:Text   id="branch_name1" name="branch_name1" value='${rmHelper.returnParams.branch_name1}' displayLable="用户名"/> --%>
	                        <%-- <webTag:DynamicSelectTag src="statusSelect" name="status" id="status" displayLable="机构状态" value='${rmHelper.returnParams.status}'/> --%>
						    <%-- <webTag:DynamicSelectTag src="branchLevelSelectNew" id="branch_level_code" name="branch_level_code"  value='${rmHelper.returnParams.branch_level_name}' displayLable="机构级别"  /> --%>
							<!-- 查询条件修改 by zdd 20190606-->
							<%-- <webTag:Date id="found_date" name="found_date"  value="${rmHelper.returnParams.found_date}" displayLable="成立日期" /> --%>
							
							<webTag:Text   id="branch_id1" name="branch_id1" value='${rmHelper.returnParams.branch_id1}' displayLable="机构编码"/>
							<!-- 添加机构层级 by zdd 20190606 -->
							 <webTag:Select   id="branch_level_name"   name="branch_level_name"  displayLable="机构层级" >
								   <c:forEach var="entry" items="${maps}">
								   <c:if  test="${rmHelper.returnParams.branch_level_name!=entry}">
								      <option   value='${entry}'>${entry}</option>
								    </c:if>
								      <c:if  test="${rmHelper.returnParams.branch_level_name==entry}">
	                                  <option selected="selected"  value='${rmHelper.returnParams.branch_level_name}'>${rmHelper.returnParams.branch_level_name}</option>
	                                  </c:if>
                                    </c:forEach>
						 </webTag:Select>
						</div>
						<div class="row">
							<webTag:Text   id="branch_name1" name="branch_name1" value='${rmHelper.returnParams.branch_name1}' displayLable="组织名称"/>
						    <!--   组织状态 -->
						    
						    <%--<webTag:Select   id="status"   name="status"  displayLable="组织状态" >
						      <c:forEach var="dds" items="${m}" >
						      <c:if  test="${rmHelper.returnParams.status!=dds.key}">
						       <option   value='${dds.key}'>${dds.value}</option>
						      </c:if>
						      <c:if  test="${rmHelper.returnParams.status==dds.key}">
	                             <option selected="selected"  value='${rmHelper.returnParams.status}'>${dds.value}</option>
	                          </c:if>
	                          
						      </c:forEach>
						    </webTag:Select>--%>
							<webTag:Text id="channelcode" name="channelcode" value='${rmHelper.returnParams.channelcode}' displayLable="渠道代码"/>
						    <webTag:Text   id="permitarea" name="permitarea" value='${rmHelper.returnParams.permitarea}' displayLable="所属区域"/>
						</div>
						<div class="row">
							<webTag:Date id="found_date" name="found_date"  value="${buslicensefounddate}" displayLable="营业执照成立日期从" />
							<webTag:Date id="recall_date" name="recall_date"  value="${rmHelper.returnParams.recall_date}" displayLable="至" />
							<%--<webTag:Select name="licensepath" id="licensepath"
										   value='${rmHelper.returnParams.licensepath}' displayLable="否上传营业执照">
								<webTag:Option value="1" displayLable="是"></webTag:Option>
								<webTag:Option value="0" displayLable="否"></webTag:Option>
							</webTag:Select>--%>
						</div>

					   <div>	
						<div>
						   <!-- zdd20190717 -->
						   <webTag:HiddenInputTag   id="seq_id" name="seq_id" value=''/>
						   <webTag:HiddenInputTag    id="cbranch_name" name="cbranch_name" value='' displayLable="hhhhhhhh"/>
						   <webTag:HiddenInputTag    id="licensepath" name="licensepath" value=''/>
						</div>
					    <div class="row" style="text-align:right;float: right">
					    <button type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-search icon-white"></i>查询</button>
						   	<%-- <webTag:Button name="exportBranchInfo" type="button" onClick="exportSalesInfo();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导出"/> --%> 
						   	<!-- 添加重置  by 孙豪 -->
						   	<button name="resetting"  id="newreset"  type="button" class="btn btn-danger" onclick="chongzhi()" >重置</button>
					     <%-- <webTag:Button name="exportBranchInfo" type="button" onClick="importRedirect();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导入"/>  <!--  by zhudongdong 20190606 --> --%>
						   	<webTag:Button name="exportBranchInfo" type="button" onClick="exportSalesInfo();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导出"/>  <!--  by zhudongdong 20190606 -->
						   
					      	
						</div><!-- /.row -->
					</div>
					</form>
				</div>
			</div>
			<!-- 查询面板 end -->
			
			<!-- 查询结果 start -->
			<div class="overAuto row-fluid">
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
					<thead>
						<tr>
							<!-- 修改显示查询结果 by 孙豪 -->
							<th>序号</th>
							<th>操作</th>
							<th>组织编码</th>
							<th>组织名称</th>
							<th>组织详情</th>
							<!-- <th>成立日期</th> --><!-- 列表中成立日期不要 by zdd 20190606 -->
							<th>组织层级</th><!-- 列表中 by zdd 20190610 -->
							<th>渠道代码</th>
							<th>统一社会信用代码</th><!-- 列表中 by zdd 20190610 -->
						    <th>负责人</th><!-- 列表中 by zdd 20190610 -->
						    <th>营业场所</th><!-- 列表中 by zdd 20190610 -->
						    <th>营业执照成立日期</th><!-- 列表中 by zdd 20190610 -->
						    <th>银保监机构编码</th><!-- 列表中 by zdd 20190610 -->
						    <th>许可经营区域</th><!-- 列表中 by zdd 20190610 -->
							<th>机构状态</th> 
							<th>退出时间</th> <!-- 显示列表 by zdd 20190613 -->
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
							<tr>
								<!-- 修改显示查询结果 by 孙豪 -->
								<td>${number.index+1}</td>
								<td>
								     <input type="hidden" id="seq_id1" name="seq_id1" value="${sm.seq_id}"/>
									<input type="hidden" id="branch_name2" name="branch_name2" value="${sm.branch_name}"/>
									<input type="hidden" id="licensepath1" name="licensepath1" value="${sm.licensepath}"/>
									<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/Branch/viewBranch.do?seq_id=${sm.seq_id}'><i class="icon-zoom-in icon-white"></i>明细</a><!-- View -->
									<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/Branch/branchGoModify.do?seq_id=${sm.seq_id}'><i class="icon-pencil icon-white"></i>修改</a><!-- Edit -->
									<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/Branch/branchGoAddJunior.do?seq_id=${sm.seq_id}'><i class="icon-pencil icon-white"></i>新增下级机构</a><!-- Edit -->
									<%-- <a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/Branch/importRedirect.do?seq_id=${sm.seq_id}&branch_name=${sm.branch_name}&licensepath=${sm.licensepath}'><i class="icon-pencil icon-white"></i>营业执照</a><!--  by zhudongdong 20190606 --> --%>
									<!-- <button name="resetting"  id="newreset"  type="button" class="btn btn-danger" onclick="yingyezhizhao()" >营业执照</button> -->
								    <%-- <c:if test="${sm.branch_level != 2 && sm.branch_level != 5 && sm.branch_level != 6}">
								    	<a class="btn btn-mini btn-dangerLight" href="<%=basePath %>/Branch/importRedirect.do?seq_id=${sm.seq_id}"><i class="icon-pencil icon-white"></i>上传营业执照</a><!--  by zhudongdong 20190606 -->
								     	<a class="btn btn-mini btn-dangerLight" href="<%=basePath %>/Branch/businessLicenseHis.do?seq_id=${sm.seq_id}"><i class="icon-pencil icon-white"></i>营业执照历史记录</a>
									</c:if> --%>
								</td>
								<td>${sm.branch_id}</td>
								<td>${sm.branch_name}</td>
								<td>${sm.sbname}</td>
								<td>${sm.branch_level_name}</td>
								<td>${sm.channelcode}</td>
								<td>${sm.unifiedSocialCreditNO}</td>
								<td>${sm.delegate}</td>
								<td>${sm.province}${sm.city}${sm.area}<%-- ${sm.deatailedaddress} --%></td>
								<td>${sm.buslicensefounddate}</td>
								<td>${sm.permitcode}</td>
								<td>${sm.permitarea}</td>
                                <th>
                                    <c:if  test="${sm.status=='0'}">
                                                                                                                          失效                                                                                                                                                      
                                    </c:if>
                                    <c:if  test="${sm.status=='1'}">
                                                                                                                             启用
                                    </c:if>
                                    <c:if  test="${sm.status=='2'}">
                                                                                                                              禁用                                                                                     
                                    </c:if>
                                </th>
                                <td>${sm.exittime}</td>
                                
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
				<!-- 查询结果 end -->
				<div class="pagination pagination-centered">
				    <ul id="Pagination"></ul>
				</div>
			</div>
			<!-- 底部高度填充块 -->
			<div class="zeoBottomH90"></div>
			<!-- 底部高度填充块 结束-->
	</body>
</html>