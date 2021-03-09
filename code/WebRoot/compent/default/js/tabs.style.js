jQuery.fn.extend({
	table_style:function(){
		var _this=$(this);
		var tabWarp=_this.find(".tabWarp:eq(0)");
		var n_tools=_this.find(".n_tools:eq(0)");
		var rowPage=_this.find(".rowPage:eq(0)");
		var buttonArray=_this.find("button");
		var table=_this.find(".n_result");
		var windowH=$(window).height()-4;
		var off_top=_this.offset().top;
		var thisH=_this.height();
		var tableH=table.height();
		if((windowH-off_top)<thisH){
			thisH=windowH-off_top;
			tabWarp.height(thisH-n_tools.height()-32);
			_this.height(thisH);
		}
		$(window).resize(function() {
			windowH=$(window).height()-4;
			off_top=_this.offset().top;
			thisH=_this.height();
			thisHnew=tableH+n_tools.height()+32;
			if((windowH-off_top)<thisH){
				thisH=windowH-off_top;
				tabWarp.height(thisH-n_tools.height()-32);
				_this.height(thisH);
			}
			else{
				
				if((windowH-off_top)>thisHnew){
					tabWarp.height(tableH);
					_this.height(thisHnew);
				}
				else{
					var diffH=windowH-off_top-thisHnew;
					tabWarp.height(tableH+diffH);
					_this.height(thisHnew+diffH);
				}
			}
		});
		buttonArray.each(function(){
			$(this).mouseover(function(){
        		$(this).addClass("buttonOver");
        	});
        	$(this).mouseout(function(){
        		$(this).removeClass("buttonOver");
        	});
		});
		if(table.length>0){
			var tbody=table.find("tbody");
//			table.find("th").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
			tbody.each(function(){
				$(this).find("tr:odd").addClass("odd");
				$(this).find("tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
			});
		}
	}
});