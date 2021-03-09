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
   var req={};
   //键盘松开事件
   //obj表示传递过来的this对象，this是当前对象，当前是在文本框中操作，获取的就是正在操作的文本框
    function searchWord(obj) {
	   var id = $("#branch_id").val();//获取中介公司机构代码
	   var text =obj.value;//获取的是键盘输入的
	   console.log("中介公司机构的代码="+id);
	   console.log("键盘输入的内容="+text);
	   //获取到输入的文本框的内容去服务器的进行查询展示
	   //判断文本框中有没有数据，如果文本框中没有数据，那么文本框的value属性就是空字符串“”
	   if($.trim(text) == ""){
		   //搜索文本中没有内容，就清除候选词提示
		     $("#show").empty();
		     req = {};
		     //移除另外代理代码和推荐维修码内容
		     $("#cpybranch_id1").val("");
		     $("#repair_coding").val("");
		     $("#parentbranch_name").val("");
		     cli1();
		   //不需要搜索了，使用return结束函数
		   return;
	   }
	   //发送ajax异步请求，让服务器查询
	   $.ajax({
		   type:"POST",
		   url:"<%=basePath %>/reimbursement/reimbursement/queryAgentment.do?rqstType=AJAX&agency_name="+text+"&branch_id="+id,
		   async: false,
		   //传过去的date数据有输入的文字和机构的id
		   //response是服务器响应过来的数据
		   success:function(response){ 
			   console.log("后端传来的数据类型="+typeof(response));
			   console.log("后端传来的数据="+response);
			   //每次请求成功，得到新的数据时，要清空show这个div中的内容（清除原来的提示）
			   $("#show").empty();
			   //传过来的是个集合
			   var list =  JSON.parse(response).retList;
			   console.log("得到list集合="+list);
			   req = list;
			   $.each(list,function(i,e){
				   //展示数据，展示在文本框的下面,msg是一个对象，所以展示的时候需要获取某个对象的
				   $("#show").append($("<ul style='margin-top:0; width:300px;' onmouseover='over(this)' onmouseout='out(this)' onclick='cli(this)'> " +e["agency_name"] + "</ul>"));
				   if(i>=9){
					   return false;
				   }
			   })
			   /* for(var msg in response){
				   req = response[msg]
				   //展示数据，展示在文本框的下面,msg是一个对象，所以展示的时候需要获取某个对象的
				   $("#show").append($("<div onmouseover='over(this)' onmouseout='out(this)' onclick='cli(this)'> " + response[msg] + " </div>"));
			   } */
			   //将show元素的display属性改回block，可以继续显示了
			   $("#show").css("display","block");
		   },
		   error:function(){
				alert('查询失败,请稍后重试');
			}
	   });
   }
    function over(obj){
    	$("#cpybranch_name").removeAttr("onblur");//移除事件
        $(obj).css("background-color","gainsboro");
    }
    function out(obj){
    	$("#cpybranch_name").attr("onblur","blu();");//添加事件
        $(obj).css("background-color","white");
    }
    //将下拉框的内容赋值到输入框中
    function cli(obj) {
    	$("#cpybranch_name").attr("onblur","blu();");//添加事件
        $("#cpybranch_name").val($(obj).html());
        var ob = $("#cpybranch_name").val();
    	$.each(req,function(i,e){
    		if(e["agency_name"]==$.trim(ob)){
    			 $("#cpybranch_id").val(e["agency_no"]);
    		     $("#cpybranch_id1").val(e["repair_coding"]);
    		     $("#repair_coding").val(e["repair_coding"]);
    		     $("#parentbranch_name").val(e["ins_branchname"]);
    		     return false;
    		}
    	})
        $("#show").empty();
    }
    function cli2(obj) {
    	$("#cpybranch_name").attr("onblur","blu();");//添加事件
        $("#cpybranch_name").val($(obj).html());
    	var ob = $(obj).html();
    	console.log(ob);
    	$.each(req1,function(i,e){
    		console.log(e["agency_name"]);
    		if(e["agency_name"]==$.trim(ob)){
    			 $("#cpybranch_id").val(e["agency_no"]);
    		     $("#cpybranch_id1").val(e["repair_coding"]);
    		     $("#repair_coding").val(e["repair_coding"]);
    		     $("#parentbranch_name").val(e["ins_branchname"]);
    		     return false;
    		}
    	})
        $("#show").empty();
    }
    
    //文本框失去焦点，清除show中的div内容
    function blu(){
    	$("#show").empty();
    }
    var req1={};
    function cli1(){
    	 var id = $("#branch_id").val();//获取中介公司机构代码
    	 var text = $("#cpybranch_name").val(); 
    	 text = $.trim(text);
    	var name = $("#branch_name").val(); //获得中介机构公司的文本内容
    	console.log("中介公司机构的代码="+id);
    	console.log("中介公司机构的名称="+name);
    	if(name == ""){
    		alert("请先填写中介公司机构");
    		$("#show").empty();
    		$("#cpybranch_name").val("");
    		$("#cpybranch_name").attr("readonly","readonly");
    		return;
    	}
    		$.ajax({
        		type:"POST",
        		url:"<%=basePath %>/reimbursement/reimbursement/queryAgentment.do?rqstType=AJAX&agency_name="+text+"&branch_id="+id,
       		    async: false,
       		    success:function(response){
       		    	//每次请求成功，得到新的数据时，要清空show这个div的内容（清除原来的提示）
       		     $("#show").empty();
       		     var list1 = JSON.parse(response).retList;
       		     req1 = list1;
       		     $.each(list1,function(i,e){
    				   //展示数据，展示在文本框的下面,msg是一个对象，所以展示的时候需要获取某个对象的
    				   $("#show").append($("<ul style='margin-top:0; width:300px;' onmouseover='over(this)' onmouseout='out(this)' onclick='cli2(this)'> " +e["agency_name"] + " </ul>"));
    				   if(i>=9){
    					   return false;
    				   }
    			   })
    			   //将show元素的display属性改回block，可以继续显示了
    			       $("#show").css("display","block");
       		    },
       		   error:function(){
    				alert('查询失败,请稍后重试');
    			}
        	});
    	
    	$("#cpybranch_name").removeAttr("readonly");
    }
    
    function getcpyOrgTree() {
        $.ajax({
            type: "POST",
            url: "<%=basePath %>/reimbursement/reimbursement/queryTree.do?rqstType=AJAX",
            async: false,
            success: function (data) {
            	debugger;
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
            level: "level", //定义机构级别属性
            agency:"agency"
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
                nameKey: "name",
                agency:"agency"
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
        debugger;
        $.ajax({
            type: "POST",
            url: "<%=basePath %>/reimbursement/reimbursement/queryTree.do?rqstType=AJAX&org_level=" + org_level + "&org_id=" + org_id,
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
        debugger;
        var agency=nodes[0].agency;
        $("#cpybranch_name").prop("value", nodes[0].name);//赋值
        if(agency == 1){
        	//获取选中节点的值
            $("#org_level").prop("value", nodes[0].blevel);//赋值机构等级
            $("#cpybranch_id").prop("value", nodes[0].id);//赋值
            $("#repair_coding").prop("value", nodes[0].id);//赋值
            $("#cpybranch_id1").prop("value", nodes[0].id);//赋值
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
            $.ajax({
    			type: "post",
    			url: "<%=basePath%>/reimbursement/reimbursement/queryrepair_coding.do?cpybranch_id="+$("#cpybranch_id").val()+"&&rqstType=AJAX",
    			async: true,
    			dataType:"json",
    			success:function(data){
    				if(data.retList.length!=0){
          	          	 $("#parentbranch_name").attr("value",data.retList[0].parentbranch_name);
          	          	 $("#parentbranch_id").attr("value",data.retList[0].parentbranch_id);
    				}
    			},
    			error:function(){
    				alert('查询失败,请稍后重试');
    			}
    		});
        }else{
        	alert('请选取代理');
        }
        
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
    <label class='control-label' for='cpybranch_name'>代理选择</label>
    <div class='controls'>
    <!-- getcpyOrgTree -->
        <input  type='text'  onblur="blu()" onclick="cli1()" onkeyup="searchWord(this)" class='input-medium back_image cpybranch_name' name='cpybranch_name'
             readonly  id='cpybranch_name' value=''/>
        <input type="hidden" id="cpybranch_id" name="cpybranch_id" value=''/>
        <input type="hidden" id="org_level" name="org_level" value='${rmHelper.returnParams.org_level}'/>
        <div class="show" id="show" style="display:none;position: absolute;z-index:99999;background:white"></div>
    </div>
</div>


<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:99999;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:300px;"></ul>
</div>
