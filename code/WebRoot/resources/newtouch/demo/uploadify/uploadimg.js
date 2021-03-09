/*
 * 2014年4月22日
 * 影像预览功能弹出dialog对影像进行预览功能公共方法
 */

function showDialogImage(path,src){
	var image = new Image(); //定义图像对象
    image.src = src;  //对应被点击的影像
    var width=image.width;  //获取宽度
    var height=image.height; //获取高度
    var dialogWidth=""; //根据影像的宽度动态设置dialog宽度
    var dialogHeight=""; //根据影像的高度动态设置dialog高度
    
    if(width < 800){ //如果影像的宽度大于800则根据影像的宽度进行设置dialog的宽度
    	dialogWidth="dialogWidth:"+(width+50)+"px;";
    }else{ //否则使用定义的最大宽度
    	dialogWidth="dialogWidth:870px;";
    }
    
    if(height < 700){ //如果影像的高度大于700则根据影像的高度进行设置dialog的高度
    	dialogHeight="dialogHeight:"+(height+50)+"px;";
    }else{ //定义的最大高度700
    	dialogHeight="dialogHeight:700px;";
    }
    
    var obj = new Object();
	obj.src = src;  //传递参数
	
	var feature=dialogWidth+dialogHeight+"status:no;help:no;location=no;resizable:yes;Minimize=yes;Maximize=yes;"; 
    var value = window.showModelessDialog(path+"/SalesProtocol/showDialogImage.do",obj,feature); 
}