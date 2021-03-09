var str = "";
var  ver = navigator.appVersion;   
var bType =navigator.appName;   
var  vNumber;
	 if(bType=="Microsoft Internet Explorer")
	 {
     vNumber=parseFloat(ver.substring(ver.indexOf("MSIE")+5,ver.lastIndexOf("Windows")));
	 }

$(function (){
	$.each($("table"), function (key, value){
		if(value.id != "" && value.id != "tableid"){			
			$("#"+value.id+" tr:odd").addClass("alt");
		}
	});
		
	$("#daochu").click(function(){
		var data = elementsToValueString("form1")+'&'+"isPaging=false";
		form1.action = $("#daochu").attr('name')+'?'+data;
		form1.submit();
	});	
	
	
	$("#chaxun").click(function(){
		$("#chaxun")[0].disabled = true;
		var formValue = formToJsonString("form1");
		$("#hiddenform").val(formValue);
		var url = $("#chaxun").attr('name');
		form1.action = url + "?funcID="+url;
		form1.submit();
	});
	
	// 财富渠道-薪资管理-薪资查询
	$("#xinziChaxun").click(function(){
		if(checkUi()){
			$("#xinziChaxun")[0].disabled = true;
			var formValue = formToJsonString("form1");
			$("#hiddenform").val(formValue);
			var url = $("#xinziChaxun").attr('name');
			form1.action = url + "?funcID="+url;
			form1.submit();
		}
	});
	
	$("#chaxun4KaoHeQueRen").click(function(){
		var data = elementsToValueString("form");
		var ids = $("#ids").val().split(',');
		var url = $("#chaxun4KaoHeQueRen").attr('name');
		var jsonData = string2json("{"+data+",funcID:'"+url+"'}");
		ajax4KaoHeQueRen(url, jsonData, "grid", ids);
	});
	
	// 所以页面重置按钮
	$("#chongzhi").click(function(){
		form1.reset();
	});
	$("#checkboxid").click(function() {
		if ($("#checkboxid").attr("checked") == true) {
			$("[name='box']").attr("checked", "true");
		} else {
			$("[name='box']").removeAttr("checked");
		}
	});
	$("#mingxi").click(function() {
		var count = 0;
		var id = "";
		for(var i = 0; i < $("[name='box']").length; i++){
			if(($("[name='box']"))[i].checked){
				id = ($("[name='box']"))[i].value;
				++count;
			}
		} 
		if(count != 1){			
			alert("请选择一条记录");		
		}else {
			var formValue = formToJsonString("form1,form3");
			$("#hiddenform").val(formValue);
			var url = $("#mingxi").attr('name');
			form1.action = url + "?funcID="+url+"&id="+id;
			resetTitle("mingxi","");
			form1.submit();
		}
	});
	$("#xiugaiinit").click(function() {
		var count = 0;
		var id = "";
		for(var i = 0; i < $("[name='box']").length; i++){
			if(($("[name='box']"))[i].checked){
				id = ($("[name='box']"))[i].value;
				++count;
			}
		} 
		if(count != 1){
			alert("请选择一条记录");
			return;
		}
		if(checkUi()){			
			var formValue = formToJsonString("form1,form3");
			$("#hiddenform").val(formValue);
			var url = $("#xiugaiinit").attr('name');
			form1.action = url + "?funcID="+url+"&id="+id;
			resetTitle("xiugaiinit","");
			form1.submit();
		}
	});
	$("#xiugai").click(function() {
		if(checkUi()){			
			$("#xiugai")[0].disabled = true;
			var data = elementsToValueString("form1");
			var subJson = "" ; 
			subJson= getTableIdByForm();// 将tab页中的信息加入
			var url = $("#xiugai").attr("name");
			var jsonData = string2json("{"+data+",funcID:'"+url+"'"+subJson+"}");
			$.ajax({
				type: "POST",
				url: url,
				data: jsonData,
				dataType: "json",
				success:function(data){
					if(data.fail != undefined){
						alert(data.fail);
						$("#xiugai")[0].disabled = false;
					}else if(data.success != undefined){
						alert(data.success);
						$("#xiugai")[0].disabled = false;
					}
				}
			});
		}
	});
	
	$("#xinzenginit").click(function(){
		var formValue = formToJsonString("form1,form3");
		$("#hiddenform").val(formValue);
		var url = $("#xinzenginit").attr('name');
		form1.action = url + "?funcID="+url;
		//TODO 暂时注释，需要打开
		//resetTitle("xinzenginit","");
		form1.submit();
	});
	
	$("#xinzeng").click(function(){
		if(checkUi()){			
			$("#xinzeng")[0].disabled = true;
			var data = elementsToValueString("form1");
			var subJson = "" ; 
			subJson= getTableIdByForm();// 将tab页中的信息加入
			var url = $("#xinzeng").attr("name");
			var jsonData = string2json("{"+data+",funcID:'"+url+"'"+subJson+"}");
			$.ajax({
				type: "POST",
				url: url,
				data: jsonData,
				dataType: "json",
				success:function(data){
					if(data.fail != undefined){						
						alert(data.fail);
						$("#xinzeng")[0].disabled = false;
					}else if(data.success != undefined){
						form1.reset();
						alert(data.success);
						$("#xinzeng")[0].disabled = false;
					}
				}
			});
		}
	});
	$("#fanhui").click(function(){
		var url = $("#fanhui").attr('name');
		form1.action = url + "?funcID="+url;
		resetTitle("fanhui","");
		form1.submit();
	});
	if($("#fanhui")!=undefined){
		var url = $("#fanhui").attr('name');
		if(url!=undefined)resetTitle("ahref",url);
	}

});

