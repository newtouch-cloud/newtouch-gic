function fillGrid(json, namespace) {
	if(namespace == null || namespace == undefined)
		namespace = "body";
	fillGrid4Id("grid", json, namespace);
}

function fillGrid4Id(gridId, json, namespace) {
	debugger;
	if(json.flag != "success"){
		alertMsg4Modal(json);
	}
	if(json != undefined && json.hiddenform != undefined){
		var jsonForm = string2json("{"+json.hiddenform+"}");
		for (var fieldName in jsonForm) {
			$("#" + namespace + " form[id='form1'] [name='"+fieldName+"']").val(jsonForm[fieldName]);
		}
	}
	initShowPageQuery(namespace, json);
	if (json != undefined) {
		json2Page(gridId, json, namespace);
	}
}

function json2Page(gridId, data, namespace) {
	value2Grid(gridId, data, namespace);
	$("#"+namespace+" tbody[id=page_" + data.nowPage+"]").show();
	fillPagination(data.pageAll, data.nowPage, data.rowAll, namespace);
	// 维护每页记录数
	$("#" + namespace + " select[id='row4Page']").val(data.row4Page);
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
	joinTable(gridId, data.retList, data.row4Page, data.nowPage, data.returnRows, namespace);
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
	var nowRow = 0, redRow = 0, obj = null, tbody = $("<tbody id='page_" + nowPage + "' style='display:none'/>");
	var rownum=0;
	$.each(data, function(key, value) {
		rownum++;
		var tr = $("<tr/>");
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
			createTD(titleArr, tr, value, axis, obj, namespace, i, trSize);
		}
		tbody.append(tr);
		nowRow++;
		redRow++;
		if (nowRow == row4Page) {
			nowRow = 0;
			if (redRow < returnRows) { // 已读取记录数小于返回记录数,则再拼一页，否则不用拼了，本页已经结束了。
				nowPage++;
				$("#" + namespace +" table[id=" + gridId+ "]").append(tbody.clone());
				tbody = $("<tbody id='page_" + nowPage + "' style='display:none'>");
				if ($("#page_" + nowPage)[0] != undefined) {// 如果对应页已经存在了，则需要先移除对应页
					$("#page_" + nowPage).remove();
				}
			}
		}
	});
	$("#" + namespace +" table[id=" + gridId+ "]").append(tbody.clone());
}

function findTH(titleArr, tdArr, gridId, namespace) {
	// 取表头，并判断是否显示选择框
	var trList = $("#" + namespace + " table[id=" + gridId + "] thead tr").first();
	$.each(trList.find("th"), function(i, value) {
		titleArr.push($(value).attr("abbr"));
		tdArr.push($(value));
	});
	return $("#" + namespace + " table[id=" + gridId + "] tbody tr").size();
}

function ifCheckAll(obj){
	var parent = $(obj).parent().parent().parent();
	var boxlist = parent.find("tr td:first-child :not(:checked)").length;
	if(boxlist == 0){
		parent.parent().find("thead tr th").eq(0).find(":checkbox").attr("checked",true);
	} else {
		parent.parent().find("thead tr th").eq(0).find(":checkbox").attr("checked",false);
	} 
}

