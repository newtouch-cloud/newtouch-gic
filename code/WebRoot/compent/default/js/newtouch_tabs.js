
jQuery.fn.extend({
	
	newtouchtabs:function(){
//		添加自有方法
		var tabs_array=new Array();
		var frame_array=new Array();
		tabs_array=$(this).find("li");
		frame_array=$(this).find("iframe");
		var h=$(this).height();
		var id=$(this).attr("id");
		$(tabs_array[0]).attr("val","1");
//		实现iframe跳转
		$(tabs_array).each(function(indext){
			$(this).click(function(){
				var link=$(this).find("a").attr("href");
				alert(link);
				if($(this).attr("val")==undefined){
					$(this).attr("val","1");
					$(link).attr("src",$(this).find("a").attr("val"));
				}
				for(var i=0;i<tabs_array.length;i++){
					$(tabs_array[i]).removeClass("open_tab");
					$(frame_array[i]).hide();
				}
				$(this).removeClass("tabOver");
				$(this).addClass("open_tab");
				$(link).show();
			});
//			标签鼠标移入移出样式
			$(this).mouseover(function(){
				if(!$(this).hasClass("open_tab")){
					$(this).addClass("tabOver");
				}
			});
			$(this).mouseout(function(){
				if(!$(this).hasClass("open_tab")){
					$(this).removeClass("tabOver");
				}
			});
		});
		frame_array.each(function(){
//			初始化Iframe大小
			$(this).height(h-27);
		});
		$(window).resize(function(){
//			窗口大小改变时，改变Iframe大小
			frame_array.each(function(){
				$(this).height(0);
			});
			var newh=$("#"+id).height();
			frame_array.each(function(){
				$(this).height(newh-27);
			});
		});
	},
	
//	添加多标签表格创建方法
    newtouch_table:function(option){
    	var tabs_array=new Array();
    	var div_array=new Array();
    	var button_array=new Array();
    	var table_array=new Array();
    	var $body = $(document.body);
    	var link="#"+$(this).find("div.tab_block[name='open']").attr("id");
//    	找到第一个显示的div
    	var html = $("<div class='masks_box' id='masksfull'></div>");
//    	定义遮罩层
    	html.appendTo($body);
    	$(window).scroll(function(){
    		var top=$(window).scrollTop();
			var left=$(window).scrollLeft();
			html.css({top:top,left:left});
    	});
//    	遮罩层随滚动条移动
    	var dialog_box=$("<div class='dialogbox'></div>");
    	var dialog=$("<div class='dialogbox_div'></div>");
    	var dialogtittle=$("<div class='dialogtittle'></div>");
    	var close=$("<div class='clsoe' title='关闭'></div>");
    	var dialogtool=$("<div class='dialogtool'></div>");
    	var baocunbutton=$("<button class='dialogbutton'><span class='baocun'></span><span>保存</span></button>");
    	var chexiaobutton=$("<button class='dialogbutton'><span class='chexiao'></span><span>撤销</span></button>");
    	dialogtittle.html("<span>"+$(this).find("li.open_table").find("span.text").html()+"新增"+"</span>");
    	close.appendTo(dialogtittle);
    	dialogtittle.appendTo(dialog_box);
    	dialog.appendTo(dialog_box);
    	baocunbutton.appendTo(dialogtool);
    	chexiaobutton.appendTo(dialogtool);
    	dialogtool.appendTo(dialog_box);
//    	初始化对话框
    	var table=$("<table class='dialog_table'></table>");
//    	初始化输入表格
    	html.click(function(){
    		dialog_box.remove();
    		$(this).hide();
    	});
//    	遮罩层的点击事件
    	close.mouseover(function(){
    		$(this).css({"background-position":"0 -13"});
    	});
    	close.mouseout(function(){
    		$(this).css({"background-position":"0 0"});
    	});
    	close.click(function(){
    		dialog_box.remove();
    		html.hide();
    		$(this).css({"background-position":"0 0"});
    	});
//    	关闭按钮事件
    	tabs_array=$(this).find("li");
    	div_array=$(this).find("div.tab_block");
    	button_array=$(this).find(".table_tool button");
    	table_array=$(this).find("table.tables_tab");

    	$(tabs_array).each(function(){
			$(this).click(function(){
				link=$(this).find("a").attr("href");
				if(link.lastIndexOf("/") > 0){
					link = link.substring(link.lastIndexOf("/")+1,link.length);
				}
				for(var i=0;i<tabs_array.length;i++){
					$(tabs_array[i]).removeClass("open_table");
					$(div_array[i]).hide();
				}
				dialogtittle.find("span").text($(this).find("span.text").html()+"新增");
				$(this).removeClass("over_table");
				$(this).addClass("open_table");
				$(link).show();
			});
//			标签鼠标移入移出样式
			$(this).mouseover(function(){
				if(!$(this).hasClass("open_table")){
					$(this).addClass("over_table");
				}
			});
			$(this).mouseout(function(){
				if(!$(this).hasClass("open_table")){
					$(this).removeClass("over_table");
				}
			});
		});
        $(button_array).each(function(){
        	$(this).mouseover(function(){
        		$(this).addClass("buttonOver");
        	});
        	$(this).mouseout(function(){
        		$(this).removeClass("buttonOver");
        	});
//        	添加行开始
        	if($(this).find("span").hasClass("add")){
        		$(this).click(function(){
//        			var str=$(this).attr("id").substring(4);
        			var thArray=$(link).find("th[class!='checkbox_td']");
        			table.empty();
        			dialog.empty();
        			thArray.each(function(){
        				var text=$(this).text();
        				var $this=$(this);
//        				var _this=$(this);
        				var tr=$("<tr name='input'></tr>");
        				var td=$("<td></td>");
        				var input=$("<td><input type='text'/></td>");
        				if($(this).attr("name")=="time"){
        					var time=$("<input class='Wdate' type='text'/>");
        					time.focus(function(){
        						WdatePicker({dateFmt:"yyyy-MM-dd"});
        					});
        					input=$("<td></td>");
        					time.appendTo(input);
        				}
        				if($(this).attr("name")=="nselect"){
        					
        					if($(this).find("div").length>0){
        						var _html=$($(this).find("div").html());
        						input=$("<td></td>");
        						$(_html.html()).appendTo(input);
        						var Thtml=$this.html().split("<");
        						text=Thtml[2].split(">")[1];
        					}
        					tr=$("<tr name='select'></tr>");
        				}
        				if($(this).attr("name")=="select"){
        					
        					if($(this).find("div").length>0){
        						var _html=$($(this).find("div").html());
        						input=$("<td></td>");
        						_html.appendTo(input);
        						var Thtml=$this.html().split("<");
        						text=Thtml[2].split(">")[1];
        					}
        					tr=$("<tr name='select'></tr>");
        				}
        				if($(this).attr("name")=="checkbox"){
        					input=$("<td><input type='checkbox' class='check'/></td>");
        					tr=$("<tr name='checkbox'></tr>");
        				}
        				
        				td.html(text+"：");
        				td.appendTo(tr);
        				input.appendTo(tr);
        				tr.appendTo(table);
        				table.appendTo(dialog);
//        				alert($(this).html());
        			});
//        			alert(table.html());
        			dialogtittle.width(0);
        			dialogtool.width(0);
        			var top=$(window).scrollTop();
        			var left=$(window).scrollLeft();
        			var thistop=$(this).offset().top+$(this).height();
        			var thisleft=$(this).offset().left;
        			html.css({top:top,left:left});
//        			遮罩层的弹出位置
        			html.show();
        			dialog_box.css({top:thistop,left:thisleft});
        			dialog_box.appendTo($body);
        			dialog_box.show();
        			close.mouseover(function(){
        	    		$(this).css({"background-position":"0 -13"});
        	    	});
        	    	close.mouseout(function(){
        	    		$(this).css({"background-position":"0 0"});
        	    	});
        	    	close.click(function(){
        	    		dialog_box.remove();
        	    		html.hide();
        	    		$(this).css({"background-position":"0 0"});
        	    	});
//        			alert(table.width());
        			table.show();
//        			var tw=table.width();
        			var dw=dialog.width();
        			dialogtittle.width(dw+2);
        			dialogtool.width(dw+2);
        			
        			baocunbutton.click(function(){
        				var input=table.find("tr");
        				var tr=$("<tr><td class='checkbox_td'><input type='checkbox'/></td></tr>");
        				input.each(function(){
        					var _this=$(this);
        					var text=$(this).find("input").val();
        					if($(this).attr("name")=="select"){
        						text=_this.find("select option:selected").text();
        					}
        					
        					if($(this).attr("name")=="checkbox"){
        						var chec=_this.find("input:checkbox");
        						if(chec.is(':checked')){
        							text="是";
        						}
        						else{
        							text="否";
        						}
        					}
        					td=$("<td>"+text+"</td>");
        					td.appendTo(tr);
        				});
        				tr.appendTo($(link).find("table.tables_tab"));
        				dialog_box.remove();
        				html.hide();
        				$(link).find("table.tables_tab").each(function(){
        		        	$(this).find("tbody tr:odd").addClass("odd");
        		        	$(this).find("tbody tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
        		        	var tr=$(this).find("tbody tr");
        		        	$(this).find("tbody tr").click(function(){
        		        		tr.removeClass("select");
        		        		$(this).addClass("select");
        		        	});
        		        });
//        				baocunbutton.blur();
        			});
        			chexiaobutton.click(function(){
        				var input=table.find("input");
        				input.each(function(){
        					$(this).val("");
        				});
        			});
        		});
        	}
//        	添加行结束
//        	删除行开始
        	if($(this).find("span").hasClass("delete")){
        		$(this).click(function(){
//        			alert("确定");
        			$(link).find("table.tables_tab tbody tr").each(function(){
        				thistr=$(this);
        				_this=$(this).find("td:first input:checkbox");
//        				alert(_this.is(':checked'));
        				if(_this.is(':checked')){
        					thistr.remove();
        				}
        			});
        			$(link).find("table.tables_tab").each(function(){
        				$(this).find("thead th:first input[type=checkbox]").attr("checked",false);
        				$(this).find("tbody tr").removeClass("odd").removeClass("select");
    		        	$(this).find("tbody tr:odd").addClass("odd");
    		        	$(this).find("tbody tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
    		        	var tr=$(this).find("tbody tr");
    		        	$(this).find("tbody tr").click(function(){
    		        		tr.removeClass("select");
    		        		$(this).addClass("select");
    		        	});
    		        });
        		});
        	}
//        	删除行结束
        });
        
        
//        初始化表格鼠标样式并添加checkbox全选方法
        $(table_array).each(function(){
        	
        	var _this=$(this).find("tbody");
//        	alert(_this.html());
        	$(this).find("thead th:first input[type=checkbox]").attr("checked",false);
        	$(this).find("tbody tr:odd").addClass("odd");
        	$(this).find("tbody tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
        	var tr=$(this).find("tbody tr");
        	$(this).find("tbody tr").click(function(){
        		tr.removeClass("select");
        		$(this).addClass("select");
        	});
        	$(this).find("thead th:first input[type=checkbox]").click(function(){
        		if($(this).is(':checked')){
//        			alert(_this.html());
        			_this.find("tr").each(function(){
        				$(this).find("td:first input[type=checkbox]").attr("checked",true);
        			});
        		}
        		else{
        			_this.find("tr").each(function(){
        				$(this).find("td:first input[type=checkbox]").attr("checked",false);
        			});
        		}
        	});
        	
//        	创建指针数组
        	var thArray=new Array();
        	thArray=$(this).find("thead th");
        	var fontArray=new Array(thArray.length-2);
        	$.each(thArray,function(key,val){
        		var font=$("<font class='resizeTH'></font>");
        		if(key!=0&&key<(thArray.length-1)){
        			font.prependTo($(thArray[key]));
        			left=$(thArray[key]);
        			right=$(thArray[key+1]);
        			fontArray[key-1]=new fontObj(font,left,right);
        			
        		}
        	});
//        	调用表格拖动算法
        	for(var i=0;i<fontArray.length;i++){
        		dragTable(fontArray[i],option);
        	}
        });
        
    }
    
});

//构建指针类
function fontObj(font,left,right){
	this.font=font;
	this.left=left;
	this.right=right;
};

//拖动表格方法
function dragTable(obj,op){
	var font=obj.font;
	var left=obj.left;
	left.tdW=0;
	var right=obj.right;
	var limitWidth=10;
	if(op>0){limitWidth=op;}
	var currentTd=null;
	font.bind('mousedown',function(event,ob){
		ob=ob||this;
		currentTd=ob;
		event=event||window.event;  
		ob.mouseDownX=event.clientX;  
		left.tdW=left.width();
		right.tdW=right.width();
        if(ob.setCapture){
        	ob.setCapture();
        }
        else if(window.captureEvents){    
            window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);      
        } 
	});
	font.bind('mousemove',function(event){
		if(!currentTd) return ; 
		ob=currentTd;
		event=event||window.event;  
		
        if(!ob.mouseDownX) return false;
        var newWidth=left.tdW*1+event.clientX*1-ob.mouseDownX;
        var newRightWidth=right.tdW*1-(event.clientX*1-ob.mouseDownX);
        if(newWidth>limitWidth){
        	if(newRightWidth>limitWidth){
        		left.width(newWidth);  
        		right.width(newRightWidth);
        	}
        	else{
        		right.width(limitWidth);
        		return false;
        	}
        }
        else{
        	left.width(limitWidth);
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
}

