<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>


    <title>系统日志</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>

</head>
<body>

<div class="admin-main">

    <blockquote class="layui-elem-quote" style="margin-left: 20px;margin-top: 10px;height:28px">
        <div style="float: left">
            <a href="javascript:void(0);" class="layui-btn layui-btn-danger delete" id="delete">删除</a>
        </div>
        <div style="float: right">
            日志类型:
            <select name="logType" id="logType" lay-verify="required" style="font-family: 'Microsoft YaHei'">
                <option value="0">系统日志</option>
                <option value="1">业务日志</option>
            </select>
            日志时间:
            <input type="text" class="mini-textbox startTime" id="startTime"
                   name="startTime">
            至
            <input type="text" class="mini-textbox endTime" id="endTime"
                   name="endTime">
            <button class="layui-btn" lay-submit lay-filter="formSubmit">立即查询</button>
        </div>
    </blockquote>


    <div style="clear: both;"></div>


    <div style="margin-left: 20px;">

        <table id="logList" lay-filter="logList"></table>
    </div>
</div>
</body>


<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="../../js/system/log/manageLog.js"></script>

</html>