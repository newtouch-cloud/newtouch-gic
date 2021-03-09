<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/newtouch/core/pub/wf/basecss.jsp"%>
<%@ include file="/WEB-INF/jsp/newtouch/core/pub/wf/basejs.jsp"%>
<script type="text/javascript">
</script>
</head>
<body id="body" style="height: 750px">
  <!-- 查询条件 -->
  <div class="panel panel-default">
    <div class="panel-heading">
      <div class="panel-title">
        查询条件 <span class="pull-right"><a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> 收缩</a> </span>
      </div>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
    <input type="hidden" id="addFlag" name="addFlag" value="${addFlag}">
      <div class="panel-body">
        <form id="form1" method="post" action="">
        <%-- <input type="text" id ="ca_code" name ="ca_code" value="${opt_no}">
        <input type="text" id ="ca_name" name ="ca_name" value="${opt_name}"> --%>
          <div class="row">
            <div class="col-xs-4">
              <div class="input-group">
              	<span class="input-group-addon"> <label for="usercode" class="control-label">核心用户代码</label> </span> <input type="text" class="form-control" id="usercode2" name='usercode2' />
              </div>
            </div>
            <div class="col-xs-4">
              <div class="input-group">
              	<span class="input-group-addon"> <label for="username">核心用户姓名</label> </span> <input type="text" class="form-control" id="username2" name='username2'/>
             	<input type="hidden" id="opt_no" name="opt_no" value="${re_no}"/>
             	<input type="hidden" id="opt_name" name="opt_name" value="${re_name}"/>
              </div>
            </div>
            </div>
         
          <div class="col-md-12 text-center panel-body">
            <span><button  name="doQueryUserMapping.do" type="button" class="btn btn-default btn-xs" onclick="queryUser(this)">查询</button></span> 
<!--        <span><button id="chongzhi" type="button" class="btn btn-default btn-xs">重置</button> </span>
 -->       </div>
        </form>
      </div>
    </div>
  </div>
  <!-- 查询结果 -->
  <div class="panel panel-default">
    <div class="panel-heading">
       <span><button type="button" name="addUserMapping.do" class="btn btn-default btn-xs" onclick="subBox(this)">保存</button> </span>      
      <span><button id="fanhui" type="button" name="doUserMgmtQueryPage.do" class="btn btn-default btn-xs">返回</button> </span> 
    </div>
    <div class="panel-body">
      <div class="table-responsive">
        <table id="grid" class="table table-bordered table-hover table-condensed">
          <thead>
            <tr>
              <!-- <th axis="button" class="pager">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	<button class="btn btn-default btn-xs" name="addUserMapping.do" type="button" style="display: none">绑定用户</button>              	
              </th> -->
              <th abbr="role_no" axis="checkbox" class="pager" title="选中本页全部"><input onclick="checkAll(this)" type='checkbox'  name="box" style="display:none" /></th>
              <th abbr="rn" class="pager">序号</th>
              <th abbr="usercode"  class="pager" >核心用户代码</th>
              <th abbr="username" class="pager">核心用户姓名</th>
              <th abbr="ca_code2"  class="pager" >绑定人员代码</th>
              <th abbr="ca_name2" class="pager">绑定人员姓名</th>
                       
              <th axis="hidden" abbr="opt_no" class="hidden"></th>
              <th axis="hidden" abbr="opt_name" class="hidden"></th>
              <th axis="hidden" abbr="usercode" class="hidden"></th>
              <th axis="hidden" abbr="username" class="hidden"></th>
              
            </tr>
          </thead>
        </table>
      </div>
      <%@ include file="/WEB-INF/jsp/newtouch/core/pub/pagination.jsp"%>
    </div>
  </div>
</body>
<script type="text/javascript">
	$(function (){
		jsonToPage(${json});
		fillGrid(${json});
		var data = $("#addFlag").val();
		if(data.length > 0){
			var msgList = new Array;
    		var msgInfo = new Object();
    		msgInfo.msg = data;
    		msgList.push(msgInfo);
    		var alertmsg = new Object();
    		alertmsg.msgType = "normal";
    		alertmsg.msg = msgList;
    		alertMsg(alertmsg);
		}	
	});
