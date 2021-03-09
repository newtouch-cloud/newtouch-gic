<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.ca.cacore.mass.webapp.webtag.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>新致金保通</title>
<jsp:include page="/WEB-INF/jsp/newtouch/core/pub/jvbasecss.jsp"
	flush="true" />
	
	<jsp:include page="/WEB-INF/jsp/newtouch/core/pub/jvbasejs.jsp" flush="true" />
	<!-- 回跳 -->
	<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true" />

<script type="text/javascript">
	function downloadPic(){
		//var person_no = document.getElementById("person_no").value;
		var url = document.getElementById("upload_approval_file").value;
		if(url == null || url == ""){
			alert("文件不存在,请先上传");
			return;
		}
		window.location.href="<%=basePath %>/Person/PersonManage/downloadPic.do?url="+url;
		<%-- $.ajax({
		 	 type: "GET",
			  url: "<%=basePath%>/Person/PersonManage/downloadPic.do",
			  data: {
				  "url": url
			  },
			  dataType:"text",
			  success: function(data){
				 alert(data); 
			 },
		 	 error: function(){
			 	 alert("下载失败");
		 	 }
			}); --%>
	}
	jQuery.validator.addMethod("getBirthday",function(value,element){
     	var now=new Date();
     	var str=changeDateToString(now);
     	if(!isUndefined(value)){
     		if(value>str){
     			return false;
     		}
     	}
     	return true;
     },"出生日期大于当前日期，请检查。");


	function getCities(){
		var province_code=$("#province").val();
		$("#city").empty();
		$("#city").append("<option value=''>---请选择---</option>");
		$.ajax({
			type:"post",
			async:false,
			url:"<%=basePath %>/SalesEnter/getCities.do",
			data:{"province_code":province_code},
			dataType:"json",
			success:function(data){
				$.each(data,function(index,comment){
					var city_name=comment.name;
					var city_id=comment.id;
					if(city_id==$("#city_hidden").val()){
						$("#city").append("<option value='"+city_id+"' selected='selected'>"+city_name+"</option>");
					}else{
						$("#city").append("<option value='"+city_id+"'>"+city_name+"</option>");
					}
				});
			}
		});
	}
	$(function() {
		//通过省的值动态改变市的备选值
		$("#province").change(function(){
			getCities();
		});
		$("#team_id").blur(function(){
			getPostRankSer();
		});
		//通过职位代码动态改变职级下拉选的备选值
		$("#post_code").change(function() {
			getPostRankSer();
		});
		//通过职级代码动态改变职位和职级序列的值
		$("#rank_id").change(function(){
			var rank_id=$("#rank_id").val();
			var basic_version_id=$("A").val();
			if(!isUndefined(rank_id)){
				$.ajax({
					type:"post",
					async:false,
					url:"<%=basePath %>/SalesEnter/rank2post2rankSeries.do",
					data:{"rank_id":rank_id,"basic_version_id":'A'},
					success:function(data){
						var str = data.substring(1, data.lastIndexOf('}'));
						var post_code=str.split(',')[0].split(':')[1];
						var rankSeries_code=str.split(',')[1].split(':')[1];
						$("#post_code").val(post_code);
						$("#rankSeries_code").val(rankSeries_code);
						$("#rankSeries_code_hidden").val(rankSeries_code);
					}
				});
			}
		});
	 });
	 //通过职位代码动态改变职级下拉选的备选值
	 function getPostRankSer(){
		var post_code=$("#post_code").val();
		var basic_version_id=$("A").val();
		var team_id=$("#team_id").val();
		if(!isUndefined(post_code)||!isUndefined(team_id)){
			$("#rank_id").empty();
			$("#rank_id").append("<option value=''>---请选择---</option>");
			$.ajax({
				type:"post",
				async:false,
				url:"<%=basePath %>/SalesEnter/post2rank.do",
				data:{"post_code":post_code,"basic_version_id":'A'},
				dataType:"json",
				success:function(data){
					$.each(data,function(index,comment){
						var rank_name=comment.name;
						var rank_id=comment.id;
						if(rank_id==$("#rank_id_hidden").val()){
							$("#rank_id").append("<option value='"+rank_id+"' selected='selected'>"+rank_name+"</option>");
						}else{
							$("#rank_id").append("<option value='"+rank_id+"'>"+rank_name+"</option>");
							$("#rankSeries_code").val("");
							$("#rankSeries_code_hidden").val("");
						}
					});
				}
			});
		}
	 }
	 jQuery.validator.addMethod("checkIdCard", function(value, element,param) {
	    	//如果证件类型为身份证（value=11）那么验证身份证号的正确性
	    	
	    	var idcard = $("#idcard").val();
	     	if(isUndefined(idcard)){
	     		return true;
	     	}
	     	var regex1 = /^[1-9][0-7]\d{4}((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))\d{3}(\d|X|x)?$/;
	     	var regex2;
	     	/*身份号码位数及格式检验*/
	     	switch (idcard.length) {
	     	  case 15:
	     		if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
	     			regex2= /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
	     		} else {
	     			regex2 = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
	     		}
	     		if(regex2.test(idcard)){
	     			$("#birthday").val(getBirthdayFromCnId(idcard));
	     			$("#sex").val(getGenderFromCnId(idcard)=="M"?1:2);
	     			return true;
	     		}
	     		else{
	     			$("#birthday").val("");
	     			return false;
	     		}
	     		break; 
	     	  case 18:
	     	 	 if(regex1.test(idcard)){
	     			var S = (parseInt(idcard[0]) + parseInt(idcard[10])) * 7 + (parseInt(idcard[1]) + parseInt(idcard[11])) * 9 + (parseInt(idcard[2]) + parseInt(idcard[12])) * 10 + (parseInt(idcard[3]) + parseInt(idcard[13])) * 5 + (parseInt(idcard[4]) + parseInt(idcard[14])) * 8 + (parseInt(idcard[5]) + parseInt(idcard[15])) * 4 + (parseInt(idcard[6]) + parseInt(idcard[16])) * 2 + parseInt(idcard[7]) * 1 + parseInt(idcard[8]) * 6 + parseInt(idcard[9]) * 3;
	     			var Y = S % 11;
	     			var M = "F";
	     			var JYM = "10X98765432";
	     			M = JYM.substr(Y, 1);
	     			/*判断校验位*/
	     			if (M == idcard[17].toUpperCase()) {
	     				$("#birthday").val(getBirthdayFromCnId(idcard));
	     				$("#sex").val(getGenderFromCnId(idcard)=="M"?1:2);
	     				$("label:contains('请输入合法的身份证')").remove();
	     				return true;
	     			} else {
	     				$("#birthday").val("");
	     				return false;
	     			}
	     		}else{
	     			$("#birthday").val("");
	     			return false;
	     		}
	     		break;
	     	  default:
	     		 $("#birthday").val("");
	     		 return false;
	     	}
	     }, jQuery.validator.format("请输入合法的身份证"));
     // 异步请求组织名称，机构名称，机构代码信息
     function getTeamNameBranchInfo(value){
    	 var flag=true;
    	 $.ajax({
	     		url : "<%=basePath %>/Person/PersonManage/queryTeamNameBrachName.do",
	     		type : "post",
	     		async : false,
	     		data : {
	     			"team_id" : value
	     		},
	     		success : function(data) {
	     			var obj=eval("["+data+"]");
	     			if (obj[0].isSuccess == 'true') {
	     				var team_name = obj[0].team_name;
	     				var branch_id = obj[0].branch_id;
	     				var branch_name = obj[0].branch_name;
	     				var basic_version_id=obj[0].basic_version_id;
	     				$("#team_name").val(team_name);
	     				$("#branch_id").val(branch_id);
	     				$("#branch_name").val(branch_name);
	     				$("#basic_version_id").val(basic_version_id);
	     				$("#flag").val("true");
	     			} else {
	     				// 清空不可输入框的数据
	     				$("#team_name").val("");
	     				$("#branch_id").val("");
	     				$("#branch_name").val("");
	     				$("#basic_version_id").val("");
	     				$("#flag").val("false");
	     			}
	     		}
	     	});
     }
     //检查是否存在
     jQuery.validator.addMethod("getTeamNameBranchInfo", function(value, element) {
    	getTeamNameBranchInfo(value);
     	if ($("#flag").val() == "false") {
     		return false;
     	} else {
     		return true;
     	}
     }, "输入的团队代码不存在或该团队状态已经无效 ");
     //异步请求推荐人姓名
     jQuery.validator.addMethod("getRecommendName",function(value,element){
    	if(isUndefined(value)||value=='0000000'){
    		$("#recommend_name").val("");
				$("#branch_id_recommend").val("");
    		return true;
    	}
    	var flag=true;
     	$.ajax({
     		url:"<%=basePath %>/SalesEnter/queryRecommenderName.do",
     		type:"post",
     		async: false,
     		data:{"recommend_id":value},
     		success:function(data){
     			var str=data.substring(1,data.lastIndexOf('}'));
     			var isSuccess=str.split(',')[0].split(':')[1];
     			if(isSuccess=="true"){
     				var recommend_name=str.split(',')[1].split(':')[1];
     				var branch_id_recommend=str.split(',')[2].split(':')[1];
     				$("#recommend_name").val(recommend_name);
     				$("#branch_id_recommend").val(branch_id_recommend);
     				flag=true;
     			}else if(isSuccess=="false"){
     				//清空不可输入框中的值
     				$("#recommend_name").val("");
     				$("#branch_id_recommend").val("");
     				flag=false;
     			}
     		}
     	});
     	return flag;
     },"录入的推荐人代码不存在或非在职，请重新录入。");
	
     //判断开始考核日期
     jQuery.validator.addMethod("getAssessStartDate",function(value,element){
     	if(!isUndefined(value)){
     		var date=changeStringToDate(value);
	     	var day=0;
	     	day=date.getDate();
     		var now=new Date();
     		if(date.getFullYear()==now.getFullYear()){
     			if(date.getMonth() < now.getMonth()-1||date.getMonth() > now.getMonth()){
	     			return false;
	     		}else{
		     		if(day>15){
		         		date.setUTCMonth(date.getMonth()+1, 0);
		         	}else{
		         		date.setUTCFullYear(date.getFullYear(),date.getMonth(),0)
		         	}
	         		$("#assess_start_date").val(changeDateToString(date));
	     			return true;
	     		}
     		}
     		if(date.getFullYear()>now.getFullYear()){
     			return false;
     		}
     		if(date.getFullYear()<now.getFullYear()){
     			if(date.getMonth()==11){
     				if(day>15){
     					date.setUTCFullYear(date.getFullYear()+1,0,0)
		         	}else{
		         		date.setUTCFullYear(date.getFullYear(),date.getMonth(),0)
		         	}
	         		$("#assess_start_date").val(changeDateToString(date));
	     			return true;
	     		}else{
		     		return false;
	     		}
     		}
     	}else{
     		return false;
     	}
     },"只能输入当月或上一月");
     //判断证件信息是否唯一（展业证和资格证）
     jQuery.validator.addMethod("checkCredentialsUnique",function(value,element,param){
    	var flag=true;
     	if(!isUndefined(value)){
     		$.ajax({
     			url:"<%=basePath %>/SalesEnter/checkCredentialsUnique.do",
     			type:"post",
     			async: false,
     			data:{"credentials_type":param,"credentials_id":value},
     			success:function(data){
     				var str=data.substring(1,data.lastIndexOf('}'));
     				var isSuccess=str.split(',')[0].split(':')[1];
     				if(isSuccess=="true"){
     					flag=true;
     				}else{
     					flag=false;
     				}
     			}
     		});
      		return flag;
      	}else{
      		return true;
      	}
     	
     },"输入的资格证号码在系统中已存在");

     //校验日期的先后顺序正确性
     jQuery.validator.addMethod("checkDateOrder",function(value,element,param){
     	var give_date=$("#"+param[0]).val();
     	var valid_date=$("#"+param[1]).val();
     	if(!isUndefined(give_date)&&!isUndefined(valid_date)){
     		if(give_date>=valid_date){
     			return false;
     		}
     	}
     	$("label:contains('时间顺序有误')").remove();
     	return true;
     },"时间顺序有误");

     //检查展业证号码输入了，日期可以不输入，如果日期输入了，展业证号码必须输入
     jQuery.validator.addMethod("checkCredentials",function(value,element){
     	if(!isUndefined($("#exh_give_date").val())||!isUndefined($("#exh_valid_date").val())){
     		if(isUndefined($("#exhibitionCard_id").val())){
     			return false;
     		}
     		return true;
     	}
     	return true;
     },"请输入展业证代码,若不输入请保证展业证的发证日期和有效截止日期为空。");

     /*
      *判断参数是否为未定义或null,如未定义或为null返回true
      *@param paraValue 待处理字符串
      *@return true或者false
      */
     function isUndefined(paraValue){
        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
        return false;
     }

     //将日期类型转换成字符串型格式yyyy-MM-dd
     function changeDateToString(date){ 
     	var Year=0; 
     	var Month=0; 
     	var Day=0;
        var CurrentDate="";
         //初始化时间 
         Year = date.getFullYear(); 
         Month = date.getMonth()+1; 
         Day = date.getDate();
         CurrentDate = Year + "-"; 
         if (Month >= 10 ) { 
         	CurrentDate = CurrentDate + Month + "-"; 
         } else { 
         	CurrentDate = CurrentDate + "0" + Month + "-"; 
         } 
         if (Day >= 10 ) { 
         	CurrentDate = CurrentDate + Day ; 
         } else { 
         	CurrentDate = CurrentDate + "0" + Day ; 
         }
         return CurrentDate;
     }

     //将字符串型格式yyyy-MM-dd转换成日期类型
     function changeStringToDate(date){
     	return new Date(Date.parse(date.replace(/-/g,"/")));
     }
     //遮盖的方法
     function blockUI(){
		$.blockUI({
			message: '<image src="<%=basePath %>/compent/charisma/img/loadingpage.gif">请等待...</image>', 
			css: {
				border:'none',                   // 无边界
				width:"150px",                     // 中间框框的宽度
				top:"10%",                        // 高居中
				left:"45%",                      // 左居中        
				padding: '15px',
				opacity: .9    
			}
		});
	 }
     
     $(document).ready(function() {
		getCities();
		//如果组织代码不为空，那么加载时就加载好职级的下拉选
		var team_id=$("#team_id").val();
		if(team_id!=""&&team_id!=null){
		 	getPostRankSer();
		}
		var validator = $("#form").validate({
			rules : {
				sales_name : {
					required : true,
					maxlength : 50
				},
				sex : {
					required : true
				},
				birthday : {
					required : true,
					getBirthday:[]
				},
				certi_type : {
					required : true,
					checkIdCard:[],
					checkIsResigned : []
				},
				idcard : {
					required : true,
					maxlength : 50,
					checkIdCard:[],
					checkIsResigned : []
				},
				nation : {
					required : true
				},
				province : {
					required : true
				},
				city : {
					required : true
				},
				domicile : {
					required : true,
					maxlength : 200
				},
				political : {
					required : true
				},
				marital_stat : {
					required : true
				},
				education : {
					required : true
				},
				contract_type : {
					required : true
				},
				team_id : {
					required : true,
					maxlength : 12,
					getTeamNameBranchInfo : []
				},
				recommend_id : {
					maxlength : 15,
					getRecommendName : []
				},
				post_code : {
					required : true
				},
				rank_id : {
					required : true
				},
				probation_date : {
					required : true,
					getAssessStartDate : []
				},
				technology_no : {
					required : true,
					maxlength : 20,
					minlength : 20,
					isNum : [],
					checkCredentialsUnique : "1"
				},
				cer_give_date : {
					checkDateOrder : [ "cer_give_date", "cer_valid_date" ]
				},
				cer_valid_date : {
					checkDateOrder : [ "cer_give_date", "cer_valid_date" ]
				},
				exhibitionCard_id : {
					maxlength : 50,
					checkCredentials : [],
					checkCredentialsUnique : "2"
				},
				exh_give_date : {
					checkDateOrder : [ "exh_give_date", "exh_valid_date" ]
				},
				exh_valid_date : {
					checkDateOrder : [ "exh_give_date", "exh_valid_date" ]
				},
				home_address : {
					required : true,
					maxlength : 200
				},
				home_zipcode : {
					required : true,
					maxlength : 6,
					checkPost : []
				},
				mobile : {
					required : true,
					maxlength : 40,
					mobilePhone : []
				},
				fixed_line : {
					maxlength : 30,
					telephone : []
				},
				email : {
					maxlength : 255,
					email : true
				},
				graduate_school : {
					maxlength : 100
				},
				degree : {
					maxlength : 5
				},
				major : {
					maxlength : 100
				},
				old_job : {
					maxlength : 100
				},
				old_company : {
					maxlength : 100
				},
				work_experience : {
					maxlength : 100
				},
				biz_years : {
					maxlength : 2,
					checkNum : []
				},
				bank_code : {
					required : true,
					maxlength : 100
				},
				bank_account_no : {
					required : true,
					maxlength : 32,
					isNum : []
				},
				bank_account_name : {
					required : true,
					maxlength : 16
				}
			},
			messages:{
				technology_no:{
					maxlength : "请输入20位的资格证号码",
					minlength : "请输入20位的资格证号码"
				},
				home_zipcode:{
					maxlength:"请输入正确的邮政编码！"
				}
			},
			onkeyup:false,
			onsubmit:false
		});
		$("#subButton").click(function(){
			blockUI();
			if(!validator.form()){
				$.unblockUI();
			}else{
				document.getElementById('form').submit();
			}
		});
	});
