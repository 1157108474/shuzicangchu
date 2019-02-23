<%@ page language="java" pageEncoding="GBK" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="GBK">
    <title>打印物资领料单</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
    <style type="text/css">
        .center_tr td {
            text-align: center;
        }

        .my_tr td {
            border: solid #000 1px;
        }
    </style>
</head>
<body>
<div class="admin-main" style="margin-left: 10px;margin-right: 10px;margin-top: 20px">
    <input id="num" type="hidden">
    <div class="noPrint">
        <a href="javascript:;" class="layui-btn  layui-btn-small" id="print"
           style="height: 30px;line-height: 30px;float:right; margin-right: 2px; margin-top: 2px">打印</a>
    </div>
    <div>
        <input id="id" value="${sheet.id}" type="hidden">
        <table width="100%">
            <tr class="center_tr">
                <td colspan="2">内蒙古中煤远兴能源化工有限公司</td>
            </tr>
            <tr class="center_tr">
                <td colspan="2">物 资 申 领 单</td>
            </tr>
            <tr class="my_tr">
                <td >&nbsp;&nbsp;日期：${sheet.createDateStr}</td>
                <td >&nbsp;&nbsp;单据编号：${sheet.code}</td>
            </tr>
        </table >
        <br>
        <table width="100%">
            <tr class="my_tr center_tr">
                <td>序号</td>
                <td>物资编码</td>
                <td>物资名称</td>
                <td>规格型号</td>
                <td>单位</td>
                <td>请发数量</td>
                <td>备注</td>
            </tr>
            <c:forEach items="${details}" var="detail" varStatus="d">
                <tr class="my_tr center_tr">
                    <td>${d.index+1}</td>
                    <td>${detail.materialCode}</td>
                    <td>${detail.materialName}</td>
                    <td>${detail.description}</td>
                    <td>${detail.detailUnitName}</td>
                    <td>${detail.detailCount}</td>
                    <td>${detail.memo}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <table width="100%">
            <tr class="my_tr">
                <td >&nbsp;&nbsp;领用中心部门领导：</td>
                <td >&nbsp;&nbsp;录入人：${sheet.personName}</td>
                <td >&nbsp;&nbsp;领用人：</td>
            </tr>
        </table >
        <iframe id="printf" src="" width="0" height="0" frameborder="0"></iframe>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/system/print/JSPrint.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
</html>
