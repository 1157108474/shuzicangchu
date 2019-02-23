<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <input type="text" class="layui-input" name="materialCode">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label bill-label">物料描述：</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" name="description">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">物料分类：</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="wlfl1" name="wlfl1" style="width: 40%;float: left">
                                <span style="width: 8%;float: left">到</span>
                                <input type="text" class="layui-input" id="wlfl2" name="wlfl2" style="width: 40%;float: left">
                            </div>
                        </div>
                    </td>

                </tr>
                <tr>
                    <td>
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">库房：</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="storeID" name="storeID" value="${storeID}" readonly="readonly" style="display: none">
                                <input type="text" class="layui-input" id="storeName" name="storeName" value="${storeName}" readonly="readonly">
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
                    <td>
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">库位范围：</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="storeLocationID1" name="storeLocationID1" style="width: 40%;float: left">
                                <span style="width: 8%;float: left">到</span>
                                <input type="text" class="layui-input" id="storeLocationID2" name="storeLocationID2" style="width: 40%;float: left">
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
        <input class="layui-input" id="newlocationName" type="hidden">
        <input class="layui-input" id="newlocationId" type="hidden">
        <input class="layui-input" id="newlocationCode" type="hidden">
    </div>
    <div style="margin:0px 5px">
        <table id="detailGrid" lay-filter="detailGrid"></table>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/pd/getxhpdStock.js"></script>
</html>

