/**
 * 
 * @param allPage 总页数(显示分页使用)
 * @param nowPage 当前第几页(显示分页使用)
 * @param allRows 总记录数(显示检索到的记录数使用)
 * 
 */
function fillPagination(allPage, nowPage, allRows, namespace) {
	var page = "";
	// 显示当前页前2页，以及后1页的连接
	var bef = nowPage - 2;
	var aft = nowPage - 0 + 1;
	if (bef < 1) {
		bef = 1;
		aft = (bef + 3) > allPage ? allPage : bef + 3;
	}
	if (aft > allPage) {
		aft = allPage;
		bef = (allPage - 3) < 1 ? 1 : allPage - 3;
	}
	if (bef == 2)
		page += "<li><a href='#' name='"+namespace+"' onclick='showPage(1,this.name)'>1</a></li>";
	if (bef > 2)
		page += "<li><a href='#' name='"+namespace+"' onclick='showPage(1,this.name)'>1</a></li><li><a>...<span class='sr-only'></span></a></li>";
	for ( var i = bef; i <= aft; i++) {
		if (i == nowPage) {
			page += "<li class='active'><a href='#'>" + i + "<span class='sr-only'></span></a></li>";
			continue;
		}
		page += "<li><a href='#' name='"+namespace+"' onclick='showPage("+i+",this.name)'>" + i + "</a></li>";
	}
	if (aft == allPage - 1)
		page += "<li><a href='#' name='"+namespace+"' onclick='showPage("+allPage+",this.name)'>" + allPage + "</a></li>";
	if (aft < allPage - 1)
		page += "<li><a>...<span class='sr-only'></span></a></li><li><a href='#' name='"+namespace+"' onclick='showPage("+allPage+",this.name)'>" + allPage + "</a></li>";
	//page += "<li><input type='text' id='tzpagenum'/><a href='#' onclick='showPage()'>跳转</a></li>";
	page += "<input type='hidden' id='allPage'/><input type='hidden' id='nowPage' name='nowPage'/><input type='hidden' id='allRows'/>";
	var ulPage = $("#" + namespace + " ul[id='pagination']");
	ulPage.empty();
	ulPage.append(page);
	$("#" + namespace + " input[id='allPage']").val(allPage); // 所有页(给点击下一页时使用)
	$("#" + namespace + " input[id='allRows']").val(allRows); // 所有记录(给点击下一页时使用)
	$("#" + namespace + " input[id='nowPage']").val(nowPage); // 当前页(检索到的记录数)
	$("#" + namespace + " select[id='row4Page']").attr('data-ns',namespace);
	$("#" + namespace + " button[id='tiaozhuan']").attr('data-ns',namespace);
}