function createTD(titleArr, tr, value, axis, obj, namespace, i, trSize){
	var button = $("<td/>");
	button.attr("class", obj.attr("class"));
	button.attr("rowspan", obj.attr("rowspan"));
	if (axis == "checkbox") { // 针对复选框处理
		var checkbox = obj.find("[type='checkbox']");
		var showBox = obj.attr("data-showbox");
		var checkValue = trimStr(value[titleArr[i]]);
		// 设置复选框ID和值
		var checked =  checkValue != undefined && checkValue == "true" ? true : false;
		var id = titleArr[i];
		var onclick = checkbox.attr("onclick");
		if(onclick == undefined) onclick = "";
		if(onclick == "checkAll(this)" || onclick.indexOf("check4ID") >= 0) checkbox.removeAttr("onclick");
		checkbox.attr("onclick","ifCheckAll(this)");
		checkbox.show();
		if(showBox != undefined && showBox == "false")
			checkbox.hide();
		if(checkbox.attr("id") == undefined) id="checkbox";
		checkbox.prop("checked", checked);
		checkbox.attr("id", id);
		checkbox.attr("name", id+"_" + value["rn"]);
		checkbox.val(checkValue);
		checkbox.attr("value", checkValue);
		checkbox.attr("class", "checkbox");
		button.append(checkbox);
		tr.append(button); // 将表头的TH替换为TD
		return;
	}
	if (axis == "button") { // 针对按钮进行处理
		obj.find("button").each(function(i, eachV) {
			var abut = $(eachV).clone();
			abut.attr("onclick", "submitRow(this)");
			abut.attr("data-ns", namespace);
			abut.removeAttr("style");
			abut.show();
			button.append(abut);
		});
		tr.append(button); // 将表头的TH替换为TD
		return;
	}
	if (axis == "btn-group") { // 针对按钮进行处理
		var btnGrp = obj.find("div[class='btn-group']");
		btnGrp.find("button").removeAttr("style");
		button.append(btnGrp);
		tr.append(button); // 将表头的TH替换为TD
		return;
	}
	if (axis == "hidden") { // 针对隐藏域进行处理，加入input标签，供提交时使用
		button.append("<input type='hidden' id='" + titleArr[i] + "' name='" + titleArr[i] + "' value='" + trimStr(value[titleArr[i]]) + "' />");
		tr.append(button); // 将表头的TH替换为TD
		return;
	}
	if (axis == "text") { // 针对文本框进行处理，加入input标签，供提交时使用
		var inputValue = trimStr(value[titleArr[i]]);
		obj.find("input").each(function(index, eachV) {
			var abut = $(eachV).clone();
			abut.removeAttr("style");
			abut.val(inputValue);
			abut.attr("value",inputValue);
			button.append(abut);
		});
		tr.append(button); // 将表头的TH替换为TD
		return;
	}
	if (axis == "date") { //
		var inputValue = trimStr(value[titleArr[i]]);
		obj.find("input").each(function(index, eachV) {
			var abut = $(eachV).clone();
			abut.removeAttr("style");
			abut.val(inputValue);
			abut.attr("value",inputValue);
			var serno = trimStr(value["rn"]);
			if(serno == "")
				serno = trSize + 1;
			// 给对象添加赋值事件
			if(abut.attr("onfocus") != undefined && abut.attr("onfocus").indexOf("_serno") > 0){
				abut.removeAttr("onblur");
				abut.attr("onblur","setDate(this,'"+abut.attr("id")+"_"+serno+"')");
				// 替换取值编码
				abut.attr("onfocus",abut.attr("onfocus").replace("_serno", "_" + serno));
				// 增加赋值取标签。
				button.append("<input type='hidden' id='"+abut.attr("id")+"_"+serno+"' value='"+inputValue+"'/>");
			}
			button.append(abut);
		});
		tr.append(button);
		return;
	}
	if (axis == "select"){
		var inputValue = value[titleArr[i]];
		if($.type(inputValue) == "array"){
			var slct = obj.find("select[id='"+titleArr[i]+"']");
			slct.empty();
			slct.append("<option value=''></option>");
			$.each(inputValue, function(sKey, sVal){
				var selected = "";
				if(sVal["selected"] != undefined)
					selected = "selected = 'selected'";
				slct.append("<option value='"+sVal["code"]+"' " + selected + ">"+sVal["name"]+"</option>");
			});
			slct.removeAttr("style");
			slct.show();
			button.append(slct);
			tr.append(button); // 将表头的TH替换为TD
			return;
		}
		obj.find("select").each(function(index, eachV) {
			var abut = $(eachV).clone();
			abut.children("option").each(function(){
				if($(this).val() == inputValue){							
					$(this).attr("selected", "true");
				}						
			});
			abut.removeAttr("style");
			abut.show();
			button.append(abut);
		});
		tr.append(button); // 将表头的TH替换为TD
		return;
	}
	if (axis == "elps"){
		var inputValue = trimStr(value[titleArr[i]]);
		var len = 3;
		obj.find("[data-toggle='tooltip']").each(function(index, eachV) {
			var abut = $(eachV);
			if(abut.attr("data-len") != undefined && abut.attr("data-len") != ""){
				len = abut.attr("data-len");
			}
			if(!(inputValue.length > len)){
				button.text(inputValue);
				return;
			}
			abut.removeAttr("style");
			abut.show();
			abut.attr("title", inputValue);
			abut.text(inputValue.substring(0, len));
			abut.popover();
			button.append(abut);
		});
		tr.append(button); // 将表头的TH替换为TD
		return;
	}
	if (axis == "selectrank"){
		var selectStr = "";
		if(typeof value[titleArr[i]] != "string"){								
			selectStr+="<option value=''></option>";
			$.each(value[titleArr[i]], function(i, value){
				selectStr+="<option value='"+value.code+ "'>"+value.name+"</option>";
			});
			button.append("<select type='select' id='" + titleArr[i] + "' name='" + titleArr[i] + "' onchange='selectRank(this)'>"+selectStr+"</select> ");
			tr.append(button); // 将表头的TH替换为TD
			return;
		}
	}
	if (axis == "selectgrade"){//修改打分管理时，根据打分类型自动加载对应的打分项，并且被选中
		var grade_item=value.grade_item;
		var gradeitem=value.gradeitem; //人员对应的打分项
		var selectStr = "";
		if(typeof grade_item != "string" && typeof grade_item != "undefined"){
			$.each(grade_item, function(i, value){
				if(gradeitem == value["code"]){
					selectStr+="<option value='"+value["code"]+ "' selected>"+value["name"]+"</option>";
				}else{
					selectStr+="<option value='"+value["code"]+ "'>"+value["name"]+"</option>";
				}
			});
			button.append("<select class='form-control' id='" + titleArr[i] + "' name='" + titleArr[i] + "'>"+selectStr+"</select> ");
			tr.append(button); // 将表头的TH替换为TD
			return;
		}
		if(typeof grade_item == "undefined"){
			selectStr="<option value='' selected></option>";
			button.append("<select class='form-control' id='" + titleArr[i] + "' name='" + titleArr[i] + "'>"+selectStr+"</select> ");
			tr.append(button); // 将表头的TH替换为TD
			return;
		}
	}
	if (titleArr[i] != "" && titleArr[i] != undefined) { // 存在abbr则赋值，不存在则设置为空
		button.text(trimStr(value[titleArr[i]]));
	} else {
		button.text("");
	}
	tr.append(button); // 将表头的TH替换为TD
}

