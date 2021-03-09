<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ServletHelper"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
    //request.getContextPath()返回当前页面所在的应用的名字
	String path = request.getContextPath();
    //request.getScheme()返回当前请求所使用的协议;request.getServerName()返回当前页面所在的服务器的名字
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html lang="en">
	<head >
		<title>新致金保通</title>
		<jsp:include page="../../pub/jvbasecss.jsp" flush="true"/>
		<jsp:include page="../../pub/jvbasejs.jsp" flush="true"/>
		<!-- 回跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<!-- 职级联动 -->
	    <jsp:include page="../../pub/jvrank.jsp" flush="true"/>
		<script src="../../compent/webstyle/js/jqueryui/tests/index.js" type="text/javascript"></script>
		<script type="text/javascript">
		
		jQuery.validator.addMethod("checkcdcard", function(value, element) {
			 var reg = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
			   if(reg.test(value) != true){
			    return false;
			   }else{
			    return true;
			   }
		}, "身份证号不符合格式");
		jQuery.validator.addMethod("checkcdcard1", function(value, element) {
			 var reg = /^1\d{10}$/;
			   if(reg.test(value) != true){
			    return false;
			   }else{
			    return true;
			   }
		}, "手机号不符合格式");
		jQuery.validator.addMethod("checkcdcard2", function(value, element) {
			 var reg = /0\d{2,3}-\d{7,8}$/;
			   if(reg.test(value) != true){
			    return false;
			   }else{
			    return true;
			   }
		}, "固话不符合格式");
		
		$(document).ready(function() {
   		$("#queryForm").validate({
   			rules : {
   				insured_name : {
   					required : true,
   					maxlength :100,
   					checkteamnameRepeat: []
   				},
   				insured_cid : {
   					required : true,
   					maxlength :18,
   					minlength :18,
   					checkcdcard:[]
   				},
   				insured_phone : {
   					required : true,
   					checkcdcard1:[]
   			    },
   			 insured_tel : {
					required : true,
					checkcdcard2:[]
				}
   			},
   			onkeyup:false 
   		});
   	});  		
		//校验样式效果,文本框获取焦点,隐藏该文本框相应报错信息
  		 $("#queryForm").find("input").each(function(){
          		$(this).click(function(){
          			var _this=$(this);
          			if(_this.hasClass("error")){
          				_this.removeClass("error");
          				var labelAR = _this.parent().find("label[class='error']");
          				labelAR.remove();
          			}
          		});
          	});	
		
		 //验证身份证
	     jQuery.validator.addMethod("checkIdCard", function(value, element,param) {
	    	//如果证件类型为身份证（value=11）那么验证身份证号的正确性
	    	var certi_type = $("#certi_type").val();
	    	
	     	if(certi_type!="11"){
	     		return true;
	     	}
	    	var idcard = $("#certi_no").val();
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
	     			$("#gender").val(getGenderFromCnId(idcard)=="M"?1:2);
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
	     				$("#gender").val(getGenderFromCnId(idcard)=="M"?1:2);
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
	     

	  		
	    
	     
	        
	    
	     
	       

			$(document).ready(function() {
				$("input[name='yitiao']").each(function(i,val){
			 		var seq_id=$(this).val();
			 		var a=$(this).attr("class");
			 		var corporation_contact_person=$("#corporation_contact_person"+a).val();
			 		var corporation_contact_mobile=$("#corporation_contact_mobile"+a).val();
			 		var corporation_contact_qq=$("#corporation_contact_qq"+a).val();
			 		var corporation_contact_wechat=$("#corporation_contact_wechat"+a).val();
		 	 		$("#index"+a).val(a+"#"+"person,"+corporation_contact_person+"#"+"mobile,"+corporation_contact_mobile+"#"+"qq,"+corporation_contact_qq+"#"+"wechat,"+corporation_contact_wechat);
		 	 		$("#corporation_contact_person"+a).rules("add",{maxlength :60,required:true }); 
					$("#corporation_contact_mobile"+a).rules("add",{mobilePhone : [],required:true  }); 
					$("#corporation_contact_qq"+a).rules("add",{number:true }); 
					$("#corporation_contact_wechat"+a).rules("add",{maxlength :60  }); 
			 	 });
			  });
			
				function checkSubmit(){
					 var customer_type=$("#customer_type").val();
						if(customer_type!="" && customer_type!=null){
							if("1"==customer_type){//法人
							 	 $("input[name='yitiao']").each(function(i,val){
							 		var seq_id=$(this).val();
							 		var a=$(this).attr("class");
							 		var corporation_contact_person=$("#corporation_contact_person"+a).val();
							 		var corporation_contact_mobile=$("#corporation_contact_mobile"+a).val();
							 		var corporation_contact_qq=$("#corporation_contact_qq"+a).val();
							 		var corporation_contact_wechat=$("#corporation_contact_wechat"+a).val();
						 	 		 $("#index"+a).val(a+"#"+"person,"+corporation_contact_person+"#"+"mobile,"+corporation_contact_mobile+"#"+"qq,"+corporation_contact_qq+"#"+"wechat,"+corporation_contact_wechat);
							 	 });
							}
						}
						 $("#queryForm").submit();
		     	}  
		     	 $(document).ready(function() {
		    		$("#queryForm").validate({
		    			rules : {
		    				insured_name : {
		    					required : true,
		    				},
		    				insured_cid : {
		    					required : true,
		    					maxlength :100,
		    					checkteamnameRepeat: []
		    				}
		    				
		    			},
		    			onkeyup:false 
		    		});
		    	}); 
		     	
		     	
		    
					
		 </script>		   
		 <!--<script type="text/javascript" src="<%=basePath%>/compent/default/js/attachmentModify.js"></script>  --> 
	     <!--<script type="text/javascript" src="<%=basePath %>/compent/ckeditor/ckeditor.js"></script> --> 
	</head>
	<body style="height: 750px">
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户信息维护</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户信息修改</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm"  class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CRM/Customer/modifySave.do" method="POST" autocomplete="off">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				<!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				     <webTag:HiddenInputTag name="returnMsg" id="returnMsg" value="${rmHelper.returnParams.returnMsg}"></webTag:HiddenInputTag>
				     <webTag:HiddenInputTag name="removeflag" id="removeflag" value="${removeflag}"></webTag:HiddenInputTag>	
					 <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					 <webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					 <webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>

					
			       
			       
			         <webTag:HiddenInputTag id="customer_id" name="customer_id"  value="${rmHelper.returnMsg.dataTable.customer_id}"/>
				   
					<fieldset >
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户基本信息</span>
					</div>
					<div class="row"> 
					 <jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree.jsp" flush="true" />
                     <webTag:Text  id="insured_name" name="insured_name" value='${rmHelper.returnMsg.dataTable.insured_name}' displayLable="客户姓名" isdisplay="true"/>    
					<div class="control-group span4">
							<label class="control-label" for="insured_company_type">客户性质</label>
							<div class='controls'>
								<select class="input-medium null" id="insured_company_type"
									name="insured_company_type">
									<option value="">---请选择---</option>
									<option value='1'
										<c:if test="${rmHelper.returnMsg.dataTable.insured_company_type=='1'}"> selected="selected"</c:if>>单位</option>
									<option value='2'
										<c:if test="${rmHelper.returnMsg.dataTable.insured_company_type=='2'}"> selected="selected"</c:if>>个人</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="row"> 
                         <webTag:Text id="home_address" name="home_address" value='${rmHelper.returnMsg.dataTable.home_address}' displayLable="客户地址"/>
                         <webTag:Text id="insured_phone" name="insured_phone" value='${rmHelper.returnMsg.dataTable.insured_phone}' displayLable="客户手机号" isdisplay="true"/>
                         <webTag:Text id="insured_tel" name="insured_tel" value='${rmHelper.returnMsg.dataTable.insured_tel}' displayLable="客户固话"/>
					</div>
					<div class="row">    
					   <!--  <webTag:DynamicSelectTag src="documenttypeSelect" name="insured_papertype" id="insured_papertype" displayLable="被保险人证件类型:" value='${rmHelper.returnParams.insured_papertype}' />-->
					     <webTag:Text id="insured_mailbox" name="insured_mailbox" value='${rmHelper.returnMsg.dataTable.insured_mailbox}' displayLable="客户邮箱"/>
                         <webTag:Text  id="insured_cid" name="insured_cid" value='${rmHelper.returnMsg.dataTable.insured_cid}' displayLable="客户证件号码" isdisplay="true"/>
                         
					</div> 
				     	<div class="row" style="text-align:right;">
	            
                       <!-- <button id="submitBtn" type="submit" onclick="submitFormForFile('<%=basePath %>');checkSubmit();" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>	-->		
    			      	<button id="submitBtn" type="submit" class="btn btn-danger" onClick="defaultQuery();" ><i class="icon-inbox icon-white"></i>保存</button>
					    <a class="btn btn-danger" id="backButton" href="<%=basePath %>/CRM/Customer/goQueryAndModify.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>
				  </fieldset>
				  	
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		
		<!-- 		底部高度填充块 结束-->
	</body>
</html>
