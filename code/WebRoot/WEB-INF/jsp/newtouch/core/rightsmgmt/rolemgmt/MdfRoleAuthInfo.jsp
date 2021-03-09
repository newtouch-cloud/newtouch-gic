<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../../core/pub/basecss.jsp"%>
<link rel="stylesheet" href="<%=path%>/compent/ztree/zTreeStyle.css" type="text/css">
</head>
<body id="body" style="height: 750px">
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
                <span class="input-group-addon"> <label for="role_no">角色编码</label> </span> <input type="text" class="form-control" id="role_no" name="role_no" notnull disabled="disabled" /> <span class="not-null">*</span>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_name" class="control-label">角色名称</label> </span> <input type="text" class="form-control" id="role_name" name="role_name" notnull disabled="disabled" /> <span class="not-null">*</span>
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
                <span class="input-group-addon"> <label for="role_type" class="control-label">角色类型</label> </span> <select id="role_type" name="role_type" class="form-control" notnull disabled="disabled">
                  <option value=""></option>
                  <option value="1">系统管理员</option>
                  <option value="2">普通用户</option>
                </select> <span class="not-null">*</span>
                <input type="hidden" id="data_auth_type" name="data_auth_type" value="MENU"/>
              </div>
            </div>
          </div>
          <div class="col-md-12 text-center panel-body">
            <span><button id="xiugai1" onclick="menu2Json('authTree')" name="doMdfRoleAuthInfo.do" type="button" class="btn btn-default btn-xs">修改</button> </span> 
            <span><button id="fanhui" name="goRoleMgmtQueryPage.do" type="button" class="btn btn-default btn-xs">返回</button> </span>
          </div>
          <div class="row">
            <ul id="authTree" class="ztree" style="width:auto;height:auto;margin-left:10px;"></ul>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
<%@ include file="../../../core/pub/basejs.jsp"%>
<script type="text/javascript" src="<%=path%>/compent/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=path%>/compent/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {  
                    enable:true,  
                    idKey: "data_auth_no",  
                    pIdKey: "parent_data_auth",  
                    rootPId: null
                }
			}
		};
	    $(function (){
	    	jsonToPage(${json});
	    	$.fn.zTree.init($("#authTree"), setting, ${json}.retList);
	    });
	    function filter(node, invokeParam) {
	    	if(node.parent_data_auth == invokeParam && node.checked){
	    		return true;
	    	}
	        return false;
	    }
	    function menu2Json(treeId) {
	    	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	    	var nodes = treeObj.getCheckedNodes(true);
	    	var isDisplay = false;
	    	var treeJson = "treelist:[";
	    	for (var i = 0; i < nodes.length; i++) {
	    		//下级被选择的节点不为1个 或者 下级不为半选状态  则显示，否则不显示。
	    		/* if((treeObj.getNodesByFilter(filter, false, nodes[i], nodes[i].data_auth_no).length > 1 || !nodes[i].isParent) && !isDisplay){
	    			isDisplay = true;
	    		} */
	    		isDisplay = true;
	    		treeJson += "{data_auth_no:'" + nodes[i].data_auth_no
	    				+ "',data_auth_child:'false',is_half_check:'";
				treeJson += nodes[i].getCheckStatus().half ? 'false':'';
				treeJson += "',is_display:'"+isDisplay+"'},";
	    				
	    	}
	    	if(treeJson.length > 10){
	    		treeJson = treeJson.substr(0, treeJson.length - 1);
	    	}
	    	treeJson += "]";
			var formId="form1", btnId="xiugai1";
			if (!checkUi(formId)) {
				return;
			}
			var button = $("#"+btnId); 
			button[0].disabled = true;
			var data = formToJsonString(formId,"body");
			var subJson = getTableIdByForm();// 将tab页中的信息加入
			var url = button.attr("name");
			var jsonData = string2json("{" + data + ",funcID:'" + url + "'" + subJson + ",rqstType:'AJAX',"+treeJson+"}");
			$.ajax({
				type : "POST",
				url : url,
				data : jsonData,
				dataType : "json",
				success : function(data) {
					var datamsg = "";
					$.each(data.msg, function(i, value) {
						$.each(value, function(msgKey, msgInfo) {
							datamsg += msgKey + msgInfo + "\n";
						});
					});
					var msgList = new Array;
					var msgInfo = new Object();
					msgInfo.msg = datamsg;
					msgList.push(msgInfo);
					var alertmsg = new Object();
					alertmsg.msgType = "normal";
					alertmsg.msg = msgList;
					alertMsg(alertmsg);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					/* alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
					alert(errorThrown); */
					alert("请不要重复点击修改按钮");
				}
			});
			button[0].disabled = false;
		
	    }
</script>
</html>