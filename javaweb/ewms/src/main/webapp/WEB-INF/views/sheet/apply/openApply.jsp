<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>申领单号</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="admin-main">

    <div style="margin-left: 10px;">
        <input type="hidden" id="ztId" value="${ztId}">

        <div class="layui-form-item bill-search layui-elem-quote">
            <label class="layui-form-label">申领单号：</label>
            <input type="text" class="mini-textbox" id="code" >
            <button class="layui-btn layui-btn-warm bill-td-button" id="search">查询</button>
        </div>
        <div >
            <table id="applyList" lay-filter="applyList"></table>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/apply/openApply.js"></script>
<script type="text/javascript" src="/js/ewms.js"></script>


</html>