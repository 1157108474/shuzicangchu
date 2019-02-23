<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>供应商列表</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>

</head>
<body>
<div class="admin-main" style="margin-left: 20px;">
    <blockquote class="layui-elem-quote"
                style="z-index: 9999; position: fixed ! important; left: 0px; top: 0px;width: 1150px;">
        <form class="layui-form" action="">
            供应商名称:
            <input name="name" autocomplete="on" placeholder="供应商名称" class="mini-textbox" type="text">
            <input id="providerId" type="hidden" value="${providerId}">
            <button class="layui-btn" lay-submit lay-filter="formSubmit">查询</button>
            <a href="javascript:void(0);" class="layui-btn add" id="add">确认</a>
        </form>

    </blockquote>

    <div style="position: absolute; left: 0px; top: 30px;">
        <table id="providerList" lay-filter="providerList"></table>
    </div>
</div>
</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="../../js/system/provider/generalProvider.js"></script>

</html>