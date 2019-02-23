<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>采购订单列表</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>

</head>
<body>
<div class="admin-main" style="margin-left:0px">
    <blockquote class="layui-elem-quote" style="z-index: 9999; position: fixed ! important; left: 0px; top: 0px;width: 1150px;">
        <form class="layui-form" action="">

            订单编码:

            <input name="orderNum" autocomplete="on" placeholder="订单编码" class="mini-textbox" type="text">
            <input id="orderId" name="orderId" type="hidden" value="${orderId}">

            供应商名称:

            <input name="providerName" autocomplete="on" placeholder="供应商名称" class="mini-textbox" type="text">
            <input id="providerId" type="hidden" value="">


            <button class="layui-btn layui-btn-warm" lay-submit lay-filter="formSubmit">查询</button>
            <button type="reset" class="layui-btn bill-td-button">重置</button>
            <a href="javascript:void(0);" class="layui-btn add" id="add">确认</a>


        </form>

    </blockquote>

    <div style="position: absolute; left: 0px; top: 30px;">

        <table id="orderList" lay-filter="orderList"></table>
    </div>
</div>
</body>


<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="../../js/sheet/order/generalOrder.js"></script>

</html>