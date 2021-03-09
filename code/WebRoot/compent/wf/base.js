var msgFlag = false;
$(function() {
	$("#chaxun").click(function() {
		query(this);
	});
	// 所以页面重置按钮
	$("#chongzhi").click(function(){
		resetForm(this, "form1");
	});
	// 返回
	$("#fanhui").click(function() {
		goBack(this);
	});
	$("#xinzenginit").click(function(){
		addInit_bak(this);
	});
	$("#xiugaiinit").click(function(){
		addInit_bak(this);
	});
	$("#xiugai").click(function(){
		mdf(this);
	});
	
	$("#xinzeng").click(function() {
		add(this);
	});
	$("#xiayibu").click(function() {
		add(this);
	});
	
	$("#daoruinit").click(function(){
		addInit(this);
	});
	$("#mobandownload").click(function(){
		var url = $("#mobandownload").attr("name");
		window.location.href= url;
	});
	$("#uploadFile").click(function(){
		var file = document.getElementById("file").value;
		if(file == "") {
			updateTips("请选择上传文件！");
			return;
		}
		var fileType = file.substring(file.lastIndexOf(".")+1);
		if(fileType != "xls") {
			updateTips("上传的excel文件类型为.xls格式！");
			return;
		} 
		var url=$("#fileForm").attr('name');
		var hiddenform=$("#hiddenform").val();
		fileForm.action = url + "?hiddenform="+hiddenform;
		fileForm.submit();
	});
	$("#daochu").click(function(){
		var namespace = _namespace($(this));
		var formValue = formToJsonString("form1,form3", namespace);
		var url = $("#daochu").attr("name");
		form1.action = url + "?" + formValue;
		form1.submit();
	});
	$("#daorufanhui").click(function() {
		$("#daorufanhui")[0].disabled = true;
		var jsonForm = string2json("{" + $("#hiddenform").val() + "}");
		var form = "";
		for (var fieldName in jsonForm) {
			form += "<input type='hidden' id='" + fieldName + "' name='" + fieldName + "' value='" + jsonForm[fieldName] + "'/>";
		}
		$("#fileForm").append(form);
		var url = $("#daorufanhui").attr('name');
		fileForm.action = url + "?funcID="+url;
		fileForm.submit();
	}); 
});

function query(btn, successFunc){
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
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			updateTips("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
					+ " | errorThrown = " + errorThrown);
			_this[0].disabled = false;
		}
	});
	
}

//手动配置要填充的table，girdId为table的id
function query4Id(btn, gridId, successFunc){
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
			$("#"+namespace+" table[id='"+gridId+"'] tbody").remove();
			fillGrid4Id(gridId, data, namespace);
            if (data.flag == "success" && successFunc != null)
            	doCallBackEach(btn, successFunc, data);
            initShowPageQuery(namespace, data);
            var end = new Date();
            $("#" + namespace + " span[id='allrowmsg']").prepend("本次查询用时" + ((end.getTime() - start.getTime()) / 1000.00) + "秒,");
            _this[0].disabled = false;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			updateTips("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
					+ " | errorThrown = " + errorThrown);
			_this[0].disabled = false;
		}
	});
	
}

function add(btn, successFunc){
	var button = $(btn);
	button[0].disabled = true;
	var namespace = _namespace(button);
	var url = button.attr("name");
	if (!checkUi("form1", namespace)) {
		button[0].disabled = false;
		return;
	}
	var data = formToJsonString("form1", namespace);
	var subJson = getTableIdByForm(namespace);// 将tab页中的信息加入
	var jsonData = string2json("{" + data + ",funcID:'" + url + "'" + subJson + ",rqstType:\"AJAX\"}");
	$.ajax({
		type : "POST",
		url : url,
		data : jsonData,
		dataType : "json",
		success : function(data) {
            if (data.flag == "success") {
            	resetForm(btn, "form1");
    			if(successFunc != null)
    				doCallBackEach(btn, successFunc, data);
			}
            alertMsg4Modal(data);
            button[0].disabled = false;
		},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	updateTips("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
					+ " | errorThrown = " + errorThrown);
			button[0].disabled = false;
        }
	});
}

