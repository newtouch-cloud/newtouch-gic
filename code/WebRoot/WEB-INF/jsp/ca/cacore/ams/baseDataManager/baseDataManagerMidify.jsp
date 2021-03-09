<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/compent/jstl/webTag-Bootstrap.tld" prefix="webTag"%>
<%@page import="com.newtouch.core.returnmsg.ReturnMsg"%>
<%@page import="com.newtouch.component.c11assistant.JspHelper"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<!DOCTYPE html>
<html>
<head>
        <%@ include file="../../pub/jvbasecss.jsp" %>
		<%@ include file="../../pub/jvbasejs.jsp" %>
		<!-- 回跳、收缩及上跳 -->
		<jsp:include page="../../pub/backPageHelper.jsp" flush="true"/>
		<script type="text/javascript" src="<%=basePath%>/compent/default/js/attachmentModify.js"></script>
</head>
<script type="text/javascript">

   
   function addOneData(){
	   var numdiv=$("#row").nextAll().length;
	   $("#mainForm").append("<div id='row"+(numdiv+1)+"' class='row' align='center'>数据编码   <input type='text' id='code"+(numdiv+1)+"' name='code'/>数据名称   <input type='text' id='name"+(numdiv+1)+"' name='name'/></div>");
	  
   }

</script>
<body>

<div class="container-fluid" >
			<!-- 面包屑导航  start -->
			<div class="dreadcount">
			    <span class=mrl14><i class="icon-list icon-red"></i>系统管理 </span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>数据字典管理</span><span class="divider">/</span>
			    <span><i class="icon-list icon-red"></i>修改</span><span class="divider">/</span>
			</div>
			<!-- 面包屑导航  end -->
			
			<!--增加面板 start -->
			<div class="row-fluid">
				<form id="mainForm" name="mainForm" class="form-horizontal alert alert-info fade in span12" action="<%=basePath %>/AMS/BaseDataManagerController/saveModifyBaseData.do"  enctype="multipart/form-data"  method="POST">
					<!-- 提示信息标签 -->
					<div id="dialog" title="提示信息" style="display:none">
						<image id="dialog_img" >${rmHelper.msgStr}</image>
					</div  >
					<webTag:HiddenInputTag id="result_flag" name="result_flag" value="${rmHelper.successflag}" displayLable="msg状态"/>
					<webTag:HiddenInputTag id="msg" name="msg" value="${rmHelper.msgStr}" displayLable="msg信息"/>
					<webTag:HiddenInputTag id="path" name="path" value="<%=basePath %>"/>					
					<!-- 标志位用来存储异步请求的成功与否，以便验证时获取异步请求的结果 -->
				    <webTag:HiddenInputTag name="flag" id="flag"></webTag:HiddenInputTag>
				    
				    <div class="row" align="right">
					     <a id="addone" class="btn btn-danger" onclick="addOneData();"><i class="icon-plus icon-white"></i>增加一条</a>
						 <button id="submitBtn" type="submit" class="btn btn-danger"><i class="icon-inbox icon-white"></i>保存</button>
			             <a class="btn btn-danger" href="<%=basePath %>/AMS/BaseDataManagerController/queryBaseData.do"><i class="icon-share-alt icon-white"></i>返回</a>
					</div>				
		
			
			<c:forEach var="sm" items="${rmHelper.returnMsg.dataList}" varStatus="index">
                           <input type="hidden" id="id" name="id" value='${sm.id}'>
                           <input type="hidden" id="typeid" name="typeid" value='${sm.typeid}'>
				    <c:if test="${sm.isleaf=='0'}">
					<div  id="row" class="row" align="center">
					    类型编码   <input type="text" id="typecode" name="typecode"  value='${sm.typecode}' />
					    类型名称   <input type="text" id="namez" name="name"  value='${sm.name}'  />
					</div>
				    </c:if>
				    <c:if test="${sm.isleaf=='1'}">
					<div  id="row${index.index}" class="row" align="center">
                                                     
					   数据编码   <input type="text" id="code${index.index}" name="code"  value='${sm.code}'  />
					   数据名称   <input type="text" id="name${index.index}" name="name"  value='${sm.name}'  />
					</div>
				    </c:if>	
			</c:forEach>
					
					
				</form>
			</div>
			<!-- 增加面板 end -->			
        </div>
<!-- 		底部高度填充块 -->
		<div class="zeoBottomH90"></div>
<!-- 		底部高度填充块 结束-->



</body>
</html>