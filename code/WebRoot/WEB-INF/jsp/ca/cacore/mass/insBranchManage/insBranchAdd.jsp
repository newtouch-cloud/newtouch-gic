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
    <jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
    <jsp:include page="../../manage/branch/province.jsp" flush="true"/>
    <script>
        <%-- jQuery.validator.addMethod("checkName", function(value, element) {
          var flag=true;
          var branch_name = $("#branch_cpyname").val();
          var seq_id ="0";
          debugger;
          if(!isUndefined(branch_name)){
              $.ajax({
                  url:"<%=basePath %>/sms/InsBranchManage/checkInsbName.do",
                  type:"post",
                  async: false,
                  data:{"branch_name":branch_name,"seq_id":seq_id},
                  success:function(data){
                     if (data == "true") {
                       flag=true;
                     } else {
                       flag=false;
                     }
                  }
              });
           }
              if (!flag) {
                  $("label:contains('保险公司名称已经存在')").remove();
             } else {
                  return flag;
              }
          }, "保险公司名称已经存在"); --%>

        //手机号校验以1开头，第二位可能是3/4/5/7/8等的任意一个
        jQuery.validator.addMethod("checkphone", function(telephone, element) {
            var telephone =  $("#telephone").val();
            var reg = /^1(3|4|5|7|8)\d{9}$/;
            if(telephone != null && telephone != ""){
                if(reg.test(telephone) != true){
                    return false;
                }else{
                    return true;
                }
            }
            return true;
        }, "手机号格式不正确");
        jQuery.validator.addMethod("checkPost", function(zip, element) {
            var zip =  $("#zip").val();
            var reg = /^[0-9][0-9]{5}$/;
            if(zip != null && zip != ""){
                if(reg.test(zip) != true){
                    return false;
                }else{
                    return true;
                }
            }
            return true;
        }, "邮政编码格式不正确");
        jQuery.validator.addMethod("checkFax", function(fax, element) {
            var fax =  $("#fax").val();
            var reg = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
            if(fax != null && fax!= ""){
                if(reg.test(fax) != true){
                    return false;
                }else{
                    return true;
                }
            }
            return true;
        }, "传真格式不正确");

        $(document).ready(function() {
            $("#mainForm").validate({
                rules : {
                    branch_cpyname : {
                        required : true,
                        checkName : [],
                        maxlength : 20
                    },
                    branch_name:{
                        required : true
                    },
                    delegate:{
                        minlength : 2,
                        maxlength : 25
                    },
                    address:{
                        maxlength : 100
                    },
                    fax:{
                        maxlength : 30,
                        checkFax : []
                    },
                    remark:{
                        maxlength : 500
                    },
                    status:{
                        required : true
                    },
                    found_date:{
                        required : true
                    },
                    zip : {
                        required : false,
                        minlength :6,
                        maxlength : 6,
                        checkPost : []
                    },
                    telephone:{
                        maxlength : 11,
                        checkphone : []
                    }
                },
                onkeyup:false

            });
        });

        /*
         *判断参数是否为未定义或null,如未定义或为null返回true
         *@param paraValue 待处理字符串
         *@return true或者false
         */
        function isUndefined(paraValue){
            if(paraValue==null||paraValue==undefined||paraValue=="") return true;
            return false;
        }


    </script>
