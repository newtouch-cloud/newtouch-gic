function doCallback(fn, args) {
	fn.apply(this, args);
}
function isNull(input) {
	jQueryObj = $(input);
	var parent = jQueryObj.parent();
	if (jQueryObj.attr("notnull") == undefined) {
		return;
	}
	var clazz = parent.attr("class");
	if ($.trim(jQueryObj.val()) == "") {
		if (!clazz.indexOf("has-error") >= 0) { // 不大于0，既没有错误提示样式
			parent.attr("class", clazz + " has-error");
			var onchange = jQueryObj.attr("onchange");
			if (onchange == undefined) {
				onchange = "";
			} else {
				onchange += ",";
			}
			if (onchange.indexOf("isNull") < 0) { // 不包含为空校验，则加入
				jQueryObj.attr("onchange", onchange + "isNull(this)");
			}
		}
		if (parent.find("span label").html() != undefined) {
			return parent.find("span label").html() + "不能为空";
		} else {
			return "";
		}
	}
	if (clazz.indexOf("has-error") >= 0) {
		clazz = clazz.replace("has-error", "");
		parent.attr("class", clazz);
	}
}

function restrictLength(input) {
	jQueryObj = $(input);
	var parent = jQueryObj.parent();
	if (jQueryObj.attr("notnull") == undefined) {
		return;
	}
	if(jQueryObj.val().length>50){
		return parent.find("span label").html() + "长度太长";
	}
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
	if ($(o).val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");// TODO 错误样式,需要修改
		updateTips(n + "的长度必须在[" + min + "]到[" + max + "]之间。");// 提示信息
		return false;
	} else {
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
	var tips = $("validateTips"); // TODO 校验信息提示，每个页面必须包含
	tips.text(t).addClass("ui-state-highlight"); // TODO 提示信息样式，需要修改
	setTimeout(function() {
		tips.removeClass("ui-state-highlight", 1500);// TODO 了解后面的1500是什么意思
	}, 500);
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
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");// TODO 错误样式,需要修改
		updateTips(n);
		return false;
	} else {
		return true;
	}
}
// a到z并且0到9校验:/^[a-z]([0-9a-z_])+$/i
// email校验:/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i
