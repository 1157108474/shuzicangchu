<%@ page language="java" pageEncoding="GBK" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>任务管理</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="admin-main" style="height: 80%" >
    <blockquote class="layui-elem-quote" style="margin-left: 10px;margin-top: 5px;height: 28px">
        <div style="float: left">
        <a href="javascript:void(0)" class="layui-btn" id="add">新增</a>
        <a href="javascript:void(0)" class="layui-btn" id="edit">修改</a>
        <a href="javascript:void(0)" class="layui-btn" id="delete">删除</a>
            </div>
        <div style="float: right">

            接口任务名称:
            <input id="name"  class="mini-textbox"   />
            <a href="javascript:void(0)" class="layui-btn" id="search">查找</a>
        </div>
    </blockquote>

    <div style="margin-left: 10px;">
        <table id="taskList" lay-filter="taskList" style="height: 80%"></table>
    </div>
</div>
</body>
<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/system/task/manageInfTask.js"></script>
<script type="text/javascript" src="../../js/ewms.js"></script>
</html>