function ajax(url, data, gridId, ids, count){
	$.ajax({
		type: "POST",
		url: url,
		data: data,
		dataType: "json",
		success:function(data){
			if(data.fail != undefined){						
				alert(data.fail);
				return;
			}
			value2Grid(gridId, ids, data);
		}
	});
}

function value2Grid(gridId, ids, data){
	var content;
	var num = 0;
	while($("#"+gridId+" tr:not(:first)").val() == $("#"+gridId+" tr:last").val()){
		$("#"+gridId+">tbody>tr:last").remove();
	}
	var content = "";
	var hidden = "";
	var num = 0;
	var arr;
	$("#pageNo").val(data.pageNo); 
    $("#pageAll").text(data.pageAll); 
    $("#rowAll").text(data.rowAll);
// var o = $("#"+ gridId +" td");
// if(o.length > 0){
// var idf = [];
// for(var i = 0; i < o.length; i++){
// if($(o[i]).attr("name") != undefined){
// idf[i-1] = $(o[i]).attr("name");
// }
// }
// ids = idf;
// }
	if(data.retList != undefined){
		$.each(data.retList, function (key, value){
			hidden = "";
			content += "<tr id='trid' onclick='trclick(this)'><td>"+value["rn"]+"</td>"
			content +="<td><input name='box' value="+value[ids[0]]+" type='checkbox'/></td>"
			for(var i = 1; i < ids.length; i++){						
				if(value[ids[i]]==null||undefined == value[ids[i]] ){							
					value[ids[i]] ="";							
				}
				arr = ids[i].split("#");
				ids[i] = jQuery.trim(ids[i]);
				if(arr[1] == "hidden"){
					hidden += '<input name="'+ids[i]+'" type="hidden" value="'+value[ids[i]]+'"/>';
				}else{
					content += '<td><div id="'+ids[i]+'" style="width:100%;height:20px;text-overflow:ellipsis; white-space:nowrap; overflow:hidden; ">'+ value[ids[i]] +'</div></td>';
				}
			}
			content += hidden+"</tr>";
		});
	}else{
		content += "<tr id='trid' onclick='trclick(this)'><td></td>"
		content +="<td><input name='box' value ='' type='checkbox'/></td>"
		for(var i = 1; i < ids.length; i++){
			arr = ids[i].split("#");
			ids[i] = jQuery.trim(ids[i]);
			if(arr[1] != "hidden"){						
				content += "<td></td>";
			}
		}
		content += "</tr>";
	}
	$("#"+gridId).append($(content));
	$("#"+gridId+" tr:odd").addClass("alt");
	$("#"+gridId+" tr:not(:first)").mouseover(function(){
		$(this).addClass("over");}).mouseout(function(){
		$(this).removeClass("over");})
}

function fanhui(json){
	if(json != undefined){		
		var jsonForm = json.hiddenform;
		for(var fieldName in jsonForm){//如果有查询条件，则给查询条件赋值
			$("[name="+fieldName+"]").val(jsonForm[fieldName]);
		}
		$("#hiddenform").val(jsonToString(jsonForm));//重新赋值，供下次查询时使用
		var ids = $("#ids").val().split(',');	
		value2Grid("grid", ids, json);
	}
}

