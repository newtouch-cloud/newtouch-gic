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
		addInit(this);
	});
	$("#xiugaiinit").click(function(){
		addInit(this);
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
			alert("请选择上传文件！");
			return;
		}
		var fileType = file.substring(file.lastIndexOf(".")+1);
		if(fileType != "xls") {
			alert("上传的excel文件类型为.xls格式！");
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

function query(btn){
	var _this = $(btn);
	var namespace = _namespace(_this);
	_this[0].disabled = true;
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
			$("#"+namespace+" table[id='grid'] tbody").remove();
			debugger;
			fillGrid(data, namespace);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
					+ " | errorThrown = " + errorThrown);
		}
	});
	_this[0].disabled = false;
}

function add(btn){
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
			var datamsg = "";
			$.each(data.msg, function(i, value){
				$.each(value, function(msgKey,msgInfo){
					datamsg += msgKey + msgInfo + "\n";
				});
			});
            if (data.flag == "success") {
            	resetForm(this, "form1");
			}
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
			alert("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
					+ " | errorThrown = " + errorThrown);
        }
	});
	button[0].disabled = false;
}

function mdf(btn){
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
			var datamsg = "";
			$.each(data.msg, function(i, value){
				$.each(value, function(msgKey,msgInfo){
					datamsg += msgKey + msgInfo + "\n";
				});
			});
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
			alert("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
					+ " | errorThrown = " + errorThrown);
        }
	});
	button[0].disabled = false;
}

function addInit(btn){
	var _this = $(btn);
	_this[0].disabled = true;
	var namespace = _namespace(_this);
	var url = _this.attr("name");
	var formValue = formToJsonString("form1,form3", namespace);
	$("#"+namespace).load(url + "?funcID="+url, string2json("{hiddenform:'"+formValue+"'}"), null);
	_this[0].disabled = false;
}

function goBack(btn){
	var _this = $(btn);
	var namespace = _namespace(_this);
	_this[0].disabled = true;
	var url = _this.attr('name');
	var hiddenform = $("#" + namespace + " input[id='hiddenform']").val();
	var jsonForm = string2json("{" + hiddenform + ",hiddenform:'" + hiddenform + "'}");
	$("#"+namespace).load(url + "?funcID="+url, jsonForm, null);
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
			// alert(value.id + "|" + valueType);
			if(valueType == "submit")
				return;
			if(valueType == "button")
				return;
			if(valueObj.attr("disabled"))
				return;
			if(valueType == "select-multiple"){ // 多选下拉
				valueObj.find("option:selected").prop("selected", false);
				return;
			}
			if(valueType == "select-one"){ // 下拉列表
				valueObj.val("");
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
	var info = undefined;
	var aInfo = "";
	for (var j = 0; j < formName.length; j++) {
		if (formName[j] != undefined) {
			fields = $("#"+namespace+" form[id='"+formName[j]+"'] :input");
		} else {
			fields = $(":input");
		}
		fields.each(function(i, value){
			aInfo = isNull(value);
			if(aInfo == ""){
				info += "";
				return;
			}
			if(aInfo != undefined){
				if(info == undefined) info = "";
				info += aInfo + "\n";
			}
		});
	}
	if(info == undefined){
		return true;
	}
	if(info == "undefined"){
		info = "录入有误";
	}
	alert(info);
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

function successCallback(btn, data){
	var _this = $(btn);
	debugger;
	var cbsuc = _this.attr("data-cbsuc");
	if(cbsuc == undefined || cbsuc == "")
		return;
	doCallBackEach(btn, cbsuc, data);
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

/**
 * 
 * @param formId
 *            取值的form id
 * @param btnId
 *            当前按钮
 * @param altInfo
 *            是否显示提示信息;true显示，false不显示
 * @param next_previous
 *            非多table页不设置，多table页时，上一页:previous；下一页:next
 */
function saveForm(formId, btn, altInfo, next_previous, namespace){
	if (!checkUi(formId, namespace)) {
		return;
	}
	var button = $(btn); 
	if(namespace == null || namespace == undefined || namespace == "")
		namespace = _namespace(button);
	button[0].disabled = true;
	var data = formToJsonString(formId, namespace);
	var subJson = getTableIdByForm();// 将tab页中的信息加入
	var url = button.attr("name");
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
				$("#"+namespace+" form[id='"+formId+"']")[0].reset();
			}
            if(altInfo){
    			var msgList = new Array;
    			var msgInfo = new Object();
    			msgInfo.msg = datamsg;
    			msgList.push(msgInfo);
    			var alertmsg = new Object();
    			alertmsg.msgType = "normal";
    			alertmsg.msg = msgList;
    			alertMsg(alertmsg);
            }
            if(next_previous != "" && next_previous != undefined){
            	$("#"+namespace+" a[href='#"+button.attr(next_previous)+"']").tab('show');
            }
		},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
			alert("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
					+ " | errorThrown = " + errorThrown);
        }
	});
	button[0].disabled = false;
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
		var tr = "{";
		$(trValue).find("td").each(function(tdI, tdValue){
			var jTdValue = $(tdValue);
			if(jTdValue.css("display") == "none"){
				return;
			}
			if(jTdValue.find(":input").length > 0){
				tr += jTdValue.attr("abbr") + ":'" + jTdValue.find(":input").eq(0).val() + "',";
			} else {
				tr += jTdValue.attr("abbr") + ":'" + jTdValue.html() + "',";
			}
			 
		});
		if(tr.length > 1){
			tr = tr.substring(0, tr.length - 1) + "}";
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

function formToJsonString(formNames, namespace) {// 将表单转换为josn串
	var fields = null;
	var json = "", fieldId = "", nameList = "";
	var formName = formNames.split(",");
	for (var j = 0; j < formName.length; j++) {
		fields = null;
		if (formName[j] != undefined) {
			fields = $("#"+namespace+" form[id='"+formName[j]+"'] :input");
		}
		if(fields == null || fields == undefined){
			continue;
		}
		nameList = ","; // 每个form清空一下
		fields.each(function(i, value){
			var valueObj = $(value);
			fieldId = valueObj.attr("id");
			if(nameList.indexOf("," + fieldId + ",") > 0){
				return;
			}
			nameList += fieldId + ","; // 取过值的字段都放到这里
			var valueType = value.type;
			if(valueType == "submit"){
				return;
			}
			if(valueType == "button"){
				return;
			}
			if (value.name == null || value.name == "" || value.name == "hiddenform") {
				return;
			}
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
				$("#"+fieldId+"[id='"+fieldId+"']").each(function(boxI, boxValue){
					var jBoxValue = $(boxValue);
					if(jBoxValue.prop("checked")){
						if(boxJson != ""){
							boxJson += ",";
						}
						boxJson += '{' + fieldId + ':"'+jBoxValue.val()+'"}';
					}
				});
				if(boxJson != ""){
					json = joinJson(json, fieldId + ':[' + boxJson + ']');
				}
				return;
			}
			if(valueType == "radio"){ // 单选下拉/单选框
				if(valueObj.prop("checked")){
					json = joinJson(json, fieldId + ':"' + valueObj.val() + '"');
				}
				return;
			}
			if(fieldId == 'nowPage'){ // TODO 需要修改
				$(value).val("1");
			}
			json = joinJson(json, fieldId + ':"' + $(value).val() + '"');
		});
	}
	return json;
}