</script>	   
<script type="text/javascript">
 $(function(){
	 	$("#st1").delegate("td","click",function(){
			var str = $(this).text();
			$("#recommend_id").next("label[class='error']").remove();
			$("#recommend_id").val(str);
			$("#recommend_id").focus();
			$("#st1 tbody").empty();
		}); 
	 	 //鼠标移到到那一行改变颜色
	 	$("#st1").delegate("tr","mouseover",function(){
			$(this).addClass("over");
		}); 
	 	 //鼠标移出到那一行改变颜色
		$("#st1").delegate("tr","mouseout",function(){
			$(this).removeClass("over");
		});  
	 	$("#recommend_id").keyup(function(){
		var recommend_id = $("#recommend_id").val();
		if(recommend_id==null||recommend_id==""){
			$("#st1 tbody").empty();
			return;
		} 
	 	var emp_id; 
	 	var t_name = 'sales_id';//要查询的字段
	 	var table_name = 'smis_sales';//要查询的表名
	  	var reg = recommend_id+'%'; //定义匹配规则
			$.ajax({
	     		url : "<%=basePath %>/Manage/FuncPanel/getSechInfo.do",
	     		type : "post",
	     		async : false,
	     		data : {
	     			"emp_id" : emp_id,
	     			"keyWord" : recommend_id,
	     			"t_name" : t_name,
	     			"table_name" : table_name,
	     			"reg" : reg
	     		},
	     		dataType:"json",
	     		success : function(data) {
	     			$("#st1 tbody").empty();
	     			var tbodyHtml="";
	     			var arr = new Array();
	     			$.each(data,function(index,comment){
	     				tbodyHtml+='<tr>'
     								+'<td id="newTd1">'+comment.seachMsg+'</td>'
	     							+'</tr>';
	     				arr[index] = comment.seachMsg;
	     			}); 
     				$("#st1 tbody").append(tbodyHtml);
     				if(arr.length == 1 && recommend_id == arr[0] ){  //当输入完整时去掉带出的那条数据
	     				$("#st1 tbody").empty();
	     			}
     				if(tbodyHtml==""){
     					$("#promptMessage1").css({ display: "none" });
     				}else{
	     				$("#promptMessage1").css({ display: "block" });
     				}
	     		}
			});
 	}); 
}); 
</script> 
<script type="text/javascript">
 $(function(){
	 	$("#st2").delegate("td","click",function(){
			var str = $(this).text();
			$("#team_id").next("label[class='error']").remove();
			$("#team_id").val(str);
			$("#team_id").focus();
			$("#st2 tbody").empty();
		}); 
	 	 //鼠标移到到那一行改变颜色
	 	$("#st2").delegate("tr","mouseover",function(){
			$(this).addClass("over");
		}); 
	 	 //鼠标移出到那一行改变颜色
		$("#st2").delegate("tr","mouseout",function(){
			$(this).removeClass("over");
		});  
	 	$("#team_id").keyup(function(){
		var team_id = $("#team_id").val();
		if(team_id==null||team_id==""){
			$("#st2 tbody").empty();
			return;
		} 
	 	var emp_id; 
	 	var t_name = 'team_id';//要查询的字段
	 	var table_name = 'smis_team';//要查询的表名
	  	var reg =team_id+'%'; //定义匹配规则(根据后四位人员代码查询)
			$.ajax({
	     		url : "<%=basePath %>/Manage/FuncPanel/getSechInfo.do",
	     		type : "post",
	     		async : false,
	     		data : {
	     			"emp_id" : emp_id,
	     			"keyWord" : team_id,
	     			"t_name" : t_name,
	     			"table_name" : table_name,
	     			"reg" : reg
	     		},
	     		dataType:"json",
	     		success : function(data) {
	     			$("#st2 tbody").empty();
	     			var tbodyHtml="";
	     			var arr = new Array();
	     			$.each(data,function(index,comment){
	     				tbodyHtml+='<tr>'
     								+'<td id="newTd">'+comment.seachMsg+'</td>'
	     							+'</tr>';
	     				arr[index] = comment.seachMsg;
	     			}); 
     				$("#st2 tbody").append(tbodyHtml);
     				if(arr.length == 1 && team_id == arr[0] ){  //当输入完整时去掉带出的那条数据
	     				$("#st2 tbody").empty();
	     			}
     				if(tbodyHtml==""){
     					$("#promptMessage2").css({ display: "none" });
     				}else{
	     				$("#promptMessage2").css({ display: "block" });
     				}
	     		}
			});
 	}); 
}); 
	     
	    
</script>
</head>
<body>

	<div class="container-fluid">
		<!-- 面包屑导航  start -->
		<div class="dreadcount">
			<span class=mrl14><i class="icon-list icon-red"></i> 人员管理 </span><span
				class="divider">/</span> <span><i class="icon-list icon-red"></i>
				人员基本信息查询</span><span class="divider"></span> <span><i
				class="icon-list icon-red"></i>新增</span>
		</div>
		<!-- 面包屑导航  end -->

		<!-- 添加信息数据区 start -->
		<div class="row-fluid">
			<form class="form-horizontal alert alert-info fade in span12" id="form"
				action="<%=basePath%>/Person/PersonManage/insertPersonInfo.do" method="POST" autocomplete="off">
				<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper1.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper1.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper1.msgStr}" displayLable="状态信息"/>	
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    <webTag:HiddenInputTag name="seq_id" id="seq_id" value="${rmHelper1.returnParams.seq_id}"></webTag:HiddenInputTag>
					<webTag:ReturnMsgTag id="msg" name="msg"
						successflag='${rmHelper1.successflag}' msg='${rmHelper1.msgStr}' />
				<fieldset>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 个人基本信息</span>
					</div>
					<div class="row">
						<webTag:Text id="person_no" name="person_no"
							value='${rmHelper.returnParams.person_no}'  displayLable="员工编码"
							readonly="true" isdisplay="false"></webTag:Text>
						<webTag:Text id="person_name" name="person_name"
							value='${rmHelper.returnParams.person_name}'  displayLable="员工姓名"
							readonly="false" isdisplay="false"></webTag:Text>
						<webTag:Select id="sex" name="sex"
							value='${rmHelper.returnParams.sex}' displayLable="性别:">
							<webTag:Option value="1" displayLable="男"></webTag:Option>
							<webTag:Option value="2" displayLable="女"></webTag:Option>
						</webTag:Select>
						
						
					</div>
					<div class="row">
						<webTag:Date id="birthday" name="birthday"
							value='${rmHelper.returnParams.birthday}' displayLable="出生日期:"
							readonly="false" />
						<webTag:Text id="age" name="age" value='${rmHelper.returnParams.age}'
								displayLable="年龄:"  readonly="false" ></webTag:Text>
						<%-- <webTag:Text id="person_type" name="person_type"
								value='${rmHelper.returnParams.person_type}'
								displayLable="员工类型" readonly="true" isdisplay="false"/>
						 --%>
						 <div class="control-group span4">
							<label class="control-label" for="person_status">员工状态</label>
							<div class='controls'> 
							<select class="input-medium null" id="person_status" name="person_status">
								<option value="">---请选择---</option>
								<c:forEach var="person_status" items="${person_statusList}">
									<c:if test="${rmHelper.returnParams.person_status == person_status.enum_code}">
							 			<option selected="selected" value="${person_status.enum_code}" >${person_status.enum_name}</option>
							 		</c:if>
							 		<c:if test="${rmHelper.returnParams.person_status != person_status.enum_code}">
							 			<option value="${person_status.enum_code}" >${person_status.enum_name}</option>
							 		</c:if>
                         		</c:forEach>
							</select>
							</div>
				    	</div> 
				    <div class="row">
						<%--<webTag:Date name="end_date" id="end_date"
						value='${rmHelper.returnParams.end_date}' displayLable="离职时间:" readonly="false" ></webTag:Date>--%>
						<%-- <webTag:Text id="work_relation" name="work_relation" value='${rmHelper.returnParams.work_relation}'
								displayLable="用工类型" readonly="true" isdisplay="false"></webTag:Text> --%>
