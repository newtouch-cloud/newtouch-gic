<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
String pathjs = request.getContextPath();
String basePathjs = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathjs+"";
%>
<!-- fram start  -->
<script type="text/javascript" src="<%=basePathjs %>/compent/jquery/jquery-1.9.1.min.js"></script><!-- jQuery -->
<script type="text/javascript" src="<%=basePathjs %>/compent/base.js"></script><!-- jQuery -->
<!--  收缩 -->
<script type="text/javascript" src="<%=basePathjs%>/compent/charisma/js/xinzhijunyang-haupt.js"></script>
<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="<%=basePathjs %>/compent/html5shiv/html5shiv.js"></script>
<![endif]-->
<!-- fram plugins start-->
<script type="text/javascript" src="<%=basePathjs %>/compent/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/pagination/jquery.pagination.js"></script>
<!-- fileupload start-->

<!-- 保存完成提示框 -->
<script type="text/javascript" src="<%=basePathjs %>/compent/webstyle/js/jqueryui/ui/jquery-ui.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/common/dialog.js"></script>

<style>
	.my-dialog .ui-dialog-titlebar-close{
	display: none;
	}
</style> 

<!-- 数据校验 -->
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/jquery.metadata.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/newtouch-validate.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/idHelper.js"></script>
<!-- 重置按钮 by yanqiguang -->
<script type="text/javascript" > 
$(document).ready(function(){
$('#newreset').click(function(){
	 $(':input','#queryForm')
      .not(':button,:submit,:reset')   //将myform表单中input元素type为button、submit、reset、hidden排除
      .val('')  //将input元素的value设为空值
      .removeAttr('selected')
      .removeAttr('checked') 
      
       $(':input','#queryForms')
      .not(':button,:submit,:reset')   //将myform表单中input元素type为button、submit、reset、hidden排除
      .val('')  //将input元素的value设为空值
      .removeAttr('selected')
      .removeAttr('checked')
 });
});

var lastTime = new Date().getTime();
var currentTime = new Date().getTime();
var timeOut = 30*60*1000; //设置超时时间： 10分
var isChaoShi=false;
$(function(){
    /* 鼠标移动事件 */
    $(document).mouseover(function(){
        lastTime = new Date().getTime(); //更新操作时间
		
    });
}); 

function testTime(){
    currentTime = new Date().getTime(); //更新当前时间
    if(currentTime - lastTime > timeOut&&!isChaoShi&&$("#islogin").val()!='1'){ //判断是否超时
    	isChaoShi = true;
		$("#textshow2").text("您好，您已经超过三十分钟未对系统操作，请重新登录系统");
		ShowDiv1('MyDiv2','fade2');
    }
}

/* 定时器  间隔1秒检测是否长时间未操作页面  */
window.setInterval(testTime, 1000);

</script>
<style>
	.my-dialog .ui-dialog-titlebar-close{
	display: none;
	}
</style> 

<!-- 数据校验 -->
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/jquery.metadata.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/newtouch-validate.js"></script>
<script type="text/javascript" src="<%=basePathjs %>/compent/newtouch/util/idHelper.js"></script>