function jsonToString(json){
	var temp = "";
	var str = "";
	for(var fieldName in json){
        var fieldValue=json[fieldName];
        temp += fieldName +':"'+fieldValue+'",';
        str += "&"+fieldName +"="+ fieldValue;
	}
	temp = temp.replace(/,$/,"");
	temp += "";
	str = str.replace(/&$/,"");
	return temp;
}


function ajax4KaoHeQueRen(url, data, gridId, ids, count){
	$.ajax({
		type: "POST",
		url: url,
		data: data,
		dataType: "json",
		success:function(data){
			var content;
			var num = 0;
			while($("#"+gridId+" tr:not(:first)").val() == $("#"+gridId+" tr:last").val()){
				$("#"+gridId+">tbody>tr:last").remove();
			}
			var content = "";
			var hidden = "";
			var num = 0;
			var arr;
			$("#pageNo").val(data.pageNo); 
	        $("#pageAll").text(data.pageAll); 
	        $("#rowAll").text(data.rowAll);
			if(data.retList != undefined){
				$.each(data.retList, function (key, value){
					hidden = "";
					var confirm_status = 0;
					content += "<tr id='trid' onclick='trclick(this)'><td>"+value["rn"]+"</td>"
					content +="<td><input name='box' value="+value[ids[0]]+" type='checkbox'/></td>"
					for(var i = 1; i < ids.length; i++){						
						if(value[ids[i]]==null||undefined == value[ids[i]] ){							
							value[ids[i]] ="";							
						}
						arr = ids[i].split("#");
						ids[i] = jQuery.trim(ids[i]);
						if(arr[1] == "hidden"){
							hidden += '<input name="'+ids[i]+'" type="hidden" value="'+hidden4KaoHeQueRe(ids[i],value[ids[i]],value['ranktransstatus#hidden'], value['confirm_status#hidden']) +'"/>';
						}else {
							content += '<td><div id="'+ids[i]+'" style="width:100%;height:20px;text-overflow:ellipsis; white-space:nowrap; overflow:hidden; ">'+ 
							selected4KaoHeQueRe(ids[i], value[ids[i]],value['confirm_status#hidden'], value['old_rank#hidden'],value['recom_rank#hidden'], value['ranktransstatus'])  +'</div></td>';
						}
					}
					content += hidden+"</tr>";
				});
			}else{
				content += "<tr id='trid' onclick='trclick(this)'><td></td>"
				content +="<td><input name='box' value ='' type='checkbox'/></td>"
				for(var i = 1; i < ids.length; i++){
					arr = ids[i].split("#");
					ids[i] = jQuery.trim(ids[i]);
					if(arr[1] != "hidden"){						
						content += "<td></td>";
					}
				}
				content += "</tr>";
			}
			$("#"+gridId).append($(content));
			$(".tablestyle tr:odd").addClass("alt");
			$(".tablestyle tr:not(:first)").mouseover(function(){
				$(this).addClass("over");}).mouseout(function(){
				$(this).removeClass("over");})
		}
	});
}

function hidden4KaoHeQueRe(name, value, ranktransstatus, confirm_status) {
	if(confirm_status != 1 && name == "final_rank_stats#hidden") {
		return ranktransstatus;
	}
	return value;
}

function selected4KaoHeQueRe(name, value,confirm_status, old_rank, recom_rank, ranktransstatus) {
	
		if(confirm_status != 1 && name == "new_rank_name") {
			var conTransType = "conTransType(this)";
			var data = "rank_code=" + old_rank;
			var selectStr = "<select id='rank' onchange='"+ conTransType +"'>";
			$.ajax({
				type: "POST",
				url: 'chaXunRankRange.controller',
				data: data,
				dataType: "json",
				async: false,
				success:function(data){
					$.each(data.retList, function (key, value){
						var rank_range_arr = value['rank_range'].split('#');
						for(var i=0; i<rank_range_arr.length; i++) {
							var new_rank_arr = rank_range_arr[i].split("|");
							selectStr = selectStr + "<option value ='"+ new_rank_arr[0] +"' " + ((recom_rank==new_rank_arr[0]) ? "SELECTED" : "") +">"+ new_rank_arr[1] +"</option>";
						}
					})
					selectStr = selectStr + "</select>"
				}
			});
			return selectStr;
		}
	else if(confirm_status != 1 && name == "f_rank_stats_name") {
			return ranktransstatus;
	}
	else {
		return value;
	}
}

