<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.newtouch.component.c11assistant.ServletHelper" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "";
%>
<script type="text/javascript">

    $(document).ready(function () {
        $.ajax({
            url: "<%=basePath %>/Branch/queryProvince.do?rqstType=AJAX",
            type: "post",
            async: true,
            dataType: "json",
            success: function (data) {
                if (data.retList != null) {
                    if (data.retList.length != 0) {
                        var province = $("#province_code option:selected").val();
                        $("#province_code option").remove();
                        $("#province_code").append("<option value=''>--请选择省--</option>");
                        for (var i = 0; i < data.retList.length; i++) {
                            if (data.retList[i].province_code == province) {
                                $("#province_code").append("<option selected='selected' value=" + data.retList[i].province_code + ">" + data.retList[i].province + "</option>");
                            } else {
                                $("#province_code").append("<option value=" + data.retList[i].province_code + ">" + data.retList[i].province + "</option>");
                            }
                        }
                    }
                }
            },
            error:

                function () {
                    alert('省查询出错');
                }
        });

        $.ajax({
            type: "post",
            url: "<%=basePath %>/Branch/queryCity.do?parent_code=" + $("#province_code").val() + "&rqstType=AJAX",
            async: true,
            dataType: "json",
            success: function (data) {
                var city = $("#city_code").val()
                $("#city_code option").remove();
                $("#city_code").append("<option value=''>--请选择市--</option>");
                if (data.retList != null) {
                    if (data.retList.length != 0) {
                        for (var i = 0; i < data.retList.length; i++) {
                            if (data.retList[i].city_code == city) {
                                $("#city_code").append("<option selected='selected' value=" + data.retList[i].city_code + ">" + data.retList[i].city + "</option>");
                            } else {
                                $("#city_code").append("<option value=" + data.retList[i].city_code + ">" + data.retList[i].city + "</option>");
                            }
                        }
                    }
                }
            },
            error: function () {
                alert('error');
            }
        });
        $.ajax({
            type: "post",
            url: "<%=basePath %>/Branch/queryArea.do?parent_code=" + $("#city_code").val() + "&rqstType=AJAX",
            async: true,
            dataType: "json",
            success: function (data) {
                var area = $("#area_code").val()
                $("#area_code option").remove();
                $("#area_code").append("<option value=''>--请选择县--</option>");
                if (data.retList != null) {
                    if (data.retList.length != 0) {
                        for (var i = 0; i < data.retList.length; i++) {
                            if (data.retList[i].area_code == area) {
                                $("#area_code").append("<option selected='selected' value=" + data.retList[i].area_code + ">" + data.retList[i].area + "</option>");
                            } else {
                                $("#area_code").append("<option value=" + data.retList[i].area_code + ">" + data.retList[i].area + "</option>");
                            }
                        }
                    }
                }
            },
            error: function () {
                alert('error');
            }
        });
        //根据省市县的code 设置这三个下拉框的选中。
        //	$("#province").attr("value","${province}");
        /* debugger; */
        //$("#province").find("option:contains('${province}')").attr("selected",true)
        $("#province_code").change(function () {
            $("#city_code option").remove();
            $.ajax({
                type: "post",
                url: "<%=basePath %>/Branch/queryCity.do?parent_code=" + $("#province_code").val() + "&rqstType=AJAX",
                async: true,
                dataType: "json",
                success: function (data) {
                    if (data.retList != null) {
                        if (data.retList.length != 0) {
                            $("#city_code").append("<option value=''>--请选择市--</option>");
                            for (var i = 0; i < data.retList.length; i++) {
                                $("#city_code").append("<option value=" + data.retList[i].city_code + ">" + data.retList[i].city + "</option>");
                            }
                        }
                    }
                },
                error: function () {
                    alert('error');
                }
            });
            $.ajax({
                type: "post",
                url: "<%=basePath %>/Branch/queryArea.do?parent_code=" + $("#city_code").val() + "&rqstType=AJAX",
                async: true,
                dataType: "json",
                success: function (data) {
                    if (data.retList != null) {
                        if (data.retList.length != 0) {
                            $("#area_code").append("<option value=''>--请选择县--</option>");
                            for (var i = 0; i < data.list.length; i++) {
                                $("#area_code").append("<option value=" + data.retList[i].area_code + ">" + data.retList[i].area + "</option>");
                            }
                        }
                    }
                },
                error: function () {
                    alert('error');
                }
            });
        });

        $("#city_code").change(function () {
            $("#area_code option").remove();
            $("#area_code").append("<option value=''>--请选择县--</option>");
            $.ajax({
                type: "post",
                url: "<%=basePath %>/Branch/queryArea.do?parent_code=" + $("#city_code").val() + "&rqstType=AJAX",
                async: true,
                dataType: "json",
                success: function (data) {
                    if (data.retList != null) {
                        if (data.retList.length != 0) {
                            for (var i = 0; i < data.retList.length; i++) {
                                $("#area_code").append("<option value=" + data.retList[i].area_code + ">" + data.retList[i].area + "</option>");
                            }
                        }
                    }
                },
                error: function () {
                    alert('error');
                }
            });
        });
    })
    ;
</script>