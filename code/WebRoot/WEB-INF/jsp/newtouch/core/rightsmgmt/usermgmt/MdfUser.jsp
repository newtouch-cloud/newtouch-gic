<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String orgLevel = "all";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../../core/pub/basecss.jsp"%>
<%@ include file="../../../core/pub/basejs.jsp"%>
<script type="text/javascript">
function checkEmail(){
	var emailTest = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	var email = $("#opt_mail").val();
	if(email!=""&&!emailTest.test(email)){
		alert("邮箱格式错误");
		$("#opt_mail").val("");
		$("#opt_mail").focus();
	}
}
</script>
</head>
<body id="body" style="height: 750px">
<div id="collapseOne" class="panel-collapse collapse in">
      <div class="panel-body">
        <form id="form1" method="post" action="">
        <input type="hidden" id="serno" name="serno">
          <div class="row">
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="did">组织结构</label> </span>
                <input type="hidden" id="dept_no" name="dept_no" class="form-control" />
                <input id="dept_name" name="dept_name" class="form-control" onclick="getOrg('<%=orgLevel %>','all')" notnull disabled="disabled"/>
                <span class="not-null">*</span>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="opt_no" class="control-label">用户代码</label> </span> 
                <input type="text" class="form-control" id="opt_no" name="opt_no" notnull disabled="disabled"/>
                <span class="not-null">*</span>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="opt_name" class="control-label">用户姓名</label> </span> 
                <input type="text" class="form-control" id="opt_name" name="opt_name" notnull/>
                <span class="not-null">*</span>
              </div>
            </div>
          </div>
          <div class="row">  
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="opt_sex" class="control-label"> 用户性别</label> </span> 
                <n:Select classPath="selectServiceImp" style="form-control"
	                      value="" id="opt_sex" name="opt_sex" queryId="gender" notnull="true"/>
                <span class="not-null">*</span>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="opt_type" class="control-label">用户类型</label> </span> 
                <n:Select classPath="selectServiceImp" style="form-control"
	                      value="" id="opt_type" name="opt_type" queryId="opt_type" notnull="true"/>
                <span class="not-null">*</span>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="opt_mail" class="control-label">电子邮件</label></span> 
                <input type="text" class="form-control" id="opt_mail" name="opt_mail" onblur="checkEmail()"/>
                <span>&nbsp;&nbsp;</span>
              </div>
            </div>
          </div>
          <div class="col-md-12 text-center panel-body">
            <span><button id="xiugai" name="doUserMgmtMdfUser.do" type="button" class="btn btn-default btn-xs">保存</button> </span> <span><button id="fanhui" type="button" name="goUserMgmtQueryPage.do" class="btn btn-default btn-xs">返回</button> </span>
          </div>
        </form>
      </div>
    </div>

</body>
<script type="text/javascript">
    $(function (){jsonToPage(${json});});
</script>
</html>