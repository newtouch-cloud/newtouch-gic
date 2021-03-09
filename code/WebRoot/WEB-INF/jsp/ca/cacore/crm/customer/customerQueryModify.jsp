<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg" %>
<%@page import="com.newtouch.component.c11assistant.StringHelper" %>
<%@page import="com.newtouch.component.c11assistant.JspHelper" %>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper" %>
<%@page import="com.newtouch.component.c11assistant.ServletHelper" %>
<%
    //request.getContextPath()返回当前页面所在的应用的名字
    String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "";

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
        <%--   $(document).ready(function() {
               //表单校验
               $("#queryForm").validate({
                       rules : {
                           branch_name : {  //机构名称
                               required : true,
                               checkSubmit : []
                           }
                       },
                       onkeyup:false,
                       onfocusout : false
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

           });--%>

        function importBtn() {
            location.href = "<%=basePath%>/CRM/Customer/goImpQueryAndModify.do";
        }

        var linkUrl = "<%=basePath %>/CRM/Customer/goQueryAndModify.do";
        $(document).ready(function () {
            linkUrl = linkUrl + "?<%=ServletHelper.getHttpRequestQueryString(request)%>&nowPage=__id__&";
            $("#Pagination").pagination(${rmHelper.pageCount.allRows}
                , {
                    items_per_page:${rmHelper.pageCount.rows4Page}
                    , num_display_entries: 5
                    , ellipse_text: '...'
                    , current_page: ${rmHelper.pageCount.nowPage}-1
                    , link_to: linkUrl
                    , callback: defaultQuery
                });
        });

        function defaultQuery() {
            document.queryForm.submit;
        }

        //设置查询条件:机构选择是必输项，除去机构选择，其它查询条件中必须还有一个必输项，才能查询出客户信息。
        <%--	jQuery.validator.addMethod("checkSubmit",function(value,element){
               var name = $('#name').val();
               /* var customer_type = $('#customer_type').val();
               var customer_id = $('#customer_id').val();
               var gender = $('#gender').val(); */

               if(name != "" || customer_type != "" || customer_id != "" || gender != ""){
                   return true;
               }else{
                   alert("查询条件必须是机构选择及任意另外最少一个查询条件");
                   return false;
               }
            },"");

           function importInfo(){
               //?branch_id=${rmHelper.returnParams.branch_id}&customer_type=${rmHelper.returnParams.customer_type}&customer_id=${rmHelper.returnParams.customer_id}&name=${rmHelper.returnParams.name}&gender=${rmHelper.returnParams.gender}
               $('#queryForm').attr("action","<%=basePath %>/CRM/Customer/daochuCustomer.do"); //设置action指向导出
               $('#queryForm').submit();
               $('#queryForm').attr("action","<%=basePath %>/CRM/Customer/goQueryAndModify.do"); //设置action执向查询
            }--%>
    </script>
</head>
<body>
<div class="container-fluid">
    <!-- 面包屑导航  start -->
    <div class="dreadcount">
        <span class=mrl14><i class="icon-list icon-red"></i> 客户管理 </span><span class="divider">/</span>
        <span><i class="icon-list icon-red"></i> 客户信息维护</span>
        <div class="slideUp_Down" id="Shrinkbutton1"></div>
    </div>
    <!-- 面包屑导航  end -->

    <!-- 查询面板 start -->
    <div class="row-fluid" id="Shrinkcontent1">
        <form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12"
              action="<%=basePath %>/CRM/Customer/goQueryAndModify.do" method="POST" autocomplete="off">
            <div class="row">
                <jsp:include page="../../util/NewBranchTree.jsp" flush="true"/>
                <webTag:Text id="customer_id" name="customer_id" value='${rmHelper.returnParams.customer_id}'
                             displayLable="客户代码号"/>
                <webTag:Text id="insured_name" name="insured_name" value='${rmHelper.returnParams.insured_name}'
                             displayLable="客户姓名"/>
            </div>
            <div class="row">
                <webTag:Text id="insured_cid" name="insured_cid" value='${rmHelper.returnParams.insured_cid}'
                             displayLable="客户证件号"/>
                <webTag:Text id="insured_phone" name="insured_phone" value='${rmHelper.returnParams.insured_phone}'
                             displayLable="移动电话"/>
                <div class="control-group span4">
                    <label class="control-label" for="work_nature">客户性质</label>
                    <div class='controls'>
                        <select class="input-medium null" id="insured_company_type"
                                name="insured_company_type">
                            <option value="">---请选择---</option>
                            <option value='1'
                                    <c:if test="${rmHelper.returnMsg.dataTable.insured_company_type=='1'}"> selected="selected"</c:if>>
                                单位
                            </option>
                            <option value='2'
                                    <c:if test="${rmHelper.returnMsg.dataTable.insured_company_type=='2'}"> selected="selected"</c:if>>
                                个人
                            </option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="row" style="text-align:right;">
                <button type="submit" class="btn btn-danger" onClick="defaultQuery();"><i
                        class="icon-search icon-white"></i>查询
                </button>
                <button name="resetting" id="newreset" type="button" class="btn btn-danger">重置</button>
                <a class="btn btn-danger" href="<%=basePath%>/CRM/Customer/goAddCustomer.do"
                   style='text-decoration:none;'><i class="icon-plus icon-white"></i>新增</a>
                <%--  <button type="button" class="btn btn-danger"
                  onclick="importBtn();">导入</button>--%>
                <%-- <a class="btn btn-danger" href='<%=basePath %>/CRM/Customer/daochuCustomer.do?branch_id=${rmHelper.returnParams.branch_id}&customer_type=${rmHelper.returnParams.customer_type}&customer_id=${rmHelper.returnParams.customer_id}&name=${rmHelper.returnParams.name}&gender=${rmHelper.returnParams.gender}'>
                    <i class="icon-download-alt icon-white"></i>导出
                </a> --%>
                <%-- 					    <webTag:Button name="exportCustomer.do" type="button" onClick="importInfo();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="导出"/> --%>
                <%-- 					    <webTag:Ahref path='<%=basePath%>' name="exportCustomer.do" value="导出" iclass_css="icon-download-alt icon-white"  class_css="btn btn-danger"  href="/CRM/Customer/daochuCustomer.do?branch_id=${rmHelper.returnParams.branch_id}&customer_type=${rmHelper.returnParams.customer_type}&customer_id=${rmHelper.returnParams.customer_id}&name=${rmHelper.returnParams.name}&gender=${rmHelper.returnParams.gender}"/>
                 --%>                    </div><!-- /.row -->

        </form>
    </div>
    <!-- 查询面板 end -->

    <!-- 查询结果 start -->
    <div class="overAuto row-fluid">
        <table class="table table-striped table-bordered bootstrap-datatable datatable">
            <thead>
            <tr>
                <th>序号</th>
                <th>操作</th>
                <th>机构代码</th>
                <th>机构名称</th>
                <th>客户代码</th>
                <th>客户姓名</th>
                <th>客户性质</th>
                <th>客户地址</th>
                <th>客户固话</th>
                <th>客户邮箱</th>
                <!-- 	<th>证件类型</th>-->
                <th>证件号码</th>
                <th>移动电话</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="number">
                <tr>
                    <td>${number.index+1}</td>
                    <td>
                        <a class="btn btn-mini btn-dangerLight"
                           href='<%=basePath %>/CRM/Customer/goModify.do?customer_id=${sm.customer_id}'
                           style='text-decoration:none;'><i class="icon-pencil icon-white"></i>修改</a><!-- Edit -->
                    </td>
                    <td>${sm.org_id}</td>
                    <td>${sm.branch_name}</td>
                    <td>${sm.customer_id}</td>
                    <td>${sm.insured_name}</td>
                    <!-- 	<td>
									<c:if  test="${sm.insured_papertype=='S'}">
								身份证
									</c:if>
									<c:if  test="${!sm.insured_papertype.equals('S')}">
								其他
									</c:if>
								</td> -->
                    <td>${sm.insured_company_type}</td>
                    <td>${sm.home_address}</td>
                    <td>${sm.insured_tel}</td>
                    <td>${sm.insured_mailbox}</td>
                    <td>${sm.insured_cid}</td>
                    <td>${sm.insured_phone}</td>

                        <%-- 	<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>'><i class="icon-zoom-in icon-white"></i>行为录入</a><!-- View --> --%>
                        <%--	<a class="btn btn-mini btn-dangerLight" href='<%=basePath %>/CRM/Customer/CustomerModifyView.do?customer_id=${sm.customer_id}&customer_type=${sm.customer_type}'><i class="icon-zoom-in icon-white"></i>明细</a><!-- View -->--%>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pagination pagination-centered">
        <ul id="Pagination"></ul>
    </div>
</div>
<!-- 查询结果 end -->
<!-- 		底部高度填充块 -->
<div class="zeoBottomH90"></div>
<!-- 		底部高度填充块 结束-->
</body>
</html>