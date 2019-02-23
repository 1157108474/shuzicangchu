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
<div id="formbody" class="formbody">

    <div id="llSheetHead" class="layui-collapse" lay-filter="test">
        <div class="layui-colla-item">

            <div class="layui-colla-content layui-show">
               <input type="hidden" value="${type}" id="type">
                <input type="hidden" value="${ztId}" id="ztId">
                    <div class="layui-form-item">
                          <label class="layui-form-label">调拨单号:</label>
                          <div class="layui-input-block" style="position:relative;">
                           <input  id="code"  type="text" class="layui-input" >
                           </div>
                     </div>
                    <button class="layui-btn layui-btn-warm bill-td-button" id="search">查询</button>

            </div>
        </div>
    </div>
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
<script type="text/javascript" src="/js/sheet/db/details.js"></script>

</html>