function conTransType(obj, old_rank) {
	var num = obj.parentElement.parentElement.parentElement.rowIndex - 1;
	if(obj.value == 'FIRE') {
		 ($("div[id='f_rank_stats_name']"))[num].innerHTML = "解聘";
		 ($("[name='final_rank_stats#hidden']"))[num].value = "4";
		 return;
	}
	var old_rank_code   =  ($("[name='old_rank#hidden']"))[num].value;
	var new_rank_code = obj.value;
	var data = "old_rank_code=" + old_rank_code + "&new_rank_code=" + new_rank_code;
	if(new_rank_code == old_rank_code ) {
		 ($("div[id='f_rank_stats_name']"))[num].innerHTML = "维持";
		 ($("[name='final_rank_stats#hidden']"))[num].value = "2";
		 return;
	}
	$.ajax({
		type: "POST",
		url: 'compareRank.controller',
		data: data,
		dataType: "json",
		async: false,
		success:function(data){
			var transArr = data.rm[0].key.split('#');
			 ($("div[id='f_rank_stats_name']"))[num].innerHTML = transArr[1];
			 ($("[name='final_rank_stats#hidden']"))[num].value = transArr[0];
		}
	});
	
}

var row;
var obj;
function trclick(tr){
	if(row%2 == 0){
		obj == null ? '':obj.className = "alt";
	}else{
		obj == null ? '':obj.className = "";
	}
	for(var i = 0; i < $("[name='box']").length; i++){		
		if(i == tr.rowIndex - 1){
// if(($("[name='box']"))[i].checked){
// ($("[name='box']"))[i].checked = false;
// }else{
// ($("[name='box']"))[i].checked = true;
// }
			tr.className = "select";
			row = i;
		}
	}	
	obj = tr;
}

// 将页面内容拼成字符串，如："e_id=123&name=123"
function elementsToValueString(form_name){
	var fields=null;
	if(form_name){
		fields = document.forms(form_name).elements;
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
		switch (fieldTypeName) {
			case 'select-multiple':
				value=getSelectMultipleValues(fields[i]);
				break;
			case 'radio':
				if (fields[i].checked) {
					break;
				}
				continue;
			case 'checkbox':
				if (num==0) {
					// value=getCheckboxValues(fieldName);
					break;
				}
				continue;
			case 'button':
				continue;
		}
		if(value != ""){			
			fieldValue += ",'"+fieldName + "':'" + value +"'" ;
		}
	}
// fieldValue += "}";
	return fieldValue;
}

function elementsToValue(form_name){
	var fields=null;
	if(form_name){
		fields = document.forms(form_name).elements;
	}else{
		fields = $(":input");
	}		
	var fieldNameValue = [];// 装载属性及对应的值
	var fieldNameNum = [];// 记录属性出现次数,处理数组
	var fieldValue = "'empty'='1'";
	
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
		switch (fieldTypeName) {
			case 'select-multiple':
				value=getSelectMultipleValues(fields[i]);
				break;
			case 'radio':
				if (fields[i].checked) {
					break;
				}
				continue;
			case 'checkbox':
				if (num==0) {
					// value=getCheckboxValues(fieldName);
					break;
				}
				continue;
			case 'button':
				continue;
		}
		if(value != ""){			
			fieldValue += "&"+fieldName + "=" + value;
		}
	}
// fieldValue += "}";
	return fieldValue;
}