function addRow(btn, gridId) {
	var _this = $(btn);
	var namespace = _namespace(_this);
	var titleArr = new Array();
	var tdArr = new Array();
	var trSize = findTH(titleArr, tdArr, gridId, namespace);
	var obj = null, tr = $("<tr/>");
	var axis = "";
	for ( var i = 0; i < titleArr.length; i++) { // 写每行数据中的每个单元格
		obj = tdArr[i].clone();
		axis = obj.attr("axis") != undefined ? obj.attr("axis").toLowerCase() : "";
		if(axis == "checkbox")
			obj.attr("data-showbox", "true");
		createTD(titleArr, tr, {}, axis, obj, namespace, i, trSize);
	}
	if($("#" + namespace + " table[id='"+gridId+"'] tbody")[0] == undefined)
		$("#" + namespace + " table[id='"+gridId+"']").append("<tbody/>");
	$("#" + namespace + " table[id='"+gridId+"'] tbody").append(tr);
}

function delRow(btn, gridId) {
	var namespace = _namespace($(btn));
	var boxlist = $("#" + namespace + " table[id=" + gridId + "] tbody tr td:first-child").find(":checked");//只选择第一列，否则其他列有复选框会有问题
	if(boxlist == undefined || boxlist.length < 1){
		var data = {flag:"warn",msg:[{"":"请至少选择一条记录"}]};
		alertMsg4Modal(data);
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
function submitRow(obj){
	obj.disabled = true;
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
	var formValue = $("#"+namespace+" form[id='form3'] input[id='showpagequery']").val();
	if(formValue == undefined)
		formValue = formToJsonString("form1,form3", namespace);
	else
		formValue += "," + formToJsonString("form3", namespace);
	var url = obj.name;
	$("#"+namespace).load(url + "?funcID="+url, string2json("{nowPage:'1',hiddenform:'"+formValue+"'"+param+",InitPageType:'false'}"), null);
}

/**
 * 
 * @param obj 当前按钮对象
 */
function submitRow_bak(obj){
	obj.disabled = true;
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
	var box = $(checkbox);
	var namespace = _namespace(box);
	var checked = false;
	if (box.prop("checked")) {
		checked = true;
	}
	var id = box.attr("id");
	if(box.attr("id") == undefined) id = "checkbox";
	$("#" + namespace + " table[id='" + gridid + "'] tbody").find(":visible").find(":checkbox[id='"+id+"']").prop("checked", checked);
}



