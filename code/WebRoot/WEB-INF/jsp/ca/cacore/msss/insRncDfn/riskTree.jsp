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

function getOrgTree(){
	$.ajax({
		type : "POST",
		url :  "<%=basePath %>/msss/InsRncDfn/queryCompanyTree.do?rqstType=AJAX",
		async : false,
		success : function(data) {
			 var zNodes=JSON.parse(data).retList;
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var cityObj = $("#branch_name");
			var cityOffset = $("#branch_name").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
		},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert("系统异常");
        }
	});
	}
	
	
//初始化ztree 设置
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
			//beforeClick: beforeClick,
			onClick: onClick
		}
	};
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check) alert("只能选择城市...");
	return check;
}

function onClick(e, treeId, treeNode) {
	//获取机构树实例
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	//获取选中节点
	var nodes = zTree.getSelectedNodes();
	//获取选中节点的值
	var branch_name = document.getElementById("branch_name").id; 
	var branch_id=  document.getElementById("branch_id").id; 
	
/*多选 	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		branch_name += nodes[i].name + ",";
		alert(nodes[i].name);
	} */
//	if (branch_name.length > 0 ) branch_name = branch_name.substring(0, branch_name.length-1);
	$("#branch_name").prop("value", nodes[0].name);//赋值
	$(".branch_name").prop("value", nodes[0].name);//赋值
	$("#branch_id").prop("value", nodes[0].id);//赋值
	$(".branch_id").prop("value", nodes[0].id);//赋值
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
	$.ajax({
			url : "<%=basePath%>/msss/InsRncDfn/queryclassCode.do?rqstType=AJAX&branch_id="+$("#branch_id").val(),
			type : "post",
			async : true, 
		    dataType: "json",  
			success : function(data) { 
				if(data.retList != null && data.retList.length!=0){
					$("#classCode option").remove();
					$("#classCode").append("<option value=''>--请选择险类--</option>"); 
					for(var i=0;i<data.retList.length;i++){
   						   $("#classCode").append("<option value=" + data.retList[i].classCode + ">" + data.retList[i].className + "</option>");
					}
				}else{
					alert('此机构下无险类，请重新选择机构');
					$("#branch_name").prop("value", "");//赋值
					$("#branch_id").prop("value", "");//赋值
					$("#classCode option").remove();
					$("#classCode").append("<option selected='selected' value=''>--请选择险类--</option>");
				}
	    	}, 
	    	error : function() {
	       		alert('险类查询出错');
			}
		});
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

<div style="margin-left:0px" class='control-group span4'>
	<label class='control-label' for='branch_name' >保险公司机构选择(<font color="red">*</font>):</label>
	<div class='controls'>
		<input type='text' onclick='getOrgTree();'  class='input-medium back_image' name='branch_name' readonly id='branch_name'  value='${rmHelper.returnParams.branch_name}' />
		<input type="hidden" id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}'/>
	</div>
</div>


<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:99999;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:210px;"></ul>
</div>