function mdf(btn, successFunc){
	var button = $(btn);
	button[0].disabled = true;
	var namespace = _namespace(button);
	var url = button.attr("name");
	if (!checkUi("form1", namespace)) {
		button[0].disabled = false;
		return;
	}
	var data = formToJsonString("form1", namespace);
	var subJson = getTableIdByForm(namespace);// 将tab页中的信息加入
	var jsonData = string2json("{" + data + ",funcID:'" + url + "'" + subJson + ",rqstType:\"AJAX\"}");
	$.ajax({
		type : "POST",
		url : url,
		data : jsonData,
		dataType : "json",
		success : function(data) {
            if (data.flag == "success" && successFunc != null)
            	doCallBackEach(btn, successFunc, data);
			alertMsg4Modal(data);
			button[0].disabled = false;
		},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	updateTips("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
					+ " | errorThrown = " + errorThrown);
			button[0].disabled = false;
        }
	});
}

function doCallBackEach(btn, funcList, data){
	var param = new Array();
	param.push(btn);
	if(data != undefined && data != "" && data != null)
		param.push(data);
	var funcArray = funcList.split(",");
	$.each(funcArray, function(i, func){
		doCallback(eval(func), param);
	});
}

function addInit(btn){
	var _this = $(btn);
	_this[0].disabled = true;
	var namespace = _namespace(_this);
	var url = _this.attr("name");
	var queryValue = formToJsonString("form1", namespace);
	var formValue = $("#"+namespace+" form[id='form3'] input[id='showpagequery']").val();
	if(formValue == undefined)
		formValue = formToJsonString("form1,form3", namespace);
	else
		formValue += "," + formToJsonString("form3", namespace);
	$("#"+namespace).load(url + "?funcID="+url, string2json("{"+queryValue+",hiddenform:'"+formValue+"',InitPageType:'false'}"), null);
}

function addInit_bak(btn){
	var _this = $(btn);
	_this[0].disabled = true;
	var namespace = _namespace(_this);
	var action = _this.attr("name") + "?funcID=" + _this.attr("name");
	var form = "<form id='form2' name='form2' action='"+action+"' method='post'>";
	if($("#" + namespace + " form[id='form2']")[0] != undefined){
		$("#" + namespace + " form[id='form2']").remove();
	}
	var formValue = $("#"+namespace+" form[id='form3'] input[id='showpagequery']").val();
	if(formValue == undefined)
		formValue = formToJsonString("form1,form3", namespace);
	else
		formValue += "," + formToJsonString("form3", namespace);
	form += "<input type='hidden' id='hiddenform' name='hiddenform' value='"+formValue+"'/>";
	form += "</form>";
	$("#"+namespace).append(form);
	$("#"+namespace + " form[id='form2']")[0].submit();
	_this[0].disabled = false;
}

function goBack(btn){
	var _this = $(btn);
	var namespace = _namespace(_this);
	_this[0].disabled = true;
	var url = _this.attr('name');
	var hiddenform = $("#" + namespace + " input[id='hiddenform']").last().val();
	var jsonForm = string2json("{" + hiddenform + ",hiddenform:'" + hiddenform + "',InitPageType:'false'}");
	$("#"+namespace).load(url + "?funcID="+url, jsonForm, null);
}

function goBacks(obj){
	var url=$(obj).attr("name");
	window.location.href=url+"?funcID="+url;
}

//重置表单
function resetForm(btn, formList){
	var _this = $(btn);
	var namespace = _namespace(_this);
	var fList = formList.split(",");
	for(var i = 0; i < fList.length; i++){
		$("#" + namespace + " form[id='" + fList[i] + "'] :input").each(function(key, value){
			var valueObj = $(value);
			var valueType = value.type;
			if(valueType == "submit")
				return;
			if(valueType == "hidden")
				return;
			if(valueType == "button")
				return;
			if(valueObj.attr("disabled") == "disabled")
				return;
			if(valueObj.attr("readonly") == "readonly")
				return;
			if(valueObj.attr("data-notrst"))
				return;
			if(valueType == "select-multiple"){ // 多选下拉
				valueObj.find("option:selected").prop("selected", false);
				return;
			}
			if(valueType == "select-one"){ // 下拉列表
				valueObj.find("option:first").prop("selected",true);
				return;
			}
			if(valueType == "checkbox" || valueType == "radio"){ // 复选框
				valueObj.prop("checked", false);
				return;
			}
			valueObj.val("");
		});
	}
}

