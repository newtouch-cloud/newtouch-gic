<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg" %>
<%@page import="com.newtouch.component.c11assistant.StringHelper" %>
<%@page import="com.newtouch.component.c11assistant.JspHelper" %>

<!-- 机构树进行下拉选择 yanqiguang 数据权限查询 2017.11.22 -->
<!-- 财险公司机构树进行优化异步加载 yanqiguang 2018.7.17 -->
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

    function getcpyOrgTree() {
        $.ajax({
            type: "POST",
            url: "<%=basePath %>/mass/queryCpyBranchTree.do?rqstType=AJAX",
            async: false,
            success: function (data) {
                var zNodes = JSON.parse(data).retList;
                $.fn.zTree.init($("#treeDemo"), setting1, zNodes);
                var cityObj = $("#cpybranch_name");
                var cityOffset = $("#cpybranch_name").offset();
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
    var setting1 = {
        key: {
            level: "level" //定义机构级别属性
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
        var org_id = treeNode.id;
        var org_level = treeNode.blevel;
        $.ajax({
            type: "POST",
            url: "<%=basePath %>/mass/queryCpyBranchTree.do?rqstType=AJAX&org_level=" + org_level + "&org_id=" + org_id,
            async: false,
            success: function (data) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                var zNodes = JSON.parse(data).retList;
                //先移除所有子节点
                if(zNodes!=undefined){
                    zTree.removeChildNodes(treeNode);
                }
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
        $("#org_level").prop("value", nodes[0].blevel);//赋值机构等级
        $("#cpybranch_name").prop("value", nodes[0].name);//赋值
        $(".cpybranch_name").prop("value", nodes[0].name);//赋值
        $("#cpybranch_id").prop("value", nodes[0].id);//赋值
        $("#repair_coding").prop("value", nodes[0].id);//赋值
        $("#cpybranch_id1").prop("value", nodes[0].id);//赋值
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
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
        $("#cpybranch_name").tooltip();
    });
</script>
<div style="margin-left:0px" class='control-group span4'>
    <label class='control-label' for='cpybranch_name'>保险公司机构选择</label>
    <div class='controls'>
        <input type='text' onclick='getcpyOrgTree();' class='input-medium back_image cpybranch_name' name='cpybranch_name'
               readonly id='cpybranch_name' value='${rmHelper.returnParams.cpybranch_name}'/>
        <input type="hidden" id="cpybranch_id" name="cpybranch_id" value='${rmHelper.returnParams.cpybranch_id}'/>
        <input type="hidden" id="org_level" name="org_level" value='${rmHelper.returnParams.org_level}'/>
    </div>
</div>


<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:99999;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:300px;"></ul>
</div>
