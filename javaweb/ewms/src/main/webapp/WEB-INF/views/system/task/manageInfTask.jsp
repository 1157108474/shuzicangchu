<%@ page language="java" pageEncoding="GBK" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>�������</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="admin-main" style="height: 80%" >
    <blockquote class="layui-elem-quote" style="margin-left: 10px;margin-top: 5px;height: 28px">
        <div style="float: left">
        <a href="javascript:void(0)" class="layui-btn" id="add">����</a>
        <a href="javascript:void(0)" class="layui-btn" id="edit">�޸�</a>
        <a href="javascript:void(0)" class="layui-btn" id="delete">ɾ��</a>
            </div>
        <div style="float: right">

            �ӿ���������:
            <input id="name"  class="mini-textbox"   />
            <a href="javascript:void(0)" class="layui-btn" id="search">����</a>
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