<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>出库单号</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="admin-main">

    <div style="margin-left: 5px;">

        <div class="layui-form-item bill-search">
            <label class="layui-form-label">出库单号：</label>
            <input type="text"  id="code" >
            <button class="layui-btn layui-btn-warm bill-td-button" id="search">查询</button>
        </div>
        <div style="margin-left: 10px;">
            <table id="cKOrderList" lay-filter="cKOrderList"></table>
        </div>

    </div>

</div>
</body>

<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/ck/CKOrder.js"></script>
<script type="text/javascript" src="/js/ewms.js"></script>


</html>