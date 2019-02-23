<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>


    <title>接口日志</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <style type="text/css">
        .test{
            line-height: inherit;!important;
        }

    </style>
</head>
<body>

<div class="admin-main">

    <blockquote class="layui-elem-quote" style="margin-left: 20px;margin-top: 10px;height: 28px">
        <div style="float: left">
        <a href="javascript:void(0);" class="layui-btn layui-btn-danger delete" id="delete">删除</a>
        </div>
        <div style="float: right">
            接口名称:
            <input name="infTaskdetailn" id="infTaskdetailn" autocomplete="on"
                   class="mini-textbox infTaskdetailn" type="text">
            同步结果:
            <select name="syncResult" id="syncResult" style="font-family: 'Microsoft YaHei'">
                <option value="未同步">未同步</option>
                <option value="同步成功">同步成功</option>
                <option value="同步失败">同步失败</option>
                <option value="同步异常">同步异常</option>
            </select>
            同步时间:
            <input type="text" class="mini-textbox startTime"  id="startTime"
             id="startTime"      name="startTime">
            至
            <input type="text" class="mini-textbox endTime"  id="endTime"
             id="endTime"      name="endTime">
            <button class="layui-btn" lay-submit lay-filter="formSubmit">立即查询</button>
        </div>
    </blockquote>
    <div style="clear: both;"></div>
    <div style="margin-left: 20px;">

        <table id="taskLogList" lay-filter="taskLogList"></table>
    </div>
</div>
</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="../../js/system/taskLog/manageTaskLog.js"></script>

</html>