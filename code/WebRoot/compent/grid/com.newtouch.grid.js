function fillGrid(json, namespace) {
	if(namespace == null || namespace == undefined)
		namespace = "body";
	fillGrid4Id("grid", json, namespace);
}

function fillGrid4Id(gridId, json, namespace) {
	if(json != undefined && json.hiddenform != undefined){
		var jsonForm = string2json("{"+json.hiddenform+"}");
		for (var fieldName in jsonForm) {
			$("#" + namespace + " form[id='form1'] input[name='"+fieldName+"']").val(jsonForm[fieldName]);
		}
	}
	if (json != undefined) {
		json2Page(gridId, json, namespace);
	}
}

function json2Page(gridId, data, namespace) {
	value2Grid(gridId, data, namespace);
	$("#"+namespace+" tbody[id=page_" + data.nowPage+"]").show();
	fillPagination(data.pageAll, data.nowPage, data.rowAll, namespace);
	darwWranMsg("msg", data);
	darwErrorMsg("msg", data);
}

function darwWranMsg(gridId, data) {
	if (data.warn == undefined || data.warn == "") {
		return "";
	}
	var ulMsg = "<ul class=blue>";
	$.each(data.warn, function(key, value) {
		$.each(value, function(valueKey, valueValue) {
			ulMsg += "<li>" + valueKey + " | " + valueValue + "</li>";
		});
	});
	ulMsg += "</ul>";
	$("#" + gridId + " ").append(ulMsg);
}
function darwErrorMsg(gridId, data) {
	if (data.fail == undefined || data.fail == "") {
		return "";
	}
	var ulMsg = "<ul class=red>";
	$.each(data.fail, function(key, value) {
		$.each(value, function(valueKey, valueValue) {
			ulMsg += "<li>" + valueKey + " | " + valueValue + "</li>";
		});
	});
	ulMsg += "</ul>";
	$("#" + gridId + "").append(ulMsg);
}

function value2Grid(gridId, data, namespace) {
	if (data.retList == undefined || data.retList == "") {
		$("#" + namespace + " span[id='allrowmsg']").text("共[0]条记录");
		return "";
	}
	var bodyArray = joinTable(gridId, data.retList, data.row4Page, data.nowPage, data.returnRows, namespace);
	$("#" + namespace +" table[id=" + gridId+ "]").append(bodyArray.join(""));
	if($("#" + namespace + " input[id='maxPage']")[0] == undefined){
		$("#" + namespace + " table[id='" + gridId + "']").append("<input type='hidden' id='maxPage' value='0'/>");
	}
	$("#" + namespace + " input[id='maxPage']").val(data.nowPage + 2); // 当前加载后的最大页面
	if($("#" + namespace + " input[id='allRows']")[0] != undefined){ //如果已经存在记录，表示为点击页码时进行的调用
		$("#" + namespace + " input[id='allPage']").val(data.pageAll); // 所有页(赋值，供分页使用)
		$("#" + namespace + " input[id='allRows']").val(data.rowAll); // 所有记录(赋值，供分页使用)
	}
	$("#" + namespace + " span[id='allrowmsg']").text("共["+data.rowAll+"]条记录");
}

/**
 * 
 * @param gridId
 *            table id
 * @param data
 *            数据
 * @param row4Page
 *            每页行数
 * @param nowPage
 *            当前页
 * @param returnRows
 *            总记录数
 * @returns {Array} 拼接好的字符串
 */
