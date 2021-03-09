<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>新致金保通</title>
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<jsp:include page="../../manage/branch/province.jsp" flush="true"/>
		<script >
		
		//手机号校验以1开头，第二位可能是3/4/5/7/8等的任意一个
		jQuery.validator.addMethod("checkphone", function(telephone, element) {
	    	 var telephone =  $("#telephone").val();
			 var reg = /^1(3|4|5|7|8)\d{9}$/;
			 if(telephone != null && telephone != ""){
				 if(reg.test(telephone) != true){
					    return false;
					   }else{
					    return true;
					   }
			 }
			 return true;
		}, "手机号不符合格式");
		
		 // 异步请求机构名称
	        jQuery.validator.addMethod("checkBranchNameRepeat",function(value,element){
	        	debugger;
		     	$.ajax({
		     		url:"<%=basePath %>/Branch/addCheckRepeat.do",
		     		type:"post",
		     		async: false,
		     		data:{"branch_name":value},
		     		success:function(data){
		     			var str=data.substring(1,data.lastIndexOf('}'));
	     				var isSuccess=str.split(',')[0].split(':')[1];
	     				if(isSuccess=="true"){
	     					$("#flag").val("true");
	     				}else{
	     					$("#flag").val("false");
	     				}
		     		}
		     	});
		     	if($("#flag").val()=="true"){
		     		return true;
		     	}else{
		     		return false;
		     	}
		     },"机构名称重复，请重新输入。");
		 
	        jQuery.validator.addMethod("checkDateFormat",function(value,element){
	        	if(!isUndefined(value)){
	        		var date = new Date();
			        var Year1 = date.getFullYear(); 
			        var Month1 = date.getMonth(); 
			        var Day1 = date.getDate();
			        var dateNumber1 = Year1*10000+Month1*100+Day1;
			        var pdate = changeStringToDate(value);
			        var Year2 = pdate.getFullYear(); 
			        var Month2 = pdate.getMonth(); 
			        var Day2 = pdate.getDate();
			        var dateNumber2 = Year2*10000+Month2*100+Day2;
			        if(dateNumber2>=dateNumber1){
			        	return true;
			        }else{
			        	return false;
			        }
			        
	        	}else{
	        		return false;
	        	}
		     },"日期不正确，请核实。");
	        
	       //判断值为空函数
	       function isUndefined(paraValue){
		        if(paraValue==null||paraValue==undefined||paraValue=="") return true;
		        return false;
		    }
	       
	       //将字符串型格式yyyy-MM-dd转换成日期类型
	       function changeStringToDate(date){
		     	return new Date(Date.parse(date.replace(/-/g,"/")));
		     }
	       
	     $(document).ready(function() {
	    		$("#queryForm").validate({
	    			rules : {
	    				branch_name : {
	    					required : true,
	    					maxlength :40,
	    					checkBranchNameRepeat : []
	    				},
	    				branch_abbr : {
	    					maxlength :40
	    				},
	    				/* branch_level : {
	    					required : true	    					
	    				}, */
	    				found_date : {
	    					required : true,
	    					minlength :10,
	    					//checkDateFormat : []
	    				},
	    				cost_center :
	    					{
	    						required:true,
	    						maxlength:10
	    					},
	    				settle_center :
	    					{
	    						required:true,
	    						maxlength:10
	    					},
	    				delegate : {
	    					required : false,
	    					maxlength :15
	    				},
	    				address : {
	    					required : false,
	    					maxlength :100
	    				},
	    				zip : {
	    					required : false,
	    					minlength :6,
	    					maxlength : 6,
	    					checkPost : []
	    				},
	    				telephone : {
	    					required : false,
	    					minlength :11,
	    					maxlength : 11,
	    					checkphone : []
	    				},
	    				fax : {
	    					required : false,
	    					maxlength :30,
	    					fax : []
	    				},
	    				email : {
	    					required : false,
	    					maxlength :30,
	    					checkEmail : []
	    				},
	    				remark : {
	    					required : false,
	    					maxlength :500
	    				},
	    				status : {
	    					required : true,
	    				},
	    				permitcode : {
	    					required : false,
	    					maxlength :50
	    				},
	    				permitarea : {
	    					required : false,
	    					maxlength :100
	    				},
	    				channelcode : {
	    					required : false,
	    					maxlength :50
	    				}
	    				
	    				
	    			},
	    			onkeyup:false,
	    		});	    		
	    	
	     var flag=$("#removeflag").val();
	     if(flag == "1")
	    	 {
		    	 $("#submitBtn").remove();
		    	 document.getElementById("branch_name").disabled='true';
	    	 }
	     
	     });
		 </script>		     
	</head>
	<body>
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>机构管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>新增</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/Branch/branchAdd.do" method="POST">
					<!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
					<!--<webTag:HiddenInputTag   id="branch_level" name="branch_level" value='${rmHelper.returnParams.branch_level}'/> -->
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag" value="${rmHelper.returnParams.flag}"></webTag:HiddenInputTag>
					<webTag:HiddenInputTag name="removeflag" id="removeflag" value="${removeflag}"></webTag:HiddenInputTag>					
					<fieldset>
					<div class="row">
					  	<webTag:Text id="branch_parentid" name="branch_parentid" value='${rmHelper.returnParams.branch_parentid}' displayLable="上级机构代码" isdisplay="true" readonly="true"/>
				  		<webTag:Text id="parent_branch_name" name="parent_branch_name"  value='${rmHelper.returnParams.parent_branch_name}' displayLable="上级机构名称" isdisplay="true" readonly="true"/>
				  		<webTag:Text id="branch_level_name" name="branch_level_name" value='${rmHelper.returnParams.branch_level_name}' readonly="true" isdisplay="true" displayLable="本机构层级"/>
					</div><!-- /.row -->
					
					<div class="row">                          
                        <webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="机构名称" isdisplay="true"/>
                        <webTag:Text id="branch_abbr" name="branch_abbr"  value='${rmHelper.returnParams.branch_abbr}' displayLable="机构简称" isdisplay="true"/>
                        <webTag:DynamicSelectTag src="statusSelect" name="status" id="status" value='1' displayLable="机构状态" isdisplay="true"></webTag:DynamicSelectTag>
					</div>
					
					<div class="row">                          
                        <webTag:Date id="found_date" name="found_date" value='${rmHelper.returnParams.found_date}' displayLable="成立日期" isdisplay="true"/>
                        <webTag:Text id="delegate" name="delegate" value='${rmHelper.returnParams.delegate}' displayLable="负责人:"/>
                        <webTag:Text id="zip" name="zip" value='${rmHelper.returnParams.zip}' displayLable="邮政编码:"/>
					</div>
					
					
					<div class="row">                
						
                        <webTag:Text id="telephone" name="telephone" value='${rmHelper.returnParams.telephone}' displayLable="电话号码:"/>
                        <webTag:Text id="address" name="address"  value='${rmHelper.returnParams.address}' displayLable="联系地址:"/>
                        <webTag:Text id="fax" name="fax" value='${rmHelper.returnParams.fax}' displayLable="机构传真:"/>
					</div><!-- /.row -->
					
					<div class="row">
					    <webTag:Text id="email" name="email" value='${rmHelper.returnParams.email}' displayLable="电子邮件:"/>
						<webTag:Text id="permitcode" name="permitcode" value='${rmHelper.returnParams.permitcode}' displayLable="保监许可机构编码:"/>
						<webTag:Text id="permitarea" name="permitarea" value='${rmHelper.returnParams.permitarea}' displayLable="区域:"/>
					</div>
					
					<!-- 省市县  by 孙豪-->
					<div class="row">
						 <webTag:Select  isdisplay="true"  id="province_code"  name="province_code"  displayLable="省" >
						 	<option selected="selected" value='${rmHelper.returnParams.province_code}'></option>
						 </webTag:Select>
					 	 <webTag:Select  isdisplay="true"  id="city_code"  name="city_code"  displayLable="市" >
						 	<option selected="selected" value='${rmHelper.returnParams.city_code}'></option>
						 </webTag:Select>
					 	 <webTag:Select  isdisplay="true"  id="area_code"  name="area_code"  displayLable="县">
						 	<option selected="selected" value='${rmHelper.returnParams.area_code}'></option>
						 </webTag:Select>
					</div>
					
					<div class="row">
						<webTag:Text id="channelcode" name="channelcode" value='${rmHelper.returnParams.channelcode}'  displayLable="渠道代码信息:" />
						<webTag:TextareaTag id="remark" name="remark" rows='3' value='${rmHelper.returnParams.remark}' displayLable="备注:"/>
					</div><!-- /.row -->
										
				    <div class="row" style="text-align:right;">
			    		<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
			    		<a class="btn btn-danger" href="<%=basePath %>/Branch/queryBranch.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
				  </fieldset>	
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
	</body>
</html>
