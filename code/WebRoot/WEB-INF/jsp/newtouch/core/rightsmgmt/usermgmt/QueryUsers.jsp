<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../../core/pub/basecss.jsp"%>
<%@ include file="../../../core/pub/basejs.jsp"%>
<script type="text/javascript">
</script>
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
          	<jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree6.jsp" flush="true" />
            <div class="col-xs-4">
              <div class="input-group">
              	<span class="input-group-addon"> <label for="user_no" class="control-label">用户代码</label> </span> <input type="text" class="form-control" id="user_no" name='user_no' />
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
              	<span class="input-group-addon"> <label for="user_name">用户姓名</label> </span> <input type="text" class="form-control" id="user_name" name='user_name'/>
              </div>
            </div>
          </div>
          <div class="row">  
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="user_type" class="control-label"> 用户类型</label> </span> 
                <select id="user_type" name="user_type" class="form-control">
                  <option value=""></option>
                  <option value="1">系统管理员</option>
                  <option value="2">普通用户</option>
                </select>
            </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="status" class="control-label">用户状态</label> </span> 
                <select id="status" name="status" class="form-control">
                  <option value=""></option>
                  <option value="1">有效</option>
                  <option value="0">无效</option>
                </select>
              </div>
            </div>
          </div>
          <div class="col-md-12 text-center panel-body">
            <span><n:Button id="chaxun" name="doUserMgmtQueryPage.do" type="button" classCss="btn btn-default btn-xs" value="查询"/></span> 
            <span><button id="chongzhi" type="button" class="btn btn-default btn-xs">重置</button> </span>
          </div>
        </form>
       </div>
      </div>
    </div>
  </div>
  <!-- 查询结果 -->
  <div class="panel panel-default">
    <div class="panel-heading">
      <span>
      	<a id="backButton" class="btn btn-default btn-xs"
						href="<%=basePath %>/goUserMgmtAddPage.do"><i class="icon-share-alt icon-white"></i>添加</a>
     <%--  <n:Button type="button" id="xinzenginit" name="goUserMgmtAddPage.do" classCss="btn btn-default btn-xs" value="添加"/></span> --%>
     <c:if test="${flag=='1'}">
      <span><button type="button" name="doUserDelete.do" onclick="submitBox4id(this,'grid')"  class="btn btn-default btn-xs">删除</button> </span>
     </c:if> 
    </div>
    <div class="panel-body">
      <div class="table-responsive">
        <table id="grid" class="table table-bordered table-hover table-condensed">
          <thead>
            <tr>
              <th abbr="opt_no2" axis="checkbox" class="pager" title="选中本页全部" ><input onclick="checkAll(this)" type='checkbox' name="box"/>全选</th>
              <th abbr="rn" class="pager">序号</th>
              <th abbr="opt_no"  class="pager" >用户代码</th>
              <th abbr="opt_name" class="pager">用户姓名</th>
              <th abbr="opt_type" class="pager">用户类型</th>
              <th abbr="operauth" class="pager">可操作机构</th>
              <th abbr="operrole" class="pager">可授权角色</th>
              <th abbr="opt_status" class="pager">用户状态</th>
              <th abbr="dept_no" class="pager">所属组织编码</th>
              <th abbr="branch_name" class="pager">所属组织名称</th>
              <th abbr="opt_phone" class="pager">手机号码</th>
              <th abbr="opt_mail" class="pager">邮箱</th>
              <th abbr="opt_sex" class="pager">性别</th>
              <%-- <th>${this}</th> --%>
              <%-- <th >${json.relist[this].opt_status}</th> --%>
<!--               <th abbr="ins_usercode" class="pager">核心登陆代码</th>
 -->              <th axis="button" class="pager">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 			  <%-- <c:if test="th[abbr='opt_status'].value() == '有效'"> --%>
 				<n:Button classCss="btn btn-default btn-xs" name="goUpdateOptStatus.do" type="button" style="display: none" value="失效/复效" />
 			  <%-- </c:if> --%>
 			  <%-- <c:if test="$  == '无效'"> --%>
 				<%-- <n:Button classCss="btn btn-default btn-xs" name="goUpdateOptStatus.do" type="button" style="display: none" value="复效" /> --%>
              <%-- </c:if> --%>
                <n:Button classCss="btn btn-default btn-xs" name="goUserMdfPage.do" type="button" style="display: none" value="修改" />
              	<n:Button classCss="btn btn-default btn-xs" name="goUserRoleAddPage.do" type="button" style="display: none" value="添加角色" />
              	<n:Button classCss="btn btn-default btn-xs" name="goUserDeptAddPage.do" type="button" style="display: none" value="添加组织" />    
              	<n:Button classCss="btn btn-default btn-xs" name="goResetUserPassword.do" type="button" style="display: none" value="修改密码" /> 
              	<n:Button classCss="btn btn-default btn-xs" name="goSelectSuperior.do" type="button" style="display: none" value="选择上级" />
              	       
<!--               	<button class="btn btn-default btn-xs" name="doQueryUserMapping.do" type="button" style="display: none">绑定核心用户</button>              	
 -->              </th>
              <th axis="hidden" abbr="opt_no" class="hidden"></th>
              <th axis="hidden" abbr="opt_name" class="hidden"></th>
              <th axis="hidden" abbr="opt_status" class="hidden"></th>
              <th axis="hidden" abbr="dept_no" class="hidden"></th>
              <th axis="hidden" abbr="branch_name" class="hidden"></th>
            </tr>
          </thead>
        </table>
      </div>
      <%@ include file="/WEB-INF/jsp/newtouch/core/pub/pagination.jsp"%>
    </div>
  </div>
</body>
<script type="text/javascript">
    $(function (){fillGrid(${json});});
</script>
</html>