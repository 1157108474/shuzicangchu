<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>ֱ�ӳ�����ϸ</title>
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
                            <label class="layui-form-label">���ϱ��룺</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="materialCode">
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="layui-form-item bill-search">
                            <label class="layui-form-label">�ⷿ��</label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="storelocationname" name="storelocationname">
                            </div>
                        </div>
                    </td>
                    <td class="bill-td">
                        <a href="javascript:void(0);" class="layui-btn layui-btn-warm bill-td-button" lay-submit
                           lay-filter="formSubmit">��ѯ</a>
                        <button class="layui-btn bill-td-button" type="reset">����</button>
                        <a href="javascript:void(0);" class="layui-btn bill-td-button" id="add">����</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div>
        <table id="zjckdetails" lay-filter="zjckdetails"></table>
    </div>
</div>
</body>
<script type="text/javascript" src="/plugins/layui/layui.js"></script>
<script type="text/javascript" src="/js/sheet/ck/zjCKDetailed.js"></script>
</html>
