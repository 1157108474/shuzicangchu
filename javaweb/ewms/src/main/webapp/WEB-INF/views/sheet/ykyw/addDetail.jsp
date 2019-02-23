<%@ page language="java" pageEncoding="GBK" %>
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
                            <label class="layui-form-label bill-label">供应商：</label>
                            <div class="layui-input-block bill-block ">
                                <input type="text" class="layui-input" id="providerName" disabled width="220px">
                                <button class="layui-btn bill-button" id="provider">...</button>
                                <input class="layui-hide" name="providerDepId" id="providerId" >

                            </div>
                        </div>
                    </td>

                    <td>
                        <div class="layui-form-item ">
                            <label class="layui-form-label">原库房：</label>
                            <div class="layui-input-block">
                                <select id="store" lay-filter="store" name="storeId">
                                    <option value=""></option>
                                    <c:forEach items="${stores}" var="store" varStatus="d">
                                        <option value="${store.id}">${store.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">原库位：</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="locationName">
                                <button class="layui-btn bill-button" id="storeLocation">...</button>
                                <input class="layui-hide" name="locationId" id="locationId">
                                <input class="layui-hide" id="locationCode" >
                            </div>
                        </div>
                    </td>
                    <td class="bill-td">
                        <button class="layui-btn layui-btn-warm bill-td-button" lay-filter="search" lay-submit>查询
                        </button>
                        <button class="layui-btn bill-td-button " type="reset">重置</button>
                        <button class="layui-btn bill-td-button" id="add">添加</button>

                    </td>
                </tr>

            </table>
        </form>
        <input class="layui-input" id="newlocationName" type="hidden">
        <input class="layui-input" id="newlocationId" type="hidden">
        <input class="layui-input" id="newlocationCode" type="hidden">
        <input class="layui-input" id="ztId" type="hidden" value="${ztId}">
    </div>
    <div style="margin:0px 5px">
        <table id="detailGrid" lay-filter="detailGrid"></table>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/ykyw/addDetail.js"></script>
</html>

