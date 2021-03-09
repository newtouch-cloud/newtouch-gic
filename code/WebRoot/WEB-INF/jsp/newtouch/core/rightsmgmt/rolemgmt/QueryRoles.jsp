<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../../core/pub/basecss.jsp"%>
<%@ include file="../../../core/pub/basejs.jsp"%>
</head>
<!-- <script type="text/javascript">
function saveData11(){
	document.getElementById("form2").submit();
}
</script> -->
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
                <span class="input-group-addon"> <label for="role_no">角色编码</label> </span> 
                <input type="text" class="form-control" id="role_no" name="role_no"/>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_name" class="control-label">角色名称</label> </span> 
                <input type="text" class="form-control" id="role_name" name="role_name"/>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_type" class="control-label">角色类型</label> </span> 
                <select id="role_type" name="role_type" class="form-control">
                  <option value=""></option>
                  <option value="1">系统管理员</option>
                  <option value="2">普通用户</option>
                </select>
              </div>
            </div>
          </div>
          <div class="col-xs-12 text-center panel-body">
            <span><n:Button id="chaxun" name="doRoleMgmtQueryPage.do" type="button" classCss="btn btn-default btn-xs" value="查询"/></span> 
            <span><button id="chongzhi" type="button" class="btn btn-default btn-xs">重置</button> </span>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- 查询结果 -->
   <div class="panel panel-default">
    <div class="panel-heading">
    <%-- <span> <n:Button id="xinzenginit" name= "goRoleMgmtAddPage.do" type="button" classCss="btn btn-default btn-xs" value="新增"/> </span> --%>
     <span> <button id="xinzenginit"  type="button" classCss="btn btn-default btn-xs" value="新增">新增</button></span>
      <span><n:Button name="doDeleteRole.do" type="button" onClick='submitBox4id(this,"grid")' classCss="btn btn-default btn-xs" value="删除"/> </span>
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
              <th abbr="role_typen" class="pager">角色类型</th>
              <th abbr="user_info" class="pager">用户信息</th>
              <th abbr="menu_info" class="pager">菜单权限</th>
              <th axis="button" class="pager operate">修改 
              	<button classCss="btn btn-default btn-xs" name="goMdfRoleBaseInfo.do" type="button"  style="display: none" value="基本信息" >基本信息</button> <!-- name="goMdfRoleBaseInfo.do" --> 
              	<button classCss="btn btn-default btn-xs" name="goMdfRoleAuthInfo.do" type="button"  style="display: none" value="菜单信息" >菜单信息</button> <!-- name="goMdfRoleAuthInfo.do" -->
              	<button classCss="btn btn-default btn-xs" name="goMdfRoleUserInfo.do" type="button"  style="display: none" value="用户信息" >用户信息</button> <!-- name="goMdfRoleUserInfo.do" -->
              </th>
              <th axis="hidden" abbr="role_no" class="hidden"></th>
            </tr>
          </thead>
        </table>
      </div>
      <%@ include file="/WEB-INF/jsp/newtouch/core/pub/pagination.jsp"%>
    </div>
  </div>
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    	<div class="modal-dialog" role="document">
    		<form id="addform" method="post" action="">
    			<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>	
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag name="seq_id" id="seq_id" value="${rmHelper.returnParams.seq_id}"></webTag:HiddenInputTag>
    			<div class="modal-content">
             		<div class="modal-header">
             			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    	<h4 class="modal-title" id="myModalLabel">新增</h4>
					</div>
                	<div class="modal-body">
                    	<div class="form-group">
                        	<label for="role_no">角色编码</label>         
                        	<input type="text" class="form-control" id="role_no1" name="role_no" notnull />
            				<span class="input-group-btn  not-null">*</span>
            			</div>
                    	<div class="form-group">
                    		 <label for="role_name" class="control-label">角色名称</label>
           					 <input type="text" class="form-control" id="role_name1" name="role_name" notnull/>
           				 	 <span class="input-group-btn  not-null">*</span>
                    	</div>
                    	<div class="form-group">
                    		<label for="role_type" class="control-label">角色类型</label>
          						<select id="role_type1" name="role_type" class="form-control" notnull>
              						<option value=""></option>
              						<option value="1">系统管理员</option>
              						<option value="2">普通用户</option>
            					</select>
            				<span class="input-group-btn  not-null">*</span>
                     	</div>
                 	</div>
                 	<div class="modal-footer">
                 		<%-- <span><button id="xinzeng1" type="button" class="btn btn-default btn-xs"><a href="<%=basePath %>/doRoleMgmtAddRole.do" style="text-decoration:none;color:#383838;">保存</a></button> </span>  --%>
                 		<span><button id="xinzeng1" type="button" onclick="saveData11();" class="btn btn-default btn-xs">保存</button> </span> 
        				<span><button id="chongzhi1" type="button" class="btn btn-default btn-xs">重置</button> </span> 
        				<span><button id="fanhui1"  type="button" class="btn btn-default btn-xs"><a href="<%=basePath %>/goRoleMgmtQueryPage.do" style="text-decoration:none;color:#383838;">返回</a></button> </span>
                 	</div>
            	</div>
         	</form>
         </div>
	</div>
</body>

<script type="text/javascript">
    $(function (){fillGrid(${json});});
   	$("#xinzenginit").click(function () {
    	//debugger;
	    $("#myModalLabel").text("新增");
  	    $('#myModal').modal();
   	 });
    $("#chongzhi1").click(function(){
		resetForm(this, "addform");
	});
    
    function saveData11(){
    	var role_no = document.getElementById("role_no1").value;
    	var role_name = document.getElementById("role_name1").value;
    	var role_type = document.getElementById("role_type1").value;
    	$.ajax({
    		type: 'GET',
    		url: '<%=basePath %>/doRoleMgmtAddRole.do',
    		data:{
    			"role_no": role_no,
    			"role_name": role_name,
    			"role_type": role_type
    		},
    		dataType: 'text',
    		success: function(data){
    			alert(data);
    		},
    		error: function(){
    			alert("保存失败!");
    		}
    	});
    }
</script>
<!-- <script type="text/javascript">
    $(function (){jsonToPage(${json});});
</script> -->
</html>