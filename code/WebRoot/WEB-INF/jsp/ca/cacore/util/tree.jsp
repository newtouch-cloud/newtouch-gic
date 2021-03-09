<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		
		
		
		
		
	
		<!-- fram start -->
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-cerulean.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/bootstrap-responsive.min.css" >
		<link rel="stylesheet" href="<%=basePath %>/compent/charisma/css/charisma-app.css">
		<link rel="stylesheet" href="<%=basePath %>/compent/ztree/zTreeStyle.css">
		<script type="text/javascript" src="<%=basePath %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
		<script type="text/javascript" src="<%=basePath %>/compent/ztree/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="<%=basePath %>/compent/base.js"></script><!-- jQuery -->
		<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="<%=basePath %>/compent/html5shiv/html5shiv.js"></script>
		<![endif]-->
		<script>
			var zTree;
		    var setting = {  
		            view: {
		            	dblClickExpand: false,
		    			showLine: true,
		    			fontCss : {color:"black"},
		    			showIcon: true,
		                selectedMulti: false        //禁止多点选中  
		            },  
		            data: {  
		                simpleData: {  
		                    enable:true,  
		                    idKey: "id",  
		                    pIdKey: "pid",  
		                    rootPId: "" ,
		                    nameKey:"name"
		                }  
		            },  
		            callback: {  
		                onClick: function(treeId, treeNode) {  
		                	var treeObj = $.fn.zTree.getZTreeObj(treeNode);  
		                	var selectedNode = treeObj.getSelectedNodes()[0];
		                	//M by  ma_cj for if条件支持IE11 begin
		                	if(document.getElementById) //IE 
		                	//M by  ma_cj for if条件支持IE11 end
		                	{ 
		                		parent.window.returnValue=selectedNode.branch_id+"#"+selectedNode.branch_name;
		                	} else {	                		
			                	window.opener.$("#branch_id").val(selectedNode.branch_id);
			                	window.opener.$("#branch_name").val(selectedNode.branch_name);
		                	}
		                	window.close();
		                }
		            }  
		        }; 
			//zNodes =  [{id:1,pid:0,name:1},{id:2,pid:1,name:2}];
			zNodes =  ${json}.retList;
			//function document.oncontextmenu(){event.returnValue=false;}
			jQuery( function(){		      
				$.fn.zTree.init($("#treeDemo"), setting,zNodes);
			});
		</script>
	</head>
	<body style="margin-top: 0px; margin-left: 0px;">
  		<form name="submitForm" method="post" action="leftMenu" target="rightFrame">
    		<div>
      			<ul id="treeDemo" class="ztree"></ul>
    		</div>
    	</form>
		<div style="height: 20px"></div>
	</body>
</html>