</head>
<body>
<div class="container-fluid">
    <div class="dreadcount">
        <span class=mrl14><i class="icon-list icon-red"></i> 保险公司管理 </span><span class="divider">/</span>
        <span><i class="icon-list icon-red"></i> 保险公司管理 </span><span class="divider">/</span>
        <span><i class="icon-list icon-red"></i> 新增保险公司机构</span>
    </div>
    <%-- 数据区 start--%>
    <div class="row-fluid">
        <form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/mass/InsBranchManage/insBranchAdd.do" method="POST" autocomplete="off">
            <div id="dialog" title="提示信息" style="display:none">
                <center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
            </div>
            <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
            <webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
            <webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
            <webTag:HiddenInputTag id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}' displayLable="seq_id:"/>

            <div class="row">
                <webTag:Text id="branch_parentid" name="branch_parentid" value='${rmHelper.returnParams.branch_parentid}' displayLable="上级保险公司代码" isdisplay="true" readonly="true"/>
                <webTag:Text id="branch_parentname" name="branch_parentname" value='${rmHelper.returnParams.branch_parentname}' displayLable="上级保险公司名称" isdisplay="true" readonly="true"/>
                <!--   新增保险公司分类  zddxiu -->
                <webTag:Text id="bleng_branchname" name="bleng_branchname" value='${rmHelper.returnParams.bleng_branchname}' displayLable="中介公司机构"  readonly="true"/>
                <webTag:HiddenInputTag id="bleng_branchid" name="bleng_branchid" value='${rmHelper.returnParams.bleng_branchid}' displayLable="中介公司机构" />
                <%--                          <jsp:include page="../../util/NewBranchTreeZ.jsp" flush="true" />--%>



            </div>

            <div class="row">
                <webTag:Text id="cpy_branch_level" name="cpy_branch_level" value='${rmHelper.returnParams.cpy_branch_level}' displayLable="新增保险公司等级" isdisplay="true" readonly="true"/>

                <%--                          <c:if test="${rmHelper.returnParams.bleng_branchname==null or rmHelper.returnParams.bleng_branchname==''}">--%>
                <%--                                <jsp:include page="../../util/NewBranchTreeZ2.jsp" flush="true" />  <!-- zdd20190722 -->--%>
                <%--                         </c:if>--%>
                <%--                           <c:if test="${rmHelper.returnParams.bleng_branchname!=null and rmHelper.returnParams.bleng_branchname!=''}">--%>
                <webTag:Select    id="branch_sort"  name="branch_sort"  displayLable="新增保险公司分类" isdisplay="true">
                    <c:forEach var="model" items="${models}">
                        <c:if test="${rmHelper.returnParams.branch_sort!=model.branch_sort}">
                            <option  value='${model.branch_sort}'>${model.branch_sortname}</option>
                        </c:if>
                        <c:if test="${rmHelper.returnParams.branch_sort==model.branch_sort}">
                            <option selected="selected" value='${model.branch_sort}'>${model.branch_sortname}</option>
                        </c:if>
                    </c:forEach>
                </webTag:Select>
                <%--                          </c:if>                                                     --%>
                <webTag:Text id="branch_cpyname" name="branch_cpyname" value='${rmHelper.returnParams.branch_cpyname}' displayLable="保险公司名称" isdisplay="true"/>


            </div>
            <div class="row">

                <webTag:Select    id="province_code"  name="province_code"  displayLable="省" >
                    <option selected="selected" value='${rmHelper.returnParams.province_code}'></option>
                </webTag:Select>
                <webTag:Select    id="city_code"  name="city_code"  displayLable="市" >
                    <option selected="selected" value='${rmHelper.returnParams.city_code}'></option>
                </webTag:Select>
                <webTag:Select    id="area_code"  name="area_code"  displayLable="县" >
                    <option selected="selected" value='${rmHelper.returnParams.area_code}'></option>
                </webTag:Select>
            </div>
            <div class="row">
                <webTag:Text id="branch_abbr" name="branch_abbr" value='${rmHelper.returnParams.branch_abbr}' displayLable="保险公司简称" />
                <webTag:DynamicSelectTag src="statusSelect" name="status" id="status"  displayLable="状态" value='${rmHelper.returnParams.status}' isdisplay="true" ></webTag:DynamicSelectTag>
                <webTag:Date id="found_date" name="found_date" value='${rmHelper.returnParams.found_date}' displayLable="成立日期" /><!-- isdisplay="true" -->


            </div>

            <div class="row">
                <webTag:Text id="delegate" name="delegate" value='${rmHelper.returnParams.delegate}' displayLable="法人代表:" />
                <webTag:Text id="address" name="address" value='${rmHelper.returnParams.address}' displayLable="联系地址:" />
                <webTag:Text id="zip" name="zip" value='${rmHelper.returnParams.zip}' displayLable="邮政编码:" />


            </div>

            <div class="row">
                <webTag:Text id="fax" name="fax" value='${rmHelper.returnParams.fax}' displayLable="公司传真:" />
                <webTag:Text id="telephone" name="telephone" value='${rmHelper.returnParams.telephone}' displayLable="联系电话:" />

            </div>
            <%--                      <div class="row">--%>
            <%--                       <webTag:TextareaTag name="remark" rows="5" id="remark" value='${rmHelper.returnParams.remark}' displayLable="备注信息:" />--%>
            <%--                      </div>--%>
            <div class="row" style="text-align:right;">
                <button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
                <a id="backButton" class="btn btn-danger" href="<%=basePath %>/mass/InsBranchManage/toInsBranchQuery.do"><i class="icon-share-alt icon-white"></i>返回</a>
            </div>
        </form>
    </div>
</div>
<div class="zeoBottomH90"></div>
</body>
</html>