function joinTable(gridId, data, row4Page, nowPage, returnRows, namespace) {
	var titleArr = new Array();
	var tdArr = new Array(); 
	var trSize = findTH(titleArr, tdArr, gridId, namespace);
	var nowRow = 0, redRow = 0, obj = null, bodyArray = new Array();
	bodyArray.push("<tbody id='page_" + nowPage + "' style='display:none'>");
	var rownum=0;
	$.each(data, function(key, value) {
		rownum++;
		bodyArray.push("<tr>");
		var axis = "";
		var rowspan = "";
		for ( var i = 0; i < titleArr.length; i++) { // 写每行数据中的每个单元格
			obj = tdArr[i].clone();
			rowspan = obj.attr("rowspan") != undefined ? obj.attr("rowspan") : "";
			if(rowspan!=1 && rownum%rowspan==0) {
				continue;
			}
			axis = obj.attr("axis") != undefined ? obj.attr("axis").toLowerCase() : "";
			// abbr 简单取值
			// axis 复杂元素(此处只需要对checkbox、button、hidden三种类型进行处理)
			createTD(titleArr, bodyArray, value, axis, obj, namespace, i, trSize);
		}
		bodyArray.push("</tr>");
		nowRow++;
		redRow++;
		if (nowRow == row4Page) {
			nowRow = 0;
			if (redRow < returnRows) { // 已读取记录数小于返回记录数,则再拼一页，否则不用拼了，本页已经结束了。
				nowPage++;
				bodyArray.push("</tbody>");
				bodyArray.push("<tbody id='page_" + nowPage + "' style='display:none'>");
				if ($("#page_" + nowPage)[0] != undefined) {// 如果对应页已经存在了，则需要先移除对应页
					$("#page_" + nowPage).remove();
				}
			}
		}
	});
	bodyArray.push("</tbody>");
	return bodyArray;
}

function findTH(titleArr, tdArr, gridId, namespace) {
	// 取表头，并判断是否显示选择框
	var trList = $("#" + namespace + " table[id=" + gridId + "] thead tr");
	$.each(trList.find("th"), function(i, value) {
		titleArr.push($(value).attr("abbr"));
		var tdHtml = $(value).outerHTML();
		if (tdHtml.indexOf("<TH") >= 0 || tdHtml.indexOf("TH>") >= 0) {
			tdHtml = tdHtml.replace("<TH", "<TD").replace("TH>", "TD>"); // copy th 将th替换成tr(IE)
		}
		if (tdHtml.indexOf("<th") >= 0 || tdHtml.indexOf("th>") >= 0) {
			tdHtml = tdHtml.replace("<th", "<TD").replace("th>", "TD>"); // copy th 将th替换成tr(火狐)
		}
		tdArr.push($(tdHtml));
		//dragTH(i, value); // 增加表头拖动内容
	});
	return trList.size();
}