function checkUi(formNames, namespace){
	var formName = formNames.split(",");
	var checkuiMsg = $("#checkui-msg");
	if(checkuiMsg[0] == undefined){
		$("body").append('<div class="modal fade" id="checkui-msg" tabindex="-1" role="dialog" aria-labelledby="msg-titls" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div id="msg-header" class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title" id="checkui-titls"></h4></div><div class="modal-body" id="checkui-body"></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">Close</button></div></div></div></div>');
	}
	var field = "",fieldId = "";
	for (var j = 0; j < formName.length; j++) {
		if (formName[j] != undefined) {
			fields = $("#"+namespace+" form[id='"+formName[j]+"'] :input");
		} else {
			fields = $(":input");
		}
		fields.each(function(i, value){
			field = $(value);
			field.val($.trim(field.val()));
			if(field.css('display') == 'none')
				return;
			fieldId = field.attr("id");
			if(fieldId == undefined || fieldId == "")
				return;
			isNull(value);
			restrictLength(value);
			isPwd(value);
			isEmail(value);
		});
	}
	checkuiMsg = $("#checkui-msg");
	if(checkuiMsg.find("[id='checkui-body']").find("h5").size() > 0){
		checkuiMsg.modal({show:'true'});
		checkuiMsg.on('hidden.bs.modal', function (e) {
			$("#checkui-msg").remove();
		});
		return false;
	}
	return true;
}

/**
 * 
 * @param obj
 * @param gridid
 */
function submitBox4id(obj, gridid, confirm,mustOne){
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
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			updateTips("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
					+ " | errorThrown = " + errorThrown);
		}
	});
}

function submitRow4BtnGrp(obj){
	obj.disabled = true;
	var namespace = _namespace($(obj));
	var p = $(obj).parent().parent().parent().parent().parent();
	var valHtml, valInput;
	var param = "";
	p.find("[type='hidden']").each(function(i, value){
		valHtml =  $(value).outerHTML();
		if(valHtml != "" && valHtml != undefined){
			valInput = $(valHtml);
			param += "," + valInput.attr("id")+ ":'"+valInput.val()+"'";
		}
	});
	var formValue = $("#"+namespace+" form[id='form3'] input[id='showpagequery']").val();
	if(formValue == undefined)
		formValue = formToJsonString("form1,form3", namespace);
	else
		formValue += "," + formToJsonString("form3", namespace);
	var url = obj.name;
	$("#"+namespace).load(url + "?funcID="+url, string2json("{hiddenform:'"+formValue+"'"+param+",InitPageType:'false'}"), null);
}

function getTableIdByForm(namespace) {
	var subJosn = "";
	var tabJson = "";
	$("#"+namespace+" form table").each(function() {
		if ($(this).attr("id") != "" && "tableid" != $(this).attr("id")) {
			// 通过表名获取该表中所有的信息项
			tabJson = getTableData($(this).attr("id"), namespace);
			if (tabJson != "") {
				subJosn += ",'" + $(this).attr("id") + "':" + tabJson;
			}
		}
	});
	return subJosn;
}

// 返回表格中所有的数据的json数据，已备传至后台
function getTableData(table_id, namespace) {
	var trd = "[";
	$("#" + namespace + " table[id='" + table_id + "'] tbody tr").each(function(trI, trValue) {
		var tr = "";
		$(trValue).find("td").each(function(tdI, tdValue){
			var jTdValue = $(tdValue);
			if(jTdValue.css("display") == "none" && jTdValue.find(":input").length <= 0)
				return;
			if(jTdValue.find(":input").length > 0){
				jTdValue.find(":input").each(function(key, value){
					tr = joinJson(tr, attrInputValue($(value)));
				});
			} else {
				tr = joinJson(tr, jTdValue.attr("abbr") + ":'" + jTdValue.html() + "'");
			}
		});
		if(tr.length > 1){
			tr = "{" + tr + "}";
			trd += tr + ",";
		}
	});
	if(trd.length > 1){
		trd = trd.substring(0, trd.length - 1) + "]";
	}
	if (trd == "[") {
		return "";
	}
	return trd;
}

