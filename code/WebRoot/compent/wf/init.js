/**
 * 初始化页面需要使用的按钮
 */
$(function() {
	$("#chaxun").each(function(key, btn) {
		$(btn).click(function() {
			query(this);
		});
	});
	// 所以页面重置按钮
	$("#chongzhi").each(function(key, btn) {
		$(btn).click(function() {
			resetForm(this, "form1");
		});
	});
	// 返回
	$("#fanhui").each(function(key, btn) {
		$(btn).click(function() {
			goBack(this);
		});
	});
	$("#xinzenginit").each(function(key, btn) {
		$(btn).click(function() {
			addInit_bak(this);
		});
	});
	$("#xiugaiinit").each(function(key, btn) {
		$(btn).click(function() {
			addInit_bak(this);
		});
	});
	$("#xiugai").each(function(key, btn) {
		$(btn).click(function() {
			mdf(this);
		});
	});

	$("#xinzeng").each(function(key, btn) {
		$(btn).click(function() {
			add(this);
		});
	});
	$("#xiayibu").each(function(key, btn) {
		$(btn).click(function() {
			add(this);
		});
	});

	$("#daoruinit").each(function(key, btn) {
		$(btn).click(function() {
			addInit(this);
		});
	});
	$("#mobandownload").each(function(key, btn) {
		$(btn).click(function() {
			var url = $("#mobandownload").attr("name");
			window.location.href = url;
		});
	});
	$("#uploadFile").each(function(key, btn) {
		$(btn).click(function() {
			var file = document.getElementById("file").value;
			if (file == "") {
				alert("请选择上传文件！");
				return;
			}
			var fileType = file.substring(file.lastIndexOf(".") + 1);
			if (fileType != "xls") {
				alert("上传的excel文件类型为.xls格式！");
				return;
			}
			var url = $("#fileForm").attr('name');
			var hiddenform = $("#hiddenform").val();
			fileForm.action = url + "?hiddenform=" + hiddenform;
			fileForm.submit();
		});
	});
	$("#daochu").each(function(key, btn) {
		$(btn).click(function() {
			var namespace = _namespace($(this));
			var formValue = formToJsonString("form1,form3", namespace);
			var url = $("#daochu").attr("name");
			form1.action = url + "?" + formValue;
			form1.submit();
		});
	});
	$("#daorufanhui").each(
			function(key, btn) {
				$(btn).click(
						function() {
							$("#daorufanhui")[0].disabled = true;
							var jsonForm = string2json("{"
									+ $("#hiddenform").val() + "}");
							var form = "";
							for ( var fieldName in jsonForm) {
								form += "<input type='hidden' id='" + fieldName
										+ "' name='" + fieldName + "' value='"
										+ jsonForm[fieldName] + "'/>";
							}
							$("#fileForm").append(form);
							var url = $("#daorufanhui").attr('name');
							fileForm.action = url + "?funcID=" + url;
							fileForm.submit();
						});
			});
});