function createTD(titleArr, bodyArray, value, axis, obj, namespace, i, trSize){
	if (axis == "checkbox") { // 针对复选框处理
		obj.empty();
		obj.attr("title", ""); // 设置为空
		// 设置复选框ID和值
		var checked =  value["checked"] != undefined && value["checked"]=="true" ? true : false;
		if(checked){
			obj.append("<input type='checkbox' checked='checked'  id='checkbox' name='checkbox_" + value["rn"] + "' value='" + trimStr(value[titleArr[i]]) + "'/>");
		}else{
			obj.append("<input type='checkbox'  id='checkbox' name='checkbox_" + value["rn"] + "' value='" + trimStr(value[titleArr[i]]) + "'/>");
		}
		bodyArray.push(obj.outerHTML()); // 将表头的TH替换为TD
		return;
	}
	if (axis == "button") { // 针对按钮进行处理
		var button = "";
		obj.find("button").each(function(i, value) {
			var abut = $(value);
			abut.attr("onclick", "submitRow(this)");
			abut.attr("data-ns", namespace);
			button += abut.show().outerHTML();
		});
		obj.text("");
		obj.append(button);
		bodyArray.push(obj.outerHTML()); // 将表头的TH替换为TD
		return;
	}
	if (axis == "hidden") { // 针对隐藏域进行处理，加入input标签，供提交时使用
		obj.append("<input type='hidden' id='" + titleArr[i] + "' name='" + titleArr[i] + "' value='" + trimStr(value[titleArr[i]]) + "' />");
		bodyArray.push(obj.outerHTML()); // 将表头的TH替换为TD
		return;
	}
	if (axis == "text") { // 针对文本框进行处理，加入input标签，供提交时使用
		var button = "";
		var inputValue = trimStr(value[titleArr[i]]);
		obj.find("input").each(function(index, value) {
			var abut = $(value);
			abut.attr("style", "display:block");
			abut.val(inputValue);
			button += abut.show().outerHTML();
		});
		obj.text("");
		obj.append(button);
		bodyArray.push(obj.outerHTML()); // 将表头的TH替换为TD
		return;
	}
	if (axis == "date") { //
		var button = "";
		var inputValue = trimStr(value[titleArr[i]]);
		obj.find("input").each(function(index, value) {
			var abut = $(value);
			abut.removeAttr("style");
			abut.val(inputValue);
			var serno = trimStr(value["rn"]);
			if(serno == "")
				serno = trSize + 1;
			// 给对象添加赋值事件
			if(abut.attr("onclick").indexOf("_serno") > 0){
				abut.removeAttr("onblur");
				abut.attr("onblur","setDate(this,'"+abut.attr("id")+"_"+serno+"')");
			}
			button += abut.outerHTML();
			// 替换取值编码
			button = button.replace("_serno", "_" + serno);
			// 增加赋值取标签。
			button += "<input type='hidden' id='"+abut.attr("id")+"_"+serno+"' value='"+inputValue+"'/>";
		});
		obj.text("");
		obj.append(button);
		bodyArray.push(obj.outerHTML()); // 将表头的TH替换为TD
		return;
	}
	if (axis == "select"){
		var button = "";
		var inputValue = trimStr(value[titleArr[i]]);
		obj.find("select").each(function(index, value) {
			var abut = $(value);
			abut.children("option").each(function(){
				if($(this).val() == inputValue){							
					$(this).attr("selected", "true");
				}						
			});
			abut.attr("style", "display:block");
			button += abut.show().outerHTML();
		});
		obj.text("");
		obj.append(button);
		bodyArray.push(obj.outerHTML()); // 将表头的TH替换为TD
		return;
	}
	if (axis == "selectrank"){
		obj.empty();
		var selectStr = "";
		if(typeof trimStr(value[titleArr[i]]) != "string"){								
			$.each(trimStr(value[titleArr[i]]), function(i, value){
				selectStr+="<option value='"+value.code+ "'>"+value.name+"</option>";
			});
			obj.append("<select type='select' id='" + titleArr[i] + "' name='" + titleArr[i] + "' onchange='selectRank(this)'>"+selectStr+"</select> ");
			bodyArray.push(obj.outerHTML()); // 将表头的TH替换为TD
			return;
		}
	}
	obj.empty();
	if (titleArr[i] != "" && titleArr[i] != undefined) { // 存在abbr则赋值，不存在则设置为空
		obj.text(trimStr(value[titleArr[i]]));
	} else {
		obj.text("");
	}
	bodyArray.push(obj.outerHTML()); // 将表头的TH替换为TD
}

function addRow(btn, gridId) {
	var _this = $(btn);
	var namespace = _namespace(_this);
	var titleArr = new Array();
	var tdArr = new Array();
	var trSize = findTH(titleArr, tdArr, gridId, namespace);
	var obj = null, bodyArray = new Array();
	bodyArray.push("<tr>");
	var axis = "";
	for ( var i = 0; i < titleArr.length; i++) { // 写每行数据中的每个单元格
		obj = tdArr[i].clone();
		axis = obj.attr("axis") != undefined ? obj.attr("axis").toLowerCase() : "";
		createTD(titleArr, bodyArray, {}, axis, obj, namespace, i, trSize);
	}
	bodyArray.push("</tr>");
	$("#" + gridId + " tbody").append(bodyArray.join(""));
}

function delRow(btn, gridId) {
	var namespace = _namespace($(btn));
	var boxlist = $("#" + namespace + " table[id=" + gridId + "] tbody").find(":visible").find(":checkbox:checked");
	if(boxlist == undefined || boxlist.length < 1){
		var msgList = new Array;
		var msgInfo = new Object();
		msgInfo.msg = "请至少选择一条记录";
		msgList.push(msgInfo);
		var alertmsg = new Object();
		alertmsg.msgType = "normal";
		alertmsg.msg = msgList;
		alertMsg(alertmsg);
		return;
	}
	boxlist.each(function(i, val){// 把每个checkbox以及checkbox所在行里的隐藏列的值全取出来
		$(this).parent().parent().remove(); 
	});
	
}

function trimStr(obj){
	if(obj == null || obj == undefined)
		return "";
	return $.trim(obj);
}
// 用于添加一行/删除一行操作时针对日期框进行范围限制使用
function setDate(btn, serno){
    var _this = $(btn);
    $("#"+serno).val(_this.val());
}

