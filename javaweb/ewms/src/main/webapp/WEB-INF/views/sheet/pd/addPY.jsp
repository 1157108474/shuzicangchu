<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%><!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加盘盈数据</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body class="childrenBody">
<form class="layui-form layui-row layui-col-space10" action="" style="margin-top: 20px">
    <table style="width: 90%;margin: auto">
       <input type="hidden" value="${wzpdDetail.sheetId}" id="sheetId">
        <input type="hidden" value="${wzpdDetail.ztId}" id="ztId">
        <tr>
            <td>
                <div class="layui-form-item bill-search">
                    <label class="layui-form-label">物料编码：</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="tagCode" id="tagCode" value="${wzpdDetail.tagCode}">
                    </div>
                </div>
            </td>
            <td>
                <div class="layui-form-item bill-search">
                    <label class="layui-form-label bill-label">单位：</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="detailUnitName" id="detailUnitName" value="${wzpdDetail.detailUnitName}">
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td  colspan="2">
                <div class="layui-form-item bill-search">
                    <label class="layui-form-label">物料说明：</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="description" id="description" value="${wzpdDetail.description}">
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="layui-form-item bill-search">
                    <label class="layui-form-label">数量：</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="sysCount" id="sysCount" value="${wzpdDetail.detailCount-wzpdDetail.sysCount}">
                    </div>
                </div>
            </td>
            <td>
                <div class="layui-form-item bill-search">
                    <label class="layui-form-label bill-label">供应商：</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" id="providerName">
                        <button class="layui-btn bill-button" id="provider">...</button>
                        <input class="layui-input" name="providerDepId" id="providerId" type="hidden">
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="layui-form-item">
                    <label class="layui-form-label">库房：<span style="color: red">*</span></label>
                    <div class="layui-input-block">
                        <select name="storeId" id="storeId" name="storeId"  lay-filter="storeId">
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
                    <label class="layui-form-label">库位：<span style="color: red">*</span></label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" id="locationName">
                        <button class="layui-btn bill-button" id="storeLocation">...</button>
                        <input class="layui-input" name="locationId" id="locationId" type="hidden">
                        <input class="layui-input" id="locationCode" type="hidden">
                        <%--<input type="text" class="layui-input" name="storeLocationId" id="storeLocationId" >--%>
                    </div>
                </div>
            </td>
        </tr>
        <%--<td class="bill-td" colspan="2">
                <button class="layui-btn layui-btn-warm bill-td-button" lay-filter="search" lay-submit>查询
                </button>
                <button class="layui-btn bill-td-button " type="reset" id="reset">重置</button>
                <button class="layui-btn bill-td-button" id="add">添加</button>
            </td>--%>
        <tr>
            <td>
                <div class="layui-form-item">
                    <label class="layui-form-label">启用序列号：</label>
                    <div class="layui-input-block">
                        <select name="enableSN" id="enableSN"  lay-filter="enableSN">
                            <option value="0">否</option>
                            <option value="1">是</option>
                        </select>
                    </div>
                </div>
            </td>
            <td>
                <div class="layui-form-item bill-search">
                    <label class="layui-form-label bill-label">序列号：</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" name="snCode" id="snCode"  disabled="disabled">
                    </div>
                </div>
            </td>
        </tr>
    </table>
    <div style="margin:10px 40%">
        <button class="layui-btn bill-td-button" id="add" style="height: 30px;line-height: 30px;">添加</button>
        <button class="layui-btn bill-td-button" id="reset" style="height: 30px;line-height: 30px;">取消</button>
    </div>
</form>
</body>
<script type="text/javascript" src="<%=path %>/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/pd/addPY.js"></script>
</html>