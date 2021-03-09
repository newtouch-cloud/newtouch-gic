<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../../core/pub/basecss.jsp"%>
<%@ include file="../../../core/pub/basejs.jsp"%>
</head>
<body id="body" style="height: 750px;width: 100%;">
  <div class="panel panel-default">
    <div class="panel-heading">
      <div class="panel-title">
        修改角色 <span class="pull-right"><a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> 收缩</a> </span>
      </div>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="panel-body">
        <form id="form1" method="post" action="">
          <div class="row">
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_no">角色编码</label> </span> 
                <input type="text" class="form-control" id="role_no" name="role_no" notnull disabled="disabled" /> 
                <span class="not-null">*</span>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_name" class="control-label">角色名称</label> </span> 
                <input type="text" class="form-control" id="role_name" name="role_name" notnull /> <span class="not-null">*</span>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_type" class="control-label">角色类型</label> </span> 
                <select id="role_type" name="role_type" class="form-control" notnull>
                  <option value=""></option>
                  <option value="1">系统管理员</option>
                  <option value="2">普通用户</option>
                </select> <span class="not-null">*</span>
              </div>
            </div>
          </div>
          <div class="col-md-12 text-center panel-body">
            <span><button id="xiugai" name="doMdfRoleBaseInfo.do" type="button" class="btn btn-default btn-xs">修改</button> </span> 
            <span><button id="fanhui" name="goRoleMgmtQueryPage.do" type="button" class="btn btn-default btn-xs">返回</button> </span>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>

<script type="text/javascript">
    $(function (){jsonToPage(${json});});
</script>
</html>