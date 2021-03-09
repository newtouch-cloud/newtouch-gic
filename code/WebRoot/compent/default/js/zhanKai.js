var box1H=0;
var box2H=0;
var bodyH=0;
var click_dom=null;
var  ver = navigator.appVersion;   
var bType =navigator.appName;   
var  vNumber;
	 if(bType=="Microsoft Internet Explorer")
	 {
     vNumber=parseFloat(ver.substring(ver.indexOf("MSIE")+5,ver.lastIndexOf("Windows")));
	 }
function JQuery(element){
    return element = document.getElementById(element);
}
function JQueryHA(){
	var bodyD=JQuery('html');
	var D1=JQuery('class1content');
	var D2=JQuery('class2content');
	if(bodyD!=undefined){
		bodyH=bodyD.offsetHeight;
	}
	if(D1!=undefined){
	    box1H=D1.offsetHeight;
	}    
    box2H=bodyH-box1H-112;
    if(D2!=undefined){
    	D2.style.height=box2H+'px';
    }
    var frame_name=window.name;
    var thisid=frame_name.substring(5);
    var clickid=thisid+"click";
    if(vNumber>6.0){
    	click_dom=window.top.frames["mainFrame"].frames["rightFrameTop"].document.getElementById(clickid);
    	click_dom.onclick=function(){JQueryuse();};
    	click_dom.className=click_dom.className+"zhankai_click";
    	click_dom.style.display="block";
//    	alert(click_dom.className);
    }
    else{
    	click_dom=window.top.frames["mainFrame"].frames["rightFrameTop"].document.getElementById("stateBut");
    	click_dom.onclick=function(){JQueryuse();};
    	click_dom.style.display="block";
    }
//  frame框架下查找下层对象的方法  
//  alert(window.top.frames["mainFrame"].frames["rightFrameTop"].document.getElementById(clickid).innerHTML);
//	JQueryAA();
}    
function JQueryH(){
	var box1HT=0;
	var bodyD=JQuery('html');
	var D1=JQuery('class1content');
	var D2=JQuery('class2content');
	if(bodyD!=undefined){
		bodyH=bodyD.offsetHeight;
	}
    if(D1!=undefined){
        box1HT=D1.offsetHeight;
	}  

    if(box1HT==0){
    	box2H=bodyH-box1HT-112;
    }
    else{
    	box2H=bodyH-box1H-112;
    }
    if(D2!=undefined){
    	D2.style.height=box2H+'px';
    }
//	JQueryAA();
}    
function JQueryAA(){
	var aa="offsetheight = "+bodyH+"px\n"
	       +"offsetheight = "+box1H+"px\n"
	       +"offsetheight = "+box2H+"px\n";
	alert(aa); 
}
function JQueryD(){
	//JQueryAA();
    var d=JQuery('class1content');
    var d1=JQuery('class2content');
    var h=0;
    var h1=0;
    if(d!=undefined){h=d.offsetHeight;}
    if(d1!=undefined){h1=d1.offsetHeight;}
    var maxh=box1H;
    var minh1=box2H;
    function dmove(){
	    h+=20; //层展开速度
	    h1-=20;
	    if(h>=maxh){
		    d.style.height=box1H+'px';
		    if(d1!=undefined){
		    	d1.style.height=box2H+'px';
		    }
		    clearInterval(iIntervalId);
	    }else{
		    d.style.display='block';
		    if(d1!=undefined){
		    	d1.style.display='block';
		    }
		    d.style.height=h+'px';
		    if(d1!=undefined){
		    	d1.style.height=h1+'px';
		    }
	    }
    }
   
    iIntervalId=setInterval(dmove,2);
}

function JQueryD2(){
	//JQueryAA();
    var d=JQuery('class1content');
    var h=0;
    if(d!=undefined){h=d.offsetHeight;}
    var maxh=box1H;
    var d1=JQuery('class2content');
    var h1=0;
    if(d1!=undefined){h1=d1.offsetHeight;}
    var maxh1=box1H+box2H+10;
    function dmove(){
	    h-=20;//层收缩速度
	    h1+=20;
	    if(h<=0){
		    d.style.display='none';
		    if(d1!=undefined){
		    	d1.style.height=maxh1+'px';
		    }
		    clearInterval(iIntervalId);
	    }else{
	    	if(d1!=undefined){
	    		d1.style.display='block';
	    	}
		    d.style.height=h+'px';
		    if(d1!=undefined){
		    	d1.style.height=h1+'px';
		    }
	    }
    }
    
    iIntervalId=setInterval(dmove,1);
}

function JQueryuse(){
    var d=JQuery('class1content');
    var sb=$("#stateBut");
    if(d.style.display=='none'){
    	JQueryD();
    	if(sb.length>0){
    	    sb.html("收缩");
    	}
    	if($(click_dom).length>0){
    		$(click_dom).html("收缩");
    	}
    }else{
    	JQueryD2();
    	if(sb.length>0){
    	    sb.html("展开");
    	}
    	if($(click_dom).length>0){
    		$(click_dom).html("展开");
    	}
    }
}

window.onload=function(){JQueryHA();}