<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>完成任务信息</title>
    <link rel="stylesheet" href="<%=path %>/plugins/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="admin-main">
    <fieldset class="layui-elem-field" style="margin-left: 10px;margin-right: 10px;">
    </fieldset>
    <input type="hidden"  class="layui-input " value="${taskId}" name="taskId" id="taskId">
    <div  style="margin:-15px 10px;height:100%;"  >
        <div style="float:left;width:100%;height:100%;">
            <table  id="processComplete" lay-filter="processComplete"></table>
        </div>
    </div>
</div>
</body>
<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript" src="<%=path %>/plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path %>/js/system/act/processComplete.js"></script>

</html>