</script>
<script>	
	$(document).ready
	(
			//判断隐藏域是否存在值，如果存在，则把已绑定用户（默认第一条）打上勾
			function a()
			{
				var a=$("#page_1 tr:eq(0) td:eq(4)").text();
				if(a!=null && a!="")
					{
						document.getElementsByName("checkbox_1")[0].checked="true";
					}
				
				//为每一个checkbox绑定一个onclick事件c()
				$(".checkbox").each
				(
					function()
					{
						$(this).attr("onclick",'c(this)');		
					}
				)					
			}
	);
		//移除checkbox其他打钩选项的勾
		function c(a)
		{		
			 var cname=$(a).attr("name");
			 $(".checkbox").each(function(){
				 if($(this).attr("name")!=cname)
					 {
						 $(this).attr("checked",false); 
					 }			 	          
			 })
		}
		
		function subBox(thisval)
		{
			var val=$("#page_1 tr:eq(0) td:eq(4)").text();
			var cb1=document.getElementsByName("checkbox_1")[0];
			//alert(cb1.checked);
			//alert($(cb1).attr("checked"));
			if(val!=null && val!="" && cb1.checked)
			{				
				alert("该用户已经绑定");					
			}			
			else
			{
			    submitBox(thisval,'grid');
			}
		}
		
		function queryUser(btn, successFunc){
			var _this = $(btn);
			var start = new Date();
			var namespace = _namespace(_this);
			_this[0].disabled = true;
			if (!checkUi("form1", namespace)) {
				_this[0].disabled = false;
				return;
			}
			$("#" + namespace + " input[id='nowPage']").val("1");
			var formValue = formToJsonString("form1,form3", namespace);
			var subJson = getTableIdByForm(namespace);// 将tab页中的信息加入
			var url = _this.attr("name");
			var jsonData = string2json("{" + formValue + ",funcID:'" + url + "'" + subJson + ",rqstType:'AJAX'}");
			$.ajax({
				type : "POST",
				url : url,
				data : jsonData,
				dataType : "json",
				success : function(data) {
					if(data.flag != "success"){
						alertMsg4Modal(data);
						_this[0].disabled = false;
						return;
					}
					$("#"+namespace+" table[id='grid'] tbody").remove();
					fillGrid(data, namespace);
		            if (data.flag == "success" && successFunc != null)
		            	doCallBackEach(btn, successFunc, data);
		            initShowPageQuery(namespace, data);
		            var end = new Date();
		            $("#" + namespace + " span[id='allrowmsg']").prepend("本次查询用时" + ((end.getTime() - start.getTime()) / 1000.00) + "秒,");
		            _this[0].disabled = false;
		            
		          //判断隐藏域是否存在值，如果存在，则把已绑定用户（默认第一条）打上勾
					var pd1=document.getElementsByName("opt_no")[0].value;
					var pd2=$("#page_1 tr:eq(0) td:eq(4)").text();
					if(pd1==pd2)
						{
							var a=$("#page_1 tr:eq(0) td:eq(4)").text();
							if(a!=null && a!="")
								{
									document.getElementsByName("checkbox_1")[0].checked="true";
									//为每一个checkbox绑定一个onclick事件c()
									$(".checkbox").each
									(
										function()
										{
											$(this).attr("onclick",'c(this)');		
										}
									)	
								}
						}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					updateTips("status = " + XMLHttpRequest.status + " | readyState = "
							+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
							+ " | errorThrown = " + errorThrown);
					_this[0].disabled = false;
				}
			});
			
		}
		
		function submitBox(obj, gridid, confirm,mustOne){
			var namespace = _namespace($(obj));
			if(mustOne == undefined)
				mustOne = true;
			var boxlist = $("#" + namespace + " table[id='" + gridid + "'] tbody tr td:first-child").find(":checked");//只选择第一列，否则其他列有复选框会有问题
			if(mustOne && (boxlist == undefined || boxlist.length < 1)){
				var data = {flag:"warn",msg:[{"":"请至少选择一条记录"}]};
				alertMsg4Modal(data);
				return;
			}
			if(confirm == undefined)
				confirm = true;
			if(confirm == true){
				if(!window.confirm('请您确认操作！')){
					return;
				}
			}
			var p, str = "";
			boxlist.each(function(i, val){// 把每个checkbox以及checkbox所在行里的隐藏列的值全取出来
				p = $(val).parent().parent();
				str += "{";
				str += $(val).attr("id")+":'"+$(val).val()+"'";
				p.find("td").each(function(tdI, tdValue) {
					var jTdValue = $(tdValue);
					if (jTdValue.css("display") == "none"
							&& jTdValue.find(":input").length <= 0)
						return;
					if (jTdValue.find(":input").length > 0) {
						jTdValue.find(":input").each(function(key, value) {
							if(value.type == "button" || value.type == "checkbox")
								return;
							str += "," + $(value).attr("id") + ":'" + $(value).val()+"'";
						});
					}
				});
				str += "},";
			});
			var url = obj.name;
			var data = formToJsonString("form1", namespace);
			var jsonData = string2json("{"+data+",checkbox:[" + str + "],funcID:'" + url + "',rqstType:'AJAX'}");
			$.ajax({
				type : "POST",
				url : url,
				data : jsonData,
				dataType : "json",
				success : function(data) {
					//取消全选按钮
					if($("[name='box']")!=undefined && $("[name='box']").is(":checked")==true){
						$("[name='box']").removeAttr("checked");
					}
					if(data.flag == "success")
						successCallback(obj, data);

					$("#" + namespace + " tbody[id='page_" + $("#nowPage").val() + "']").remove();
					showPage($("#nowPage").val(), namespace);
					alertMsg4Modal(data);
					
					//判断隐藏域是否存在值，如果存在，则把已绑定用户（默认第一条）打上勾
					var pd1=document.getElementsByName("opt_no")[0].value;
					var pd2=$("#page_1 tr:eq(0) td:eq(4)").text();
					if(pd1==pd2)
						{
							var a=$("#page_1 tr:eq(0) td:eq(4)").text();
							if(a!=null && a!="")
								{
									document.getElementsByName("checkbox_1")[0].checked="true";
									//为每一个checkbox绑定一个onclick事件c()
									$(".checkbox").each
									(
										function()
										{
											$(this).attr("onclick",'c(this)');		
										}
									)	
								}
						}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					updateTips("status = " + XMLHttpRequest.status + " | readyState = "
							+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
							+ " | errorThrown = " + errorThrown);
				}
			});
		}
</script>
</html>