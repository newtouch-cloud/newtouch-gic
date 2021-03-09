//显示隐藏 model 
//@target id   //sample:#model1
//function showHideInfFrm(target){
	//$(target).find(".modal-body").toggle("fade");
	//$(target).find(".top-right-btn").toggleClass("icon-chevron-down");	
//}

//显示隐藏 alert 
//@target id   //sample:#alert1
function showHideInfFrm(target){
	$(target).find(".alert-body").toggle("fade");
	$(target).find(".top-right-btn").toggleClass("icon-chevron-down");	
}

function showhide(target){
	$(target).toggle();
}

function showHideTableData(target){
	$(target).find("thead").toggle();
	$(target).find("tbody").toggle();	
	
}

