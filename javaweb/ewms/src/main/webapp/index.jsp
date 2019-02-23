<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>
nsert title herensert title herensert title herensert title here

<a href="<%=path %>/system/activitiManage/modelListTest.htm">model管理</a><br>
<a href="<%=path %>/system/processManage/findProcessingByperson.htm">流程监控</a><br>
<a href="<%=path %>/system/processManage/findProcessingByperson.htm?userId=1">我的待办(人员1)</a><br>
<a href="<%=path %>/system/processManage/findProcessingByperson.htm?userId=2">我的待办(人员2)</a><br>
<a href="<%=path %>/system/processManage/findProcessedByperson.htm">我的已办</a><br>
<a href="<%=path %>/system/processManage/processManage.htm">流程管理</a><br>
<a href="javascript:void(0);" onclick="location.href='<%=path %>/system/activitiListener/deletePi.htm?piid=115023'">删除流程实例</a><br>
<%--<a href="<%=path %>/model/create.htm?name=test10&key=test10&description=testModel">创建流程图</a><br>--%>
<br>
<a href="javascript:void(0);"
   onclick="location.href='<%=path %>/system/activitiListener/setAssigneeTask.htm?taskId=392502'">另派</a><br>
<a href="javascript:void(0);"
   onclick="location.href='<%=path %>/system/activitiListener/getHistoryImage.htm?taskId=62502'">查看流转历史</a><br>
<image src='<%=path %>/system/activitiListener/getHistoryImage.htm?taskId=132575'></image>
<hr>
<script type="text/javascript">
    function clickFindL() {
        /*alert("----");
        var taskId = $("#taskId").val();
        alert(taskId);*/
        $.post("<%=path %>/system/activitiListener/findOutComeListByTaskId.htm", {'taskId': 387502},
            function (data) {
                $(data).each(function (index, obj) {
                    $("#butList").append("<a href='' onclick=\"<%=path %>/system/activitiListener/completeMyPersonalTask.josn?outcome=" + obj.name + "\">" + obj.name + "</a>");
                });
            }, "JSON");
    }
</script>
</body>
<script type="text/javascript" src="<%=path %>/js/jquery.js"/>
</html>