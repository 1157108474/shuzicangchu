<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
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
                                <input type="text" name="materialCode" class="layui-input">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">物料名称：</label>
                            <div class="layui-input-block">
                                <input type="text" name="materialName" class="layui-input">
                            </div>
                        </div>
                    </td>
                    <td class="bill-td">
                        <button class="layui-btn layui-btn-warm bill-td-button" lay-submit lay-filter="formSubmit">查询
                        </button>
                        <button type="reset" class="layui-btn bill-td-button">重置</button>
                        <button class="layui-btn bill-td-button" id="add">添加</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div>
        <table id="detailGrid" lay-filter="detailGrid"></table>
    </div>
</div>

</body>
<%--<script src="http://code.jquery.com/jquery-latest.js"></script>--%>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/jcwzck/addDetails.js"></script>
</html>

