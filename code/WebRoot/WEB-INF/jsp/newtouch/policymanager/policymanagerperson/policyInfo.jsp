<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg" %>
<%@page import="com.newtouch.component.c11assistant.ServletHelper" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>新致金保通</title>
    <jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp" flush="true"/>
    <jsp:include page="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" flush="true"/>
    <script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery.form.js"></script>
    <!-- 回跳 -->
    <jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/>
    <!-- 职级联动 -->

    <script>
        <!--重置-->


        var linkUrl = "<%=basePath%>/PolicyManagerPerson/QueryPolicyList.do";
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

        //查询条件中 开始日期 自   到  前后顺序正确性校验
        jQuery.validator.addMethod("checkDate1", function (value, element) {
            var startdate = $("#createDateL").val();
            var enddate = $("#createDateG").val();
            var flag = true;
            if (!isUndefined(startdate) && !isUndefined(enddate)) {
                if (startdate >= enddate) {
                    flag = false;
                }
            }
            if (!flag) {
                return false;
            } else {
                $("label:contains('保险起期必须小于等于止期')").remove();
                return true;
            }
        }, "保险起期必须小于等于止期");


        //查询条件中 开始日期 自   到  前后顺序正确性校验
        jQuery.validator.addMethod("checkDate2", function (value, element) {
            var startdate = $("#check_date_L").val();
            /* var enddate=$("#check_date_G").val();
            var flag = true;
               if(!isUndefined(startdate)&&!isUndefined(enddate)){
                   if(startdate>=enddate){
                       flag=false;
                   }
               } */
            if (!flag) {
                return false;
            } else {
                $("label:contains('核保起期必须小于等于止期')").remove();
                return true;
            }
        }, "核保起期必须小于等于止期");


        function isUndefined(paraValue) {
            if (paraValue == null || paraValue == undefined || paraValue == "") return true;
            return false;
        };

        $(document).ready(function () {
            $("#queryForm").validate({
                rules: {
                    createDateL: {// 生效日期   从 Date 非空
                        checkDate1: []
                    },
                    createDateG: {// 终止日期  从 Date 非空
                        checkDate1: []
                    },
                },
                onkeyup: false
            });
        });


    </script>
    <script type="text/javascript">
        function downlodeExcel() {
            console.log(document.queryForm.action);
            var oldAction = document.queryForm.action;
            document.queryForm.action = "<%=basePath%>/PolicyManagerPerson/DownloadExcel.do";
            console.log(document.queryForm.action);
            document.queryForm.submit();
            document.queryForm.action = oldAction;
        };

        function uplodePolicy() {
            window.location.href = "<%=basePath%>/PolicyManagerPerson/uplodePolicy.do";
        }

        function reset_1() {
            window.location.href = "<%=basePath%>/PolicyManagerPerson/goPolicyList.do";
            //window.location.reload(true);
        }
    </script>


