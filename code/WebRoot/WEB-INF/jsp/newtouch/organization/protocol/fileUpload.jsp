<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <html>
 <head>
     <title>文件上传</title>
     <script>
         <%--判断是否填写上传人并已经选择上传文件--%>
         function check() {
             var name = document.getElementById("name").value();
             var name = document.getElementById("file").value();
             if(name==""){
                 alert("请填写上传人");
                 return false;
             }
             if(file.length==0||file==""){
                 alert("请选择上传文件");
                 return false;
             }
             return true;
         }
     </script>
 </head>
 <body>
 <%--enctype="multipart/form-data"采用二进制流处理表单数据--%>
 <form action="${pageContext.request.contextPath}/fileUpload.action" method="post" enctype="multipart/form-data" onsubmit="return check()">
     上传人：<input id="name" type="text" name="name"/><br/>
     请选择文件：<input id="file" type="file" name="uploadfile" multiple="multiple"/><br/>
     <%--multiple属性选择多个文件上传--%>
     <input type="submit" value="文件上传" />
 </form>
 </body>
 </html>