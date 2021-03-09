<%@ page language="java" pageEncoding="UTF-8"%>
<%
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");  
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String menuString = (String) session.getAttribute("menuString");
	System.out.println(menuString+"   " +path+"   "+basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" href="<%=basePath %>/compent/ztree/zTreeStyle.css" type="text/css"><!-- ztree -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/compent/msg/css/com.newtouch.message.css" />
	
	<script type="text/javascript" src="<%=basePath%>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
	<script type="text/javascript" src="<%=basePath%>/compent/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath%>/compent/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath%>/compent/ztree/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath%>/compent/msg/com.newtouch.message.js"></script>

	<script language="javascript">
	 			
		var zTree, rMenu;
	
	    var setting = {  
	            view: {  
	            	dblClickExpand: false,
	    			showLine: true,
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
				check: {
					enable: false
				},
	            callback: {  
// 	            	onRightClick: OnRightClick  
	                onRightClick: function(event, treeId, treeNode) {  
	                	if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
	            			zTree.cancelSelectedNode();
	            			showRMenu("root", event.clientX, event.clientY);
	            		} else if (treeNode && !treeNode.noR) {
	            			zTree.selectNode(treeNode);
	            			showRMenu("node", event.clientX, event.clientY);
	            		}
	                }  
	            }  
	        }; 
		

		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_del").hide();
				$("#m_check").hide();
				$("#m_unCheck").hide();
			} else {
				$("#m_del").show();
				$("#m_check").show();
				$("#m_unCheck").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
	
			$("body").bind("mousedown", onBodyMouseDown);
		}
		
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		var addCount = 1;
		function addTreeNode() {
			var nodes = zTree.getSelectedNodes()[0];
			hideRMenu();			
			window.parent.document.getElementById("rightInfoFrame").src = encodeURI(encodeURI('<%=basePath %>addMenuMgmtInit.do?nodename='+nodes.name+'&nodeno='+nodes.id+'&nodeseq='+nodes.menu_seq));
		}
		
		function updateTreeNode() {
			hideRMenu();
			var nodes = zTree.getSelectedNodes()[0];
			window.parent.document.getElementById("rightInfoFrame").src = encodeURI(encodeURI('<%=basePath %>updateMenuMgmtInit.do?nodeno='+nodes.id));
		}
		
		function removeTreeNode() {
			hideRMenu();
			var url = "deleteMenuMgmt.do";
			var nodes = zTree.getSelectedNodes()[0];
			if(nodes.children && nodes.children.length > 0){				
				var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
				if (confirm(msg)==false){
					return;
				}
			}
			var jsonData = eval("({menu_no:'"+nodes.id+"',menu_seq:'"+nodes.menu_seq+"',funcID:'" + url + "',rqstType:\"AJAX\"})");
			$.ajax({
				type : "POST",
				url : url,
				data : jsonData,
				dataType : "json",
				success : function(data) {
					var datamsg = "";
					$.each(data.msg, function(i, value){
						$.each(value, function(msgKey,msgInfo){
							datamsg += msgKey + msgInfo + "\n";
						});
					});
					debugger;
	                if (data.flag == "success") {
						var nodes = zTree.getSelectedNodes();
						zTree.removeNode(nodes[0]);
					}
					var msgList = new Array;
					var msgInfo = new Object();
					msgInfo.msg = datamsg;
					msgList.push(msgInfo);
					var alertmsg = new Object();
					alertmsg.msgType = "normal";
					alertmsg.msg = msgList;
					// var   editor = window.parent.document.getElementById('rightInfoFrame').contentWindow;
					alertMsg(alertmsg);
				},
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		            alert(errorThrown);
		        }
			});
		}
	
		var zNodes = ${json}.retList;
		
		jQuery( function(){		   		
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
		});
		
	</script>
	<style type="text/css">
	
		html, body, div, span, ul, li {
	margin: 0;padding: 0;border: 0;outline: 0;font-weight: inherit;font-style: inherit;font-size: 100%;font-family: inherit;vertical-align: baseline;}

		div.content_wrap {width: 265px;height:380px;}
		div.content_wrap div.left{float: left;width: 250px;}

		ul.ztree {margin-top: 10px;border: 1px solid #617775;width:265px;height:500px;overflow-y:auto;overflow-x:auto;}
		ul.log {border: 1px solid #617775;background: #f0f6e4;width:265px;height:170px;overflow: hidden;}
		ul.log.small {height:45px;}
		ul.log li {color: #666666;list-style: none;padding-left: 10px;}

		div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
		div#rMenu ul li{
			margin: 1px 0;
			padding: 0 5px;
			cursor: pointer;
			list-style: none outside none;
			background-color: #DFDFDF;
		}
	</style>
</head>
<body style="margin-top: 0px; margin-left: 0px;" style="height: 750px">
	<form name="submitForm" method="post" action="leftMenu"
		target="rightInfoFrame">
		<div class="content_wrap">		
			<div class="left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>
		
	</form>

<div id="rMenu">
	<ul>
		<li id="m_add" onclick="addTreeNode()">增加节点</li>
		<li id="m_upd" onclick="updateTreeNode()">修改节点</li>
		<li id="m_del" onclick="removeTreeNode()">删除节点</li>
	</ul>
</div>
</body>
</html>