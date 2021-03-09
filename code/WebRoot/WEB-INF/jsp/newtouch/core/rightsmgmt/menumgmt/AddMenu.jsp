<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../../core/pub/basecss.jsp"%>

</head>

<body id="body">
	<form id="form1" name="form1" class="form-horizontal" action="addMenuMgmt.do"
		method="post">
		<div class="panel panel-default">
    <div class="panel-heading">
      <div class="panel-title">
        新增菜单 <span class="pull-right"><a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> 收缩</a> </span>
      </div>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="panel-body">
          <div class="row">
            <div class="col-xs-12">
              <div class="input-group">
                <span class="input-group-addon"> <label for="menu_name" class="control-label">菜单名称</label></span> 
                <input type="text" class="col-sm-12 form-control" id="menu_name"
				    name="menu_name" notnull/>
			     <span class="not-null">*</span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <div class="input-group">
              <span class="input-group-addon"> <label for="menu_url" class="control-label">菜单事件</label></span> 
                <input type="text" class="col-sm-12 form-control" id="menu_url"
				    name="menu_url"/>
				<input type="hidden" class="form-control" id="menu_seq" 
			    name="menu_seq" value="${nodeseq}" />
			    <span class="not-null">&nbsp;</span>
	          </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <div class="input-group">
              <span class="input-group-addon"> <label for="parent_name" class="control-label">上级菜单名称</label></span> 
				<input type="text" class="col-sm-12 form-control" id="parent_name"
				name="parent_name" value="${nodename}" disabled/> 
			<input type="hidden" class="form-control" id="parent_no" 
			    name="parent_no" value="${nodeno}" />
			     <span class="not-null">*</span>
	          </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <div class="input-group">
              <span class="input-group-addon"> <label for="menu_order" class="control-label">显示顺序</label></span> 
                <input type="text" class="col-sm-12 form-control" id="menu_order"
				    name="menu_order" notnull/>
			     <span class="not-null">*</span>
	          </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <div class="input-group">
              <span class="input-group-addon"> <label for="menu_status" class="control-label">菜单状态</label></span> 
                <select class="col-sm-12 form-control" id="menu_status" name="menu_status" notnull>
					<option value=""></option>
	                <option value="1">有效</option>
	                <option value="0">无效</option>			
				</select>
			     <span class="not-null">*</span>
	          </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <div class="input-group">
              <span class="input-group-addon"> <label for="menu_type" class="control-label">菜单类型</label></span> 
                			<select class="col-sm-12 form-control" id="menu_type" name="menu_type" notnull>
				<option value=""></option>
                <option value="menu">菜单</option>
                <option value="button">按钮</option>
			</select>
			     <span class="not-null">*</span>
	          </div>
            </div>
          </div>
          <div class="col-md-12 text-center panel-body">
			<span><button id="xinzengnew" name="addMenuMgmt.do"
					type="button" class="btn btn-default btn-sm">新增</button> </span> <span><button
					id="chongzhi" type="button" class="btn btn-default btn-sm">重置</button>
			</span>
		</div>
      </div>
    </div>
  </div>
		
	</form>

</body>
<%@ include file="../../../core/pub/basejs.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#xinzengnew").click(function() {
			var namespace = _namespace($(this));
			if (!checkUi("form1",namespace)) {
				return;
			}
			var data = formToJsonString("form1", namespace);
			var subJson = getTableIdByForm();// 将tab页中的信息加入
			var url = $("#xinzengnew").attr("name");
			log(url);
			log("{" + data + ",funcID:'" + url + "'" + subJson + ",rqstType:\"AJAX\"}");
			var jsonData = string2json("{" + data + ",funcID:'" + url + "'" + subJson + ",rqstType:\"AJAX\"}");
			
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
		            if (data.flag == "success") {
						form1.reset();
						document.getElementById("parent_name").value = "";
						document.getElementById("parent_no").value = "";
						//刷新左侧机构树
						window.parent.frames['leftTreeFrame'].location.reload();
					}
					var msgList = new Array;
					var msgInfo = new Object();
					msgInfo.msg = datamsg;
					msgList.push(msgInfo);
					var alertmsg = new Object();
					alertmsg.msgType = "normal";
					alertmsg.msg = msgList;
					alertMsg(alertmsg);
					$("#xinzengnew")[0].disabled = false;
				},
		        error:function(XMLHttpRequest, textStatus, errorThrown) {
		            alert(XMLHttpRequest.status);
		            alert(XMLHttpRequest.readyState);
		            alert(textStatus);
		            alert(errorThrown);
		        }
			});
		});
	});
</script>
</html>