function dragTH(int, thValue){
	var font = $("<span class='resizeTH' onmousedown='clmDrag4MD(event, this)' onmousemove='clmDrag4MO(event, this)' onmouseup='clmDrag4UP(event, this)'></span>");//给每个TH加上font标签，显示位置用
	if($(thValue).find("span")[0] != undefined){
		return;
	}
	if($(thValue).attr("abbr") != "rn" && $(thValue).attr("abbr") != ""){
		font.prependTo($(thValue)); // 放到TH中
		$(thValue).width($(thValue).width()); // 设置TH的固定宽度(此处也可以在页面中写上宽度)
	} else {
		$(thValue).width($(thValue).width());
	}
}

var currentTd = null;
var limitWidth = 10;
function clmDrag4MD(event, obj) {// 鼠标按下
	currentTd = $(obj).parent();
	var th = $(obj).parent();
	event = event || window.event; 
	currentTd.mouseDownX = event.clientX;
	currentTd.Width = th.width();
	if(obj.setCapture){
		obj.setCapture();
    } else if(window.captureEvents){    
        window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);      
    }
}

function clmDrag4MO(event, obj) {// 鼠标移动
	if(!currentTd) return ; 
	var ob = currentTd;
	var th = $(obj).parent();
	event = event || window.event;
    if(!ob.mouseDownX) return false;
    var newWidth = currentTd.Width * 1 + event.clientX * 1 - ob.mouseDownX;
    if(newWidth > limitWidth){
    	th.width(newWidth);
    } else {
    	th.width(limitWidth);
    	return false;
    }
}

function clmDrag4UP(event, obj) {// 鼠标放开
	if(!currentTd) return; 
	if(obj.releaseCapture){
		obj.releaseCapture();
    }else if(window.captureEvents){  
        window.releaseEvents(Event.MOUSEMOVE|Event.MOUSEUP);
    }
	currentTd=null;
}
/**
 * 
 * @param obj 当前按钮对象
 */
function submitRow_bak(obj){
	var namespace = _namespace($(obj));
	var p = $(obj).parent().parent();
	var valHtml, valInput;
	var param = "";
	p.find("[type='hidden']").each(function(i, value){
		valHtml =  $(value).outerHTML();
		if(valHtml != "" && valHtml != undefined){
			valInput = $(valHtml);
			param += "," + valInput.attr("id")+ ":'"+valInput.val()+"'";
		}
	});
	var formValue = formToJsonString("form1,form3", namespace);
	var url = obj.name;
	$("#"+namespace).load(url + "?funcID="+url, string2json("{hiddenform:'"+formValue+"'"+param+"}"), null);
}

/**
 * 
 * @param obj 当前按钮对象
 */
function submitRow(obj){
	var namespace = _namespace($(obj));
	var action = obj.name + "?funcID=" + obj.name;
	var form = "<form id='form2' name='form2' action='"+action+"' method='post'>";
	var p = $(obj).parent().parent();
	var valHtml, valInput;
	p.find("[type='hidden']").each(function(i, value){
		valHtml =  $(value).outerHTML();
		if(valHtml != "" && valHtml != undefined){
			valInput = $(valHtml);
			form += "<input type='hidden' id='"+valInput.attr("id")+"' name='"+valInput.attr("id")+"' value='"+valInput.val()+"'/>";
		}
	});
	var formValue = formToJsonString("form1,form3", namespace);
	form += "<input type='hidden' id='hiddenform' name='hiddenform' value='"+formValue+"'/>";
	form += "</form>";
	// 新加一个form进去，放提交参数，原来的form1,form3放hiddenform，到新页面返回时使用
	$("#"+namespace).append(form);
	$("#"+namespace + " form[id='form2']")[0].submit();
}

function checkAll(checkbox){
	check4ID(checkbox, "grid");
}

/**
 * 全选
 * @param checkbox
 *            当前checkbox
 * @param gridid
 *            作用于表格的ID
 * @param boxid
 *            需要选择的checkbox id
 * @returns
 */
function check4ID(checkbox, gridid) {
	var namespace = _namespace($(checkbox));
	var checked = false;
	if ($(checkbox).prop("checked")) {
		checked = true;
	}
	$("#" + namespace + " table[id='" + gridid + "'] tbody").find(":visible").find(":checkbox").prop("checked", checked);
}