function formToJsonString(formNames){	
	var fields = null;
	var fieldValue = "";
	var json = "";
	var reg = /{$/;
	var formName = formNames.split(",");
	for(var j = 0; j < formName.length; j++){
		if(formName[j] != ""){
			fields = document.forms(formName[j]).elements;
		}else{
			fields = $(":input");
		}		
		var fieldNameValue = [];// 装载属性及对应的值
		var fieldNameNum = [];// 记录属性出现次数,处理数组
		// j == 0 ? json += formName[j]+":{" : json += ","+formName[j]+":{"
		for ( var i = 0; i < fields.length; i++) {		
			var fieldTypeName = fields[i].type;
			var fieldName = fields[i].name;
			if (fieldName == null || fieldName == ""|| fieldName == "hiddenform") {
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
				value=getSelectMultipleValues(fields[i]);
				break;
			case 'radio':
				if (fields[i].checked) {
					break;
				}
				continue;
			case 'checkbox':
				if (num==0) {
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
			fieldValue += fieldName + '=' + value ;				
		}
	}
// json += "}";
	return json;
}

// 将JSON字符串回填到页面
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

// 将JSON字符串回填到表格中
function fillElementsGrid(key,jsonStr,arr,index){
	var content = "";
	var row = 0;
	if (jsonStr instanceof Array) {
		if(jsonStr == ""){
// content += "<tr id='trid####'>";
// for(var i = 0; i < arr.length; i++){
// content += "<td></td>";
// }
// content += "</tr>";
// $("#"+key).append($(content));
		}
		for(var index in jsonStr){
            var obj=jsonStr[index];
            fillElementsGrid(key,obj,arr,index);
        }
	} else if (jsonStr instanceof Object){
		content += "<tr id='trid"+index+"'>";
		for(var i = 0; i < arr.length; i++){
			content += "<td  name ="+arr[i]+">"+jsonStr[arr[i]]+"</td>";
		}			
		content += "</tr>";
		$("#"+key).append($(content));
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
// if(fieldValue instanceof Array){
// for(var index in fieldValue){
// var obj= fieldValue[index];
// fillElementsValue(key,obj,index);
// }
// }
            var fields=document.getElementsByName(fieldName);
// var fields = $("#"+key+"[name="+fieldName+"]");
            if(fields.length==0){
            	// alert("警告：该属性【"+fieldName+"】未找到对应的页面元素");
            	continue ;
            }
            var fieldTypeName=fields[0].type;
            switch (fieldTypeName) {
            	case 'checkbox':
					checked(fields,fieldValue);
					continue;
            	case 'select-one':
            		selectnew(fieldName, fieldValue);
					continue;
            	case 'radio':
					checked(fields,fieldValue);
					continue;
				case 'select-multiple':               
					selected(fields[index],fieldValue);
					continue;
			}
            fields[index].value=fieldValue;
        }
	} else {
		alert("在自动解析JSON时，遇到未知类型！");
    }
}

function checked(fields,fieldValues){
    var fieldValue=fieldValues.split(",");
    for(var index=0;index<fields.length;index++){
        var value=fields[index].value;
        fields[index].checked=false;
        for(var key in fieldValue){
        	if(value==fieldValue[key]){
        		fields[index].checked=true;
        	}
        }
    }
}

function selectnew(sltName,value){
	if($("[name="+sltName+"]").length > 0){
		$("[name="+sltName+"]").val(value);
	}
}

// 页面分页查询功能
function chaxunPage(){
	var formValue = formToJsonString("form1,form3");
	$("#hiddenform").val(formValue);
	form1.action = $("#chaxun").attr('name');
	form1.submit();
}

// 待删除
function query4Rows(obj){
// alert(1123211);
	var data = elementsToValueString("form");
// alert(data);
	var ids = $("#ids").val().split(',');
	ajax(obj.name, data, "grid", ids);
}
// 所有修改方法
function doUpdate(){
    var data = elementsToValueString("form");
	
    $.ajax({
		type: "POST",
		url: $("#do_UpDATA").attr('name'),
		data: data,
		dataType: "json",
		success:function(data){ alert("修改成功"); }
        
	});
}

// 根据form获取页面所有table 的id,
// 然后根据ID获取tab页中的所有输入信息
function getTableIdByForm(){
	var subJosn = "";
	var tabJson = "";
	 $("form").find("table").each(function(){
		 if($(this).attr("id")!="" && "tableid" !=$(this).attr("id")){
			 // 通过表名获取该表中所有的信息项
// subJosn += "&"+$(this).attr("id")+"="+getJsonString($(this).attr("id"));
			 tabJson =  getJsonString($(this).attr("id"));
			 if(tabJson != ""){
				 subJosn  += ",'"+$(this).attr("id")+"':'"+tabJson+"'";
			 }
		 }
	 });
	 return subJosn;  
}
// 返回表格中所有的数据的json数据，已备传至后台
function getJsonString(table_id){
			var trd = "[";
			$("#"+table_id+" tr").not("#"+table_id+" tr:first").each(function(){
				if(this.style.display!= "none"){					
					var tds = $("#"+table_id+" #"+this.id+" td");					
					var tdd = "{";
					for(var i=1;i<tds.size();i++){
						if(tdd=="{"){
							tdd=tdd+"\""+tds[i].name+"\":\""+tds[i].innerHTML+"\"";
						}else{
							tdd=tdd+",\""+tds[i].name+"\":\""+tds[i].innerHTML+"\"";
						}
					}
					tdd=tdd+"}";
					
					if(trd=="["){
						trd=trd+tdd;
					}else{
						trd=trd+","+tdd;
					}

				}
			});

			trd=trd+"]";
			if(trd == "[]"){
				return "";
			}
			return trd;
}

// 根据table id 添加行
function addRow(table_id){

		var rows=$("#"+table_id);
		var vNum=$("#"+table_id+" tr").size();
		var vTr=$("#"+table_id+" tr:first");
		var tClone=vTr.clone(true);
		var tr_id = "tr_"+vNum;
		
		tClone[0].id=tr_id
		tClone.appendTo(rows);

		$("#"+table_id+" #"+tr_id+" input[type='checkbox']")[0].id="ck_"+vNum;
		$("#"+table_id+" #"+tr_id+" input[type='checkbox']").unbind("click");
		$("#"+table_id+" #"+tr_id+" input[type='checkbox']")[0].value=tr_id;

		var tds = $("#"+table_id+" #"+tr_id+" td");

		tds.click(tdclick);
		
		for(var i=1;i<tds.size();i++){
			
			
				tds[i].innerHTML="";
			
		}
}
// 根据table id 删除行,将行禁用掉，以备以后修改成禁用修改
function removeRow(table_id){
	var count = 0;
	$('#'+table_id+' input[type="checkbox"]').not("#ck_0").each(function(){		
		if(this.checked){		
			count++;
		}					
	});
	if(count == 0){
		alert("请选择要删除的信息！");
	} else if(confirm("是否将此信息删除?")){
		$('#'+table_id+' input[type="checkbox"]').not("#ck_0").each(function(){
			if(this.checked){		
				$(this).parents('tr').remove();
			}
		});

		$("#"+table_id+" tr:odd").addClass("alt");
		$("#"+table_id+" tr:even").filter(":gt(0)").removeClass("alt");
	}	
}


function string2json(strJson){
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

// 导航标题 例如菜单管理 > 财富渠道 > 团队管理 > 团队编制审批
function resetTitle(type,url){
	var urlStr="";
	var fs = window.parent.document.frames;
	var div = null;
	for(var i = 0; i < fs.length; i++){
		if (window == fs[i]) {
			if(vNumber>6.0){
				var iframe = window.parent.document.getElementsByTagName("iframe")[i];
    			var iframeid = iframe.id;
	    		var divid = iframeid.substring(5, iframeid.length)+"text";
		    	div = window.parent.parent("rightFrameTop").document.getElementById(divid);
			}
			else{
				var iframe = window.parent.document.getElementById("rightFrame");
				div = window.parent("rightFrameTop").document.getElementById("top_td_text");
			}
			content = jQuery(div).html();
			if(type=="ahref"){
				urlStr = url + "?funcID="+url;
				content = content.substring(0,content.indexOf("\"")+1)+urlStr+content.substring(content.lastIndexOf("\""));
				jQuery(div).html(content);
				$(jQuery(div).find("a")[0]).click(function(){
					$(iframe).attr("src",url);
					content = content.substring(0,content.indexOf("\"")+1)+"#"+content.substring(content.lastIndexOf("\""));
					jQuery(div).html(content.substring(0, content.lastIndexOf(">")+1));
					return false;
				});
			}else{
				if(type=="fanhui"){
					content = content.substring(0,content.indexOf("\"")+1)+"#"+content.substring(content.lastIndexOf("\""));
					jQuery(div).html(content.substring(0, content.lastIndexOf(">")+1));
				}else{
					var val = "";
					if(type=="xinzenginit"){
						val = "新增";
					}else if(type=="xiugaiinit"){
						val = "修改";
					}else if(type=="mingxi"){
						val = "明细";
					}
					jQuery(div).html(content+" > "+val);
				}
			}
		}
	}
	
}
