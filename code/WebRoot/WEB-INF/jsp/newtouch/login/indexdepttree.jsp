<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.newtouch.core.quanxianguanli.pojo.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<script language="JavaScript">
    
    var setting = {
        data: {  
            simpleData: {
                enable:true,  
                idKey: "data_auth_no",  
                pIdKey: "parent_data_auth",  
                rootPId: "" ,
                nameKey:"name"
            }  
        },  
        // 回调函数  
        callback : {  
            onClick : function(event, treeId, treeNode, clickFlag) {
                // 设置上级窗口名称
//             	window.parent.document.getElementById("dept_name").innerHTML=treeNode.name;
                // 更新USER对象
				$.ajax({
					type : "POST",
					url : "setoptdept.do?funcID=setoptdept.do",
					data : {"dept_no":treeNode.data_auth_no,"deptName":treeNode.name},
					dataType : "json",
					success : function(data) {
					}
				});
            }
        }
    };  
    // 渲染  
    $(document).ready(function() {
        var deptTreeObj = $.fn.zTree.init($("#treeDemo"), setting, ${json}.retList);  
		var deptNodes = deptTreeObj.getNodes();
		if(deptNodes.length > 0){
			deptTreeObj.expandNode(deptNodes[0], true, false, true);
		}
    });  
</script>
</HEAD>
<BODY>
  <div>
    <ul id="treeDemo" class="ztree"></ul>
  </div>
</BODY>
</HTML>