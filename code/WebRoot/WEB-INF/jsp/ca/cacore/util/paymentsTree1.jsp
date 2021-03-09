<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<!-- 机构树进行下拉选择 yanqiguang 数据权限查询 2017.11.22 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!-- 引入ztree下拉选的样式 -->
<link rel="stylesheet" href="<%=basePath %>/compent/ztree/tree.demo.css" type="text/css">
<link rel="stylesheet" href="<%=basePath %>/compent/ztree/zTreeStyle.css" type="text/css">
<!--引入jquery 和ztree核心  -->
<script type="text/javascript" src="<%=basePath %>/compent/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript">

function cpygetOrgTree(){
	$.ajax({
		type : "POST",
		url :  "<%=basePath %>/balance/addreduce/queryTree.do?rqstType=AJAX",
		async : false,
		success : function(data) {
			 var zNodes=JSON.parse(data).retList;
			$.fn.zTree.init($("#treeDemo"), settings, zNodes);
			var cityObj = $("#cpybranch_name");
			var cityOffset = $("#cpybranch_name").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
		},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert("系统异常");
        }
	});
	}
	
	
//初始化ztree 设置
var settings = {
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
                 level:"blevel",
                 nameKey:"name"
             }  
         }, 
		callback: {
			//beforeClick: beforeClick,
			onClick: onClLICK
		}
	};
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check) alert("只能选择城市...");
	return check;
}

function onClLICK(e, treeId, treeNode) {

	//获取机构树实例
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	//获取选中节点
	debugger;
	var nodes = zTree.getSelectedNodes();
	//获取机构等级
	var blevel = nodes[0].blevel;
	debugger;
	
		//获取选中节点的值
		var cpybranch_name = document.getElementById("cpybranch_name").id; 
		var cpybranch_id=  document.getElementById("cpybranch_id").id; 
	/*多选 	nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			branch_name += nodes[i].name + ",";
			alert(nodes[i].name);
		} */
//		if (branch_name.length > 0 ) branch_name = branch_name.substring(0, branch_name.length-1);
		$("#cpybranch_name").prop("value", nodes[0].name);//赋值
		$("#cpybranch_name").prop("title", nodes[0].name);//赋值
		$("#cpybranch_id").prop("value", nodes[0].id);//赋值
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	
	
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
		
</script>
<script>
    $(function () { $("#cpybranch_name").tooltip(); });
</script>
<div style="margin-left:0px"  class='control-group span4'>
	<label   class='control-label' for='branch_name'>财险公司机构</label>
	<div class='controls' >
		<input type='text' onclick='cpygetOrgTree();'  class='input-medium back_image cpybranch_name' name='cpybranch_name' readonly id='cpybranch_name'  value='${rmHelper.returnParams.cpybranch_name}'/>
		<input type="hidden" id="cpybranch_id" name="cpybranch_id" value='${rmHelper.returnParams.cpybranch_id}'/>
	</div>
</div>


<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:99999;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:300px;"></ul>
</div>
