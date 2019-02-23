<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%
    List<String> buttonNames = (List<String>) request.getSession().getAttribute("buttonNames");
%>
<!DOCTYPE html>
<html lang="en">
<head>


    <title>入库单列表</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all"/>

</head>
<body>
<div class="admin-main" style="margin-left:20px">
    <blockquote class="layui-elem-quote">
        <form class="layui-form" action="">

            入库单号:

            <input name="code" autocomplete="on" placeholder="入库单号" class="mini-textbox" type="text">

            采购订单编号:

            <input name="orderNum" autocomplete="on" placeholder="采购订单编号" class="mini-textbox" type="text">

            <button class="layui-btn" lay-submit lay-filter="formSubmit">查询</button>
            <a href="javascript:void(0);" class="layui-btn add" id="add">确认</a>
            <input type="hidden" id="creator" value="${creator}">


        </form>

    </blockquote>

    <div>

        <table id="rkdList" lay-filter="rkdList"></table>
    </div>
</div>
</body>


<script type="text/javascript" src="../../plugins/layui/layui.js"></script>
<script type="text/javascript" src="../../js/dateUtil.js"></script>
<script type="text/javascript" src="../../js/sheet/rk/generalRKD.js"></script>

</html>