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
		url :  "<%=basePath %>/Branch/querySalesFirmBranchTree.do?rqstType=AJAX",
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
	
function zTreeOnExpand(event, treeId, treeNode) {

    var branchid = treeNode.id;
    var level = treeNode.blevel;
    $.ajax({
        type: "POST",
        url: "<%=basePath %>/Branch/querySalesFirmBranchTree.do?rqstType=AJAX&org_level=" + level + "&branchid=" + branchid,
        async: false,
        success: function (data) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            var zNodes = JSON.parse(data).retList;
            //先移除所有子节点
            zTree.removeChildNodes(treeNode);
            //添加子节点
            zTree.addNodes(treeNode, zNodes);
            treeNode.icon = "";
            zTree.updateNode(treeNode);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("获取机构数据出现异常。");
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            treeNode.icon = "";
            zTree.updateNode(treeNode);
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
                 blevel:"blevel",
                 nameKey:"name"
             }  
         }, 
		callback: {
			onClick: onClick,
            beforeExpand: beforeExpand,
            onExpand: zTreeOnExpand  //异步扩展节点
		}
	};
	
function beforeExpand(treeId, treeNode) {
    if (!treeNode.isAjaxing) {
        treeNode.times = 1;
        ajaxGetNodes(treeNode, "refresh");
        return true;
    } else {
        alert("数据加载中，请稍后");
        return false;
    }
}

function ajaxGetNodes(treeNode, reloadType) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    if (reloadType == "refresh") {
        treeNode.icon = "/compent/ztree/img/loading.gif";
        zTree.updateNode(treeNode);
    }
}
	
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
	var this_level=nodes[0].branch_level;
	if(this_level==""||this_level=="1"||this_level=="2"||this_level==null){
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
		alert("请选择营业部");
	}else{
		$("#branch_name").prop("value", nodes[0].name);//赋值
		$("#branch_name").prop("title", nodes[0].name);//赋值
		$(".branch_name").prop("value", nodes[0].name);//赋值
		$("#branch_id").prop("value", nodes[0].id);//赋值
	    $("#branch_idA").prop("value",nodes[0].id);
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
		var branch_id_val = $("#branch_id").val();
		//调用ajax 查询员工姓名 --给select赋值
		 $.ajax({
	        type: "post",
	        url: "<%=basePath %>/single/Single/queryPersonName.do?rqstType=AJAX&branch_id="+branch_id_val,
	       	async : true, 
	       	dataType: "json",
	           success: function (data) {
	        	   
	           	if(data.retList!=null&&data.retList.length!=0 ){
	           		debugger;
	           			var person_name =$("#person_name option:selected").val();
						$("#person_name option").remove();
						$("#person_name").append("<option value=''>--请选择--</option>");
						debugger;
						for(var i=0;i<data.retList.length;i++){
							if(person_name == data.retList[i].person_no){
								$("#person_name").append("<option selected='selected' value="+data.retList[i].person_no+">"+data.retList[i].person_name+"</option>");
							}else{
								$("#person_name").append("<option value="+data.retList[i].person_no+">"+data.retList[i].person_name+"</option>");
							}
						}
	           }else{
					alert('该机构下无员工，请重新选择');
						$("#branch_name").prop("value", "");//赋值
						$("#branch_id").prop("value", "");//赋值
						$("#person_name option").remove();
			   	    	$("#person_cid").prop("value","");
			   	    	$("#person_no").prop("value","");
			   }
	       }, 
			error : function() {
				 debugger;
					alert('error');
				} ,
		});
	}

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
	<label   class='control-label' for='branch_name'>所属中介公司(<font color="red">*</font>)</label>
	
	<div class='controls' >
		<input type='text' onclick='getOrgTree();'  class='input-medium back_image branch_name' name='branch_name' readonly id='branch_name'  value='${rmHelper.returnParams.branch_name}'/>
		<input type="hidden" id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}'/>
	</div>
</div>


<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:99999;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:300px;"></ul>
</div>



