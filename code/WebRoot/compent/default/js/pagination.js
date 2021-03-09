/**
 * 
 * @param allPage 总页数(显示分页使用)
 * @param nowPage 当前第几页(显示分页使用)
 * @param allRows 总记录数(显示检索到的记录数使用)
 * 
 */
function fillPagination(allPage, nowPage, allRows) {
	//alert("allPage | "+allPage + "| \n nowPage | "+nowPage + "| \n allRows | "+allRows);
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
		page += "<a href='#' onclick='showPage(1)'>1</a>";
	if (bef > 2)
		page += "<a href='#' onclick='showPage(1)'>1</a><span>...</span>";
	for ( var i = bef; i <= aft; i++) {
		if (i == nowPage) {
			page += "<span class='nowpage'>" + i + "</span>";
			continue;
		}
		page += "<a href='#' onclick='showPage("+i+")'>" + i + "</a>";
	}
	if (aft == allPage - 1)
		page += "<a href='#' onclick='showPage("+allPage+")'>" + allPage + "</a>";
	if (aft < allPage - 1)
		page += "<span>...</span><a href='#' onclick='showPage("+allPage+")'>" + allPage + "</a>";
	page += "<span><input type='text' id='tzpagenum'/><a href='#' onclick='showPage()'>跳转</a></span>";
	page += "<input type='hidden' id='allPage'/><input type='hidden' id='nowPage'/><input type='hidden' id='allRows'/>";
	$("#pagination").empty();
	$("#pagination").append(page);
	$("#allPage").val(allPage); // 所有页(给点击下一页时使用)
	$("#allRows").val(allRows); // 所有记录(给点击下一页时使用)
	$("#nowPage").val(nowPage); // 当前页(检索到的记录数)
}

function showPage(nowPage){
	var nowPageBak = nowPage;
	var allRows = $("#allRows").val();
	if(nowPage == null || nowPage == "")
		nowPage = $("#tzpagenum").val();
	if($("#page_" + nowPage)[0] != undefined){ // 当前页存在，则直接显示
		//alert("当前页存在，则直接显示");
		$("#page_" + $("#nowPage").val()).hide();
		$("#page_" + nowPage).show();
		fillPagination($("#allPage").val(), nowPage, $("#allRows").val());
	}
	if($("#page_" + nowPage)[0] == undefined){ //当前页面不存在，则加载后显示
		//alert("当前页面不存在，则加载后显示");
		$("#page_" + $("#nowPage").val()).hide();
		//当前页大于最大页数 - 3，则从最大页数-3开始预加载
		if(nowPage >= $("#allPage").val() - 2){
			nowPageBak = $("#allPage").val() - 2;
			preloadData(nowPageBak);
		}else{
			preloadData(nowPage - 0);
		}
		// 若总记录数发生变化，则移除本次加载之外的所有页面
		if(allRows != $("#allRows").val()){
			//alert("记录数变化，清空现有，重新加载");
			$("tbody[id][id!='page_" + nowPageBak + "'][id!='page_" + (nowPageBak - 0 + 1) + "'][id!='page_" + (nowPageBak - 0 + 2) + "']").remove()
		}
		if(nowPage - 0 > $("#allPage").val() - 0){
			nowPage = $("#allPage").val();
		}
		fillPagination($("#allPage").val(), nowPage, $("#allRows").val());
		$("#page_" + nowPage).show();
		return;
	}
	var allPage = $("#allPage").val();
	nowPageBak = (nowPage + 2) > (allPage - 0) ? (allPage - 0) : (nowPage + 2);
	if($("#page_" + nowPageBak)[0] == undefined){ // 当前页存在，并且当前页加2页为空时，预加载
		//alert("当前页存在，并且当前页加2页为空时，预加载");
		if($("#maxPage").val() - 0 < $("#allPage").val() - 0){ //当前加载到本地的最大页小于总页数时才执行
			nowPageBak = $("#maxPage").val() - 0 + 1; 
			preloadData(nowPageBak); //从当前加载到本地的最大页+1页开始，再预加载3页
			// 若总记录数发生变化，则移除本次加载之外的所有页面，并重新加载当前页面
			if(allRows != $("#allRows").val()){
				//alert("记录数变化，清空现有，重新加载");
				$("tbody[id][id!='page_" + nowPageBak + "'][id!='page_" + (nowPageBak - 0 + 1) + "'][id!='page_" + (nowPageBak - 0 + 2) + "']").remove()
				showPage(nowPage); //重新加载当前页面
			}
		}
		return;
	}
	if($("#page_" + (nowPage - 2))[0] == undefined){ // 当前页存在，并且当前页-2页为空时预加载，-2 与+2不会同时存在
		//alert("当前页存在，并且当前页-2页为空时预加载，-2 与+2不会同时存在");
		nowPageBak = nowPage - 3; //当前加载到本地的最小页
		if(nowPageBak <= 1){
			return;
		}
		preloadData(nowPageBak);
		// 若总记录数发生变化，则移除本次加载之外的所有页面，并重新加载当前页面
		if(allRows != $("#allRows").val()){
			//alert("记录数变化，清空现有，重新加载");
			$("tbody[id][id!='page_" + nowPageBak + "'][id!='page_" + (nowPageBak - 0 + 1) + "'][id!='page_" + (nowPageBak - 0 + 2) + "']").remove()
			showPage(nowPage); //重新加载当前页面
		}
	}
}

function chgRow4Page(){
	$("tbody[id][id^='page_']").remove();
	preloadData("1");
	fillPagination($("#allPage").val(), 1, $("#allRows").val());
	$("#page_1").show();
}

function preloadData(nowPage){
	$.ajax({
		async: false,
		type: "POST",
		url: $("#chaxun").attr('name'),
		data: string2json("{"+formToJsonString("form1,form3")+",pageNo:\""+nowPage+"\",rqstType:\"AJAX\"}"),
		dataType: "json",
		success:function(data){
			value2Grid("grid", data);
		},
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert(errorThrown);
        }
	});
}