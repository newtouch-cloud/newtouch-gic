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
        var linkUrl = "<%=basePath%>/PolicyManager/QueryPolicyList.do";
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
        /*导出为excel*/
        function downlodeExcel() {
            console.log(document.queryForm.action);
            var oldAction = document.queryForm.action;
            document.queryForm.action = "<%=basePath%>/PolicyManager/DownloadExcel.do";
            console.log(document.queryForm.action);
            document.queryForm.submit();
            document.queryForm.action = oldAction;
        };

        /*导入保单*/
        function uplodePolicy() {
            window.location.href = "<%=basePath%>/PolicyManager/uplodePolicy.do";
        }

        function reset_1() {
            window.location.href = "<%=basePath%>/PolicyManager/goPolicyList.do";
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
              action="<%=basePath%>/PolicyManager/QueryPolicyList.do"
              method="POST" autocomplete="off">
            <webTag:ReturnMsgTag id="msg" name="msg"
                                 successflag='${rmHelper.successflag}' msg='${rmHelper.msgStr}'/>
            <div class="row">
                <jsp:include page="/WEB-INF/jsp/ca/cacore/util/cpyBranchTree.jsp" flush="true"/>
                <jsp:include page="/WEB-INF/jsp/ca/cacore/util/cpyBranchTreeBranch.jsp" flush="true"/>
                <webTag:Text name="insurance_type_no" id="insurance_type_no"
                             value='${rmHelper.returnParams.insurance_type_no}' displayLable="险种代码"/>
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
                <webTag:Date name="sign_date" id="sign_date"
                             value='${rmHelper.returnParams.sign_date}' displayLable="签单日期"></webTag:Date>
                <webTag:Date name="check_date_L" id="check_date_L"
                             value='${rmHelper.returnParams.check_date_L}' displayLable="核保起期"></webTag:Date>
                <webTag:Date name="check_date_G" id="check_date_G"
                             value='${rmHelper.returnParams.check_date_G}' displayLable="核保止期"></webTag:Date>
            </div>
            <div class="row">
                <webTag:Date name="approval_date_L" id="approval_date_L"
                             value='${rmHelper.returnParams.approval_date_L}' displayLable="批改日期从"></webTag:Date>
                <webTag:Date name="approval_date_G" id="approval_date_G"
                             value='${rmHelper.returnParams.approval_date_G}' displayLable="至"></webTag:Date>


            </div>
            <div class="row">
                <webTag:Date name="ins_validate_L" id="ins_validate_L"
                             value='${rmHelper.returnParams.ins_validate_L}' displayLable="批改生效日期从"></webTag:Date>
                <webTag:Date name="ins_validate_G" id="ins_validate_G"
                             value='${rmHelper.returnParams.ins_validate_G}' displayLable="至"></webTag:Date>

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
                <th>保险公司区县机构代码</th>
                <th>保险公司区县机构名称</th>
                <th>经办人工号</th>
                <th>经办人姓名</th>
                <th>渠道代码</th>
                <th>险类名称</th>
                <th>险类代码</th>
                <th>险种名称</th>
                <th>险种代码</th>
                <th>签单日期</th>
                <th>投保单号</th>
                <th>保单号</th>
                <th>投保人姓名</th>
                <th>被保险人姓名</th>
                <th>被保险人身份证</th>
                <th>被保险人单位性质</th>
                <th>被保险人证件类型</th>
                <th>被保险人地址</th>
                <th>被保险人邮箱</th>
                <th>被保险人手机</th>
                <th>被保险人固话</th>
                <th>车牌号</th>
                <th>车型编码</th>
                <th>车型名称</th>
                <th>车架号（vin码）</th>
                <th>发动机号</th>
                <th>车辆种类</th>
                <th>使用性质</th>
                <th>初登日期</th>
                <th>条款类型</th>
                <th>保额</th>
                <th>保费增值税</th>
                <th>总保费</th>
                <th>净保费（不含税）</th>
                <th>车船税</th>
                <th>核保日期</th>
                <th>起保日期</th>
                <th>终保日期</th>
                <th>跟单手续费比例</th>
                <th>跟单手续费</th>
                <th>新/续保标志</th>
                <th>保单打印日期</th>
                <th>保单生效日期</th>
                <th>保险公司省级机构名称</th>
                <th>保险公司省级机构代码</th>
                <th>保险公司地市机构名称</th>
                <th>保险公司地市机构代码</th>
                <th>中介公司归属人员姓名</th>
                <th>中介公司归属人员编码</th>
                <th>代理人佣金支付比例</th>
                <th>代理人佣金金额</th>
                <th>归属中介公司代码</th>
                <th>批单号</th>
                <th>数量变化</th>
                <th>批改日期</th>
                <th>批改生效日期</th>
                <th>结算日期</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="person" items="${rmHelper.returnMsg.dataList}"
                       varStatus="index">
                <tr>
                    <td class="FixedTd">${index.index+1}</td>
                    <td>${person.area_orgid}</td>
                    <td>${person.area_orgname}</td>
                    <td>${person.responsible_no}</td>
                    <td>${person.responsible_name}</td>
                    <td>${person.channel_no}</td>
                    <td>${person.insurance_class_name}</td>
                    <td>${person.insurance_class_no}</td>
                    <td>${person.insurance_type_name}</td>
                    <td>${person.insurance_type_no}</td>
                    <td>${person.sign_date}</td>
                    <td>${person.insure_no}</td>
                    <td>${person.policy_no}</td>
                    <td>${person.applicant_name}</td>
                    <td>${person.insured_name}</td>
                    <td>${person.insured_cid}</td>
                    <td>${person.Insured_company_type}</td>
                    <td>${person.insured_papertype}</td>
                    <td>${person.insured_add}</td>
                    <td>${person.insured_mailbox}</td>
                    <td>${person.insured_phone}</td>
                    <td>${person.insured_tel}</td>
                    <td>${person.lpn}</td>
                    <td>${person.car_type}</td>
                    <td>${person.car_name}</td>
                    <td>${person.vin}</td>
                    <td>${person.engine_no}</td>
                    <td>${person.car_class}</td>
                    <td>${person.use_type}</td>
                    <td>${person.first_date}</td>
                    <td>${person.clause_type}</td>
                    <td>${person.amount}</td>
                    <td>${person.premium_tax}</td>
                    <td>${person.premium}</td>
                    <td>${person.net_premium}</td>
                    <td>${person.vehicel_tax}</td>
                    <td>${person.check_date}</td>
                    <td>${person.begin_date}</td>
                    <td>${person.end_date}</td>
                    <td>${person.fee_ratio}</td>
                    <td>${person.fee}</td>
                    <td>${person.approval_flag}</td>
                    <td>${person.print_date}</td>
                    <td>${person.ins_validate}</td>
                    <td>${person.province_orgname}</td>
                    <td>${person.province_orgid}</td>
                    <td>${person.city_orgname}</td>
                    <td>${person.city_orgid}</td>
                    <td>${person.owner_name}</td>
                    <td>${person.owner_no}</td>
                    <td>${person.wei_rate}</td>
                    <td>${person.commission}</td>
                    <td>${person.ord_id}</td>
                    <td>${person.endor_no}</td>
                    <td>${person.change_nu}</td>
                    <td>${person.approval_date}</td>
                    <td>${person.ins_validate}</td>
                    <td>${person.settle_time}</td>
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
