<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="../../../core/pub/basecss.jsp"%>
<%@ include file="../../../core/pub/basejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body id="body" style="height: 750px">
  <!-- 查询条件 -->
  <div class="panel panel-default">
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="panel-body">
        <form id="form1" method="post" action="">
          <div class="row">
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="opt_no">当前用户代码</label> </span> <input type="text" class="form-control" id="opt_no" name="opt_no" value="${opt_no}" readonly="readonly"/>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="opt_name" class="control-label">当前用户名称</label> </span> <input type="text" class="form-control" id="opt_name" name="opt_name" value="${opt_name}" readonly="readonly"/>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_no">&nbsp;&nbsp;&nbsp;&nbsp;角色编码&nbsp;&nbsp;&nbsp;&nbsp;</label> </span> <input type="text" class="form-control" id="role_no" name="role_no"/>
              </div>
            </div>
          </div>
          <div class="row">  
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_name" class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;角色名称&nbsp;&nbsp;&nbsp;&nbsp;</label> </span> <input type="text" class="form-control" id="role_name" name="role_name"/>
              </div>
            </div>
          </div>
          <div class="col-md-12 text-center panel-body">
            <span><button id="chaxun" name="doUserRoleQueryPage.do" type="button" class="btn btn-default btn-xs">查询</button> </span> <span><button id="fanhui" type="button" name="goUserMgmtQueryPage.do" class="btn btn-default btn-xs">返回</button> </span> 
          </div>
       
  <!-- 查询结果 -->
  <div class="panel panel-default">
    <div class="panel-heading">
      <span><button type="button" name="goUserRoleAdd.do" class="btn btn-default btn-xs" onclick="submitBox4id(this,'grid')">保存</button> </span> 
    </div>
    <div class="panel-body">
      <div class="table-responsive">
        <table id="grid" class="table table-bordered table-hover table-condensed">
          <thead>
            <tr>
              <th abbr="role_no" axis="checkbox" class="pager" title="选中本页全部"><input onclick="checkAll(this)" type='checkbox'  name="box"/>全选</th>
              <th abbr="rn" class="pager">序号</th>
              <th abbr="role_no" class="pager">角色编码</th>
              <th abbr="role_name" class="pager">角色名称</th>
              <th abbr="opt_number" class="pager">用户信息</th>
              <th abbr="menu_info" class="pager">菜单权限</th>
              <th axis="hidden" abbr="role_no" class="hidden"></th>
              <th axis="hidden" abbr="opt_no" class="hidden"></th>
            </tr>
          </thead>
        </table>
      </div>
      <%@ include file="/WEB-INF/jsp/newtouch/core/pub/pagination.jsp"%>
    </div>
  </div>
   </form>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
    $(function (){
    	jsonToPage(${json});
    });
</script>
</html>