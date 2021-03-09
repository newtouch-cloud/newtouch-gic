
window.AbstractTab = {};
AbstractTab.add = function( option ){
	// 如果已经存在,就激活该菜单
	if( this.isExist(option.id) ){
		this.showSelect(option.id);
		return false;
	}
	this.addTab(option);
	this.addTitle(option);
	this.addFrame(option);
};

// 添加一个Tab
AbstractTab.addTab = function( option ){
	
	var _this = this;
	var id = option.id;
	var isLock = (option.lock ? true : false);
	var lock = option.lock ? this.lockClass : "";
	var active = this.activeClass;
	var title = option.title ? option.title : "";
	var name = this.getCutName(option.name);
	var args = {id:id,name:name,active:active,title:title,lock:lock,isLock:isLock};
	var html = this.getTabHtml(args);
	var element = jQuery(html);
	
	// 绑定选择事件并触发。
	element.click( function(){
		_this.click(this.id);
	}).click();
//	// 绑定关闭按钮事件
	element.find(".closeTab").click( function(){
		_this.close(jQuery(this).parent().attr("id"));
		return false;
	});
	element.appendTo(this.tabs);
	this.showNext(this);
};
//添加一个Title
AbstractTab.addTitle = function( option ){
	var jsonData = string2json("{menuid:'"+option.name+"',funid:'"+option.funcId+"'}");
//	var _thisTexts=this.texts;
	var id=option.id+"text";
	var click_id=option.id+"click";
	var html="<div id='"+id+"'></div>";
	var click_html="<span id='"+click_id+"'></span>";
	var element = jQuery(html);
	var click_element=jQuery(click_html);
	click_element.html("收缩");
	element.appendTo(this.texts);
	click_element.appendTo(this.zhankais);
//	var title;
	$.ajax({
		type: "POST",
		url: "getJspTitle.do",
		data: jsonData,
		dataType: "json",
		async:false,
		success:function(data){
			if(data == undefined){
				return;
			}
			element.html(data.titleDiv);
		}
	});
	this.texts.find("div").hide();
	this.zhankais.find("span").hide();
	this.texts.find("#"+id).show();
//	if(this.zhankais.find("#"+click_id).hasClass("zhankai_click")){
//		alert("os");
//		this.zhankais.find("#"+click_id).show();
//	}
	if(this.texts.find("#"+id).find("a").length>0){
		var alist=this.texts.find("#"+id).find("a");
		alist.each(function(){
			$(this).click(function(){
//				alert(alist.html());
				return false;
			});
		});
	}
};

function string2json(strJson){
	try {
		var j = "(" + strJson + ")"; // 用括号将json字符串括起来        
		return eval(j); // 返回json对象    
	} catch (e) { return ""; }
}

//当标签数量超过标签栏能允许显示的标签数量右移标签，并将新标签显示在最后。
AbstractTab.showNext=function( obj ){
	var li_Array = obj.tabs.find("li");
	var li_length = li_Array.length-1;
	var tabs_length=parseInt(obj.tabs.width()/94);
	if(li_length>tabs_length){
		var indext = li_length-tabs_length;
		for(var i=0;i<=indext;i++){
			jQuery(li_Array[i]).hide();
		}
	}
};

// 取得HTML
AbstractTab.getTabHtml = function( o ){
	var li = "<li lock='" + o.isLock + "' id='" + o.id + "' class='" + o.active + "'  title='" + o.title + "'> \n";
	li += "<a class='li " + o.lock + "'> \n";
	li += "<span class=\"left\"></span> \n";
	li += "<span class=\"text\"><span>" + o.name + "</span></span> \n";
	li += "<span class=\"right\"></span></a>\n";
	li += "<a href='javascript:void(0)' class='closeTab'></a>\n";
	li += "</li> \n";
	return li;
};

