<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>计划领料明细</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>
<div id="formbody" class="formbody">
    <div>
        <input type="hidden" id="ztId" value="${ztId}">
        <input type="hidden" id="useDepId" value="${useDepId}">
        <input type="hidden" id="sheetId" value="${sheetId}">

    </div>
    <div class="layui-colla-content layui-show">
        <form class="layui-form " action="">
            <table >
                <tr>
                    <td >
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label bill-label">计划编号：</label>
                            <div class="layui-input-block bill-block ">
                                <input type="text" class="layui-input" id="planCode">
                            </div>
                        </div>
                    </td>
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
                                <input  type="text" class="layui-input" id="materialDes">
                            </div>
                        </div>
                    </td>
                    <td class="bill-td">
                        <a href="javascript:void(0);" class="layui-btn layui-btn-warm bill-td-button" lay-submit
                           lay-filter="formSubmit">查询</a>
                        <button class="layui-btn bill-td-button" type="reset">重置</button>
                        <a href="javascript:void(0);" class="layui-btn bill-td-button" id="detail_add">添加</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div>
        <table id="planDetailGrid" lay-filter="planDetailGrid"></table>
    </div>
</div>
</body>
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/apply/planDetailed.js"></script>
</html>

