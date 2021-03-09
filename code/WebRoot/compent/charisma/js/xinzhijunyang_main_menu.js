jQuery.fn.extend({
	mainMenu : function(op) {
		var $this = $(this);
		var meinMenuB = op.MeinMenuB;
		var menuBlock = op.MenuBlock;
		var kinderTree = op.KinderTree;
		var mainpage = op.Mainpage;
		var mid=menuBlock.attr("id");
		var meinIfram = op.MeinIfram;
		var urlArray=$this.find(".hasurl");
		var liArray=$this.find("li");
		var zoom=true;
		mainpage.click(function(){
			var _this=$(this);
 			var thislink=_this.attr("name");
 			meinIfram.attr("src",thislink);
 			$this.css("bottom","0px");
 			zoom=false;
		});
		urlArray.each(function(){
			var _this=$(this);
			_this.click(function(){
				var thisurl=_this.attr("id");
//				alert(thisurl);
				meinIfram.attr("src",thisurl);
				menuBlock.hide();
				$this.css("bottom","-70px");
				zoom=true;
			});
		});
		meinMenuB.mouseover(function(){
			if(zoom){
				$this.stop(true,true);
				$this.show();
				$this.animate({bottom: '0px'}, "slow");
				//$this.css("bottom","0px");
			}
		});
		meinMenuB.mouseleave(function(){
			if(zoom){
				$this.stop(true,true);
				$this.animate({bottom: '-70px'}, "slow");	//实现几秒后滑动效果，可改变slow来改变时间
			}
		});
		meinMenuB.mouseleave();
		liArray.each(function(){
			var _this=$(this);
			_this.mouseover(function(){
				_this.css("background-color","#5c5655");
			});
			_this.mouseleave(function(){
				_this.css("background-color","transparent");
			});
		});

		meinMenuB.click(function() {
			if (menuBlock.is(":hidden")) {
//				menuBlock.show().animate({width:'124px'}, 1000);
				menuBlock.show();
				var ktreeL = kinderTree.children("li").length;
				var ktreeH = $(kinderTree.children("li")[0]).height();
				var MenuH=ktreeL*ktreeH;
				
				menuBlock.css("bottom",MenuH+70+"px");
				zoom=false;
			} else {
//				menuBlock.animate({width:'0px'}, 1000).hide();
				menuBlock.hide();
				zoom=true;//点击后可以再次隐藏
			}
		});
		var meinMenuBack=meinMenuB.parent(".bottomMenuLink");
		meinMenuB.mouseover(function(){
			meinMenuBack.css('background-position','0px -90px');
		});
		meinMenuB.mouseleave(function(){
			meinMenuBack.css('background-position','0px 0px');
		});

		// alert(treeNode.length);
		// document.onclick = close(ddmenuitem);
	},
	mainTree : function() {
		var Tree = $(this);
		var timeout = 500;
		var closetimer = 0;
		var ddmenuitem = 0;

		var treeNode = Tree.children("li");
		var treeNodeUL = Tree.children("li").children("ul");
		treeNode.each(function() {
			var $this = $(this);
			var thisA = $this.children("a");
			var thisUl = $this.children("ul");
			var thisLi = thisUl.children("li");
			var thisLiL = thisLi.length;
			var thisLiH = $(thisLi[0]).height();
			var thisH = thisLiL*thisLiH;
//			var thisTop = $this[0].style.top
			var thisTop = 0;
			var thisscrlloT=0;
			var WH = 0;
			var restH = WH-thisTop-70;
			
			// $this.click(function() {
			// if (thisUl.is(":hidden")) {
			// treeNodeUL.hide();
			// thisUl.show();
			// } else {
			// thisUl.hide();
			// }
			// });
//			$this.click(function(){
//				alert(thisTop+" ?= "+restH+"?="+thisscrlloT );
//			});
			$this.mouseover(function() {
				WH = $(window).height();
				thisTop=$this.offset().top;
				thisscrlloT=$(window).scrollTop();
				restH = WH-thisTop-71;
//				var ulTop = thisTop-restH+thisH;
				if(restH+thisscrlloT>thisH){
//					thisUl.offset({top:thisTop});
					thisUl.css("top",0+"px");
				}
				else{
//					thisUl.offset({top:ulTop});
					thisUl.css("top",restH-thisH+thisscrlloT+"px");
				}
				ddmenuitem = jsddm_open(thisUl, closetimer, ddmenuitem);
				// alert(ddmenuitem);
			});
			$this.mouseleave(function() {
				closetimer = jsddm_timer(ddmenuitem, closetimer, timeout);
			});
		});
	},
	close : function() {
		var _this = $(this);
		_this.css('visibility', 'hidden');
	},
	frameWH : function(frameID){
		var $this=$(this);
		var Windows=$(window);
		var WH=document.body.offsetHeight;
		var Iframe=$(window.parent.document).find(frameID);
		Iframe.height(WH);
	},
	spanShow : function(spanArray){
		var SpanArray=spanArray;
		spanArray.each(function(){
			var sThis=$(this);
			if(sThis.text()==""){
				sThis.css("visibility","hidden");
			}
			else{
				sThis.css("visibility","visible");
			}
		});
	}
});

function jsddm_open(obj, closetimer, ddmenuitem) {

	closetimer = jsddm_canceltimer(closetimer);
	if (ddmenuitem) {
		ddmenuitem.close();
	}
	// alert("over");
	ddmenuitem = obj.css('visibility', 'visible');
	return ddmenuitem;
};

// function close(obj) {
// if (obj) {
// obj.css('visibility', 'hidden');
// }
// };

function jsddm_canceltimer(obj) {
	if (obj) {
		window.clearTimeout(obj);
		obj = null;
	}
	return obj;
};

function jsddm_timer(obj, closetimer, timeout) {
	// var close=close(obj);
	var self = null;
	if (obj) {
		self = obj;
	}
	closetimer = window.setTimeout(function() {
		self.close();
	}, timeout);
	return closetimer;
};