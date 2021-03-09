var newtouch=new Object;
newtouch.ajax=function(contextpath,paramJson,options){
	//var json=eval("("+paramJson+")");
	var param="";
	for(var name in paramJson){
		param=param+"&"+name+"="+paramJson[name];
	}
	var url=contextpath+"/ajaxplatform/ajax.controller?1=1"+param;
	url=encodeURI(encodeURI(url));
	$.ajax({
		type:"post",
		url:url,
		async: true,
		success:function(data,state){
			var rtData;
			try{
				rtData=eval("("+data+")");
			}catch(e){
				rtData=data;
			}
			options.success(rtData);
		},
		error:function(){
			alert("ajax异常");
		}
	});
};