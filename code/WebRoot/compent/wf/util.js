function isNull(input) {
	var obj = $(input);
	if (obj.attr("notnull") == undefined)
		return;
	var parent = obj.parent();
	var clazz = parent.attr("class");
	if ($.trim(obj.val()) == "") {
		if (clazz.indexOf("has-error") < 0) { // 小于0，既没有错误提示样式
			parent.attr("class", clazz + " has-error");
			var onblur = obj.attr("onchange");
			if (onblur == undefined)
				onblur = "";
			else
				onblur = "," + onblur;
			if (onblur.indexOf("isNull") < 0) // 不包含为空校验，则加入
				obj.attr("onchange", "isNull(this)" + onblur);
		}
		var t = "不能为空.";
		if (parent.find("span label").html() != undefined)
			t = "[" + parent.find("label").html() + "]不能为空.";
		updateTips(t);// 提示信息
		return false;
	}
	if (clazz.indexOf("has-error") >= 0) {
		clazz = clazz.replace("has-error", "");
		parent.attr("class", clazz);
	}
}

function restrictLength(input) {
	var obj = $(input);
	obj.val($.trim(obj.val()));
	var parent = obj.parent();
	var onblur = obj.outerHTML();
	var val = obj.val(), len = 0;
	if (val != undefined && val != "")
		len = val.length;
	var isInput = input.type != "textarea" && input.type != "select-one" && input.type != "hidden"
			&& input.type != "select-multiple" && input.type != "checkbox"
			&& input.type != "radio" && obj.attr("id") != "hiddenform"
			&& obj.attr("id") != "showpagequery";
	var lable = parent.parent().find("label").text();
	if (lable == undefined)
		lable == "";
	var clazz = parent.attr("class");
	if (isInput && (val.indexOf("\"") >= 0 || val.indexOf("\\") >= 0 || val.indexOf("'") >= 0 || val.indexOf("$") >= 0 || val.indexOf("?") >= 0 || val.indexOf(";") >= 0)) {
		updateTips(lable + "包含非法字符[\"]或[\\]或[']或[$]或[?]或[;].");
	}
	if (onblur == undefined || onblur.indexOf("checkLength") < 0) {
		if (isInput && len > 32) {
			var onchange = obj.attr("onchange");
			if (onchange == undefined)
				onchange = "";
			else
				onchange = "," + onchange;
			obj.attr("onchange", "checkLength(this,'" + lable + "',0,32)"
					+ onchange);
			if (clazz.indexOf("has-error") < 0) // 小于0，既没有错误提示样式
				parent.attr("class", clazz + " has-error");
			var t = "";
			if (lable[0] != undefined)
				t = "[" + lable + "]的";
			updateTips(t + "长度必须在[0]位到[32]位之间.");
		}
		return;
	}
	onblur = onblur.substring(onblur.indexOf("checkLength"), onblur.length);
	onblur = onblur.substring(12, onblur.indexOf(")"));
	onblur = onblur.replace(/\'/g, '');
	onblur = onblur.replace(/[ ]/g, "");
	var param = onblur.split(",");
	param[0] = input;
	doCallback(eval("checkLength"), param);
}

function isPwd(input) {
	jQueryObj = $(input);
	var onblur = jQueryObj.outerHTML();
	if (onblur == undefined || onblur.indexOf("checkPWd") < 0) {
		return;
	}

	onblur = onblur.substring(onblur.indexOf("checkPWd"), onblur.length);
	onblur = onblur.substring(12, onblur.indexOf(")"));
	onblur = onblur.replace(/\'/g, '');
	onblur = onblur.replace(/[ ]/g, "");
	var param = onblur.split(",");
	param[0] = input;
	doCallback(eval("checkPWd"), param);
}

function isEmail(input) {
	jQueryObj = $(input);
	var onblur = jQueryObj.outerHTML();
	if (onblur == undefined || onblur.indexOf("checkEmail") < 0) {
		return;
	}

	onblur = onblur.substring(onblur.indexOf("checkEmail"), onblur.length);
	onblur = onblur.substring(12, onblur.indexOf(")"));
	onblur = onblur.replace(/\'/g, '');
	onblur = onblur.replace(/[ ]/g, "");
	var param = onblur.split(",");
	param[0] = input;
	doCallback(eval("checkEmail"), param);
}

function doCallback(fn, args) {
	fn.apply(this, args);
}

/**
 * 校验长度
 * 
 * @param o
 *            被校验对象
 * @param n
 *            被校验对象名称
 * @param min
 *            最小值
 * @param max
 *            最大值
 * @returns {Boolean}
 */
function checkLength(o, n, min, max) {
	var ipt = $(o);
	var parent = ipt.parent();
	var clazz = parent.attr("class");
	var _if, t;
	if (!isNaN(min) && isNaN(max)) // 最小值存在，最大值不存在
		max = 32;
	if (!isNaN(min)) {// 是数字
		_if = ipt.val().length > max || ipt.val().length < min;
		t = "[" + n + "]的长度必须在[" + min + "]位到[" + max + "]位之间.";
	} else {// 不是数字
		_if = ipt.val() == "";
		t = "[" + n + "]不能为空.";
		if (ipt.val().length > 32 && o.type != "textarea") {
			t = "[" + n + "]的长度必须小于[32]位.";
			_if = true;
		}
	}
	if (_if) {
		if (clazz.indexOf("has-error") < 0) { // 小于0，既没有错误提示样式
			parent.attr("class", clazz + " has-error");
		}
		updateTips(t);// 提示信息
		return false;
	} else {
		if (clazz.indexOf("has-error") >= 0) {
			clazz = clazz.replace("has-error", "");
			parent.attr("class", clazz);
		}
		return true;
	}
}
/**
 * 更新提示信息
 * 
 * @param t
 *            提示信息
 */
function updateTips(t) {
	var checkuiMsg = $("#checkui-msg");
	if (checkuiMsg[0] == undefined) {
		$("body")
				.append(
						'<div class="modal fade" id="checkui-msg" tabindex="-1" role="dialog" aria-labelledby="msg-titls" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div id="msg-header" class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title" id="checkui-titls"></h4></div><div class="modal-body" id="checkui-body"></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">Close</button></div></div></div></div>');
		checkuiMsg = $("#checkui-msg");
	}
	var checkui_titls = checkuiMsg.find("[id='checkui-titls']");
	checkui_titls.text("失败");
	var checkuiBody = checkuiMsg.find("[id='checkui-body']");
	if (checkuiBody.outerHTML().indexOf(t) < 0)
		checkuiMsg.find("[id='checkui-body']").append("<h5>" + t + "</h5>");
	checkuiMsg.modal({show : 'true'});
	checkuiMsg.on('hidden.bs.modal', function(e) {
		$("#checkui-msg").remove();
	});
}

/**
 * 校验正则表达式
 * 
 * @param o
 *            被校验对象
 * @param regexp
 *            校验表示式
 * @param n
 *            提示信息
 * @returns {Boolean}
 */
function checkRegexp(o, regexp, n) {
	var input = $(o);
	if (!(regexp.test(input.val()))) {
		input.addClass("ui-state-error");// TODO 错误样式,需要修改
		updateTips(n);
		return false;
	} else {
		return true;
	}
}
/**
 * 只能录入正整数
 * 
 * @param input
 *            要录入的对象
 */
function onlyInt(input) {
	$(input).val($(input).val().replace(/\D/g, ''));
}

/**
 * 只能录入正小数
 * 
 * @param input
 *            要录入的对象
 */
function onlyDec(input) {
	var obj = $(input);
	// 先把非数字的都替换掉，除了数字和.
	obj.val(obj.val().replace(/[^\d.]/g, ""));
	// 必须保证第一个为数字而不是.
	obj.val(obj.val().replace(/^\./g, ""));
	// 保证只有出现一个.而没有多个.
	obj.val(obj.val().replace(/\.{2,}/g, "."));
	// 保证.只出现一次，而不能出现两次以上
	obj.val(obj.val().replace(".", "$#$").replace(/\./g, "")
			.replace("$#$", "."));
	var value = obj.val();
	if (value.indexOf("0") == 0 && value.length > 2 && value.indexOf("0.") != 0)
		obj.val("0");
}
// a到z并且0到9校验:/^[a-z]([0-9a-z_])+$/i
// email校验:/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i