<%-- 						<webTag:DynamicSelectTag src="work_natureSelect" name="work_nature" id="work_nature" value='${rmHelper.returnParams.work_nature}' displayLable="合同类型" isdisplay="false"></webTag:DynamicSelectTag> --%>						
					 	 <webTag:Select id="work_nature" name="work_nature" value='${rmHelper.returnParams.work_nature}'
								displayLable="合同类型:"  readonly="false" >
								<webTag:Option value="1" displayLable="正式合同"></webTag:Option>
								<webTag:Option value="2" displayLable="试用期员工"></webTag:Option>
								<webTag:Option value="3" displayLable="个人代理"></webTag:Option>
								<webTag:Option value="4" displayLable="退休返聘"></webTag:Option>
								<webTag:Option value="5" displayLable="劳务派遣"></webTag:Option>
								<webTag:Option value="6" displayLable="实习员工"></webTag:Option>
								<webTag:Option value="7" displayLable="专职委派"></webTag:Option>
								<webTag:Option value="8" displayLable="兼职委派"></webTag:Option>
						</webTag:Select>

							<webTag:Text id="phone" name="phone"
											 value='${rmHelper.returnParams.phone}' displayLable="移动电话:"
											 readonly="false"  />
							<webTag:Text name="home_address" id="home_address"
										 value='${rmHelper.returnParams.home_address}' displayLable="通信地址:"
										 readonly="false"></webTag:Text>
				    </div>
				    <div class="row">
				    	<webTag:DynamicSelectTag src="nationSelect" name="national" id="national" value='${rmHelper.returnParams.national}' displayLable="民族" isdisplay="false"></webTag:DynamicSelectTag>
						<webTag:DynamicSelectTag src="politicalSelect" name="political" id="political" value='${rmHelper.returnParams.political}' displayLable="政治面貌" isdisplay="false"></webTag:DynamicSelectTag>
						<webTag:Select name="card_type" id="card_type"
							value='${rmHelper.returnParams.card_type}' displayLable="户口类型:"
							 readonly="false">
							<webTag:Option value="1" displayLable="城镇"></webTag:Option>
							<webTag:Option value="2" displayLable="农村"></webTag:Option>
						</webTag:Select>
				    </div>
				    <div class="row">
				    	<%--<webTag:DynamicSelectTag src="educationSelect" name="education" id="education" value='${rmHelper.returnParams.education}' displayLable="学历" isdisplay="false"></webTag:DynamicSelectTag>--%>
						<!-- 照片 -->
						<webTag:Text id="idcard" name="idcard"
							value='${rmHelper.returnParams.idcard}' displayLable="身份证号:"
							readonly="false" />

							<webTag:DynamicSelectTag src="maritalSelect" name="ismarrid" id="ismarrid" value='${rmHelper.returnParams.ismarrid}' displayLable="婚姻状态" isdisplay="false"></webTag:DynamicSelectTag>
							
				    </div>

				    <div class="row">
						
				    </div>
							<div class="dreadcount">
								<span><i class="icon-ziliao icon-red mrl14"></i> 教育信息</span>
								<div class="Shrinktop">
									<div class="slideUp_Down" id="Shrinkbutton5"></div>
								</div>
							</div>
							<div class="Shrinkcontent" id="Shrinkcontent5">
								<div class="row">
									<webTag:Text id="address" name="address"
												 value='${rmHelper.returnParams.address}'
												 displayLable="毕业院校:" readonly="false" />
									<webTag:Text id="major" name="major"
												 value='${rmHelper.returnParams.major}'
												 displayLable="专业:" readonly="false" />
									<%-- 							<webTag:DynamicSelectTag src="education_typeSelect" name="education_type" id="education_type" value='${rmHelper.returnParams.education_type}' displayLable="教育类型" isdisplay="false"></webTag:DynamicSelectTag>--%>
									<%--<webTag:Select id="education_type" name="education_type"
												   value='${rmHelper.returnParams.education_type}'
												   displayLable="教育类型:" readonly="false" >
										<webTag:Option value="1" displayLable="全日制"></webTag:Option>
										<webTag:Option value="2" displayLable="在职"></webTag:Option>
									</webTag:Select>--%>
									<webTag:Date id="graduation_date" name="graduation_date"
												 value='${rmHelper.returnParams.graduation_date}'
												 displayLable="毕业年份:" readonly="false" />
								</div>

								<div class="row">

									<webTag:DynamicSelectTag src="educationSelect" name="education" id="education" value='${rmHelper.returnParams.education}' displayLable="学历" isdisplay="false"></webTag:DynamicSelectTag>

								</div>


							</div>

							<div class="dreadcount">
								<span><i class="icon-ziliao icon-red mrl14"></i> 执业信息</span>
								<div class="Shrinktop">
									<div class="slideUp_Down" id="Shrinkbutton3"></div>
								</div>
							</div>
							<div class="Shrinkcontent" id="Shrinkcontent3">

								<div class="row">
									<webTag:Text id="practice_no" name="practice_no"
												 value='${rmHelper.returnParams.practice_no}'
												 displayLable="执业证编号:" />
									<webTag:Date id="practice_startdate" name="practice_startdate"
												 value='${rmHelper.returnParams.practice_startdate}'
												 displayLable="执业证有效期开始:"></webTag:Date>
									<webTag:Date id="practice_enddate" name="practice_enddate"
												 value='${rmHelper.returnParams.practice_enddate}'
												 displayLable="执业证有效期截止:"></webTag:Date>

								</div>

							</div>
					<div id="uploadimage" style="width: 100%;"></div>
					<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i> 行政信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton1"></div>
						</div>
					</div>
					<div class="Shrinkcontent" id="Shrinkcontent1">
						<div class="row">
							<webTag:Text id="team_id" name="team_id" value='${rmHelper.returnParams.team_id}' displayLable="团队代码" isdisplay="false"/>
							<div id="promptMessage2" class="seachDiv div1">
        				         <table id="st2"><tbody></tbody></table>
    					    </div>
						 	<webTag:Text id="team_name" name="team_name" value='${rmHelper.returnParams.team_name}' displayLable="团队名称:" readonly="true"/>
						 	<webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="所属机构名称:" readonly="true"/>
						 	<webTag:HiddenInputTag name="branch_id" id="branch_id"></webTag:HiddenInputTag>
						</div>

						<div class="row">
							<webTag:Text id="entry_source" name="entry_source"
								value='${rmHelper.returnParams.entry_source}'
								displayLable="入职来源:" readonly="false" />
							<webTag:Date id="entry_date" name="entry_date"
								value='${rmHelper.returnParams.entry_date}' displayLable="入职时间:" readonly="false"/>

						</div>


					<%--</div>

						<div class="dreadcount">
							<span><i class="icon-ziliao icon-red mrl14"></i> 执业信息</span>
							<div class="Shrinktop">
								<div class="slideUp_Down" id="Shrinkbutton3"></div>
							</div>
						</div>
						<div class="Shrinkcontent" id="Shrinkcontent3">

							<div class="row">
								<webTag:Text id="practice_no" name="practice_no"
									value='${rmHelper.returnParams.practice_no}'
									displayLable="执业证编号:" />
								<webTag:Date id="practice_startdate" name="practice_startdate"
									value='${rmHelper.returnParams.practice_startdate}'
									displayLable="执业证有效期开始:"></webTag:Date>
								<webTag:Date id="practice_enddate" name="practice_enddate"
									value='${rmHelper.returnParams.practice_enddate}'
									displayLable="执业证有效期截止:"></webTag:Date>

							</div>

						</div>--%>



					


					
				<!-- 	<div class="dreadcount">
						<span><i class="icon-ziliao icon-red mrl14"></i>高管信息</span>
						<div class="Shrinktop">
							<div class="slideUp_Down" id="Shrinkbutton9"></div>
						</div>
					</div> -->
					<%-- <div class="Shrinkcontent" id="Shrinkcontent11">
						<div class="row" >
							<webTag:Select name="gleader" id="gleader" value='${rmHelper.returnParams.gleader}' displayLable="是否高管:">
							<webTag:Option value="1" displayLable="是"></webTag:Option>
							<webTag:Option value="0" displayLable="否"></webTag:Option>
							</webTag:Select>
							<webTag:Date name="approval_time" id="approval_time"
							value='${rmHelper.returnParams.approval_time}' displayLable="高管批复时间:"></webTag:Date>
							<webTag:Text name="approval_file" id="approval_file"
							value='${rmHelper.returnParams.approval_file}' displayLable="高管批复文号:" />
						</div>

						<div class="row">
							<webTag:Date name="employment_term" id="employment_term"
								value='${rmHelper.returnParams.employment_term}' displayLable="高管聘期:"></webTag:Date>
							<div class="control-group span4"><label class="control-label" for="upload_approval_file">上传批复文件:</label>
								<input class="input-medium null" type="file" id="file" name="uploadfile" value="${rmHelper.returnParams.upload_approval_file}"/>
							</div>
            				<button type="button" value="上传"  onclick="fileUpload();">上传</button>
						</div>
						<div class="row">
            				<webTag:Date name="gvalid_time" id="gvalid_time"
							value='${rmHelper.returnParams.gvalid_time}' displayLable="有效期 :"></webTag:Date>
						</div>
					</div> --%>
					<%-- <div class="Shrinkcontent" id="Shrinkcontent9">
						<table
							class="table table-striped table-bordered bootstrap-datatable datatable ">
							<thead>
								<tr>
									<th>是否高管</th>
									<th>高管批复时间</th>
									<th>高管批复文号</th>
									<th>高管聘期</th>
									<th>下载批复文件</th>
									<th>有效期至</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<c:if test="${rmHelper.returnParams.gleader=='1'}">是</c:if>
										<c:if test="${rmHelper.returnParams.gleader=='0'}">否</c:if>
									</td>
									<td>${rmHelper.returnParams.approval_time}</td>
									<td>${rmHelper.returnParams.approval_file}</td>
									<td>${rmHelper.returnParams.employment_term}</td>
									<td><c:if test="${rmHelper.returnParams.gleader=='1'}"><a href="javaScript:void();" onclick="downloadPic();">下载</a></c:if>
									<input id="upload_approval_file" type="hidden" value='${rmHelper.returnParams.upload_approval_file}'/>
									<td>${rmHelper.returnParams.gvalid_time}</td>
						 --%>
				</fieldset>
				<div class="row" align="right">

					<button type="button"  class="btn btn-danger" onclick="saveData();"><i class="icon-inbox icon-white"></i>保存</button></span>
					<%-- <a id="saveButton" class="btn btn-danger"
						href="<%=basePath %>/Person/PersonManage/updatePerson.do?pageName=personInfo"><i
						class="icon-share-alt icon-white"></i>保存</a> --%>
					<a id="backButton" class="btn btn-danger"
						href="<%=basePath %>/Person/PersonManage/toQueryPersonInfo.do?pageName=personInfo"><i
						class="icon-share-alt icon-white"></i>返回</a> 
				</div>
			</form>
		</div>
		<!-- 数据区 end -->
	</div>
	<!-- 		底部高度填充块 -->
	<div class="zeoBottomH90"></div>
	<!-- 		底部高度填充块 结束-->