</head>
<body>
<div class="container-fluid">
    <!-- 面包屑导航  start -->
    <div class="dreadcount">
        <span class=mrl14><i class="icon-list icon-red"></i> 保单管理 </span><span class="divider">/</span>
        <span><i class="icon-list icon-red"></i> 保单基本信息查询</span><span class="divider"></span>
        <div class="slideUp_Down" id="Shrinkbutton1"></div>
    </div>
    <!-- 面包屑导航  end -->

    <!-- 查询面板 start -->
    <div class="row-fluid" id="Shrinkcontent1">
        <form id="queryForm" name="queryForm"
              class="form-horizontal alert alert-info fade in span12"
              action="<%=basePath%>/PolicyManagerPerson/QueryPolicyList.do"
              method="POST" autocomplete="off">
            <webTag:ReturnMsgTag id="msg" name="msg"
                                 successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}'/>
            <div class="row">
                <jsp:include page="/WEB-INF/jsp/ca/cacore/util/cpyBranchTree.jsp" flush="true"/>
                <jsp:include page="/WEB-INF/jsp/ca/cacore/util/cpyBranchTreeBranch.jsp" flush="true"/>
                <webTag:Text name="center_risk_id" id="center_risk_id"
                             value='${rmHelper.returnParams.center_risk_id}' displayLable="险种代码"/>
            </div>
            <div class="row">
                <webTag:Text name="policy_no" id="policy_no"
                             value='${rmHelper.returnParams.policy_no}' displayLable="保单号"/>
                <webTag:Date name="createDateL" id="createDateL"
                             value='${rmHelper.returnParams.createDateL}' displayLable="保险起期从"></webTag:Date>
                <webTag:Date name="createDateG" id="createDateG"
                             value='${rmHelper.returnParams.createDateG}' displayLable="至"></webTag:Date>
            </div>
            <div class="row">
                <webTag:Date name="check_date_L" id="check_date_L"
                             value='${rmHelper.returnParams.check_date_L}' displayLable="签单日期"></webTag:Date>
            </div>
            <!-- /.row -->
            <div class="row" style="text-align: right;">
                <button type="submit" class="btn btn-danger"
                        onClick="defaultQuery();">
                    <i class="icon-search icon-white"></i>查询
                </button>
                <button onClick="downlodeExcel();" type="button"
                        class="btn btn-danger">导出
                </button>
                <button onClick="uplodePolicy();" type="button"
                        class="btn btn-danger">导入保单
                </button>
                <button name="resetting" onClick="reset_1();" type="button"
                        class="btn btn-danger">重置
                </button>

            </div>
            <!-- /.row -->
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
                <th>保险公司省级机构</th>
                <th>保险公司省级机构代码</th>
                <th>保险公司地市机构</th>
                <th>保险公司地市机构代码</th>
                <th>保险公司县支公司</th>
                <th>保险公司县支公司代码</th>
                <th>中介公司机构名称</th>
                <th>中介公司机构代码</th>
                <th>保单号</th>
                <th>产品代码</th>
                <th>产品名称</th>
                <th>产品类型</th>
                <th>长短险标志</th>
                <th>新单续收标识</th>
                <th>投保人姓名</th>
                <th>投保人身份证号</th>
                <th>被保险人</th>
                <th>被保险险人身份证号</th>
                <th>保费收入(元)</th>
                <th>保额(元)</th>
                <th>保险期类型</th>
                <th>保险期间</th>
                <th>缴费方式</th>
                <th>付款方式名称</th>
                <th>缴费年限</th>
                <th>保单年度</th>
                <th>佣金费率</th>
                <th>佣金(元)</th>
                <th>业务生效日期</th>
                <th>承保日期</th>
                <th>客户签收日期</th>
                <th>回执核销日期</th>
                <th>回访成功日期</th>
                <th>中介公司归属人员姓名</th>
                <th>中介公司归属人员编码</th>
                <th>代理人佣金支付比例</th>
                <th>代理人佣金金额</th>
                <th>批单号</th>
                <th>批改日期</th>
                <th>批改生效日期</th>
                <th>数量变化</th>
                <th>结算时间</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="person" items="${rmHelper.returnMsg.dataList}"
                       varStatus="index">
                <tr>
                    <td class="FixedTd">${index.index+1}</td>
                    <td>${person.province_orgname       }</td>
                    <td>${person.province_orgid         }</td>
                    <td>${person.city_orgname           }</td>
                    <td>${person.city_orgid             }</td>
                    <td>${person.area_orgname           }</td>
                    <td>${person.area_orgid             }</td>
                    <td>${person.sale_org_name          }</td>
                    <td>${person.sale_org_id            }</td>
                    <td>${person.policy_no              }</td>
                    <td>${person.center_risk_id         }</td>
                    <td>${person.center_risk_name       }</td>
                    <td>${person.center_risk_type       }</td>
                    <td>${person.long_risk_flag         }</td>
                    <td>${person.renew_flag             }</td>
                    <td>${person.applicant_name         }</td>
                    <td>${person.applicant_id           }</td>
                    <td>${person.insured_name           }</td>
                    <td>${person.insured_id             }</td>
                    <td>${person.premium                }</td>
                    <td>${person.amount                 }</td>
                    <td>${person.insured_during_flag    }</td>
                    <td>${person.insured_during         }</td>
                    <td>${person.pay_fee_type           }</td>
                    <td>${person.pay_type               }</td>
                    <td>${person.pay_fee_year           }</td>
                    <td>${person.risk_year              }</td>
                    <td>${person.fee_ratio              }</td>
                    <td>${person.new_paper_fee          }</td>
                    <td>${person.ins_validate           }</td>
                    <td>${person.insure_date            }</td>
                    <td>${person.paper_date             }</td>
                    <td>${person.cancel_date            }</td>
                    <td>${person.visit_date             }</td>
                    <td>${person.owner_name             }</td>
                    <td>${person.owner_no               }</td>
                    <td>${person.agent_fee_rate         }</td>
                    <td>${person.agent_fee              }</td>
                    <td>${person.endor_no               }</td>
                    <td>${person.endor_date             }</td>
                    <td>${person.endor_valid            }</td>
                    <td>${person.change_nu              }</td>
                    <td>${person.settled_time              }</td>
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
<!--底部高度填充块 -->
<div class="zeoBottomH90"></div>
<!--底部高度填充块 结束-->
</body>
</html>
