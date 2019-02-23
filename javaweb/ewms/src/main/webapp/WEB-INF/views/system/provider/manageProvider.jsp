<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>��Ӧ�̹���</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="admin-main">

    <blockquote class="layui-elem-quote" style="margin-left: 10px;margin-top: 5px;height: 28px">
        <div style="float: left">
            <a href="javascript:void(0);" class="layui-btn add" id="add">����</a>
            <a href="javascript:void(0);" class="layui-btn edit" id="edit">�޸�</a>
            <a href="javascript:void(0);" class="layui-btn layui-btn-danger delete" id="delete">ɾ��</a>
            <a href="javascript:void(0);" class="layui-btn layui-btn-danger lock" style="display: none" id="lock">����</a>
        </div>
        <div style="float: right">
            ��Ӧ������:
            <input name="name" id="name" autocomplete="on" class="mini-textbox"
                   style="width:150px;display:inline; " type="text">
            <button class="layui-btn" lay-submit lay-filter="formSubmit">������ѯ</button>
        </div>
    </blockquote>
    <div style="clear: both;"></div>
    <div style="margin-left: 10px;">

        <table id="providerList" lay-filter="providerList"></table>
    </div>
</div>
</body>


<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="../../js/system/provider/manageProvider.js"></script>

</html>