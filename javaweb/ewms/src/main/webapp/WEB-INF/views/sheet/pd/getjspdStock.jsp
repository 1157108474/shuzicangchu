<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>添加明细</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>
<div id="formbody" class="formbody">
    <div class="layui-colla-content layui-show">
        <form class="layui-form " action="">
            <input type="hidden" id="extendInt2" name="extendInt2" value="${extendInt2}">
            <table>
                <tr>
                    <td>
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">物料编码：</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="materialCode" id="materialCode">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label bill-label">物料描述：</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="description" id="description">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">库房：</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input layui-disabled" disabled id="storeID" name="storeID" value="${storeID}" style="display: none">
                                <input type="text" class="layui-input  layui-disabled" disabled id="storeName" name="storeName" value="${storeName}">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label">供应商：</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="providerName">
                                <button class="layui-btn bill-button" id="provider">...</button>
                                <input class="layui-input" name="providerDepId" id="providerId" type="hidden">
                            </div>
                        </div>
                    </td>
                    <td class="bill-td" colspan="2">
                        <button class="layui-btn layui-btn-warm bill-td-button" lay-filter="search" lay-submit>查询
                        </button>
                        <button class="layui-btn bill-td-button " type="reset" id="reset">重置</button>
                        <button class="layui-btn bill-td-button" id="add">添加</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="margin:0px 5px">
        <table id="detailGrid" lay-filter="detailGrid"></table>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/pd/getjspdStock.js"></script>
</html>

