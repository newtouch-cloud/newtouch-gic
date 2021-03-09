<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<%@ include file="../../../core/pub/basecss.jsp"%>
<%@ include file="../../../core/pub/basejs.jsp"%>
<link rel="stylesheet" href="<%=basePath%>/compent/ztree/zTreeStyle.css" type="text/css"><!-- ztree -->
<script type="text/javascript" src="<%=basePath%>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
<script type="text/javascript" src="<%=basePath%>/compent/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=basePath%>/compent/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=basePath%>/compent/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript">
var setting = {
		check: {
			enable: true,
			chkboxType:{ "Y" : "ps", "N" : "ps" },
			chkStyle:"checkbox"
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
    		if((treeObj.getNodesByFilter(filter, false, nodes[i], nodes[i].data_auth_no).length > 1 || !nodes[i].isParent) && !isDisplay){
    			isDisplay = true;
    		}
    		//判断此节点有没有子节点，如果没有那判断此节点的父节点是否是全选状态，如果是全选状态，则此节点不存入json
    		//获取此节点是否是父节点
    		var isParent =nodes[i].isParent;
    		//获取此节点的父节点
			var parent_node= nodes[i].getParentNode();
    		if(!isParent){
    			
    			//判断此节点是否有父节点
    			if(parent_node!=null){
    				//判断此节点的父节点是否是全选
        			if(!parent_node.getCheckStatus().half){
        				continue;
            		}
    			}
    		}
    		if(isParent){
    			//判断此节点是否有父节点，如果没有那么此节点是根节点
    			if(parent_node!=null){
    				//如果有父节点，然后判断此节点是否是全勾选
        			if(! nodes[i].getCheckStatus().half){
        				//获取此节点的父节点
            			var parent_node= nodes[i].getParentNode();
            			//如果他的父节点勾选中，那么他就不用选了
            			if(!parent_node.getCheckStatus().half){
            				continue;
            			}
        	    		treeJson += "{data_auth_no:'" + nodes[i].data_auth_no
        	    				+ "',data_auth_child:'false',is_half_check:'";
        				treeJson += nodes[i].getCheckStatus().half ? 'false':'';
        				treeJson += "',is_display:'"+isDisplay+"'},";
        				continue;
        			}
    			}
    		}
    		var halfCheck = nodes[i].getCheckStatus();  
    		treeJson += "{data_auth_no:'" + nodes[i].data_auth_no
    				+ "',data_auth_child:'false',is_half_check:'";
			treeJson += nodes[i].getCheckStatus().half ? 'false':'';
			treeJson += "',is_display:'"+isDisplay+"'},";
    	}
    	if(treeJson.length > 10){
    		treeJson = treeJson.substr(0, treeJson.length - 1);
    	}
    	treeJson += "]";
		var formId="form1", btnId="adduserdeptsave";
		if (!checkUi(formId)) {
			return;
		}
		var button = $("#"+btnId); 
// 		button[0].disabled = true;
		var is4Sub=$('input[name="is4Sub"]:checked').val();
		if(is4Sub == undefined){
			alert("请选择权限是否包含下级！");
			return;
		}
		var data = formToJsonString(formId,"body");
		var subJson = getTableIdByForm();// 将tab页中的信息加入
		var url = button.attr("name");
		var jsonData = string2json("{" + data + ",is4Sub:'"+is4Sub+"',funcID:'" + url + "'" + subJson + ",rqstType:'AJAX',"+treeJson+"}");
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
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
				alert(errorThrown);
			}
		});
		button[0].disabled = false;
    }
</script>
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
          </div>
          <div class="col-md-12 text-center panel-body">
            <span><button id="adduserdeptsave" name="doUserDeptAddPage.do" type="button"  onclick="menu2Json('authTree')" class="btn btn-default btn-xs">保存</button> </span> <span><button id="fanhui" type="button" name="goUserMgmtQueryPage.do" class="btn btn-default btn-xs">返回</button> </span>
          </div>
          
        </form>
        
      </div>
    </div>
  </div>
  <div>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<!-- 权限是否包含下级
  	<select id="is4Sub">
  		<option selected="selected" value="1">包含</option>
  		<option value="0">不包含</option>
 	</select> -->
 	权限是否包含下级：
 	<c:if test="${is4sub=='1'}">
	 	<input type="radio" name="is4Sub" value="1" checked="checked">包含&nbsp;&nbsp;
	 	<input type="radio" name="is4Sub" value="0">不包含
 	</c:if>
 	<c:if test="${is4sub=='0'}">
	 	<input type="radio" name="is4Sub" value="1" >包含&nbsp;&nbsp;
	 	<input type="radio" name="is4Sub" value="0" checked="checked">不包含
 	</c:if>
 	<c:if test="${is4sub!='0' && is4sub!='1'}">
	 	<input type="radio" name="is4Sub" value="1">包含&nbsp;&nbsp;
	 	<input type="radio" name="is4Sub" value="0">不包含
 	</c:if>
  </div>
  <div>
	<ul id="authTree" class="ztree"></ul>
  </div>
</body>
</html>