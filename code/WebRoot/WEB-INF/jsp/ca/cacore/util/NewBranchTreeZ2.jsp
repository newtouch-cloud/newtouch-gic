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
            url: "<%=basePath %>/sms/querySalesFirmBranchTreeZ.do?rqstType=AJAX&path=3",
            async: true,
            data:{},
            success: function (data) {
                var zNodes = JSON.parse(data).retList;
                 $.each(zNodes,function(index,item){
                	 console.log(item.isParent); 
                	item.isParent = "false";
                })
                console.log(zNodes);
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
            beforeExpand: beforeExpand/* ,
            onExpand:  zTreeOnExpand   *///异步扩展节点
            /* , setDisabledNode() */
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
    //设置禁用的复选框节点  
    function setDisabledNode(){  
          var treeObj = $.fn.zTree.getZTreeObj("treeDemo");  
          var disabledNode = treeObj.getNodeByParam("level", 0);  
          treeObj.setChkDisabled(disabledNode, true);     
    } 
    function ajaxGetNodes(treeNode, reloadType) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        if (reloadType == "refresh") {
            treeNode.icon = "/compent/ztree/img/loading.gif";
            zTree.updateNode(treeNode);
        }
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

<div style="margin-left:0px" class='control-group span4'>
    <label class='control-label' for='branch_name'>中介公司机构</label>
    <div class='controls'>
        <input type='text' onclick='getOrgTree();' class='input-medium back_image branch_name tooltip-hide'
               name='branch_name' data-toggle="tooltip" readonly id='branch_name' value='${rmHelper.returnParams.branch_name}'/>
        <input type="hidden" id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}'/>
        <input type="hidden" id="branch_level" name="branch_level" value='${rmHelper.returnParams.branch_level}'/>
        <input type="hidden" id="branch_id2" name="branch_id2" value='${rmHelper.returnParams.branch_id}'/>
    </div>
</div>


<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:99999;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:300px;"></ul>
</div>


