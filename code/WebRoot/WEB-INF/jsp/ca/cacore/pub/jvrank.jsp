<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.newtouch.core.returnmsg.ReturnMsg"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>
<script type="text/javascript">
	function getRanks(){
		var basic_version_id=$("#basic_version_id").val();
		if(basic_version_id==null || basic_version_id==""){
			return;
		}
		$("#rank_id").empty();
		$("#rank_id").append("<option value=''>---请选择---</option>");
		$.ajax({
			type:"post",
			async:false,
			url:"<%=basePath %>/SalesRankChange/getRanks.do",
			data:{"basic_version_id":basic_version_id},
			dataType:"json",
			success:function(data){
				$.each(data,function(index,comment){
					var rank_name=comment.name;
					var rank_id=comment.id;
					$("#rank_id").append("<option value='"+rank_id+"'>"+rank_name+"</option>");
				});
				var rank_id = $("#rank_id_hidden").val();
				if(rank_id!=null && rank_id!=""){
					$("#rank_id").val(rank_id);
				}
			}
		});
	}
	
	$(function() {
		//通过基本法的值动态改变职级的备选值 
		$("#basic_version_id").change(function(){
			//先清空rank_id_hidden的值 
			$("#rank_id_hidden").val("");
			//再清空rank_id的值 ，目的在于：当选择空基本法时，职级也变为空  
			$("#rank_id").val("");
			$("#rank_id").empty();
			$("#rank_id").append("<option value=''>---请选择---</option>");
			//调用getRanks()方法 
			getRanks();
		});
		$("#rank_id").change(function(){
			//把所选职级值放在rank_id_hidden隐藏域里 
			$("#rank_id_hidden").val($("#rank_id").val());
		});
		getRanks();
	});
</script>