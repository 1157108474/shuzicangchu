<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>


    <title>�ӿ���־</title>
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
        <a href="javascript:void(0);" class="layui-btn layui-btn-danger delete" id="delete">ɾ��</a>
        </div>
        <div style="float: right">
            �ӿ�����:
            <input name="infTaskdetailn" id="infTaskdetailn" autocomplete="on"
                   class="mini-textbox infTaskdetailn" type="text">
            ͬ�����:
            <select name="syncResult" id="syncResult" style="font-family: 'Microsoft YaHei'">
                <option value="δͬ��">δͬ��</option>
                <option value="ͬ���ɹ�">ͬ���ɹ�</option>
                <option value="ͬ��ʧ��">ͬ��ʧ��</option>
                <option value="ͬ���쳣">ͬ���쳣</option>
            </select>
            ͬ��ʱ��:
            <input type="text" class="mini-textbox startTime"  id="startTime"
             id="startTime"      name="startTime">
            ��
            <input type="text" class="mini-textbox endTime"  id="endTime"
             id="endTime"      name="endTime">
            <button class="layui-btn" lay-submit lay-filter="formSubmit">������ѯ</button>
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