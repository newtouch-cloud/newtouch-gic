function callAjax() {
	// 传输数据
	var btn = $(arguments[2]);
	var namespace = _namespace(btn);
	if(namespace == "body"){
		updateTips("未增加data-ns属性,无法触发.");
		return;
	}
	var dataXml = joinData(arguments, namespace, btn);
	if (dataXml == null || dataXml == '') {
		return;
	}
	// 生成URL
	$.ajax({
		type : "POST",
		url : "callAjax.do",
		data : string2json(dataXml),
		dataType : "json",
		success : function(data) {
			if (data.flag != undefined) {
				alertMsg4Modal(data);
				return;
			}
			if (data.retList == undefined)
				return;
			var scope = $("#" + namespace);
			$.each(data.retList, function(key, value) {
				if(value["ajaxscope"] == "table")
					scope = btn.parent().parent();
				input(key, value, namespace, scope);
				focus(key, value, namespace, scope);
				alertInfo(key, value, namespace);
				style(key, value, namespace, scope);
				readonly(key, value, namespace, scope);
				disabled(key, value, namespace, scope);
				edit(key, value, namespace, scope);
				select(key, value, namespace, scope);
				grid(key, value, namespace);
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = "
					+ textStatus + " | errorThrown = " + errorThrown);
		}
	});
}

function input(aKey, value, namespace, scope) {
	// alert(key);
	if (aKey != "input")
		return;
	$.each(value, function(key, value) {
		// alert("input | "+key+" | "+value);
		scope.find(":input[id='" + key + "']").val(value);
	});
}

function focus(aKey, value, namespace, scope) {
	if (aKey != "focus")
		return;
	var input;
	$.each(value, function(key, value) {
		// alert("focus | "+key+" | "+value);
		input = scope.find(":input[id='" + key + "']");
		if (value != "none")
			input.val(value);
		input.focus();
	});
}

function alertInfo(key, value, namespace) {
	if (key != "alert")
		return;
	// alertMsg4Modal(data);
	// alert("alertInfo");
	$.each(value, function(key, value) {
		updateTips(value);
	});
}

function style(aKey, value, namespace, scope) {
	if (aKey != "style")
		return;
	$.each(value, function(key, value) {
		// alert("style | "+key+" | "+value);
		scope.find(":input[id='" + key + "']").css("color", value);
	});
}

function msg(aKey, value, namespace, scope) {
	if (aKey != "msg")
		return;
	$.each(value, function(key, value) {
		// alert("msg | "+key+" | "+value);
		scope.find(":input[id='" + key + "']").val(value);
	});
}

function disabled(aKey, value, namespace, scope) {
	if (aKey != "disabled")
		return;
	var input;
	$.each(value, function(key, value) {
		// alert("disabled | "+key+" | "+value);
		input = scope.find(":input[id='" + key + "']");
		if (value != "none")
			input.val(value);
		input.attr("disabled", "disabled");
	});
}

function edit(aKey, value, namespace, scope) {
	if (aKey != "edit")
		return;
	// alert("edit.type" + key);
	var input;
	$.each(value, function(key, value) {
		// alert("edit | "+key+" | "+value);
		input = scope.find(":input[id='" + key + "']");
		if (value != "none")
			input.val(value);
		input.removeAttr("readonly");
		input.removeAttr("disabled");
	});
}

function readonly(aKey, value, namespace, scope) {
	if (aKey != "readonly")
		return;
	var input;
	$.each(value, function(key, value) {
		// alert("readonly | "+key+" | "+value);
		input = scope.find(":input[id='" + key + "']");
		if (value != "none")
			input.val(value);
		input.attr("readonly", "readonly");
	});
}

function select(aKey, value, namespace, scope) {
	if (aKey != "select")
		return;
	$.each(value, function(key, value) {
		// alert("readonly | "+key+" | "+value);
		addSelectOption(key, value, namespace, scope);
	});
}

function grid(key, value, namespace) {
	if (key != "grid")
		return;
	var titleArr = new Array();
	var tdArr = new Array();
	var obj = null, axis = "", tr = "", trSize = null, table = null, tbody = null;
	$.each(value, function(gridId, value) {
		// alert("readonly | "+key+" | "+value);
		trSize = findTH(titleArr, tdArr, gridId, namespace);
		table = $("#" + namespace + " table[id='" + gridId + "']");
		if (table.find("tbody").length <= 0)
			table.append("<tbody/>");
		tbody = table.find("tbody");
		tbody.empty();
		$.each(value, function(aKey, aValue) {
			obj = null;
			tr = $("<tr/>");
			axis = "";
			for (var i = 0; i < titleArr.length; i++) { // 写每行数据中的每个单元格
				obj = tdArr[i].clone();
				axis = obj.attr("axis") != undefined ? obj.attr("axis")
						.toLowerCase() : "";
				if (axis == "checkbox")
					obj.attr("data-showbox", "true");
				createTD(titleArr, tr, aValue, axis, obj, namespace, i,
						trSize);
			}
			tbody.append(tr);
		});
	});
}

// 拼接上传值的JSON串
function joinData(arguments, namespace, btn) {
	var formValue = formToJsonString("form1", namespace);
	var subJson = getTableIdByForm(namespace);
	var json = "'classname':'" + arguments[0] + "','method':'" + arguments[1]
			+ "','_this':'"+btn.val()+"',rqstType:'AJAX'";
	if(formValue != "")
		json += "," + formValue;
	if(subJson != "")
		json += subJson;
	return "{" + json + "}";
}

// 对下拉列表写值
function addSelectOption(aKey, value, namespace, scope) {
	if(scope.is("tr") || scope.is("td")){
		addSelectOptionTd(aKey, value, namespace, scope);
		return;
	}
	//alert("select | " + key + " | " + value);
	// 得到界面元素中下拉列表
	var slctArg = scope.find(":input[id='" + aKey + "']");
	// alert(slctArg);
	// 界面元素不存在则返回
	if (slctArg[0] == undefined) {
		return;
	}
	// 先清空下拉列表
	slctArg.find("option").remove();
	slctArg.append("<option value=''></option>");
	$.each(value, function(sKey, sVal) {
		var selected = "";
		if(sVal["selected"] != undefined)
			selected = "selected = 'selected'";
		slctArg.append("<option value='"+sVal["code"]+"' " + selected + ">"+sVal["name"]+"</option>");
	});
}


//对下拉列表写值
function addSelectOptionTd(aKey, value, namespace, scope) {
	//alert("select | " + key + " | " + value);
	// 得到界面元素中下拉列表
	var slctArg = scope.find(":input[id='" + aKey + "']");
	var td = slctArg.parent();
	slctArg.remove();
	// alert(slctArg);
	// 界面元素不存在则返回
	if (slctArg[0] == undefined) {
		return;
	}
	// 先清空下拉列表
	slctArg.find("option").remove();
	slctArg.append("<option value=''></option>");
	$.each(value, function(sKey, sVal) {
		var selected = "";
		if(sVal["selected"] != undefined)
			selected = "selected = 'selected'";
		slctArg.append("<option value='"+sVal["code"]+"' " + selected + ">"+sVal["name"]+"</option>");
	});
	td.append(slctArg);
}