<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>


    <title>ϵͳ��־</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>

</head>
<body>

<div class="admin-main">

    <blockquote class="layui-elem-quote" style="margin-left: 20px;margin-top: 10px;height:28px">
        <div style="float: left">
            <a href="javascript:void(0);" class="layui-btn layui-btn-danger delete" id="delete">ɾ��</a>
        </div>
        <div style="float: right">
            ��־����:
            <select name="logType" id="logType" lay-verify="required" style="font-family: 'Microsoft YaHei'">
                <option value="0">ϵͳ��־</option>
                <option value="1">ҵ����־</option>
            </select>
            ��־ʱ��:
            <input type="text" class="mini-textbox startTime" id="startTime"
                   name="startTime">
            ��
            <input type="text" class="mini-textbox endTime" id="endTime"
                   name="endTime">
            <button class="layui-btn" lay-submit lay-filter="formSubmit">������ѯ</button>
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