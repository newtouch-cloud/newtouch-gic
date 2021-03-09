<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../../core/pub/basecss.jsp"%>
</head>
<body id="body" style="height: 750px">
  <!-- 查询条件 -->
  <div class="panel panel-default">
    <div class="panel-heading">
      <div class="panel-title">
        查询条件 <span class="pull-right"><a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> 收缩</a> </span>
      </div>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="panel-body">
        <form id="form1" method="post" action="">
          <div class="row">
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_no">角色编码</label> </span> <input type="text" class="form-control" id="role_no" name="role_no" disabled="disabled" />
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_name" class="control-label">角色名称</label> </span> 
                <input type="text" class="form-control" id="role_name" name="role_name" disabled="disabled" />
                <input type="hidden" class="form-control" id="type" name="type"   value="role" />
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_type" class="control-label">角色类型</label> </span> <select id="role_type" name="role_type" class="form-control" disabled="disabled">
                  <option value=""></option>
                  <option value="1">系统管理员</option>
                  <option value="2">普通用户</option>
                </select>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="opt_no">用户编码</label> </span> <input type="text" class="form-control" id="opt_no" name="opt_no" />
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="opt_name" class="control-label">用户名称</label> </span> <input type="text" class="form-control" id="opt_name" name="opt_name" />
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="opt_type" class="control-label">用户类型</label> </span> <select id="opt_type" name="opt_type" class="form-control">
                  <option value=""></option>
                  <option value="1">系统管理员</option>
                  <option value="2">普通用户</option>
                </select>
              </div>
            </div>
          </div>
          <div class="col-xs-12 text-center panel-body">
            <span><button id="chaxun" name="doMdfRoleUserInfoQuery.do" type="button" class="btn btn-default btn-xs">查询</button> 
            <span><button id="chongzhi1" type="button" class="btn btn-default btn-xs">重置</button> </span> 
            </span> <span><button id="fanhui" name="goRoleMgmtQueryPage.do" type="button" class="btn btn-default btn-xs">返回</button> </span>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- 查询结果 -->
  <div class="panel panel-default">
    <div class="panel-heading">
      <span><button name="addMdfRoleUserInfo.do" type="button" class="btn btn-default btn-xs" id="" onclick="submitBox4id(this,'grid')">保存</button> </span>
    </div>
    <div class="panel-body">
      <div class="table-responsive">
        <table id="grid" class="table table-bordered table-hover table-condensed">
          <thead>
            <tr>
              <th abbr="role_no" axis="checkbox" class="pager" title="选中本页全部"><input onclick="checkAll(this)" type='checkbox'  name="box"/>全选</th>
              <th abbr="rn" class="pager">序号</th>
              <th abbr="opt_no" class="pager">用户编码</th>
              <th abbr="opt_name" class="pager">用户名称</th>
              <th abbr="dept_name" class="pager">所属机构</th>
              <th axis="hidden" abbr="opt_no" class="hidden"></th>
              <th axis="hidden" abbr="role_no" class="hidden" ></th>
            </tr>
          </thead>
        </table>
      </div>
      <%@ include file="/WEB-INF/jsp/newtouch/core/pub/pagination.jsp"%>
    </div>
  </div>
</body>
<%@ include file="../../../core/pub/basejs.jsp"%>
<script type="text/javascript">
    $(function (){jsonToPage(${json});});
    $("#chongzhi1").click(function(){
		resetForm(this, "form1");
	});
</script>
</html>