</body>
</html>
<script type="text/javascript">
	function saveData(){
	$("#form").submit();
} 

<%-- function saveData(){
	var birthday = document.getElementById("birthday").value;
	var education = document.getElementById("education").value;
	var org_id = document.getElementById("org_id").value;
	var org_name = document.getElementById("org_name").value;
	var belong_opt_no = document.getElementById("belong_opt_no").value;
	var belong_opt_name = document.getElementById("belong_opt_name").value;
	var belong_idcard = document.getElementById("belong_idcard").value;
	var technology_no = document.getElementById("technology_no").value;
	var practice_type = document.getElementById("practice_type").value;
	var practice_status = document.getElementById("practice_status").value;
	var practice_no = document.getElementById("practice_no").value;
	var practice_startdate = document.getElementById("practice_startdate").value;
	var practice_enddate = document.getElementById("practice_enddate").value;
	var practice_area = document.getElementById("practice_area").value;
	var channel_type = document.getElementById("channel_type").value;
	var business_scope = document.getElementById("business_scope").value
	var person_no = document.getElementById("person_no").value;
	var political = document.getElementById("political").value;
	var content_code = document.getElementById("content_code").value;
	var political_startdate = "";
	var political_joindate = "";
	var political_company = "";
	var party_org = "";
	var political_fee = "";
	if(political != null && political == "党员"){
		var political_startdate = document.getElementById("political_startdate").value;
		var political_joindate = document.getElementById("political_joindate").value;
		var political_company = document.getElementById("political_company").value;
		var party_org = document.getElementById("party_org").value;
		var political_fee = document.getElementById("political_fee").value;
	}
	/* var gleader = document.getElementById("gleader").value;
	var approval_time = document.getElementById("approval_time").value;
	var approval_file = document.getElementById("approval_file").value;
	var employment_term = document.getElementById("employment_term").value; */

	$.ajax({
		  type: "POST",
		  url: "<%=basePath%>/Person/PersonManage/savePersonInfo.do",
		  data: {
			  "education": education,
			  "org_id": org_id,
			  "org_name": org_name,
			  "belong_opt_no": belong_opt_no,
			  "belong_opt_name": belong_opt_name,
			  "belong_idcard": belong_idcard,
			  "technology_no": technology_no,
			  "practice_type": practice_type,
			  "practice_status": practice_status,
			  "practice_no": practice_no,
			  "practice_startdate": practice_startdate,
			  "practice_enddate": practice_enddate,
			  "practice_area": practice_area,
			  "channel_type": channel_type,
			  "business_scope": business_scope,
			  "political_startdate": political_startdate,
			  "political_joindate": political_joindate,
			  "political_company": political_company,
			  "party_org": party_org,
			  "political_fee": political_fee,
			  "person_no": person_no,
			  "content_code": content_code
			 /*  "gleader": gleader,
			  "approval_time": approval_time,
			  "approval_file": approval_file,
			  "employment_term": employment_term */
			  },
		  dataType:"text",
		  success: function(data){
			 alert(data); 
		  },
		  error: function(){
			  alert("保存失败!");
		  }
		});
} --%>
</script>