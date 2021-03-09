  $(function() {
     	    //dialog 信息
     	    $("#dialog").dialog({
     	        autoOpen:false,
     	        buttons:[{
     	            text:"关闭",
     	            click:function(){
     	                	$("#dialog").dialog("close");
     	                }
     	            }
     	            ],
     	            position:"top",//弹出位置
     	            modal:true,
     	            width:400, //窗口宽度
    	            height:330,
     	            dialogClass: "my-dialog",
     	            closeText:false,
     	            drag:function(){
     	                
     	            }

     	        });
     		 var result_flag=$("#result_flag").val();
     		 var msg=$("#msg").val();
     		 var path=$("#path").val();
     		 var src_cg=path+"/compent/charisma/img/checkmark.png";
     		 var src_sb=path+"/compent/charisma/img/cross.png";
     		 if(msg!=""){
     			if(result_flag=="true"){
         	    	$("#dialog_img").attr("src",src_cg);
         	    	 $("#dialog").dialog("open");
         	     }else if(result_flag=="false"){
         	    	$("#dialog_img").attr("src",src_sb);
         	    	$("#dialog").dialog("open");
         	    	// $("#dialog_false").dialog("open");
         	     }
     		 }
     	     
     	     
     	     
     	});
  //Ajax方式提交提示笑脸
  function dialogAjax(result_flag,msg,path,toPath) {
	    //dialog 信息
	    $("#dialog").dialog({
	        autoOpen:false,
	        buttons:[{
	            text:"关闭",
	            click:function(){
	                	$("#dialog").dialog("close");
	                	if(toPath==null){
	                		$("#backBtn").gobackTopOn();//回到顶端
	                	}else if(result_flag=="false"){
	                		$("#backBtn").gobackTopOn();//回到顶端
	                	}
	                	else if(result_flag=='true' && toPath != null){
	                		location.href = path+toPath;  //跳转到指定的页面
	                	}
	                }
	            }
	            ],
	            position:"top",//弹出位置
	            modal:true,
	            width:345, //窗口宽度
	            height:280,
	            dialogClass: "my-dialog",
	            closeText:false,
	            drag:function(){
	            }
	        });
		 var src_cg=path+"/compent/charisma/img/checkmark.png";
		 var src_sb=path+"/compent/charisma/img/cross.png";
		 $("#dialog_msg").html(msg);
		 if(msg!=""){
			if(result_flag=="true"){
    	    	$("#dialog_img").attr("src",src_cg);
    	    	 $("#dialog").dialog("open");
    	     }else if(result_flag=="false"){
    	    	$("#dialog_img").attr("src",src_sb);
    	    	$("#dialog").dialog("open");
    	    	// $("#dialog_false").dialog("open");
    	     }
		 }
	}
  //导入数据时，提示笑脸
  function dialogForUpload(result_flag,msg,path,toPath) {
	    //dialog 信息
	    $("#dialog").dialog({
	        autoOpen:false,
	        buttons:[{
	   	            text:"确定",
	   	            click:function(){
	   	            		location.href = path+toPath;
	   	                }
   	            },
   				{
   					text: "返回",
   					click:function(){
   						location.reload();
   	                }
   				}
   	            ],
	            position:"top",//弹出位置
	            modal:true,
	            width:345, //窗口宽度
	            height:280,
	            dialogClass: "my-dialog",
	            closeText:true,
	            drag:function(){
	            }
	        });
		 var src_cg=path+"/compent/charisma/img/checkmark.png";
		 var src_sb=path+"/compent/charisma/img/cross.png";
		 $("#dialog_msg").html(msg);
		 if(msg!=""){
			if(result_flag=="true"){
  	    	$("#dialog_img").attr("src",src_cg);
  	    	 $("#dialog").dialog("open");
  	     }else if(result_flag=="false"){
  	    	$("#dialog_img").attr("src",src_sb);
  	    	$("#dialog").dialog("open");
  	     }
		 }
	}
  
  //人员导入，提示框
  function dialogForSalesUpload(result_flag,msg,path,toPath) {
	    //dialog 信息
	    $("#dialog").dialog({
	        autoOpen:false,
	        buttons:[{
	   	            text:"确定",
	   	            click:function(){
	   	            		location.href = path+toPath;
	   	                }
 	            }],
	            position:"top",//弹出位置
	            modal:true,
	            width:345, //窗口宽度
	            height:280,
	            dialogClass: "my-dialog",
	            closeText:true,
	            drag:function(){
	            }
	        });
		 var src_cg=path+"/compent/charisma/img/checkmark.png";
		 var src_sb=path+"/compent/charisma/img/cross.png";
		 $("#dialog_msg").html(msg);
		 if(msg!=""){
			if(result_flag=="true"){
	    	$("#dialog_img").attr("src",src_cg);
	    	 $("#dialog").dialog("open");
	     }else if(result_flag=="false"){
	    	$("#dialog_img").attr("src",src_sb);
	    	$("#dialog").dialog("open");
	     }
		 }
	}
  
  
//导入数据时，只关闭
  function dialogForNormal(result_flag,msg,path,toPath) {
	    //dialog 信息
	    $("#dialog").dialog({
	        autoOpen:false,
	        buttons:[
   				{
   					text: "确定",
   					click:function(){
   						location.reload();
   	                }
   				}
   	            ],
	            position:"top",//弹出位置
	            modal:true,
	            width:345, //窗口宽度
	            height:280,
	            dialogClass: "my-dialog",
	            closeText:true,
	            drag:function(){
	            }
	        });
		 var src_cg=path+"/compent/charisma/img/checkmark.png";
		 var src_sb=path+"/compent/charisma/img/cross.png";
		 $("#dialog_msg").html(msg);
		 if(msg!=""){
			if(result_flag=="true"){
  	    	$("#dialog_img").attr("src",src_cg);
  	    	 $("#dialog").dialog("open");
  	     }else if(result_flag=="false"){
  	    	$("#dialog_img").attr("src",src_sb);
  	    	$("#dialog").dialog("open");
  	     }
		 }
	}