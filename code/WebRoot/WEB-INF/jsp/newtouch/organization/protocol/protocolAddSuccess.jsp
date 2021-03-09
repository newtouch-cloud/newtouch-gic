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
		<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasecss.jsp" %>
		<%@ include file="/WEB-INF/jsp/ca/cacore/pub/jvbasejs.jsp" %>
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="/WEB-INF/jsp/ca/cacore/pub/backPageHelper.jsp" flush="true"/>
		<script type="text/javascript" >
   
//验证 生效日期-终止日期  前后顺序正确性校验
	    jQuery.validator.addMethod("checkDate1",function(value,element){
    	var startdate=$("#startdate").val();
		var enddate=$("#enddate").val();
		var flag = true;
   		if(!isUndefined(startdate)&&!isUndefined(enddate)){
   			if(startdate>enddate){
   				flag=false;
   			}
   		}
    	if(!flag){
    		return false;
    	}else{
    		$("label:contains('')").remove();
    		return true;
    	}
   	},"协议生效止期必须大于、等于协议生效起期"); 
	//验证 生效日期-终协议签订日期  前后顺序正确性校验
	 jQuery.validator.addMethod("checkDate2",function(value,element){
    	var startdate=$("#startdate").val();
		var dateofsign=$("#dateofsign").val();
		var flag = true;
   		if(!isUndefined(startdate)&&!isUndefined(dateofsign)){
   			if(dateofsign>startdate){
   				flag=false;
   			}
   		}
    	if(!flag){
    		return false;
    	}else{
    		$("label:contains('')").remove();
    		return true;
    	}
   },"协议生效起期必须大于、等于协议签订日期"); 
	function isUndefined(paraValue){
       if(paraValue==null||paraValue==undefined||paraValue=="") return true;
       return false;
   }
	 
	 jQuery.validator.addMethod("checkHasCHN1", function(agreement_no, element) {
		 var reg = /[\u4E00-\u9FA5]/g;
		   if(reg.test(agreement_no)){
		    return false;
		   }else{
		    return true;
		   }
	}, "不能包含中文字符");
	 //检验保险公司协议号是否重复
	 jQuery.validator.addMethod("checkAgreement_no", function(value,element) {
		 var agreement_no  = $("#agreement_no").val();
	 	 var branch_id = $("#branch_id").val();
	 	 var a = false; 
	 	$.ajax({
	 		url:"<%=basePath%>/organization/protocol/toCheckAgreement_no.do",
	 		dataType:"json",
	 		data:{"agreement_no":agreement_no,"branch_id":branch_id},
	 		async:false,
	 		type:"post",
	 		success:function(data){
	 			if(data=="true"){
					 a=true;
				 }
	 		}
	 	});
		return a;
	}, "协议号已被使用！")
  $(document).ready(function() {
		 $("#queryForm").validate({
			 rules:{
			 agreement_no:{//协议号 19 非空
					 required:true,
					 checkHasCHN1:[],
					 checkAgreement_no:[],
					 maxlength:19
				 },
	        branch_name:{// 使用机构name 200
					 required:true,
				 }, 
				  startdate:{// 生效日期   从 Date 非空
					 required:true,
					  checkDate1:[],
					  checkDate2:[] 
				 },
				 enddate:{// 终止日期  从 Date 非空
					 required:true ,
					 checkDate1:[] 
				 },
				 protocol_category:{
					 required:true,
				 },
				 contract_type:{
					 required:true,
				 },
				 sign_type:{
					 required:true,
				 },
				 party_a:{
					 required:true,
				 },
				 party_b:{
					 required:true,
				 },
				 status:{
					 required:true,
				 },
				 dateofsign:{
					 required:true,
					 checkDate2:[]
				 }
				 <%-- dateofsign:{//协议签订日期 Date 非空
					 required:true ,
					 checkDate2:[]
				 },--%>
			 },
		  onkeyup:false
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
    });
		</script>
		<script type="text/javascript">
		 $(function(){
			 	$("#st").delegate("td","click",function(){
					var str = $(this).text();
					$("#code").next("label[class='error']").remove();
					$("#code").val(str);
					$("#code").focus();
					$("#st tbody").empty();
				}); 
			 	 //鼠标移到到那一行改变颜色
			 	$("#st").delegate("tr","mouseover",function(){
					$(this).addClass("over");
				}); 
			 	 //鼠标移出到那一行改变颜色
				$("#st").delegate("tr","mouseout",function(){
					$(this).removeClass("over");
				});  
			 	$("#code").keyup(function(){
				var code = $("#code").val();
				if(code==null||code==""){
					$("#st tbody").empty();
					return;
				} 
			 	var emp_id; 
			 	var t_name = 'sales_id';//要查询的字段
			 	var table_name = 'smis_sales';//要查询的表名
			  	var reg = code+'%'; //定义匹配规则(从前至后匹配)
					$.ajax({
			     		url : "<%=basePath %>/Manage/FuncPanel/getSechInfo.do",
			     		type : "post",
			     		async : false,
			     		data : {
			     			"emp_id" : emp_id,
			     			"keyWord" : code,
			     			"t_name" : t_name,
			     			"table_name" : table_name,
			     			"reg" : reg
			     		},
			     		dataType:"json",
			     		success : function(data) {
			     			$("#st tbody").empty();
			     			var tbodyHtml="";
			     			var arr = new Array();
			     			$.each(data,function(index,comment){
			     				tbodyHtml+='<tr>'
		     								+'<td id="newTd">'+comment.seachMsg+'</td>'
			     							+'</tr>';
			     				arr[index] = comment.seachMsg;
			     			}); 
		     				$("#st tbody").append(tbodyHtml);
		     				if(arr.length == 1 && code == arr[0] ){  //当输入完整时去掉带出的那条数据
			     				$("#st tbody").empty();
			     			}
		     				if(tbodyHtml==""){
		     					$("#promptMessage").css({ display: "none" });
		     				}else{
			     				$("#promptMessage").css({ display: "block" });
		     				}
			     		}
					});
		 	}); 
		}); 
		</script> 
		   <script type="text/javascript">
		   $(document).ready(function(){
			 //加载合同类型，协议类型
		          var positionList;
		          $.ajax({
		                type: "GET",
		                url: "/CACore/organization/Protocol/treeList.do",
		                async : true,
		                dataType: "json",
		                success: function (respData) {
		                    positionList = respData.retList;
		                    var arr = [];
		                    arr.push('<option value="">---请选择---</option>')
		                    if(respData.retList){
		                        for (var i = 0; i < positionList.length; i++) {
		                            var item = positionList[i];
		                            if(${rmHelper.returnParams.protocol_category}==item.proCode){
		                              arr.push('<option value="' + item.proCode + '" selected>' + item.proName + '</option>');
		                            }else{
		                              arr.push('<option value="' + item.proCode + '">' + item.proName + '</option>');
		                            }
		                        };
		                        $("#protocol_category").html(arr.join('\n'));
		                        var rankList = [];
		                      for (var i = 0; i < positionList.length; i++) {
		                          var item = positionList[i];
		                          if (item.proCode == ${rmHelper.returnParams.protocol_category}) {
		                              rankList = item.contractList;
		                         break;
		                    }
		                      }
		                      var arr = [];
		                      arr.push('<option value="">---请选择---</option>');
		                      for (var j = 0; j < rankList.length; j++) {
		                          var item = rankList[j];
		                          if(${rmHelper.returnParams.contract_type}==item.conCode){
		                            arr.push('<option value="' + item.conCode + '" selected>' + item.conName + '</option>');
		                          }else{
		                          arr.push('<option value="' + item.conCode + '">' + item.conName + '</option>');
		                          }
		                      }
		                      $('#contract_type').html(arr.join(''));
		                    }
		                },
		                error: function (request, status, errorTrown) {
		                    alert('协议类型加载失败', {icon: 5});
		                }
		            });

		           $("#protocol_category").change(function () {
		                  var selectPositionCode=$(this).val();
		                  var rankList = [];
		                  for (var i = 0; i < positionList.length; i++) {
		                      var item = positionList[i];
		                      if (item.proCode == selectPositionCode) {
		                          rankList = item.contractList;
		                     break;
		                }
		                  }
		                  var arr = [];
		                  arr.push('<option value="">---请选择---</option>');
		                  for (var j = 0; j < rankList.length; j++) {
		                      var item = rankList[j];
		                      arr.push('<option value="' + item.conCode + '">' + item.conName + '</option>');
		                  }
		                  $('#contract_type').html(arr.join(''))
		            });
		    	 //加载签订类型数据
		    	 $.ajax({
		             type: "POST",
		             url: "/CACore/organization/Protocol/proSignType.do",
		             async : true,
		             dataType: "json",
		             success: function (respData) {
		                 positionList = respData.retList;
		                 var arr = [];
		                 arr.push('<option value="">---请选择---</option>')
		                 if(respData.retList){
		                     for (var i = 0; i < positionList.length; i++) {
		                         var item = positionList[i];
		                         if(${rmHelper.returnParams.sign_type}==item.code){
		                        	 arr.push('<option value="' + item.code + '" selected>' + item.name + '</option>');
		                         }else{
		                             arr.push('<option value="' + item.code + '">' + item.name + '</option>');
		                         }
		                     }
		                 }
		                 $("#sign_type").html(arr.join('\n'))
		             },
		             error: function (request, status, errorTrown) {
		                 alert('签订类型加载失败', {icon: 5});
		             }
		         });
		    	 
		    	 if(${rmHelper.returnParams.protocol_category}=='2'){
		     		document.getElementById("push_code").disabled=false;
		     		document.getElementById("credit_code").disabled=false;
		     		document.getElementById("isconsult").disabled=false;
		     		document.getElementById("bank_name").disabled=false;
		     		document.getElementById("bank_code").disabled=false;
		     	}else{
		     		document.getElementById("push_code").disabled=true;
		     		document.getElementById("credit_code").disabled=true;
		     		document.getElementById("isconsult").disabled=true;
		     		document.getElementById("bank_name").disabled=true;
		     		document.getElementById("bank_code").disabled=true;
		     	}
		    });
</script>
	</head>
	<body style="height: 750px">
		<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i> 协议管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>协议管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i> 新增</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="queryForm" name="queryForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/organization/Protocol/addProtocol.do" method="POST" enctype="multipart/form-data">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<center><image id="dialog_img"></image></br><span>${rmHelper.msgStr}</span></center>
					</div>
					<!-- 路径--> 
					<webTag:HiddenInputTag id="path" name="path" value='<%=basePath %>'/>
					<!-- value为后台返回的 true 或者false-->
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="状态位"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="状态信息"/>	
					 <!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
					<fieldset>
					<div class="row" style="margin-left:-30px;margin-right:-40px">
				
    					 <div class="control-group span4">    
							<label class="control-label" for="protocol_category">协议类型</label> <div class="controls"> 
							<select  id="protocol_category" name="protocol_category" class="input-medium null"></select>
							</div></div>
    					<div class="control-group span4">    
							<label class="control-label" for="contract_type">合同类型</label> <div class="controls"> 
							<select  id="contract_type" name="contract_type" class="input-medium null"></select>
							</div>
						</div> 
						 <jsp:include page="/WEB-INF/jsp/ca/cacore/util/NewBranchTree.jsp" flush="true"/>
					</div>
					
					<div class="row" style="margin-left:-30px;margin-right:-40px">
				  		<webTag:Text id="party_a"  name="party_a"  value='${rmHelper.returnParams.party_a}' displayLable="甲方" />
 						<webTag:Text id="party_b"  name="party_b"  value='${rmHelper.returnParams.party_b}' displayLable="乙方" />
						<webTag:Date id="dateofsign"  name="dateofsign" value='${rmHelper.returnParams.dateofsign}'   displayLable="协议签订日期" dateFormat="yyyy-MM-dd" isdisplay="true"/>           
					</div>
					<div class="row" style="margin-left:-30px;margin-right:-40px">     
						<webTag:Date id="startdate"   name="startdate"  value='${rmHelper.returnParams.startdate}'    displayLable="协议生效起期"    dateFormat="yyyy-MM-dd"   isdisplay="true"/>
						<webTag:Date id="enddate"     name="enddate"    value='${rmHelper.returnParams.enddate}'      displayLable="协议生效止期"    dateFormat="yyyy-MM-dd"   isdisplay="true"/> 
						 <div class="control-group span4">    
							<label class="control-label" for="sign_type">签订类型</label> <div class="controls"> 
							<select  id="sign_type" name="sign_type" class="input-medium null"></select>
							</div>
						</div> 	
					</div>
				<!-- 	<div class="row" style="margin-left:-30px;margin-right:-40px"> 
					<div class="control-group span4">    
							<label class="control-label" for="status">协议状态</label> <div class="controls"> 
							<select  id="status" name="status" class="input-medium null"></select>
							</div></div> 
					
					 	
						
					<webTag:Text id="agreement_no"  name="agreement_no"  value='${rmHelper.returnParams.agreement_no}' displayLable="协议号" />
						 
					</div>-->
					<div class="row" style="margin-left:-30px;margin-right:-40px">  
						<webTag:Text id="party_c"  name="party_c"  value='${rmHelper.returnParams.party_c}' displayLable="丙方" /> 
						<jsp:include page="/WEB-INF/jsp/ca/cacore/util/protocolTree.jsp" flush="true"/>
 						<webTag:Text id="credit_code"  name="credit_code"  value='${rmHelper.returnParams.credit_code}' displayLable="代理社会统一信用码" />
					</div>
					
					<div class="row" style="margin-left:-30px;margin-right:-40px">   
						<div class="control-group span4">
							<label class="control-label" for="work_nature">汽车信息咨询</label>
							<div class='controls'>
								<select class="input-medium null" id="isconsult"
									name="isconsult">
									<option value="">---请选择---</option>
									<option value='是'
										<c:if test="${rmHelper.returnParams.isconsult=='是'}"> selected="selected"</c:if>>是</option>
									<option value='否'
										<c:if test="${rmHelper.returnParams.isconsult=='否'}"> selected="selected"</c:if>>否</option>
								</select>
							</div>
						</div>
						<webTag:Text id="bank_name"  name="bank_name"  value='${rmHelper.returnParams.bank_name}' displayLable="代理开户行" />
 						<webTag:Text id="bank_code"  name="bank_code"  value='${rmHelper.returnParams.bank_code}' displayLable="代理银行账号" />
					</div>
					<div class="row" style="margin-left:-30px;margin-right:-40px">
						<webTag:Text id="push_code"  name="push_code"  value='${rmHelper.returnParams.push_code}' displayLable="代理推荐维修码" />
						<webTag:Text id="enterprise_type"  name="enterprise_type"  value='${rmHelper.returnParams.enterprise_type}' displayLable="代理企业类型" />
						<webTag:Text id="amount"  name="amount"  value='${rmHelper.returnParams.amount}' displayLable="合同金额" />
					</div>
					<div class="row" style="margin-left:-30px;margin-right:-40px">
						<webTag:TextareaTag id="remarks"  name="remarks"  value='${rmHelper.returnParams.remarks}' displayLable="备注" rows="5"/>					
					</div>
					
					
					
				    <div class="row" style="text-align:right;">
					   <!--  <button type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button> -->
					    <a class="btn btn-danger" href="<%=basePath %>/organization/Protocol/goProtocolList.do" style='text-decoration:none;'><i class="icon-share-alt icon-white"></i>返回</a>
					</div><!-- /.row -->				
				  </fieldset>	
				</form>
			</div>
			<!-- 增加面板 end -->
		</div>
		<!-- 		底部高度填充块 -->
		<div class="zeoBottom"></div>
		<!-- 		底部高度填充块 结束-->
	</body>
	<script type="text/javascript">
    $(document).ready(function() {
    var a= $(window.parent.document).find("#sidebar").height();
    a=a+150;
	$(window.parent.document).find("#ffame").css("height",""+a+"px");
    $("#protocol_category").change(function (){
    	var selectPositionCode=$(this).val();
    	if(selectPositionCode=='2'){
    		document.getElementById("push_code").disabled=false;
    		document.getElementById("credit_code").disabled=false;
    		document.getElementById("isconsult").disabled=false;
    		document.getElementById("bank_name").disabled=false;
    		document.getElementById("bank_code").disabled=false;

    	}else{
    		document.getElementById("push_code").disabled=true;
    		document.getElementById("credit_code").disabled=true;
    		document.getElementById("isconsult").disabled=true;
    		document.getElementById("bank_name").disabled=true;
    		document.getElementById("bank_code").disabled=true;
    	}
    	
    });
	
    });
    </script>
  
</html>
