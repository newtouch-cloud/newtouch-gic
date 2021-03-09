$(function() {
	$("#chaxun").click(function() {
		$("#chaxun")[0].disabled = true;
		var formValue = formToJsonString("form1");
		if ($("#hiddenform")[0] == undefined) {
			$("#form1").append($("<input type='hidden' id='hiddenform' name='hiddenform' />"));
		}
		$("#hiddenform").val(formValue);
		var url = $("#chaxun").attr('name');
		form1.action = url + "?funcID=" + url;
		form1.submit();
	});
	// 所以页面重置按钮
	$("#chongzhi").click(function(){
		form1.reset();
	});
});

function fillGrid(json) {
	fillGrid4Id("grid", json);
}

function fillGrid4Id(gridId, json) {
debugger;
	if (json != undefined) {
		var jsonForm = json.hiddenform;
		for (var fieldName in jsonForm) {
			$("[name=" + fieldName + "]").val(jsonForm[fieldName]);// 设置查询条件
		}
		// $("#hiddenform").val(jsonToString(jsonForm));
		json2Page(gridId, json);
	}
}
function json2Page(gridId, data) {
	value2Grid(gridId, data);
	$("#page_" + data.pageNo).show();
	fillPagination(data.pageAll, data.pageNo, data.rowAll);
	darwWranMsg("msg", data);
	darwErrorMsg("msg", data);
}
function value2Grid(gridId, data) {
	debugger;
	if (data.retList == undefined || data.retList == "") {
		return "";
	}
	var titleArr = new Array();
	var tdArr = new Array();
	// 取表头，并判断是否显示选择框
	$.each($("#" + gridId + " thead tr th"),function(i, value){
		titleArr.push($(value).attr("abbr"));
		//dragTH(i, value);
		//alert(value.outerHTML);
		tdArr.push(value);
	});
	var row4Page = data.row4Page; // 每页显示行数
	var nowPage = data.pageNo; // 当前第几页
	var returnRows = data.returnRows; // 本次返回记录数
	var tbody = "<tbody id='page_" + nowPage + "' style='display:none'>";
	var nowRow = 0;
	var redRow = 0; //已读取记录数
	var obj = null;
	$.each(data.retList, function(key, value) {
		tbody += "<tr>";
		for(var i = 0; i < titleArr.length; i++) { //写每行数据中的每个单元格
			obj = $(tdArr[i].outerHTML);
			if(obj.html().indexOf("checkbox") > 0){ // 针对复选框处理
				obj.attr("title",""); // 设置为空
				obj.find("input").attr("value", value[titleArr[i]]); // 设置复选框的值
				obj.find("input").attr("id", "checkbox_" + value["rn"]); // 设置复选框的ID
				tbody += obj[0].outerHTML.replace("<TH","<TD").replace("TH>","TD>"); // 将表头的TH替换为TD
				continue;
			}
			if(obj.attr("class") != undefined &&  obj.attr("class").toLowerCase() == "hidden"){ // 针对隐藏域进行处理，加入input标签，供提交时使用
				obj.append($("<intput type='hidden' id='"+titleArr[i]+"' value='"+value[titleArr[i]]+"' >"));
				tbody += obj[0].outerHTML.replace("<TH","<TD").replace("TH>","TD>"); // 将表头的TH替换为TD
				continue;
			}
			obj.text(value[titleArr[i]]);
			
			tbody += obj[0].outerHTML.replace("<TH","<TD").replace("TH>","TD>"); // 将表头的TH替换为TD
		}
		tbody += "</tr>";
		nowRow++;
		redRow++;
		if(nowRow == row4Page){
			nowRow = 0;
			if(redRow < returnRows){ //已读取记录数小于返回记录数,则再拼一页，否则不用拼了，本页已经结束了。
				nowPage++;
				tbody += "</tbody>";
				tbody += "<tbody id='page_" + nowPage + "' style='display:none'>";
				if($("#page_" + nowPage)[0] != undefined){// 如果对应页已经存在了，则需要先移除对应页
					$("#page_" + nowPage).remove();
				}
			}
		}
	});
	tbody += "</tbody>";
	while(tbody.indexOf("<th") >= 0 || tbody.indexOf("th>") >= 0){
		tbody = tbody.replace("<th","<TD").replace("th>","TD>"); // 将表头的TH替换为TD
	}
	$("#" + gridId + " ").append($(tbody));
	if($("#maxPage")[0] == undefined){
		$("#" + gridId + " ").append($("<input type='hidden' id='maxPage' value='0'/>"));
	}
	$("#maxPage").val(nowPage); // 当前加载后的最大页面
	if($("#allRows")[0] != undefined){ //如果已经存在记录，表示为点击页码时进行的调用
		$("#allPage").val(data.pageAll); // 所有页(赋值，供分页使用)
		$("#allRows").val(data.rowAll); // 所有记录(赋值，供分页使用)
	}
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
	ulMsg += "</ul>"
	$("#" + gridId + " ").append($(ulMsg));
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
	ulMsg += "</ul>"
	$("#" + gridId + "").append($(ulMsg));
}


