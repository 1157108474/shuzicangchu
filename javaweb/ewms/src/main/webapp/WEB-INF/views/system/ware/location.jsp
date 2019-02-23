<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>库房库区管理</title>
    <link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="../../plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css">

    <style type="text/css">
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
</head>
<body>

<div class="admin-main">

    <div style="margin-left: 5px;">
        <input id="pre" value="${pre}" type="hidden">
        <input id="parentId" value="${parentId}" type="hidden">
        <input id="parentCode" value="${parentCode}" type="hidden">
        <input id="ztId" value="${ztid}" type="hidden">
        <div class="layui-form-item bill-search">
            <label class="layui-form-label">名称：</label>
            <input type="text" id="name">
            <button class="layui-btn layui-btn-warm bill-td-button" id="search">查询</button>
        </div>
        <div style="margin-left: 10px;">
            <table id="locationList" lay-filter="locationList"></table>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../plugins/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../../js/ewms.js"></script>
<script type="text/javascript" src="../../js/system/ware/location.js"></script>
</html>