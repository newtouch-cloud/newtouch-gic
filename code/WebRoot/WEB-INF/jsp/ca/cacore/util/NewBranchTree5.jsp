<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg" %>
<%@page import="com.newtouch.component.c11assistant.StringHelper" %>
<%@page import="com.newtouch.component.c11assistant.JspHelper" %>

<!-- 机构树进行下拉选择 yanqiguang 数据权限查询 2017.11.22 -->
<!-- 中介公司机构树进行优化异步加载 yanqiguang 2018.7.17 -->
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "";
%>
<!-- 引入ztree下拉选的样式 -->
<link rel="stylesheet" href="<%=basePath %>/compent/ztree/tree.demo.css" type="text/css">
<link rel="stylesheet" href="<%=basePath %>/compent/ztree/zTreeStyle.css" type="text/css">
<!--引入jquery 和ztree核心 -->
<script type="text/javascript" src="<%=basePath %>/compent/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript">

    function getOrgTree() {
        $.ajax({
            type: "POST",
            url: "<%=basePath %>/Branch/querySalesFirmBranchTree.do?rqstType=AJAX",
            async: false,
            success: function (data) {
                var zNodes = JSON.parse(data).retList;
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                var cityObj = $("#branch_name");
                var cityOffset = $("#branch_name").offset();
                $("#menuContent").css({
                    left: cityOffset.left + "px",
                    top: cityOffset.top + cityObj.outerHeight() + "px"
                }).slideDown("fast");
                $("body").bind("mousedown", onBodyDown);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("系统异常");
            }
        });
    }
    


    //初始化ztree 设置
    var setting = {
        key: {
            level: "blevel" //定义机构级别属性
        },
        view: {
            dblClickExpand: false,
            showLine: true,
            fontCss: {color: "black"},
            showIcon: true,
            selectedMulti: false        //禁止多点选中
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                level: "blevel",
                nameKey: "name"
            }
        },
        callback: {
            //beforeClick: beforeClick,
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


    function onClick(e, treeId, treeNode) {
        //获取机构树实例
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        //获取选中节点
        var nodes = zTree.getSelectedNodes();
        //获取选中节点的值
        var branch_name = document.getElementById("branch_name").id;
        var branch_id = document.getElementById("branch_id").id;

        $("#branch_name").prop("value", nodes[0].name);//赋值
        $("#branch_name").prop("value", nodes[0].name);//赋值
        $("#branch_id").prop("value", nodes[0].id);//赋值
        $("#branch_level").prop("value", nodes[0].level);
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
        var branch_id = document.getElementById("branch_id").value;
    	//alert(branch_id);
    	$.ajax({
    		type: "get",
    		async: true,
    		url: '<%=basePath %>/findSuperiors.do',
    		data:{
    			"branch_id": branch_id,
    		},
    		dataType: 'json',
    		success: function(data){
				//alert(data.length);
    			if (data != null) {
                    if (data.length != 0) {
                    	 <%-- <% request.removeAttribute("superiorList");%> --%>
                        $("#dataList datalist").remove();
                        $("#dataList").append("<datalist id='superiorList'>");
                        /* $("#superiorList").append("<option value=''>--请选择--</option>"); */
                        for (var i = 0; i < data.length; i++) {
                            $("#superiorList").append("<option data-id=" + data[i].superior_no + ">" +data[i].superior_no+"---"+ data[i].superior_name + "</option>");
                        }
                        $("#dataList").append("</datalist>");
                    }else{
                    	$("#dataList datalist").remove();	
                    }
                }else{
                	$("#dataList datalist").remove();	
                }
    		},
    		error: function(){
    			alert("查询出错!");
    		}
    	});
    }

    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
            hideMenu();
        }
    }

</script>
<script>
    $(function () {
        $("#branch_name").tooltip();
    });
</script>

<div class="col-xs-4">
    <div class="input-group">
    <span class="input-group-addon"><label class='control-label' for='branch_name'>中介公司机构</label></span>
    <div>
        <input type='text' onclick='getOrgTree();' class='input-medium back_image branch_name tooltip-hide'
               name='branch_name' data-toggle="tooltip" readonly id='branch_name' value='${rmHelper.returnParams.branch_name}'/>
        <input type="hidden" id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}'/>
        <input type="hidden" id="branch_level" name="branch_level" value='${rmHelper.returnParams.branch_level}'/>
    </div>
    </div>
</div>


<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:99999;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:300px;"></ul>
</div>