function formToJsonString(formNames) {// 将表单转换为josn串
	var fields = null;
	var fieldValue = "";
	var json = "";
	var reg = /{$/;
	var formName = formNames.split(",");
	for ( var j = 0; j < formName.length; j++) {
		if (formName[j] != "") {
			fields = document.forms(formName[j]).elements;
		} else {
			fields = $(":input");
		}
		var fieldNameValue = [];// 装载属性及对应的值
		var fieldNameNum = [];// 记录属性出现次数,处理数组
		// j == 0 ? json += formName[j]+":{" : json += ","+formName[j]+":{"
		for ( var i = 0; i < fields.length; i++) {
			var fieldTypeName = fields[i].type;
			if(fieldTypeName == "submit"){
				continue;
			}
			var fieldName = fields[i].name;
			if (fieldName == null || fieldName == ""
					|| fieldName == "hiddenform") {
				continue;
			}
			var fieldValues = fieldNameValue[fieldName];
			var num = fieldNameNum[fieldName];
			num = num == null ? 0 : num + 1;
			var value = "";
			if (fields[i].value != null && fields[i].value != "") {
				value = fields[i].value;
			}
			switch (fieldTypeName) {
			case 'select-multiple':
				alert('select-multiple');
				value = getSelectMultipleValues(fields[i]);//TODO 此处需要修改，没有调用目前
				break;
			case 'select-one':
				value = $("#"+fieldName).val();
				break;
			case 'radio':
				if (fields[i].checked) {
					break;
				}
				continue;
			case 'checkbox':
				if (num == 0) {
					// value=getCheckboxValues(fieldName);
					break;
				}
				continue;
			case 'button':
				continue;
			}
			if (json != "") {
				fieldValue += '&';
				json += ',';
			}
			json += fieldName + ':"' + value + '"';
			fieldValue += fieldName + '=' + value;
		}
	}
	// json += "}";
	return json;
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

//将JSON字符串回填到页面
function jsonStrToElementsValue(jsonStr){
    if (typeof jsonStr == "undefined" || jsonStr == null) {
    	return;
	}
    if(jsonStr.fail != undefined){
    	alert(jsonStr.fail);
    }
// str = jsonToString(formJson, "");
    $("#hiddenform").val(jsonToString(jsonStr.hiddenform));
    for(var key in jsonStr){
    	if(jsonStr[key] instanceof Array){
    		if("tableid" == key){
    			fillElementsValue(key,jsonStr[key],0);
    		} else {
    			var o = $("#"+key+" td");
    			if(o.length > 0){    				
    				var arr = [];
    				for(var i = 0; i < o.length; i++){
    					if($(o[i]).attr("name") != undefined){   				
    						arr[i] = $(o[i]).attr("name");
    					}
    				}
    				
    				while($("#"+key+" tr:not(:first)").val() == $("#"+key+" tr:last").val()){
    					$("#"+key+">tbody>tr:last").remove();
    				}
    				fillElementsGrid(key,jsonStr[key],arr,0);
    				$("#"+key+" tr:odd").addClass("alt");
    				$("#"+key+" tr:not(:first)").mouseover(function(){
    					$(this).addClass("over");}).mouseout(function(){
    						$(this).removeClass("over");})
    			}
    		}
    	}    	
    }
}
function dragTH(int, thValue){
	var font = $("<font class='resizeTH'></font>");//给每个TH加上font标签，显示位置用
	if($(thValue).attr("abbr") != "rn" && $(thValue).attr("abbr") != ""){
		font.prependTo($(thValue)); // 放到TH中
		$(thValue).width($(thValue).width()); // 设置TH的固定宽度(此处也可以在页面中写上宽度)
		var currentTd = null;
		var th = $(thValue);
		var limitWidth = 10;
		font.unbind();
		font.bind('mousedown',function(event, ob){ // 给拖拽的内容设置事件
			ob = ob || thValue;
			currentTd = ob;
			event = event || window.event; 
			ob.mouseDownX = event.clientX;
			th.Width = th.width();
			if(ob.setCapture){
	        	ob.setCapture();
	        } else if(window.captureEvents){    
	            window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);      
	        }
		});
		font.bind('mousemove',function(event, ob){ // 鼠标移动
			if(!currentTd) return ; 
			ob = currentTd;
			event = event || window.event;  
	        if(!ob.mouseDownX) return false;
	        var newWidth = th.Width * 1 + event.clientX * 1 - ob.mouseDownX;
	        if(newWidth > limitWidth){
	        	th.width(newWidth);
	        } else {
	        	th.width(limitWidth);
	        	return false;
	        }
		});
		font.mouseup(function(){
			if(!currentTd) return; 
			if(currentTd.releaseCapture){
				currentTd.releaseCapture();     
	        }else if(window.captureEvents){
	            window.releaseEvents(Event.MOUSEMOVE|Event.MOUSEUP);      
	        }
			currentTd=null;
		});
	} else {
		$(thValue).width($(thValue).width());
	}
}


