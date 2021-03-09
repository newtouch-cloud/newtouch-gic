jQuery.fn.extend({
	frameWH : function(op){
		var frameID=op.FrameId;
		var frameW=op.FrameW;
		var $this=$(this);
		var Windows=$(window);
		var WH=document.body.offsetHeight+2;
		if($(window.parent.document).has(frameID)){
			var Iframe=$(window.parent.document).find(frameID);
			Iframe.height(WH);
			Iframe.width(frameW);
			Iframe.parent().width(frameW);
		}
//		alert(Iframe.parent().length);
	},
	
	UpDown : function(ob){
		var $this=$(this);
		var frameId=ob.FrameId;
		var obj=ob.Obj;
		var Iframe=null;
		var IframeH=0;
		var objH=0;
		var obcange=true;
		if($(window.parent.document).has(frameId)){
			Iframe=$(window.parent.document).find(frameId);
			IframeH=document.body.offsetHeight;
		}
//		var thisTop=obj.offset().top-46;
//		skt.css("top",thisTop);
		if(obj.is(":hidden")){
			$this.css('background-position','-18px 0px');
		}
		else{
			$this.css('background-position','0px 0px');
		}
		$this.mouseover(function(){
			var pos=$this.css('background-position').split(" ")[0]+" -23px";
			$this.css('background-position',pos);
//			obcange=true;
		});
		$this.mouseleave(function(){
			if(obcange){
				if(obj.is(":hidden")){
					$this.css('background-position','-18px 0px');
				}
				else{
					$this.css('background-position','0px 0px');
				}
			}
		});
		$this.mousedown(function(){
			var pos=$this.css('background-position').split(" ")[0]+" -46px";
			$this.css('background-position',pos);
			obcange=false;
		});
		$this.mouseup(function(){
			if(obj.is(":hidden")){
//				$this.css('background-position','0px -23px');
			}
			else{
//				$this.css('background-position','-18px -23px');
			}
		});
		$this.click(function(){
//			var pos=$this.css('background-position').split(" ")[0]+" -46px";
//			$this.css('background-position',pos);
			if(obj.is(":hidden")){
				obj.slideDown(400,function(){
					$this.css('background-position','0px 0px');
					obcange=true;
				});
				if(Iframe!=null){
					var temH=document.body.offsetHeight;
					IframeTempH=temH+objH;
					
					Iframe.height(IframeTempH);
				}
			}
			else{
				objH=obj.height();
				obj.slideUp(400,function(){
					$this.css('background-position','-18px 0px');
					obcange=true;
				});
				if(Iframe!=null){
					temH=document.body.offsetHeight;
					IframeTempH=temH-objH;
					Iframe.height(IframeTempH);
				}
			}
		});
	},
	
	FiexscrollLeft : function(){
		var $this=$(this);
		var Fiextd=null;
		Fiextd=$this.find(".FixedTd");
		$this.scroll(function(){
			if(Fiextd.length!=0){
				Fiextd.css("left",$this.scrollLeft()+"px");
				Fiextd.each(function(){
					$(this).css("left",$this.scrollLeft()+"px");
				});
			}
		});
	},
	
	gobackTop : function(){
		var $this = $(this);
		var parentW = $(window.parent.document.documentElement);
		$this.click(function(){
//			alert(parentW.scrollTop()+"=?"+window.parent.document.documentElement.scrollTop );
			parentW.scrollTop(0);
		});
	},
	
	gobackTopOn : function(){
		var $this = $(this);
		var parentW = $(window.parent.document.documentElement);
		//$this.click(function(){
//			alert(parentW.scrollTop()+"=?"+window.parent.document.documentElement.scrollTop );
			parentW.scrollTop(0);
		//});
	}
	
});
