<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>修改领料明细</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>

<div id="formbody" class="formbody">
    <div>
        <input type="hidden" id="id" value="${id}">
        <input type="hidden" id="sheetId" value="${sheetId}">
    </div>
    <div style="padding-left:10px;padding-top: 5px">
        <table>
            <tr>
                <td>
                    <a href="javascript:void(0);" class="layui-btn bill-td-button" id="detail_edit">保存</a>
                </td>
            </tr>
        </table>
        <table id="detailsgrid" lay-filter="detailsgrid"></table>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/apply/editApply.js"></script>
</html>

