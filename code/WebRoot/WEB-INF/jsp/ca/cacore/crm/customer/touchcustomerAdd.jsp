<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.StringHelper"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>
<%@page import="com.newtouch.component.c11assistant.ReturnMsgHelper"%>

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
		<script type="text/javascript">
			/* $(document).ready(function() {
				$("#personBaseInfo").hide();//个人基本信息
				$("#personContactInfo").hide();//个人联系信息
				$("#corporationBaseInfo").hide();//法人基本信息
				$("#corporationContactInfo").hide();//法人联系信息
				$("#customerValueInfo").hide();//客户价值分析
			}); */
			jQuery.validator.addMethod("checkcdcard", function(value, element) {
				 var reg = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
				   if(reg.test(value) != true){
				    return false;
				   }else{
				    return true;
				   }
			}, "身份证号不符合格式");
			
			$(document).ready(function() {
	    		$("#queryForm").validate({
	    			rules : {
	    				
	    				insured_name : {
	    					required : true,
	    					maxlength :100,
	    				},
	    				insured_cid : {
	    					required : true,
	    					maxlength :18,
	    					minlength :18,
	    					checkcdcard:[]
	    				},
	    				insured_phone : {
	    					required : true,
	    					maxlength :11,
	    					minlength :11,
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
	
		  $(function() {
			     $("#customer_type").change(
			    	function(){
			    		customerTypeChange();
			    	}
			     );
		    });
		
		function customerTypeChange(){
			var customer_type=$("#customer_type").val();
			if(customer_type!="" && customer_type!=null){
				if("0"==customer_type){//个人
					 $("#personBaseInfo").show();//个人基本信息
					 $("#personContactInfo").show();//个人联系信息
					 $("#customerValueInfo").show();//客户价值分析 
					 $("#corporationBaseInfo").hide();//法人基本信息
					 $("#corporationContactInfo").hide();//法人联系信息
				}else if("1"==customer_type){//法人
					$("#corporationBaseInfo").show();//法人基本信息
					$("#corporationContactInfo").show();//法人联系信息
					$("#customerValueInfo").show();//客户价值分析 
					$("#personBaseInfo").hide();//个人基本信息
					$("#personContactInfo").hide();//个人联系信息
				}
			}
		}
		
		
		 //验证身份证
	  /*   jQuery.validator.addMethod("checkIdCard", function(value, element,param) {
	    	//如果证件类型为身份证（value=11）那么验证身份证号的正确性
	    	var certi_type = $("#certi_type").val();
	    	
	     	if(certi_type!="11"){
	     		return true;
	     	}
	    	var insured_cid = $("#certi_no").val();
	     	if(isUndefined(insured_cid)){
	     		return true;
	     	}
	     	var regex1 = /^[1-9][0-7]\d{4}((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))0229))\d{3}(\d|X|x)?$/;
	     	var regex2;
	     	/*身份号码位数及格式检验
	     	switch (insured_cid.length) {
	     	  case 15:
	     		if ( (parseInt(insured_cid.substr(6,2))+1900) % 4 == 0 || ((parseInt(insured_cid.substr(6,2))+1900) % 100 == 0 && (parseInt(insured_cid.substr(6,2))+1900) % 4 == 0 )){
	     			regex2= /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
	     		} else {
	     			regex2 = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
	     		}
	     		if(regex2.test(insured_cid)){
	     			$("#birthday").val(getBirthdayFromCnId(insured_cid));
	     			$("#gender").val(getGenderFromCnId(insured_cid)=="M"?1:2);
	     			return true;
	     		}
	     		else{
	     			$("#birthday").val("");
	     			return false;
	     		}
	     		break; 
	     	  case 18:
	     	 	 if(regex1.test(insured_cid)){
	     			var S = (parseInt(insured_cid[0]) + parseInt(insured_cid[10])) * 7 + (parseInt(insured_cid[1]) + parseInt(insured_cid[11])) * 9 + (parseInt(insured_cid[2]) + parseInt(insured_cid[12])) * 10 + (parseInt(insured_cid[3]) + parseInt(insured_cid[13])) * 5 + (parseInt(insured_cid[4]) + parseInt(insured_cid[14])) * 8 + (parseInt(insured_cid[5]) + parseInt(insured_cid[15])) * 4 + (parseInt(insured_cid[6]) + parseInt(insured_cid[16])) * 2 + parseInt(insured_cid[7]) * 1 + parseInt(insured_cid[8]) * 6 + parseInt(insured_cid[9]) * 3;
	     			var Y = S % 11;
	     			var M = "F";
	     			var JYM = "10X98765432";
	     			M = JYM.substr(Y, 1);
	     			/*判断校验位
	     			if (M == insured_cid[17].toUpperCase()) {
	     				$("#birthday").val(getBirthdayFromCnId(insured_cid));
	     				$("#gender").val(getGenderFromCnId(insured_cid)=="M"?1:2);
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
	     }, jQuery.validator.format("请输入合法的身份证"));*/
	     

	  		
	     
	   
	     
	        
	      
	     
	    
			 
		 </script>		     
		
			     
	</head>
	<body style="height: 750px">
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 客户关系管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 客户信息维护</span>
			     <span><i class="icon-list icon-red"></i> 客户添加</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm"  class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/CRM/Customer/addCustomerAndContact.do" method="POST" enctype="multipart/form-data" autocomplete="off">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				 <webTag:HiddenInputTag name="returnMsg" id="returnMsg" value="${rmHelper.returnParams.returnMsg}"></webTag:HiddenInputTag>
				 <webTag:HiddenInputTag name="removeflag" id="removeflag" value="${removeflag}"></webTag:HiddenInputTag>	
				 <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
				 <webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>	
				 <webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/> 
					
					<fieldset id="personContactInfo">
					<div class="dreadcount">
				    	<span class=mrl14><i class="icon-list icon-red"></i> 客户数据</span>
					</div>
					<div class="row">
				     	<jsp:include page="../../util/NewBranchTree.jsp" flush="true" />
						<webTag:Text id="insured_name" name="insured_name" value=' ${rmHelper.returnParams.insured_name}' displayLable="客户姓名" isdisplay="true"/>
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
					</div><!-- /.row -->
					
					<div class="row">                
                        <!--<webTag:Text id="insured_papertype" name="insured_papertype" value='${rmHelper.returnParams.insured_papertype}' displayLable="被保险人证件类型"/>-->
                         <webTag:Text id="home_address" name="home_address" value='${rmHelper.returnParams.home_address}' displayLable="客户地址"/>
                         <webTag:Text id="insured_phone" name="insured_phone" value='${rmHelper.returnParams.insured_phone}' displayLable="客户手机号" isdisplay="true"/>
                        <webTag:Text id="insured_tel" name="insured_tel" value='${rmHelper.returnParams.insured_tel}' displayLable="客户固话"/>
					</div><!-- /.row -->
					
					
					<div class="row">                
                      <!--   <webTag:DynamicSelectTag src="documenttypeSelect" name="insured_papertype" id="insured_papertype" displayLable="被保险人证件类型" value='${rmHelper.returnParams.insured_papertype}' /> -->
                       <webTag:Text id="insured_mailbox" name="insured_mailbox" value='${rmHelper.returnParams.insured_mailbox}' displayLable="客户邮箱"/>
                       <webTag:Text id="insured_cid" name="insured_cid" value='${rmHelper.returnParams.insured_cid}' displayLable="客户证件号码" isdisplay="true"/>                        
					</div>
					
					<div class="row" style="text-align: right;">
						<c:if test="${flag=='1'}">
						<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
						</c:if>
						<a id="backBtn" class="btn btn-danger" href="<%=basePath%>/CRM/Customer/toAddAndModifyCustomer.do" style='text-decoration:none;'><i class="icon-share-alt icon-white"></i>返回</a>
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
