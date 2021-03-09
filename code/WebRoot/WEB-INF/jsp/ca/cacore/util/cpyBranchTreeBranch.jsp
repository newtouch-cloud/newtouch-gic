<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg" %>
<%@page import="com.newtouch.component.c11assistant.StringHelper" %>
<%@page import="com.newtouch.component.c11assistant.JspHelper" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "";
%>

<script type="text/javascript">
    function getBranchTree() {
        $.ajax({
            type: "POST",
            url: "<%=basePath %>/Branch/querySalesFirmBranchTree.do?rqstType=AJAX",
            async: false,
            success: function (data) {
                var zNodes = JSON.parse(data).retList;
                $.fn.zTree.init($("#treeDemo_Branch"), settingBranch, zNodes);
                var cityObj = $("#branch_name_Branch");
                var cityOffset = $("#branch_name_Branch").offset();
                $("#menuContent_Branch").css({
                    left: cityOffset.left + "px",
                    top: cityOffset.top + cityObj.outerHeight() + "px"
                }).slideDown("fast");
                $("body").bind("mousedown", onBodyDownBranch);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("系统异常");
            }
        });
    }


    //初始化ztree 设置
    var settingBranch = {
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
            onClick: onClickBranch,
            beforeExpand: beforeExpandBranch,
            onExpand: zTreeOnExpandBranch  //异步扩展节点
        }
    };

    function beforeExpandBranch(treeId, treeNode) {
        if (!treeNode.isAjaxing) {
            treeNode.times = 1;
            ajaxGetNodesBranch(treeNode, "refresh");
            return true;
        } else {
            alert("数据加载中，请稍后");
            return false;
        }
    }

    function ajaxGetNodesBranch(treeNode, reloadType) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo_Branch");
        if (reloadType == "refresh") {
            treeNode.icon = "/compent/ztree/img/loading.gif";
            zTree.updateNode(treeNode);
        }
    }

    function zTreeOnExpandBranch(event, treeId, treeNode) {

        var branchid = treeNode.id;
        var level = treeNode.blevel;
        $.ajax({
            type: "POST",
            url: "<%=basePath %>/Branch/querySalesFirmBranchTree.do?rqstType=AJAX&org_level=" + level + "&branchid=" + branchid,
            async: false,
            success: function (data) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo_Branch");
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
                var zTree = $.fn.zTree.getZTreeObj("treeDemo_Branch");
                treeNode.icon = "";
                zTree.updateNode(treeNode);
            }
        });
    }


    function onClickBranch(e, treeId, treeNode) {
    	debugger;
        //获取机构树实例
        var zTree = $.fn.zTree.getZTreeObj("treeDemo_Branch");
        //获取选中节点
        var nodes = zTree.getSelectedNodes();
        //获取选中节点的值
        var branch_name = document.getElementById("branch_name_Branch").id;
        var branch_id = document.getElementById("branch_id_Branch").id;

        $("#branch_name_Branch").prop("value", nodes[0].name);//赋值
        $("#branch_name_Branch").prop("title", nodes[0].name);//赋值

        $("#branch_id_Branch").prop("value", nodes[0].id);//赋值
        $("#branch_level_Branch").prop("value", nodes[0].level);
        $("#menuContent_Branch").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDownBranch);
    }

    function hideMenuBranch() {
        $("#menuContent_Branch").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDownBranch);
    }

    function onBodyDownBranch(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent_Branch" || $(event.target).parents("#menuContent_Branch").length > 0)) {
            hideMenuBranch();
        }
    }

</script>
<script>
    $(function () {
        $("#branch_name_branch").tooltip();
    });
</script>

<div class='control-group span4'>
    <label class='control-label' for='branch_name_branch'>中介公司机构</label>
    <div class='controls'>
        <input type='text' onclick='getBranchTree();' class='input-medium back_image branch_name_branch tooltip-hide'
               name='branch_name_branch' data-toggle="tooltip"
               readonly id="branch_name_Branch" value='${rmHelper.returnParams.branch_name_branch}'/>
        <input type="hidden" id="branch_id_Branch" name="branch_id_Branch" value='${rmHelper.returnParams.branch_id_Branch}'/>
        <input type="hidden" id="branch_level_Branch" name="branch_level" value='${rmHelper.returnParams.branch_level_Branch}'/>
    </div>
</div>


<div id="menuContent_Branch" class="menuContent" style="display:none; position: absolute;z-index:99999;">
    <ul id="treeDemo_Branch" class="ztree" style="margin-top:0; width:300px;"></ul>
</div>
