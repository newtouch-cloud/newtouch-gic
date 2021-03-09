function callAjax() {
	// 传输数据
	var dataXml = joinData(arguments);
	if (dataXml == null || dataXml == '') {
		return;
	}
	// 生成URL
	$.ajax({
		type : "POST",
		url : "callAjax.controller",
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