function joinJson(json, value){
	if (json != "") {
		json += ',';
	}
	return json + value;
}

function string2json(strJson){
	strJson = strJson.replace(/\r\n/ig,""); 
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
				alert("转换录入信息出错，请升级浏览器" + e);
			}
		}
	}
}

// 将JSON字符串回填到页面
function jsonToPage(jsonStr, namespace) {
	if(namespace == null || namespace == undefined)
		namespace = "body";
	if (typeof jsonStr == "undefined" || jsonStr == null
			|| jsonStr == undefined) {
		return;
	}
	if (jsonStr.fail != undefined) {
		alert(jsonStr.fail);
	}
	// 如果有json数据时hiddenform就写页面上的hiddendform
	if (jsonStr.hiddenform != null && jsonStr.hiddenform != "") {
		var form = "<form id='form4' name='form4' action='' method='post'>";
		form += "<input type='hidden' id='hiddenform' name='hiddenform' value=''/>";
		form += "</form>";
		$("#" + namespace).append(form);
		$("#" + namespace + " input[id='hiddenform']").val(jsonStr.hiddenform);
	}
	var defmap = jsonStr["defmap"];// 返回值默认为defmap不可修改
	for (var key in defmap) { // 循环每个数组
		var defmapkey = defmap[key]; 
		for(var i in defmapkey){
			jsonToValue(i, defmapkey[i], namespace);
		}
	}
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
	// 表格赋值
	var bodyArray = joinTable(key, data, data.length, "1", data.length, namespace);
	elmt.append(bodyArray.join(""));
	elmt.find("tbody[id='page_1']").show();
}
function log(msg){
	if(msgFlag){
		alert(msg);
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

function getOrg(level,name){	
	var someValue = "";
	if(document.getElementById) //IE 
	{
	    feature="dialogWidth:500px;dialogHeight:500px;status:no;help:no"; 
	    someValue = window.showModalDialog(encodeURI("Branch/queryBranchTreePage.do?level="+level+"&name="+name),null,feature);
	    
		if(someValue != null){
			$("#dept_no").val(someValue.split("#")[0]);
			$("#dept_name").val(someValue.split("#")[1]);
			if($("#dept_level")){
				$("#dept_level").val(someValue.split("#")[2]);				
			}
		}
	} 
	else 
	{ 
		feature ="top=100,left=500,width=500,height=500,menubar=no,toolbar=no,location=no,"; 
		feature+="scrollbars=no,status=no,modal=yes"; 
		window.open(encodeURI("goDeptTreePage.do?level="+level+"&name="+name),"new",feature); 
	} 

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
			var jsonData = string2json("{" + data + "funcID:'" + link + ".do'" + subJson + ",hiddenform:'" + hiddenform + "'}");
			$("#"+link).load(link + ".do", jsonData, null);
		});
	}
});


//---------------------------------------------------------------CACore使用 begin-------------------------------------------------------------------------------------------------------------------------
/**
 * 滚屏函数
 * @param element
 */
function scrollToElement(element){
    var pos = element.offset().top;
    $("html,body").animate({scrollTop: pos}, 500);
}

/**
 * 异步提示信息
 * @param alertTarget
 * @param level
 * @param msg
 * @param handler
 */
function alertInfo(alertTarget, level, msg, handler) {
    $("#msg").remove();

    alertTarget.prepend("<div class='alert alert-"+level+"' id ='msg' name='msg'><strong>提示信息：</strong> " + msg + "</div>");

    if (handler) {
        setTimeout(function() {
            handler.call();
        }, 1000);//停1秒
    }

    if ($("#msg").length > 0) {
        setTimeout(function() {

            $("#msg").hide(600, function() {
                $("#msg").remove();


            });
        }, 5000);//停5秒
    }
}
//---------------------------------------------------------------CACore使用 end-------------------------------------------------------------------------------------------------------------------------