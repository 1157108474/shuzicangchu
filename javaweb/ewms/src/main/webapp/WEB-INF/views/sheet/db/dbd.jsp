<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>物资调拨单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>

</head>
<body>

    <blockquote class="layui-elem-quote" style="margin-left:20px">

    <div  class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">

            <div class="layui-colla-content layui-show">
               <input type="hidden" value="${type}" id="type">
                <input type="hidden" value="${ztId}" id="ztId">

                调拨单号:

                <input id="code" autocomplete="on" placeholder="调拨单号" class="mini-textbox" type="text">

                <button class="layui-btn" lay-submit lay-filter="formSubmit">查询</button>
            </div>
        </div>
    </div>
    </blockquote>
    <div class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">
            <div class="layui-colla-content layui-show">
                <table id="sheetgrid" lay-filter="sheetgrid"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/db/dbd.js"></script>

</html>

