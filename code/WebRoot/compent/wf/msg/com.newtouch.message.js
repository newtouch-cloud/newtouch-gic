//弹出提示信息
function alertMsg4Modal(data, backdrop) {
	var modal = $("div[class='modal fade in']");
	var msgArray = modal.find("div[id='msgalert']");
	var tbody = modal.find("table tbody");
	if(msgArray != undefined){
		$.each(msgArray,function(i,value){
			$(value).hide();
		});
	}
	if(tbody != undefined){
		$.each(tbody,function(i,value){
			$(value).remove();
		});
	}
	if(modal[0] != undefined){
		if (data && data.msg && data.flag) {
			var msgHtml = '<div id="msgalert" class="alert alert-danger"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>';
			$.each(data.msg, function(i, value){
				$.each(value, function(msgKey, msgInfo){
					if(msgKey != "")
						msgKey += ":";
					msgHtml += "<h5>" + msgKey + msgInfo + " </h5>";
				});
			});
			msgHtml += "</div>";
			modal.find("div[class='modal-body']").prepend(msgHtml);
			setTimeout("$('#msgalert').fadeOut()",10000);
		}
		return;
	}
	var normalMmsg = $('<div class="modal fade" id="modal-msg" tabindex="-1" role="dialog" aria-labelledby="msg-titls" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div id="msg-header" class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title" id="msg-titls"></h4></div><div class="modal-body" id="msg-body"></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">Close</button></div></div></div></div>');
	if (data && data.msg && data.flag) {
		var msg = "<ul>";
		$.each(data.msg, function(i, value){
			$.each(value, function(msgKey, msgInfo){
				if(msgKey != "")
					msgKey += ":";
				msg += "<h5>" + msgKey + msgInfo + " </h5>";
			});
		});
		msg += "</ul>";
		var msg_titls = normalMmsg.find("[id='msg-titls']");
		//var msg_header = normalMmsg.find("[id='msg-header']");
		if(data.flag == 'success'){
			msg_titls.text("成功");
			//msg_header.attr("class", "modal-header btn-success");
		}
		if(data.flag == 'warn'){
			msg_titls.text("警告");
			//msg_header.attr("class", "modal-header btn-warning");
		}
		if(data.flag == 'fail'){
			msg_titls.text("失败");
			//msg_header.attr("class", "modal-header btn-danger");
		}
		var msgBody = normalMmsg.find("[id='msg-body']"); 
		msgBody.empty();
		msgBody.append(msg);
		if(data.dtildMsg){
			var fstMsg = "";
			var dtMsg = "";
			var dtildMsg = $('<div class="panel-group" id="msg-dtild"><div><div><a data-toggle="collapse" data-toggle="collapse" data-parent="#msg-dtild" href="#msg-dtild-collapse">点击查看详细</a></div><div id="msg-dtild-collapse" class="panel-collapse collapse"><div class="panel-body" id="msg-dtild-body"/></div></div></div>');
			var fst = true;
			$.each(data.dtildMsg,function(key, value){
				$.each(value, function(msgKey, msgInfo){
					if(msgKey != "") msgKey += ":";
					if(fst){
						fstMsg += "<pre><p class='text-danger'>" + msgKey + msgInfo + "</p></pre>";
						fst = false;
					} else {
						dtMsg += "<p>" + msgKey + msgInfo + "</p>";
					}
				});
			});
			dtildMsg.find("[id='msg-dtild-body']").append(fstMsg + "<pre>" + dtMsg + "</pre>");
			msgBody.append(dtildMsg);
		}
		if ($("#modal-msg").length <= 0) {
			$("body").prepend(normalMmsg);
		} else {
			$("#modal-msg").remove();
			$("body").prepend(normalMmsg);
		}
		var options = "show:'true'";
		if(data.flag != 'success' && (backdrop == undefined || backdrop == ""))
			options += ",backdrop:'static'";
		$('#modal-msg').modal(string2json("{" + options + "}"));
		$('#modal-msg').on('hidden.bs.modal', function (e) {
			$('#modal-msg').remove();
		});
	}
	return true;
}

//弹出提示信息
function alertMsg(data) {
	var normalMmsg = $("<div id='normal-msg' class='normalMmsg' iconCls='icon-ok' closed='true' "
			+ "modal='true' collapsible='fasle' minimizable='fasle' maximizable='fasle' draggable='false' "
			+ "style='width:517px;'>"
			+ "<div class='normalMmsg_TIT' style='width:510px;'><span>信息提示</span><a class='close'id='msg_close' title='关闭' ></a></div>"
			+ "<div class='normalMmsg_TIT_layout' fit='true' style='width:495px;'>"
			+ "<div id='show-normal-msg' region='center' border='false' style='padding:10px;background:#fff;border:1px solid #ccc;height:100%;'>"
			+ "</div></div></div>");
	if (data && data.msgType) {
		if($("#normal-msg")[0] == undefined){
			$("body").prepend(normalMmsg);
		}
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
			msg += data.msg[index].msg + ";<br>";
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
			//拖拽
			$('#normal-msg').mousedown(function (event) { 
				var isMove = true; 
				var abs_x = event.pageX - $('div.normalMmsg').offset().left;  
				var abs_y = event.pageY - $('div.normalMmsg').offset().top;  
				$(document).mousemove(function (event) {
					if (isMove) {
						var obj = $('div.normalMmsg');
						obj.css({'left':event.pageX - abs_x, 'top':event.pageY - abs_y});
					} 
				}).mouseup( function () {isMove = false; });
			});
			//$('#normal-msg').window('open');
			return true;
		} else {
			$.messager.alert('异常信息', '未知的消息类型!');
		}
		return false;
	}
	
	
	return true;
}