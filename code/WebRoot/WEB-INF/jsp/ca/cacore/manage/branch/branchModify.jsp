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
		
		 <%-- 	// 异步请求机构名称
	        jQuery.validator.addMethod("checkBranchNameRepeat",function(value,element){
	        	var seq_id = $("#seq_id").val();
	        	
		     	$.ajax({
		     		url:"<%=basePath %>/Branch/updateCheckRepeate.do",
		     		type:"post",
		     		async: false,
		     		data:{"branch_name":value,"seq_id":seq_id},
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
		     },"机构名称重复，请重新输入。"); --%>
		 
	        
	        
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
						found_date : {
							required : true,
						},
						cost_center :
    					{
    						required:true,
    						maxlength:10
    					},
    					settle_center :
    					{
    						required:true,
    						maxlength:50
    					},
						delegate : {
							maxlength :15
						},
						address : {
							maxlength :100
						},
						zip : {
							minlength : 6,
							maxlength : 6,
							checkPost : []
						},
						telephone : {
							minlength : 11,
							maxlength :11,
							checkphone : []
						},
						fax : {
							maxlength :30,
							fax : []
						},
						email : {
							maxlength :30,
							checkEmail : []
						},
						remark : {
							maxlength :500
						},
						/* province_code : {
	    					required : true,
	    				},
	    				city_code : {
	    					required : true,
	    				},
	    				area_code : {
	    					required : true,
	    				}, */
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
					onkeyup:false
				});
 			});
			function downimg(){
				var path=$("#zzimg").val();
				if(path==null || path==""){
					alert("未上传营业执照，请上传后再进行查看！");
					return ;
				}
				
				location.href="<%=basePath %>/Branch/downImg.do?u="+path;
			}
			function uploadimg(){
				var seq_id=$("#seq_id").val();
				location.href="<%=basePath %>/Branch/uploadimg.do?seq_id="+seq_id+"&lurl=ca/cacore/manage/branch/branchYYZZMOfify";
			}
		 </script>		     	     
	</head>
	<body id="body">
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>机构管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>修改</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/Branch/modifyBranch.do" method="POST">
					<!-- 提示信息 -->
				    <div id="dialog" title="提示信息" style="display:none">
				    	<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
				    <webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}"   displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}"    displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<webTag:HiddenInputTag id="status1" name="status1" value='${rmHelper.returnParams.status}'  displayLable="机构状态:" />
					<webTag:HiddenInputTag   id="seq_id" name="seq_id" value='${rmHelper.returnParams.seq_id}'/>
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
					<fieldset>
					
					<div class="row">
						<webTag:Text id="parent_branch_id" name="parent_branch_id" value='${rmHelper.returnParams.parent_branch_id}'  readonly="true" displayLable="上级组织代码" isdisplay="true"/>
						<webTag:Text id="parent_branch_name" name="parent_branch_name" value='${rmHelper.returnParams.parent_branch_name}' readonly="true" displayLable="上级组织名称" isdisplay="true"/>
						<webTag:Text id="branch_level_name" name="branch_level_name" value='${rmHelper.returnParams.branch_level_name}' readonly="true" displayLable="本组织层级" isdisplay="true"/>
					</div><!-- /.row -->
					
					<div class="row">                                    
						<webTag:Text id="branch_id" name="branch_id" value='${rmHelper.returnParams.branch_id}' readonly="true" displayLable="组织编码" isdisplay="true"/>
                        <webTag:Text id="branch_name" name="branch_name" value='${rmHelper.returnParams.branch_name}' displayLable="组织名称" isdisplay="true" readonly="true"/> 
                        <webTag:Text id="branch_abbr" name="branch_abbr"  value='${rmHelper.returnParams.branch_abbr}' displayLable="组织简称" isdisplay="true" readonly="true"/>
					</div><!-- /.row -->
					
					<div class="row">    
					   <!--  将成立日期修改成营业执照成立日期 by zdd 20190606    -->                   
                        <%-- <webTag:Date id="found_date" name="found_date" value='${rmHelper.returnParams.found_date}' displayLable="成立日期" isdisplay="true" readonly="true"/> --%>
                        
                        <%-- <webTag:Text id="delegate" name="delegate" value='${rmHelper.returnParams.delegate}' displayLable="负责人:"/> --%> <!-- by  zdd 20190606 -->
                        <webTag:Text id="delegate" name="delegate" value='${rmHelper.returnParams.delegate}' displayLable="负责人:" readonly="true"/> 
                        
					</div>
					
					<!-- 省市县 -->
					<div class="row">
					    
						 <%-- <webTag:Select   id="province_code"  name="province_code"  displayLable="省："  >
						 	<option selected="selected" value='${rmHelper.returnParams.province_code}'></option>
						 </webTag:Select>
					 	 <webTag:Select    id="city_code"  name="city_code"  displayLable="市："  >
						 	<option selected="selected" value='${rmHelper.returnParams.city_code}'></option>
						 
						 </webTag:Select> 
						 
					 	 <webTag:Select    id="area_code"  name="area_code"  displayLable="县："  >
						 	<option selected="selected" value='${rmHelper.returnParams.area_code}'></option>
						 </webTag:Select> --%><!-- zddxiu -->
						 <webTag:Text id="province" name="province" value='${rmHelper.returnParams.province}' displayLable="省:"/>
						 <webTag:Text id="city" name="city" value='${rmHelper.returnParams.city}' displayLable="市:"/>
						 <webTag:Text id="area" name="area" value='${rmHelper.returnParams.area}' displayLable="区/县:"/>
					</div>
					
					
					<div class="row">
						<%-- <c:if  test="${rmHelper.returnParams.status=='0' }">
							<webTag:Text id="status" name="status" value='失效' readonly="true" displayLable="组织状态:" />
	                    </c:if> --%>
	                    <%-- <c:if  test="${rmHelper.returnParams.status=='1' }"> --%>
							<%-- <webTag:Text id="status" name="status" value='启用' readonly="true" displayLable="机构状态:" /> --%>
							  <webTag:Select   id="status"   name="status"  displayLable="组织状态:" >
							  <c:if  test="${rmHelper.returnParams.status=='1' }">
						          <option   selected="selected" value='1'>启用</option>
						           <option   value='2'>禁用</option>
							  <option  value='0'>失效</option>
						          </c:if>
						          <c:if  test="${rmHelper.returnParams.status=='2' }">
						           <option   value='1'>启用</option>
	                              <option  selected="selected" value='2'>禁用</option>
	                               <option  value='0'>失效</option>
	                              </c:if>
	                              <c:if  test="${rmHelper.returnParams.status=='0'}">
	                               <option   value='1'>启用</option>
							 <option   value='2'>禁用</option>
	                              <option selected="selected"  value='0'>失效</option>
	                              </c:if>
	                            </webTag:Select>
	                   <%--  </c:if>
	                          --%>
	                   <%--  <c:if  test="${rmHelper.returnParams.status=='2' }">
							<webTag:Text id="status" name="status" value='禁用' readonly="true" displayLable="组织状态:" />
	                    </c:if> --%>
	                    <c:if  test="${rmHelper.returnParams.status=='0'}">
							<webTag:Text id="exittime" name="exittime" value='${rmHelper.returnParams.exittime }' readonly="true" displayLable="退出时间:" />
	                    </c:if>
						<webTag:Text id="channelcode" name="channelcode" value='${rmHelper.returnParams.channelcode}' displayLable="渠道代码信息:" /><!-- readonly="true"  -->
					    <%--  <webTag:Text id="channelcode" name="channelcode" value='${rmHelper.returnParams.channelcode}' readonly="true" displayLable="渠道代码信息:" /> --%>
	                    <webTag:Text id="unifiedSocialCreditNO" name="unifiedSocialCreditNO" value='${rmHelper.returnParams.unifiedSocialCreditNO}'  displayLable="统一社会信用代码:" />
	                    <%-- <webTag:Text id="deatailedaddress" name="deatailedaddress" value='${rmHelper.returnParams.deatailedaddress}'  displayLable="详细地址:" /> --%>
					</div>
					
					<div class="row">
						<%-- <webTag:Date id="found_date" name="found_date" value='${rmHelper.returnParams.buslicensefounddate}' displayLable="营业执照成立日期" isdisplay="true" /> --%>
						<webTag:Text id="fax" name="fax" value='${rmHelper.returnParams.fax}' displayLable="机构传真:"/>
						<webTag:Text id="zip" name="zip" value='${rmHelper.returnParams.zip}' displayLable="邮政编码:" />
						<webTag:Text id="deatailedaddress" name="deatailedaddress" value='${rmHelper.returnParams.deatailedaddress}'  displayLable="详细地址:" />
					</div>
					
					
					<div class="row">
                        <webTag:Text id="telephone" name="telephone" value='${rmHelper.returnParams.telephone}' displayLable="电话号码:"/>
                        <webTag:Text id="address" name="address" value='${rmHelper.returnParams.address}' displayLable="联系地址:"/>
                        <webTag:Text id="email" name="email" value='${rmHelper.returnParams.email}' displayLable="电子邮件:" />
                       
					</div><!-- /.row -->
					
					<div class="row"> 
                         
                        <webTag:Text id="permitcode" name="permitcode" value='${rmHelper.returnParams.permitcode}' displayLable="保监许可机构编码:"/>
                        <webTag:Text id="permitarea" name="permitarea" value='${rmHelper.returnParams.permitarea}' displayLable="区域:"/>
					</div><!-- /.row -->
				
                    <div class="row" style="text-align:right;">
                    <input type="hidden" id="zzimg" name="zzimg" value='${rmHelper.returnParams.licensepath}'/>
			    		<%-- <webTag:Button name="exportBranchInfo" type="button" onClick="downimg();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="营业执照查看"/>  <!--  by zhudongdong 20190606 -->
			    		<webTag:Button name="exportBranchInfo" type="button" onClick="uploadimg();" classCss="btn btn-danger" iClassCss="icon-download-alt icon-white" value="【上传/修改】营业执照"/>  <!--  by zhudongdong 20190606 --> --%>
			    		<button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
			    		<a class="btn btn-danger" href="<%=basePath %>/Manage/Branch/toQueryBranch.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->
					
				  </fieldset>	
				</form>
			</div>
			<!-- 增加面板 end -->
			<div class="zeoBottomH90"></div>
		</div>
	</body>

</html>