function attrInputValue(input){
	var valueType = input[0].type;
	var json = "";
	if(input.attr("name") == "" || input.attr("name") == undefined)
		return "";
	if(valueType == "select-multiple"){ // 多选下拉
		var selectJson = "";
		input.find("option").each(function(selectI, selectValue){
			var jSelectValue = $(selectValue);
			if(jSelectValue.prop("selected")){
				if(selectJson != ""){
					selectJson += ",";
				}
				selectJson += '{' + input.attr("id") + ':"'+jSelectValue.val()+'"}';
			}
		});
		if(selectJson != "")
			json = joinJson(json, fieldId + ':[' + selectJson + ']');
		return json;
	}
	if(valueType == "checkbox"){ // 复选框
		if(input.prop("checked"))
			json = joinJson(json, input.attr("id") + ':"true"');
		else
			json = joinJson(json, input.attr("id") + ':"false"');
		return json;
	}
	if(valueType == "radio"){ // 单选下拉/单选框
		if(input.prop("checked"))
			json = joinJson(json, input.attr("id") + ':"' + input.val() + '"');
		return json;
	}
	var btval = replaceErrorChar(input.val());
	json = joinJson(json, input.attr("id") + ':"' + btval + '"');
	return json;
}

function formToJsonString(formNames, namespace) {// 将表单转换为josn串
	var fields = null;
	var json = "";
	var formName = formNames.split(",");
	for (var j = 0; j < formName.length; j++) {
		fields = null;
		if (formName[j] != undefined) 
			fields = $("#"+namespace+" form[id='"+formName[j]+"'] :input");
		if(fields == null || fields == undefined)
			continue;
		json = joinJson(json, valFormValue(fields));
	}
	return json; 
}

function valFormValue(fields){
	var nameList = ",", parent = "", fieldId = "", json = ""; // 每个form清空一下
	fields.each(function(i, value){
		var valueObj = $(value);
		parent = valueObj.parent();
		if(parent.is("td") || parent.is("th"))
			return;
		fieldId = valueObj.attr("id");
		if(fieldId == undefined || fieldId == "")
			return;
		if(fieldId == "hiddenform" || fieldId == "showpagequery")
			return;
		if(nameList.indexOf("," + fieldId + ",") > 0)
			return;
		nameList += fieldId + ","; // 取过值的字段都放到这里
		var valueType = value.type;
		if(valueType == "submit")
			return;
		if(valueType == "button")
			return;
		if (value.name == null || value.name == "" || value.name == "hiddenform") 
			return;
		if(valueType == "select-multiple"){ // 多选下拉
			var selectJson = "";
			$("#"+fieldId+" option").each(function(selectI, selectValue){
				var jSelectValue = $(selectValue);
				if(jSelectValue.prop("selected")){
					if(selectJson != ""){
						selectJson += ",";
					}
					selectJson += '{' + fieldId + ':"'+jSelectValue.val()+'"}';
				}
			});
			if(selectJson != ""){
				json = joinJson(json, fieldId + ':[' + selectJson + ']');
			}
			return;
		}
		if(valueType == "checkbox"){ // 复选框
			var boxJson = "";
			var boxList = $("#"+fieldId+"[id='"+fieldId+"']");
			if(boxList.length > 1)
				boxList.each(function(boxI, boxValue){
					var jBoxValue = $(boxValue);
					if(jBoxValue.prop("checked")){
						boxJson = joinJson(boxJson, "{" + fieldId + ':"true"}');
					} else {
						boxJson = joinJson(boxJson, "{" + fieldId + ':"false"}');
					}
				});
			else
				boxList.each(function(boxI, boxValue){
					var jBoxValue = $(boxValue);
					if(jBoxValue.prop("checked")){
						json = joinJson(json, fieldId + ':"true"');
					} else {
						json = joinJson(json, fieldId + ':"false"');
					}
				});
			if(boxJson != "")
				json = joinJson(json, fieldId + ':[' + boxJson + ']');
			return;
		}
		if(valueType == "radio"){ // 单选下拉/单选框
			if(valueObj.prop("checked")){
				json = joinJson(json, fieldId + ':"' + valueObj.val() + '"');
			}
			return;
		}
		if(valueType == "textarea"){ // 单选下拉/单选框
			var str = valueObj.val();
			str = str.replace(/\n/ig,"")
			json = joinJson(json, fieldId + ':"' + str + '"');
			return;
		}
		var btval = replaceErrorChar($(value).val());
		json = joinJson(json, fieldId + ':"' + btval + '"');
	});
	return json;
}

