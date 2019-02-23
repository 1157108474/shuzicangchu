<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>申领出库明细</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>
<div id="formbody" class="formbody">
    <div class="layui-colla-content layui-show">
        <form class="layui-form " action="">
            <table >
                <tr>
                    <%--<td >--%>
                    <%--<div class="layui-form-item bill-search">--%>
                    <%--<label class="layui-form-label">申领单号：</label>--%>
                    <%--<div class="layui-input-block">--%>
                    <%--<input  type="text" class="layui-input" id="code">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</td>--%>
                    <td >
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">物料编码：</label>
                            <div class="layui-input-block" >
                                <input  type="text" class="layui-input" id="materialCode">
                            </div>
                        </div>
                    </td>
                    <td >
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">物料描述：</label>
                            <div class="layui-input-block">
                                <input  type="text" class="layui-input" id="description">
                            </div>
                        </div>
                    </td>
                    <td class="bill-td">
                        <a href="javascript:void(0);" class="layui-btn layui-btn-warm bill-td-button" lay-submit
                           lay-filter="formSubmit">查询</a>
                        <button class="layui-btn bill-td-button" type="reset">重置</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <input class="layui-hide" id="code" value="${code}">
    <input class="layui-hide" id="g_id">
    <input class="layui-hide" id="g_materialCode">
    <input class="layui-hide" id="g_sncode">
    <input class="layui-hide" id="g_sheetDetailId">
    <input class="layui-hide" id="g_allowCount">

    <div>
        <table id="slckdetails" lay-filter="slckdetails"></table>
    </div>
</div>
<div class="layui-collapse" lay-filter="test" id="stockDetail" style="display: none">
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">库存信息</h2>
        <div class="layui-colla-content layui-show">
            <div>
                <button class="layui-btn bill-td-button" id="add">添加</button>
            </div>
            <div>
                <table id="cldetails" lay-filter="cldetails" style="display:none"></table>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/ck/applyCKDetailed.js"></script>
</html>