function showPage(nowPage, namespace){
	if(nowPage == null || nowPage == "" || nowPage == undefined){
		return;
	}
	var nowPageBak = nowPage;
	// 总页面数
	var allPage = $("#" + namespace + " input[id='allPage']");
	// 总记录数
	var allRows = $("#" + namespace + " input[id='allRows']");
	// 当前显示的页面编码
	var nowPage_current = $("#" + namespace + " input[id='nowPage']");
	// 将要显示的页面
	var tbody_now = $("#" + namespace + " tbody[id='page_" + nowPage + "']");
	// 当前显示的页面
	var tbody_current = $("#" + namespace + " tbody[id='page_" + nowPage_current.val() + "']");
	if(tbody_now[0] != undefined){ // 当前页存在，则直接显示
		//alert("当前页存在，则直接显示");
		tbody_current.hide();
		tbody_now.show();
		fillPagination(allPage.val(), nowPage, allRows.val(), namespace);
	}
	if(tbody_now[0] == undefined){ //当前页面不存在，则加载后显示
		//alert("当前页面不存在，则加载后显示");
		tbody_current.hide();
		//当前页大于最大页数 - 3，则从最大页数-3开始预加载
		if(nowPage >= allPage.val() - 2){
			nowPageBak = allPage.val() - 2;
			preloadData(nowPageBak, false, namespace);
		}else{
			preloadData(nowPage - 0, false, namespace);
		}
		// 若总记录数发生变化，则移除本次加载之外的所有页面
		var newAllRows = $("#" + namespace + " input[id='allRows']");
		if(allRows.val() != newAllRows.val()){
			//alert("记录数变化，清空现有，重新加载");
			$("#" + namespace + " tbody[id][id!='page_" + nowPageBak + "'][id!='page_" + (nowPageBak - 0 + 1) + "'][id!='page_" + (nowPageBak - 0 + 2) + "']").remove();
		}
		allPage = $("#" + namespace + " input[id='allPage']");
		if(nowPage - 0 > allPage.val() - 0){
			nowPage = allPage.val();
		}
		fillPagination(allPage.val(), nowPage, newAllRows.val(), namespace);
		$("#" + namespace + " tbody[id='page_" + nowPage + "']").show();
		return;
	}
	nowPageBak = (nowPage + 2) > (allPage - 0) ? (allPage - 0) : (nowPage + 2);
	var tbody_nowPageBak = $("#" + namespace + " tbody[id='page_" + nowPageBak + "']");
	if(tbody_nowPageBak[0] == undefined){ // 当前页存在，并且当前页加2页为空时，预加载
		//alert("当前页存在，并且当前页加2页为空时，预加载[maxPage]"+$("#maxPage").val());
		var maxPage = $("#" + namespace + " input[id='maxPage']");
		if(maxPage.val() - 0 < allPage.val() - 0){ //当前加载到本地的最大页小于总页数时才执行
			nowPageBak = maxPage.val() - 0 + 1; 
			preloadData(nowPageBak, true, namespace); //从当前加载到本地的最大页+1页开始，再预加载3页
			// 若总记录数发生变化，则移除本次加载之外的所有页面，并重新加载当前页面
			if(allRows.val() != $("#" + namespace + " input[id='allRows']").val()){
				//alert("记录数变化，清空现有，重新加载");
				$("#" + namespace + " tbody[id][id!='page_" + nowPageBak + "'][id!='page_" + (nowPageBak - 0 + 1) + "'][id!='page_" + (nowPageBak - 0 + 2) + "']").remove();
				showPage(nowPage, namespace); //重新加载当前页面
			}
		}
		return;
	}
	if($("#" + namespace + " tbody[id='page_" + (nowPage - 2) + "']")[0] == undefined){ // 当前页存在，并且当前页-2页为空时预加载，-2 与+2不会同时存在
		//alert("当前页存在，并且当前页-2页为空时预加载，-2 与+2不会同时存在");
		nowPageBak = nowPage - 3; //当前加载到本地的最小页
		if(nowPageBak <= 1){
			return;
		}
		preloadData(nowPageBak, true, namespace);
		// 若总记录数发生变化，则移除本次加载之外的所有页面，并重新加载当前页面
		if(allRows.val() != $("#" + namespace + " input[id='allRows']").val()){
			//alert("记录数变化，清空现有，重新加载");
			$("#" + namespace + " tbody[id][id!='page_" + nowPageBak + "'][id!='page_" + (nowPageBak - 0 + 1) + "'][id!='page_" + (nowPageBak - 0 + 2) + "']").remove();
			showPage(nowPage, namespace); //重新加载当前页面
		}
	}
}

function chgRow4Page(btn){
	var namespace = $(btn).attr("data-ns");
	$("#" + namespace + " tbody[id][id^='page_']").remove();
	preloadData("1", false, namespace);
	// 总页面数
	var allPage = $("#" + namespace + " input[id='allPage']").val();
	// 总记录数
	var allRows = $("#" + namespace + " input[id='allRows']").val();
	fillPagination(allPage, 1, allRows, namespace);
	$("#" + namespace + " tbody[id='page_1']").show();
}

function tiaoZhuan(btn){
	var namespace = $(btn).attr("data-ns");
	// 查找总页面数，若总页面数小于当前要跳转的页，则不跳转
	var allPage = $("#" + namespace + " input[id='allPage']").val();
	var tzpagenum = $("#" + namespace + " input[id='tzpagenum']").val();
	if(allPage == 0 && tzpagenum != ""){
		showPage(tzpagenum, namespace);
	}
	if(tzpagenum == "" || tzpagenum > allPage)
		return;
	showPage(tzpagenum, namespace);
}

function preloadData(nowPage, async, namespace){
	if(nowPage < 1) nowPage = 1;
	var tbody_now = $("#" + namespace + " tbody[id='page_" + nowPage + "']");
	if(tbody_now[0] != undefined){
		tbody_now.show();
		return;
	}
	//alert("ajax  预加载");
	//alert($("#" + namespace + " button[id='chaxun']").attr('name'));
	//alert($("#" + namespace + " [id='row4Page']").val());
	//alert("{"+formToJsonString("form1", namespace)+",nowPage:\""+nowPage+"\",row4Page:\""+$("#" + namespace + " input[id='row4Page']").val()+"\",rqstType:\"AJAX\"}");
	$.ajax({
		async: async,
		type: "POST",
		url: $("#" + namespace + " button[id='chaxun']").attr('name'),
		data: string2json("{"+formToJsonString("form1", namespace)+",nowPage:\""+nowPage+"\",row4Page:\""+$("#" + namespace + " select[id='row4Page']").val()+"\",rqstType:\"AJAX\"}"),
		dataType: "json",
		success:function(data){
			value2Grid("grid", data, namespace);
		},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
			alert("status = " + XMLHttpRequest.status + " | readyState = "
					+ XMLHttpRequest.readyState + " | textStatus = " + textStatus
					+ " | errorThrown = " + errorThrown);
        }
	});
}