function initShowPageQuery(namespace, data){
	//设置查询条件给分页用
    var showPageForm3 = $("#"+namespace+" form[id='form3']");
    if(showPageForm3[0] == undefined || data.showpagequery == undefined)
    	return;
    var showPage = showPageForm3.find("input[id='showpagequery']");
    if(showPage[0] == undefined){
    	showPageForm3.append("<input type='hidden' id='showpagequery'/>");
    	showPage = showPageForm3.find("input[id='showpagequery']");
    }
    showPage.val(data.showpagequery);
}

function joinJson(json, value){
	if(value == "")
		return json;
	if (json != "")
		json += ',';
	return json + value;
}

function replaceErrorChar(val){
	if(val == null || val == undefined)
		return "";
	var btval = val.replace(/\\/g,"");//替换\为空
	btval = btval.replace(/\"/g,"");//替换引号为空
	btval = btval.replace(/\'/g,"");//替换单引号为空
	return btval;
}

function string2json(strJson){
	strJson = strJson.replace(/\r\n/ig,"");
	strJson = strJson.replace(/\r/gm,"");
	try {
		return JSON.parse(strJson);
	} catch(e) {
		try{
			return (new Function("return "+strJson))();
		} catch(e) {
			try{
				var j = "(" + strJson + ")"; // 用括号将json字符串括起来
				return eval(j); // 返回json对象
			} catch(e){
				updateTips("转换录入信息出错，请升级浏览器" + e);
			}
		}
	}
}

// 将JSON字符串回填到页面
function jsonToPage(jsonStr, namespace, funcList) {
	if(namespace == null || namespace == undefined)
		namespace = "body";
	if (typeof jsonStr == "undefined" || jsonStr == null
			|| jsonStr == undefined) {
		return;
	}
	// 如果有json数据时hiddenform就写页面上的hiddendform
	if (jsonStr.hiddenform != null && jsonStr.hiddenform != "") {
		var form = "<form id='form4' name='form4' action='' method='post'>";
		form += "<input type='hidden' id='hiddenform' name='hiddenform' value=''/>";
		form += "</form>";
		$("#" + namespace).append(form);
		$("#" + namespace + " input[id='hiddenform']").val(jsonStr.hiddenform);
	}
	if (jsonStr.flag == "fail") {
		alertMsg4Modal(jsonStr);
		return;
	}
	var defmap = jsonStr["defmap"];// 返回值默认为defmap不可修改
	for (var key in defmap) { // 循环每个数组
		var defmapkey = defmap[key]; 
		for(var i in defmapkey){
			jsonToValue(i, defmapkey[i], namespace);
		}
	}
	if(funcList != null && funcList != undefined)
		doCallBackEach(null, funcList, jsonStr);
}

function jsonToValue(key, data, namespace){
	var elmt = $("#"+namespace+" [id='"+key+"']");
	if(elmt[0] == undefined){
		return;
	}
	if(elmt[0].type != undefined){ // 界面元素赋值
		if(elmt[0].type == "checkbox" || elmt[0].type == "radio"){
			if(data instanceof Object){// 可能用于一组有多个选项时使用
				for(var dKey in data){
					for(var ddkey in data[dKey]){
						elmt.find("[name='" + ddkey + "']").prop("checked","true");
					}
				}
				return;
			}
			elmt.find("[name='"+key+"']").prop("checked","true");
			if(data == "true")
				elmt.prop("checked","true");//为明细页面只有一个复选框时进行赋值使用
			return;
		}
		if(elmt[0].type == "select-multiple" || elmt[0].type == "select-one"){
			elmt = $("#"+namespace+" select[id='"+key+"']");
			if($.type(data) == "array"){
				elmt.empty();
				elmt.append("<option value=''></option>");
				$.each(data, function(sKey, sVal){
					var aValue = "";
					if(sVal["selected"] != undefined)
						aValue = "selected = 'selected'";
					elmt.append("<option value='"+sVal["code"]+"' " + aValue + ">"+sVal["name"]+"</option>");
				});
				return;
			}
			if(data instanceof Object){
				for(var dKey in data){
					for(var ddkey in data[dKey]){
						elmt.find("option[value='" + ddkey + "']").prop("selected","true");
					}
				}
				return;
			}
		}
		elmt.val(data);
		return;
	}
	var titleArr = new Array();
	var tdArr = new Array();
	var trSize = findTH(titleArr, tdArr, key, namespace);
	var obj = null, axis = "", tr = "";
	var table = $("#" + namespace + " table[id='"+key+"']");
	if(table.find("tbody").length <= 0)
		table.append("<tbody/>");
	table.find("tbody").empty();
	var rowspan = "";
	var rownum=0;
	$.each(data, function(aKey, aValue){
		rownum++;
		obj = null;
		tr = $("<tr/>");
		axis = "";
		rowspan = "";
		for ( var i = 0; i < titleArr.length; i++) { // 写每行数据中的每个单元格
			obj = tdArr[i].clone();
			axis = obj.attr("axis") != undefined ? obj.attr("axis").toLowerCase() : "";
			if(axis == "checkbox")
				obj.attr("data-showbox", "true");
			rowspan = obj.attr("rowspan") != undefined ? obj.attr("rowspan") : "";
			if(rowspan!=1 && rownum%rowspan==0) {
				continue;
			}
			createTD(titleArr, tr, aValue, axis, obj, namespace, i, trSize);
		}
		$("#" + namespace + " table[id='"+key+"'] tbody").append(tr);
	});
}

function successCallback(btn, data){
	var _this = $(btn);
	var cbsuc = _this.attr("data-cbsuc");
	if(cbsuc == undefined || cbsuc == "")
		return;
	doCallBackEach(btn, cbsuc, data);
}

function log(msg){
	if(msgFlag){
		updateTips(msg);
	}
}

jQuery.fn.outerHTML = function(s) {
	return (s) ? this.before(s).remove() : jQuery("<p>").append(
			this.eq(0).clone()).html();
};

// 定义简单Map
function getMap() {// 初始化map_,给map_对象增加方法，使map_像Map
	var map_ = new Object();
	map_.put = function(key, value) {
		map_[key + '_'] = value;
	};
	map_.get = function(key) {
		return map_[key + '_'];
	};
	map_.remove = function(key) {
		delete map_[key + '_'];
	};
	map_.keyset = function() {
		var ret = "";
		for ( var p in map_) {
			if (typeof p == 'string' && p.substring(p.length - 1) == "_") {
				ret += ",";
				ret += p.substring(0, p.length - 1);
			}
		}
		if (ret == "") {
			return ret.split(",");
		} else {
			return ret.substring(1).split(",");
		}
	};
	return map_;
}

function _namespace(btn){
	var namespace = btn.attr("data-ns");
	if(namespace == null || namespace == undefined)
		return "body";
	return namespace;
}

function getOrg(btn, level, name, func){
	var up = $(btn).parent();
	var jsonData = '{level:"' + level + '",name:"' + name + '",rqstType:"AJAX"}';
	var setting = {
		view : {dblClickExpand : false, showLine : true, fontCss : {color : "black"}, showIcon : true, selectedMulti : false},
		data : {simpleData : {enable : true, idKey : "id", pIdKey : "pid", rootPId : "", levelKey : "dept_level", nameKey : "name"}},
		callback : {onClick : function(treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj(treeNode);
			var selectedNode = treeObj.getSelectedNodes()[0];
			if (level != "all") {
				if (level.indexOf(selectedNode.dept_level) == "-1") {
					alert("请选择"+name);
					return;
				}
			}
			// 赋值
			up.find(":input[id='dept_no']").val(selectedNode.id);
			up.find(":input[id='dept_name']").val(selectedNode.name);
			var dept_level = up.find(":input[id='dept_level']");
			if (dept_level[0] != undefined) {
				dept_level.val(selectedNode.dept_level);
			}
			$('#ModalDeptTree').modal('hide');
			if(func != undefined)
			    doCallBackEach(btn, func);
		}}};
	$.ajax({
		type : "POST",
		url : "goDeptTreePage.do",
		data : string2json(jsonData),
		dataType : "json",
		success : function(data) {
			if(data.flag != "success"){
				alertMsg4Modal(data);
				return;
			}
			$.fn.zTree.init($("#ZTreeModalDeptTree"), setting, data.retList);
			var options = "show:'true',backdrop:'static'";
			$('#ModalDeptTree').modal(string2json("{" + options + "}"));
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			updateTips("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = "
					+ textStatus + " | errorThrown = " + errorThrown);
		}
	});
}

function getTree(btn,type,code,name,func){
	var up = $(btn).parent();
	var jsonData = '{type:"' + type + '",code:"' + code + '",name:"'+name+'",rqstType:"AJAX"}';
	var setting = {
		view : {dblClickExpand : false, showLine : true, fontCss : {color : "black"}, showIcon : true, selectedMulti : false},
		data : {simpleData : {enable : true, idKey : "id", pIdKey : "pid", rootPId : "", levelKey : "tree_level", nameKey : "name"}},
		callback : {onClick : function(treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj(treeNode);
			var selectedNode = treeObj.getSelectedNodes()[0];
			if (selectedNode.tree_level != "0") {
				alert("请选择表单数据");
				return;
			}
			// 赋值
			$('#ModalRankSeqTree').modal('hide');
			up.find(":input[id='"+code+"']").val(selectedNode.id);
			up.find(":input[id='"+name+"']").val(selectedNode.name);
			if(func != undefined)
			    doCallBackEach(btn, func);
			
		}}};
	$.ajax({
		type : "POST",
		url : "goTreePage.do",
		data : string2json(jsonData),
		dataType : "json",
		success : function(data) {
			if(data.flag != "success"){
				alertMsg4Modal(data);
				return;
			}
			$.fn.zTree.init($("#ZTreeModalRankSeqTree"), setting, data.retList);
			var options = "show:'true',backdrop:'static'";
			$('#ModalRankSeqTree').modal(string2json("{" + options + "}"));
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = "
					+ textStatus + " | errorThrown = " + errorThrown);
		}
	});
}

jQuery.fn.extend({
	tabs : function(namespace){
		var li_array = $(this).find("li");
		$(li_array).each(function(i, value){
			var link = $(value).find("a").attr("href");
			if(link != "" && link != undefined){
				link = link.substring(1,link.length);
			}
			var data = formToJsonString("form1", namespace);
			if(data != null && data != undefined && data != "")
				data += ",";
			var subJson = getTableIdByForm(namespace);// 将tab页中的信息加入
			var hiddenform = $("#" + namespace + " input[id='hiddenform']").val();// 前一页查询条件
			if(hiddenform == null || hiddenform == undefined)
				hiddenform = "";
			var jsonData = string2json("{" + data + "funcID:'" + link + ".do'" + subJson + ",hiddenform:'" + hiddenform + "',InitPageType:'false'}");
			$("#"+namespace).find("div[id='"+link+"']").load(link + ".do", jsonData, null);
		});
	}
});


function getCourseTree(btn,type,code,name,func){
	var up = $(btn).parent();
	var jsonData = '{type:"' + type + '",code:"' + code + '",name:"'+name+'",rqstType:"AJAX"}';
	var setting = {
		view : {dblClickExpand : false, showLine : true, fontCss : {color : "black"}, showIcon : true, selectedMulti : false},
		data : {simpleData : {enable : true, idKey : "id", pIdKey : "pid", rootPId : "", levelKey : "tree_level", nameKey : "name"}},
		callback : {onClick : function(treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj(treeNode);
			var selectedNode = treeObj.getSelectedNodes()[0];
			if (selectedNode.tree_level != "0") {
				alert("请选择表单数据");
				return;
			}
			// 赋值
			$('#ModalRankSeqTree').modal('hide');
			up.find(":input[id='"+code+"']").val(selectedNode.id);
			up.find(":input[id='"+name+"']").val(selectedNode.name);
			if(func != undefined)
			    doCallBackEach(btn, func);
			
		}}};
	$.ajax({
		type : "POST",
		url : "goTreePage.do",
		data : string2json(jsonData),
		dataType : "json",
		success : function(data) {
			if(data.flag != "success"){
				alertMsg4Modal(data);
				return;
			}
			$.fn.zTree.init($("#ZTreeModalRankSeqTree"), setting, data.retList);
			var options = "show:'true',backdrop:'static'";
			$('#ModalRankSeqTree').modal(string2json("{" + options + "}"));
			
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = "
					+ textStatus + " | errorThrown = " + errorThrown);
		}
	});
}
