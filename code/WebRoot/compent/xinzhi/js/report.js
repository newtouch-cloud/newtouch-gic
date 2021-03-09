//报表查询-页面加载时-只留报表类型，其余div去掉
  $(document).ready(  function(){
	  	    $("#ddd").hide();
			$("#ywbbcx").hide();
			$("#date").hide();
			$("#statistic").hide();
		    $("#jbqkb").hide();
		});

		//检查所选机构是否是二级或者三级 
			jQuery.validator.addMethod("checkBranchLvl",function(value,element){
				var flag = true;
				var branch_id=$("#branch_id").val();
				 if(!isUndefined(branch_id)){
					 $.ajax({
				     		url : "<%=basePath %>/SalesProtocol/checkBranchLvl.do",
				     		type : "post",
				     		async : false,
				     		dataType : "json",
				     		data : {"branch_id" : branch_id},
				     		success : function(data) {
				     			$.each(data,function(index,comment){
			     					var isSuccess =comment.isSuccess;
					     			if (isSuccess == 'true') {
					     				flag = true;
					     			}else{
					     				flag = false;
					     			} 
			     				});
				     		}
				     	});
					 if(flag){
						 return true;
					 }else{
						 return false;
					 }
				 }
				 return flag;
			},"请选择总公司或分公司或者营业部"); 
           
    $(document).ready(function() {
		    //报表绑定动态生成对应项的事件
		    $("#reportType").bind("change",showReportType);

		    $("#queryForm").validate({
				 rules:{
					 reportType:{
						 required:true
					 },
					 branch_name:{
	    				required:true,
	    				checkBranchLvl:[]
	    			},
			 		firstDate:{
    					required:true
    				 },
    				 secondDate:{
    					 required:true
    				 },
    				 firstDateJbb:{
    					 required:true
    				 },
    				 statistic_year:{
    					 required:true
    				 },
    				 statistic_month:{
    					 required:true
    				 }
//    				 solve_date:{
//    					required:true,
//    					 checkDateOrder : ["solve_date","validate_date"]
//    				 }
				 },
				 onkeyup:false
			 });
	     });
	   
	     //报表显示控制
		 function showReportType(){
			 var reportType = $("#reportType").val();//报表类型
			 if(reportType==""){
				    $("#ywbbcx").hide();
					$("#date").hide();
					$("#statistic").hide();
				    $("#jbqkb").hide();
				    $("#ddd").hide();
			 }else if(reportType=="YWBBCX"){//保险代理机构业务报表-产险
					$("#date").hide();
					$("#statistic").hide();
				    $("#jbqkb").hide();
				 
		    	    $("#ywbbcx").show();
		    	    $("#ddd").show();
		     }else if(reportType=="YWBBRSX"){//保险代理机构业务报表-人身险
		    	    $("#date").hide();
					$("#statistic").hide();
				    $("#jbqkb").hide();
		    	 
		    	    $("#ywbbcx").show();
		    	    $("#ddd").show();
		     }else if(reportType=="JBQKB"){//基本情况表
		        	$("#ywbbcx").hide();
					$("#date").hide();
					$("#statistic").hide();
					$("#ddd").hide();
		    	   
				    $("#jbqkb").show();
		     }else if(reportType=="RYDZDA"){//保险代理机构人员电子档案
		    	    $("#ywbbcx").hide();
					$("#date").hide();
					$("#statistic").hide();
				    $("#jbqkb").hide();

				    $("#ddd").show();
		     }else if(reportType=="XYDZDA"){//保险代理机构业务协议电子档案
		    	    $("#ywbbcx").hide();
					$("#date").hide();
					$("#statistic").hide();
				    $("#jbqkb").hide();

				    $("#ddd").show();
		     }else if(reportType=="QTFBB"){//客户群体分布表
		    	    $("#ywbbcx").hide();
					$("#date").hide();
				    $("#ddd").hide();
				    $("#statistic").hide();
				    
				    $("#jbqkb").show();
		     }else if(reportType=="XLQKBB"){//营运人员效力情况报表
		    	    $("#ywbbcx").hide();//统计月份
					$("#date").hide();//统计日期 从 到
					$("#statistic").hide();//统计年份 统计季度
				    $("#ddd").hide();//机构

				    $("#jbqkb").show();//统计月份
		     }else if(reportType=="YWHZB"){//保险代理机构业务汇总表
				    $("#ywbbcx").hide();//统计月份
					$("#date").hide();//统计日期 从 到
					$("#statistic").hide();//统计年份 统计季度
				    
				    $("#jbqkb").show();//统计月份
				    $("#ddd").show();
		     }
		 }
		 
	 

     //校验日期的先后顺序正确性
	 jQuery.validator.addMethod("checkDateOrder",function(value,element,param){
	 	var firstDate=$("#firstDate").val();
	 	var secondDate=$("#secondDate").val();
	 	if(!isUndefined(firstDate)&&!isUndefined(secondDate)){
	 		if(secondDate>firstDate){
	 			return false;
	 		}
	 		$("label:contains('日期顺序有误')").remove();
	 		return true;
	 	}
	 	return true;
	 },"日期顺序有误");
			  
	 function isUndefined(paraValue){
        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
        return false;
     }