// 点击标签
AbstractTab.click = function( id ){
	
	var aClass=this.activeClass;
	this.tabs.find("li").removeClass(aClass);
	this.tabs.find("#" + id).addClass(aClass);
	this.texts.find("div").hide();
	this.texts.find("#"+id+"text").show();
	this.zhankais.find("span").hide();
	if(this.zhankais.find("#"+id+"click").hasClass("zhankai_click")){
		this.zhankais.find("#"+id+"click").show();
	}
//	单次绑定点击事件
//	var frameID=this.getFrameID(id);
//	var frames=this.frames;
//	this.textsParent.find("#stateBut").die().live("click",function(){
////		frames.find("#"+frameID)[0].contentWindow.dayin();
//		frames.find("#"+frameID)[0].contentWindow.JQueryuse();
//	});
//	frames.contents().find("#"+frameID).dayin();
//	alert(frames.find("#"+frameID)[0].contentWindow.dayin());
//	if(frames.contents().find("#"+frameID).zhankai_oder){
//		alert("ja");
//	}
//	else{alert("nein");}
	this.loadFrame(id);
};
// 点击已存在标签的菜单按钮使标签显示
AbstractTab.showSelect = function(id){
	var aClass=this.activeClass;
	this.tabs.find("li").removeClass(aClass);
	this.tabs.find("#" + id).addClass(aClass);
	this.texts.find("div").hide();
	this.texts.find("#"+id+"text").show();
	this.zhankais.find("span").hide();
	if(this.zhankais.find("#"+id+"click").hasClass("zhankai_click")){
		this.zhankais.find("#"+id+"click").show();
	}
	var li_Array = this.tabs.find("li");
	var Vli_Array = this.tabs.find("li:visible").length;
	var li_length = li_Array.length-1;
	var tabs_length=parseInt(this.tabs.width()/94);
	if(jQuery(this.tabs.find("#" + id)).is(":hidden")){
		
		this.tabs.find("li:visible:first").hide();
		this.tabs.find("#" + id).show();
	}
	else{
		if(Vli_Array>tabs_length){
			var indext = li_length-tabs_length;
			for(var i=0;i<indext;i++){
				jQuery(li_Array[i]).hide();
			}
			this.tabs.find("li:visible:first").hide();
			this.tabs.find("#" + id).show();
		}
	}
	this.loadFrame(id);
};

// 判断标签是否存在
AbstractTab.isExist = function( id ){
	if(window.name=="rightFrameTop"){
		document.getElementById(id)!=null;
	}
	else{
		return window.parent.parent("rightFrameTop").document.getElementById(id)!=null;
	}
};

// 取得简短的名称
AbstractTab.getCutName = function( name ){
	var text = new String(name);
	if( text.length > 4 ){
		return text.substring(0,4) + "...";
	}
	return text;
};
// 添加Frame
AbstractTab.addFrame = function( option ){
	var url = option.url;
	var id = this.getFrameID(option.id);
	var frame = option.frame || {};
	var style = frame.style || "width:100%;height:100%";
	var name = frame.name || id;
	var frame = "<iframe id='" + id + "' name='" + name + "' frameborder='0' style='" + style + "' src='" + url + "'></iframe>";
	jQuery(frame).appendTo(this.frames);
	this.loadFrame(option.id);
};

// 加载Tab对应的Frame
AbstractTab.loadFrame = function( id ){
	var fid = this.getFrameID(id);
    this.frames.find("iframe").hide();
    this.frames.find("#" + fid).show();
};

AbstractTab.refresh = function( id ){ 
var frameId = this.getFrameID(id); 
var src = jQuery("#" + frameId ).attr("src"); 
var isSimple = src.indexOf("?") < 0; 
var now = new Date().getTime(); 
var newSrc = src + (isSimple ? "?" : "&") + now; 
jQuery("#" + frameId).attr("src",newSrc); 
}; 


// 取得FrameID
AbstractTab.getFrameID = function( id ){
	return "Frame" + id;
};

// 关闭Tab
AbstractTab.close = function( id ){
	var activeClass = this.activeClass;
	var tab = this.tabs.find("#" + id);
	var lock = tab.attr("lock");
	var isActive = tab.hasClass(activeClass);
	if(  lock != "true" ){
		var frame = this.frames.find("#" + this.getFrameID(id) );
		var texts = this.texts.find("#"+id+"text");
		var zhankais = this.zhankais.find("#"+id+"click");
		// 如果当前标签是激活状态,要不打开后一个，要不打开前一个
		if( isActive ){
			if( tab.next().is("li") ){
				tab.next().click();
			}else if( tab.prev().is("li") ){
				tab.prev().click();
			}
		}
		this.move({action:'left'},this);//删除TAD前左移。
		tab.remove();// 删除Tab
		frame.remove();// 删除IFrame
		texts.remove();// 删除Tittle
		zhankais.remove();// 删除收缩按钮
	}
};

