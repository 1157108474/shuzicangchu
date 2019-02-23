<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>�����ϸ</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="/css/public.css" media="all"/>
</head>
<body>
<div id="formbody" class="formbody">
    <div class="layui-colla-content layui-show">
        <form class="layui-form " action="">
            <table >
                <tr>
                    <td >
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">���ϱ��룺</label>
                            <div class="layui-input-block" >
                                <input  type="text" class="layui-input" name="materialCode">
                            </div>
                        </div>
                    </td>

                    <td class="bill-td">
                        <button class="layui-btn layui-btn-warm bill-td-button" lay-filter="search" lay-submit>��ѯ</button>
                        <button class="layui-btn bill-td-button " type="reset">����</button>
                        <button class="layui-btn bill-td-button" id="add">���</button>
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
<script type="text/javascript" src="/js/check.js"></script>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/tk/detailed.js"></script>
</html>

