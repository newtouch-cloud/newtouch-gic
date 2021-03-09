<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../../../core/pub/basecss.jsp"%>
<%@ include file="../../../core/pub/basejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script type="text/javascript">


	function selectnew(sltName,value){
		if($("[name="+sltName+"]").length > 0){
			$("[name="+sltName+"]").val(value);
		}
	}
	
	// 将JSON字符串回填到主表上
	function fillElementsValue(key,jsonStr,index){
		if (jsonStr instanceof Array) {
			for(var index in jsonStr){
	            var obj=jsonStr[index];
	            fillElementsValue(key,obj,index);
	        }                  
		} else if (jsonStr instanceof Object){
			for(var fieldName in jsonStr){
	            var fieldValue=jsonStr[fieldName];
	            var fields=document.getElementsByName(fieldName);
	            if(fields.length==0){
	            	continue ;
	            }
	            var fieldTypeName=fields[0].type;
	            switch (fieldTypeName) {
	            	case 'select-one':
	            		selectnew(fieldName, fieldValue);
						continue;
				}
	            fields[index].value=fieldValue;
	        }
		} else {
			alert("在自动解析JSON时，遇到未知类型！");
	    }
	}
	
	// 将JSON字符串回填到页面
	function jsonStrToElementsValue(jsonStr){
	    if (typeof jsonStr == "undefined" || jsonStr == null) {
	    	return;
		}
	    if(jsonStr.fail != undefined){						
	    	alert(jsonStr.fail);
	    }

	    $.each(jsonStr.defmap, function(i, value){
			$.each(value, function(msgKey,msgInfo){
				fillElementsValue(msgKey,msgInfo,0);
			});
		});
	    	
	}
	

	//将页面内容拼成字符串，如："e_id=123&name=123"
	function elementsToValueString(form_name){
		var fields=null;
		if(form_name){
			fields = document.forms[form_name].elements;
		}else{
			fields = $(":input");
		}		
		var fieldNameValue = [];// 装载属性及对应的值
		var fieldNameNum = [];// 记录属性出现次数,处理数组
		var fieldValue = "'empty':'1'";
		
		for ( var i = 0; i < fields.length; i++) {
			
			var fieldTypeName = fields[i].type;
			var fieldName = fields[i].name;
			if (fieldName == null || fieldName == "") {
				continue;
			}
			var fieldValues = fieldNameValue[fieldName];
			var num = fieldNameNum[fieldName];
			num = num == null ? 0 : num + 1;
			var value = "";
			if (fields[i].value != null && fields[i].value != "") {
				value = fields[i].value;
			}			
			if(value != ""){			
				fieldValue += ",'"+fieldName + "':'" + value +"'" ;
			}
		}
		return fieldValue;
	}
	
	$(function (){
		jsonStrToElementsValue(${json});
		$("#xiugainew").click(function() {
			if(checkUi("form1")){
				$("#xiugainew")[0].disabled = true;
				var data = elementsToValueString("form1");
				var subJson = "" ; 
				subJson= getTableIdByForm();// 将tab页中的信息加入
				var url = $("#xiugainew").attr("name");
				var jsonData = string2json("{"+data+",funcID:'"+url+"'"+subJson + ",rqstType:\"AJAX\"}");
				$.ajax({
					type: "POST",
					url: url,
					data: jsonData,
					dataType: "json",
					success:function(data){
						var datamsg = "";
						$.each(data.msg, function(i, value){
							$.each(value, function(msgKey,msgInfo){
								datamsg += msgKey + msgInfo + "\n";
							});
						});
						if (data.flag == "success") {
							//form1.reset();
							window.frames.document.getElementById("leftTreeFrame")
							//window.parent.document.frames('leftTreeFrame').location.reload();
							//刷新tree
							window.parent.frames['leftTreeFrame'].location.reload();
						}
						$("#xiugainew")[0].disabled = false;
						var msgList = new Array;
						var msgInfo = new Object();
						msgInfo.msg = datamsg;
						msgList.push(msgInfo);
						var alertmsg = new Object();
						alertmsg.msgType = "normal";
						alertmsg.msg = msgList;
						alertMsg(alertmsg);
					},
					error:function(XMLHttpRequest, textStatus, errorThrown) {
			            alert(XMLHttpRequest.status);
			            alert(XMLHttpRequest.readyState);
			            alert(textStatus);
			            alert(errorThrown);
			        }
				});
			}
		});
	});
	</script>
</head>

<body id="body">
	<form id="form1" name="form1" class="form-horizontal" action=""
		method="post">
	    <div class="panel panel-default">
    <div class="panel-heading">
      <div class="panel-title">
        修改菜单 <span class="pull-right"><a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> 收缩</a> </span>
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
              <span class="input-group-addon"> <label for="menu_no" class="control-label">菜单编号（必须维一）</label></span> 
                <input type="text" class="col-sm-12 form-control" id="menu_no"
				    name="menu_no" disabled/>
	          </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <div class="input-group">
              <span class="input-group-addon"> <label for="menu_url" class="control-label">菜单事件</label></span> 
                <input type="text" class="col-sm-12 form-control" id="menu_url"
				    name="menu_url"/>
	          </div>
            </div>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <div class="input-group">
              <span class="input-group-addon"> <label for="parent_name" class="control-label">上级菜单名称</label></span> 
				<input type="text" class="col-sm-12 form-control" id="parent_name"
				name="parent_name" " disabled/> 
			<input type="hidden" class="form-control" id="parent_no" 
			    name="parent_no"  />
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
			<span><button id="xiugainew" name="updateMenuMgmt.do"
					type="button" class="btn btn-default btn-sm">修改</button> </span>
		</div>
      </div>
    </div>
  </div>
		
	</form>

</body>

</html>