jQuery.fn.extend({
	draghide : function(option) {
		var _this = $(this);//取到查询结果的DIV，dragID
		var off_top=$(this).offset().top;
		var isie6=$.browser.msie && ($.browser.version == "6.0") && !$.support.style;
		var browser=navigator.appName;
		var isIE=(browser=="Microsoft Internet Explorer" );
		var $body = $(document.body);
		var $body_H=$body.height();
    	var link = "#" + _this.attr("id");//取到查询结果的DIV，dragID
        //隐藏列事件_开始
    	var show_check=_this.find(".n_tools div[class=show_check]"); //找到单击的那个小框，格式默认是不显示
    	show_check.show(); // 显示单击的小框
    	var show_check_block=$("<div class='show_check_block'></div>");//外面大框
    	var show_check_tittle=$("<div class='show_check_tittle'><span>隐藏选中列</span></div>");//表头
    	var show_check_close=$("<div class='clsoe'></div>");//表头中关闭按钮
    	var show_check_box=$("<div class='show_check_box'></div>");//里面的框
    	var check_table=$("<table class='check_table'></table>");//字段表格
    	show_check_close.appendTo(show_check_tittle);//把关闭按钮加载到表头上
    	show_check_tittle.appendTo(show_check_block);//把表头加载到大框上
    	check_table.appendTo(show_check_box);//把表格加载到里面的小框
    	show_check_box.appendTo(show_check_block);//把小框加到大框里
    	show_check_block.appendTo($(link));//把隐藏表格相关内容添加到查询结果上
    	show_check.mouseover(function(){//鼠标指向样式
    		$(this).css({"background-position":"0 -22"});
    	});
    	show_check.mouseout(function(){//鼠标移出样式
    		$(this).css({"background-position":"0 0"});
    	});
    	var thAR=$(link).find("table.n_result th:visible");//找到表头
    	show_check.click(function(){//点击时把表头中的内容取出来，放到table中
    		if(!show_check_block.is(":hidden")){ //如果当前是显示的，则隐藏
    			show_check_block.hide();
    			return;
    		}
   			check_table.empty();
   			var thead=$(link).find("table.n_result thead");
   			thAR.each(function(int){
   				if($(thAR[int]).attr("abbr") != "rn" && $(thAR[int]).attr("abbr") != ""){
   					var $this = $(this);
   					var tr = $("<tr></tr>");
   					var td = $("<td><input type='checkbox' class='check'/></td>");
   					td.appendTo(tr);
   					var check = td.find("input[type=checkbox]");
   					if($this.is(":hidden")){ //设置是否勾选
   						if(isie6){
   							check.attr("defaultChecked",false);
   						} else {
   							check.attr("checked",false);
   						}
   					} else {
   						if(isie6){
   							check.attr("defaultChecked",true);
   						} else {
   							check.attr("checked",true);
   						}
   					}
   					//check.appendTo(td);
   					var text = $("<td>"+$(this).text()+"</td>");  //未用到
   					if($(this).attr("name") == "select" || $(this).attr("name") == "nselect"){
   						if($(this).find("div").length>0){
   							var Thtml=$this.html().split("<");
   							if($(this).find("font.resizeTH").length>0){
       							_text=Thtml[2].split(">")[1];
       						} else {
       							_text=Thtml[0];
       						}
   							text=$("<td>"+_text+"</td>");
   						}
   					}
   					text.appendTo(tr);
   					tr.appendTo(check_table);
   					check.click(function(){ //用于设置列的隐藏显示
   						if($(this).is(":checked")){
   							$(thAR[int]).show();
   							$(link).find("table.n_result tbody tr").each(function(){
   								$($(this).find("td")[int]).show();
   							});
   						} else {
   							$(thAR[int]).hide();
   							$(link).find("table.n_result tbody tr").each(function(){
   								$($(this).find("td")[int]).hide();
   							});
   						}
   					});
   				}
   			});
   			// 以下为控制显示框的宽度和位置
   			var tempLW = $(link).width();
   			show_check_block.show();
   			show_check_box.width(check_table.width()+22);
   			show_check_tittle.width(show_check_box.width());
   			if(isIE){
   				show_check_tittle.width(show_check_box.width()+2);
   			}
   			show_check_block.width(show_check_tittle.width()+2);
   			var sw = show_check_block.width();
   			var thistop = 30 + $(this).height();
   			var thisleft = tempLW-sw-2;
   			show_check_block.css({top:thistop,left:thisleft});
   			if(off_top > show_check_block.offset().top){
   				thistop = off_top + 30 + $(this).height();
   				show_check_block.css({top:thistop});
   			}
    	});
    	show_check_close.mouseover(function(){
    		$(this).css({"background-position":"0 -13"});
    	});
    	show_check_close.mouseout(function(){
    		$(this).css({"background-position":"0 0"});
    	});
    	show_check_close.click(function(){
    		show_check_block.hide();
    	});
    	//隐藏列事件结束
    	
    	//拖拽事件
		var tharray = $(link).find("table.n_result th:visible");
		var fontArray = new Array();
		tharray.each(function(int){
			var font = $("<font class='resizeTH'></font>");//给每个TH加上font标签，显示位置用
			if(int != tharray.length - 1 && $(thAR[int]).attr("abbr") != "rn" && $(thAR[int]).attr("abbr") != ""){
				font.prependTo($(tharray[int])); // 放到TH中
				$(tharray[int]).width($(tharray[int]).width()); // 设置TH的固定宽度(此处也可以在页面中写上宽度)
				var currentTd = null;
				var th = $(tharray[int]);
				var limitWidth = 10;
				font.bind('mousedown',function(event, ob){ // 给拖拽的内容设置事件
					ob = ob || this;
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
				font.bind('mousemove',function(event){ // 鼠标移动
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
				$(tharray[int]).width($(tharray[int]).width());
			}
		});
	}
});