// 关闭所有
AbstractTab.closeAll = function(this1){
	var _this = this1;
	// 删除没有锁定的标签
	_this.tabs.find("li[lock!='true']").each( function(){
		jQuery(this).remove();
		var id = _this.getFrameID(this.id);
		var this_id=this.id;
		_this.frames.find("#" + id).remove();
		_this.texts.find("#"+this_id+"text").remove();
		_this.zhankais.find("#"+this_id+"click").remove();
	});
	var activeClass = this.activeClass;
	// 如果没有激活的锁定的标签，那么激活最后一个锁定的标签。
	if( _this.tabs.find("." + activeClass).size() == 0 ){
		_this.tabs.find("li:last").click();
	}
	_this.tabs.find("li").show();
};

// 锁定/解锁
AbstractTab.lock = function( id , lock ){
	var lockClass = this.lockClass;
	this.tabs.find("#" + id).attr("lock",new String(lock));
	var a = this.tabs.find("#" + id ).find("a");
	if( lock ){
		a.addClass(lockClass);
	}else{
		a.removeClass(lockClass);
	}
};

// 左移、右移动、重置
AbstractTab.move = function( option,this2 ){
	if( option.action == "left" ){
		this2.tabs.find("li:hidden:last").show();
	}else if(option.action == "right"){
		this2.tabs.find("li:visible:first").hide();
	}else if(option.action == "reset"){
		this2.tabs.find("li").show();
	}
};

// 初始化
AbstractTab.init = function(){
	var _this = this;
	this.tabs.parents().parents().find("#" + this.leftID ).click( function(){
		_this.move({action:'left'},_this);
	});
	this.tabs.parents().parents().find("#" + this.rightID ).click( function(){
		_this.move({action:'right'},_this);
	});
	this.tabs.parents().parents().find("#" + this.resetID ).click( function(){
		_this.move({action:'reset'},_this);
	});
	this.tabs.parents().parents().find("#" + this.closeID ).click( function(){
		_this.closeAll(_this);
	});
	return this;
};

// 定义类型  定义全局变量
window.FantasyTab = function( option ){
	var op = option || {};
	this.tabID = op.tabID || "Tabs";
	this.frameID = op.frameID || "Frames";
	this.textID=op.textID||"top_td_text";
	this.zhankaiID=op.textID||"shouqibutton";
	this.textParentID=op.textParentID||"top_Tabl";
	this.activeClass = op.activeClass || "on";
	this.lockClass = op.lockClass || "locked";
	this.leftID = op.leftID || "Left";
	this.rightID = op.rightID || "Right";
	this.resetID = op.resetID || "Reset";
	this.closeID = op.closeID || "Close";
	if(window.name=="rightFrameTop"){
		this.tabs = jQuery("#" + this.tabID);
		this.frames=jQuery(window.parent("rightFrame").document.getElementById(this.frameID));
		this.texts=jQuery("#" + this.textID);
		this.zhankais=jQuery("#" + this.zhankaiID);
		this.textsParent=jQuery("#" + this.textParentID);
	}
	else{
		this.tabs = jQuery(window.parent.parent("rightFrameTop").document.getElementById(this.tabID));
		this.frames = jQuery(window.parent.parent("rightFrame").document.getElementById(this.frameID));
		this.texts = jQuery(window.parent.parent("rightFrameTop").document.getElementById(this.textID));
		this.zhankais = jQuery(window.parent.parent("rightFrameTop").document.getElementById(this.zhankaiID));
		this.textsParent = jQuery(window.parent.parent("rightFrameTop").document.getElementById(this.textParentID));
	}
};
// 继承 AbstractTab;
jQuery.extend(FantasyTab.prototype,AbstractTab);

//传说中的静态方法.......
window.FantasyTab.create = function( option ){
	var tab = new FantasyTab(option);
	return tab.init();
};