function callAjax() {
	// 传输数据
	var dataXml = joinData(arguments);
	if (dataXml == null || dataXml == '') {
		return;
	}
	// 生成URL
	$.ajax({
		type : "POST",
		url : "callAjax.do",
		data : JSON.decode(dataXml),
		dataType : "json",
		success : function(data) {
			if (data.retList == undefined) {
				return;
			}
			$.each(data.retList, function(key, value) {
				input(key, value);
				focus(key, value);
				alertInfo(key, value);
				style(key, value);
				readonly(key, value);
				disabled(key, value);
				edit(key, value);
				select(key, value);
			});
		}
	});
}

function input(key, value) {
	// alert(key);
	if (key != "input")
		return;
	$.each(value, function(key, value) {
		// alert("input | "+key+" | "+value);
		$("#" + key).val(value);
	});
}

function focus(key, value) {
	if (key != "focus")
		return;
	$.each(value, function(key, value) {
		// alert("focus | "+key+" | "+value);
		if (value != "none")
			$("#" + key).val(value);
		$("#" + key).focus();
	});
}

function alertInfo(key, value) {
	if (key != "alert")
		return;

	// alert("alertInfo");
	var msgList = new Array;
	$.each(value, function(key, value) {
		var msgInfo = new Object();
		msgInfo.msg = key + ":" + value;
		msgList.push(msgInfo);
	});

	var obj = new Object();
	obj.msgType = "normal";
	obj.msg = msgList;
	alertMsg(obj);
}

function style(key, value) {
	if (key != "style")
		return;
	$.each(value, function(key, value) {
		// alert("style | "+key+" | "+value);
		$("#" + key).css("color", value);
	});
}

function msg(key, value) {
	if (key != "msg")
		return;
	$.each(value, function(key, value) {
		// alert("msg | "+key+" | "+value);
		$("#" + key).val(value);
	});
}

function disabled(key, value) {
	if (key != "disabled")
		return;
	$.each(value, function(key, value) {
		// alert("disabled | "+key+" | "+value);
		if (value != "none")
			$("#" + key).val(value);
		$("#" + key).attr("disabled", "disabled");
	});
}

function edit(key, value) {
	if (key != "edit")
		return;
	// alert("edit.type" + key);
	$.each(value, function(key, value) {
		// alert("edit | "+key+" | "+value);
		if (value != "none")
			$("#" + key).val(value);
		$("#" + key).removeAttr("readonly");
		$("#" + key).removeAttr("disabled");
	});
}

function readonly(key, value) {
	if (key != "readonly")
		return;
	$.each(value, function(key, value) {
		// alert("readonly | "+key+" | "+value);
		if (value != "none")
			$("#" + key).val(value);
		$("#" + key).attr("readonly", "readonly");
	});
}

function select(key, value) {
	if (key != "select")
		return;
	$.each(value, function(key, value) {
		// alert("readonly | "+key+" | "+value);
		addSelectOption(key, value);
	});
}

// 拼接上传值的JSON串
function joinData(arguments) {
	// 拼接业务类和方法
	var xml = "'classname':'" + arguments[0] + "','method':'" + arguments[1]
			+ "'";
	var aValue = "";
	for ( var i = 2; i < arguments.length; i++) {
		aValue = $("#" + arguments[i] + "").val();
		// alert(arguments[i] + "|" +aValue);
		xml += ",'" + arguments[i] + "':'" + aValue + "'";
	}
	// alert("{" + xml + "}");
	return "{" + xml + "}";
}

// 对下拉列表写值
function addSelectOption(key, value) {
	// alert("select | " + key + " | " + value);
	// 得到界面元素中下拉列表
	var slctArg = $("#" + key);
	// alert(slctArg);
	// 界面元素不存在则返回
	if (slctArg == null) {
		return;
	}
	// 先清空下拉列表
	slctArg.empty();
	slctArg.append("<option value=''></option>");
	var selectedValue = "";
	$.each(value, function(key, value) {
		// alert("select value | "+key+" | "+value);
		if (key == "selected") {
			selectedValue = value;
			return;
		}
		slctArg.append("<option value='" + key + "'>" + value + "</option>");
	});
	// alert(selectedValue);
	if (selectedValue != "")
		$("#" + key).attr("value", selectedValue);
}

// 弹出提示信息
function alertMsg(data) {
	var normalMmsg = $("<div id='normal-msg' class='normalMmsg' title='消息提示' iconCls='icon-ok' closed='true' "
			+ "modal='true' collapsible='fasle' minimizable='fasle' maximizable='fasle' draggable='false' "
			+ "style='width:500px;height:200px;display:none;'>"
			+ "<div class='normalMmsg_TIT'><span>信息提示</span><a class='close'id='msg_close' title='关闭' ></a></div>"
			+ "<div class='normalMmsg_TIT_layout' fit='true'>"
			+ "<div id='show-normal-msg' region='center' border='false' style='padding:10px;background:#fff;border:1px solid #ccc;width:100%;height:100%;'>"
			+ "</div></div></div>");
	if (data && data.msgType) {

		$("body").prepend(normalMmsg);
		// $('#normal-msg').click(function(){
		// $(this).hide();
		// });
		$("#msg_close").click(function() {
			$('#normal-msg').hide();
		});
		$("#msg_close").mouseover(function() {
			$(this).css("background-position", "-286px -434px");
		});
		$("#msg_close").mouseout(function() {
			$(this).css("background-position", "-286px -418px");
		});
		var msg = "";
		for ( var index in data.msg) {
			msg += data.msg[index].msg + "<br>";
		}
		if (data.msgType == 'error') {
			$('#show-error-msg').html(msg);
			$('#error-msg').css("display", "");
			$('#error-msg').window('open');
		} else if (data.msgType == 'warning') {
			$('#nextDoController').val(data.nextDoController);
			$('#show-warning-msg').html(msg);
			$('#warning-msg').css("display", "");
			$('#warning-msg').window('open');
		} else if (data.msgType == 'normal') {
			$('#show-normal-msg').html(msg);
			$('#normal-msg').css("display", "block");
			// $('#normal-msg').window('open');
			if (data.returnValue) {
				JSON.jsonObjectToElementsValue(data.returnValue);
			}
			return true;
		} else {
			$.messager.alert('异常信息', '未知的消息类型!');
		}
		return false;
	}
	return true;

}